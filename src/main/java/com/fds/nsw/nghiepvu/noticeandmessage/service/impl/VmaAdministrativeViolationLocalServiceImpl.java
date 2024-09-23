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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaAdministrativeViolationFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaAdministrativeViolationPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaAdministrativeViolationLocalServiceImpl { 
@Autowired VmaAdministrativeViolationPersistenceImpl persistence;
@Autowired VmaAdministrativeViolationFinderImpl finder;

	public VmaAdministrativeViolation createVmaAdministrativeViolation(long id) {
		return persistence.create(id);
	}

	public VmaAdministrativeViolation deleteVmaAdministrativeViolation(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaAdministrativeViolation deleteVmaAdministrativeViolation(VmaAdministrativeViolation VmaAdministrativeViolation)
		throws SystemException {
		return persistence.remove(VmaAdministrativeViolation);
	}

	public VmaAdministrativeViolation fetchVmaAdministrativeViolation(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaAdministrativeViolation getVmaAdministrativeViolation(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaAdministrativeViolation> getVmaAdministrativeViolations(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaAdministrativeViolationsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaAdministrativeViolation updateVmaAdministrativeViolation(VmaAdministrativeViolation VmaAdministrativeViolation,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaAdministrativeViolation, merge);
	}

    public VmaAdministrativeViolation addVmaAdministrativeViolation(
            VmaAdministrativeViolation vmaAdministrativeViolation)
            throws SystemException {
        long id = CounterLocalServiceUtil
                .increment(VmaAdministrativeViolation.class.getName());
        vmaAdministrativeViolation.setId(id);
        vmaAdministrativeViolation.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaAdministrativeViolation);
        return persistence.updateImpl(
                vmaAdministrativeViolation, false);
    }

    public VmaAdministrativeViolation updateVmaAdministrativeViolation(
            VmaAdministrativeViolation vmaAdministrativeViolation)
            throws SystemException {
        vmaAdministrativeViolation.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaAdministrativeViolation);
        return persistence.updateImpl(
                vmaAdministrativeViolation, false);
    }

    public VmaAdministrativeViolation delete(long id) throws SystemException,
            NoSuchVmaAdministrativeViolationException {
        return persistence.remove(id);
    }

    public List<VmaAdministrativeViolation> findByPortofAuthority(
            String portofAuthority) throws SystemException {
        return persistence
                .findByportofAuthority(portofAuthority);
    }

    public int countByPortofAuthority(String portofAuthority)
            throws SystemException {
        return persistence
                .countByportofAuthority(portofAuthority);
    }

    public List<VmaAdministrativeViolation> findVmaAdministrativeViolations(
            String portofAuthority, String violationDate,
            String notViolationDate, int start, int end) throws SystemException {
        return finder
                .findVmaAdministrativeViolations(portofAuthority,
                        violationDate, notViolationDate, start, end);
    }

    public long countVmaAdministrativeViolation(String portofAuthority,
                                                String violationDate, String notViolationDate)
            throws SystemException {
        return finder
                .countVmaAdministrativeViolation(portofAuthority,
                        violationDate, notViolationDate);
    }

}

