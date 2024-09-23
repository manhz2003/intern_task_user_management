package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import vn.gt.dao.danhmuc.service.VmaTransactionDepartmentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class UserPortUtils {

	public static JSONObject getUserPorts(String departmentCode, int start,
			int end) throws SystemException {
		List<UserPort> userPorts = UserPortLocalServiceUtil
				.fetchByDepartmentCode(departmentCode, start, end);
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		for (UserPort userPort : userPorts) {
			User user = UserLocalServiceUtil.fetchUser(userPort.getUserId());
			JSONObject object = JSONFactoryUtil.createJSONObject();
			object.put("userId", user.getUserId());
			object.put("firstName", user.getFirstName());
			object.put("emailAddress", user.getEmailAddress());
			object.put("screenName", user.getScreenName());
			object.put("departmentCode", userPort.getDepartmentCode());
			object.put("status", userPort.getStatus());
			object.put("portCode", userPort.getPortCode());
			try {
				object.put("departmentName",
						VmaTransactionDepartmentLocalServiceUtil
								.fetchVmaTransactionDepartment(departmentCode)
								.getDepartmentName());
			} catch (Exception e) {
				object.put("departmentName", "");
			}
			array.put(object);
		}
		result.put("total",
				UserPortLocalServiceUtil.countByDepartmentCode(departmentCode));
		result.put("data", array);
		return result;
	}

	public static void updateUserPort(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) throws SystemException {
		UserPort userPortAction = UserPortLocalServiceUtil
				.findByUserId(themeDisplay.getUserId());
		UserSign userSignAction = UserSignLocalServiceUtil
				.getByUserId(userPortAction.getUserId());
		String portCode = UserPortLocalServiceUtil.findByUserId(
				GetterUtil.getLong(themeDisplay.getUserId())).getPortCode();
		String departmentCode = DanhMucUtils.getString(actionRequest,
				"departmentCode");
		long userId = ParamUtil.getLong(actionRequest, "userId");
		if (Validator.isNotNull(portCode) & Validator.isNotNull(departmentCode)
				& userId != 0) {
			UserPort userPort = UserPortLocalServiceUtil.findByUserId(userId);
			userPort.setDepartmentCode(departmentCode);
			UserPortLocalServiceUtil.updateUserPort(userPort);

			VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPortAction
					.getUserId(),
					userSignAction != null ? userSignAction.getModifyuser()
							: StringPool.BLANK, LogsConstant.SUA, "user_port",
					String.valueOf(userPort.getUserId()), StringPool.BLANK);
		}
	}

	public static JSONObject findByUserId(ThemeDisplay themeDisplay) {
		UserPort user = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		JSONObject object = JSONFactoryUtil.createJSONObject();
		object.put("userId", user.getUserId());
		object.put("departmentCode", user.getDepartmentCode());
		object.put("portCode", user.getPortCode());
		object.put("routeCode", user.getRouteCode());
		object.put("status", user.getStatus());
		try {
			object.put(
					"departmentName",
					VmaTransactionDepartmentLocalServiceUtil
							.fetchVmaTransactionDepartment(
									user.getDepartmentCode())
							.getDepartmentName());
		} catch (Exception e) {
			object.put("departmentName", "");
		}

		return object;
	}

}
