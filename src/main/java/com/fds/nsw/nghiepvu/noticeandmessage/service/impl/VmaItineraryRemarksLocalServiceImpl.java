package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaItineraryRemarksFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaItineraryRemarksPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import vn.gt.portlet.phuongtien.VMAUtils;

@Slf4j @Service
public class VmaItineraryRemarksLocalServiceImpl { 
@Autowired VmaItineraryRemarksPersistenceImpl persistence;
@Autowired VmaItineraryRemarksFinderImpl finder;


	public VmaItineraryRemark createVmaItineraryRemarks(long id) {
		return persistence.create(id);
	}

	public VmaItineraryRemark deleteVmaItineraryRemarks(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaItineraryRemark deleteVmaItineraryRemarks(VmaItineraryRemark VmaItineraryRemark)
		throws SystemException {
		return persistence.remove(VmaItineraryRemark);
	}

	public VmaItineraryRemark fetchVmaItineraryRemarks(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaItineraryRemark getVmaItineraryRemarks(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaItineraryRemark> getVmaItineraryRemarkses(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaItineraryRemarksesCount() throws SystemException {
		return persistence.countAll();
	}


	public VmaItineraryRemark updateVmaItineraryRemarks(VmaItineraryRemark VmaItineraryRemark,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaItineraryRemark, merge);
	}
public VmaItineraryRemark addVmaItineraryRemarks(
        VmaItineraryRemark vmaItineraryRemarks) throws SystemException {

    long id = CounterLocalServiceUtil.increment(VmaItineraryRemark.class
            .getName());

    vmaItineraryRemarks.setId(id);

    long sequenceNo = CounterLocalServiceUtil.increment(
            "VMA_ITINERARY_REMARKS_SEQUENCE_NO", 1);

    vmaItineraryRemarks.setSequenceNo((int) sequenceNo);
    vmaItineraryRemarks.setModifiedDate(new Date());
    //VMAUtils.formatUnicode(vmaItineraryRemarks);
    return persistence
            .updateImpl(vmaItineraryRemarks, false);

}

    public VmaItineraryRemark delete(long id) throws SystemException,
            NoSuchVmaItineraryRemarksException {
        return persistence.remove(id);
    }

    public VmaItineraryRemark updateVmaItineraryRemarks(
            VmaItineraryRemark vmaItineraryRemarks) throws SystemException {
        vmaItineraryRemarks.setModifiedDate(new Date());
        //VMAUtils.formatUnicode(vmaItineraryRemarks);
        return persistence
                .updateImpl(vmaItineraryRemarks, false);

    }

    public List<VmaItineraryRemark> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public VmaItineraryRemark findByItineraryNo_NoticeShipType_SequenceNo(
            String itineraryNo, int noticeShipType, int sequenceNo)
            throws SystemException, NoSuchVmaItineraryRemarksException {
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

    public List<VmaItineraryRemark> findByItineraryNo_NoticeShipType(
            String itineraryNo, int noticeShipType) throws SystemException {
        return persistence.findByitineraryNo_noticeShipType(
                itineraryNo, noticeShipType);
    }

    public int countByItineraryNo_NoticeShipType(String itineraryNo,
                                                 int noticeShipType) throws SystemException {
        return persistence
                .countByitineraryNo_noticeShipType(itineraryNo, noticeShipType);
    }

    public JSONObject findVmaItineraryRemarks(String itineraryNo,
                                              String portofAuthority, String nameOfShip, Long documentName,
                                              Integer documentYear, Integer noticeShipType, String requestDate,
                                              String requestPerson, String remarks, Integer markedAsPending,
                                              int start, int end) throws SystemException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder.countVmaItineraryRemarks(
                itineraryNo, portofAuthority, nameOfShip, documentName,
                documentYear, noticeShipType, requestDate, requestPerson,
                remarks, markedAsPending);

        JSONArray data = finder.findVmaItineraryRemarks(
                itineraryNo, portofAuthority, nameOfShip, documentName,
                documentYear, noticeShipType, requestDate, requestPerson,
                remarks, markedAsPending, start, end);

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public JSONObject findVmaItineraryRemarks(String searchQuery,
                                              String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaItineraryRemarks(countQuery);

        List<VmaItineraryRemark> vmaItineraryRemarks = finder
                .findVmaItineraryRemarks(VmaItineraryRemark.class,
                        "VmaItineraryRemark", searchQuery, start, end);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        for (VmaItineraryRemark vmaItineraryRemark : vmaItineraryRemarks) {

            JSONObject object = VMAUtils.object2Json(vmaItineraryRemark,
                    VmaItineraryRemark.class);

            data.put(object);
        }

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaItineraryRemarks(String itineraryNo,
                                         String portofAuthority, String nameOfShip, Long documentName,
                                         Integer documentYear, Integer noticeShipType, String requestDate,
                                         String requestPerson, String remarks, Integer markedAsPending)
            throws SystemException {

        return finder.countVmaItineraryRemarks(itineraryNo,
                portofAuthority, nameOfShip, documentName, documentYear,
                noticeShipType, requestDate, requestPerson, remarks,
                markedAsPending);

    }

    public long countVmaItineraryRemarks(String sql) throws SystemException {
        return finder.countVmaItineraryRemarks(sql);
    }

}

