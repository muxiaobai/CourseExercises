package org.sun.demo.collectionTest;

import java.util.HashMap;
import java.util.Map;

/** 
 * 
 * @author zhang
 * @Date  2016年7月20日 下午2:32:56
 * @doing 
 */

public class MapTest {
	public static void main(String[] args) {
		Map<Object, Object> map=new HashMap<>();
		map.put("a",1);
		map.put("a",2);
		map.put(97,2);
		map.put(null, 2);
		System.out.println(map);
		System.out.println("a".hashCode());
		System.out.println(97);
		System.out.println(map.get("a"));
		//System.out.println((11-1)&11);
		//hash一致
		//String a = "小明";
		String a= "小李";
		System.out.println(a.hashCode()>>>16);
		System.out.println(a.hashCode()^(a.hashCode()>>>16));
		System.out.println(31&(a.hashCode()^(a.hashCode()>>>16)));    
		a();
	}
	    public static void a(){
	        Map<String, Integer> map = new HashMap();
	        map.put("小明", 66);
	        map.put("小李", 77);
	        map.put("小红", 88);
	        map.put("小刚", 89);
	        map.put("小力", 90);
	        map.put("小王", 91);
	        map.put("小黄", 92);
	        map.put("小青", 93);
	        map.put("小绿", 94);
	        map.put("小黑", 95);
	        map.put("小蓝", 96);
	        map.put("小紫", 97);
	        map.put("小橙", 98);
//	        map.put("小赤", 99);
	        map.put("Frank", 100);
	        for(Map.Entry<String, Integer> entry : map.entrySet()){
	            System.out.println(entry.getKey() + ":" + entry.getValue());
	        }
	    }
}
