package com.grow.common_infra.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AWSConfig {

    @Value("${aws.cognitoRegion}")
    private String cognitoRegion;

    @Value("${aws.cognitoUserPoolId}")
    private String cognitoUserPoolId;

    @Value("${aws.cognitoWebClientId}")
    private String cognitoWebClientId;

    @Value("${aws.s3.bucketName}")
    private String s3BucketName;

    @Bean
    public AWSCredentialsProvider getCredentialsProvider() {
        return new AWSCredentialsProviderChain(
                new InstanceProfileCredentialsProvider(false),
                new EnvironmentVariableCredentialsProvider(),
                new ProfileCredentialsProvider());
    }

    @Bean
    public AWSCognitoIdentityProvider getAmazonCognitoIdentityProvider() {
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(getCredentialsProvider())
                .withRegion(cognitoRegion)
                .build();
    }

    @Bean
    public AmazonS3 amazonS3Provider() {
        return AmazonS3Client.builder()
                .withCredentials(getCredentialsProvider())
                .withRegion(cognitoRegion)
                .build();
    }
}
