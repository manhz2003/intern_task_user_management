package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import org.json.JSONObject;
import vn.gt.dao.danhmuc.service.DmVmaPilotLocalServiceUtil;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import java.util.Date;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import java.util.ArrayList; import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmVmaPilotFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmVmaPilotPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class DmVmaPilotLocalServiceImpl { @Autowired
DmVmaPilotPersistenceImpl persistence;@Autowired
DmVmaPilotFinderImpl finder;
public DmVmaPilot addDmVmaPilot(DmVmaPilot DmVmaPilot)
		throws SystemException {
		return persistence.updateImpl(DmVmaPilot, false);
	}

	public DmVmaPilot createDmVmaPilot(long id) {
		return persistence.create(id);
	}

	public DmVmaPilot deleteDmVmaPilot(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmVmaPilot deleteDmVmaPilot(DmVmaPilot DmVmaPilot)
		throws SystemException {
		return persistence.remove(DmVmaPilot);
	}

	public DmVmaPilot fetchDmVmaPilot(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmVmaPilot getDmVmaPilot(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmVmaPilot> getDmVmaPilots(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmVmaPilotsCount() throws SystemException {
		return persistence.countAll();
	}

	public DmVmaPilot updateDmVmaPilot(DmVmaPilot DmVmaPilot)
		throws SystemException {
		return updateDmVmaPilot(DmVmaPilot, true);
	}

	public DmVmaPilot updateDmVmaPilot(DmVmaPilot DmVmaPilot,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmVmaPilot, merge);
	}

    public List<DmVmaPilot> findbyMaritimeCode_pilotCompanyCode(
            String maritimeCode, String pilotCompanyCode, int start, int end) {
        try {
            return persistence.findByF_maritimeCode_pilotCompanyCode(
                    maritimeCode, pilotCompanyCode, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<DmVmaPilot> findbyMaritimeCode(String maritimeCode, int start,
                                               int end) {
        try {
            return persistence.findByF_maritimeCode(maritimeCode,
                    start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public DmVmaPilot fetchbyPilotCode(String pilotCode) {
        try {
            return persistence.fetchByF_pilotCode(pilotCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<DmVmaPilot> findVmaPilots(String maritimeCode,
                                          String pilotCompanyCode, String pilotCategoryCode,
                                          String pilotName, String isDelete, String pilotCodeGroup,
                                          int start, int end) {
        try {
            return finder.findVmaPilots(maritimeCode,
                    pilotCompanyCode, pilotCategoryCode, pilotName, isDelete,
                    pilotCodeGroup, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public long countVmaPilots(String maritimeCode, String pilotCompanyCode,
                               String pilotCategoryCode, String pilotName, String isDelete,
                               String pilotCodeGroup) {
        try {
            return finder.countVmaPilots(maritimeCode,
                    pilotCompanyCode, pilotCategoryCode, pilotName, isDelete,
                    pilotCodeGroup);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    // update by TrungNT -> add param pilotShortName
    public DmVmaPilot updateVmaPilot(String fromMaritimeCode,
                                     String maritimeCode, String pilotCompanyCode,
                                     String pilotCompanyName, String pilotBOD, String pilotNo,
                                     String pilotCode, String pilotName, String pilotCertificateNo,
                                     String pilotCategoryCode, Date pilotCertificateDate,
                                     String remarks, String syncVersion, String pilotShortName,
                                     Date pilotExpiredDate) throws SystemException,
            NoSuchDmVmaPilotException {

        DmVmaPilot dmVmaPilot = persistence
                .fetchByF_pilotCode(pilotCode);

        if (dmVmaPilot == null) {
            long id = CounterLocalServiceUtil.increment(DmVmaPilot.class.getName());

            dmVmaPilot = persistence.create(id);
        }
        Date now = new Date();

        dmVmaPilot.setPilotCompanyCode(pilotCompanyCode);
        dmVmaPilot.setPilotCompanyName(pilotCompanyName);
        dmVmaPilot.setPilotBOD(pilotBOD);
        dmVmaPilot.setPilotNo(pilotNo);
        dmVmaPilot.setPilotCode(pilotCode);
        dmVmaPilot.setPilotName(pilotName);
        dmVmaPilot.setPilotCertificateNo(pilotCertificateNo);
        dmVmaPilot.setPilotCategoryCode(pilotCategoryCode);
        dmVmaPilot.setPilotCertificateDate(pilotCertificateDate);
        dmVmaPilot.setRemarks(remarks);
        dmVmaPilot.setMaritimeCode(maritimeCode);

        dmVmaPilot.setIsDelete(0);
        dmVmaPilot.setMarkedAsDelete(0);
        dmVmaPilot.setModifiedDate(now);
        dmVmaPilot.setRequestedDate(now);
        dmVmaPilot.setSyncVersion(syncVersion);
        dmVmaPilot.setPilotShortName(pilotShortName);
        dmVmaPilot.setPilotExpiredDate(pilotExpiredDate);

        dmVmaPilot = persistence.updateImpl(dmVmaPilot, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaPilot);

        return dmVmaPilot;
    }

    public DmVmaPilot deleteVmaPilot(String fromMaritimeCode, String pilotCode,
                                     String syncVersion) throws SystemException,
            NoSuchDmVmaPilotException {

        DmVmaPilot dmVmaPilot = persistence
                .findByF_pilotCode(pilotCode);

        Date now = new Date();

        dmVmaPilot.setIsDelete(1);
        dmVmaPilot.setMarkedAsDelete(1);
        dmVmaPilot.setModifiedDate(now);
        dmVmaPilot.setRequestedDate(now);
        dmVmaPilot.setSyncVersion(syncVersion);
        DmVmaPilotLocalServiceUtil.updateDmVmaPilot(dmVmaPilot);

        dmVmaPilot = persistence.updateImpl(dmVmaPilot, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaPilot);

        return dmVmaPilot;
    }

    public JSONObject getModelMau26_1T(String maritimeCode, String pilotCode)
            throws SystemException {
        return finder.getModelMau26_1T(maritimeCode, pilotCode);
    }

}
