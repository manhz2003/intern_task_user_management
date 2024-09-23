package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import java.util.ArrayList; import java.util.List;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmVmaShipyardFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmVmaShipyardPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class DmVmaShipyardLocalServiceImpl { @Autowired
DmVmaShipyardPersistenceImpl persistence;@Autowired
DmVmaShipyardFinderImpl finder;
public DmVmaShipyard addDmVmaShipyard(DmVmaShipyard DmVmaShipyard)
		throws SystemException {
		return persistence.updateImpl(DmVmaShipyard, false);
	}

	public DmVmaShipyard createDmVmaShipyard(long id) {
		return persistence.create(id);
	}

	public DmVmaShipyard deleteDmVmaShipyard(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmVmaShipyard deleteDmVmaShipyard(DmVmaShipyard DmVmaShipyard)
		throws SystemException {
		return persistence.remove(DmVmaShipyard);
	}

	public DmVmaShipyard fetchDmVmaShipyard(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmVmaShipyard getDmVmaShipyard(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmVmaShipyard> getDmVmaShipyards(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmVmaShipyardsCount() throws SystemException {
		return persistence.countAll();
	}

	public DmVmaShipyard updateDmVmaShipyard(DmVmaShipyard DmVmaShipyard)
		throws SystemException {
		return updateDmVmaShipyard(DmVmaShipyard, true);
	}

	public DmVmaShipyard updateDmVmaShipyard(DmVmaShipyard DmVmaShipyard,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmVmaShipyard, merge);
	}

    public DmVmaShipyard fetchByShipYardCode(String shipYardCode) {
        try {
            return persistence.fetchByF_shipYardCode(shipYardCode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DmVmaShipyard> findVmaShipyards(String maritimeCode,
                                                String companyName, String companyAddress, String contactEmail,
                                                String telNo, String taxCode, String isDelete,
                                                String shipYardCodeGroup, int start, int end) {
        try {
            return finder.findVmaShipYards(maritimeCode,
                    companyName, companyAddress, contactEmail, telNo, taxCode,
                    isDelete, shipYardCodeGroup, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public long countVmaShipyards(String maritimeCode, String companyName,
                                  String companyAddress, String contactEmail, String telNo,
                                  String taxCode, String isDelete, String shipYardCodeGroup) {
        try {
            return finder.countVmaShipYards(maritimeCode,
                    companyName, companyAddress, contactEmail, telNo, taxCode,
                    isDelete, shipYardCodeGroup);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    public DmVmaShipyard fetchByF_taxCode(String taxCode) {
        try {
            return persistence.fetchByF_taxCode(taxCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    // update by TrungNT -> add params
    // companyShortName,markupMaintainane,markupConstruction,markupDeconstruction,profileMaintainane,profileConstruction,profileDeconstruction

    public DmVmaShipyard updateVmaShipYard(String fromMaritimeCode,
                                           String shipYardCode, String companyName, String companyAddress,
                                           String contactEmail, String faxNo, String remarks,
                                           String maritimeCode, String telNo, String taxCode,
                                           String syncVersion, String companyShortName, int markupMaintainane,
                                           int markupConstruction, int markupDeconstruction,
                                           String profileMaintainane, String profileConstruction,
                                           String profileDeconstruction) throws SystemException,
            NoSuchDmVmaShipyardException {

        DmVmaShipyard dmVmaShipyard = persistence
                .fetchByF_shipYardCode(shipYardCode);

        if (dmVmaShipyard == null) {
            long id = CounterLocalServiceUtil.increment(DmVmaShipyard.class
                    .getName());

            dmVmaShipyard = persistence.create(id);
        }
        Date now = new Date();

        dmVmaShipyard.setShipYardCode(shipYardCode);
        dmVmaShipyard.setCompanyName(companyName);
        dmVmaShipyard.setCompanyAddress(companyAddress);
        dmVmaShipyard.setContactEmail(contactEmail);
        dmVmaShipyard.setFaxNo(faxNo);
        dmVmaShipyard.setRemarks(remarks);
        dmVmaShipyard.setMaritimeCode(maritimeCode);
        dmVmaShipyard.setTelNo(telNo);
        dmVmaShipyard.setTaxCode(taxCode);

        dmVmaShipyard.setIsDelete(0);
        dmVmaShipyard.setMarkedAsDelete(0);
        dmVmaShipyard.setModifiedDate(now);
        dmVmaShipyard.setRequestedDate(now);
        dmVmaShipyard.setSyncVersion(syncVersion);
        dmVmaShipyard.setCompanyShortName(companyShortName);
        dmVmaShipyard.setMarkupMaintainane(markupMaintainane);
        dmVmaShipyard.setMarkupConstruction(markupConstruction);
        dmVmaShipyard.setMarkupDeconstruction(markupDeconstruction);
        dmVmaShipyard.setProfileMaintainane(profileMaintainane);
        dmVmaShipyard.setProfileConstruction(profileConstruction);
        dmVmaShipyard.setProfileDeconstruction(profileDeconstruction);

        dmVmaShipyard = persistence.updateImpl(dmVmaShipyard, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaShipyard);

        return dmVmaShipyard;
    }

    public DmVmaShipyard deleteVmaShipYard(String fromMaritimeCode,
                                           String shipYardCode, String syncVersion) throws SystemException,
            NoSuchDmVmaShipyardException {

        DmVmaShipyard dmVmaShipyard = persistence
                .findByF_shipYardCode(shipYardCode);

        Date now = new Date();

        dmVmaShipyard.setIsDelete(1);
        dmVmaShipyard.setMarkedAsDelete(1);
        dmVmaShipyard.setModifiedDate(now);
        dmVmaShipyard.setRequestedDate(now);
        dmVmaShipyard.setSyncVersion(syncVersion);

        dmVmaShipyard = persistence.updateImpl(dmVmaShipyard, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaShipyard);

        return dmVmaShipyard;
    }

}
