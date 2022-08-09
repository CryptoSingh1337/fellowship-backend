package com.cyborg.fellowshipscheduler.config;

import com.cyborg.fellowshipscheduler.jobs.CleanupJob;
import com.cyborg.fellowshipscheduler.jobs.NotificationJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.PostConstruct;

/**
 * @author saranshk04
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

    private final ThreadPoolTaskScheduler taskScheduler;
    private final CleanupJob cleanupJob;
    private final NotificationJob notificationJob;
    @Value("${scheduler.jobs.cron.clean-up}")
    private String cleanUp;
    @Value("${scheduler.jobs.cron.notification}")
    private String notification;

    @PostConstruct
    public void initialize() {
        log.info("------------------------ Scheduler Config ------------------------");
        log.info("CLEAN UP cron: {}", cleanUp);
        log.info("NOTIFICATION cron: {}", notification);
        setupTasks(taskScheduler);
    }

    private void setupTasks(ThreadPoolTaskScheduler taskScheduler) {
        taskScheduler.schedule(cleanupJob, new CronTrigger(cleanUp));
        taskScheduler.schedule(notificationJob, new CronTrigger(notification));
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler);
    }
}
