package ClassLoader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;


public class MyClassLoader {

	@SuppressWarnings({ "rawtypes", "resource", "unused" })
	public static void main(String[] args) throws Exception {
		  URL url = new URL("file:/E:"+File.separator+"JavaEE"+File.separator+"Project"+File.separator+"JavaDemo"+File.separator+"src"+File.separator+"ClassLoader"); 
          ClassLoader myloader = new URLClassLoader(new URL[]{url}); 
          
          System.out.println("==自定义加载类继承App "+myloader.getParent()+"===");
          Class c = myloader.loadClass("ClassLoader.Test1"); 
          
          System.out.println("=="+c.getClassLoader()+"====="); //为什么是Application ClassLoader
          Test1 test1 = (Test1) c.newInstance(); 
  } 
	}

 class Test1 { 
    static { 
            System.out.println("Test1的静态初始化块执行了！"); 
    } 
}
