package Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zhang
 * @Date 2016年7月20日 下午2:53:45
 * @doing
 */

public class DateTest {
	public static void main(String[] args) {
		Map<String, List<String>> maps = new HashMap<>();
		Date date = new Date();
		String deadline = "20140321";
		String now = DateTest.formatDate("yyyyMM", date);
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
					gc.setTime(DateTest.formatString("yyyyMM", temp));
					gc.add(2, -1);
					//System.out.println(DateTest.formatDate("yyyyMMdd", gc.getTime()));
					temp = DateTest.formatDate("yyyyMM", gc.getTime());
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
					System.out.println(Integer.parseInt(temp.substring(4, 6)));
					lists.add(tempyearmonth);
					gc.setTime(DateTest.formatString("yyyyMM", temp));
					gc.add(2, -1);
					//System.out.println(DateTest.formatDate("yyyyMMdd", gc.getTime()));
					temp = DateTest.formatDate("yyyyMM", gc.getTime());
					if(Integer.parseInt(temp.substring(4, 6))==1){
						lists.add(temp.substring(0, 6));
					}
					maps.put(tempyear, lists);
				}
			}  
			gc.setTime(DateTest.formatString("yyyyMM", temp));
			gc.add(1, -1);
			gc.add(2, +11);
			temp = DateTest.formatDate("yyyyMM", gc.getTime());
			//System.out.println(temp);
		}
		System.out.println(maps);
	}

	public static String formatDate(String format, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

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
}
