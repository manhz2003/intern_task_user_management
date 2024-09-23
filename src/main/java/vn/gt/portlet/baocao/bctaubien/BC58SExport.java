package vn.gt.portlet.baocao.bctaubien;

import java.util.HashMap;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;

public class BC58SExport {

	

	public static JSONObject getModel(String maritimeCode, String fromDate,
			String toDate, String createDate, String reportPeriod,
			String reportMadeBy) throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray arrayResult = DmPortWharfLocalServiceUtil.getModelMau58S(
				maritimeCode, fromDate, toDate);
		HashMap<String, Integer> portRegionCode = new HashMap<String, Integer>();
		for (int i = 0; i < arrayResult.length(); i++) {
			JSONObject obj = arrayResult.getJSONObject(i);
			if (obj.getString("portRegionCode") != null) {
				portRegionCode.put(obj.getString("portRegionCode"), i);
			}
		}
		JSONArray results = JSONFactoryUtil.createJSONArray();
		for (String str : portRegionCode.keySet()) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			JSONArray array = JSONFactoryUtil.createJSONArray();
			for (int i = 0; i < arrayResult.length(); i++) {
				JSONObject re = arrayResult.getJSONObject(i);
				if (re.getString("portRegionCode").equals(str)) {
					JSONObject jsonArray = JSONFactoryUtil.createJSONObject();
					jsonArray.put("B", re.getString("B"));
					jsonArray.put("C", re.getString("C"));
					jsonArray.put("D", re.getString("D"));
					jsonArray.put("E", re.getString("E"));
					jsonArray.put("F", re.getString("F"));
					jsonArray.put("G", re.getString("G"));
					jsonArray.put("H", re.getString("H"));
					jsonArray.put("I", re.getString("I"));
					jsonArray.put("K", re.getString("K"));
					jsonArray.put("L", re.getString("L"));

					array.put(jsonArray);
				}
				if (!re.getString("A").equals(StringPool.BLANK)) {
					obj.put("A", re.getString("A"));
				}
				obj.put("Bang2", array);
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
