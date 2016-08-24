package Socket;
import java.net.*;
import java.io.*;
public  class TCPServer {
	public static void main(String[] args) throws Exception{
		ServerSocket ss=new ServerSocket(6666);
			while (true) {
				Socket s=ss.accept();
				System.out.println("hello word!");
				DataInputStream  Dim=new DataInputStream(s.getInputStream());
				System.out.println(Dim.readUTF());
				s.close();
			}
	}

}