package vn.gt.portlet.user;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ParamUtil;

import com.fds.nsw.liferay.core.SessionErrors;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.utils.validation.ValidationUtils;




public class UserPortValidates {
	public static boolean validateUserPort(ActionRequest actionrequest) {
		boolean valid = true;
		
		String maritimeCode = ParamUtil.getString(actionrequest, "portCode")
				.trim();
		if (maritimeCode==null ||maritimeCode.length() == 0		) {
			System.out.println(maritimeCode+"  1");
			SessionErrors.add(actionrequest, "err.portHarbourCode");
			valid = false;
		}
		
		int count =   DmMaritimeLocalServiceUtil.countByMaritimeCode(maritimeCode);//  UserPortLocalServiceUtil.countByPortHarbourCode(portHarbourCode);
		
		if(count==0){
			System.out.println(maritimeCode+"  2");
			SessionErrors.add(actionrequest, "err.portHarbourCode_count");
			valid = false;
		}
		
		int status = ParamUtil.getInteger(actionrequest, "status");
		if(status==0){
			System.out.println(maritimeCode+"  3");
			SessionErrors.add(actionrequest, "err.status");
			valid = false;
		}
		
		long userId = ParamUtil.getLong(actionrequest, "userId");
		System.out.println("userId "+userId);
		
		if(userId==0){
			System.out.println(maritimeCode+"  4");
			SessionErrors.add(actionrequest, "err.userId");
			valid = false;
		}
		
		
		if (!valid) {
			ValidationUtils.setParams(actionrequest);
		}

		return valid;
	}

}
