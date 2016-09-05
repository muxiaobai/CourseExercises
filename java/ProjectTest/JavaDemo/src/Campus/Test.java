package Campus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * 
 * @author zhang
 * @Date  2016年9月5日 上午8:54:53
 * @doing 
 */

public class Test {
	public static void main(String[] args) {  
		String[] title = {"标题","公司名称","城市","行业标签","链接地址","来源","描述信息"};
        POIWriteUtil.makeEXECL(getData(), "D:/", DateUtil.formatDate("yyyyMMdd",new Date())+".xls", title);
		//System.out.println(CampusJSON.loadJson(getUrl(30)));
	}  
	public static  List<Campus> getData(){
		List<Campus> list=new ArrayList<>();
		   for (int i = 0; i <20; i++) {
				  Integer 	url=i*30;
			        String json =CampusJSON.loadJson(getUrl(url));  
			       list.addAll( HandleJSON.ListData(json));
				}
			return  list;
	}
	public static String  getUrl(Integer page){
		//http://zhaopin.baidu.com/api/xzzwmidasync?query=%E6%A0%A1%E5%9B%AD%E6%8B%9B%E8%81%98&recruitcity=&rn=30&pn=30
        String base="http://zhaopin.baidu.com/api/xzzwmidasync?query=%E6%A0%A1%E5%9B%AD%E6%8B%9B%E8%81%98&recruitcity=&rn=30&pn=";
        String real=base+page;
       return real;
	}
}
