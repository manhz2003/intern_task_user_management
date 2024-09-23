package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import vn.gt.dao.noticeandmessage.service.VmaScheduleMergingLocalServiceUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import vn.gt.portlet.phuongtien.VMAUtils;
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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaScheduleMergingFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaScheduleMergingPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaScheduleMergingLocalServiceImpl { 
@Autowired VmaScheduleMergingPersistenceImpl persistence;
@Autowired VmaScheduleMergingFinderImpl finder;


	public VmaScheduleMerging createVmaScheduleMerging(long id) {
		return persistence.create(id);
	}

	public VmaScheduleMerging deleteVmaScheduleMerging(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaScheduleMerging deleteVmaScheduleMerging(VmaScheduleMerging VmaScheduleMerging)
		throws SystemException {
		return persistence.remove(VmaScheduleMerging);
	}

	public VmaScheduleMerging fetchVmaScheduleMerging(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaScheduleMerging getVmaScheduleMerging(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaScheduleMerging> getVmaScheduleMergings(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaScheduleMergingsCount() throws SystemException {
		return persistence.countAll();
	}


	public VmaScheduleMerging updateVmaScheduleMerging(VmaScheduleMerging VmaScheduleMerging,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaScheduleMerging, merge);
	}

    public VmaScheduleMerging addVmaScheduleMerging(
            VmaScheduleMerging vmaScheduleMerging) throws SystemException {

        long id = CounterLocalServiceUtil.increment(VmaScheduleMerging.class
                .getName());

        vmaScheduleMerging.setId(id);

        long sequenceNo = CounterLocalServiceUtil.increment(
                "VMA_SCHEDULE_MERGING_SEQUENCE_NO", 1);

        vmaScheduleMerging.setSequenceNo((int) sequenceNo);

        vmaScheduleMerging.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaScheduleMerging);
        return persistence.updateImpl(vmaScheduleMerging, false);
    }

    public VmaScheduleMerging delete(long id) throws SystemException,
            NoSuchVmaScheduleMergingException {
        return persistence.remove(id);
    }

    public VmaScheduleMerging updateVmaScheduleMerging(
            VmaScheduleMerging vmaScheduleMerging) throws SystemException {
        vmaScheduleMerging.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaScheduleMerging);
        return persistence.updateImpl(vmaScheduleMerging, false);
    }

    public List<VmaScheduleMerging> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public List<VmaScheduleMerging> findByItineraryNo_NoticeShipType(
            String itineraryNo, int noticeShipType) throws SystemException {
        return persistence.findByitineraryNo_noticeShipType(
                itineraryNo, noticeShipType);
    }

    public int countByItineraryNo_NoticeShipType(String itineraryNo,
                                                 int noticeShipType) throws SystemException {
        return persistence.countByitineraryNo_noticeShipType(
                itineraryNo, noticeShipType);
    }

    public JSONObject findVmaScheduleMerging(String itineraryNo,
                                             String portofAuthority, Long documentName, Integer documentYear,
                                             Integer noticeShipType, String shipOwnerCode,
                                             String shipOwnersName, String nameOfShip, String flagStateOfShip,
                                             String imoNumber, String callSign, String vrCode,
                                             String registryNumber, String shipOperatorCode,
                                             String shipOperatorName, String shipAgencyCode,
                                             String shipAgencyName, Double gt, Double nt, Double shownDraftxF,
                                             Double shownDraftxA, Double loa, Double dwt,
                                             long itineraryScheduleId, int start, int end)
            throws SystemException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder.countVmaScheduleMerging(
                itineraryNo, portofAuthority, documentName, documentYear,
                noticeShipType, shipOwnerCode, shipOwnersName, nameOfShip,
                flagStateOfShip, imoNumber, callSign, vrCode, registryNumber,
                shipOperatorCode, shipOperatorName, shipAgencyCode,
                shipAgencyName, gt, nt, shownDraftxF, shownDraftxA, loa, dwt,
                itineraryScheduleId);

        JSONArray data = finder.findVmaScheduleMerging(
                itineraryNo, portofAuthority, documentName, documentYear,
                noticeShipType, shipOwnerCode, shipOwnersName, nameOfShip,
                flagStateOfShip, imoNumber, callSign, vrCode, registryNumber,
                shipOperatorCode, shipOperatorName, shipAgencyCode,
                shipAgencyName, gt, nt, shownDraftxF, shownDraftxA, loa, dwt,
                itineraryScheduleId, start, end);

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaScheduleMerging(String itineraryNo,
                                        String portofAuthority, Long documentName, Integer documentYear,
                                        Integer noticeShipType, String shipOwnerCode,
                                        String shipOwnersName, String nameOfShip, String flagStateOfShip,
                                        String imoNumber, String callSign, String vrCode,
                                        String registryNumber, String shipOperatorCode,
                                        String shipOperatorName, String shipAgencyCode,
                                        String shipAgencyName, Double gt, Double nt, Double shownDraftxF,
                                        Double shownDraftxA, Double loa, Double dwt,
                                        long itineraryScheduleId) throws SystemException {

        return finder.countVmaScheduleMerging(itineraryNo,
                portofAuthority, documentName, documentYear, noticeShipType,
                shipOwnerCode, shipOwnersName, nameOfShip, flagStateOfShip,
                imoNumber, callSign, vrCode, registryNumber, shipOperatorCode,
                shipOperatorName, shipAgencyCode, shipAgencyName, gt, nt,
                shownDraftxF, shownDraftxA, loa, dwt, itineraryScheduleId);

    }

    public JSONObject findVmaScheduleMerging(String searchQuery,
                                             String countQuery, int start, int end) throws JSONException,
            SystemException {
        List<VmaScheduleMerging> vmaScheduleMergings = finder
                .findVmaScheduleMerging(VmaScheduleMerging.class,
                        "VmaScheduleMerging", searchQuery, start, end);

        long count = finder
                .countVmaScheduleMerging(countQuery);

        JSONObject result = JSONFactoryUtil.createJSONObject();

        JSONArray data = JSONFactoryUtil.createJSONArray();

        if (vmaScheduleMergings != null) {

            for (VmaScheduleMerging vmaScheduleMerging : vmaScheduleMergings) {
                JSONObject object = VMAUtils.object2Json(vmaScheduleMerging,
                        VmaScheduleMerging.class);
                data.put(object);
            }

        }

        result.put("total", count);

        result.put("data", data);

        return result;
    }

    public List<VmaScheduleMerging> fetchByItineraryScheduleId(
            long itineraryScheduleId, int start, int end) {
        try {
            return persistence.findByitineraryScheduleId(
                    itineraryScheduleId, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public JSONObject updateVmaScheduleMergings(List<VmaScheduleMerging> vmaScheduleMergings, long itineraryScheduleId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        if(vmaScheduleMergings != null && !vmaScheduleMergings.isEmpty()){
            JSONArray array = JSONFactoryUtil.createJSONArray();
            try {
                persistence
                        .removeByitineraryScheduleId(itineraryScheduleId);
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            for(VmaScheduleMerging vmaScheduleMerging : vmaScheduleMergings){
                vmaScheduleMerging.setItineraryScheduleId(itineraryScheduleId);
                try {
                    //vmaScheduleMerging = persistence.updateImpl(vmaScheduleMerging, false);
                    VmaScheduleMergingLocalServiceUtil.addVmaScheduleMerging(vmaScheduleMerging);
                }catch(Exception e){
                    log.error(e.getMessage());
                    //Rollback
                    try {
                        persistence.removeByitineraryScheduleId(itineraryScheduleId);
                    } catch (Exception ex) {
                        log.error(ex.getMessage());
                    }
                    return JSONFactoryUtil.createJSONObject();
                }
                try {
                    JSONObject object = VMAUtils.object2Json(vmaScheduleMerging, VmaScheduleMerging.class);
                    array.put(object);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
            result.put("data", array);
        }
        return result;
    }


}

