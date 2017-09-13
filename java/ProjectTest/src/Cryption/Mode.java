package Cryption;

import java.util.HashMap;
import java.util.Map;

/** 
 * 
 * @author zhang
 * @Date  2016年9月17日 下午3:45:30
 * @doing 两种加解密方法  摩斯密码 和小键盘
 */

public class Mode {
    public MorseCode morseCode;
    public SmallKeyPad smallKeyPad;
    Map<String, String> maplist = new HashMap<String, String>();

    public Mode() {
    }
    public Mode( MorseCode morseCode,SmallKeyPad smallKeyPad) {
        this.morseCode=morseCode;
        this.smallKeyPad = smallKeyPad;
    }
    public MorseCode getMorseCode() {
        return morseCode;
    }
    public SmallKeyPad getSmallKeyPad() {
        return smallKeyPad;
    }
    public void setMorseCode(MorseCode morseCode) {
        this.morseCode = morseCode;
    }
    public void setSmallKeyPad(SmallKeyPad smallKeyPad) {
        this.smallKeyPad = smallKeyPad;
    }
    public Map<String, String> getMaplist() {
        return maplist;
    }
     public  String getValue(Character character){
         return maplist.get(character);
     }
     public  Boolean containsKey(char str){
         return maplist.containsKey(str);
     }
     public  Boolean containsValue(String str){
         return maplist.containsValue(str);
     }
}