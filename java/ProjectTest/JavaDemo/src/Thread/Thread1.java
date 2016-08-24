package Thread;

/** 
 * 
 * @author zhang
 * @Date  2016年7月23日 上午11:24:34
 * @doing 
 */

public class Thread1 implements Runnable {  
    public void run() {  
         synchronized(this) {  
              for (int i = 0; i < 100; i++) {  
                   System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);  
              }  
         }  
    }  
    public static void main(String[] args) {  
         Runnable runnable = new Thread1();  
         Thread ta = new Thread(runnable, "A");  
         Thread tb = new Thread(runnable, "B");  
         ta.start();  
         tb.start();  
    } 
}