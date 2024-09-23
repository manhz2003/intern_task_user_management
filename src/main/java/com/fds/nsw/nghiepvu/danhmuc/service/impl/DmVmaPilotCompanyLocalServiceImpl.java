package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmVmaPilotCompanyFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmVmaPilotCompanyPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class DmVmaPilotCompanyLocalServiceImpl { @Autowired
DmVmaPilotCompanyPersistenceImpl persistence;@Autowired
DmVmaPilotCompanyFinderImpl finder;
public DmVmaPilotCompany addDmVmaPilotCompany(DmVmaPilotCompany DmVmaPilotCompany)
		throws SystemException {
		return persistence.updateImpl(DmVmaPilotCompany, false);
	}

	public DmVmaPilotCompany createDmVmaPilotCompany(long id) {
		return persistence.create(id);
	}

	public DmVmaPilotCompany deleteDmVmaPilotCompany(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmVmaPilotCompany deleteDmVmaPilotCompany(DmVmaPilotCompany DmVmaPilotCompany)
		throws SystemException {
		return persistence.remove(DmVmaPilotCompany);
	}

	public DmVmaPilotCompany fetchDmVmaPilotCompany(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmVmaPilotCompany getDmVmaPilotCompany(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmVmaPilotCompany> getDmVmaPilotCompanies(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmVmaPilotCompaniesCount() throws SystemException {
		return persistence.countAll();
	}

	public DmVmaPilotCompany updateDmVmaPilotCompany(DmVmaPilotCompany DmVmaPilotCompany)
		throws SystemException {
		return updateDmVmaPilotCompany(DmVmaPilotCompany, true);
	}

	public DmVmaPilotCompany updateDmVmaPilotCompany(DmVmaPilotCompany DmVmaPilotCompany,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmVmaPilotCompany, merge);
	}


    public List<DmVmaPilotCompany> findByMaritimeCode(String maritimeCode,
                                                      int start, int end) {
        try {
            return persistence.findByF_maritimeCode(
                    maritimeCode, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public DmVmaPilotCompany fetchByPilotCompanyCode(String pilotCompanyCode) {
        try {
            return persistence
                    .fetchByF_pilotCompanyCode(pilotCompanyCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<DmVmaPilotCompany> findPilotCompanies(String maritimeCode,
                                                      String pilotCompanyName, String companyAddress,
                                                      String contactEmail, String telNo, String isDelete,
                                                      String pilotCompanyCodeGroup, int start, int end) {
        try {
            return finder.findVmaPilotCompanies(maritimeCode,
                    pilotCompanyName, companyAddress, contactEmail, telNo,
                    isDelete, pilotCompanyCodeGroup, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public long countPilotCompanies(String maritimeCode,
                                    String pilotCompanyName, String companyAddress,
                                    String contactEmail, String telNo, String isDelete,
                                    String pilotCompanyCodeGroup) {
        try {
            return finder.countVmaPilotCompanies(maritimeCode,
                    pilotCompanyName, companyAddress, contactEmail, telNo,
                    isDelete, pilotCompanyCodeGroup);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    //update by TrungNT -> companyShortName
    public DmVmaPilotCompany updatePilotCompany(String fromMaritimeCode,
                                                String maritimeCode, String pilotCompanyCode,
                                                String pilotCompanyName, String telNo, String faxNo,
                                                String contactEmail, String companyAddress, String remarks,
                                                String syncVersion, String companyShortName) throws SystemException,
            NoSuchDmVmaPilotCompanyException {

        DmVmaPilotCompany dmVmaPilotCompany = persistence
                .fetchByF_pilotCompanyCode(pilotCompanyCode);

        if (dmVmaPilotCompany == null) {
            long id = CounterLocalServiceUtil.increment(DmVmaPilotCompany.class
                    .getName());

            dmVmaPilotCompany = persistence.create(id);
        }
        Date now = new Date();

        dmVmaPilotCompany.setPilotCompanyCode(pilotCompanyCode);
        dmVmaPilotCompany.setPilotCompanyName(pilotCompanyName);
        dmVmaPilotCompany.setTelNo(telNo);
        dmVmaPilotCompany.setFaxNo(faxNo);
        dmVmaPilotCompany.setContactEmail(contactEmail);
        dmVmaPilotCompany.setCompanyAddress(companyAddress);
        dmVmaPilotCompany.setRemarks(remarks);
        dmVmaPilotCompany.setMaritimeCode(maritimeCode);

        dmVmaPilotCompany.setIsDelete(0);
        dmVmaPilotCompany.setMarkedAsDelete(0);
        dmVmaPilotCompany.setModifiedDate(now);
        dmVmaPilotCompany.setRequestedDate(now);
        dmVmaPilotCompany.setSyncVersion(syncVersion);

        dmVmaPilotCompany = persistence.updateImpl(
                dmVmaPilotCompany, true);
        dmVmaPilotCompany.setCompanyShortName(companyShortName);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaPilotCompany);

        return dmVmaPilotCompany;
    }

    public DmVmaPilotCompany deletePilotCompany(String fromMaritimeCode,
                                                String pilotCompanyCode, String syncVersion)
            throws SystemException, NoSuchDmVmaPilotCompanyException {

        DmVmaPilotCompany dmVmaPilotCompany = persistence
                .findByF_pilotCompanyCode(pilotCompanyCode);

        Date now = new Date();

        dmVmaPilotCompany.setIsDelete(1);
        dmVmaPilotCompany.setMarkedAsDelete(1);
        dmVmaPilotCompany.setModifiedDate(now);
        dmVmaPilotCompany.setRequestedDate(now);
        dmVmaPilotCompany.setSyncVersion(syncVersion);

        dmVmaPilotCompany = persistence.updateImpl(
                dmVmaPilotCompany, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaPilotCompany);

        return dmVmaPilotCompany;
    }

}
