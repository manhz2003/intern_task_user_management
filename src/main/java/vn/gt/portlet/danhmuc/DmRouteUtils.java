/**
 * 
 */
package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmRoute;
import com.fds.nsw.nghiepvu.model.DmVRCode;
import vn.gt.dao.danhmuc.service.DmRouteLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVRCodeLocalServiceUtil;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

/**
 * @author ddung
 *
 */
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmRouteUtils {

	public static JSONObject getRoutes(int start, int end)
			throws SystemException {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject results = JSONFactoryUtil.createJSONObject();
		long total = 0;

		List<DmRoute> dmRoutes = DmRouteLocalServiceUtil
				.getDmRoutes(start, end);
		total = dmRoutes.size();

		for (DmRoute dmRoute : dmRoutes) {
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("id", dmRoute.getId());
			result.put("routeLevelCode", dmRoute.getRouteLevelCode());
			result.put("routeLevelName", dmRoute.getRouteLevelName());
			result.put("markedAsDelete", dmRoute.getMarkedAsDelete());
			result.put("isDelete", dmRoute.getIsDelete());
			result.put("syncVersion", dmRoute.getSyncVersion());
			result.put("modifiedDate", dmRoute.getModifiedDate());
			array.put(result);
		}
		results.put("data", array);
		results.put("total", total);

		return results;
	}

	public static JSONObject getDetailPort(int id) throws SystemException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmRoute dmRoute = DmRouteLocalServiceUtil.fetchDmRoute(id);
		if (dmRoute != null) {
			result.put("id", dmRoute.getId());
			result.put("routeLevelCode", dmRoute.getRouteLevelCode());
			result.put("routeLevelName", dmRoute.getRouteLevelName());
			result.put("markedAsDelete", dmRoute.getMarkedAsDelete());
			result.put("isDelete", dmRoute.getIsDelete());
			result.put("syncVersion", dmRoute.getSyncVersion());
			result.put("modifiedDate", dmRoute.getModifiedDate());
		}
		return result;
	}
}
