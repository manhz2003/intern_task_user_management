package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryScheduleLocalServiceUtil;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*; 
import vn.gt.portlet.phuongtien.VMAUtils;import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaScheduleTestingFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaScheduleTestingPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaScheduleTestingLocalServiceImpl { 
@Autowired VmaScheduleTestingPersistenceImpl persistence;
@Autowired VmaScheduleTestingFinderImpl finder;

	public VmaScheduleTesting createVmaScheduleTesting(long id) {
		return persistence.create(id);
	}

	public VmaScheduleTesting deleteVmaScheduleTesting(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaScheduleTesting deleteVmaScheduleTesting(VmaScheduleTesting VmaScheduleTesting)
		throws SystemException {
		return persistence.remove(VmaScheduleTesting);
	}

	public VmaScheduleTesting fetchVmaScheduleTesting(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaScheduleTesting getVmaScheduleTesting(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaScheduleTesting> getVmaScheduleTestings(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaScheduleTestingsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaScheduleTesting updateVmaScheduleTesting(VmaScheduleTesting VmaScheduleTesting,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaScheduleTesting, merge);
	}
public VmaScheduleTesting addVmaScheduleTesting(
        VmaScheduleTesting vmaScheduleTesting) throws SystemException {

    long id = CounterLocalServiceUtil.increment(VmaScheduleTesting.class
            .getName());

    vmaScheduleTesting.setId(id);
    vmaScheduleTesting.setModifiedDate(new Date());
    // VMAUtils.formatUnicode(vmaScheduleTesting);
    return persistence.updateImpl(vmaScheduleTesting, false);

}

    public VmaScheduleTesting delete(long id) throws SystemException,
            NoSuchVmaScheduleTestingException {
        return persistence.remove(id);
    }

    public VmaScheduleTesting updateVmaScheduleTesting(
            VmaScheduleTesting vmaScheduleTesting) throws SystemException {
        vmaScheduleTesting.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaScheduleTesting);
        VmaItinerarySchedule vmaItinerarySchedule = null;
        VmaItinerary vmaItinerary = null;

        try {

            vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
                    .findByitineraryNo_certificateNo_noticeShipType(
                            vmaScheduleTesting.getItineraryNo(), 5,
                            vmaScheduleTesting.getCertificateNo());

            vmaItinerary = VmaItineraryLocalServiceUtil
                    .findByitineraryNo(vmaScheduleTesting.getItineraryNo());
        } catch (Exception e) {
            // Nothing todo
        }

        if (vmaItinerarySchedule == null) {
            if (vmaScheduleTesting.getRequestState() == 4) {
                vmaItinerarySchedule = VMAUtils.updateVmaItinerarySchedule(
                        null, vmaScheduleTesting, vmaItinerary);
                vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
                        .addVmaItinerarySchedule(vmaItinerarySchedule);
                vmaScheduleTesting.setItineraryScheduleId(vmaItinerarySchedule.getId());

            }
        } else {
            if (vmaScheduleTesting.getRequestState() == 4) {
                vmaItinerarySchedule = VMAUtils.updateVmaItinerarySchedule(
                        null, vmaScheduleTesting, vmaItinerary);
                VmaItineraryScheduleLocalServiceUtil.delete(vmaItinerarySchedule);
                vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
                        .addVmaItinerarySchedule(vmaItinerarySchedule);

                vmaScheduleTesting.setItineraryScheduleId(vmaItinerarySchedule.getId());

            } else if (vmaScheduleTesting.getRequestState() == 6) {
                VmaItineraryScheduleLocalServiceUtil.delete(vmaItinerarySchedule);
                vmaScheduleTesting.setItineraryScheduleId(0L);
            }
        }

        return persistence.updateImpl(vmaScheduleTesting, false);

    }

    public List<VmaScheduleTesting> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public List<VmaScheduleTesting> findByItineraryNo_NoticeShipType(
            String itineraryNo, int noticeShipType) throws SystemException {
        return persistence.findByitineraryNo_noticeShipType(
                itineraryNo, noticeShipType);
    }

    public int countByItineraryNo_NoticeShipType(String itineraryNo,
                                                 int noticeShipType) throws SystemException {
        return persistence.countByitineraryNo_noticeShipType(
                itineraryNo, noticeShipType);
    }

    public JSONObject findVmaScheduleTesting(String itineraryNo,
                                             String portofAuthority, Long documentName, Integer documentYear,
                                             Integer noticeShipType, String testingFrom, String testingTo,
                                             String nameOfShip, String flagStateOfShip, String imoNumber,
                                             String callSign, String vrCode, String registryNumber,
                                             Double shownDraftxF, Double shownDraftxA, Double loa, Double dwt,
                                             int start, int end) throws SystemException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder.countVmaScheduleTesting(
                itineraryNo, portofAuthority, documentName, documentYear,
                noticeShipType, testingFrom, testingTo, nameOfShip,
                flagStateOfShip, imoNumber, callSign, vrCode, registryNumber,
                shownDraftxF, shownDraftxA, loa, dwt);

        JSONArray data = finder.findVmaScheduleTesting(
                itineraryNo, portofAuthority, documentName, documentYear,
                noticeShipType, testingFrom, testingTo, nameOfShip,
                flagStateOfShip, imoNumber, callSign, vrCode, registryNumber,
                shownDraftxF, shownDraftxA, loa, dwt, start, end);

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public JSONObject findVmaScheduleTesting(String searchQuery,
                                             String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaScheduleTesting(countQuery);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        List<VmaScheduleTesting> vmaScheduleTestings = finder
                .findVmaScheduleTesting(VmaScheduleTesting.class,
                        "VmaScheduleTesting", searchQuery, start, end);

        if (vmaScheduleTestings != null) {
            for (VmaScheduleTesting vmaScheduleTesting : vmaScheduleTestings) {

                JSONObject object = VMAUtils.object2Json(vmaScheduleTesting,
                        VmaScheduleTesting.class, new String[] {
                                "timeOfArrival", "timeOfDeparture" });

                data.put(object);
            }
        }

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaScheduleTesting(String itineraryNo,
                                        String portofAuthority, Long documentName, Integer documentYear,
                                        Integer noticeShipType, String testingFrom, String testingTo,
                                        String nameOfShip, String flagStateOfShip, String imoNumber,
                                        String callSign, String vrCode, String registryNumber,
                                        Double shownDraftxF, Double shownDraftxA, Double loa, Double dwt)
            throws SystemException {

        return finder.countVmaScheduleTesting(itineraryNo,
                portofAuthority, documentName, documentYear, noticeShipType,
                testingFrom, testingTo, nameOfShip, flagStateOfShip, imoNumber,
                callSign, vrCode, registryNumber, shownDraftxF, shownDraftxA,
                loa, dwt);

    }

    public long countVmaScheduleTesting(String sql) throws SystemException {

        return finder.countVmaScheduleTesting(sql);

    }

    public VmaScheduleTesting fetchByitineraryNo_noticeShipType_certificateNo(
            String itineraryNo, int noticeShipType, String certificateNo) {
        try {
            return persistence
                    .fetchByitineraryNo_noticeShipType_certificateNo(itineraryNo, noticeShipType, certificateNo);
        } catch (Exception e) {
            return null;
        }
    }

}

