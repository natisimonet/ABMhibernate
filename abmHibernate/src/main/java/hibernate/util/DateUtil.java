package hibernate.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	
	public static String PATTERN_D2_M2_Y4 = "yyyy/MM/dd";
	public static String PATTERN_D2_M2_Y4_H2_M2_S2 = "dd/MM/yyyy HH:mm:ss";
	
	public static String format(String pattern, Date date) {
		SimpleDateFormat sdfDMY = new SimpleDateFormat (pattern);
		return sdfDMY.format(date);
	}
	
	public static Date parse(String pattern, String date) throws ParseException {
		SimpleDateFormat sdfYMD = new SimpleDateFormat (pattern);
		
		return sdfYMD.parse(date);
		
}
}
