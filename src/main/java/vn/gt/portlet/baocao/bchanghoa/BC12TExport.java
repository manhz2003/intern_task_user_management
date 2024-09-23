package vn.gt.portlet.baocao.bchanghoa;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;

public class BC12TExport {
	
	private final static int SUM = 1, DIV = 2;

	public static JSONObject getModel(String maritimeCode, String fromDate,
			String toDate, String createDate, String reportPeriod,
			String reportMadeBy) throws SystemException, ParseException {

		Date _fromDate = FormatData.formatDDMMYYYY.parse(fromDate);
		Calendar fromCal = Calendar.getInstance();
		fromCal.setTime(_fromDate);
		int fromYear = fromCal.get(Calendar.YEAR);
		int fromMonth = fromCal.get(Calendar.MONTH) + 1;
		int fromDay = fromCal.get(Calendar.DAY_OF_MONTH);

		Date _toDate = FormatData.formatDDMMYYYY.parse(toDate);
		Calendar toCal = Calendar.getInstance();
		toCal.setTime(_toDate);
		int toYear = toCal.get(Calendar.YEAR);
		int toMonth = toCal.get(Calendar.MONTH) + 1;
		int toDay = toCal.get(Calendar.DAY_OF_MONTH);

		String dauNamDenThangTruoc_startDate = String.valueOf(fromYear)
				+ "-01-01";
		String thangDauNam = ((toMonth - 1) < 10 ? ("-0" + String
				.valueOf(toMonth - 1)) : "-" + String.valueOf(toMonth - 1));
		if (toMonth == 1) {
			thangDauNam = "-01";
		}
		String dauNamDenThangTruoc_endDate = String.valueOf(toYear)
				+ thangDauNam
				+ "-"
				+ BaoCaoBussinessUtils.countDayByMonthAndYear(toMonth == 1 ? 1
						: toMonth - 1, toYear);

		String thangBaoCao_startDate = String.valueOf(fromYear)
				+ ((fromMonth) < 10 ? ("-0" + String.valueOf(fromMonth)) : "-"
						+ String.valueOf(fromMonth)) + "-01";
		String thangBaoCao_endDate = String.valueOf(toYear)
				+ ((toMonth) < 10 ? ("-0" + String.valueOf(toMonth)) : "-"
						+ String.valueOf(toMonth))
				+ "-"
				+ String.valueOf(BaoCaoBussinessUtils.countDayByMonthAndYear(
						toMonth, toYear));

		String luyKeCungKyNamTruoc_startDate = String.valueOf(fromYear - 1)
				+ "-01-01";
		String luyKeCungKyNamTruoc_endDate = String.valueOf(toYear - 1)
				+ ((toMonth) < 10 ? ("-0" + String.valueOf(toMonth)) : "-"
						+ String.valueOf(toMonth))
				+ "-"
				+ String.valueOf(BaoCaoBussinessUtils.countDayByMonthAndYear(
						toMonth, toYear - 1));

		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONObject e = VmaItineraryLocalServiceUtil.getModelMau12T(
				maritimeCode, dauNamDenThangTruoc_startDate,
				dauNamDenThangTruoc_endDate, "E");
		JSONObject f = VmaItineraryLocalServiceUtil.getModelMau12T(
				maritimeCode, thangBaoCao_startDate, thangBaoCao_endDate, "F");
		JSONObject g = calculate(e, f, maritimeCode, SUM, "G", "E", "F");
		JSONObject h = VmaItineraryLocalServiceUtil.getModelMau12T(
				maritimeCode, luyKeCungKyNamTruoc_startDate,
				luyKeCungKyNamTruoc_endDate, "H");
		JSONObject i = calculate(g, h, maritimeCode, DIV, "I", "G", "H");
		JSONObject d = JSONFactoryUtil.createJSONObject();
		JSONObject k = JSONFactoryUtil.createJSONObject();
		result = BaoCaoBussinessUtils.mergeJson(d, e, f, g, h, i, k);
		result.put("reportMadeBy", reportMadeBy);
		result.put("reportPeriod", reportPeriod);
		result.put("fromDate", fromDate.replace(" 00:00", StringPool.BLANK));
		result.put("toDate", toDate.replace(" 00:00", StringPool.BLANK));
		result.put("reportMonth", fromMonth);
		result.put("reportYear", fromYear);
		result.put("maritimeNameVN", DmMaritimeLocalServiceUtil
				.getByMaritimeCode(maritimeCode).getMaritimeNameVN());
		result.put("signPlace",
				DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode)
						.getCityCode());
		result.put("signDate", createDate.replace(" 00:00", StringPool.BLANK));

		return result;
	}

	public static JSONObject calculate(JSONObject obj1, JSONObject obj2,
			String maritimeCode, int operation, String objName,
			String objName1, String objName2) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "1",
				objName2 + "1", operation) != -1) {
			result.put(objName + "1", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "1", objName2 + "1",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "2",
				objName2 + "2", operation) != -1) {
			result.put(objName + "2", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "2", objName2 + "2",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "3",
				objName2 + "3", operation) != -1) {
			result.put(objName + "3", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "3", objName2 + "3",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "4",
				objName2 + "4", operation) != -1) {
			result.put(objName + "4", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "4", objName2 + "4",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "5",
				objName2 + "5", operation) != -1) {
			result.put(objName + "5", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "5", objName2 + "5",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "6",
				objName2 + "6", operation) != -1) {
			result.put(objName + "6", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "6", objName2 + "6",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "7",
				objName2 + "7", operation) != -1) {
			result.put(objName + "7", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "7", objName2 + "7",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "8",
				objName2 + "8", operation) != -1) {
			result.put(objName + "8", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "8", objName2 + "8",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "9",
				objName2 + "9", operation) != -1) {
			result.put(objName + "9", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "9", objName2 + "9",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "10",
				objName2 + "10", operation) != -1) {
			result.put(objName + "10", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "10", objName2 + "10",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "11",
				objName2 + "11", operation) != -1) {
			result.put(objName + "11", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "11", objName2 + "11",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "12",
				objName2 + "12", operation) != -1) {
			result.put(objName + "12", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "12", objName2 + "12",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "13",
				objName2 + "13", operation) != -1) {
			result.put(objName + "13", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "13", objName2 + "13",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "14",
				objName2 + "14", operation) != -1) {
			result.put(objName + "14", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "14", objName2 + "14",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "15",
				objName2 + "15", operation) != -1) {
			result.put(objName + "15", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "15", objName2 + "15",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "16",
				objName2 + "16", operation) != -1) {
			result.put(objName + "16", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "16", objName2 + "16",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "17",
				objName2 + "17", operation) != -1) {
			result.put(objName + "17", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "17", objName2 + "17",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "18",
				objName2 + "18", operation) != -1) {
			result.put(objName + "18", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "18", objName2 + "18",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "19",
				objName2 + "19", operation) != -1) {
			result.put(objName + "19", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "19", objName2 + "19",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "20",
				objName2 + "20", operation) != -1) {
			result.put(objName + "20", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "20", objName2 + "20",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "21",
				objName2 + "21", operation) != -1) {
			result.put(objName + "21", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "21", objName2 + "21",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "22",
				objName2 + "22", operation) != -1) {
			result.put(objName + "22", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "22", objName2 + "22",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "23",
				objName2 + "23", operation) != -1) {
			result.put(objName + "23", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "23", objName2 + "23",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "24",
				objName2 + "24", operation) != -1) {
			result.put(objName + "24", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "24", objName2 + "24",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "25",
				objName2 + "25", operation) != -1) {
			result.put(objName + "25", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "25", objName2 + "25",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "26",
				objName2 + "26", operation) != -1) {
			result.put(objName + "26", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "26", objName2 + "26",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "27",
				objName2 + "27", operation) != -1) {
			result.put(objName + "27", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "27", objName2 + "27",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "28",
				objName2 + "28", operation) != -1) {
			result.put(objName + "28", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "28", objName2 + "28",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "29",
				objName2 + "29", operation) != -1) {
			result.put(objName + "29", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "29", objName2 + "29",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "30",
				objName2 + "30", operation) != -1) {
			result.put(objName + "30", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "30", objName2 + "30",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "31",
				objName2 + "31", operation) != -1) {
			result.put(objName + "31", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "31", objName2 + "31",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "32",
				objName2 + "32", operation) != -1) {
			result.put(objName + "32", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "32", objName2 + "32",
							operation)));
		}
		if (BaoCaoBussinessUtils.calculator(obj1, obj2, objName1 + "33",
				objName2 + "33", operation) != -1) {
			result.put(objName + "33", formatter.format(BaoCaoBussinessUtils
					.calculator(obj1, obj2, objName1 + "33", objName2 + "33",
							operation)));
		}

		return result;
	}
}
