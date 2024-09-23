
package vn.gt.portlet.monitor;

import java.io.IOException;
import java.util.Date;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;



import com.fds.nsw.liferay.core.ParamUtil;

import com.fds.nsw.nghiepvu.model.AswmsgMessagequeue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.gt.dao.asw.service.AswmsgMessageQueueLocalServiceUtil;
import vn.gt.portlet.TransportationMVCAction;

@Slf4j
@Service
public class MonitorPortlet extends TransportationMVCAction {
	
	
	public void refeshMonitor(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException {
		
		long id = ParamUtil.getLong(actionRequest, "id");
		String redirectURL = ParamUtil.getString(actionRequest, "redirectURL");
		
		if (id > 0) {
			try {
				AswmsgMessagequeue aswmsgMessageQueue = AswmsgMessageQueueLocalServiceUtil.fetchAswmsgMessageQueue(id);
				if (aswmsgMessageQueue != null) {
					aswmsgMessageQueue.setPriority(15);
					aswmsgMessageQueue.setSolangui(0);
					aswmsgMessageQueue.setValidated(-1);
					aswmsgMessageQueue.setCreatedtime(new Date());
					
					AswmsgMessageQueueLocalServiceUtil.updateAswmsgMessageQueue(aswmsgMessageQueue);
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
//		actionResponse.sendRedirect(redirectURL);
	}
}
