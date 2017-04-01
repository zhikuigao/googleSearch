package com.jwis.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	/**
	   * 获取文件夹下的所有文件
	   * @param dir    文件夹路径
	   * @param filename 后缀名称
	   * @param recursive 是否递归该文件夹下的子文件夹
	   * @return 
	   */
	  public static List<File> listFile(File dir,final String filename, boolean recursive)
	  {
	    if (!dir.exists()) {
	      throw new IllegalArgumentException("目录：" + dir + "不存在");
	    }
	    if (!dir.isDirectory()) {
	      throw new IllegalArgumentException(dir + "不是目录");
	    }
	    FileFilter ff = null;
	    if ((filename == null) || (filename.length() == 0)) {
	      ff = new FileFilter()
	      {
	        public boolean accept(File pathname)
	        {
	          return true;
	        }
	      };
	    } else {
	      ff = new FileFilter()
	      {
	        public boolean accept(File pathname)
	        {
	          if (pathname.isDirectory()) {
	            return true;
	          }
	          String name = pathname.getName();
	          if (name.indexOf(filename) != -1) {
	            return true;
	          }
	          return false;
	        }
	      };
	    }
	    return listFile(dir, ff, recursive);
	  }
	  
	  private static List<File> listFile(File dir, FileFilter ff, boolean recursive)
	  {
	    List<File> list = new ArrayList<File>();
	    File[] subs = dir.listFiles(ff);
	    if ((subs != null) && (subs.length > 0)) {
	      for (File sub : subs) {
	        if (sub.isFile()) {
	          list.add(sub);
	        } else if (recursive) {
	          list.addAll(listFile(sub, ff, true));
	        }
	      }
	    }
	    return list;
	  }
}
