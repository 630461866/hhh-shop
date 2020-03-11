package com.qf.shopemailservice.controller;

import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${activeServerUrl}")
    private String activeServerUrl;

    @Autowired
    private RedisTemplate redisTemplate;

    //http://localhost:8763/active/account/339934c6-31db-4e8c-a43b-d604f6111cb1
    @RequestMapping("active/account/{uuid}")
    public ResultBean activeAccount(@PathVariable String uuid){

        //1.组织redis中的键
        String redisKey = RedisUtil.getRedisKey(RedisConstant.REGIST_EMAIL_URL_KEY_PRE, uuid);

        //2.得到邮箱账号
        String addr = (String) redisTemplate.opsForValue().get(redisKey);


//        TODO 去数据库，做一次更新，把这个账号对应的状态，从0改成1

        return ResultBean.success("激活成功");
    }




    @RequestMapping("send/{addr}/{uuid}")
    public ResultBean sendEmail(@PathVariable String addr,
                                @PathVariable String uuid){
        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper mailMessage = null;
        try {
            mailMessage = new MimeMessageHelper(message,true);
            mailMessage.setSubject("请激活您在本中心的账号");

            //读取模板内容
            Context context = new Context();
            context.setVariable("username",addr.substring(0,addr.lastIndexOf('@')));
            context.setVariable("url",activeServerUrl+uuid);

            String info = templateEngine.process("emailTemplate", context);

            mailMessage.setText(info,true);

            mailMessage.setFrom("2505560247@qq.com");//系统账号
            mailMessage.setTo(addr);

            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResultBean.success("email send success");

    }


}
