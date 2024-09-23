package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmArrivalPurpose;
import vn.gt.dao.danhmuc.service.DmArrivalPurposeLocalServiceUtil;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmArrivalPurposeUtils {

	public static JSONObject getArrivalPurposes(String purposeName,
			String isDelete, String purposeCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmArrivalPurpose> dmPurpose = DmArrivalPurposeLocalServiceUtil
				.findArrivalPurposes(purposeName, isDelete, purposeCodeGroup,
						start, end);
		total = DmArrivalPurposeLocalServiceUtil.countArrivalPurposes(
				purposeName, isDelete, purposeCodeGroup);

		for (DmArrivalPurpose arrivalPurpose : dmPurpose) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", arrivalPurpose.getId());
			obj.put("purposeCode", arrivalPurpose.getPurposeCode());
			obj.put("purposeName", arrivalPurpose.getPurposeName());
			obj.put("purposeNameVN", arrivalPurpose.getPurposeNameVN());
			obj.put("isDelete", arrivalPurpose.getIsDelete());
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailArrivalPurpose(String purposeCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmArrivalPurpose dmArrivalPurpose = DmArrivalPurposeLocalServiceUtil
				.getByPortCode(purposeCode);
		result.put("id", dmArrivalPurpose.getId());
		result.put("purposeCode", dmArrivalPurpose.getPurposeCode());
		result.put("purposeName", dmArrivalPurpose.getPurposeName());
		result.put("modifiedDate", dmArrivalPurpose.getModifiedDate());
		result.put("isDelete", dmArrivalPurpose.getIsDelete());
		return result;
	}
}
