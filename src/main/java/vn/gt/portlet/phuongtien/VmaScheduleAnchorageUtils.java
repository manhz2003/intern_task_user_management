package vn.gt.portlet.phuongtien;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.fds.nsw.nghiepvu.model.DmArrivalPurpose;
import com.fds.nsw.nghiepvu.model.DmPort;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import vn.gt.dao.danhmuc.service.DmArrivalPurposeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.VmaItinerary;
import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;
import com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage;



import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryScheduleLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleAnchorageLocalServiceUtil;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;


import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaScheduleAnchorageUtils
 {
	

	// Can check lại ham nay ve mat nghiep vu
	public static double getAnchoragePaymentDuration(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);
		int makePayment = ParamUtil.getInteger(request, "makePayment");
		int anchorageSupplement = ParamUtil.getInteger(request,
				"anchorageSupplement");
		double duration = 0;
		if (makePayment == 2) {
			try {
				List<VmaScheduleAnchorage> vmaScheduleAnchorages = VmaScheduleAnchorageLocalServiceUtil
						.findByItineraryNo_MakePayment(itineraryNo, 0);

				for (VmaScheduleAnchorage vmaScheduleAnchorage : vmaScheduleAnchorages) {
					DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
							.getByPortWharfCode(vmaScheduleAnchorage
									.getAnchoringPortWharfCode());
					if (dmPortWharf != null) {
						if (dmPortWharf.getPortWharfPayment() != 1
								&& dmPortWharf.getPortWharfType() != 1) {
							duration += vmaScheduleAnchorage
									.getAnchorageFreeDuration();
						}
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} else if (makePayment == 1 || makePayment == 0) {
			try {
				List<VmaScheduleAnchorage> vmaScheduleAnchorages = VmaScheduleAnchorageLocalServiceUtil
						.findByitineraryNo_makePayment_anchorageSupplement(
								itineraryNo, makePayment, anchorageSupplement);

				for (VmaScheduleAnchorage vmaScheduleAnchorage : vmaScheduleAnchorages) {
					DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
							.getByPortWharfCode(vmaScheduleAnchorage
									.getAnchoringPortWharfCode());
					if (dmPortWharf != null) {
						if (dmPortWharf.getPortWharfPayment() == 1
								&& dmPortWharf.getPortWharfType() == 1) {
							double _tmp = vmaScheduleAnchorage
									.getAnchoringDuration()
									- vmaScheduleAnchorage
											.getAnchorageFreeDuration();
							duration += _tmp > 0 ? _tmp : 0;
						}
					}

				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

		return duration;
	}

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		long vmaScheduleAnchorageId = ParamUtil.getLong(request,
				"vmaScheduleAnchorageId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaScheduleAnchorage vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
					.getVmaScheduleAnchorage(vmaScheduleAnchorageId);
			result = VMAUtils.object2Json(vmaScheduleAnchorage,
					VmaScheduleAnchorage.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/*
	 * id itineraryNo sequenceNo portofAuthority nameOfShip purposeCode
	 * purposeSpecified anchoringDateFrom anchoringDateTo anchoringDuration
	 * anchoringPortRegionCode anchoringPortHarbourCode anchoringPortWharfCode
	 * noticeShipType portRegionCode portHarbourCode portWharfCode shipOwnerCode
	 * shipOperatorCode shipAgencyCode makePayment modifiedDate
	 */
	public static VmaScheduleAnchorage getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;

		long id = GetterUtil.getLong(
				request.getParameter("vmaScheduleAnchorageId"), -1);
		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);
		
		VmaScheduleAnchorage vmaScheduleAnchorage = null;
		if (id > 0) {
			try {
				vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
						.getVmaScheduleAnchorage(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else if (itineraryScheduleId > 0) { 
			vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil.fetchByitineraryScheduleId(itineraryScheduleId);
		} else {
			vmaScheduleAnchorage = new VmaScheduleAnchorage();
		}

		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);

		if (Validator.isNotNull(itineraryNo)) {
			vmaScheduleAnchorage.setItineraryNo(itineraryNo);
		}
		if (itineraryScheduleId >= 0) {
			vmaScheduleAnchorage.setItineraryScheduleId(itineraryScheduleId);
		}
		// tu tang
		/*
		 * int sequenceNo = GetterUtil.getInteger(
		 * request.getParameter("sequenceNo"), -1); if (sequenceNo >= 0) {
		 * vmaScheduleAnchorage.setSequenceNo(sequenceNo); }
		 */

		String portofAuthority = ParamUtil.getString(actionRequest,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaScheduleAnchorage.setPortofAuthority(portofAuthority);
		}
		String nameOfShip = VMAUtils.getString(actionRequest, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaScheduleAnchorage.setNameOfShip(nameOfShip);
		}
		String purposeCode = VMAUtils.getString(actionRequest, "purposeCode",
				StringPool.BLANK);
		if (Validator.isNotNull(purposeCode)) {
			vmaScheduleAnchorage.setPurposeCode(purposeCode);
		}
		String purposeSpecified = VMAUtils.getString(actionRequest,
				"purposeSpecified", StringPool.BLANK);
		if (Validator.isNotNull(purposeSpecified)) {
			vmaScheduleAnchorage.setPurposeSpecified(purposeSpecified);
		}
		String anchorageFreeDurationRemarks = VMAUtils.getString(actionRequest, "anchorageFreeDurationRemarks",
				StringPool.BLANK);
		if (Validator.isNotNull(anchorageFreeDurationRemarks)) {
			vmaScheduleAnchorage.setAnchorageFreeDurationRemarks(anchorageFreeDurationRemarks);
		}
		String anchoringDateFrom = ParamUtil.getString(actionRequest,
				"anchoringDateFrom", StringPool.BLANK);
		try {
			Date date = FormatData.formatDateShort3.parse(anchoringDateFrom);
			vmaScheduleAnchorage.setAnchoringDateFrom(date);
		} catch (ParseException e) {
			//Edit by Dungnv
			vmaScheduleAnchorage.setAnchoringDateFrom(null);
		}
		String anchoringDateTo = ParamUtil.getString(actionRequest,
				"anchoringDateTo", StringPool.BLANK);
		try {
			Date date = FormatData.formatDateShort3.parse(anchoringDateTo);
			vmaScheduleAnchorage.setAnchoringDateTo(date);
		} catch (ParseException e) {
			//Edit by Dungnv
			vmaScheduleAnchorage.setAnchoringDateTo(null);
		}
		
				
		String anchoringPortRegionCode = ParamUtil.getString(actionRequest,
				"anchoringPortRegionCode", StringPool.BLANK);
		if (Validator.isNotNull(anchoringPortRegionCode)) {
			vmaScheduleAnchorage
					.setAnchoringPortRegionCode(anchoringPortRegionCode);
		}
		String anchoringPortHarbourCode = ParamUtil.getString(actionRequest,
				"anchoringPortHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(anchoringPortHarbourCode)) {
			vmaScheduleAnchorage
					.setAnchoringPortHarbourCode(anchoringPortHarbourCode);
		}
		String anchoringPortWharfCode = ParamUtil.getString(actionRequest,
				"anchoringPortWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(anchoringPortWharfCode)) {
			vmaScheduleAnchorage
					.setAnchoringPortWharfCode(anchoringPortWharfCode);
		}
		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		if (noticeShipType >= 0) {
			vmaScheduleAnchorage.setNoticeShipType(noticeShipType);
		}
		String portRegionCode = ParamUtil.getString(actionRequest,
				"portRegionCode", StringPool.BLANK);
		if (Validator.isNotNull(portRegionCode)) {
			vmaScheduleAnchorage.setPortRegionCode(portRegionCode);
		}
		String portHarbourCode = ParamUtil.getString(actionRequest,
				"portHarbourCode", StringPool.BLANK);
		if (Validator.isNotNull(portHarbourCode)) {
			vmaScheduleAnchorage.setPortHarbourCode(portHarbourCode);
		}
		String portWharfCode = ParamUtil.getString(actionRequest,
				"portWharfCode", StringPool.BLANK);
		if (Validator.isNotNull(portWharfCode)) {
			vmaScheduleAnchorage.setPortWharfCode(portWharfCode);
		}
		String shipOwnerCode = ParamUtil.getString(actionRequest,
				"shipOwnerCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerCode)) {
			vmaScheduleAnchorage.setShipOwnerCode(shipOwnerCode);
		}
		String shipOperatorCode = ParamUtil.getString(actionRequest,
				"shipOperatorCode", StringPool.BLANK);
		if (Validator.isNotNull(shipOperatorCode)) {
			vmaScheduleAnchorage.setShipOperatorCode(shipOperatorCode);
		}
		String shipAgencyCode = ParamUtil.getString(actionRequest,
				"shipAgencyCode", StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyCode)) {
			vmaScheduleAnchorage.setShipAgencyCode(shipAgencyCode);
		}		

		String securityLevelCode = ParamUtil.getString(actionRequest,
				"securityLevelCode", StringPool.BLANK);
		if (Validator.isNotNull(securityLevelCode)) {
			vmaScheduleAnchorage.setSecurityLevelCode(securityLevelCode);
		}
		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);
		if (makePayment >= 0) {
			vmaScheduleAnchorage.setMakePayment(makePayment);
		}
		

		long anchorageSupplement = GetterUtil.getLong(
				request.getParameter("anchorageSupplement"), -1);
		if (anchorageSupplement >= 0) {
			vmaScheduleAnchorage.setAnchorageSupplement((int) anchorageSupplement);
		}
		
		String documentaryCode = ParamUtil.getString(request,
				"documentaryCode", StringPool.BLANK);
		if (Validator.isNotNull(documentaryCode)) {
			vmaScheduleAnchorage.setDocumentaryCode(documentaryCode);
		}
		
		double anchorageFreeDuration = GetterUtil.getDouble(
				request.getParameter("anchorageFreeDuration"), -1);
		double anchorageDomesticDuration = GetterUtil.getDouble(
				request.getParameter("anchorageDomesticDuration"), -1);
		double anchorageForeignDuration = GetterUtil.getDouble(
				request.getParameter("anchorageForeignDuration"), -1);
		
		double anchoringDuration = VMAUtils
				.calculateAnchoringDuration(
						vmaScheduleAnchorage
								.getAnchoringDateFrom(),
						vmaScheduleAnchorage
								.getAnchoringDateTo());
		vmaScheduleAnchorage
				.setAnchoringDuration(anchoringDuration);
		
				
		 
		if ((anchorageFreeDuration > 0) && (anchorageFreeDuration <= anchoringDuration)) {
			vmaScheduleAnchorage
					.setAnchorageFreeDuration(anchorageFreeDuration);
			vmaScheduleAnchorage
					.setAnchorageForeignDuration((double)0);
			vmaScheduleAnchorage
					.setAnchorageDomesticDuration((double)0);
						
		} else {
			vmaScheduleAnchorage
					.setAnchorageFreeDuration((double)0);
			vmaScheduleAnchorage
					.setAnchorageForeignDuration((double)0);
			vmaScheduleAnchorage
					.setAnchorageDomesticDuration((double)0);
		}
		
		DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
				.getByPortWharfCode(anchoringPortWharfCode);
		if (dmPortWharf != null
				&& dmPortWharf.getPortWharfPayment() == 1) {
			// object.put("Verify_PAYMENT", "Cảng vụ tính phí");
			if ((vmaScheduleAnchorage.getAnchorageFreeDuration() == 0) || ((vmaScheduleAnchorage.getAnchorageDomesticDuration() == 0) && (vmaScheduleAnchorage.getAnchorageForeignDuration() == 0) )){
				vmaScheduleAnchorage
				.setAnchorageDomesticDuration(anchoringDuration - vmaScheduleAnchorage.getAnchorageFreeDuration());
				vmaScheduleAnchorage.setAnchorageForeignDuration((double)0);
			
			
				try {
					VmaItinerary	vmaItinerary = VmaItineraryLocalServiceUtil
							.fetchByitineraryNo(vmaScheduleAnchorage.getItineraryNo());
					
					VmaItinerarySchedule vmaItineraryScheduleIn = VmaItineraryScheduleLocalServiceUtil
							.findByItineraryNo_NoticeShipType(
									vmaScheduleAnchorage.getItineraryNo(),
									1);
					if (Validator.isNotNull(vmaItinerary)
							&& (!(vmaItinerary.getFlagStateOfShip()
									.equalsIgnoreCase("VN")))
							&& (vmaItinerary
									.getDomesticTransportCertificate() != 1)) {
						// Tinh phi ngoai neu Tau nuoc ngoai khong co giay phep van chuyen noi dia
						vmaScheduleAnchorage
								.setAnchorageForeignDuration(anchoringDuration - vmaScheduleAnchorage.getAnchorageFreeDuration());
						vmaScheduleAnchorage
								.setAnchorageDomesticDuration((double)0);
					} else if (Validator
							.isNotNull(vmaItineraryScheduleIn)
							&& vmaItineraryScheduleIn
									.getLastPortOfCallCode()
									.length() > 0) {
						DmPort objArrivalPort = DmPortLocalServiceUtil
								.getByPortCode(vmaItineraryScheduleIn
										.getLastPortOfCallCode());
						if ( ((Validator.isNotNull(objArrivalPort))
								&& objArrivalPort.getStateCode()
										.length() > 0
								&& (!(objArrivalPort.getStateCode()
										.equalsIgnoreCase("VN")))) 
										|| ((!(vmaItineraryScheduleIn.getLastPortOfCallCode().substring(0, 2).equalsIgnoreCase("VN"))) && Validator.isNull(objArrivalPort)) ){
							// Tinh phi ngoai neu Cang roi cuoi cung
							// la cang quoc te
							vmaScheduleAnchorage
									.setAnchorageForeignDuration(anchoringDuration - vmaScheduleAnchorage.getAnchorageFreeDuration());
							vmaScheduleAnchorage
									.setAnchorageDomesticDuration((double)0);
						}
					}
					
					if (Validator.isNotNull(vmaScheduleAnchorage.getPurposeCode()) && 
							(vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C17") 
									|| vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C18")
									|| vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C19")
									|| vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C20")
									|| vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C30")
									|| vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C31") )) {
						// Các trường hợp quy định Không phải nộp phí neo đậu cho tàu thuyền
						if ((anchorageFreeDuration > 0) && (anchorageFreeDuration <= anchoringDuration)) {
							vmaScheduleAnchorage
									.setAnchorageFreeDuration(anchorageFreeDuration);																	
						} else {
							vmaScheduleAnchorage.setAnchorageFreeDuration(anchoringDuration);
							vmaScheduleAnchorage.setAnchorageDomesticDuration((double)0);
							vmaScheduleAnchorage.setAnchorageForeignDuration((double)0);
						}						
						
					}
				} catch (Exception e) {

				}
			} 
		} else {
			// object.put("Verify_PAYMENT", "Không tính phí");
			vmaScheduleAnchorage
					.setAnchorageFreeDuration(anchoringDuration);
			vmaScheduleAnchorage.setAnchorageDomesticDuration((double)0);
			vmaScheduleAnchorage.setAnchorageForeignDuration((double)0);
			
		}
		
		
		return vmaScheduleAnchorage;
	}

	@Deprecated
	public static JSONObject addVmaScheduleAnchorage(ThemeDisplay themeDisplay,
			ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleAnchorage vmaScheduleAnchorage = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleAnchorage == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
					.addVmaScheduleAnchorage(vmaScheduleAnchorage);
			result = VMAUtils.object2Json(vmaScheduleAnchorage,
					VmaScheduleAnchorage.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	@Deprecated
	public static JSONObject updateVmaScheduleAnchorage(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaScheduleAnchorage vmaScheduleAnchorage = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaScheduleAnchorage == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
					.updateVmaScheduleAnchorage(vmaScheduleAnchorage);
			result = VMAUtils.object2Json(vmaScheduleAnchorage,
					VmaScheduleAnchorage.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaScheduleAnchorage_VmaItinerary(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaScheduleAnchorage vmaScheduleAnchorage = getObjectFromRequest(
				themeDisplay, actionRequest);

		VmaItinerary vmaItinerary = null;

		try {
			vmaItinerary = VmaItineraryLocalServiceUtil
					.fetchByitineraryNo(vmaScheduleAnchorage.getItineraryNo());
		} catch (SystemException e) {
			log.error(e.getMessage());
		}

		if (vmaScheduleAnchorage == null || vmaItinerary == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		
		try {
			if (Validator.isNotNull(vmaScheduleAnchorage)) {
				if ((Validator.isNotNull(vmaScheduleAnchorage.getAnchoringDateTo())) && (Validator.isNotNull(vmaScheduleAnchorage.getAnchoringDateFrom())) ) {
					long diff = vmaScheduleAnchorage.getAnchoringDateTo().getTime() - vmaScheduleAnchorage.getAnchoringDateFrom().getTime();
					if (diff < 0) {
						return VMAUtils.createResponseMessage(
								LanguageUtil.get(themeDisplay.getLocale(), "Failed: Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc."),	
								"system_error", "Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc.");
					}
				}
				
				VmaScheduleAnchorage vmaScheduleAnchorageOriginal = VmaScheduleAnchorageLocalServiceUtil.fetchByitineraryScheduleId(vmaScheduleAnchorage.getItineraryScheduleId());
				
				// Canh bao: Tinh phi hay khong
				if ( (Validator.isNotNull(vmaScheduleAnchorageOriginal)) && (vmaScheduleAnchorageOriginal.getMakePayment() == 1) && (vmaScheduleAnchorageOriginal.getAnchoringDuration() > 0)) {
					// object.put("Verify_MAKEPAYMENT", "Đã ghi phiếu thu");
					return VMAUtils.createResponseMessage(
							LanguageUtil.get(themeDisplay.getLocale(), "Failed: Giờ neo đã ghi vào biên lai thu phí, không chỉnh sửa."),	
							"system_error", "Giờ neo đã ghi vào biên lai thu phí, không chỉnh sửa.");
				} else {
					// object.put("Verify_MAKEPAYMENT",
					// "Chưa ghi phiếu thu");
					double anchoringDuration = VMAUtils
							.calculateAnchoringDuration(
									vmaScheduleAnchorage
											.getAnchoringDateFrom(),
									vmaScheduleAnchorage
											.getAnchoringDateTo());
					if (vmaScheduleAnchorage.getAnchorageFreeDuration() > 0 && vmaScheduleAnchorage.getAnchorageFreeDuration() > anchoringDuration) {
						return VMAUtils.createResponseMessage(
								LanguageUtil.get(themeDisplay.getLocale(), "Failed: Số giờ neo không tính phí phải nhỏ hơn số giờ neo."),	
								"system_error", "Số giờ neo không tính phí phải nhỏ hơn số giờ neo.");
					} else if (vmaScheduleAnchorage.getAnchorageForeignDuration() > 0 && vmaScheduleAnchorage.getAnchorageForeignDuration() > anchoringDuration) {
						return VMAUtils.createResponseMessage(
								LanguageUtil.get(themeDisplay.getLocale(), "Failed: Số giờ neo tính phí ngoại phải nhỏ hơn số giờ neo."),	
								"system_error", "Số giờ neo tính phí ngoại phải nhỏ hơn số giờ neo.");
					} else if (vmaScheduleAnchorage.getAnchorageDomesticDuration() > 0 && vmaScheduleAnchorage.getAnchorageDomesticDuration() > anchoringDuration) {
						return VMAUtils.createResponseMessage(
								LanguageUtil.get(themeDisplay.getLocale(), "Failed: Số giờ neo tính phí nội phải nhỏ hơn số giờ neo."),	
								"system_error", "Số giờ neo tính phí nội phải nhỏ hơn số giờ neo.");
					} else if (vmaScheduleAnchorage.getAnchorageFreeDuration() < 0) {
						return VMAUtils.createResponseMessage(
								LanguageUtil.get(themeDisplay.getLocale(), "Failed: Số giờ neo không tính phí không hợp lệ (số âm)."),	
								"system_error", "Số giờ neo không tính phí không hợp lệ (số âm).");
					} else if (vmaScheduleAnchorage.getAnchorageForeignDuration() < 0) {
						return VMAUtils.createResponseMessage(
								LanguageUtil.get(themeDisplay.getLocale(), "Failed: Số giờ neo tính phí ngoại không hợp lệ (số âm)."),	
								"system_error", "Số giờ neo tính phí ngoại không hợp lệ (số âm).");
					} else if (vmaScheduleAnchorage.getAnchorageDomesticDuration() < 0) {
						return VMAUtils.createResponseMessage(
								LanguageUtil.get(themeDisplay.getLocale(), "Failed: Số giờ neo tính phí nội không hợp lệ (số âm)."),	
								"system_error", "Số giờ neo tính phí nội không hợp lệ (số âm).");
					}
					
					vmaScheduleAnchorage.setMakePayment(0);
					vmaScheduleAnchorage.setDocumentaryCode(null);
					
					
					try {
						vmaItinerary = VmaItineraryUtils.getObjectFromRequest(actionRequest,
								vmaItinerary);
						result = VmaScheduleAnchorageLocalServiceUtil
								.updateVmaScheduleAnchorage_VmaItinerary(
										vmaScheduleAnchorage, vmaItinerary);

					} catch (Exception e) {
						log.error(e.getMessage());
						return VMAUtils.createResponseMessage(
								LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
								"system_error", StringPool.BLANK);
					}
				}
				
			}
		} catch (Exception e) {
			try {
			result = VMAUtils.object2Json(vmaItinerary,
					VmaItinerary.class);

			JSONObject tmp = VMAUtils.object2Json(vmaScheduleAnchorage,
					VmaScheduleAnchorage.class);

			result = VMAUtils.mergeJSON(result, tmp,
					VmaScheduleAnchorage.class);

			String anchoringDateTo = FormatData
					.parseDateToTringType3(vmaScheduleAnchorage
							.getAnchoringDateTo());
	
			result.remove("anchoringDateTo");
			result.put("anchoringDateTo", anchoringDateTo);

			
			String anchoringDateFrom = FormatData
					.parseDateToTringType3(vmaScheduleAnchorage
							.getAnchoringDateFrom());
			result.remove("anchoringDateFrom");				
			result.put("anchoringDateFrom", anchoringDateFrom);
				
				
			} catch (Exception e1) {
				// nothing to do
			}
		}

		
		

		
		return result;
	}

	public static JSONObject deleteVmaScheduleAnchorage(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {

		long id = ParamUtil.getLong(actionRequest, "vmaScheduleAnchorageId");

		if (id > 0) {
			try {
				VmaScheduleAnchorage curScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
						.getVmaScheduleAnchorage(id);
				if (curScheduleAnchorage.getMakePayment() == 0) {
					VmaScheduleAnchorageLocalServiceUtil
							.deleteVmaScheduleAnchorage(curScheduleAnchorage);
					return VMAUtils.createResponseMessage(LanguageUtil.get(
							themeDisplay.getLocale(), "success"), "",
							StringPool.BLANK);
				} else
					return VMAUtils.createResponseMessage(LanguageUtil.get(
							themeDisplay.getLocale(), "Failed"), "MakePayment",
							"Make payment not 0");
			} catch (Exception e) {
				return VMAUtils.createResponseMessage(LanguageUtil.get(
						themeDisplay.getLocale(), "system_error"),
						"system_error", StringPool.BLANK);
			}
		} else {
			return VMAUtils.createResponseMessage(LanguageUtil.get(
					themeDisplay.getLocale(), "incorrect_identifier"),
					"incorrect_identifier", StringPool.BLANK);
		}
	}

	public static JSONObject doFind(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		int end = GetterUtil.getInteger(request.getParameter("end"), 0);
		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);
		String anchoringPortRegionCode = ParamUtil.getString(resourceRequest,
				"anchoringPortRegionCode", StringPool.BLANK);
		String anchoringPortHarbourCode = ParamUtil.getString(resourceRequest,
				"anchoringPortHarbourCode", StringPool.BLANK);
		String anchoringPortWharfCode = ParamUtil.getString(resourceRequest,
				"anchoringPortWharfCode", StringPool.BLANK);

		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String purposeCode = ParamUtil.getString(resourceRequest,
				"purposeCode", StringPool.BLANK);

		String anchoringDateFrom = ParamUtil.getString(resourceRequest,
				"anchoringDateFrom", StringPool.BLANK);
		String anchoringDateTo = ParamUtil.getString(resourceRequest,
				"anchoringDateTo", StringPool.BLANK);

		String shipOwnerCode = ParamUtil.getString(resourceRequest,
				"shipOwnerCode", StringPool.BLANK);
		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		String shipAgencyCode = ParamUtil.getString(resourceRequest,
				"shipAgencyCode", StringPool.BLANK);

		int makePayment = GetterUtil.getInteger(
				request.getParameter("makePayment"), -1);

		int anchorageSupplement = GetterUtil.getInteger(
				request.getParameter("anchorageSupplement"), -1);

		long itineraryScheduleId = GetterUtil.getLong(
				request.getParameter("itineraryScheduleId"), -1);

		String documentaryCode = ParamUtil.getString(request,
				"documentaryCode", StringPool.BLANK);

		int shipPosition = GetterUtil.getInteger(
				request.getParameter("shipPosition"), -1);

		long documentName = GetterUtil.getLong(
				request.getParameter("documentName"), -1);

		long documentYear = GetterUtil.getLong(
				request.getParameter("documentYear"), -1);

		int markedAsDeparture = GetterUtil.getInteger(
				request.getParameter("markedAsDeparture"), -1);

		String flag = ParamUtil.getString(resourceRequest, "flag",
				StringPool.BLANK);

		String flag2 = ParamUtil.getString(resourceRequest, "flag2",
				StringPool.BLANK); // vi tri tau tai cang

		String imoNumber = ParamUtil.getString(request, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(request, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(request, "registryNumber",
				StringPool.BLANK);
		String timeOfArrival = ParamUtil.getString(request, "timeOfArrival",
				StringPool.BLANK);

		try {
			return VmaScheduleAnchorageLocalServiceUtil
					.findVmaItinerary_VmaScheduleAnchorage(itineraryNo,
							nameOfShip, purposeCode, anchoringDateFrom,
							anchoringDateTo, anchoringPortRegionCode,
							anchoringPortHarbourCode, anchoringPortWharfCode,
							shipOwnerCode, shipOperatorCode, shipAgencyCode,
							makePayment >= 0 ? makePayment : null,
							anchorageSupplement >= 0 ? anchorageSupplement
									: null, documentaryCode,
							itineraryScheduleId >= 0 ? itineraryScheduleId
									: null, shipPosition >= 0 ? shipPosition
									: null, flag.trim(), flag2.trim(),
							imoNumber, callSign, registryNumber, timeOfArrival,
							start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("total", 0);
			result.put("data", JSONFactoryUtil.createJSONArray());
			return result;
		}
	}

	public static void doExport(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);
		String anchoringPortRegionCode = ParamUtil.getString(resourceRequest,
				"anchoringPortRegionCode", StringPool.BLANK);
		String anchoringPortHarbourCode = ParamUtil.getString(resourceRequest,
				"anchoringPortHarbourCode", StringPool.BLANK);
		String anchoringPortWharfCode = ParamUtil.getString(resourceRequest,
				"anchoringPortWharfCode", StringPool.BLANK);

		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String purposeCode = ParamUtil.getString(resourceRequest,
				"purposeCode", StringPool.BLANK);

		String anchoringDateFrom = ParamUtil.getString(resourceRequest,
				"anchoringDateFrom", StringPool.BLANK);
		String anchoringDateTo = ParamUtil.getString(resourceRequest,
				"anchoringDateTo", StringPool.BLANK);

		String shipOwnerCode = ParamUtil.getString(resourceRequest,
				"shipOwnerCode", StringPool.BLANK);
		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		String shipAgencyCode = ParamUtil.getString(resourceRequest,
				"shipAgencyCode", StringPool.BLANK);

		String flagStateOfShip = ParamUtil.getString(resourceRequest,
				"flagStateOfShip", StringPool.BLANK);

		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);

		double gt = GetterUtil.getInteger(request.getParameter("gt"), -1);
		double nt = GetterUtil.getInteger(request.getParameter("nt"), -1);
		double dwt = GetterUtil.getInteger(request.getParameter("dwt"), -1);
		double loa = GetterUtil.getInteger(request.getParameter("loa"), -1);
		double breadth = GetterUtil.getInteger(request.getParameter("breadth"),
				-1);
		double clearanceHeight = GetterUtil.getInteger(
				request.getParameter("clearanceHeight"), -1);
		double power = GetterUtil.getInteger(request.getParameter("power"), -1);
		double maxDraft = GetterUtil.getInteger(
				request.getParameter("maxDraft"), -1);

		double shownDraftxF = GetterUtil.getInteger(
				request.getParameter("shownDraftxF"), -1);
		double shownDraftxA = GetterUtil.getInteger(
				request.getParameter("shownDraftxA"), -1);

		String chanelCodeList = ParamUtil.getString(resourceRequest,
				"chanelCodeList", StringPool.BLANK);
		String shiftingDate = ParamUtil.getString(resourceRequest,
				"shiftingDate", StringPool.BLANK);

		int makePayment = GetterUtil.getInteger(
				resourceRequest.getParameter("makePayment"), -1);

		int anchorageSupplement = GetterUtil.getInteger(
				resourceRequest.getParameter("anchorageSupplement"), -1);

		long itineraryScheduleId = GetterUtil.getLong(
				resourceRequest.getParameter("itineraryScheduleId"), -1);

		String documentaryCode = ParamUtil.getString(resourceRequest,
				"documentaryCode", StringPool.BLANK);

		int shipPosition = GetterUtil.getInteger(
				request.getParameter("shipPosition"), -1);

		String flag = ParamUtil.getString(resourceRequest, "flag",
				StringPool.BLANK);

		String flag2 = ParamUtil.getString(resourceRequest, "flag2",
				StringPool.BLANK); // vi tri tau tai cang

		String imoNumber = ParamUtil.getString(request, "imoNumber",
				StringPool.BLANK);

		String registryNumber = VMAUtils.getString(request, "registryNumber",
				StringPool.BLANK);

		String timeOfArrival = ParamUtil.getString(request, "timeOfArrival",
				StringPool.BLANK);

		String[] headers = new String[] { "STT", "T\u00EAn t\u00E0u",
				"Th\u1EDDi gian b\u1EAFt \u0111\u1EA7u",
				"M\u1EE5c \u0111\u00EDch neo",
				"Th\u1EDDi gian k\u1EBFt th\u00FAc", "V\u1ECB tr\u00ED neo",
				"Gi\u1EDD neo", "Neo b\u1ED5 sung", "Phao",
				"Ho\u1EA1t \u0111\u1ED9ng n\u1ED9i th\u1EE7y",
				"H\u00E0ng tr\u00EAn t\u00E0u", "\u0110\u1EA1i l\u00FD",
				"Ch\u1EE7 t\u00E0u", "Khai th\u00E1c" };

		String[][] exportData = null;

		try {
			JSONObject objects = VmaScheduleAnchorageLocalServiceUtil
					.findVmaItinerary_VmaScheduleAnchorage(itineraryNo,
							nameOfShip, purposeCode, anchoringDateFrom,
							anchoringDateTo, anchoringPortRegionCode,
							anchoringPortHarbourCode, anchoringPortWharfCode,
							shipOwnerCode, shipOperatorCode, shipAgencyCode,
							makePayment >= 0 ? makePayment : null,
							anchorageSupplement >= 0 ? anchorageSupplement
									: null, documentaryCode,
							itineraryScheduleId >= 0 ? itineraryScheduleId
									: null, shipPosition >= 0 ? shipPosition
									: null, flag.trim(), flag2.trim(),
							imoNumber, callSign, registryNumber, timeOfArrival,
							-1, -1);

			JSONArray data = objects.getJSONArray("data");

			if (data != null && data.length() > 0) {
				exportData = new String[data.length()][headers.length];
				for (int i = 0; i < data.length(); i++) {
					JSONObject object = data.getJSONObject(i);

					int stt = i + 1;

					nameOfShip = object.getString("nameOfShip");

					flagStateOfShip = object.getString("flagStateOfShip");

					callSign = object.getString("callSign");

					gt = object.getDouble("gt");
					dwt = object.getDouble("dwt");
					loa = object.getDouble("loa");
					shownDraftxA = object.getDouble("shownDraftxA");
					shownDraftxF = object.getDouble("shownDraftxF");

					String loaihang = "";
					String vitrineodautu = "";
					String vitrineodauden = "";
					shiftingDate = object.getString("shiftingDate");
					chanelCodeList = object.getString("chanelCodeList");

					exportData[i][0] = String.valueOf(stt);
					exportData[i][1] = nameOfShip;
					exportData[i][2] = flagStateOfShip;
					exportData[i][3] = callSign;
					exportData[i][4] = String.valueOf(gt);
					exportData[i][5] = String.valueOf(dwt);
					exportData[i][6] = String.valueOf(loa);
					exportData[i][7] = shownDraftxA + "-" + shownDraftxF;
					exportData[i][8] = loaihang;
					exportData[i][9] = vitrineodautu;
					exportData[i][10] = vitrineodauden;
					exportData[i][11] = shiftingDate;
					exportData[i][12] = chanelCodeList;

				}
			}

			String sheetName = "VMA-Schedule-Anchorage-Ship";

			String fileName = sheetName + "-" + System.currentTimeMillis();

			String filePath = VMAUtils.doExportExcelFile(request,
					resourceResponse, sheetName, fileName, headers, exportData);
//todo response file in controller
//			resourceResponse
//					.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//			resourceResponse.setProperty("Content-Disposition",
//					"attachment; filename= " + fileName + ".xls");
//			resourceResponse.setCharacterEncoding("UTF-8");
//			// resourceResponse.setProperty("filename", fileName + ".xls");
//			resourceResponse.addProperty("FILE-NAME", fileName + ".xls");
//
//			resourceResponse.getPortletOutputStream().write(
//					VMAUtils.readFileToByteArray(new File(filePath)));

		} catch (Exception e) {
			log.error(e.getMessage());

		}
	}

	public static long doCount(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);
		String anchoringPortRegionCode = ParamUtil.getString(resourceRequest,
				"anchoringPortRegionCode", StringPool.BLANK);
		String anchoringPortHarbourCode = ParamUtil.getString(resourceRequest,
				"anchoringPortHarbourCode", StringPool.BLANK);
		String anchoringPortWharfCode = ParamUtil.getString(resourceRequest,
				"anchoringPortWharfCode", StringPool.BLANK);

		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String purposeCode = ParamUtil.getString(resourceRequest,
				"purposeCode", StringPool.BLANK);

		String anchoringDateFrom = ParamUtil.getString(resourceRequest,
				"anchoringDateFrom", StringPool.BLANK);
		String anchoringDateTo = ParamUtil.getString(resourceRequest,
				"anchoringDateTo", StringPool.BLANK);

		String shipOwnerCode = ParamUtil.getString(resourceRequest,
				"shipOwnerCode", StringPool.BLANK);
		String shipOperatorCode = ParamUtil.getString(resourceRequest,
				"shipOperatorCode", StringPool.BLANK);
		String shipAgencyCode = ParamUtil.getString(resourceRequest,
				"shipAgencyCode", StringPool.BLANK);

		int makePayment = GetterUtil.getInteger(
				resourceRequest.getParameter("makePayment"), -1);

		int anchorageSupplement = GetterUtil.getInteger(
				resourceRequest.getParameter("anchorageSupplement"), -1);

		long itineraryScheduleId = GetterUtil.getLong(
				resourceRequest.getParameter("itineraryScheduleId"), -1);

		String documentaryCode = ParamUtil.getString(resourceRequest,
				"documentaryCode", StringPool.BLANK);

		int shipPosition = GetterUtil.getInteger(
				request.getParameter("shipPosition"), -1);

		String flag = ParamUtil.getString(resourceRequest, "flag",
				StringPool.BLANK);
		String imoNumber = ParamUtil.getString(request, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(request, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(request, "registryNumber",
				StringPool.BLANK);

		String timeOfArrival = ParamUtil.getString(request, "timeOfArrival",
				StringPool.BLANK);
		try {
			return VmaScheduleAnchorageLocalServiceUtil
					.countVmaItinerary_VmaScheduleAnchorage(itineraryNo,
							nameOfShip, purposeCode, anchoringDateFrom,
							anchoringDateTo, anchoringPortRegionCode,
							anchoringPortHarbourCode, anchoringPortWharfCode,
							shipOwnerCode, shipOperatorCode, shipAgencyCode,
							makePayment >= 0 ? makePayment : null,
							anchorageSupplement >= 0 ? anchorageSupplement
									: null, documentaryCode,
							itineraryScheduleId >= 0 ? itineraryScheduleId
									: null, shipPosition >= 0 ? shipPosition
									: null, flag.trim(), imoNumber, callSign,
							registryNumber, timeOfArrival);
		} catch (Exception e) {
			log.error(e.getMessage());

			return 0;
		}
	}

	public static JSONObject getVmaScheduleAnchorageDuration(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws SystemException {
		HttpServletRequest request = resourceRequest;

		String itineraryNo = ParamUtil.getString(request, "itineraryNo");

		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();
		array = VmaScheduleAnchorageLocalServiceUtil
				.getVmaScheduleAnchorageDuration(itineraryNo, -1, -1);
		result.put("data", array);
		return result;
	}

	public static JSONObject updateVmaScheduleAnchoragePort(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray results = JSONFactoryUtil.createJSONArray();

		String documentaryCode = ParamUtil
				.getString(request, "documentaryCode");
		String itineraryNo = ParamUtil.getString(request, "itineraryNo");
		String makePayment = ParamUtil.getString(request, "makePayment");
		String vmaScheduleAnchorageId = ParamUtil.getString(request,
				"vmaScheduleAnchorageId");
		String[] arrayId = vmaScheduleAnchorageId.split(",");
		if (arrayId != null && arrayId.length > 0) {
			for (int i = 0; i < arrayId.length; i++) {
				VmaScheduleAnchorage vmaScheduleAnchorage = null;
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				try {
					vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
							.fetchVmaScheduleAnchorage(Long.valueOf(arrayId[i]));
					if (vmaScheduleAnchorage != null) {
						if (vmaScheduleAnchorage.getItineraryNo().equals(
								itineraryNo)) {
							vmaScheduleAnchorage
									.setDocumentaryCode(documentaryCode);
							vmaScheduleAnchorage.setMakePayment(Integer
									.valueOf(makePayment));

							vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
									.updateVmaScheduleAnchorage(vmaScheduleAnchorage);
							obj = VMAUtils.object2Json(vmaScheduleAnchorage,
									VmaScheduleAnchorage.class);

							results.put(obj);
						}
					}
				} catch (Exception e) {
					// nothing to do
				}
			}
		}
		result.put("data", results);

		return result;
	}

	public static JSONObject updateVmaScheduleAnchoragePortByDocumentaryCode_ItineraryNo(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray results = JSONFactoryUtil.createJSONArray();

		String documentaryCode = ParamUtil
				.getString(request, "documentaryCode");
		String itineraryNo = ParamUtil.getString(request, "itineraryNo");
		String makePayment = ParamUtil.getString(request, "makePayment");

		List<VmaScheduleAnchorage> vmaScheduleAnchorages = VmaScheduleAnchorageLocalServiceUtil
				.findByItineraryNo_documentaryCode(itineraryNo, documentaryCode);
		if (vmaScheduleAnchorages != null && !vmaScheduleAnchorages.isEmpty()) {
			for (VmaScheduleAnchorage vmaScheduleAnchorage : vmaScheduleAnchorages) {
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				try {
					vmaScheduleAnchorage.setMakePayment(Integer
							.valueOf(makePayment));
					vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
							.updateVmaScheduleAnchorage(vmaScheduleAnchorage);

					obj = VMAUtils.object2Json(vmaScheduleAnchorage,
							VmaScheduleAnchorage.class);

					results.put(obj);
				} catch (Exception e) {
					// nothing to do
				}
			}
		}

		result.put("data", results);
		return result;
	}
}
