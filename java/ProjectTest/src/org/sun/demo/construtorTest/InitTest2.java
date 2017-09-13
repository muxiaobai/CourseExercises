package org.sun.demo.construtorTest;

/**
 * 
 * @author zhang
 * @Date 2016年7月22日 下午4:06:24
 * @doing
 */

public class InitTest2 {
	int cla;
	int j=f();
	int a;
	int f(){
	System.out.println("f()");
	return 1;
	}
	public InitTest2() {
		System.out.println("construtor前:"+a);
		a=11;
		System.out.println("construtor后:"+a);
		System.out.println("con");
	}
	public static void main(String[] args) {
	int func = 0 ;
	InitTest2 initTest=new InitTest2();
	System.out.println(initTest.cla);
	System.out.println(initTest.j);
	System.out.println(func);
		
	}
}