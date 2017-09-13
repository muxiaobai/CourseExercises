package org.sun.demo.collectionTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** 
 * 
 * @author zhang
 * @Date  2016年7月20日 下午2:32:48
 * @doing 
 */

public class ListTest {
	public static void main(String[] args) {
		List lists=new ArrayList<>();
		lists.add("asas");
		lists.add(null);
		System.out.println(lists.indexOf(null));
		System.out.println(lists);
		LinkedList linkedList=new LinkedList<>();
	}
}
