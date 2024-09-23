package vn.gt.portlet.danhmuc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryPortWharf;
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import com.fds.nsw.nghiepvu.model.DmPortWharf;


import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmHistoryPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;
import vn.gt.tichhop.message.haiquan2giaothong.DMListSyn;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.PageType;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;




import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmPortWharfUtils {


	

	public static JSONObject getPortWharfs(long userId, String maritimeCode,
			String holdPortRegionCode, String holdPortHarbourCode,
			String portWharfNameVN, String isDelete, String portWharfCodeGroup,
			int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmPortWharf> dsPortWharfs = DmPortWharfLocalServiceUtil
				.findPortWharfs(maritimeCode, holdPortRegionCode,
						holdPortHarbourCode, portWharfNameVN, isDelete,
						portWharfCodeGroup, start, end);
		total = DmPortWharfLocalServiceUtil.countPortWharfs(maritimeCode,
				holdPortRegionCode, holdPortHarbourCode, portWharfNameVN,
				isDelete, portWharfCodeGroup);
		for (DmPortWharf item : dsPortWharfs) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", item.getId());
			obj.put("portWharfCode", item.getPortWharfCode());
			obj.put("portWharfNameVN", item.getPortWharfNameVN());
			obj.put("portCodeBC", item.getPortCode());
			obj.put("portRegionCode", item.getPortRegionCode());
			try {
				obj.put("portRegionNameVN", DmPortRegionLocalServiceUtil
						.getByPortRegionCode(item.getPortRegionCode())
						.getPortRegionNameVN());
			} catch (Exception e) {
				obj.put("portRegionNameVN", "");
			}
			obj.put("portHarbourCode", item.getPortHarbourCode());
			obj.put("portWharfShortName", item.getPortWharfShortName());
			obj.put("node", item.getNote());
			obj.put("dwt", item.getDwt());
			obj.put("loa", item.getLoa());
			obj.put("maxDraft", item.getMaxDraft());
			obj.put("isDelete", item.getIsDelete());
			obj.put("managedVinalines", item.getManagedVinalines());
			obj.put("portWharfType", item.getPortWharfType());
			obj.put("sequenceNo", item.getSequenceNo());
			obj.put("portWharfPayment", item.getPortWharfPayment());
			try {
				obj.put("role",
						DmPortRegionLocalServiceUtil
								.getByPortRegionCode(item.getPortRegionCode())
								.getPortRegionRef().equals("")
								|| DmPortRegionLocalServiceUtil
										.getByPortRegionCode(
												item.getPortRegionCode())
										.getPortRegionRef()
										.equals(DanhMucUtils
												.getMaritimeCurrent(userId)
												.getString("maritimeCode")) ? true
								: false);
			} catch (Exception e) {
				obj.put("role", false);
			}
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailPortWharf(String portWharfCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
				.getByPortWharfCode(portWharfCode);
		result.put(
				"maritimeCode",
				DmMaritimeLocalServiceUtil.getByMaritimeCode(
						DmPortRegionLocalServiceUtil.getByPortRegionCode(
								dmPortWharf.getPortRegionCode())
								.getPortRegionRef()).getMaritimeCode());
		result.put("note", dmPortWharf.getNote());
		result.put("portWharfName", dmPortWharf.getPortWharfNameVN());
		result.put("portWharfShortName", dmPortWharf.getPortWharfShortName());
		result.put("portHarbourCode", dmPortWharf.getPortHarbourCode());
		result.put("portRegionCode", dmPortWharf.getPortRegionCode());
		result.put("modifiedDate", dmPortWharf.getModifiedDate());
		result.put("dwt", dmPortWharf.getDwt());
		result.put("loa", dmPortWharf.getLoa());
		result.put("maxDraft", dmPortWharf.getMaxDraft());
		result.put("isDelete", dmPortWharf.getIsDelete());
		result.put("managedVinalines", dmPortWharf.getManagedVinalines());
		result.put("portWharfType", dmPortWharf.getPortWharfType());
		result.put("sequenceNo", dmPortWharf.getSequenceNo());
		result.put("portWharfPayment", dmPortWharf.getPortWharfPayment());
		return result;
	}

	public static void actionUpdatePortWharf(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) throws Exception {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());
		String maritimeCode = userPort.getPortCode();
		String portRegionCode = DanhMucUtils.getString(actionRequest,
				"portRegionCode");
		String portHarbourCode = DanhMucUtils.getString(actionRequest,
				"portHarbourCode");
		String portWharfNameVN = DanhMucUtils.getString(actionRequest,
				"portWharfNameVN");
		String portWharfName = DanhMucUtils.getString(actionRequest,
				"portWharfName");
		String portWharfShortName = DanhMucUtils.getString(actionRequest,
				"portWharfShortName");
		String portWharfCode = DanhMucUtils.getString(actionRequest,
				"portWharfCode");
		int dwt = ParamUtil.getInteger(actionRequest, "dwt");
		double loa = ParamUtil.getDouble(actionRequest, "loa", 0L);
		double maxDraft = ParamUtil.getDouble(actionRequest, "maxDraft", 0L);
		String remarks = DanhMucUtils.getString(actionRequest, "note");
		int managedVinalines = ParamUtil.getInteger(actionRequest,
				"managedVinalines");
		String sequenceNoEdit = ParamUtil
				.getString(actionRequest, "sequenceNo");
		if (Validator.isNull(sequenceNoEdit)){
			sequenceNoEdit = "1";
		}
		int portWharfPament = ParamUtil.getInteger(actionRequest,
				"portWharfPayment");

		String status = "0";
		String capmoi = DanhMucUtils.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String capsua = DanhMucUtils.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String danhdauXoa = DanhMucUtils.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);

		try {
			status = "0";
			String portHarbourCodeName = "";
			DmPortHarbour dmPortHarbourItem = DmPortHarbourLocalServiceUtil
					.getByPortHarbourCode(portHarbourCode);
			if (dmPortHarbourItem != null) {
				portHarbourCodeName = dmPortHarbourItem.getPortHarbourNameVN();
			}
			log.info("messageType103 -- DM_PORT_WHARF ");

			int sequenceNo = (int) DmPortWharfLocalServiceUtil
					.getMaxSequenceNo(portRegionCode, portHarbourCode);
			if (sequenceNo == 0) {
				sequenceNo = 1;
			} else {
				sequenceNo = sequenceNo + 1;
			}

			DmPortWharf details = new DmPortWharf();
			DmHistoryPortWharf history = new DmHistoryPortWharf();
			String syncVersion = "1";

			details.setPortWharfCode(portWharfCode);
			details.setPortWharfName(portWharfNameVN);
			details.setPortWharfNameVN(portWharfNameVN);
			details.setPortWharfShortName(portWharfShortName);
			details.setPortWharfType(1);
			details.setPortCode(portHarbourCodeName);
			details.setNote(remarks);
			details.setPortRegionCode(portRegionCode);
			details.setPortHarbourCode(portHarbourCode);
			details.setDwt(BigDecimal.valueOf(dwt));
			details.setLoa(loa);
			details.setMaxDraft(maxDraft);
			details.setSequenceNo(sequenceNo);
			details.setManagedVinalines(managedVinalines);
			details.setPortWharfPayment(portWharfPament);

			history.setPortWharfCode(portWharfCode);
			history.setPortWharfName(portWharfNameVN);
			history.setPortWharfNameVN(portWharfNameVN);
			history.setPortWharfShortName(portWharfShortName);
			history.setPortWharfType(1);
			history.setPortCode(portHarbourCodeName);
			history.setNote(remarks);
			history.setPortRegionCode(portRegionCode);
			history.setPortHarbourCode(portHarbourCode);
			history.setSyncVersion(syncVersion);
			history.setDwt(BigDecimal.valueOf(dwt));
			history.setLoa(loa);
			history.setMaxDraft(maxDraft);
			history.setSequenceNo(sequenceNo);
			history.setManagedVinalines(managedVinalines);
			history.setPortWharfPayment(portWharfPament);

			if (danhdauXoa.length() > 0) {
				log.info("messageType103  Xoa");
				status = "0";
				DmPortWharf dmPortWharfItem = DmPortWharfLocalServiceUtil
						.getByPortWharfCode(portWharfCode);
				dmPortWharfItem.setIsDelete(1);
				dmPortWharfItem.setMarkedAsDelete(1);
				// dmPortitem.setRequestedDate(new Date());
				dmPortWharfItem.setModifiedDate(new Date());
				dmPortWharfItem.setSyncVersion("1|");
				DmPortWharfLocalServiceUtil.updateDmPortWharf(dmPortWharfItem);

				DmHistoryPortWharf dmHistoryPortWharfItem = DmHistoryPortWharfLocalServiceUtil
						.findByPortWharfCodeAndSyncVersion(portWharfCode,
								syncVersion);
				dmHistoryPortWharfItem.setIsDelete(1);
				dmHistoryPortWharfItem.setMarkedAsDelete(1);
				// dmPortitem.setRequestedDate(new Date());
				dmHistoryPortWharfItem.setModifiedDate(new Date());
				dmHistoryPortWharfItem.setSyncVersion("1");
				history = DmHistoryPortWharfLocalServiceUtil
						.updateDmHistoryPortWharf(dmHistoryPortWharfItem);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(), userSign != null ? userSign.getModifyuser() : StringPool.BLANK,
						LogsConstant.XOA, "dm_port_wharf",
						dmPortWharfItem.getPortWharfCode(),
						dmPortWharfItem.getPortWharfNameVN());

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(), userSign != null ? userSign.getModifyuser() : StringPool.BLANK,
						LogsConstant.XOA, "dm_history_port_wharf",
						history.getPortWharfCode(),
						history.getPortWharfNameVN());
			} else if (capmoi.length() > 0) {
				log.info("messageType103 Them");
				status = "2";
				if (portWharfCode.trim().length() == 0) {
					//datagroupid = 10 la nhom danh muc tao ma code
					String prefix = DmDataItemLocalServiceUtil.findByDataGroupIdAndNode1(10L, maritimeCode).get(0).getCode();
					portWharfCode = ReportsBusinessUtils.taoSo("PORTWHARF", prefix, 4);
					portWharfName = portHarbourCode + "-" + portWharfCode;
				}

				DmPortWharf dmPortWharfItem = DmPortWharfLocalServiceUtil
						.getByPortWharfCode(portWharfCode);

				if (dmPortWharfItem != null) {
					dmPortWharfItem.setPortWharfCode(portWharfCode);
					dmPortWharfItem.setPortWharfName(portWharfNameVN);
					dmPortWharfItem.setPortWharfNameVN(portWharfNameVN);
					dmPortWharfItem.setPortWharfShortName(portWharfShortName);
					dmPortWharfItem.setPortCode(portHarbourCodeName);
					dmPortWharfItem.setNote(remarks);
					dmPortWharfItem.setPortRegionCode(portRegionCode);
					dmPortWharfItem.setPortHarbourCode(portHarbourCode);
					dmPortWharfItem.setDwt(BigDecimal.valueOf(dwt));
					dmPortWharfItem.setLoa(loa);
					dmPortWharfItem.setMaxDraft(maxDraft);
					dmPortWharfItem.setSequenceNo(sequenceNo);
					dmPortWharfItem.setManagedVinalines(managedVinalines);
					dmPortWharfItem.setPortWharfPayment(portWharfPament);

					dmPortWharfItem.setIsDelete(0);
					dmPortWharfItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmPortWharfItem.setModifiedDate(new Date());
					dmPortWharfItem.setSyncVersion("1|");
					DmPortWharfLocalServiceUtil
							.updateDmPortWharf(dmPortWharfItem);
				} else {
					details.setPortWharfCode(portWharfCode);
					details.setPortWharfName(portWharfNameVN);
					details.setPortWharfNameVN(portWharfNameVN);
					details.setPortWharfShortName(portWharfShortName);
					details.setIsDelete(0);
					details.setMarkedAsDelete(0);
					details.setRequestedDate(new Date());
					details.setModifiedDate(new Date());
					details.setSyncVersion("1|");
					DmPortWharfLocalServiceUtil.addDmPortWharf(details);
				}
				DmHistoryPortWharf dmHistoryPortWharfItem = DmHistoryPortWharfLocalServiceUtil
						.findByPortWharfCodeAndSyncVersion(portWharfCode,
								syncVersion);

				if (dmHistoryPortWharfItem != null) {
					dmHistoryPortWharfItem.setPortWharfCode(portWharfCode);
					dmHistoryPortWharfItem.setPortWharfName(portWharfNameVN);
					dmHistoryPortWharfItem.setPortWharfNameVN(portWharfNameVN);
					dmHistoryPortWharfItem.setPortWharfShortName(portWharfShortName);
					dmHistoryPortWharfItem.setPortCode(portHarbourCodeName);
					dmHistoryPortWharfItem.setNote(remarks);
					dmHistoryPortWharfItem.setPortRegionCode(portRegionCode);
					dmHistoryPortWharfItem.setPortHarbourCode(portHarbourCode);
					dmHistoryPortWharfItem.setDwt(BigDecimal.valueOf(dwt));
					dmHistoryPortWharfItem.setLoa(loa);
					dmHistoryPortWharfItem.setMaxDraft(maxDraft);
					dmHistoryPortWharfItem.setSequenceNo(sequenceNo);
					dmHistoryPortWharfItem
							.setManagedVinalines(managedVinalines);
					dmHistoryPortWharfItem.setPortWharfPayment(portWharfPament);

					dmHistoryPortWharfItem.setIsDelete(0);
					dmHistoryPortWharfItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryPortWharfItem.setModifiedDate(new Date());
					dmHistoryPortWharfItem.setSyncVersion("1");
					history = DmHistoryPortWharfLocalServiceUtil
							.updateDmHistoryPortWharf(dmHistoryPortWharfItem);
				} else {
					history.setPortWharfCode(portWharfCode);
					history.setPortWharfName(portWharfNameVN);
					history.setPortWharfNameVN(portWharfNameVN);
					history.setPortWharfShortName(portWharfShortName);
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryPortWharfLocalServiceUtil
							.addDmHistoryPortWharf(history);
				}

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(), userSign != null ? userSign.getModifyuser() : StringPool.BLANK,
						LogsConstant.THEM, "dm_port_wharf",
						dmPortWharfItem.getPortWharfCode(),
						dmPortWharfItem.getPortWharfNameVN());

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(), userSign != null ? userSign.getModifyuser() : StringPool.BLANK,
						LogsConstant.THEM, "dm_history_port_wharf",
						history.getPortWharfCode(),
						history.getPortWharfNameVN());
			} else if (capsua.length() > 0) {
				log.info("messageType103  Sua");
				status = "1";
				DmPortWharf dmPortWharfItem = DmPortWharfLocalServiceUtil
						.getByPortWharfCode(portWharfCode);
				if (dmPortWharfItem != null) {
					dmPortWharfItem.setPortWharfCode(portWharfCode);
					dmPortWharfItem.setPortWharfName(portWharfNameVN);
					dmPortWharfItem.setPortWharfNameVN(portWharfNameVN);
					dmPortWharfItem.setPortWharfShortName(portWharfShortName);
					dmPortWharfItem.setPortCode(portHarbourCodeName);
					dmPortWharfItem.setNote(remarks);
					dmPortWharfItem.setPortRegionCode(portRegionCode);
					dmPortWharfItem.setPortHarbourCode(portHarbourCode);
					dmPortWharfItem.setDwt(BigDecimal.valueOf(dwt));
					dmPortWharfItem.setLoa(loa);
					dmPortWharfItem.setMaxDraft(maxDraft);
					dmPortWharfItem.setManagedVinalines(managedVinalines);
					dmPortWharfItem.setSequenceNo(Integer
							.valueOf(sequenceNoEdit));
					dmPortWharfItem.setPortWharfPayment(portWharfPament);

					if (dmPortWharfItem.getIsDelete() == 1) {
						status = "2";
					}
					dmPortWharfItem.setIsDelete(0);
					dmPortWharfItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmPortWharfItem.setModifiedDate(new Date());
					dmPortWharfItem.setSyncVersion("1|");
					DmPortWharfLocalServiceUtil
							.updateDmPortWharf(dmPortWharfItem);
				} else {
					log.info("messageType103  Them/ Sua");
					details.setIsDelete(0);
					details.setMarkedAsDelete(0);
					details.setRequestedDate(new Date());
					details.setModifiedDate(new Date());
					details.setSyncVersion("1|");
					DmPortWharfLocalServiceUtil.addDmPortWharf(details);
				}
				DmHistoryPortWharf dmHistoryPortWharfItem = DmHistoryPortWharfLocalServiceUtil
						.findByPortWharfCodeAndSyncVersion(portWharfCode,
								syncVersion);

				if (dmHistoryPortWharfItem != null) {
					dmHistoryPortWharfItem.setPortWharfCode(portWharfCode);
					dmHistoryPortWharfItem.setPortWharfName(portWharfNameVN);
					dmHistoryPortWharfItem.setPortWharfNameVN(portWharfNameVN);
					dmHistoryPortWharfItem.setPortWharfShortName(portWharfShortName);
					dmHistoryPortWharfItem.setPortCode(portHarbourCodeName);					
					dmHistoryPortWharfItem.setNote(remarks);
					dmHistoryPortWharfItem.setPortRegionCode(portRegionCode);
					dmHistoryPortWharfItem.setPortHarbourCode(portHarbourCode);
					dmHistoryPortWharfItem.setDwt(BigDecimal.valueOf(dwt));
					dmHistoryPortWharfItem.setLoa(loa);
					dmHistoryPortWharfItem.setMaxDraft(maxDraft);
					dmHistoryPortWharfItem
							.setManagedVinalines(managedVinalines);
					dmHistoryPortWharfItem.setSequenceNo(Integer
							.valueOf(sequenceNoEdit));
					dmHistoryPortWharfItem.setPortWharfPayment(portWharfPament);

					dmHistoryPortWharfItem.setIsDelete(0);
					dmHistoryPortWharfItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryPortWharfItem.setModifiedDate(new Date());
					dmHistoryPortWharfItem.setSyncVersion("1");
					history = DmHistoryPortWharfLocalServiceUtil
							.updateDmHistoryPortWharf(dmHistoryPortWharfItem);
				} else {
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryPortWharfLocalServiceUtil
							.addDmHistoryPortWharf(history);
				}

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(), userSign != null ? userSign.getModifyuser() : StringPool.BLANK,
						LogsConstant.SUA, "dm_port_wharf",
						dmPortWharfItem.getPortWharfCode(),
						dmPortWharfItem.getPortWharfNameVN());

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK,
						LogsConstant.SUA, "dm_history_port_wharf",
						history.getPortWharfCode(),
						history.getPortWharfNameVN());
			}
			int messageType103 = 103;
			log.info("==guiBanTinSangHQMC==dongbodanhmuc MessageType=="
					+ messageType103);

			DMListSyn synch = new DMListSyn();
			synch.synchronizePortWharfList(messageType103, history, status,
					maritimeCode);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		//todo tinh sau
	}
}
