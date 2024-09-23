package vn.gt.portlet.kehoach.utils;

import com.fds.nsw.liferay.core.ActionRequest;
//import javax.portlet.PortletSession;

import com.fds.nsw.liferay.core.ParamUtil;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class CommonUtils {


	public static boolean checkFromDate(ActionRequest actionRequest) {
		String FORM_DATE = "FORM_DATE_SUBMIT";
		String formDate = ParamUtil.getString(actionRequest, "formDate");
		//Chưa biết thằng củ nồi portlet session này làm gì nên tạm comment
		if (formDate != null && formDate.trim().length() > 0) {
//			PortletSession portletSession = actionRequest.getPortletSession();
//
//			Object oldFormDate = portletSession.getAttribute(FORM_DATE);
//
//			if (oldFormDate != null && formDate.equalsIgnoreCase(String.valueOf(oldFormDate))) {
//				return true;
//			}
//
//			portletSession.setAttribute(FORM_DATE, formDate);
		}
		
		return false;
	}
}
