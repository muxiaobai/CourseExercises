package us.test.codecraft.tinyioc.java.us.codecraft.tinyioc.aop;

import us.codecraft.tinyioc.aop.MethodInterceptor;
import us.codecraft.tinyioc.aop.MethodInvocation;

/**
 * @author yihua.huang@dianping.com
 */
public class TimerInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long time = System.nanoTime();
		System.out.println("Invocation of Method " + invocation.getMethod().getName() + " start!");
		Object proceed = invocation.proceed();
		System.out.println("Invocation of Method " + invocation.getMethod().getName() + " end! takes " + (System.nanoTime() - time)
				+ " nanoseconds.");
		return proceed;
	}

}
