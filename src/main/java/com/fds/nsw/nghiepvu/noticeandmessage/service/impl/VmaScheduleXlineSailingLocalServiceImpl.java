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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaScheduleXlineSailingFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaScheduleXlineSailingPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaScheduleXlineSailingLocalServiceImpl { 
@Autowired VmaScheduleXlineSailingPersistenceImpl persistence;
@Autowired VmaScheduleXlineSailingFinderImpl finder;

	public VmaScheduleXlineSailing createVmaScheduleXlineSailing(long id) {
		return persistence.create(id);
	}

	public VmaScheduleXlineSailing deleteVmaScheduleXlineSailing(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaScheduleXlineSailing deleteVmaScheduleXlineSailing(VmaScheduleXlineSailing VmaScheduleXlineSailing)
		throws SystemException {
		return persistence.remove(VmaScheduleXlineSailing);
	}

	public VmaScheduleXlineSailing fetchVmaScheduleXlineSailing(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaScheduleXlineSailing getVmaScheduleXlineSailing(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaScheduleXlineSailing> getVmaScheduleXlineSailings(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaScheduleXlineSailingsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaScheduleXlineSailing updateVmaScheduleXlineSailing(VmaScheduleXlineSailing VmaScheduleXlineSailing,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaScheduleXlineSailing, merge);
	}
public VmaScheduleXlineSailing addVmaScheduleXlineSailing(
        VmaScheduleXlineSailing vmaScheduleXlineSailing)
        throws SystemException {

    long id = CounterLocalServiceUtil.increment(VmaScheduleXlineSailing.class
            .getName());

    vmaScheduleXlineSailing.setId(id);

    long sequenceNo = CounterLocalServiceUtil.increment(
            "VMA_SCHEDULE_XLINE_SAILING_SEQUENCE_NO", 1);

    vmaScheduleXlineSailing.setSequenceNo((int) sequenceNo);
    vmaScheduleXlineSailing.setModifiedDate(new Date());
    // VMAUtils.formatUnicode(vmaScheduleXlineSailing);
    return persistence.updateImpl(
            vmaScheduleXlineSailing, false);

}

    public VmaScheduleXlineSailing delete(long id) throws SystemException,
            NoSuchVmaScheduleXlineSailingException {
        return persistence.remove(id);
    }

    public VmaScheduleXlineSailing updateVmaScheduleXlineSailing(
            VmaScheduleXlineSailing vmaScheduleXlineSailing)
            throws SystemException {
        vmaScheduleXlineSailing.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaScheduleXlineSailing);
        return persistence.updateImpl(
                vmaScheduleXlineSailing, false);

    }

    public List<VmaScheduleXlineSailing> findByshipOperatorCode_scheduleYear_scheduleMonth(
            String shipOperatorCode, int scheduleYear, int scheduleMonth)
            throws SystemException {
        return persistence
                .findByshipOperatorCode_scheduleYear_scheduleMonth(
                        shipOperatorCode, scheduleYear, scheduleMonth);
    }

    public List<VmaScheduleXlineSailing> findVmaScheduleXlineSailings(
            String portofAuthority, String nameOfShip, String imoNumber,
            String callSign, String registryNumber, String voyageNo,
            String stateCode, String provinceCode, String maritimePortCode,
            String portGoingToStateName, String portGoingToCode, int start,
            int end) throws SystemException {
        return finder.findVmaScheduleXlineSailings(
                portofAuthority, nameOfShip, imoNumber, callSign,
                registryNumber, voyageNo, stateCode, provinceCode,
                maritimePortCode, portGoingToStateName, portGoingToCode, start,
                end);
    }

    public long countVmaScheduleXlineSailings(String portofAuthority,
                                              String nameOfShip, String imoNumber, String callSign,
                                              String registryNumber, String voyageNo, String stateCode,
                                              String provinceCode, String maritimePortCode,
                                              String portGoingToStateName, String portGoingToCode)
            throws SystemException {
        return finder.countVmaScheduleXlineSailings(
                portofAuthority, nameOfShip, imoNumber, callSign,
                registryNumber, voyageNo, stateCode, provinceCode,
                maritimePortCode, portGoingToStateName, portGoingToCode);
    }

}

