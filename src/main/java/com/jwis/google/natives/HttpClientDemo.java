package com.jwis.google.natives;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Query;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
import com.jwis.util.FileUtilsPath;


public class HttpClientDemo {
		public static void main(String[] args) {
		Customsearch customsearch = new Customsearch(new NetHttpTransport(), new JacksonFactory(),null);
		try {
			com.google.api.services.customsearch.Customsearch.Cse.List list = customsearch.cse().list("csdn");
			Long start = new Long(8);
			list.setCx(FileUtilsPath.GOOGLE_CX);
			list.setKey(FileUtilsPath.GOOGLE_KEY);
			list.setAlt("json");
			list.setStart(start);
			list.setSiteSearch("www.baidu.com 20www.csdn.net");
			Search results = list.execute();
			List<Result> items = results.getItems();
			 for(Result result:items)
		        {
		            System.out.println("Title:"+result.getHtmlTitle());
		            System.out.println("Html:"+result.getLink());
		            result.getLink();
		        }
			} catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
		}

}
