package Cryption;

import java.util.Map.Entry;

/**
 * 
 * @author zhang
 * @Date 2016年9月17日 下午3:43:55
 * @doing
 */

public class test {
	public static void main(String[] args) {
		MorseCodeCryptionImpl cryption = Factory.getMorseCodeCrytionInstance();
		SmallKeyPadCryption smallKeyPadCryption = Factory.getSmallKeyPadCryptionInstance();
		String myname = "car";

		char[] str = myname.toCharArray();
		String EncryptionStr = cryption.Encryption(str);
		System.out.println("一次加密后：" + EncryptionStr);
		String erci = cryption.Encryption(EncryptionStr.toCharArray());
		System.out.println("二次加密后：" + erci);

		String[] yici = erci.split(" ");
		String yicihou = cryption.Decryption(yici);
		System.out.println("一次解密后：" + yicihou);
		String[] plainStr = yicihou.split(" ");
		String ercihou = cryption.Decryption(plainStr);
		System.out.println("二次解密后：" + ercihou);

	}
}
