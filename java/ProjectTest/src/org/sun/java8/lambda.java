/**
 * Project Name:ProjectTest
 * File Name:lambda.java
 * Package Name:org.sun.java8
 * Date:2017年9月18日上午9:49:22
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package org.sun.java8;

import java.util.Arrays;

/**
 * ClassName:lambda <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年9月18日 上午9:49:22 <br/>
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class lambda {
    public static void main(String[] args) {
        Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
    }
}

