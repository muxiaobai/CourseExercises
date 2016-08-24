package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileInputStreamDemo {
	public static void main(String[] args) throws IOException {
		  File file = new File("E:" + File.separator + "hellodemo"
	                + File.separator + "test.txt"); // 定义文件路径
		  if (file.exists()) {    // 文件存在则可以读取
	            InputStream input = new FileInputStream(file) ;
	            byte data[] = new byte[1024]; // 假设要读的长度是1024
	            int foot = 0; // 操作data数组的脚标
	            int temp = 0;
	            // 第一步：temp = input.read()，读取一个单个字节，并且将内容给temp变量
	            // 第二步：temp != -1，将接收到的temp的数值判断是否为-1，如果为-1则表示退出循环，如果不是，则保存数据
	            while ((temp = input.read()) != -1) {
	                data[foot++] = (byte) temp; // 保存读取进来的单个字节
	            }
	            input.close();
	            System.out.println("读取的数据是：【" + new String(data, 0, foot) + "】");
	        }else{
	        	  System.out.println("文件不存在");
	        }


	}
}
