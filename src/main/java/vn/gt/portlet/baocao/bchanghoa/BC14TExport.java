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

public class BC14TExport {
	
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
		JSONObject d = VmaItineraryLocalServiceUtil.getModelMau14T(
				maritimeCode, dauNamDenThangTruoc_startDate,
				dauNamDenThangTruoc_endDate, "D");
		JSONObject e = VmaItineraryLocalServiceUtil.getModelMau14T(
				maritimeCode, thangBaoCao_startDate, thangBaoCao_endDate, "E");
		JSONObject f = calculate(d, e, maritimeCode, SUM, "F", "D", "E");
		JSONObject g = VmaItineraryLocalServiceUtil.getModelMau14T(
				maritimeCode, luyKeCungKyNamTruoc_startDate,
				luyKeCungKyNamTruoc_endDate, "G");
		JSONObject h = calculate(f, g, maritimeCode, DIV, "H", "F", "G");

		result = BaoCaoBussinessUtils.mergeJson(d, e, f, g, h);

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

		return result;
	}
}
