package com.fds.nsw.nghiepvu.danhmuc.service.impl;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryPilotCompanyPersistenceImpl;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DmHistoryPilotCompanyLocalServiceImpl {
    @Autowired
    DmHistoryPilotCompanyPersistenceImpl persistence;

    public DmHistoryPilotCompany fetchByPilotCompanyCode_SyncVersion(
            String pilotCompanyCode, String syncVersion) {
        try {
            return persistence
                    .fetchByF_pilotCompanyCode_syncVersion(pilotCompanyCode,
                            syncVersion);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    //update by TrungNT -> add param companyShortName
    public DmHistoryPilotCompany updateHistoryPilotCompany(
            String fromMaritimeCode, String maritimeCode,
            String pilotCompanyCode, String pilotCompanyName, String telNo,
            String faxNo, String contactEmail, String companyAddress,
            String remarks, String syncVersion, String companyShortName) throws SystemException,
            NoSuchDmHistoryPilotCompanyException {

        DmHistoryPilotCompany dmHistoryPilotCompany = persistence
                .fetchByF_pilotCompanyCode_syncVersion(pilotCompanyCode,
                        syncVersion);

        if (dmHistoryPilotCompany == null) {
            long id = CounterLocalServiceUtil.increment(DmHistoryPilotCompany.class
                    .getName());
            dmHistoryPilotCompany = persistence.create(id);
        }

        dmHistoryPilotCompany.setPilotCompanyCode(pilotCompanyCode);
        dmHistoryPilotCompany.setPilotCompanyName(pilotCompanyName);
        dmHistoryPilotCompany.setTelNo(telNo);
        dmHistoryPilotCompany.setFaxNo(faxNo);
        dmHistoryPilotCompany.setContactEmail(contactEmail);
        dmHistoryPilotCompany.setCompanyAddress(companyAddress);
        dmHistoryPilotCompany.setRemarks(remarks);
        dmHistoryPilotCompany.setMaritimeCode(maritimeCode);

        dmHistoryPilotCompany.setIsDelete(0);
        dmHistoryPilotCompany.setMarkedAsDelete(0);
        dmHistoryPilotCompany.setModifiedDate(new Date());
        dmHistoryPilotCompany.setSyncVersion(syncVersion);
        dmHistoryPilotCompany.setCompanyShortName(companyShortName);

        dmHistoryPilotCompany = persistence.updateImpl(dmHistoryPilotCompany,
                true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistoryPilotCompany);

        return dmHistoryPilotCompany;
    }

    public DmHistoryPilotCompany deleteHistoryPilotCompany(
            String fromMaritimeCode, String pilotCompanyCode, String syncVersion)
            throws SystemException, NoSuchDmHistoryPilotCompanyException {

        DmHistoryPilotCompany dmHistoryPilotCompany = persistence
                .findByF_pilotCompanyCode_syncVersion(pilotCompanyCode,
                        syncVersion);

        if (dmHistoryPilotCompany == null) {
            long id = CounterLocalServiceUtil.increment(DmHistoryPilotCompany.class
                    .getName());
            dmHistoryPilotCompany = persistence.create(id);
        }

        dmHistoryPilotCompany.setIsDelete(1);
        dmHistoryPilotCompany.setMarkedAsDelete(1);
        dmHistoryPilotCompany.setModifiedDate(new Date());
        dmHistoryPilotCompany.setSyncVersion(syncVersion);

        dmHistoryPilotCompany = persistence.updateImpl(dmHistoryPilotCompany,
                true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistoryPilotCompany);

        return dmHistoryPilotCompany;
    }



    public DmHistoryPilotCompany addDmHistoryPilotCompany(DmHistoryPilotCompany DmHistoryPilotCompany)
		throws SystemException {
		return persistence.updateImpl(DmHistoryPilotCompany, false);
	}

	public DmHistoryPilotCompany createDmHistoryPilotCompany(long id) {
		return persistence.create(id);
	}

	public DmHistoryPilotCompany deleteDmHistoryPilotCompany(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmHistoryPilotCompany deleteDmHistoryPilotCompany(DmHistoryPilotCompany DmHistoryPilotCompany)
		throws SystemException {
		return persistence.remove(DmHistoryPilotCompany);
	}

	public DmHistoryPilotCompany fetchDmHistoryPilotCompany(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmHistoryPilotCompany getDmHistoryPilotCompany(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmHistoryPilotCompany> getDmHistoryPilotCompanies(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmHistoryPilotCompaniesCount() throws SystemException {
		return persistence.countAll();
	}

	public DmHistoryPilotCompany updateDmHistoryPilotCompany(DmHistoryPilotCompany DmHistoryPilotCompany)
		throws SystemException {
		return updateDmHistoryPilotCompany(DmHistoryPilotCompany, true);
	}

	public DmHistoryPilotCompany updateDmHistoryPilotCompany(DmHistoryPilotCompany DmHistoryPilotCompany,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmHistoryPilotCompany, merge);
	}
}
