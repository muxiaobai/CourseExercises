package org.sun.demo.threadTest;

/**
 * 
 * @author zhang
 * @Date 2016年7月22日 下午4:03:07
 * @doing
 */

public class  OtherLock implements Runnable {

	int a = 100;

	synchronized void mul() throws InterruptedException {
		a = 1000;
		Thread.sleep(500);
		System.out.println("synchronized:" + a);
	}

	 void display() throws InterruptedException {
		Thread.sleep(200);
		a = 200;
		System.out.println("display:" + a);
	}

	public void run() {
		try {
			mul();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		OtherLock otherLock = new OtherLock();
		Thread thread = new Thread(otherLock);
		thread.start();
		System.out.println("firstmain:" + otherLock.a);
		Thread.sleep(100);
		otherLock.display();
		thread.join();
		System.out.println("secondmain:" + otherLock.a);
	}
}


