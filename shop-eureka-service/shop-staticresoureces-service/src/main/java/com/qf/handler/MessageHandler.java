package com.qf.handler;

import com.qf.constant.RabbitConstant;
import com.qf.dto.TProductAddDTO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;


@Component
public class MessageHandler {

    @Autowired
    private Configuration configuration;

    @Value("${imageServer}")
    private String imageServer;

    @Value("${resourcesPath}")
    private String resourcesPath;

    @RabbitListener(queues = RabbitConstant.PRODUCT_ADD_TO_RESOURCES_QUEUE)
    public void proccess(TProductAddDTO product){
        try {
            Template template = configuration.getTemplate("introduction.ftl");
            Map<String,Object> data = new HashMap<>();
            product.setPimage(imageServer+product.getPimage());
            data.put("pro",product);
            String fileName = product.getPid().toString()+".html";
            //这个模块应该打成一个jar包直接放到阿里云服务器去运行
            FileWriter fileWriter = new FileWriter(resourcesPath + fileName);
            template.process(data,fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
