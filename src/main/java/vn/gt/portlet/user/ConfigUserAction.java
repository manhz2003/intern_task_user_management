package vn.gt.portlet.user;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;




import com.fds.nsw.nghiepvu.model.UserPort;
import org.springframework.stereotype.Service;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.portlet.TransportationMVCAction;
import vn.gt.portlet.business.Constans;

/**
 * Portlet implementation class ConfigUserAction
 */
@Service
public class ConfigUserAction extends TransportationMVCAction {

	public void addUserToPort(ActionRequest resourceRequest,
			ActionResponse httpReq) throws Exception {

		UserPortUtils userPortUtils = new UserPortUtils();
		UserPort userPort = userPortUtils.fillData2Form(resourceRequest,
				httpReq);
		System.out.println(" Start");
		if (UserPortValidates.validateUserPort(resourceRequest)) {
			System.out.println(" Validate");
			UserPortLocalServiceUtil.addUserPort(userPort);
		} else {
			System.out.println("Not  Validate");
			resourceRequest.setAttribute(Constans.USER_PORT_ERROR, userPort);
			//todo config jspPage
//			httpReq.setRenderParameter("jspPage",
//					"/html/configuser/adduser.jsp");
		}
//		PortletConfig portletConfig = (PortletConfig) resourceRequest
//				.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(resourceRequest, portletConfig.getPortletName()
//				+ SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}

	public void editUserToPort(ActionRequest resourceRequest,
			ActionResponse httpReq) throws Exception {

		UserPortUtils userPortUtils = new UserPortUtils();
		UserPort userPort = userPortUtils.fillData2Form(resourceRequest,
				httpReq);
		System.out.println(" Start");
		if (UserPortValidates.validateUserPort(resourceRequest)) {
			System.out.println(" Validate");
			UserPortLocalServiceUtil.updateUserPort(userPort);
		} else {
			System.out.println("Not  Validate");
			resourceRequest.setAttribute(Constans.USER_PORT_ERROR, userPort);
			//todo config jspPage
//			httpReq.setRenderParameter("jspPage",
//					"/html/configuser/edituser.jsp");
		}
//		PortletConfig portletConfig = (PortletConfig) resourceRequest
//				.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(resourceRequest, portletConfig.getPortletName()
//				+ SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}
}
