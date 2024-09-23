package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import java.util.ArrayList; import java.util.List;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmVmaShipTypeFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmVmaShipTypePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class DmVmaShipTypeLocalServiceImpl { @Autowired
DmVmaShipTypePersistenceImpl persistence;@Autowired
DmVmaShipTypeFinderImpl finder;
public DmVmaShipType addDmVmaShipType(DmVmaShipType DmVmaShipType)
		throws SystemException {
		return persistence.updateImpl(DmVmaShipType, false);
	}

	public DmVmaShipType createDmVmaShipType(long id) {
		return persistence.create(id);
	}

	public DmVmaShipType deleteDmVmaShipType(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmVmaShipType deleteDmVmaShipType(DmVmaShipType DmVmaShipType)
		throws SystemException {
		return persistence.remove(DmVmaShipType);
	}

	public DmVmaShipType fetchDmVmaShipType(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmVmaShipType getDmVmaShipType(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmVmaShipType> getDmVmaShipTypes(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmVmaShipTypesCount() throws SystemException {
		return persistence.countAll();
	}

	public DmVmaShipType updateDmVmaShipType(DmVmaShipType DmVmaShipType)
		throws SystemException {
		return updateDmVmaShipType(DmVmaShipType, true);
	}

	public DmVmaShipType updateDmVmaShipType(DmVmaShipType DmVmaShipType,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmVmaShipType, merge);
	}

    public DmVmaShipType fetchByShipTypeCode(String shipTypeCode) {
        try {
            return persistence.fetchByF_shipTypeCode(shipTypeCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<DmVmaShipType> findVmaShipTypes(String shipTypeName,
                                                String applyShip, String applyBoat, String isDelete,
                                                String shipTypeCodeGroup, int start, int end) {
        try {
            return finder.findVmaShipTypes(shipTypeName,
                    applyShip, applyBoat, isDelete, shipTypeCodeGroup, start,
                    end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public long countVmaShipTypes(String shipTypeName, String applyShip,
                                  String applyBoat, String isDelete, String shipTypeCodeGroup) {
        try {
            return finder.countVmaShipTypes(shipTypeName,
                    applyShip, applyBoat, isDelete, shipTypeCodeGroup);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    public DmVmaShipType updateVmaShipType(String fromMaritimeCode,
                                           String shipTypeCode, String shipTypeName, int applyBoat,
                                           int applyShip, String remarks, String shipTypeRef,
                                           String syncVersion) throws SystemException {

        DmVmaShipType dmVmaShipType = persistence
                .fetchByF_shipTypeCode(shipTypeCode);

        if (dmVmaShipType == null) {
            long id = CounterLocalServiceUtil.increment(DmVmaShipType.class
                    .getName());

            dmVmaShipType = persistence.create(id);
        }
        Date now = new Date();

        dmVmaShipType.setShipTypeCode(shipTypeCode);
        dmVmaShipType.setShipTypeName(shipTypeName);
        dmVmaShipType.setApplyBoat(String.valueOf(applyBoat));
        dmVmaShipType.setApplyShip(String.valueOf(applyShip));
        dmVmaShipType.setRemarks(remarks);
        dmVmaShipType.setShipTypeRef(shipTypeRef);

        dmVmaShipType.setIsDelete(0);
        dmVmaShipType.setMarkedAsDelete(0);
        dmVmaShipType.setModifiedDate(now);
        dmVmaShipType.setRequestedDate(now);
        dmVmaShipType.setSyncVersion(syncVersion);

        dmVmaShipType = persistence.updateImpl(dmVmaShipType, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaShipType);

        return dmVmaShipType;
    }

    public DmVmaShipType deleteVmaShipType(String fromMaritimeCode,
                                           String shipTypeCode, String syncVersion) throws SystemException,
            NoSuchDmVmaShipTypeException {

        DmVmaShipType dmVmaShipType = persistence
                .findByF_shipTypeCode(shipTypeCode);

        Date now = new Date();

        dmVmaShipType.setIsDelete(1);
        dmVmaShipType.setMarkedAsDelete(1);
        dmVmaShipType.setModifiedDate(now);
        dmVmaShipType.setRequestedDate(now);
        dmVmaShipType.setSyncVersion(syncVersion);

        dmVmaShipType = persistence.updateImpl(dmVmaShipType, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaShipType);

        return dmVmaShipType;
    }

}
