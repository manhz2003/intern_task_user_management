/**
 * 
 */
package vn.gt.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import vn.gt.utils.config.ConfigurationManager;
/**
 * @author win_64
 */
public class FormatData {

	public static SimpleDateFormat formatDateShortZone = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	public static SimpleDateFormat formatDateShort = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat formatDateShort2 = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat formatDateShort3 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	public static SimpleDateFormat formatDateShort4 = new SimpleDateFormat("HH:mm dd/MM/yyyy");
	public static SimpleDateFormat formatDateShortSMS1 = new SimpleDateFormat("HH'h'mm-dd/MM/yyyy");
	public static SimpleDateFormat formatDateShortSMS2 = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
	public static SimpleDateFormat formatDateShortSMS3 = new SimpleDateFormat("HH:mm dd/MM/yyyy");
	public static SimpleDateFormat formatDD = new SimpleDateFormat("dd");
	public static SimpleDateFormat formatMM = new SimpleDateFormat("MM");
	public static SimpleDateFormat formatYYYY = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat formatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
	public static SimpleDateFormat formatDDMMYYY2 = new SimpleDateFormat("dd-MM-yyyy");
	
	public static String parseLongToString(long value) {
		try {
			return String.valueOf(value);
		} catch (Exception es) {
			return null;
		}
	}
	
	public static String parseIntToString(int value) {
		try {
			return String.valueOf(value);
		} catch (Exception es) {
			return null;
		}
	}
	
	public static String getDayOfMonth(Date date) {
		try {
			return formatDD.format(date);
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String getMonth(Date date) {
		try {
			return formatMM.format(date);
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String getYear(Date date) {
		try {
			return formatYYYY.format(date);
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String getToday() {
		try {
			return formatDDMMYYYY.format(new Date());
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String parseDateToTringDDMMYYY(Date date) {
		try {
			return formatDDMMYYYY.format(date);
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String parseDateToTring(Date date) {
		try {
			return formatDateShort.format(date);
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String parseDateToTringType3(Date date) {
		try {
			return formatDateShort3.format(date);
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String parseDateToTringType4(Date date) {
		try {
			return formatDateShort4.format(date);
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String parseDateToStringType5(Date date) {
		try {
			return formatDDMMYYY2.format(date);
		} catch (Exception e) {
		}
		return "";
	}
	
	public static String parseDateToStringType6(Date date) {
		try {
			return formatDateShort2.format(date);
		} catch (Exception e) {
		}
		return "";
	}
	
	public static XMLGregorianCalendar convertFromDateToXMLGregorianCalendar(Date date) {
		XMLGregorianCalendar dateReturn = null;
		try {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(date);
			dateReturn = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (Exception es) {
			// es.printStackTrace();
			try {
				dateReturn = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		return dateReturn;
	}
	
	public static Date parseStringToDate(String date) {
		try {
			if (date == null || date.length() == 0) {
				return null;
			}
			return formatDateShort.parse(date.trim());
		} catch (Exception es) {
			try {
				return formatDateShort2.parse(date.trim());
			} catch (Exception e1) {
				try {
					return formatDateShort3.parse(date.trim());
				} catch (Exception e2) {
					try {
						return formatDateShort4.parse(date.trim());
					} catch (Exception e3) {
						try {
							return formatDateShortSMS1.parse(date.trim());
						} catch (Exception e4) {
							try {
								return formatDateShortSMS2.parse(date.trim());
							} catch (Exception e5) {
								try {
									return formatDateShortSMS3.parse(date.trim());
								} catch (Exception e) {
									try {
										return formatDateShortZone.parse(date.trim());
									} catch (Exception e6) {
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
		}
		return new Date();
	}
	
	public static Date parseStringToDateZone(String date) {
		try {
			if (date == null || date.length() == 0) {
				return null;
			}
			return formatDateShortZone.parse(date.trim());
		} catch (Exception es) {
			return null;
		}
	}
	
	public static Date parseDateShort3StringToDate(String date) {
		try {
			return formatDateShort3.parse(date);
		} catch (Exception e) {
		}
		return new Date();
	}
	
	/**
	 * Convert from String to Long
	 * 
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
	 * 
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
	 * 
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
	
	public static boolean isThisDateValid(String dateToValidate) {
		
		if (dateToValidate == null) {
			return false;
		}
		formatDateShort.setLenient(false);
		try {
			// if not valid, it will throw ParseException
			formatDateShort.parse(dateToValidate.trim());
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static String formatIntToString(int data) {
		try {
			if (data < 10) {
				return "0" + data;
			}
		} catch (Exception es) {
		}
		
		return String.valueOf(data);
	}
	
	public static String formatDoubleToString(double data) {
		try {
			DecimalFormat df = new DecimalFormat("#.00");
			
			return df.format(data);
		} catch (Exception e) {
			
		}

		return String.valueOf(data);
	}
	
	public static String checkData(long number1, long number2) {
		try {
			if (number1 == number2) {
				return "selected=\"selected\"";
			}
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String checkedData(String dmCode, String value) {
		try {
			if (dmCode.equals(value)) {
				return "selected=\"selected\"";
			}
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String congNgay(Date date) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			return formatDateShort3.format(calendar.getTime());
		} catch (Exception e) {
		}
		return "";
	}
	
	public static Date subTractDate(Date dateInstance, int numberDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateInstance);
		cal.add(Calendar.DATE, -numberDay);
		Date dateBefore30Days = cal.getTime();
		return dateBefore30Days;
	}
	
	public static String formatNumberForRead(double number) {
        NumberFormat nf = NumberFormat.getInstance();
        String temp = nf.format(number);
        String strReturn = "";
        int slen = temp.length();
        for (int i = 0; i < slen; i++) {
            if (String.valueOf(temp.charAt(i)).equals("."))
                break;
            else if (Character.isDigit(temp.charAt(i))) {
                strReturn += String.valueOf(temp.charAt(i));
            }
        }
        return strReturn;

    }
    public static String numberToString(double number) {
        String sNumber = formatNumberForRead(number);
        // Tao mot bien tra ve
        String sReturn = "";
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Lat nguoc chuoi nay lai
        String sNumber1 = "";
        for (int i = iLen - 1; i >= 0; i--) {
            sNumber1 += sNumber.charAt(i);
        }
        // Tao mot vong lap de doc so
        // Tao mot bien nho vi tri cua sNumber
        int iRe = 0;
        do {
            // Tao mot bien cat tam
            String sCut = "";
            if (iLen > 3) {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + 3);
                sReturn = Read(sCut, iRe) + sReturn;
                iRe++;
                iLen -= 3;
            } else {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + iLen);
                sReturn = Read(sCut, iRe) + sReturn;
                break;
            }
        } while (true);
        if(sReturn.length() > 1){
            sReturn = sReturn.substring(0,1).toUpperCase() + sReturn.substring(1);
        }
        
        //sReturn = sReturn + ConfigurationManager.getStrProp("numberToString.dong.chan", "dong chan.");
        return sReturn;
    }

    // Khoi tao ham Read
    public static String Read(String sNumber, int iPo) {
        // Tao mot bien tra ve
        String sReturn = "";
        // Tao mot bien so
        String sPo[] = { "", ConfigurationManager.getStrProp("numberToString.ngan", "ngan") + " ",
        		ConfigurationManager.getStrProp("numberToString.trieu", "trieu") + " ", ConfigurationManager.getStrProp("numberToString.ty", "ty") + " " };
        String sSo[] = { ConfigurationManager.getStrProp("numberToString.0", "khong") + " ", ConfigurationManager.getStrProp("numberToString.1", "mot") + " ",
        		ConfigurationManager.getStrProp("numberToString.2", "hai") + " ", ConfigurationManager.getStrProp("numberToString.3", "ba") + " ",
        		ConfigurationManager.getStrProp("numberToString.4", "bon") + " ", ConfigurationManager.getStrProp("numberToString.5", "nam") + " ",
        		ConfigurationManager.getStrProp("numberToString.6", "sau") + " ", ConfigurationManager.getStrProp("numberToString.7", "bay") + " ",
        		ConfigurationManager.getStrProp("numberToString.8", "tam") + " ", ConfigurationManager.getStrProp("numberToString.9", "chin") + " " };
        String sDonvi[] = { "", ConfigurationManager.getStrProp("numberToString.muoi", "muoi") + " ",
        		ConfigurationManager.getStrProp("numberToString.tram", "tram") + " " };
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Tao mot bien nho vi tri doc
        int iRe = 0;
        // Tao mot vong lap de doc so
        for (int i = 0; i < iLen; i++) {
            String sTemp = "" + sNumber.charAt(i);
            int iTemp = Integer.parseInt(sTemp);
            // Tao mot bien ket qua
            String sRead = "";
            // Kiem tra xem so nhan vao co = 0 hay ko
            if (iTemp == 0) {
                switch (iRe) {
                case 0:
                    break;// Khong lam gi ca
                case 1: {
                    if (Integer.parseInt("" + sNumber.charAt(0)) != 0) {
                        sRead = ConfigurationManager.getStrProp("numberToString.le", "le") + " ";
                    }
                    break;
                }
                case 2: {
                    if (Integer.parseInt("" + sNumber.charAt(0)) != 0
                            && Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                        sRead = ConfigurationManager.getStrProp("numberToString.khong.tram", "khong tram") + " ";
                    }
                    break;
                }

    	        
                }
            } else if (iTemp == 1) {
                switch (iRe) {
                case 1:
                    sRead = ConfigurationManager.getStrProp("numberToString.10", "muoi") + " ";
                    break;
                default:
                    sRead = ConfigurationManager.getStrProp("numberToString.1", "mot") + " " + sDonvi[iRe];
                    break;
                }
            } else if (iTemp == 5) {
                switch (iRe) {
                case 0: {
                    if(sNumber.length() <= 1){
                        sRead = ConfigurationManager.getStrProp("numberToString.nam", "nam") + " ";
                    }
                    else if (Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                        sRead = ConfigurationManager.getStrProp("numberToString.lam", "lam") + " ";
                    } else
                        sRead = ConfigurationManager.getStrProp("numberToString.nam", "nam") + " ";
                    break;
                }
                default:
                    sRead = sSo[iTemp] + sDonvi[iRe];
                }
            } else {
                sRead = sSo[iTemp] + sDonvi[iRe];
            }

            sReturn = sRead + sReturn;
            iRe++;
        }
        if (sReturn.length() > 0) {
            sReturn += sPo[iPo];
        }

        return sReturn;
    }

    public static String getRomanNumerals(int Int) {
        LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
        roman_numerals.put("M", 1000);
        roman_numerals.put("CM", 900);
        roman_numerals.put("D", 500);
        roman_numerals.put("CD", 400);
        roman_numerals.put("C", 100);
        roman_numerals.put("XC", 90);
        roman_numerals.put("L", 50);
        roman_numerals.put("XL", 40);
        roman_numerals.put("X", 10);
        roman_numerals.put("IX", 9);
        roman_numerals.put("V", 5);
        roman_numerals.put("IV", 4);
        roman_numerals.put("I", 1);
        String res = "";
        for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
          int matches = Int/entry.getValue();
          res += repeat(entry.getKey(), matches);
          Int = Int % entry.getValue();
        }
        return res;
  }
  public static String repeat(String s, int n) {
    if(s == null) {
        return null;
    }
    final StringBuilder sb = new StringBuilder();
    for(int i = 0; i < n; i++) {
        sb.append(s);
    }
    return sb.toString();
  }
  
//	public static void main(String[] abc123) {
//		try {
//			DecimalFormat df = new DecimalFormat("#.00");
//			double x = Double.MAX_VALUE;
//			Double y = Double.valueOf("9999999999999999999.00");
//			//df.setRoundingMode(RoundingMode.DOWN);
//			System.out.println(df.format(x));
//			System.out.println(df.format(y));
//			System.out.println(y);
//			//11111111455955485000
//			//11111111111111110000
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
