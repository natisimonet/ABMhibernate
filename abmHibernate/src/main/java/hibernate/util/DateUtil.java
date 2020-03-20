package hibernate.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	
	public static String PATTERN_Y4_M2_D2 = "yyyy/MM/dd";
	public static String PATTERN_Y4_M2_D2_H2_M2_S2 = "yyyy/MM/dd HH:mm:ss"; //este no se usa
	
	public static String format(String pattern, Date date) { // no se usa
		SimpleDateFormat sdfDMY = new SimpleDateFormat (pattern);
		return sdfDMY.format(date);
	}
	
	public static Date parse(String pattern, String date) throws ParseException {
		SimpleDateFormat sdfYMD = new SimpleDateFormat (pattern);
		
		return sdfYMD.parse(date);
		
}
}
