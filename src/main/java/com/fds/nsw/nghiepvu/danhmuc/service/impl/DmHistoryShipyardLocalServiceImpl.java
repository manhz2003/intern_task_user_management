package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import java.util.ArrayList; import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryShipyardPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import java.util.Date;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class DmHistoryShipyardLocalServiceImpl { @Autowired
DmHistoryShipyardPersistenceImpl persistence;


    public DmHistoryShipyard fetchByShipYardCode_SyncVersion(
            String shipYardCode, String syncVersion) {
        try {
            return persistence
                    .fetchByF_shipYardCode_syncVersion(shipYardCode,
                            syncVersion);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    // update by TrungNT -> add params
    // companyShortName,markupMaintainane,markupConstruction,markupDeconstruction,profileMaintainane,profileConstruction,profileDeconstruction
    public DmHistoryShipyard updateHistoryShipYard(String fromMaritimeCode,
                                                   String shipYardCode, String companyName, String companyAddress,
                                                   String contactEmail, String faxNo, String remarks,
                                                   String maritimeCode, String telNo, String taxCode,
                                                   String syncVersion, String companyShortName, int markupMaintainane,
                                                   int markupConstruction, int markupDeconstruction,
                                                   String profileMaintainane, String profileConstruction,
                                                   String profileDeconstruction) throws SystemException {

        DmHistoryShipyard dmHistoryShipyard = persistence
                .fetchByF_shipYardCode_syncVersion(shipYardCode, syncVersion);

        if (dmHistoryShipyard == null) {
            long id = CounterLocalServiceUtil.increment(DmHistoryShipyard.class
                    .getName());
            dmHistoryShipyard = persistence.create(id);
        }

        dmHistoryShipyard.setShipYardCode(shipYardCode);
        dmHistoryShipyard.setCompanyName(companyName);
        dmHistoryShipyard.setCompanyAddress(companyAddress);
        dmHistoryShipyard.setContactEmail(contactEmail);
        dmHistoryShipyard.setFaxNo(faxNo);
        dmHistoryShipyard.setRemarks(remarks);
        dmHistoryShipyard.setMaritimeCode(maritimeCode);
        dmHistoryShipyard.setTelNo(telNo);
        dmHistoryShipyard.setTaxCode(taxCode);

        dmHistoryShipyard.setIsDelete(0);
        dmHistoryShipyard.setMarkedAsDelete(0);
        dmHistoryShipyard.setModifiedDate(new Date());
        dmHistoryShipyard.setSyncVersion(syncVersion);

        dmHistoryShipyard.setCompanyShortName(companyShortName);
        dmHistoryShipyard.setMarkupMaintainane(markupMaintainane);
        dmHistoryShipyard.setMarkupConstruction(markupConstruction);
        dmHistoryShipyard.setMarkupDeconstruction(markupDeconstruction);
        dmHistoryShipyard.setProfileMaintainane(profileMaintainane);
        dmHistoryShipyard.setProfileConstruction(profileConstruction);
        dmHistoryShipyard.setProfileDeconstruction(profileDeconstruction);

        dmHistoryShipyard = persistence.updateImpl(
                dmHistoryShipyard, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistoryShipyard);

        return dmHistoryShipyard;
    }

    public DmHistoryShipyard deleteHistoryShipYard(String fromMaritimeCode,
                                                   String shipYardCode, String syncVersion) throws SystemException {

        DmHistoryShipyard dmHistoryShipyard = persistence
                .fetchByF_shipYardCode_syncVersion(shipYardCode, syncVersion);

        if (dmHistoryShipyard == null) {
            long id = CounterLocalServiceUtil.increment(DmHistoryShipyard.class
                    .getName());
            dmHistoryShipyard = persistence.create(id);
        }

        dmHistoryShipyard.setIsDelete(1);
        dmHistoryShipyard.setMarkedAsDelete(1);
        dmHistoryShipyard.setModifiedDate(new Date());
        dmHistoryShipyard.setSyncVersion(syncVersion);

        dmHistoryShipyard = persistence.updateImpl(
                dmHistoryShipyard, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistoryShipyard);

        return dmHistoryShipyard;
    }

public DmHistoryShipyard addDmHistoryShipyard(DmHistoryShipyard DmHistoryShipyard)
		throws SystemException {
		return persistence.updateImpl(DmHistoryShipyard, false);
	}

	public DmHistoryShipyard createDmHistoryShipyard(long id) {
		return persistence.create(id);
	}

	public DmHistoryShipyard deleteDmHistoryShipyard(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmHistoryShipyard deleteDmHistoryShipyard(DmHistoryShipyard DmHistoryShipyard)
		throws SystemException {
		return persistence.remove(DmHistoryShipyard);
	}

	public DmHistoryShipyard fetchDmHistoryShipyard(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmHistoryShipyard getDmHistoryShipyard(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmHistoryShipyard> getDmHistoryShipyards(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmHistoryShipyardsCount() throws SystemException {
		return persistence.countAll();
	}

	public DmHistoryShipyard updateDmHistoryShipyard(DmHistoryShipyard DmHistoryShipyard)
		throws SystemException {
		return updateDmHistoryShipyard(DmHistoryShipyard, true);
	}

	public DmHistoryShipyard updateDmHistoryShipyard(DmHistoryShipyard DmHistoryShipyard,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmHistoryShipyard, merge);
	}
}
