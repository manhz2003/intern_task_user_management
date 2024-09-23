package vn.gt.portlet.danhmuc;

import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;

import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.tichhop.message.haiquan2giaothong.DMListSyn;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.PageType;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmMaritimeUtils {

	

	public static JSONObject getMaritimes(long userId, String maritimeCode,
			String isDelete, int start, int end) {
		long total = 0;
		List<DmMaritime> dmMaritimes = DmMaritimeLocalServiceUtil
				.findMaritimes(maritimeCode, isDelete, start, end);
		total = DmMaritimeLocalServiceUtil.countMaritimes(maritimeCode,
				isDelete);

		JSONObject results = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();
		for (DmMaritime dmMaritime : dmMaritimes) {
			JSONObject json = JSONFactoryUtil.createJSONObject();
			json.put("id", dmMaritime.getId());
			json.put("maritimeCode", dmMaritime.getMaritimeCode());
			json.put("cityCode", dmMaritime.getCityCode());
			json.put("maritimeReference", dmMaritime.getMaritimeReference());
			json.put("maritimeName", dmMaritime.getMaritimeName());
			json.put("maritimeNameVN", dmMaritime.getMaritimeNameVN());
			json.put("isDelete", dmMaritime.getIsDelete());
			json.put("shortName", dmMaritime.getShortName());
			json.put(
					"role",
					dmMaritime.getMaritimeCode().equals("")
							|| dmMaritime.getMaritimeCode().equals(
									DanhMucUtils.getMaritimeCurrent(userId)
											.getString("maritimeCode")) ? true
							: false);
			array.put(json);
		}
		results.put("data", array);
		results.put("total", total);
		return results;
	}

	public static JSONObject getDetailMaritime(String maritimeCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
				.getByMaritimeCode(maritimeCode);
		result.put("maritimeCode", dmMaritime.getMaritimeCode());
		result.put("maritimeNameVN", dmMaritime.getMaritimeNameVN());
		result.put("maritimeName", dmMaritime.getMaritimeName());
		result.put("cityCode", dmMaritime.getCityCode());
		result.put("address", dmMaritime.getAddress());
		result.put("maritimeReference", dmMaritime.getMaritimeReference());
		result.put("modifiedDate", dmMaritime.getModifiedDate());
		result.put("stateCode", dmMaritime.getStateCode());
		result.put("shortName", dmMaritime.getShortName());

		return result;
	}

	public static void actionUpdateMaritime(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) throws Exception {
		try {
			String maritimeCode = DanhMucUtils.getString(actionRequest,
					"maritimeCode");
			String MaritimeReference = DanhMucUtils.getString(actionRequest,
					"maritimeReference");
			String MaritimeName = DanhMucUtils.getString(actionRequest,
					"maritimeName");
			String MaritimeNameVN = DanhMucUtils.getString(actionRequest,
					"maritimeNameVN");
			String Address = DanhMucUtils.getString(actionRequest, "address");
			String StateCode = DanhMucUtils.getString(actionRequest,
					"stateCode");
			String CityCode = DanhMucUtils.getString(actionRequest, "cityCode");

			String status = "0";
			String capmoi = DanhMucUtils.getString(actionRequest,
					PageType.LAN_CAP_MOI_DU_LIEU);
			String capsua = DanhMucUtils.getString(actionRequest,
					PageType.LAN_SUA_DU_LIEU);
			String danhdauXoa = DanhMucUtils.getString(actionRequest,
					PageType.LAN_XOA_DU_LIEU);
			String shortName = DanhMucUtils.getString(actionRequest,
					"shortName");
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
			details.setShortName(shortName);

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
			history.setShortName(shortName);

			if (danhdauXoa.length() > 0) {
				if (!maritimeCode.equals(UserPortLocalServiceUtil.findByUserId(
						GetterUtil.getLong(themeDisplay.getUserId()))
						.getPortCode())) {
					return;
				}
				log.info("messageType100  Xoa");
				status = "0";
				DmMaritime dmMaritimeItem = DmMaritimeLocalServiceUtil
						.getByMaritimeCode(maritimeCode);
				dmMaritimeItem.setIsDelete(1);
				dmMaritimeItem.setMarkedAsDelete(1);
				dmMaritimeItem.setModifiedDate(new Date());
				dmMaritimeItem.setSyncVersion("1|");
				DmMaritimeLocalServiceUtil.updateDmMaritime(dmMaritimeItem);

				DmHistoryMaritime dmHistoryMaritimeItem = DmHistoryMaritimeLocalServiceUtil
						.getByMaritimeCodeAndSyncVersion(maritimeCode,
								syncVersion);
				dmHistoryMaritimeItem.setIsDelete(1);
				dmHistoryMaritimeItem.setMarkedAsDelete(1);
				dmHistoryMaritimeItem.setModifiedDate(new Date());
				dmHistoryMaritimeItem.setSyncVersion("1");
				history = DmHistoryMaritimeLocalServiceUtil
						.updateDmHistoryMaritime(dmHistoryMaritimeItem);

			} else if (capmoi.length() > 0) {
				log.info("messageType100 Them");
				status = "2";
				if (maritimeCode.trim().length() == 0) {
					maritimeCode = ReportsBusinessUtils.taoSo("PORTAUTHORITY",
							2);
				}
				DmMaritime dmMaritimeItem = DmMaritimeLocalServiceUtil
						.getByMaritimeCode(maritimeCode);

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
					dmMaritimeItem.setShortName(shortName);

					dmMaritimeItem.setIsDelete(0);
					dmMaritimeItem.setMarkedAsDelete(0);
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
						.getByMaritimeCodeAndSyncVersion(maritimeCode,
								syncVersion);

				if (dmHistoryMaritimeItem != null) {
					dmHistoryMaritimeItem.setMaritimeCode(maritimeCode);
					dmHistoryMaritimeItem.setMaritimeName(MaritimeName);
					dmHistoryMaritimeItem.setMaritimeNameVN(MaritimeNameVN);
					dmHistoryMaritimeItem
							.setMaritimeReference(MaritimeReference);
					dmHistoryMaritimeItem.setMaritimeOrder(100);
					dmHistoryMaritimeItem.setAddress(Address);
					dmHistoryMaritimeItem.setAddressVN(Address);
					dmHistoryMaritimeItem.setStateCode(StateCode);
					dmHistoryMaritimeItem.setCityCode(CityCode);
					dmHistoryMaritimeItem.setShortName(shortName);

					dmHistoryMaritimeItem.setIsDelete(0);
					dmHistoryMaritimeItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryMaritimeItem.setModifiedDate(new Date());
					dmHistoryMaritimeItem.setSyncVersion("1");
					history = DmHistoryMaritimeLocalServiceUtil
							.updateDmHistoryMaritime(dmHistoryMaritimeItem);
				} else {
					history.setMaritimeCode(maritimeCode);
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryMaritimeLocalServiceUtil
							.addDmHistoryMaritime(history);
				}

			} else if (capsua.length() > 0) {
				if (!maritimeCode.equals(UserPortLocalServiceUtil.findByUserId(
						GetterUtil.getLong(themeDisplay.getUserId()))
						.getPortCode())) {
					return;
				}
				log.info("messageType100  Sua");
				status = "1";
				DmMaritime dmMaritimeItem = DmMaritimeLocalServiceUtil
						.getByMaritimeCode(maritimeCode);
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
					dmMaritimeItem.setShortName(shortName);

					if (dmMaritimeItem.getIsDelete() == 1) {
						status = "2";
					}
					dmMaritimeItem.setIsDelete(0);
					dmMaritimeItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmMaritimeItem.setModifiedDate(new Date());
					dmMaritimeItem.setSyncVersion("1|");
					DmMaritimeLocalServiceUtil.updateDmMaritime(dmMaritimeItem);
				} else {
					log.info("messageType100  Them/ Sua");
					details.setIsDelete(0);
					details.setMarkedAsDelete(0);
					details.setRequestedDate(new Date());
					details.setModifiedDate(new Date());
					details.setSyncVersion("1|");
					DmMaritimeLocalServiceUtil.addDmMaritime(details);
				}

				DmHistoryMaritime dmHistoryMaritimeItem = DmHistoryMaritimeLocalServiceUtil
						.getByMaritimeCodeAndSyncVersion(maritimeCode,
								syncVersion);

				if (dmHistoryMaritimeItem != null) {
					dmHistoryMaritimeItem.setMaritimeCode(maritimeCode);
					dmHistoryMaritimeItem.setMaritimeName(MaritimeName);
					dmHistoryMaritimeItem.setMaritimeNameVN(MaritimeNameVN);
					dmHistoryMaritimeItem
							.setMaritimeReference(MaritimeReference);
					dmHistoryMaritimeItem.setMaritimeOrder(100);
					dmHistoryMaritimeItem.setAddress(Address);
					dmHistoryMaritimeItem.setAddressVN(Address);
					dmHistoryMaritimeItem.setStateCode(StateCode);
					dmHistoryMaritimeItem.setCityCode(CityCode);
					dmHistoryMaritimeItem.setShortName(shortName);

					dmHistoryMaritimeItem.setIsDelete(0);
					dmHistoryMaritimeItem.setMarkedAsDelete(0);
					// dmPortitem.setRequestedDate(new Date());
					dmHistoryMaritimeItem.setModifiedDate(new Date());
					dmHistoryMaritimeItem.setSyncVersion("1");
					history = DmHistoryMaritimeLocalServiceUtil
							.updateDmHistoryMaritime(dmHistoryMaritimeItem);
				} else {
					history.setIsDelete(0);
					history.setMarkedAsDelete(0);
					history.setRequestedDate(new Date());
					history.setModifiedDate(new Date());
					history.setSyncVersion("1");
					history = DmHistoryMaritimeLocalServiceUtil
							.addDmHistoryMaritime(history);
				}
			}
			int messageType100 = 100;
			log.info("==guiBanTinSangHQMC==dongbodanhmuc MessageType=="
					+ messageType100);

			DMListSyn synch = new DMListSyn();
			synch.synchronizePortOfAuthorityList(messageType100, history,
					status, maritimeCode);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
//		PortletConfig portletConfig = (PortletConfig) actionRequest
//				.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
//		SessionMessages.add(actionRequest, portletConfig.getPortletName()
//				+ SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}

}
