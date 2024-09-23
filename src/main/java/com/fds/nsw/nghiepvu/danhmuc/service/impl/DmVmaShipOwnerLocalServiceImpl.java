package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import java.util.ArrayList; import java.util.List;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmVmaShipOwnerFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmVmaShipOwnerPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
@Slf4j @Service
public class DmVmaShipOwnerLocalServiceImpl { @Autowired
DmVmaShipOwnerPersistenceImpl persistence;@Autowired
DmVmaShipOwnerFinderImpl finder;
public DmVmaShipOwner addDmVmaShipOwner(DmVmaShipOwner DmVmaShipOwner)
		throws SystemException {
		return persistence.updateImpl(DmVmaShipOwner, false);
	}

	public DmVmaShipOwner createDmVmaShipOwner(long id) {
		return persistence.create(id);
	}

	public DmVmaShipOwner deleteDmVmaShipOwner(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmVmaShipOwner deleteDmVmaShipOwner(DmVmaShipOwner DmVmaShipOwner)
		throws SystemException {
		return persistence.remove(DmVmaShipOwner);
	}

	public DmVmaShipOwner fetchDmVmaShipOwner(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmVmaShipOwner getDmVmaShipOwner(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmVmaShipOwner> getDmVmaShipOwners(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmVmaShipOwnersCount() throws SystemException {
		return persistence.countAll();
	}

	public DmVmaShipOwner updateDmVmaShipOwner(DmVmaShipOwner DmVmaShipOwner)
		throws SystemException {
		return updateDmVmaShipOwner(DmVmaShipOwner, true);
	}

	public DmVmaShipOwner updateDmVmaShipOwner(DmVmaShipOwner DmVmaShipOwner,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmVmaShipOwner, merge);
	}

    public List<DmVmaShipOwner> findByMaritimeCode(String maritimeCode,
                                                   int start, int end) {
        try {
            return persistence.findByF_maritimeCode(maritimeCode,
                    start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public DmVmaShipOwner fetchByShipOwnerCode(String shipOwnerCode) {
        try {
            return persistence
                    .fetchByF_shipOwnerCode(shipOwnerCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<DmVmaShipOwner> findVmaShipOwners(String maritimeCode,
                                                  String taxCode, String companyName, String companyAddress,
                                                  String contactEmail, String telNo, String isShipOwner,
                                                  String isShipOperator, String isDelete, String shipOwnerCodeGroup,
                                                  int isOther, int start, int end) {
        try {
            return finder.findVmaShipOwners(maritimeCode,
                    taxCode, companyName, companyAddress, contactEmail, telNo,
                    isShipOwner, isShipOperator, isDelete, shipOwnerCodeGroup,
                    isOther, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public long countVmaShipOwners(String maritimeCode, String taxCode,
                                   String companyName, String companyAddress, String contactEmail,
                                   String telNo, String isShipOwner, String isShipOperator,
                                   String isDelete, String shipOwnerCodeGroup, int isOther) {
        try {
            return finder.countVmaShipOwners(maritimeCode,
                    taxCode, companyName, companyAddress, contactEmail, telNo,
                    isShipOwner, isShipOperator, isDelete, shipOwnerCodeGroup,
                    isOther);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    public DmVmaShipOwner fetchByF_taxCode(String taxCode) {
        try {
            return persistence.fetchByF_taxCode(taxCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    // update by TrungNT -> add param companyShortName
    public DmVmaShipOwner updateVmaShipOwner(String fromMaritimeCode,
                                             String maritimeCode, String shipOwnerCode, String taxCode,
                                             String companyName, String companyAddress, String contactEmail,
                                             String telNo, String faxNo, int isShipOwner, int isShipOperator,
                                             String remarks, String syncVersion, String companyShortName, int isOther)
            throws SystemException, NoSuchDmVmaShipOwnerException {

        DmVmaShipOwner dmVmaShipOwner = null;
        dmVmaShipOwner = persistence
                .fetchByF_shipOwnerCode(shipOwnerCode);

        if (dmVmaShipOwner == null) {
            long id = CounterLocalServiceUtil.increment(DmVmaShipOwner.class
                    .getName());

            dmVmaShipOwner = persistence.create(id);
        }
        Date now = new Date();

        dmVmaShipOwner.setShipOwnerCode(shipOwnerCode);
        dmVmaShipOwner.setTaxCode(taxCode);
        dmVmaShipOwner.setCompanyName(companyName);
        dmVmaShipOwner.setCompanyAddress(companyAddress);
        dmVmaShipOwner.setContactEmail(contactEmail);
        dmVmaShipOwner.setTelNo(telNo);
        dmVmaShipOwner.setFaxNo(faxNo);
        dmVmaShipOwner.setIsShipOwner(isShipOwner);
        dmVmaShipOwner.setIsShipOperator(isShipOperator);
        dmVmaShipOwner.setRemarks(remarks);
        dmVmaShipOwner.setMaritimeCode(maritimeCode);
        dmVmaShipOwner.setIsOther(isOther);

        dmVmaShipOwner.setIsDelete(0);
        dmVmaShipOwner.setMarkedAsDelete(0);
        dmVmaShipOwner.setModifiedDate(now);
        dmVmaShipOwner.setRequestedDate(now);
        dmVmaShipOwner.setSyncVersion(syncVersion);
        dmVmaShipOwner.setCompanyShortName(companyShortName);

        dmVmaShipOwner = persistence.updateImpl(dmVmaShipOwner, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaShipOwner);

        return dmVmaShipOwner;
    }

    public DmVmaShipOwner deleteVmaShipOwner(String fromMaritimeCode,
                                             String shipOwnerCode, String syncVersion) throws SystemException,
            NoSuchDmVmaShipOwnerException {

        DmVmaShipOwner dmVmaShipOwner = persistence
                .findByF_shipOwnerCode(shipOwnerCode);

        Date now = new Date();

        dmVmaShipOwner.setIsDelete(1);
        dmVmaShipOwner.setMarkedAsDelete(1);
        dmVmaShipOwner.setModifiedDate(now);
        dmVmaShipOwner.setRequestedDate(now);
        dmVmaShipOwner.setSyncVersion(syncVersion);

        dmVmaShipOwner = persistence.updateImpl(dmVmaShipOwner, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaShipOwner);

        return dmVmaShipOwner;
    }

}
