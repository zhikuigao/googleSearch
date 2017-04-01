package googleSearch;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Demo {
	 public static void main(String[] args) throws UnsupportedEncodingException {
		String s= "4|20026";
		//System.out.println(URLEncoder.encode(s,"gbk"));
		System.out.println(java.net.URLDecoder.decode("4%2C12%2C10006%2C1|1665873633|1033|4", "UTF-8"));
		//String queryStirng = URLEncoder.encode(s,"UTF-8");
		//System.out.println(queryStirng);
		
	}

}
