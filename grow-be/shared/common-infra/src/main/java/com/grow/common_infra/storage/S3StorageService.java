package com.grow.common_infra.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.grow.common_core.domain.value_objects.File;
import com.grow.common_core.storage.StorageService;
import com.grow.common_core.storage.model.UploadFileRequest;
import com.grow.common_infra.config.AWSConfig;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.UUID;

@Component
@AllArgsConstructor
public class S3StorageService implements StorageService {

    private final AWSConfig awsConfig;
    private final AmazonS3 s3Client;

    @Override
    public File upload(@NonNull UploadFileRequest request) {
        byte[] content = request.content();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(content.length);
        objectMetadata.setContentType(request.contentType());
        String objectKey = constructFileName();
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                awsConfig.getS3BucketName(),
                objectKey, new ByteArrayInputStream(content), objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        s3Client.putObject(putObjectRequest);
        URL url = s3Client.getUrl(awsConfig.getS3BucketName(), objectKey);
        return new File(url, request.name());
    }

    private String constructFileName() {
        return UUID.randomUUID().toString();
    }
}
