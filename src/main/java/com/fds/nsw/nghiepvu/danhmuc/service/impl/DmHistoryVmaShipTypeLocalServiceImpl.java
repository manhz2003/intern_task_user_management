package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryVmaShipTypePersistenceImpl;
import com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType;
import org.springframework.beans.factory.annotation.Autowired;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;import org.springframework.stereotype.Service;@Service@Slf4j

public class DmHistoryVmaShipTypeLocalServiceImpl {
    @Autowired
    DmHistoryVmaShipTypePersistenceImpl persistence;
    public DmHistoryVmaShipType fetchByShiptypeCode_SyncVersion(
            String shipTypeCode, String syncVersion) {
        try {
            return persistence
                    .fetchByF_shipTypeCode_syncVersion(shipTypeCode,
                            syncVersion);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DmHistoryVmaShipType updateHistoryVmaShipType(
            String fromMaritimeCode, String shipTypeCode, String shipTypeName,
            int applyBoat, int applyShip, String remarks, String shipTypeRef,
            String syncVersion) throws SystemException,
            NoSuchDmHistoryVmaShipTypeException {

        DmHistoryVmaShipType dmHistoryVmaShipType = persistence
                .fetchByF_shipTypeCode_syncVersion(shipTypeCode, syncVersion);

        if (dmHistoryVmaShipType == null) {

            long id = CounterLocalServiceUtil.increment(DmHistoryVmaShipType.class
                    .getName());
            dmHistoryVmaShipType = persistence.create(id);

        }

        dmHistoryVmaShipType.setShipTypeCode(shipTypeCode);
        dmHistoryVmaShipType.setShipTypeName(shipTypeName);
        dmHistoryVmaShipType.setApplyBoat(String.valueOf(applyBoat));
        dmHistoryVmaShipType.setApplyShip(String.valueOf(applyShip));
        dmHistoryVmaShipType.setRemarks(remarks);
        dmHistoryVmaShipType.setShipTypeRef(shipTypeRef);

        dmHistoryVmaShipType.setIsDelete(0);
        dmHistoryVmaShipType.setMarkedAsDelete(0);
        dmHistoryVmaShipType.setModifiedDate(new Date());
        dmHistoryVmaShipType.setSyncVersion(syncVersion);

        dmHistoryVmaShipType = persistence.updateImpl(dmHistoryVmaShipType,
                true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistoryVmaShipType);

        return dmHistoryVmaShipType;
    }

    public DmHistoryVmaShipType deleteHistoryVmaShipType(
            String fromMaritimeCode, String shipTypeCode, String syncVersion)
            throws SystemException, NoSuchDmHistoryVmaShipTypeException {

        DmHistoryVmaShipType dmHistoryVmaShipType = persistence
                .findByF_shipTypeCode_syncVersion(shipTypeCode, syncVersion);

        if (dmHistoryVmaShipType == null) {

            long id = CounterLocalServiceUtil.increment(DmHistoryVmaShipType.class
                    .getName());
            dmHistoryVmaShipType = persistence.create(id);

        }

        dmHistoryVmaShipType.setIsDelete(1);
        dmHistoryVmaShipType.setMarkedAsDelete(1);
        dmHistoryVmaShipType.setModifiedDate(new Date());
        dmHistoryVmaShipType.setSyncVersion(syncVersion);

        dmHistoryVmaShipType = persistence.updateImpl(dmHistoryVmaShipType,
                true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistoryVmaShipType);

        return dmHistoryVmaShipType;
    }
    public DmHistoryVmaShipType addDmHistoryVmaShipType(DmHistoryVmaShipType DmHistoryVmaShipType)
		throws SystemException {
		return persistence.updateImpl(DmHistoryVmaShipType, false);
	}

	public DmHistoryVmaShipType createDmHistoryVmaShipType(long id) {
		return persistence.create(id);
	}

	public DmHistoryVmaShipType deleteDmHistoryVmaShipType(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmHistoryVmaShipType deleteDmHistoryVmaShipType(DmHistoryVmaShipType DmHistoryVmaShipType)
		throws SystemException {
		return persistence.remove(DmHistoryVmaShipType);
	}

	public DmHistoryVmaShipType fetchDmHistoryVmaShipType(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmHistoryVmaShipType getDmHistoryVmaShipType(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmHistoryVmaShipType> getDmHistoryVmaShipTypes(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmHistoryVmaShipTypesCount() throws SystemException {
		return persistence.countAll();
	}

	public DmHistoryVmaShipType updateDmHistoryVmaShipType(DmHistoryVmaShipType DmHistoryVmaShipType)
		throws SystemException {
		return updateDmHistoryVmaShipType(DmHistoryVmaShipType, true);
	}

	public DmHistoryVmaShipType updateDmHistoryVmaShipType(DmHistoryVmaShipType DmHistoryVmaShipType,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmHistoryVmaShipType, merge);
	}

}
