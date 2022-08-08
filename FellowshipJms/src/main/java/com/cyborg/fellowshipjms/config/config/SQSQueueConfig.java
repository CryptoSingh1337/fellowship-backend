package com.cyborg.fellowshipjms.config.config;

import com.cyborg.utilities.sqs.SQSUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;

/**
 * @author saranshk04
 */
@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aws.sqs.queue.names")
@DependsOn("SQSUtil")
public class SQSQueueConfig {

    private String notificationQueue;

    @PostConstruct
    public void initialize() {
        log.info("Notification queue url: {}", SQSUtil.get(notificationQueue));
    }
}
