package com.github.emails.services;

import com.github.emails.models.Mail;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;

@Slf4j
@Service
public class EmailService {

    private final JavaMailSender emailSender;

    private final Configuration fConfiguration;

    public EmailService(JavaMailSender emailSender,
                        @Qualifier("freeMarkerConfiguration") Configuration fConfiguration
    ) {
        this.emailSender = emailSender;
        this.fConfiguration = fConfiguration;
    }

    public void sendEmail(Mail mail, String template) {
        if (Objects.nonNull(mail) && StringUtils.isNotEmpty(template)) {
            MimeMessage mm = this.emailSender.createMimeMessage();
            try {
                MimeMessageHelper mmh = new MimeMessageHelper(
                        mm,
                        MULTIPART_MODE_MIXED_RELATED,
                        StandardCharsets.UTF_8.name()
                );
                Template t = this.fConfiguration.getTemplate(template);
                var html  = FreeMarkerTemplateUtils
                        .processTemplateIntoString(t, mail.getModel());
                mmh.setTo(mail.getTo());
                mmh.setText(html, Boolean.TRUE);
                mmh.setSubject(mail.getSubject());
                mmh.setFrom(mail.getFrom());
                this.emailSender.send(mm);
                log.info("Send email {}", mm);
            } catch (MessagingException | IOException | TemplateException e) {
                log.error(e.getMessage());
            }
        }
    }

}
