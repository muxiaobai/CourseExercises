/**
 * Project Name:ProjectTest
 * File Name:NioServer.java
 * Package Name:RPC.HelloWorld
 * Date:2019年5月8日上午9:38:07
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package RPC.HelloWorld;
/**
 * ClassName:NioServer 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年5月8日 上午9:38:07 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */

 
public class NioServer {
    
    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length < 0){
            
            //port = Integer.valueOf(args[0]);  
        }
        //创建服务器线程
        NioRPCExporter nioServerWork = new NioRPCExporter(port);
        new Thread(nioServerWork, "server").start();
    }
 
}