package vn.gt.portlet.phuongtien;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;

import com.fds.nsw.nghiepvu.model.*;
import jakarta.servlet.http.HttpServletRequest;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipOwnerLocalServiceUtil;


import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryScheduleLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTransferLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaShipLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionConversionLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionSlipDetailsLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionSlipLocalServiceUtil;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import org.json.JSONException;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.utility.string.StringUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.WebKeys;
import com.fds.nsw.liferay.core.ThemeDisplay;


import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaTransactionSlipUtils
 {
	

	private static final int VND = 1, USD = 2;

	private String realPath = this.getClass().getProtectionDomain()
			.getCodeSource().getLocation().toString();
	private String pathFileTemp = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/report/baocao/";
	private String pathFileSub = realPath.substring(realPath.lastIndexOf(":"),
			realPath.lastIndexOf("/WEB-INF")).replaceFirst(":", "")
			+ "/export/";

	public static VmaTransactionSlip getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;

		long id = GetterUtil.getLong(
				request.getParameter("vmaTransactionSlipId"), -1);

		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);

		String documentaryCode = ParamUtil.getString(request,
				"documentaryCode", StringPool.BLANK);

		VmaTransactionSlip vmaTransactionSlip = null;

		if (id > 0) {
			try {
				vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
						.getVmaTransactionSlip(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}			
		} else {
			vmaTransactionSlip = new VmaTransactionSlip();
		}

		/*
		 * int sequenceNo = GetterUtil.getInteger(
		 * request.getParameter("sequenceNo"), -1);
		 */

		if (Validator.isNotNull(itineraryNo)) {
			vmaTransactionSlip.setItineraryNo(itineraryNo);
		}

		String portofAuthority = ParamUtil.getString(request,
				"portofAuthority", StringPool.BLANK);
		if (Validator.isNotNull(portofAuthority)) {
			vmaTransactionSlip.setPortofAuthority(portofAuthority);
		}
		String nameOfShip = VMAUtils.getString(request, "nameOfShip",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfShip)) {
			vmaTransactionSlip.setNameOfShip(nameOfShip);
		}

		int noticeShipType = GetterUtil.getInteger(
				request.getParameter("noticeShipType"), -1);
		if (noticeShipType >= 0) {
			vmaTransactionSlip.setNoticeShipType(noticeShipType);
		}

		if (Validator.isNotNull(documentaryCode)) {
			vmaTransactionSlip.setDocumentaryCode(documentaryCode);
		}
		String documentaryKind = VMAUtils.getString(request, "documentaryKind",
				StringPool.BLANK);
		if (Validator.isNotNull(documentaryKind)) {
			vmaTransactionSlip.setDocumentaryKind(documentaryKind);
		}
		String documentaryNo = ParamUtil.getString(request, "documentaryNo",
				StringPool.BLANK);
		if (Validator.isNotNull(documentaryNo)) {
			vmaTransactionSlip.setDocumentaryNo(documentaryNo);
		}

		String documentaryIssued = ParamUtil.getString(request,
				"documentaryIssued", StringPool.BLANK);
		if (Validator.isNotNull(documentaryIssued)) {
			try {
				Date date = FormatData.formatDateShort3
						.parse(documentaryIssued);
				vmaTransactionSlip.setDocumentaryIssued(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}		

		String supplementDocumentary = VMAUtils.getString(request,
				"supplementDocumentary", StringPool.BLANK);
		if (Validator.isNotNull(supplementDocumentary)) {
			vmaTransactionSlip.setSupplementDocumentary(supplementDocumentary);
		}
		
		String fundTransferNo = VMAUtils.getString(request, "fundTransferNo",
				StringPool.BLANK);
		if (Validator.isNotNull(fundTransferNo)) {
			vmaTransactionSlip.setFundTransferNo(fundTransferNo);
		}
		
		String fundTransferDetails = VMAUtils.getString(request, "fundTransferDetails",
				StringPool.BLANK);
		if (Validator.isNotNull(fundTransferDetails)) {
			vmaTransactionSlip.setFundTransferDetails(fundTransferDetails);
		}
		
		String receiptNo = VMAUtils.getString(request, "receiptNo",
				StringPool.BLANK);
		if (Validator.isNotNull(receiptNo)) {
			vmaTransactionSlip.setReceiptNo(receiptNo);
		}

		String receiptDetails = VMAUtils.getString(request, "receiptDetails",
				StringPool.BLANK);
		if (Validator.isNotNull(receiptDetails)) {
			vmaTransactionSlip.setReceiptDetails(receiptDetails);
		}
		
		String receiptRemark = VMAUtils.getString(request, "receiptRemark",
				StringPool.BLANK);
		if (Validator.isNotNull(receiptRemark)) {
			vmaTransactionSlip.setReceiptRemark(receiptRemark);
		}
		
		String receiptBookNo = VMAUtils.getString(request, "receiptBookNo",
				StringPool.BLANK);
		if (Validator.isNotNull(receiptBookNo)) {
			vmaTransactionSlip.setReceiptBookNo(receiptBookNo);
		}
		
		String eReceiptNo = VMAUtils.getString(request, "eReceiptNo",
				StringPool.BLANK);
		if (Validator.isNotNull(eReceiptNo)) {
			vmaTransactionSlip.setEReceiptNo(eReceiptNo);
		}
		
		String eReceiptDetails = VMAUtils.getString(request, "eReceiptDetails",
				StringPool.BLANK);
		if (Validator.isNotNull(eReceiptDetails)) {
			vmaTransactionSlip.setEReceiptDetails(eReceiptDetails);
		}
		
		String emailRecipients = VMAUtils.getString(request, "emailRecipients",
				StringPool.BLANK);
		if (Validator.isNotNull(emailRecipients)) {
			vmaTransactionSlip.setEmailRecipients(emailRecipients);
		}
		
		String portClearanceCertificateNo = VMAUtils.getString(request, "portClearanceCertificateNo",
				StringPool.BLANK);
		if (Validator.isNotNull(portClearanceCertificateNo)) {
			vmaTransactionSlip.setPortClearanceCertificateNo(portClearanceCertificateNo);
		}
		
		/*
		if (Validator.isNotNull(documentaryIssued)) {

			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(documentaryIssued);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 00:00:00'";
				Date date = FormatData.formatDateShort
						.parse(strDate);
				vmaTransactionSlip.setDocumentaryIssued(date);
			}
		}
*/
		String approvalDate = ParamUtil.getString(request,
				"approvalDate", StringPool.BLANK);
		if (Validator.isNotNull(approvalDate)) {

			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(approvalDate);
				vmaTransactionSlip.setApprovalDate(date);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		String fundTransferDate = ParamUtil.getString(request,
				"fundTransferDate", StringPool.BLANK);
		if (Validator.isNotNull(fundTransferDate)) {

			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(fundTransferDate);
				vmaTransactionSlip.setFundTransferDate(date);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		String receiptDate = ParamUtil.getString(request,
				"receiptDate", StringPool.BLANK);
		if (Validator.isNotNull(receiptDate)) {

			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(receiptDate);
				vmaTransactionSlip.setReceiptDate(date);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		String eReceiptDate = ParamUtil.getString(request,
				"eReceiptDate", StringPool.BLANK);
		if (Validator.isNotNull(eReceiptDate)) {

			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(eReceiptDate);
				vmaTransactionSlip.setEReceiptDate(date);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		String dateOfStatement = ParamUtil.getString(request,
				"dateOfStatement", StringPool.BLANK);
		if (Validator.isNotNull(dateOfStatement)) {

			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(dateOfStatement);
				vmaTransactionSlip.setDateOfStatement(date);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		String statementValidUntil = ParamUtil.getString(request,
				"statementValidUntil", StringPool.BLANK);
		if (Validator.isNotNull(statementValidUntil)) {

			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(statementValidUntil);
				vmaTransactionSlip.setStatementValidUntil(date);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		

		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);
		if (gt >= 0) {
			vmaTransactionSlip.setGt(BigDecimal.valueOf(gt));
		}

		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);
		if (dwt >= 0) {
			vmaTransactionSlip.setDwt(BigDecimal.valueOf(dwt));
		}
		
		String signTitle = VMAUtils.getString(request, "signTitle",
				StringPool.BLANK);
		if (Validator.isNotNull(signTitle)) {
			vmaTransactionSlip.setSignTitle(signTitle);
		}

		String signName = VMAUtils.getString(request, "signName",
				StringPool.BLANK);
		if (Validator.isNotNull(signName)) {
			vmaTransactionSlip.setSignName(signName);
		}

		String arrivalPortName = VMAUtils.getString(request, "arrivalPortName",
				StringPool.BLANK);
		if (Validator.isNotNull(arrivalPortName)) {
			vmaTransactionSlip.setArrivalPortName(arrivalPortName);
		}

		String flagStateOfShip = VMAUtils.getString(request, "flagStateOfShip",
				StringPool.BLANK);

		if (Validator.isNotNull(flagStateOfShip)) {
			vmaTransactionSlip.setFlagStateOfShip(flagStateOfShip);
		}
		String imoNumber = ParamUtil.getString(request, "imoNumber",
				StringPool.BLANK);
		if (Validator.isNotNull(imoNumber)) {
			vmaTransactionSlip.setImoNumber(imoNumber);
		}

		String callSign = VMAUtils.getString(request, "callSign",
				StringPool.BLANK);
		if (Validator.isNotNull(callSign)) {
			vmaTransactionSlip.setCallSign(callSign);
		}
		
		String registryNumber = VMAUtils.getString(request, "registryNumber", 
				StringPool.BLANK);
		if (Validator.isNotNull(registryNumber)) {
			vmaTransactionSlip.setRegistryNumber(registryNumber);
		}

		String lastPortName = VMAUtils.getString(request, "lastPortName",
				StringPool.BLANK);
		if (Validator.isNotNull(lastPortName)) {
			vmaTransactionSlip.setLastPortName(lastPortName);
		}

		String nextPortName = VMAUtils.getString(request, "nextPortName",
				StringPool.BLANK);
		if (Validator.isNotNull(nextPortName)) {
			vmaTransactionSlip.setNextPortName(nextPortName);
		}

		String dischargedPorts = VMAUtils.getString(request, "dischargedPorts",
				StringPool.BLANK);
		if (Validator.isNotNull(dischargedPorts)) {
			vmaTransactionSlip.setDischargedPorts(dischargedPorts);
		}

		String cargoDescription = VMAUtils.getString(request,
				"cargoDescription", StringPool.BLANK);
		if (Validator.isNotNull(cargoDescription)) {
			vmaTransactionSlip.setCargoDescription(cargoDescription);
		}

		String arrivalDate = ParamUtil.getString(request, "arrivalDate",
				StringPool.BLANK);
		if (Validator.isNotNull(arrivalDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(arrivalDate);
				vmaTransactionSlip.setArrivalDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		

		String departureDate = ParamUtil.getString(request, "departureDate",
				StringPool.BLANK);
		if (Validator.isNotNull(departureDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(departureDate);
				vmaTransactionSlip.setDepartureDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}

		String shipAgencyCode = ParamUtil.getString(request, "shipAgencyCode",
				StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyCode)) {
			vmaTransactionSlip.setShipAgencyCode(shipAgencyCode);
		}

		String shipAgencyName = VMAUtils.getString(request, "shipAgencyName",
				StringPool.BLANK);
		if (Validator.isNotNull(shipAgencyName)) {
			vmaTransactionSlip.setShipAgencyName(shipAgencyName);
		}
		
		String shipOwnerCode = VMAUtils.getString(request, "shipOwnerCode",
				StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerCode)) {
			vmaTransactionSlip.setShipOwnerCode(shipOwnerCode);
		}
		
		String shipOwnerName = VMAUtils.getString(request, "shipOwnerName",
				StringPool.BLANK);
		if (Validator.isNotNull(shipOwnerName)) {
			vmaTransactionSlip.setShipOwnerName(shipOwnerName);
		}
		String shipOperationName = ParamUtil.getString(actionRequest,
				"shipOperationName", StringPool.BLANK);
		if (Validator.isNotNull(shipOperationName)) {
			vmaTransactionSlip.setShipOperationName(shipOperationName);
		}
		String doCharge = ParamUtil.getString(actionRequest,
				"doCharge", StringPool.BLANK);
		if (Validator.isNotNull(doCharge)) {
			vmaTransactionSlip.setDoCharge(doCharge);
		}
		
		int paymentType = GetterUtil.getInteger(
				request.getParameter("paymentType"), -1);
		if (paymentType >= 0) {
			vmaTransactionSlip.setPaymentType(paymentType);
		}
		int makePayment2Arrival = GetterUtil.getInteger(
				request.getParameter("makePayment2Arrival"), -1);
		if (paymentType >= 0) {
			vmaTransactionSlip.setMakePayment2Arrival(makePayment2Arrival);
		}
		int makePayment2Departure = GetterUtil.getInteger(
				request.getParameter("makePayment2Departure"), -1);
		if (makePayment2Departure >= 0) {
			vmaTransactionSlip.setMakePayment2Departure(makePayment2Departure);
		}
		int paymentStatus = GetterUtil.getInteger(
				request.getParameter("paymentStatus"), -1);
		if (paymentStatus >= 0) {
			vmaTransactionSlip.setPaymentStatus(paymentStatus);
		}
		
		int hideExchangeRate = GetterUtil.getInteger(
				request.getParameter("hideExchangeRate"), -1);
		if (hideExchangeRate >= 0) {
			vmaTransactionSlip.setHideExchangeRate(hideExchangeRate);
		} else {
			vmaTransactionSlip.setHideExchangeRate(0);
		}
		int hideShipOwnerShipAgency = GetterUtil.getInteger(
				request.getParameter("hideShipOwnerShipAgency"), -1);
		if (hideShipOwnerShipAgency >= 0) {
			vmaTransactionSlip.setHideShipOwnerShipAgency(hideShipOwnerShipAgency);
		} else {
			vmaTransactionSlip.setHideShipOwnerShipAgency(0);
		}
		
		int printedConvertVND = GetterUtil.getInteger(
				request.getParameter("printedConvertVND"), -1);
		if (printedConvertVND >= 0) {
			vmaTransactionSlip.setPrintedConvertVND(printedConvertVND);
		} else {
			vmaTransactionSlip.setPrintedConvertVND(0);
		}
		
		double tax = GetterUtil.getDouble(request.getParameter("tax"), -1);
		if (tax >= 0) {
			vmaTransactionSlip.setTax(tax);
		}
		double exchangeRate = GetterUtil.getDouble(
				request.getParameter("exchangeRate"), -1);
		if (exchangeRate >= 0) {
			vmaTransactionSlip.setExchangeRate(exchangeRate);
		} else {
			vmaTransactionSlip.setExchangeRate((double) 0);
		}
		String currencyExgDate = ParamUtil.getString(request,
				"currencyExgDate", StringPool.BLANK);
		if (Validator.isNotNull(currencyExgDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(currencyExgDate);
				vmaTransactionSlip.setCurrencyExgDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}
		double paymentAmount = GetterUtil.getDouble(
				request.getParameter("paymentAmount"), -1);
		if (paymentAmount >= 0) {
			vmaTransactionSlip.setPaymentAmount(paymentAmount);
		}
		
		String paymentDate = ParamUtil.getString(request, "paymentDate",
				StringPool.BLANK);

		if (Validator.isNotNull(paymentDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(paymentDate);
				vmaTransactionSlip.setPaymentDate(date);
				
				double documentaryExchangeRate = GetterUtil.getDouble(
						request.getParameter("documentaryExchangeRate"), -1);
				if (documentaryExchangeRate >= 0) {
					vmaTransactionSlip.setDocumentaryExchangeRate(documentaryExchangeRate);
					vmaTransactionSlip.setDocumentaryCurrencyExgDate(date);
					vmaTransactionSlip.setExchangeRateDifferences(documentaryExchangeRate - exchangeRate);
					vmaTransactionSlip.setDifferencesExgDate(new Date());
					
					double usdTotalAmount = GetterUtil.getDouble(
							request.getParameter("usdTotalAmount"), -1);
					
					if ( (usdTotalAmount >= 0) ) {
						// Tổng tiền chênh lệch tỷ giá
						vmaTransactionSlip.setPaymentDifferences((documentaryExchangeRate - exchangeRate) * usdTotalAmount);
						// Tổng tiền ghi thu usd ra vnd (tỷ giá tại thời điểm tính phí)
						vmaTransactionSlip.setPaymentInFigures(exchangeRate * usdTotalAmount);
						// Tổng tiền thực tế phải trả (quy đổi tại thời điểm thanh toán)
						vmaTransactionSlip.setPaymentRealAmount(documentaryExchangeRate * usdTotalAmount);						
					}
				}
				
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}

		
		
		double vndTotalAmount = GetterUtil.getDouble(
				request.getParameter("vndTotalAmount"), -1);
		if (vndTotalAmount >= 0) {
			vmaTransactionSlip.setVndTotalAmount(vndTotalAmount);
		}
		double usdTotalAmount = GetterUtil.getDouble(
				request.getParameter("usdTotalAmount"), -1);
		if (usdTotalAmount >= 0) {
			vmaTransactionSlip.setUsdTotalAmount(usdTotalAmount);
			double documentaryExchangeRate = GetterUtil.getDouble(
					request.getParameter("documentaryExchangeRate"), -1);
						
		}
		String amountInWordsVnd = VMAUtils.getString(request,
				"amountInWordsVnd", StringPool.BLANK);
		if (Validator.isNotNull(amountInWordsVnd)) {
			vmaTransactionSlip.setAmountInWordsVnd(amountInWordsVnd);
		}
		String amountInWordsUsd = VMAUtils.getString(request,
				"amountInWordsUsd", StringPool.BLANK);
		if (Validator.isNotNull(amountInWordsUsd)) {
			vmaTransactionSlip.setAmountInWordsUsd(amountInWordsUsd);
		}
		String financialAccountant = VMAUtils.getString(request,
				"financialAccountant", StringPool.BLANK);
		if (Validator.isNotNull(financialAccountant)) {
			vmaTransactionSlip.setFinancialAccountant(financialAccountant);
		}
		
		boolean flagChangeSymbolReceipt = false;
		if (vmaTransactionSlip.getItineraryNo().contains("HPG")) {
			flagChangeSymbolReceipt = true;
		}
		String gtRemarks = VMAUtils.getString(request, "gtRemarks",
				StringPool.BLANK);
		if (Validator.isNotNull(gtRemarks)) {
			if (flagChangeSymbolReceipt == true) {
				gtRemarks = StringUtil.replace(gtRemarks, "*", "x");
			}
			vmaTransactionSlip.setGtRemarks(gtRemarks);
		}
		String departmentCode = ParamUtil.getString(request, "departmentCode",
				StringPool.BLANK);
		if (Validator.isNotNull(departmentCode)) {
			vmaTransactionSlip.setDepartmentCode(departmentCode);
		}
		String departmentName = VMAUtils.getString(request, "departmentName",
				StringPool.BLANK);

		if (Validator.isNotNull(departmentName)) {
			vmaTransactionSlip.setDepartmentName(departmentName);
		}
		int debitnoteid = GetterUtil.getInteger(
				request.getParameter("debitnoteid"), -1);

		if (debitnoteid >= 0) {
			vmaTransactionSlip.setDebitnoteid(debitnoteid);
		}

		String modifiedDate = ParamUtil.getString(request, "modifiedDate",
				StringPool.BLANK);
		if (Validator.isNotNull(modifiedDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(modifiedDate);
				vmaTransactionSlip.setModifiedDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}

		int paymentCategory = GetterUtil.getInteger(
				request.getParameter("paymentCategory"), -1);

		if (paymentCategory >= 0) {
			vmaTransactionSlip.setPaymentCategory(paymentCategory);
						
			
			VmaItinerary vmaItinerary = null;
			VmaItinerarySchedule itinerarySchedule1 = null;
			VmaItinerarySchedule itinerarySchedule2 = null;
			

			try {
				if (noticeShipType == 1) {
					itinerarySchedule1 = VmaItineraryScheduleLocalServiceUtil
							.findByItineraryNo_NoticeShipType(itineraryNo,
									noticeShipType);
				} else if (noticeShipType == 2) {
					itinerarySchedule2 = VmaItineraryScheduleLocalServiceUtil
							.findByItineraryNo_NoticeShipType(itineraryNo,
									noticeShipType);
				} else if (noticeShipType == 3) {
					itinerarySchedule1 = VmaItineraryScheduleLocalServiceUtil
							.findByItineraryNo_NoticeShipType(itineraryNo, 1);
					itinerarySchedule2 = VmaItineraryScheduleLocalServiceUtil
							.findByItineraryNo_NoticeShipType(itineraryNo, 2);
				}
			} catch (Exception e) {
								
			}
			try {
				vmaItinerary = VmaItineraryLocalServiceUtil
						.fetchByitineraryNo(itineraryNo);
				if (Validator.isNull(shipOperationName)) {
					if (paymentCategory == 1){
						// tinh 2 luot vao,roi
						shipOperationName = (Validator.isNotNull(vmaItinerary) ? vmaItinerary.getShipOperatorName() : "");
					} else if (paymentCategory == 2){ 
						// luot vao
						shipOperationName = (Validator.isNotNull(itinerarySchedule1) ? itinerarySchedule1.getShipOperatorName() : "");
					} else if (paymentCategory == 3){ 
						// luot roi
						shipOperationName = (Validator.isNotNull(itinerarySchedule2) ? itinerarySchedule2.getShipOperatorName() : "");
					} else {
						shipOperationName = (Validator.isNotNull(vmaItinerary) ? vmaItinerary.getShipOperatorName() : "");
						if (noticeShipType == 2) {
							shipOperationName = (Validator.isNotNull(itinerarySchedule2) ? itinerarySchedule2.getShipOperatorName() : "");
						}
					}
					
					vmaTransactionSlip.setShipOperationName(shipOperationName);
				}
			} catch (Exception e) {
				
			}
		}

		String stampSerialNo = ParamUtil.getString(request, "stampSerialNo");

		if (Validator.isNotNull(stampSerialNo)) {
			vmaTransactionSlip.setStampSerialNo(stampSerialNo);
		}

		
		int stampStatus = GetterUtil.getInteger(
				request.getParameter("stampStatus"), -1);
		if (stampStatus >= 0) {
			vmaTransactionSlip.setStampStatus(stampStatus);
		}

		
		String paymentBy = ParamUtil.getString(actionRequest, "paymentBy",
				StringPool.BLANK);
		if (Validator.isNotNull(paymentBy)) {
			vmaTransactionSlip.setPaymentBy(paymentBy);
		}
		String paymentName = VMAUtils.getString(actionRequest, "paymentName",
				StringPool.BLANK);
		if (Validator.isNotNull(paymentName)) {
			vmaTransactionSlip.setPaymentName(paymentName);
		}
		int trackChangesCargoList = GetterUtil.getInteger(
				request.getParameter("trackChangesCargoList"), -1);
		if (trackChangesCargoList >= 0) {
			vmaTransactionSlip.setTrackChangesCargoList(trackChangesCargoList);
		}
		int trachChangesAnchorage = GetterUtil.getInteger(
				request.getParameter("trachChangesAnchorage"), -1);
		if (trachChangesAnchorage >= 0) {
			vmaTransactionSlip.setTrachChangesAnchorage(trachChangesAnchorage);
		}
		int trachChangesProtest = GetterUtil.getInteger(
				request.getParameter("trachChangesProtest"), -1);
		if (trachChangesProtest >= 0) {
			vmaTransactionSlip.setTrachChangesProtest(trachChangesProtest);
		}
		// edit by Dungnv
		double f1311Vnd = GetterUtil.getDouble(
				request.getParameter("f1311Vnd"), -1);
		if (f1311Vnd >= 0) {
			vmaTransactionSlip.setF1311Vnd(f1311Vnd);
		}
		double f1312Usd = GetterUtil.getDouble(
				request.getParameter("f1312Usd"), -1);
		if (f1312Usd >= 0) {
			vmaTransactionSlip.setF1312Usd(f1312Usd);
			//1319- Phí trọng tải tàu thuyền (quy đổi ghi thu), tiền Việt Nam
			vmaTransactionSlip.setF1319Vnd(f1312Usd * exchangeRate);
		}
		double f1321Vnd = GetterUtil.getDouble(
				request.getParameter("f1321Vnd"), -1);
		if (f1321Vnd >= 0) {
			vmaTransactionSlip.setF1321Vnd(f1321Vnd);
		}
		double f1322Usd = GetterUtil.getDouble(
				request.getParameter("f1322Usd"), -1);
		if (f1322Usd >= 0) {
			vmaTransactionSlip.setF1322Usd(f1322Usd);
			//1329- Phí neo đậu đối với phương tiện (quy đổi ghi thu), tiền Việt Nam
			vmaTransactionSlip.setF1329Vnd(f1322Usd * exchangeRate);
		}
		double f1331Vnd = GetterUtil.getDouble(
				request.getParameter("f1331Vnd"), -1);
		if (f1331Vnd >= 0) {
			vmaTransactionSlip.setF1331Vnd(f1331Vnd);
		}
		double f1332Usd = GetterUtil.getDouble(
				request.getParameter("f1332Usd"), -1);
		if (f1332Usd >= 0) {
			vmaTransactionSlip.setF1332Usd(f1332Usd);
			//1339- Phí neo đậu đối với hàng hóa (quy đổi ghi thu), tiền Việt Nam
			vmaTransactionSlip.setF1339Vnd(f1332Usd * exchangeRate);
		}
		double f1341Vnd = GetterUtil.getDouble(
				request.getParameter("f1341Vnd"), -1);
		if (f1341Vnd >= 0) {
			vmaTransactionSlip.setF1341Vnd(f1341Vnd);
		}
		double f1342Usd = GetterUtil.getDouble(
				request.getParameter("f1342Usd"), -1);
		if (f1342Usd >= 0) {
			vmaTransactionSlip.setF1342Usd(f1342Usd);
			//1349- Phí neo đậu đối với hành khách (quy đổi ghi thu), tiền Việt Nam
			vmaTransactionSlip.setF1349Vnd(f1342Usd * exchangeRate);
		}
		double f1351Vnd = GetterUtil.getDouble(
				request.getParameter("f1351Vnd"), -1);
		if (f1351Vnd >= 0) {
			vmaTransactionSlip.setF1351Vnd(f1351Vnd);
		}
		double f1352Usd = GetterUtil.getDouble(
				request.getParameter("f1352Usd"), -1);
		if (f1352Usd >= 0) {
			vmaTransactionSlip.setF1352Usd(f1352Usd);
			//1359- Phí bảo đảm hàng hải (quy đổi ghi thu), tiền Việt Nam
			vmaTransactionSlip.setF1359Vnd(f1352Usd * exchangeRate);
		}
		double f1361Vnd = GetterUtil.getDouble(
				request.getParameter("f1361Vnd"), -1);
		if (f1361Vnd >= 0) {
			vmaTransactionSlip.setF1361Vnd(f1361Vnd);
		}
		double f1362Usd = GetterUtil.getDouble(
				request.getParameter("f1362Usd"), -1);
		if (f1362Usd >= 0) {
			vmaTransactionSlip.setF1362Usd(f1362Usd);
			//1369- Lệ phí ra vào cảng biển (quy đổi ghi thu), tiền Việt Nam
			vmaTransactionSlip.setF1369Vnd(f1362Usd * exchangeRate);
		}
		double f1371Vnd = GetterUtil.getDouble(
				request.getParameter("f1371Vnd"), -1);
		if (f1371Vnd >= 0) {
			vmaTransactionSlip.setF1371Vnd(f1371Vnd);
		}
		double f1372Usd = GetterUtil.getDouble(
				request.getParameter("f1372Usd"), -1);
		if (f1372Usd >= 0) {
			vmaTransactionSlip.setF1372Usd(f1372Usd);
			//1379- Lệ phí chứng thực (Kháng nghị hàng hải) (quy đổi ghi thu), tiền Việt Nam
			vmaTransactionSlip.setF1379Vnd(f1372Usd * exchangeRate);
		}
		double f1381Vnd = GetterUtil.getDouble(
				request.getParameter("f1381Vnd"), -1);
		if (f1381Vnd >= 0) {
			vmaTransactionSlip.setF1381Vnd(f1381Vnd);
		}
		double f1382Usd = GetterUtil.getDouble(
				request.getParameter("f1382Usd"), -1);
		if (f1382Usd >= 0) {
			vmaTransactionSlip.setF1382Usd(f1382Usd);
			//1389- Phí hoa tiêu (quy đổi ghi thu), tiền Việt Nam
			vmaTransactionSlip.setF1389Vnd(f1382Usd * exchangeRate);
		}
		double f1391Vnd = GetterUtil.getDouble(
				request.getParameter("f1391Vnd"), -1);
		if (f1391Vnd >= 0) {
			vmaTransactionSlip.setF1391Vnd(f1391Vnd);
		}
		double f1392Usd = GetterUtil.getDouble(
				request.getParameter("f1392Usd"), -1);
		if (f1392Usd >= 0) {
			vmaTransactionSlip.setF1392Usd(f1392Usd);
			//1399- Phí tàu lai (quy đổi ghi thu), tiền Việt Nam
			vmaTransactionSlip.setF1399Vnd(f1392Usd * exchangeRate);
		}
		double f1301Vnd = GetterUtil.getDouble(
				request.getParameter("f1301Vnd"), -1);
		if (f1301Vnd >= 0) {
			vmaTransactionSlip.setF1301Vnd(f1301Vnd);
		}
		double f1302Usd = GetterUtil.getDouble(
				request.getParameter("f1302Usd"), -1);
		if (f1302Usd >= 0) {
			vmaTransactionSlip.setF1302Usd(f1302Usd);
			//1309- Phí khác (bổ sung) (quy đổi ghi thu), tiền Việt Nam
			vmaTransactionSlip.setF1309Vnd(f1302Usd * exchangeRate);
		}
		double f1313Vnd = GetterUtil.getDouble(
				request.getParameter("f1313Vnd"), -1);
		if (f1313Vnd >= 0) {
			vmaTransactionSlip.setF1313Vnd(f1313Vnd);
		}
		double f1363Vnd = GetterUtil.getDouble(
				request.getParameter("f1363Vnd"), -1);
		if (f1363Vnd >= 0) {
			vmaTransactionSlip.setF1363Vnd(f1363Vnd);
		}
		double f1373Vnd = GetterUtil.getDouble(
				request.getParameter("f1373Vnd"), -1);
		if (f1373Vnd >= 0) {
			vmaTransactionSlip.setF1373Vnd(f1373Vnd);
		}
		
		
		
		double f1411Vnd = GetterUtil.getDouble(
				request.getParameter("f1411Vnd"), -1);
		if (f1411Vnd >= 0) {
			vmaTransactionSlip.setF1411Vnd(f1411Vnd);
		}
		double f1412Vnd = GetterUtil.getDouble(
				request.getParameter("f1412Vnd"), -1);
		if (f1412Vnd >= 0) {
			vmaTransactionSlip.setF1412Vnd(f1412Vnd);
		}
		double f1413Vnd = GetterUtil.getDouble(
				request.getParameter("f1413Vnd"), -1);
		if (f1413Vnd >= 0) {
			vmaTransactionSlip.setF1413Vnd(f1413Vnd);
		}
		double f1421Vnd = GetterUtil.getDouble(
				request.getParameter("f1421Vnd"), -1);
		if (f1421Vnd >= 0) {
			vmaTransactionSlip.setF1421Vnd(f1421Vnd);
		}
		double f1422Vnd = GetterUtil.getDouble(
				request.getParameter("f1422Vnd"), -1);
		if (f1422Vnd >= 0) {
			vmaTransactionSlip.setF1422Vnd(f1422Vnd);
		}
		double f1423Vnd = GetterUtil.getDouble(
				request.getParameter("f1423Vnd"), -1);
		if (f1423Vnd >= 0) {
			vmaTransactionSlip.setF1423Vnd(f1423Vnd);
		}
		double f1431Vnd = GetterUtil.getDouble(
				request.getParameter("f1431Vnd"), -1);
		if (f1431Vnd >= 0) {
			vmaTransactionSlip.setF1431Vnd(f1431Vnd);
		}
		double f1432Vnd = GetterUtil.getDouble(
				request.getParameter("f1432Vnd"), -1);
		if (f1432Vnd >= 0) {
			vmaTransactionSlip.setF1432Vnd(f1432Vnd);
		}
		double totalAnchorageHours = GetterUtil.getDouble(
				request.getParameter("totalAnchorageHours"), -1);
		if (totalAnchorageHours >= 0) {
			vmaTransactionSlip.setTotalAnchorageHours(totalAnchorageHours+"");
		}
		double numberItineraryPerMonth = GetterUtil.getDouble(
				request.getParameter("numberItineraryPerMonth"), -1);
		if (numberItineraryPerMonth >= 0) {
			vmaTransactionSlip.setNumberItineraryPerMonth(numberItineraryPerMonth+"");
		}
		
		String mainPurpose = VMAUtils.getString(actionRequest, "mainPurpose",
				StringPool.BLANK);
		if (Validator.isNotNull(mainPurpose)) {
			vmaTransactionSlip.setMainPurpose(mainPurpose);
		}
		
		String nameOfPortFacility = VMAUtils.getString(actionRequest, "nameOfPortFacility",
				StringPool.BLANK);
		if (Validator.isNotNull(nameOfPortFacility)) {
			vmaTransactionSlip.setNameOfPortFacility(nameOfPortFacility);
		}
		
		String addressOfPortFacility = VMAUtils.getString(actionRequest, "addressOfPortFacility",
				StringPool.BLANK);
		if (Validator.isNotNull(addressOfPortFacility)) {
			vmaTransactionSlip.setAddressOfPortFacility(addressOfPortFacility);
		}
		
		String statementNumber = VMAUtils.getString(actionRequest, "statementNumber",
				StringPool.BLANK);
		if (Validator.isNotNull(statementNumber)) {
			vmaTransactionSlip.setStatementNumber(statementNumber);
		}
		
		String statementIssuedAt = VMAUtils.getString(actionRequest, "statementIssuedAt",
				StringPool.BLANK);
		if (Validator.isNotNull(statementIssuedAt)) {
			vmaTransactionSlip.setStatementIssuedAt(statementIssuedAt);
		}
		
		String f1301Remarks = VMAUtils.getString(actionRequest, "f1301Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1301Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1301Remarks = StringUtil.replace(f1301Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1301Remarks(f1301Remarks);			
		}
		String f1302Remarks = VMAUtils.getString(actionRequest, "f1302Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1302Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1302Remarks = StringUtil.replace(f1302Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1302Remarks(f1302Remarks);			
		}
		
		String f1311Remarks = VMAUtils.getString(actionRequest, "f1311Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1311Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1311Remarks = StringUtil.replace(f1311Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1311Remarks(f1311Remarks);			
		}
		String f1312Remarks = VMAUtils.getString(actionRequest, "f1312Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1312Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1312Remarks = StringUtil.replace(f1312Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1312Remarks(f1312Remarks);			
		}
		String f1321Remarks = VMAUtils.getString(actionRequest, "f1321Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1321Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1321Remarks = StringUtil.replace(f1321Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1321Remarks(f1321Remarks);			
		}
		String f1322Remarks = VMAUtils.getString(actionRequest, "f1322Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1322Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1322Remarks = StringUtil.replace(f1322Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1322Remarks(f1322Remarks);			
		}
		String f1331Remarks = VMAUtils.getString(actionRequest, "f1331Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1331Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1331Remarks = StringUtil.replace(f1331Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1331Remarks(f1331Remarks);			
		}
		String f1332Remarks = VMAUtils.getString(actionRequest, "f1332Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1332Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1332Remarks = StringUtil.replace(f1332Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1332Remarks(f1332Remarks);			
		}
		String f1341Remarks = VMAUtils.getString(actionRequest, "f1341Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1341Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1341Remarks = StringUtil.replace(f1341Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1341Remarks(f1341Remarks);			
		}
		String f1342Remarks = VMAUtils.getString(actionRequest, "f1342Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1342Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1342Remarks = StringUtil.replace(f1342Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1342Remarks(f1342Remarks);			
		}
		String f1351Remarks = VMAUtils.getString(actionRequest, "f1351Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1351Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1351Remarks = StringUtil.replace(f1351Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1351Remarks(f1351Remarks);			
		}
		String f1352Remarks = VMAUtils.getString(actionRequest, "f1352Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1352Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1352Remarks = StringUtil.replace(f1352Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1352Remarks(f1352Remarks);			
		}
		String f1361Remarks = VMAUtils.getString(actionRequest, "f1361Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1361Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1361Remarks = StringUtil.replace(f1361Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1361Remarks(f1361Remarks);			
		}
		String f1362Remarks = VMAUtils.getString(actionRequest, "f1362Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1362Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1362Remarks = StringUtil.replace(f1362Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1362Remarks(f1362Remarks);			
		}
		String f1371Remarks = VMAUtils.getString(actionRequest, "f1371Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1371Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1371Remarks = StringUtil.replace(f1371Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1371Remarks(f1371Remarks);			
		}
		String f1372Remarks = VMAUtils.getString(actionRequest, "f1372Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1372Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1372Remarks = StringUtil.replace(f1372Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1372Remarks(f1372Remarks);			
		}
		String f1381Remarks = VMAUtils.getString(actionRequest, "f1381Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1381Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1381Remarks = StringUtil.replace(f1381Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1381Remarks(f1381Remarks);			
		}
		String f1382Remarks = VMAUtils.getString(actionRequest, "f1382Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1382Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1382Remarks = StringUtil.replace(f1382Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1382Remarks(f1382Remarks);			
		}
		String f1391Remarks = VMAUtils.getString(actionRequest, "f1391Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1391Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1391Remarks = StringUtil.replace(f1391Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1391Remarks(f1391Remarks);			
		}
		String f1392Remarks = VMAUtils.getString(actionRequest, "f1392Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1392Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1392Remarks = StringUtil.replace(f1392Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1392Remarks(f1392Remarks);			
		}
		String f1313Remarks = VMAUtils.getString(actionRequest, "f1313Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1313Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1313Remarks = StringUtil.replace(f1313Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1313Remarks(f1313Remarks);			
		}
		String f1363Remarks = VMAUtils.getString(actionRequest, "f1363Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1363Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1363Remarks = StringUtil.replace(f1363Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1363Remarks(f1363Remarks);			
		}
		String f1373Remarks = VMAUtils.getString(actionRequest, "f1373Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1373Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1373Remarks = StringUtil.replace(f1373Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1373Remarks(f1373Remarks);			
		}
		String f1411Remarks = VMAUtils.getString(actionRequest, "f1411Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1411Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1411Remarks = StringUtil.replace(f1411Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1411Remarks(f1411Remarks);			
		}
		
		String f1412Remarks = VMAUtils.getString(actionRequest, "f1412Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1412Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1412Remarks = StringUtil.replace(f1412Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1412Remarks(f1412Remarks);			
		}
		
		String f1413Remarks = VMAUtils.getString(actionRequest, "f1413Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1413Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1413Remarks = StringUtil.replace(f1413Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1413Remarks(f1413Remarks);			
		}
		
		String f1421Remarks = VMAUtils.getString(actionRequest, "f1421Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1421Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1421Remarks = StringUtil.replace(f1421Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1421Remarks(f1421Remarks);
		}
		
		String f1422Remarks = VMAUtils.getString(actionRequest, "f1422Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1422Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1422Remarks = StringUtil.replace(f1422Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1422Remarks(f1422Remarks);
		}
		
		String f1423Remarks = VMAUtils.getString(actionRequest, "f1423Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1423Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1423Remarks = StringUtil.replace(f1423Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1423Remarks(f1423Remarks);
		}
		
		String f1431Remarks = VMAUtils.getString(actionRequest, "f1431Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1431Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1431Remarks = StringUtil.replace(f1431Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1431Remarks(f1431Remarks);
		}
		
		String f1432Remarks = VMAUtils.getString(actionRequest, "f1432Remarks",
				StringPool.BLANK);
		if (Validator.isNotNull(f1432Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1432Remarks = StringUtil.replace(f1432Remarks, "*", "x");
			}
			vmaTransactionSlip.setF1432Remarks(f1432Remarks);
		}
		
		return vmaTransactionSlip;
	}

	public static JSONObject doCharge(ThemeDisplay themeDisplay,
			ActionRequest actionRequest, ActionResponse actionResponse) {

		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);

		int noticeShipType = GetterUtil.getInteger(
				actionRequest.getParameter("noticeShipType"), -1);

		VmaItinerary vmaItinerary = null;
		VmaItinerarySchedule itinerarySchedule1 = null;
		VmaItinerarySchedule itinerarySchedule2 = null;
		try {
			vmaItinerary = VmaItineraryLocalServiceUtil
					.fetchByitineraryNo(itineraryNo);

		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(LanguageUtil.get(
					themeDisplay.getLocale(), "not_found_vma_itinerary"),
					"not_found_vma_itinerary", StringPool.BLANK);
		}

		try {
			if (noticeShipType == 1) {
				itinerarySchedule1 = VmaItineraryScheduleLocalServiceUtil
						.findByItineraryNo_NoticeShipType(itineraryNo,
								noticeShipType);
			} else if (noticeShipType == 2) {
				itinerarySchedule2 = VmaItineraryScheduleLocalServiceUtil
						.findByItineraryNo_NoticeShipType(itineraryNo,
								noticeShipType);
			} else if (noticeShipType == 3) {
				itinerarySchedule1 = VmaItineraryScheduleLocalServiceUtil
						.findByItineraryNo_NoticeShipType(itineraryNo, 1);
				itinerarySchedule2 = VmaItineraryScheduleLocalServiceUtil
						.findByItineraryNo_NoticeShipType(itineraryNo, 2);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(LanguageUtil.get(
					themeDisplay.getLocale(),
					"not_found_vma_itinerary_schedule"),
					"not_found_vma_itinerary_schedule", StringPool.BLANK);
		}

		String imoNumber = vmaItinerary.getImoNumber();

		String callSign = vmaItinerary.getCallSign();
		
		//add by Dung
		String registryNumber = vmaItinerary.getRegistryNumber();
		String nameOfShip = vmaItinerary.getNameOfShip();
		String flagStateOfShip = vmaItinerary.getFlagStateOfShip();
		String vrCode = vmaItinerary.getVrCode();
		if(noticeShipType == 2){
			List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil.findByItineraryNo_NoticeShipType(itineraryNo, 2);
			VmaScheduleTransfer vmaScheduleTransfer = null;
			if(vmaScheduleTransfers != null && !vmaScheduleTransfers.isEmpty()){
				vmaScheduleTransfer = vmaScheduleTransfers.get(0);
			}
			if(vmaScheduleTransfer != null){
				imoNumber = vmaScheduleTransfer.getImoNumber();
				callSign = vmaScheduleTransfer.getCallSign();
				registryNumber = vmaScheduleTransfer.getRegistryNumber();
				nameOfShip = vmaScheduleTransfer.getShipName();
				flagStateOfShip = vmaScheduleTransfer.getFlagStateOfShip();
				vrCode = vmaScheduleTransfer.getVrCode();
			}
		}
		//================
		
		VmaShip vmaShip = null;

		try {
			if (imoNumber.trim().length() >= 7) {

				vmaShip = VmaShipLocalServiceUtil.fetchByIMONumber_CallSign(
						imoNumber, callSign);
			} else {

				vmaShip = VmaShipLocalServiceUtil.fetchByCallSign(callSign);
				// Tim lai theo registryNumber
				if (!vmaShip.getShipName().contains(nameOfShip)) {
					vmaShip = VmaShipLocalServiceUtil.fetchByRegistryNumber(registryNumber);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());

			return VMAUtils.createResponseMessage(LanguageUtil.get(
					themeDisplay.getLocale(), "not_found_vma_ship"),
					"not_found_vma_ship", StringPool.BLANK);
		}

		JSONObject transactionConversionObj = VmaTransactionConversionLocalServiceUtil
				.findTransactionConversionData(vmaShip);

		VmaTransactionSlip vmaTransactionSlip = getObjectFromRequest(
				themeDisplay, actionRequest);

		try {
			VmaTransactionSlip temp1 = null;
			VmaTransactionSlip temp2 = null;
			VmaTransactionSlip temp3 = null;

			double anchorageDurationTime = VMAUtils.getArchorageDurationTime(
					itineraryNo, noticeShipType, 1);

			switch (noticeShipType) {
			case 1:
				// Chi tinh phi luot vao
				// TODO -> check 1,3: if exist and paymentStatus = 1 -> then
				// notify:
				// da thanh toan
				// TODO -> Thieu thong tin tinh phi, tham so tinh phi dau vao ->
				// then notify: thieu thon tin tinh phi
				// TODO remove 1,3 -> add 1
				// Lay cong thuc chuyen doi dung tich, trong tai toan phan

				temp1 = VmaTransactionSlipLocalServiceUtil
						.fetchByitineraryNo_noticeShipType(itineraryNo, 1);
				temp3 = VmaTransactionSlipLocalServiceUtil
						.fetchByitineraryNo_noticeShipType(itineraryNo, 3);
				if (temp1 != null && temp1.getPaymentStatus() == 1) {
					return VMAUtils.createResponseMessage(
							LanguageUtil.get(themeDisplay.getLocale(), "paid"),
							"paid", StringPool.BLANK);
				}

				if (temp3 != null && temp3.getPaymentStatus() == 1) {
					return VMAUtils.createResponseMessage(
							LanguageUtil.get(themeDisplay.getLocale(), "paid"),
							"paid", StringPool.BLANK);
				}

				if (temp1 != null) {
					VmaTransactionSlipLocalServiceUtil
							.deleteVmaTransactionSlip(temp1);
				}

				if (temp3 != null) {
					VmaTransactionSlipLocalServiceUtil
							.deleteVmaTransactionSlip(temp3);
				}

				vmaTransactionSlip = setVmaTransactionSlipField(actionRequest,
						itineraryNo, noticeShipType, vmaTransactionSlip,
						vmaItinerary, itinerarySchedule1, itinerarySchedule2,
						vmaShip, transactionConversionObj);

				vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
						.updateVmaTransactionSlip(vmaTransactionSlip,
								vmaItinerary.getPortofAuthority(),
								transactionConversionObj
										.getDouble("conversionValue"),
								vmaTransactionSlip.getMakePayment2Arrival(),
								null, null,
								itinerarySchedule1.getPurposeCode(),
								vmaItinerary.getShipTypeCode(),
								itinerarySchedule1.getPortHarbourCode(), null,
								null, null, anchorageDurationTime);
				break;

			case 2:
				// Chi tinh phi lươt roi
				// TODO -> check 2,3: if exist and paymentStatus = 1 -> then
				// notify:
				// da thanh toan
				// TODO -> Thieu thong tin tinh phi, tham so tinh phi dau vao ->
				// then notify: thieu thon tin tinh phi
				// TODO remove 2,3 -> add 2

				temp2 = VmaTransactionSlipLocalServiceUtil
						.fetchByitineraryNo_noticeShipType(itineraryNo, 2);
				temp3 = VmaTransactionSlipLocalServiceUtil
						.fetchByitineraryNo_noticeShipType(itineraryNo, 3);
				if (temp2 != null && temp2.getPaymentStatus() == 1) {
					return VMAUtils.createResponseMessage(
							LanguageUtil.get(themeDisplay.getLocale(), "paid"),
							"paid", StringPool.BLANK);
				}

				if (temp3 != null && temp3.getPaymentStatus() == 1) {
					return VMAUtils.createResponseMessage(
							LanguageUtil.get(themeDisplay.getLocale(), "paid"),
							"paid", StringPool.BLANK);
				}

				if (temp2 != null) {
					VmaTransactionSlipLocalServiceUtil
							.deleteVmaTransactionSlip(temp2);
				}

				if (temp3 != null) {
					VmaTransactionSlipLocalServiceUtil
							.deleteVmaTransactionSlip(temp3);
				}

				vmaTransactionSlip = setVmaTransactionSlipField(actionRequest,
						itineraryNo, noticeShipType, vmaTransactionSlip,
						vmaItinerary, itinerarySchedule1, itinerarySchedule2,
						vmaShip, transactionConversionObj);

				vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
						.updateVmaTransactionSlip(vmaTransactionSlip,
								vmaItinerary.getPortofAuthority(),
								transactionConversionObj
										.getDouble("conversionValue"),
								vmaTransactionSlip.getMakePayment2Arrival(),
								null, null,
								itinerarySchedule2.getPurposeCode(),
								vmaItinerary.getShipTypeCode(),
								itinerarySchedule2.getPortHarbourCode(), null,
								null, null, anchorageDurationTime);
				break;
			case 3:
				// Tinh phi 1 lan 2 luot(vao + roi)
				// TODO -> check 1,2,3: if exist and paymentStatus = 1 -> then
				// notify: da thanh toan
				// TODO -> Thieu thong tin tinh phi, tham so tinh phi dau vao ->
				// then notify: thieu thon tin tinh phi
				// remove 1,2,3 -> add 3
				temp1 = VmaTransactionSlipLocalServiceUtil
						.fetchByitineraryNo_noticeShipType(itineraryNo, 1);
				temp2 = VmaTransactionSlipLocalServiceUtil
						.fetchByitineraryNo_noticeShipType(itineraryNo, 2);
				temp3 = VmaTransactionSlipLocalServiceUtil
						.fetchByitineraryNo_noticeShipType(itineraryNo, 3);

				if (temp1 != null && temp1.getPaymentStatus() == 1) {
					return VMAUtils.createResponseMessage(
							LanguageUtil.get(themeDisplay.getLocale(), "paid"),
							"paid", StringPool.BLANK);
				}

				if (temp2 != null && temp2.getPaymentStatus() == 1) {
					return VMAUtils.createResponseMessage(
							LanguageUtil.get(themeDisplay.getLocale(), "paid"),
							"paid", StringPool.BLANK);
				}

				if (temp3 != null && temp3.getPaymentStatus() == 1) {
					return VMAUtils.createResponseMessage(
							LanguageUtil.get(themeDisplay.getLocale(), "paid"),
							"paid", StringPool.BLANK);
				}

				if (temp1 != null) {
					VmaTransactionSlipLocalServiceUtil
							.deleteVmaTransactionSlip(temp1);
				}

				if (temp2 != null) {
					VmaTransactionSlipLocalServiceUtil
							.deleteVmaTransactionSlip(temp2);
				}

				if (temp3 != null) {
					VmaTransactionSlipLocalServiceUtil
							.deleteVmaTransactionSlip(temp3);
				}

				vmaTransactionSlip = setVmaTransactionSlipField(actionRequest,
						itineraryNo, noticeShipType, vmaTransactionSlip,
						vmaItinerary, itinerarySchedule1, itinerarySchedule2,
						vmaShip, transactionConversionObj);

				vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
						.updateVmaTransactionSlip(vmaTransactionSlip,
								vmaItinerary.getPortofAuthority(),
								transactionConversionObj
										.getDouble("conversionValue"), 1, null,
								null, itinerarySchedule2.getPurposeCode(),
								vmaItinerary.getShipTypeCode(),
								itinerarySchedule2.getPortHarbourCode(), null,
								null, null, anchorageDurationTime);

				vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
						.updateVmaTransactionSlip(vmaTransactionSlip,
								vmaItinerary.getPortofAuthority(),
								transactionConversionObj
										.getDouble("conversionValue"), 2, null,
								null, itinerarySchedule2.getPurposeCode(),
								vmaItinerary.getShipTypeCode(),
								itinerarySchedule2.getPortHarbourCode(), null,
								null, null, anchorageDurationTime);

				break;
			case 4:
				// Tinh phi hang hoa
				// TODO xoa 4 -> tinh lại
				VmaTransactionSlip temp4 = VmaTransactionSlipLocalServiceUtil
						.fetchByitineraryNo_noticeShipType(itineraryNo, 4);

				if (temp4 != null) {
					VmaTransactionSlipLocalServiceUtil
							.deleteVmaTransactionSlip(temp4);
				}
				vmaTransactionSlip = setVmaTransactionSlipField(actionRequest,
						itineraryNo, noticeShipType, vmaTransactionSlip,
						vmaItinerary, itinerarySchedule1, itinerarySchedule2,
						vmaShip, transactionConversionObj);
				break;
			case 5:
				// Tinh phi dv khac(phi dau khi, tau lai, hoa tieu)
				// TODO add
				vmaTransactionSlip = setVmaTransactionSlipField(actionRequest,
						itineraryNo, noticeShipType, vmaTransactionSlip,
						vmaItinerary, itinerarySchedule1, itinerarySchedule2,
						vmaShip, transactionConversionObj);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		try {
			return VMAUtils.object2Json(vmaTransactionSlip,
					VmaTransactionSlip.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
	}

	public static int getMakePayment(long documentName, int documentYear) {
		TempDocument tempDocument = TempDocumentLocalServiceUtil
				.findTemDocumentByDocumentNameDocumentYear(documentName,
						documentYear);
		if (tempDocument != null) {
			if (tempDocument.getDocumentTypeCode().equals("NC")
					|| tempDocument.getDocumentTypeCode().equals("XC")
					|| tempDocument.getDocumentTypeCode().equals("QC")
					|| tempDocument.getDocumentTypeCode().equals("8")
					|| tempDocument.getDocumentTypeCode().equals("9")
					|| tempDocument.getDocumentTypeCode().equals("14")
					|| tempDocument.getDocumentTypeCode().equals("15")
					|| tempDocument.getDocumentTypeCode().equals("16")
					|| tempDocument.getDocumentTypeCode().equals("17")) {
				return 2;// Hoat dong hang hai quoc te
			} else if (tempDocument.getDocumentTypeCode().equals("4")
					|| tempDocument.getDocumentTypeCode().equals("5")
					|| tempDocument.getDocumentTypeCode().equals("6")
					|| tempDocument.getDocumentTypeCode().equals("7")
					|| tempDocument.getDocumentTypeCode().equals("10")
					|| tempDocument.getDocumentTypeCode().equals("11")
					|| tempDocument.getDocumentTypeCode().equals("12")
					|| tempDocument.getDocumentTypeCode().equals("13")

			) {
				return 1;// Hoat dong hang hai noi dia
			} else {
				return 3;// ap dung tai cang, ben thuy noi dia
			}
		}
		return 0;
	}

	public static VmaTransactionSlip setVmaTransactionSlipField(
			ActionRequest actionRequest, String itineraryNo,
			int noticeShipType, VmaTransactionSlip vmaTransactionSlip,
			VmaItinerary vmaItinerary, VmaItinerarySchedule itinerarySchedule1,
			VmaItinerarySchedule itinerarySchedule2, VmaShip vmaShip,
			JSONObject transactionConversionObj) {
		vmaTransactionSlip.setItineraryNo(itineraryNo);
		// SequenceNo Tu tang
		String documentaryCode = StringPool.BLANK;
		long debitnoteid = 0;
		int documentaryKind = 2;
		String documentaryNo = ParamUtil.getString(actionRequest,
				"documentaryNo", StringPool.BLANK);
		Date documentaryIssued = new Date();
		String supplementDocumentary = ParamUtil.getString(actionRequest,
				"supplementDocumentary", StringPool.BLANK);
		String signTitle = StringPool.BLANK;
		String signName = StringPool.BLANK;
		String portofAuthority = vmaItinerary.getMaritimeCode();
		String nameOfShip = vmaItinerary.getNameOfShip();
		String flagStateOfShip = vmaItinerary.getFlagStateOfShip();
		String imoNumber = vmaItinerary.getImoNumber();
		String callSign = vmaItinerary.getCallSign();
		//add by Dungnv
		String vrCode = vmaItinerary.getVrCode();
		String registryNumber = vmaItinerary.getRegistryNumber();
		if(noticeShipType == 2){
			List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil.findByItineraryNo_NoticeShipType(itineraryNo, 2);
			VmaScheduleTransfer vmaScheduleTransfer = null;
			if(vmaScheduleTransfers != null && !vmaScheduleTransfers.isEmpty()){
				vmaScheduleTransfer = vmaScheduleTransfers.get(0);
			}
			if(vmaScheduleTransfer != null){
				imoNumber = vmaScheduleTransfer.getImoNumber();
				callSign = vmaScheduleTransfer.getCallSign();
				registryNumber = vmaScheduleTransfer.getRegistryNumber();
				nameOfShip = vmaScheduleTransfer.getShipName();
				flagStateOfShip = vmaScheduleTransfer.getFlagStateOfShip();
				vrCode = vmaScheduleTransfer.getVrCode();
			}
		}
		//================
		double gt = -1;
		double dwt = -1;

		DmPort dmPort = null;
		String arrivalPortCode = vmaItinerary.getArrivalPortCode();
		String arrivalPortName = StringPool.BLANK;
		if (Validator.isNotNull(arrivalPortCode)) {
			dmPort = DmPortLocalServiceUtil.getByPortCode(arrivalPortCode);
			arrivalPortName = dmPort != null ? dmPort.getPortName()
					: StringPool.BLANK;
		}

		String lastPortCode = vmaItinerary.getLastPortCode();
		String lastPortName = StringPool.BLANK;
		if (Validator.isNotNull(arrivalPortCode)) {
			dmPort = DmPortLocalServiceUtil.getByPortCode(lastPortCode);
			lastPortName = dmPort != null ? dmPort.getPortName()
					: StringPool.BLANK;
		}
		String nextPortCode = vmaItinerary.getNextPortCode();
		String nextPortName = StringPool.BLANK;
		if (Validator.isNotNull(arrivalPortCode)) {
			dmPort = DmPortLocalServiceUtil.getByPortCode(nextPortCode);
			nextPortName = dmPort != null ? dmPort.getPortName()
					: StringPool.BLANK;
		}

		String dischargedPorts = vmaItinerary.getDischargedPorts();
		String cargoDescription = StringPool.BLANK;
		Date arrivalDate = vmaItinerary.getTimeOfArrival();
		Date departureDate = vmaItinerary.getTimeOfDeparture();

		String shipAgencyCode = StringPool.BLANK;
		String shipAgencyName = StringPool.BLANK;
		String shipOwnerName = StringPool.BLANK;
		int paymentType = GetterUtil.getInteger(
				actionRequest.getParameter("paymentType"), 2);

		int makePayment2Arrival = 0;

		int makePayment2Departure = 0;

		int paymentStatus = 0;
		int payment2ArrivalStatus = 0;
		int payment2DepartureStatus = 0;
		// double tax -> ham sau
		// double exchangeRate -> ham sau
		String currencyExgDate = ParamUtil.getString(actionRequest,
				"currencyExgDate", StringPool.BLANK);
		// PaymentAmount -> ham sau
		String paymentDate = ParamUtil.getString(actionRequest, "paymentDate",
				StringPool.BLANK);
		// VndTotalAmount -> ham sau
		// UsdTotalAmount -> ham sau
		// AmountInWordsVnd-> ham sau
		// AmountInWordsUsd -> ham sau
		String financialAccountant = ParamUtil.getString(actionRequest,
				"financialAccountant", StringPool.BLANK);


		String gtRemarks = "("
				+ transactionConversionObj.getString("unit")
				+ " "
				+ String.valueOf(transactionConversionObj
						.getDouble("originValue"))
				+ "*"
				+ String.valueOf(transactionConversionObj.getDouble("rate"))
				+ " = "
				+ String.valueOf(transactionConversionObj
						.getDouble("conversionValue")) + ")";
		String departmentCode = StringPool.BLANK;
		String departmentName = StringPool.BLANK;

		switch (noticeShipType) {
		case 1:

			if (itinerarySchedule1 != null) {
				departmentCode = itinerarySchedule1.getDepartmentCode();
				departmentName = itinerarySchedule1.getDepartmentName();
				gt = itinerarySchedule1.getGt().doubleValue();
				dwt = itinerarySchedule1.getDwt().doubleValue();
				cargoDescription = itinerarySchedule1.getCargoType();
				shipAgencyCode = itinerarySchedule1.getShipAgencyCode();
				shipAgencyName = itinerarySchedule1.getShipAgencyName();
				shipOwnerName = itinerarySchedule1.getShipOperatorName();
				payment2ArrivalStatus = 1;
				payment2DepartureStatus = 0;
				makePayment2Arrival = getMakePayment(
						itinerarySchedule1.getDocumentName(),
						itinerarySchedule1.getDocumentYear());

				TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil
						.findByDocumentNameAnddocumentYear(
								itinerarySchedule1.getDocumentName(),
								itinerarySchedule1.getDocumentYear());

				if (tempDebitNote != null) {
					documentaryCode = tempDebitNote.getDebitnotenumber();
					debitnoteid = tempDebitNote.getId();
				}
			}
			break;

		case 2:

			if (itinerarySchedule2 != null) {

				departmentCode = itinerarySchedule2.getDepartmentCode();
				departmentName = itinerarySchedule2.getDepartmentName();
				gt = itinerarySchedule2.getGt().doubleValue();
				dwt = itinerarySchedule2.getDwt().doubleValue();
				cargoDescription = itinerarySchedule2.getCargoType();
				shipAgencyCode = itinerarySchedule2.getShipAgencyCode();
				shipAgencyName = itinerarySchedule2.getShipAgencyName();
				shipOwnerName = itinerarySchedule2.getShipOperatorName();
				payment2ArrivalStatus = 0;
				payment2DepartureStatus = 1;
				makePayment2Arrival = 0;
				makePayment2Departure = getMakePayment(
						itinerarySchedule2.getDocumentName(),
						itinerarySchedule2.getDocumentYear());

				TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil
						.findByDocumentNameAnddocumentYear(
								itinerarySchedule2.getDocumentName(),
								itinerarySchedule2.getDocumentYear());

				if (tempDebitNote != null) {
					documentaryCode = tempDebitNote.getDebitnotenumber();
					debitnoteid = tempDebitNote.getId();
				}
			}
			break;
		case 3:

			if (itinerarySchedule1 != null) {
				makePayment2Arrival = getMakePayment(
						itinerarySchedule1.getDocumentName(),
						itinerarySchedule1.getDocumentYear());
			}
			if (itinerarySchedule2 != null) {
				departmentCode = itinerarySchedule2.getDepartmentCode();
				departmentName = itinerarySchedule2.getDepartmentName();
				gt = itinerarySchedule2.getGt().doubleValue();
				dwt = itinerarySchedule2.getDwt().doubleValue();
				cargoDescription = itinerarySchedule2.getCargoType();
				shipAgencyCode = itinerarySchedule2.getShipAgencyCode();
				shipAgencyName = itinerarySchedule2.getShipAgencyName();
				shipOwnerName = itinerarySchedule2.getShipOperatorName();
				payment2ArrivalStatus = 1;
				payment2DepartureStatus = 1;

				makePayment2Departure = getMakePayment(
						itinerarySchedule2.getDocumentName(),
						itinerarySchedule2.getDocumentYear());

				TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil
						.findByDocumentNameAnddocumentYear(
								itinerarySchedule2.getDocumentName(),
								itinerarySchedule2.getDocumentYear());

				if (tempDebitNote != null) {
					documentaryCode = tempDebitNote.getDebitnotenumber();
					debitnoteid = tempDebitNote.getId();
				}
			}
			break;
		case 4:

			if (itinerarySchedule2 != null) {
				departmentCode = itinerarySchedule2.getDepartmentCode();
				departmentName = itinerarySchedule2.getDepartmentName();
				gt = itinerarySchedule2.getGt().doubleValue();
				dwt = itinerarySchedule2.getDwt().doubleValue();
				cargoDescription = itinerarySchedule2.getCargoType();
				shipAgencyCode = itinerarySchedule2.getShipAgencyCode();
				shipAgencyName = itinerarySchedule2.getShipAgencyName();
				shipOwnerName = itinerarySchedule2.getShipOperatorName();
				makePayment2Arrival = 0;
				makePayment2Departure = 0;
			}
			break;
		case 5:

			documentaryKind = 1;
			break;

		default:
			break;
		}

		vmaTransactionSlip.setPortofAuthority(portofAuthority);

		vmaTransactionSlip.setNameOfShip(nameOfShip);

		vmaTransactionSlip.setNoticeShipType(noticeShipType);

		vmaTransactionSlip.setDocumentaryCode(documentaryCode);

		vmaTransactionSlip.setDebitnoteid((int) debitnoteid);

		vmaTransactionSlip.setDocumentaryKind(String.valueOf(documentaryKind));

		vmaTransactionSlip.setDocumentaryNo(documentaryNo);

		vmaTransactionSlip.setDocumentaryIssued(documentaryIssued);

		vmaTransactionSlip.setSupplementDocumentary(supplementDocumentary);

		vmaTransactionSlip.setSignTitle(signTitle);

		vmaTransactionSlip.setGt(BigDecimal.valueOf(gt));

		vmaTransactionSlip.setDwt(BigDecimal.valueOf(dwt));

		vmaTransactionSlip.setSignName(signName);

		vmaTransactionSlip.setArrivalPortName(arrivalPortName);

		vmaTransactionSlip.setFlagStateOfShip(flagStateOfShip);

		vmaTransactionSlip.setImoNumber(imoNumber);

		vmaTransactionSlip.setCallSign(callSign);

		vmaTransactionSlip.setLastPortName(lastPortName);

		vmaTransactionSlip.setNextPortName(nextPortName);

		vmaTransactionSlip.setDischargedPorts(dischargedPorts);

		vmaTransactionSlip.setCargoDescription(cargoDescription);

		vmaTransactionSlip.setArrivalDate(arrivalDate);

		vmaTransactionSlip.setDepartureDate(departureDate);

		vmaTransactionSlip.setShipAgencyCode(shipAgencyCode);

		vmaTransactionSlip.setShipAgencyName(shipAgencyName);

		vmaTransactionSlip.setShipOwnerName(shipOwnerName);

		vmaTransactionSlip.setPaymentType(paymentType);

		vmaTransactionSlip.setMakePayment2Arrival(makePayment2Arrival);

		vmaTransactionSlip.setMakePayment2Departure(makePayment2Departure);

		vmaTransactionSlip.setPaymentStatus(paymentStatus);



		if (Validator.isNotNull(currencyExgDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(currencyExgDate);
				vmaTransactionSlip.setCurrencyExgDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}


		if (Validator.isNotNull(paymentDate)) {
			try {
				Date date = FormatData.formatDateShort3.parse(paymentDate);
				vmaTransactionSlip.setPaymentDate(date);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
		}



		vmaTransactionSlip.setFinancialAccountant(financialAccountant);
		
		boolean flagChangeSymbolReceipt = false;
		if (vmaTransactionSlip.getItineraryNo().contains("HPG")) {
			flagChangeSymbolReceipt = true;
		}
		if (Validator.isNotNull(gtRemarks)) {
			if (flagChangeSymbolReceipt == true) {
				gtRemarks = StringUtil.replace(gtRemarks, "*", "x");
			}
			vmaTransactionSlip.setGtRemarks(gtRemarks);
		}

		vmaTransactionSlip.setDepartmentCode(departmentCode);

		vmaTransactionSlip.setDepartmentName(departmentName);



		return vmaTransactionSlip;
	}

	public static JSONObject addVmaTransactionSlip_VmaTransactionSlipDetail(
			ThemeDisplay themeDisplay, ActionRequest actionRequest,
			ActionResponse actionResponse) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaTransactionSlip vmaTransactionSlip = getObjectFromRequest(
				themeDisplay, actionRequest);

		VmaTransactionSlipDetails vmaTransactionSlipDetails = VmaTransactionSlipDetailUtils
				.getObjectFromRequest(themeDisplay, actionRequest);

		if (vmaTransactionSlip == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		if (vmaTransactionSlipDetails == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found_detail", StringPool.BLANK);
		}
		try {
			VmaTransactionSlip vmaTransactionSlip2 = VmaTransactionSlipLocalServiceUtil
					.fetchByitineraryNo_documentaryCode(
							vmaTransactionSlip.getItineraryNo(),
							vmaTransactionSlip.getDocumentaryCode());
			/*VmaTransactionSlipDetails vmaTransactionSlipDetails2 = VmaTransactionSlipDetailsLocalServiceUtil
					.fetchByItineraryNo_DocumentaryCode(
							vmaTransactionSlipDetails.getItineraryNo(),
							vmaTransactionSlipDetails.getDocumentaryCode());*/

			if (Validator.isNull(vmaTransactionSlip2) )  {				
				result = VmaTransactionSlipLocalServiceUtil
						.addVmaTransactionSlip_VmaTransactionSlipDetail(
								vmaTransactionSlip, vmaTransactionSlipDetails);
			} else {
				result = VmaTransactionSlipLocalServiceUtil
						.updateVmaTransactionSlip_VmaTransactionSlipDetail(
								vmaTransactionSlip, vmaTransactionSlipDetails);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaTransactionSlip(
			ThemeDisplay themeDisplay, ActionRequest actionRequest,
			ActionResponse actionResponse) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaTransactionSlip vmaTransactionSlip = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaTransactionSlip == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
					.updateVmaTransactionSlip(vmaTransactionSlip);
			result = VMAUtils.object2Json(vmaTransactionSlip,
					VmaScheduleTugboat.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaTransactionSlip_VmaTransactionSlipDetail(
			ThemeDisplay themeDisplay, ActionRequest actionRequest,
			ActionResponse actionResponse) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaTransactionSlip vmaTransactionSlip = getObjectFromRequest(
				themeDisplay, actionRequest);

		VmaTransactionSlipDetails vmaTransactionSlipDetails = VmaTransactionSlipDetailUtils
				.getObjectFromRequest(themeDisplay, actionRequest);

		if (vmaTransactionSlip == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}

		if (vmaTransactionSlipDetails == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found_detail", StringPool.BLANK);
		}
		try {
			result = VmaTransactionSlipLocalServiceUtil
					.updateVmaTransactionSlip_VmaTransactionSlipDetail(
							vmaTransactionSlip, vmaTransactionSlipDetails);
			
			// Trường hợp xác nhận thanh toán, gọi service biên lai điện tử
			// VmaVnptServiceConfig serviceConf = VmaVnptServiceConfigLocalServiceUtil.
			
			if (vmaTransactionSlip.getPaymentStatus() == 9)  {
				String flagEReceipt = ParamUtil.getString(actionRequest, "flagEReceipt",
						StringPool.BLANK);
				if (Validator.isNotNull(flagEReceipt) && flagEReceipt.contains("---")) {
					// Do nothing
					// Cảng vụ chủ động không xuất biên lai điện tử 
				} else {
//					CallWebserviceClient.exportCustomersAndInvoicesToVNPT_Mode1
//					(vmaTransactionSlip.getItineraryNo(), vmaTransactionSlip.getDocumentaryCode(), themeDisplay.getUserId());
//
				}
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}
	
	public static JSONObject updateVmaTransactionSlip_ERecipt(
			ThemeDisplay themeDisplay, ActionRequest actionRequest,
			ActionResponse actionResponse) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaTransactionSlip vmaTransactionSlip = getObjectFromRequest(
				themeDisplay, actionRequest);

		
		if (vmaTransactionSlip == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		long paymentStatus = GetterUtil.getLong(
				actionRequest.getParameter("paymentStatus"), -1);
		
		try {
						
			// Trường hợp xác nhận thanh toán, gọi service biên lai điện tử
			// VmaVnptServiceConfig serviceConf = VmaVnptServiceConfigLocalServiceUtil.  
			if (vmaTransactionSlip.getPaymentStatus() == 9 && paymentStatus  == 9)  {
//				CallWebserviceClient.exportCustomersAndInvoicesToVNPT_Mode1
//					(vmaTransactionSlip.getItineraryNo(), vmaTransactionSlip.getDocumentaryCode(), themeDisplay.getUserId());
//
			}
			
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject findByItineraryNo_DocumentaryCode(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);
		String documentaryCode = ParamUtil.getString(resourceRequest,
				"documentaryCode", StringPool.BLANK);

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);
		try {

			JSONObject result = JSONFactoryUtil.createJSONObject();

			VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
					.fetchByitineraryNo_documentaryCode(itineraryNo,
							documentaryCode);

			VmaTransactionSlipDetails vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
					.fetchByItineraryNo_DocumentaryCode(itineraryNo,
							documentaryCode);

			if (vmaTransactionSlip == null && vmaTransactionSlipDetails == null) {
				return VMAUtils
						.createResponseMessage(LanguageUtil.get(
								themeDisplay.getLocale(), "not_found"),
								"not_found", StringPool.BLANK);
			}

			result.put(
					"vmaTransactionSlip",
					vmaTransactionSlip != null ? VMAUtils.object2Json(
							vmaTransactionSlip,
							VmaTransactionSlip.class)
							: JSONFactoryUtil.createJSONObject());

			result.put(
					"vmaTransactionSlipDetails",
					vmaTransactionSlipDetails != null ? VMAUtils.object2Json(
							vmaTransactionSlipDetails,
							VmaTransactionSlipDetails.class)
							: JSONFactoryUtil.createJSONObject());

			return result;

		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
	}

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {

		HttpServletRequest request = resourceRequest;
		long vmaTransactionSlipId = ParamUtil.getLong(request,
				"vmaTransactionSlipId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {

			VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
					.getVmaTransactionSlip(vmaTransactionSlipId);

			result = VMAUtils.object2Json(vmaTransactionSlip,
					VmaTransactionSlip.class);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONObject doFind(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		int end = GetterUtil.getInteger(request.getParameter("end"), 0);

		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);

		String documentaryNo = ParamUtil.getString(resourceRequest,
				"documentaryNo", StringPool.BLANK);
		String documentaryIssued = ParamUtil.getString(resourceRequest,
				"documentaryIssued", StringPool.BLANK);
		String documentaryCode = ParamUtil.getString(resourceRequest,
				"documentaryCode", StringPool.BLANK);

		String paymentDate = ParamUtil.getString(resourceRequest,
				"paymentDate", StringPool.BLANK);

		double vndTotalAmount = GetterUtil.getDouble(
				request.getParameter("vndTotalAmount"), -1);

		double usdTotalAmount = GetterUtil.getDouble(
				request.getParameter("usdTotalAmount"), -1);

		String amountInWordsVnd = ParamUtil.getString(resourceRequest,
				"amountInWordsVnd", StringPool.BLANK);

		String amountInWordsUsd = ParamUtil.getString(resourceRequest,
				"amountInWordsUsd", StringPool.BLANK);

		String financialAccountant = ParamUtil.getString(resourceRequest,
				"financialAccountant", StringPool.BLANK);

		String paymentStatus = ParamUtil.getString(resourceRequest,
				"paymentStatus", StringPool.BLANK);
		String currentPaymentStatus = ParamUtil.getString(resourceRequest,
				"currentPaymentStatus", StringPool.BLANK);
		String paymentCategory = ParamUtil.getString(resourceRequest,
				"paymentCategory", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);

		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String shipAgencyCode = ParamUtil.getString(resourceRequest,
				"shipAgencyCode", StringPool.BLANK);
		String shipAgencyName = ParamUtil.getString(resourceRequest,
				"shipAgencyName", StringPool.BLANK);
		String shipOwnerName = ParamUtil.getString(resourceRequest,
				"shipOwnerName", StringPool.BLANK);

		String paymentBy = ParamUtil.getString(resourceRequest, "paymentBy",
				StringPool.BLANK);

		String paymentName = VMAUtils.getString(resourceRequest, "paymentName",
				StringPool.BLANK);

		int paymentType = GetterUtil.getInteger(
				request.getParameter("paymentType"), -1);

		try {
			String searchQuery = generateQuery("search", portofAuthority,
					itineraryNo, documentaryNo, documentaryIssued, documentaryCode, paymentDate,
					vndTotalAmount >= 0 ? vndTotalAmount : null,
					usdTotalAmount >= 0 ? usdTotalAmount : null,
					amountInWordsVnd, amountInWordsUsd, financialAccountant,
					paymentStatus, currentPaymentStatus, paymentCategory, imoNumber, callSign,
					nameOfShip, shipAgencyCode, shipAgencyName, shipOwnerName,
					paymentBy, paymentName, paymentType >= 0 ? paymentType
							: null);

			String countQuery = generateQuery("count", portofAuthority,
					itineraryNo, documentaryNo, documentaryIssued, documentaryCode, paymentDate,
					vndTotalAmount >= 0 ? vndTotalAmount : null,
					usdTotalAmount >= 0 ? usdTotalAmount : null,
					amountInWordsVnd, amountInWordsUsd, financialAccountant,
					paymentStatus, currentPaymentStatus, paymentCategory, imoNumber, callSign,
					nameOfShip, shipAgencyCode, shipAgencyName, shipOwnerName,
					paymentBy, paymentName, paymentType >= 0 ? paymentType
							: null);

			return VmaTransactionSlipLocalServiceUtil.findVmaTransactionSlip(
					searchQuery, countQuery, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("total", 0);
			result.put("data", JSONFactoryUtil.createJSONArray());
			return result;
		}
	}

	public static long doCount(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;

		String portofAuthority = ParamUtil.getString(resourceRequest,
				"portofAuthority", StringPool.BLANK);

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);

		String documentaryNo = ParamUtil.getString(resourceRequest,
				"documentaryNo", StringPool.BLANK);
		String documentaryIssued = ParamUtil.getString(resourceRequest,
				"documentaryIssued", StringPool.BLANK);
		String documentaryCode = ParamUtil.getString(resourceRequest,
				"documentaryCode", StringPool.BLANK);
		String paymentDate = ParamUtil.getString(resourceRequest,
				"paymentDate", StringPool.BLANK);

		double vndTotalAmount = GetterUtil.getDouble(
				request.getParameter("vndTotalAmount"), -1);

		double usdTotalAmount = GetterUtil.getDouble(
				request.getParameter("usdTotalAmount"), -1);

		String amountInWordsVnd = ParamUtil.getString(resourceRequest,
				"amountInWordsVnd", StringPool.BLANK);

		String amountInWordsUsd = ParamUtil.getString(resourceRequest,
				"amountInWordsUsd", StringPool.BLANK);

		String financialAccountant = ParamUtil.getString(resourceRequest,
				"financialAccountant", StringPool.BLANK);

		String paymentStatus = ParamUtil.getString(resourceRequest,
				"paymentStatus", StringPool.BLANK);
		String currentPaymentStatus = ParamUtil.getString(resourceRequest,
				"currentPaymentStatus", StringPool.BLANK);
		String paymentCategory = ParamUtil.getString(resourceRequest,
				"paymentCategory", StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(resourceRequest, "callSign",
				StringPool.BLANK);

		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String shipAgencyCode = ParamUtil.getString(resourceRequest,
				"shipAgencyCode", StringPool.BLANK);
		String shipAgencyName = ParamUtil.getString(resourceRequest,
				"shipAgencyName", StringPool.BLANK);
		String shipOwnerName = ParamUtil.getString(resourceRequest,
				"shipOwnerName", StringPool.BLANK);

		String paymentBy = ParamUtil.getString(resourceRequest, "paymentBy",
				StringPool.BLANK);

		String paymentName = VMAUtils.getString(resourceRequest, "paymentName",
				StringPool.BLANK);

		int paymentType = GetterUtil.getInteger(
				request.getParameter("paymentType"), -1);

		try {
			String countQuery = generateQuery("count", portofAuthority,
					itineraryNo, documentaryNo, documentaryIssued, documentaryCode, paymentDate,
					vndTotalAmount >= 0 ? vndTotalAmount : null,
					usdTotalAmount >= 0 ? usdTotalAmount : null,
					amountInWordsVnd, amountInWordsUsd, financialAccountant,
					paymentStatus, currentPaymentStatus, paymentCategory, imoNumber, callSign,
					nameOfShip, shipAgencyCode, shipAgencyName, shipOwnerName,
					paymentBy, paymentName, paymentType >= 0 ? paymentType
							: null);

			return VmaTransactionSlipLocalServiceUtil
					.countVmaTransactionSlip(countQuery);
		} catch (Exception e) {
			log.error(e.getMessage());

			return 0;
		}
	}

	private static String generateQuery(String cmd, String portofAuthority,
			String itineraryNo, String documentaryNo, String documentaryIssued, String documentaryCode,
			String paymentDate, Double vndTotalAmount, Double usdTotalAmount,
			String amountInWordsVnd, String amountInWordsUsd,
			String financialAccountant, String paymentStatus, String currentPaymentStatus,
			String paymentCategory, String imoNumber, String callSign,
			String nameOfShip, String shipAgencyCode, String shipAgencyName,
			String shipOwnerName, String paymentBy, String paymentName,
			Integer paymentType) {

		String sql = StringPool.BLANK;

		if (cmd.equals("count")) {
			sql = "SELECT count(*) AS total FROM vma_transaction_slip as a";
		} else {
			sql = "SELECT a.* FROM vma_transaction_slip AS a";
		}

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(VMAUtils.buildSQLCondition("PortofAuthority", "'"
					+ portofAuthority + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(VMAUtils.buildSQLCondition("ItineraryNo", "'"
					+ itineraryNo + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(documentaryNo)) {
			condition.append(VMAUtils.buildSQLCondition("DocumentaryNo", "'%"
					+ documentaryNo + "%'", "AND", StringPool.LIKE));
		}
		
		if (Validator.isNotNull(documentaryNo)) {
			condition.append(VMAUtils.buildSQLCondition("documentaryCode", "'%"
					+ documentaryNo + "%'", "AND", StringPool.LIKE));
		}
		
		if (Validator.isNotNull(documentaryIssued)) {

			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(documentaryIssued);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition(
						"DocumentaryIssued", "'" + strDate + " 00:00:00'"
								+ " AND '" + strDate + " 23:59:59'", "AND",
						StringPool.BETWEEN));
			}
		}

		if (Validator.isNotNull(paymentDate)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(paymentDate);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(VMAUtils.buildSQLCondition("PaymentDate", "'"
						+ strDate + " 00:00:00'" + " AND '" + strDate
						+ " 23:59:59'", "AND", StringPool.BETWEEN));
			}
		}

		if (vndTotalAmount != null) {
			condition.append(VMAUtils.buildSQLCondition("VndTotalAmount",
					vndTotalAmount, "AND", StringPool.EQUAL));
		}

		if (usdTotalAmount != null) {
			condition.append(VMAUtils.buildSQLCondition("UsdTotalAmount",
					usdTotalAmount, "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(amountInWordsVnd)) {
			condition.append(VMAUtils.buildSQLCondition("AmountInWordsVnd",
					"'%" + amountInWordsVnd + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(amountInWordsUsd)) {
			condition.append(VMAUtils.buildSQLCondition("AmountInWordsUsd",
					"'%" + amountInWordsUsd + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(financialAccountant)) {
			condition.append(VMAUtils.buildSQLCondition("FinancialAccountant",
					"'%" + financialAccountant + "%'", "AND", StringPool.LIKE));
		}
		if (Validator.isNotNull(paymentStatus)) {
			condition.append(VMAUtils.buildSQLCondition("paymentStatus", "("
					+ paymentStatus + ")", "AND", "IN"));
		}

		if (Validator.isNotNull(paymentCategory)) {
			condition.append(VMAUtils.buildSQLCondition("paymentCategory", "("
					+ paymentCategory + ")", "AND", "IN"));
		}

		if (Validator.isNotNull(imoNumber)) {
			condition.append(VMAUtils.buildSQLCondition("imoNumber", "'%"
					+ imoNumber + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(callSign)) {
			condition.append(VMAUtils.buildSQLCondition("callSign", "'%"
					+ callSign + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(nameOfShip)) {
			condition.append(VMAUtils.buildSQLCondition("nameOfShip", "'%"
					+ nameOfShip + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(shipAgencyName)) {
			condition.append(VMAUtils.buildSQLCondition("shipAgencyName", "'%"
					+ shipAgencyName + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(shipOwnerName)) {
			condition.append(VMAUtils.buildSQLCondition("shipOwnerName", "'%"
					+ shipOwnerName + "%'", "AND", StringPool.LIKE));
		}

		if (Validator.isNotNull(shipAgencyCode)) {
			condition.append(VMAUtils.buildSQLCondition("shipAgencyCode", "'"
					+ shipAgencyCode + "'", "AND", StringPool.EQUAL));
		}

		
		if (Validator.isNotNull(currentPaymentStatus) && (!currentPaymentStatus.equals("0")) ) {			
			condition.append(VMAUtils.buildSQLCondition("paymentStatus",  "'"
					+ currentPaymentStatus + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(paymentBy)) {
			condition.append(VMAUtils.buildSQLCondition("PaymentBy", "'"
					+ paymentBy + "'", "AND", StringPool.EQUAL));
		}

		if (Validator.isNotNull(paymentName)) {
			condition.append(VMAUtils.buildSQLCondition("PaymentName", "'%"
					+ paymentName + "%'", "AND", StringPool.LIKE));
		}

		if (paymentType != null) {
			condition.append(VMAUtils.buildSQLCondition("PaymentType",
					+paymentType, "AND", StringPool.EQUAL));
		}

		if (cmd.equals("search")) {
			condition.append(" ORDER BY DocumentaryIssued DESC ");
		}
		return sql + condition.toString();
	}

//	public static String genDocumentaryCode(ResourceRequest resourceRequest,
//			ResourceResponse resourceResponse) {
//
//		HttpServletRequest request = resourceRequest;
//
//		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
//				StringPool.BLANK);
//
//		String debitnoteNumber = ParamUtil.getString(request,
//				"debitnoteNumber", StringPool.BLANK);
//		return VmaTransactionSlipLocalServiceUtil.genDocumenttaryCode(
//				itineraryNo, debitnoteNumber);
//	}

	@SuppressWarnings("unchecked")
//	public boolean export2Excel(String itineraryNo, String documentaryCode,
//			String fileName, long userId, int printing) throws SystemException,
//			ParsePropertyException, InvalidFormatException, IOException {
//		boolean result = false;
//
//		VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
//				.fetchByitineraryNo_documentaryCode(itineraryNo,
//						documentaryCode);
//
//		VmaTransactionSlipDetails vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
//				.fetchByItineraryNo_DocumentaryCode(itineraryNo,
//						documentaryCode);
//
//		String maritimeName = StringPool.BLANK;
//		String cityCode = StringPool.BLANK;
//		String maritimeNameVN = StringPool.BLANK;
//		String maritimeCode = StringPool.BLANK;
//		try {
//			UserPort userPort = UserPortLocalServiceUtil.findByUserId(userId);
//			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
//					.getByMaritimeCode(userPort.getPortCode());
//			maritimeName = dmMaritime.getMaritimeName();
//			cityCode = dmMaritime.getCityCode();
//			maritimeNameVN = dmMaritime.getMaritimeNameVN();
//			maritimeCode = dmMaritime.getMaritimeCode();
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//
//		Map mapVmaTransactionSlip = new HashMap();
//		mapVmaTransactionSlip.put("NameOfShip",
//				vmaTransactionSlip.getNameOfShip());
//		mapVmaTransactionSlip.put("StateName", vmaTransactionSlip
//				.getFlagStateOfShip() != null ? DmStateLocalServiceUtil
//				.getByStateCode(vmaTransactionSlip.getFlagStateOfShip())
//				.getStateName() : "");
//		mapVmaTransactionSlip.put("CallSign", vmaTransactionSlip.getCallSign());
//		if (vmaTransactionSlip.getShipAgencyCode() != null
//				&& !vmaTransactionSlip.getShipAgencyCode().equals(
//						StringPool.BLANK)) {
//			try {
//				mapVmaTransactionSlip.put(
//						"ShipAgencyName",
//						DmShipAgencyLocalServiceUtil.getByShipAgencyCode(
//								vmaTransactionSlip.getShipAgencyCode())
//								.getShipAgencyNameVN());
//			} catch (Exception e) {
//				mapVmaTransactionSlip.put("ShipAgencyName",
//						vmaTransactionSlip.getShipAgencyName());
//			}
//		} else {
//			mapVmaTransactionSlip.put("ShipAgencyName",
//					vmaTransactionSlip.getShipAgencyName());
//		}
//		mapVmaTransactionSlip.put("ShipOwnerName",
//				vmaTransactionSlip.getShipOwnerName());
//		// Duy nhat CVHH Hải Phòng muốn hiển thị Người khai thác ở mục Chủ tàu
//		  if  (maritimeCode.equalsIgnoreCase("19")
//				  && (!(Validator.isNull(vmaTransactionSlip.getShipOperationName())
//						  || (vmaTransactionSlip.getShipOperationName().trim().length() <= 1)
//						  || (vmaTransactionSlip.getShipOperationName().equalsIgnoreCase("0"))))
//						  ){
//			  mapVmaTransactionSlip.put("ShipOwnerName", vmaTransactionSlip.getShipOperationName());
//		  }
//		mapVmaTransactionSlip.put("GRT", vmaTransactionSlip.getGt());
//		mapVmaTransactionSlip.put("ArrivalPortName",
//				vmaTransactionSlip.getArrivalPortName());
//		mapVmaTransactionSlip.put("LastPortName",
//				vmaTransactionSlip.getLastPortName());
//		mapVmaTransactionSlip.put("NextPortName",
//				vmaTransactionSlip.getNextPortName());
//		/*mapVmaTransactionSlip.put("GtRemarks",
//				vmaTransactionSlip.getGtRemarks());*/
//		String gtRemarks = vmaTransactionSlip.getGtRemarks();
//		boolean flagChangeSymbolReceipt = false;
//		if (vmaTransactionSlip.getItineraryNo().contains("HPG")) {
//			flagChangeSymbolReceipt = true;
//		}
//		if (Validator.isNotNull(gtRemarks)) {
//			if (flagChangeSymbolReceipt == true) {
//				gtRemarks = StringUtil.replace(gtRemarks, "*", "x");
//			}
//			mapVmaTransactionSlip.put("GtRemarks",gtRemarks);
//		} else {
//			mapVmaTransactionSlip.put("GtRemarks","");
//		}
//
//		if (vmaTransactionSlipDetails.getGtConversion() == vmaTransactionSlip.getGt().doubleValue()) {
//			mapVmaTransactionSlip.put("GtConversionRemarks","");
//		} else {
//			mapVmaTransactionSlip.put("GtConversionRemarks",
//					" GTQĐ: "+ vmaTransactionSlipDetails.getGtConversion());
//		}
//
//		Locale locale = new Locale("en", "UK");
//
//		DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
//		symbols.setDecimalSeparator(',');
//		symbols.setGroupingSeparator('.');
//
//		String pattern = "#,##0.###";
//		DecimalFormat formatter = new DecimalFormat(pattern, symbols);
//		String patternVND = "#,##0";
//		DecimalFormat formatterVND = new DecimalFormat(patternVND, symbols);
//
//		if (vmaTransactionSlip.getUsdTotalAmount() > 0) {
//			mapVmaTransactionSlip.put("ExchangeRate",
//					vmaTransactionSlip.getExchangeRate());
//			if (vmaTransactionSlip.getPrintedConvertVND() == 1) {
//				mapVmaTransactionSlip.put("ExchangeRateRemarks",
//						"TG: " + formatterVND
//						.format(vmaTransactionSlip.getExchangeRate()) );
//			} else if ((vmaTransactionSlip.getHideExchangeRate() == 0) && (vmaTransactionSlip.getPrintedConvertVND() == 0)) {
//				mapVmaTransactionSlip.put("ExchangeRateRemarks",
//						"TG: " + formatterVND
//						.format(vmaTransactionSlip.getExchangeRate()) );
//			} else if ((vmaTransactionSlip.getHideExchangeRate() == 1) && (vmaTransactionSlip.getPrintedConvertVND() == 0)) {
//				// Không in tỷ giá
//				mapVmaTransactionSlip.put("ExchangeRateRemarks","");
//			} else {
//				mapVmaTransactionSlip.put("ExchangeRateRemarks","");
//			}
//
//			mapVmaTransactionSlip.put("PrintedConvertVND", vmaTransactionSlip.getPrintedConvertVND());
//		} else {
//			mapVmaTransactionSlip.put("ExchangeRateRemarks","");
//			mapVmaTransactionSlip.put("PrintedConvertVND", 0);
//		}
//
//		mapVmaTransactionSlip.put("DWT", vmaTransactionSlip.getDwt());
//		try {
//			mapVmaTransactionSlip.put("ArrivalDate", FormatData.formatDDMMYYYY
//					.format(vmaTransactionSlip.getArrivalDate()));
//		} catch (Exception e) {
//			mapVmaTransactionSlip.put("ArrivalDate", StringPool.BLANK);
//		}
//		try {
//			mapVmaTransactionSlip.put("DepartureDate",
//					FormatData.formatDDMMYYYY.format(vmaTransactionSlip
//							.getDepartureDate()));
//		} catch (Exception e) {
//			mapVmaTransactionSlip.put("DepartureDate", StringPool.BLANK);
//		}
//		mapVmaTransactionSlip.put("CargoDescription",
//				vmaTransactionSlip.getCargoDescription());
//		mapVmaTransactionSlip.put("ShipAgencyCode",
//				vmaTransactionSlip.getShipAgencyCode());
//		mapVmaTransactionSlip
//				.put("VndTotalAmount",
//						vmaTransactionSlip.getVndTotalAmount() >= 0 ? vmaTransactionSlip
//								.getVndTotalAmount() : null);
//		mapVmaTransactionSlip
//				.put("UsdTotalAmount",
//						vmaTransactionSlip.getUsdTotalAmount() >= 0 ? vmaTransactionSlip
//								.getUsdTotalAmount() : null);
//		mapVmaTransactionSlip.put(
//				"F1362Usd",
//				vmaTransactionSlip.getF1362Usd() >= 0 ? vmaTransactionSlip
//						.getF1362Usd() : null);
//		mapVmaTransactionSlip.put(
//				"F1361Vnd",
//				vmaTransactionSlip.getF1361Vnd() >= 0 ? vmaTransactionSlip
//						.getF1361Vnd() : null);
//		mapVmaTransactionSlip.put(
//				"F1372Usd",
//				vmaTransactionSlip.getF1372Usd() >= 0 ? vmaTransactionSlip
//						.getF1372Usd() : null);
//		mapVmaTransactionSlip.put(
//				"F1371Vnd",
//				vmaTransactionSlip.getF1371Vnd() >= 0 ? vmaTransactionSlip
//						.getF1371Vnd() : null);
//
//		String specRemarks = StringPool.BLANK;
//		if (Validator.isNotNull(vmaTransactionSlip.getF1361Remarks()) && (flagChangeSymbolReceipt == true)) {
//			specRemarks = StringUtil.replace(vmaTransactionSlip.getF1361Remarks(), "*", "x");
//			mapVmaTransactionSlip.put("F1361Remarks",specRemarks);
//		} else {
//			mapVmaTransactionSlip.put("F1361Remarks",
//					vmaTransactionSlip.getF1361Remarks());
//		}
//		if (Validator.isNotNull(vmaTransactionSlip.getF1362Remarks()) && (flagChangeSymbolReceipt == true)) {
//			specRemarks = StringUtil.replace(vmaTransactionSlip.getF1362Remarks(), "*", "x");
//			mapVmaTransactionSlip.put("F1362Remarks",specRemarks);
//		} else {
//			mapVmaTransactionSlip.put("F1362Remarks",
//					vmaTransactionSlip.getF1362Remarks());
//		}
//
//		if (Validator.isNotNull(vmaTransactionSlip.getF1371Remarks()) && (flagChangeSymbolReceipt == true)) {
//			specRemarks = StringUtil.replace(vmaTransactionSlip.getF1371Remarks(), "*", "x");
//			mapVmaTransactionSlip.put("F1371Remarks",specRemarks);
//		} else {
//			mapVmaTransactionSlip.put("F1371Remarks",
//					vmaTransactionSlip.getF1371Remarks());
//		}
//
//		if (Validator.isNotNull(vmaTransactionSlip.getF1372Remarks()) && (flagChangeSymbolReceipt == true)) {
//			specRemarks = StringUtil.replace(vmaTransactionSlip.getF1372Remarks(), "*", "x");
//			mapVmaTransactionSlip.put("F1372Remarks",specRemarks);
//		} else {
//			mapVmaTransactionSlip.put("F1372Remarks",
//					vmaTransactionSlip.getF1372Remarks());
//		}
//
//		mapVmaTransactionSlip.put("PaymentType",
//				getPaymentName(vmaTransactionSlip
//						.getPaymentType()));
//
//		Map mapVmaTransactionSlipDetails = new HashMap();
//		mapVmaTransactionSlipDetails
//				.put("InF1311Vnd",
//						vmaTransactionSlipDetails.getInF1311Vnd() >= 0 ? vmaTransactionSlipDetails
//								.getInF1311Vnd() : null);
//		mapVmaTransactionSlipDetails
//				.put("InF1312Usd",
//						vmaTransactionSlipDetails.getInF1312Usd() >= 0 ? vmaTransactionSlipDetails
//								.getInF1312Usd() : null);
//		if ((vmaTransactionSlipDetails.getInF1312Usd() > 0) && (vmaTransactionSlip.getPrintedConvertVND() == 1)) {
//			double Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getInF1312Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//			mapVmaTransactionSlipDetails
//			.put("InF1311Vnd", Extra1_ThanhTienDongUSDQuyDoi );
//			mapVmaTransactionSlipDetails
//			.put("InF1312Usd",0);
//		}
//		mapVmaTransactionSlipDetails
//				.put("OutF1311Vnd",
//				vmaTransactionSlipDetails.getOutF1311Vnd() >= 0 ? vmaTransactionSlipDetails
//						.getOutF1311Vnd() : null);
//		mapVmaTransactionSlipDetails
//				.put("OutF1312Usd",
//						vmaTransactionSlipDetails.getOutF1312Usd() >= 0 ? vmaTransactionSlipDetails
//								.getOutF1312Usd() : null);
//		if ((vmaTransactionSlipDetails.getOutF1312Usd() > 0) && (vmaTransactionSlip.getPrintedConvertVND() == 1)) {
//			double Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getOutF1312Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//			mapVmaTransactionSlipDetails
//			.put("OutF1311Vnd", Extra1_ThanhTienDongUSDQuyDoi );
//			mapVmaTransactionSlipDetails
//			.put("OutF1312Usd",0);
//		}
//		mapVmaTransactionSlipDetails
//				.put("InF1321Vnd",
//						vmaTransactionSlipDetails.getInF1321Vnd() >= 0 ? vmaTransactionSlipDetails
//								.getInF1321Vnd() : null);
//		mapVmaTransactionSlipDetails
//				.put("InF1322Usd",
//						vmaTransactionSlipDetails.getInF1322Usd() >= 0 ? vmaTransactionSlipDetails
//								.getInF1322Usd() : null);
//		if ((vmaTransactionSlipDetails.getInF1322Usd() > 0) && (vmaTransactionSlip.getPrintedConvertVND() == 1)) {
//			double Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getInF1322Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//			mapVmaTransactionSlipDetails
//			.put("InF1321Vnd", Extra1_ThanhTienDongUSDQuyDoi );
//			mapVmaTransactionSlipDetails
//			.put("InF1322Usd",0);
//		}
//		mapVmaTransactionSlipDetails
//				.put("InF1351Vnd",
//						vmaTransactionSlipDetails.getInF1351Vnd() >= 0 ? vmaTransactionSlipDetails
//								.getInF1351Vnd() : null);
//		mapVmaTransactionSlipDetails
//		.put("InF1352Usd",
//				vmaTransactionSlipDetails.getInF1352Usd() >= 0 ? vmaTransactionSlipDetails
//						.getInF1352Usd() : null);
//		if ((vmaTransactionSlipDetails.getInF1352Usd() > 0) && (vmaTransactionSlip.getPrintedConvertVND() == 1)) {
//			double Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getInF1352Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//			mapVmaTransactionSlipDetails
//			.put("InF1351Vnd", Extra1_ThanhTienDongUSDQuyDoi );
//			mapVmaTransactionSlipDetails
//			.put("InF1352Usd",0);
//		}
//		mapVmaTransactionSlipDetails
//				.put("OutF1351Vnd",
//						vmaTransactionSlipDetails.getOutF1351Vnd() >= 0 ? vmaTransactionSlipDetails
//								.getOutF1351Vnd() : null);
//		mapVmaTransactionSlipDetails
//				.put("OutF1352Usd",
//						vmaTransactionSlipDetails.getOutF1352Usd() >= 0 ? vmaTransactionSlipDetails
//								.getOutF1352Usd() : null);
//		if ((vmaTransactionSlipDetails.getOutF1352Usd() > 0) && (vmaTransactionSlip.getPrintedConvertVND() == 1)) {
//			double Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getOutF1352Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//			mapVmaTransactionSlipDetails
//			.put("OutF1351Vnd", Extra1_ThanhTienDongUSDQuyDoi );
//			mapVmaTransactionSlipDetails
//			.put("OutF1352Usd",0);
//		}
//		mapVmaTransactionSlipDetails
//				.put("InF1361Vnd",
//				vmaTransactionSlipDetails.getInF1361Vnd() >= 0 ? vmaTransactionSlipDetails
//						.getInF1361Vnd() : null);
//		mapVmaTransactionSlipDetails
//				.put("InF1362Usd",
//						vmaTransactionSlipDetails.getInF1362Usd() >= 0 ? vmaTransactionSlipDetails
//								.getInF1362Usd() : null);
//		if ((vmaTransactionSlipDetails.getInF1362Usd() > 0) && (vmaTransactionSlip.getPrintedConvertVND() == 1)) {
//			double Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getInF1362Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//			mapVmaTransactionSlipDetails
//			.put("InF1361Vnd", Extra1_ThanhTienDongUSDQuyDoi );
//			mapVmaTransactionSlipDetails
//			.put("InF1362Usd",0);
//		}
//		mapVmaTransactionSlipDetails
//				.put("OutF1361Vnd",
//				vmaTransactionSlipDetails.getOutF1361Vnd() >= 0 ? vmaTransactionSlipDetails
//						.getOutF1361Vnd() : null);
//		mapVmaTransactionSlipDetails
//				.put("OutF1362Usd",
//						vmaTransactionSlipDetails.getOutF1362Usd() >= 0 ? vmaTransactionSlipDetails
//								.getOutF1362Usd() : null);
//		if ((vmaTransactionSlipDetails.getOutF1362Usd() > 0) && (vmaTransactionSlip.getPrintedConvertVND() == 1)) {
//			double Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getOutF1362Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//			mapVmaTransactionSlipDetails
//			.put("OutF1361Vnd", Extra1_ThanhTienDongUSDQuyDoi );
//			mapVmaTransactionSlipDetails
//			.put("OutF1362Usd",0);
//		}
//
//		mapVmaTransactionSlipDetails
//				.put("InF1331Vnd",
//						vmaTransactionSlipDetails.getInF1331Vnd() >= 0 ? vmaTransactionSlipDetails
//								.getInF1331Vnd() : null);
//		mapVmaTransactionSlipDetails
//				.put("InF1332Usd",
//						vmaTransactionSlipDetails.getInF1332Usd() >= 0 ? vmaTransactionSlipDetails
//								.getInF1332Usd() : null);
//		if ((vmaTransactionSlipDetails.getInF1332Usd() > 0) && (vmaTransactionSlip.getPrintedConvertVND() == 1)) {
//			double Extra1_ThanhTienDongUSDQuyDoi = (new Double(vmaTransactionSlipDetails.getInF1332Usd() * vmaTransactionSlip.getExchangeRate()).longValue());
//			mapVmaTransactionSlipDetails
//			.put("InF1331Vnd", Extra1_ThanhTienDongUSDQuyDoi );
//			mapVmaTransactionSlipDetails
//			.put("InF1332Usd",0);
//		}
//
//		if (Validator.isNotNull(vmaTransactionSlipDetails.getF1311Remarks()) && (flagChangeSymbolReceipt == true)) {
//			specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1311Remarks(), "*", "x");
//			mapVmaTransactionSlipDetails.put("F1311Remarks",specRemarks);
//		} else {
//			mapVmaTransactionSlipDetails.put("F1311Remarks",
//					vmaTransactionSlipDetails.getF1311Remarks());
//		}
//		if (Validator.isNotNull(vmaTransactionSlipDetails.getF1312Remarks()) && (flagChangeSymbolReceipt == true)) {
//			specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1312Remarks(), "*", "x");
//			mapVmaTransactionSlipDetails.put("F1312Remarks",specRemarks);
//		} else {
//			mapVmaTransactionSlipDetails.put("F1312Remarks",
//					vmaTransactionSlipDetails.getF1312Remarks());
//		}
//		if (Validator.isNotNull(vmaTransactionSlipDetails.getF1321Remarks()) && (flagChangeSymbolReceipt == true)) {
//			specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1321Remarks(), "*", "x");
//			mapVmaTransactionSlipDetails.put("F1321Remarks",specRemarks);
//		} else {
//			mapVmaTransactionSlipDetails.put("F1321Remarks",
//					vmaTransactionSlipDetails.getF1321Remarks());
//		}
//		if (Validator.isNotNull(vmaTransactionSlipDetails.getF1322Remarks()) && (flagChangeSymbolReceipt == true)) {
//			specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1322Remarks(), "*", "x");
//			mapVmaTransactionSlipDetails.put("F1322Remarks",specRemarks);
//		} else {
//			mapVmaTransactionSlipDetails.put("F1322Remarks",
//					vmaTransactionSlipDetails.getF1322Remarks());
//		}
//		if (Validator.isNotNull(vmaTransactionSlipDetails.getF1331Remarks()) && (flagChangeSymbolReceipt == true)) {
//			specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1331Remarks(), "*", "x");
//			mapVmaTransactionSlipDetails.put("F1331Remarks",specRemarks);
//		} else {
//			mapVmaTransactionSlipDetails.put("F1331Remarks",
//					vmaTransactionSlipDetails.getF1331Remarks());
//		}
//		if (Validator.isNotNull(vmaTransactionSlipDetails.getF1332Remarks()) && (flagChangeSymbolReceipt == true)) {
//			specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1332Remarks(), "*", "x");
//			mapVmaTransactionSlipDetails.put("F1332Remarks",specRemarks);
//		} else {
//			mapVmaTransactionSlipDetails.put("F1332Remarks",
//					vmaTransactionSlipDetails.getF1332Remarks());
//		}
//		if (Validator.isNotNull(vmaTransactionSlipDetails.getF1351Remarks()) && (flagChangeSymbolReceipt == true)) {
//			specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1351Remarks(), "*", "x");
//			mapVmaTransactionSlipDetails.put("F1351Remarks",specRemarks);
//		} else {
//			mapVmaTransactionSlipDetails.put("F1351Remarks",
//					vmaTransactionSlipDetails.getF1351Remarks());
//		}
//		if (Validator.isNotNull(vmaTransactionSlipDetails.getF1352Remarks()) && (flagChangeSymbolReceipt == true)) {
//			specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1352Remarks(), "*", "x");
//			mapVmaTransactionSlipDetails.put("F1352Remarks",specRemarks);
//		} else {
//			mapVmaTransactionSlipDetails.put("F1352Remarks",
//					vmaTransactionSlipDetails.getF1352Remarks());
//		}
//
//
//		if (vmaTransactionSlipDetails.getInF1311Vnd() > 0) {
//			if (Validator.isNotNull(vmaTransactionSlipDetails.getF1311Remarks()) && (flagChangeSymbolReceipt == true)) {
//				specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1311Remarks(), "*", "x");
//				mapVmaTransactionSlipDetails.put("InF1311Remarks",specRemarks);
//			} else {
//				mapVmaTransactionSlipDetails.put("InF1311Remarks",
//						vmaTransactionSlipDetails.getF1311Remarks());
//			}
//		  } else if (vmaTransactionSlipDetails.getInF1312Usd() > 0) {
//			  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1312Remarks()) && (flagChangeSymbolReceipt == true)) {
//					specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1312Remarks(), "*", "x");
//					mapVmaTransactionSlipDetails.put("InF1312Remarks",specRemarks);
//				} else {
//					mapVmaTransactionSlipDetails.put("InF1312Remarks",
//						vmaTransactionSlipDetails.getF1312Remarks());
//				}
//		  } else if (vmaTransactionSlipDetails.getInF1313Vnd() > 0) {
//			  if (Validator.isNotNull(vmaTransactionSlipDetails.getInF1313Remarks()) && (flagChangeSymbolReceipt == true)) {
//					specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getInF1313Remarks(), "*", "x");
//					mapVmaTransactionSlipDetails.put("InF1313Remarks",specRemarks);
//				} else {
//					mapVmaTransactionSlipDetails.put("InF1313Remarks",
//						vmaTransactionSlipDetails.getInF1313Remarks());
//				}
//		  } else {
//
//		  }
//
//
//		if (vmaTransactionSlipDetails.getOutF1311Vnd() > 0) {
//			if (Validator.isNotNull(vmaTransactionSlipDetails.getF1311Remarks()) && (flagChangeSymbolReceipt == true)) {
//				specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1311Remarks(), "*", "x");
//				mapVmaTransactionSlipDetails.put("OutF1311Remarks",specRemarks);
//			} else {
//			  mapVmaTransactionSlipDetails.put("OutF1311Remarks",
//						vmaTransactionSlipDetails.getF1311Remarks());
//			}
//		  } else if (vmaTransactionSlipDetails.getOutF1312Usd() > 0) {
//			  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1312Remarks()) && (flagChangeSymbolReceipt == true)) {
//					specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1312Remarks(), "*", "x");
//					mapVmaTransactionSlipDetails.put("OutF1312Remarks",specRemarks);
//				} else {
//					mapVmaTransactionSlipDetails.put("OutF1312Remarks",
//						vmaTransactionSlipDetails.getF1312Remarks());
//				}
//		  } else if (vmaTransactionSlipDetails.getOutF1313Vnd() > 0) {
//			  if (Validator.isNotNull(vmaTransactionSlipDetails.getOutF1313Remarks()) && (flagChangeSymbolReceipt == true)) {
//					specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getOutF1313Remarks(), "*", "x");
//					mapVmaTransactionSlipDetails.put("OutF1313Remarks",specRemarks);
//				} else {
//					mapVmaTransactionSlipDetails.put("OutF1313Remarks",
//						vmaTransactionSlipDetails.getOutF1313Remarks());
//				}
//		  } else {
//
//		  }
//
//		if (vmaTransactionSlipDetails.getInF1351Vnd() > 0) {
//			if (Validator.isNotNull(vmaTransactionSlipDetails.getF1351Remarks()) && (flagChangeSymbolReceipt == true)) {
//				specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1351Remarks(), "*", "x");
//				mapVmaTransactionSlipDetails.put("InF1351Remarks",specRemarks);
//			} else {
//				mapVmaTransactionSlipDetails.put("InF1351Remarks",
//						vmaTransactionSlipDetails.getF1351Remarks());
//			}
//		  } else if (vmaTransactionSlipDetails.getInF1352Usd() > 0) {
//			  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1352Remarks()) && (flagChangeSymbolReceipt == true)) {
//					specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1352Remarks(), "*", "x");
//					mapVmaTransactionSlipDetails.put("InF1352Remarks",specRemarks);
//				} else {
//					mapVmaTransactionSlipDetails.put("InF1352Remarks",
//						vmaTransactionSlipDetails.getF1352Remarks());
//				}
//		  } else {
//
//		  }
//
//		if (vmaTransactionSlipDetails.getOutF1351Vnd() > 0) {
//			if (Validator.isNotNull(vmaTransactionSlipDetails.getF1351Remarks()) && (flagChangeSymbolReceipt == true)) {
//				specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1351Remarks(), "*", "x");
//				mapVmaTransactionSlipDetails.put("OutF1351Remarks",specRemarks);
//			} else {
//				mapVmaTransactionSlipDetails.put("OutF1351Remarks",
//						vmaTransactionSlipDetails.getF1351Remarks());
//			}
//		  } else if (vmaTransactionSlipDetails.getOutF1352Usd() > 0) {
//			  if (Validator.isNotNull(vmaTransactionSlipDetails.getF1352Remarks()) && (flagChangeSymbolReceipt == true)) {
//					specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1352Remarks(), "*", "x");
//					mapVmaTransactionSlipDetails.put("OutF1352Remarks",specRemarks);
//				} else {
//					mapVmaTransactionSlipDetails.put("OutF1352Remarks",
//						vmaTransactionSlipDetails.getF1352Remarks());
//				}
//		  } else {
//
//		  }
//		Boolean flagPTTND = false;
//		// Kiểm tra nếu là Biên lai thu phí PTTNĐ
//		if (vmaTransactionSlip.getF1313Vnd() > 0 || vmaTransactionSlip.getF1363Vnd() > 0 || vmaTransactionSlip.getF1373Vnd() > 0 ) {
//			flagPTTND = true;
//			mapVmaTransactionSlip.put(
//					"F1313Vnd",
//					vmaTransactionSlip.getF1313Vnd() >= 0 ? vmaTransactionSlip
//							.getF1313Vnd() : null);
//			mapVmaTransactionSlip.put(
//					"F1363Vnd",
//					vmaTransactionSlip.getF1363Vnd() >= 0 ? vmaTransactionSlip
//							.getF1363Vnd() : null);
//			mapVmaTransactionSlip.put(
//					"F1373Vnd",
//					vmaTransactionSlip.getF1373Vnd() >= 0 ? vmaTransactionSlip
//							.getF1373Vnd() : null);
//
//			if (Validator.isNotNull(vmaTransactionSlip.getF1313Remarks()) && (flagChangeSymbolReceipt == true)) {
//				specRemarks = StringUtil.replace(vmaTransactionSlip.getF1313Remarks(), "*", "x");
//				mapVmaTransactionSlip.put("F1313Remarks",specRemarks);
//			} else {
//				mapVmaTransactionSlip.put("F1313Remarks",
//						vmaTransactionSlip.getF1313Remarks());
//			}
//			if (Validator.isNotNull(vmaTransactionSlip.getF1363Remarks()) && (flagChangeSymbolReceipt == true)) {
//				specRemarks = StringUtil.replace(vmaTransactionSlip.getF1363Remarks(), "*", "x");
//				mapVmaTransactionSlip.put("F1363Remarks",specRemarks);
//			} else {
//				mapVmaTransactionSlip.put("F1363Remarks",
//					vmaTransactionSlip.getF1363Remarks());
//			}
//			if (Validator.isNotNull(vmaTransactionSlip.getF1373Remarks()) && (flagChangeSymbolReceipt == true)) {
//				specRemarks = StringUtil.replace(vmaTransactionSlip.getF1373Remarks(), "*", "x");
//				mapVmaTransactionSlip.put("F1373Remarks",specRemarks);
//			} else {
//				mapVmaTransactionSlip.put("F1373Remarks",
//					vmaTransactionSlip.getF1373Remarks());
//			}
//
//			mapVmaTransactionSlipDetails
//			.put("InF1313Vnd",
//					vmaTransactionSlipDetails.getInF1313Vnd() >= 0 ? vmaTransactionSlipDetails
//							.getInF1313Vnd() : null);
//			mapVmaTransactionSlipDetails
//			.put("OutF1313Vnd",
//					vmaTransactionSlipDetails.getOutF1313Vnd() >= 0 ? vmaTransactionSlipDetails
//							.getOutF1313Vnd() : null);
//
//			if (Validator.isNotNull(vmaTransactionSlipDetails.getInF1313Remarks()) && (flagChangeSymbolReceipt == true)) {
//				specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getInF1313Remarks(), "*", "x");
//				mapVmaTransactionSlipDetails.put("InF1313Remarks",specRemarks);
//			} else {
//				mapVmaTransactionSlipDetails.put("InF1313Remarks",
//					vmaTransactionSlipDetails.getInF1313Remarks());
//			}
//			if (Validator.isNotNull(vmaTransactionSlipDetails.getOutF1313Remarks()) && (flagChangeSymbolReceipt == true)) {
//				specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getOutF1313Remarks(), "*", "x");
//				mapVmaTransactionSlipDetails.put("OutF1313Remarks",specRemarks);
//			} else {
//				mapVmaTransactionSlipDetails.put("OutF1313Remarks",
//					vmaTransactionSlipDetails.getOutF1313Remarks());
//			}
//			if (Validator.isNotNull(vmaTransactionSlipDetails.getF1313Remarks()) && (flagChangeSymbolReceipt == true)) {
//				specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1313Remarks(), "*", "x");
//				mapVmaTransactionSlipDetails.put("F1313Remarks",specRemarks);
//			} else {
//				mapVmaTransactionSlipDetails.put("F1313Remarks",
//					vmaTransactionSlipDetails.getF1313Remarks());
//			}
//			if (Validator.isNotNull(vmaTransactionSlipDetails.getF1363Remarks()) && (flagChangeSymbolReceipt == true)) {
//				specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getF1363Remarks(), "*", "x");
//				mapVmaTransactionSlipDetails.put("F1363Remarks",specRemarks);
//			} else {
//				mapVmaTransactionSlipDetails.put("F1363Remarks",
//					vmaTransactionSlipDetails.getF1363Remarks());
//			}
//			if (Validator.isNotNull(vmaTransactionSlipDetails.getInDwtRemarks()) && (flagChangeSymbolReceipt == true)) {
//				specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getInDwtRemarks(), "*", "x");
//				mapVmaTransactionSlipDetails.put("InDwtRemarks",specRemarks);
//			} else {
//				mapVmaTransactionSlipDetails.put("InDwtRemarks",
//					vmaTransactionSlipDetails.getInDwtRemarks());
//			}
//			if (Validator.isNotNull(vmaTransactionSlipDetails.getOutDwtRemarks()) && (flagChangeSymbolReceipt == true)) {
//				specRemarks = StringUtil.replace(vmaTransactionSlipDetails.getOutDwtRemarks(), "*", "x");
//				mapVmaTransactionSlipDetails.put("OutDwtRemarks",specRemarks);
//			} else {
//				mapVmaTransactionSlipDetails.put("OutDwtRemarks",
//					vmaTransactionSlipDetails.getOutDwtRemarks());
//			}
//
//
//
//		}
//		Date date = Calendar.getInstance().getTime();
//		String strDate = FormatData.parseDateToTringDDMMYYY(vmaTransactionSlip.getDocumentaryIssued());
//
//		String inWordUSD = DanhMucUtils.convert(
//				vmaTransactionSlip.getUsdTotalAmount(), USD);
//
//		String inWordVND = DanhMucUtils.convert(
//				vmaTransactionSlip.getVndTotalAmount(), VND);
//
//		if ((vmaTransactionSlip.getPrintedConvertVND() == 1) && (vmaTransactionSlip.getUsdTotalAmount() > 0) ){
//			inWordVND = DanhMucUtils.convert(vmaTransactionSlip.getPaymentAmount(), VND);
//			// Trường hợp In quy đổi VNĐ từ USD,
//			inWordUSD = "";
//			mapVmaTransactionSlip.put("VndTotalAmount",
//					vmaTransactionSlip.getPaymentAmount());
//			mapVmaTransactionSlip.put("UsdTotalAmount", 0);
//		}
//
//		DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
//				.getByMaritimeCode(vmaTransactionSlip.getPortofAuthority());
//		String maritimeReference = dmMaritime.getMaritimeReference();
//
//		Map dataBeans = new HashMap();
//
//		dataBeans.put("A1", mapVmaTransactionSlip);
//		dataBeans.put("A2", mapVmaTransactionSlipDetails);
//		dataBeans.put("CreateDate", strDate);
//		dataBeans.put("InWordVnd", inWordVND);
//		dataBeans.put("InWordUSD", inWordUSD);
//		dataBeans.put("maritimeName", maritimeName);
//		dataBeans.put("cityCode", cityCode);
//		dataBeans.put("maritimeNameVN", maritimeNameVN);
//
//		XLSTransformer transformer = new XLSTransformer();
//		File file = new File(pathFileTemp + "TemplateExcelBLTPLPHH/"
//				+ "BienLaiThuPhiLePhiHangHaiTemplate" + ".xls");
//		String srcFilePath = StringPool.BLANK;
//		if (file.exists()) {
//			srcFilePath = pathFileTemp + "TemplateExcelBLTPLPHH/"
//					+ "BienLaiThuPhiLePhiHangHaiTemplate" + ".xls";
//		} else {
//			srcFilePath = pathFileTemp
//					+ "BienLaiThuPhiLePhiHangHaiTemplate.xls";
//		}
//		if (printing == 1 && flagPTTND == false) {
//			transformer
//			.transformXLS(srcFilePath, dataBeans, pathFileSub + fileName);
//		} else if (printing == 0 && flagPTTND == false) {
//			File file2 = new File(pathFileTemp + "TemplateExcelBLTPLPHH/" + maritimeReference + StringPool.UNDERLINE
//					+ "BienLaiThuPhiLePhiHangHaiTemplate" + ".xls");
//			srcFilePath = StringPool.BLANK;
//			if (file2.exists()) {
//				srcFilePath = pathFileTemp + "TemplateExcelBLTPLPHH/" + maritimeReference + StringPool.UNDERLINE
//						+ "BienLaiThuPhiLePhiHangHaiTemplate" + ".xls";
//			} else {
//				srcFilePath = pathFileTemp
//						+ "BienLaiThuPhiLePhiHangHaiTemplate.xls";
//			}
//			transformer
//			.transformXLS(srcFilePath, dataBeans, pathFileSub + fileName);
//		} else if (printing == 1 && flagPTTND == true) {
//			File file2 = new File(pathFileTemp + "TemplateExcelBLTPLPHH/"
//					+ "BienLaiThuPhiLePhiPTTNDTemplate" + ".xls");
//			srcFilePath = StringPool.BLANK;
//			if (file2.exists()) {
//				srcFilePath = pathFileTemp + "TemplateExcelBLTPLPHH/"
//						+ "BienLaiThuPhiLePhiPTTNDTemplate" + ".xls";
//			} else {
//				srcFilePath = pathFileTemp
//						+ "BienLaiThuPhiLePhiPTTNDTemplate.xls";
//			}
//			transformer
//			.transformXLS(srcFilePath, dataBeans, pathFileSub + fileName);
//		} else if (printing == 0 && flagPTTND == true) {
//			File file2 = new File(pathFileTemp + "TemplateExcelBLTPLPHH/" + maritimeReference + StringPool.UNDERLINE
//					+ "BienLaiThuPhiLePhiPTTNDTemplate" + ".xls");
//			srcFilePath = StringPool.BLANK;
//			if (file2.exists()) {
//				srcFilePath = pathFileTemp + "TemplateExcelBLTPLPHH/" + maritimeReference + StringPool.UNDERLINE
//						+ "BienLaiThuPhiLePhiPTTNDTemplate" + ".xls";
//			} else {
//				srcFilePath = pathFileTemp
//						+ "BienLaiThuPhiLePhiPTTNDTemplate.xls";
//			}
//			transformer
//			.transformXLS(srcFilePath, dataBeans, pathFileSub + fileName);
//		}
//
//
//		result = true;
//
//		return result;
//	}

	private String getPaymentName(int paymentType) {
		switch (paymentType) {
		case 1:
			return "Chuyển khoản";
		case 2:
			return "Tiền mặt";
		case 3:
			return "Ký quỹ";
		default:
			return StringPool.BLANK;
		}
	}

	public JSONObject retrieveTemplate(ResourceRequest resourceRequest)
			throws IOException {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		String templateFileName = ParamUtil.getString(resourceRequest,
				"templateFileName", StringPool.BLANK);
		String templateFileNameOrigin = StringPool.BLANK;
		if (templateFileName.equalsIgnoreCase("BLTPLPHH_TauBien")) {
			templateFileNameOrigin = "BienLaiThuPhiLePhiHangHaiTemplate";
		}
		try {
			FileInputStream excelFile = new FileInputStream(new File(
					pathFileTemp + templateFileNameOrigin + ".xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
			File folder = new File(pathFileTemp + "TemplateExcelBLTPLPHH");
			boolean created = true;
			if (!folder.isDirectory()) {
				created = folder.mkdir();
			}
			if (created) {
				workbook.write(new FileOutputStream(new File(pathFileTemp
						+ "TemplateExcelBLTPLPHH/" + templateFileNameOrigin
						+ ".xls")));
				result.put("status", 200);
				result.put("message", "Successfully");
			}
		} catch (Exception e) {
			result.put("status", 400);
			result.put("message", "Retrieve failed");
			log.error(e.getMessage());
		}

		return result;
	}

	public JSONObject uploadNewTemplate(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException{
		/*
		 * HttpServletRequest request = PortalUtil
		 * .getHttpServletRequest(resourceRequest);
		 */

		JSONObject result = JSONFactoryUtil.createJSONObject();

		String templateFileName = ParamUtil.getString(resourceRequest,
				"templateFileName", StringPool.BLANK);

		String templateFileNameOrigin = StringPool.BLANK;
		if (templateFileName.equalsIgnoreCase("BLTPLPHH_TauBien")) {
			templateFileNameOrigin = "BienLaiThuPhiLePhiHangHaiTemplate";
		}

		/*
		 * List<FileItem> multiparts = new ServletFileUpload( new
		 * DiskFileItemFactory()).parseRequest(request);
		 * 
		 * File folder = new File(pathFileTemp + "TemplateExcelBLTPLPHH");
		 * boolean created = true; if (!folder.isDirectory()) { created =
		 * folder.mkdir(); }
		 * 
		 * for (FileItem item : multiparts) { if (!item.isFormField()) { if
		 * (created) { log.info("================ uploadddddddddddddd");
		 * item.write(new File(pathFileTemp + "TemplateExcelBLTPLPHH/" +
		 * templateFileNameOrigin + ".xls")); } } }
		 */
//todo response file in controller
//		File file = uploadRequest.getFile("file");
//		FileOutputStream outputStream = new FileOutputStream(new File(
//				pathFileTemp + "TemplateExcelBLTPLPHH/"
//						+ templateFileNameOrigin + ".xls"));
//		FileInputStream inputStream = null;
		try {
//			inputStream = new FileInputStream(file);
//
//			File folder = new File(pathFileTemp + "TemplateExcelBLTPLPHH");
//			boolean created = true;
//			if (!folder.isDirectory()) {
//				created = folder.mkdir();
//			}
//
//			int read = 0;
//			final byte[] bytes = new byte[1024];
//			if (created) {
//				while ((read = inputStream.read(bytes)) != -1) {
//					outputStream.write(bytes, 0, read);
//				}
//			}

			result.put("status", 200);
			result.put("message", "Successfully");
		} catch (Exception e) {
			result.put("status", 400);
			result.put("message", "File Upload Failed");
			log.error(e.getMessage());
		}

		return result;
	}

	public boolean downloadTemplate(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException {


		String templateFileName = ParamUtil.getString(resourceRequest,
				"templateFileName", StringPool.BLANK);

		FileInputStream excelFile = null;
		String templateFileNameOrigin = StringPool.BLANK;
		if (templateFileName.equalsIgnoreCase("BLTPLPHH_TauBien")) {
			templateFileNameOrigin = "BienLaiThuPhiLePhiHangHaiTemplate";
		}
		excelFile = new FileInputStream(new File(pathFileTemp
				+ templateFileNameOrigin + ".xls"));
		HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
		//todo response file in controller
//		resourceResponse
//				.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
//		resourceResponse.setProperty("Content-Disposition",
//				"attachment; filename= " + templateFileNameOrigin + ".xls");
//		resourceResponse.setCharacterEncoding("UTF-8");
//		workbook.write(resourceResponse.getPortletOutputStream());
		return true;
	}

	public static JSONObject dsTBPTauDichVu_ChuyenTuyen(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws SystemException {
		HttpServletRequest request = resourceRequest;
		JSONObject result = JSONFactoryUtil.createJSONObject();
		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);
		String tugboatCode = ParamUtil.getString(request, "tugboatCode",
				StringPool.BLANK);
		String imoNumber = ParamUtil.getString(resourceRequest, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(request, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(request, "registryNumber",
				StringPool.BLANK);
		String portofAuthority = ParamUtil.getString(request,
				"portofAuthority", StringPool.BLANK);

		String documentaryNo = ParamUtil.getString(resourceRequest,
				"documentaryNo", StringPool.BLANK);
		String documentaryIssued = ParamUtil.getString(resourceRequest,
				"documentaryIssued", StringPool.BLANK);
		String paymentDate = ParamUtil.getString(resourceRequest,
				"paymentDate", StringPool.BLANK);
		String paymentStatus = ParamUtil.getString(resourceRequest,
				"paymentStatus", StringPool.BLANK);
		String currentPaymentStatus = ParamUtil.getString(resourceRequest,
				"currentPaymentStatus", StringPool.BLANK);


		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String shipAgencyCode = ParamUtil.getString(resourceRequest,
				"shipAgencyCode", StringPool.BLANK);
		String shipAgencyName = ParamUtil.getString(resourceRequest,
				"shipAgencyName", StringPool.BLANK);
		String shipOwnerName = ParamUtil.getString(resourceRequest,
				"shipOwnerName", StringPool.BLANK);
		
		//nameOfShip, shipAgencyCode, shipAgencyName, shipOwnerName, documentaryNo, documentaryIssued, paymentDate, paymentStatus, currentPaymentStatus, 
		
		int keytaudichvu = ParamUtil.getInteger(request, "keytaudichvu", 0);
		int keytaukhach = ParamUtil.getInteger(request, "keytaukhach", 0);

		int flag = -1;
		if (keytaudichvu == 1) {
			flag = 1;
		} else if (keytaukhach == 1) {
			flag = 0;
		}
		long total = 0;
		int start = GetterUtil.getInteger(request.getParameter("start"), 0);
		int end = GetterUtil.getInteger(request.getParameter("end"), 0);

		if (flag > -1) {
			total = VmaTransactionSlipLocalServiceUtil.countDsTBP(itineraryNo,
					tugboatCode, imoNumber, callSign, registryNumber, portofAuthority, 
					nameOfShip, shipAgencyCode, shipAgencyName, shipOwnerName, documentaryNo, documentaryIssued, paymentDate, paymentStatus, currentPaymentStatus,
					flag);
			if (flag == 1) {
				JSONArray vmaTransactionSlips = VmaTransactionSlipLocalServiceUtil
						.findDsTBP(itineraryNo, tugboatCode, portofAuthority,
								imoNumber, callSign, registryNumber, 
								nameOfShip, shipAgencyCode, shipAgencyName, shipOwnerName, documentaryNo, documentaryIssued, paymentDate, paymentStatus, currentPaymentStatus,
								start, end);
				result.put("data", vmaTransactionSlips);
				result.put("total", total);
			} else if (flag == 0) {
				List<VmaTransactionSlip> vmaTransactionSlips = VmaTransactionSlipLocalServiceUtil
						.findDsTBP_ChuyenTuyen(itineraryNo, portofAuthority,
								imoNumber, callSign, registryNumber, 
								nameOfShip, shipAgencyCode, shipAgencyName, shipOwnerName, documentaryNo, documentaryIssued, paymentDate, paymentStatus, currentPaymentStatus,
								start, end);

				JSONArray array = JSONFactoryUtil.createJSONArray();
				for (VmaTransactionSlip vmaTransactionSlip : vmaTransactionSlips) {
					JSONObject obj = JSONFactoryUtil.createJSONObject();

					try {
						obj = VMAUtils.object2Json(vmaTransactionSlip,
								VmaTransactionSlip.class);
					} catch (JSONException e) {
						log.error(e.getMessage());
					}

					array.put(obj);
				}
				result.put("data", array);
				result.put("total", total);
			}

		}

		return result;
	}

	public static long countDsTBPTauDichVu_ChuyenTuyen(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws SystemException {
		HttpServletRequest request = resourceRequest;
		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);
		String tugboatCode = ParamUtil.getString(request, "tugboatCode",
				StringPool.BLANK);
		String imoNumber = ParamUtil.getString(request, "imoNumber", StringPool.BLANK);
		String callSign = VMAUtils.getString(request, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(request, "registryNumber",
				StringPool.BLANK);
		String portofAuthority = ParamUtil.getString(request,
				"portofAuthority", StringPool.BLANK);
		
		String documentaryNo = ParamUtil.getString(resourceRequest,
				"documentaryNo", StringPool.BLANK);		
		String documentaryIssued = ParamUtil.getString(resourceRequest,				
				"documentaryIssued", StringPool.BLANK);
		String paymentDate = ParamUtil.getString(resourceRequest,
				"paymentDate", StringPool.BLANK);
		String paymentStatus = ParamUtil.getString(resourceRequest,
				"paymentStatus", StringPool.BLANK);
		String currentPaymentStatus = ParamUtil.getString(resourceRequest,
				"currentPaymentStatus", StringPool.BLANK);


		String nameOfShip = ParamUtil.getString(resourceRequest, "nameOfShip",
				StringPool.BLANK);
		String shipAgencyCode = ParamUtil.getString(resourceRequest,
				"shipAgencyCode", StringPool.BLANK);
		String shipAgencyName = ParamUtil.getString(resourceRequest,
				"shipAgencyName", StringPool.BLANK);
		String shipOwnerName = ParamUtil.getString(resourceRequest,
				"shipOwnerName", StringPool.BLANK);
		
		int keytaudichvu = ParamUtil.getInteger(request, "keytaudichvu", 0);
		int keytaukhach = ParamUtil.getInteger(request, "keytaukhach", 0);
		
		

		int flag = -1;
		if (keytaudichvu == 1) {
			flag = 1;
		} else if (keytaukhach == 1) {
			flag = 0;
		}

		if (flag > -1) {
			return VmaTransactionSlipLocalServiceUtil.countDsTBP(itineraryNo, tugboatCode, imoNumber, callSign, registryNumber,	portofAuthority, 
					nameOfShip, shipAgencyCode, shipAgencyName, shipOwnerName, documentaryNo, documentaryIssued, paymentDate, paymentStatus, currentPaymentStatus, flag);
		}
		return -1;
	}

	public static long countDsQLCongNo(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws SystemException {
		HttpServletRequest request = resourceRequest;

		String nameOfShip = ParamUtil.getString(request, "nameOfShip",
				StringPool.BLANK);
		String departmentCode = ParamUtil.getString(request, "departmentCode",
				StringPool.BLANK);
		String paymentName = ParamUtil.getString(request, "paymentName",
				StringPool.BLANK);
		String month = ParamUtil.getString(request, "month", StringPool.BLANK);
		String  paymentStatus = ParamUtil.getString(request, "paymentStatus", StringPool.BLANK);
		int usdTotalAmount = ParamUtil
				.getInteger(request, "usdTotalAmount", -1);
		String vrCode = ParamUtil
				.getString(request, "vrCode", StringPool.BLANK);
		try {
			return VmaTransactionSlipLocalServiceUtil.countDsQLCongNo(
					nameOfShip, departmentCode, paymentName, month,
					paymentStatus, usdTotalAmount, vrCode, -1, -1);
		} catch (Exception e) {
			return 0;
		}
	}

	public static JSONObject dsQLCongNo(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws SystemException,
			JSONException {
		HttpServletRequest request = resourceRequest;

		String nameOfShip = ParamUtil.getString(request, "nameOfShip",
				StringPool.BLANK);
		String departmentCode = ParamUtil.getString(request, "departmentCode",
				StringPool.BLANK);
		String paymentName = ParamUtil.getString(request, "paymentName",
				StringPool.BLANK);
		String month = ParamUtil.getString(request, "month", StringPool.BLANK);
		String  paymentStatus = ParamUtil.getString(request, "paymentStatus", StringPool.BLANK); 
		int usdTotalAmount = ParamUtil
				.getInteger(request, "usdTotalAmount", -1);
		String vrCode = ParamUtil
				.getString(request, "vrCode", StringPool.BLANK);
		int start = ParamUtil.getInteger(request, "start", 0);
		int end = ParamUtil.getInteger(request, "end", 10);
		String shipAgencyName = ParamUtil.getString(request, "shipAgencyName",
				StringPool.BLANK);
		String imoNumber = ParamUtil.getString(request, "imoNumber",
				StringPool.BLANK);
		String callSign = VMAUtils.getString(request, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(request, "registryNumber",
				StringPool.BLANK);
		String power = ParamUtil.getString(request, "power", StringPool.BLANK);
		String documentaryIssued = ParamUtil.getString(request,
				"documentaryIssued", StringPool.BLANK);
		String vndTotalAmount = ParamUtil.getString(request, "vndTotalAmount",
				StringPool.BLANK);

		List<VmaTransactionSlip> vmaTransactionSlips = VmaTransactionSlipLocalServiceUtil
				.dsQLCongNo(nameOfShip, departmentCode, paymentName, month,
						paymentStatus, usdTotalAmount, vrCode, shipAgencyName,
						imoNumber, callSign, registryNumber, power,
						documentaryIssued, vndTotalAmount, start, end);

		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();
		for (VmaTransactionSlip vmaTransactionSlip : vmaTransactionSlips) {
			JSONObject currentObject = JSONFactoryUtil.createJSONObject();

			try {
				currentObject = VMAUtils.object2Json(vmaTransactionSlip,
						VmaTransactionSlip.class);
			} catch (JSONException e) {
			}
			
			String itineraryNo = currentObject.getString("itineraryNo");
			
			VmaItinerary vmaItinerary = null;
			if (Validator.isNotNull(itineraryNo)
					&& !itineraryNo.equals("---")) {
				try {
					vmaItinerary = VmaItineraryLocalServiceUtil
							.fetchByitineraryNo(itineraryNo);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
			if (Validator.isNotNull(vmaItinerary) ) {
				JSONObject jsonItinerary = VMAUtils.object2Json(
						vmaItinerary, VmaItinerary.class);
				List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil.findByItineraryNo_NoticeShipType(itineraryNo, 2);
				VmaScheduleTransfer vmaScheduleTransfer = null;
				if(vmaScheduleTransfers != null && vmaScheduleTransfers.size()>0){
					vmaScheduleTransfer = vmaScheduleTransfers.get(0);
				}
				if ((vmaTransactionSlip.getNoticeShipType() == 2 ) && (Validator.isNotNull(vmaScheduleTransfer) )) {
					String _imoNumber = vmaScheduleTransfer.getImoNumber();
					String _callSign = vmaScheduleTransfer.getCallSign();
					String _registryNumber = vmaScheduleTransfer.getRegistryNumber();
					String _nameOfShip = vmaScheduleTransfer.getShipName();
					String _flagStateOfShip = vmaScheduleTransfer.getFlagStateOfShip();
					
					jsonItinerary.put("nameOfShip", _nameOfShip);
					jsonItinerary.put("flagStateOfShip", _flagStateOfShip);
					jsonItinerary.put("callSign", _callSign);
					jsonItinerary.put("imoNumber", _imoNumber);
					jsonItinerary.put("registryNumber", _registryNumber);
					jsonItinerary.put("shipOperatorCode", vmaScheduleTransfer.getShipOperatorCode());
					try {
						jsonItinerary.put("shipOperatorName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOperatorCode()).getCompanyName());
					} catch(Exception e){
					}
					jsonItinerary.put("shipOwnerCode", vmaScheduleTransfer.getShipOwnerCode());
					try {
						jsonItinerary.put("shipOwnerName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOwnerCode()).getCompanyName());
					} catch(Exception e){
					}
					jsonItinerary.put("shipCaptain", vmaScheduleTransfer.getNameOfMaster());
					jsonItinerary.put("vmaShipTypeCode", vmaScheduleTransfer.getShipTypeMT());
					jsonItinerary.put("shipTypeCode", vmaScheduleTransfer.getShipTypeCode());
					jsonItinerary.put("vrCode", vmaScheduleTransfer.getVrCode());
					
				}
				currentObject.put("itinerary", jsonItinerary);
			}
			array.put(currentObject);
		}
		long total = VmaTransactionSlipLocalServiceUtil.countDsQLCongNo(
				nameOfShip, departmentCode, paymentName, month, paymentStatus,
				usdTotalAmount, vrCode, start, end);

		result.put("data", array);
		result.put("total", total);
		
		return result;
	}

	public static JSONObject findVmaTransactionSlipDetailsChild(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws SystemException, JSONException {
		HttpServletRequest request = resourceRequest;

		String debitnoteid = ParamUtil.getString(request, "debitnoteid");
		String trackChangesCargoList = ParamUtil.getString(request,
				"trackChangesCargoList");
		String trachChangesAnchorage = null, trachChangesProtest = null;
		if (Validator.isNull(trackChangesCargoList)) {
			trachChangesAnchorage = ParamUtil.getString(request,
					"trachChangesAnchorage");
			if (Validator.isNull(trachChangesAnchorage)) {
				trachChangesProtest = ParamUtil.getString(request,
						"trachChangesProtest");
			}
		}
		int start = ParamUtil.getInteger(request, "start", 0);
		int end = ParamUtil.getInteger(request, "end", 15);

		List<VmaTransactionSlip> vmaTransactionSlips = VmaTransactionSlipLocalServiceUtil
				.findByF_Debitnoteid(Integer.valueOf(debitnoteid), start, end);
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		for (VmaTransactionSlip vmaTransactionSlip : vmaTransactionSlips) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			JSONObject obj_1 = JSONFactoryUtil.createJSONObject();
			JSONObject obj_2 = JSONFactoryUtil.createJSONObject();
			VmaTransactionSlipDetails vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
					.fetchByItineraryNo_DocumentaryCode(
							vmaTransactionSlip.getItineraryNo(),
							vmaTransactionSlip.getDocumentaryCode());
			if (Validator.isNotNull(trackChangesCargoList)) {
				if (vmaTransactionSlip.getTrackChangesCargoList() == 1) {
					obj_1 = VMAUtils.object2Json(vmaTransactionSlipDetails,
							VmaTransactionSlipDetails.class);
					obj_2 = VMAUtils.object2Json(vmaTransactionSlip,
							VmaTransactionSlip.class,
							new String[] { "shipAgencyCode" });

					obj.put("vmaTransactionSlip", obj_2);
					obj.put("vmaTransactionSlipDetails", obj_1);

					array.put(obj);
				}
			} else if (Validator.isNotNull(trachChangesAnchorage)) {
				if (vmaTransactionSlip.getTrachChangesAnchorage() == 1) {
					obj_1 = VMAUtils.object2Json(vmaTransactionSlipDetails,
							VmaTransactionSlipDetails.class);
					obj_2 = VMAUtils.object2Json(vmaTransactionSlip,
							VmaTransactionSlip.class,
							new String[] { "shipAgencyCode" });

					obj.put("vmaTransactionSlip", obj_2);
					obj.put("vmaTransactionSlipDetails", obj_1);

					array.put(obj);
				}
			} else if (Validator.isNotNull(trachChangesProtest)) {
				if (vmaTransactionSlip.getTrachChangesProtest() == 1) {
					obj_1 = VMAUtils.object2Json(vmaTransactionSlipDetails,
							VmaTransactionSlipDetails.class);
					obj_2 = VMAUtils.object2Json(vmaTransactionSlip,
							VmaTransactionSlip.class,
							new String[] { "shipAgencyCode" });

					obj.put("vmaTransactionSlip", obj_2);
					obj.put("vmaTransactionSlipDetails", obj_1);

					array.put(obj);
				}
			}
		}
		result.put("data", array);
		return result;
	}
}
