/**
 * Project Name:ProjectTest
 * File Name:MorseCode.java
 * Package Name:Cryption
 * Date:2017年9月12日下午5:17:13
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package Cryption;

import java.util.Map;

/**
 * ClassName:MorseCode <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO 摩斯密码. <br/>
 * Date:     2017年9月12日 下午5:17:13 <br/>
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public interface MorseCode {
     public Map<String, String> getMaplist();
     public  String getValue(String value);
     public  Boolean containsKey(String key);
     public  Boolean containsValue(String value);
}

