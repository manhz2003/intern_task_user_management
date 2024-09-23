package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryShipAgency;
import com.fds.nsw.nghiepvu.model.DmShipAgency;
import vn.gt.dao.danhmuc.service.DmHistoryShipAgencyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;
import vn.gt.utils.PageType;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;




import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmShipAgencyUtils {

	

	public static JSONObject getDetailShipAgency(String shipAgencyCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmShipAgency dmShipAgency = DmShipAgencyLocalServiceUtil
				.getByShipAgencyCode(shipAgencyCode);
		result.put("id", dmShipAgency.getId());
		result.put("shipAgencyCode", dmShipAgency.getShipAgencyCode());
		result.put("shipAgencyNameVN", dmShipAgency.getShipAgencyNameVN());
		result.put("taxCode", dmShipAgency.getTaxCode());
		result.put("stateCode", dmShipAgency.getStateCode());
		result.put("cityCode", dmShipAgency.getCityCode());
		result.put("addressVN", dmShipAgency.getAddressVN());
		result.put("phone", dmShipAgency.getPhone());
		result.put("fax", dmShipAgency.getFax());
		result.put("email", dmShipAgency.getEmail());
		result.put("description", dmShipAgency.getDescription());
		result.put("contacter", dmShipAgency.getContacter());
		result.put("position", dmShipAgency.getPosition());
		result.put("contactTell", dmShipAgency.getContactTell());
		result.put("modifiedDate", dmShipAgency.getModifiedDate());
		result.put("isDelete", dmShipAgency.getIsDelete());
		result.put("syncVersion", dmShipAgency.getSyncVersion());
		result.put("representative1", dmShipAgency.getRepresentative1());
		result.put("representative2", dmShipAgency.getRepresentative2());
		result.put("representativeTitle1",
				dmShipAgency.getRepresentativeTitle1());
		result.put("representativeTitle2",
				dmShipAgency.getRepresentativeTitle2());
		result.put("shipAgencyShortName", dmShipAgency.getShipAgencyShortName());
		return result;
	}

	public static JSONObject getShipAgencys(String shipAgencyNameVN,
			String addressVN, String fax, String taxCode, String isDelete,
			String shipAgencyCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmShipAgency> dmAgency = DmShipAgencyLocalServiceUtil
				.findShipAgencys(shipAgencyNameVN, addressVN, fax, taxCode,
						isDelete, shipAgencyCodeGroup, start, end);
		total = DmShipAgencyLocalServiceUtil.countShipAgencys(shipAgencyNameVN,
				addressVN, fax, taxCode, isDelete, shipAgencyCodeGroup);
		for (DmShipAgency dmShipAgency : dmAgency) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", dmShipAgency.getId());
			obj.put("shipAgencyCode", dmShipAgency.getShipAgencyCode());
			obj.put("shipAgencyNameVN", dmShipAgency.getShipAgencyNameVN());
			obj.put("taxCode", dmShipAgency.getTaxCode());
			obj.put("stateCode", dmShipAgency.getStateCode());
			obj.put("cityCode", dmShipAgency.getCityCode());
			obj.put("addressVN", dmShipAgency.getAddressVN());
			obj.put("phone", dmShipAgency.getPhone());
			obj.put("fax", dmShipAgency.getFax());
			obj.put("email", dmShipAgency.getEmail());
			obj.put("description", dmShipAgency.getDescription());
			obj.put("contacter", dmShipAgency.getContacter());
			obj.put("position", dmShipAgency.getPosition());
			obj.put("contactTell", dmShipAgency.getContactTell());
			obj.put("isDelete", dmShipAgency.getIsDelete());
			obj.put("syncVersion", dmShipAgency.getSyncVersion());
			obj.put("representative1", dmShipAgency.getRepresentative1());
			obj.put("representative2", dmShipAgency.getRepresentative2());
			obj.put("representativeTitle1",
					dmShipAgency.getRepresentativeTitle1());
			obj.put("representativeTitle2",
					dmShipAgency.getRepresentativeTitle2());
			obj.put("shipAgencyShortName",
					dmShipAgency.getShipAgencyShortName());

			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static void actionUpdateDmShipAgency(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());
		String shipAgencyCode = DanhMucUtils.getString(actionRequest,
				"shipAgencyCode");
		String shipAgencyName = DanhMucUtils.getString(actionRequest,
				"shipAgencyName");
		String shipAgencyNameVN = DanhMucUtils.getString(actionRequest,
				"shipAgencyNameVN");
		String taxCode = shipAgencyCode;
		String stateCode = DanhMucUtils.getString(actionRequest, "stateCode");
		String citycode = DanhMucUtils.getString(actionRequest, "citycode");
		String address = DanhMucUtils.getString(actionRequest, "address");
		String addressVN = DanhMucUtils.getString(actionRequest, "addressVN");
		String phone = DanhMucUtils.getString(actionRequest, "phone");
		String fax = DanhMucUtils.getString(actionRequest, "fax");
		String email = DanhMucUtils.getString(actionRequest, "email");
		String description = DanhMucUtils.getString(actionRequest,
				"description");
		String representative1 = DanhMucUtils.getString(actionRequest,
				"representative1");
		String representativeTitle1 = DanhMucUtils.getString(actionRequest,
				"representativeTitle1");
		String representative2 = DanhMucUtils.getString(actionRequest,
				"representative2");
		String representativeTitle2 = DanhMucUtils.getString(actionRequest,
				"representativeTitle2");
		String contacter = DanhMucUtils.getString(actionRequest, "contacter");
		String position = DanhMucUtils.getString(actionRequest, "position");
		String contactTell = DanhMucUtils.getString(actionRequest,
				"contactTell");
		String syncVersion = DanhMucUtils.getString(actionRequest,
				"syncVersion");

		String capmoi = DanhMucUtils.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String capsua = DanhMucUtils.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String danhdauXoa = DanhMucUtils.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);
		// add by TrungNT
		String shipAgencyShortName = DanhMucUtils.getString(actionRequest,
				"shipAgencyShortName");
		if (Validator.isNotNull(shipAgencyCode) && !shipAgencyCode.isEmpty()) {
			try {
				if (danhdauXoa.length() > 0) {
					DmShipAgency dmShipAgency = DmShipAgencyLocalServiceUtil
							.deleteDmShipAgency(shipAgencyCode, DanhMucUtils
									.createNewSyncVersion(syncVersion));

					DmHistoryShipAgency dmHistoryShipAgency = DmHistoryShipAgencyLocalServiceUtil
							.deleteDmHistoryShipAgency(shipAgencyCode,
									DanhMucUtils
											.getLastSyncVersion(dmShipAgency
													.getSyncVersion()));

					VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort
							.getUserId(),
							userSign != null ? userSign.getModifyuser()
									: StringPool.BLANK, LogsConstant.XOA,
							"dm_ship_agency", dmShipAgency.getShipAgencyCode(),
							dmShipAgency.getShipAgencyNameVN());

					VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort
							.getUserId(),
							userSign != null ? userSign.getModifyuser()
									: StringPool.BLANK, LogsConstant.XOA,
							"dm_history_ship_agency", dmHistoryShipAgency
									.getShipAgencyCode(), dmHistoryShipAgency
									.getShipAgencyNameVN());
				} else if (capmoi.length() > 0) {
					if (shipAgencyCode.length() >= 10) {
						try {
							if (DmShipAgencyLocalServiceUtil
									.fetchByShipAgencyCode(shipAgencyCode) != null) {
								log.error("================> MST da ton tai");
								return;
							}
						} catch (Exception e) {
						}

						DmShipAgency dmShipAgency = DmShipAgencyLocalServiceUtil
								.updateDmShipAgency(shipAgencyCode,
										shipAgencyName, shipAgencyNameVN,
										taxCode, stateCode, citycode, address,
										addressVN, phone, fax, email,
										description, representative1,
										representativeTitle1, representative2,
										representativeTitle2, contacter,
										position, contactTell, "1|",
										shipAgencyShortName);

						DmHistoryShipAgency dmHistoryShipAgency = DmHistoryShipAgencyLocalServiceUtil
								.updateDmHistoryShipAgency(shipAgencyCode,
										shipAgencyName, shipAgencyNameVN,
										taxCode, stateCode, citycode, address,
										addressVN, phone, fax, email,
										description, representative1,
										representativeTitle1, representative2,
										representativeTitle2, contacter,
										position, contactTell, "1",
										shipAgencyShortName);
						
						VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort
								.getUserId(),
								userSign != null ? userSign.getModifyuser()
										: StringPool.BLANK, LogsConstant.THEM,
								"dm_ship_agency", dmShipAgency
										.getShipAgencyCode(), dmShipAgency
										.getShipAgencyNameVN());

						VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort
								.getUserId(),
								userSign != null ? userSign.getModifyuser()
										: StringPool.BLANK, LogsConstant.THEM,
								"dm_history_ship_agency", dmHistoryShipAgency
										.getShipAgencyCode(),
								dmHistoryShipAgency.getShipAgencyNameVN());
					} else {
						log.info("============ MST khong duoc phep nho hon 10 ki tu.");
						return;
					}
				} else if (capsua.length() > 0) {
					if (shipAgencyCode.length() >= 10) {
						DmShipAgency dmShipAgency = DmShipAgencyLocalServiceUtil
								.updateDmShipAgency(
										shipAgencyCode,
										shipAgencyName,
										shipAgencyNameVN,
										taxCode,
										stateCode,
										citycode,
										address,
										addressVN,
										phone,
										fax,
										email,
										description,
										representative1,
										representativeTitle1,
										representative2,
										representativeTitle2,
										contacter,
										position,
										contactTell,
										DanhMucUtils
												.createNewSyncVersion(syncVersion),
										shipAgencyShortName);

						DmHistoryShipAgency dmHistoryShipAgency = DmHistoryShipAgencyLocalServiceUtil
								.updateDmHistoryShipAgency(
										shipAgencyCode,
										shipAgencyName,
										shipAgencyNameVN,
										taxCode,
										stateCode,
										citycode,
										address,
										addressVN,
										phone,
										fax,
										email,
										description,
										representative1,
										representativeTitle1,
										representative2,
										representativeTitle2,
										contacter,
										position,
										contactTell,
										DanhMucUtils
												.getLastSyncVersion(dmShipAgency
														.getSyncVersion()),
										shipAgencyShortName);

						VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort
								.getUserId(),
								userSign != null ? userSign.getModifyuser()
										: StringPool.BLANK, LogsConstant.SUA,
								"dm_ship_agency", dmShipAgency
										.getShipAgencyCode(), dmShipAgency
										.getShipAgencyNameVN());

						VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort
								.getUserId(),
								userSign != null ? userSign.getModifyuser()
										: StringPool.BLANK, LogsConstant.SUA,
								"dm_history_ship_agency", dmHistoryShipAgency
										.getShipAgencyCode(),
								dmHistoryShipAgency.getShipAgencyNameVN());
					} else {
						log.info("============ MST khong duoc phep nho hon 10 ki tu.");
						return;
					}
				}
			} catch (Exception e) {
				log.info(e.getMessage());
			}
		}
		//todo tinh sau
	}
}
