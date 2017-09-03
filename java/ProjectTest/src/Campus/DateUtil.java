package Campus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * 
 * @author zhang
 * @Date  2016年7月9日 上午10:17:50
 * @doing 
 */

public class DateUtil {

	 public static int daysBetween(Date smdate,Date bdate) throws ParseException    
	    {    
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        smdate=sdf.parse(sdf.format(smdate));  
	        bdate =sdf.parse(sdf.format(bdate)); 
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(smdate);    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(bdate);    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       return Integer.parseInt(String.valueOf(between_days));           
	    }    
	      
		/** 
		*字符串的日期格式的计算 
		*/  
	    public static int daysBetween(String smdate,String bdate) throws ParseException{  
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(sdf.parse(smdate));    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(sdf.parse(bdate));    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       return Integer.parseInt(String.valueOf(between_days));     
	    }  
	    /**
	     * 将Date格式化String
	     * @param format
	     * @param date
	     * @return
	     */
	    public static String formatDate(String format, Date date) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
	    /**
	     * 将String格式化Date
	     * @param format
	     * @param date
	     * @return
	     */
		public static Date formatString(String format, String date) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		public static Map<String, List<String>> getMapDate(String deadline){
			Map<String, List<String>> maps = new HashMap<>();
			Date date = new Date();
		//	String deadline = "20140321";
			String now = DateUtil.formatDate("yyyyMM", date);
			String temp = now;
			GregorianCalendar gc =new GregorianCalendar();
			
			while (Integer.parseInt(temp.substring(0, 4)) - Integer.parseInt(deadline.substring(0, 4)) >= 0){
				
				//整年+最近一年
				if(Integer.parseInt(temp.substring(0, 4)) - Integer.parseInt(deadline.substring(0, 4)) > 0){
					String tempyear = temp.substring(0, 4);
					List<String> lists = new ArrayList<>();
					while (Integer.parseInt(temp.substring(4, 6)) > 1){
						String tempyearmonth = temp.substring(0, 6);
						lists.add(tempyearmonth);
						gc.setTime(DateUtil.formatString("yyyyMM", temp));
						gc.add(2, -1);
						//System.out.println(DateUtil.formatDate("yyyyMMdd", gc.getTime()));
						temp = DateUtil.formatDate("yyyyMM", gc.getTime());
						if(Integer.parseInt(temp.substring(4, 6))==1){
							lists.add(temp.substring(0, 6));
						}
					//	System.out.println(Integer.parseInt(temp.substring(4, 6)));
					}
					maps.put(tempyear, lists);
				}
				
				//最后一年
				if(Integer.parseInt(temp.substring(0, 4)) - Integer.parseInt(deadline.substring(0, 4)) == 0){
					String tempyear = temp.substring(0, 4);
					List<String> lists = new ArrayList<>();
					while (Integer.parseInt(temp.substring(4, 6)) -Integer.parseInt(deadline.substring(4, 6))  > 0){
						String tempyearmonth = temp.substring(0, 6);
						//System.out.println(Integer.parseInt(temp.substring(4, 6)));
						lists.add(tempyearmonth);
						gc.setTime(DateUtil.formatString("yyyyMM", temp));
						gc.add(2, -1);
						//System.out.println(DateUtil.formatDate("yyyyMMdd", gc.getTime()));
						temp = DateUtil.formatDate("yyyyMM", gc.getTime());
						if(Integer.parseInt(temp.substring(4, 6))==1){
							lists.add(temp.substring(0, 6));
						}
						maps.put(tempyear, lists);
					}
				}  
				gc.setTime(DateUtil.formatString("yyyyMM", temp));
				gc.add(1, -1);
				gc.add(2, +11);
				temp = DateUtil.formatDate("yyyyMM", gc.getTime());
				//System.out.println(temp);
			}
			return maps;
		}
}
