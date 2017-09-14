package Cryption;

/** 
 * 
 * @author zhang
 * @Date  2016年9月17日 下午3:46:50
 * @doing 小键盘按键对应的字母
 * 1     2 adb 3 def
 * 4 ghi 5 jkl 6 mno
 * 7 pqrs 8 tuv 9 wxyz
 */

public class SmallKeyPadImpl extends Mode implements SmallKeyPad{
	
	 SmallKeyPadImpl() {  
	     maplist.put("21","a");  
         maplist.put("22","b");  
         maplist.put("23","c");  
         maplist.put("31","d");
         maplist.put("32","e");  
         maplist.put("33","f");  
         maplist.put("41","g");  
         maplist.put("42","h");  
         maplist.put("43","i");  
         maplist.put("51","j");  
         maplist.put("52","k");  
         maplist.put("53","l");  
         maplist.put("61","m");  
         maplist.put("62","n");  
         maplist.put("63","o");  
         maplist.put("71","p");  
         maplist.put("72","q");  
         maplist.put("73","r");  
         maplist.put("74","s");  
         maplist.put("81","t");  
         maplist.put("82","u");  
         maplist.put("83","v");  
         maplist.put("91","w");  
         maplist.put("92","x");  
         maplist.put("93","y");  
         maplist.put("94","z");  
         maplist.put("a","21");  
         maplist.put("b","22");  
         maplist.put("c","23");  
         maplist.put("d","31");
         maplist.put("e","32");  
         maplist.put("f","33");  
         maplist.put("g","41");  
         maplist.put("h","42");  
         maplist.put("i","43");  
         maplist.put("j","51");  
         maplist.put("k","52");  
         maplist.put("l","53");  
         maplist.put("m","61");  
         maplist.put("n","62");  
         maplist.put("o","63");  
         maplist.put("p","71");  
         maplist.put("q","72");  
         maplist.put("r","73");  
         maplist.put("s","74");  
         maplist.put("t","81");  
         maplist.put("u","82");  
         maplist.put("v","83");  
         maplist.put("w","91");  
         maplist.put("x","92");  
         maplist.put("y","93");  
         maplist.put("z","94");
	    }  
}