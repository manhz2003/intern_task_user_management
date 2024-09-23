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
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaTransactionReceiptPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaTransactionReceiptLocalServiceImpl { 
@Autowired VmaTransactionReceiptPersistenceImpl persistence;
public VmaTransactionReceipt addVmaTransactionReceipt(VmaTransactionReceipt VmaTransactionReceipt)
		throws SystemException {
		return persistence.updateImpl(VmaTransactionReceipt, false);
	}

	public VmaTransactionReceipt createVmaTransactionReceipt(long id) {
		return persistence.create(id);
	}

	public VmaTransactionReceipt deleteVmaTransactionReceipt(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaTransactionReceipt deleteVmaTransactionReceipt(VmaTransactionReceipt VmaTransactionReceipt)
		throws SystemException {
		return persistence.remove(VmaTransactionReceipt);
	}

	public VmaTransactionReceipt fetchVmaTransactionReceipt(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaTransactionReceipt getVmaTransactionReceipt(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaTransactionReceipt> getVmaTransactionReceipts(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaTransactionReceiptsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaTransactionReceipt updateVmaTransactionReceipt(VmaTransactionReceipt VmaTransactionReceipt)
		throws SystemException {
		return updateVmaTransactionReceipt(VmaTransactionReceipt, true);
	}

	public VmaTransactionReceipt updateVmaTransactionReceipt(VmaTransactionReceipt VmaTransactionReceipt,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaTransactionReceipt, merge);
	}
public List<VmaTransactionReceipt> findByMaritimeCode(String maritimeCode) {
    try {
        return persistence.findByMaritimeCode(maritimeCode);
    } catch (Exception e) {
        log.info("====> Khong VmaTransactionReceipt");
        return null;
    }
}

}

