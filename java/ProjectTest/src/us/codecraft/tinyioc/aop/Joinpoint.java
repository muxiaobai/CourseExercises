/**
 * Project Name:12221
 * File Name:Joinpoint.java
 * Package Name:us.codecraft.tinyioc.aop
 * Date:2018年6月27日下午3:10:46
 * Copyright (c) 2018, All Rights Reserved.
 *
*/

package us.codecraft.tinyioc.aop;
/**
 * ClassName:Joinpoint 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2018年6月27日 下午3:10:46 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
import java.lang.reflect.AccessibleObject;

public interface Joinpoint
{

    public abstract Object proceed()
        throws Throwable;

    public abstract Object getThis();

    public abstract AccessibleObject getStaticPart();
}
