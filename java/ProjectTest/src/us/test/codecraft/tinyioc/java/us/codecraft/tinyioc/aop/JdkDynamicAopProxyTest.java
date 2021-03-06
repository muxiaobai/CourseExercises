package us.test.codecraft.tinyioc.java.us.codecraft.tinyioc.aop;

import java.lang.reflect.Method;

import org.junit.Test;

import us.codecraft.tinyioc.aop.AdvisedSupport;
import us.codecraft.tinyioc.aop.JdkDynamicAopProxy;
import us.codecraft.tinyioc.aop.MethodMatcher;
import us.codecraft.tinyioc.aop.TargetSource;
import us.codecraft.tinyioc.context.ApplicationContext;
import us.codecraft.tinyioc.context.ClassPathXmlApplicationContext;
import us.test.codecraft.tinyioc.HelloWorldService;
import us.test.codecraft.tinyioc.HelloWorldServiceImpl;
import us.test.codecraft.tinyioc.OutputService;
import us.test.codecraft.tinyioc.OutputServiceImpl;

/**
 * @author yihua.huang@dianping.com
 */
public class JdkDynamicAopProxyTest {
    public static void main(String[] args){
        JdkDynamicAopProxyTest jdkDynamicAopProxyTest = new JdkDynamicAopProxyTest();
        try {
            jdkDynamicAopProxyTest.testInterceptor();
        } catch (Exception e) {
            
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
}
	@Test
	public void testInterceptor() throws Exception {
		// --------- helloWorldService without AOP
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
//		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
//		helloWorldService.helloWorld();

		  OutputService outputService = new OutputServiceImpl();
	      HelloWorldService helloWorldService = new HelloWorldServiceImpl("Hello World!",outputService);
	      helloWorldService.helloWorld();
		
		// --------- helloWorldService with AOP
		// 1. 设置被代理对象(Joinpoint)
		AdvisedSupport advisedSupport = new AdvisedSupport();
		TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class,
				HelloWorldService.class);
		advisedSupport.setTargetSource(targetSource);
		
		//设置匹配的match
		MethodMatcher methodMatcher = new MethodMatcher() {
            
            @Override
            public boolean matches(Method method, Class targetClass) {
                return true;
            }
        };
		advisedSupport.setMethodMatcher(methodMatcher);
		
		// 2. 设置拦截器(Advice)
		TimerInterceptor timerInterceptor = new TimerInterceptor();
		advisedSupport.setMethodInterceptor(timerInterceptor);

		// 3. 创建代理(Proxy)
		JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
		HelloWorldService helloWorldServiceProxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();

		// 4. 基于AOP的调用
		helloWorldServiceProxy.helloWorld();

	}
}
