package Cryption;

/**
 * 
 * @author zhang
 * @Date 2016年9月17日 下午3:43:55
 * @doing 加密解密操作，练习工厂模式
 * Cryption 密   EnCryption加密  DeCryption解密
 * SmallKey morsecode 摩斯密码
 * 
 */

public class test {
	public static void main(String[] args) {
		MorseCodeCryption morseCodeCryption = Factory.getMorseCodeCrytionInstance();
		SmallKeyPadCryption smallKeyPadCryption = Factory.getSmallKeyPadCryptionInstance();
		String myname = "car";
		String[] strings =getStrings(myname);
		char[] str = myname.toCharArray();
		String EncryptionStr = smallKeyPadCryption.Encryption(strings);
		System.out.println("一次加密后：" + EncryptionStr);
		String erci = morseCodeCryption.Encryption(getStrings(EncryptionStr));
		System.out.println("二次加密后：" + erci);

//		String[] yici = erci.split(" ");
//		String yicihou = morseCodeCryption.Decryption(yici);
//		System.out.println("一次解密后：" + yicihou);
//		String[] plainStr = yicihou.split(" ");
//		String ercihou = smallKeyPadCryption.Decryption(plainStr);
//		System.out.println("二次解密后：" + ercihou);

	}
	public static String[] getStrings(String str){
        char[] characters = str.toCharArray();
        String[] strings = new String[str.length()];
        for (int i = 0; i < characters.length; i++) {
            strings[i] =String.valueOf(characters[i]);
        }
        return strings;
    }
}
