package vn.gt.portlet.baocao.bctaubien;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;

@Slf4j
public class BC13TExport {
	
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
		JSONObject e = VmaItineraryLocalServiceUtil.getModelMau13T(
				maritimeCode, dauNamDenThangTruoc_startDate,
				dauNamDenThangTruoc_endDate, "E");
		JSONObject f = VmaItineraryLocalServiceUtil.getModelMau13T(
				maritimeCode, thangBaoCao_startDate, thangBaoCao_endDate, "F");
		JSONObject g = calculate(e, f, maritimeCode, SUM, "G", "E", "F");
		JSONObject h = VmaItineraryLocalServiceUtil.getModelMau13T(
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

		log.info("==> " + result);

		return result;
	}

	public static JSONObject calculate(JSONObject obj1, JSONObject obj2,
			String maritimeCode, int operation, String objName,
			String objName1, String objName2) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			result.put(
					objName + "1",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "1"),
							obj2.getInt(objName2 + "1"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "1"),
									obj2.getInt(objName2 + "1"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "2",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "2"),
							obj2.getInt(objName2 + "2"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "2"),
									obj2.getInt(objName2 + "2"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "3",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "3"),
							obj2.getInt(objName2 + "3"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "3"),
									obj2.getInt(objName2 + "3"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "4",
					BaoCaoBussinessUtils.calculator(
							obj1.getDouble(objName1 + "4"),
							obj2.getDouble(objName2 + "4"), operation) >= 0 ? formatter
							.format(BaoCaoBussinessUtils.calculator(
									obj1.getDouble(objName1 + "4"),
									obj2.getDouble(objName2 + "4"), operation))
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "5",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "5"),
							obj2.getInt(objName2 + "5"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "5"),
									obj2.getInt(objName2 + "5"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "6",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "6"),
							obj2.getInt(objName2 + "6"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "6"),
									obj2.getInt(objName2 + "6"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "7",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "7"),
							obj2.getInt(objName2 + "7"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "7"),
									obj2.getInt(objName2 + "7"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "8",
					BaoCaoBussinessUtils.calculator(
							obj1.getDouble(objName1 + "8"),
							obj2.getDouble(objName2 + "8"), operation) >= 0 ? formatter
							.format(BaoCaoBussinessUtils.calculator(
									obj1.getDouble(objName1 + "8"),
									obj2.getDouble(objName2 + "8"), operation))
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "9",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "9"),
							obj2.getInt(objName2 + "9"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "9"),
									obj2.getInt(objName2 + "9"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "10",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "10"),
							obj2.getInt(objName2 + "10"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "10"),
									obj2.getInt(objName2 + "10"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "11",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "11"),
							obj2.getInt(objName + "11"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "11"),
									obj2.getInt(objName + "11"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "12",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "12"),
							obj2.getInt(objName2 + "12"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "12"),
									obj2.getInt(objName2 + "12"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "13",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "13"),
							obj2.getInt(objName2 + "13"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "13"),
									obj2.getInt(objName2 + "13"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "14",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "14"),
							obj2.getInt(objName2 + "14"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "14"),
									obj2.getInt(objName2 + "14"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "15",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "15"),
							obj2.getInt(objName2 + "15"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "15"),
									obj2.getInt(objName2 + "15"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "16",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "16"),
							obj2.getInt(objName2 + "16"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "16"),
									obj2.getInt(objName2 + "16"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "17",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "17"),
							obj2.getInt(objName2 + "17"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "17"),
									obj2.getInt(objName2 + "17"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}
		try {
			result.put(
					objName + "18",
					BaoCaoBussinessUtils.calculator(
							obj1.getInt(objName1 + "18"),
							obj2.getInt(objName2 + "18"), operation) >= 0 ? BaoCaoBussinessUtils
							.calculator(obj1.getInt(objName1 + "18"),
									obj2.getInt(objName2 + "18"), operation)
							: null);
		} catch (Exception e) {
			// nothing to do
		}

		return result;
	}
}
