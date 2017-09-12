package Cryption;

/** 
 * 
 * @author zhang
 * @Date  2016年9月17日 下午3:42:33
 * @doing 
 */

public class Factory{
    public final  static  mode = new Mode();
	public static  MorseCode getMorseCodeInstance(){
		return mo;
	}
	public static MorseCodeCryptionImpl getMorseCodeCrytionInstance(){
		return new MorseCodeCryptionImpl();
	}
	public static SmallKeyPad getSmallKeyPadInstance(){
		return new SmallKeyPad();
	}
	public static SmallKeyPad getSmallKeyPadInstance(){
        return new SmallKeyPad();
    }
	public static SmallKeyPadCryptionImpl getSmallKeyPadCryptionInstance(){
		return new SmallKeyPadCryptionImpl();
	}
	
}