package com.cyborg.fellowshipscheduler.mail;

import com.cyborg.fellowshipjms.config.payload.ScholarshipNotification;
import com.cyborg.fellowshipnetwork.request.contact.ContactUsMailRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

/**
 * @author saranshk04
 */
@RequiredArgsConstructor
@Service
public class MailContentBuilder {

    private final TemplateEngine templateEngine;

    public String scholarshipNotificationListBuilder(List<ScholarshipNotification> list) {
        Context context = new Context();
        context.setVariable("list", list);
        return templateEngine.process("ScholarshipNotification", context);
    }

    public String contactUsMailBuilder(ContactUsMailRequestModel contactUsMailRequestModel) {
        Context context = new Context();
        context.setVariable("mailRequest", contactUsMailRequestModel);
        return templateEngine.process("ContactUsMail", context);
    }
}
