package Construtor;

/**
 * 
 * @author zhang
 * @Date 2016年7月22日 下午4:06:24
 * @doing
 */

public class StaticConstrutorTest {
	static{
		System.out.println("StaticConstrutorTest  static ");
	}
	{
		System.out.println("StaticConstrutorTest {}");
	}
	public static void main(String[] args) {
		
		 new HelloB();
		 System.out.println("========================");
		 new HelloB();
	}
}

class HelloA {   
	static { 
		System.out.println("static A");
		}  

	public HelloA() {      
		System.out.println("HelloA");     
	}           
	{ 
		System.out.println("I'm A class");
	}          
}
class HelloB extends HelloA {   
	static {
		System.out.println("static B"); 
	}  
	public HelloB() {        
		System.out.println("HelloB");     
	}          
	{ 
		System.out.println("I'm B class");
	}            
	 
}  
//静态块在第一个对象创建时运行static{}
//初始化块在每个对象创建时运行{}
//构造块public className{}
//区别是静态块只执行一次，操作的内存在静态区
//初始化块每个对象构造时都需要执行一次，操作的内存在用户区
//复制代码  答案：    static A  static B  I'm A class  HelloA  I'm B class  HelloB  