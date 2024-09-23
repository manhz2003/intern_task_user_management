package vn.gt.portlet.baocao.bchanghoa;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.flex.common.utility.string.StringPool;

public class MauBC15Export {

	public static JSONObject getModel(String maritimeCode,
			String portHarbourCode, String fromDate, String toDate,
			String createDate, String reportPeriod, String reportMadeBy)
			throws SystemException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray bang1 = JSONFactoryUtil.createJSONArray();
		JSONArray bang2 = JSONFactoryUtil.createJSONArray();
		JSONArray bang3 = JSONFactoryUtil.createJSONArray();
		JSONArray bang4 = JSONFactoryUtil.createJSONArray();

		try {
			bang1 = VmaItineraryLocalServiceUtil.getModelMauBC15T(maritimeCode,
					"9", "12", "3", portHarbourCode, fromDate, toDate);
		} catch (Exception e) {

		}
		try {
			bang2 = VmaItineraryLocalServiceUtil.getModelMauBC15T(maritimeCode,
					"9", StringPool.BLANK, StringPool.BLANK, portHarbourCode,
					fromDate, toDate);
		} catch (Exception e) {

		}
		try {
			bang2 = VmaItineraryLocalServiceUtil.getModelMauBC15T(maritimeCode,
					StringPool.BLANK, "12", StringPool.BLANK, portHarbourCode,
					fromDate, toDate);
		} catch (Exception e) {

		}
		try {
			bang4 = VmaItineraryLocalServiceUtil.getModelMauBC15T(maritimeCode,
					StringPool.BLANK, StringPool.BLANK, "3", portHarbourCode,
					fromDate, toDate);
		} catch (Exception e) {

		}
		result.put("Bang1", bang1);
		result.put("Bang2", bang2);
		result.put("Bang3", bang3);
		result.put("Bang4", bang4);

		result.put("reportMadeBy", reportMadeBy);
		result.put("reportPeriod", reportPeriod);
		try {
			result.put("fromDate", FormatData
					.parseDateToTringDDMMYYY(FormatData.formatDateShort
							.parse(fromDate)));
			result.put("toDate", FormatData
					.parseDateToTringDDMMYYY(FormatData.formatDateShort
							.parse(toDate)));
		} catch (Exception e) {
			// nothing to do
		}
		result.put("reportMonth", fromDate.split(" ")[0].split("-")[1]);
		result.put("reportYear", fromDate.split(" ")[0].split("-")[0]);
		result.put("maritimeNameVN", DmMaritimeLocalServiceUtil
				.getByMaritimeCode(maritimeCode).getMaritimeNameVN());
		result.put("signPlace",
				DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode)
						.getCityCode());
		result.put("signDate", createDate.replace(" 00:00", StringPool.BLANK));

		return result;
	}
}
