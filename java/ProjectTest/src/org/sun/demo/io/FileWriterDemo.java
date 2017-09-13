package org.sun.demo.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileWriterDemo {
	public static void main(String[] args) throws IOException {
		File file = new File("E:" + File.separator + "hellodemo"
                + File.separator + "test.txt"); // 定义文件路径
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();// 创建父目录
        }
        Writer out = new FileWriter(file);
        String data = "Hello World";
        out.write(data) ;   // 直接输出字符串
        out.close() ; 

	}
}
