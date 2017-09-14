package Cryption2;

/** 
 * 
 * @author zhang
 * @Date  2016年9月17日 下午3:47:53
 * @doing 
 */

public class MorseCodeImpl extends ModeImpl implements MorseCode{
   
	 MorseCodeImpl() {  
	     maplist.put("A", "01");  
         maplist.put("B", "1000");  
         maplist.put("C", "1010");  
         maplist.put("D", "100");  
         maplist.put("E", "0");  
         maplist.put("F", "0010");  
         maplist.put("G", "110");  
         maplist.put("H", "0000");  
         maplist.put("I", "00");  
         maplist.put("J", "0111");  
         maplist.put("K", "101");  
         maplist.put("L", "0100");  
         maplist.put("M", "11");  
         maplist.put("N", "10");  
         maplist.put("O", "111");  
         maplist.put("P", "0110");  
         maplist.put("Q", "1101");  
         maplist.put("R", "010");  
         maplist.put("S", "000");  
         maplist.put("T", "1");  
         maplist.put("U", "001");  
         maplist.put("V", "0001");  
         maplist.put("W", "011");  
         maplist.put("X", "1001");  
         maplist.put("Y", "1011");  
         maplist.put("Z", "1100"); 
         
         maplist.put("a", "01");  
         maplist.put("b", "1000");  
         maplist.put("c", "1010");  
         maplist.put("d", "100");  
         maplist.put("e", "0");  
         maplist.put("f", "0010");  
         maplist.put("g", "110");  
         maplist.put("h", "0000");  
         maplist.put("i", "00");  
         maplist.put("j", "0111");  
         maplist.put("k", "101");  
         maplist.put("l", "0100");  
         maplist.put("m", "11");  
         maplist.put("n", "10");  
         maplist.put("o", "111");  
         maplist.put("p", "0110");  
         maplist.put("q", "1101");  
         maplist.put("r", "010");  
         maplist.put("s", "000");  
         maplist.put("t", "1");  
         maplist.put("u", "001");  
         maplist.put("v", "0001");  
         maplist.put("w", "011");  
         maplist.put("x", "1001");  
         maplist.put("y", "1011");  
         maplist.put("z", "1100");  
         /* 数字电码019 */  
         maplist.put("0", "11111");  
         maplist.put("1", "01111");  
         maplist.put("2", "00111");  
         maplist.put("3", "00011");  
         maplist.put("4", "00001");  
         maplist.put("5", "00000");  
         maplist.put("6", "10000");  
         maplist.put("7", "11000");  
         maplist.put("8", "11100");  
         maplist.put("9", "11110");  
   
         /* 标点符号，可自增删 */  
         maplist.put(",", "110011"); // ,逗号  
         maplist.put(".", "010101"); // .句号  
         maplist.put("?", "001100"); // ?问号  
         maplist.put("!", "101011"); // !感叹号  
         maplist.put("\"", "011110");// '单引号  
         maplist.put("\"", "010010");// "引号  
         maplist.put("=", "10001");  // =等号  
         maplist.put(":", "111000"); // :冒号  
         maplist.put(";", "101010"); // ;分号  
         maplist.put("(", "10110");  // (前括号  
         maplist.put(")", "101101"); // )后括号  
         maplist.put(" ", "★");      // 留空格，这里的星号是自定义的  
	    }
   
}