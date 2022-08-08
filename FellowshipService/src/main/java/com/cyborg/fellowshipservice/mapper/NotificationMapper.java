package com.cyborg.fellowshipservice.mapper;

import com.cyborg.fellowshipdataaccess.entity.Scholarship;
import com.cyborg.fellowshipjms.config.payload.ScholarshipNotification;
import org.springframework.stereotype.Component;

/**
 * @author saranshk04
 */
@Component
public class NotificationMapper {

    public ScholarshipNotification scholarshipToNotificationPayload(Scholarship scholarship) {
        String description = scholarship.getDescription();
        return ScholarshipNotification.builder()
                .title(scholarship.getTitle())
                .description(description.length() > 100 ?
                        String.format("%s...", description.substring(0, 100)) : description)
                .url(scholarship.getUrl())
                .build();
    }
}
