package vn.gt.portlet.baocao.bchanghoa;

import java.util.HashMap;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;

public class BC11BTExport {

	

	public static JSONObject getModel(String maritimeCode, String fromDate,
			String toDate, String createDate, String reportPeriod,
			String reportMadeBy) throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray arrayResult = VmaItineraryLocalServiceUtil.getModelMau11BT(
				maritimeCode, fromDate, toDate);
		HashMap<String, Integer> hmItineraryNo = new HashMap<String, Integer>();
		for (int i = 0; i < arrayResult.length(); i++) {
			JSONObject obj = arrayResult.getJSONObject(i);
			hmItineraryNo.put(obj.getString("itineraryNo"), i);
		}
		JSONArray results = JSONFactoryUtil.createJSONArray();
		for (String str : hmItineraryNo.keySet()) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			for (int i = 0; i < arrayResult.length(); i++) {
				JSONObject re = arrayResult.getJSONObject(i);
				if (re.getString("itineraryNo").equals(str)) {
					obj.put("A1", re.getString("A1"));
					obj.put("A2", re.getString("A2"));
					obj.put("A3", re.getString("A3"));
					obj.put("A4", re.getString("A4"));
					obj.put("A5", re.getString("A5"));
					obj.put("A6", re.getString("A6"));
					obj.put("A7", re.getString("A7"));
					obj.put("A26", re.getString("A26"));
					obj.put("A27", re.getString("A27"));
					obj.put("A28", re.getString("A28"));
					obj.put("A29", re.getString("A29"));
					obj.put("A30", re.getString("A30"));
					obj.put("A31", re.getString("A31"));
					obj.put("A32", re.getString("A32"));
					obj.put("A33", re.getString("A33"));
					obj.put("A34", re.getString("A34"));
					JSONArray array = JSONFactoryUtil.createJSONArray();
					for (int j = 0; j < arrayResult.length(); j++) {
						JSONObject re2 = arrayResult.getJSONObject(j);
						if (re2.getString("itineraryNo").equals(str)) {
							JSONObject jsonArray = JSONFactoryUtil
									.createJSONObject();
							jsonArray.put("A8", re2.getString("A8"));
							jsonArray.put("A9", re2.getString("A9"));
							jsonArray.put("A10", re2.getString("A10"));
							jsonArray.put("A11", re2.getString("A11"));
							jsonArray.put("A12", re2.getString("A12"));
							jsonArray.put("A13", re2.getString("A13"));
							jsonArray.put("A14", re2.getString("A14"));
							jsonArray.put("A15", re2.getString("A15"));
							jsonArray.put("A16", re2.getString("A16"));
							jsonArray.put("A17", re2.getString("A17"));
							jsonArray.put("A18", re2.getString("A18"));
							jsonArray.put("A19", re2.getString("A19"));
							jsonArray.put("A20", re2.getString("A20"));
							jsonArray.put("A21", re2.getString("A21"));
							jsonArray.put("A22", re2.getString("A22"));
							jsonArray.put("A23", re2.getString("A23"));
							jsonArray.put("A24", re2.getString("A24"));
							jsonArray.put("A25", re2.getString("A25"));

							array.put(jsonArray);
						}
					}
					obj.put("Bang2", array);
					break;
				}
			}
			results.put(obj);
		}
		result.put("Bang1", results);
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
