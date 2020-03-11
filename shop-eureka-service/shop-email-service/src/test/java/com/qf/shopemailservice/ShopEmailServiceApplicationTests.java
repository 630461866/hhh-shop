package com.qf.shopemailservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class ShopEmailServiceApplicationTests {


    @Autowired
    private JavaMailSender sender;

    @Test
    public void sendMail(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("测试邮件主题");
        mailMessage.setText("猜猜我是哪个：你是不是肖程鹏");
        mailMessage.setFrom("2505560247@qq.com");
        mailMessage.setTo("2505560247@qq.com");
        sender.send(mailMessage);
    }

}
