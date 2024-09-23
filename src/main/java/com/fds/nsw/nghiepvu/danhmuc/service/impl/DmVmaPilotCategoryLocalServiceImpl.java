package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmVmaPilotCategoryFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmVmaPilotCategoryPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class DmVmaPilotCategoryLocalServiceImpl { @Autowired
DmVmaPilotCategoryPersistenceImpl persistence;@Autowired
DmVmaPilotCategoryFinderImpl finder;
public DmVmaPilotCategory addDmVmaPilotCategory(DmVmaPilotCategory DmVmaPilotCategory)
		throws SystemException {
		return persistence.updateImpl(DmVmaPilotCategory, false);
	}

	public DmVmaPilotCategory createDmVmaPilotCategory(long id) {
		return persistence.create(id);
	}

	public DmVmaPilotCategory deleteDmVmaPilotCategory(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmVmaPilotCategory deleteDmVmaPilotCategory(DmVmaPilotCategory DmVmaPilotCategory)
		throws SystemException {
		return persistence.remove(DmVmaPilotCategory);
	}

	public DmVmaPilotCategory fetchDmVmaPilotCategory(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmVmaPilotCategory getDmVmaPilotCategory(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmVmaPilotCategory> getDmVmaPilotCategories(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmVmaPilotCategoriesCount() throws SystemException {
		return persistence.countAll();
	}

	public DmVmaPilotCategory updateDmVmaPilotCategory(DmVmaPilotCategory DmVmaPilotCategory)
		throws SystemException {
		return updateDmVmaPilotCategory(DmVmaPilotCategory, true);
	}

	public DmVmaPilotCategory updateDmVmaPilotCategory(DmVmaPilotCategory DmVmaPilotCategory,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmVmaPilotCategory, merge);
	}

    public DmVmaPilotCategory fetchByPilotCategoryCode(String pilotCategoryCode) {
        try {
            return persistence
                    .fetchByF_pilotCategoryCode(pilotCategoryCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<DmVmaPilotCategory> findByPilotCategoryName(
            String pilotCategoryName, int start, int end) {
        try {
            return persistence.findByF_pilotCategoryName(
                    pilotCategoryName, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<DmVmaPilotCategory> findPilotCategoryies(
            String pilotCategoryName, String isDelete,
            String pilotCategoryCodeGroup, int start, int end) {
        try {
            return finder.findPilotCategoryies(
                    pilotCategoryName, isDelete, pilotCategoryCodeGroup, start,
                    end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public long countPilotCategoryies(String pilotCategoryName,
                                      String isDelete, String pilotCategoryCodeGroup) {
        try {
            return finder.countPilotCategoryies(
                    pilotCategoryName, isDelete, pilotCategoryCodeGroup);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    public DmVmaPilotCategory updateVmaPilotCategory(String fromMaritimeCode,
                                                     String pilotCategoryCode, String pilotCategoryName,
                                                     String maxLength, String safeTime, String remarks,
                                                     String grossTonage, String syncVersion) throws SystemException,
            NoSuchDmVmaPilotCategoryException {

        DmVmaPilotCategory dmVmaPilotCategory = persistence
                .fetchByF_pilotCategoryCode(pilotCategoryCode);

        if (dmVmaPilotCategory == null) {
            long id = CounterLocalServiceUtil.increment(DmVmaPilotCategory.class
                    .getName());

            dmVmaPilotCategory = persistence.create(id);
        }
        Date now = new Date();

        dmVmaPilotCategory.setPilotCategoryCode(pilotCategoryCode);
        dmVmaPilotCategory.setPilotCategoryName(pilotCategoryName);
        dmVmaPilotCategory.setMaxLength(maxLength);
        dmVmaPilotCategory.setSafeTime(safeTime);
        dmVmaPilotCategory.setRemarks(remarks);
        dmVmaPilotCategory.setGrossTonage(grossTonage);

        dmVmaPilotCategory.setIsDelete(0);
        dmVmaPilotCategory.setMarkedAsDelete(0);
        dmVmaPilotCategory.setModifiedDate(now);
        dmVmaPilotCategory.setRequestedDate(now);
        dmVmaPilotCategory.setSyncVersion(syncVersion);

        dmVmaPilotCategory = persistence.updateImpl(
                dmVmaPilotCategory, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaPilotCategory);

        return dmVmaPilotCategory;
    }

    public DmVmaPilotCategory deleteVmaPilotCategory(String fromMaritimeCode,
                                                     String pilotCategoryCode, String syncVersion)
            throws SystemException, NoSuchDmVmaPilotCategoryException {

        DmVmaPilotCategory dmVmaPilotCategory = persistence
                .findByF_pilotCategoryCode(pilotCategoryCode);

        Date now = new Date();

        dmVmaPilotCategory.setIsDelete(1);
        dmVmaPilotCategory.setMarkedAsDelete(1);
        dmVmaPilotCategory.setModifiedDate(now);
        dmVmaPilotCategory.setRequestedDate(now);
        dmVmaPilotCategory.setSyncVersion(syncVersion);

        dmVmaPilotCategory = persistence.updateImpl(
                dmVmaPilotCategory, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmVmaPilotCategory);

        return dmVmaPilotCategory;
    }


}
