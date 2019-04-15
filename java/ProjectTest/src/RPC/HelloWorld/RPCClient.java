/**
 * Project Name:ProjectTest
 * File Name:RPCClient.java
 * Package Name:RPC.HelloWorld
 * Date:2019年3月13日下午6:59:36
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package RPC.HelloWorld;

import java.net.InetSocketAddress;

/**
 * ClassName:RPCClient 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月13日 下午6:59:36 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class RPCClient {
    public static void main(String[] args) {
        RPCImporter<EchoService> importer=new RPCImporter<EchoService>();
        EchoService echoService=importer.importer(EchoServiceImpl.class,new InetSocketAddress("localhost",8080));
        System.out.println(echoService.echo("hello"));
    }
}

