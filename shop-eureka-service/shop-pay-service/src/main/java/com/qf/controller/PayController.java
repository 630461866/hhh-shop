package com.qf.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.google.gson.Gson;
import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.TOrder;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("pay")
public class PayController {
    private TOrder tOrder;

    private String oderId;

    private Double paycount;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("success")
    public String getSuccess(Model model){

        model.addAttribute("order",tOrder);
        return "success";
    }

    @RequestMapping("doPay/{oid}")
    @ResponseBody
    public String doPay(HttpServletRequest httpRequest,
                      HttpServletResponse httpResponse,@PathVariable("oid") String oid) throws ServletException, IOException {
        oderId =oid;
        System.out.println("订单号："+oid);
        //从dedis中获取订单信息
        String redisKey = StringUtil.getRedisKey(RedisConstant.ORDER_USER, "" + oid);
        Object o = redisTemplate.opsForValue().get(redisKey);
        if (o!=null){
            String orderjson = (String) o;
            Gson gson = new Gson();
            tOrder = gson.fromJson(orderjson, TOrder.class);
        }
         paycount = tOrder.getoPaycount();
        /**
         *参数说明：
         * 1. 支付网关
         * 2.应用id
         * 3.商户应用私钥
         * 4.返回的数据类型： json
         * 5.字符集： utf-8
         * 6. 支付宝公钥  注意，是支付宝公钥，不是商户公钥
         * 7. 密钥生成方式： RSA2
         */


        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016101800719345",
                "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDDnap92LEquCLdHwCg+KtY9daILhL5a8t1B+mdvv99+X26t2EPRT2yIWBjVwJsi1b6EPa41NXhZiasKX3wHNEe1D+Z+vFT2rfd5hFbI1LaRKct+kxxeQU5Tv/HtfyyrAXmVewF7yQrao7o/n3hwLIzTZ2fekfme39LW0/5BpV9YyNBeg+1YVqkshhWMeOFnZSfFnI98VwKCc0NbPx2/7Nrz17DH9PyXiqgmJlkajbtJDs7TdoJ64WjQpuVynBw3ziy3CQ9Xir6sEd3muMkQbic7zpwYhv06KMw+z7x9vMGNYuztJfWMyf1aLbuVrDLpFKFHjPF4do0nloVl6vHRZ3dAgMBAAECggEAFHbCBzhMEHQC8a70meJpynytXMG1KnKkbjHNtrrsb3lSNd5JQRe1RJVJSBsqHcDV8IMlzGA3n8TX9trAWgYpulfp72nZJTSUV5ph5m7jlAHqm1y78oL+kOg6yeG39dkZJOeIMXa7Ce+z05wL/znxanyMKMraCrqQrol27E5MBTnS6rCfrWf0KUoNiBP1a5L6iv1WZdV4qZUc+QD9nmulS5xg+2PULLMHf7OI3VN9DTLVIt0M4KhoUymlSVWXRPkSa7BiGAY1LPEYtVNE1237khfqUHXK3YdORsg4McpsI0b25fPMgbNGj5dIRw+PpNTefVEF0rXuT/rZj6JUwOq54QKBgQDsLtNJQonFkfDY8ziBV2nZGOIStDvZs7QJOQC5xZYvNHebDviN8HYjBwz/2RtX3bjL8C24fx+WO7wgRBi99CCXYgkkv/3NqedlbmpiqycNlAlM8z1VG/TkUOwff4zcVDZXDMReY0AvFlkO1XILjX3pgWqHWSFoesE3UjMba11GlwKBgQDUB3d9Vi3aVD2OxABY8A3dp13XzV+I8cApdrO0hqYEZK9qZWlOWg60wNzUW/LHIyOn1j7okqokvcfiQ9iB/95RFPnp94pKFIIsxiDjP1iwaKftxgbH6xe/i0eeU4yEtXc1XVp5xDVHBytxMf3JOTO6e4FfN3CmFFTdEc9XLtIhqwKBgDifgUgvC5iyRGu+4+0eZ+IEdS7GxxKKYr9TSzk+haMLkSE7lZtx6uC68ALmqgeGwKckDdINqCT8bT3PFZ90x9uDRW3qGY8VDMTkk9zT66j1tMUeC7Y73ZpfTDzA96RbDCwSg4GdieXAFdnYkl7yrR0uSbvpQnkp9OiubFJGizZBAoGAf+25ZMA4jkDBm4AbDY5Zb2FqN6jMeZ4TaXEbYFLVnFT7meByNzoTIXA0TVTlGM3U15FnB4BmdCCll5mD6nvtMVLMryQSJjxVhppMgZBY/KdImygeM0VfYMyye47Be9jZgjM/QbTmyGJFDdkx3NVMprmaB082Ap7FzVTPmzS+yF0CgYB/Z5DiERF5V4ToNDf2o6N5mxj19SfrASG5DA7ZXhZbLRKhcVmdtJ1tLjAbH8c+IGrdPSbaCH0hHRIpQfk0TfuGv3fHF6b01SIZdfhXSy5JK4nZxpcMu6/aAbzsbi0UCnwf4PanxjaY0L7kmT3jZQGyLzkuqyH1sv4qBPt7BUnLrg==",
                "JSON",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgrmpP67bor7ikoFL9MMHmjM0ouGByW0ITop+BlrtCIjoPlRrBsTAFvlJfWhfRrnpidnZNNq2oOZ+QAe2laeQMY4D5NqGNp8oNoyK1EA5m4WLT0hz1M3HI/U7qERPOv9YulGpCB5kiQOC351AtTGJ6AONYaJ2PTNg1KQWJ+mRo0KjKJVVx77tRjd+8sLgjxM0RZ1/G5PbRzGMMWfPN/IYc4PSaaecXpRK/+5p2ihoKvkdeHE6BCC/N0RJoivM0uSmqveYdlaTtBCT8MOp2gs/e/kqow6wjGd2GB305aMDlf66PyfMxdFr7IBpcuXR3GOvzj7bqoPAtZwSoGxVoQlSjwIDAQAB",
                "RSA2"); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://cme5vb.natappfree.cc/pay/success");
        alipayRequest.setNotifyUrl("http://cme5vb.natappfree.cc/pay/notifyUrl");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+oid+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+paycount+"," +
                "    \"subject\":\"Iphone16 16G\"," +
                "    \"body\":\"Iphone16 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }"+
                "  }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
        return "调用支付服务成功!!";
    }


    @RequestMapping("notifyUrl")
    @ResponseBody
    public void notifyUrl(HttpServletRequest request,HttpServletResponse response) throws AlipayApiException, IOException {

        Map<String, String[]> map = request.getParameterMap();
        //需要将map中的String[]==>String
        Map<String, String> paramsMap = new HashMap<>(); //将异步通知中收到的所有参数都存放到 map 中
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String[] values = entry.getValue();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < values.length-1; i++) {
                sb.append(values[i]+",");
            }
            sb.append(values[values.length-1]);
            paramsMap.put(entry.getKey(),sb.toString());
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap,
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgrmpP67bor7ikoFL9MMHmjM0ouGByW0ITop+BlrtCIjoPlRrBsTAFvlJfWhfRrnpidnZNNq2oOZ+QAe2laeQMY4D5NqGNp8oNoyK1EA5m4WLT0hz1M3HI/U7qERPOv9YulGpCB5kiQOC351AtTGJ6AONYaJ2PTNg1KQWJ+mRo0KjKJVVx77tRjd+8sLgjxM0RZ1/G5PbRzGMMWfPN/IYc4PSaaecXpRK/+5p2ihoKvkdeHE6BCC/N0RJoivM0uSmqveYdlaTtBCT8MOp2gs/e/kqow6wjGd2GB305aMDlf66PyfMxdFr7IBpcuXR3GOvzj7bqoPAtZwSoGxVoQlSjwIDAQAB",
                "utf-8",
                "RSA2"); //调用SDK验证签名
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            if(paramsMap.get("out_trade_no").equals(oderId)&&
                    paramsMap.get("total_amount").equals(""+paycount)){
                System.out.println("金额正确，验签成功");//要去数据库中改变订单状态
                //TODO response.getWriter().write("json");

                String url1 = "http://shop-pay-service/order/updateOrder/"+oderId;
                String result =restTemplate.getForObject(url1,String.class);
                System.out.println(result);
            }
        }else{
            // TODO 验签失败则记录异常日志，并在response中返回failure.
        }


    }
}
