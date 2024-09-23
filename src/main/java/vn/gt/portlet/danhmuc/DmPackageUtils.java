package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmPackage;
import vn.gt.dao.danhmuc.service.DmPackageLocalServiceUtil;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmPackageUtils {

	public static JSONObject getDetailPackage(String packageCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmPackage dmPackage = DmPackageLocalServiceUtil
				.getByPackageCode(packageCode);
		result.put("id", dmPackage.getId());
		result.put("packageCode", dmPackage.getPackageCode());
		result.put("packageNameVN", dmPackage.getPackageNameVN());
		result.put("modifiedDate", dmPackage.getModifiedDate());
		result.put("isDelete", dmPackage.getIsDelete());
		return result;
	}

	public static JSONObject getPackgages(String packageCode,
			String packageNameVN, String isDelete, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;

		List<DmPackage> dmPackages = DmPackageLocalServiceUtil.findPackages(
				packageCode, packageNameVN, isDelete, start, end);
		total = DmPackageLocalServiceUtil.countPackages(packageCode,
				packageNameVN, isDelete);
		for (DmPackage dmPackage : dmPackages) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			obj.put("id", dmPackage.getId());
			obj.put("code", dmPackage.getPackageCode());
			obj.put("name", dmPackage.getPackageNameVN());
			obj.put("isDelete", dmPackage.getIsDelete());
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}
}
