/**
 *
 */
package vn.gt.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fds.flex.common.utility.string.StringPool;

import jakarta.servlet.http.HttpServletRequest;



/**
 * @author win_64
 */
public class ConvertUtil {
	
	public static int compareTwoDates(String fDate, String sDate, String dateFormat) throws ParseException {
		DateFormat df = new SimpleDateFormat(dateFormat);
		Date first = df.parse(fDate);
		Date second = df.parse(sDate);
		return compareTwoDates(first, second);
	}
	
	public static int compareTwoDates(Date fDate, Date sDate) {
		int dateDifference = 0;
		long firstDateToMillis = fDate.getTime();
		long secondDateToMillis = sDate.getTime();
		dateDifference = (int) ((firstDateToMillis - secondDateToMillis) / (24 * 60 * 60 * 1000));
		return dateDifference;
	}
	
	public static Date formatDate(Date date) {
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(date);
		startDate.set(java.util.Calendar.HOUR_OF_DAY, 0);
		startDate.set(java.util.Calendar.MINUTE, 0);
		startDate.set(java.util.Calendar.SECOND, 0);
		
		return startDate.getTime();
	}
	
	public static long daysBetween2Dates(Date date1, Date date2, int defaultValue) {
		try {
			return (date1.getTime() - date2.getTime()) / (24 * 3600 * 1000);
		} catch (Exception es) {
		}
		return defaultValue;
	}
	
	public static Date getDateWithSoNgay(Date date, int soNgay) {
		try {
			// return (date1.getTime() - date2.getTime()) / (24*3600*1000);
			new Date(date.getTime() + (soNgay * 24 * 3600 * 1000));
		} catch (Exception es) {
		}
		return null;
	}
	
	public static String getValueString(HttpServletRequest resHttpServletRequest, String key) {
		try {
			if (resHttpServletRequest.getAttribute(key) != null) {
				return resHttpServletRequest.getAttribute(key).toString().trim();
			}
		} catch (Exception es) {
		}
		return "";
	}
	
	public static long getValueLong(HttpServletRequest resHttpServletRequest, String key) {
		try {
			if (resHttpServletRequest.getAttribute(key) != null) {
				return FormatData.convertToLong(resHttpServletRequest.getAttribute(key).toString().trim());
			}
		} catch (Exception es) {
		}
		return 0;
	}
	
	public static boolean validationURL(String url) {
		try {
			String urlPattern = "^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";
			String urlPattern1 = "^www.[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";
			String urlPattern2 = "^[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";
			if (url.matches(urlPattern) || url.matches(urlPattern1) || url.matches(urlPattern2)) {
				return true;
			}
		} catch (Exception es) {
		}
		return false;
	}
	
	public static String formatString(String data) {
		String data1 = data.replaceAll("> ", ">");
		String data2 = data1.replaceAll(" <", "<");
		String data3 = data2.replaceAll("\n", "");
		String data4 = data3.replaceAll("\t", "");
		String data5 = data4.replaceAll("\"", "'");
		String data6 = data5.replaceAll("<p>", "");
		String data7 = data6.replaceAll("</p>", "");
		return data7;
	}
	
	public static String CheckRemainingTime(Date date1, Date date2) {
		try {
			long rangeTime = date2.getTime() - date1.getTime();
			int gio = Math.round(rangeTime / (1000 * 60 * 60));
			long phut = (rangeTime % (1000 * 60 * 60)) / (1000 * 60);
			if (phut < 10) {
				return (gio + ":0" + phut);
			} else if (phut >= 10) {
				return (gio + ":" + phut);
			}
		} catch (Exception e) {
		}
		return "";
	}
	
	public static String splitString(String data) {
		try {
			String[] strings = data.split("/");
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < (strings.length - 1); i++) {
				result.append(strings[i]);
			}
			return result.toString();
		} catch (Exception e) {
		}
		return "";
	}
	
	public static String getValueFromStringList(String data, String delimiter, int position) {
		try {
			String[] strings = data.split(delimiter);
			if (strings != null && strings.length > position && position >= 0) {
				return strings[position];
			}
			return StringPool.BLANK;
		} catch (Exception e) {
		}
		return StringPool.BLANK;
	}
	
	public static String capSoGiayPhep(String soGiayPhep) {
		String capSo = "";
		if (soGiayPhep != null && soGiayPhep.length() > 0) {
			switch (soGiayPhep.length()) {
				case 1:
					capSo = "000000000" + soGiayPhep;
					break;
				case 2:
					capSo = "00000000" + soGiayPhep;
					break;
				case 3:
					capSo = "0000000" + soGiayPhep;
					break;
				case 4:
					capSo = "000000" + soGiayPhep;
					break;
				case 5:
					capSo = "00000" + soGiayPhep;
					break;
				case 6:
					capSo = "0000" + soGiayPhep;
					break;
				case 7:
					capSo = "000" + soGiayPhep;
					break;
				case 8:
					capSo = "00" + soGiayPhep;
					break;
				case 9:
					capSo = "0" + soGiayPhep;
					break;
				case 10:
					capSo = "" + soGiayPhep;
					break;
				default:
					capSo = soGiayPhep;
					break;
			}
			return capSo;
		}
		return soGiayPhep;
	}
	public static long convertToLong(String value) {
		try {
			return Long.parseLong(value.trim());
		} catch (Exception es) {
		}

		return 0;
	}
	
	
	
}
