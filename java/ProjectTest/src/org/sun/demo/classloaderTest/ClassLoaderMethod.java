package org.sun.demo.classloaderTest;
/*
 * 同一个ClassLoader加载的类文件，只有一个Class实例。
 * 但是，如果同一个类文件被不同的ClassLoader载入，
 * 则会有两份不同的ClassLoader实例（前提是着两个类加载器不能用相同的父类加载器）。
 */
public class ClassLoaderMethod {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		   ClassLoader loader = ClassLoaderMethod.class.getClassLoader(); 
           System.out.println("ClassLoaderMethod:"+loader); 
           //使用ClassLoader.loadClass()来加载类，不会执行初始化块 
          Class clazz =   loader.loadClass("org.sun.demo.classloaderTest.Test"); 
          System.out.println(clazz.getClassLoader().getParent());
          Object claObject = clazz.newInstance();
          
           //使用Class.forName()来加载类，默认会执行初始化块 
           Object object = Class.forName("org.sun.demo.classloaderTest.Test"); 
           System.out.println(object.getClass().getClassLoader());
           
           //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块 false/true(决定是否执行)
//           Class.forName("ClassLoader.Test ", false, loader); 
	}
	

}

 class Test { 
	 	public Test() {
	 		System.out.println("私有构造方法模块");
	 	}
       static{ 
               System.out.println("静态初始化块执行了！"); 
       } 
}