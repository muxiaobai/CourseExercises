package org.sun.demo.stringTest;

/** 
 * 
 * @author zhang
 * @Date  2016年7月21日 下午1:03:11
 * @doing 
 */

public class equalsTest {
	public static void main(String[] args) {
		//Byte Short Integer Long Float Double Boolean String Object
		Integer wInteger=new Integer(1212);
		System.out.println(wInteger.hashCode());
	}
	public static void inte(){
		int int1=155;
		int int2=155;
		System.out.println("int1,int2");
		System.out.println(int1==int2);//true
	}
	public static void Integer(){
		Integer integer1=155;
		Integer integer2=155;
		System.out.println("int1,int2");
		System.out.println(integer1==integer2);
		System.out.println(integer1.equals(integer2));
	}
	public static void newInteger(){
		Integer i=new Integer(1);
		Integer j=new Integer(1);
		System.out.println("i,j");
		System.out.println(i==j);//false
		System.out.println(i.equals(j));//true
	}
}
