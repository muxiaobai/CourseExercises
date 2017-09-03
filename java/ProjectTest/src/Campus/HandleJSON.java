package Campus;

import java.util.List;

/** 
 * 
 * @author zhang
 * @Date  2016年9月5日 上午9:08:14
 * @doing 
 */

public class HandleJSON {
	public static List<Campus> ListData(String data){
		return RunJavaScript.run(data);
	}
	
	
}
