package Cryption;

/** 
 * 
 * @author zhang
 * @Date  2016年9月17日 下午3:42:33
 * @doing 
 */

class Factory{
	public static  MorseCode getMorseCodeInstance(){
		return new MorseCode();
	}
	public static MorseCodeCryptionImpl getMorseCodeCrytionInstance(){
		return new MorseCodeCryptionImpl(getMorseCodeInstance());
	}
	public static SmallKeyPad getSmallKeyPadInstance(){
		return new SmallKeyPad();
	}
	public static SmallKeyPadCryption getSmallKeyPadCryptionInstance(){
		return new SmallKeyPadCryption(getSmallKeyPadInstance());
	}
	
}