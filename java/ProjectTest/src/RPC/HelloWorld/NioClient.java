/**
 * Project Name:ProjectTest
 * File Name:NioClient.java
 * Package Name:RPC.HelloWorld
 * Date:2019年5月8日上午9:38:18
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package RPC.HelloWorld;
/**
 * ClassName:NioClient 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年5月8日 上午9:38:18 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class NioClient {
    
    public static void main(String[] args) {
        
        int port = 8080;
        if(args !=null && args.length > 0){
            try {
                //port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        //创建客户端线程
        new Thread(new NioRPCImporter("127.0.0.1",port),"client").start();
        
    }
}