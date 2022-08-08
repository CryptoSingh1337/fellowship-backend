package com.cyborg.fellowshipjms.config.producer;

import com.cyborg.fellowshipjms.config.config.SQSQueueConfig;
import com.cyborg.utilities.sqs.SQSUtil;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author saranshk04
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SQSProducer {

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final SQSQueueConfig sqsQueueConfig;

    public <T> void publishToNotificationQueue(T payload) {
        log.info("Sending scholarship payload: {}", payload);
        String endpoint = SQSUtil.get(sqsQueueConfig.getNotificationQueue());
        queueMessagingTemplate.convertAndSend(endpoint, payload);
    }
}
