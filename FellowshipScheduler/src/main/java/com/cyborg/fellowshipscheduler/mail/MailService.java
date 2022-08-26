package com.cyborg.fellowshipscheduler.mail;

import com.cyborg.fellowshipdataaccess.repository.UserRepository;
import com.cyborg.fellowshipjms.config.payload.ScholarshipNotification;
import com.cyborg.fellowshipnetwork.request.contact.ContactUsMailRequestModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author saranshk04
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder contentBuilder;
    private final UserRepository userRepository;

    @Value("${mail.from}")
    private String emailFrom;
    @Value("${mail.to}")
    private String emailTo;

    public void sendMail(List<ScholarshipNotification> notifications) {
        userRepository.findAll().forEach(user -> {
            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                mimeMessageHelper.setFrom(emailFrom);
                mimeMessageHelper.setTo(emailTo);
                mimeMessageHelper
                        .setText(contentBuilder.scholarshipNotificationListBuilder(notifications), true);
            };

            mailSender.send(messagePreparator);
            log.info("Email send");
        });
    }

    public void sendContactUsMail(ContactUsMailRequestModel contactUsMailRequestModel) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(emailFrom);
            mimeMessageHelper.setTo(contactUsMailRequestModel.getEmail());
            mimeMessageHelper.setText(contentBuilder.contactUsMailBuilder(contactUsMailRequestModel), true);
        };

        mailSender.send(messagePreparator);
        log.info("Contact us mail send");
    }
}
