package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaScheduleAnchorageFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaScheduleAnchoragePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import vn.gt.portlet.phuongtien.VMAUtils;
import vn.gt.utils.FormatData;

@Slf4j @Service
public class VmaScheduleAnchorageLocalServiceImpl { 
@Autowired VmaScheduleAnchoragePersistenceImpl persistence;
@Autowired VmaScheduleAnchorageFinderImpl finder;

	public VmaScheduleAnchorage createVmaScheduleAnchorage(long id) {
		return persistence.create(id);
	}

	public VmaScheduleAnchorage deleteVmaScheduleAnchorage(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaScheduleAnchorage deleteVmaScheduleAnchorage(VmaScheduleAnchorage VmaScheduleAnchorage)
		throws SystemException {
		return persistence.remove(VmaScheduleAnchorage);
	}

	public VmaScheduleAnchorage fetchVmaScheduleAnchorage(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaScheduleAnchorage getVmaScheduleAnchorage(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaScheduleAnchorage> getVmaScheduleAnchorages(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaScheduleAnchoragesCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaScheduleAnchorage updateVmaScheduleAnchorage(VmaScheduleAnchorage VmaScheduleAnchorage,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaScheduleAnchorage, merge);
	}
public VmaScheduleAnchorage addVmaScheduleAnchorage(
        VmaScheduleAnchorage vmaScheduleAnchorage) throws SystemException {

    long id = CounterLocalServiceUtil.increment(VmaScheduleAnchorage.class
            .getName());

    long sequenceNo = CounterLocalServiceUtil.increment(
            "VMA_SCHEDULE_ANCHORAGE_SEQUENCE_NO", 1);

    vmaScheduleAnchorage.setId(id);

    vmaScheduleAnchorage.setSequenceNo((int) sequenceNo);
    vmaScheduleAnchorage.setModifiedDate(new Date());
    // VMAUtils.formatUnicode(vmaScheduleAnchorage);
    return persistence.updateImpl(vmaScheduleAnchorage,
            false);

}

    public VmaScheduleAnchorage delete(long id) throws SystemException,
            NoSuchVmaScheduleAnchorageException {
        return persistence.remove(id);
    }

    public VmaScheduleAnchorage updateVmaScheduleAnchorage(
            VmaScheduleAnchorage vmaScheduleAnchorage) throws SystemException {
        vmaScheduleAnchorage.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaScheduleAnchorage);
        return persistence.updateImpl(vmaScheduleAnchorage,
                false);

    }

    public List<VmaScheduleAnchorage> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public List<VmaScheduleAnchorage> findByItineraryNo_MakePayment(
            String itineraryNo, int makePayment) throws SystemException {
        return persistence.findByitineraryNo_makePayment(
                itineraryNo, makePayment);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public VmaScheduleAnchorage findByItineraryNo_SequenceNo(
            String itineraryNo, int sequenceNo) throws SystemException,
            NoSuchVmaScheduleAnchorageException {
        return persistence.findByitineraryNo_sequenceNo(
                itineraryNo, sequenceNo);
    }

    public int countByItineraryNo_SequenceNo(String itineraryNo, int sequenceNo)
            throws SystemException {
        return persistence.countByitineraryNo_sequenceNo(
                itineraryNo, sequenceNo);
    }

    public int countByitineraryNo_makePayment(String itineraryNo,
                                              int makePayment) throws SystemException {
        return persistence.countByitineraryNo_makePayment(
                itineraryNo, makePayment);
    }

    public JSONObject updateVmaScheduleAnchorage_VmaItinerary(
            VmaScheduleAnchorage vmaScheduleAnchorage, VmaItinerary vmaItinerary)
            throws SystemException, JSONException {

        vmaScheduleAnchorage.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaScheduleAnchorage);
        vmaScheduleAnchorage = persistence.updateImpl(
                vmaScheduleAnchorage, false);
        // VMAUtils.formatUnicode(vmaItinerary);

        int count = countByitineraryNo_makePayment(
                vmaItinerary.getItineraryNo(), 0);
        if (count > 0) {
            vmaItinerary.setPayment2AnchorageStatus(1);
        }
        vmaItinerary = VmaItineraryLocalServiceUtil
                .updateVmaItinerary(vmaItinerary);

        JSONObject result = VMAUtils.object2Json(vmaItinerary,
                VmaItinerary.class);

        JSONObject tmp = VMAUtils.object2Json(vmaScheduleAnchorage,
                VmaScheduleAnchorage.class);

        result = VMAUtils.mergeJSON(result, tmp,
                VmaScheduleAnchorage.class);

        try {
            String anchoringDateFrom = FormatData
                    .parseDateToTringType3(vmaScheduleAnchorage
                            .getAnchoringDateFrom());
            String anchoringDateTo = FormatData
                    .parseDateToTringType3(vmaScheduleAnchorage
                            .getAnchoringDateTo());

            result.remove("anchoringDateFrom");
            result.remove("anchoringDateTo");
            result.put("anchoringDateFrom", anchoringDateFrom);
            result.put("anchoringDateTo", anchoringDateTo);
        } catch (Exception e) {
            // nothing to do
        }

        return result;

    }

    public JSONObject findVmaItinerary_VmaScheduleAnchorage(String itineraryNo,
                                                            String nameOfShip, String purposeCode, String anchoringDateFrom,
                                                            String anchoringDateTo, String anchoringPortRegionCode,
                                                            String anchoringPortHarbourCode, String anchoringPortWharfCode,
                                                            String shipOwnerCode, String shipOperatorCode,
                                                            String shipAgencyCode, Integer makePayment,
                                                            Integer anchorageSupplement, String documentaryCode,
                                                            Long itineraryScheduleId, Integer shipPosition, String flag,
                                                            String flag2, String imoNumber, String callSign,
                                                            String registryNumber, String timeOfArrival, int start, int end)
            throws SystemException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaItinerary_VmaScheduleAnchorage(itineraryNo,
                        nameOfShip, purposeCode, anchoringDateFrom,
                        anchoringDateTo, anchoringPortRegionCode,
                        anchoringPortHarbourCode, anchoringPortWharfCode,
                        shipOwnerCode, shipOperatorCode, shipAgencyCode,
                        makePayment, anchorageSupplement, documentaryCode,
                        itineraryScheduleId, shipPosition, flag, imoNumber,
                        callSign, registryNumber, timeOfArrival);

        JSONArray data = finder
                .findVmaItinerary_VmaScheduleAnchorage(itineraryNo, nameOfShip,
                        purposeCode, anchoringDateFrom, anchoringDateTo,
                        anchoringPortRegionCode, anchoringPortHarbourCode,
                        anchoringPortWharfCode, shipOwnerCode,
                        shipOperatorCode, shipAgencyCode, makePayment,
                        anchorageSupplement, documentaryCode,
                        itineraryScheduleId, shipPosition, flag, flag2,
                        imoNumber, callSign, registryNumber, timeOfArrival,
                        start, end);

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaItinerary_VmaScheduleAnchorage(String itineraryNo,
                                                       String nameOfShip, String purposeCode, String anchoringDateFrom,
                                                       String anchoringDateTo, String anchoringPortRegionCode,
                                                       String anchoringPortHarbourCode, String anchoringPortWharfCode,
                                                       String shipOwnerCode, String shipOperatorCode,
                                                       String shipAgencyCode, Integer makePayment,
                                                       Integer anchorageSupplement, String documentaryCode,
                                                       Long itineraryScheduleId, Integer shipPosition, String flag,
                                                       String imoNumber, String callSign, String registryNumber,
                                                       String timeOfArrival) throws SystemException {

        return finder
                .countVmaItinerary_VmaScheduleAnchorage(itineraryNo,
                        nameOfShip, purposeCode, anchoringDateFrom,
                        anchoringDateTo, anchoringPortRegionCode,
                        anchoringPortHarbourCode, anchoringPortWharfCode,
                        shipOwnerCode, shipOperatorCode, shipAgencyCode,
                        makePayment, anchorageSupplement, documentaryCode,
                        itineraryScheduleId, shipPosition, flag, imoNumber,
                        callSign, registryNumber, timeOfArrival);

    }

    public VmaScheduleAnchorage fetchByanchorageSupplement(
            long anchorageSupplement) {
        try {
            return persistence
                    .fetchByanchorageSupplement(anchorageSupplement);
        } catch (SystemException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public VmaScheduleAnchorage fetchByitineraryScheduleId(
            long itineraryScheduleId) {
        try {
            return persistence
                    .fetchByitineraryScheduleId(itineraryScheduleId);
        } catch (SystemException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<VmaScheduleAnchorage> findByitineraryNo_makePayment_anchorageSupplement(
            String itineraryNo, int makePayment, int anchorageSupplement) {
        try {
            return persistence
                    .findByitineraryNo_makePayment_anchorageSupplement(
                            itineraryNo, makePayment, anchorageSupplement);
        } catch (SystemException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<VmaScheduleAnchorage> findByitineraryNo_noticeShipType_makePayment(
            String itineraryNo, int noticeShipType, int makePayment) {
        try {
            return persistence
                    .findByitineraryNo_noticeShipType_makePayment(itineraryNo,
                            noticeShipType, makePayment);
        } catch (SystemException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public JSONArray getVmaScheduleAnchorageDuration(String itineraryNo,
                                                     int start, int end) throws SystemException {
        try {
            return finder.getVmaScheduleAnchorageDuration(
                    itineraryNo, start, end);
        } catch (SystemException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public VmaScheduleAnchorage findByitineraryScheduleId(
            long itineraryScheduleId) {
        try {
            return persistence
                    .findByitineraryScheduleId(itineraryScheduleId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<VmaScheduleAnchorage> findByItineraryNo_documentaryCode(
            String itineraryNo, String documentaryCode) {
        try {
            return persistence
                    .findByitineraryNo_documentaryCode(itineraryNo,
                            documentaryCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<VmaScheduleAnchorage> findByItineraryNo_noticeShipType(
            String itineraryNo, int noticeShipType) {
        try {
            return persistence
                    .findByitineraryNo_noticeShipType(itineraryNo,
                            noticeShipType);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}

