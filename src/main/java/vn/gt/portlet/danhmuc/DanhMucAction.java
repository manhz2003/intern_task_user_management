package vn.gt.portlet.danhmuc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.nsw.kernel.dao.orm.QueryUtil;









import com.fds.nsw.liferay.core.PortalUtil;

import com.fds.nsw.nghiepvu.model.DmDataitem;
import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour;
import com.fds.nsw.nghiepvu.model.DmHistoryPortRegion;
import com.fds.nsw.nghiepvu.model.DmHistoryPortWharf;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import com.fds.nsw.nghiepvu.model.DmPortRegion;
import com.fds.nsw.nghiepvu.model.DmPortWharf;

import lombok.extern.slf4j.Slf4j;

import com.fds.nsw.nghiepvu.model.DmDataitem;
import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour;
import com.fds.nsw.nghiepvu.model.DmHistoryPortRegion;
import com.fds.nsw.nghiepvu.model.DmHistoryPortWharf;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import com.fds.nsw.nghiepvu.model.DmPortRegion;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.portlet.TransportationMVCAction;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.haiquan2giaothong.DMListSyn;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.KeyParams;
import vn.gt.utils.PageType;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
/**
 * Portlet implementation class DanhMucAction
 */
@Slf4j
@Service
public class DanhMucAction extends TransportationMVCAction {

	/**
	 * Default constructor.
	 */
	

	public void findPortNameByPortRegionCode(ActionRequest resourceRequest, ActionResponse httpReq) throws IOException {
		try {
			String portRegionCode = ParamUtil.getString(resourceRequest, "portRegionCode");
			log.info("====portRegionCode=====" + portRegionCode);

			// Process Json feed
			JSONArray array = JSONFactoryUtil.createJSONArray();
			JSONObject obj = null;

			DmPortRegion dmPortRegionItem = DmPortRegionLocalServiceUtil.getByPortRegionCode(portRegionCode);

			obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", dmPortRegionItem.getPortRegionCode());
			obj.put("name", dmPortRegionItem.getPortRegionNameVN());
			obj.put("portname", dmPortRegionItem.getPortCode());
			array.put(obj);
			//todo query in ActionController
//			HttpServletResponse resourceResponse = PortalUtil.getHttpServletResponse(httpReq);
//			log.info("=====array===" + array.toString());
//
//			resourceResponse.setContentType("application/json");
//			resourceResponse.setCharacterEncoding("UTF-8");
//			resourceResponse.getWriter().write(array.toString());
//			resourceResponse.getWriter().close();
		} catch (Exception e1) {

		}
	}

	public void findPortRegionByMaritimeCode(ActionRequest resourceRequest, ActionResponse httpReq) throws IOException {
		try {
			String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
			log.info("====maritimeCode=====" + maritimeCode);

			// Process Json feed

			JSONArray array = JSONFactoryUtil.createJSONArray();
			JSONObject obj = null;

			List<DmPortRegion> dsDmPortRegions = DmPortRegionLocalServiceUtil
					.findPortRegionByPortRegionRef(maritimeCode);
			if ((dsDmPortRegions == null) || (dsDmPortRegions.size() == 0)) {
				dsDmPortRegions = DmPortRegionLocalServiceUtil.getDmPortRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			}
			log.info("==dsDmPortRegions==size==" + dsDmPortRegions.size());
			for (DmPortRegion item : dsDmPortRegions) {
				obj = JSONFactoryUtil.createJSONObject();
				obj.put("id", item.getPortRegionCode());
				obj.put("name", item.getPortRegionNameVN());
				obj.put("portname", item.getPortCode());
				array.put(obj);
			}
//todo query in ActionController
//			HttpServletResponse resourceResponse = PortalUtil.getHttpServletResponse(httpReq);
//			log.info("=====array===" + array.toString());
//
//			resourceResponse.setContentType("application/json");
//			resourceResponse.setCharacterEncoding("UTF-8");
//			resourceResponse.getWriter().write(array.toString());
//			resourceResponse.getWriter().close();
		} catch (Exception e1) {

		}
	}

	public void findPortHarbourByPortRegionCode(ActionRequest resourceRequest, ActionResponse httpReq)
			throws IOException {
		try {
			String portRegionCode = ParamUtil.getString(resourceRequest, "portRegionCode");
			// DmPortRegion portRegionDetails =
			// DmPortRegionLocalServiceUtil.getByPortRegionCode(portRegionCode);
			// if (Validator.isNotNull(portRegionDetails)) {
			// maritimeCode = portRegionDetails.getPortRegionRef();
			// }
			// log.info("====portRegionCode=====" + portRegionCode);
			// log.info("====maritimeCode=====" + maritimeCode);

			// Process Json feed

			JSONArray array = JSONFactoryUtil.createJSONArray();
			JSONObject obj = null;

			List<DmPortHarbour> dsDmPortHarbours = DmPortHarbourLocalServiceUtil.findByPortRegion(portRegionCode);
			if ((dsDmPortHarbours == null) || (dsDmPortHarbours.size() == 0)) {
				// dsDmPortHarbours =
				// DmPortHarbourLocalServiceUtil.getDmPortHarbours(QueryUtil.ALL_POS,
				// QueryUtil.ALL_POS);
			}
			log.info("==dsDmPortHarbours==size==" + dsDmPortHarbours.size());
			for (DmPortHarbour item : dsDmPortHarbours) {
				obj = JSONFactoryUtil.createJSONObject();
				obj.put("id", item.getPortHarbourCode());
				obj.put("name", item.getPortHarbourNameVN());
				array.put(obj);
			}
//todo query in ActionController
//			HttpServletResponse resourceResponse = PortalUtil.getHttpServletResponse(httpReq);
//			log.info("=====array===" + array.toString());
//
//			resourceResponse.setContentType("application/json");
//			resourceResponse.setCharacterEncoding("UTF-8");
//			resourceResponse.getWriter().write(array.toString());
//			resourceResponse.getWriter().close();
		} catch (Exception e1) {

		}
	}

	public void findPortHarbourByMaritimeCode(ActionRequest resourceRequest, ActionResponse httpReq)
			throws IOException {
		try {
			String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
			log.info("====maritimeCode=====" + maritimeCode);

			// Process Json feed

			JSONArray array = JSONFactoryUtil.createJSONArray();
			JSONObject obj = null;

			List<DmPortHarbour> dsDmPortHarbours = DmPortHarbourLocalServiceUtil.findByPortRegionCode(maritimeCode);
			if ((dsDmPortHarbours == null) || (dsDmPortHarbours.size() == 0)) {
				dsDmPortHarbours = DmPortHarbourLocalServiceUtil.getDmPortHarbours(QueryUtil.ALL_POS,
						QueryUtil.ALL_POS);
			}
			log.info("==dsDmPortHarbours==size==" + dsDmPortHarbours.size());
			for (DmPortHarbour item : dsDmPortHarbours) {
				obj = JSONFactoryUtil.createJSONObject();
				obj.put("id", item.getPortHarbourCode());
				obj.put("name", item.getPortHarbourNameVN());
				array.put(obj);
			}
//todo query in ActionController
//			HttpServletResponse resourceResponse = PortalUtil.getHttpServletResponse(httpReq);
//			log.info("=====array===" + array.toString());
//
//			resourceResponse.setContentType("application/json");
//			resourceResponse.setCharacterEncoding("UTF-8");
//			resourceResponse.getWriter().write(array.toString());
//			resourceResponse.getWriter().close();
		} catch (Exception e1) {

		}
	}

	public void findPortWhartByPortRegionAndHarbour(ActionRequest resourceRequest, ActionResponse httpReq)
			throws IOException {

		String portRegionCode = ParamUtil.getString(resourceRequest, "portRegionCode");

		if (portRegionCode.length() == 1) {
			portRegionCode = "00" + portRegionCode;
		} else if (portRegionCode.length() == 2) {
			portRegionCode = "0" + portRegionCode;
		}

		String portHarbourCode = ParamUtil.getString(resourceRequest, "portHarbourCode");
		// TODO - dinhminh, Cancel ben cang -> cau cang, chon combo box
		log.info("====portRegionCode=====" + portRegionCode);
		log.info("====portHarbourCode====" + portHarbourCode);

		// Process Jason feed

		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject obj = null;

		//
		// List<DmPortWharf> dsPortWharfs =
		// DmPortWharfLocalServiceUtil.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(portRegionCode,
		// portHarbourCode, KeyParams.ORDER_BY_ASC);
		List<DmPortWharf> dsPortWharfs = DmPortWharfLocalServiceUtil.findByPortHarbourCodeOrNull(portHarbourCode,
				KeyParams.ORDER_BY_ASC);
		if (dsPortWharfs == null) {
			dsPortWharfs = new ArrayList<DmPortWharf>();
		}

		for (DmPortWharf item : dsPortWharfs) {
			obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", item.getPortWharfCode());
			obj.put("name", item.getPortWharfNameVN() + " - " + item.getPortCode());
			array.put(obj);
		}

		log.info("==dsPortWharfs==size==" + dsPortWharfs.size());
//todo query in ActionController
//		HttpServletResponse resourceResponse = PortalUtil.getHttpServletResponse(httpReq);
//		log.info("=====array===" + array.toString());
//		// resourceRequest.setAttribute("portRegionRef", portRegionRef);
//
//		resourceResponse.setContentType("application/json");
//		resourceResponse.setCharacterEncoding("UTF-8");
//		resourceResponse.getWriter().write(array.toString());
//		resourceResponse.getWriter().close();
	}

	public void updatePortWharf(ActionRequest resourceRequest, ActionResponse actionResponse) throws Exception {
		boolean result = false;
		try {
			String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
			log.info("====maritimeCode=====" + maritimeCode);
			String idCollection = ParamUtil.getString(resourceRequest, "idCollection");
			log.info("====idCollection=====" + idCollection);
			String requestCode = ParamUtil.getString(resourceRequest, "REQUEST_CODE");
			log.info("====PageType.REQUEST_CODE=====" + requestCode);
			String portCodeName = ParamUtil.getString(resourceRequest, "portCodeName");
			log.info("====portCodeName=====" + portCodeName);
			String portRegionCode = ParamUtil.getString(resourceRequest, "portRegionCode");
			log.info("====portRegionCode=====" + portRegionCode);
			String portHarbourCode = ParamUtil.getString(resourceRequest, "portHarbourCode");
			log.info("====portHarbourCode=====" + portHarbourCode);
			String portWharfNameVN = ParamUtil.getString(resourceRequest, "portWharfNameVN");
			log.info("====portWharfNameVN=====" + portWharfNameVN);
			String portWharfName = ParamUtil.getString(resourceRequest, "portWharfName");
			log.info("====portWharfName=====" + portWharfName);
			String portWharfCode = ParamUtil.getString(resourceRequest, "portWharfCode");
			log.info("====portWharfCode=====" + portWharfCode);
			String versionNo = ParamUtil.getString(resourceRequest, "versionNo");
			log.info("====versionNo=====" + versionNo);
			String remarks = ParamUtil.getString(resourceRequest, "remarks");
			log.info("====remarks=====" + remarks);

			String status = "0";
			String capmoi = ParamUtil.getString(resourceRequest, PageType.LAN_CAP_MOI_DU_LIEU);
			String capsua = ParamUtil.getString(resourceRequest, PageType.LAN_SUA_DU_LIEU);
			String danhdauXoa = ParamUtil.getString(resourceRequest, PageType.LAN_XOA_DU_LIEU);
			log.info("====capmoi=====" + capmoi + "====capsua=====" + capsua + "====danhdauXoa=====" + danhdauXoa);

			String portHarbourCodeName = "";
			DmPortHarbour dmPortHarbourItem = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(portHarbourCode);
			if (dmPortHarbourItem != null) {
				portHarbourCodeName = dmPortHarbourItem.getPortHarbourNameVN();
			}
			log.info("messageType103 -- DM_PORT_WHARF ");

			DmPortWharf details = new DmPortWharf();
			DmHistoryPortWharf history = new DmHistoryPortWharf();
			String syncVersion = "1";

			details.setPortWharfCode(portWharfCode);
			details.setPortWharfName(portWharfNameVN);
			details.setPortWharfNameVN(portWharfNameVN);
			details.setPortWharfType(1);
			details.setPortCode(portHarbourCodeName);
			details.setNote(remarks);
			details.setPortRegionCode(portRegionCode);
			details.setPortHarbourCode(portHarbourCode);

			history.setPortWharfCode(portWharfCode);
			history.setPortWharfName(portWharfNameVN);
			history.setPortWharfNameVN(portWharfNameVN);
			history.setPortWharfType(1);
			history.setPortCode(portHarbourCodeName);
			history.setNote(remarks);
			history.setPortRegionCode(portRegionCode);
			history.setPortHarbourCode(portHarbourCode);
			history.setSyncVersion(syncVersion);

			if (danhdauXoa.length() > 0) {
				log.info("messageType103  Xoa");
				status = "0";
				DmPortWharf dmPortWharfItem = DmPortWharfLocalServiceUtil.getByPortWharfCode(portWharfCode);
				dmPortWharfItem.setIsDelete(1);
				dmPortWharfItem.setMarkedAsDelete(1);
				// dmPortitem.setRequestedDate(new Date());
				dmPortWharfItem.setModifiedDate(new Date());
				dmPortWharfItem.setSyncVersion("1|");
				DmPortWharfLocalServiceUtil.updateDmPortWharf(dmPortWharfItem);

				DmHistoryPortWharf dmHistoryPortWharfItem = DmHistoryPortWharfLocalServiceUtil
						.findByPortWharfCodeAndSyncVersion(portWharfCode, syncVersion);
				dmHistoryPortWharfItem.setIsDelete(1);
				dmHistoryPortWharfItem.setMarkedAsDelete(1);
				// dmPortitem.setRequestedDate(new Date());
				dmHistoryPortWharfItem.setModifiedDate(new Date());
				dmHistoryPortWharfItem.setSyncVersion("1");
				history = DmHistoryPortWharfLocalServiceUtil.updateDmHistoryPortWharf(dmHistoryPortWharfItem);

			} else if (capmoi.length() > 0) {
				log.info("messageType103 Them");
				status = "2";
				if (portWharfCode.trim().length() == 0) {
					portWharfCode = ReportsBusinessUtils.taoSo("PORTWHARF", 5);
					portWharfName = portHarbourCode + "-" + portWharfCode;
				}
				DmPortWharf dmPortWharfItem = DmPortWharfLocalServiceUtil.getByPortWharfCode(portWharfCode);

				if (dmPortWharfItem != null) {
					dmPortWharfItem.setPortWharfCode(portWharfCode);
					dmPortWharfItem.setPortWharfName(portWharfNameVN);
					dmPortWharfItem.setPortWharfNameVN(portWharfNameVN);
					dmPortWharfItem.setPortCode(portHarbourCodeName);
					dmPortWharfItem.setNote(remarks);
					dmPortWharfItem.setPortRegionCode(portRegionCode);
					dmPortWharfItem.setPortHarbourCode(portHarbourCode);

					dmPortWharfItem.setIsDelete(0);
					dmPortWharfItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmPortWharfItem.setModifiedDate(new Date());
					dmPortWharfItem.setSyncVersion("1|");
					DmPortWharfLocalServiceUtil.updateDmPortWharf(dmPortWharfItem);
				} else {
					details.setPortWharfCode(portWharfCode);
					details.setPortWharfName(portWharfNameVN);
					details.setPortWharfNameVN(portWharfNameVN);
					details.setIsDelete(0);
					details.setMarkedAsDelete(0);
					details.setRequestedDate(new Date());
					details.setModifiedDate(new Date());
					details.setSyncVersion("1|");
					DmPortWharfLocalServiceUtil.addDmPortWharf(details);
				}

				DmHistoryPortWharf dmHistoryPortWharfItem = DmHistoryPortWharfLocalServiceUtil
						.findByPortWharfCodeAndSyncVersion(portWharfCode, syncVersion);

				if (dmHistoryPortWharfItem != null) {
					dmHistoryPortWharfItem.setPortWharfCode(portWharfCode);
					dmHistoryPortWharfItem.setPortWharfName(portWharfNameVN);
					dmHistoryPortWharfItem.setPortWharfNameVN(portWharfNameVN);
					dmHistoryPortWharfItem.setPortCode(portHarbourCodeName);
					dmHistoryPortWharfItem.setNote(remarks);
					dmHistoryPortWharfItem.setPortRegionCode(portRegionCode);
					dmHistoryPortWharfItem.setPortHarbourCode(portHarbourCode);

					dmHistoryPortWharfItem.setIsDelete(0);
					dmHistoryPortWharfItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryPortWharfItem.setModifiedDate(new Date());
					dmHistoryPortWharfItem.setSyncVersion("1");
					history = DmHistoryPortWharfLocalServiceUtil.updateDmHistoryPortWharf(dmHistoryPortWharfItem);
				} else {
					history.setPortWharfCode(portWharfCode);
					history.setPortWharfName(portWharfNameVN);
					history.setPortWharfNameVN(portWharfNameVN);
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryPortWharfLocalServiceUtil.addDmHistoryPortWharf(history);
				}

			} else if (capsua.length() > 0) {
				log.info("messageType103  Sua");
				status = "1";
				DmPortWharf dmPortWharfItem = DmPortWharfLocalServiceUtil.getByPortWharfCode(portWharfCode);
				if (dmPortWharfItem != null) {
					dmPortWharfItem.setPortWharfCode(portWharfCode);
					dmPortWharfItem.setPortWharfName(portWharfNameVN);
					dmPortWharfItem.setPortWharfNameVN(portWharfNameVN);
					dmPortWharfItem.setPortCode(portHarbourCodeName);
					dmPortWharfItem.setNote(remarks);
					dmPortWharfItem.setPortRegionCode(portRegionCode);
					dmPortWharfItem.setPortHarbourCode(portHarbourCode);

					if (dmPortWharfItem.getIsDelete() == 1) {
						status = "2";
					}
					dmPortWharfItem.setIsDelete(0);
					dmPortWharfItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmPortWharfItem.setModifiedDate(new Date());
					dmPortWharfItem.setSyncVersion("1|");
					DmPortWharfLocalServiceUtil.updateDmPortWharf(dmPortWharfItem);
				}

				else {
					log.info("messageType103  Them/ Sua");
					details.setIsDelete(0);
					details.setMarkedAsDelete(0);
					details.setRequestedDate(new Date());
					details.setModifiedDate(new Date());
					details.setSyncVersion("1|");
					DmPortWharfLocalServiceUtil.addDmPortWharf(details);
				}

				DmHistoryPortWharf dmHistoryPortWharfItem = DmHistoryPortWharfLocalServiceUtil
						.findByPortWharfCodeAndSyncVersion(portWharfCode, syncVersion);

				if (dmHistoryPortWharfItem != null) {
					dmHistoryPortWharfItem.setPortWharfCode(portWharfCode);
					dmHistoryPortWharfItem.setPortWharfName(portWharfNameVN);
					dmHistoryPortWharfItem.setPortWharfNameVN(portWharfNameVN);
					dmHistoryPortWharfItem.setPortCode(portHarbourCodeName);
					dmHistoryPortWharfItem.setNote(remarks);
					dmHistoryPortWharfItem.setPortRegionCode(portRegionCode);
					dmHistoryPortWharfItem.setPortHarbourCode(portHarbourCode);

					dmHistoryPortWharfItem.setIsDelete(0);
					dmHistoryPortWharfItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryPortWharfItem.setModifiedDate(new Date());
					dmHistoryPortWharfItem.setSyncVersion("1");
					history = DmHistoryPortWharfLocalServiceUtil.updateDmHistoryPortWharf(dmHistoryPortWharfItem);
				} else {
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryPortWharfLocalServiceUtil.addDmHistoryPortWharf(history);
				}

			}

			result = true;
			int messageType103 = 103;
			log.info("==guiBanTinSangHQMC==dongbodanhmuc MessageType==" + messageType103);

			DMListSyn synch = new DMListSyn();
			synch.synchronizePortWharfList(messageType103, history, status, maritimeCode);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
//todo query in ActionController
//		PortletConfig portletConfig = (PortletConfig) resourceRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(resourceRequest,
//				portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
//		String backURL = ParamUtil.getString(resourceRequest, "backURL");
//		String redirectURL = ParamUtil.getString(resourceRequest, "redirectURL");
//
//		if (result && Validator.isNotNull(redirectURL)) {
//			actionResponse.sendRedirect(redirectURL);
//		} else if (!result && Validator.isNotNull(backURL)) {
//			actionResponse.sendRedirect(backURL);
//		}

	}

	public void updatePortRegion(ActionRequest resourceRequest, ActionResponse actionResponse) throws Exception {
		boolean result = false;
		try {
			String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
			log.info("====maritimeCode=====" + maritimeCode);
			String idCollection = ParamUtil.getString(resourceRequest, "idCollection");
			log.info("====idCollection=====" + idCollection);
			String requestCode = ParamUtil.getString(resourceRequest, "REQUEST_CODE");
			log.info("====PageType.REQUEST_CODE=====" + requestCode);
			String portCodeName = ParamUtil.getString(resourceRequest, "portCodeName");
			log.info("====portCodeName=====" + portCodeName);
			String portRegionCode = ParamUtil.getString(resourceRequest, "portRegionCode");
			log.info("====portRegionCode=====" + portRegionCode);
			String portRegionNameVN = ParamUtil.getString(resourceRequest, "portRegionNameVN");
			log.info("====portRegionNameVN=====" + portRegionNameVN);

			String versionNo = ParamUtil.getString(resourceRequest, "versionNo");
			log.info("====versionNo=====" + versionNo);
			String remarks = ParamUtil.getString(resourceRequest, "remarks");
			log.info("====remarks=====" + remarks);

			String status = "0";
			String capmoi = ParamUtil.getString(resourceRequest, PageType.LAN_CAP_MOI_DU_LIEU);
			String capsua = ParamUtil.getString(resourceRequest, PageType.LAN_SUA_DU_LIEU);
			String danhdauXoa = ParamUtil.getString(resourceRequest, PageType.LAN_XOA_DU_LIEU);
			log.info("====capmoi=====" + capmoi + "====capsua=====" + capsua + "====danhdauXoa=====" + danhdauXoa);

			log.info("messageType101 -- DM_PORT_REGION ");

			DmPortRegion details = new DmPortRegion();
			DmHistoryPortRegion history = new DmHistoryPortRegion();
			String syncVersion = "1";

			details.setPortRegionCode(portRegionCode);
			details.setPortRegionName(portRegionNameVN);
			details.setPortRegionNameVN(portRegionNameVN);
			details.setPortRegionRef(maritimeCode);
			details.setPortCode(portCodeName);

			history.setPortRegionCode(portRegionCode);
			history.setPortRegionName(portRegionNameVN);
			history.setPortRegionNameVN(portRegionNameVN);
			history.setPortRegionRef(maritimeCode);
			history.setPortCode(portCodeName);
			history.setSyncVersion(syncVersion);

			if (danhdauXoa.length() > 0) {
				log.info("messageType101  Xoa");
				status = "0";
				DmPortRegion dmPortRegionItem = DmPortRegionLocalServiceUtil.getByPortRegionCode(portRegionCode);
				dmPortRegionItem.setIsDelete(1);
				dmPortRegionItem.setMarkedAsDelete(1);
				// dmPortitem.setRequestedDate(new Date());
				dmPortRegionItem.setModifiedDate(new Date());
				dmPortRegionItem.setSyncVersion("1|");
				DmPortRegionLocalServiceUtil.updateDmPortRegion(dmPortRegionItem);

				DmHistoryPortRegion dmHistoryPortRegionItem = DmHistoryPortRegionLocalServiceUtil
						.findByPortRegionCodeAndSyncVersion(portRegionCode, syncVersion);
				dmHistoryPortRegionItem.setIsDelete(1);
				dmHistoryPortRegionItem.setMarkedAsDelete(1);
				// dmPortitem.setRequestedDate(new Date());
				dmHistoryPortRegionItem.setModifiedDate(new Date());
				dmHistoryPortRegionItem.setSyncVersion("1");
				history = DmHistoryPortRegionLocalServiceUtil.updateDmHistoryPortRegion(dmHistoryPortRegionItem);

			} else if (capmoi.length() > 0) {
				log.info("messageType101 Them");
				status = "2";
				if (portRegionCode.trim().length() == 0) {
					portRegionCode = ReportsBusinessUtils.taoSo("PORTREGION", 3);
				}
				DmPortRegion dmPortRegionItem = DmPortRegionLocalServiceUtil.getByPortRegionCode(portRegionCode);

				if (dmPortRegionItem != null) {
					dmPortRegionItem.setPortRegionCode(portRegionCode);
					dmPortRegionItem.setPortRegionName(portRegionNameVN);
					dmPortRegionItem.setPortRegionNameVN(portRegionNameVN);
					dmPortRegionItem.setPortRegionRef(maritimeCode);
					dmPortRegionItem.setPortCode(portCodeName);

					dmPortRegionItem.setIsDelete(0);
					dmPortRegionItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmPortRegionItem.setModifiedDate(new Date());
					dmPortRegionItem.setSyncVersion("1|");
					DmPortRegionLocalServiceUtil.updateDmPortRegion(dmPortRegionItem);
				} else {
					details.setPortRegionCode(portRegionCode);
					details.setIsDelete(0);
					details.setMarkedAsDelete(0);
					details.setRequestedDate(new Date());
					details.setModifiedDate(new Date());
					details.setSyncVersion("1|");
					DmPortRegionLocalServiceUtil.addDmPortRegion(details);
				}

				DmHistoryPortRegion dmHistoryPortRegionItem = DmHistoryPortRegionLocalServiceUtil
						.findByPortRegionCodeAndSyncVersion(portRegionCode, syncVersion);

				if (dmHistoryPortRegionItem != null) {
					dmHistoryPortRegionItem.setPortRegionCode(portRegionCode);
					dmHistoryPortRegionItem.setPortRegionName(portRegionNameVN);
					dmHistoryPortRegionItem.setPortRegionNameVN(portRegionNameVN);
					dmHistoryPortRegionItem.setPortRegionRef(maritimeCode);
					dmHistoryPortRegionItem.setPortCode(portCodeName);

					dmHistoryPortRegionItem.setIsDelete(0);
					dmHistoryPortRegionItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryPortRegionItem.setModifiedDate(new Date());
					dmHistoryPortRegionItem.setSyncVersion("1");
					history = DmHistoryPortRegionLocalServiceUtil.updateDmHistoryPortRegion(dmHistoryPortRegionItem);
				} else {
					history.setPortRegionCode(portRegionCode);
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryPortRegionLocalServiceUtil.addDmHistoryPortRegion(history);
				}

			} else if (capsua.length() > 0) {
				log.info("messageType101  Sua");
				status = "1";
				DmPortRegion dmPortRegionItem = DmPortRegionLocalServiceUtil.getByPortRegionCode(portRegionCode);
				if (dmPortRegionItem != null) {
					dmPortRegionItem.setPortRegionCode(portRegionCode);
					dmPortRegionItem.setPortRegionName(portRegionNameVN);
					dmPortRegionItem.setPortRegionNameVN(portRegionNameVN);
					dmPortRegionItem.setPortRegionRef(maritimeCode);
					dmPortRegionItem.setPortCode(portCodeName);

					if (dmPortRegionItem.getIsDelete() == 1) {
						status = "2";
					}
					dmPortRegionItem.setIsDelete(0);
					dmPortRegionItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmPortRegionItem.setModifiedDate(new Date());
					dmPortRegionItem.setSyncVersion("1|");
					DmPortRegionLocalServiceUtil.updateDmPortRegion(dmPortRegionItem);
				}

				else {
					log.info("messageType101  Them/ Sua");
					details.setIsDelete(0);
					details.setMarkedAsDelete(0);
					details.setRequestedDate(new Date());
					details.setModifiedDate(new Date());
					details.setSyncVersion("1|");
					DmPortRegionLocalServiceUtil.addDmPortRegion(details);
				}

				DmHistoryPortRegion dmHistoryPortRegionItem = DmHistoryPortRegionLocalServiceUtil
						.findByPortRegionCodeAndSyncVersion(portRegionCode, syncVersion);

				if (dmHistoryPortRegionItem != null) {
					dmHistoryPortRegionItem.setPortRegionCode(portRegionCode);
					dmHistoryPortRegionItem.setPortRegionName(portRegionNameVN);
					dmHistoryPortRegionItem.setPortRegionNameVN(portRegionNameVN);
					dmHistoryPortRegionItem.setPortRegionRef(maritimeCode);
					dmHistoryPortRegionItem.setPortCode(portCodeName);

					dmHistoryPortRegionItem.setIsDelete(0);
					dmHistoryPortRegionItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryPortRegionItem.setModifiedDate(new Date());
					dmHistoryPortRegionItem.setSyncVersion("1");
					history = DmHistoryPortRegionLocalServiceUtil.updateDmHistoryPortRegion(dmHistoryPortRegionItem);
				} else {
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryPortRegionLocalServiceUtil.addDmHistoryPortRegion(history);
				}

			}

			result = true;
			int messageType101 = 101;
			log.info("==guiBanTinSangHQMC==dongbodanhmuc MessageType==" + messageType101);

			DMListSyn synch = new DMListSyn();
			synch.synchronizePortRegionList(messageType101, history, status, maritimeCode);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
////todo query in ActionController
//		PortletConfig portletConfig = (PortletConfig) resourceRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(resourceRequest,
//				portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
//		String backURL = ParamUtil.getString(resourceRequest, "backURL");
//		String redirectURL = ParamUtil.getString(resourceRequest, "redirectURL");
//
//		if (result && Validator.isNotNull(redirectURL)) {
//			actionResponse.sendRedirect(redirectURL);
//		} else if (!result && Validator.isNotNull(backURL)) {
//			actionResponse.sendRedirect(backURL);
//		}

	}

	public void updatePortHarbour(ActionRequest resourceRequest, ActionResponse actionResponse) throws Exception {
		boolean result = false;
		try {
			String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
			log.info("====maritimeCode=====" + maritimeCode);
			String idCollection = ParamUtil.getString(resourceRequest, "idCollection");
			log.info("====idCollection=====" + idCollection);
			String requestCode = ParamUtil.getString(resourceRequest, "REQUEST_CODE");
			log.info("====PageType.REQUEST_CODE=====" + requestCode);
			String portCodeName = ParamUtil.getString(resourceRequest, "portCodeName");
			log.info("====portCodeName=====" + portCodeName);
			String portRegionCode = ParamUtil.getString(resourceRequest, "portRegionCode");
			log.info("====portRegionCode=====" + portRegionCode);
			String portHarbourCode = ParamUtil.getString(resourceRequest, "portHarbourCode");
			log.info("====portHarbourCode=====" + portHarbourCode);
			String portHarbourNameVN = ParamUtil.getString(resourceRequest, "portHarbourNameVN");
			log.info("====portHarbourNameVN=====" + portHarbourNameVN);
			String portRegionName = ParamUtil.getString(resourceRequest, "portHarbourNameVN");
			log.info("====portRegionName=====" + portHarbourNameVN);

			String versionNo = ParamUtil.getString(resourceRequest, "versionNo");
			log.info("====versionNo=====" + versionNo);
			String remarks = ParamUtil.getString(resourceRequest, "remarks");
			log.info("====remarks=====" + remarks);

			String status = "0";
			String capmoi = ParamUtil.getString(resourceRequest, PageType.LAN_CAP_MOI_DU_LIEU);
			String capsua = ParamUtil.getString(resourceRequest, PageType.LAN_SUA_DU_LIEU);
			String danhdauXoa = ParamUtil.getString(resourceRequest, PageType.LAN_XOA_DU_LIEU);
			log.info("====capmoi=====" + capmoi + "====capsua=====" + capsua + "====danhdauXoa=====" + danhdauXoa);

			log.info("messageType102 -- DM_PORT_HARBOUR ");

			DmPortHarbour details = new DmPortHarbour();
			DmHistoryPortHarbour history = new DmHistoryPortHarbour();
			String syncVersion = "1";

			details.setPortHarbourCode(portHarbourCode);
			details.setPortHarbourName(portHarbourNameVN);
			details.setPortHarbourNameVN(portHarbourNameVN);
			details.setPortHarbourType(1);
			details.setNote(remarks);
			details.setPortCode(portCodeName);
			details.setPortRegionCode(maritimeCode);
			details.setPortRegion(portRegionCode);

			history.setPortHarbourCode(portHarbourCode);
			history.setPortHarbourName(portHarbourNameVN);
			history.setPortHarbourNameVN(portHarbourNameVN);
			history.setPortHarbourType(1);
			history.setNote(remarks);
			history.setPortCode(portCodeName);
			history.setPortRegionCode(maritimeCode);
			history.setPortRegion(portRegionCode);
			history.setSyncVersion(syncVersion);

			if (danhdauXoa.length() > 0) {
				log.info("messageType102  Xoa");
				status = "0";
				DmPortHarbour dmPortHarbourItem = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(portHarbourCode);
				dmPortHarbourItem.setIsDelete(1);
				dmPortHarbourItem.setMarkedAsDelete(1);
				// dmPortitem.setRequestedDate(new Date());
				dmPortHarbourItem.setModifiedDate(new Date());
				dmPortHarbourItem.setSyncVersion("1|");
				DmPortHarbourLocalServiceUtil.updateDmPortHarbour(dmPortHarbourItem);

				DmHistoryPortHarbour dmHistoryPortHarbourItem = DmHistoryPortHarbourLocalServiceUtil
						.findByPortHarbourCodeAndSyncVersion(portHarbourCode, syncVersion);
				dmHistoryPortHarbourItem.setIsDelete(1);
				dmHistoryPortHarbourItem.setMarkedAsDelete(1);
				// dmPortitem.setRequestedDate(new Date());
				dmHistoryPortHarbourItem.setModifiedDate(new Date());
				dmHistoryPortHarbourItem.setSyncVersion("1");
				history = DmHistoryPortHarbourLocalServiceUtil.updateDmHistoryPortHarbour(dmHistoryPortHarbourItem);

			} else if (capmoi.length() > 0) {
				log.info("messageType102 Them");
				status = "2";
				if (portHarbourCode.trim().length() == 0) {
					portHarbourCode = ReportsBusinessUtils.taoSo("PORTHARBOUR", "BC", 3);
				}
				DmPortHarbour dmPortHarbourItem = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(portHarbourCode);

				if (dmPortHarbourItem != null) {
					dmPortHarbourItem.setPortHarbourCode(portHarbourCode);
					dmPortHarbourItem.setPortHarbourName(portHarbourNameVN);
					dmPortHarbourItem.setPortHarbourNameVN(portHarbourNameVN);
					dmPortHarbourItem.setPortHarbourType(1);
					dmPortHarbourItem.setNote(remarks);
					dmPortHarbourItem.setPortCode(portCodeName);
					dmPortHarbourItem.setPortRegionCode(maritimeCode);
					dmPortHarbourItem.setPortRegion(portRegionCode);

					dmPortHarbourItem.setIsDelete(0);
					dmPortHarbourItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmPortHarbourItem.setModifiedDate(new Date());
					dmPortHarbourItem.setSyncVersion("1|");
					DmPortHarbourLocalServiceUtil.updateDmPortHarbour(dmPortHarbourItem);
				} else {
					details.setPortHarbourCode(portHarbourCode);
					details.setIsDelete(0);
					details.setMarkedAsDelete(0);
					details.setRequestedDate(new Date());
					details.setModifiedDate(new Date());
					details.setSyncVersion("1|");
					DmPortHarbourLocalServiceUtil.addDmPortHarbour(details);
				}

				DmHistoryPortHarbour dmHistoryPortHarbourItem = DmHistoryPortHarbourLocalServiceUtil
						.findByPortHarbourCodeAndSyncVersion(portHarbourCode, syncVersion);

				if (dmHistoryPortHarbourItem != null) {
					dmHistoryPortHarbourItem.setPortHarbourCode(portHarbourCode);
					dmHistoryPortHarbourItem.setPortHarbourName(portHarbourNameVN);
					dmHistoryPortHarbourItem.setPortHarbourNameVN(portHarbourNameVN);
					dmHistoryPortHarbourItem.setPortHarbourType(1);
					dmHistoryPortHarbourItem.setNote(remarks);
					dmHistoryPortHarbourItem.setPortCode(portCodeName);
					dmHistoryPortHarbourItem.setPortRegionCode(maritimeCode);
					dmHistoryPortHarbourItem.setPortRegion(portRegionCode);

					dmHistoryPortHarbourItem.setIsDelete(0);
					dmHistoryPortHarbourItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryPortHarbourItem.setModifiedDate(new Date());
					dmHistoryPortHarbourItem.setSyncVersion("1");
					history = DmHistoryPortHarbourLocalServiceUtil.updateDmHistoryPortHarbour(dmHistoryPortHarbourItem);
				} else {
					history.setPortHarbourCode(portHarbourCode);
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryPortHarbourLocalServiceUtil.addDmHistoryPortHarbour(history);
				}

			} else if (capsua.length() > 0) {
				log.info("messageType102  Sua");
				status = "1";
				DmPortHarbour dmPortHarbourItem = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(portHarbourCode);
				if (dmPortHarbourItem != null) {
					dmPortHarbourItem.setPortHarbourCode(portHarbourCode);
					dmPortHarbourItem.setPortHarbourName(portHarbourNameVN);
					dmPortHarbourItem.setPortHarbourNameVN(portHarbourNameVN);
					dmPortHarbourItem.setPortHarbourType(1);
					dmPortHarbourItem.setNote(remarks);
					dmPortHarbourItem.setPortCode(portCodeName);
					dmPortHarbourItem.setPortRegionCode(maritimeCode);
					dmPortHarbourItem.setPortRegion(portRegionCode);

					if (dmPortHarbourItem.getIsDelete() == 1) {
						status = "2";
					}
					dmPortHarbourItem.setIsDelete(0);
					dmPortHarbourItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmPortHarbourItem.setModifiedDate(new Date());
					dmPortHarbourItem.setSyncVersion("1|");
					DmPortHarbourLocalServiceUtil.updateDmPortHarbour(dmPortHarbourItem);
				}

				else {
					log.info("messageType101  Them/ Sua");
					details.setIsDelete(0);
					details.setMarkedAsDelete(0);
					details.setRequestedDate(new Date());
					details.setModifiedDate(new Date());
					details.setSyncVersion("1|");
					DmPortHarbourLocalServiceUtil.addDmPortHarbour(details);
				}

				DmHistoryPortHarbour dmHistoryPortHarbourItem = DmHistoryPortHarbourLocalServiceUtil
						.findByPortHarbourCodeAndSyncVersion(portHarbourCode, syncVersion);

				if (dmHistoryPortHarbourItem != null) {
					dmHistoryPortHarbourItem.setPortHarbourCode(portHarbourCode);
					dmHistoryPortHarbourItem.setPortHarbourName(portHarbourNameVN);
					dmHistoryPortHarbourItem.setPortHarbourNameVN(portHarbourNameVN);
					dmHistoryPortHarbourItem.setPortHarbourType(1);
					dmHistoryPortHarbourItem.setNote(remarks);
					dmHistoryPortHarbourItem.setPortCode(portCodeName);
					dmHistoryPortHarbourItem.setPortRegionCode(maritimeCode);
					dmHistoryPortHarbourItem.setPortRegion(portRegionCode);

					dmHistoryPortHarbourItem.setIsDelete(0);
					dmHistoryPortHarbourItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryPortHarbourItem.setModifiedDate(new Date());
					dmHistoryPortHarbourItem.setSyncVersion("1");
					history = DmHistoryPortHarbourLocalServiceUtil.updateDmHistoryPortHarbour(dmHistoryPortHarbourItem);
				} else {
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryPortHarbourLocalServiceUtil.addDmHistoryPortHarbour(history);
				}

			}

			result = true;
			int messageType102 = 102;
			log.info("==guiBanTinSangHQMC==dongbodanhmuc MessageType==" + messageType102);

			DMListSyn synch = new DMListSyn();
			synch.synchronizePortHarbourList(messageType102, history, status, maritimeCode);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
//todo query in ActionController
//		PortletConfig portletConfig = (PortletConfig) resourceRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(resourceRequest,
//				portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
//		String backURL = ParamUtil.getString(resourceRequest, "backURL");
//		String redirectURL = ParamUtil.getString(resourceRequest, "redirectURL");
//
//		if (result && Validator.isNotNull(redirectURL)) {
//			actionResponse.sendRedirect(redirectURL);
//		} else if (!result && Validator.isNotNull(backURL)) {
//			actionResponse.sendRedirect(backURL);
//		}

	}

	public void updateTuyenLuong(ActionRequest resourceRequest, ActionResponse actionResponse) throws Exception {
		boolean result = false;
		try {
			long idCollection = ParamUtil.getLong(resourceRequest, "idCollection");
			String code = ParamUtil.getString(resourceRequest, "code");
			log.info("====code=====" + code);
			String nameS = ParamUtil.getString(resourceRequest, "nameS");
			log.info("====nameS=====" + nameS);
			String maritimeportCode = ParamUtil.getString(resourceRequest, "maritimeportCode");
			
			String capmoi = ParamUtil.getString(resourceRequest, PageType.LAN_CAP_MOI_DU_LIEU);
			String capsua = ParamUtil.getString(resourceRequest, PageType.LAN_SUA_DU_LIEU);
			String danhdauXoa = ParamUtil.getString(resourceRequest, PageType.LAN_XOA_DU_LIEU);
			
			if (danhdauXoa.length() > 0) {
				log.info("messageType100  Xoa");
				DmDataitem dataItem = DmDataItemLocalServiceUtil.fetchDmDataItem(idCollection);
				dataItem.setStatus(0);
				
				DmDataItemLocalServiceUtil.updateDmDataItem(dataItem);

			} else if (capmoi.length() > 0) {
				DmDataitem dataItem = new DmDataitem();
				dataItem.setDatagroupid(130);
				dataItem.setNode1(maritimeportCode);
				dataItem.setCode(code);
				dataItem.setName(nameS);
				dataItem.setLevel(1);
				dataItem.setValidatedfrom(new Date());
				dataItem.setStatus(1);
				dataItem.setOrder(1);
				
				DmDataItemLocalServiceUtil.updateDmDataItem(dataItem);
			} else if (capsua.length() > 0) {
				DmDataitem dataItem = DmDataItemLocalServiceUtil.fetchDmDataItem(idCollection);
				dataItem.setCode(code);
				dataItem.setName(nameS);
				
				DmDataItemLocalServiceUtil.updateDmDataItem(dataItem);
			}

			result = true;
			int messageType100 = 100;
			log.info("==guiBanTinSangHQMC==dongbodanhmuc MessageType==" + messageType100);

//			DMListSyn synch = new DMListSyn();
//			synch.synchronizePortOfAuthorityList(messageType, history, status);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
//todo query in ActionController
//		PortletConfig portletConfig = (PortletConfig) resourceRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(resourceRequest,
//				portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
//		String backURL = ParamUtil.getString(resourceRequest, "backURL");
//		String redirectURL = ParamUtil.getString(resourceRequest, "redirectURL");
//
//		if (result && Validator.isNotNull(redirectURL)) {
//			actionResponse.sendRedirect(redirectURL);
//		} else if (!result && Validator.isNotNull(backURL)) {
//			actionResponse.sendRedirect(backURL);
//		}

	}

	public void updateMaritime(ActionRequest resourceRequest, ActionResponse actionResponse) throws Exception {
		boolean result = false;
		try {
			String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
			log.info("====maritimeCode=====" + maritimeCode);
			String idCollection = ParamUtil.getString(resourceRequest, "idCollection");
			log.info("====idCollection=====" + idCollection);
			String requestCode = ParamUtil.getString(resourceRequest, "REQUEST_CODE");
			log.info("====PageType.REQUEST_CODE=====" + requestCode);

			String MaritimeReference = ParamUtil.getString(resourceRequest, "MaritimeReference");
			log.info("====MaritimeReference=====" + MaritimeReference);
			String MaritimeOrder = ParamUtil.getString(resourceRequest, "MaritimeOrder");
			log.info("====MaritimeOrder=====" + MaritimeOrder);
			String MaritimeName = ParamUtil.getString(resourceRequest, "MaritimeName");
			log.info("====MaritimeName=====" + MaritimeName);
			String MaritimeNameVN = ParamUtil.getString(resourceRequest, "MaritimeNameVN");
			log.info("====MaritimeNameVN=====" + MaritimeNameVN);
			String Address = ParamUtil.getString(resourceRequest, "Address");
			log.info("====Address=====" + Address);
			String StateCode = ParamUtil.getString(resourceRequest, "StateCode");
			log.info("====StateCode=====" + StateCode);
			String CityCode = ParamUtil.getString(resourceRequest, "CityCode");
			log.info("====CityCode=====" + CityCode);

			String versionNo = ParamUtil.getString(resourceRequest, "versionNo");
			log.info("====versionNo=====" + versionNo);
			// String remarks = ParamUtil.getString(resourceRequest, "remarks");
			// log.info("====remarks=====" + remarks);

			String status = "0";
			String capmoi = ParamUtil.getString(resourceRequest, PageType.LAN_CAP_MOI_DU_LIEU);
			String capsua = ParamUtil.getString(resourceRequest, PageType.LAN_SUA_DU_LIEU);
			String danhdauXoa = ParamUtil.getString(resourceRequest, PageType.LAN_XOA_DU_LIEU);
			log.info("====capmoi=====" + capmoi + "====capsua=====" + capsua + "====danhdauXoa=====" + danhdauXoa);

			log.info("messageType100 -- DM_MARITIME ");

			DmMaritime details = new DmMaritime();
			DmHistoryMaritime history = new DmHistoryMaritime();
			String syncVersion = "1";

			details.setMaritimeCode(maritimeCode);
			details.setMaritimeName(MaritimeName);
			details.setMaritimeNameVN(MaritimeNameVN);
			details.setMaritimeReference(MaritimeReference);
			details.setMaritimeOrder(100);
			details.setAddress(Address);
			details.setAddressVN(Address);
			details.setStateCode(StateCode);
			details.setCityCode(CityCode);

			history.setMaritimeCode(maritimeCode);
			history.setMaritimeName(MaritimeName);
			history.setMaritimeNameVN(MaritimeNameVN);
			history.setMaritimeReference(MaritimeReference);
			history.setMaritimeOrder(100);
			history.setAddress(Address);
			history.setAddressVN(Address);
			history.setStateCode(StateCode);
			history.setCityCode(CityCode);
			history.setSyncVersion(syncVersion);

			if (danhdauXoa.length() > 0) {
				log.info("messageType100  Xoa");
				status = "0";
				DmMaritime dmMaritimeItem = DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode);
				dmMaritimeItem.setIsDelete(1);
				dmMaritimeItem.setMarkedAsDelete(1);
				// dmPortitem.setRequestedDate(new Date());
				dmMaritimeItem.setModifiedDate(new Date());
				dmMaritimeItem.setSyncVersion("1|");
				DmMaritimeLocalServiceUtil.updateDmMaritime(dmMaritimeItem);

				DmHistoryMaritime dmHistoryMaritimeItem = DmHistoryMaritimeLocalServiceUtil
						.getByMaritimeCodeAndSyncVersion(maritimeCode, syncVersion);
				dmHistoryMaritimeItem.setIsDelete(1);
				dmHistoryMaritimeItem.setMarkedAsDelete(1);
				// dmPortitem.setRequestedDate(new Date());
				dmHistoryMaritimeItem.setModifiedDate(new Date());
				dmHistoryMaritimeItem.setSyncVersion("1");
				history = DmHistoryMaritimeLocalServiceUtil.updateDmHistoryMaritime(dmHistoryMaritimeItem);

			} else if (capmoi.length() > 0) {
				log.info("messageType100 Them");
				status = "2";
				if (maritimeCode.trim().length() == 0) {
					maritimeCode = ReportsBusinessUtils.taoSo("PORTAUTHORITY", 2);
				}

				DmMaritime dmMaritimeItem = DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode);

				if (dmMaritimeItem != null) {
					dmMaritimeItem.setMaritimeCode(maritimeCode);
					dmMaritimeItem.setMaritimeName(MaritimeName);
					dmMaritimeItem.setMaritimeNameVN(MaritimeNameVN);
					dmMaritimeItem.setMaritimeReference(MaritimeReference);
					dmMaritimeItem.setMaritimeOrder(100);
					dmMaritimeItem.setAddress(Address);
					dmMaritimeItem.setAddressVN(Address);
					dmMaritimeItem.setStateCode(StateCode);
					dmMaritimeItem.setCityCode(CityCode);

					dmMaritimeItem.setIsDelete(0);
					dmMaritimeItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmMaritimeItem.setModifiedDate(new Date());
					dmMaritimeItem.setSyncVersion("1|");
					DmMaritimeLocalServiceUtil.updateDmMaritime(dmMaritimeItem);
				} else {
					details.setMaritimeCode(maritimeCode);
					details.setIsDelete(0);
					details.setMarkedAsDelete(0);
					details.setRequestedDate(new Date());
					details.setModifiedDate(new Date());
					details.setSyncVersion("1|");
					DmMaritimeLocalServiceUtil.addDmMaritime(details);
				}

				DmHistoryMaritime dmHistoryMaritimeItem = DmHistoryMaritimeLocalServiceUtil
						.getByMaritimeCodeAndSyncVersion(maritimeCode, syncVersion);

				if (dmHistoryMaritimeItem != null) {
					dmHistoryMaritimeItem.setMaritimeCode(maritimeCode);
					dmHistoryMaritimeItem.setMaritimeName(MaritimeName);
					dmHistoryMaritimeItem.setMaritimeNameVN(MaritimeNameVN);
					dmHistoryMaritimeItem.setMaritimeReference(MaritimeReference);
					dmHistoryMaritimeItem.setMaritimeOrder(100);
					dmHistoryMaritimeItem.setAddress(Address);
					dmHistoryMaritimeItem.setAddressVN(Address);
					dmHistoryMaritimeItem.setStateCode(StateCode);
					dmHistoryMaritimeItem.setCityCode(CityCode);

					dmHistoryMaritimeItem.setIsDelete(0);
					dmHistoryMaritimeItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryMaritimeItem.setModifiedDate(new Date());
					dmHistoryMaritimeItem.setSyncVersion("1");
					history = DmHistoryMaritimeLocalServiceUtil.updateDmHistoryMaritime(dmHistoryMaritimeItem);
				} else {
					history.setMaritimeCode(maritimeCode);
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryMaritimeLocalServiceUtil.addDmHistoryMaritime(history);
				}

			} else if (capsua.length() > 0) {
				log.info("messageType100  Sua");
				status = "1";
				DmMaritime dmMaritimeItem = DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode);
				if (dmMaritimeItem != null) {
					dmMaritimeItem.setMaritimeCode(maritimeCode);
					dmMaritimeItem.setMaritimeName(MaritimeName);
					dmMaritimeItem.setMaritimeNameVN(MaritimeNameVN);
					dmMaritimeItem.setMaritimeReference(MaritimeReference);
					dmMaritimeItem.setMaritimeOrder(100);
					dmMaritimeItem.setAddress(Address);
					dmMaritimeItem.setAddressVN(Address);
					dmMaritimeItem.setStateCode(StateCode);
					dmMaritimeItem.setCityCode(CityCode);

					if (dmMaritimeItem.getIsDelete() == 1) {
						status = "2";
					}
					dmMaritimeItem.setIsDelete(0);
					dmMaritimeItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmMaritimeItem.setModifiedDate(new Date());
					dmMaritimeItem.setSyncVersion("1|");
					DmMaritimeLocalServiceUtil.updateDmMaritime(dmMaritimeItem);
				}

				else {
					log.info("messageType100  Them/ Sua");
					details.setIsDelete(0);
					details.setMarkedAsDelete(0);
					details.setRequestedDate(new Date());
					details.setModifiedDate(new Date());
					details.setSyncVersion("1|");
					DmMaritimeLocalServiceUtil.addDmMaritime(details);
				}

				DmHistoryMaritime dmHistoryMaritimeItem = DmHistoryMaritimeLocalServiceUtil
						.getByMaritimeCodeAndSyncVersion(maritimeCode, syncVersion);

				if (dmHistoryMaritimeItem != null) {
					dmHistoryMaritimeItem.setMaritimeCode(maritimeCode);
					dmHistoryMaritimeItem.setMaritimeName(MaritimeName);
					dmHistoryMaritimeItem.setMaritimeNameVN(MaritimeNameVN);
					dmHistoryMaritimeItem.setMaritimeReference(MaritimeReference);
					dmHistoryMaritimeItem.setMaritimeOrder(100);
					dmHistoryMaritimeItem.setAddress(Address);
					dmHistoryMaritimeItem.setAddressVN(Address);
					dmHistoryMaritimeItem.setStateCode(StateCode);
					dmHistoryMaritimeItem.setCityCode(CityCode);

					dmHistoryMaritimeItem.setIsDelete(0);
					dmHistoryMaritimeItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryMaritimeItem.setModifiedDate(new Date());
					dmHistoryMaritimeItem.setSyncVersion("1");
					history = DmHistoryMaritimeLocalServiceUtil.updateDmHistoryMaritime(dmHistoryMaritimeItem);
				} else {
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryMaritimeLocalServiceUtil.addDmHistoryMaritime(history);
				}

			}

			result = true;
			int messageType100 = 100;
			log.info("==guiBanTinSangHQMC==dongbodanhmuc MessageType==" + messageType100);

			DMListSyn synch = new DMListSyn();
			synch.synchronizePortOfAuthorityList(messageType100, history, status, maritimeCode);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
//todo query in ActionController
//		PortletConfig portletConfig = (PortletConfig) resourceRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(resourceRequest,
//				portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
//		String backURL = ParamUtil.getString(resourceRequest, "backURL");
//		String redirectURL = ParamUtil.getString(resourceRequest, "redirectURL");
//
//		if (result && Validator.isNotNull(redirectURL)) {
//			actionResponse.sendRedirect(redirectURL);
//		} else if (!result && Validator.isNotNull(backURL)) {
//			actionResponse.sendRedirect(backURL);
//		}

	}

	public void updateReferencePortItem107(ActionRequest resourceRequest, ActionResponse actionResponse)
			throws Exception {
		boolean result = false;
		try {
			String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
			log.info("====maritimeCode=====" + maritimeCode);
			String idCollection = ParamUtil.getString(resourceRequest, "idCollection");
			log.info("====idCollection=====" + idCollection);
			String requestCode = ParamUtil.getString(resourceRequest, "REQUEST_CODE");
			log.info("====PageType.REQUEST_CODE=====" + requestCode);
			String DataItem107Code = ParamUtil.getString(resourceRequest, "DataItem107Code");
			log.info("====DataItem107Code=====" + DataItem107Code);
			String DataItem107Name = ParamUtil.getString(resourceRequest, "DataItem107Name");
			log.info("====DataItem107Name=====" + DataItem107Name);

			String DataItem107Node2 = ParamUtil.getString(resourceRequest, "DataItem107Node2");
			log.info("====DataItem107Node2=====" + DataItem107Node2);
			String versionNo = ParamUtil.getString(resourceRequest, "versionNo");
			log.info("====versionNo=====" + versionNo);
			String remarks = ParamUtil.getString(resourceRequest, "remarks");
			log.info("====remarks=====" + remarks);

			int DataGroupId = MessageType.NHOM_DM_CANG_BIEN_HANG_HAI;
			int itemLevel = 0;
			int itemOrder = 0;
			int itemStatus = 1;
			String DataItem107Node1 = maritimeCode;
			String DataItem107ShortName = DataItem107Node2;
			DmMaritime dmMaritimeItem = DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode);

			if (dmMaritimeItem != null) {
				DataItem107Code = dmMaritimeItem.getMaritimeReference();
			}
			String status = "0";
			String capmoi = ParamUtil.getString(resourceRequest, PageType.LAN_CAP_MOI_DU_LIEU);
			String capsua = ParamUtil.getString(resourceRequest, PageType.LAN_SUA_DU_LIEU);
			String danhdauXoa = ParamUtil.getString(resourceRequest, PageType.LAN_XOA_DU_LIEU);
			log.info("====capmoi=====" + capmoi + "====capsua=====" + capsua + "====danhdauXoa=====" + danhdauXoa);

			log.info("messageType105 -- DM_DATAITEM_GROUP107 ");

			DmDataitem details = new DmDataitem();

			String syncVersion = "1";

			details.setDatagroupid(DataGroupId);
			details.setCode(DataItem107Code);
			details.setName(DataItem107Name);
			details.setDescription(remarks);
			details.setNode1(DataItem107Node1);
			details.setNode2(DataItem107Node2);
			details.setShortName(DataItem107ShortName);
			details.setLevel(itemLevel);
			details.setOrder(itemOrder);
			details.setStatus(itemStatus);
			details.setValidatedfrom(new Date());
			details.setValidatedto(null);

			if (danhdauXoa.length() > 0) {
				log.info("messageType105  Xoa");
				status = "0";
				DmDataitem dmDataItemItem = new DmDataitem();
				List<DmDataitem> dmDataItem = DmDataItemLocalServiceUtil.findByDataGroupIdAndShortName(DataGroupId,
						DataItem107ShortName);
				if (dmDataItem != null && dmDataItem.size() > 0) {
					dmDataItemItem = dmDataItem.get(0);

					dmDataItemItem.setStatus(0);
					dmDataItemItem.setValidatedto(new Date());
					DmDataItemLocalServiceUtil.updateDmDataItem(dmDataItemItem);
				}

			} else if (capmoi.length() > 0) {
				log.info("messageType105 Them");
				status = "2";
				DmDataitem dmDataItemItem = null;
				if (DataItem107Node2.trim().length() == 0) {
					DataItem107Node2 = ReportsBusinessUtils.taoSo("PORTCODE", DataItem107Code, 2);
					DataItem107ShortName = DataItem107Node2;
				}

				List<DmDataitem> dmDataItem = DmDataItemLocalServiceUtil.findByDataGroupIdAndShortName(DataGroupId,
						DataItem107ShortName);
				if (dmDataItem != null && dmDataItem.size() > 0) {
					dmDataItemItem = dmDataItem.get(0);
				}

				if (dmDataItemItem != null) {
					dmDataItemItem.setDatagroupid(DataGroupId);
					dmDataItemItem.setCode(DataItem107Code);
					dmDataItemItem.setName(DataItem107Name);
					dmDataItemItem.setDescription(remarks);
					dmDataItemItem.setNode1(DataItem107Node1);
					dmDataItemItem.setNode2(DataItem107Node2);
					dmDataItemItem.setShortName(DataItem107ShortName);
					dmDataItemItem.setLevel(itemLevel);
					dmDataItemItem.setOrder(itemOrder);
					dmDataItemItem.setStatus(itemStatus);
					dmDataItemItem.setValidatedfrom(new Date());
					dmDataItemItem.setValidatedto(null);
					DmDataItemLocalServiceUtil.updateDmDataItem(dmDataItemItem);
				} else {
					details.setNode2(DataItem107Node2);
					details.setShortName(DataItem107ShortName);
					DmDataItemLocalServiceUtil.addDmDataitem(details);
				}
			} else if (capsua.length() > 0) {
				log.info("messageType105  Sua");
				status = "1";
				DmDataitem dmDataItemItem = null;
				List<DmDataitem> dmDataItem = DmDataItemLocalServiceUtil.findByDataGroupIdAndShortName(DataGroupId,
						DataItem107ShortName);
				if (dmDataItem != null && dmDataItem.size() > 0) {
					dmDataItemItem = dmDataItem.get(0);
				}

				if (dmDataItemItem != null) {
					if (dmDataItemItem.getStatus() == 0) {
						status = "2";
					}

					dmDataItemItem.setDatagroupid(DataGroupId);
					dmDataItemItem.setCode(DataItem107Code);
					dmDataItemItem.setName(DataItem107Name);
					dmDataItemItem.setDescription(remarks);
					dmDataItemItem.setNode1(DataItem107Node1);
					dmDataItemItem.setNode2(DataItem107Node2);
					dmDataItemItem.setShortName(DataItem107ShortName);
					dmDataItemItem.setLevel(itemLevel);
					dmDataItemItem.setOrder(itemOrder);
					dmDataItemItem.setStatus(itemStatus);
					dmDataItemItem.setValidatedfrom(new Date());
					dmDataItemItem.setValidatedto(null);

					DmDataItemLocalServiceUtil.updateDmDataItem(dmDataItemItem);
				}

				else {
					log.info("messageType105  Them/ Sua");

					DmDataItemLocalServiceUtil.addDmDataitem(details);
				}

			}

			result = true;
			int messageType105 = 105;
			log.info("==guiBanTinSangHQMC==dongbodanhmuc MessageType==" + messageType105);

			DMListSyn synch = new DMListSyn();
			synch.synchronizeReferencePortList(messageType105, details, status, maritimeCode);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
//todo query in ActionController
//		PortletConfig portletConfig = (PortletConfig) resourceRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(resourceRequest,
//				portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
//		String backURL = ParamUtil.getString(resourceRequest, "backURL");
//		String redirectURL = ParamUtil.getString(resourceRequest, "redirectURL");
//
//		if (result && Validator.isNotNull(redirectURL)) {
//			actionResponse.sendRedirect(redirectURL);
//		} else if (!result && Validator.isNotNull(backURL)) {
//			actionResponse.sendRedirect(backURL);
//		}

	}

	public void actionSearchPortRegion(ActionRequest resourceRequest, ActionResponse httpReq)
			throws NumberFormatException, Exception {

		log.info("==actionSearchPortRegion==");

		String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode").trim();
		String portCodeName = ParamUtil.getString(resourceRequest, "portCodeName").trim();
		String portCodeNode2 = ParamUtil.getString(resourceRequest, "portCodeNode2").trim();
		String portRegionNameVN = ParamUtil.getString(resourceRequest, "portRegionNameVN").trim();
//todo query in ActionController
//		httpReq.setRenderParameter("jspPage", "/html/danhmuc/view-DM_PORT_REGION.jsp");
//		httpReq.setRenderParameter("maritimeCode", maritimeCode);
//		httpReq.setRenderParameter("portCodeName", portCodeName);
//		httpReq.setRenderParameter("portCodeNode2", portCodeNode2);
//		httpReq.setRenderParameter("portRegionNameVN", portRegionNameVN);
//
//		PortletConfig portletConfig = (PortletConfig) resourceRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(resourceRequest,
//				portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}

	public void findPortNameByPortCodeNode2(ActionRequest resourceRequest, ActionResponse httpReq) throws IOException {
		try {
			String portCodeNode2 = ParamUtil.getString(resourceRequest, "portCodeNode2");
			log.info("====portCodeNode2=====" + portCodeNode2);

			// Process Json feed
			JSONArray array = JSONFactoryUtil.createJSONArray();
			JSONObject obj = null;

			DmDataitem dmDataItemPortName = DmDataItemLocalServiceUtil.getByNode2AndDataGroupID(107, portCodeNode2);

			obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", dmDataItemPortName.getNode2());
			obj.put("portname", dmDataItemPortName.getName());
			obj.put("code", dmDataItemPortName.getCode());
			array.put(obj);
//todo query in ActionController
//			HttpServletResponse resourceResponse = PortalUtil.getHttpServletResponse(httpReq);
//			log.info("=====array===" + array.toString());
//
//			resourceResponse.setContentType("application/json");
//			resourceResponse.setCharacterEncoding("UTF-8");
//			resourceResponse.getWriter().write(array.toString());
//			resourceResponse.getWriter().close();
		} catch (Exception e1) {

		}
	}
}
