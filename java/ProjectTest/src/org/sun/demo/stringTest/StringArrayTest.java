package org.sun.demo.stringTest;

/** 
 * 
 * @author zhang
 * @Date  2016年8月2日 下午1:16:41
 * @doing 
 */


public class StringArrayTest {      
	String str = new String("good");       
	char[] ch = { 'a', 'b', 'c' };     
	public static void main(String args[]) {          
		StringArrayTest ex = new StringArrayTest();       
		ex.change(ex.str, ex.ch);         
		System.out.print(ex.str + " and ");     
		System.out.print(ex.ch);      
		}     
	public void change(String str, char ch[]) {   
		str = "test ok";        
		ch[0] = 'g';    
		}  
	}  
//复制代码  A、 good and abc    B、 good and gbc    C、 test ok and abc    D、 test ok and gbc 