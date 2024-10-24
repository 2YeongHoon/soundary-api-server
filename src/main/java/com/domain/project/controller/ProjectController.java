package com.domain.project.controller;

import com.domain.common.dto.SuccessResponse;
import com.domain.common.utils.SuccessResponseUtils;
import com.domain.project.controller.dto.request.CreateProjectRequest;
import com.domain.project.controller.dto.request.CreateSocketRequest;
import com.domain.project.controller.dto.request.HistoryRequest;
import com.domain.project.controller.dto.request.InviteProjectRequest;
import com.domain.project.controller.dto.request.InvolveProjectRequest;
import com.domain.project.controller.dto.request.RemoveUserRequest;
import com.domain.project.controller.dto.request.UpdateInvolvedUserRequest;
import com.domain.project.controller.dto.request.UpdateJsonRequest;
import com.domain.project.controller.dto.request.UpdateProjectRequest;
import com.domain.project.controller.dto.response.DecryptLinkResponse;
import com.domain.project.controller.dto.response.InvolvedUserResponse;
import com.domain.project.controller.dto.response.ProjectJsonDataResponse;
import com.domain.project.controller.dto.response.RetrieveProjectsResponse;
import com.domain.project.controller.dto.response.RetrieveSocketResponse;
import com.domain.project.service.ProjectManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/projects")
@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectManagementService projectManagementService;


    @GetMapping
    public ResponseEntity<SuccessResponse<List<RetrieveProjectsResponse>>> retrieve(@RequestParam String keywords) {
        return SuccessResponseUtils.successResponse(projectManagementService.retrieve(keywords));
    }

    @GetMapping("public")
    public ResponseEntity<SuccessResponse<List<RetrieveProjectsResponse>>> retrieveAll(@RequestParam String keywords) {
        return SuccessResponseUtils.successResponse(projectManagementService.getPublicProjects(keywords));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> create(@RequestBody @Valid CreateProjectRequest request) {
        projectManagementService.create(request);
        return SuccessResponseUtils.successResponse();
    }

    @GetMapping("/{project-id}")
    public ResponseEntity<SuccessResponse<RetrieveProjectsResponse>> getById(@PathVariable("project-id") Long projectId) {
        return SuccessResponseUtils.successResponse(projectManagementService.getById(projectId));
    }

    @GetMapping("/involved-user/{project-id}")
    public ResponseEntity<SuccessResponse<List<InvolvedUserResponse>>> getInvolvedUsers(@PathVariable("project-id") Long projectId) {
        return SuccessResponseUtils.successResponse(projectManagementService.getInvolvedUsers(projectId));
    }

    @PostMapping("/invite")
    public ResponseEntity<SuccessResponse<String>> invite(@RequestBody @Valid InviteProjectRequest request) throws Exception {
        return SuccessResponseUtils.successResponse(projectManagementService.inviteUser(request));
    }

    @PostMapping("/involve")
    public ResponseEntity<SuccessResponse> invite(@RequestBody @Valid InvolveProjectRequest request) throws Exception {
        projectManagementService.involveUser(request);
        return SuccessResponseUtils.successResponse();
    }

    @PreAuthorize("@projectSecurity.isOwner(#request.projectId)")
    @PostMapping("/remove-users")
    public ResponseEntity<SuccessResponse> removeUser(@RequestBody RemoveUserRequest request) throws Exception {
        projectManagementService.removeUsers(request);
        return SuccessResponseUtils.successResponse();
    }

    @GetMapping("/decrypt-link")
    public ResponseEntity<SuccessResponse<DecryptLinkResponse>> decryptLink(@RequestParam String inviteKey) throws Exception {
        return SuccessResponseUtils.successResponse(projectManagementService.decryptLink(inviteKey));
    }

    @PreAuthorize("@projectSecurity.isEditableUser(#request.projectId)")
    @PutMapping
    public ResponseEntity<SuccessResponse> update(@RequestBody @Valid UpdateProjectRequest request) {
        projectManagementService.update(request);
        return SuccessResponseUtils.successResponse();
    }

    @GetMapping("/json-data")
    public ResponseEntity<SuccessResponse<JsonNode>> getJsonData(@RequestParam Long projectId)
        throws JsonProcessingException {
        return SuccessResponseUtils.successResponse(projectManagementService.getJsonData(projectId));
    }

    @PreAuthorize("@projectSecurity.isEditableUser(#request.projectId) || @projectSecurity.fromSocket(#httpServletRequest.getHeader('fromSocket'))")
    @PutMapping("/json-data")
    public ResponseEntity<SuccessResponse> updateJsonData(@RequestBody @Valid UpdateJsonRequest request,
        HttpServletRequest httpServletRequest) {
        projectManagementService.updateJsonData(request);
        return SuccessResponseUtils.successResponse();
    }

    @PreAuthorize("@projectSecurity.isOwner(#request.projectId)")
    @PutMapping("/involved-user")
    public ResponseEntity<SuccessResponse> updateInvolvedUser(@RequestBody UpdateInvolvedUserRequest request) {
        projectManagementService.updateInvolvedUser(request.getUserList());
        return SuccessResponseUtils.successResponse();
    }

    @PreAuthorize("@projectSecurity.isEditableUser(#projectId)")
    @DeleteMapping("/{project-id}")
    public ResponseEntity<SuccessResponse> remove(@PathVariable("project-id") Long projectId) {
        projectManagementService.delete(projectId);
        return SuccessResponseUtils.successResponse();
    }

    @PostMapping("/action-history")
    public ResponseEntity<SuccessResponse> saveActionHistory(@RequestBody @Valid HistoryRequest request) throws Exception {
        projectManagementService.saveActionHistory(request);
        return SuccessResponseUtils.successResponse();
    }

    @PostMapping("/object-history")
    public ResponseEntity<SuccessResponse> saveObjectHistory(@RequestBody @Valid HistoryRequest request) throws Exception {
        projectManagementService.saveObjectHistory(request);
        return SuccessResponseUtils.successResponse();
    }

    @GetMapping("/object-history")
    public ResponseEntity<SuccessResponse<ProjectJsonDataResponse>> getObjectHistory(@RequestParam Long projectId) throws Exception {
        return SuccessResponseUtils.successResponse(projectManagementService.getProjectHistoryJsonData(projectId));
    }

    @PreAuthorize("@projectSecurity.fromSocket(#httpServletRequest.getHeader('fromSocket'))")
    @PostMapping("/socket")
    public ResponseEntity<SuccessResponse> createSocket(@RequestBody CreateSocketRequest request,
        HttpServletRequest httpServletRequest) throws Exception {
        projectManagementService.saveSocket(request);
        return SuccessResponseUtils.successResponse();
    }

    @PreAuthorize("@projectSecurity.fromSocket(#httpServletRequest.getHeader('fromSocket'))")
    @GetMapping("/socket")
    public ResponseEntity<SuccessResponse<RetrieveSocketResponse>> getSocket(@RequestParam Long projectId,
        HttpServletRequest httpServletRequest) throws Exception {
        return SuccessResponseUtils.successResponse(projectManagementService.getSocket(projectId));
    }

    @PreAuthorize("@projectSecurity.fromSocket(#httpServletRequest.getHeader('fromSocket'))")
    @DeleteMapping("/socket/{project-id}")
    public ResponseEntity<SuccessResponse> removeSocket(@PathVariable("project-id") Long projectId,
        HttpServletRequest httpServletRequest) {
        projectManagementService.deleteSocket(projectId);
        return SuccessResponseUtils.successResponse();
    }
}
