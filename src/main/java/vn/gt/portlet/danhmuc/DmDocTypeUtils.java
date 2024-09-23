package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmDocType;
import vn.gt.dao.danhmuc.service.DmDocTypeLocalServiceUtil;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmDocTypeUtils {

	public static JSONObject getDocTypes(String documentTypeName,
			String isDelete, String documentTypeCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;

		List<DmDocType> dmDocTypes = DmDocTypeLocalServiceUtil.findDocTypes(
				documentTypeName, isDelete, documentTypeCodeGroup, start, end);
		total = DmDocTypeLocalServiceUtil.countDocTypes(documentTypeName,
				isDelete, documentTypeCodeGroup);

		for (DmDocType dmDocType : dmDocTypes) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", dmDocType.getId());
			obj.put("documentType", dmDocType.getDocumentType());
			obj.put("documentTypeCode", dmDocType.getDocumentTypeCode());
			obj.put("documentTypeName", dmDocType.getDocumentTypeName());
			obj.put("isDelete", dmDocType.getIsDelete());
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailDocType(String documentTypeCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmDocType dmDocType = DmDocTypeLocalServiceUtil
				.getByDocumentTypeCode(documentTypeCode);
		result.put("id", dmDocType.getId());
		result.put("documentType", dmDocType.getDocumentType());
		result.put("documentTypeCode", dmDocType.getDocumentTypeCode());
		result.put("documentTypeName", dmDocType.getDocumentTypeName());
		result.put("modifiedDate", dmDocType.getModifiedDate());
		result.put("isDelete", dmDocType.getIsDelete());
		return result;
	}
}
