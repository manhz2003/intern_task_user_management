package com.fds.nsw.nghiepvu.danhmuc.service.impl;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryPilot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryPilotPersistenceImpl;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DmHistoryPilotLocalServiceImpl {

    @Autowired
    DmHistoryPilotPersistenceImpl persistence;
    public DmHistoryPilot fetchByPilotCode_SyncVersion(String pilotCode,
                                                       String syncVersion) {
        try {
            return persistence.fetchByF_pilotCode_syncVersion(
                    pilotCode, syncVersion);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    // update by TrungNT -> add param pilotShortName
    public DmHistoryPilot updateHistoryPilot(String fromMaritimeCode,
                                             String maritimeCode, String pilotCompanyCode,
                                             String pilotCompanyName, String pilotBOD, String pilotNo,
                                             String pilotCode, String pilotName, String pilotCertificateNo,
                                             String pilotCategoryCode, Date pilotCertificateDate,
                                             String remarks, String syncVersion, String pilotShortName,
                                             Date pilotExpiredDate) throws SystemException,
            NoSuchDmHistoryPilotException {

        DmHistoryPilot dmHistoryPilot = persistence
                .fetchByF_pilotCode_syncVersion(pilotCode, syncVersion);

        if (dmHistoryPilot == null) {
            long id = CounterLocalServiceUtil.increment(DmHistoryPilot.class
                    .getName());
            dmHistoryPilot = persistence.create(id);
        }

        dmHistoryPilot.setPilotCompanyCode(pilotCompanyCode);
        dmHistoryPilot.setPilotCompanyName(pilotCompanyName);
        dmHistoryPilot.setPilotBOD(pilotBOD);
        dmHistoryPilot.setPilotNo(pilotNo);
        dmHistoryPilot.setPilotCode(pilotCode);
        dmHistoryPilot.setPilotName(pilotName);
        dmHistoryPilot.setPilotCertificateNo(pilotCertificateNo);
        dmHistoryPilot.setPilotCategoryCode(pilotCategoryCode);
        dmHistoryPilot.setPilotCertificateDate(pilotCertificateDate);
        dmHistoryPilot.setRemarks(remarks);
        dmHistoryPilot.setMaritimeCode(maritimeCode);

        dmHistoryPilot.setIsDelete(0);
        dmHistoryPilot.setMarkedAsDelete(0);
        dmHistoryPilot.setModifiedDate(new Date());
        dmHistoryPilot.setSyncVersion(syncVersion);
        dmHistoryPilot.setPilotShortName(pilotShortName);
        dmHistoryPilot.setPilotExpiredDate(pilotExpiredDate);

        dmHistoryPilot = persistence.updateImpl(dmHistoryPilot, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistoryPilot);

        return dmHistoryPilot;
    }

    public DmHistoryPilot deleteHistoryPilot(String fromMaritimeCode,
                                             String pilotCode, String syncVersion) throws SystemException,
            NoSuchDmHistoryPilotException {

        DmHistoryPilot dmHistoryPilot = persistence
                .findByF_pilotCode_syncVersion(pilotCode, syncVersion);

        if (dmHistoryPilot == null) {
            long id = CounterLocalServiceUtil.increment(DmHistoryPilot.class
                    .getName());
            dmHistoryPilot = persistence.create(id);
        }

        dmHistoryPilot.setIsDelete(1);
        dmHistoryPilot.setMarkedAsDelete(1);
        dmHistoryPilot.setModifiedDate(new Date());
        dmHistoryPilot.setSyncVersion(syncVersion);

        dmHistoryPilot = persistence.updateImpl(dmHistoryPilot, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistoryPilot);

        return dmHistoryPilot;
    }

    public DmHistoryPilot addDmHistoryPilot(DmHistoryPilot DmHistoryPilot)
		throws SystemException {
		return persistence.updateImpl(DmHistoryPilot, false);
	}

	public DmHistoryPilot createDmHistoryPilot(long id) {
		return persistence.create(id);
	}

	public DmHistoryPilot deleteDmHistoryPilot(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmHistoryPilot deleteDmHistoryPilot(DmHistoryPilot DmHistoryPilot)
		throws SystemException {
		return persistence.remove(DmHistoryPilot);
	}

	public DmHistoryPilot fetchDmHistoryPilot(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmHistoryPilot getDmHistoryPilot(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmHistoryPilot> getDmHistoryPilots(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmHistoryPilotsCount() throws SystemException {
		return persistence.countAll();
	}

	public DmHistoryPilot updateDmHistoryPilot(DmHistoryPilot DmHistoryPilot)
		throws SystemException {
		return updateDmHistoryPilot(DmHistoryPilot, true);
	}

	public DmHistoryPilot updateDmHistoryPilot(DmHistoryPilot DmHistoryPilot,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmHistoryPilot, merge);
	}

}
