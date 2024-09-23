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
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaTugboatConditionPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaTugboatConditionLocalServiceImpl { 
@Autowired VmaTugboatConditionPersistenceImpl persistence;

	public VmaTugboatCondition createVmaTugboatCondition(long id) {
		return persistence.create(id);
	}

	public VmaTugboatCondition deleteVmaTugboatCondition(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaTugboatCondition deleteVmaTugboatCondition(VmaTugboatCondition VmaTugboatCondition)
		throws SystemException {
		return persistence.remove(VmaTugboatCondition);
	}

	public VmaTugboatCondition fetchVmaTugboatCondition(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaTugboatCondition getVmaTugboatCondition(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaTugboatCondition> getVmaTugboatConditions(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaTugboatConditionsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaTugboatCondition updateVmaTugboatCondition(VmaTugboatCondition VmaTugboatCondition,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaTugboatCondition, merge);
	}
public VmaTugboatCondition addVmaTugboatCondition(
        VmaTugboatCondition vmaTugboatCondition) throws SystemException {
    long id = CounterLocalServiceUtil.increment(VmaTugboatCondition.class
            .getName());
    vmaTugboatCondition.setId(id);
    vmaTugboatCondition.setModifiedDate(new Date());
    return persistence.updateImpl(vmaTugboatCondition, false);
}

    public VmaTugboatCondition updateVmaTugboatCondition(
            VmaTugboatCondition vmaTugboatCondition) throws SystemException {
        vmaTugboatCondition.setModifiedDate(new Date());
        return persistence.updateImpl(vmaTugboatCondition, false);
    }

    public VmaTugboatCondition delete(long id)
            throws SystemException, NoSuchVmaTugboatConditionException {
        return persistence.remove(id);
    }

    public List<VmaTugboatCondition> findByMaritimeCode(String maritimeCode)
            throws SystemException {
        return persistence.findBymaritimeCode(maritimeCode);
    }

    public int countByMaritimeCode(String maritimeCode) throws SystemException {
        return persistence.countBymaritimeCode(maritimeCode);
    }

}

