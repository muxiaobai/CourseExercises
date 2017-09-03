package org.sun.demo.stringTest;
public class StringTest {
	public static void main(String[] args) {
		//byte short int long float double char boolean (reference type)
		String a="one";//""字符串常量就是String的匿名对象."hello".equal();可以使用
		String b="one";//""直接赋值，则表明在方法区（字符串常量新建“one”）
		String str=new String();//new和直接""是一样的,但是new在堆里面开辟了一块空间。在常量池中开辟一块空间""
		String c=null;
		String stra=new String("one");//在堆里面开辟了一块空间。在常量池中开辟了一块空间"one"
		String strb=new String("one").intern();//手工如池  和String strb="one"; 作用一样但是开辟的空间依然存在。
		System.out.println(a==b);//true //指同一个字符串常量     //   共享设计模式
		System.out.println(a==stra); //false  //内存地址的比较
		System.out.println(b==strb);//true
		System.out.println("".equals(str)); //true //equal 内容的比较""==""
		System.out.println("".equals(c));//false //equal 内容的比较""<>null
		//String+="str";如果是循环，尽量避免使用String，而是采用StringBuffer(多线程安全),Stringbuilder(多线程不安全)
		//常用方法Deprecated.不建议使用。字节byte 数据传输或者编码转换 字符
	}
	
}