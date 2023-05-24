package com.grow.integration_test;

import an.awesome.pipelinr.Pipeline;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.grow.common_core.auth.AuthClient;
import com.grow.application.GrowApplication;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = GrowApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {
    @ClassRule
    private static final PostgreSQLContainer container;
    @Autowired
    protected Pipeline commandPipeline;
    @Autowired
    protected Pipeline queryPipeline;

    @MockBean
    protected AuthClient authClient;
    @MockBean
    protected AmazonS3 amazonS3;
    @MockBean
    protected AWSCognitoIdentityProvider cognitoIDP;

    static {
        DockerImageName myImage = DockerImageName.parse("postgres:latest").asCompatibleSubstituteFor("postgres");
        container =
                new PostgreSQLContainer<>(myImage)
                        .withDatabaseName("postgres")
                        .withUsername("postgres")
                        .withPassword("root")
                        .withReuse(true);
        container.start();
    }

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}
