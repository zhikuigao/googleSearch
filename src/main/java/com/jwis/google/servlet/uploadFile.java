package com.jwis.google.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.jwis.util.FileUtilsPath;
public class uploadFile extends HttpServlet {
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		String path =  FileUtilsPath.path;
		File repositoryFile = new File(path);
		if (!repositoryFile.exists()) {repositoryFile.mkdirs();}
		FileItemFactory factory = new DiskFileItemFactory(1024 * 32,repositoryFile);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		upload.setSizeMax(1024 * 1024 * 500);
		List<?> fileItr = null;
		try {
			 fileItr = upload.parseRequest(request);
				// 讲非文件值放在map中
				Iterator<?> iter = fileItr.iterator();
				//处理文件
				//boolean isTrue = true;
				String filename = "";
				while (iter.hasNext()) {
					FileItem f = (FileItem) iter.next();
					if (!f.isFormField()) {
				//详情 name=abc.png,  size=376507 bytes, 	isFormField=false, FieldName=picture1
						filename = f.getName() == null ? "" : f.getName();
						File saveFile = new File(path,filename);
						if (saveFile.exists()) {
							break;
							}
							f.write(saveFile);
						} 
			}
		} catch (Exception e) {	
			System.out.println(e);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
