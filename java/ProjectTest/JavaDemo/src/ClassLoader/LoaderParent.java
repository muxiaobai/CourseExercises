package ClassLoader;
public class LoaderParent {
	public static int a=1;
	public int b=1;
//	public LoaderParent() {
//		System.out.println("初始化模块赋值前a:"+a);
//		a=3;
//		System.out.println("初始化模块赋值后a:"+a);
//		System.out.println("初始化模块赋值前b:"+b);
//		b=2;
//		System.out.println("初始化模块赋值后b:"+b);
//		
//	}
//	static{
//		System.out.println("static模块赋值前a:"+a);
//		a=2;
//		System.out.println("static模块赋值前a:"+a);
//	}
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		   LoaderParent instance = new LoaderParent(); 
           Class c = instance.getClass(); 
           ClassLoader loader = c.getClassLoader(); 
           System.out.println(loader) ; 
           System.out.println(loader.getParent()); 
           System.out.println(loader.getParent().getParent()); 
           //AppClassLoader>ExtClassLoader
           //BootstrapLoader为空。原因是Bootstrap Loader（启动类加载器）是用C语言实现的，找不到一个确定的返回父Loader的方式，于是就返回null。
	}

}
