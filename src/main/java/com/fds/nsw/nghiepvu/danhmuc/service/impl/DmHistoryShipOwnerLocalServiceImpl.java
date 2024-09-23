package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryShipOwnerPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class DmHistoryShipOwnerLocalServiceImpl { @Autowired
DmHistoryShipOwnerPersistenceImpl persistence;

    public DmHistoryShipOwner fetchByshipOwnerCode_syncVersion(
            String shipOwnerCode, String syncVersion) {
        try {
            return persistence
                    .fetchByF_shipOwnerCode_syncVersion(shipOwnerCode,
                            syncVersion);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    // update By TrungNT -> add param companyShortName
    public DmHistoryShipOwner updateHistoryShipOwner(String fromMaritimeCode,
                                                     String maritimeCode, String shipOwnerCode, String taxCode,
                                                     String companyName, String companyAddress, String contactEmail,
                                                     String telNo, String faxNo, int isShipOwner, int isShipOperator,
                                                     String remarks, String syncVersion, String companyShortName,
                                                     int isOther) throws SystemException {

        DmHistoryShipOwner dmHistoryShipOwner = persistence
                .fetchByF_shipOwnerCode_syncVersion(shipOwnerCode, syncVersion);

        if (dmHistoryShipOwner == null) {
            long id = CounterLocalServiceUtil.increment(DmHistoryShipOwner.class
                    .getName());
            dmHistoryShipOwner = persistence.create(id);
        }

        dmHistoryShipOwner.setShipOwnerCode(shipOwnerCode);
        dmHistoryShipOwner.setTaxCode(taxCode);
        dmHistoryShipOwner.setCompanyName(companyName);
        dmHistoryShipOwner.setCompanyAddress(companyAddress);
        dmHistoryShipOwner.setContactEmail(contactEmail);
        dmHistoryShipOwner.setTelNo(telNo);
        dmHistoryShipOwner.setFaxNo(faxNo);
        dmHistoryShipOwner.setIsShipOwner(isShipOwner);
        dmHistoryShipOwner.setIsShipOperator(isShipOperator);
        dmHistoryShipOwner.setRemarks(remarks);
        dmHistoryShipOwner.setMaritimeCode(maritimeCode);
        dmHistoryShipOwner.setIsOther(isOther);

        dmHistoryShipOwner.setIsDelete(0);
        dmHistoryShipOwner.setMarkedAsDelete(0);
        dmHistoryShipOwner.setModifiedDate(new Date());
        dmHistoryShipOwner.setSyncVersion(syncVersion);
        dmHistoryShipOwner.setCompanyShortName(companyShortName);

        dmHistoryShipOwner = persistence.updateImpl(
                dmHistoryShipOwner, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistoryShipOwner);

        return dmHistoryShipOwner;
    }

    public DmHistoryShipOwner deleteHistoryShipOwner(String fromMaritimeCode,
                                                     String shipOwnerCode, String syncVersion) throws SystemException {

        DmHistoryShipOwner dmHistoryShipOwner = persistence
                .fetchByF_shipOwnerCode_syncVersion(shipOwnerCode, syncVersion);

        if (dmHistoryShipOwner == null) {
            long id = CounterLocalServiceUtil.increment(DmHistoryShipOwner.class
                    .getName());
            dmHistoryShipOwner = persistence.create(id);
        }

        dmHistoryShipOwner.setIsDelete(1);
        dmHistoryShipOwner.setMarkedAsDelete(1);
        dmHistoryShipOwner.setModifiedDate(new Date());
        dmHistoryShipOwner.setSyncVersion(syncVersion);

        dmHistoryShipOwner = persistence.updateImpl(
                dmHistoryShipOwner, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistoryShipOwner);

        return dmHistoryShipOwner;
    }

    public DmHistoryShipOwner addDmHistoryShipOwner(DmHistoryShipOwner DmHistoryShipOwner)
		throws SystemException {
		return persistence.updateImpl(DmHistoryShipOwner, false);
	}

	public DmHistoryShipOwner createDmHistoryShipOwner(long id) {
		return persistence.create(id);
	}

	public DmHistoryShipOwner deleteDmHistoryShipOwner(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmHistoryShipOwner deleteDmHistoryShipOwner(DmHistoryShipOwner DmHistoryShipOwner)
		throws SystemException {
		return persistence.remove(DmHistoryShipOwner);
	}

	public DmHistoryShipOwner fetchDmHistoryShipOwner(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmHistoryShipOwner getDmHistoryShipOwner(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmHistoryShipOwner> getDmHistoryShipOwners(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmHistoryShipOwnersCount() throws SystemException {
		return persistence.countAll();
	}

	public DmHistoryShipOwner updateDmHistoryShipOwner(DmHistoryShipOwner DmHistoryShipOwner)
		throws SystemException {
		return updateDmHistoryShipOwner(DmHistoryShipOwner, true);
	}

	public DmHistoryShipOwner updateDmHistoryShipOwner(DmHistoryShipOwner DmHistoryShipOwner,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmHistoryShipOwner, merge);
	}

}
