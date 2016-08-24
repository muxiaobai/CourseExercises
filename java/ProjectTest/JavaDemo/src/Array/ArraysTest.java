package Array;

import java.util.Arrays;

/** 
 * 
 * @author zhang
 * @Date  2018年7月22日 下午2:10:58
 * @doing 
 */

public class ArraysTest {
	public static void main(String[] args) {
		int[] intsort=new int[]{1,23,112,122,43,56,65,67,78,32,432,12};
		for (int i = 0; i < intsort.length; i++) {
			System.out.println("intsort:"+intsort[i]);
		}
		Arrays.sort(intsort);
		System.out.println("=======sort===========");
		for (int i = 0; i < intsort.length; i++) {
			System.out.println("intsort:"+intsort[i]);
		}
	//	Arrays
		
		
	}
}
