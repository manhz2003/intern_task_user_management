package vn.gt.portlet.danhmuc;

import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour;
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.tichhop.message.haiquan2giaothong.DMListSyn;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.PageType;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;



import com.fds.flex.common.ultility.GetterUtil;

import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmPortHarbourUtils {

	

	public static JSONObject getDetailPortHarbour(String portHarbourCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil
				.getByPortHarbourCode(portHarbourCode);
		result.put("note", dmPortHarbour.getNote());
		result.put("portHarbourNameVN", dmPortHarbour.getPortHarbourNameVN());
		result.put("portHarbourCode", dmPortHarbour.getPortHarbourCode());
		result.put("portHarbourShortName", dmPortHarbour.getPortHarbourShortName());
		result.put("portRegion", dmPortHarbour.getPortRegion());
		result.put("portRegionCode", dmPortHarbour.getPortRegionCode());
		result.put("modifiedDate", dmPortHarbour.getModifiedDate());
		result.put("isDelete", dmPortHarbour.getIsDelete());
		result.put("sequenceNo", dmPortHarbour.getSequenceNo());
		return result;
	}

	public static JSONObject getPortHarbours(long userId, String maritimeCode,
			String portRegion, String portHarbourName, String isDelete,
			String portHarbourCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;

		List<DmPortHarbour> dsDmPortHarbours = DmPortHarbourLocalServiceUtil
				.findPortHarbours(maritimeCode, portRegion, portHarbourName,
						isDelete, portHarbourCodeGroup, start, end);
		total = DmPortHarbourLocalServiceUtil.countPortHarbours(maritimeCode,
				portRegion, portHarbourName, isDelete, portHarbourCodeGroup);
		for (DmPortHarbour item : dsDmPortHarbours) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", item.getId());
			obj.put("portHarbourCode", item.getPortHarbourCode());
			obj.put("portHarbourNameVN", item.getPortHarbourNameVN());
			obj.put("portHarbourShortName", item.getPortHarbourShortName());
			try {
				obj.put("portCodeCB", DmPortRegionLocalServiceUtil
						.getByPortRegionCode(item.getPortRegion())
						.getPortCode());
			} catch (Exception e) {
				obj.put("portCodeCB", "");
			}
			try {
				obj.put("portRegionNameVN", DmPortRegionLocalServiceUtil
						.getByPortRegionCode(item.getPortRegion())
						.getPortRegionNameVN());
			} catch (Exception e) {
				obj.put("portRegionNameVN", "");
			}
			obj.put("portRegion", item.getPortRegion());
			obj.put("portRegionCode", item.getPortRegionCode());
			obj.put("isDelete", item.getIsDelete());
			obj.put("sequenceNo", item.getSequenceNo());
			obj.put("role",
					item.getPortRegionCode().equals("")
							|| item.getPortRegionCode().equals(
									DanhMucUtils.getMaritimeCurrent(userId)
											.getString("maritimeCode")) ? true
							: false);
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static void actionUpdatePortHarbour(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) throws Exception {

		try {
			String maritimeCode = UserPortLocalServiceUtil.findByUserId(
					GetterUtil.getLong(themeDisplay.getUserId())).getPortCode();
			String portRegion = ParamUtil
					.getString(actionRequest, "portRegion");
			String portCodeName = "";
			if (Validator.isNotNull(portRegion) & !portRegion.equals("")) {
				portCodeName = DmPortRegionLocalServiceUtil
						.getByPortRegionCode(portRegion).getPortCode();
			}
			String portHarbourCode = DanhMucUtils.getString(actionRequest,
					"portHarbourCode");
			String portHarbourNameVN = DanhMucUtils.getString(actionRequest,
					"portHarbourNameVN");
			String portHarbourShortName = DanhMucUtils.getString(actionRequest,
					"portHarbourShortName");
			String remarks = DanhMucUtils.getString(actionRequest, "note");
			String sequenceNoEdit = ParamUtil.getString(actionRequest,
					"sequenceNo");
			if (Validator.isNull(sequenceNoEdit)){
				sequenceNoEdit = "1";
			}
			String status = "0";
			String capmoi = DanhMucUtils.getString(actionRequest,
					PageType.LAN_CAP_MOI_DU_LIEU);
			String capsua = DanhMucUtils.getString(actionRequest,
					PageType.LAN_SUA_DU_LIEU);
			String danhdauXoa = DanhMucUtils.getString(actionRequest,
					PageType.LAN_XOA_DU_LIEU);

			log.info("messageType102 -- DM_PORT_HARBOUR ");
			DmPortHarbour details = new DmPortHarbour();
			DmHistoryPortHarbour history = new DmHistoryPortHarbour();
			String syncVersion = "1";

			int sequenceNo = (int) DmPortHarbourLocalServiceUtil
					.getMaxSequenceNo(maritimeCode, portRegion);
			if (sequenceNo == 0) {
				sequenceNo = 1;
			} else {
				sequenceNo = sequenceNo + 1;
			}

			details.setPortHarbourCode(portHarbourCode);
			details.setPortHarbourName(portHarbourNameVN);
			details.setPortHarbourNameVN(portHarbourNameVN);
			details.setPortHarbourShortName(portHarbourShortName);
			details.setPortHarbourType(1);
			details.setNote(remarks);
			details.setPortCode(portCodeName);
			details.setPortRegionCode(maritimeCode);
			details.setPortRegion(portRegion);
			details.setSequenceNo(sequenceNo);

			history.setPortHarbourCode(portHarbourCode);
			history.setPortHarbourName(portHarbourNameVN);
			history.setPortHarbourNameVN(portHarbourNameVN);
			history.setPortHarbourShortName(portHarbourShortName);
			history.setPortHarbourType(1);
			history.setNote(remarks);
			history.setPortCode(portCodeName);
			history.setPortRegionCode(maritimeCode);
			history.setPortRegion(portRegion);
			history.setSyncVersion(syncVersion);
			history.setSequenceNo(sequenceNo);

			if (danhdauXoa.length() > 0) {
				log.info("messageType102  Xoa");
				status = "0";
				DmPortHarbour dmPortHarbourItem = DmPortHarbourLocalServiceUtil
						.getByPortHarbourCode(portHarbourCode);
				dmPortHarbourItem.setIsDelete(1);
				dmPortHarbourItem.setMarkedAsDelete(1);
				// dmPortitem.setRequestedDate(new Date());
				dmPortHarbourItem.setModifiedDate(new Date());
				dmPortHarbourItem.setSyncVersion("1|");
				DmPortHarbourLocalServiceUtil
						.updateDmPortHarbour(dmPortHarbourItem);

				DmHistoryPortHarbour dmHistoryPortHarbourItem = DmHistoryPortHarbourLocalServiceUtil
						.findByPortHarbourCodeAndSyncVersion(portHarbourCode,
								syncVersion);
				dmHistoryPortHarbourItem.setIsDelete(1);
				dmHistoryPortHarbourItem.setMarkedAsDelete(1);
				// dmPortitem.setRequestedDate(new Date());
				dmHistoryPortHarbourItem.setModifiedDate(new Date());
				dmHistoryPortHarbourItem.setSyncVersion("1");
				history = DmHistoryPortHarbourLocalServiceUtil
						.updateDmHistoryPortHarbour(dmHistoryPortHarbourItem);

			} else if (capmoi.length() > 0) {
				log.info("messageType102 Them");
				status = "2";
				if (portHarbourCode.trim().length() == 0) {
					//datagroupid = 10 la nhom danh muc tao ma code
					String prefix = DmDataItemLocalServiceUtil.findByDataGroupIdAndNode1(10L, maritimeCode).get(0).getCode();
					portHarbourCode = ReportsBusinessUtils.taoSo("PORTHARBOUR", prefix, 4);
				}

				DmPortHarbour dmPortHarbourItem = DmPortHarbourLocalServiceUtil
						.getByPortHarbourCode(portHarbourCode);

				if (dmPortHarbourItem != null) {
					dmPortHarbourItem.setPortHarbourCode(portHarbourCode);
					dmPortHarbourItem.setPortHarbourName(portHarbourNameVN);
					dmPortHarbourItem.setPortHarbourNameVN(portHarbourNameVN);
					dmPortHarbourItem.setPortHarbourShortName(portHarbourShortName);
					dmPortHarbourItem.setPortHarbourType(1);
					dmPortHarbourItem.setNote(remarks);
					dmPortHarbourItem.setPortCode(portCodeName);
					dmPortHarbourItem.setPortRegionCode(maritimeCode);
					dmPortHarbourItem.setPortRegion(portRegion);
					dmPortHarbourItem.setSequenceNo(sequenceNo);

					dmPortHarbourItem.setIsDelete(0);
					dmPortHarbourItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmPortHarbourItem.setModifiedDate(new Date());
					dmPortHarbourItem.setSyncVersion("1|");
					DmPortHarbourLocalServiceUtil
							.updateDmPortHarbour(dmPortHarbourItem);
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
						.findByPortHarbourCodeAndSyncVersion(portHarbourCode,
								syncVersion);

				if (dmHistoryPortHarbourItem != null) {
					dmHistoryPortHarbourItem
							.setPortHarbourCode(portHarbourCode);
					dmHistoryPortHarbourItem
							.setPortHarbourName(portHarbourNameVN);
					dmHistoryPortHarbourItem
							.setPortHarbourNameVN(portHarbourNameVN);
					dmHistoryPortHarbourItem.setPortHarbourShortName(portHarbourShortName);
					dmHistoryPortHarbourItem.setPortHarbourType(1);
					dmHistoryPortHarbourItem.setNote(remarks);
					dmHistoryPortHarbourItem.setPortCode(portCodeName);
					dmHistoryPortHarbourItem.setPortRegionCode(maritimeCode);
					dmHistoryPortHarbourItem.setPortRegion(portRegion);
					dmHistoryPortHarbourItem.setSequenceNo(sequenceNo);

					dmHistoryPortHarbourItem.setIsDelete(0);
					dmHistoryPortHarbourItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryPortHarbourItem.setModifiedDate(new Date());
					dmHistoryPortHarbourItem.setSyncVersion("1");
					history = DmHistoryPortHarbourLocalServiceUtil
							.updateDmHistoryPortHarbour(dmHistoryPortHarbourItem);
				} else {
					history.setPortHarbourCode(portHarbourCode);
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryPortHarbourLocalServiceUtil
							.addDmHistoryPortHarbour(history);
				}
			} else if (capsua.length() > 0) {
				log.info("messageType102  Sua");
				status = "1";
				DmPortHarbour dmPortHarbourItem = DmPortHarbourLocalServiceUtil
						.getByPortHarbourCode(portHarbourCode);
				if (dmPortHarbourItem != null) {
					dmPortHarbourItem.setPortHarbourCode(portHarbourCode);
					dmPortHarbourItem.setPortHarbourName(portHarbourNameVN);
					dmPortHarbourItem.setPortHarbourNameVN(portHarbourNameVN);
					dmPortHarbourItem.setPortHarbourShortName(portHarbourShortName);
					dmPortHarbourItem.setPortHarbourType(1);
					dmPortHarbourItem.setNote(remarks);
					dmPortHarbourItem.setPortCode(portCodeName);
					dmPortHarbourItem.setPortRegionCode(maritimeCode);
					dmPortHarbourItem.setPortRegion(portRegion);
					dmPortHarbourItem.setSequenceNo(Integer
							.valueOf(sequenceNoEdit));

					if (dmPortHarbourItem.getIsDelete() == 1) {
						status = "2";
					}
					dmPortHarbourItem.setIsDelete(0);
					dmPortHarbourItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmPortHarbourItem.setModifiedDate(new Date());
					dmPortHarbourItem.setSyncVersion("1|");
					DmPortHarbourLocalServiceUtil
							.updateDmPortHarbour(dmPortHarbourItem);
				} else {
					log.info("messageType101  Them/ Sua");
					details.setIsDelete(0);
					details.setMarkedAsDelete(0);
					details.setRequestedDate(new Date());
					details.setModifiedDate(new Date());
					details.setSyncVersion("1|");
					DmPortHarbourLocalServiceUtil.addDmPortHarbour(details);
				}
				DmHistoryPortHarbour dmHistoryPortHarbourItem = DmHistoryPortHarbourLocalServiceUtil
						.findByPortHarbourCodeAndSyncVersion(portHarbourCode,
								syncVersion);

				if (dmHistoryPortHarbourItem != null) {
					dmHistoryPortHarbourItem
							.setPortHarbourCode(portHarbourCode);
					dmHistoryPortHarbourItem
							.setPortHarbourName(portHarbourNameVN);
					dmHistoryPortHarbourItem
							.setPortHarbourNameVN(portHarbourNameVN);
					dmHistoryPortHarbourItem.setPortHarbourShortName(portHarbourShortName);
					dmHistoryPortHarbourItem.setPortHarbourType(1);
					dmHistoryPortHarbourItem.setNote(remarks);
					dmHistoryPortHarbourItem.setPortCode(portCodeName);
					dmHistoryPortHarbourItem.setPortRegionCode(maritimeCode);
					dmHistoryPortHarbourItem.setPortRegion(portRegion);
					dmHistoryPortHarbourItem.setSequenceNo(Integer
							.valueOf(sequenceNoEdit));

					dmHistoryPortHarbourItem.setIsDelete(0);
					dmHistoryPortHarbourItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryPortHarbourItem.setModifiedDate(new Date());
					dmHistoryPortHarbourItem.setSyncVersion("1");
					history = DmHistoryPortHarbourLocalServiceUtil
							.updateDmHistoryPortHarbour(dmHistoryPortHarbourItem);
				} else {
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryPortHarbourLocalServiceUtil
							.addDmHistoryPortHarbour(history);
				}

			}
			int messageType102 = 102;
			log.info("==guiBanTinSangHQMC==dongbodanhmuc MessageType=="
					+ messageType102);

			DMListSyn synch = new DMListSyn();
			synch.synchronizePortHarbourList(messageType102, history, status,
					maritimeCode);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
//		PortletConfig portletConfig = (PortletConfig) actionRequest
//				.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(actionRequest, portletConfig.getPortletName()
//				+ SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}
}
