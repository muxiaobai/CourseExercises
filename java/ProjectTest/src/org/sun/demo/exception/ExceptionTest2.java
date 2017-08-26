package org.sun.demo.exception;


/**
 * 
 * @author zhang
 * @Date 2016年7月20日 下午1:59:25
 * @doing
 */

public class ExceptionTest2 {
	public static void main(String[] args) {
		ExceptionTest2 exceptionTest=new ExceptionTest2();
		System.out.println(exceptionTest.value());
		System.out.println("=======================");
	}
	public Integer value(){
		Integer ret = 0;
		try {
			System.out.println("try:"+ret);
			ret=1/0;
			return ret;
		} 
		catch(ArithmeticException e){
			System.out.println("ArithmeticException catch:"+ret);
			//e.printStackTrace();
		}catch(NullPointerException e){
			System.out.println("NullPointer catch:"+ret);
			e.printStackTrace();
		} 
		catch (Exception e) {
			System.out.println("Exception catch:"+ret);
			e.printStackTrace();
			ret =3;
		} finally {
			ret = 2;
			System.out.println("finally:"+ret);
			//return ret;
		}
		return ret;
	}
}
