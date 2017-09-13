package Cryption;

import java.util.Map.Entry;

/** 
 * 
 * @author zhang
 * @Date  2016年9月17日 下午3:53:07
 * @doing 
 */

public class SmallKeyPadCryptionImpl extends Mode implements SmallKeyPadCryption{

    SmallKeyPad smallKeyPad;
    public SmallKeyPadCryptionImpl() {
    }
    public SmallKeyPadCryptionImpl(SmallKeyPad smallKeyPad) {
        this.smallKeyPad=smallKeyPad;
    }
    /**
    * 加密
    * @param str
    */
    
    public String Encryption(char[] str){  
    StringBuffer cipherStr=new StringBuffer();
    for(int i=0; i<str.length; i++){  
    	char tmp = str[i];  
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
            	for (Entry<String, String> s :smallKeyPad.getMaplist().entrySet()) {  
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
}