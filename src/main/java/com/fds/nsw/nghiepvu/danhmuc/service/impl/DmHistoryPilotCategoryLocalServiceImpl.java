package com.fds.nsw.nghiepvu.danhmuc.service.impl;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmHistoryPilotCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryPilotCategoryPersistenceImpl;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DmHistoryPilotCategoryLocalServiceImpl {
    @Autowired
    DmHistoryPilotCategoryPersistenceImpl persistence;

    public DmHistoryPilotCategory fetchByPilotCategoryCode_SyncVersion(
            String pilotCategoryCode, String syncVersion) {
        try {
            return persistence
                    .fetchByF_pilotCategoryCode_syncVersion(pilotCategoryCode,
                            syncVersion);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public DmHistoryPilotCategory updateHistoryPilotCategory(
            String fromMaritimeCode, String pilotCategoryCode,
            String pilotCategoryName, String maxLength, String safeTime,
            String remarks, String grossTonage, String syncVersion)
            throws SystemException, NoSuchDmHistoryPilotCategoryException {

        DmHistoryPilotCategory dmHistoryPilotCategory = persistence
                .fetchByF_pilotCategoryCode_syncVersion(pilotCategoryCode,
                        syncVersion);

        if (dmHistoryPilotCategory == null) {
            long id = CounterLocalServiceUtil
                    .increment(DmHistoryPilotCategory.class.getName());

            dmHistoryPilotCategory = persistence
                    .create(id);
        }

        dmHistoryPilotCategory.setPilotCategoryCode(pilotCategoryCode);
        dmHistoryPilotCategory.setPilotCategoryName(pilotCategoryName);
        dmHistoryPilotCategory.setMaxLength(maxLength);
        dmHistoryPilotCategory.setSafeTime(safeTime);
        dmHistoryPilotCategory.setRemarks(remarks);
        dmHistoryPilotCategory.setGrossTonage(grossTonage);

        dmHistoryPilotCategory.setIsDelete(0);
        dmHistoryPilotCategory.setMarkedAsDelete(0);
        dmHistoryPilotCategory.setModifiedDate(new Date());
        dmHistoryPilotCategory.setSyncVersion(syncVersion);

        dmHistoryPilotCategory = persistence.updateImpl(dmHistoryPilotCategory,
                true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistoryPilotCategory);

        return dmHistoryPilotCategory;
    }

    public DmHistoryPilotCategory deleteHistoryPilotCategory(
            String fromMaritimeCode, String pilotCategoryCode,
            String syncVersion) throws SystemException,
            NoSuchDmHistoryPilotCategoryException {

        DmHistoryPilotCategory dmHistoryPilotCategory = persistence
                .findByF_pilotCategoryCode_syncVersion(pilotCategoryCode,
                        syncVersion);

        if (dmHistoryPilotCategory == null) {
            long id = CounterLocalServiceUtil
                    .increment(DmHistoryPilotCategory.class.getName());

            dmHistoryPilotCategory = persistence
                    .create(id);
        }

        dmHistoryPilotCategory.setIsDelete(1);
        dmHistoryPilotCategory.setMarkedAsDelete(1);
        dmHistoryPilotCategory.setModifiedDate(new Date());
        dmHistoryPilotCategory.setSyncVersion(syncVersion);

        dmHistoryPilotCategory = persistence.updateImpl(dmHistoryPilotCategory,
                true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistoryPilotCategory);

        return dmHistoryPilotCategory;
    }

    public DmHistoryPilotCategory addDmHistoryPilotCategory(DmHistoryPilotCategory DmHistoryPilotCategory)
		throws SystemException {
		return persistence.updateImpl(DmHistoryPilotCategory, false);
	}

	public DmHistoryPilotCategory createDmHistoryPilotCategory(long id) {
		return persistence.create(id);
	}

	public DmHistoryPilotCategory deleteDmHistoryPilotCategory(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmHistoryPilotCategory deleteDmHistoryPilotCategory(DmHistoryPilotCategory DmHistoryPilotCategory)
		throws SystemException {
		return persistence.remove(DmHistoryPilotCategory);
	}

	public DmHistoryPilotCategory fetchDmHistoryPilotCategory(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmHistoryPilotCategory getDmHistoryPilotCategory(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmHistoryPilotCategory> getDmHistoryPilotCategories(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmHistoryPilotCategoriesCount() throws SystemException {
		return persistence.countAll();
	}

	public DmHistoryPilotCategory updateDmHistoryPilotCategory(DmHistoryPilotCategory DmHistoryPilotCategory)
		throws SystemException {
		return updateDmHistoryPilotCategory(DmHistoryPilotCategory, true);
	}

	public DmHistoryPilotCategory updateDmHistoryPilotCategory(DmHistoryPilotCategory DmHistoryPilotCategory,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmHistoryPilotCategory, merge);
	}

}
