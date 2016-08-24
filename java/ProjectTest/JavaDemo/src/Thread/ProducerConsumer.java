package Thread;

/** 
 * 
 * @author zhang
 * @Date  2016年7月22日 下午4:03:07
 * @doing 
 */

public class ProducerConsumer {
    public static void main(String[] args){
    	SyncStack syncStack=new SyncStack();
    	for (int i = 0; i < 3; i++) {
    		Producer producer=new Producer(syncStack);
    		Thread producerthread=new Thread(producer);
    		producerthread.setName("Producer"+i);
    		producerthread.start();
		}
    	for (int i = 0; i < 3; i++) {
    		Consumer consumer=new Consumer(syncStack);
        	Thread consumerthread=new Thread(consumer);
        	consumerthread.setName("Consumer"+i);
        	consumerthread.start();
		}
    	
    }
}
class Consumer extends Thread{
	SyncStack syncStack =null;
	public Consumer(SyncStack syncStack) {
		this.syncStack=syncStack;
	}
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
		Mantou mantou=	syncStack.pop();
			System.out.println(Thread.currentThread().getName()+" Consumer mantou:"+mantou);
		}
	}
}
class Producer extends Thread{
	SyncStack syncStack=null;
	public Producer(SyncStack syncStack) {
		this.syncStack=syncStack;
	}
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			Mantou mantou=new Mantou(i);
			System.out.println(this.currentThread().getName()+" Producer mantou:"+mantou);
			syncStack.push(mantou);
//			Thread.sleep((int));
		}
	}
}
class Mantou{
	int id;
	public Mantou(int id) {
		this.id=id;
	}
	@Override
	public String toString() {
		return "id:"+id;
	}
}
class SyncStack{
	int index=0;
	Mantou[] arr=new Mantou[6];
	public synchronized void push(Mantou mt) {
		while(index==arr.length) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		this.notifyAll();
		arr[index]=mt;
		index++;
	}
	public synchronized Mantou pop() {
		while (index==0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		this.notifyAll();
		index--;
		return arr[index];
	}
}

