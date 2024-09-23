package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import java.math.BigDecimal;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import java.util.ArrayList; import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import java.util.Date;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryTugboatPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class DmHistoryTugboatLocalServiceImpl { @Autowired
DmHistoryTugboatPersistenceImpl persistence;
    public DmHistoryTugboat fetchByShipCode_SyncVersion(String shipCode,
                                                        String syncVersion) {
        try {
            return persistence.fetchByF_shipCode_syncVersion(
                    shipCode, syncVersion);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    //update by TrungNT -> add param tugboatShortName
    public DmHistoryTugboat updateHistoryTugboat(String fromMaritimeCode,
                                                 String maritimeCode, String tugboatCompanyCode,
                                                 String tugboatCompanyName, String shipCode, String shipName,
                                                 double power, double loa, double breadth, double clearanceHeight,
                                                 double displacement, String remarks, String unitPower,
                                                 double vndUnitPrice, double usdUnitPrice, int gt, int nt, int dwt,
                                                 String unitGRT, String unitNT, String unitDWT, String syncVersion,
                                                 String tugboatShortName, Date tugboatExpiredDate) throws SystemException,
            NoSuchDmHistoryTugboatException {

        DmHistoryTugboat dmHistoryTugboat = persistence
                .fetchByF_shipCode_syncVersion(shipCode, syncVersion);

        if (dmHistoryTugboat == null) {
            long id = CounterLocalServiceUtil.increment(DmHistoryTugboat.class
                    .getName());
            dmHistoryTugboat = persistence.create(id);
        }

        dmHistoryTugboat.setTugboatCompanyCode(tugboatCompanyCode);
        dmHistoryTugboat.setTugboatCompanyName(tugboatCompanyName);
        dmHistoryTugboat.setShipCode(shipCode);
        dmHistoryTugboat.setShipName(shipName);
        dmHistoryTugboat.setPower(power);
        dmHistoryTugboat.setLoa(loa);
        dmHistoryTugboat.setBreadth(breadth);
        dmHistoryTugboat.setClearanceHeight(clearanceHeight);
        dmHistoryTugboat.setDisplacement(displacement);
        dmHistoryTugboat.setRemarks(remarks);
        dmHistoryTugboat.setUnitPower(unitPower);
        dmHistoryTugboat.setVndUnitPrice(vndUnitPrice);
        dmHistoryTugboat.setUsdUnitPrice(usdUnitPrice);
        dmHistoryTugboat.setGt(BigDecimal.valueOf(gt));
        dmHistoryTugboat.setNt(BigDecimal.valueOf(nt));
        dmHistoryTugboat.setDwt(BigDecimal.valueOf(dwt));
        dmHistoryTugboat.setUnitDWT(unitDWT);
        dmHistoryTugboat.setUnitGRT(unitGRT);
        dmHistoryTugboat.setUnitNT(unitNT);
        dmHistoryTugboat.setMaritimeCode(maritimeCode);

        dmHistoryTugboat.setIsDelete(0);
        dmHistoryTugboat.setMarkedAsDelete(0);
        dmHistoryTugboat.setModifiedDate(new Date());
        dmHistoryTugboat.setSyncVersion(syncVersion);
        dmHistoryTugboat.setTugboatShortName(tugboatShortName);
        dmHistoryTugboat.setTugboatExpiredDate(tugboatExpiredDate);

        dmHistoryTugboat = persistence.updateImpl(dmHistoryTugboat,
                true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistoryTugboat);

        return dmHistoryTugboat;
    }

    public DmHistoryTugboat deleteHistoryTugboat(String fromMaritimeCode,
                                                 String shipCode, String syncVersion) throws SystemException,
            NoSuchDmHistoryTugboatException {

        DmHistoryTugboat dmHistoryTugboat = persistence
                .fetchByF_shipCode_syncVersion(shipCode, syncVersion);

        if (dmHistoryTugboat == null) {
            long id = CounterLocalServiceUtil.increment(DmHistoryTugboat.class
                    .getName());
            dmHistoryTugboat = persistence.create(id);
        }

        dmHistoryTugboat.setIsDelete(1);
        dmHistoryTugboat.setMarkedAsDelete(1);
        dmHistoryTugboat.setModifiedDate(new Date());
        dmHistoryTugboat.setSyncVersion(syncVersion);

        dmHistoryTugboat = persistence.updateImpl(dmHistoryTugboat,
                true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistoryTugboat);

        return dmHistoryTugboat;
    }


public DmHistoryTugboat addDmHistoryTugboat(DmHistoryTugboat DmHistoryTugboat)
		throws SystemException {
		return persistence.updateImpl(DmHistoryTugboat, false);
	}

	public DmHistoryTugboat createDmHistoryTugboat(long id) {
		return persistence.create(id);
	}

	public DmHistoryTugboat deleteDmHistoryTugboat(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmHistoryTugboat deleteDmHistoryTugboat(DmHistoryTugboat DmHistoryTugboat)
		throws SystemException {
		return persistence.remove(DmHistoryTugboat);
	}

	public DmHistoryTugboat fetchDmHistoryTugboat(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmHistoryTugboat getDmHistoryTugboat(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmHistoryTugboat> getDmHistoryTugboats(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmHistoryTugboatsCount() throws SystemException {
		return persistence.countAll();
	}

	public DmHistoryTugboat updateDmHistoryTugboat(DmHistoryTugboat DmHistoryTugboat)
		throws SystemException {
		return updateDmHistoryTugboat(DmHistoryTugboat, true);
	}

	public DmHistoryTugboat updateDmHistoryTugboat(DmHistoryTugboat DmHistoryTugboat,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmHistoryTugboat, merge);
	}
}
