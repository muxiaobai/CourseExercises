package Socket;
import java.net.*;
import java.io.*;
public  class UDPServer {
	public static void main(String[] args) throws Exception {
		byte[] buf=new byte[1024];
		DatagramPacket dp=new DatagramPacket(buf,buf.length);
		DatagramSocket ds=new DatagramSocket(6666);
		while (true) {
			ds.receive(dp);
			System.out.println("IP:"+ds.getInetAddress());
			System.out.println("Port:"+ds.getPort());
		//System.out.println(new String(buf,0,dp.getLength()));
			ByteArrayInputStream bais=new ByteArrayInputStream(buf);
			DataInputStream dis=new DataInputStream(bais);
			System.out.println(dis.readLong());
		}
	}
}