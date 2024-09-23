package vn.gt.portlet.user;

import java.util.Date;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;

import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserPort;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;



import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.liferay.core.LanguageUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.UploadPortletRequest;
@Slf4j
public class UserPortUtils
 {

	public UserPort fillData2Form(ActionRequest actionrequest,
			ActionResponse httpReq) {
		try {
			long userId = ParamUtil.getLong(actionrequest, "userId");
			long userPortId = ParamUtil.getLong(actionrequest, "USER_PORTID");

			UserPort userPort = null;
			if (userPortId > 0) {
				userPort = UserPortLocalServiceUtil.getUserPort(userPortId);
			} else {
				userPort = new UserPort();
			}
			String portCode = ParamUtil.getString(actionrequest,
					"portCode");
			Integer status = ParamUtil.getInteger(actionrequest, "status");

			userPort.setCreatedate(new Date());
			userPort.setUserId((int) userId);
			userPort.setStatus(status);
			userPort.setPortCode(portCode);

			return userPort;
		} catch (Exception e) {
		}
		return null;

	}

}
