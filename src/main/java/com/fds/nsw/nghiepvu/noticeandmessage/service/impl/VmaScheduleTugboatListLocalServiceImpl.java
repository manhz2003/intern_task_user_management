package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*; 
import vn.gt.portlet.phuongtien.VMAUtils;import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import java.util.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import org.json.*;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.kernel.util.OrderByComparator;
import org.springframework.beans.factory.annotation.Autowired;
import com.fds.nsw.kernel.exception.PortalException;
import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaScheduleTugboatListFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaScheduleTugboatListPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaScheduleTugboatListLocalServiceImpl { 
@Autowired VmaScheduleTugboatListPersistenceImpl persistence;
@Autowired VmaScheduleTugboatListFinderImpl finder;

	public VmaScheduleTugboatList createVmaScheduleTugboatList(long id) {
		return persistence.create(id);
	}

	public VmaScheduleTugboatList deleteVmaScheduleTugboatList(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaScheduleTugboatList deleteVmaScheduleTugboatList(VmaScheduleTugboatList VmaScheduleTugboatList)
		throws SystemException {
		return persistence.remove(VmaScheduleTugboatList);
	}

	public VmaScheduleTugboatList fetchVmaScheduleTugboatList(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaScheduleTugboatList getVmaScheduleTugboatList(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaScheduleTugboatList> getVmaScheduleTugboatLists(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaScheduleTugboatListsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaScheduleTugboatList updateVmaScheduleTugboatList(VmaScheduleTugboatList VmaScheduleTugboatList,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaScheduleTugboatList, merge);
	}
public VmaScheduleTugboatList addVmaScheduleTugboatList(
        VmaScheduleTugboatList vmaScheduleTugboatList)
        throws SystemException {

    long id = CounterLocalServiceUtil.increment(VmaScheduleTugboatList.class
            .getName());

    vmaScheduleTugboatList.setId(id);
    vmaScheduleTugboatList.setModifiedDate(new Date());
    // VMAUtils.formatUnicode(vmaScheduleTugboatList);
    return persistence.updateImpl(vmaScheduleTugboatList,
            false);

}

    public VmaScheduleTugboatList delete(long id) throws SystemException,
            NoSuchVmaScheduleTugboatListException {
        return persistence.remove(id);
    }

    public VmaScheduleTugboatList updateVmaScheduleTugboatList(
            VmaScheduleTugboatList vmaScheduleTugboatList)
            throws SystemException {
        vmaScheduleTugboatList.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaScheduleTugboatList);
        return persistence.updateImpl(vmaScheduleTugboatList,
                false);

    }

    public List<VmaScheduleTugboatList> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence
                .countByitineraryNo(itineraryNo);
    }

    public VmaScheduleTugboatList findByItineraryNo_SequenceNo_ShipCode(
            String itineraryNo, int sequenceNo, String shipCode)
            throws SystemException, NoSuchVmaScheduleTugboatListException {
        return persistence
                .findByitineraryNo_sequenceNo_shipCode(itineraryNo, sequenceNo,
                        shipCode);
    }

    public int countByItineraryNo_SequenceNo_ShipCode(String itineraryNo,
                                                      int sequenceNo, String shipCode) throws SystemException {
        return persistence
                .countByitineraryNo_sequenceNo_shipCode(itineraryNo,
                        sequenceNo, shipCode);
    }

    public List<VmaScheduleTugboatList> findByItineraryNo_SequenceNo(
            String itineraryNo, int sequenceNo) throws SystemException {
        return persistence.findByitineraryNo_sequenceNo(
                itineraryNo, sequenceNo);
    }

    public int countByItineraryNo_SequenceNo(String itineraryNo, int sequenceNo)
            throws SystemException {
        return persistence.countByitineraryNo_sequenceNo(
                itineraryNo, sequenceNo);
    }

    public JSONObject findScheduleTugboatList(
            LinkedHashMap<String, Class<?>> colMap, String searchQuery,
            String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaScheduleTugboatList(countQuery);

        JSONArray data = finder
                .findVmaScheduleTugboatList(colMap, null, "", searchQuery,
                        start, end);

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaScheduleTugboatList(String sql) throws SystemException {

        return finder.countVmaScheduleTugboatList(sql);
    }

    public List<VmaScheduleTugboatList> getByShipCode_MakePayment(
            String shipCode, int makePayment) {
        try {
            return persistence
                    .findByshipCode_makePayment(shipCode, makePayment);
        } catch (SystemException e) {
            log.error(e.getMessage());
            ;
            return null;
        }
    }

}

