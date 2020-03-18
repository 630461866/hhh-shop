package com.qf.phoneregisterservice.handler;




import com.qf.util.StringUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class SMSHandler {

    @Value("${sms.Url}")
    private String Url;

    @Value("${sms.account}")
    private String account;

    @Value("${sms.password}")
    private String password;

    @Value("${sms.mobile}")
    private String phone;

    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queues ="sms_send_queue")
    public void proccess(String phone){
       /* //1.发送短信
        String code = String.valueOf((int) (((Math.random() * 9 + 1) * 1000)));

        System.out.println(code);
        System.out.println(code);
        System.out.println(code);
        String content = "@1@="+code;
        SmsUtil.account = account;
        SmsUtil.password = password;
        SmsUtil.veryCode = veryCode;
        SmsUtil.http_url  =http_url;
        SmsUtil.sendTplSms(phone,tplId,content);

        //2.组织键值对，存入到redis中
        String redisKey = StringUtil.getRedisKey("register:phone:", phone);
        //设置键值对和有效期
        redisTemplate.opsForValue().set(redisKey,code,1, TimeUnit.MINUTES);*/



        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);

        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");

        String mobile_code = String.valueOf((int) (((Math.random() * 9 + 1) * 1000)));

        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

        NameValuePair[] data = {//提交短信
                new NameValuePair("account", account), //查看用户名是登录用户中心->验证码短信->产品总览->APIID
                new NameValuePair("password", password),  //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                new NameValuePair("mobile", phone),
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);

        try {
            client.executeMethod(method);

            String SubmitResult = method.getResponseBodyAsString();

            //System.out.println(SubmitResult);

            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();

            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");

            System.out.println(code);
            System.out.println(msg);
            System.out.println(smsid);

            if ("2".equals(code)) {
                System.out.println("短信提交成功");
            }

        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // Release connection
            method.releaseConnection();
            //client.getConnectionManager().shutdown();
        }
        //2.组织键值对，存入到redis中
        String redisKey = StringUtil.getRedisKey("register:phone:", phone);
        //设置键值对和有效期
        redisTemplate.opsForValue().set(redisKey,mobile_code,1, TimeUnit.MINUTES);
    }
}
