package org.sun.demo.classloaderTest;
class parent{
	public static int parent=1;
	static{
		System.out.println("parent static块");
	}
}
class child extends parent{
	static{
		System.out.println("child static块");
	}
}
public class MainLoader {
	public static int i=1;
	static{
		System.out.println("MainLoader static i:"+i);
		System.out.println("MainLoader static 块");
	}
	public static void main(String[] args) {
	System.out.println(child.parent);
	}

}
