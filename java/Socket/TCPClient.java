import java.net.*;
import java.io.*;
public  class TCPClient {
	public static void main(String[] args){
		InputStream in=null;
		OutputStream out=null;
		try{
			Socket socket =new Socket("127.0.0.1",6666);
			in=socket.getInputStream();
			out=socket.getOutputStream();
			DataInputStream dis=new DataInputStream(in);
			DataOutputStream dos=new DataOutputStream(out);
			dos.writeUTF("Hello Server!我是张！");
			String s=null;
			if((s=dis.readUTF())!=null){
					System.out.println("服务器的数据："+s);
			}
			dos.close();
			dis.close();
			socket.close();
		}catch(IOException e){
		e.printStackTrace();
		}
	}
}