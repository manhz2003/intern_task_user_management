package vn.gt.portlet.danhmuc;

import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmDataitem;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.haiquan2giaothong.DMListSyn;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.PageType;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmDataItemUtils {


	

	public static JSONObject getDataItemGroup107(long userId,
			String maritimeCode, String status, String codeGroup, int start,
			int end) {
		List<DmDataitem> dmDataItems = DmDataItemLocalServiceUtil
				.findDataItems(MessageType.NHOM_DM_CANG_BIEN_HANG_HAI, null,
						maritimeCode, codeGroup, status, start, end);
		Long total = DmDataItemLocalServiceUtil.countDataItems(
				MessageType.NHOM_DM_CANG_BIEN_HANG_HAI, null, maritimeCode,
				codeGroup, status);
		JSONObject results = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();
		for (DmDataitem dmDataItem : dmDataItems) {
			JSONObject json = JSONFactoryUtil.createJSONObject();
			json.put("dataItemId", dmDataItem.getId());
			json.put("dataGroupId", dmDataItem.getDatagroupid());
			json.put("dataGroupId", dmDataItem.getDatagroupid());
			json.put("code0", dmDataItem.getCode());
			json.put("name", dmDataItem.getName());
			json.put("node1", dmDataItem.getNode1());
			json.put("node2", dmDataItem.getNode2());
			json.put("node3", dmDataItem.getNode3());
			json.put("node4", dmDataItem.getNode4());
			json.put("level", dmDataItem.getLevel());
			json.put("description", dmDataItem.getDescription());
			json.put("validatedFrom", dmDataItem.getValidatedfrom());
			try {
				json.put("maritimeNameVN", DmMaritimeLocalServiceUtil
						.getByMaritimeCode(dmDataItem.getNode1())
						.getMaritimeNameVN());
			} catch (Exception e) {
				json.put("maritimeNameVN", "");
			}
			json.put("status", dmDataItem.getStatus());
			json.put(
					"role",
					dmDataItem.getNode1().equals("")
							|| dmDataItem.getNode1().equals(
									DanhMucUtils.getMaritimeCurrent(userId)
											.getString("maritimeCode")) ? true
							: false);
			array.put(json);
		}
		results.put("data", array);
		results.put("total", total);
		return results;
	}

	public static JSONObject getDataItemGroup124(String name, String status,
			String codeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmDataitem> dmDataItems = DmDataItemLocalServiceUtil
				.findDataItems(124, name, null, codeGroup, status, start, end);
		total = DmDataItemLocalServiceUtil.countDataItems(124, name, null,
				codeGroup, status);

		for (DmDataitem dmDataItem : dmDataItems) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", dmDataItem.getId());
			obj.put("dataGroupId", dmDataItem.getDatagroupid());
			obj.put("code0", dmDataItem.getCode());
			obj.put("name", dmDataItem.getName());
			obj.put("node1", dmDataItem.getNode1());
			obj.put("node2", dmDataItem.getNode2());
			obj.put("node3", dmDataItem.getNode3());
			obj.put("node4", dmDataItem.getNode4());
			obj.put("level", dmDataItem.getLevel());
			obj.put("description", dmDataItem.getDescription());
			obj.put("status", dmDataItem.getStatus());
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	// Tuyen Luong: DataGroupId = 130
	public static JSONObject getDataItems(long dataGroupId, String name,
			long userId, String maritimeCode, String status, String codeGroup,
			int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;

		List<DmDataitem> dmDataItems = DmDataItemLocalServiceUtil
				.findDataItems(dataGroupId, name, maritimeCode, codeGroup,
						status, start, end);
		total = DmDataItemLocalServiceUtil.countDataItems(dataGroupId, name,
				maritimeCode, codeGroup, status);

		for (DmDataitem dmDataItem : dmDataItems) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("dataItemId", dmDataItem.getId());
			obj.put("dataGroupId", dmDataItem.getDatagroupid());
			obj.put("code", dmDataItem.getCode());
			obj.put("name", dmDataItem.getName());
			obj.put("node1", dmDataItem.getNode1());
			obj.put("node2", dmDataItem.getNode2());
			obj.put("node3", dmDataItem.getNode3());
			obj.put("node4", dmDataItem.getNode4());
			obj.put("level", dmDataItem.getLevel());
			obj.put("description", dmDataItem.getDescription());
			obj.put("status", dmDataItem.getStatus());
			try {
				obj.put("maritimeNameVN", DmMaritimeLocalServiceUtil
						.getByMaritimeCode(dmDataItem.getNode1())
						.getMaritimeNameVN());
			} catch (Exception e) {
				obj.put("maritimeNameVN", "");
			}
			try {
				obj.put("role",
						dmDataItem.getNode1().equals("")
								|| dmDataItem.getNode1().equals(
										DanhMucUtils.getMaritimeCurrent(userId)
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

	public static JSONObject getDetailDataItem107(String node2)
			throws SystemException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmDataitem dmDataItem = DmDataItemLocalServiceUtil
				.findByDataGroupId_Node2(107, node2);
		result.put("dataItemId", dmDataItem.getId());
		result.put("dataGroupId", dmDataItem.getDatagroupid());
		result.put("code0", dmDataItem.getCode());
		result.put("name", dmDataItem.getName());
		result.put("node1", dmDataItem.getNode1());
		result.put("node2", dmDataItem.getNode2());
		result.put("node3", dmDataItem.getNode3());
		result.put("node4", dmDataItem.getNode4());
		result.put("level", dmDataItem.getLevel());
		result.put("validatedFrom", dmDataItem.getValidatedfrom());
		result.put("note", dmDataItem.getDescription());
		return result;
	}

	// Tuyen luong: datagroupId = 130
	public static JSONObject getDetailDataItem(long dataItemId)
			throws SystemException, PortalException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmDataitem dataItem = DmDataItemLocalServiceUtil
				.fetchDmDataItem(dataItemId);
		result.put("id", dataItem.getId());
		result.put("dataGroupId", dataItem.getDatagroupid());
		result.put("code0", dataItem.getCode());
		result.put("name", dataItem.getName());
		result.put("node1", dataItem.getNode1());
		result.put("node2", dataItem.getNode2());
		result.put("node3", dataItem.getNode3());
		result.put("node4", dataItem.getNode4());
		result.put("level", dataItem.getLevel());
		result.put("description", dataItem.getDescription());
		result.put("validatedFrom", dataItem.getValidatedfrom());
		result.put("status", dataItem.getStatus());
		return result;
	}

	public static JSONObject getDetailDataItem124(long dataItemId)
			throws SystemException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmDataitem dmDataItem = DmDataItemLocalServiceUtil
				.fetchDmDataItem(dataItemId);
		result.put("id", dmDataItem.getId());
		result.put("dataGroupId", dmDataItem.getDatagroupid());
		result.put("code0", dmDataItem.getCode());
		result.put("name", dmDataItem.getName());
		result.put("node1", dmDataItem.getNode1());
		result.put("node2", dmDataItem.getNode2());
		result.put("node3", dmDataItem.getNode3());
		result.put("node4", dmDataItem.getNode4());
		result.put("level", dmDataItem.getLevel());
		result.put("description", dmDataItem.getDescription());
		result.put("validatedFrom", dmDataItem.getValidatedfrom());
		result.put("status", dmDataItem.getStatus());
		return result;
	}

	public static void actionUpdateDataItem(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		try {
			long dataitemId = ParamUtil.getLong(actionRequest, "dataitemId");
			String code = DanhMucUtils.getString(actionRequest, "code0");
			String nameS = DanhMucUtils.getString(actionRequest, "name");
			String maritimeportCode = UserPortLocalServiceUtil.findByUserId(
					GetterUtil.getLong(themeDisplay.getUserId())).getPortCode();
			String maritimeReference = StringPool.BLANK;
			String maritimeCode = DanhMucUtils.getString(actionRequest,
					"maritimeCode");
			if (Validator.isNotNull(maritimeCode)
					& !maritimeCode.equals(maritimeportCode)) {
				return;
			}
			maritimeReference = DmMaritimeLocalServiceUtil.getByMaritimeCode(UserPortLocalServiceUtil.findByUserId(
					GetterUtil.getLong(themeDisplay.getUserId())).getPortCode()).getMaritimeReference();

			String capmoi = DanhMucUtils.getString(actionRequest,
					PageType.LAN_CAP_MOI_DU_LIEU);
			String capsua = DanhMucUtils.getString(actionRequest,
					PageType.LAN_SUA_DU_LIEU);
			String danhdauXoa = DanhMucUtils.getString(actionRequest,
					PageType.LAN_XOA_DU_LIEU);

			DmDataitem details = new DmDataitem();
			details.setDatagroupid(130);
			details.setNode1(maritimeportCode);
			details.setCode(code);
			details.setName(nameS);
			details.setLevel(1);
			details.setValidatedfrom(new Date());
			details.setStatus(1);
			details.setOrder(1);
			String status = "0";
			if (danhdauXoa.length() > 0) {
				log.info("messageType100  Xoa");
				status = "0";
				DmDataitem dataItem = DmDataItemLocalServiceUtil
						.fetchDmDataItem(dataitemId);
				dataItem.setStatus(0);

				DmDataItemLocalServiceUtil.updateDmDataItem(dataItem);

			} else if (capmoi.length() > 0) {
				status = "2";
				DmDataitem dataItem = new DmDataitem();
				dataItem.setDatagroupid(130);
				dataItem.setNode1(maritimeportCode);
				dataItem.setCode(maritimeReference + code);
				dataItem.setName(nameS);
				dataItem.setLevel(1);
				dataItem.setValidatedfrom(new Date());
				dataItem.setStatus(1);
				dataItem.setOrder(1);

				DmDataItemLocalServiceUtil.updateDmDataItem(dataItem);
			} else if (capsua.length() > 0) {
				status = "1";
				DmDataitem dataItem = DmDataItemLocalServiceUtil
						.fetchDmDataItem(dataitemId);
				if (dataItem.getStatus() == 0) {
					status = "2";
				}
				dataItem.setCode(code);
				dataItem.setName(nameS);

				DmDataItemLocalServiceUtil.updateDmDataItem(dataItem);
			}
			int messageType100 = 100;
			log.info("==guiBanTinSangHQMC==dongbodanhmuc MessageType=="
					+ messageType100);

			DMListSyn synch = new DMListSyn();
			synch.synchronizeReferencePortList(messageType100, details, status,
					maritimeCode);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
//		PortletConfig portletConfig = (PortletConfig) actionRequest
//				.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(actionRequest, portletConfig.getPortletName()
//				+ SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}

	public static void actionUpdateDataItemGroup107(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) throws Exception {
		try {
			String maritimeCode = UserPortLocalServiceUtil.findByUserId(
					GetterUtil.getLong(themeDisplay.getUserId())).getPortCode();
			String maritimeCodeReq = DanhMucUtils.getString(actionRequest,
					"maritimeCode");
			if (Validator.isNotNull(maritimeCodeReq)
					& !maritimeCode.equals(maritimeCodeReq)) {
				return;
			}
			String DataItem107Code = ParamUtil
					.getString(actionRequest, "code0");
			String DataItem107Name = DanhMucUtils.getString(actionRequest,
					"name");
			String DataItem107Node2 = DanhMucUtils.getString(actionRequest,
					"node2");
			String remarks = DanhMucUtils.getString(actionRequest, "note");
			long dataitemId = ParamUtil.getLong(actionRequest, "dataitemId");
			int DataGroupId = MessageType.NHOM_DM_CANG_BIEN_HANG_HAI;
			int itemLevel = 0;
			int itemOrder = 0;
			int itemStatus = 1;
			String DataItem107Node1 = maritimeCode;
			String DataItem107ShortName = DataItem107Node2;
			DmMaritime dmMaritimeItem = DmMaritimeLocalServiceUtil
					.getByMaritimeCode(maritimeCode);

			if (dmMaritimeItem != null) {
				DataItem107Code = dmMaritimeItem.getMaritimeReference();
			}
			String status = "0";
			String capmoi = DanhMucUtils.getString(actionRequest,
					PageType.LAN_CAP_MOI_DU_LIEU);
			String capsua = DanhMucUtils.getString(actionRequest,
					PageType.LAN_SUA_DU_LIEU);
			String danhdauXoa = DanhMucUtils.getString(actionRequest,
					PageType.LAN_XOA_DU_LIEU);
			log.info("messageType105 -- DM_DATAITEM_GROUP107 ");

			DmDataitem details = new DmDataitem();
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
				DmDataitem dmDataItem = DmDataItemLocalServiceUtil
						.fetchDmDataItem(dataitemId);
				dmDataItemItem = dmDataItem;
				dmDataItemItem.setStatus(0);
				dmDataItemItem.setValidatedto(new Date());
				DmDataItemLocalServiceUtil.updateDmDataItem(dmDataItemItem);
			} else if (capmoi.length() > 0) {
				log.info("messageType105 Them");
				status = "2";
				if (DataItem107Node2.trim().length() == 0) {
					DataItem107Node2 = ReportsBusinessUtils.taoSo("PORTCODE",
							DataItem107Code, 2);
					DataItem107ShortName = DataItem107Node2;
				}
				DmDataitem dmDataItem = DmDataItemLocalServiceUtil
						.fetchDmDataItem(dataitemId);

				if (dmDataItem != null) {
					dmDataItem.setDatagroupid(DataGroupId);
					dmDataItem.setCode(DataItem107Code);
					dmDataItem.setName(DataItem107Name);
					dmDataItem.setDescription(remarks);
					dmDataItem.setNode1(DataItem107Node1);
					dmDataItem.setNode2(DataItem107Node2);
					dmDataItem.setShortName(DataItem107ShortName);
					dmDataItem.setLevel(itemLevel);
					dmDataItem.setOrder(itemOrder);
					dmDataItem.setStatus(itemStatus);
					dmDataItem.setValidatedfrom(new Date());
					dmDataItem.setValidatedto(null);
					DmDataItemLocalServiceUtil.updateDmDataItem(dmDataItem);
				} else {
					details.setNode2(DataItem107Node2);
					details.setShortName(DataItem107ShortName);
					DmDataItemLocalServiceUtil.addDmDataitem(details);
				}
			} else if (capsua.length() > 0) {
				log.info("messageType105  Sua");
				status = "1";
				DmDataitem dmDataItem = DmDataItemLocalServiceUtil
						.fetchDmDataItem(dataitemId);
				if (dmDataItem != null) {
					if (dmDataItem.getStatus() == 0) {
						status = "2";
					}

					dmDataItem.setDatagroupid(DataGroupId);
					dmDataItem.setCode(DataItem107Code);
					dmDataItem.setName(DataItem107Name);
					dmDataItem.setDescription(remarks);
					dmDataItem.setNode1(DataItem107Node1);
					dmDataItem.setNode2(DataItem107Node2);
					dmDataItem.setShortName(DataItem107ShortName);
					dmDataItem.setLevel(itemLevel);
					dmDataItem.setOrder(itemOrder);
					dmDataItem.setStatus(itemStatus);
					dmDataItem.setValidatedfrom(new Date());
					dmDataItem.setValidatedto(null);

					DmDataItemLocalServiceUtil.updateDmDataItem(dmDataItem);
				} else {
					log.info("messageType105  Them/ Sua");
					DmDataItemLocalServiceUtil.addDmDataitem(details);
				}
			}
			int messageType105 = 105;
			log.info("==guiBanTinSangHQMC==dongbodanhmuc MessageType=="
					+ messageType105);

			DMListSyn synch = new DMListSyn();
			synch.synchronizeReferencePortList(messageType105, details, status,
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
