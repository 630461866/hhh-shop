package com.qf.shopemailservice.handler;

import com.qf.constant.RabbitConstant;
import com.qf.dto.EmailMessageDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailHandler {


    @Autowired
    private JavaMailSender sender;

    @Autowired
    private TemplateEngine engine;


    @RabbitListener(queues = RabbitConstant.EMAIL_SEND_QUEUE)
    public void addProductToSearch(EmailMessageDTO message){
        //1.发送邮件

        try {
            MimeMessage mimeMessage = sender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

            helper.setSubject("注册邮件");

            //===通过模板引擎，将模板+数据得到输出的字符串====
            Context context = new Context();
            context.setVariable("username",message.getEmail().substring(0,message.getEmail().lastIndexOf('@')));
            context.setVariable("url",message.getUrl());
            String info = engine.process("emailtemplate", context);


            helper.setText(info,true);
            helper.setFrom("2505560247@qq.com");
            helper.setTo(message.getEmail());

            sender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }



}
