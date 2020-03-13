package com.qf.handler;

import com.qf.constant.RabbitConstant;
import com.qf.dto.TProductAddDTO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


@Component
public class MessageHandler {

    @Autowired
    private Configuration configuration;

    @Value("${imageServer}")
    private String imageServer;

    @Value("${resourcesPath}")
    private String resourcesPath;

    //可以根据实际的硬件的情况来得到初始化线程数的大小
    private int corePoolSize = Runtime.getRuntime().availableProcessors();

    private ExecutorService pool = new ThreadPoolExecutor(corePoolSize,corePoolSize*2,1L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(3),new ThreadPoolExecutor.DiscardOldestPolicy());



    @RabbitListener(queues = RabbitConstant.PRODUCT_ADD_TO_RESOURCES_QUEUE)
    public void process(TProductAddDTO product){

        try {
            //生成静态页面
            Template template = configuration.getTemplate("introduction.ftl");
            //数据
            Map<String,Object> data = new HashMap<>();

            product.setPimage(imageServer+product.getPimage());

            data.put("pro",product);


            String fileName = product.getPid().toString()+".html";
            Writer out = new FileWriter(resourcesPath+fileName);
            template.process(data,out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }


    }


    public void process2(List<TProductAddDTO> products){

        List<Future> list = new ArrayList<>();
        for (TProductAddDTO product : products) {
            //多线程的方式创建静态页面
            list.add(pool.submit(new MyCreateHtmlCallBack(product)));
        }

    }

    class MyCreateHtmlCallBack implements Callable<Boolean>{

        private TProductAddDTO product;

        public MyCreateHtmlCallBack(TProductAddDTO product) {
            this.product = product;
        }

        public MyCreateHtmlCallBack() {

        }

        @Override
        public Boolean call() throws Exception {

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
                return false;
            }
            return true;
        }

    }


}
