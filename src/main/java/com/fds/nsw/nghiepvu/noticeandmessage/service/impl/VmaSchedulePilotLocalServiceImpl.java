package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotListLocalServiceUtil;
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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaSchedulePilotFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaSchedulePilotPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaSchedulePilotLocalServiceImpl { 
@Autowired VmaSchedulePilotPersistenceImpl persistence;
@Autowired VmaSchedulePilotFinderImpl finder;

	public VmaSchedulePilot createVmaSchedulePilot(long id) {
		return persistence.create(id);
	}

	public VmaSchedulePilot deleteVmaSchedulePilot(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaSchedulePilot deleteVmaSchedulePilot(VmaSchedulePilot VmaSchedulePilot)
		throws SystemException {
		return persistence.remove(VmaSchedulePilot);
	}

	public VmaSchedulePilot fetchVmaSchedulePilot(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaSchedulePilot getVmaSchedulePilot(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaSchedulePilot> getVmaSchedulePilots(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaSchedulePilotsCount() throws SystemException {
		return persistence.countAll();
	}
    
	public VmaSchedulePilot updateVmaSchedulePilot(VmaSchedulePilot VmaSchedulePilot,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaSchedulePilot, merge);
	}

    public VmaSchedulePilot addVmaSchedulePilot(
            VmaSchedulePilot vmaSchedulePilot) throws SystemException {

        long id = CounterLocalServiceUtil.increment(VmaSchedulePilot.class
                .getName());

        long sequenceNo = CounterLocalServiceUtil.increment(
                "VMA_SCHEDULE_PILOT_SEQUENCE_NO", 1);

        vmaSchedulePilot.setSequenceNo((int) sequenceNo);

        vmaSchedulePilot.setId(id);
        vmaSchedulePilot.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaSchedulePilot);
        return persistence.updateImpl(vmaSchedulePilot, false);
    }

    public JSONObject addVmaSchedulePilot_VmaSchedulePilotLists(
            VmaSchedulePilot vmaSchedulePilot,
            List<VmaSchedulePilotList> vmaSchedulePilotLists)
            throws SystemException, PortalException {

        long id = CounterLocalServiceUtil.increment(VmaSchedulePilot.class
                .getName());

        long sequenceNo = CounterLocalServiceUtil.increment(
                "VMA_SCHEDULE_PILOT_SEQUENCE_NO", 1);

        vmaSchedulePilot.setId(id);

        vmaSchedulePilot.setSequenceNo((int) sequenceNo);
        vmaSchedulePilot.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaSchedulePilot);
        vmaSchedulePilot = persistence.updateImpl(vmaSchedulePilot,
                false);

        JSONObject result = VMAUtils.object2Json(vmaSchedulePilot,
                VmaSchedulePilot.class);

        JSONArray array = JSONFactoryUtil.createJSONArray();

        if (vmaSchedulePilotLists != null) {
            for (VmaSchedulePilotList vmaSchedulePilotList : vmaSchedulePilotLists) {
                vmaSchedulePilotList.setSequenceNo((int) sequenceNo);
                vmaSchedulePilotList = VmaSchedulePilotListLocalServiceUtil
                        .addVmaSchedulePilotList(vmaSchedulePilotList);

                JSONObject tmp = VMAUtils.object2Json(vmaSchedulePilotList,
                        VmaSchedulePilotList.class);

                array.put(tmp);
            }
        }

        result.put("pilotList", array);

        return result;

    }

    public JSONObject updateVmaSchedulePilot_VmaSchedulePilotLists(
            VmaSchedulePilot vmaSchedulePilot,
            List<VmaSchedulePilotList> vmaSchedulePilotLists)
            throws SystemException, PortalException {
        vmaSchedulePilot.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaSchedulePilot);
        vmaSchedulePilot = persistence.updateImpl(vmaSchedulePilot,
                false);

        JSONObject result = VMAUtils.object2Json(vmaSchedulePilot,
                VmaSchedulePilot.class);

        JSONArray array = JSONFactoryUtil.createJSONArray();

        List<VmaSchedulePilotList> oldVmaSchedulePilotLists = VmaSchedulePilotListLocalServiceUtil
                .findByItineraryNo_SequenceNo(
                        vmaSchedulePilot.getItineraryNo(),
                        vmaSchedulePilot.getSequenceNo());

        if (oldVmaSchedulePilotLists != null) {
            for (VmaSchedulePilotList vmaSchedulePilotList : oldVmaSchedulePilotLists) {
                VmaSchedulePilotListLocalServiceUtil
                        .deleteVmaSchedulePilotList(vmaSchedulePilotList
                                .getId());
            }
        }

        if (vmaSchedulePilotLists != null) {
            for (VmaSchedulePilotList vmaSchedulePilotList : vmaSchedulePilotLists) {
                vmaSchedulePilotList.setSequenceNo(vmaSchedulePilot
                        .getSequenceNo());
                vmaSchedulePilotList = VmaSchedulePilotListLocalServiceUtil
                        .addVmaSchedulePilotList(vmaSchedulePilotList);

                JSONObject tmp = VMAUtils.object2Json(vmaSchedulePilotList,
                        VmaSchedulePilotList.class);

                array.put(tmp);
            }
        }

        result.put("pilotList", array);

        return result;

    }

    public VmaSchedulePilot delete(long id) throws SystemException,
            NoSuchVmaSchedulePilotException {
        return persistence.remove(id);
    }

    public VmaSchedulePilot updateVmaSchedulePilot(
            VmaSchedulePilot vmaSchedulePilot) throws SystemException {
        vmaSchedulePilot.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaSchedulePilot);
        return persistence.updateImpl(vmaSchedulePilot, false);

    }

    public List<VmaSchedulePilot> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public VmaSchedulePilot findByItineraryNo_NoticeShipType_SequenceNo(
            String itineraryNo, int noticeShipType, int sequenceNo)
            throws SystemException, NoSuchVmaSchedulePilotException {
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

    public List<VmaSchedulePilot> findByItineraryNo_NoticeShipType(
            String itineraryNo, int noticeShipType) throws SystemException {
        return persistence.findByitineraryNo_noticeShipType(
                itineraryNo, noticeShipType);
    }

    public int countByItineraryNo_NoticeShipType(String itineraryNo,
                                                 int noticeShipType) throws SystemException {
        return persistence.countByitineraryNo_noticeShipType(
                itineraryNo, noticeShipType);
    }

    public JSONObject findVmaSchedulePilot(String itineraryNo,
                                           String portofAuthority, String nameOfShip, String certificateNo,
                                           Integer noticeShipType, String anchoringPortHarbourCode,
                                           String anchoringPortWharfCode, String shiftingPortRegionCode,
                                           String shiftingPortHarbourCode, String shiftingPortWharfCode,
                                           String pilotDateFrom, String pilotDateTo, int start, int end)
            throws SystemException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder.countVmaSchedulePilot(itineraryNo,
                portofAuthority, nameOfShip, certificateNo, noticeShipType,
                anchoringPortHarbourCode, anchoringPortWharfCode,
                shiftingPortRegionCode, shiftingPortHarbourCode,
                shiftingPortWharfCode, pilotDateFrom, pilotDateTo);

        JSONArray data = finder.findVmaSchedulePilot(
                itineraryNo, portofAuthority, nameOfShip, certificateNo,
                noticeShipType, anchoringPortHarbourCode,
                anchoringPortWharfCode, shiftingPortRegionCode,
                shiftingPortHarbourCode, shiftingPortWharfCode, pilotDateFrom,
                pilotDateTo, start, end);

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public JSONObject findVmaSchedulePilot(String searchQuery,
                                           String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        JSONArray data = JSONFactoryUtil.createJSONArray();

        long total = finder.countVmaSchedulePilot(countQuery);

        List<VmaSchedulePilot> vmaSchedulePilots = finder
                .findVmaSchedulePilot(VmaSchedulePilot.class,
                        "VmaSchedulePilot", searchQuery, start, end);

        if (vmaSchedulePilots != null) {
            for (VmaSchedulePilot vmaSchedulePilot : vmaSchedulePilots) {
                List<VmaSchedulePilotList> vmaSchedulePilotLists = VmaSchedulePilotListLocalServiceUtil
                        .findByItineraryNo_SequenceNo(
                                vmaSchedulePilot.getItineraryNo(),
                                vmaSchedulePilot.getSequenceNo());

                JSONArray pilotList = JSONFactoryUtil.createJSONArray();

                if (vmaSchedulePilotLists != null) {
                    for (VmaSchedulePilotList vmaSchedulePilotList : vmaSchedulePilotLists) {
                        pilotList.put(VMAUtils.object2Json(
                                vmaSchedulePilotList,
                                VmaSchedulePilotList.class));
                    }
                }

                JSONObject object = VMAUtils.object2Json(vmaSchedulePilot,
                        VmaSchedulePilot.class);

                object.put("pilotList", pilotList);

                data.put(object);
            }
        }

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaSchedulePilot(String itineraryNo,
                                      String portofAuthority, String nameOfShip, String certificateNo,
                                      Integer noticeShipType, String anchoringPortHarbourCode,
                                      String anchoringPortWharfCode, String shiftingPortRegionCode,
                                      String shiftingPortHarbourCode, String shiftingPortWharfCode,
                                      String pilotDateFrom, String pilotDateTo) throws SystemException {

        return finder.countVmaSchedulePilot(itineraryNo,
                portofAuthority, nameOfShip, certificateNo, noticeShipType,
                anchoringPortHarbourCode, anchoringPortWharfCode,
                shiftingPortRegionCode, shiftingPortHarbourCode,
                shiftingPortWharfCode, pilotDateFrom, pilotDateTo);

    }

    public long countVmaSchedulePilot(String sql) throws SystemException {

        return finder.countVmaSchedulePilot(sql);

    }

    public VmaSchedulePilot findByItineraryNo_NoticeShipType_CertificateNo(
            String itineraryNo, int noticeShipType, String certificateNo)
            throws SystemException, NoSuchVmaSchedulePilotException {
        return persistence
                .fetchByitineraryNo_noticeShipType_certificateNo(itineraryNo,
                        noticeShipType, certificateNo);
    }

    public JSONArray getModelMau26_2T(String maritimeCode, String pilotCode,
                                      String startDate, String endDate) throws SystemException {
        return finder.getModelMau26_2T(maritimeCode, pilotCode,
                startDate, endDate);
    }

    public JSONArray getModelMau27T(String maritimeCode,
                                    String pilotCompanyCode, String startDate, String endDate)
            throws SystemException {
        return finder.getModelMau27T(maritimeCode,
                pilotCompanyCode, startDate, endDate);
    }

}

