package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.nghiepvu.modelImpl.VmaItineraryModelImpl;
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleShiftingModelImpl;
import vn.gt.dao.danhmuc.service.DmArrivalPurposeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryScheduleLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleAnchorageLocalServiceUtil;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import vn.gt.portlet.phuongtien.VMAUtils;
import java.util.Date;
import java.util.ArrayList; 
import java.util.List;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import org.json.*;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.kernel.util.OrderByComparator;
import org.springframework.beans.factory.annotation.Autowired;
import com.fds.nsw.kernel.exception.PortalException;
import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaScheduleShiftingFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaScheduleShiftingPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaScheduleShiftingLocalServiceImpl { 
@Autowired VmaScheduleShiftingPersistenceImpl persistence;
@Autowired VmaScheduleShiftingFinderImpl finder;


	public VmaScheduleShifting createVmaScheduleShifting(long id) {
		return persistence.create(id);
	}

	public VmaScheduleShifting deleteVmaScheduleShifting(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaScheduleShifting deleteVmaScheduleShifting(VmaScheduleShifting VmaScheduleShifting)
		throws SystemException {
		return persistence.remove(VmaScheduleShifting);
	}

	public VmaScheduleShifting fetchVmaScheduleShifting(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaScheduleShifting getVmaScheduleShifting(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaScheduleShifting> getVmaScheduleShiftings(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaScheduleShiftingsCount() throws SystemException {
		return persistence.countAll();
	}


	public VmaScheduleShifting updateVmaScheduleShifting(VmaScheduleShifting VmaScheduleShifting,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaScheduleShifting, merge);
	}


    public VmaScheduleShifting addVmaScheduleShifting(
            VmaScheduleShifting vmaScheduleShifting) throws SystemException {
        long id = CounterLocalServiceUtil.increment(VmaScheduleShifting.class
                .getName());
        long versionNoCount = CounterLocalServiceUtil.increment(
                "VMA_SCHEDULESHIFTING_VERSION_NO", 1);
        vmaScheduleShifting.setId(id);
        vmaScheduleShifting.setVersionNo(String.valueOf(versionNoCount));
        vmaScheduleShifting.setModifiedDate(new Date());
        // comment by TrungNT

        /*
         * VmaItinerary vmaItinerary = null;
         *
         * try { vmaItinerary = VmaItineraryLocalServiceUtil
         * .findByitineraryNo(vmaScheduleShifting.getItineraryNo());
         *
         * VmaItinerarySchedule vmaItinerarySchedule = VMAUtils
         * .updateVmaItinerarySchedule(null, vmaScheduleShifting, vmaItinerary);
         * if (vmaItinerarySchedule != null && vmaItinerary != null) {
         * VmaItineraryScheduleLocalServiceUtil
         * .updateVmaItinerarySchedule_VmaItinerary( vmaItinerarySchedule,
         * vmaItinerary); // edit by // Dungnv if
         * (vmaItinerarySchedule.getNoticeShipType() == 4) {
         * VmaScheduleAnchorage vmaScheduleAnchorage = VMAUtils
         * .updateVmaScheduleAnchorage(null, vmaItinerary,
         * vmaItinerarySchedule); VmaScheduleAnchorageLocalServiceUtil
         * .addVmaScheduleAnchorage(vmaScheduleAnchorage); } } } catch
         * (Exception e) { log.error(e.getMessage()); }
         */

        // VMAUtils.formatUnicode(vmaScheduleShifting);
        return persistence
                .updateImpl(vmaScheduleShifting, false);
    }

    public VmaScheduleShifting delete(long id) throws SystemException,
            NoSuchVmaScheduleShiftingException {
        return persistence.remove(id);
    }

    public VmaScheduleShifting updateVmaScheduleShifting(
            VmaScheduleShifting vmaScheduleShifting) throws SystemException {
        vmaScheduleShifting.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaScheduleShifting);
        return persistence
                .updateImpl(vmaScheduleShifting, false);
    }

    public JSONObject updateVmaScheduleShifting_VmaItinerary(
            VmaScheduleShifting vmaScheduleShifting, VmaItinerary vmaItinerary)
            throws SystemException, PortalException {

        vmaScheduleShifting.setModifiedDate(new Date());

        vmaItinerary = VmaItineraryLocalServiceUtil
                .updateVmaItinerary(vmaItinerary);

        VmaItinerarySchedule vmaItinerarySchedule = null;

        try {
            vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
                    .findByitineraryNo_certificateNo_noticeShipType(
                            vmaItinerary.getItineraryNo(), 4,
                            vmaScheduleShifting.getCertificateNo());
        } catch (Exception e) {
            // Nothing todo
        }

        if (vmaItinerarySchedule == null) {
            if (vmaScheduleShifting.getRequestState() == 4) {
                vmaItinerarySchedule = VMAUtils.updateVmaItinerarySchedule(
                        null, vmaScheduleShifting, vmaItinerary);
                vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
                        .addVmaItinerarySchedule(vmaItinerarySchedule);

                vmaScheduleShifting.setItineraryScheduleId(vmaItinerarySchedule
                        .getId());
            }
        } else {
            if (vmaScheduleShifting.getRequestState() == 4) {
                vmaItinerarySchedule = VMAUtils.updateVmaItinerarySchedule(
                        null, vmaScheduleShifting, vmaItinerary);
                VmaItineraryScheduleLocalServiceUtil
                        .deleteVmaItinerarySchedule(vmaItinerarySchedule
                                .getId());
                vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
                        .addVmaItinerarySchedule(vmaItinerarySchedule);

                vmaScheduleShifting.setItineraryScheduleId(vmaItinerarySchedule
                        .getId());

            } else if (vmaScheduleShifting.getRequestState() == 6) {
                VmaItineraryScheduleLocalServiceUtil
                        .deleteVmaItinerarySchedule(vmaItinerarySchedule
                                .getId());

                vmaScheduleShifting.setItineraryScheduleId(0L);
            }
        }

        if (vmaItinerarySchedule != null && vmaItinerary != null) {

            if (vmaItinerarySchedule.getNoticeShipType() == 4) {
                VmaScheduleAnchorage vmaScheduleAnchorage = VMAUtils
                        .updateVmaScheduleAnchorage(null, vmaItinerary,
                                vmaItinerarySchedule, vmaScheduleShifting);
                vmaScheduleAnchorage.setPurposeCode(vmaItinerarySchedule.getPurposeCode());
                vmaScheduleAnchorage.setPurposeSpecified(vmaItinerarySchedule.getPurposeSpecified());

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
        }

        vmaScheduleShifting = persistence.updateImpl(
                vmaScheduleShifting, false);

        JSONObject result = VMAUtils.object2Json(vmaItinerary,
                VmaItinerary.class);

        JSONObject tmp = VMAUtils.object2Json(vmaScheduleShifting,
                VmaScheduleShifting.class);

        result = VMAUtils.mergeJSON(result, tmp,
                VmaScheduleShifting.class);

        return result;

    }

    public JSONObject updateVmaScheduleShifting_VmaItinerary(
            Integer markedAsArrival, Integer markedAsDeparture,
            Integer markedAsTransmit, Integer markedAsVoyage, double crewNumber, double passengerNumber,
            VmaScheduleShifting vmaScheduleShifting, VmaItinerary vmaItinerary)
            throws SystemException, PortalException {

        vmaScheduleShifting.setModifiedDate(new Date());

        vmaItinerary = VmaItineraryLocalServiceUtil
                .updateVmaItinerary(vmaItinerary);

        VmaItinerarySchedule vmaItinerarySchedule = null;

        try {
            vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
                    .findByitineraryNo_certificateNo_noticeShipType(
                            vmaItinerary.getItineraryNo(), 4,
                            vmaScheduleShifting.getCertificateNo());
        } catch (Exception e) {
            // Nothing todo
        }

        // RequestState = 4 la Duyet
        if (vmaItinerarySchedule == null) {
            if (vmaScheduleShifting.getRequestState() == 4) {
                vmaItinerarySchedule = VMAUtils.updateVmaItinerarySchedule(
                        null, vmaScheduleShifting, vmaItinerary, crewNumber, passengerNumber);
                vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
                        .addVmaItinerarySchedule(vmaItinerarySchedule);

                vmaScheduleShifting.setItineraryScheduleId(vmaItinerarySchedule
                        .getId());
            }
        } else {
            if (vmaScheduleShifting.getRequestState() == 4) {
                log.info("=========> old scheduleId: " + vmaItinerarySchedule.getId());
                vmaItinerarySchedule = VMAUtils.updateVmaItinerarySchedule(
                        vmaItinerarySchedule, vmaScheduleShifting, vmaItinerary, crewNumber, passengerNumber);
                // edit by Dungnv
				/*if (vmaScheduleShifting.getItineraryScheduleId() > 0) {
					VmaItineraryScheduleLocalServiceUtil
							.deleteVmaItinerarySchedule(vmaScheduleShifting
									.getItineraryScheduleId());
				}*/
                //Edit by Dungnv
                vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
                        .updateVmaItinerarySchedule(vmaItinerarySchedule);

                log.info("=========> new scheduleId: " + vmaItinerarySchedule.getId());

                vmaScheduleShifting.setItineraryScheduleId(vmaItinerarySchedule
                        .getId());
            } else if (vmaScheduleShifting.getRequestState() == 6) {
                if (vmaScheduleShifting.getItineraryScheduleId() > 0) {
                    VmaItineraryScheduleLocalServiceUtil
                            .deleteVmaItinerarySchedule(vmaScheduleShifting
                                    .getItineraryScheduleId());
                }
                vmaScheduleShifting.setItineraryScheduleId(0L);
            }
        }
        try {
            if (vmaItinerarySchedule != null && vmaItinerary != null
                    && vmaScheduleShifting.getRequestState() == 4) {

                VmaScheduleAnchorage vmaScheduleAnchorageShifting = VmaScheduleAnchorageLocalServiceUtil
                        .fetchByitineraryScheduleId(vmaScheduleShifting
                                .getItineraryScheduleId());

                if (vmaItinerarySchedule.getNoticeShipType() == 4) {
                    // Edit by SonVH
                    List<VmaScheduleAnchorage> vmaScheduleAnchorages = VmaScheduleAnchorageLocalServiceUtil
                            .findByItineraryNo_noticeShipType(
                                    vmaItinerarySchedule.getItineraryNo(), 4);

                    if (vmaScheduleAnchorageShifting == null) {

                        vmaScheduleAnchorageShifting = VMAUtils
                                .updateVmaScheduleAnchorage(
                                        vmaScheduleAnchorageShifting, vmaItinerary,
                                        vmaItinerarySchedule, vmaScheduleShifting);
                        vmaScheduleAnchorageShifting.setAnchoringDateTo(null);



                        // Cap nhat lai muc dich neo dau
                        if (Validator.isNotNull(vmaScheduleAnchorageShifting.getPurposeCode()) &&
                                (vmaScheduleAnchorageShifting.getPurposeCode().equalsIgnoreCase("C17")
                                        || vmaScheduleAnchorageShifting.getPurposeCode().equalsIgnoreCase("C18")
                                        || vmaScheduleAnchorageShifting.getPurposeCode().equalsIgnoreCase("C19")
                                        || vmaScheduleAnchorageShifting.getPurposeCode().equalsIgnoreCase("C20")
                                        || vmaScheduleAnchorageShifting.getPurposeCode().equalsIgnoreCase("C30")
                                        || vmaScheduleAnchorageShifting.getPurposeCode().equalsIgnoreCase("C31") )) {
                            // Khong cap nhat lai muc dich neo dau (can bo thu tuc tu cap nhat lai)
                        } else {
                            vmaScheduleAnchorageShifting.setPurposeCode(vmaItinerarySchedule.getPurposeCode());
                            vmaScheduleAnchorageShifting.setPurposeSpecified(vmaItinerarySchedule.getPurposeSpecified());
                        }
                        VmaScheduleAnchorageLocalServiceUtil
                                .addVmaScheduleAnchorage(vmaScheduleAnchorageShifting);


                        List<VmaScheduleAnchorage> vmaScheduleAnchoragesItinerarySchedule = VmaScheduleAnchorageLocalServiceUtil
                                .findByItineraryNo_noticeShipType(
                                        vmaScheduleShifting.getItineraryNo(), 2); // Tim xem co luot roi chua, chen them khoang neo truoc khi roi
                        if (vmaScheduleAnchoragesItinerarySchedule != null
                                && vmaScheduleAnchoragesItinerarySchedule
                                .size() > 0) {
                            VmaScheduleAnchorage vmaScheduleAnchorage = vmaScheduleAnchoragesItinerarySchedule
                                    .get(0);
                            if (vmaScheduleAnchorageShifting.getAnchoringPortWharfCode().equalsIgnoreCase(vmaScheduleAnchorage.getAnchoringPortWharfCode())){
                                vmaScheduleAnchorageShifting.setAnchoringDateTo(vmaScheduleAnchorage.getAnchoringDateTo());
                                VmaScheduleAnchorageLocalServiceUtil
                                        .updateVmaScheduleAnchorage(vmaScheduleAnchorageShifting);
                            }
                        }
                        if (vmaScheduleAnchorages == null
                                || vmaScheduleAnchorages.size() == 0) {
                            // do nothing
                        } else if (vmaScheduleAnchorages != null
                                && vmaScheduleAnchorages.size() >= 1) {

                            VmaScheduleAnchorage previousVmaScheduleAnchorageShifting = vmaScheduleAnchorages
                                    .get(vmaScheduleAnchorages.size() - 1);
                            if (previousVmaScheduleAnchorageShifting.getAnchoringPortWharfCode().equalsIgnoreCase(vmaScheduleAnchorageShifting.getPortWharfCode())
                                    && previousVmaScheduleAnchorageShifting.getMakePayment() == 0) {
                                previousVmaScheduleAnchorageShifting
                                        .setAnchoringDateTo(vmaScheduleShifting
                                                .getShiftingDate());
                                VmaScheduleAnchorageLocalServiceUtil
                                        .updateVmaScheduleAnchorage(previousVmaScheduleAnchorageShifting);
                            }
                        }
                    } else {
                        vmaScheduleAnchorageShifting = VMAUtils
                                .updateVmaScheduleAnchorage(
                                        vmaScheduleAnchorageShifting,
                                        vmaItinerary, vmaItinerarySchedule,
                                        vmaScheduleShifting);
                        VmaScheduleAnchorageLocalServiceUtil
                                .updateVmaScheduleAnchorage(vmaScheduleAnchorageShifting);

                        if (vmaScheduleAnchorages != null
                                && vmaScheduleAnchorages.size() == 1) {
                            // do nothing
                        } else if (vmaScheduleAnchorages != null
                                && vmaScheduleAnchorages.size() > 1) {

                            for (int i = 0; i < vmaScheduleAnchorages.size(); i++   ) {
                                if ( (i>0) && vmaScheduleAnchorages.get(i).getItineraryScheduleId() ==  vmaScheduleAnchorageShifting.getItineraryScheduleId()) {
                                    VmaScheduleAnchorage previousVmaScheduleAnchorageShifting = vmaScheduleAnchorages.get(i-1);
                                    if (previousVmaScheduleAnchorageShifting.getAnchoringPortWharfCode().equalsIgnoreCase(vmaScheduleAnchorageShifting.getPortWharfCode())
                                            && previousVmaScheduleAnchorageShifting.getMakePayment() == 0) {
                                        previousVmaScheduleAnchorageShifting
                                                .setAnchoringDateTo(vmaScheduleShifting
                                                        .getShiftingDate());
                                        VmaScheduleAnchorageLocalServiceUtil
                                                .updateVmaScheduleAnchorage(previousVmaScheduleAnchorageShifting);
                                    }
                                }


                            }
                        }
                    }

                    if ((vmaScheduleAnchorages == null
                            || vmaScheduleAnchorages.size() == 0) ||
                            (vmaScheduleAnchorages != null
                                    && vmaScheduleAnchorages.size() >0
                                    && (vmaScheduleAnchorageShifting.getItineraryScheduleId()
                                    == vmaScheduleAnchorages.get(0).getItineraryScheduleId())
                            )){
                        // Cap nhat Khoang neo luot vao

                        List<VmaScheduleAnchorage> vmaScheduleAnchoragesItinerarySchedule = VmaScheduleAnchorageLocalServiceUtil
                                .findByItineraryNo_noticeShipType(
                                        vmaScheduleShifting.getItineraryNo(), 1);
                        if (vmaScheduleAnchoragesItinerarySchedule != null
                                && vmaScheduleAnchoragesItinerarySchedule
                                .size() > 0) {
                            VmaScheduleAnchorage vmaScheduleAnchorage = vmaScheduleAnchoragesItinerarySchedule
                                    .get(0);

                            if (vmaScheduleAnchorage.getNoticeShipType() == 1) {
                                try {
                                    // Canh bao: Tinh phi hay khong 
                                    if (vmaScheduleAnchorage.getMakePayment() == 1) {
                                        // object.put("Verify_MAKEPAYMENT", "Đã ghi phiếu thu");
                                    } else {
                                        // object.put("Verify_MAKEPAYMENT", "Chưa ghi phiếu thu");
                                        vmaScheduleAnchorage
                                                .setAnchoringDateTo(vmaScheduleShifting
                                                        .getShiftingDate());
                                        double anchoringDuration = VMAUtils
                                                .calculateAnchoringDuration(
                                                        vmaScheduleAnchorage
                                                                .getAnchoringDateFrom(),
                                                        vmaScheduleAnchorage
                                                                .getAnchoringDateTo());
                                        vmaScheduleAnchorage
                                                .setAnchoringDuration(anchoringDuration);

                                        String portWharfCode = vmaScheduleAnchorage.getAnchoringPortWharfCode();
                                        DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil.getByPortWharfCode(portWharfCode);
                                        if (dmPortWharf !=null && dmPortWharf.getPortWharfPayment()==1){
                                            //object.put("Verify_PAYMENT", "Cảng vụ tính phí");
                                            vmaScheduleAnchorage
                                                    .setAnchorageDomesticDuration(anchoringDuration);
                                            vmaScheduleAnchorage
                                                    .setAnchorageForeignDuration((double) 0);
                                            try {
                                                VmaItinerarySchedule vmaItineraryScheduleIn = VmaItineraryScheduleLocalServiceUtil
                                                        .findByItineraryNo_NoticeShipType(vmaItinerary.getItineraryNo(), 1);
                                                if (Validator.isNotNull(vmaItinerary) &&
                                                        (!(vmaItinerary.getFlagStateOfShip().equalsIgnoreCase("VN"))) && (vmaItinerary.getDomesticTransportCertificate()!=1)){
                                                    // Tinh phi ngoai neu Tau nuoc ngoai khong co giay phep van chuyen noi dia
                                                    vmaScheduleAnchorage
                                                            .setAnchorageForeignDuration(anchoringDuration);
                                                    vmaScheduleAnchorage
                                                            .setAnchorageDomesticDuration((double) 0);
                                                } else if (Validator.isNotNull(vmaItineraryScheduleIn) && vmaItineraryScheduleIn.getLastPortOfCallCode().length() > 0 ) {
                                                    DmPort objArrivalPort = DmPortLocalServiceUtil.getByPortCode(vmaItineraryScheduleIn.getLastPortOfCallCode());
                                                    if ( ((Validator.isNotNull(objArrivalPort)) && objArrivalPort.getStateCode().length() > 0
                                                            && (!(objArrivalPort.getStateCode().equalsIgnoreCase("VN"))))
                                                            || ((!(vmaItineraryScheduleIn.getLastPortOfCallCode().substring(0, 2).equalsIgnoreCase("VN"))) && Validator.isNull(objArrivalPort)) ){
                                                        // Tinh phi ngoai neu Cang roi cuoi cung la cang quoc te
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
                                            //object.put("Verify_PAYMENT", "Không tính phí");
                                            vmaScheduleAnchorage
                                                    .setAnchorageFreeDuration(anchoringDuration);
                                            vmaScheduleAnchorage.setAnchorageDomesticDuration((double) 0);
                                            vmaScheduleAnchorage.setAnchorageForeignDuration((double) 0);
                                        }
                                    }

                                } catch (Exception e) {
                                    // nothing to do
                                }

                                VmaScheduleAnchorageLocalServiceUtil
                                        .updateVmaScheduleAnchorage(vmaScheduleAnchorage);
                            }
                        }

                    }



                }
                // =====
            }
        } catch (Exception e) {
            // Nothing todo
        }
        vmaScheduleShifting = persistence.updateImpl(
                vmaScheduleShifting, false);

        JSONObject result = VMAUtils.object2Json(vmaItinerary,
                VmaItinerary.class);

        JSONObject tmp = VMAUtils.object2Json(vmaScheduleShifting,
                VmaScheduleShifting.class);

        result = VMAUtils.mergeJSON(result, tmp,
                VmaScheduleShifting.class);

        result.put("itineraryScheduleId",
                vmaScheduleShifting.getItineraryScheduleId());

        return result;

    }

    public List<VmaScheduleShifting> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public VmaScheduleShifting findByItineraryNo_CertificateNo(
            String itineraryNo, String certificateNo) throws SystemException,
            NoSuchVmaScheduleShiftingException {
        return persistence.findByitineraryNo_certificateNo(
                itineraryNo, certificateNo);
    }

    public int countByItineraryNo_CertificateNo(String itineraryNo,
                                                String certificateNo) throws SystemException {
        return persistence.countByitineraryNo_certificateNo(
                itineraryNo, certificateNo);
    }

    public VmaScheduleShifting findByItineraryNo_VersionNo(String itineraryNo,
                                                           String versionNo) throws SystemException,
            NoSuchVmaScheduleShiftingException {
        return persistence.findByitineraryNo_versionNo(
                itineraryNo, versionNo);
    }

    public int countByItineraryNo_VersionNo(String itineraryNo, String versionNo)
            throws SystemException {
        return persistence.countByitineraryNo_versionNo(
                itineraryNo, versionNo);
    }

    public JSONObject findVmaItinerary_VmaScheduleShifting(String itineraryNo,
                                                           String portofAuthority, String shiftingDate, String requestState,
                                                           int start, int end) throws SystemException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaItinerary_VmaScheduleShifting(itineraryNo,
                        portofAuthority, shiftingDate, requestState);
        JSONArray data = finder
                .findVmaItinerary_VmaScheduleShifting(itineraryNo,
                        portofAuthority, shiftingDate, requestState, start, end);

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaItinerary_VmaScheduleShifting(String itineraryNo,
                                                      String portofAuthority, String shiftingDate, String requestState)
            throws SystemException {

        return finder.countVmaItinerary_VmaScheduleShifting(
                itineraryNo, portofAuthority, shiftingDate, requestState);

    }


}

