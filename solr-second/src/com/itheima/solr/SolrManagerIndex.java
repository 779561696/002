package com.itheima.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrManagerIndex {
	
	@Test
	public void addDocument() throws Exception{
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
		SolrInputDocument document  = new SolrInputDocument();
		document.addField("id", "2");
		document.addField("title", "bbbbbbbbbbbba");
		solrServer.add(document);
		solrServer.commit();
	}
	
	@Test
	public void deleteDocument() throws Exception{
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
		
	}
	
	
}





