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
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaAuditLogPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaAuditLogLocalServiceImpl { 
@Autowired VmaAuditLogPersistenceImpl persistence;
public VmaAuditLog addVmaAuditLog(VmaAuditLog VmaAuditLog)
		throws SystemException {
		return persistence.updateImpl(VmaAuditLog, false);
	}

	public VmaAuditLog createVmaAuditLog(long id) {
		return persistence.create(id);
	}

	public VmaAuditLog deleteVmaAuditLog(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaAuditLog deleteVmaAuditLog(VmaAuditLog VmaAuditLog)
		throws SystemException {
		return persistence.remove(VmaAuditLog);
	}

	public VmaAuditLog fetchVmaAuditLog(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaAuditLog getVmaAuditLog(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaAuditLog> getVmaAuditLogs(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaAuditLogsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaAuditLog updateVmaAuditLog(VmaAuditLog VmaAuditLog)
		throws SystemException {
		return updateVmaAuditLog(VmaAuditLog, true);
	}

	public VmaAuditLog updateVmaAuditLog(VmaAuditLog VmaAuditLog,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaAuditLog, merge);
	}

    public VmaAuditLog addVmaAuditLog(long userId, String modifyUser,
                                      String actionName, String tableName, String keyCode, String remarks)
            throws SystemException {
        long id = CounterLocalServiceUtil.increment(VmaAuditLog.class.getName());

        VmaAuditLog vmaAuditLog = persistence.create(id);

        vmaAuditLog.setUserid(userId);
        vmaAuditLog.setModifyuser(modifyUser);
        vmaAuditLog.setActionname(actionName);
        vmaAuditLog.setTablename(tableName);
        vmaAuditLog.setKeycode(keyCode);
        vmaAuditLog.setRemarks(remarks);

        Date now = new Date();
        vmaAuditLog.setActiontime(now);

        return persistence.updateImpl(vmaAuditLog, false);
    }

}

