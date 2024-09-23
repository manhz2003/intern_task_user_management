package com.fds.nsw.nghiepvu.danhmuc.service.impl;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.liferay.service.exception.NoSuchUserException;
import com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.service.exception.*;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryTugboatCompanyPersistenceImpl;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DmHistoryTugboatCompanyLocalServiceImpl {
    public DmHistoryTugboatCompany addDmHistoryTugboatCompany(DmHistoryTugboatCompany DmHistoryTugboatCompany)
		throws SystemException {
		return persistence.updateImpl(DmHistoryTugboatCompany, false);
	}

	public DmHistoryTugboatCompany createDmHistoryTugboatCompany(long id) {
		return persistence.create(id);
	}

	public DmHistoryTugboatCompany deleteDmHistoryTugboatCompany(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmHistoryTugboatCompany deleteDmHistoryTugboatCompany(DmHistoryTugboatCompany DmHistoryTugboatCompany)
		throws SystemException {
		return persistence.remove(DmHistoryTugboatCompany);
	}

	public DmHistoryTugboatCompany fetchDmHistoryTugboatCompany(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmHistoryTugboatCompany getDmHistoryTugboatCompany(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmHistoryTugboatCompany> getDmHistoryTugboatCompanies(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmHistoryTugboatCompaniesCount() throws SystemException {
		return persistence.countAll();
	}

	public DmHistoryTugboatCompany updateDmHistoryTugboatCompany(DmHistoryTugboatCompany DmHistoryTugboatCompany)
		throws SystemException {
		return updateDmHistoryTugboatCompany(DmHistoryTugboatCompany, true);
	}

	public DmHistoryTugboatCompany updateDmHistoryTugboatCompany(DmHistoryTugboatCompany DmHistoryTugboatCompany,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmHistoryTugboatCompany, merge);
	}
    @Autowired
    DmHistoryTugboatCompanyPersistenceImpl persistence;
    public DmHistoryTugboatCompany fetchByTugboatCompanyCode_SyncVersion(
            String tugboatCompanyCode, String syncVersion) {
        try {
            return persistence
                    .fetchByF_tugboatCompanyCode_syncVersion(
                            tugboatCompanyCode, syncVersion);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //update by TrungNT -> add param companyShortName
    public DmHistoryTugboatCompany updateHistoryTugboatCompany(
            String fromMaritimeCode, String maritimeCode,
            String tugboatCompanyCode, String tugboatCompanyName,
            String companyAddress, String contactEmail, String telNo,
            String faxNo, String remarks, String syncVersion, String companyShortName)
            throws SystemException, NoSuchDmVmaTugboatCompanyException,
            NoSuchUserException, NoSuchDmHistoryTugboatCompanyException {

        DmHistoryTugboatCompany dmHistoryTugboatCompany = persistence
                .fetchByF_tugboatCompanyCode_syncVersion(tugboatCompanyCode,
                        syncVersion);

        if (dmHistoryTugboatCompany == null) {
            long id = CounterLocalServiceUtil
                    .increment(DmHistoryTugboatCompany.class.getName());
            dmHistoryTugboatCompany = persistence
                    .create(id);
        }

        // Audit fields
        dmHistoryTugboatCompany.setTugboatCompanyCode(tugboatCompanyCode);
        dmHistoryTugboatCompany.setTugboatCompanyName(tugboatCompanyName);
        dmHistoryTugboatCompany.setCompanyAddress(companyAddress);
        dmHistoryTugboatCompany.setContactEmail(contactEmail);
        dmHistoryTugboatCompany.setFaxNo(faxNo);
        dmHistoryTugboatCompany.setRemarks(remarks);
        dmHistoryTugboatCompany.setMaritimeCode(maritimeCode);
        dmHistoryTugboatCompany.setTelNo(telNo);

        dmHistoryTugboatCompany.setIsDelete(0);
        dmHistoryTugboatCompany.setMarkedAsDelete(0);
        dmHistoryTugboatCompany.setModifiedDate(new Date());
        dmHistoryTugboatCompany.setSyncVersion(syncVersion);
        dmHistoryTugboatCompany.setCompanyShortName(companyShortName);

        dmHistoryTugboatCompany = persistence.updateImpl(
                dmHistoryTugboatCompany, true);

        MessageSyncUtil
                .dongBoDanhMuc(fromMaritimeCode, dmHistoryTugboatCompany);

        return dmHistoryTugboatCompany;
    }

    public DmHistoryTugboatCompany deleteHistoryTugboatCompany(
            String fromMaritimeCode, String tugboatCompanyCode,
            String syncVersion) throws SystemException,
            NoSuchDmVmaTugboatCompanyException,
            NoSuchDmHistoryTugboatCompanyException {
        DmHistoryTugboatCompany dmHistoryTugboatCompany = persistence
                .findByF_tugboatCompanyCode_syncVersion(tugboatCompanyCode,
                        syncVersion);

        if (dmHistoryTugboatCompany == null) {
            long id = CounterLocalServiceUtil
                    .increment(DmHistoryTugboatCompany.class.getName());
            dmHistoryTugboatCompany = persistence
                    .create(id);
        }

        dmHistoryTugboatCompany.setIsDelete(1);
        dmHistoryTugboatCompany.setMarkedAsDelete(1);
        dmHistoryTugboatCompany.setModifiedDate(new Date());
        dmHistoryTugboatCompany.setSyncVersion(syncVersion);

        dmHistoryTugboatCompany = persistence.updateImpl(
                dmHistoryTugboatCompany, true);

        MessageSyncUtil
                .dongBoDanhMuc(fromMaritimeCode, dmHistoryTugboatCompany);

        return dmHistoryTugboatCompany;
    }

}
