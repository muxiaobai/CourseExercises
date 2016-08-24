package Thread;

import java.util.Date;

/** 
 * 
 * @author zhang
 * @Date  2016年7月23日 上午11:24:34
 * @doing 
 */

public class ThreadSleep  {  
	 public static void main(String[] args) {  
		MyThread myThread=new MyThread();
		myThread.start();
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		// myThread.interrupt();
		myThread.falg=false;
	    } 
   
}

class MyThread extends Thread{
	boolean falg=true;
	 public void run() {  
	    	while (falg) {
	             System.out.println("date:"+new Date());  
	             try {
					sleep(100);
				} catch (InterruptedException e) {
					return;
				}
	    	}
	    }  
}