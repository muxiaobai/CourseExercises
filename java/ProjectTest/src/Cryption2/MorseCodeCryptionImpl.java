package Cryption2;

public class MorseCodeCryptionImpl extends MorseCodeImpl  implements MorseCodeCryption{
    MorseCode morseCode;
    public MorseCodeCryptionImpl() {
    }
    public MorseCodeCryptionImpl(MorseCode morseCode) {
        this.morseCode=morseCode;
    }
    /**
    * 加密
    * @param str
    */
    public String Encryption(String[] str){  
        StringBuffer cipherStr=new StringBuffer();
        for(int i=0; i<str.length; i++){  
            String key = str[i];  
            /*  字母      */ 
            if(morseCode.containsKey(key))  
                cipherStr.append(morseCode.getValue(key)).append(" "); // 追加空格  
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
            plainStr.append(morseCode.getKey(tmp));
        }  
        return plainStr.toString();
    }
  

}