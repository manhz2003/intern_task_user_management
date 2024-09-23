/**
 * 
 */
package vn.gt.portlet.phuongtien;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import com.fds.nsw.liferay.core.ActionRequest;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.kernel.exception.SystemException;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;


import com.fds.nsw.nghiepvu.model.VmaScheduleTransfer;

import vn.gt.dao.noticeandmessage.service.VmaScheduleTransferLocalServiceUtil;
import vn.gt.utils.FormatData;

/**
 * @author ddung
 *
 */
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaScheduleTransferUtils
 {

	

	public static VmaScheduleTransfer getObjectFromRequest(
			VmaScheduleTransfer vmaScheduleTransfer, ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		if (vmaScheduleTransfer == null) {
			vmaScheduleTransfer = new VmaScheduleTransfer();
		}
		int mtgateway = GetterUtil.getInteger(
				request.getParameter("mtgateway"), -1);
		if (mtgateway >= 0) {
			vmaScheduleTransfer.setMtgateway(mtgateway);
		}
		String maritimeCode = ParamUtil.getString(actionRequest,
				"maritimeCode", StringPool.BLANK);
		if (Validator.isNotNull(maritimeCode)) {
			vmaScheduleTransfer.setMaritimeCode(maritimeCode);
		}
		String shipName = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(shipName)) {
			vmaScheduleTransfer.setShipName(shipName);
		}
		String shipPreviousName = VMAUtils.getString(actionRequest,
				"shipPreviousName", StringPool.BLANK);
		if (Validator.isNotNull(shipPreviousName)) {
			vmaScheduleTransfer.setShipPreviousName(shipPreviousName);
		}
		String shipTypeMT = ParamUtil.getString(actionRequest, "shipTypeMT",
				StringPool.BLANK);
		if (Validator.isNotNull(shipTypeMT)) {
			vmaScheduleTransfer.setShipTypeMT(shipTypeMT);
		}
		String shipTypeCode = ParamUtil.getString(actionRequest,
				"shipTypeCode", StringPool.BLANK);
		if (Validator.isNotNull(shipTypeCode)) {
			vmaScheduleTransfer.setShipTypeCode(shipTypeCode);
		}
		String shipBoat = ParamUtil.getString(actionRequest, "shipBoat",
				StringPool.BLANK);
		if (Validator.isNotNull(shipBoat)) {
			vmaScheduleTransfer.setShipBoat(shipBoat);
		}
		int hasTugBoat = GetterUtil.getInteger(
				request.getParameter("hasTugBoat"), -1);
		if (hasTugBoat >= 0) {
			vmaScheduleTransfer.setHasTugBoat(hasTugBoat);
		}
		String tugBoatName = VMAUtils.getString(actionRequest, "tugBoatName",
				StringPool.BLANK);
		if (Validator.isNotNull(tugBoatName)) {
			vmaScheduleTransfer.setTugBoatName(tugBoatName);
		}
		String nameOfMaster = VMAUtils.getString(actionRequest, "nameOfMaster",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfMaster)) {
			vmaScheduleTransfer.setNameOfMaster(nameOfMaster);
		}
		String chiefOfEngineer = VMAUtils.getString(actionRequest,
				"chiefOfEngineer", StringPool.BLANK);
		if (Validator.isNotNull(chiefOfEngineer)) {
			vmaScheduleTransfer.setChiefOfEngineer(chiefOfEngineer);
		}
		String imoNumber = ParamUtil.getString(actionRequest, "imoNumber",
				StringPool.BLANK);
		if (Validator.isNotNull(imoNumber)) {
			vmaScheduleTransfer.setImoNumber(imoNumber);
		}
		String callSign = VMAUtils.getString(actionRequest, "callSign",
				StringPool.BLANK);
		if (Validator.isNotNull(callSign)) {
			vmaScheduleTransfer.setCallSign(callSign);
		}
		String flagStateOfShip = ParamUtil.getString(actionRequest,
				"flagStateOfShip", StringPool.BLANK);
		if (Validator.isNotNull(flagStateOfShip)) {
			vmaScheduleTransfer.setFlagStateOfShip(flagStateOfShip);
		}
		String vrCode = ParamUtil.getString(actionRequest, "vrCode",
				StringPool.BLANK);
		if (Validator.isNotNull(vrCode)) {
			vmaScheduleTransfer.setVrCode(vrCode);
		}
		String registryNumber = VMAUtils.getString(actionRequest,
				"registryNumber", StringPool.BLANK);
		if (Validator.isNotNull(registryNumber)) {
			vmaScheduleTransfer.setRegistryNumber(registryNumber);
		}
		String registryDate = ParamUtil.getString(actionRequest,
				"registryDate", StringPool.BLANK);
		if (Validator.isNotNull(registryDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(registryDate);
				vmaScheduleTransfer.setRegistryDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		String registryPortCode = ParamUtil.getString(actionRequest,
				"registryPortCode", StringPool.BLANK);
		if (Validator.isNotNull(registryPortCode)) {
			vmaScheduleTransfer.setRegistryPortCode(registryPortCode);
		}
		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);
		if (gt >= 0) {
			vmaScheduleTransfer.setGt(BigDecimal.valueOf(gt));
		}
		double nt = GetterUtil.getDouble(request.getParameter("nt"), -1);
		if (nt >= 0) {
			vmaScheduleTransfer.setNt(BigDecimal.valueOf(nt));
		}
		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);
		if (dwt >= 0) {
			vmaScheduleTransfer.setDwt(BigDecimal.valueOf(dwt));
		}
		double loa = GetterUtil.getDouble(request.getParameter("loa"), -1);
		if (loa >= 0) {
			vmaScheduleTransfer.setLoa(loa);
		}
		double breadth = GetterUtil.getDouble(request.getParameter("breadth"),
				-1);
		if (breadth >= 0) {
			vmaScheduleTransfer.setBreadth(breadth);
		}
		double clearanceHeight = GetterUtil.getDouble(
				request.getParameter("clearanceHeight"), -1);
		if (clearanceHeight >= 0) {
			vmaScheduleTransfer.setClearanceHeight(clearanceHeight);
		}
		double power = GetterUtil.getDouble(request.getParameter("power"), -1);
		if (power >= 0) {
			vmaScheduleTransfer.setPower(power);
		}
		double maxDraft = GetterUtil.getDouble(
				request.getParameter("maxDraft"), -1);
		if (maxDraft >= 0) {
			vmaScheduleTransfer.setMaxDraft(maxDraft);
		}
		double shownDraftxF = GetterUtil.getDouble(
				request.getParameter("shownDraftxF"), -1);
		if (shownDraftxF >= 0) {
			vmaScheduleTransfer.setShownDraftxF(shownDraftxF);
		}
		double shownDraftxA = GetterUtil.getDouble(
				request.getParameter("shownDraftxA"), -1);
		if (shownDraftxA >= 0) {
			vmaScheduleTransfer.setShownDraftxA(shownDraftxA);
		}
		String unitPower = ParamUtil.getString(actionRequest, "unitPower",
				StringPool.BLANK);
		if (Validator.isNotNull(unitPower)) {
			vmaScheduleTransfer.setUnitPower(unitPower);
		}
		String productionCountry = VMAUtils.getString(actionRequest,
				"productionCountry", StringPool.BLANK);
		if (Validator.isNotNull(productionCountry)) {
			vmaScheduleTransfer.setProductionCountry(productionCountry);
		}
		String productionYear = ParamUtil.getString(actionRequest,
				"productionYear", StringPool.BLANK);
		if (Validator.isNotNull(productionYear)) {
			vmaScheduleTransfer.setProductionYear(productionYear);
		}
		String shipOwnerCode = ParamUtil.getString(actionRequest,
				"shipOwnerCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerCode)) {
			vmaScheduleTransfer.setShipOwnerCode(shipOwnerCode);
		}
		String shipOperatorCode = ParamUtil.getString(actionRequest,
				"shipOperatorCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorCode)) {
			vmaScheduleTransfer.setShipOperatorCode(shipOperatorCode);
		}
		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyCode)) {
			vmaScheduleTransfer.setShipAgencyCode(shipAgencyCode);
		}
		String remarks = VMAUtils.getString(actionRequest, "remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(remarks)) {
			vmaScheduleTransfer.setRemarks(remarks);
		}
		String certificateOfMaster = VMAUtils.getString(actionRequest,
				"certificateOfMaster", StringPool.BLANK);
		if (Validator.isNotNull(certificateOfMaster)) {
			vmaScheduleTransfer.setCertificateOfMaster(certificateOfMaster);
		}
		String certificateChiefOfEngineer = VMAUtils.getString(actionRequest,
				"certificateChiefOfEngineer", StringPool.BLANK);
		if (Validator.isNotNull(certificateChiefOfEngineer)) {
			vmaScheduleTransfer
					.setCertificateChiefOfEngineer(certificateChiefOfEngineer);
		}
		String securityLevelCode = ParamUtil.getString(actionRequest,
				"securityLevelCode", StringPool.BLANK);
		if (Validator.isNotNull(securityLevelCode)) {
			vmaScheduleTransfer.setSecurityLevelCode(securityLevelCode);
		}
		int violated = GetterUtil.getInteger(request.getParameter("violated"),
				-1);
		if (violated >= 0) {
			vmaScheduleTransfer.setViolated(violated);
		}
		int seat = GetterUtil.getInteger(request.getParameter("seat"), -1);
		if (seat >= 0) {
			vmaScheduleTransfer.setSeat(seat);
		}
		int lies = GetterUtil.getInteger(request.getParameter("lies"), -1);
		if (lies >= 0) {
			vmaScheduleTransfer.setLies(lies);
		}
		int deconstructed = GetterUtil.getInteger(
				request.getParameter("deconstructed"), -1);
		if (deconstructed >= 0) {
			vmaScheduleTransfer.setDeconstructed(deconstructed);
		}
		String constructionShipyardCode = ParamUtil.getString(actionRequest,
				"constructionShipyardCode", StringPool.BLANK);
		if (Validator.isNotNull(constructionShipyardCode)) {
			vmaScheduleTransfer
					.setConstructionShipyardCode(constructionShipyardCode);
		}
		String deconstructionShipyardCode = ParamUtil.getString(actionRequest,
				"deconstructionShipyardCode", StringPool.BLANK);
		if (Validator.isNotNull(deconstructionShipyardCode)) {
			vmaScheduleTransfer
					.setDeconstructionShipyardCode(deconstructionShipyardCode);
		}
		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);
		if (Validator.isNotNull(itineraryNo)) {
			vmaScheduleTransfer.setItineraryNo(itineraryNo);
		}
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		if (noticeShipType > 0) {
			vmaScheduleTransfer.setNoticeShipType(noticeShipType);
		}

		return vmaScheduleTransfer;
	}

	public static VmaScheduleTransfer updateVmaScheduleTransfer(
			VmaScheduleTransfer vmaScheduleTransfer) throws SystemException {
		long id = vmaScheduleTransfer.getId();
		if (id <= 0) {
			return VmaScheduleTransferLocalServiceUtil
					.addVmaScheduleTransfer(vmaScheduleTransfer);
		} else {
			return VmaScheduleTransferLocalServiceUtil
					.updateVmaScheduleTransfer(vmaScheduleTransfer);
		}
	}
}
