package Cryption2;

/** 
 * 
 * @author zhang
 * @Date  2016年9月17日 下午3:53:07
 * @doing 
 */

public class SmallKeyPadCryptionImpl extends SmallKeyPadImpl implements SmallKeyPadCryption{

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
    
    public String Encryption(String[] str){  
    StringBuffer cipherStr=new StringBuffer();
    for(int i=0; i<str.length; i++){  
        String key = str[i];  
        if(smallKeyPad.containsKey(key))  
            cipherStr.append(smallKeyPad.getValue(key)).append(" "); // 追加空格  
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
            plainStr.append(smallKeyPad.getKey(tmp));
        }  
        return plainStr.toString();
    }
   
}