package org.sun.demo.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileOutputStreamDemo {
	public static void main(String[] args) throws IOException {
		   File file = new File("E:" + File.separator + "hellodemo"
	                + File.separator + "test.txt"); // 定义文件路径
	        if (!file.getParentFile().exists()) { // 父路径不存在
	            file.getParentFile().mkdirs(); // 创建父路径
	        }
	        OutputStream output = new FileOutputStream(file); // 通过子类实例化父类
	        String outputdata = "Hello World";// 要输出的数据
	        output.write(outputdata.getBytes()); // 输出数据，要将数据变为字节数组输出
	        output.close(); // 关闭资源
	}
}
