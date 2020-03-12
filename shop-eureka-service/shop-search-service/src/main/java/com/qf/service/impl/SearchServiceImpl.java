package com.qf.service.impl;

import com.qf.dto.ResultBean;
import com.qf.entity.TProductSearchDTO;
import com.qf.mapper.TProductSearchDTOMapper;
import com.qf.service.ISearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements ISearchService{

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private TProductSearchDTOMapper mapper;

    @Override
    public ResultBean searchByKeyword(String keyword) {

        //创建查询对象
        SolrQuery query = new SolrQuery();
        query.set("df","t_item_keywords");
        query.setQuery(keyword);

        //分页
        query.setStart(0);
        query.setRows(12);

        //高亮
        query.setHighlight(true);
        query.addHighlightField("t_product_name");
        query.setHighlightSimplePre("<span style='color:red'>");
        query.setHighlightSimplePost("</span>");

        try {

            QueryResponse response = solrClient.query(query);
            List<TProductSearchDTO> products = new ArrayList<>();

            //获得数据结果集
            SolrDocumentList results = response.getResults();

            //获得高亮结果集
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

            for (SolrDocument document : results) {
                TProductSearchDTO product = new TProductSearchDTO();
                String stringId = (String) document.getFieldValue("id");
                Long id = Long.parseLong(stringId);
                product.setId(id);

                //==========从高亮结果集中那带高亮效果的t_product_name=============
                Map<String, List<String>> stringListMap = highlighting.get(stringId);
                List<String> list = stringListMap.get("t_product_name");
                String t_product_name = list.get(0);
                product.settProductName(t_product_name);

                Double t_product_sale_price = (Double) document.getFieldValue("t_product_sale_price");
                product.settProductSalePrice(new BigDecimal(t_product_sale_price));
                String t_product_pimage = (String) document.getFieldValue("t_product_pimage");
                product.settProductPimage(t_product_pimage);
                String t_product_pdesc = (String) document.getFieldValue("t_product_pdesc");
                product.settProductPdesc(t_product_pdesc);

                products.add(product);

            }

            return ResultBean.success(products);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResultBean.error("查询出现异常");
    }

    @Override
    public ResultBean addProduct(Long pid) {

        //根据pid从数据库中获取该商品
        TProductSearchDTO product = mapper.selectById(pid);

        SolrInputDocument document = new SolrInputDocument();
        document.setField("id",product.getId().toString());
        document.setField("t_product_name",product.gettProductName());
        document.setField("t_product_sale_price",product.gettProductSalePrice().floatValue());
        document.setField("t_product_pimage",product.gettProductPimage());
        document.setField("t_product_pdesc",product.gettProductPdesc());

        try {
            solrClient.add(document);
            solrClient.commit();
            return ResultBean.success("插入搜索库成功");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return ResultBean.error("插入搜索库失败");
    }


}
