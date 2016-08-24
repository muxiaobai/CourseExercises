package Construtor;

/**
 * 
 * @author zhang
 * @Date 2016年7月22日 下午4:06:24
 * @doing
 */

public class InitTest {

	// 主函数
	public static void main(String str[]) {
		Test a = new Test();
	}
}

class Sample {
	Sample(String s) {
		System.out.println(s);
	}

	Sample() {
		System.out.println("Sample默认构造函数被调用");
	}
}

class Test {
	static Sample sam = new Sample("静态成员sam初始化");
	Sample sam1 = new Sample("sam1成员初始化");
	static {
		System.out.println("static块执行");
		if (sam == null)
			System.out.println("sam is null");
		sam = new Sample("静态块内初始化sam成员变量");
	}

	Test() {
		System.out.println("Test默认构造函数被调用");
	}
}