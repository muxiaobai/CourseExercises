package Cryption;

import java.util.Map.Entry;

public class MorseCodeCryptionImpl extends Cryption implements Decryption,Encryption{
	
	@Override
	public String Encryption(String[] str) {
		// TODO Auto-generated method stub
		return null;
	}  
/**
* 加密
* @param str
*/
public String Encryption(char[] str){  
StringBuffer cipherStr=new StringBuffer();
for(int i=0; i<str.length; i++){  
	char tmp = str[i];  
    /*  字母      */  
    if(morseCode.containsKey(tmp))  
        cipherStr.append(morseCode.getValue(tmp)).append(" "); // 追加空格  
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
    if(morseCode.containsValue(tmp)){  
        Boolean flag=true;
    	for (Entry<Character, String> s :morseCode.maplist.entrySet()) {  
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