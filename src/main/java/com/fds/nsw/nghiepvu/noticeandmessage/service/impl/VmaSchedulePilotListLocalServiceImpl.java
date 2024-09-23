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
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaSchedulePilotListPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaSchedulePilotListLocalServiceImpl { 
@Autowired VmaSchedulePilotListPersistenceImpl persistence;


	public VmaSchedulePilotList createVmaSchedulePilotList(long id) {
		return persistence.create(id);
	}

	public VmaSchedulePilotList deleteVmaSchedulePilotList(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaSchedulePilotList deleteVmaSchedulePilotList(VmaSchedulePilotList VmaSchedulePilotList)
		throws SystemException {
		return persistence.remove(VmaSchedulePilotList);
	}

	public VmaSchedulePilotList fetchVmaSchedulePilotList(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaSchedulePilotList getVmaSchedulePilotList(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaSchedulePilotList> getVmaSchedulePilotLists(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaSchedulePilotListsCount() throws SystemException {
		return persistence.countAll();
	}


	public VmaSchedulePilotList updateVmaSchedulePilotList(VmaSchedulePilotList VmaSchedulePilotList,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaSchedulePilotList, merge);
	}
public VmaSchedulePilotList addVmaSchedulePilotList(
        VmaSchedulePilotList vmaSchedulePilotList) throws SystemException {

    long id = CounterLocalServiceUtil.increment(VmaSchedulePilotList.class
            .getName());
    vmaSchedulePilotList.setId(id);
    vmaSchedulePilotList.setModifiedDate(new Date());
    //VMAUtils.formatUnicode(vmaSchedulePilotList);
    return persistence.updateImpl(vmaSchedulePilotList,
            false);

}

    public VmaSchedulePilotList delete(long id) throws SystemException,
            NoSuchVmaSchedulePilotListException {
        return persistence.remove(id);
    }

    public VmaSchedulePilotList updateVmaSchedulePilotList(
            VmaSchedulePilotList vmaSchedulePilotList) throws SystemException {
        vmaSchedulePilotList.setModifiedDate(new Date());
        //VMAUtils.formatUnicode(vmaSchedulePilotList);
        return persistence.updateImpl(vmaSchedulePilotList,
                false);

    }

    public List<VmaSchedulePilotList> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public VmaSchedulePilotList findByItineraryNo_SequenceNo_PilotCode(
            String itineraryNo, int sequenceNo, String pilotCode)
            throws SystemException, NoSuchVmaSchedulePilotListException {
        return persistence
                .findByitineraryNo_sequenceNo_pilotCode(itineraryNo,
                        sequenceNo, pilotCode);
    }

    public int countByItineraryNo_SequenceNo_PilotCode(String itineraryNo,
                                                       int sequenceNo, String pilotCode) throws SystemException {
        return persistence
                .countByitineraryNo_sequenceNo_pilotCode(itineraryNo,
                        sequenceNo, pilotCode);
    }

    public List<VmaSchedulePilotList> findByItineraryNo_SequenceNo(
            String itineraryNo, int sequenceNo) throws SystemException {
        return persistence.findByitineraryNo_sequenceNo(
                itineraryNo, sequenceNo);
    }

    public int countByItineraryNo_SequenceNo(String itineraryNo, int sequenceNo)
            throws SystemException {
        return persistence.countByitineraryNo_sequenceNo(
                itineraryNo, sequenceNo);
    }

}

