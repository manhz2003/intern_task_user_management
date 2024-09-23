package vn.gt.portlet.danhmuc;

import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.VmaAccidentList;
import vn.gt.dao.noticeandmessage.service.VmaAccidentListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.FormatData;
import vn.gt.utils.PageType;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;




import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaAccidentListUtils {

	

	public static JSONObject getVmaAccidentLists(String nameOfShip,
			String flagStateOfShip, String callSign, String imoNumber,
			String registryNumber, Date accidentTime, String accidentType,
			String accidentCriticalType, String numberOfDead,
			String numberOfMissed, String numberOfInjured, String pilotOnBoad,
			String makeInvestigation, Date investigationDate,
			String portofAuthority, Date accidentOfficialDate, int start,
			int end) throws SystemException {
		List<VmaAccidentList> vmaAccidentLists = VmaAccidentListLocalServiceUtil
				.getVmaAccidentLists(nameOfShip, flagStateOfShip, callSign,
						imoNumber, registryNumber, accidentTime, accidentType,
						accidentCriticalType, numberOfDead, numberOfMissed,
						numberOfInjured, pilotOnBoad, makeInvestigation,
						investigationDate, portofAuthority,
						accidentOfficialDate, start, end);
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		for (VmaAccidentList vmaAccidentList : vmaAccidentLists) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", vmaAccidentList.getId());
			obj.put("portofAuthority", vmaAccidentList.getPortofAuthority());
			obj.put("accidentCode", vmaAccidentList.getAccidentCode());
			try {
				obj.put("accidentTime", vmaAccidentList.getAccidentTime()
						.getTime());
			} catch (Exception e) {
				obj.put("accidentTime", StringPool.BLANK);
			}
			obj.put("accidentRegion", vmaAccidentList.getAccidentRegion());
			obj.put("accidentBrief", vmaAccidentList.getAccidentBrief());
			obj.put("accidentConclusion",
					vmaAccidentList.getAccidentConclusion());
			obj.put("accidentType", vmaAccidentList.getAccidentType());
			obj.put("accidentCriticalType",
					vmaAccidentList.getAccidentCriticalType());
			obj.put("nameOfShip", vmaAccidentList.getNameOfShip());
			obj.put("imoNumber", vmaAccidentList.getImoNumber());
			obj.put("callSign", vmaAccidentList.getCallSign());
			obj.put("flagStateOfShip", vmaAccidentList.getFlagStateOfShip());
			obj.put("registryNumber", vmaAccidentList.getRegistryNumber());
			obj.put("domesticShip", vmaAccidentList.getDomesticShip());
			obj.put("internationalShip", vmaAccidentList.getInternationalShip());
			obj.put("damageHumanLife", vmaAccidentList.getDamageHumanLife());
			obj.put("numberOfDead", vmaAccidentList.getNumberOfDead());
			obj.put("numberOfMissed", vmaAccidentList.getNumberOfMissed());
			obj.put("numberOfInjured", vmaAccidentList.getNumberOfInjured());
			obj.put("damageToCargo", vmaAccidentList.getDamageToCargo());
			obj.put("remarksOfCargo", vmaAccidentList.getRemarksOfCargo());
			obj.put("damageToShip", vmaAccidentList.getDamageToShip());
			obj.put("remarksOfShip", vmaAccidentList.getRemarksOfShip());
			obj.put("damageToEnvironment",
					vmaAccidentList.getDamageToEnvironment());
			obj.put("remarksOfEnvironment",
					vmaAccidentList.getRemarksOfEnvironment());
			obj.put("damageToMarineActivity",
					vmaAccidentList.getDamageToMarineActivity());
			obj.put("remarksOfMarineActivity",
					vmaAccidentList.getRemarksOfMarineActivity());
			obj.put("accidentOfficialNo",
					vmaAccidentList.getAccidentOfficialNo());
			try {
				obj.put("accidentOfficialDate", vmaAccidentList
						.getAccidentOfficialDate().getTime());
			} catch (Exception e) {
				obj.put("accidentOfficialDate", StringPool.BLANK);
			}
			obj.put("pilotOnBoad", vmaAccidentList.getPilotOnBoad());
			obj.put("makeInvestigation", vmaAccidentList.getMakeInvestigation());
			try {
				obj.put("investigationDate",
						vmaAccidentList.getInvestigationDate());
			} catch (Exception e) {
				obj.put("investigationDate", StringPool.BLANK);
			}
			obj.put("investigationOffice",
					vmaAccidentList.getInvestigationOffice());
			obj.put("f1AttachedReport", vmaAccidentList.getF1AttachedReport());
			obj.put("f2AttachedReport", vmaAccidentList.getF2AttachedReport());
			obj.put("f3AttachedReport", vmaAccidentList.getF3AttachedReport());
			obj.put("f4AttachedReport", vmaAccidentList.getF4AttachedReport());
			obj.put("modifiedDate", vmaAccidentList.getModifiedDate());
			array.put(obj);
		}
		total = VmaAccidentListLocalServiceUtil.countVmaAccidentLists(
				nameOfShip, flagStateOfShip, callSign, imoNumber,
				registryNumber, accidentTime, accidentType,
				accidentCriticalType, numberOfDead, numberOfMissed,
				numberOfInjured, pilotOnBoad, makeInvestigation,
				investigationDate, portofAuthority, accidentOfficialDate);
		result.put("total", total);
		result.put("data", array);

		return result;
	}

	public static JSONObject actionUpdateVmaAccidentList(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());
		String maritimeCode = userPort.getPortCode();
		String portofAuthority = maritimeCode;
		String accidentCode = ParamUtil
				.getString(actionRequest, "accidentCode");
		String accidentTime = ParamUtil
				.getString(actionRequest, "accidentTime");
		String accidentRegion = DanhMucUtils.getString(actionRequest,
				"accidentRegion");
		String accidentBrief = DanhMucUtils.getString(actionRequest,
				"accidentBrief");
		String accidentConclusion = DanhMucUtils.getString(actionRequest,
				"accidentConclusion");
		String accidentType = ParamUtil
				.getString(actionRequest, "accidentType");
		String accidentCriticalType = DanhMucUtils.getString(actionRequest,
				"accidentCriticalType");
		String nameOfShip = DanhMucUtils.getString(actionRequest, "nameOfShip");
		String IMONumber = DanhMucUtils.getString(actionRequest, "imoNumber");
		String callSign = DanhMucUtils.getString(actionRequest, "callSign");
		String flagStateOfShip = DanhMucUtils.getString(actionRequest,
				"flagStateOfShip");
		String registryNumber = DanhMucUtils.getString(actionRequest,
				"registryNumber");
		String domesticShip = ParamUtil
				.getString(actionRequest, "domesticShip");
		String internationalShip = DanhMucUtils.getString(actionRequest,
				"internationalShip");
		String damageHumanLife = DanhMucUtils.getString(actionRequest,
				"damegeHumanLife");
		String numberOfDead = ParamUtil
				.getString(actionRequest, "numberOfDead");
		String numberOfMissed = DanhMucUtils.getString(actionRequest,
				"numberOfMissed");
		String numberOfInjured = DanhMucUtils.getString(actionRequest,
				"numberOfInjured");
		String damageToCargo = DanhMucUtils.getString(actionRequest,
				"damageToCargo");
		String remarksOfShip = DanhMucUtils.getString(actionRequest,
				"remarksOfShip");
		String damageToMarineActivity = DanhMucUtils.getString(actionRequest,
				"damageToMarineActivity");
		String damageToEnvironment = DanhMucUtils.getString(actionRequest,
				"damageToEnvironment");
		String remarksOfEnvironment = DanhMucUtils.getString(actionRequest,
				"remarksOfEnvironment");
		String remarksOfMarineActivity = DanhMucUtils.getString(actionRequest,
				"remarksOfMarineActivity");
		String accidentOfficialNo = DanhMucUtils.getString(actionRequest,
				"accidentOfficialNo");
		String accidentOfficialDate = DanhMucUtils.getString(actionRequest,
				"accidentOfficialDate");
		String pilotOnBoad = DanhMucUtils.getString(actionRequest,
				"pilotOnBoad");
		String makeInvestigation = DanhMucUtils.getString(actionRequest,
				"makeInvestigation");
		String investigationDate = DanhMucUtils.getString(actionRequest,
				"investigationDate");
		String investigationOffice = DanhMucUtils.getString(actionRequest,
				"investigationOffice");
		String f1AttachedReport = DanhMucUtils.getString(actionRequest,
				"f1AttachedReport");
		String f2AttachedReport = DanhMucUtils.getString(actionRequest,
				"f2AttachedReport");
		String f3AttachedReport = DanhMucUtils.getString(actionRequest,
				"f3AttachedReport");
		String f4AttachedReport = DanhMucUtils.getString(actionRequest,
				"f4AttachedReport");
		String remarksOfCargo = DanhMucUtils.getString(actionRequest,
				"remarksOfCargo");
		String capmoi = DanhMucUtils.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String capsua = DanhMucUtils.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String danhdauXoa = DanhMucUtils.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);
		int status = 0;
		String message = "";
		try {
			if (danhdauXoa.length() > 0) {
				log.info("==============  Xoa: VmaAccidentList");
				long id = ParamUtil.getLong(actionRequest, "id");
				VmaAccidentList vmaAccidentList = VmaAccidentListLocalServiceUtil
						.delete(id);
				if (vmaAccidentList != null) {
					status = 1;
					message = "Xóa thành công!";

					VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort
							.getUserId(),
							userSign != null ? userSign.getModifyuser()
									: StringPool.BLANK, LogsConstant.XOA,
							"vma_accident_list", vmaAccidentList
									.getAccidentCode(), StringPool.BLANK);
				}
			} else if (capmoi.length() > 0) {
				log.info("============ Them: VmaAccidentList");
				accidentCode = ReportsBusinessUtils.taoSo("VmaAccidentList", 3);
				VmaAccidentList vmaAccidentList = new VmaAccidentList();
				vmaAccidentList.setPortofAuthority(portofAuthority);
				vmaAccidentList.setAccidentCode(accidentCode);
				vmaAccidentList
						.setAccidentTime((Validator.isNotNull(accidentTime) && !accidentTime
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(accidentTime))) : null);
				vmaAccidentList.setAccidentRegion(accidentRegion);
				vmaAccidentList.setAccidentBrief(accidentBrief);
				vmaAccidentList.setAccidentConclusion(accidentConclusion);
				vmaAccidentList.setAccidentType(accidentType);
				vmaAccidentList.setAccidentCriticalType(accidentCriticalType);
				vmaAccidentList.setNameOfShip(nameOfShip);
				vmaAccidentList.setImoNumber(IMONumber);
				vmaAccidentList.setCallSign(callSign);
				vmaAccidentList.setFlagStateOfShip(flagStateOfShip);
				vmaAccidentList.setRegistryNumber(registryNumber);
				vmaAccidentList.setDomesticShip(domesticShip);
				vmaAccidentList.setInternationalShip(internationalShip);
				vmaAccidentList.setDamageHumanLife(damageHumanLife);
				vmaAccidentList.setNumberOfDead(numberOfDead);
				vmaAccidentList.setNumberOfMissed(numberOfMissed);
				vmaAccidentList.setNumberOfInjured(numberOfInjured);
				vmaAccidentList.setDamageToCargo(damageToCargo);
				vmaAccidentList.setRemarksOfShip(remarksOfShip);
				vmaAccidentList.setRemarksOfCargo(remarksOfCargo);
				vmaAccidentList
						.setDamageToMarineActivity(damageToMarineActivity);
				vmaAccidentList.setDamageToEnvironment(damageToEnvironment);
				vmaAccidentList
						.setRemarksOfMarineActivity(remarksOfMarineActivity);
				vmaAccidentList.setRemarksOfEnvironment(remarksOfEnvironment);
				vmaAccidentList
						.setAccidentOfficialDate((Validator
								.isNotNull(accidentOfficialDate) && !accidentOfficialDate
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(accidentOfficialDate))) : null);
				vmaAccidentList.setAccidentOfficialNo(accidentOfficialNo);
				vmaAccidentList.setPilotOnBoad(pilotOnBoad);
				vmaAccidentList.setMakeInvestigation(makeInvestigation);
				vmaAccidentList.setInvestigationDate((Validator
						.isNotNull(investigationDate) && !investigationDate
						.isEmpty()) ? FormatData.formatDateShort3
						.parse(DanhMucUtils.timeStamp2Date(Long
								.valueOf(investigationDate))) : null);
				vmaAccidentList.setInvestigationOffice(investigationOffice);
				vmaAccidentList.setF1AttachedReport(f1AttachedReport);
				vmaAccidentList.setF2AttachedReport(f2AttachedReport);
				vmaAccidentList.setF3AttachedReport(f3AttachedReport);
				vmaAccidentList.setF4AttachedReport(f4AttachedReport);

				VmaAccidentList accidentList = VmaAccidentListLocalServiceUtil
						.addVmaAccidentList(vmaAccidentList);
				if (accidentList != null) {
					status = 1;
					message = "Thêm thành công!";

					VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort
							.getUserId(),
							userSign != null ? userSign.getModifyuser()
									: StringPool.BLANK, LogsConstant.THEM,
							"vma_accident_list", vmaAccidentList
									.getAccidentCode(), StringPool.BLANK);
				}
			} else if (capsua.length() > 0) {
				log.info("========= Sua: VmaAccidentList");
				long id = ParamUtil.getLong(actionRequest, "id");
				VmaAccidentList vmaAccidentList = VmaAccidentListLocalServiceUtil
						.fetchVmaAccidentList(id);
				if (vmaAccidentList != null) {
					vmaAccidentList.setPortofAuthority(portofAuthority);
					vmaAccidentList.setAccidentCode(accidentCode);
					vmaAccidentList
							.setAccidentTime((Validator.isNotNull(accidentTime) && !accidentTime
									.isEmpty()) ? FormatData.formatDateShort3
									.parse(DanhMucUtils.timeStamp2Date(Long
											.valueOf(accidentTime))) : null);
					vmaAccidentList.setAccidentRegion(accidentRegion);
					vmaAccidentList.setAccidentBrief(accidentBrief);
					vmaAccidentList.setAccidentConclusion(accidentConclusion);
					vmaAccidentList.setAccidentType(accidentType);
					vmaAccidentList
							.setAccidentCriticalType(accidentCriticalType);
					vmaAccidentList.setNameOfShip(nameOfShip);
					vmaAccidentList.setImoNumber(IMONumber);
					vmaAccidentList.setCallSign(callSign);
					vmaAccidentList.setFlagStateOfShip(flagStateOfShip);
					vmaAccidentList.setRegistryNumber(registryNumber);
					vmaAccidentList.setDomesticShip(domesticShip);
					vmaAccidentList.setInternationalShip(internationalShip);
					vmaAccidentList.setDamageHumanLife(damageHumanLife);
					vmaAccidentList.setNumberOfDead(numberOfDead);
					vmaAccidentList.setNumberOfMissed(numberOfMissed);
					vmaAccidentList.setNumberOfInjured(numberOfInjured);
					vmaAccidentList.setDamageToCargo(damageToCargo);
					vmaAccidentList.setRemarksOfShip(remarksOfShip);
					vmaAccidentList.setRemarksOfCargo(remarksOfCargo);
					vmaAccidentList
							.setDamageToMarineActivity(damageToMarineActivity);
					vmaAccidentList.setDamageToEnvironment(damageToEnvironment);
					vmaAccidentList
							.setRemarksOfMarineActivity(remarksOfMarineActivity);
					vmaAccidentList
							.setRemarksOfEnvironment(remarksOfEnvironment);
					vmaAccidentList
							.setAccidentOfficialDate((Validator
									.isNotNull(accidentOfficialDate) && !accidentOfficialDate
									.isEmpty()) ? FormatData.formatDateShort3
									.parse(DanhMucUtils.timeStamp2Date(Long
											.valueOf(accidentOfficialDate)))
									: null);
					vmaAccidentList.setAccidentOfficialNo(accidentOfficialNo);
					vmaAccidentList.setPilotOnBoad(pilotOnBoad);
					vmaAccidentList.setMakeInvestigation(makeInvestigation);
					vmaAccidentList.setInvestigationDate((Validator
							.isNotNull(investigationDate) && !investigationDate
							.isEmpty()) ? FormatData.formatDateShort3
							.parse(DanhMucUtils.timeStamp2Date(Long
									.valueOf(investigationDate))) : null);
					vmaAccidentList.setInvestigationOffice(investigationOffice);
					vmaAccidentList.setF1AttachedReport(f1AttachedReport);
					vmaAccidentList.setF2AttachedReport(f2AttachedReport);
					vmaAccidentList.setF3AttachedReport(f3AttachedReport);
					vmaAccidentList.setF4AttachedReport(f4AttachedReport);

					VmaAccidentList accidentList = VmaAccidentListLocalServiceUtil
							.updateVmaAccidentList(vmaAccidentList);
					if (accidentList != null) {
						status = 1;
						message = "Sửa thành công!";

						VmaAuditLogLocalServiceUtil.addVmaAuditLog(userPort
								.getUserId(),
								userSign != null ? userSign.getModifyuser()
										: StringPool.BLANK, LogsConstant.SUA,
								"vma_accident_list", vmaAccidentList
										.getAccidentCode(), StringPool.BLANK);
					}
				}
			}
			//todo tinh sau
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("status", status);
		result.put("message", DanhMucUtils.encodeUTF8(message));
		return result;
	}

	public static JSONObject getDetailVmaAccidentList(long id)
			throws PortalException, SystemException {
		VmaAccidentList vmaAccidentList = VmaAccidentListLocalServiceUtil
				.getVmaAccidentList(id);
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		obj.put("id", vmaAccidentList.getId());
		obj.put("portofAuthority", vmaAccidentList.getPortofAuthority());
		obj.put("accidentCode", vmaAccidentList.getAccidentCode());
		try {
			obj.put("accidentTime", vmaAccidentList.getAccidentTime().getTime());
		} catch (Exception e) {
			obj.put("accidentTime", StringPool.BLANK);
		}
		obj.put("accidentRegion", vmaAccidentList.getAccidentRegion());
		obj.put("accidentBrief", vmaAccidentList.getAccidentBrief());
		obj.put("accidentConclusion", vmaAccidentList.getAccidentConclusion());
		obj.put("accidentType", vmaAccidentList.getAccidentType());
		obj.put("accidentCriticalType",
				vmaAccidentList.getAccidentCriticalType());
		obj.put("nameOfShip", vmaAccidentList.getNameOfShip());
		obj.put("imoNumber", vmaAccidentList.getImoNumber());
		obj.put("callSign", vmaAccidentList.getCallSign());
		obj.put("flagStateOfShip", vmaAccidentList.getFlagStateOfShip());
		obj.put("registryNumber", vmaAccidentList.getRegistryNumber());
		obj.put("domesticShip", vmaAccidentList.getDomesticShip());
		obj.put("internationalShip", vmaAccidentList.getInternationalShip());
		obj.put("damageHumanLife", vmaAccidentList.getDamageHumanLife());
		obj.put("numberOfDead", vmaAccidentList.getNumberOfDead());
		obj.put("numberOfMissed", vmaAccidentList.getNumberOfMissed());
		obj.put("numberOfInjured", vmaAccidentList.getNumberOfInjured());
		obj.put("damageToCargo", vmaAccidentList.getDamageToCargo());
		obj.put("remarksOfCargo", vmaAccidentList.getRemarksOfCargo());
		obj.put("damageToShip", vmaAccidentList.getDamageToShip());
		obj.put("remarksOfShip", vmaAccidentList.getRemarksOfShip());
		obj.put("damageToEnvironment", vmaAccidentList.getDamageToEnvironment());
		obj.put("remarksOfEnvironment",
				vmaAccidentList.getRemarksOfEnvironment());
		obj.put("damageToMarineActivity",
				vmaAccidentList.getDamageToMarineActivity());
		obj.put("remarksOfMarineActivity",
				vmaAccidentList.getRemarksOfMarineActivity());
		obj.put("accidentOfficialNo", vmaAccidentList.getAccidentOfficialNo());
		try {
			obj.put("accidentOfficialDate", vmaAccidentList
					.getAccidentOfficialDate().getTime());
		} catch (Exception e) {
			obj.put("accidentOfficialDate", StringPool.BLANK);
		}
		obj.put("pilotOnBoad", vmaAccidentList.getPilotOnBoad());
		obj.put("makeInvestigation", vmaAccidentList.getMakeInvestigation());
		try {
			obj.put("investigationDate", vmaAccidentList.getInvestigationDate());
		} catch (Exception e) {
			obj.put("investigationDate", StringPool.BLANK);
		}
		obj.put("investigationOffice", vmaAccidentList.getInvestigationOffice());
		obj.put("f1AttachedReport", vmaAccidentList.getF1AttachedReport());
		obj.put("f2AttachedReport", vmaAccidentList.getF2AttachedReport());
		obj.put("f3AttachedReport", vmaAccidentList.getF3AttachedReport());
		obj.put("f4AttachedReport", vmaAccidentList.getF4AttachedReport());
		obj.put("modifiedDate", vmaAccidentList.getModifiedDate());
		return obj;
	}
}
