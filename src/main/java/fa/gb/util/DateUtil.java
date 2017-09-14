package fa.gb.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

	public static Date addDays(Date date, int days) {
		return new Date(date.getTime() + TimeUnit.DAYS.toMillis(days));
	}

	public static Date removeDays(Date date, int days) {
		return new Date(date.getTime() - TimeUnit.DAYS.toMillis(days));
	}

	public static boolean checkIfDayBefore(Date date, Date dateToCheck) {
		if(DateUtil.removeDays(date, 1).equals(dateToCheck)) {
			return true;
		}

		return false;
	}

	public static boolean checkIfDayAfter(Date date, Date dateToCheck) {
		if(DateUtil.addDays(date, 1).equals(dateToCheck)) {
			return true;
		}

		return false;
	}

	public static int getDaysDifference(Date startDate, Date endDate) {
		long diff = endDate.getTime() - startDate.getTime();
		return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public static Date getCurrentUTCDate() {
		Date date = new Date();
		
		return date;
	}
	
	public static long getCurrentUTCTime() {
		Date date = getCurrentUTCDate();
		
		return date.getTime();
	}
	
	public static long getCurrentUTCTime(Date date) {
		return date.getTime();
	}
	
	public static java.sql.Timestamp convertUTCTimeToSQLTimestamp(long time) {
		return new java.sql.Timestamp(time);
	}
	
}
