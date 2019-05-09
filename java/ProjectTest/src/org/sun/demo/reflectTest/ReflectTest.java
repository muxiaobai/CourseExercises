package org.sun.demo.reflectTest;

import java.lang.Class;
import java.lang.String;
import java.lang.System;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
class invokeTest{
	private String username;
	private String password;
	//public String mypublic;
	public invokeTest(){

	}
	public invokeTest(String username,String password){
		this.username=username;
		this.password=password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
public class ReflectTest {

	public static void main(String[] args) throws Exception {
		//
		Class<?> invokeClass=invokeTest.class;
		//testDemo的一个实例invokeTest
		//通过构造函数创建实例对象
		Constructor constructor= invokeClass.getConstructors ()[1];
		Object  constructorObject =constructor.newInstance (new Object[]{"username","password"});
		//通过类创建实例对象
		Object invokeInstance= invokeClass.newInstance();
		//getDeclaredFields(private)和getFields(public不能查到private Field)
		Field[] fields=invokeClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldString =field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
			String getMethodname="get"+fieldString;
			String setMethodname="set"+fieldString;
			//调用getMethod方法获取方法
			//invoke执行获取的方法	
			Method setMethod=invokeClass.getMethod(setMethodname,new Class[]{String.class});
			setMethod.invoke(invokeInstance,new Object[]{"zhang"});
			Method getMethod=invokeClass.getMethod(getMethodname);
			System.out.println(getMethod.invoke(invokeInstance));
		}
		
		//getDeclaredMethids getMethods get
		Method[] methods=invokeClass.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			System.out.println(method);
		}
		//获取类的类类型
		Class<?> classType1=Class.forName("java.lang.String");//静态方法和类本身无关
		Class<?> classType2=String.class;//类的class属性
		Class<?> classType3="".getClass();//对象的getClass()方法

	
	}
}