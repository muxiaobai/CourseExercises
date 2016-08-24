package Exception;

/**
 * 
 * @author zhang
 * @Date 2016年7月20日 下午1:59:25
 * @doing
 */

public class ExceptionTest {
	public static void main(String[] args) throws Exception {
		ExceptionTest exceptionTest=new ExceptionTest();
		System.out.println(exceptionTest.value());
		System.out.println("======================");
		System.out.println(exceptionTest.va());
		System.out.println("错误后，后面代码仍可以执行");
		
	}
	public Integer va() throws Exception{
		Integer ret = 0;
		ret=1/0;
		System.out.println("yichangl");
		return ret;
	}
	public Integer value(){
		Integer ret = 0;
		try {
			ret=1/1;
			//throw new Exception();
		} catch (Exception e) {
			ret = 3;
			e.printStackTrace();
			System.out.println("catch:"+ret);
			return ret;
		} finally {
			ret = 2;
			System.out.println("finally:"+ret);
		}
		return ret;
	}
}
