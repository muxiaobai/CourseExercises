package StringTest;

/** 
 * 
 * @author zhang
 * @Date  2016年7月22日 下午3:27:3i
 * @doing 
 */

public class NumberTest {
	public static void main(String[] args) {
//		java中有三种移位运算符
//		<<      :     左移运算符，num << 1,相当于num乘以2
//		>>      :     右移运算符，num >> 1,相当于num除以2
//		>>>    :     无符号右移，忽略符号位，空位都以0补齐
		leftright(2);
		move(2);
	}
	public static void move(int i){
		System.out.println("=========//无符号的移位只有右移，没有左移使用“>>>”进行移位，都补充0=============");
		System.out.println("i:"+Integer.toBinaryString(i));
		System.out.println("i>>>2:"+ (i>>2)+":"+Integer.toBinaryString(i>>>2));
		System.out.println("-i:"+Integer.toBinaryString(-i));
		System.out.println("-i>>>2:"+ (i>>2)+":"+Integer.toBinaryString(-i>>>2));
	}
	public static void leftright(int i){
//		int a = 10;
//		System.out.println(Integer.toBinaryString(a));
//		转换成八进制把Binary换成Octal
//		转换成十六进制把Binary换成Hex
//		解释:Binary是二进制的意思，Octal是八进制的意思，Hex是十六进制的意思。
		
//		左移位：<<，有符号的移位操作
//		左移操作时将运算数的二进制码整体左移指定位数，左移之后的空位用0补充
//		右移位：>>，有符号的移位操作
//		右移操作是将运算数的二进制码整体右移指定位数，右移之后的空位用符号位补充，如果是正数用0补充，负数用1补充。
		System.out.println("=========左移位：<<，有符号的移位操作,右移位：>>，有符号的移位操作=============");
		System.out.println("i:"+Integer.toBinaryString(i));
		System.out.println("i>>2:"+ (i>>2)+":"+Integer.toBinaryString(i>>2));
		System.out.println("i<<2:"+ (i<<2)+":"+Integer.toBinaryString(i<<2));
		System.out.println("-i:"+Integer.toBinaryString(-i));
		System.out.println("-i<<2:"+ (-i<<2)+":"+Integer.toBinaryString(-i<<2));
		System.out.println("-i>>2:"+ (i>>2)+":"+Integer.toBinaryString(-i>>2));
	}
}
