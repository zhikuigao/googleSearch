package com.jws.goldfire.util;

import java.util.HashMap;

public class Consts {
		
	public static final String SERVER_URL = "http://47.89.29.77/goldfire";
	
	public static final HashMap<String, String> headers = new HashMap<>();
	static {
		headers.put("api-key", "5F7CA21FB9AB6057CBB372E099636ACB");
//		headers.put("sid", "S-1-5-21-2688229506-1294040951-2515647071");
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
	}
	
	public static  String GOLDFIRE_NAME = "gfuser3";
	public static  String GOLDFIRE_PWD = "coyote";
	public static final String LOGIN_URL = SERVER_URL + "/ghc/account/login";
	public static final String FILE_NAME = "Goldfire_cookie.txt";
}
