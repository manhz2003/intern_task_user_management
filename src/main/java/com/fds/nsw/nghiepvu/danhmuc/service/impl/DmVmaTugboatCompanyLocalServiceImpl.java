package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import java.util.Date;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import java.util.ArrayList; import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmVmaTugboatCompanyFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmVmaTugboatCompanyPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class DmVmaTugboatCompanyLocalServiceImpl { @Autowired
DmVmaTugboatCompanyPersistenceImpl persistence;@Autowired
DmVmaTugboatCompanyFinderImpl finder;
public DmVmaTugboatCompany addDmVmaTugboatCompany(DmVmaTugboatCompany DmVmaTugboatCompany)
		throws SystemException {
		return persistence.updateImpl(DmVmaTugboatCompany, false);
	}

	public DmVmaTugboatCompany createDmVmaTugboatCompany(long id) {
		return persistence.create(id);
	}

	public DmVmaTugboatCompany deleteDmVmaTugboatCompany(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmVmaTugboatCompany deleteDmVmaTugboatCompany(DmVmaTugboatCompany DmVmaTugboatCompany)
		throws SystemException {
		return persistence.remove(DmVmaTugboatCompany);
	}

	public DmVmaTugboatCompany fetchDmVmaTugboatCompany(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmVmaTugboatCompany getDmVmaTugboatCompany(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmVmaTugboatCompany> getDmVmaTugboatCompanies(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmVmaTugboatCompaniesCount() throws SystemException {
		return persistence.countAll();
	}

	public DmVmaTugboatCompany updateDmVmaTugboatCompany(DmVmaTugboatCompany DmVmaTugboatCompany)
		throws SystemException {
		return updateDmVmaTugboatCompany(DmVmaTugboatCompany, true);
	}

	public DmVmaTugboatCompany updateDmVmaTugboatCompany(DmVmaTugboatCompany DmVmaTugboatCompany,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmVmaTugboatCompany, merge);
	}

    public List<DmVmaTugboatCompany> findByMaritimeCode(String maritimeCode,
                                                        int start, int end) {
        try {
            return persistence.findByF_maritimeCode(
                    maritimeCode, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public DmVmaTugboatCompany fetchByTugboatCompanyCode(
            String tugboatCompanyCode) {
        try {
            return persistence
                    .fetchByF_tugboatCompanyCode(tugboatCompanyCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<DmVmaTugboatCompany> findVmaTugboatCompanies(
            String maritimeCode, String tugboatCompanyName,
            String companyAddress, String contactEmail, String telNo,
            String isDelete, String tugboatCompanyCodeGroup, int start, int end) {
        try {
            return finder.findVmaTugboatCompanies(
                    maritimeCode, tugboatCompanyName, companyAddress,
                    contactEmail, telNo, isDelete, tugboatCompanyCodeGroup,
                    start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public long countVmaTugboatCompanies(String maritimeCode,
                                         String tugboatCompanyName, String companyAddress,
                                         String contactEmail, String telNo, String isDelete,
                                         String tugboatCompanyCodeGroup) {
        try {
            return finder.countVmaTugboatCompanies(
                    maritimeCode, tugboatCompanyName, companyAddress,
                    contactEmail, telNo, isDelete, tugboatCompanyCodeGroup);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }
    //update by TrungNT -> add param companyShortName
    public DmVmaTugboatCompany updateVmaTugboatCompany(String fromMaritimeCode,
                                                       String maritimeCode, String tugboatCompanyCode,
                                                       String tugboatCompanyName, String companyAddress,
                                                       String contactEmail, String telNo, String faxNo, String remarks,
                                                       String syncVersion, String companyShortName) throws SystemException,
            NoSuchDmVmaTugboatCompanyException {

        DmVmaTugboatCompany dmVmaTugboatCompany = null;

        dmVmaTugboatCompany = persistence
                .fetchByF_tugboatCompanyCode(tugboatCompanyCode);

        if (dmVmaTugboatCompany == null) {
            long id = CounterLocalServiceUtil.increment(DmVmaTugboatCompany.class
                    .getName());

            dmVmaTugboatCompany = persistence.create(id);
        }

        Date now = new Date();

        // Audit fields
        dmVmaTugboatCompany.setTugboatCompanyCode(tugboatCompanyCode);
        dmVmaTugboatCompany.setTugboatCompanyName(tugboatCompanyName);
        dmVmaTugboatCompany.setCompanyAddress(companyAddress);
        dmVmaTugboatCompany.setContactEmail(contactEmail);
        dmVmaTugboatCompany.setFaxNo(faxNo);
        dmVmaTugboatCompany.setRemarks(remarks);
        dmVmaTugboatCompany.setMaritimeCode(maritimeCode);
        dmVmaTugboatCompany.setTelNo(telNo);

        dmVmaTugboatCompany.setIsDelete(0);
        dmVmaTugboatCompany.setMarkedAsDelete(0);
        dmVmaTugboatCompany.setModifiedDate(now);
        dmVmaTugboatCompany.setRequestedDate(now);
        dmVmaTugboatCompany.setSyncVersion(syncVersion);
        dmVmaTugboatCompany.setCompanyShortName(companyShortName);

        persistence.updateImpl(dmVmaTugboatCompany, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaTugboatCompany);

        return dmVmaTugboatCompany;
    }

    public DmVmaTugboatCompany deleteVmaTugboatCompany(String fromMaritimeCode,
                                                       String tugboatCompanyCode, String syncVersion)
            throws SystemException, NoSuchDmVmaTugboatCompanyException {
        DmVmaTugboatCompany dmVmaTugboatCompany = persistence
                .findByF_tugboatCompanyCode(tugboatCompanyCode);

        Date now = new Date();

        dmVmaTugboatCompany.setIsDelete(1);
        dmVmaTugboatCompany.setMarkedAsDelete(1);
        dmVmaTugboatCompany.setModifiedDate(now);
        dmVmaTugboatCompany.setRequestedDate(now);
        dmVmaTugboatCompany.setSyncVersion(syncVersion);

        persistence.updateImpl(dmVmaTugboatCompany, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaTugboatCompany);

        return dmVmaTugboatCompany;
    }

}
