package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmUnitGeneral;
import vn.gt.dao.danhmuc.service.DmUnitGeneralLocalServiceUtil;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmUnitGeneralUtils {

	public static JSONObject getUnitGenerals(String unitName, String isDelete,
			String unitCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;

		List<DmUnitGeneral> dmUnitGenerals = DmUnitGeneralLocalServiceUtil
				.findUnitGenerals(unitName, isDelete, unitCodeGroup, start, end);
		total = DmUnitGeneralLocalServiceUtil.countUnitGenerals(unitName,
				isDelete, unitCodeGroup);

		for (DmUnitGeneral dmUnitGeneral : dmUnitGenerals) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", dmUnitGeneral.getId());
			obj.put("unitCode", dmUnitGeneral.getUnitCode());
			obj.put("unitName", dmUnitGeneral.getUnitName());
			obj.put("isDelete", dmUnitGeneral.getIsDelete());
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailUnitGeneral(String unitCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmUnitGeneral dmUnitGeneral = DmUnitGeneralLocalServiceUtil
				.getByUnitCode(unitCode);
		result.put("id", dmUnitGeneral.getId());
		result.put("unitCode", dmUnitGeneral.getUnitCode());
		result.put("unitName", dmUnitGeneral.getUnitName());
		result.put("modifiedDate", dmUnitGeneral.getModifiedDate());
		result.put("isDelete", dmUnitGeneral.getIsDelete());
		return result;
	}
}
