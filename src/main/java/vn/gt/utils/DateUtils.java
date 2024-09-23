
package vn.gt.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fds.flex.common.ultility.Validator;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DateUtils
 {
	
	public static final String CURRENT_TIME_STAMP = new Date().getTime() + "";
	
	public static String dateToString(String format, Date date) { // lay ra ngay, thang, nam, gio, phu, giay hien tai
		String stringResult = "";
		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat(format);
			stringResult = sdf.format(date);
		} catch (Exception e) {
			stringResult = "";
		}
		return stringResult;
	}
	
	public static Date stringToDate(String sDate, String format) {
		Date result = null;
		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat(format);
			result = sdf.parse(sDate);
		} catch (Exception e) {
		}
		return result;
	}
	
	public static boolean isDate(String sDate, String format) {
		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat(format);
			sdf.parse(sDate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String getDateAfterNumberDay(int soNgayAfterCurrentDay) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, soNgayAfterCurrentDay);
			
			return dateToString("dd/MM/yyyy HH:mm", calendar.getTime());
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String getAfterNumberOfDayAgrument(Date dateAgrument, int soNgayAfter) {
		try {
			if (Validator.isNotNull(dateAgrument)) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dateAgrument);
				calendar.add(Calendar.DAY_OF_YEAR, soNgayAfter);
				return dateToString("dd/MM/yyyy HH:mm", calendar.getTime());
			}
			
		} catch (Exception e) {
			return "";
		}
		return "";
	}
	
	public static List<Entry<Integer,Date>> comparatorDateDesc (Map<Integer, Date> lstDate) {
		Comparator<Map.Entry<Integer, Date>> byMapValues = new Comparator<Map.Entry<Integer, Date>>() {
			@Override
			public int compare(Map.Entry<Integer, Date> left, Map.Entry<Integer, Date> right) {
				return right.getValue().compareTo(left.getValue());
			}
		};
		// create a list of map entries
		List<Map.Entry<Integer, Date>> candyBars = new ArrayList<Map.Entry<Integer, Date>>();
		// add all candy bars
		candyBars.addAll(lstDate.entrySet());
		// sort the collection
		Collections.sort(candyBars, byMapValues);
		return candyBars;
	}
	
}
