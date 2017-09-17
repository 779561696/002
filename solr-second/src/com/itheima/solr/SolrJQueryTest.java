package com.itheima.solr;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class SolrJQueryTest {
	
	@Test
	public void TestSolrJQuery() throws Exception{
		// 1）创建一个SolrServer对象，HttpSolrServer对象
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/collection1");
		// 2）创建SolrQuery对象
		SolrQuery query=new SolrQuery();
		// 3）需要向SolrQuery中设置查询条件，可以查询后台
		query.set("q", "小黄人");
		query.addFilterQuery("product_price:[0 TO 12]");
		query.setSort("product_price", ORDER.asc);
		query.setStart(0);
		query.setRows(9);
		query.set("df", "product_keywords");
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		// 4）执行查询
		QueryResponse queryResponse = solrServer.query(query);
		// 5）取查询结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		// 6）取查询结果总记录数
		long numFound = solrDocumentList.getNumFound();
		System.out.println("总计路数:"+numFound);
		// 7）取到商品列表
		//取高亮结果
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			// 8）取高亮显示的结果
			List<String> list = highlighting.get(solrDocument.get("id")).get("product_name");
			String name = "";
			if (list != null && list.size() > 0) {
				name = list.get(0);
			} else {
				name = solrDocument.get("product_name").toString();
			}
			System.out.println(name);
			System.out.println(solrDocument.get("product_price"));
			System.out.println(solrDocument.get("product_picture"));
			System.out.println(solrDocument.get("product_catalog_name"));
		}
		
	}
	
	
}







