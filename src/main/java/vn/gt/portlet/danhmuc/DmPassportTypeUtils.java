package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmPassportType;
import vn.gt.dao.danhmuc.service.DmPassportTypeLocalServiceUtil;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmPassportTypeUtils {


	public static JSONObject getPassPorts(String passportTypeNameVN,
			String isDelete, String passportTypeCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;

		List<DmPassportType> dmPassportTypes = DmPassportTypeLocalServiceUtil
				.findPassportTypes(passportTypeNameVN, isDelete,
						passportTypeCodeGroup, start, end);
		total = DmPassportTypeLocalServiceUtil
				.countPassportTypes(passportTypeNameVN, isDelete,
						passportTypeCodeGroup, start, end);

		for (DmPassportType dmPassportType : dmPassportTypes) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", dmPassportType.getId());
			obj.put("passportType", dmPassportType.getPassportType());
			obj.put("passportTypeCode", dmPassportType.getPassportTypeCode());
			obj.put("passportTypeNameVN",
					dmPassportType.getPassportTypeNameVN());
			obj.put("isDelete", dmPassportType.getIsDelete());

			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailPassportType(String passportTypeCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmPassportType dmPassportType = DmPassportTypeLocalServiceUtil
				.getByPassportTypeCode(passportTypeCode);
		result.put("id", dmPassportType.getId());
		result.put("passportType", dmPassportType.getPassportType());
		result.put("passportTypeCode", dmPassportType.getPassportTypeCode());
		result.put("passportTypeNameVN", dmPassportType.getPassportTypeNameVN());
		result.put("modifiedDate", dmPassportType.getModifiedDate());
		result.put("isDelete", dmPassportType.getIsDelete());
		return result;
	}
}
