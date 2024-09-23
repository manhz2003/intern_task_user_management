package vn.gt.portlet.thongtintau;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fds.nsw.liferay.core.*;


import com.fds.nsw.nghiepvu.model.DmPort;
import com.fds.nsw.nghiepvu.model.DmPortRegion;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.portlet.GiaoThongAction;
import vn.gt.portlet.TransportationMVCAction;
import vn.gt.utils.config.ConfigurationManager;

/**
 * Portlet implementation class ThongTinTauAction
 */
@Slf4j
@Service
public class ThongTinTauAction extends TransportationMVCAction {



	public static String GIAY_PHEP_ROI_CANG = "1";
	public static String KE_HOACH_DIEU_DONG_TAU = "2";
	public static String GIAY_PHEP_QUA_CANH = "3";
	public static String GIAY_PHEP_VAO_CANG_PTTND = "16";
	public static String THONG_TIN_TAU = "48";

    //todo remove this, not use, by giaonn
//	public void search(ActionRequest resourceRequest, ActionResponse httpReq) {
//
//		String flagMenu = ParamUtil.getString(resourceRequest, "flagMenu").trim();
//
//		String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode").trim();
//		String portCode = ParamUtil.getString(resourceRequest, "portCode").trim();
//		String shipName = ParamUtil.getString(resourceRequest, "shipName").trim();
//		String callSign = ParamUtil.getString(resourceRequest, "callSign").trim();
//		String shipDateFrom = ParamUtil.getString(resourceRequest, "shipDateFrom").trim();
//		String shipDateTo = ParamUtil.getString(resourceRequest, "shipDateTo").trim();
//		String certificateNumber = ParamUtil.getString(resourceRequest, "certificateNumber").trim();
//
//		log.info("====searchGiayPhepRoiCang-maritimeCode=====maritimeCode==" + maritimeCode);
//
//		httpReq.setRenderParameter("maritimeCode", maritimeCode);
//		httpReq.setRenderParameter("portCode", portCode);
//		httpReq.setRenderParameter("shipName", shipName);
//		httpReq.setRenderParameter("callSign", callSign);
//		httpReq.setRenderParameter("shipDateFrom", shipDateFrom);
//		httpReq.setRenderParameter("shipDateTo", shipDateTo);
//		httpReq.setRenderParameter("certificateNumber", certificateNumber);
//		httpReq.setRenderParameter("flagMenu", flagMenu);
//
//		PortletConfig portletConfig = (PortletConfig) resourceRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(resourceRequest, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
//	}

	public void findPortRegionAndPortBymaritimeCode(ActionRequest resourceRequest, ActionResponse httpReq) throws IOException {

		String luaChon_label = ConfigurationManager.getStrProp("vn.gt.luachon", "--Lua chon ---");
		String maritimeCode = ParamUtil.getString(resourceRequest, "maritimeCode");
		// luaChon_label=Liferay.Language.get("vn.gt.luachon");
		// Process Jason feed
		JSONObject jsonFeed = JSONFactoryUtil.createJSONObject();
		JSONArray name = JSONFactoryUtil.createJSONArray();
		JSONArray id = JSONFactoryUtil.createJSONArray();

		//
		List<DmPortRegion> dsPortRegion = DmPortRegionLocalServiceUtil.findPortRegionByPortRegionRef(maritimeCode);
		if (dsPortRegion == null) {
			dsPortRegion = new ArrayList<DmPortRegion>();
		}

		for (DmPortRegion item : dsPortRegion) {
			id.put(item.getPortRegionCode());
			name.put(item.getPortRegionName());
		}

		jsonFeed.put("idPortRegion", id);
		jsonFeed.put("namePortRegion", name);

		id = JSONFactoryUtil.createJSONArray();
		name = JSONFactoryUtil.createJSONArray();

		List<DmPort> dsPorts = DmPortLocalServiceUtil.findByLoCode(maritimeCode);
		if (dsPorts == null) {
			dsPorts = new ArrayList<DmPort>();
		}

		for (DmPort item : dsPorts) {
			id.put(item.getPortCode());
			name.put(item.getPortName());
		}

		jsonFeed.put("idPort", id);
		jsonFeed.put("namePort", name);

//		HttpServletResponse resourceResponse = PortalUtil.getHttpServletResponse(httpReq);
//
//		resourceResponse.setContentType("application/json");
//		resourceResponse.setCharacterEncoding("UTF-8");
//		resourceResponse.getWriter().write(jsonFeed.toString());
//		resourceResponse.getWriter().close();
	}

//	public void menu(ActionRequest resourceRequest, ActionResponse httpReq) throws NumberFormatException, Exception {
//
//		String menuSelect = ParamUtil.getString(resourceRequest, MenuConstraint.SESSION_MENU_SELECTED);
//		httpReq.setRenderParameter(MenuConstraint.SESSION_MENU_SELECTED, menuSelect);
//
//		PortletConfig portletConfig = (PortletConfig) resourceRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(resourceRequest, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
//	}
}
