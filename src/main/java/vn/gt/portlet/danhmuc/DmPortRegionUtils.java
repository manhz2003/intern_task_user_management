package vn.gt.portlet.danhmuc;

import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmDataitem;
import com.fds.nsw.nghiepvu.model.DmHistoryPortRegion;
import com.fds.nsw.nghiepvu.model.DmPortRegion;


import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
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
public class DmPortRegionUtils {

	

	public static JSONObject getPortRegions(long userId, String maritimeCode,
			String portRegionNameVN, String portCode, String isDelete,
			String portRegionCodeGroup, int start, int end) {
		try {
			JSONArray array = JSONFactoryUtil
					.createJSONArray();
			JSONObject result = JSONFactoryUtil.createJSONObject();
			long total = 0;
			List<DmPortRegion> dmPortRegions = DmPortRegionLocalServiceUtil
					.findPortRegions(maritimeCode, portRegionNameVN, portCode,
							isDelete, portRegionCodeGroup, start, end);
			total = DmPortRegionLocalServiceUtil.countPortRegions(maritimeCode,
					portRegionNameVN, portCode, isDelete, portRegionCodeGroup);
			if (dmPortRegions != null && !dmPortRegions.isEmpty()) {
				total = dmPortRegions.size();
				for (DmPortRegion dmPortRegion : dmPortRegions) {
					JSONObject obj = JSONFactoryUtil.createJSONObject();

					obj.put("id", dmPortRegion.getId());
					obj.put("portRegionCode", dmPortRegion.getPortRegionCode());
					obj.put("portRegionNameVN",
							dmPortRegion.getPortRegionNameVN());
					obj.put("portRegionShortName", dmPortRegion.getPortRegionShortName());
					obj.put("portCodeCB", dmPortRegion.getPortCode());
					obj.put("portCodeRef", dmPortRegion.getPortCodeRef());
					try {
						obj.put("cityCode",
								DmMaritimeLocalServiceUtil.getByMaritimeCode(
										dmPortRegion.getPortRegionRef())
										.getCityCode());
					} catch (Exception e) {
						obj.put("cityCode", "");
					}
					obj.put("portRegionRef", dmPortRegion.getPortRegionRef());
					obj.put("isDelete", dmPortRegion.getIsDelete());
					obj.put("sequenceNo", dmPortRegion.getSequenceNo());
					obj.put("role",
							dmPortRegion.getPortRegionRef().equals("")
									|| dmPortRegion.getPortRegionRef().equals(
											DanhMucUtils.getMaritimeCurrent(
													userId).getString(
													"maritimeCode")) ? true
									: false);
					array.put(obj);
				}
			}
			result.put("data", array);
			result.put("total", total);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JSONObject getDetailPortRegion(String portRegionCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil
				.getByPortRegionCode(portRegionCode);
		result.put("id", dmPortRegion.getId());
		result.put("portRegionRef", dmPortRegion.getPortCodeRef());
		result.put("portCode", dmPortRegion.getPortCode());
		result.put("portRegionName", dmPortRegion.getPortRegionNameVN());
		result.put("portRegionShortName", dmPortRegion.getPortRegionShortName());
		result.put("modifiedDate", dmPortRegion.getModifiedDate());
		result.put("portRegionRef", dmPortRegion.getPortRegionRef());
		result.put("portCodeRef", dmPortRegion.getPortCodeRef());
		result.put("sequenceNo", dmPortRegion.getSequenceNo());

		return result;
	}

	public static void actionUpdatePortRegion(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		String maritimeCode = UserPortLocalServiceUtil.findByUserId(
				GetterUtil.getLong(themeDisplay.getUserId())).getPortCode();
		String portCodeRef = DanhMucUtils.getString(actionRequest,
				"portCodeRef");
		String portCode = "";
		if (Validator.isNotNull(portCodeRef) && !portCodeRef.isEmpty()) {
			DmDataitem dataItem = DmDataItemLocalServiceUtil
					.findByDataGroupId_Node2(107, portCodeRef);
			if (dataItem == null) {
				log.error("portCodeRef khong co trong bang dm_dataitem");
				return;
			} else if (dataItem != null) {
				portCode = dataItem.getName();
			}
		}
		String portRegionCode = DanhMucUtils.getString(actionRequest,
				"portRegionCode");
		String portRegionNameVN = DanhMucUtils.getString(actionRequest,
				"portRegionNameVN");
		
		String portRegionShortName = DanhMucUtils.getString(actionRequest,
				"portRegionShortName");
		String sequenceNoEdit = ParamUtil
				.getString(actionRequest, "sequenceNo");
		if (Validator.isNull(sequenceNoEdit)){
			sequenceNoEdit = "1";
		}
		String capmoi = DanhMucUtils.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String capsua = DanhMucUtils.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String danhdauXoa = DanhMucUtils.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);

		try {
			String status = "0";
			log.info("messageType101 -- DM_PORT_REGION ");
			DmPortRegion details = new DmPortRegion();
			DmHistoryPortRegion history = new DmHistoryPortRegion();
			String syncVersion = "1";

			int sequenceNo = (int) DmPortRegionLocalServiceUtil
					.getMaxSequenceNo(maritimeCode, portCodeRef);
			if (sequenceNo == 0) {
				sequenceNo = 1;
			} else {
				sequenceNo = sequenceNo + 1;
			}

			details.setPortRegionCode(portRegionCode);
			details.setPortRegionName(portRegionNameVN);
			details.setPortRegionNameVN(portRegionNameVN);
			details.setPortRegionShortName(portRegionShortName);
			details.setPortRegionRef(maritimeCode);
			details.setPortCode(portCode);
			details.setPortCodeRef(portCodeRef);
			details.setSequenceNo(sequenceNo);

			history.setPortRegionCode(portRegionCode);
			history.setPortRegionName(portRegionNameVN);
			history.setPortRegionNameVN(portRegionNameVN);
			history.setPortRegionShortName(portRegionShortName);
			history.setPortRegionRef(maritimeCode);
			history.setPortCode(portCode);
			history.setPortCodeRef(portCodeRef);
			history.setSyncVersion(syncVersion);
			history.setSequenceNo(sequenceNo);

			if (danhdauXoa.length() > 0) {
				log.info("messageType101  Xoa");
				status = "0";
				DmPortRegion dmPortRegionItem = DmPortRegionLocalServiceUtil
						.getByPortRegionCode(portRegionCode);
				dmPortRegionItem.setIsDelete(1);
				dmPortRegionItem.setMarkedAsDelete(1);
				dmPortRegionItem.setModifiedDate(new Date());
				dmPortRegionItem.setSyncVersion("1|");
				DmPortRegionLocalServiceUtil
						.updateDmPortRegion(dmPortRegionItem);

				DmHistoryPortRegion dmHistoryPortRegionItem = DmHistoryPortRegionLocalServiceUtil
						.findByPortRegionCodeAndSyncVersion(portRegionCode,
								syncVersion);
				dmHistoryPortRegionItem.setIsDelete(1);
				dmHistoryPortRegionItem.setMarkedAsDelete(1);
				dmHistoryPortRegionItem.setModifiedDate(new Date());
				dmHistoryPortRegionItem.setSyncVersion("1");
				history = DmHistoryPortRegionLocalServiceUtil
						.updateDmHistoryPortRegion(dmHistoryPortRegionItem);
			} else if (capmoi.length() > 0) {
				log.info("messageType101 Them");
				status = "2";
				if (portRegionCode.trim().length() == 0) {
					//datagroupid = 10 la nhom danh muc tao ma code
					String prefix = DmDataItemLocalServiceUtil.findByDataGroupIdAndNode1(10L, maritimeCode).get(0).getCode();
					portRegionCode = ReportsBusinessUtils.taoSo("PORTREGION", prefix, 4);
				}

				DmPortRegion dmPortRegionItem = DmPortRegionLocalServiceUtil
						.getByPortRegionCode(portRegionCode);

				if (dmPortRegionItem != null) {
					dmPortRegionItem.setPortRegionCode(portRegionCode);
					dmPortRegionItem.setPortRegionName(portRegionNameVN);
					dmPortRegionItem.setPortRegionNameVN(portRegionNameVN);
					dmPortRegionItem.setPortRegionShortName(portRegionShortName);
					dmPortRegionItem.setPortRegionRef(maritimeCode);
					dmPortRegionItem.setPortCode(portCode);
					dmPortRegionItem.setPortCodeRef(portCodeRef);
					dmPortRegionItem.setSequenceNo(sequenceNo);

					dmPortRegionItem.setIsDelete(0);
					dmPortRegionItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmPortRegionItem.setModifiedDate(new Date());
					dmPortRegionItem.setSyncVersion("1|");
					DmPortRegionLocalServiceUtil
							.updateDmPortRegion(dmPortRegionItem);
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
						.findByPortRegionCodeAndSyncVersion(portRegionCode,
								syncVersion);

				if (dmHistoryPortRegionItem != null) {
					dmHistoryPortRegionItem.setPortRegionCode(portRegionCode);
					dmHistoryPortRegionItem.setPortRegionName(portRegionNameVN);
					dmHistoryPortRegionItem
							.setPortRegionNameVN(portRegionNameVN);
					dmHistoryPortRegionItem.setPortRegionShortName(portRegionShortName);
					dmHistoryPortRegionItem.setPortRegionRef(maritimeCode);
					dmHistoryPortRegionItem.setPortCode(portCode);
					dmHistoryPortRegionItem.setPortCodeRef(portCodeRef);
					dmHistoryPortRegionItem.setSequenceNo(sequenceNo);

					dmHistoryPortRegionItem.setIsDelete(0);
					dmHistoryPortRegionItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryPortRegionItem.setModifiedDate(new Date());
					dmHistoryPortRegionItem.setSyncVersion("1");
					history = DmHistoryPortRegionLocalServiceUtil
							.updateDmHistoryPortRegion(dmHistoryPortRegionItem);
				} else {
					history.setPortRegionCode(portRegionCode);
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryPortRegionLocalServiceUtil
							.addDmHistoryPortRegion(history);
				}

			} else if (capsua.length() > 0) {
				log.info("messageType101  Sua");
				status = "1";
				DmPortRegion dmPortRegionItem = DmPortRegionLocalServiceUtil
						.getByPortRegionCode(portRegionCode);
				if (dmPortRegionItem != null) {
					dmPortRegionItem.setPortRegionCode(portRegionCode);
					dmPortRegionItem.setPortRegionName(portRegionNameVN);
					dmPortRegionItem.setPortRegionNameVN(portRegionNameVN);
					dmPortRegionItem.setPortRegionShortName(portRegionShortName);
					dmPortRegionItem.setPortRegionRef(maritimeCode);
					dmPortRegionItem.setPortCode(portCode);
					dmPortRegionItem.setPortCodeRef(portCodeRef);
					dmPortRegionItem.setSequenceNo(Integer.valueOf(sequenceNoEdit));

					if (dmPortRegionItem.getIsDelete() == 1) {
						status = "2";
					}
					dmPortRegionItem.setIsDelete(0);
					dmPortRegionItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmPortRegionItem.setModifiedDate(new Date());
					dmPortRegionItem.setSyncVersion("1|");
					DmPortRegionLocalServiceUtil
							.updateDmPortRegion(dmPortRegionItem);
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
						.findByPortRegionCodeAndSyncVersion(portRegionCode,
								syncVersion);

				if (dmHistoryPortRegionItem != null) {
					dmHistoryPortRegionItem.setPortRegionCode(portRegionCode);
					dmHistoryPortRegionItem.setPortRegionName(portRegionNameVN);
					dmHistoryPortRegionItem
							.setPortRegionNameVN(portRegionNameVN);
					dmHistoryPortRegionItem.setPortRegionShortName(portRegionShortName);
					dmHistoryPortRegionItem.setPortRegionRef(maritimeCode);
					dmHistoryPortRegionItem.setPortCode(portCode);
					dmHistoryPortRegionItem.setPortCodeRef(portCodeRef);
					dmHistoryPortRegionItem.setSequenceNo(Integer.valueOf(sequenceNoEdit));

					dmHistoryPortRegionItem.setIsDelete(0);
					dmHistoryPortRegionItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryPortRegionItem.setModifiedDate(new Date());
					dmHistoryPortRegionItem.setSyncVersion("1");
					history = DmHistoryPortRegionLocalServiceUtil
							.updateDmHistoryPortRegion(dmHistoryPortRegionItem);
				} else {
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryPortRegionLocalServiceUtil
							.addDmHistoryPortRegion(history);
				}
			}
			int messageType101 = 101;
			log.info("==guiBanTinSangHQMC==dongbodanhmuc MessageType=="
					+ messageType101);

			DMListSyn synch = new DMListSyn();
			synch.synchronizePortRegionList(messageType101, history, status,
					maritimeCode);

			//todo tinh sau

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
