package com.yeqifu.sys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


/**
 * 邮件配置类
 */
@Configuration
public class MailConfig {

    @Bean
    public MailSender mailSender(){
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");

        mailSender.setPort(587);
        mailSender.setUsername("1844246071@qq.com");
        mailSender.setPassword("iyycpzraacyobadj");
        return  mailSender;
    }
}