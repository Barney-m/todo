package com.ks.todo.core.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * Date Util
 * 
 * @author Ken Shen
 * @version 1.0
 */
public final class DateUtil {

	private static final String DATE_FORMAT = PropertyUtil.getDefaultDateFormat();

	private static final String DATE_TIME_FORMAT = PropertyUtil.getDefaultDateTimeFormat();

	public static final FastDateFormat DEFAULT_TIMESTAMP_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd hh:mm:ss");

	public static final FastDateFormat DDMMMYY_FMT = FastDateFormat.getInstance("ddMMMyy");
	public static final FastDateFormat DD_MMM_YYYY_FMT = FastDateFormat.getInstance("dd MMM yyyy");
	public static final FastDateFormat DD_MMM_YYYY_HH_MM_AA_FMT = FastDateFormat.getInstance("dd MMM yyyy hh:mm aa");

	public static final FastDateFormat YYYYMMDDHHMMSS_FMT = FastDateFormat.getInstance("yyyyMMddHHmmss");

	public static final FastDateFormat YYYY_MM_DD_FMT = FastDateFormat.getInstance("yyyy-MM-dd");

	public static final FastDateFormat YYYYMMDD_FMT = FastDateFormat.getInstance("yyyyMMdd");

	public static final FastDateFormat YYYYDDMM_FMT = FastDateFormat.getInstance("yyyyddMM");

	public static final FastDateFormat HHMMSSSSS_FMT = FastDateFormat.getInstance("HHmmssSSS");

	public static final FastDateFormat HHMMSS_FMT = FastDateFormat.getInstance("HHmmss");

	public static final FastDateFormat HHMM_FMT = FastDateFormat.getInstance("HHmm");

	private static final FastDateFormat DEFAULT_DATE_FORMATTER = FastDateFormat.getInstance(DATE_FORMAT);

	private static final FastDateFormat DEFAULT_DATE_TIME_FORMATTER = FastDateFormat.getInstance(DATE_TIME_FORMAT);

	private static final int MONTH_OF_YEAR = 12;

	private static final int DAY_OF_WEEK = 7;

	private static final int LEAP_YEAR = 4;

	private static final Date DATE_HIGHEST_THRESHOLD;

	private static final Date DATE_LOWEST_THRESHOLD;

	private DateUtil() {
	}

	static {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2999);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		trimTime(cal);
		DATE_HIGHEST_THRESHOLD = new Date(cal.getTime().getTime());

		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1900);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		trimTime(cal);
		DATE_LOWEST_THRESHOLD = new Date(cal.getTime().getTime());
	}

	public static Calendar trimTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static Date trimTime(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		trimTime(cal);
		return cal.getTime();
	}

	public static Date getHighestThreshold() {
		return DATE_HIGHEST_THRESHOLD;
	}

	public static Date getLowestThreshold() {
		return DATE_LOWEST_THRESHOLD;
	}

	public static Date getCurrentDateTime() {
		return new Date();
	}

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(getCurrentDateTime().getTime());
	}

	/**
	 * Get x day before now
	 * 
	 * @param x - How many day before
	 * @return {@link Date}
	 */
	public static Date getBeforeDay(int x) {
		Calendar cal = Calendar.getInstance();
		trimTime(cal);
		cal.add(Calendar.DATE, -x);

		return new Date(cal.getTime().getTime());
	}

	/**
	 * Get x month before now
	 * 
	 * @param x - How many month before
	 * @return {@link Date}
	 */
	public static Date getBeforeMonth(int x) {
		Calendar cal = Calendar.getInstance();
		trimTime(cal);
		cal.add(Calendar.MONTH, -x);

		return new Date(cal.getTime().getTime());
	}

	/**
	 * Get x year before now
	 * 
	 * @param x - How many year before
	 * @return {@link Date}
	 */
	public static Date getBeforeYear(int x) {
		Calendar cal = Calendar.getInstance();
		trimTime(cal);
		cal.add(Calendar.YEAR, -x);

		return new Date(cal.getTime().getTime());
	}

	public static Date parseDate(String d) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(d);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String parseSqlDate(Date d) {
		return YYYY_MM_DD_FMT.format(d);
	}

	public static String toDateTimeFormat(Date date, FastDateFormat fdf) {
		if (date == null) {
			return null;
		}

		return fdf.format(date);
	}

	public static boolean isAfter(Date date1, Date date2) {
		return isAfter(date1, date2, false);
	}

	public static boolean isAfter(Date date1, Date date2, boolean includeTime) {

		long i;
		if (includeTime) {
			i = date1.getTime() - date2.getTime();
		} else {
			i = trimTime(date1).getTime() - trimTime(date2).getTime();
		}

		return i > 0;
	}

	public static boolean isEqualORAfter(Date date1, Date date2) {
		return isEqualORAfter(date1, date2, false);
	}

	public static boolean isEqualORAfter(Date date1, Date date2, boolean includeTime) {

		long i;
		if (includeTime) {
			i = date1.getTime() - date2.getTime();
		} else {
			i = trimTime(date1).getTime() - trimTime(date2).getTime();
		}

		return i >= 0;
	}

	public static boolean isEqual(Date date1, Date date2) {
		return isEqual(date1, date2, false);
	}

	public static boolean isEqual(Date date1, Date date2, boolean includeTime) {
		if (date1 == null && date2 == null) {
			return true;
		}

		if ((date1 != null && date2 == null) || (date1 == null && date2 != null)) {
			return false;
		}

		long i;
		if (includeTime) {
			i = date1.getTime() - date2.getTime();
		} else {
			i = trimTime(date1).getTime() - trimTime(date2).getTime();
		}

		return i >= 0;
	}

	public static boolean isEqualORBefore(Date date1, Date date2) {
		return isEqualORBefore(date1, date2, false);
	}

	public static boolean isEqualORBefore(Date date1, Date date2, boolean includeTime) {

		long i;
		if (includeTime) {
			i = date1.getTime() - date2.getTime();
		} else {
			i = trimTime(date1).getTime() - trimTime(date2).getTime();
		}

		return i <= 0;
	}

	public static boolean isBefore(Date date1, Date date2) {
		return isBefore(date1, date2, false);
	}

	public static boolean isBefore(Date date1, Date date2, boolean includeTime) {
		if (date1 == null && date2 == null) {
			return true;
		}

		if ((date1 != null && date2 == null) || (date1 == null && date2 != null)) {
			return false;
		}

		long i;
		if (includeTime) {
			i = date1.getTime() - date2.getTime();
		} else {
			i = trimTime(date1).getTime() - trimTime(date2).getTime();
		}

		return i < 0;
	}
}
