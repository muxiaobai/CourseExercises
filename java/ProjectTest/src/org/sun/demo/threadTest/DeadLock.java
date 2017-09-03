package org.sun.demo.threadTest;

/** 
 * 
 * @author zhang
 * @Date  2016年7月22日 下午4:03:07
 * @doing 
 */

public class DeadLock {
    public static void main(String[] args){
    	Runnable runnable1=new testDeadLock(true);
    	Runnable runnable2=new testDeadLock(false);
    	Thread thread1=new Thread(runnable1);
    	Thread thread2=new Thread(runnable2);
    	thread1.start();
    	thread2.start();
    }
}

class testDeadLock implements Runnable{
	
	public  boolean flag;
	static Object o1=new Object();
	static Object o2=new Object();
	public testDeadLock(boolean flag) {
		this.flag=flag;
	}
	@Override
	public void run() {
		System.out.println("=====================:"+flag);
		if(flag){
			synchronized (o1) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized (o2) {
					System.out.println("00000000000000");
				}
			}
			
		}
		
		if(!flag){
			synchronized (o2) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized (o1) {
					System.out.println("11111111111111");
				}
			}
			
		}
	}
}


