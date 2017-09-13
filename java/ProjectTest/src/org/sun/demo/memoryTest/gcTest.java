package org.sun.demo.memoryTest;

public class gcTest {
private static final int _1MB=1024*1024;
	public gcTest() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		byte[] allocation1,allocation2,allocation3,allocation4;
		allocation1=new byte[500*_1MB];
		System.gc(); 
		allocation2=new byte[500*_1MB];
		allocation3=new byte[10*_1MB];
		allocation4=new byte[10*_1MB];
	}

}
