package com.dowjones.data.source.sequencer.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

	public static long getEndOfDayTime(Date date) {
	    return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE), -1).getTime();
	}

	public static long getStartOfDayTime(Date date) {
	    return DateUtils.truncate(date, Calendar.DATE).getTime();
	}
	
	public static Date getEndOfDay(Date date) {
	    return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE), -1);
	}

	public static Date getStartOfDay(Date date) {
	    return DateUtils.truncate(date, Calendar.DATE);
	}
	
	public static String formatDateFromDateTime(long date){
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	    return sdf.format(date);
	}
}
