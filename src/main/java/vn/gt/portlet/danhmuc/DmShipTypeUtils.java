package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmShipType;
import vn.gt.dao.danhmuc.service.DmShipTypeLocalServiceUtil;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmShipTypeUtils {


	public static JSONObject getShipTypes(String shipTypeNameVN,
			String isDelete, String shipTypeCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmShipType> dmType = DmShipTypeLocalServiceUtil.findShipTypes(
				shipTypeNameVN, isDelete, shipTypeCodeGroup, start, end);
		total = DmShipTypeLocalServiceUtil.countShipTypes(shipTypeNameVN,
				isDelete, shipTypeCodeGroup);

		for (DmShipType dmShipType : dmType) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", dmShipType.getId());
			obj.put("shipTypeCode", dmShipType.getShipTypeCode());
			obj.put("shipTypeNameVN", dmShipType.getShipTypeNameVN());
			obj.put("isDelete", dmShipType.getIsDelete());

			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailShipType(String shipTypeCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmShipType dmShipType = DmShipTypeLocalServiceUtil
				.getByShipTypeCode(shipTypeCode);
		result.put("id", dmShipType.getId());
		result.put("shipTypeCode", dmShipType.getShipTypeCode());
		result.put("shipTypeNameVN", dmShipType.getShipTypeNameVN());
		result.put("modifiedDate", dmShipType.getModifiedDate());
		result.put("isDelete", dmShipType.getIsDelete());
		return result;
	}
}
