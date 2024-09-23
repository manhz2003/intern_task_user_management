package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmPort;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmPortUtils {


	// update by TrungNT -> add param portType
	public static JSONObject getPorts(String portName, String isDelete,
			String portCodeGroup, String portType, String stateCode, int start,
			int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject results = JSONFactoryUtil.createJSONObject();
		long total = 0;

		List<DmPort> dmPorts = DmPortLocalServiceUtil.findPorts(portName,
				isDelete, portCodeGroup, portType, stateCode, start, end);
		total = DmPortLocalServiceUtil.countPorts(portName, isDelete,
				portCodeGroup, portType, stateCode);

		for (DmPort dmPort : dmPorts) {
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("id", dmPort.getId());
			result.put("portCode", dmPort.getPortCode());
			result.put("portName", dmPort.getPortName());
			result.put("fullNameVN", dmPort.getFullNameVN());
			result.put("addressVN", dmPort.getAddressVN());
			result.put("cityCode", dmPort.getCityCode());
			result.put("portType", dmPort.getPortType());
			result.put("modifiedDate", dmPort.getModifiedDate());
			result.put("isDelete", dmPort.getIsDelete());
			result.put("loCode", dmPort.getLoCode());
			result.put("portOrder", dmPort.getPortOrder());
			result.put("stateCode", dmPort.getStateCode());
			array.put(result);
		}
		results.put("data", array);
		results.put("total", total);

		return results;
	}

	public static JSONObject getDetailPort(int portId) throws SystemException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmPort dmPort = DmPortLocalServiceUtil.fetchDmPort(portId);
		result.put("id", dmPort.getId());
		result.put("portCode", dmPort.getPortCode());
		result.put("portName", dmPort.getPortName());
		result.put("fullNameVN", dmPort.getFullNameVN());
		result.put("addressVN", dmPort.getAddressVN());
		result.put("cityCode", dmPort.getCityCode());
		result.put("portType", dmPort.getPortType());
		result.put("modifiedDate", dmPort.getModifiedDate());
		result.put("isDelete", dmPort.getIsDelete());
		result.put("loCode", dmPort.getLoCode());
		result.put("portOrder", dmPort.getPortOrder());
		result.put("stateCode", dmPort.getStateCode());
		return result;
	}

	public static JSONObject updateDmPort(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) throws SystemException {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());

		String portCode = ParamUtil.getString(actionRequest, "portCode");
		String loCode = ParamUtil.getString(actionRequest, "loCode");
		String portName = DanhMucUtils.getString(actionRequest, "portName");
		String fullName = DanhMucUtils.getString(actionRequest, "fullName");
		String fullNameVN = DanhMucUtils.getString(actionRequest, "fullNameVN");
		String portType = ParamUtil.getString(actionRequest, "portType");
		String portOrder = ParamUtil.getString(actionRequest, "portOrder");
		String address = DanhMucUtils.getString(actionRequest, "address");
		String addressVN = DanhMucUtils.getString(actionRequest, "addressVN");
		String stateCode = ParamUtil.getString(actionRequest, "stateCode");
		String cityCode = DanhMucUtils.getString(actionRequest, "cityCode");

		DmPort dmPort = DmPortLocalServiceUtil.getByPortCode(portCode);
		if (dmPort != null) {
			if (Validator.isNotNull(portOrder)) {
				dmPort.setPortOrder(Integer.valueOf(portOrder));
			}
			if (Validator.isNotNull(portName)) {
				dmPort.setPortName(portName);
			}
			if (Validator.isNotNull(fullName)) {
				dmPort.setFullName(fullName);
			}
			if (Validator.isNotNull(fullNameVN)) {
				dmPort.setFullNameVN(fullNameVN);
			}
			if (Validator.isNotNull(loCode)) {
				dmPort.setLoCode(loCode);
			}
			if (Validator.isNotNull(portType)) {
				dmPort.setPortType(portType);
			}
			if (Validator.isNotNull(address)) {
				dmPort.setAddress(address);
			}
			if (Validator.isNotNull(addressVN)) {
				dmPort.setAddressVN(addressVN);
			}
			if (Validator.isNotNull(stateCode)) {
				dmPort.setStateCode(stateCode);
			}
			if (Validator.isNotNull(cityCode)) {
				dmPort.setCityCode(cityCode);
			}
			dmPort = DmPortLocalServiceUtil.updateDmPort(dmPort);

			VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort.getUserId(),
					userSign != null ? userSign.getModifyuser()
							: StringPool.BLANK, LogsConstant.SUA, "dm_port",
					dmPort.getPortCode(), dmPort.getPortName());
			if (dmPort != null) {
				JSONObject result = JSONFactoryUtil.createJSONObject();
				result.put("id", dmPort.getId());
				result.put("portCode", dmPort.getPortCode());
				result.put("portName", dmPort.getPortName());
				result.put("fullNameVN", dmPort.getFullNameVN());
				result.put("addressVN", dmPort.getAddressVN());
				result.put("cityCode", dmPort.getCityCode());
				result.put("portType", dmPort.getPortType());
				result.put("modifiedDate", dmPort.getModifiedDate());
				result.put("isDelete", dmPort.getIsDelete());
				result.put("loCode", dmPort.getLoCode());
				result.put("portOrder", dmPort.getPortOrder());
				return result;
			}
		}
		return JSONFactoryUtil.createJSONObject();
	}
}
