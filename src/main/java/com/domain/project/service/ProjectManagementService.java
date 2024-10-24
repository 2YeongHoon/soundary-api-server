package com.domain.project.service;

import com.domain.common.enums.Authority;
import com.domain.common.utils.AesEncryptUtils;
import com.domain.common.utils.EncryptLinkUtils;
import com.domain.external.mail.builder.MailSender;
import com.domain.external.mail.dto.MailDto;
import com.domain.external.mail.dto.MailParams;
import com.domain.external.mail.enums.TemplateType;
import com.domain.notification.enums.NotificationType;
import com.domain.notification.service.NotificationManagementService;
import com.domain.notification.service.dto.CreateNotificationDto;
import com.domain.project.controller.dto.request.CreateProjectRequest;
import com.domain.project.controller.dto.request.CreateSocketRequest;
import com.domain.project.controller.dto.request.HistoryRequest;
import com.domain.project.controller.dto.request.InviteProjectRequest;
import com.domain.project.controller.dto.request.InvolveProjectRequest;
import com.domain.project.controller.dto.request.InvolvedUserRequest;
import com.domain.project.controller.dto.request.RemoveUserProjectRequest;
import com.domain.project.controller.dto.request.RemoveUserRequest;
import com.domain.project.controller.dto.request.UpdateJsonRequest;
import com.domain.project.controller.dto.request.UpdateProjectRequest;
import com.domain.project.controller.dto.response.DecryptLinkResponse;
import com.domain.project.controller.dto.response.InvolvedUserResponse;
import com.domain.project.controller.dto.response.ProjectJsonDataResponse;
import com.domain.project.controller.dto.response.RetrieveProjectsResponse;
import com.domain.project.controller.dto.response.RetrieveSocketResponse;
import com.domain.project.domain.Project;
import com.domain.project.domain.ProjectActionHistory;
import com.domain.project.domain.ProjectObjectHistory;
import com.domain.project.domain.ProjectSocket;
import com.domain.user.controller.dto.response.UserInfoResponse;
import com.domain.user.domain.Account;
import com.domain.user.domain.User;
import com.domain.user.domain.UserProject;
import com.domain.user.service.AccountService;
import com.domain.user.service.AuthService;
import com.domain.user.service.UserProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProjectManagementService {

    @Value("${custom.client.base-url}")
    private String clientBaseUrl;

    private final AuthService authService;
    private final AccountService accountService;
    private final ProjectService projectService;
    private final UserProjectService userProjectService;
    private final ProjectHistoryService projectHistoryService;
    private final ObjectMapper objectMapper;
    private final MailSender mailSender;
    private final NotificationManagementService notificationManagementService;

    @Transactional
    public RetrieveProjectsResponse getById(Long projectId) {
        final String loginId = authService.getLoginId();
        Project project = projectService.getByProjectId(projectId);
        Account account = accountService.getByLoginId(loginId);

        return RetrieveProjectsResponse.of(project, account);
    }

    @Transactional
    public List<InvolvedUserResponse> getInvolvedUsers(Long projectId) {
        return userProjectService.getInvolvedUsers(projectId);
    }

    @Transactional
    public List<RetrieveProjectsResponse> retrieve(final String keywords) {
        final String loginId = authService.getLoginId();
        Account account = accountService.getByLoginId(loginId);
        final Long userId = account.getUser().getId();

        return projectService.findAllByUserId(userId, keywords).stream()
            .map(project -> RetrieveProjectsResponse.of(project, account))
            .collect(Collectors.toList());
    }

    @Transactional
    public List<RetrieveProjectsResponse> getPublicProjects(final String keywords) {
        final String loginId = authService.getLoginId();
        Account account = accountService.getByLoginId(loginId);

        return projectService.getPublicProjects(keywords).stream()
            .map(project -> RetrieveProjectsResponse.of(project, account))
            .collect(Collectors.toList());
    }

    public void create(CreateProjectRequest request) {
        final String loginId = authService.getLoginId();
        final String jsonData = "{\"arrangementList\":[],\"markerList\":[],\"tempoList\":[{ \"id\": \"firstemp\", \"width\": 100, \"value\": 120, \"end\": 120 }],\"instrumentList\":[],\"blockList\":[],\"noteList\":[]}}";

        Account account = accountService.getByLoginId(loginId);

        Project project = Project.builder()
            .name(request.getName())
            .description(request.getDescription())
            .genre(request.getGenre())
            .imageUrl(request.getImageFile())
            .publicYn(request.getPublicYn())
            .data(jsonData)
            .build();

        UserProject userProject = UserProject.of(
            account.getUser(), project, Authority.OWNER
        );

        project.addUserProject(userProject);
        projectService.save(project);
    }

    public void update(UpdateProjectRequest request) {
        // 프로젝트 조회
        Project project = projectService.getByProjectId(request.getProjectId());

        project.setPublicYn(request.getPublicYn());
        project.setName(request.getProjectName());
        project.setDescription(request.getDescription());
        project.setGenre(request.getGenre());
        project.setImageUrl(request.getImageUrl());

        projectService.save(project);
    }

    @Transactional
    public JsonNode getJsonData(Long projectId) throws JsonProcessingException {
        String jsonString = projectService.getJsonData(projectId);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        return jsonNode;
    }


    public void updateJsonData(UpdateJsonRequest request) {
        // 프로젝트 조회
        Project project = projectService.getByProjectId(request.getProjectId());

        project.setData(request.getJsonData().toString());

        projectService.save(project);
    }

    @Transactional
    public void updateInvolvedUser(List<InvolvedUserRequest> request) {
        // 유저 프로젝트 조회
        List<Long> ids = request.stream().map(x -> x.getId()).collect(Collectors.toList());
        List<UserProject> list = userProjectService.getByIds(ids);
        for (UserProject userProject : list) {
            Authority authority = request.stream().filter(x -> x.getId() == userProject.getId())
                .findFirst().get().getAuthority();
            userProject.updateAuthority(authority);
        }
    }

    public void delete(Long id) {
        Project project = projectService.getByProjectId(id);
        final String projectName = project.getName();
        final String ownerName = project.getOwnUser().map(User::getAlias).orElse(null);

        List<CreateNotificationDto> dtos = project.getInvolvedUsers().stream()
            .map(involvedUser -> CreateNotificationDto.of(involvedUser,
                NotificationType.PROJECT_DELETE, ownerName,
                projectName))
            .collect(Collectors.toList());

        projectService.delete(id);
        notificationManagementService.sendAll(dtos);
    }

    public void deleteSocket(Long projectId) {
        projectService.deleteSocket(projectId);
    }

    public String inviteUser(InviteProjectRequest request) throws Exception {
        // 초대 링크 생성
        // 1. 만료 시간 설정
        LocalDateTime expireTime = LocalDateTime.now().plusHours(12);

        // 2. 데이터를 Map에 넣기
        Map<String, String> dataToEncrypt = new HashMap<>();
        dataToEncrypt.put("projectId", request.getProjectId().toString());
        dataToEncrypt.put("expireTime", expireTime.format(DateTimeFormatter.ISO_DATE_TIME));
        dataToEncrypt.put("authority", request.getAuthority().toString());

        String link = EncryptLinkUtils.createEncryptedLink(dataToEncrypt);

        // 초대 메일 전송
        UserInfoResponse user = authService.me();
        Project projectInfo = this.projectService.getByProjectId(request.getProjectId());

        MailParams mailParams = MailParams.builder()
            .senderEmail(user.getLoginId())
            .senderName(user.getAlias())
            .projectName(projectInfo.getName())
            .link(clientBaseUrl + "invite-link?inviteKey=" + link)
            .build();

        mailSender.send(
            MailDto.of(
                TemplateType.PROJECT_INVITE,
                mailParams,
                "[프로젝트 초대] 프로젝트 " + projectInfo.getName() + "에 초대되었습니다",
                request.getReceiverEmails()
            ));

        // TODO: 초대 알림 발송

        return link;
    }

    @Transactional
    public void involveUser(InvolveProjectRequest request) throws Exception {
        User invitee = accountService.getByLoginId(request.getLoginId()).getUser();
        Project project = projectService.getByProjectId(request.getProjectId());

        final String inviterName = authService.getAccount().getUser().getAlias();
        final String projectName = project.getName();

        UserProject userProject = UserProject.of(invitee, project, request.getAuthority());
        project.addUserProject(userProject);
        projectService.save(project);

//        notificationManagementService.send(
//            CreateNotificationDto.of(invitee, NotificationType.PROJECT_INVITE, inviterName,
//                projectName));
    }

    @Transactional
    public void removeUsers(RemoveUserRequest request) throws Exception {

        final String projectName = projectService.getByProjectId(request.getProjectId()).getName();

        // 추방하기
        List<Long> userProjectIds = request.getUsers().stream()
            .map(RemoveUserProjectRequest::getUserProjectId).collect(
                Collectors.toList());
        List<String> loginIds = request.getUsers().stream()
            .map(RemoveUserProjectRequest::getLoginId)
            .collect(Collectors.toList());
        List<String> excludedUserMails = request.getUsers().stream()
            .map(RemoveUserProjectRequest::getLoginId)
            .map(loginId -> loginId.split("_")[1])
            .collect(Collectors.toList());

        userProjectService.removeUsers(userProjectIds);

        // 추방 당한 유저에게 이메일 전송
        Account sender = authService.getAccount();
        MailParams mailParams = MailParams.builder()
            .senderEmail(sender.getLoginId().split("_")[1])
            .senderName(sender.getUser().getAlias())
            .projectName(request.getProjectName())
            .build();

        mailSender.send(
            MailDto.of(
                TemplateType.PROJECT_OUT,
                mailParams,
                "[프로젝트 삭제] 프로젝트 " + request.getProjectName() + "에서 제거되었습니다.",
                excludedUserMails
            ));

        List<CreateNotificationDto> excludedUsersDto = loginIds.stream()
            .map(accountService::findByLoginId)
            .flatMap(Optional::stream)
            .map(Account::getUser)
            .map(excludedUser -> CreateNotificationDto.of(
                excludedUser,
                NotificationType.PROJECT_EXCLUDE,
                sender.getUser().getAlias(),
                projectName))
            .collect(Collectors.toList());

        notificationManagementService.sendAll(excludedUsersDto);
    }

    public DecryptLinkResponse decryptLink(String encryptedInfo) throws Exception {
        // 1. 암호화된 데이터를 복호화
        String decryptedData = AesEncryptUtils.decrypt(encryptedInfo);

        // 2. 복호화된 데이터를 Map으로 변환 (JSON 파싱)
        DecryptLinkResponse response = objectMapper.readValue(decryptedData,
            DecryptLinkResponse.class);
        return response;
    }

    @Transactional
    public void saveObjectHistory(HistoryRequest request) {
        ProjectObjectHistory projectObjectHistory = projectHistoryService.getObjectHistory(
            request.getProjectId());

        if (projectObjectHistory == null) {
            Project project = projectService.getByProjectId(request.getProjectId());
            ProjectObjectHistory history = ProjectObjectHistory.of(project,
                request.getData().toString());
            projectHistoryService.saveObjectHistory(history);
        } else {
            projectObjectHistory.update(request.getData().toString());
        }
    }

    @Transactional
    public void saveActionHistory(HistoryRequest request) {
        Project project = projectService.getByProjectId(request.getProjectId());
        ProjectActionHistory history = ProjectActionHistory.of(project,
            request.getData().toString());
        projectHistoryService.saveActionHistory(history);
    }

    @Transactional
    public ProjectJsonDataResponse getProjectHistoryJsonData(Long projectId) {
        ProjectObjectHistory objectHistory = projectHistoryService.getObjectHistory(projectId);
        return ProjectJsonDataResponse.of(projectId, objectHistory.getData());
    }

    @Transactional
    public void saveSocket(CreateSocketRequest request) {
        ProjectSocket projectSocket = projectService.getSocket(request.getProjectId());

        if (projectSocket == null) {
            Project project = projectService.getByProjectId(request.getProjectId());
            ProjectSocket socket = ProjectSocket.of(project, request.getPort());
            project.setProjectSocket(socket);
            projectService.save(project);
        } else {
            projectSocket.update(request.getPort());
        }
    }

    @Transactional
    public RetrieveSocketResponse getSocket(Long projectId) {
        return RetrieveSocketResponse.from(projectService.getSocket(projectId));
    }

    //TODO: 이미지 업로드 구현
    public void updateImage() {
    }

    private String updateImageFile(MultipartFile file) {
        return null;
    }
}