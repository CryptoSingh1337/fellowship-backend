package com.cyborg.fellowshipscheduler.jobs;

import com.cyborg.fellowshipjms.config.consumer.SQSConsumer;
import com.cyborg.fellowshipjms.config.payload.ScholarshipNotification;
import com.cyborg.fellowshipscheduler.mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author saranshk04
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationJob {

    private final SQSConsumer sqsConsumer;
    private final MailService mailService;

    @Scheduled(cron = "*/45 * * * * *")
    void sendNotifications() {
        List<ScholarshipNotification> scholarshipNotifications = sqsConsumer.consumeMessageTillAvailable();
        if (!scholarshipNotifications.isEmpty())
            mailService.sendMail(scholarshipNotifications);
        else
            log.info("No notifications exists");
    }
}
