package Number;

/** 
 * 
 * @author zhang
 * @Date  2016年8月2日 下午1:14:18
 * @doing 
 */

public class IntegerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 A ab = new B();
		 ab = new B();
	}
	
}
class A { 
	static {
		System.out.print("1"); 
		} 
	public A() {
		System.out.print("2"); }
	} 
class B extends A{ 
	static { 
		System.out.print("a"); 
		} 
	public B() {
		System.out.print("b"); 
		} 
	}
 