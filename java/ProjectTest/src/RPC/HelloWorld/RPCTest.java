package RPC.HelloWorld;

import java.net.InetSocketAddress;

/** 
 * 
 * @author zhang
 * @Date  2016年9月9日 下午9:17:33
 * @doing 
 */

public class RPCTest {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					RPCExporter.exporter("localhost", 8080);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}).start();
		RPCImporter<EchoService> importer=new RPCImporter<EchoService>();
		EchoService echoService=importer.importer(EchoServiceImpl.class,new InetSocketAddress("localhost",8080));
		System.out.println(echoService.echo("hello"));
	}
}
