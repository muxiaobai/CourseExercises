import java.net.*;
import java.io.*;
public  class TCPServer {
	public static void main(String[] args) {
		InputStream in=null;
		OutputStream out=null;
	try{
		ServerSocket ss=new ServerSocket(6666);
		while (true) {
			Socket socket=ss.accept();
			in=socket.getInputStream();
			out=socket.getOutputStream();
			DataInputStream  dis=new DataInputStream(in);
			DataOutputStream dos=new DataOutputStream(out);
			String s=null;
			if((s=dis.readUTF())!=null){
				System.out.println("客户端的数据："+s);
				System.out.println("IP:"+socket.getInetAddress());
				System.out.println("port:"+socket.getPort());
			}
			dos.writeUTF("hi Hello");
			dis.close();
			dos.close();
			socket.close();
		}
	}catch(IOException e){
		e.printStackTrace();
		System.out.println("程序出错"+e);
	}
	}

}