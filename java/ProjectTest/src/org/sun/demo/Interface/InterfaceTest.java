package org.sun.demo.Interface;

interface Action{
	public void myAction();
}
interface Read extends Action{
	public static final int Read_Id=2;
	public void myRead();
} 
 interface Listen extends Action{
	public static final int Spead_Id=3;
	public static final int Read_Id=3;
	public	void myListen();		
} 
class student implements Listen,Read{
	@Override
	public void myListen() {
		System.out.println("I can listen");
	}
	@Override
	public void myRead() {
		System.out.println("I can read");
	}
	@Override
	public void myAction() {
		
	}
	
}

public class InterfaceTest {

	public InterfaceTest() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		Listen listen=new student();
		listen.myListen();
		//listen.myRead();//必须强制转换才能使用
	}

}
