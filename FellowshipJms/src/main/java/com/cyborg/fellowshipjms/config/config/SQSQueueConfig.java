package com.cyborg.fellowshipjms.config.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author saranshk04
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aws.sqs.queue.names")
public class SQSQueueConfig {

    private String notificationQueue;
}
