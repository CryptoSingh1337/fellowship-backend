package com.cyborg.fellowshipjms.config.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.ExecutorFactory;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author saranshk04
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class AwsSQSConfig {

    private String accessKey;
    private String secretKey;
    private final Environment env;

    @PostConstruct
    public void initialize() {
        accessKey = Objects.requireNonNull(env.getProperty("aws.sqs.access-key"));
        secretKey = Objects.requireNonNull(env.getProperty("aws.sqs.secret-key"));
            log.info("----------------------- Aws credentials -----------------------");
            log.info("Access key: {}", accessKey);
            log.info("Secret key: {}", secretKey);
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate() {
        return new QueueMessagingTemplate(amazonSQSAsync());
    }

    private AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withRegion(Regions.AP_SOUTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(accessKey, secretKey)))
                .withExecutorFactory(getExecutorFactory())
                .build();
    }

    private ExecutorFactory getExecutorFactory() {
        return () -> new ThreadPoolExecutor(5, 10, 0L,
                SECONDS, new ArrayBlockingQueue<>(100, true));
    }
}
