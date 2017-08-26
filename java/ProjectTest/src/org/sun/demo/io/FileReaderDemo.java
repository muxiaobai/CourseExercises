package org.sun.demo.io;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class FileReaderDemo {
	 public static void main(String[] args) throws Exception {
	        File file = new File("E:" + File.separator + "hellodemo"
	                + File.separator + "test.txt"); // 定义文件路径
	        if (file.exists()) {
	            Reader in = new FileReader(file); // 字符输入流
	            char data[] = new char[1024]; // 开辟数组
	            int len = in.read(data); // 读取数据
	            System.out.println("读取数据内容：【" + new String(data, 0, len) + "】");
	            in.close() ;
	        }else{
	        	  System.out.println("文件不存在");
	        }

	 }
}
