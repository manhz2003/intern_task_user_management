/**
 * 
 */
package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmVRCode;
import vn.gt.dao.danhmuc.service.DmVRCodeLocalServiceUtil;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.flex.common.utility.string.StringPool;

/**
 * @author ddung
 *
 */
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmVRCodeUtils {

	public static JSONObject getVRCodes(String shipBoat, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject results = JSONFactoryUtil.createJSONObject();
		long total = 0;

		List<DmVRCode> dmVRCodes = DmVRCodeLocalServiceUtil.findByShipBoat(
				shipBoat, start, end);
		total = DmVRCodeLocalServiceUtil.countByShipBoat(shipBoat);

		for (DmVRCode dmVRCode : dmVRCodes) {
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("id", dmVRCode.getId());
			result.put("vrCode", dmVRCode.getVrCode());
			result.put("vrCodeName", dmVRCode.getVrCodeName());
			result.put("markedAsDelete", dmVRCode.getMarkedAsDelete());
			result.put("isDelete", dmVRCode.getIsDelete());
			result.put("syncVersion", dmVRCode.getSyncVersion());
			result.put("shipBoat", dmVRCode.getShipBoat());
			result.put("modifiedDate", dmVRCode.getModifiedDate());
			array.put(result);
		}
		results.put("data", array);
		results.put("total", total);

		return results;
	}

	public static JSONObject getAllVRCodes(int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject results = JSONFactoryUtil.createJSONObject();
		long total = 0;
		try {
				List<DmVRCode> dmVRCodes = DmVRCodeLocalServiceUtil.getDmVRCodes(start, end);
				total = dmVRCodes.size();
		
				for (DmVRCode dmVRCode : dmVRCodes) {
					JSONObject result = JSONFactoryUtil.createJSONObject();
					result.put("id", dmVRCode.getId());
					result.put("vrCode", dmVRCode.getVrCode());
					result.put("vrCodeName", dmVRCode.getVrCodeName());
					result.put("markedAsDelete", dmVRCode.getMarkedAsDelete());
					result.put("isDelete", dmVRCode.getIsDelete());
					result.put("syncVersion", dmVRCode.getSyncVersion());
					result.put("shipBoat", dmVRCode.getShipBoat());
					result.put("modifiedDate", dmVRCode.getModifiedDate());
					array.put(result);
				}
				results.put("data", array);
				results.put("total", total);
		
				return results;
		} catch (Exception e) {
			results.put("data", StringPool.BLANK);
			results.put("total", 0);
			return results;
		}
	}
	
	public static JSONObject getDetailPort(int id) throws SystemException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmVRCode dmVRCode = DmVRCodeLocalServiceUtil.fetchDmVRCode(id);
		if (dmVRCode != null) {
			result.put("id", dmVRCode.getId());
			result.put("vrCode", dmVRCode.getVrCode());
			result.put("vrCodeName", dmVRCode.getVrCodeName());
			result.put("markedAsDelete", dmVRCode.getMarkedAsDelete());
			result.put("isDelete", dmVRCode.getIsDelete());
			result.put("syncVersion", dmVRCode.getSyncVersion());
			result.put("shipBoat", dmVRCode.getShipBoat());
			result.put("modifiedDate", dmVRCode.getModifiedDate());
		}
		return result;
	}
}
