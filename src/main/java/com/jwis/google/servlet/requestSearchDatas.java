package com.jwis.google.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwis.google.data.GoogleSearchHttpclient;
import com.jwis.google.model.SearchDatas;
import com.jwis.util.JiveGlobe;
import com.jwis.util.ResultPackaging;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class requestSearchDatas extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
		   JSONObject json = new JSONObject();
			List<SearchDatas> dsList = new ArrayList<SearchDatas>();
		   try {
			   String webSite = request.getParameter("webSite")==null?"":request.getParameter("webSite");
			   String queryString = request.getParameter("queryString");
			   String start= request.getParameter("start");
			   	String result = GoogleSearchHttpclient.getInstance().getGooglesearchdata(queryString, start,webSite);
			   	if(result.equals("200")){
			   		json = ResultPackaging.dealJsonObject(1, "error 200", null);
			   	}else if(result.equals("400")){
			   		json = ResultPackaging.dealJsonObject(0,"error 400", null);
			   		json = ResultPackaging.dealJsonObject(0, "unknown error", null);
			   	}else{
			   		JSONObject jsobject = JSONObject.fromObject(result);
			   		JSONArray ja = jsobject.getJSONArray("items");
			   		for(int i=0;i<ja.size();i++){
			   			SearchDatas sd = new SearchDatas();
			   			sd.setTitle(ja.getJSONObject(i).getString("title"));
			   			sd.setHtmlTitle(ja.getJSONObject(i).getString("htmlTitle"));
			   			sd.setUrl(ja.getJSONObject(i).getString("link"));
			   			sd.setSummary(ja.getJSONObject(i).getString("htmlSnippet"));
			   			dsList.add(sd);
			   		}
			   		json = ResultPackaging.dealJsonObject(1,"success", dsList);
			   	}
			    response.getWriter().write(json.toString());
		} catch (Exception e) {
				json = ResultPackaging.dealJsonObject(0,"error", null);
				 response.getWriter().write(json.toString());
		}
			
	}

}
