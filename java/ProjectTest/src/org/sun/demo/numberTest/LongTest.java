package org.sun.demo.numberTest;

/** 
 * 
 * ClassName: LongTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2019年2月22日 下午5:39:43 <br/>
 *
 * @author  Mu Xiaobai
 * @version 
 * @since JDK 1.8
 */

public class LongTest {

	public static void main(String[] args) {
	    Long  Long1= 127L;
        Long  Long2= 127L;
        Long  Long3= 128L;
        Long  Long4= 128L;
        
        System.out.println(Long1==Long2);
        System.out.println(Long3==Long4);
        System.out.println(Long3.equals(Long4));
	    System.out.println("========================");
	    Long  Long5= -128L;
        Long  Long6= -128L;
        Long  Long7= -129L;
        Long  Long8= -129L;
//        Long 缓存从 -128到127可以直接==,其它使用equals() ,才能相等
        System.out.println(Long5==Long6);
        System.out.println(Long7==Long8);
        System.out.println("========================");
        long long1 = 128L;
        long long2 = 128L;
        long long3 = 129L;
        long long4 = 129L;
        //但是long定义的可以等，包装类型和其它类型
        System.out.println(long1==long2);
        System.out.println(long3==long4);
                
	}
	
}