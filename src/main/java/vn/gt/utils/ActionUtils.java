package vn.gt.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.fds.nsw.liferay.core.ActionRequest;

import jakarta.servlet.http.HttpServletRequest;
import javax.xml.datatype.XMLGregorianCalendar;

import vn.gt.config.ConfigUtils;
import vn.gt.tichhop.message.MessageType;



import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.liferay.core.LanguageUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.UploadPortletRequest;
@Slf4j
public class ActionUtils
 {

	public static final String REGISTRATION_ACTION = "action";
	public static final String REGISTRATION_ACTION_GO_BACK = "back";
	public static final String REGISTRATION_GO_BUSINESS_COOPERATION = "registration.go_business.cooperation";
	public static final String REGISTRATION_BUSINESS_COOPERATION = "registration.business.cooperation";

	//Danh Sach Giao Dich
	public static final String VIEW_REGISTRATION_BUSINESS = "brms.registration";
	public static final String VIEW_TRANSACTION = "brms.transaction";
	public static final String VIEW_TRANSACTION_DETAILS = "brms.transaction.details";
	public static final String VIEW_DOCUMENT = "brms.document";
	public static final String LEVEL_CONTENT = "3";

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	// public static String REGISTER_CITIZEN_ID = ConfigUtils.getValue("egov.application.brms.profile.citizen.id", "Cong Dan");
	// public static String REGISTER_COMPANY_ID = ConfigUtils.getValue("egov.application.brms.profile.company.id", "Doanh Nghiep");
	// public static String REGISTER_FAMILY_ID = ConfigUtils.getValue(
	
	public static String NumberFormat(int value) {
		try {
			DecimalFormat format = new DecimalFormat("#,###");
			return format.format("NumberFormat");
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String NumberFormat1(String value) {
		try {
			DecimalFormat format = new DecimalFormat("#,###");
			return format.format("");
		} catch (Exception es) {
		}
		return "";
	}
	
	public static void NumberFM(String[] args) {
		DecimalFormat format = new DecimalFormat("#,###");
		System.out.println(format.format(1000000000));
	}
	
	// //////////////////////////////////////////////////////////
	public static long convertToLong(String value) {
		try {
			return Long.parseLong(value.trim());
		} catch (Exception es) {
		}
		
		return 0;
	}
	
	public static int convertToInt(String value) {
		try {
			return Integer.parseInt(value.trim());
		} catch (Exception es) {
		}
		
		return 0;
	}
	
	public static double convertToDouble(String value) {
		try {
			return Double.parseDouble(value.trim());
		} catch (Exception es) {
		}
		
		return 0;
	}
	
	public static Date parseStringToDate(String data) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormat.parse(data);
		} catch (Exception es) {
		}
		return null;
	}
	
	public static String parseDateToTring(Date date) {
		try {
			return formatDateShort.format(date);
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String parseXMLGregorianCalendarToTring(XMLGregorianCalendar date) {
		try {
			return formatDateShort.format(date.toGregorianCalendar().getTime());
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String checkData(Long number1, Long number2) {
		String out = StringPool.BLANK;
		if (number1 != null && number2 != null && number1.equals(number2)) {
			out = "selected";
		}
		return out;
	}
	
	public static String checkData(int number1, long number2) {
		try {
			if (number1 == number2) {
				return "selected=\"selected\"";
			}
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String checkData(int number1, int number2) {
		try {
			if (number1 == number2) {
				return "selected=\"selected\"";
			}
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String checkData(String number1, String number2) {
		try {
			if (null != number1 && null != number2)
				if (number1.equals(number2)) {
					return "selected=\"selected\"";
				}
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String checkDataLong(long number1, int number2) {
		try {
			if (number1 == number2) {
				return "selected=\"selected\"";
			}
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String checkDataForRadio(long number1, long number2) {
		try {
			if (number1 == number2) {
				return "checked=\"checked\"";
			}
		} catch (Exception es) {
		}
		return "";
	}
	
	public static boolean checkDataForRadio2(long number1, long number2) {
		try {
			if (number1 == number2) {
				return true;
			}
		} catch (Exception es) {
		}
		return false;
	}
	
	public static String checkDataToDisable(long number1, long number2) {
		try {
			if (number1 != number2) {
				return "disabled=\"disabled\"";
			}
		} catch (Exception es) {
		}
		return "";
	}
	
	public static String checkDataHTX(long number1, long number2) {
		try {
			if (number1 == number2) {
				return "selected";
			}
		} catch (Exception es) {
		}
		return "";
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
				return convertToLong(resHttpServletRequest.getAttribute(key).toString().trim());
			}
		} catch (Exception es) {
		}
		
		return 0;
	}
	
	public static List<String> getNganhNgheKinhDoanh(String listNganhNgheKDId) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(listNganhNgheKDId, ",");
		while (st.hasMoreElements()) {
			String value = st.nextElement().toString();
			// System.out.println("value   "+value);
			list.add(value);
		}
		return list;
		
	}
	
	public static String getTitle(SimpleDateFormat format) {
		Date date = new Date();
		return format.format(date);
	}
	
	public static String getDateTitle(SimpleDateFormat format, Date date) {
		
		return format.format(date);
	}
	
	public static boolean checkIdNganhNgheKD(Long id, List<String> list) {
		boolean rerulst = false;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String value = list.get(i);
				
				if (value.equals(id.toString())) {
					
					rerulst = true;
					break;
				}
			}
		}
		return rerulst;
	}
	
	// BinhNT Add 2013-03-13
	public static String checkColspanColumn(String value, int typeCheck) {
		if (typeCheck == 1) {
			// Check voi colspan
			if (value.length() > 0) {
				return "colspan=\"2\"";
			}
		} else {
			// Check display
			if (value.length() > 0) {
				return "none";
			}
		}
		
		return "";
	}
	
	public static boolean checkIdNganhNgheKD(Long id, String listIdNganhNghe) {
		
		boolean result = false;
		
		if (listIdNganhNghe != null && listIdNganhNghe.length() > 0) {
			
			String[] list = listIdNganhNghe.split(",");
			
			for (String item : list) {
				
				if (item.equalsIgnoreCase(id + "")) {
					result = true;
					break;
				}
			}
			
			// result = listIdNganhNghe.contains(id.toString());
		}
		
		return result;
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
	
	private static SimpleDateFormat formatDateShort = new SimpleDateFormat("dd/MM/yyyy");
	
	public static boolean checkIfIsLong(String input) {
		
		if (input.contains(".") || input.contains(",")) {
			return false;
		}
		
		try {
			Long.parseLong(input);
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean checkIfIsDouble(String input) {
		
		try {
			Double.parseDouble(input.replace(",", "."));
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	public static int compareTwoDates(String fDate, String sDate, String dateFormat) throws ParseException {
		
		int dateDifference = 0;
		
		DateFormat df = new SimpleDateFormat(dateFormat);
		
		Date first = df.parse(fDate);
		Date second = df.parse(sDate);
		
		long firstDateToMillis = first.getTime();
		long secondDateToMillis = second.getTime();
		
		dateDifference = (int) ((firstDateToMillis - secondDateToMillis) / (24 * 60 * 60 * 1000));
		
		return dateDifference;
	}
	
	public static String getToday() {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		return dateFormat.format(new Date());
	}
	
	public static boolean chuoiChuaSo(String chuoi) {
		char[] tmp = chuoi.toCharArray();
		for (int i = 0; i < tmp.length; i++) {
			if (Character.isDigit(tmp[i]))
				return true;
		}
		return false;
	}
	
	public static boolean checkIfContainsFloatingPoint(String input) {
		
		if (input.contains(".") || input.contains(",")) {
			return true;
		}
		
		return false;
	}
	
	public static boolean checkIfIsFloat(String input) {
		
		try {
			Float.parseFloat(input.replace(",", "."));
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean checkIfIsAValidEmail(String email) {
		
		// Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		//
		// Matcher matcher = pattern.matcher(email);
		
		// return matcher.matches();
		return true;
	}
	
	public static int convertCheckBoxValueToBooleanInt(String input) {
		
		try {
			
			if (input.equalsIgnoreCase("on")) {
				
				return 1;
			}
			
		} catch (Exception e) {
		}
		
		return 0;
	}
	
	public static String convertCheckBoxValueToBooleanString(String input) {
		
		try {
			
			if (input.equalsIgnoreCase("on")) {
				
				return "1";
			}
			
		} catch (Exception e) {
		}
		
		return "0";
	}
	
	public static String convertBooleanIntToText(int value) {
		
		if (value == 1) {
			return ConfigUtils.getValue("vn.dtt.hms.co");
		} else {
			return ConfigUtils.getValue("vn.dtt.hms.khong");
		}
	}
	
	public static String checkCheckBox(int status) {
		
		if (status == 1) {
			return " checked=\"checked\" ";
		} else {
			return "";
		}
	}
	
	public static int convertFromCheckBoxValueArrayToInt(String[] values) {
		
		int result = 0;
		
		if (values != null && values.length > 0) {
			
			result = Integer.parseInt(values.toString(), 2);
		}
		
		return result;
	}
	
	public static int[] convertFromStringToIntArray(String input) {
		
		int[] results = null;
		
		if (input != null && input.length() > 0) {
			
			char[] digits = input.toCharArray();
			
			results = new int[digits.length];
			
			for (int i = 0; i < digits.length; i++) {
				
				results[i] = Integer.parseInt(digits[i] + "");
			}
		}
		
		return results;
	}
	
	
	//TODO Tam thoi bo validate de chay phuc vu test
	public static boolean checkIfIsAValidSpecialCharacters(String inputString) {
		/**
		 * (chứa /, \, ?, :, ", <, >, |, ~, !, @, #, $, %, ^, &, *)
		 * 
		 * */
		
		char[] kitu = inputString.toCharArray();
//		for (int i = 0; i < kitu.length; i++) {
//			if ((kitu[i] > 32 && kitu[i] < 39) ||
//				(kitu[i] == 42) ||
//				(kitu[i] == 47) ||
//				(kitu[i] == 58) ||
//				(kitu[i] == 60) ||
//				(kitu[i] > 61 && kitu[i] < 65) ||
//				(kitu[i] == 92) ||
//				(kitu[i] == 94) ||
//				(kitu[i] == 124) ||
//				(kitu[i] == 126)) {
//				return false;
//			}
//		}
		//String splChrs = "/, \, ?, :, ", <, >, |, ~, !, @, #, $, %, ^, &, *" ;
		
		
//		int slash = 47;//Symbol: /
//		int backslash = 92;//Symbol: \
//		int questionMark = 63;//Symbol: ?
//		int colon = 58;//Sybol: :
//		int doubleQuotes = 34;//Symbol: "
//		int lessThanSign = 60;//Symbol: <
//		int greaterThanSign = 62;//Symbol: >
//		int verticalBar = 124;//Symbol: |
//		int equivalencySignTilde = 126;//Symbol: ~
//		int exclamationPoint = 33;//Symbol: !
//		int atSymbol = 64;//Symbol: @
//		int numberSign = 35;//Symbol: #
//		int dollarSign = 36;//Symbol: $
//		int percentSign = 37;//Symbol: %
//		int caretCircumflex = 94;//Symbol: ^
//		int ampersand = 38;//Symbol: &
//		int asterisk = 42;//Symbol: *
//		boolean result = true;
//		for (int i = 0; i < kitu.length; i++) {
//			int iKitu = kitu[i];
//			if (iKitu == slash) { result = false;
//			} else if (iKitu == backslash) { result = false;
//			} else if (iKitu == questionMark) { result = false;
//			} else if (iKitu == colon) { result = false;
//			} else if (iKitu == doubleQuotes) { result = false;
//			} else if (iKitu == lessThanSign) { result = false;
//			} else if (iKitu == greaterThanSign) { result = false;
//			} else if (iKitu == verticalBar) { result = false;
//			} else if (iKitu == equivalencySignTilde) { result = false;
//			} else if (iKitu == exclamationPoint) { result = false;
//			} else if (iKitu == atSymbol) { result = false;
//			} else if (iKitu == numberSign) { result = false;
//			} else if (iKitu == dollarSign) { result = false;
//			} else if (iKitu == percentSign) { result = false;
//			} else if (iKitu == caretCircumflex) { result = false;
//			} else if (iKitu == ampersand) { result = false;
//			} else if (iKitu == asterisk) { result = false;
//			} else {
//				result = true;
//			}
//			
//			if (result == false) { return false; }
//		}
		
		return true;
	}
	
	public static boolean checkFormatInterger(String result, int number) {
		if (result.length() <= number) {
			return true;
		}
		return false;
	}
	
	public static boolean checkFormatDouble(String result, int before, int after) {
		String[] results = result.trim().split("\\.");
		if (results.length == 1) {
			if (results[0].length() <= before) {
				return true;
			}
		} else if (results.length == 2) {
			if (results[0].length() <= before && results[1].length() <= after) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkLength(String result, int number) {
		if (result.length() <= number) {
			return true;
		}
		return false;
	}
	
//	String fromDate = ParamUtil.getString(resourceRequest, "formDate").trim();
//	if (CommonUtils.checkFromDate(resourceRequest, fromDate)) { return; }
	//TODO not using
	/*
	public static boolean checkFromDate(ActionRequest actionRequest, String fromDate) {
		String FROM_DATE = "FROM_DATE";
		if (fromDate != null && fromDate.trim().length() > 0) {
			PortletSession portletSession = actionRequest.getPortletSession();
			Object oldFromDate = portletSession.getAttribute("FROM_DATE");
			if (oldFromDate != null) {
				if (fromDate.trim().compareToIgnoreCase(oldFromDate.toString().trim()) == 0) { return true; }
			}
			portletSession.setAttribute(FROM_DATE, fromDate.trim());
		}
		
		return false;
	}
	*/
	public static String checkTitle(String messageType, String loaiThuTuc) {
		if (loaiThuTuc.trim().length() == 0 || loaiThuTuc.compareTo(MessageType.LOAT_THU_TUC_NHAP_CANH) == 0) {
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_AN_NINH_TAU_BIEN)) == 0) {
				return "vn.dtt.kehoach.bankhaianninh";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_HANG_HOA)) == 0) {
				return "vn.dtt.kehoach.bankhaihanghoa";
			}
			if (messageType.compareTo(String.valueOf(MessageType.THONG_BAO_TAU_DEN_CANG)) == 0) {
				return "vn.dtt.kehoach.thongbao";
			}
			if (messageType.compareTo(String.valueOf(MessageType.XAC_BAO_TAU_DEN_CANG)) == 0) {
				return "vn.dtt.kehoach.xacbao";
			}
			if (messageType.compareTo(String.valueOf(MessageType.LENH_DIEU_DONG)) == 0) {
				return "vn.dtt.kehoach.lenhdieudong";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_CHUNG)) == 0) {
				return "vn.dtt.kehoach.bankhaichung";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN)) == 0) {
				return "vn.dtt.kehoach.danhsachthuyenvien";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH)) == 0) {
				return "vn.dtt.kehoach.danhsachhanhkhach";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_DANH_SACH_HANG_HOA_NGUY_HIEM)) == 0) {
				return "vn.dtt.kehoach.hanghoanguyhiem";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_DU_TRU_CUA_TAU)) == 0) {
				return "vn.dtt.kehoach.bankhaidutru";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_HANH_LY_THUYEN_VIEN)) == 0) {
				return "vn.dtt.kehoach.hanhlythuyenvien";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_BAO_Y_TE_HANG_HAI)) == 0) {
				return "vn.dtt.kehoach.khaibaoytehanghai";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_KIEM_DICH_THUC_VAT)) == 0) {
				return "vn.dtt.kehoach.kiemdichthucvat";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_KIEM_DICH_DONG_VAT)) == 0) {
				return "vn.dtt.kehoach.kiemdichdongvat";
			}
		} else if (loaiThuTuc.compareTo(MessageType.LOAT_THU_TUC_XUAT_CANH) == 0) {
			if (messageType.compareTo(String.valueOf(MessageType.THONG_BAO_TAU_ROI_CANG)) == 0) {
				return "vn.dtt.kehoach.thongbao";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_CHUNG)) == 0) {
				return "vn.dtt.kehoach.bankhaichung";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN)) == 0) {
				return "vn.dtt.kehoach.danhsachthuyenvien";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH)) == 0) {
				return "vn.dtt.kehoach.danhsachhanhkhach";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_HANG_HOA)) == 0) {
				return "vn.dtt.kehoach.bankhaihanghoa";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_DU_TRU_CUA_TAU)) == 0) {
				return "vn.dtt.kehoach.bankhaidutru";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_HANH_LY_HANH_KHACH_ROI_TAU)) == 0) {
				return "vn.dtt.kehoach.hanhlyhanhkhachroitau";
			}
			if (messageType.compareTo(String.valueOf(MessageType.FUNCTION_CAC_GIAY_TO_PHAI_XUAT_TRINH)) == 0) {
				return "vn.dtt.kehoach.cacgiaytoxuattrinh";
			}
			if (messageType.compareTo(String.valueOf(MessageType.GIAY_PHEP_ROI_CANG_BO_GTVT_CAP_KHI_XUAT_CANH)) == 0) {
				return "vn.dtt.kehoach.giaypheproicang";
			}
		} else if (loaiThuTuc.compareTo(MessageType.LOAT_THU_TUC_QUA_CANH) == 0) {
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_AN_NINH_TAU_BIEN)) == 0) {
				return "vn.dtt.kehoach.bankhaianninh";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_HANG_HOA)) == 0) {
				return "vn.dtt.kehoach.bankhaihanghoa";
			}
			if (messageType.compareTo(String.valueOf(MessageType.THONG_BAO_TAU_QUA_CANH)) == 0) {
				return "vn.dtt.kehoach.thongbao";
			}
			if (messageType.compareTo(String.valueOf(MessageType.XAC_BAO_TAU_QUA_CANH)) == 0) {
				return "vn.dtt.kehoach.xacbao";
			}
			if (messageType.compareTo(String.valueOf(MessageType.LENH_DIEU_DONG)) == 0) {
				return "vn.dtt.kehoach.lenhdieudong";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_CHUNG)) == 0) {
				return "vn.dtt.kehoach.bankhaichung";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_DANH_SACH_THUYEN_VIEN)) == 0) {
				return "vn.dtt.kehoach.danhsachthuyenvien";
			}
			if (messageType.compareTo(String.valueOf(MessageType.BAN_KHAI_DANH_SACH_HANH_KHACH)) == 0) {
				return "vn.dtt.kehoach.danhsachhanhkhach";
			}
			
		}
		
		return "";
	}
}
