package vn.gt.portlet.baocao.bctaubien;

import java.text.ParseException;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.flex.common.utility.string.StringPool;

public class BC65TExport {
	
	public static JSONObject getModel(String maritimeCode, String fromDate,
			String toDate, String createDate, String reportPeriod,
			String reportMadeBy) throws SystemException, ParseException {

		JSONObject results = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();
		array = VmaItineraryLocalServiceUtil.getModelMau65T(maritimeCode,
				fromDate, toDate);
		int tong = 0;
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			if (obj.has("luot_tau_bien")) {
				tong += obj.getInt("luot_tau_bien");
			}
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("A", i + 1);
			result.put("B", obj.getString("stateName"));
			result.put("C", obj.getInt("luot_tau_bien_vao"));
			result.put("D", obj.getInt("luot_tau_bien_ra"));
			result.put("E", obj.getInt("luot_tau_bien"));
			if (tong > 0) {
				double temp1 = Double.valueOf(obj.getString("luot_tau_bien"));
				result.put(
						"F",
						obj.has("luot_tau_bien") ? (double) Math
								.round((temp1 / tong) * 1000) / 1000 : null);
			}
			result.put("G", obj.getDouble("gt_tau_bien_vao"));
			result.put("H", obj.getDouble("gt_tau_bien_ra"));
			result.put("I", obj.getDouble("gt_tau_bien"));
			result.put("K", obj.getDouble("dwt_tau_bien_vao"));
			result.put("L", obj.getDouble("dwt_tau_bien_ra"));
			result.put("M", obj.getDouble("dwt_tau_bien"));

			jsonArray.put(result);
		}
		results.put("Bang1", jsonArray);

		results.put("reportMadeBy", reportMadeBy);
		results.put("reportPeriod", reportPeriod);
		try {
			results.put("fromDate", FormatData
					.parseDateToTringDDMMYYY(FormatData.formatDateShort
							.parse(fromDate)));
			results.put("toDate", FormatData
					.parseDateToTringDDMMYYY(FormatData.formatDateShort
							.parse(toDate)));
		} catch (Exception e) {
			// nothing to do
		}
		results.put("reportMonth", fromDate.split(" ")[0].split("-")[1]);
		results.put("reportYear", fromDate.split(" ")[0].split("-")[0]);
		results.put("maritimeNameVN", DmMaritimeLocalServiceUtil
				.getByMaritimeCode(maritimeCode).getMaritimeNameVN());
		results.put("signPlace",
				DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode)
						.getCityCode());
		results.put("signDate", createDate.replace(" 00:00", StringPool.BLANK));

		return results;
	}
}
