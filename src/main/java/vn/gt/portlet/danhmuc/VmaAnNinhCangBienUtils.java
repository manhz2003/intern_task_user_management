package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;


import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien;

import vn.gt.dao.danhmuc.service.VmaAnNinhCangBienLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaAuditLogLocalServiceUtil;
import vn.gt.portlet.phuongtien.LogsConstant;
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
public class VmaAnNinhCangBienUtils {

	

	public static JSONObject getAnNinhCangBiens(int start, int end)
			throws SystemException {
		List<VmaAnNinhCangBien> lstAnNinhCangBiens = VmaAnNinhCangBienLocalServiceUtil
				.getVmaAnNinhCangBiens(start, end);
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = VmaAnNinhCangBienLocalServiceUtil.countVmaAnNinhCangBien();

		if (lstAnNinhCangBiens != null) {
			for (VmaAnNinhCangBien vmaAnNinhCangBien : lstAnNinhCangBiens) {
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				obj.put("ID", vmaAnNinhCangBien.getId());
				obj.put("MaritimeCode", vmaAnNinhCangBien.getMaritimeCode());
				obj.put("CertificateNo", vmaAnNinhCangBien.getCertificateNo());
				obj.put("IssuePlace", vmaAnNinhCangBien.getIssuePlace());
				obj.put("AttachFileId", vmaAnNinhCangBien.getAttachFileId());
				obj.put("PortFacilityName",
						vmaAnNinhCangBien.getPortFacilityName());
				obj.put("PortFacilityAddress",
						vmaAnNinhCangBien.getPortFacilityAddress());
				obj.put("SecurityName", vmaAnNinhCangBien.getSecurityName());
				obj.put("SecurityContact",
						vmaAnNinhCangBien.getSecurityContact());
				obj.put("ExpireStatus", vmaAnNinhCangBien.getExpireStatus());
				obj.put("FirstAuthorized",
						vmaAnNinhCangBien.getFirstAuthorized());
				obj.put("SecondAuthorized",
						vmaAnNinhCangBien.getSecondAuthorized());
				obj.put("ThirdAuthorized",
						vmaAnNinhCangBien.getThirdAuthorized());
				obj.put("FourthAuthorized",
						vmaAnNinhCangBien.getFourthAuthorized());
				obj.put("PassengerShipApproval",
						vmaAnNinhCangBien.getPassengerShipApproval());
				obj.put("PassengerHighSpeedApproval",
						vmaAnNinhCangBien.getPassengerHighSpeedApproval());
				obj.put("CargoHighSpeedApproval",
						vmaAnNinhCangBien.getCargoHighSpeedApproval());
				obj.put("BulkCarrierApproval",
						vmaAnNinhCangBien.getBulkCarrierApproval());
				obj.put("OilTankerApproval",
						vmaAnNinhCangBien.getOilTankerApproval());
				obj.put("ChemicalTankerApproval",
						vmaAnNinhCangBien.getChemicalTankerApproval());
				obj.put("GasCarrierApproval",
						vmaAnNinhCangBien.getGasCarrierApproval());
				obj.put("MobileOffshoreApproval",
						vmaAnNinhCangBien.getMobileOffshoreApproval());
				obj.put("OtherCargoShipApproval",
						vmaAnNinhCangBien.getOtherCargoShipApproval());
				obj.put("MarkedAsDelete", vmaAnNinhCangBien.getMarkedAsDelete());
				obj.put("ModifiedDate", vmaAnNinhCangBien.getModifiedDate());

				try {
					obj.put("IssueDate", vmaAnNinhCangBien.getIssueDate());
				} catch (Exception e) {
					obj.put("IssueDate", StringPool.BLANK);
				}
				try {
					obj.put("ExpireDate", vmaAnNinhCangBien.getExpireDate());
				} catch (Exception e) {
					obj.put("ExpireDate", StringPool.BLANK);
				}
				try {
					obj.put("FirstVerificationDate",
							vmaAnNinhCangBien.getFirstVerificationDate());
				} catch (Exception e) {
					obj.put("FirstVerificationDate", StringPool.BLANK);
				}
				try {
					obj.put("SecondVerificationDate",
							vmaAnNinhCangBien.getSecondVerificationDate());
				} catch (Exception e) {
					obj.put("SecondVerificationDate", StringPool.BLANK);
				}
				try {
					obj.put("ThirdVerificationDate",
							vmaAnNinhCangBien.getThirdVerificationDate());
				} catch (Exception e) {
					obj.put("ThirdVerificationDate", StringPool.BLANK);
				}
				try {
					obj.put("FourthVerificationDate",
							vmaAnNinhCangBien.getFourthVerificationDate());
				} catch (Exception e) {
					obj.put("FourthVerificationDate", StringPool.BLANK);
				}
				array.put(obj);
			}
			result.put("data", array);
			result.put("total", total);

			return result;
		}
		return null;
	}

	public static JSONObject actionUpdateVmaAnNinhCangBien(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		UserPort userPort = UserPortLocalServiceUtil.findByUserId(themeDisplay
				.getUserId());
		UserSign userSign = UserSignLocalServiceUtil.getByUserId(userPort
				.getUserId());
		String maritimeCode = userPort.getPortCode();
		String certificateNo = DanhMucUtils.getString(actionRequest,
				"CertificateNo");
		String issueDate = DanhMucUtils.getString(actionRequest, "IssueDate");
		String expireDate = DanhMucUtils.getString(actionRequest, "ExpireDate");
		String issuePlace = DanhMucUtils.getString(actionRequest, "IssuePlace");
		// TODO
		// int attachFileId = "0";
		String portFacilityName = DanhMucUtils.getString(actionRequest,
				"PortFacilityName");
		String portFacilityAddress = DanhMucUtils.getString(actionRequest,
				"PortFacilityAddress");
		String securityName = DanhMucUtils.getString(actionRequest,
				"SecurityName");
		String securityContact = DanhMucUtils.getString(actionRequest,
				"SecurityContact");
		String expireStatus = DanhMucUtils.getString(actionRequest,
				"ExpireStatus");
		int firstAuthorized = ParamUtil.getInteger(actionRequest,
				"FirstAuthorized");
		String firstVerificationDate = DanhMucUtils.getString(actionRequest,
				"FirstVerificationDate");
		int secondAuthorized = ParamUtil.getInteger(actionRequest,
				"SecondAuthorized");
		String secondVerificationDate = DanhMucUtils.getString(actionRequest,
				"SecondVerificationDate");
		int thirdAuthorized = ParamUtil.getInteger(actionRequest,
				"ThirdAuthorized");
		String thirdVerificationDate = DanhMucUtils.getString(actionRequest,
				"ThirdVerificationDate");
		int fourthAuthorized = ParamUtil.getInteger(actionRequest,
				"FourthAuthorized");
		String fourthVerificationDate = DanhMucUtils.getString(actionRequest,
				"FourthVerificationDate");
		int passengerShipApproval = ParamUtil.getInteger(actionRequest,
				"PassengerShipApproval");
		int passengerHighSpeedApproval = ParamUtil.getInteger(actionRequest,
				"PassengerHighSpeedApproval");
		int cargoHighSpeedApproval = ParamUtil.getInteger(actionRequest,
				"CargoHighSpeedApproval");
		int bulkCarrierApproval = ParamUtil.getInteger(actionRequest,
				"BulkCarrierApproval");
		int oilTankerApproval = ParamUtil.getInteger(actionRequest,
				"OilTankerApproval");
		int chemicalTankerApproval = ParamUtil.getInteger(actionRequest,
				"ChemicalTankerApproval");
		int gasCarrierApproval = ParamUtil.getInteger(actionRequest,
				"GasCarrierApproval");
		int mobileOffshoreApproval = ParamUtil.getInteger(actionRequest,
				"MobileOffshoreApproval");
		int otherCargoShipApproval = ParamUtil.getInteger(actionRequest,
				"OtherCargoShipApproval");

		int status = 0;
		String message = "";

		String iscreate = DanhMucUtils.getString(actionRequest,
				PageType.LAN_CAP_MOI_DU_LIEU);
		String isedit = DanhMucUtils.getString(actionRequest,
				PageType.LAN_SUA_DU_LIEU);
		String isdelete = DanhMucUtils.getString(actionRequest,
				PageType.LAN_XOA_DU_LIEU);
		try {
			if (isdelete.length() > 0) {
				long id = ParamUtil.getLong(actionRequest, "ID");
				VmaAnNinhCangBienLocalServiceUtil.deleteVmaAnNinhCangBien(id);
				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.XOA,
						"vma_anninh_cangbien", String.valueOf(id),
						StringPool.BLANK);
				status = 1;
				message += "Xoa VmaAnNinhCangBien thanh cong";
			} else if (iscreate.length() > 0) {
				log.info("==============  Them: VmaAnNinhCangBien");
				VmaAnNinhCangBien vmaAnNinhCangBien = new VmaAnNinhCangBien();

				vmaAnNinhCangBien.setMaritimeCode(maritimeCode);
				vmaAnNinhCangBien.setCertificateNo(certificateNo);

				vmaAnNinhCangBien
						.setIssueDate((Validator.isNotNull(issueDate) && !issueDate
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(issueDate))) : null);

				vmaAnNinhCangBien
						.setExpireDate((Validator.isNotNull(expireDate) && !expireDate
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(expireDate))) : null);

				vmaAnNinhCangBien.setIssuePlace(issuePlace);
				vmaAnNinhCangBien.setPortFacilityName(portFacilityName);
				vmaAnNinhCangBien.setPortFacilityAddress(portFacilityAddress);
				vmaAnNinhCangBien.setSecurityName(securityName);
				vmaAnNinhCangBien.setSecurityContact(securityContact);
				vmaAnNinhCangBien.setExpireStatus(expireStatus);

				vmaAnNinhCangBien
						.setFirstVerificationDate((Validator
								.isNotNull(firstVerificationDate) && !firstVerificationDate
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(firstVerificationDate)))
								: null);

				vmaAnNinhCangBien
						.setSecondVerificationDate((Validator
								.isNotNull(secondVerificationDate) && !secondVerificationDate
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(secondVerificationDate)))
								: null);

				vmaAnNinhCangBien
						.setThirdVerificationDate((Validator
								.isNotNull(thirdVerificationDate) && !thirdVerificationDate
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(thirdVerificationDate)))
								: null);

				vmaAnNinhCangBien
						.setFourthVerificationDate((Validator
								.isNotNull(fourthVerificationDate) && !fourthVerificationDate
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(fourthVerificationDate)))
								: null);

				vmaAnNinhCangBien.setFirstAuthorized(firstAuthorized);
				vmaAnNinhCangBien.setSecondAuthorized(secondAuthorized);
				vmaAnNinhCangBien.setThirdAuthorized(thirdAuthorized);
				vmaAnNinhCangBien.setFourthAuthorized(fourthAuthorized);
				vmaAnNinhCangBien
						.setPassengerShipApproval(passengerShipApproval);
				vmaAnNinhCangBien
						.setPassengerHighSpeedApproval(passengerHighSpeedApproval);
				vmaAnNinhCangBien
						.setCargoHighSpeedApproval(cargoHighSpeedApproval);
				vmaAnNinhCangBien.setBulkCarrierApproval(bulkCarrierApproval);
				vmaAnNinhCangBien.setOilTankerApproval(oilTankerApproval);
				vmaAnNinhCangBien
						.setChemicalTankerApproval(chemicalTankerApproval);
				vmaAnNinhCangBien.setGasCarrierApproval(gasCarrierApproval);
				vmaAnNinhCangBien
						.setMobileOffshoreApproval(mobileOffshoreApproval);
				vmaAnNinhCangBien
						.setOtherCargoShipApproval(otherCargoShipApproval);

				VmaAnNinhCangBien vmaAnNinhCangBien2 = VmaAnNinhCangBienLocalServiceUtil
						.addVmaAnNinhCangBien(vmaAnNinhCangBien);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.THEM,
						"vma_anninh_cangbien", String
								.valueOf(vmaAnNinhCangBien2.getId()),
						StringPool.BLANK);
				status = 1;
				message += "Them VmaAnNinhCangBien thanh cong";
			} else if (isedit.length() > 0) {
				long id = ParamUtil.getLong(actionRequest, "ID");
				VmaAnNinhCangBien vmaAnNinhCangBien = VmaAnNinhCangBienLocalServiceUtil
						.getVmaAnNinhCangBien(id);

				vmaAnNinhCangBien.setMaritimeCode(maritimeCode);
				vmaAnNinhCangBien.setCertificateNo(certificateNo);

				vmaAnNinhCangBien
						.setIssueDate((Validator.isNotNull(issueDate) && !issueDate
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(issueDate))) : null);

				vmaAnNinhCangBien
						.setExpireDate((Validator.isNotNull(expireDate) && !expireDate
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(expireDate))) : null);

				vmaAnNinhCangBien.setIssuePlace(issuePlace);
				vmaAnNinhCangBien.setPortFacilityName(portFacilityName);
				vmaAnNinhCangBien.setPortFacilityAddress(portFacilityAddress);
				vmaAnNinhCangBien.setSecurityName(securityName);
				vmaAnNinhCangBien.setSecurityContact(securityContact);
				vmaAnNinhCangBien.setExpireStatus(expireStatus);

				vmaAnNinhCangBien
						.setFirstVerificationDate((Validator
								.isNotNull(firstVerificationDate) && !firstVerificationDate
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(firstVerificationDate)))
								: null);

				vmaAnNinhCangBien
						.setSecondVerificationDate((Validator
								.isNotNull(secondVerificationDate) && !secondVerificationDate
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(secondVerificationDate)))
								: null);

				vmaAnNinhCangBien
						.setThirdVerificationDate((Validator
								.isNotNull(thirdVerificationDate) && !thirdVerificationDate
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(thirdVerificationDate)))
								: null);

				vmaAnNinhCangBien
						.setFourthVerificationDate((Validator
								.isNotNull(fourthVerificationDate) && !fourthVerificationDate
								.isEmpty()) ? FormatData.formatDateShort3
								.parse(DanhMucUtils.timeStamp2Date(Long
										.valueOf(fourthVerificationDate)))
								: null);

				vmaAnNinhCangBien.setFirstAuthorized(firstAuthorized);
				vmaAnNinhCangBien.setSecondAuthorized(secondAuthorized);
				vmaAnNinhCangBien.setThirdAuthorized(thirdAuthorized);
				vmaAnNinhCangBien.setFourthAuthorized(fourthAuthorized);
				vmaAnNinhCangBien
						.setPassengerShipApproval(passengerShipApproval);
				vmaAnNinhCangBien
						.setPassengerHighSpeedApproval(passengerHighSpeedApproval);
				vmaAnNinhCangBien
						.setCargoHighSpeedApproval(cargoHighSpeedApproval);
				vmaAnNinhCangBien.setBulkCarrierApproval(bulkCarrierApproval);
				vmaAnNinhCangBien.setOilTankerApproval(oilTankerApproval);
				vmaAnNinhCangBien
						.setChemicalTankerApproval(chemicalTankerApproval);
				vmaAnNinhCangBien.setGasCarrierApproval(gasCarrierApproval);
				vmaAnNinhCangBien
						.setMobileOffshoreApproval(mobileOffshoreApproval);
				vmaAnNinhCangBien
						.setOtherCargoShipApproval(otherCargoShipApproval);

				VmaAnNinhCangBien vmaAnNinhCangBien2 = VmaAnNinhCangBienLocalServiceUtil
						.updateVmaAnNinhCangBien(vmaAnNinhCangBien);

				VmaAuditLogLocalServiceUtil.addVmaAuditLog(
						userPort.getUserId(),
						userSign != null ? userSign.getModifyuser()
								: StringPool.BLANK, LogsConstant.SUA,
						"vma_anninh_cangbien", String
								.valueOf(vmaAnNinhCangBien2.getId()),
						StringPool.BLANK);
				status = 1;
				message += "Sua VmaAnNinhCangBien thanh cong";
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

	public static JSONObject getDetailVmaAnNinhCangBiens(long id)
			throws PortalException, SystemException {
		VmaAnNinhCangBien vmaAnNinhCangBien = VmaAnNinhCangBienLocalServiceUtil
				.getVmaAnNinhCangBien(id);

		JSONObject obj = JSONFactoryUtil.createJSONObject();
		obj.put("ID", vmaAnNinhCangBien.getId());
		obj.put("MaritimeCode", vmaAnNinhCangBien.getMaritimeCode());
		obj.put("CertificateNo", vmaAnNinhCangBien.getCertificateNo());
		obj.put("IssuePlace", vmaAnNinhCangBien.getIssuePlace());
		obj.put("AttachFileId", vmaAnNinhCangBien.getAttachFileId());
		obj.put("PortFacilityName", vmaAnNinhCangBien.getPortFacilityName());
		obj.put("PortFacilityAddress",
				vmaAnNinhCangBien.getPortFacilityAddress());
		obj.put("SecurityName", vmaAnNinhCangBien.getSecurityName());
		obj.put("SecurityContact", vmaAnNinhCangBien.getSecurityContact());
		obj.put("ExpireStatus", vmaAnNinhCangBien.getExpireStatus());
		obj.put("FirstAuthorized", vmaAnNinhCangBien.getFirstAuthorized());
		obj.put("SecondAuthorized", vmaAnNinhCangBien.getSecondAuthorized());
		obj.put("ThirdAuthorized", vmaAnNinhCangBien.getThirdAuthorized());
		obj.put("FourthAuthorized", vmaAnNinhCangBien.getFourthAuthorized());
		obj.put("PassengerShipApproval",
				vmaAnNinhCangBien.getPassengerShipApproval());
		obj.put("PassengerHighSpeedApproval",
				vmaAnNinhCangBien.getPassengerHighSpeedApproval());
		obj.put("CargoHighSpeedApproval",
				vmaAnNinhCangBien.getCargoHighSpeedApproval());
		obj.put("BulkCarrierApproval",
				vmaAnNinhCangBien.getBulkCarrierApproval());
		obj.put("OilTankerApproval", vmaAnNinhCangBien.getOilTankerApproval());
		obj.put("ChemicalTankerApproval",
				vmaAnNinhCangBien.getChemicalTankerApproval());
		obj.put("GasCarrierApproval", vmaAnNinhCangBien.getGasCarrierApproval());
		obj.put("MobileOffshoreApproval",
				vmaAnNinhCangBien.getMobileOffshoreApproval());
		obj.put("OtherCargoShipApproval",
				vmaAnNinhCangBien.getOtherCargoShipApproval());
		obj.put("MarkedAsDelete", vmaAnNinhCangBien.getMarkedAsDelete());
		obj.put("ModifiedDate", vmaAnNinhCangBien.getModifiedDate());

		try {
			obj.put("IssueDate", vmaAnNinhCangBien.getIssueDate());
		} catch (Exception e) {
			obj.put("IssueDate", StringPool.BLANK);
		}
		try {
			obj.put("ExpireDate", vmaAnNinhCangBien.getExpireDate());
		} catch (Exception e) {
			obj.put("ExpireDate", StringPool.BLANK);
		}
		try {
			obj.put("FirstVerificationDate",
					vmaAnNinhCangBien.getFirstVerificationDate());
		} catch (Exception e) {
			obj.put("FirstVerificationDate", StringPool.BLANK);
		}
		try {
			obj.put("SecondVerificationDate",
					vmaAnNinhCangBien.getSecondVerificationDate());
		} catch (Exception e) {
			obj.put("SecondVerificationDate", StringPool.BLANK);
		}
		try {
			obj.put("ThirdVerificationDate",
					vmaAnNinhCangBien.getThirdVerificationDate());
		} catch (Exception e) {
			obj.put("ThirdVerificationDate", StringPool.BLANK);
		}
		try {
			obj.put("FourthVerificationDate",
					vmaAnNinhCangBien.getFourthVerificationDate());
		} catch (Exception e) {
			obj.put("FourthVerificationDate", StringPool.BLANK);
		}
		return obj;
	}

}