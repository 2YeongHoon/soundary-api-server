package com.domain.external.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.domain.external.s3.config.S3Properties;
import com.domain.user.enums.UploadType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3ImageService {
  private final AmazonS3 amazonS3;
  private final S3Properties s3Properties;

  public String upload(MultipartFile image, UploadType uploadType) throws Exception {
    if(image.isEmpty() || Objects.isNull(image.getOriginalFilename())){
      throw new Exception();
    }
    return this.uploadImage(image, uploadType);
  }

  private String uploadImage(MultipartFile image, UploadType uploadType) throws Exception {
    this.validateImageFileExtention(image.getOriginalFilename());
    try {
      return this.uploadImageToS3(image, uploadType);
    } catch (IOException e) {
      throw new Exception();
    }
  }

  private void validateImageFileExtention(String filename) throws Exception {
    int lastDotIndex = filename.lastIndexOf(".");
    if (lastDotIndex == -1) {
      throw new Exception();
    }

    String extention = filename.substring(lastDotIndex + 1).toLowerCase();
    List<String> allowedExtentionList = Arrays.asList("jpg", "jpeg", "png", "gif");

    if (!allowedExtentionList.contains(extention)) {
      throw new Exception();
    }
  }

  private String uploadImageToS3(MultipartFile image, UploadType uploadType) throws IOException {
    String s3FileName = UUID.randomUUID().toString().substring(0, 20);
    String uploadUrl =  s3Properties.getS3().getBucketName() + uploadType.getCode();

    InputStream is = image.getInputStream();
    byte[] bytes = IOUtils.toByteArray(is);

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentType(image.getContentType());
    metadata.setContentLength(bytes.length);
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

    try{
      PutObjectRequest putObjectRequest =
          new PutObjectRequest(uploadUrl, s3FileName, byteArrayInputStream, metadata)
              .withCannedAcl(CannedAccessControlList.PublicRead);
      amazonS3.putObject(putObjectRequest); // put image to S3
    }catch (Exception e){
      throw e;
    }finally {
      byteArrayInputStream.close();
      is.close();
    }

    return s3FileName;
  }

}
