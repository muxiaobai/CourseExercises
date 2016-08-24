package Thread;

/** 
 * 
 * @author zhang
 * @Date  2016年7月22日 下午4:03:07
 * @doing 
 */

public class SynchronizedTest2 {
		private static int NUM_OF_THREAD =100000;
	    static Thread[] threads = new Thread[NUM_OF_THREAD];
	    public static  My acc = new My("John", 1000.0f);
	    public static void main(String[] args){
	    	Long begin=System.currentTimeMillis();
	        for (int i = 0; i< NUM_OF_THREAD; i++) {
	            threads[i] = new th(acc);
	            threads[i].start();
	        }
	        for (int i=0; i<NUM_OF_THREAD; i++){
	            try {
	                threads[i].join(); //等待所有线程运行结束
	            } catch (InterruptedException e) {
	                // ignore
	            }
	        }  
	        Long end=System.currentTimeMillis();
	        System.out.println((begin-end)/1000+"s");
	        display();
	    }
	    
	   static void  display(){
	    	 System.out.println("Finally, John's balance is:" + acc.getBalance());
	    }
}
class th extends Thread{
	public My acc;
	th(My acc){
		this.acc=acc;
	}
	@Override
	public void run() {
		  acc.deposit(100.0f);
          acc.withdraw(100.0f);
	}
	
}
class My {
    String name;
    float amount;
    
    
    public My(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }

    public synchronized void  deposit(float amt) {
        float tmp = amount;
        tmp += amt;
        
        try {
            Thread.sleep(1);//模拟其它处理所需要的时间，比如刷新数据库等
        } catch (InterruptedException e) {
            // ignore
        	
        }
        System.out.println("==method:"+"deposit=="+"amount:"+amount);
        amount = tmp;
    }

    public synchronized void withdraw(float amt) {
        float tmp = amount;
        tmp -= amt;

        try {
            Thread.sleep(1);//模拟其它处理所需要的时间，比如刷新数据库等
        } catch (InterruptedException e) {
            // ignore
        }        
        System.out.println(" ==method:"+"withdraw =="+"amount:"+amount);
        amount = tmp;
    }

    public float getBalance() {
        return amount;
    }
}


