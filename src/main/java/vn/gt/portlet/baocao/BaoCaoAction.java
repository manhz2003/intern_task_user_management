package vn.gt.portlet.baocao;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;

import lombok.extern.slf4j.Slf4j;
import vn.gt.portlet.GiaoThongAction;



import com.fds.nsw.liferay.core.ParamUtil;

/**
 * Portlet implementation class BaoCaoAction
 */
@Slf4j
public class BaoCaoAction extends GiaoThongAction {
	
	
	
	public void searchReportShipInMaritime(ActionRequest resourceRequest, ActionResponse httpReq) throws NumberFormatException, Exception {
		
		log.info("==searchReportShipInMaritime==");
		
		String reportCode = ParamUtil.getString(resourceRequest, BaoCaoConstant.MAU_BAO_CAO);
		String tuNgay = ParamUtil.getString(resourceRequest, "tuNgay");
		String denNgay = ParamUtil.getString(resourceRequest, "denNgay");
		String ngayLap = ParamUtil.getString(resourceRequest, "ngayLap");
		String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
		
		String hasData = null;
		boolean hasDataBoolean = false;
		
		// /Xu ly Report bao cao
		BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
		if (((reportCode != null) && (reportCode.trim() != "")) && ((maritimeCode != null) && (maritimeCode.trim() != ""))) {
			hasDataBoolean = action.actionBaoCaoThongKeExport(Integer.parseInt(reportCode), maritimeCode, ngayLap, tuNgay, denNgay);
		}
		if (hasDataBoolean) {
			hasData = "Y";
		} else {
			hasData = "N";
		}
		
		log.info("==searchReportShipInMaritime==          " + hasData);
//		httpReq.setRenderParameter(BaoCaoConstant.MAU_BAO_CAO, reportCode);
//		httpReq.setRenderParameter("tuNgay", tuNgay);
//		httpReq.setRenderParameter("denNgay", denNgay);
//		httpReq.setRenderParameter("ngayLap", ngayLap);
//		httpReq.setRenderParameter("maritimeCode", maritimeCode);
//		httpReq.setRenderParameter("hasData", hasData);
//
//		PortletConfig portletConfig = (PortletConfig) resourceRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(resourceRequest, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}
	
	public void searchBaoCao(ActionRequest resourceRequest, ActionResponse httpReq) {
		
	}
}
