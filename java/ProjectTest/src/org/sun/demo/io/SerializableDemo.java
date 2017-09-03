package org.sun.demo.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import sun.print.resources.serviceui;

@SuppressWarnings("serial")
class Book implements Serializable{
	private String title;
	private Double price;
	private  transient String content;//瞬时 不会被序列化
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPrice() {
		return price;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + ", content=" + content + "]";
	}
	
}
public class SerializableDemo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ser();
		//dser();
	      
	}
	/*
	 *  序列化
	 */
	public static void ser() throws FileNotFoundException, IOException{
		 Book book = new Book();
		 book.setPrice(12.8);
		 book.setTitle("admin");
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(new File("E:" + File.separator + "person.ser")));
		oos.writeObject(book); 
		oos.close();
	}
	/*
	 * 反序列化
	 */
	public  static void dser() throws FileNotFoundException, IOException, ClassNotFoundException{
		  ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
	                new File("E:" + File.separator + "person.ser")));
	        Book per = (Book) ois.readObject();  
	        ois.close();
	        System.out.println(per);
	}
}
