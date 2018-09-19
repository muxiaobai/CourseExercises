/**
 * Project Name:12221
 * File Name:Me.java
 * Package Name:us.codecraft.tinyioc.aop
 * Date:2018年6月27日下午3:05:35
 * Copyright (c) 2018, All Rights Reserved.
 *
*/

package us.codecraft.tinyioc.aop;


/**
 * ClassName:Me s
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2018年6月27日 下午3:05:35 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */


// Referenced classes of package org.aopalliance.intercept:
//            Interceptor, MethodInvocation

public interface MethodInterceptor
    extends Interceptor
{

    public abstract Object invoke(MethodInvocation methodinvocation)
        throws Throwable;
}