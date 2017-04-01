package com.jwis.util;

import java.util.Properties;



public class FileUtilsPath {
	private FileUtilsPath() {
	}
	//读取常量配置表
	public static Properties properties=FileOperation.readConfigProperties("dataConfig.properties");
	public static final String GOOGLE_CX = properties.getProperty("google.cx");
	public static final String GOOGLE_KEY = properties.getProperty("google.key");
	public static final String path = properties.getProperty("file.path");
}
