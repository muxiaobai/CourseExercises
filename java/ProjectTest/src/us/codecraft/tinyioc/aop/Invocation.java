/**
 * Project Name:12221
 * File Name:i.java
 * Package Name:us.codecraft.tinyioc.aop
 * Date:2018年6月27日下午3:08:35
 * Copyright (c) 2018, All Rights Reserved.
 *
*/

package us.codecraft.tinyioc.aop;
/**
 * ClassName:i 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2018年6月27日 下午3:08:35 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */

public interface Invocation
extends Joinpoint
{

public abstract Object[] getArguments();
}
