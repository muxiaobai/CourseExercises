package Cryption;

/** 
 * 
 * @author zhang
 * @Date  2016年9月17日 下午3:42:33
 * @doing 
 */

public class Factory{
	public static  MorseCode getMorseCodeInstance(){
		return new MorseCodeModeImpl();
	}
	public static MorseCodeCryptionImpl getMorseCodeCrytionInstance(){
		return new MorseCodeCryptionImpl(getMorseCodeInstance());
	}
	public static SmallKeyPad getSmallKeyPadInstance(){
		return new SmallKeyPadModeImpl();
	}
	public static SmallKeyPadCryptionImpl getSmallKeyPadCryptionInstance(){
		return new SmallKeyPadCryptionImpl(getSmallKeyPadInstance());
	}
	
}