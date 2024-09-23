package vn.gt.portlet.tracuu;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;


import com.fds.nsw.liferay.core.ParamUtil;
import org.springframework.stereotype.Service;
import vn.gt.portlet.GiaoThongAction;
import vn.gt.portlet.TransportationMVCAction;

/**
 * Portlet implementation class TraCuuAction
 */
@Service
public class TraCuuAction extends TransportationMVCAction {
	public void searchTraCuu(ActionRequest resourceRequest,
			ActionResponse httpReq) {
		//todo tinh sau
//		String documentName = ParamUtil.getString(resourceRequest,
//				"documentName").trim();
//		String documentYear = ParamUtil.getString(resourceRequest,
//				"documentYear").trim();
//
//		httpReq.setRenderParameter("documentName", documentName);
//		httpReq.setRenderParameter("documentYear", documentYear);
//
//		PortletConfig portletConfig = (PortletConfig) resourceRequest
//				.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(resourceRequest, portletConfig.getPortletName()
//				+ SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}

	}
