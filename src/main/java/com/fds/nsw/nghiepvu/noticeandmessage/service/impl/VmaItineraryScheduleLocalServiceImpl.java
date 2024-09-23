package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import vn.gt.dao.danhmuc.service.*;
import vn.gt.dao.noticeandmessage.service.*;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import vn.gt.portlet.phuongtien.VMAUtils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import org.json.*;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.fds.nsw.kernel.exception.PortalException;
import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaItineraryScheduleFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaItinerarySchedulePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaItineraryScheduleLocalServiceImpl { 
@Autowired VmaItinerarySchedulePersistenceImpl persistence;
@Autowired VmaItineraryScheduleFinderImpl finder;
	public VmaItinerarySchedule createVmaItinerarySchedule(long id) {
		return persistence.create(id);
	}

	public VmaItinerarySchedule deleteVmaItinerarySchedule(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaItinerarySchedule deleteVmaItinerarySchedule(VmaItinerarySchedule VmaItinerarySchedule)
		throws SystemException {
		return persistence.remove(VmaItinerarySchedule);
	}

	public VmaItinerarySchedule fetchVmaItinerarySchedule(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaItinerarySchedule getVmaItinerarySchedule(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaItinerarySchedule> getVmaItinerarySchedules(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaItinerarySchedulesCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaItinerarySchedule updateVmaItinerarySchedule(VmaItinerarySchedule VmaItinerarySchedule,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaItinerarySchedule, merge);
	}


    public VmaItinerarySchedule addVmaItinerarySchedule(
            VmaItinerarySchedule vmaItinerarySchedule) throws SystemException {
        long id = CounterLocalServiceUtil.increment(VmaItinerarySchedule.class
                .getName());
        vmaItinerarySchedule.setId(id);
        vmaItinerarySchedule.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaItinerarySchedule);
        return persistence.updateImpl(vmaItinerarySchedule,
                false);
    }

    public VmaItinerarySchedule delete(long id) throws SystemException,
            NoSuchVmaItineraryScheduleException {
        return persistence.remove(id);
    }

    public VmaItinerarySchedule delete(VmaItinerarySchedule vmaItinerarySchedule)
            throws SystemException {
        return persistence.remove(vmaItinerarySchedule);
    }

    public VmaItinerarySchedule updateVmaItinerarySchedule(
            VmaItinerarySchedule vmaItinerarySchedule) throws SystemException {
        vmaItinerarySchedule.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaItinerarySchedule);
        return persistence.updateImpl(vmaItinerarySchedule,
                false);
    }

    public JSONObject updateVmaItinerarySchedule_VmaItinerary(
            VmaItinerarySchedule vmaItinerarySchedule, VmaItinerary vmaItinerary)
            throws SystemException, JSONException {
        // VMAUtils.formatUnicode(vmaItinerarySchedule);
        vmaItinerarySchedule = persistence.updateImpl(
                vmaItinerarySchedule, false);
        vmaItinerarySchedule.setModifiedDate(new Date());
        if ((vmaItinerary.getPayment2ArrivalStatus() == 0 || vmaItinerary
                .getPayment2ArrivalStatus() == 6)
                && vmaItinerary.getMarkedAsArrival() != 6
                && vmaItinerary.getMarkedAsArrival() > 1) {
            vmaItinerary.setPayment2ArrivalStatus(1);
        }

        if ((vmaItinerary.getPayment2DepartureStatus() == 0 || vmaItinerary
                .getPayment2DepartureStatus() == 6)
                && vmaItinerary.getMarkedAsDeparture() != 6
                && vmaItinerary.getMarkedAsDeparture() > 1) {
            vmaItinerary.setPayment2DepartureStatus(1);
        }

        if ((vmaItinerary.getPayment2ItineraryStatus() == 0 || vmaItinerary
                .getPayment2ItineraryStatus() == 6)
                && vmaItinerary.getMarkedAsArrival() != 6
                && vmaItinerary.getMarkedAsDeparture() != 6
                && vmaItinerary.getMarkedAsDeparture() > 1
                && vmaItinerary.getMarkedAsArrival() > 1
                && vmaItinerary.getArrivalShipAgencyCode().equals(
                vmaItinerary.getDepartureShipAgencyCode())) {

            vmaItinerary.setPayment2ItineraryStatus(1);

            if ((vmaItinerary.getDocumentNameIN()>0) && (vmaItinerary.getDocumentNameIN() == vmaItinerary.getDocumentNameOUT()) && (vmaItinerary.getDocumentYearIN() == vmaItinerary.getDocumentYearOUT())) {
                vmaItinerary.setPayment2ItineraryStatus(0); // Qua canh
                if ((vmaItinerary.getPayment2DepartureStatus() == 0 || vmaItinerary
                        .getPayment2DepartureStatus() == 6)
                        && vmaItinerary.getMarkedAsDeparture() != 6
                        && vmaItinerary.getMarkedAsDeparture() > 1) {
                    vmaItinerary.setPayment2DepartureStatus(1);
                    if (vmaItinerary.getPayment2ArrivalStatus() == 1 ) {
                        vmaItinerary.setPayment2ArrivalStatus(0);
                    }

                }
            }
        }

        if (vmaItinerary.getMarkedAsArrival() == 6) {
            vmaItinerary.setPayment2ArrivalStatus(0);
        }

        if (vmaItinerary.getMarkedAsDeparture() == 6) {
            vmaItinerary.setPayment2ItineraryStatus(0);
            vmaItinerary.setPayment2DepartureStatus(0);
        }
        vmaItinerary = VmaItineraryLocalServiceUtil
                .updateVmaItinerary(vmaItinerary);
        VmaShip vmaShip = null;
        try {
            vmaShip = VmaShipLocalServiceUtil.fetchByIMONumber_CallSign(
                    vmaItinerary.getImoNumber(), vmaItinerary.getCallSign());

        } catch (Exception e) {
            // nothing todo
        }

        if (vmaShip != null) {
            if (vmaItinerarySchedule.getDeconstructed() == 1) {
                vmaShip.setDeconstructed(1);
            }

            if (Validator
                    .isNotNull(vmaItinerarySchedule.getSecurityLevelCode())) {
                vmaShip.setSecurityLevelCode(vmaItinerarySchedule
                        .getSecurityLevelCode());
            }

            if (Validator.isNotNull(vmaItinerarySchedule.getShipOwnerCode())) {
                vmaShip.setShipOwnerCode(vmaItinerarySchedule
                        .getShipOwnerCode());
            }

            if (Validator.isNotNull(vmaItinerarySchedule.getShipOperatorCode())) {
                vmaShip.setShipOperatorCode(vmaItinerarySchedule
                        .getShipOperatorCode());
            }
            VmaShipLocalServiceUtil.updateVmaShip(vmaShip);
        }

        if (vmaItinerary.getDocumentNameIN() == 0
                && vmaItinerary.getDocumentYearIN() == 0
                && vmaItinerary.getDocumentNameOUT() == 0
                && vmaItinerary.getDocumentYearOUT() == 0
                && vmaItinerary.getMarkedAsArrival() != 6
                && vmaItinerary.getMarkedAsDeparture() != 6) {
            TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil
                    .fetchBymariTimeCode_itineraryNo(GetterUtil
                                    .getInteger(vmaItinerary.getMaritimeCode()),
                            vmaItinerary.getItineraryNo());

            if (tempDebitNote == null) {
                Date todate = null;
                String transactionid = StringPool.BLANK;
                long totalpayment = 0;

                String organization = StringPool.BLANK;
                Date fromdate = null;
                int paymenttype = 0;
                Date reportdate = new Date();
                String reportby = StringPool.BLANK;
                String financialaccountant = StringPool.BLANK;
                int markasdeleted = 0;
                String division = vmaItinerarySchedule.getDepartmentCode();

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                String debitnotenumber = String.valueOf(timestamp.getTime());

                String corporationid = vmaItinerary.getMaritimeCode();

                TempDebitNoteLocalServiceUtil.doTempDebitNote(0, 0,
                        corporationid, debitnotenumber, division,
                        financialaccountant, fromdate, markasdeleted,
                        organization, paymenttype, reportby, reportdate,
                        todate, totalpayment, transactionid,
                        vmaItinerary.getMaritimeCode(), "VND",
                        vmaItinerary.getItineraryNo(), "");

                // Add by TrungNT
                DmShipAgency dmShipAgency = DmShipAgencyLocalServiceUtil
                        .getByShipAgencyCode(vmaItinerary
                                .getArrivalShipAgencyCode());
                VmaTransactionBalanceLocalServiceUtil
                        .autoInitVmaTransactionBalance(
                                vmaItinerary.getMaritimeCode(),
                                vmaItinerary.getArrivalShipAgencyCode(),
                                dmShipAgency != null ? dmShipAgency
                                        .getShipAgencyNameVN()
                                        : StringPool.BLANK);

                VmaTransactionBalanceLocalServiceUtil
                        .autoInitVmaTransactionBalance(
                                vmaItinerary.getMaritimeCode(),
                                String.valueOf("9999"), "TAI KHOAN VANG LAI");
            }
        }

        JSONObject result = VMAUtils.object2Json(vmaItinerary,
                VmaItinerary.class);

        JSONObject tmp = VMAUtils.object2Json(vmaItinerarySchedule,
                VmaItinerarySchedule.class);

        result = VMAUtils.mergeJSON(result, tmp,
                VmaItinerarySchedule.class);

        return result;

    }

    public JSONObject updateVmaItinerarySchedule_VmaItinerary(
            Integer markedAsArrival, Integer markedAsDeparture,
            Integer markedAsTransmit, Integer markedAsVoyage,
            VmaItinerarySchedule vmaItinerarySchedule, VmaItinerary vmaItinerary)
            throws SystemException {

        Date date = new Date();

        vmaItinerarySchedule.setModifiedDate(date);

        if (vmaItinerary.getMarkedAsArrival() == 5
                || vmaItinerary.getMarkedAsDeparture() == 5) {
            if (Validator.isNull(vmaItinerarySchedule.getTimeOfApproval())) {
                vmaItinerarySchedule.setTimeOfApproval(new Date());
            }
        }
        // VMAUtils.formatUnicode(vmaItinerarySchedule);
        vmaItinerarySchedule = persistence.updateImpl(
                vmaItinerarySchedule, false);

        vmaItinerary.setModifiedDate(date);
        // VMAUtils.formatUnicode(vmaItinerary);

        if ((vmaItinerary.getPayment2ArrivalStatus() == 0 || vmaItinerary
                .getPayment2ArrivalStatus() == 6)
                && vmaItinerary.getMarkedAsArrival() != 6
                && vmaItinerary.getMarkedAsArrival() > 1) {
            vmaItinerary.setPayment2ArrivalStatus(1);
        }

        if ((vmaItinerary.getPayment2DepartureStatus() == 0 || vmaItinerary
                .getPayment2DepartureStatus() == 6)
                && vmaItinerary.getMarkedAsDeparture() != 6
                && vmaItinerary.getMarkedAsDeparture() > 1) {
            vmaItinerary.setPayment2DepartureStatus(1);
        }

        if ((vmaItinerary.getPayment2ItineraryStatus() == 0 || vmaItinerary
                .getPayment2ItineraryStatus() == 6)
                && vmaItinerary.getMarkedAsArrival() != 6
                && vmaItinerary.getMarkedAsDeparture() != 6
                && vmaItinerary.getMarkedAsDeparture() > 1
                && vmaItinerary.getMarkedAsArrival() > 1
                && vmaItinerary.getArrivalShipAgencyCode().equals(
                vmaItinerary.getDepartureShipAgencyCode())) {

            vmaItinerary.setPayment2ItineraryStatus(1);

            if ((vmaItinerary.getDocumentNameIN()>0) && (vmaItinerary.getDocumentNameIN() == vmaItinerary.getDocumentNameOUT()) && (vmaItinerary.getDocumentYearIN() == vmaItinerary.getDocumentYearOUT())) {
                vmaItinerary.setPayment2ItineraryStatus(0); // Qua canh
                if ((vmaItinerary.getPayment2DepartureStatus() == 0 || vmaItinerary
                        .getPayment2DepartureStatus() == 6)
                        && vmaItinerary.getMarkedAsDeparture() != 6
                        && vmaItinerary.getMarkedAsDeparture() > 1) {
                    vmaItinerary.setPayment2DepartureStatus(1);
                    if (vmaItinerary.getPayment2ArrivalStatus() == 1 ) {
                        vmaItinerary.setPayment2ArrivalStatus(0);
                    }

                }
            }
        }

        if (vmaItinerary.getMarkedAsArrival() == 6) {
            vmaItinerary.setPayment2ArrivalStatus(0);
        }

        if (vmaItinerary.getMarkedAsDeparture() == 6) {
            vmaItinerary.setPayment2ItineraryStatus(0);
            vmaItinerary.setPayment2DepartureStatus(0);
        }

        if (Validator.isNotNull(vmaItinerarySchedule
                .getTimeOfArrival()) && vmaItinerarySchedule.getNoticeShipType() == 1) {
            vmaItinerary.setTimeOfArrival(vmaItinerarySchedule.getTimeOfArrival());
        }
        if (Validator.isNotNull(vmaItinerarySchedule
                .getTimeOfDeparture()) && vmaItinerarySchedule.getNoticeShipType() == 2) {
            vmaItinerary.setTimeOfDeparture(vmaItinerarySchedule.getTimeOfDeparture());
        }

        vmaItinerary = VmaItineraryLocalServiceUtil
                .updateVmaItinerary(vmaItinerary);

        VmaScheduleAnchorage vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
                .fetchByitineraryScheduleId(vmaItinerarySchedule.getId());

        // 5 = Xac Nhan
        if (vmaItinerary.getMarkedAsArrival() == 5
                || vmaItinerary.getMarkedAsDeparture() == 5) {
            if (vmaScheduleAnchorage != null
                    && vmaScheduleAnchorage.getMakePayment() == 0
                    && vmaItinerarySchedule.getNoticeShipType() == 1) {
                // Cap nhat lai vi tri neo dau
                vmaScheduleAnchorage
                        .setAnchoringPortHarbourCode(vmaItinerarySchedule
                                .getPortHarbourCode());
                vmaScheduleAnchorage
                        .setAnchoringPortRegionCode(vmaItinerarySchedule
                                .getPortRegionCode());
                vmaScheduleAnchorage
                        .setAnchoringPortWharfCode(vmaItinerarySchedule
                                .getPortWharfCode());
                // Cap nhat lai muc dich neo dau
                if (Validator.isNotNull(vmaScheduleAnchorage.getPurposeCode()) &&
                        (vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C17")
                                || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C18")
                                || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C19")
                                || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C20")
                                || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C30")
                                || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C31") )) {
                    // Khong cap nhat lai muc dich neo dau (can bo thu tuc tu cap nhat lai)
                } else {
                    vmaScheduleAnchorage.setPurposeCode(vmaItinerarySchedule.getPurposeCode());
                    //vmaScheduleAnchorage.setPurposeSpecified(vmaItinerarySchedule.getPurposeSpecified());
                    if (Validator.isNotNull(vmaScheduleAnchorage.getPurposeCode())) {
                        DmArrivalPurpose purposeSpecified = DmArrivalPurposeLocalServiceUtil.getByPortCode(vmaScheduleAnchorage.getPurposeCode());
                        if (Validator.isNotNull(purposeSpecified)) {
                            vmaScheduleAnchorage.setPurposeSpecified(purposeSpecified.getPurposeNameVN());
                        }
                    }
                }
                if (Validator.isNotNull(vmaItinerarySchedule
                        .getTimeOfPORTArrival())) {
                    vmaScheduleAnchorage
                            .setAnchoringDateFrom(vmaItinerarySchedule
                                    .getTimeOfPORTArrival());
                }

                VmaScheduleAnchorageLocalServiceUtil
                        .updateVmaScheduleAnchorage(vmaScheduleAnchorage);
            } else if (vmaScheduleAnchorage != null
                    && vmaScheduleAnchorage.getMakePayment() == 0
                    && vmaItinerarySchedule.getNoticeShipType() == 2) {
                try {

                    // Cap nhat lai muc dich neo dau
                    if (Validator.isNotNull(vmaScheduleAnchorage.getPurposeCode()) &&
                            (vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C17")
                                    || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C18")
                                    || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C19")
                                    || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C20")
                                    || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C30")
                                    || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C31") )) {
                        // Khong cap nhat lai muc dich neo dau (can bo thu tuc tu cap nhat lai)
                    } else {
                        vmaScheduleAnchorage.setPurposeCode(vmaItinerarySchedule.getPurposeCode());
                        vmaScheduleAnchorage.setPurposeSpecified(vmaItinerarySchedule.getPurposeSpecified());
                    }

                    vmaScheduleAnchorage
                            .setAnchoringDateTo(vmaItinerarySchedule
                                    .getTimeOfDeparture());
                    double anchoringDuration = VMAUtils
                            .calculateAnchoringDuration(
                                    vmaScheduleAnchorage.getAnchoringDateFrom(),
                                    vmaScheduleAnchorage.getAnchoringDateTo());
                    vmaScheduleAnchorage
                            .setAnchoringDuration(anchoringDuration);

                    // Canh bao: Tinh phi hay khong
                    if (vmaScheduleAnchorage.getMakePayment() == 1) {
                        // object.put("Verify_MAKEPAYMENT", "Đã ghi phiếu thu");
                    } else {
                        // object.put("Verify_MAKEPAYMENT",
                        // "Chưa ghi phiếu thu");
                        String portWharfCode = vmaScheduleAnchorage
                                .getAnchoringPortWharfCode();
                        DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
                                .getByPortWharfCode(portWharfCode);
                        if (dmPortWharf != null
                                && dmPortWharf.getPortWharfPayment() == 1) {
                            // object.put("Verify_PAYMENT", "Cảng vụ tính phí");
                            vmaScheduleAnchorage
                                    .setAnchorageDomesticDuration(anchoringDuration);
                            vmaScheduleAnchorage.setAnchorageForeignDuration(Double.valueOf(0));
                            try {
                                VmaItinerarySchedule vmaItineraryScheduleIn = VmaItineraryScheduleLocalServiceUtil
                                        .findByItineraryNo_NoticeShipType(
                                                vmaItinerary.getItineraryNo(),
                                                1);
                                if (Validator.isNotNull(vmaItinerary)
                                        && (!(vmaItinerary.getFlagStateOfShip()
                                        .equalsIgnoreCase("VN")))
                                        && (vmaItinerary
                                        .getDomesticTransportCertificate() != 1)) {
                                    // Tinh phi ngoai neu Tau nuoc ngoai khong co giay phep van chuyen noi dia
                                    vmaScheduleAnchorage
                                            .setAnchorageForeignDuration(anchoringDuration);
                                    vmaScheduleAnchorage
                                            .setAnchorageDomesticDuration((double) 0);
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
                                                .setAnchorageForeignDuration(anchoringDuration);
                                        vmaScheduleAnchorage
                                                .setAnchorageDomesticDuration((double) 0);
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
                                    vmaScheduleAnchorage.setAnchorageFreeDuration(anchoringDuration);
                                    vmaScheduleAnchorage.setAnchorageDomesticDuration((double) 0);
                                    vmaScheduleAnchorage.setAnchorageForeignDuration((double) 0);

                                    if (Validator.isNotNull(vmaScheduleAnchorage.getPurposeCode())) {
                                        DmArrivalPurpose purposeSpecified = DmArrivalPurposeLocalServiceUtil.getByPortCode(vmaScheduleAnchorage.getPurposeCode());
                                        if (Validator.isNotNull(purposeSpecified)) {
                                            vmaScheduleAnchorage.setAnchorageFreeDurationRemarks(purposeSpecified.getPurposeNameVN());
                                            vmaScheduleAnchorage.setPurposeSpecified(purposeSpecified.getPurposeNameVN());
                                        }
                                    }
                                }
                            } catch (Exception e) {

                            }
                        } else {
                            // object.put("Verify_PAYMENT", "Không tính phí");
                            vmaScheduleAnchorage
                                    .setAnchorageFreeDuration(anchoringDuration);
                            vmaScheduleAnchorage.setAnchorageDomesticDuration((double)0);
                            vmaScheduleAnchorage.setAnchorageForeignDuration((double)0);

                        }
                    }
                } catch (Exception e) {
                    // nothing to do
                }

                VmaScheduleAnchorageLocalServiceUtil
                        .updateVmaScheduleAnchorage(vmaScheduleAnchorage);





                // Cap nhat lai khi xac nhan lan 2, tuong tu khi xac nhan lan dau (Voi dieu kien chua tinh phi)

                List<VmaScheduleAnchorage> vmaScheduleAnchorages = VmaScheduleAnchorageLocalServiceUtil
                        .findByItineraryNo_noticeShipType(
                                vmaItinerarySchedule.getItineraryNo(), 4);
                if (vmaScheduleAnchorages == null
                        || vmaScheduleAnchorages.size() == 0) {
                    List<VmaScheduleAnchorage> vmaScheduleAnchoragesItinerarySchedule = VmaScheduleAnchorageLocalServiceUtil
                            .findByItineraryNo_noticeShipType(
                                    vmaItinerarySchedule.getItineraryNo(), 1);

                    if (vmaScheduleAnchoragesItinerarySchedule != null
                            && vmaScheduleAnchoragesItinerarySchedule.size() > 0) {

                        VmaScheduleAnchorage vmaScheduleAnchorage_first = vmaScheduleAnchoragesItinerarySchedule
                                .get(0);

                        if (vmaScheduleAnchorage_first.getNoticeShipType() == 1) {
                            try {
                                // Canh bao: Tinh phi hay khong
                                if (vmaScheduleAnchorage_first.getMakePayment() == 1) {
                                    // object.put("Verify_MAKEPAYMENT",
                                    // "Đã ghi phiếu thu");
                                } else {
                                    // object.put("Verify_MAKEPAYMENT",
                                    // "Chưa ghi phiếu thu");
                                    vmaScheduleAnchorage_first.setAnchoringDateTo(vmaItinerarySchedule
                                            .getTimeOfDeparture());
                                    double anchoringDuration = VMAUtils
                                            .calculateAnchoringDuration(
                                                    vmaScheduleAnchorage_first
                                                            .getAnchoringDateFrom(),
                                                    vmaScheduleAnchorage_first
                                                            .getAnchoringDateTo());
                                    vmaScheduleAnchorage_first
                                            .setAnchoringDuration(anchoringDuration);
                                    String portWharfCode = vmaScheduleAnchorage_first
                                            .getAnchoringPortWharfCode();
                                    DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
                                            .getByPortWharfCode(portWharfCode);
                                    if (dmPortWharf != null
                                            && dmPortWharf
                                            .getPortWharfPayment() == 1) {
                                        // object.put("Verify_PAYMENT",
                                        // "Cảng vụ tính phí");
                                        vmaScheduleAnchorage_first
                                                .setAnchorageDomesticDuration(anchoringDuration);
                                        vmaScheduleAnchorage_first
                                                .setAnchorageForeignDuration((double)0);
                                        try {
                                            VmaItinerarySchedule vmaItineraryScheduleIn = VmaItineraryScheduleLocalServiceUtil
                                                    .findByItineraryNo_NoticeShipType(
                                                            vmaItinerary
                                                                    .getItineraryNo(),
                                                            1);
                                            if (Validator
                                                    .isNotNull(vmaItinerary)
                                                    && (!(vmaItinerary
                                                    .getFlagStateOfShip()
                                                    .equalsIgnoreCase("VN")))
                                                    && (vmaItinerary
                                                    .getDomesticTransportCertificate() != 1)) {

                                                vmaScheduleAnchorage_first
                                                        .setAnchorageForeignDuration(anchoringDuration);
                                                vmaScheduleAnchorage_first
                                                        .setAnchorageDomesticDuration((double)0);
                                            } else if (Validator
                                                    .isNotNull(vmaItineraryScheduleIn)
                                                    && vmaItineraryScheduleIn
                                                    .getLastPortOfCallCode()
                                                    .length() > 0) {

                                                // Tinh phi ngoai neu Cang roi
                                                // cuoi cung la cang quoc te
                                                DmPort objArrivalPort = DmPortLocalServiceUtil
                                                        .getByPortCode(vmaItineraryScheduleIn
                                                                .getLastPortOfCallCode());
                                                if (	((Validator.isNotNull(objArrivalPort))
                                                        && objArrivalPort
                                                        .getStateCode()
                                                        .length() > 0
                                                        && (!(objArrivalPort
                                                        .getStateCode()
                                                        .equalsIgnoreCase("VN"))))
                                                        || ((!(vmaItineraryScheduleIn.getLastPortOfCallCode().substring(0, 2).equalsIgnoreCase("VN"))) && Validator.isNull(objArrivalPort)) ){
                                                    // Tinh phi ngoai neu Cang
                                                    // roi cuoi cung la cang
                                                    // quoc te
                                                    vmaScheduleAnchorage_first
                                                            .setAnchorageForeignDuration(anchoringDuration);
                                                    vmaScheduleAnchorage_first
                                                            .setAnchorageDomesticDuration((double)0);
                                                }
                                            }



                                            if (Validator.isNotNull(vmaScheduleAnchorage_first.getPurposeCode()) &&
                                                    (vmaScheduleAnchorage_first.getPurposeCode().equalsIgnoreCase("C17")
                                                            || vmaScheduleAnchorage_first.getPurposeCode().equalsIgnoreCase("C18")
                                                            || vmaScheduleAnchorage_first.getPurposeCode().equalsIgnoreCase("C19")
                                                            || vmaScheduleAnchorage_first.getPurposeCode().equalsIgnoreCase("C20")
                                                            || vmaScheduleAnchorage_first.getPurposeCode().equalsIgnoreCase("C30")
                                                            || vmaScheduleAnchorage_first.getPurposeCode().equalsIgnoreCase("C31") )) {
                                                // Các trường hợp quy định Không phải nộp phí neo đậu cho tàu thuyền
                                                vmaScheduleAnchorage_first.setAnchorageFreeDuration(anchoringDuration);
                                                vmaScheduleAnchorage_first.setAnchorageDomesticDuration((double)0);
                                                vmaScheduleAnchorage_first.setAnchorageForeignDuration((double)0);

                                                if (Validator.isNotNull(vmaScheduleAnchorage_first.getPurposeCode())) {
                                                    DmArrivalPurpose purposeSpecified = DmArrivalPurposeLocalServiceUtil.getByPortCode(vmaScheduleAnchorage_first.getPurposeCode());
                                                    if (Validator.isNotNull(purposeSpecified)) {
                                                        vmaScheduleAnchorage_first.setAnchorageFreeDurationRemarks(purposeSpecified.getPurposeNameVN());
                                                        vmaScheduleAnchorage_first.setPurposeSpecified(purposeSpecified.getPurposeNameVN());
                                                    }
                                                }
                                            }
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        // object.put("Verify_PAYMENT",
                                        // "Không tính phí");
                                        vmaScheduleAnchorage_first
                                                .setAnchorageFreeDuration(anchoringDuration);
                                        vmaScheduleAnchorage_first.setAnchorageDomesticDuration((double)0);
                                        vmaScheduleAnchorage_first.setAnchorageForeignDuration((double)0);
                                    }
                                }
                            } catch (Exception e) {
                                // nothing to do
                            }

                            VmaScheduleAnchorageLocalServiceUtil
                                    .updateVmaScheduleAnchorage(vmaScheduleAnchorage_first);
                        }
                    }
                } else if (vmaScheduleAnchorages != null
                        && vmaScheduleAnchorages.size() > 0) {
                    VmaScheduleAnchorage vmaScheduleAnchorage_shifting = vmaScheduleAnchorages
                            .get(vmaScheduleAnchorages.size() - 1);
                    if (vmaScheduleAnchorage_shifting.getMakePayment() == 1) {
                        // object.put("Verify_MAKEPAYMENT",
                        // "Đã ghi phiếu thu");
                    } else {
                        vmaScheduleAnchorage_shifting
                                .setAnchoringDateTo(vmaItinerarySchedule
                                        .getTimeOfDeparture());

                        vmaScheduleAnchorage_shifting
                                .setAnchorageFreeDuration((double)0);
                        vmaScheduleAnchorage_shifting.setAnchorageDomesticDuration((double)0);
                        vmaScheduleAnchorage_shifting.setAnchorageForeignDuration((double)0);


                        double anchoringDuration = VMAUtils
                                .calculateAnchoringDuration(
                                        vmaScheduleAnchorage_shifting.getAnchoringDateFrom(),
                                        vmaScheduleAnchorage_shifting.getAnchoringDateTo());
                        vmaScheduleAnchorage_shifting
                                .setAnchoringDuration(anchoringDuration);

                        String portWharfCode = vmaScheduleAnchorage_shifting
                                .getAnchoringPortWharfCode();
                        DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
                                .getByPortWharfCode(portWharfCode);
                        if (dmPortWharf != null
                                && dmPortWharf
                                .getPortWharfPayment() == 1) {
                            // object.put("Verify_PAYMENT",
                            // "Cảng vụ tính phí");
                            vmaScheduleAnchorage_shifting
                                    .setAnchorageDomesticDuration(anchoringDuration);
                            vmaScheduleAnchorage_shifting
                                    .setAnchorageForeignDuration((double)0);
                            try {
                                VmaItinerarySchedule vmaItineraryScheduleIn = VmaItineraryScheduleLocalServiceUtil
                                        .findByItineraryNo_NoticeShipType(
                                                vmaItinerary
                                                        .getItineraryNo(),
                                                1);
                                if (Validator
                                        .isNotNull(vmaItinerary)
                                        && (!(vmaItinerary
                                        .getFlagStateOfShip()
                                        .equalsIgnoreCase("VN")))
                                        && (vmaItinerary
                                        .getDomesticTransportCertificate() != 1)) {

                                    vmaScheduleAnchorage_shifting
                                            .setAnchorageForeignDuration(anchoringDuration);
                                    vmaScheduleAnchorage_shifting
                                            .setAnchorageDomesticDuration((double)0);
                                } else if (Validator
                                        .isNotNull(vmaItineraryScheduleIn)
                                        && vmaItineraryScheduleIn
                                        .getLastPortOfCallCode()
                                        .length() > 0) {

                                    // Tinh phi ngoai neu Cang roi
                                    // cuoi cung la cang quoc te
                                    DmPort objArrivalPort = DmPortLocalServiceUtil
                                            .getByPortCode(vmaItineraryScheduleIn
                                                    .getLastPortOfCallCode());
                                    if (	((Validator.isNotNull(objArrivalPort))
                                            && objArrivalPort
                                            .getStateCode()
                                            .length() > 0
                                            && (!(objArrivalPort
                                            .getStateCode()
                                            .equalsIgnoreCase("VN"))))
                                            || ((!(vmaItineraryScheduleIn.getLastPortOfCallCode().substring(0, 2).equalsIgnoreCase("VN"))) && Validator.isNull(objArrivalPort)) ){
                                        // Tinh phi ngoai neu Cang
                                        // roi cuoi cung la cang
                                        // quoc te
                                        vmaScheduleAnchorage_shifting
                                                .setAnchorageForeignDuration(anchoringDuration);
                                        vmaScheduleAnchorage_shifting
                                                .setAnchorageDomesticDuration((double)0);
                                    }
                                }

                                if (Validator.isNotNull(vmaScheduleAnchorage_shifting.getPurposeCode()) &&
                                        (vmaScheduleAnchorage_shifting.getPurposeCode().equalsIgnoreCase("C17")
                                                || vmaScheduleAnchorage_shifting.getPurposeCode().equalsIgnoreCase("C18")
                                                || vmaScheduleAnchorage_shifting.getPurposeCode().equalsIgnoreCase("C19")
                                                || vmaScheduleAnchorage_shifting.getPurposeCode().equalsIgnoreCase("C20")
                                                || vmaScheduleAnchorage_shifting.getPurposeCode().equalsIgnoreCase("C30")
                                                || vmaScheduleAnchorage_shifting.getPurposeCode().equalsIgnoreCase("C31") )) {
                                    // Các trường hợp quy định Không phải nộp phí neo đậu cho tàu thuyền
                                    vmaScheduleAnchorage_shifting.setAnchorageFreeDuration(anchoringDuration);
                                    vmaScheduleAnchorage_shifting.setAnchorageDomesticDuration((double)0);
                                    vmaScheduleAnchorage_shifting.setAnchorageForeignDuration((double)0);

                                    if (Validator.isNotNull(vmaScheduleAnchorage_shifting.getPurposeCode())) {
                                        DmArrivalPurpose purposeSpecified = DmArrivalPurposeLocalServiceUtil.getByPortCode(vmaScheduleAnchorage_shifting.getPurposeCode());
                                        if (Validator.isNotNull(purposeSpecified)) {
                                            vmaScheduleAnchorage_shifting.setAnchorageFreeDurationRemarks(purposeSpecified.getPurposeNameVN());
                                            vmaScheduleAnchorage_shifting.setPurposeSpecified(purposeSpecified.getPurposeNameVN());
                                        }
                                    }
                                }
                            } catch (Exception e) {

                            }
                        } else {
                            // object.put("Verify_PAYMENT",
                            // "Không tính phí");
                            vmaScheduleAnchorage_shifting
                                    .setAnchorageFreeDuration(anchoringDuration);
                            vmaScheduleAnchorage_shifting.setAnchorageDomesticDuration((double)0);
                            vmaScheduleAnchorage_shifting.setAnchorageForeignDuration((double)0);
                        }

                        VmaScheduleAnchorageLocalServiceUtil
                                .updateVmaScheduleAnchorage(vmaScheduleAnchorage_shifting);
                    }

                }

            } else if (vmaScheduleAnchorage == null
                    && vmaItinerarySchedule.getNoticeShipType() == 1) {

                vmaScheduleAnchorage = VMAUtils.updateVmaScheduleAnchorage(
                        null, vmaItinerary, vmaItinerarySchedule);

                if (Validator.isNull(vmaScheduleAnchorage
                        .getAnchoringDateFrom())) {
                    vmaScheduleAnchorage
                            .setAnchoringDateFrom(vmaItinerarySchedule
                                    .getTimeOfArrival());
                }


                // Cap nhat lai muc dich neo dau
                if (Validator.isNotNull(vmaScheduleAnchorage.getPurposeCode()) &&
                        (vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C17")
                                || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C18")
                                || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C19")
                                || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C20")
                                || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C30")
                                || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C31") )) {
                    // Khong cap nhat lai muc dich neo dau (can bo thu tuc tu cap nhat lai)
                } else {
                    vmaScheduleAnchorage.setPurposeCode(vmaItinerarySchedule.getPurposeCode());
                    vmaScheduleAnchorage.setPurposeSpecified(vmaItinerarySchedule.getPurposeSpecified());
                }

                VmaScheduleAnchorageLocalServiceUtil
                        .addVmaScheduleAnchorage(vmaScheduleAnchorage);
            }
            // Edit by Dungnv
            else if (vmaScheduleAnchorage == null
                    && vmaItinerarySchedule.getNoticeShipType() == 2) {

                // ---- Them luot roi trong vi tri neo dau -----
                vmaScheduleAnchorage = VMAUtils.updateVmaScheduleAnchorage(
                        vmaScheduleAnchorage, vmaItinerary,
                        vmaItinerarySchedule);
                try {
                    // Cap nhat lai muc dich neo dau
                    if (Validator.isNotNull(vmaScheduleAnchorage.getPurposeCode()) &&
                            (vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C17")
                                    || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C18")
                                    || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C19")
                                    || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C20")
                                    || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C30")
                                    || vmaScheduleAnchorage.getPurposeCode().equalsIgnoreCase("C31") )) {
                        // Khong cap nhat lai muc dich neo dau (can bo thu tuc tu cap nhat lai)
                    } else {
                        vmaScheduleAnchorage.setPurposeCode(vmaItinerarySchedule.getPurposeCode());
                        //vmaScheduleAnchorage.setPurposeSpecified(vmaItinerarySchedule.getPurposeSpecified());
                        if (Validator.isNotNull(vmaScheduleAnchorage.getPurposeCode())) {
                            DmArrivalPurpose purposeSpecified = DmArrivalPurposeLocalServiceUtil.getByPortCode(vmaScheduleAnchorage.getPurposeCode());
                            if (Validator.isNotNull(purposeSpecified)) {
                                vmaScheduleAnchorage.setPurposeSpecified(purposeSpecified.getPurposeNameVN());
                            }
                        }
                    }

                    vmaScheduleAnchorage
                            .setAnchoringDateTo(vmaItinerarySchedule
                                    .getTimeOfDeparture());
                    double anchoringDuration = VMAUtils
                            .calculateAnchoringDuration(
                                    vmaScheduleAnchorage.getAnchoringDateFrom(),
                                    vmaScheduleAnchorage.getAnchoringDateTo());
                    vmaScheduleAnchorage
                            .setAnchoringDuration(anchoringDuration);



                } catch (Exception e) {
                    // nothing to do
                }

                VmaScheduleAnchorageLocalServiceUtil
                        .addVmaScheduleAnchorage(vmaScheduleAnchorage);
                // -----------------------------------------------

                List<VmaScheduleAnchorage> vmaScheduleAnchorages = VmaScheduleAnchorageLocalServiceUtil
                        .findByItineraryNo_noticeShipType(
                                vmaItinerarySchedule.getItineraryNo(), 4);
                if (vmaScheduleAnchorages == null
                        || vmaScheduleAnchorages.size() == 0) {
                    List<VmaScheduleAnchorage> vmaScheduleAnchoragesItinerarySchedule = VmaScheduleAnchorageLocalServiceUtil
                            .findByItineraryNo_noticeShipType(
                                    vmaItinerarySchedule.getItineraryNo(), 1);

                    if (vmaScheduleAnchoragesItinerarySchedule != null
                            && vmaScheduleAnchoragesItinerarySchedule.size() > 0) {

                        VmaScheduleAnchorage vmaScheduleAnchorage_first = vmaScheduleAnchoragesItinerarySchedule
                                .get(0);

                        if (vmaScheduleAnchorage_first.getNoticeShipType() == 1) {

                            try {


                                // Canh bao: Tinh phi hay khong
                                if (vmaScheduleAnchorage_first.getMakePayment() == 1) {
                                    // object.put("Verify_MAKEPAYMENT",
                                    // "Đã ghi phiếu thu");
                                } else {
                                    vmaScheduleAnchorage_first
                                            .setAnchoringDateTo(vmaItinerarySchedule
                                                    .getTimeOfDeparture());

                                    double anchoringDuration = VMAUtils
                                            .calculateAnchoringDuration(
                                                    vmaScheduleAnchorage_first
                                                            .getAnchoringDateFrom(),
                                                    vmaScheduleAnchorage_first
                                                            .getAnchoringDateTo());

                                    vmaScheduleAnchorage_first
                                            .setAnchoringDuration(anchoringDuration);

                                    // object.put("Verify_MAKEPAYMENT",
                                    // "Chưa ghi phiếu thu");
                                    String portWharfCode = vmaScheduleAnchorage_first
                                            .getAnchoringPortWharfCode();
                                    DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
                                            .getByPortWharfCode(portWharfCode);
                                    if (dmPortWharf != null
                                            && dmPortWharf
                                            .getPortWharfPayment() == 1) {
                                        // object.put("Verify_PAYMENT",
                                        // "Cảng vụ tính phí");
                                        vmaScheduleAnchorage_first
                                                .setAnchorageDomesticDuration(anchoringDuration);
                                        vmaScheduleAnchorage_first
                                                .setAnchorageForeignDuration((double)0);
                                        try {
                                            VmaItinerarySchedule vmaItineraryScheduleIn = VmaItineraryScheduleLocalServiceUtil
                                                    .findByItineraryNo_NoticeShipType(
                                                            vmaItinerary
                                                                    .getItineraryNo(),
                                                            1);
                                            if (Validator
                                                    .isNotNull(vmaItinerary)
                                                    && (!(vmaItinerary
                                                    .getFlagStateOfShip()
                                                    .equalsIgnoreCase("VN")))
                                                    && (vmaItinerary
                                                    .getDomesticTransportCertificate() != 1)) {

                                                vmaScheduleAnchorage_first
                                                        .setAnchorageForeignDuration(anchoringDuration);
                                                vmaScheduleAnchorage_first
                                                        .setAnchorageDomesticDuration((double)0);
                                            } else if (Validator
                                                    .isNotNull(vmaItineraryScheduleIn)
                                                    && vmaItineraryScheduleIn
                                                    .getLastPortOfCallCode()
                                                    .length() > 0) {

                                                // Tinh phi ngoai neu Cang roi
                                                // cuoi cung la cang quoc te
                                                DmPort objArrivalPort = DmPortLocalServiceUtil
                                                        .getByPortCode(vmaItineraryScheduleIn
                                                                .getLastPortOfCallCode());
                                                if (	((Validator.isNotNull(objArrivalPort))
                                                        && objArrivalPort
                                                        .getStateCode()
                                                        .length() > 0
                                                        && (!(objArrivalPort
                                                        .getStateCode()
                                                        .equalsIgnoreCase("VN"))))
                                                        || ((!(vmaItineraryScheduleIn.getLastPortOfCallCode().substring(0, 2).equalsIgnoreCase("VN"))) && Validator.isNull(objArrivalPort)) ){
                                                    // Tinh phi ngoai neu Cang
                                                    // roi cuoi cung la cang
                                                    // quoc te
                                                    vmaScheduleAnchorage_first
                                                            .setAnchorageForeignDuration(anchoringDuration);
                                                    vmaScheduleAnchorage_first
                                                            .setAnchorageDomesticDuration((double)0);
                                                }
                                            }



                                            if (Validator.isNotNull(vmaScheduleAnchorage_first.getPurposeCode()) &&
                                                    (vmaScheduleAnchorage_first.getPurposeCode().equalsIgnoreCase("C17")
                                                            || vmaScheduleAnchorage_first.getPurposeCode().equalsIgnoreCase("C18")
                                                            || vmaScheduleAnchorage_first.getPurposeCode().equalsIgnoreCase("C19")
                                                            || vmaScheduleAnchorage_first.getPurposeCode().equalsIgnoreCase("C20")
                                                            || vmaScheduleAnchorage_first.getPurposeCode().equalsIgnoreCase("C30")
                                                            || vmaScheduleAnchorage_first.getPurposeCode().equalsIgnoreCase("C31") )) {
                                                // Các trường hợp quy định Không phải nộp phí neo đậu cho tàu thuyền
                                                vmaScheduleAnchorage_first.setAnchorageFreeDuration(anchoringDuration);
                                                vmaScheduleAnchorage_first.setAnchorageDomesticDuration((double)0);
                                                vmaScheduleAnchorage_first.setAnchorageForeignDuration((double)0);

                                                if (Validator.isNotNull(vmaScheduleAnchorage_first.getPurposeCode())) {
                                                    DmArrivalPurpose purposeSpecified = DmArrivalPurposeLocalServiceUtil.getByPortCode(vmaScheduleAnchorage_first.getPurposeCode());
                                                    if (Validator.isNotNull(purposeSpecified)) {
                                                        vmaScheduleAnchorage_first.setAnchorageFreeDurationRemarks(purposeSpecified.getPurposeNameVN());
                                                        vmaScheduleAnchorage_first.setPurposeSpecified(purposeSpecified.getPurposeNameVN());
                                                    }
                                                }
                                            }
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        // object.put("Verify_PAYMENT",
                                        // "Không tính phí");
                                        vmaScheduleAnchorage_first
                                                .setAnchorageFreeDuration(anchoringDuration);
                                        vmaScheduleAnchorage_first.setAnchorageDomesticDuration((double)0);
                                        vmaScheduleAnchorage_first.setAnchorageForeignDuration((double)0);
                                    }
                                }
                            } catch (Exception e) {
                                // nothing to do
                            }

                            VmaScheduleAnchorageLocalServiceUtil
                                    .updateVmaScheduleAnchorage(vmaScheduleAnchorage_first);
                        }
                    }
                } else if (vmaScheduleAnchorages != null
                        && vmaScheduleAnchorages.size() > 0) {
                    VmaScheduleAnchorage vmaScheduleAnchorage_shifting = vmaScheduleAnchorages
                            .get(vmaScheduleAnchorages.size() - 1);

                    if (vmaScheduleAnchorage_shifting.getMakePayment() == 1) {
                        // object.put("Verify_MAKEPAYMENT",
                        // "Đã ghi phiếu thu");
                    } else {
                        vmaScheduleAnchorage_shifting
                                .setAnchoringDateTo(vmaItinerarySchedule
                                        .getTimeOfDeparture());

                        vmaScheduleAnchorage_shifting
                                .setAnchoringDateFrom(null);
                        vmaScheduleAnchorage_shifting.setAnchorageFreeDuration((double)0);
                        vmaScheduleAnchorage_shifting.setAnchorageDomesticDuration((double)0);
                        vmaScheduleAnchorage_shifting.setAnchorageForeignDuration((double)0);


                        VmaScheduleAnchorageLocalServiceUtil
                                .updateVmaScheduleAnchorage(vmaScheduleAnchorage_shifting);
                    }
                }
            }

        }

        // Ho so thu cong phai lam theo quy trinh cua ho so dien tu ve thong bao
        // tinh phi
        /*
         * if (vmaItinerary.getDocumentNameIN() == 0 &&
         * vmaItinerary.getDocumentYearIN() == 0 &&
         * vmaItinerary.getDocumentNameOUT() == 0 &&
         * vmaItinerary.getDocumentYearOUT() == 0 &&
         * vmaItinerary.getMarkedAsArrival() != 6 &&
         * vmaItinerary.getMarkedAsDeparture() != 6) { TempDebitnote
         * tempDebitNote = TempDebitNoteLocalServiceUtil
         * .fetchBymariTimeCode_itineraryNo(GetterUtil
         * .getInteger(vmaItinerary.getMaritimeCode()),
         * vmaItinerary.getItineraryNo());
         *
         * if (tempDebitNote == null) { Date todate = null; String transactionid
         * = StringPool.BLANK; long totalpayment = 0;
         *
         * String organization = StringPool.BLANK; Date fromdate = null; int
         * paymenttype = 0; Date reportdate = new Date(); String reportby =
         * StringPool.BLANK; String financialaccountant = StringPool.BLANK; int
         * markasdeleted = 0; String division =
         * vmaItinerarySchedule.getDepartmentCode();
         *
         * Timestamp timestamp = new Timestamp(System.currentTimeMillis());
         *
         * String debitnotenumber = String.valueOf(timestamp.getTime());
         *
         * String corporationid = vmaItinerary.getMaritimeCode();
         *
         * TempDebitNoteLocalServiceUtil.doTempDebitNote(0, 0, corporationid,
         * debitnotenumber, division, financialaccountant, fromdate,
         * markasdeleted, organization, paymenttype, reportby, reportdate,
         * todate, totalpayment, transactionid, vmaItinerary.getMaritimeCode(),
         * "VND", vmaItinerary.getItineraryNo(), "");
         *
         * // Add by TrungNT DmShipAgency dmShipAgency =
         * DmShipAgencyLocalServiceUtil .getByShipAgencyCode(vmaItinerary
         * .getArrivalShipAgencyCode()); VmaTransactionBalanceLocalServiceUtil
         * .autoInitVmaTransactionBalance( vmaItinerary.getMaritimeCode(),
         * vmaItinerary.getArrivalShipAgencyCode(), dmShipAgency != null ?
         * dmShipAgency .getShipAgencyNameVN() : StringPool.BLANK);
         *
         * VmaTransactionBalanceLocalServiceUtil .autoInitVmaTransactionBalance(
         * vmaItinerary.getMaritimeCode(), String.valueOf("9999"),
         * "TAI KHOAN VANG LAI"); } }
         */

        JSONObject result = JSONFactoryUtil.createJSONObject();
        try {
            result = VMAUtils.object2Json(vmaItinerary,
                    VmaItinerary.class);
        } catch (JSONException e) {
            log.info("====> Khong convert duoc object sang json");
            log.error(e.getMessage());
        }

        JSONObject tmp = JSONFactoryUtil.createJSONObject();
        try {
            tmp = VMAUtils.object2Json(vmaItinerarySchedule,
                    VmaItinerarySchedule.class);
        } catch (JSONException e) {
            log.info("====> Khong convert duoc object sang json");
            log.error(e.getMessage());
        }

        result = VMAUtils.mergeJSON(result, tmp,
                VmaItinerarySchedule.class);

        return result;

    }

    public List<VmaItinerarySchedule> findByItineraryNo(String itineraryNo)
            throws SystemException {
        try {
            return persistence
                    .findByitineraryNo(itineraryNo);
        } catch (Exception e) {
            log.info("====> Khong co ke hoach, khong convert duoc object sang json");
            return null;
        }
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public VmaItinerarySchedule findByItineraryNo_NoticeShipType(
            String itineraryNo, int noticeShipType) {
        try {
            return persistence
                    .findByitineraryNo_noticeShipType(itineraryNo,
                            noticeShipType);
        } catch (Exception e) {
            log.info("====> Khong co ke hoach, khong convert duoc object sang json");
            return null;
        }
    }

    public int countByItineraryNo_NoticeShipType(String itineraryNo,
                                                 int noticeShipType) throws SystemException {
        return persistence
                .countByitineraryNo_noticeShipType(itineraryNo, noticeShipType);
    }

    public JSONObject findVmaItinerary_VmaItinerarySchedule(String itineraryNo,
                                                            String maritimeCode, String shipPosition, String markedAsArrival,
                                                            String markedAsDeparture, String noticeShipType, String shipBoat,
                                                            String portRegionCode, String portHarbourCode, String portWharfCode,
                                                            String cargoType, String vrCode, String routeLevelCode, String chanelCodeList,
                                                            String timeOfArrival, String timeOfDeparture, String nameOfShip,
                                                            String flagStateOfShip, String callSign, String registryNumber,
                                                            double gt, double dwt, double loa, double maxDraft, double shownDraftxF, double shownDraftxA,
                                                            int start, int end) throws SystemException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaItinerary_VmaItinerarySchedule(itineraryNo,
                        maritimeCode, shipPosition, markedAsArrival,
                        markedAsDeparture, noticeShipType, shipBoat,
                        portRegionCode, portHarbourCode, portWharfCode,
                        cargoType, vrCode, routeLevelCode, chanelCodeList,
                        timeOfArrival, timeOfDeparture, nameOfShip,
                        flagStateOfShip, callSign, registryNumber,
                        gt, dwt, loa, maxDraft, shownDraftxF, shownDraftxA);

        JSONArray data = finder
                .findVmaItinerary_VmaItinerarySchedule(itineraryNo,
                        maritimeCode, shipPosition, markedAsArrival,
                        markedAsDeparture, noticeShipType, shipBoat,
                        portRegionCode, portHarbourCode, portWharfCode,
                        cargoType, vrCode, routeLevelCode, chanelCodeList,
                        timeOfArrival, timeOfDeparture, nameOfShip,
                        flagStateOfShip, callSign, registryNumber,
                        gt, dwt, loa, maxDraft, shownDraftxF, shownDraftxA,
                        start, end);

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaItinerary_VmaItinerarySchedule(String itineraryNo,
                                                       String maritimeCode, String shipPosition, String markedAsArrival,
                                                       String markedAsDeparture, String noticeShipType, String shipBoat,
                                                       String portRegionCode, String portHarbourCode, String portWharfCode,
                                                       String cargoType, String vrCode, String routeLevelCode, String chanelCodeList,
                                                       String timeOfArrival, String timeOfDeparture, String nameOfShip,
                                                       String flagStateOfShip, String callSign, String registryNumber,
                                                       double gt, double dwt, double loa,
                                                       double maxDraft, double shownDraftxF, double shownDraftxA) throws SystemException {

        // JSONObject result = JSONFactoryUtil.createJSONObject();

        return finder
                .countVmaItinerary_VmaItinerarySchedule(itineraryNo,
                        maritimeCode, shipPosition, markedAsArrival,
                        markedAsDeparture, noticeShipType, shipBoat,
                        portRegionCode, portHarbourCode, portWharfCode,
                        cargoType, vrCode, routeLevelCode, chanelCodeList,
                        timeOfArrival, timeOfDeparture, nameOfShip,
                        flagStateOfShip, callSign, registryNumber,
                        gt, dwt, loa, maxDraft, shownDraftxF, shownDraftxA);
    }

    public JSONArray findVmaItinerary_VmaItinerarySchedule_VmaScheduleShifting(
            String itineraryNo, String maritimeCode, String shipPosition,
            String markedAsArrival, String markedAsDeparture,
            String noticeShipType, String shipBoat, String timeOfArrival,
            String portRegionCode, String certificateNo, String requestState,
            boolean export, String fromDate, String toDate, int start, int end)
            throws SystemException {
        return finder
                .findVmaItinerary_VmaItinerarySchedule_VmaScheduleShifting(
                        itineraryNo, maritimeCode, shipPosition,
                        markedAsArrival, markedAsDeparture, noticeShipType,
                        shipBoat, timeOfArrival, portRegionCode, certificateNo,
                        requestState, export, fromDate, toDate, start, end);
    }

    public List<VmaItinerarySchedule> findVmaItinerarySchedule(
            String searchQuery, int start, int end) throws JSONException,
            SystemException {
        return finder.findVmaItinerarySchedule(
                VmaItinerarySchedule.class, "VmaItinerarySchedule",
                searchQuery, start, end);
    }

    public JSONObject findVmaItinerarySchedule(String searchQuery,
                                               String countQuery, int start, int end) throws JSONException,
            SystemException {

        List<VmaItinerarySchedule> vmaItinerarySchedules = finder
                .findVmaItinerarySchedule(VmaItinerarySchedule.class,
                        "VmaItinerarySchedule", searchQuery, start, end);

        long count = finder
                .countVmaItinerarySchedule(countQuery);

        JSONObject result = JSONFactoryUtil.createJSONObject();

        JSONArray data = JSONFactoryUtil.createJSONArray();

        if (vmaItinerarySchedules != null) {

            for (VmaItinerarySchedule vmaItinerarySchedule : vmaItinerarySchedules) {
                JSONObject object = VMAUtils.object2Json(vmaItinerarySchedule,
                        VmaItinerarySchedule.class);
                data.put(object);
            }

        }

        result.put("total", count);

        result.put("data", data);

        return result;
    }

    public VmaItinerarySchedule findByDocumentName_DocumentYear_NoticeShipType(
            long documentName, int documentYear, int noticeShipType) {
        try {
            return persistence
                    .fetchBydocumentName_documentYear_noticeShipType(
                            documentName, documentYear, noticeShipType);
        } catch (Exception e) {
            // nothing to do
        }
        return null;
    }

    public VmaItinerarySchedule findByitineraryNo_certificateNo_noticeShipType(
            java.lang.String itineraryNo, int noticeShipType,
            java.lang.String certificateNo) throws SystemException,
            NoSuchVmaItineraryScheduleException {
        try {
            return persistence
                    .fetchByitineraryNo_certificateNo_noticeShipType(itineraryNo,
                            noticeShipType, certificateNo);
        } catch (Exception e) {
            // nothing to do
        }
        return null;
    }

    public List<VmaItinerarySchedule> findByDocumentName_DocumentYear(
            long documentName, int documentYear) {
        try {
            return persistence
                    .findBydocumentName_documentYear(documentName, documentYear);
        } catch (Exception e) {
            // nothing to do
        }
        return null;
    }

    public String genCertificateNo(long documentName, long documentYear,
                                   String maritimeCode, String itineraryNo) {
        try {
            long count = CounterLocalServiceUtil.increment(itineraryNo
                    + "_vmaitineraryschedule_" + documentName, 1);
            log.info("=====================>>>>>>>>>>>>>> genCertificateNo -> maritimeCode "
                    + maritimeCode);
            DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
                    .getByMaritimeCode(maritimeCode);
            return documentName + "_" + count + "/" + documentYear
                    + (dmMaritime != null ? dmMaritime.getShortName() : "");
        } catch (Exception e) {
            log.error(e.getMessage());
            return StringPool.BLANK;
        }
    }

    public String genDocumentNameVoy(String maritimeCode) {
        try {
            Date now = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            int year = calendar.get(Calendar.YEAR);
            if (Validator.isNull(maritimeCode)) {
                return StringPool.BLANK;
            }
            if (maritimeCode.length() == 1) {
                maritimeCode = "0" + maritimeCode;
            }
            long count = CounterLocalServiceUtil.increment(year + "_"
                    + maritimeCode);
            String strCount = String.valueOf(count);
            if (strCount.length() < 6) {
                String tmp = "";
                for (int i = 0; i < 6 - strCount.length(); i++) {
                    tmp += "0";
                }

                strCount = tmp + strCount;
            }
            return year + maritimeCode + strCount;
        } catch (Exception e) {
            log.error(e.getMessage());
            return StringPool.BLANK;
        }
    }

    public JSONObject findSoLuotLaiDat_VmaItinerarySchedule_VmaScheduleShifting(
            String maritimeCode, String tugboatCompanyCode, String shipCode,  String reportUser,
            String reportPeriod, String reportYear, String reportMonth,
            String fromDate, String toDate, int start, int end)
            throws SystemException {
        return finder
                .findSoLuotLaiDat_VmaItinerarySchedule_VmaScheduleShifting(
                        maritimeCode, tugboatCompanyCode, shipCode,  reportUser,
                        reportPeriod, reportYear, reportMonth,
                        fromDate, toDate, start, end);
    }

    public JSONObject findSoLuotHoaTieuDanTau_VmaItinerarySchedule_VmaScheduleShifting(
            String maritimeCode, String pilotCompanyCode, String pilotCode, String pilotTurn, String reportUser,
            String reportPeriod, String reportYear, String reportMonth,
            String fromDate, String toDate, int start, int end)
            throws SystemException {
        return finder
                .findSoLuotHoaTieuDanTau_VmaItinerarySchedule_VmaScheduleShifting(
                        maritimeCode, pilotCompanyCode, pilotCode, pilotTurn, reportUser,
                        reportPeriod, reportYear, reportMonth,
                        fromDate, toDate, start, end);
    }

    public JSONObject findThongKeTauLaiDat(
            String maritimeCode, String tugboatCompanyCode, String shipCode,  String reportUser,
            String reportPeriod, String fromDate, String toDate, int xemBK, int xemBK_Thutuc )
            throws SystemException {
        return finder
                .findThongKeTauLaiDat(
                        maritimeCode, tugboatCompanyCode, shipCode,  reportUser,
                        reportPeriod, fromDate, toDate,  xemBK, xemBK_Thutuc );
    }

}

