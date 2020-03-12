package com.qf.handler;

import com.qf.constant.RabbitConstant;
import com.qf.dto.TProductAddDTO;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SearchHandler {

    @Autowired
    private SolrClient solrClient;

    @RabbitListener(queues = RabbitConstant.PRODUCT_ADD_TO_SEARCH_QUEUE)
    public void addProductToSearch(TProductAddDTO product){

        SolrInputDocument document = new SolrInputDocument();
        document.setField("id",product.getPid().toString());
        document.setField("t_product_name",product.getPname());
        document.setField("t_product_sale_price",product.getSalePrice().doubleValue());
        document.setField("t_product_pimage",product.getPimage());
        document.setField("t_product_pdesc",product.getPdesc());

        try {
            solrClient.add(document);
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
