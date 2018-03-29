package com.healthmanager.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class EmailUtil {

    private static JavaMailSenderImpl sender;

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    static {
        sender = new JavaMailSenderImpl();
        sender.setHost("smtp.exmail.qq.com");
        sender.setPort(465);
        sender.setUsername("system@healthmanager.club");
        sender.setPassword("Aazhy19961996");
        sender.setDefaultEncoding("Utf-8");
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.timeout", "5000");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.ssl.enable","true");
        sender.setJavaMailProperties(properties);
    }

    public static void sendMail(String to, String subject, String text) {
        try {
            MimeMessage mimeMessage = sender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom("system@healthmanager.club");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
            sender.send(mimeMessage);
            log.info("send finished");
        }
        catch (Exception e){
            log.error("send mail fail",e);
        }
    }

    public static void sendMailAsync(String to, String subject, String text) {
        executorService.execute(()->{
            sendMail(to,subject,text);
        });
    }
}
