package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import java.math.BigDecimal;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import java.util.ArrayList; import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmVmaTugboatFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmVmaTugboatPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class DmVmaTugboatLocalServiceImpl { @Autowired
DmVmaTugboatPersistenceImpl persistence;@Autowired
DmVmaTugboatFinderImpl finder;
public DmVmaTugboat addDmVmaTugboat(DmVmaTugboat DmVmaTugboat)
		throws SystemException {
		return persistence.updateImpl(DmVmaTugboat, false);
	}

	public DmVmaTugboat createDmVmaTugboat(long id) {
		return persistence.create(id);
	}

	public DmVmaTugboat deleteDmVmaTugboat(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmVmaTugboat deleteDmVmaTugboat(DmVmaTugboat DmVmaTugboat)
		throws SystemException {
		return persistence.remove(DmVmaTugboat);
	}

	public DmVmaTugboat fetchDmVmaTugboat(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmVmaTugboat getDmVmaTugboat(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmVmaTugboat> getDmVmaTugboats(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmVmaTugboatsCount() throws SystemException {
		return persistence.countAll();
	}

	public DmVmaTugboat updateDmVmaTugboat(DmVmaTugboat DmVmaTugboat)
		throws SystemException {
		return updateDmVmaTugboat(DmVmaTugboat, true);
	}

	public DmVmaTugboat updateDmVmaTugboat(DmVmaTugboat DmVmaTugboat,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmVmaTugboat, merge);
	}

    public List<DmVmaTugboat> findByMaritimeCode_tugboatCompanyCode(
            String maritimeCode, String tugboatCompanyCode, int start, int end) {
        try {
            return persistence
                    .findByF_maritimeCode_tugboatCompanyCode(maritimeCode,
                            tugboatCompanyCode, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<DmVmaTugboat> findByMaritimeCode(String maritimeCode,
                                                 int start, int end) {
        try {
            return persistence.findByF_maritimeCode(maritimeCode,
                    start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public DmVmaTugboat fetchByShipCode(String shipCode) {
        try {
            return persistence.fetchByF_shipCode(shipCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<DmVmaTugboat> findVmaTugboats(String maritimeCode,
                                              String tugboatCompanyCode, String shipName, int power1, int power2,
                                              String isDelete, String shipCodeGroup, int start, int end) {
        try {
            return finder.findVmaTugboats(maritimeCode, shipName,
                    power1, power2, tugboatCompanyCode, isDelete,
                    shipCodeGroup, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public long countVmaTugboats(String maritimeCode,
                                 String tugboatCompanyCode, String shipName, int power1, int power2,
                                 String isDelete, String shipCodeGroup) {
        try {
            return finder
                    .countVmaTugboats(maritimeCode, shipName, power1, power2,
                            tugboatCompanyCode, isDelete, shipCodeGroup);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    //update by TrungNT -> add param tugboatShortName
    public DmVmaTugboat updateVmaTugboat(String fromMaritimeCode,
                                         String maritimeCode, String tugboatCompanyCode,
                                         String tugboatCompanyName, String shipCode, String shipName,
                                         double power, double loa, double breadth, double clearanceHeight,
                                         double displacement, String unitPower, double vndUnitPrice,
                                         double usdUnitPrice, int gt, int nt, int dwt, String unitGRT,
                                         String unitNT, String unitDWT, String remarks, String syncVersion, String tugboatShortName, Date tugboatExpiredDate)
            throws SystemException, NoSuchDmVmaTugboatException {

        DmVmaTugboat dmVmaTugboat = persistence
                .fetchByF_shipCode(shipCode);

        if (dmVmaTugboat == null) {
            long id = CounterLocalServiceUtil.increment(DmVmaTugboat.class
                    .getName());

            dmVmaTugboat = persistence.create(id);
        }
        Date now = new Date();

        dmVmaTugboat.setTugboatCompanyCode(tugboatCompanyCode);
        dmVmaTugboat.setTugboatCompanyName(tugboatCompanyName);
        dmVmaTugboat.setShipCode(shipCode);
        dmVmaTugboat.setShipName(shipName);
        dmVmaTugboat.setPower(power);
        dmVmaTugboat.setLoa(loa);
        dmVmaTugboat.setBreadth(breadth);
        dmVmaTugboat.setClearanceHeight(clearanceHeight);
        dmVmaTugboat.setDisplacement(displacement);
        dmVmaTugboat.setRemarks(remarks);
        dmVmaTugboat.setUnitPower(unitPower);
        dmVmaTugboat.setVndUnitPrice(vndUnitPrice);
        dmVmaTugboat.setUsdUnitPrice(usdUnitPrice);
        dmVmaTugboat.setGt(BigDecimal.valueOf(gt));
        dmVmaTugboat.setNt(BigDecimal.valueOf(nt));
        dmVmaTugboat.setDwt(BigDecimal.valueOf(dwt));
        dmVmaTugboat.setUnitDWT(unitDWT);
        dmVmaTugboat.setUnitGRT(unitGRT);
        dmVmaTugboat.setUnitNT(unitNT);
        dmVmaTugboat.setMaritimeCode(maritimeCode);

        dmVmaTugboat.setIsDelete(0);
        dmVmaTugboat.setMarkedAsDelete(0);
        dmVmaTugboat.setModifiedDate(now);
        dmVmaTugboat.setRequestedDate(now);
        dmVmaTugboat.setSyncVersion(syncVersion);
        dmVmaTugboat.setTugboatShortName(tugboatShortName);
        dmVmaTugboat.setTugboatExpiredDate(tugboatExpiredDate);

        dmVmaTugboat = persistence.updateImpl(dmVmaTugboat, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaTugboat);

        return dmVmaTugboat;
    }

    public DmVmaTugboat deleteVmaTugboat(String fromMaritimeCode,
                                         String shipCode, String syncVersion) throws SystemException,
            NoSuchDmVmaTugboatException {

        DmVmaTugboat dmVmaTugboat = persistence
                .findByF_shipCode(shipCode);

        Date now = new Date();
        dmVmaTugboat.setIsDelete(1);
        dmVmaTugboat.setMarkedAsDelete(1);
        dmVmaTugboat.setModifiedDate(now);
        dmVmaTugboat.setRequestedDate(now);
        dmVmaTugboat.setSyncVersion(syncVersion);

        dmVmaTugboat = persistence.updateImpl(dmVmaTugboat, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaTugboat);

        return dmVmaTugboat;
    }

    public JSONObject getModelMau24_1T(String maritimeCode, String shipCode,
                                       String startDate, String endDate) throws SystemException {
        return finder.getModelMau24_1T(maritimeCode, shipCode,
                startDate, endDate);
    }

    public JSONArray getModelMau36T(String maritimeCode,
                                    String pilotCompanyCode, String startDate, String endDate)
            throws SystemException {
        return finder.getModelMau36T(maritimeCode,
                pilotCompanyCode, startDate, endDate);
    }

}
