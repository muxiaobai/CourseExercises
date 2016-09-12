package Campus;

import java.util.ArrayList;
import java.util.List;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author zhang
 * @Date 2016年9月5日 上午9:50:44
 * @doing
 */

public class RunJavaScript {
	public static List<Campus> run(String data){
		List<Campus> list=new ArrayList<>();
		String ret=getJSONData(data);
		if(ret!=null&&!"".equals(ret)){
			list .addAll(JSONObject.parseArray(getJSONData(data), Campus.class));
		}
		return list;
	}
	public static String getJSONData(String data){
		ScriptEngineManager sem = new ScriptEngineManager(); 
		ScriptEngine se = sem.getEngineByName("javascript"); 
		String callbackvalue="";
		try {
			 Invocable invocableEngine = (Invocable) se; 
				se.eval(""
						+ "function onecampus(data){"
						+ "var campus= {};"
						+ "campus['title']=data.title||data.title_jd;"
						+ "campus['specialtips']=data.specialtips;"
						+ "campus['officialname']=data.officialname;"
						+ "campus['description']=data.description||data.description_jd;"
						+ "campus['city']=data.recruitcity;"
						+ "campus['loc']=data.joblink||data.loc||data.applyUrl;"
						+ "campus['source']=data.source;"
						+ "return campus;"
						+ "}"
						+ ""
						+ "function circle(data){"
						+ "var arr=[];"
						+ "for(var i=0;i<data.length;i++){"
						+ "arr[i]=onecampus(data[i]);"
						+ "}"
						+ "return arr;"
						+ "}"
						+ ""
						+ "function getCampus(data) {"
						+ "var jsondata=JSON.parse(data);"
						+ "if(jsondata.data.data.disp_data==null){"
						+ "return null;"
						+ "}" 
						+ "var arr=circle(jsondata.data.data.disp_data);"
						+ " return JSON.stringify(arr);" 
						+ "}"); 
				 callbackvalue=(String) invocableEngine.invokeFunction("getCampus",data);
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} 
		return callbackvalue;
	}
	public static void main(String[] args) {
		System.out.println(run(CampusJSON.loadJson(Test.getUrl(30))));
	}
}
