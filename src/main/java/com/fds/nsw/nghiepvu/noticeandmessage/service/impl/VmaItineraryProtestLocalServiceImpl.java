package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaItineraryProtestFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaItineraryProtestPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaItineraryProtestLocalServiceImpl { 
@Autowired VmaItineraryProtestPersistenceImpl persistence;
@Autowired VmaItineraryProtestFinderImpl finder;

	public VmaItineraryProtest createVmaItineraryProtest(long id) {
		return persistence.create(id);
	}

	public VmaItineraryProtest deleteVmaItineraryProtest(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaItineraryProtest deleteVmaItineraryProtest(VmaItineraryProtest VmaItineraryProtest)
		throws SystemException {
		return persistence.remove(VmaItineraryProtest);
	}

	public VmaItineraryProtest fetchVmaItineraryProtest(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaItineraryProtest getVmaItineraryProtest(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaItineraryProtest> getVmaItineraryProtests(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaItineraryProtestsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaItineraryProtest updateVmaItineraryProtest(VmaItineraryProtest VmaItineraryProtest,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaItineraryProtest, merge);
	}

    public VmaItineraryProtest addVmaItineraryProtest(
            VmaItineraryProtest vmaItineraryProtest) throws SystemException {

        long id = CounterLocalServiceUtil.increment(VmaItineraryProtest.class
                .getName());

        vmaItineraryProtest.setId(id);

        long sequenceNo = CounterLocalServiceUtil.increment(
                "VMA_ITINERARY_PROTEST_SEQUENCE_NO", 1);

        vmaItineraryProtest.setSequenceNo((int) sequenceNo);
        vmaItineraryProtest.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaItineraryProtest);
        return persistence
                .updateImpl(vmaItineraryProtest, false);

    }

    public VmaItineraryProtest delete(long id) throws SystemException,
            NoSuchVmaItineraryProtestException {
        return persistence.remove(id);
    }

    public VmaItineraryProtest updateVmaItineraryProtest(
            VmaItineraryProtest vmaItineraryProtest) throws SystemException {
        vmaItineraryProtest.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaItineraryProtest);
        return persistence
                .updateImpl(vmaItineraryProtest, false);

    }

    public List<VmaItineraryProtest> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public VmaItineraryProtest findByItineraryNo_NoticeShipType_SequenceNo(
            String itineraryNo, int noticeShipType, int sequenceNo)
            throws SystemException, NoSuchVmaItineraryProtestException {
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

    public List<VmaItineraryProtest> findByItineraryNo_NoticeShipType(
            String itineraryNo, int noticeShipType) throws SystemException {
        return persistence.findByitineraryNo_noticeShipType(
                itineraryNo, noticeShipType);
    }

    public int countByItineraryNo_NoticeShipType(String itineraryNo,
                                                 int noticeShipType) throws SystemException {
        return persistence
                .countByitineraryNo_noticeShipType(itineraryNo, noticeShipType);
    }

    public JSONObject findVmaItineraryProtest(String itineraryNo,
                                              String portofAuthority, String nameOfShip, Long documentName,
                                              Integer documentYear, Integer noticeShipType,
                                              String shipOperatorCode, String shipAgencyCode, String protestDate,
                                              String protestRemarks, Integer makePayment, int start, int end)
            throws SystemException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder.countVmaItineraryProtest(
                itineraryNo, portofAuthority, nameOfShip, documentName,
                documentYear, noticeShipType, shipOperatorCode, shipAgencyCode,
                protestDate, protestRemarks, makePayment);

        JSONArray data = finder.findVmaItineraryProtest(
                itineraryNo, portofAuthority, nameOfShip, documentName,
                documentYear, noticeShipType, shipOperatorCode, shipAgencyCode,
                protestDate, protestRemarks, makePayment, start, end);

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public JSONObject findVmaItineraryProtest(String searchQuery,
                                              String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaItineraryProtest(countQuery);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        List<VmaItineraryProtest> vmaItineraryProtests = finder
                .findVmaItineraryProtest(VmaItineraryProtest.class,
                        "VmaItineraryProtest", searchQuery, start, end);

        if (vmaItineraryProtests != null) {
            for (VmaItineraryProtest vmaItineraryProtest : vmaItineraryProtests) {

                JSONObject object = VMAUtils.object2Json(vmaItineraryProtest,
                        VmaItineraryProtest.class);

                data.put(object);
            }
        }

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaItineraryProtest(String itineraryNo,
                                         String portofAuthority, String nameOfShip, Long documentName,
                                         Integer documentYear, Integer noticeShipType,
                                         String shipOperatorCode, String shipAgencyCode, String protestDate,
                                         String protestRemarks, Integer makePayment) throws SystemException {

        return finder.countVmaItineraryProtest(itineraryNo,
                portofAuthority, nameOfShip, documentName, documentYear,
                noticeShipType, shipOperatorCode, shipAgencyCode, protestDate,
                protestRemarks, makePayment);

    }

    public long countVmaItineraryProtest(String sql) throws SystemException {

        return finder.countVmaItineraryProtest(sql);

    }

    public List<VmaItineraryProtest> findByItineraryNo_documentaryCode(
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

}

