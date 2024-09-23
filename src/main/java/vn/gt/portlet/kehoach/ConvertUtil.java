/**
 *
 */
package vn.gt.portlet.kehoach;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author win_64
 *
 */
public class ConvertUtil {

	public static SimpleDateFormat formatDateShort = new SimpleDateFormat("dd/MM/yyyy");
	public static SimpleDateFormat formatDateShort2 = new SimpleDateFormat("yyyy/MM/dd");
	public static SimpleDateFormat formatDateFullTime = new SimpleDateFormat("HH:mm dd/MM/yyyy");
	public static SimpleDateFormat formatDateSendDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static Date parseStringToDateOfSendDate(String data) {
		try {
			return formatDateSendDate.parse(data);
		} catch (Exception es) {
		}
		return new Date();
	}

	public static String parseDateToString2(Date date) {
		try {
			return formatDateShort2.format(date);
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String parsefulldateToString(Date date) {
		try {
			return formatDateFullTime.format(date);
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String parseDateToString(Date date) {
		try {
			return formatDateShort.format(date);
		} catch (Exception es) {
		}
		return "";
	}

	public static Date parseStringToDate(String data) {
		try {
			return formatDateShort.parse(data);
		} catch (Exception es) {
		}
		return null;
	}

	/**
	 * Convert from String to Long
	 * @param value
	 * @return
	 */
	public static long convertToLong(String value) {
		try {
			return Long.parseLong(value.trim());
		} catch (Exception es) {
		}

		return 0;
	}

	/**
	 * Convert from String to Double
	 * @param value
	 * @return
	 */
	public static double convertToDouble(String value) {
		try {
			return Double.parseDouble(value.trim());
		} catch (Exception es) {
		}

		return 0;
	}

	/**
	 * Convert from String to Long
	 * @param value
	 * @return
	 */
	public static int convertToInt(String value) {
		try {
			return Integer.parseInt(value.trim());
		} catch (Exception es) {
		}

		return 0;
	}
	public static String checkSelectData(long number1, long number2) {
		  try {
		   if (number1 == number2) {
		    return "selected=\"selected\"";
		   }
		  } catch (Exception es) {
		  }
		  return "";
		 }
	
	public static String checkSelectData(long number1, String number2) {
		try {
			if ((number2 != null) && (number2.trim().length() > 0)) {
				if (number1 == Long.parseLong(number2.trim())) {
					return "selected=\"selected\"";
				}
			}

		} catch (Exception es) {
		}
		return "";
	}
	public static String checkEqualData(String value1, String value2) {
		  try {
		   if (value1.equalsIgnoreCase(value2)) {
		    return "selected=\"selected\"";
		   }
		  } catch (Exception es) {
		  }
		  return "";
	}
	
	public static long getDefferenBetweenTwoDate(Date startDate, Date endDate) {
		  try {
			  Calendar c1 = Calendar.getInstance();
			  Calendar c2 = Calendar.getInstance();
			  
			  c1.set(Calendar.MONTH, startDate.getMonth() + 1);
		      c1.set(Calendar.YEAR, startDate.getYear()) ;
		      c1.set(Calendar.DAY_OF_MONTH, startDate.getDate()) ;
		        
		      c2.set(Calendar.MONTH, endDate.getMonth() + 1);
		      c2.set(Calendar.YEAR, endDate.getYear()) ;
		      c2.set(Calendar.DAY_OF_MONTH, endDate.getDate());
		      
		      long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
		      if (noDay > 0) {
		    	  return noDay;
		      }
		  } catch (Exception es) {
		  }
		  return 0;
	}
}
