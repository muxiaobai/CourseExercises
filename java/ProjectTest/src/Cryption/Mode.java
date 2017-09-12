package Cryption;

/** 
 * 
 * @author zhang
 * @Date  2016年9月17日 下午3:45:30
 * @doing 两种加解密方法  摩斯密码 和小键盘
 */

public class Mode {
    public MorseCode morseCode;
    public SmallKeyPad smallKeyPad;
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
}
