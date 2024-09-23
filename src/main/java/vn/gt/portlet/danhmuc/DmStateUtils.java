package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmState;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmStateUtils {


	public static JSONObject getStates(String stateName, String isDelete,
			String stateCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmState> dmStates = DmStateLocalServiceUtil.findStates(stateName,
				isDelete, stateCodeGroup, start, end);
		total = DmStateLocalServiceUtil.countStates(stateName, isDelete,
				stateCodeGroup);
		for (DmState dmState : dmStates) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", dmState.getId());
			obj.put("stateCode", dmState.getStateCode());
			obj.put("stateName", dmState.getStateName());
			obj.put("description", dmState.getDescription());
			obj.put("modifiedDate", dmState.getModifiedDate());
			obj.put("isDelete", dmState.getIsDelete());
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);
		return result;
	}

	public static JSONObject getDetailState(String stateCode)
			throws SystemException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmState dmState = DmStateLocalServiceUtil.getByStateCode(stateCode);
		result.put("id", dmState.getId());
		result.put("stateName", dmState.getStateName());
		result.put("stateCode", dmState.getStateCode());
		result.put("description", dmState.getDescription());
		result.put("modifiedDate", dmState.getModifiedDate());
		result.put("isDelete", dmState.getIsDelete());
		return result;
	}
}
