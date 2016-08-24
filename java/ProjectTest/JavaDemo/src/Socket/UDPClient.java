package Socket;
import java.net.*;
import java.io.*;
public  class UDPClient {
	public static void main(String[] args)throws Exception {
		long i=10000L;
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		DataOutputStream dos=new DataOutputStream(baos);
		dos.writeLong(i);
		//byte[] buf=(new String("hello")).getBytes();
		byte[] buf=baos.toByteArray();
		System.out.println(buf.length);
		DatagramPacket dp=new DatagramPacket(buf,buf.length,new InetSocketAddress("127.0.0.1",6666));
		DatagramSocket ds=new DatagramSocket(8888);
		ds.send(dp);
		ds.close();
	}

}