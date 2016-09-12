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
		System.out.println("end ");
        //System.out.println(CampusJSON.loadJson(getUrl(30)));
	}  
	public static  List<Campus> getData(){
		List<Campus> list=new ArrayList<>();
		   for (int i = 0; i <30; i++) {
			   	Integer 	url=i*30;
		        String json =CampusJSON.loadJson(getUrl(url));  
			        if(json!=null&&!"".equals(json)){
			            list.addAll(HandleJSON.ListData(json));
			        }
				}
	return  list;
	}
	/*
	 * 工作城市：不限recruitcity
	 * 北京 上海 广州 深圳 天津 南京 武汉 沈阳 西安 成都 重庆 杭州 青岛 大连 宁波 济南 哈尔滨 长春 厦门
	 * 郑州 长沙 福州 昆明 苏州 无锡 南昌 南宁 合肥
	 *	热门标签：不限specialtips
	 *	计算机软件 教育 电子商务 培训 院校 工商管理 互联网 计算机 证券 投资 金融 专业服务(咨询、财会、翻译)
	 *	建筑 建材 工程 生物工程 电子技术 通信 半导体 集成电路
	 *
	 **/
	public static String  getUrl(Integer page){
		//http://zhaopin.baidu.com/api/xzzwmidasync?query=%E6%A0%A1%E5%9B%AD%E6%8B%9B%E8%81%98&recruitcity=&rn=30&pn=30
        String base="http://zhaopin.baidu.com/api/xzzwmidasync?query=校园招聘&rn=30&pn="+page;
         base += "&recruitcity=";
         base +="&specialtips=电子商务";
         return base;
	}
}
