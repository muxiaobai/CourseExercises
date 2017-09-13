package Cryption;

/** 
 * 
 * @author zhang
 * @Date  2016年9月17日 下午3:42:33
 * @doing 
 */

public class Factory{
	public static  MorseCode getMorseCodeInstance(){
		return new MorseCodeImpl();
	}
	public static MorseCodeCryptionImpl getMorseCodeCrytionInstance(){
		return new MorseCodeCryptionImpl(getMorseCodeInstance());
	}
	public static SmallKeyPad getSmallKeyPadInstance(){
		return new SmallKeyPadImpl();
	}
	public static SmallKeyPadCryptionImpl getSmallKeyPadCryptionInstance(){
		return new SmallKeyPadCryptionImpl(getSmallKeyPadInstance());
	}
	
}