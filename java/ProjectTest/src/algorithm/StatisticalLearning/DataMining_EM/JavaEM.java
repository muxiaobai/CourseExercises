/**
 * Project Name:ProjectTest
 * File Name:JavaEMMining.java
 * Package Name:algorithm.StatisticalLearning.DataMining_EM
 * Date:2017年10月9日下午3:48:53
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package algorithm.StatisticalLearning.DataMining_EM;
/**
 * ClassName:JavaEMMining <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年10月9日 下午3:48:53 <br/>
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class JavaEM {
    public static void main(String[] args) {
//        Point p1 = new Point(7.608, 5.3907);
//        Point p2 = new Point(14.208, 8.745);
        Point p1 = new Point(8.418, 5.095);
        Point p2 = new Point(10.463, 8.99);
        
        Point test = new Point(4, 10);
        double s1 = Math.pow(p1.getX() -test.getX(),2)+Math.pow(p1.getY() -test.getY(),2);
        double s2 = Math.pow(p2.getX() -test.getX(),2)+Math.pow(p2.getY() -test.getY(),2);;
        System.out.println(s1/(s1+s2));
        System.out.println(s2/(s1+s2));
        
    }
}

