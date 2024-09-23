package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*; 
import vn.gt.portlet.phuongtien.VMAUtils;import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import java.util.Date;
import java.util.ArrayList; 
import java.util.List;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import org.json.*;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.kernel.util.OrderByComparator;
import org.springframework.beans.factory.annotation.Autowired;
import com.fds.nsw.kernel.exception.PortalException;
import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaVnptServiceConfigPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaVnptServiceConfigLocalServiceImpl { 
@Autowired VmaVnptServiceConfigPersistenceImpl persistence;
public VmaVnptServiceConfig addVmaVnptServiceConfig(VmaVnptServiceConfig VmaVnptServiceConfig)
		throws SystemException {
		return persistence.updateImpl(VmaVnptServiceConfig, false);
	}

	public VmaVnptServiceConfig createVmaVnptServiceConfig(long id) {
		return persistence.create(id);
	}

	public VmaVnptServiceConfig deleteVmaVnptServiceConfig(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaVnptServiceConfig deleteVmaVnptServiceConfig(VmaVnptServiceConfig VmaVnptServiceConfig)
		throws SystemException {
		return persistence.remove(VmaVnptServiceConfig);
	}

	public VmaVnptServiceConfig fetchVmaVnptServiceConfig(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaVnptServiceConfig getVmaVnptServiceConfig(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaVnptServiceConfig> getVmaVnptServiceConfigs(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaVnptServiceConfigsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaVnptServiceConfig updateVmaVnptServiceConfig(VmaVnptServiceConfig VmaVnptServiceConfig)
		throws SystemException {
		return updateVmaVnptServiceConfig(VmaVnptServiceConfig, true);
	}

	public VmaVnptServiceConfig updateVmaVnptServiceConfig(VmaVnptServiceConfig VmaVnptServiceConfig,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaVnptServiceConfig, merge);
	}
    public VmaVnptServiceConfig findByMaritimeCode(String maritimeCode) {
        try {
            return persistence.findByMaritimeCode(maritimeCode);
        } catch (Exception e) {
            log.info("====> No VmaVnptServiceConfig existed.");
            return null;
        }
    }
    public VmaVnptServiceConfig findByTestMode_MaritimeCode(String maritimeCode, int testMode) {
        try {
            return persistence.findByTestMode_MaritimeCode(maritimeCode, testMode);
        } catch (Exception e) {
            log.info("====> No VmaVnptServiceConfig existed.");
            return null;
        }
    }
    public VmaVnptServiceConfig findByTestMode(int testMode) {
        try {
            return persistence.findByTestMode(testMode);
        } catch (Exception e) {
            log.info("====> No VmaVnptServiceConfig existed.");
            return null;
        }
    }

}

