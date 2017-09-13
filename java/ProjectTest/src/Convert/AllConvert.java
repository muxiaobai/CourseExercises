package Convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
public class AllConvert {
	public static String Int2String(int i){
		return String.valueOf(i);
	}
	public static int String2Int(String str){
		return   Integer.parseInt(str);
	}
	public static Date String2Date(String format,String str) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	public static String Date2String(String format,Date date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	public static Date Long2Date(long l){
		return  new Date(l);
	}
	public static void main(String[] args) throws ParseException {
		Calendar mydate = new GregorianCalendar();
		String mystring = "02-02-2010";
		Date thedate = new SimpleDateFormat("MM-dd-yyyy").parse(mystring);
		mydate.setTime(thedate);
		//breakdown
		System.out.println("mydate -> "+mydate);
		System.out.println("year   -> "+mydate.get(Calendar.YEAR));
		System.out.println("month  -> "+mydate.get(Calendar.MONTH));
		System.out.println("dom    -> "+mydate.get(Calendar.DAY_OF_MONTH));
		System.out.println("dow    -> "+mydate.get(Calendar.DAY_OF_WEEK));
		System.out.println("hour   -> "+mydate.get(Calendar.HOUR));
		System.out.println("minute -> "+mydate.get(Calendar.MINUTE));
		System.out.println("second -> "+mydate.get(Calendar.SECOND));
		System.out.println("milli  -> "+mydate.get(Calendar.MILLISECOND));
		System.out.println("ampm   -> "+mydate.get(Calendar.AM_PM));
		System.out.println("hod    -> "+mydate.get(Calendar.HOUR_OF_DAY));
		System.out.println(AllConvert.Date2String("yyyy-MM-dd HH:mm:ss",new Date()));
		System.out.println(AllConvert.Long2Date(System.currentTimeMillis()));
		System.out.println(AllConvert.String2Date("yyyy-MM-dd", "2015-02-14"));
	}

}
