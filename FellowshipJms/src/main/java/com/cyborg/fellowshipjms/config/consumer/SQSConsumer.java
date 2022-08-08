package com.cyborg.fellowshipjms.config.consumer;

import com.cyborg.fellowshipjms.config.config.SQSQueueConfig;
import com.cyborg.fellowshipjms.config.payload.ScholarshipNotification;
import com.cyborg.utilities.sqs.SQSUtil;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saranshk04
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SQSConsumer {

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final SQSQueueConfig sqsQueueConfig;

    public ScholarshipNotification consumeMessageFromNotificationQueue() {
        return queueMessagingTemplate.receiveAndConvert(SQSUtil.get(sqsQueueConfig.getNotificationQueue()),
                ScholarshipNotification.class);
    }

    public List<ScholarshipNotification> consumeMessageTillAvailable() {
        List<ScholarshipNotification> scholarshipNotifications = new ArrayList<>();
        ScholarshipNotification scholarshipNotification = new ScholarshipNotification();
        while (scholarshipNotification != null) {
            scholarshipNotification = consumeMessageFromNotificationQueue();
            if (scholarshipNotification != null)
                scholarshipNotifications.add(scholarshipNotification);
        }
        return scholarshipNotifications;
    }
}
