package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.*;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import vn.gt.portlet.phuongtien.VMAUtils;
import vn.gt.portlet.baocao.bc12bt.BC12BTLuotHHModel;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.utils.PageType;
import vn.gt.tichhop.message.MessageType;

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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaItineraryFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaItineraryPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaItineraryLocalServiceImpl { 
@Autowired VmaItineraryPersistenceImpl persistence;
@Autowired VmaItineraryFinderImpl finder;
	public VmaItinerary createVmaItinerary(long id) {
		return persistence.create(id);
	}

	public VmaItinerary deleteVmaItinerary(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaItinerary deleteVmaItinerary(VmaItinerary VmaItinerary)
		throws SystemException {
		return persistence.remove(VmaItinerary);
	}

	public VmaItinerary fetchVmaItinerary(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaItinerary getVmaItinerary(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaItinerary> getVmaItineraries(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaItinerariesCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaItinerary updateVmaItinerary(VmaItinerary VmaItinerary,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaItinerary, merge);
	}

    public VmaItinerary addVmaItinerary(VmaItinerary vmaItinerary)
            throws SystemException {
        long id = CounterLocalServiceUtil.increment(VmaItinerary.class.getName());
        vmaItinerary.setId(id);
        String itineraryNo = VmaItineraryLocalServiceUtil
                .getItineraryNoWithRule(vmaItinerary.getMaritimeCode());
        vmaItinerary.setItineraryNo(itineraryNo);
        vmaItinerary.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaItinerary);
        return persistence.updateImpl(vmaItinerary, false);
    }

    public JSONObject addVmaItinerary_VmaItinerarySchedule(
            VmaItinerary vmaItinerary, VmaItinerarySchedule vmaItinerarySchedule)
            throws SystemException, JSONException {

        if (vmaItinerary.getId() <= 0) {
            // add new
            long id = CounterLocalServiceUtil.increment(VmaItinerary.class
                    .getName());

            vmaItinerary.setId(id);

            String itineraryNo = VmaItineraryLocalServiceUtil
                    .getItineraryNoWithRule(vmaItinerary.getMaritimeCode());
            vmaItinerary.setItineraryNo(itineraryNo);

            vmaItinerary.setModifiedDate(new Date());
        }
        vmaItinerary = persistence.updateImpl(vmaItinerary, false);

        vmaItinerarySchedule.setItineraryNo(vmaItinerary.getItineraryNo());
        vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
                .addVmaItinerarySchedule(vmaItinerarySchedule);
        // TODO auto add anchorage
        if (vmaItinerarySchedule.getNoticeShipType() == 2) {
            // Edited 16/12/2019
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

                    VmaScheduleAnchorage vmaScheduleAnchorage_ = vmaScheduleAnchoragesItinerarySchedule
                            .get(0);

                    if (vmaScheduleAnchorage_.getNoticeShipType() == 1) {
                        vmaScheduleAnchorage_
                                .setAnchoringDateTo(vmaItinerarySchedule
                                        .getTimeOfDeparture());

                        try {
                            double anchoringDuration = VMAUtils
                                    .calculateAnchoringDuration(
                                            vmaScheduleAnchorage_
                                                    .getAnchoringDateFrom(),
                                            vmaScheduleAnchorage_
                                                    .getAnchoringDateTo());
                            vmaScheduleAnchorage_
                                    .setAnchoringDuration(anchoringDuration);

                            try {
                                // Canh bao: Tinh phi hay khong
                                if (vmaScheduleAnchorage_.getMakePayment() == 1) {
                                    // object.put("Verify_MAKEPAYMENT", "Đã ghi phiếu thu");
                                } else {
                                    // object.put("Verify_MAKEPAYMENT", "Chưa ghi phiếu thu");
                                    String portWharfCode = vmaScheduleAnchorage_.getAnchoringPortWharfCode();
                                    DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil.getByPortWharfCode(portWharfCode);
                                    if (dmPortWharf !=null && dmPortWharf.getPortWharfPayment()==1){
                                        //object.put("Verify_PAYMENT", "Cảng vụ tính phí");
                                        vmaScheduleAnchorage_
                                                .setAnchorageDomesticDuration(anchoringDuration);
                                        vmaScheduleAnchorage_
                                                .setAnchorageForeignDuration((double)0);
                                        try {
                                            VmaItinerarySchedule vmaItineraryScheduleIn = VmaItineraryScheduleLocalServiceUtil
                                                    .findByItineraryNo_NoticeShipType(vmaItinerary.getItineraryNo(), 1);
                                            if (Validator.isNotNull(vmaItinerary) &&
                                                    (!(vmaItinerary.getFlagStateOfShip().equalsIgnoreCase("VN"))) && (vmaItinerary.getDomesticTransportCertificate()!=1)){
                                                // Tinh phi ngoai neu Cang roi cuoi cung la cang quoc te
                                                vmaScheduleAnchorage_
                                                        .setAnchorageForeignDuration(anchoringDuration);
                                                vmaScheduleAnchorage_
                                                        .setAnchorageDomesticDuration((double)0);
                                            } else if (Validator.isNotNull(vmaItineraryScheduleIn) && vmaItineraryScheduleIn.getLastPortOfCallCode().length() > 0 ) {
                                                DmPort objArrivalPort = DmPortLocalServiceUtil.getByPortCode(vmaItineraryScheduleIn.getLastPortOfCallCode());
                                                if ((Validator.isNotNull(objArrivalPort)) && objArrivalPort.getStateCode().length() > 0
                                                        && (!(objArrivalPort.getStateCode().equalsIgnoreCase("VN")))){
                                                    // Tinh phi ngoai neu Cang roi cuoi cung la cang quoc te
                                                    vmaScheduleAnchorage_
                                                            .setAnchorageForeignDuration(anchoringDuration);
                                                    vmaScheduleAnchorage_
                                                            .setAnchorageDomesticDuration((double)0);
                                                }
                                            }
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        //object.put("Verify_PAYMENT", "Không tính phí");
                                        vmaScheduleAnchorage_
                                                .setAnchorageFreeDuration(anchoringDuration);
                                    }
                                }
                            } catch (Exception e) {
                                // nothing to do
                                //object.put("Verify_PAYMENT", "Không tính phí");
                                vmaScheduleAnchorage_
                                        .setAnchorageFreeDuration(anchoringDuration);
                            }

                        } catch (Exception e) {
                            // nothing to do
                        }

                        VmaScheduleAnchorageLocalServiceUtil
                                .updateVmaScheduleAnchorage(vmaScheduleAnchorage_);
                    }
                }
            } else if (vmaScheduleAnchorages != null
                    && vmaScheduleAnchorages.size() > 0) {
                VmaScheduleAnchorage vmaScheduleAnchorage = vmaScheduleAnchorages
                        .get(vmaScheduleAnchorages.size() - 1);

                vmaScheduleAnchorage.setAnchoringDateTo(vmaItinerarySchedule
                        .getTimeOfDeparture());

                VmaScheduleAnchorageLocalServiceUtil
                        .updateVmaScheduleAnchorage(vmaScheduleAnchorage);
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

    public JSONObject addVmaItinerary_VmaScheduleShifting(
            VmaItinerary vmaItinerary, VmaScheduleShifting vmaScheduleShifting)
            throws SystemException, JSONException {

        if (vmaItinerary.getId() <= 0) {
            long id = CounterLocalServiceUtil.increment(VmaItinerary.class
                    .getName());

            vmaItinerary.setId(id);

            String itineraryNo = VmaItineraryLocalServiceUtil
                    .getItineraryNoWithRule(vmaItinerary.getMaritimeCode());
            vmaItinerary.setItineraryNo(itineraryNo);

            vmaItinerary.setModifiedDate(new Date());
        }
        // VMAUtils.formatUnicode(vmaItinerary);
        vmaItinerary = persistence.updateImpl(vmaItinerary, false);

        vmaScheduleShifting.setItineraryNo(vmaItinerary.getItineraryNo());

        vmaScheduleShifting = VmaScheduleShiftingLocalServiceUtil
                .addVmaScheduleShifting(vmaScheduleShifting);

        JSONObject result = VMAUtils.object2Json(vmaItinerary,
                VmaItinerary.class);

        JSONObject tmp = VMAUtils.object2Json(vmaScheduleShifting,
                VmaScheduleShifting.class);

        result = VMAUtils.mergeJSON(result, tmp,
                VmaScheduleShifting.class);

        return result;
    }

    public JSONObject addVmaItinerary_VmaScheduleAnchorage(
            VmaItinerary vmaItinerary, VmaScheduleAnchorage vmaScheduleAnchorage)
            throws SystemException, JSONException {

        if (vmaItinerary.getId() <= 0) {

            long id = CounterLocalServiceUtil.increment(VmaItinerary.class
                    .getName());

            vmaItinerary.setId(id);

            String itineraryNo = VmaItineraryLocalServiceUtil
                    .getItineraryNoWithRule(vmaItinerary.getMaritimeCode());
            vmaItinerary.setItineraryNo(itineraryNo);

            vmaItinerary.setModifiedDate(new Date());
        }

        // VMAUtils.formatUnicode(vmaItinerary);
        vmaItinerary = persistence.updateImpl(vmaItinerary, false);

        vmaScheduleAnchorage.setItineraryNo(vmaItinerary.getItineraryNo());

        vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
                .addVmaScheduleAnchorage(vmaScheduleAnchorage);

        JSONObject result = VMAUtils.object2Json(vmaItinerary,
                VmaItinerary.class);

        JSONObject tmp = VMAUtils.object2Json(vmaScheduleAnchorage,
                VmaScheduleAnchorage.class);

        result = VMAUtils.mergeJSON(result, tmp,
                VmaScheduleAnchorage.class);

        return result;
    }

    public JSONObject updateVmaItinerary(String itineraryNo,
                                         Integer markedAsArrival, Integer markedAsDeparture,
                                         Integer markedAsTransmit, Integer markedAsVoyage) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        try {
            VmaItinerary vmaItinerary = persistence
                    .fetchByitineraryNo(itineraryNo);
            if (markedAsDeparture != null) {
                vmaItinerary.setMarkedAsDeparture(markedAsDeparture);
            }

            if (markedAsArrival != null) {
                vmaItinerary.setMarkedAsArrival(markedAsArrival);
            }

            if (markedAsTransmit != null) {
                vmaItinerary.setMarkedAsTransmit(markedAsTransmit);
            }

            if (markedAsVoyage != null) {
                vmaItinerary.setMarkedAsVoyage(markedAsVoyage);
            }
            // VMAUtils.formatUnicode(vmaItinerary);
            vmaItinerary = persistence.updateImpl(vmaItinerary, false);
            result = VMAUtils.object2Json(vmaItinerary,
                    VmaItinerary.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public JSONObject updateVmaItinerary(long documentName, int documentYear,
                                         Integer markedAsArrival, Integer markedAsDeparture,
                                         Integer markedAsTransmit, Integer markedAsVoyage) {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        VmaItinerary vmaItinerary = null;

        try {

            List<TempNoticeShipMessage> tempNoTiceShipMessages = TempNoTiceShipMessageLocalServiceUtil
                    .findBydocumentNameAndDocumentYearAndNoticeShipType(
                            documentName, documentYear,
                            PageType.TYPE_THONG_BAO_TAU_DEN_CANG);

            TempNoticeShipMessage tempNoTiceShipMessage = null;
            if (tempNoTiceShipMessages != null
                    && tempNoTiceShipMessages.size() > 0) {
                tempNoTiceShipMessage = tempNoTiceShipMessages.get(0);

            }
            if (tempNoTiceShipMessage == null
                    || Validator.isNull(tempNoTiceShipMessage.getShipName())) {

                return result;
            }

            if (tempNoTiceShipMessage.getIsArrival() == 1) {
                vmaItinerary = VmaItineraryLocalServiceUtil
                        .fetchBydocumentNameIN_documentYearIN(documentName,
                                documentYear);
                result = VMAUtils.object2Json(vmaItinerary,
                        VmaItinerary.class);

            } else if (tempNoTiceShipMessage.getIsArrival() == 0) {

                vmaItinerary = VmaItineraryLocalServiceUtil
                        .fetchBydocumentNameOUT_documentYearOUT(documentName,
                                documentYear);
                result = VMAUtils.object2Json(vmaItinerary,
                        VmaItinerary.class);
            }

            if (markedAsDeparture != null && vmaItinerary.getMarkedAsDeparture() > 1) {
                vmaItinerary.setMarkedAsDeparture(markedAsDeparture);

                // edit 18/10/2019 - Dungnv
                if (markedAsDeparture == ChuyenDichTrangThaiUtils.DANH_DAU_DA_DUYET
                        || markedAsDeparture == ChuyenDichTrangThaiUtils.DANH_DAU_DA_KY_DUYET) {
                    vmaItinerary
                            .setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
                } else if (markedAsDeparture == ChuyenDichTrangThaiUtils.DANH_DAU_DA_HUY) {
                    vmaItinerary
                            .setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
                }
            }

            if (markedAsArrival != null) {
                vmaItinerary.setMarkedAsArrival(markedAsArrival);

                // edit 18/10/2019 - Dungnv
                if (markedAsArrival == ChuyenDichTrangThaiUtils.DANH_DAU_DA_HUY) {
                    vmaItinerary
                            .setShipPosition(MessageType.SHIP_POSSITION_DEN_CANG);
                } else if (markedAsArrival == ChuyenDichTrangThaiUtils.DANH_DAU_DA_DUYET
                        || markedAsArrival == ChuyenDichTrangThaiUtils.DANH_DAU_CHO_DONG_DAU_BCY
                        || markedAsArrival == ChuyenDichTrangThaiUtils.DANH_DAU_DA_KY_DUYET) {
                    vmaItinerary
                            .setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
                }
            }

            if (markedAsTransmit != null) {
                vmaItinerary.setMarkedAsTransmit(markedAsTransmit);

                // edit 18/10/2019 - Dungnv
                if (markedAsTransmit == ChuyenDichTrangThaiUtils.DANH_DAU_DA_DUYET
                        || markedAsTransmit == ChuyenDichTrangThaiUtils.DANH_DAU_DA_KY_DUYET) {
                    vmaItinerary
                            .setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
                } else if (markedAsTransmit == ChuyenDichTrangThaiUtils.DANH_DAU_DA_HUY) {
                    vmaItinerary
                            .setShipPosition(MessageType.SHIP_POSSITION_TRONG_CANG);
                }
            }

            if (markedAsVoyage != null) {
                vmaItinerary.setMarkedAsVoyage(markedAsVoyage);
            }
            // VMAUtils.formatUnicode(vmaItinerary);
            vmaItinerary = persistence.updateImpl(vmaItinerary, false);
            result = VMAUtils.object2Json(vmaItinerary,
                    VmaItinerary.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public VmaItinerary delete(long id) throws NoSuchVmaItineraryException,
            SystemException {
        return persistence.remove(id);
    }

    public VmaItinerary updateVmaItinerary(VmaItinerary vmaItinerary)
            throws SystemException {
        // VMAUtils.formatUnicode(vmaItinerary);
        return persistence.updateImpl(vmaItinerary, false);
    }

    public int countAll() throws SystemException {
        return persistence.countAll();
    }

    public int countByCallSign(String callSign) throws SystemException {
        return persistence.countBycallSign(callSign);
    }

    public int countByIMONumber(String imoNumber) throws SystemException {
        return persistence.countByimoNumber(imoNumber);
    }

    public int countByIMONumber_CallSign(String imoNumber, String callSign)
            throws SystemException {
        return persistence.countByimoNumber_callSign(imoNumber,
                callSign);
    }

    public int countByRegistryNumber(String registryNumber)
            throws SystemException {
        return persistence.countByregistryNumber(registryNumber);
    }

    public int countByVRCode(String vrCode) throws SystemException {
        return persistence.countByvrCode(vrCode);
    }

    public int countByVRCode_RegistryNumber(String imoNumber,
                                            String registryNumber) throws SystemException {
        return persistence.countByvrCode_registryNumber(imoNumber,
                registryNumber);
    }

    public List<VmaItinerary> findByCallSign(String callSign)
            throws NoSuchVmaItineraryException, SystemException {
        return persistence.findBycallSign(callSign);
    }

    public List<VmaItinerary> findByIMONumber(String imoNumber)
            throws SystemException, NoSuchVmaItineraryException {
        return persistence.findByimoNumber(imoNumber);
    }

    public List<VmaItinerary> findByIMONumber_CallSign(String imoNumber,
                                                       String callSign) throws SystemException,
            NoSuchVmaItineraryException {
        return persistence.findByimoNumber_callSign(imoNumber,
                callSign);
    }

    public List<VmaItinerary> findByRegistryNumber(String registryNumber)
            throws SystemException, NoSuchVmaItineraryException {
        return persistence.findByregistryNumber(registryNumber);
    }

    public List<VmaItinerary> findByVRCode(String vrCode)
            throws SystemException, NoSuchVmaItineraryException {
        return persistence.findByvrCode(vrCode);
    }

    public List<VmaItinerary> findByVRCode_RegistryNumber(String imoNumber,
                                                          String registryNumber) throws SystemException,
            NoSuchVmaItineraryException {
        return persistence.findByvrCode_registryNumber(imoNumber,
                registryNumber);
    }

    public List<VmaItinerary> findAll(int start, int end)
            throws SystemException {
        return persistence.findAll(start, end);
    }

    public List<VmaItinerary> findAll(int start, int end,
                                      OrderByComparator orderByComparator) throws SystemException {
        return persistence.findAll(start, end, orderByComparator);
    }

    public VmaItinerary fetchByitineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.fetchByitineraryNo(itineraryNo);
    }

    public VmaItinerary findByitineraryNo(String itineraryNo)
            throws SystemException, NoSuchVmaItineraryException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public VmaItinerary findVmaItineraryLeftByIMOandCallSign(
            String maritimeCode, Date TimeOfDeparture, String callSign,
            String imo, String ShipPosition) {
        try {
            return finder.findVmaItineraryLeftByIMOandCallSign(
                    maritimeCode, TimeOfDeparture, callSign, imo, ShipPosition);
        } catch (Exception e) {

            log.error(e.getMessage());

            return null;
        }

    }

    public VmaItinerary findVmaItineraryByVoyageIMOandCallSign(
            String maritimeCode, String VoyageNumber, String callSign,
            String imo, String ShipPosition) {
        try {
            return finder.findVmaItineraryByVoyageIMOandCallSign(
                    maritimeCode, VoyageNumber, callSign, imo, ShipPosition);
        } catch (Exception e) {

            log.error(e.getMessage());

            return null;
        }

    }

    public String getItineraryNoWithRule(String MaritimeCode) {
        try {
            return finder.getItineraryNoWithRule(MaritimeCode);
        } catch (Exception e) {

            log.error(e.getMessage());

            return null;
        }

    }

    public VmaItinerary fetchBydocumentNameIN_documentYearIN(
            long documentNameIN, int documentYearIN) {
        try {
            return persistence
                    .fetchBydocumentNameIN_documentYearIN(documentNameIN,
                            documentYearIN);
        } catch (Exception e) {

            log.error(e.getMessage());

            return null;
        }

    }

    public VmaItinerary fetchBydocumentNameOUT_documentYearOUT(
            long documentNameOUT, int documentYearOUT) {
        try {
            return persistence
                    .fetchBydocumentNameOUT_documentYearOUT(documentNameOUT,
                            documentYearOUT);
        } catch (Exception e) {

            log.error(e.getMessage());
            return null;
        }

    }

    public JSONObject findVmaItinerary(String searchQuery, String countQuery,
                                       int start, int end) throws JSONException, SystemException {

        List<VmaItinerary> vmaItineraries = finder
                .findVmaItinerary(VmaItinerary.class, "VmaItinerary",
                        searchQuery, start, end);

        long count = finder.countVmaItinerary(countQuery);

        JSONObject result = JSONFactoryUtil.createJSONObject();

        JSONArray data = JSONFactoryUtil.createJSONArray();

        if (vmaItineraries != null) {

            for (VmaItinerary vmaItinerary : vmaItineraries) {
                JSONObject object = VMAUtils.object2Json(vmaItinerary,
                        VmaItinerary.class, new String[] {
                                "flagStateOfShip", "shipAgencyCode" });
                data.put(object);
            }

        }

        result.put("total", count);

        result.put("data", data);

        return result;
    }

    public List<VmaItinerary> findVmaItinerary_ChuyenTuyen(String searchQuery,
                                                           int start, int end) throws SystemException {
        return finder.findVmaItinerary(VmaItinerary.class,
                "VmaItinerary", searchQuery, start, end);
    }

    public long countVmaItinerary(String countQuery) throws JSONException,
            SystemException {

        return finder.countVmaItinerary(countQuery);

    }

    public JSONObject getModelMau12T(String maritimeCode, String startDate,
                                     String endDate, String objName) throws SystemException {
        return finder.getModelMau12T(maritimeCode, startDate,
                endDate, objName);
    }

    public JSONObject getModelMau14T(String maritimeCode, String startDate,
                                     String endDate, String objName) throws SystemException {
        return finder.getModelMau14T(maritimeCode, startDate,
                endDate, objName);
    }

    public BC12BTLuotHHModel getModelMau12BTLuotHH(String maritimeCode,
                                                   String startDate, String endDate) throws SystemException {
        return finder.getModelMau12BTLuotHH(maritimeCode,
                startDate, endDate);
    }

    public JSONArray getModelMau11T(String maritimeCode, String startDate,
                                    String endDate) throws SystemException {
        return finder.getModelMau11T(maritimeCode, startDate,
                endDate);
    }

    public JSONArray getModelMau11BT(String maritimeCode, String startDate,
                                     String endDate) throws SystemException {
        return finder.getModelMau11BT(maritimeCode, startDate,
                endDate);
    }

    public JSONObject getModelMau19T(String maritimeCode, String startDate,
                                     String endDate) throws SystemException {
        return finder.getModelMau19T(maritimeCode, startDate,
                endDate);
    }

    public JSONArray getModelMau65T(String maritimeCode, String startDate,
                                    String endDate) throws SystemException {
        return finder.getModelMau65T(maritimeCode, startDate,
                endDate);
    }

    public JSONArray getModelMau66T(String maritimeCode, String startDate,
                                    String endDate) throws SystemException {
        return finder.getModelMau66T(maritimeCode, startDate,
                endDate);
    }

    public JSONObject getModelMau67T(String maritimeCode, String startDate,
                                     String endDate) throws SystemException {
        return finder.getModelMau67T(maritimeCode, startDate,
                endDate);
    }

    public JSONObject getModelMau13T(String maritimeCode, String startDate,
                                     String endDate, String objName) throws SystemException {
        return finder.getModelMau13T(maritimeCode, startDate,
                endDate, objName);
    }

    public JSONObject getModelMau16T(String maritimeCode, String startDate,
                                     String endDate, String objName) throws SystemException {
        return finder.getModelMau16T(maritimeCode, startDate,
                endDate, objName);
    }

    public JSONObject getModelMau20T(String maritimeCode, String startDate,
                                     String endDate) throws SystemException {
        return finder.getModelMau20T(maritimeCode, startDate,
                endDate);
    }

    public JSONObject getModelMau21T(String maritimeCode, String startDate,
                                     String endDate) throws SystemException {
        return finder.getModelMau21T(maritimeCode, startDate,
                endDate);
    }

    public JSONArray getModelMau46_47_48_52_71_72_73_79T(String query)
            throws SystemException {
        return finder.getModelMau46_47_48_52_71_72_73_79T(query);
    }

    public JSONArray getModelMau49_50_51T(String maritimeCode,
                                          String nameOfShip, String imoNumber, String registryNumber,
                                          String vrCode, String flagStateOfShip, String from_gt,
                                          String to_gt, String from_dwt, String to_dwt, String from_loa,
                                          String to_loa, String lastPortCode, String nextPortCode,
                                          String arrivalShipAgency, String departureShipAgency,
                                          String cargoType, String cargoCategory, String callSign,
                                          String startDate, String endDate) throws SystemException {
        return finder.getModelMau49_50_51T(maritimeCode,
                nameOfShip, imoNumber, registryNumber, vrCode, flagStateOfShip,
                from_gt, to_gt, from_dwt, to_dwt, from_loa, to_loa,
                lastPortCode, nextPortCode, arrivalShipAgency,
                departureShipAgency, cargoType, cargoCategory, callSign,
                startDate, endDate);
    }

    public JSONArray getModelMauBC15T(String maritimeCode,
                                      String cargoTypeContainer, String cargoTypeKho,
                                      String cargoTypeLong, String portHarbourCode, String startDate,
                                      String endDate) throws SystemException {
        return finder.getModelMauBC15T(maritimeCode,
                cargoTypeContainer, cargoTypeKho, cargoTypeLong,
                portHarbourCode, startDate, endDate);
    }

    public JSONArray dsNoiChuyen(String shipPosition, String imoNumber,
                                 String callSign, String registryNumber, String nameOfShip,
                                 String documentNameIN, String timeOfArrival, int start, int end)
            throws SystemException {
        return finder.dsNoiChuyen(shipPosition, imoNumber,
                callSign, registryNumber, nameOfShip, documentNameIN,
                timeOfArrival, start, end);
    }



}

