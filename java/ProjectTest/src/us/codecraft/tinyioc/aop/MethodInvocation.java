/**
 * Project Name:12221
 * File Name:m.java
 * Package Name:us.codecraft.tinyioc.aop
 * Date:2018年6月27日下午3:07:56
 * Copyright (c) 2018, All Rights Reserved.
 *
*/

package us.codecraft.tinyioc.aop;

import java.lang.reflect.Method;

/**
 * ClassName:m 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2018年6月27日 下午3:07:56 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */


public interface MethodInvocation
    extends Invocation
{

    public abstract Method getMethod();
}
