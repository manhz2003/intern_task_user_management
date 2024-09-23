package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaScheduleTugboatListPersistenceImpl;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatLocalServiceUtil;
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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaScheduleTugboatFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaScheduleTugboatPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaScheduleTugboatLocalServiceImpl { 
@Autowired VmaScheduleTugboatPersistenceImpl persistence;
@Autowired VmaScheduleTugboatListPersistenceImpl persistenceList;
@Autowired VmaScheduleTugboatFinderImpl finder;

	public VmaScheduleTugboat createVmaScheduleTugboat(long id) {
		return persistence.create(id);
	}

	public VmaScheduleTugboat deleteVmaScheduleTugboat(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaScheduleTugboat deleteVmaScheduleTugboat(VmaScheduleTugboat VmaScheduleTugboat)
		throws SystemException {
		return persistence.remove(VmaScheduleTugboat);
	}

	public VmaScheduleTugboat fetchVmaScheduleTugboat(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaScheduleTugboat getVmaScheduleTugboat(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaScheduleTugboat> getVmaScheduleTugboats(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaScheduleTugboatsCount() throws SystemException {
		return persistence.countAll();
	}


	public VmaScheduleTugboat updateVmaScheduleTugboat(VmaScheduleTugboat VmaScheduleTugboat,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaScheduleTugboat, merge);
	}

    public VmaScheduleTugboat addVmaScheduleTugboat(
            VmaScheduleTugboat vmaScheduleTugboat) throws SystemException {

        long id = CounterLocalServiceUtil.increment(VmaScheduleTugboat.class
                .getName());

        vmaScheduleTugboat.setId(id);
        vmaScheduleTugboat.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaScheduleTugboat);
        return persistence.updateImpl(vmaScheduleTugboat, false);
    }

    public JSONObject addVmaScheduleTugboat_VmaScheduleTugboatLists(
            VmaScheduleTugboat vmaScheduleTugboat,
            List<VmaScheduleTugboatList> vmaScheduleTugboatLists)
            throws SystemException, PortalException {

        long id = CounterLocalServiceUtil.increment(VmaScheduleTugboat.class
                .getName());

        long sequenceNo = CounterLocalServiceUtil.increment(
                "VMA_SCHEDULE_TUGBOAT_SEQUENCE_NO", 1);

        vmaScheduleTugboat.setId(id);

        vmaScheduleTugboat.setSequenceNo((int) sequenceNo);
        vmaScheduleTugboat.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaScheduleTugboat);
        vmaScheduleTugboat = persistence.updateImpl(
                vmaScheduleTugboat, false);

        JSONObject result = VMAUtils.object2Json(vmaScheduleTugboat,
                VmaScheduleTugboat.class);

        JSONArray array = JSONFactoryUtil.createJSONArray();

        if (vmaScheduleTugboatLists != null) {
            for (VmaScheduleTugboatList vmaScheduleTugboatList : vmaScheduleTugboatLists) {
                vmaScheduleTugboatList.setSequenceNo((int) sequenceNo);
                vmaScheduleTugboatList = VmaScheduleTugboatListLocalServiceUtil
                        .addVmaScheduleTugboatList(vmaScheduleTugboatList);

                JSONObject tmp = VMAUtils.object2Json(vmaScheduleTugboatList,
                        VmaScheduleTugboatList.class);

                array.put(tmp);
            }
        }

        result.put("tugboatList", array);

        return result;

    }

    public JSONObject updateVmaScheduleTugboat_VmaScheduleTugboatLists(
            VmaScheduleTugboat vmaScheduleTugboat,
            List<VmaScheduleTugboatList> vmaScheduleTugboatLists)
            throws SystemException, PortalException {
        vmaScheduleTugboat.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaScheduleTugboat);
        vmaScheduleTugboat = persistence.updateImpl(
                vmaScheduleTugboat, false);

        JSONObject result = VMAUtils.object2Json(vmaScheduleTugboat,
                VmaScheduleTugboat.class);

        JSONArray array = JSONFactoryUtil.createJSONArray();

        List<VmaScheduleTugboatList> oldVmaScheduleTugboatLists = VmaScheduleTugboatListLocalServiceUtil
                .findByItineraryNo_SequenceNo(
                        vmaScheduleTugboat.getItineraryNo(),
                        vmaScheduleTugboat.getSequenceNo());

        if (oldVmaScheduleTugboatLists != null) {
            for (VmaScheduleTugboatList vmaScheduleTugboatList : oldVmaScheduleTugboatLists) {
                VmaScheduleTugboatListLocalServiceUtil
                        .deleteVmaScheduleTugboatList(vmaScheduleTugboatList
                                .getId());
            }
        }

        if (vmaScheduleTugboatLists != null) {
            for (VmaScheduleTugboatList vmaScheduleTugboatList : vmaScheduleTugboatLists) {
                vmaScheduleTugboatList.setSequenceNo(vmaScheduleTugboat
                        .getSequenceNo());
                vmaScheduleTugboatList = VmaScheduleTugboatListLocalServiceUtil
                        .addVmaScheduleTugboatList(vmaScheduleTugboatList);

                JSONObject tmp = VMAUtils.object2Json(vmaScheduleTugboatList,
                        VmaScheduleTugboatList.class);

                array.put(tmp);
            }
        }

        result.put("tugboatList", array);

        return result;

    }

    public VmaScheduleTugboat delete(long id) throws SystemException,
            NoSuchVmaScheduleTugboatException {
        return persistence.remove(id);
    }

    public VmaScheduleTugboat updateVmaScheduleTugboat(
            VmaScheduleTugboat vmaScheduleTugboat) throws SystemException {
        // VMAUtils.formatUnicode(vmaScheduleTugboat);
        return persistence.updateImpl(vmaScheduleTugboat, false);

    }

    public List<VmaScheduleTugboat> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public VmaScheduleTugboat findByItineraryNo_NoticeShipType_CertificateNo(
            String itineraryNo, int noticeShipType, String certificateNo)
            throws SystemException, NoSuchVmaScheduleTugboatException {
        return persistence
                .fetchByitineraryNo_noticeShipType_certificateNo(itineraryNo,
                        noticeShipType, certificateNo);
    }

    public int countByItineraryNo_NoticeShipType_CertificateNo(
            String itineraryNo, int noticeShipType, String certificateNo)
            throws SystemException {
        return persistence
                .countByitineraryNo_noticeShipType_certificateNo(itineraryNo,
                        noticeShipType, certificateNo);
    }

    public VmaScheduleTugboat findByItineraryNo_NoticeShipType_SequenceNo(
            String itineraryNo, int noticeShipType, int sequenceNo)
            throws SystemException, NoSuchVmaScheduleTugboatException {
        return persistence
                .findByitineraryNo_noticeShipType_sequenceNo(itineraryNo,
                        noticeShipType, sequenceNo);
    }

    public int countByItineraryNo_NoticeShipType_SequenceNo(String itineraryNo,
                                                            int noticeShipType, int sequenceNo) throws SystemException {
        return persistence
                .countByitineraryNo_noticeShipType_sequenceNo(itineraryNo,
                        noticeShipType, sequenceNo);
    }

    public List<VmaScheduleTugboat> findByItineraryNo_NoticeShipType(
            String itineraryNo, int noticeShipType) throws SystemException {
        return persistence.findByitineraryNo_noticeShipType(
                itineraryNo, noticeShipType);
    }

    public int countByItineraryNo_NoticeShipType(String itineraryNo,
                                                 int noticeShipType) throws SystemException {
        return persistence.countByitineraryNo_noticeShipType(
                itineraryNo, noticeShipType);
    }

    public JSONObject findScheduleTugboat(String itineraryNo,
                                          String nameOfShip, Integer noticeShipType,
                                          String anchoringPortHarbourCode, String anchoringPortWharfCode,
                                          String shiftingPortRegionCode, String shiftingPortHarbourCode,
                                          String shiftingPortWharfCode, String tugDateFrom, String tugDateTo,
                                          int start, int end) throws SystemException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder.countVmaScheduleTugboat(
                itineraryNo, nameOfShip, noticeShipType,
                anchoringPortHarbourCode, anchoringPortWharfCode,
                shiftingPortRegionCode, shiftingPortHarbourCode,
                shiftingPortWharfCode, tugDateFrom, tugDateTo);

        JSONArray data = finder.findVmaScheduleTugboat(
                itineraryNo, nameOfShip, noticeShipType,
                anchoringPortHarbourCode, anchoringPortWharfCode,
                shiftingPortRegionCode, shiftingPortHarbourCode,
                shiftingPortWharfCode, tugDateFrom, tugDateTo, start, end);

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public JSONObject findScheduleTugboat(String searchQuery,
                                          String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaScheduleTugboat(countQuery);

        List<VmaScheduleTugboat> vmaScheduleTugboats = finder
                .findVmaScheduleTugboat(VmaScheduleTugboat.class,
                        "VmaScheduleTugboat", searchQuery, start, end);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        if (vmaScheduleTugboats != null) {
            for (VmaScheduleTugboat vmaScheduleTugboat : vmaScheduleTugboats) {
                List<VmaScheduleTugboatList> vmaScheduleTugboatLists = VmaScheduleTugboatListLocalServiceUtil
                        .findByItineraryNo_SequenceNo(
                                vmaScheduleTugboat.getItineraryNo(),
                                vmaScheduleTugboat.getSequenceNo());

                JSONArray tugboatList = JSONFactoryUtil.createJSONArray();

                if (vmaScheduleTugboatLists != null) {
                    for (VmaScheduleTugboatList vmaScheduleTugboatList : vmaScheduleTugboatLists) {
                        tugboatList.put(VMAUtils.object2Json(
                                vmaScheduleTugboatList,
                                VmaScheduleTugboatList.class));
                    }
                }

                JSONObject tugboat = VMAUtils.object2Json(vmaScheduleTugboat,
                        VmaScheduleTugboat.class);

                tugboat.put("tugboatList", tugboatList);

                data.put(tugboat);
            }
        }
        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaScheduleTugboat(String itineraryNo, String nameOfShip,
                                        Integer noticeShipType, String anchoringPortHarbourCode,
                                        String anchoringPortWharfCode, String shiftingPortRegionCode,
                                        String shiftingPortHarbourCode, String shiftingPortWharfCode,
                                        String tugDateFrom, String tugDateTo) throws SystemException {

        return finder.countVmaScheduleTugboat(itineraryNo,
                nameOfShip, noticeShipType, anchoringPortHarbourCode,
                anchoringPortWharfCode, shiftingPortRegionCode,
                shiftingPortHarbourCode, shiftingPortWharfCode, tugDateFrom,
                tugDateTo);
    }

    public long countVmaScheduleTugboat(String sql) throws SystemException {

        return finder.countVmaScheduleTugboat(sql);
    }

    public JSONArray getModelMau24_2T(String maritimeCode, String shipCode,
                                      String startDate, String endDate) throws SystemException {
        return finder.getModelMau24_2T(maritimeCode,
                shipCode, startDate, endDate);
    }

    public JSONArray getModelMau25T(String maritimeCode,
                                    String tugboatCompanyCode, String startDate, String endDate)
            throws SystemException {
        return finder.getModelMau25T(maritimeCode,
                tugboatCompanyCode, startDate, endDate);
    }

    public void deleteVmaScheduleTugboat_VmaScheduleTugboatList(long id)
            throws SystemException, NoSuchVmaScheduleTugboatException {
        VmaScheduleTugboat vmaScheduleTugboat = VmaScheduleTugboatLocalServiceUtil
                .fetchVmaScheduleTugboat(id);
        if (vmaScheduleTugboat != null) {
            List<VmaScheduleTugboatList> vmaScheduleTugboatLists = VmaScheduleTugboatListLocalServiceUtil
                    .findByItineraryNo_SequenceNo(
                            vmaScheduleTugboat.getItineraryNo(),
                            vmaScheduleTugboat.getSequenceNo());
            for (VmaScheduleTugboatList vmaScheduleTugboatList : vmaScheduleTugboatLists) {
                persistenceList
                        .remove(vmaScheduleTugboatList);
            }
            persistence.remove(id);
        }
    }

    public VmaScheduleTugboat getByItineraryNo_SequenceNo(String itineraryNo,
                                                          int sequenceNo) {
        try {
            return persistence.findByitineraryNo_sequenceNo(
                    itineraryNo, sequenceNo);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


}

