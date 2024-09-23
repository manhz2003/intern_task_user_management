package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import java.util.ArrayList; import java.util.List;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmVmaSecurityOfficeFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmVmaSecurityOfficePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class DmVmaSecurityOfficeLocalServiceImpl { @Autowired
DmVmaSecurityOfficePersistenceImpl persistence;@Autowired
DmVmaSecurityOfficeFinderImpl finder;
public DmVmaSecurityOffice addDmVmaSecurityOffice(DmVmaSecurityOffice DmVmaSecurityOffice)
		throws SystemException {
		return persistence.updateImpl(DmVmaSecurityOffice, false);
	}

	public DmVmaSecurityOffice createDmVmaSecurityOffice(long id) {
		return persistence.create(id);
	}

	public DmVmaSecurityOffice deleteDmVmaSecurityOffice(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmVmaSecurityOffice deleteDmVmaSecurityOffice(DmVmaSecurityOffice DmVmaSecurityOffice)
		throws SystemException {
		return persistence.remove(DmVmaSecurityOffice);
	}

	public DmVmaSecurityOffice fetchDmVmaSecurityOffice(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmVmaSecurityOffice getDmVmaSecurityOffice(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmVmaSecurityOffice> getDmVmaSecurityOffices(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmVmaSecurityOfficesCount() throws SystemException {
		return persistence.countAll();
	}

	public DmVmaSecurityOffice updateDmVmaSecurityOffice(DmVmaSecurityOffice DmVmaSecurityOffice)
		throws SystemException {
		return updateDmVmaSecurityOffice(DmVmaSecurityOffice, true);
	}

	public DmVmaSecurityOffice updateDmVmaSecurityOffice(DmVmaSecurityOffice DmVmaSecurityOffice,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmVmaSecurityOffice, merge);
	}

    public DmVmaSecurityOffice fetchBySecurityOfficeCode(
            String securityOfficeCode) {
        try {
            return persistence
                    .fetchByF_securityOfficeCode(securityOfficeCode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DmVmaSecurityOffice> findVmaSecurityOffice(String maritimeCode,
                                                           String companyName, String companyAddress, String contactEmail,
                                                           String telNo, String isDelete, String securityOfficeCodeGroup,
                                                           int start, int end) {
        try {
            return finder.findVmaSecurityOffices(
                    maritimeCode, companyName, companyAddress, contactEmail,
                    telNo, isDelete, securityOfficeCodeGroup, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public long countVmaSecurityOffice(String maritimeCode, String companyName,
                                       String companyAddress, String contactEmail, String telNo,
                                       String isDelete, String securityOfficeCodeGroup) {
        try {
            return finder.countVmaSecurityOffices(
                    maritimeCode, companyName, companyAddress, contactEmail,
                    telNo, isDelete, securityOfficeCodeGroup);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    public DmVmaSecurityOffice updateVmaSecurityOffice(String fromMaritimeCode,
                                                       String securityOfficeCode, String companyName,
                                                       String companyAddress, String contactEmail, String faxNo,
                                                       String remarks, String maritimeCode, String telNo,
                                                       String syncVersion) throws SystemException,
            NoSuchDmVmaSecurityOfficeException {

        DmVmaSecurityOffice dmVmaSecurityOffice = persistence
                .fetchByF_securityOfficeCode(securityOfficeCode);

        if (dmVmaSecurityOffice == null) {
            long id = CounterLocalServiceUtil.increment(DmVmaSecurityOffice.class
                    .getName());

            dmVmaSecurityOffice = persistence.create(id);
        }
        Date now = new Date();

        dmVmaSecurityOffice.setSecurityOfficeCode(securityOfficeCode);
        dmVmaSecurityOffice.setCompanyName(companyName);
        dmVmaSecurityOffice.setCompanyAddress(companyAddress);
        dmVmaSecurityOffice.setContactEmail(contactEmail);
        dmVmaSecurityOffice.setFaxNo(faxNo);
        dmVmaSecurityOffice.setRemarks(remarks);
        dmVmaSecurityOffice.setMaritimeCode(maritimeCode);
        dmVmaSecurityOffice.setTelNo(telNo);

        dmVmaSecurityOffice.setIsDelete(0);
        dmVmaSecurityOffice.setMarkedAsDelete(0);
        dmVmaSecurityOffice.setModifiedDate(now);
        dmVmaSecurityOffice.setRequestedDate(now);
        dmVmaSecurityOffice.setSyncVersion(syncVersion);

        dmVmaSecurityOffice = persistence.updateImpl(
                dmVmaSecurityOffice, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaSecurityOffice);

        return dmVmaSecurityOffice;
    }

    public DmVmaSecurityOffice deleteVmaSecurityOffice(String fromMaritimeCode,
                                                       String securityOfficeCode, String syncVersion)
            throws SystemException, NoSuchDmVmaSecurityOfficeException {

        DmVmaSecurityOffice dmVmaSecurityOffice = persistence
                .findByF_securityOfficeCode(securityOfficeCode);

        Date now = new Date();

        dmVmaSecurityOffice.setIsDelete(1);
        dmVmaSecurityOffice.setMarkedAsDelete(1);
        dmVmaSecurityOffice.setModifiedDate(now);
        dmVmaSecurityOffice.setRequestedDate(now);
        dmVmaSecurityOffice.setSyncVersion(syncVersion);

        dmVmaSecurityOffice = persistence.updateImpl(
                dmVmaSecurityOffice, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaSecurityOffice);

        return dmVmaSecurityOffice;
    }

}
