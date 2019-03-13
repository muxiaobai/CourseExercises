package org.sun.demo.stringTest;

public class StringBufferTest {
	public static void main(String[] args) {
	    //线程安全使用synchronized
		StringBuffer sb=new StringBuffer();
		
		while(true){
		    thdemo thread =new thdemo(sb); 
		    thread.run();
		    System.out.print(sb);
		}
		
	}
}
class thdemo implements Runnable{
    private StringBuffer sBuffer = null;
    public thdemo(StringBuffer sBuffer){
        this.sBuffer = sBuffer;
    }
    @Override
    public void run() {
        
        // TODO Auto-generated method stub
        sBuffer.append(this).append("/n");
    }
    
}