package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmSecurityLevel;
import vn.gt.dao.danhmuc.service.DmSecurityLevelLocalServiceUtil;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmSecurityLevelUtils {

	public static JSONObject getSecurityLevels(int start, int end)
			throws SystemException {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;

		List<DmSecurityLevel> dmSecurityLevels = DmSecurityLevelLocalServiceUtil
				.getDmSecurityLevels(start, end);
		total = dmSecurityLevels.size();
		for (DmSecurityLevel dmSecurityLevel : dmSecurityLevels) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", dmSecurityLevel.getId());
			obj.put("secutiryLevel", dmSecurityLevel.getSecurityLevel());
			obj.put("securityLevelCode", dmSecurityLevel.getSecurityLevelCode());
			obj.put("securityLevelName", dmSecurityLevel.getSecurityLevelName());
			obj.put("isDelete", dmSecurityLevel.getIsDelete());
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailSecurityLevel(String securityLevelCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmSecurityLevel dmSecurityLevel = DmSecurityLevelLocalServiceUtil
				.getBySecurityLevelCode(securityLevelCode);
		result.put("id", dmSecurityLevel.getId());
		result.put("secutiryLevel", dmSecurityLevel.getSecurityLevel());
		result.put("securityLevelCode", dmSecurityLevel.getSecurityLevelCode());
		result.put("securityLevelName", dmSecurityLevel.getSecurityLevelName());
		result.put("modifiedDate", dmSecurityLevel.getModifiedDate());
		result.put("isDelete", dmSecurityLevel.getIsDelete());
		return result;
	}
}
