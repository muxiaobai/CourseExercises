package Cryption;

import java.util.Map.Entry;

/** 
 * 
 * @author zhang
 * @Date  2016年9月17日 下午3:53:07
 * @doing 
 */

public class SmallKeyPadCryptionImpl {

SmallKeyPad smallKeyPad;
public SmallKeyPadCryptionImpl(SmallKeyPad smallKeyPad) {
this.smallKeyPad=smallKeyPad;
}
/**
* 加密
* @param str
*/
public String Encryption(Integer[] str){  
StringBuffer cipherStr=new StringBuffer();
for(int i=0; i<str.length; i++){  
	Integer tmp = str[i];  
    if(smallKeyPad.containsKey(tmp))  
        cipherStr.append(smallKeyPad.getValue(tmp)).append(" "); // 追加空格  
}  
return cipherStr.toString(); 
}  
/**
* 解密
* @param str
*/
public String Decryption(String[] str){  
StringBuffer plainStr=new StringBuffer();
for(int i=0; i<str.length; i++){  
    String tmp = str[i];  
    /*  字母      */
    if(smallKeyPad.containsValue(tmp)){  
        Boolean flag=true;
    	for (Entry<Integer, Character> s :smallKeyPad.maplist.entrySet()) {  
        	if(tmp.equals(s.getValue())&&flag){  
                plainStr.append(s.getKey().toString().toLowerCase());  
                flag=false;
                //break;
            }  
        }  
    } 
    
}  
return plainStr.toString();
}  