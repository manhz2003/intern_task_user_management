package vn.gt.portlet.baocao.bchanghoa;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.baocao.bctaubien.BC13TExport;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;

public class BC16TExport {
	

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

		String thangBaoCao_startDate = String.valueOf(fromYear)
				+ ((fromMonth) < 10 ? ("-0" + String.valueOf(fromMonth)) : "-"
						+ String.valueOf(fromMonth)) + "-01";
		String thangBaoCao_endDate = String.valueOf(toYear)
				+ ((toMonth) < 10 ? ("-0" + String.valueOf(toMonth)) : "-"
						+ String.valueOf(toMonth))
				+ "-"
				+ String.valueOf(BaoCaoBussinessUtils.countDayByMonthAndYear(
						toMonth, toYear));

		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONObject c = VmaItineraryLocalServiceUtil.getModelMau16T(
				maritimeCode, thangBaoCao_startDate, thangBaoCao_endDate, "C");
		JSONObject d = VmaItineraryLocalServiceUtil.getModelMau16T(
				maritimeCode, String.valueOf(fromYear) + "-01-01",
				thangBaoCao_endDate, "D");

		result = BaoCaoBussinessUtils.mergeJson(c, d);
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
}
