package Collection;

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
		map.put(97,2);
		map.put(null, 2);
		System.out.println(map);
		System.out.println("a".hashCode());
		System.out.println(97);
		System.out.println(map.get("a"));
		//System.out.println((11-1)&11);
	}
}
