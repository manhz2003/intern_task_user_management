package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.nghiepvu.modelImpl.VmaTransactionSlipModelImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaTransactionSlipDetailsPersistenceImpl;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipOwnerLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.*;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import vn.gt.portlet.phuongtien.VMAUtils;import com.fds.nsw.liferay.core.CounterLocalServiceUtil;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.ArrayList; 
import java.util.List;
import java.util.Locale;

import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import org.json.*;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.fds.nsw.kernel.exception.PortalException;
import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaTransactionSlipFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaTransactionSlipPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import vn.nsw.model.ResponseTransactionList;

@Slf4j @Service
public class VmaTransactionSlipLocalServiceImpl { 
@Autowired VmaTransactionSlipPersistenceImpl persistence;
@Autowired
VmaTransactionSlipDetailsPersistenceImpl persistenceDetail;
@Autowired VmaTransactionSlipFinderImpl finder;


	public VmaTransactionSlip createVmaTransactionSlip(long id) {
		return persistence.create(id);
	}

	public VmaTransactionSlip deleteVmaTransactionSlip(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaTransactionSlip deleteVmaTransactionSlip(VmaTransactionSlip VmaTransactionSlip)
		throws SystemException {
		return persistence.remove(VmaTransactionSlip);
	}

	public VmaTransactionSlip fetchVmaTransactionSlip(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaTransactionSlip getVmaTransactionSlip(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaTransactionSlip> getVmaTransactionSlips(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaTransactionSlipsCount() throws SystemException {
		return persistence.countAll();
	}


	public VmaTransactionSlip updateVmaTransactionSlip(VmaTransactionSlip VmaTransactionSlip,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaTransactionSlip, merge);
	}


    public VmaTransactionSlip addVmaTransactionSlip(
            VmaTransactionSlip vmaTransactionSlip) throws SystemException {
        long id = CounterLocalServiceUtil.increment(VmaTransactionSlip.class
                .getName());
        vmaTransactionSlip.setId(id);
        vmaTransactionSlip.setModifiedDate(new Date());
        long sequenceNo = CounterLocalServiceUtil.increment(
                "VMA_TRANSACTION_SLIP_SEQUENCE_NO", 1);
        vmaTransactionSlip.setSequenceNo((int) sequenceNo);
        // VMAUtils.formatUnicode(vmaTransactionSlip);
        return persistence.updateImpl(vmaTransactionSlip, false);
    }

    public JSONObject addVmaTransactionSlip_VmaTransactionSlipDetail(
            VmaTransactionSlip vmaTransactionSlip,
            VmaTransactionSlipDetails vmaTransactionSlipDetails)
            throws SystemException, PortalException {
        if (vmaTransactionSlip.getId() <= 0) {
            // add new

            long id = CounterLocalServiceUtil.increment(VmaTransactionSlip.class
                    .getName());
            vmaTransactionSlip.setId(id);
            vmaTransactionSlip.setModifiedDate(new Date());
            long sequenceNo = CounterLocalServiceUtil.increment(
                    "VMA_TRANSACTION_SLIP_SEQUENCE_NO", 1);
            vmaTransactionSlip.setSequenceNo((int) sequenceNo);
        }
		/*// sua loi, reset ShipAgencyName
		if (Validator.isNotNull(vmaTransactionSlip.getShipAgencyCode())) {
			try {
				DmShipAgency objDmShipAgency = DmShipAgencyLocalServiceUtil.fetchByShipAgencyCode(vmaTransactionSlip.getShipAgencyCode());
				if (Validator.isNotNull(objDmShipAgency)) {
					vmaTransactionSlip.setShipAgencyName(objDmShipAgency.getShipAgencyName());
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}

		}*/
        // edit by Dungnv
        VmaTransactionSlip vmaTransactionSlip2 = VmaTransactionSlipLocalServiceUtil
                .fetchByitineraryNo_documentaryCode(
                        vmaTransactionSlip.getItineraryNo(),
                        vmaTransactionSlip.getDocumentaryCode());
        VmaTransactionSlipDetails vmaTransactionSlipDetails2 = VmaTransactionSlipDetailsLocalServiceUtil
                .fetchByItineraryNo_DocumentaryCode(
                        vmaTransactionSlipDetails.getItineraryNo(),
                        vmaTransactionSlipDetails.getDocumentaryCode());

        if (Validator.isNotNull(vmaTransactionSlip2)
                && Validator.isNotNull(vmaTransactionSlip.getItineraryNo())
                && Validator.isNotNull(vmaTransactionSlip.getDocumentaryCode())
                && vmaTransactionSlip2.getSequenceNo() > 0
                && (vmaTransactionSlip2.getId() == vmaTransactionSlip.getId())) {
            vmaTransactionSlip = persistence.updateImpl(
                    vmaTransactionSlip, false);
        } else {
            if (Validator.isNotNull(vmaTransactionSlip)
                    && Validator.isNotNull(vmaTransactionSlip.getItineraryNo())
                    && Validator.isNotNull(vmaTransactionSlip.getDocumentaryCode())
                    && vmaTransactionSlip.getSequenceNo() > 0 ) {

                vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
                        .addVmaTransactionSlip(vmaTransactionSlip);
            }
        }




        if (Validator.isNotNull(vmaTransactionSlipDetails2)
                && Validator.isNotNull(vmaTransactionSlipDetails.getItineraryNo())
                && Validator.isNotNull(vmaTransactionSlipDetails.getDocumentaryCode())
                && (vmaTransactionSlipDetails2.getId() == vmaTransactionSlipDetails.getId())) {
            vmaTransactionSlipDetails = persistenceDetail
                    .updateImpl(vmaTransactionSlipDetails, false);
        } else{
            if (Validator.isNotNull(vmaTransactionSlipDetails)
                    && Validator.isNotNull(vmaTransactionSlipDetails.getItineraryNo())
                    && Validator.isNotNull(vmaTransactionSlipDetails.getDocumentaryCode())
                    && Validator.isNotNull(vmaTransactionSlip)
                    && vmaTransactionSlip.getSequenceNo() > 0 ) {

                vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
                        .addVmaTransactionSlipDetails(vmaTransactionSlipDetails);
            }
        }

        TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil
                .fetchTempDebitNote(vmaTransactionSlip.getDebitnoteid());

        JSONObject result = JSONFactoryUtil.createJSONObject();

        JSONObject tmp3 = null;

        if (tempDebitNote != null && vmaTransactionSlip.getDebitnoteid() > 0) {
            if (vmaTransactionSlip.getVndTotalAmount() != GetterUtil
                    .getDouble(0)
                    && vmaTransactionSlip.getUsdTotalAmount() != GetterUtil
                    .getDouble(0)) {
                tempDebitNote.setTotalpayment(BigDecimal.valueOf(vmaTransactionSlip
                        .getVndTotalAmount()));
                tempDebitNote.setCurrency("VND");
                tempDebitNote.setTotalforeignpayment(BigDecimal.valueOf(vmaTransactionSlip
                        .getUsdTotalAmount()));
                tempDebitNote.setForeigncurrency("USD");
            } else if (vmaTransactionSlip.getVndTotalAmount() != GetterUtil
                    .getDouble(0)) {
                tempDebitNote.setTotalpayment(BigDecimal.valueOf(vmaTransactionSlip
                        .getVndTotalAmount()));
                tempDebitNote.setCurrency("VND");
            } else if (vmaTransactionSlip.getUsdTotalAmount() != GetterUtil
                    .getDouble(0)) {
                tempDebitNote.setTotalpayment(BigDecimal.valueOf(vmaTransactionSlip
                        .getUsdTotalAmount()));
                tempDebitNote.setCurrency("USD");
            } else {
                tempDebitNote.setTotalpayment(BigDecimal.valueOf(vmaTransactionSlip
                        .getVndTotalAmount()));
                tempDebitNote.setCurrency("VND");
            }
            tempDebitNote = TempDebitNoteLocalServiceUtil
                    .updateTempDebitNote(tempDebitNote);

            tmp3 = VMAUtils.object2Json(tempDebitNote,
                    TempDebitnote.class);
        }
        result.put("tempDebitNote", tmp3);

        JSONObject tmp1 = VMAUtils.object2Json(vmaTransactionSlip,
                VmaTransactionSlip.class);

        JSONObject tmp2 = VMAUtils.object2Json(vmaTransactionSlipDetails,
                VmaTransactionSlipDetails.class);

        result.put("vmaTransactionSlip", tmp1);

        result.put("vmaTransactionSlipDetails", tmp2);

        return result;
    }

    public List<VmaTransactionSlip> findByItineraryNo_PaymentStatus(
            String itineraryNo, int paymentStatus) throws SystemException {
        return persistence.findByitineraryNo_paymentStatus(
                itineraryNo, paymentStatus);
    }

    public VmaTransactionSlip updateVmaTransactionSlip(
            VmaTransactionSlip vmaTransactionSlip) throws SystemException {
        vmaTransactionSlip.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaTransactionSlip);
        return persistence.updateImpl(vmaTransactionSlip, false);
    }

    public JSONObject updateVmaTransactionSlip_VmaTransactionSlipDetail(
            VmaTransactionSlip vmaTransactionSlip,
            VmaTransactionSlipDetails vmaTransactionSlipDetails)
            throws SystemException, PortalException {
        if (Validator.isNotNull(vmaTransactionSlip) && vmaTransactionSlip.getSequenceNo() > 0) {
            vmaTransactionSlip.setModifiedDate(new Date());
            // VMAUtils.formatUnicode(vmaTransactionSlip);
            vmaTransactionSlip = persistence.updateImpl(
                    vmaTransactionSlip, false);
            vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
                    .updateVmaTransactionSlipDetails(vmaTransactionSlipDetails);
        }
        TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil
                .fetchTempDebitNote(vmaTransactionSlip.getDebitnoteid());
        if (tempDebitNote != null && vmaTransactionSlip.getDebitnoteid() > 0
                && (vmaTransactionSlip.getPaymentStatus() == 4 || vmaTransactionSlip
                .getPaymentStatus() == 6)) {

            try {
                List<VmaTransactionSlip> vmaTransactionSlips = findByItineraryNo_PaymentStatus(
                        vmaTransactionSlip.getItineraryNo(), 4);
                if (vmaTransactionSlips != null) {
                    double vndTotalAmount = 0;
                    double usdTotalAmount = 0;
                    for (VmaTransactionSlip _tmp : vmaTransactionSlips) {
                        vndTotalAmount += _tmp.getVndTotalAmount();
                        usdTotalAmount += _tmp.getUsdTotalAmount();
                    }

                    if (vndTotalAmount != GetterUtil
                            .getDouble(0)
                            && usdTotalAmount != GetterUtil
                            .getDouble(0)) {
                        tempDebitNote.setTotalpayment(BigDecimal.valueOf(vndTotalAmount));
                        tempDebitNote.setCurrency("VND");
                        tempDebitNote.setTotalforeignpayment(BigDecimal.valueOf(usdTotalAmount));
                        tempDebitNote.setForeigncurrency("USD");
                    } else if (vmaTransactionSlip.getVndTotalAmount() != GetterUtil
                            .getDouble(0)) {
                        tempDebitNote.setTotalpayment(BigDecimal.valueOf(vndTotalAmount));
                        tempDebitNote.setCurrency("VND");
                    } else if (vmaTransactionSlip.getUsdTotalAmount() != GetterUtil
                            .getDouble(0)) {
                        tempDebitNote.setTotalpayment(BigDecimal.valueOf(usdTotalAmount));
                        tempDebitNote.setCurrency("USD");
                    } else {
                        tempDebitNote.setTotalpayment(BigDecimal.valueOf(vndTotalAmount));
                        tempDebitNote.setCurrency("VND");
                    }

                    Locale locale = new Locale("en", "UK");

                    DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
                    symbols.setDecimalSeparator(',');
                    symbols.setGroupingSeparator('.');

                    String pattern = "#,##0.###";
                    DecimalFormat formatter = new DecimalFormat(pattern, symbols);
                    String patternVND = "#,##0";
                    DecimalFormat formatterVND = new DecimalFormat(patternVND, symbols);

//					NumberFormat formatter = new DecimalFormat("#0.00");
//					NumberFormat formatterVND = new DecimalFormat("#0.00");
                    if (vndTotalAmount > GetterUtil.getDouble(0)
                            && usdTotalAmount > GetterUtil.getDouble(0)) {
                        tempDebitNote.setComments(formatterVND
                                .format(vndTotalAmount)
                                + " VND, "
                                + formatter.format(usdTotalAmount) + " USD");
                    } else if (vndTotalAmount > GetterUtil.getDouble(0)
                            && usdTotalAmount <= GetterUtil.getDouble(0)) {
                        tempDebitNote.setComments(formatterVND
                                .format(vndTotalAmount) + " VND");
                    } else if (vndTotalAmount <= GetterUtil.getDouble(0)
                            && usdTotalAmount > GetterUtil.getDouble(0)) {
                        tempDebitNote.setComments(formatter
                                .format(usdTotalAmount) + " USD");
                    } else {
                        tempDebitNote.setComments(formatterVND
                                .format(vndTotalAmount)
                                + " VND, "
                                + formatter.format(usdTotalAmount) + " USD");
                    }

                    tempDebitNote = TempDebitNoteLocalServiceUtil
                            .updateTempDebitNote(tempDebitNote);
                }

                if ((tempDebitNote != null) && (vmaTransactionSlip.getPaymentStatus() == 6) ){
                    String DocumentCode = vmaTransactionSlip.getDocumentaryCode();
                    tempDebitNote.setReportby(null);
                    TempDebitNoteLocalServiceUtil.updateTempDebitNote(tempDebitNote);

                    vmaTransactionSlip.setStampStatus(0);
                    vmaTransactionSlip.setReportdate(null);
                    vmaTransactionSlip.setReportby(null);
                    vmaTransactionSlip.setDocumentaryCode(vmaTransactionSlip.getDocumentaryCode()+"___");
                    VmaTransactionSlipLocalServiceUtil.updateVmaTransactionSlip(vmaTransactionSlip);

                    vmaTransactionSlipDetails.setDocumentaryCode(vmaTransactionSlipDetails.getDocumentaryCode()+"___");
                    vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
                            .updateVmaTransactionSlipDetails(vmaTransactionSlipDetails);

                    List<VmaScheduleAnchorage> lstVmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil.findByItineraryNo_documentaryCode(vmaTransactionSlip.getItineraryNo(), DocumentCode);
                    if (Validator.isNotNull(lstVmaScheduleAnchorage) && lstVmaScheduleAnchorage.size() > 0) {
                        for (VmaScheduleAnchorage vmaScheduleAnchorage : lstVmaScheduleAnchorage) {
                            vmaScheduleAnchorage.setMakePayment(0);
                            vmaScheduleAnchorage.setDocumentaryCode(null);
                            VmaScheduleAnchorageLocalServiceUtil
                                    .updateVmaScheduleAnchorage(vmaScheduleAnchorage);
                        }
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        try {
            VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
                    .fetchByitineraryNo(vmaTransactionSlip.getItineraryNo());

            if (vmaTransactionSlip.getPaymentStatus() == 4
                    && vmaItinerary != null) {
                switch (vmaTransactionSlip.getPaymentCategory()) {
                    case 1:
                        vmaItinerary.setPayment2DepartureStatus(4);
                        vmaItinerary.setPayment2ArrivalStatus(4);
                        vmaItinerary.setPayment2ItineraryStatus(4);
                        break;

                    case 2:
                        vmaItinerary.setPayment2ArrivalStatus(4);
                        vmaItinerary.setPayment2ItineraryStatus(0);
                        break;
                    case 3:
                        vmaItinerary.setPayment2DepartureStatus(4);
                        vmaItinerary.setPayment2ItineraryStatus(0);
                        break;
                    case 4:
                        vmaItinerary.setPayment2AnchorageStatus(4);
                        break;
                    case 5:
                        vmaItinerary.setPayment2ServiceStatus(4);
                        break;
                    case 6:
                        vmaItinerary.setPayment2CargoStatus(4);
                        break;
                    case 7:
                        vmaItinerary.setPayment2ProtestStatus(4);
                        break;
                    case 8:
                        vmaItinerary.setPayment2DepartureStatus(4);
                        vmaItinerary.setPayment2ArrivalStatus(4);
                        vmaItinerary.setPayment2ItineraryStatus(4);
                        break;
                    default:
                        break;
                }
            } else if (vmaTransactionSlip.getPaymentStatus() == 6
                    && vmaItinerary != null) {
                switch (vmaTransactionSlip.getPaymentCategory()) {
                    case 1:
                        vmaItinerary.setPayment2DepartureStatus(6);
                        vmaItinerary.setPayment2ArrivalStatus(6);
                        vmaItinerary.setPayment2ItineraryStatus(6);
                        break;

                    case 2:
                        vmaItinerary.setPayment2ArrivalStatus(6);
                        vmaItinerary.setPayment2ItineraryStatus(0);
                        break;
                    case 3:
                        vmaItinerary.setPayment2DepartureStatus(6);
                        vmaItinerary.setPayment2ItineraryStatus(0);
                        break;
                    case 4:
                        vmaItinerary.setPayment2AnchorageStatus(6);
                        break;
                    case 5:
                        vmaItinerary.setPayment2ServiceStatus(6);
                        break;
                    case 6:
                        vmaItinerary.setPayment2CargoStatus(6);
                        break;
                    case 7:
                        vmaItinerary.setPayment2ProtestStatus(6);
                        break;
                    case 8:
                        vmaItinerary.setPayment2DepartureStatus(6);
                        vmaItinerary.setPayment2ArrivalStatus(6);
                        vmaItinerary.setPayment2ItineraryStatus(6);
                        break;
                    default:
                        break;
                }
            }

            VmaItineraryLocalServiceUtil.updateVmaItinerary(vmaItinerary);
        } catch (Exception e) {
            log.error(e.getMessage());
        }


        JSONObject result = JSONFactoryUtil.createJSONObject();

        JSONObject tmp1 = VMAUtils.object2Json(vmaTransactionSlip,
                VmaTransactionSlip.class);

        JSONObject tmp2 = VMAUtils.object2Json(vmaTransactionSlipDetails,
                VmaTransactionSlipDetails.class);

        if (tempDebitNote != null && vmaTransactionSlip.getDebitnoteid() > 0) {
            JSONObject tmp3 = VMAUtils.object2Json(tempDebitNote,
                    TempDebitnote.class);
            result.put("tempDebitNote", tmp3);
        } else {
            result.put("tempDebitNote", "");
        }

        result.put("vmaTransactionSlip", tmp1);

        result.put("vmaTransactionSlipDetails", tmp2);

        return result;
    }

    public VmaTransactionSlip delete(long id) throws SystemException,
            NoSuchVmaTransactionSlipException {
        return persistence.remove(id);
    }

    public List<VmaTransactionSlip> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public VmaTransactionSlip fetchByitineraryNo_sequenceNo(String itineraryNo,
                                                            int sequenceNo) {
        try {
            return persistence.fetchByitineraryNo_sequenceNo(
                    itineraryNo, sequenceNo);
        } catch (SystemException e) {
            return null;
        }

    }

    public VmaTransactionSlip fetchByitineraryNo_noticeShipType(
            String itineraryNo, int noticeShipType) {
        try {
            return persistence
                    .fetchByitineraryNo_noticeShipType(itineraryNo,
                            noticeShipType);
        } catch (SystemException e) {
            return null;
        }
    }

    public VmaTransactionSlip fetchByitineraryNo_documentaryCode(
            String itineraryNo, String documentaryCode) {
        try {
            return persistence
                    .fetchByitineraryNo_documentaryCode(itineraryNo,
                            documentaryCode);
        } catch (SystemException e) {
            return null;
        }
    }

    public VmaTransactionSlip updateVmaTransactionSlip(
            VmaTransactionSlip vmaTransactionSlip, String maritimeCode,
            double conversionValue, Integer makePayment, Integer forArrival,
            Integer forDeparture, String purposeCode, String shipTypeCode,
            String portHarbourCode, Double anchorageHour, Double seat,
            Double voyageNumber, double anchorageDurationTime)
            throws JSONException, SystemException {

        List<VmaTransactionType> vmaTransactionTypes = VmaTransactionTypeLocalServiceUtil
                .findByPortofAuthority_transactionLevel(maritimeCode, 3);

        if (vmaTransactionSlip.getId() <= 0) {
            long id = CounterLocalServiceUtil.increment(VmaTransactionSlip.class
                    .getName());
            long sequenceNoValue = CounterLocalServiceUtil
                    .increment("vma.transaction.slip.sequence.no");
            vmaTransactionSlip.setSequenceNo((int) sequenceNoValue);
            vmaTransactionSlip.setId(id);
            vmaTransactionSlip.setModifiedDate(new Date());
        }

        // Code here

        String sql = "SELECT * FROM vma_transaction_function WHERE 1 = 1";
        if (Validator.isNotNull(maritimeCode)) {
            sql += " AND PortofAuthority ='" + maritimeCode + "'";
        }

        if (makePayment != null) {
            sql += " AND MakePayment =" + makePayment;
        }

        if (forArrival != null) {
            sql += " AND ForArrival =" + forArrival;
        }

        if (forDeparture != null) {
            sql += " AND ForDeparture =" + forDeparture;
        }

        if (Validator.isNotNull(purposeCode)) {
            sql += " AND (discountType1 = 0 OR ( discountType1 = 1 AND (PurposeCode ='"
                    + purposeCode + "' OR PurposeCode ='---')))";
        }
        if (Validator.isNotNull(shipTypeCode)) {
            sql += " AND (discountType2 = 0 OR ( discountType2 = 1 AND (ShipTypeCode ='"
                    + shipTypeCode + "' OR ShipTypeCode ='---')))";

        }
        if (Validator.isNotNull(portHarbourCode)) {
            sql += " AND (discountType5 = 0 OR ( discountType5 = 1 AND (PortHarbourCode ='"
                    + portHarbourCode + "' OR PortHarbourCode ='---')))";
        }

        if (anchorageHour != null) {
            sql += " AND (discountType4 = 0 OR (discountType4 = 1 AND anchorageHourFrom <= "
                    + anchorageHour
                    + ") OR  (discountType4 = 1 AND anchorageHourTo >= "
                    + anchorageHour + "))";
        }
        if (voyageNumber != null) {
            sql += " AND (discountType3 = 0 OR (discountType3 = 1 AND voyageNumFrom <= "
                    + voyageNumber + "))";
        }

        /*
         * if (discountType1 != null) { sql += " AND DiscountType1 =" +
         * discountType1;
         *
         * if (discountType1 > 0) { sql += " AND PurposeCode ='" + purposeCode +
         * "'"; } }
         *
         * if (discountType2 != null) { sql += " AND DiscountType2 =" +
         * discountType2; if (discountType2 > 0) { sql += " AND ShipTypeCode ='"
         * + shipTypeCode + "'"; } }
         *
         * if (discountType3 != null) { sql += " AND DiscountType3 =" +
         * discountType3; }
         *
         * if (discountType4 != null) { sql += " AND DiscountType4 =" +
         * discountType4; }
         *
         * if (discountType5 != null) { sql += " AND DiscountType5 =" +
         * discountType5; if (discountType5 > 0) { sql +=
         * " AND PortHarbourCode ='" + portHarbourCode + "'"; } }
         */

        // anchorageHourFrom
        // anchorageHourTo
        // dwtFrom
        // dwtTo
        // gtFrom
        // gtTo
        // SeatFrom
        // SeatTo
        // voyageNumFrom

        sql += " AND ((applied = '---') OR (applied = 'START'  AND CONCAT(DATE(appliedFrom), ' 00:00:00') <= CONCAT(DATE(NOW()), ' 00:00:00')) OR (applied = 'FINISH' AND CONCAT(DATE(appliedTo), ' 23:59:59') >= CONCAT(DATE(NOW()), ' 00:00:00')) )";

        /*
         * if (Validator.isNotNull(applied)) { if
         * (applied.equalsIgnoreCase("start")) { sql +=
         * " AND CONCAT(DATE(appliedFrom), ' 00:00:00') <= CONCAT(DATE(NOW()), ' 00:00:00')"
         * ; } else if (applied.equalsIgnoreCase("finish")) { // Co the trung dl
         * voi case tren sql +=
         * " AND CONCAT(DATE(appliedTo), ' 23:59:59') >= CONCAT(DATE(NOW()), ' 00:00:00')"
         * ; } else if (applied.equalsIgnoreCase("none")) {
         * foundTransactionFunction = false; } }
         */

        sql += " ORDER BY ModifiedDate DESC";

        List<VmaTransactionSlipFunction> transactionSlipFunctions = VmaTransactionSlipFunctionLocalServiceUtil
                .findByitineraryNo_documentaryCode(
                        vmaTransactionSlip.getItineraryNo(),
                        vmaTransactionSlip.getDocumentaryCode());

        if (transactionSlipFunctions != null) {
            for (VmaTransactionSlipFunction vmaTransactionSlipFunction : transactionSlipFunctions) {
                VmaTransactionSlipFunctionLocalServiceUtil
                        .deleteVmaTransactionSlipFunction(vmaTransactionSlipFunction);
            }
        }

        int chargeType = 0;
        double chargeRate = 0;
        double discountRate = 0;
        String functionNote = StringPool.BLANK;

        List<String> methodNames = new ArrayList<String>();

        Method[] methods = VmaTransactionSlipModelImpl.class.getMethods();

        if (methods != null && methods.length > 0) {
            for (int i = 0; i < methods.length; i++) {
                System.out
                        .println(">>>>>>>>>>>>>>>>>========================>>>>>>>>>> "
                                + methods[i].getName());
                methodNames.add(methods[i].getName());
            }
        }

        StringBuffer dynamicCondition = null;

        for (VmaTransactionType transactionType : vmaTransactionTypes) {

            double value = 0;

            String transactionTypeCode = transactionType
                    .getTransactionTypeCode();

            dynamicCondition = new StringBuffer();

            dynamicCondition.append(" AND transactionTypeCode = '"
                    + transactionTypeCode + "'");

            List<VmaTransactionFunction> vmaTransactionFunctions = VmaTransactionFunctionLocalServiceUtil
                    .findVmaTransactionFunctionByCustomSQL(sql
                            + dynamicCondition.toString(), -1, -1);
            if (vmaTransactionFunctions != null
                    && !vmaTransactionFunctions.isEmpty()) {
                VmaTransactionFunction vmaTransactionFunction = null;
                if (vmaTransactionFunctions.size() == 1) {
                    vmaTransactionFunction = vmaTransactionFunctions.get(0);
                } else {
                    boolean isDiscount = false;
                    for (VmaTransactionFunction temp : vmaTransactionFunctions) {
                        if (temp.getChargeType() == 2) {
                            isDiscount = true;
                            vmaTransactionFunction = temp;
                            break;
                        }
                    }

                    if (!isDiscount) {
                        vmaTransactionFunction = vmaTransactionFunctions.get(0);
                    }
                }

                chargeType = vmaTransactionFunction.getChargeType();
                chargeRate = vmaTransactionFunction.getChargeRate();
                discountRate = vmaTransactionFunction.getDiscountRate();
                functionNote = vmaTransactionFunction.getFunctionNote();

                VmaTransactionSlipFunction vmaTransactionSlipFunction = new VmaTransactionSlipFunction();
                vmaTransactionSlipFunction.setChargeRate(chargeRate);
                vmaTransactionSlipFunction.setChargeType(chargeType);
                vmaTransactionSlipFunction.setDiscountRate(discountRate);
                vmaTransactionSlipFunction
                        .setDocumentaryCode(vmaTransactionSlip
                                .getDocumentaryCode());
                vmaTransactionSlipFunction
                        .setFunctionCode(vmaTransactionFunction
                                .getFunctionCode());
                vmaTransactionSlipFunction.setFunctionNote(functionNote);
                vmaTransactionSlipFunction.setItineraryNo(vmaTransactionSlip
                        .getItineraryNo());
                vmaTransactionSlipFunction.setNameOfShip(vmaTransactionSlip
                        .getNameOfShip());
                vmaTransactionSlipFunction.setTransactionNote(transactionType
                        .getTransactionTypeName());
                vmaTransactionSlipFunction
                        .setTransactionTypeCode(transactionTypeCode);
                VmaTransactionSlipFunctionLocalServiceUtil
                        .addVmaTransactionSlipFunction(vmaTransactionSlipFunction);

            } else {
                value = 0;
            }

            try {

                if (chargeType == 1) {
                    value = chargeRate * conversionValue;
                } else if (chargeType == 2) {
                    value = chargeRate * conversionValue * discountRate;
                } else if (chargeType == 3) {
                    value = chargeRate;
                } else if (chargeType == 4) {
                    value = chargeRate * conversionValue
                            * anchorageDurationTime * discountRate;
                } else {

                }

                String setVndMethodName = "setF" + transactionTypeCode + "Vnd";
                String getVndMethodName = "getF" + transactionTypeCode + "Vnd";
                String setUsdMethodName = "setF" + transactionTypeCode + "Usd";
                String getUsdMethodName = "getF" + transactionTypeCode + "Usd";
                String setRemarksMethodName = "setF" + transactionTypeCode
                        + "Remarks";

                String getRemarksMethodName = "getF" + transactionTypeCode
                        + "Remarks";

                if (methodNames.contains(setVndMethodName)
                        && methodNames.contains(setUsdMethodName)
                        && methodNames.contains(setRemarksMethodName)) {

                    if (makePayment == 1) {
                        Method setVndValue = VmaTransactionSlipModelImpl.class
                                .getMethod(setVndMethodName, double.class);
                        Method getVndValue = VmaTransactionSlipModelImpl.class
                                .getMethod(getVndMethodName);
                        double oldValue = (Double) getVndValue
                                .invoke(vmaTransactionSlip);
                        if (vmaTransactionSlip.getId() > 0) {
                            setVndValue.invoke(vmaTransactionSlip, value
                                    + oldValue);
                        } else {
                            setVndValue.invoke(vmaTransactionSlip, value);
                        }

                    } else if (makePayment == 2) {
                        Method setUsdValue = VmaTransactionSlipModelImpl.class
                                .getMethod(setUsdMethodName, double.class);
                        Method getUsdValue = VmaTransactionSlipModelImpl.class
                                .getMethod(getUsdMethodName, double.class);
                        double oldValue = (Double) getUsdValue
                                .invoke(vmaTransactionSlip);
                        if (vmaTransactionSlip.getId() > 0) {
                            setUsdValue.invoke(vmaTransactionSlip, value
                                    + oldValue);
                        } else {
                            setUsdValue.invoke(vmaTransactionSlip, value);
                        }

                    }

                    Method setRemarksValue = VmaTransactionSlipModelImpl.class
                            .getMethod(setRemarksMethodName, String.class);
                    Method getRemarksValue = VmaTransactionSlipModelImpl.class
                            .getMethod(getRemarksMethodName);
                    String oldFunctionNote = (String) getRemarksValue
                            .invoke(vmaTransactionSlip);
                    if (vmaTransactionSlip.getId() > 0) {
                        setRemarksValue.invoke(vmaTransactionSlip, functionNote
                                + " " + oldFunctionNote);
                    } else {
                        setRemarksValue
                                .invoke(vmaTransactionSlip, functionNote);
                    }

                } else {
                    System.out.println("Not found method " + setVndMethodName
                            + "|" + setUsdMethodName + "|"
                            + setRemarksMethodName);

                }
            } catch (Exception e) {
                continue;
            }
        }
        // VMAUtils.formatUnicode(vmaTransactionSlip);
        vmaTransactionSlip = persistence.updateImpl(
                vmaTransactionSlip, false);

        return vmaTransactionSlip;
    }

    public JSONObject findVmaTransactionSlip(String searchQuery,
                                             String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaTransactionSlip(countQuery);

        List<VmaTransactionSlip> vmaTransactionSlips = finder
                .findVmaTransactionSlip(VmaTransactionSlip.class,
                        "VmaTransactionSlip", searchQuery, start, end);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        if (vmaTransactionSlips != null) {
            for (VmaTransactionSlip vmaTransactionSlip : vmaTransactionSlips) {
                JSONObject currentObject = VMAUtils.object2Json(vmaTransactionSlip,
                        VmaTransactionSlip.class);
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
                        String imoNumber = vmaScheduleTransfer.getImoNumber();
                        String callSign = vmaScheduleTransfer.getCallSign();
                        String registryNumber = vmaScheduleTransfer.getRegistryNumber();
                        String nameOfShip = vmaScheduleTransfer.getShipName();
                        String flagStateOfShip = vmaScheduleTransfer.getFlagStateOfShip();

                        jsonItinerary.put("nameOfShip", nameOfShip);
                        jsonItinerary.put("flagStateOfShip", flagStateOfShip);
                        jsonItinerary.put("callSign", callSign);
                        jsonItinerary.put("imoNumber", imoNumber);
                        jsonItinerary.put("registryNumber", registryNumber);
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
                data.put(currentObject);
            }
        }
        result.put("total", total);

        result.put("data", data);

        return result;

    }


    public ResponseTransactionList findVmaTransactionSlip_TichHopBienLai(String searchQuery,
                                                                         int start, int end) throws SystemException,
            JSONException {

        ResponseTransactionList objResponseTransactionList = new ResponseTransactionList();


        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = 0;

        List<VmaTransactionSlip> vmaTransactionSlips = finder
                .findVmaTransactionSlip(VmaTransactionSlip.class,
                        "VmaTransactionSlip", searchQuery, start, end);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        if (vmaTransactionSlips != null) {
            total = vmaTransactionSlips.size();

            for (VmaTransactionSlip vmaTransactionSlip : vmaTransactionSlips) {
                ResponseTransactionList.TransactionList objTrn = new ResponseTransactionList.TransactionList();
                objTrn.setTransactionCode(vmaTransactionSlip.getId()+"");
                objTrn.setStatus(vmaTransactionSlip.getPaymentStatus()+"");
                objTrn.setShipName(vmaTransactionSlip.getNameOfShip());
                objTrn.setShipOwner(vmaTransactionSlip.getShipOwnerName());
                objTrn.setCusName(vmaTransactionSlip.getShipAgencyName());
                objTrn.setShipFlag(Validator.isNotNull(vmaTransactionSlip
                        .getFlagStateOfShip()) ? DmStateLocalServiceUtil
                        .getByStateCode(vmaTransactionSlip.getFlagStateOfShip())
                        .getStateName() : ""); // Quốc tịch
                objTrn.setShipLOA(vmaTransactionSlip.getArrivalPortName()); // Cảng đến
                objTrn.setBerth(vmaTransactionSlip.getLastPortName()); // Cảng rời
                objTrn.setExtra2(vmaTransactionSlip.getNextPortName()); // Cảng đến tiếp theo


                String itineraryNo = vmaTransactionSlip.getItineraryNo();

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
                        String imoNumber = vmaScheduleTransfer.getImoNumber();
                        String callSign = vmaScheduleTransfer.getCallSign();
                        String registryNumber = vmaScheduleTransfer.getRegistryNumber();
                        String nameOfShip = vmaScheduleTransfer.getShipName();
                        String flagStateOfShip = vmaScheduleTransfer.getFlagStateOfShip();

                        objTrn.setShipName(nameOfShip);
                        try {
                            objTrn.setShipOwner(DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOwnerCode()).getCompanyName());

                        } catch(Exception e){
                        }
                        objTrn.setCusName(vmaTransactionSlip.getShipAgencyName());
                        objTrn.setShipFlag(flagStateOfShip != null ? DmStateLocalServiceUtil
                                .getByStateCode(flagStateOfShip)
                                .getStateName() : ""); // Quốc tịch
                        objTrn.setShipLOA(vmaTransactionSlip.getArrivalPortName()); // Cảng đến
                        objTrn.setBerth(vmaTransactionSlip.getLastPortName()); // Cảng rời
                        objTrn.setExtra2(vmaTransactionSlip.getNextPortName()); // Cảng đến tiếp theo


                        jsonItinerary.put("nameOfShip", nameOfShip);
                        jsonItinerary.put("flagStateOfShip", flagStateOfShip);
                        jsonItinerary.put("callSign", callSign);
                        jsonItinerary.put("imoNumber", imoNumber);
                        jsonItinerary.put("registryNumber", registryNumber);
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

                }

                objResponseTransactionList.getTransactionList().add(objTrn);
            }
        }
        result.put("total", total);

        result.put("data", data);

        objResponseTransactionList.setTotal(total+"");

        return objResponseTransactionList;

    }

    public long countVmaTransactionSlip(String sql) throws SystemException {

        return finder.countVmaTransactionSlip(sql);
    }

    public String genDocumenttaryCode(String itineraryNo, String debitnoteNumber) {
        try {
            long count = CounterLocalServiceUtil.increment("vmatransactionslip"
                    + "_" + itineraryNo + "_" + debitnoteNumber, 1);
            return debitnoteNumber + "_" + count;
        } catch (Exception e) {
            log.error(e.getMessage());
            return StringPool.BLANK;
        }
    }

    public long countDsTBP(String itineraryNo, String tugboatCode, String imo,
                           String callSign, String registryNumber, String portofAuthority,
                           String nameOfShip, String shipAgencyCode, String shipAgencyName, String shipOwnerName, String documentaryNo, String documentaryIssued, String paymentDate,String paymentStatus, String currentPaymentStatus,
                           int flag) throws SystemException {
        return finder.countDsTBP(itineraryNo, tugboatCode,
                imo, callSign, registryNumber, portofAuthority,
                nameOfShip, shipAgencyCode, shipAgencyName, shipOwnerName, documentaryNo, documentaryIssued, paymentDate, paymentStatus, currentPaymentStatus, flag);

    }

    public JSONArray findDsTBP(String itineraryNo, String tugboatCode,
                               String portofAuthority, String imoNumber, String callSign, String registryNumber,
                               String nameOfShip, String shipAgencyCode, String shipAgencyName, String shipOwnerName, String documentaryNo, String documentaryIssued, String paymentDate,String paymentStatus, String currentPaymentStatus,
                               int start, int end) throws SystemException {
        return finder.findDsTBP_DichVu(itineraryNo,
                tugboatCode, portofAuthority, imoNumber, callSign, registryNumber,
                nameOfShip, shipAgencyCode, shipAgencyName, shipOwnerName, documentaryNo, documentaryIssued, paymentDate, paymentStatus, currentPaymentStatus,
                start, end);
    }

    public List<VmaTransactionSlip> findDsTBP_ChuyenTuyen(String itineraryNo,
                                                          String portofAuthority, String imo, String callSign, String registryNumber,
                                                          String nameOfShip, String shipAgencyCode, String shipAgencyName, String shipOwnerName, String documentaryNo, String documentaryIssued, String paymentDate,String paymentStatus, String currentPaymentStatus,
                                                          int start, int end) throws SystemException {
        return finder.findDsTBP_ChuyenTuyen(itineraryNo,
                portofAuthority, imo, callSign, registryNumber,
                nameOfShip, shipAgencyCode, shipAgencyName, shipOwnerName, documentaryNo, documentaryIssued, paymentDate, paymentStatus, currentPaymentStatus,
                start, end);
    }

    public VmaTransactionSlip getByItineraryNo_Debitnoteid(String itineraryNo,
                                                           int debitnoteid) {
        try {
            return persistence
                    .fetchByitineraryNo_debitnoteid(itineraryNo, debitnoteid);
        } catch (SystemException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<VmaTransactionSlip> findByF_Debitnoteid(int debitnoteid,
                                                        int start, int end) {
        try {
            return persistence.findByF_debitnoteid(
                    debitnoteid, start, end);
        } catch (SystemException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public long countByF_Debitnoteid(int debitnoteid) {
        try {
            return persistence
                    .countByF_debitnoteid(debitnoteid);
        } catch (SystemException e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    public long countDsQLCongNo(String nameOfShip, String departmentCode,
                                String paymentName, String month, String paymentStatus,
                                int usdTotalAmount, String vrCode, int start, int end)
            throws SystemException {
        return finder.countDsQLCongNo(nameOfShip,
                departmentCode, paymentName, month, paymentStatus,
                usdTotalAmount, vrCode, start, end);
    }

    public List<VmaTransactionSlip> dsQLCongNo(String nameOfShip,
                                               String departmentCode, String paymentName, String month,
                                               String paymentStatus, int usdTotalAmount, String vrCode,
                                               String shipAgencyName, String imoNumber, String callSign,
                                               String registryNumber, String power, String documentaryIssued,
                                               String vndTotalAmount, int start, int end) throws SystemException {
        return finder.dsQLCongNo(nameOfShip, departmentCode,
                paymentName, month, paymentStatus, usdTotalAmount, vrCode,
                shipAgencyName, imoNumber, callSign, registryNumber, power,
                documentaryIssued, vndTotalAmount, start, end);
    }

    public JSONObject findThongKeBienLai81(int reportCode, String paymentName,
                                           String portofAuthority,  String createDate, String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai81(reportCode, paymentName,
                portofAuthority, createDate, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai82(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai82(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai83(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai83(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai84(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai84(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai85(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai85(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai86(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai86(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai87(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai87(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai88(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai88(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai89(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai89(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai90(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai90(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai91(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai91(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai92(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai92(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai93(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai93(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai94(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai94(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai95(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai95(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai96(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai96(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai97(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai97(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai98(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai98(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai99(String portofAuthority,  String reportUser,
                                           String reportPeriod, String reportYear, String reportMonth,
                                           String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai99(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai100(String portofAuthority,  String reportUser,
                                            String reportPeriod, String reportYear, String reportMonth,
                                            String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai100(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai101(String portofAuthority,  String reportUser,
                                            String reportPeriod, String reportYear, String reportMonth,
                                            String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai101(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai102(String portofAuthority,  String reportUser,
                                            String reportPeriod, String reportYear, String reportMonth,
                                            String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai102(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai103(String portofAuthority,  String reportUser,
                                            String reportPeriod, String reportYear, String reportMonth,
                                            String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai103(portofAuthority, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai104(String portofAuthority,  String createDate, String reportUser,
                                            String reportPeriod, String reportYear, String reportMonth,
                                            String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai104(portofAuthority, createDate, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }

    public JSONObject findThongKeBienLai105(String portofAuthority,  String createDate, String reportUser,
                                            String reportPeriod, String reportYear, String reportMonth,
                                            String dateFrom, String dateTo, int start, int end) throws SystemException {
        return finder.findThongKeBienLai105(portofAuthority, createDate, reportUser,
                reportPeriod, reportYear, reportMonth,
                dateFrom, dateTo, start, end);
    }


}

