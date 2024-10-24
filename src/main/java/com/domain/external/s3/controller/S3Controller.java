package com.domain.external.s3.controller;

import com.domain.common.dto.SuccessResponse;
import com.domain.common.utils.SuccessResponseUtils;
import com.domain.external.s3.service.S3ImageService;
import com.domain.external.s3.service.S3Mp3Service;
import com.domain.user.enums.UploadType;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value = "/s3")
@RestController
@RequiredArgsConstructor
public class S3Controller {

  private final S3ImageService s3ImageService;
  private final S3Mp3Service s3Mp3Service;

  @PostMapping(value="/upload")
  public ResponseEntity<SuccessResponse<String>> upload(@RequestParam MultipartFile uploadFile, @RequestParam("uploadType") UploadType uploadType)
      throws Exception {
    String result = s3ImageService.upload(uploadFile, uploadType);
    return SuccessResponseUtils.successResponse(result);
  }

  @GetMapping("/download/instrument-ogg")
  public ResponseEntity<InputStreamResource> downloadOgg(@RequestParam Long instrumentId, @RequestParam String oggName) {
    InputStream inputStream = s3Mp3Service.downloadInstrumentOgg(instrumentId, oggName);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + oggName + "\"")
        .contentType(MediaType.parseMediaType("audio/ogg"))
        .body(new InputStreamResource(inputStream));
  }

}
