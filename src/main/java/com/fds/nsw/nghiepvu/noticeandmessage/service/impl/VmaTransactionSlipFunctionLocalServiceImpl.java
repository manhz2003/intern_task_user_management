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
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaTransactionSlipFunctionPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaTransactionSlipFunctionLocalServiceImpl { 
@Autowired VmaTransactionSlipFunctionPersistenceImpl persistence;


	public VmaTransactionSlipFunction createVmaTransactionSlipFunction(long id) {
		return persistence.create(id);
	}

	public VmaTransactionSlipFunction deleteVmaTransactionSlipFunction(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaTransactionSlipFunction deleteVmaTransactionSlipFunction(VmaTransactionSlipFunction VmaTransactionSlipFunction)
		throws SystemException {
		return persistence.remove(VmaTransactionSlipFunction);
	}

	public VmaTransactionSlipFunction fetchVmaTransactionSlipFunction(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaTransactionSlipFunction getVmaTransactionSlipFunction(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaTransactionSlipFunction> getVmaTransactionSlipFunctions(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaTransactionSlipFunctionsCount() throws SystemException {
		return persistence.countAll();
	}


	public VmaTransactionSlipFunction updateVmaTransactionSlipFunction(VmaTransactionSlipFunction VmaTransactionSlipFunction,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaTransactionSlipFunction, merge);
	}

    public VmaTransactionSlipFunction addVmaTransactionSlipFunction(
            VmaTransactionSlipFunction vmaTransactionSlipFunction)
            throws SystemException {
        long id = CounterLocalServiceUtil
                .increment(VmaTransactionSlipFunction.class.getName());
        vmaTransactionSlipFunction.setId(id);
        //VMAUtils.formatUnicode(vmaTransactionSlipFunction);
        return persistence.updateImpl(
                vmaTransactionSlipFunction, false);
    }

    public VmaTransactionSlipFunction updateVmaTransactionSlipFunction(
            VmaTransactionSlipFunction vmaTransactionSlipFunction)
            throws SystemException {
        //VMAUtils.formatUnicode(vmaTransactionSlipFunction);
        return persistence.updateImpl(
                vmaTransactionSlipFunction, false);
    }

    public VmaTransactionSlipFunction delete(long id) throws SystemException,
            NoSuchVmaTransactionSlipFunctionException {
        return persistence.remove(id);
    }

    public VmaTransactionSlipFunction findBy(String itineraryNo,
                                             String documentaryCode, String transactionTypeCode) {
        try {
            return persistence
                    .findByitineraryNo_documentaryCode_transactionTypeCode(
                            itineraryNo, documentaryCode, transactionTypeCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public int countAll() {
        try {
            return persistence.countAll();
        } catch (SystemException e) {
            return 0;
        }
    }

    public List<VmaTransactionSlipFunction> findAll() {
        try {
            return persistence.findAll();
        } catch (SystemException e) {
            return null;
        }
    }

    public List<VmaTransactionSlipFunction> findAll(int start, int end) {
        try {
            return persistence.findAll(start, end);
        } catch (SystemException e) {
            return null;
        }
    }

    public List<VmaTransactionSlipFunction> findAll(int start, int end,
                                                    OrderByComparator orderByComparator) {
        try {
            return persistence.findAll(start, end,
                    orderByComparator);
        } catch (SystemException e) {
            return null;
        }
    }

    public List<VmaTransactionSlipFunction> findByitineraryNo_documentaryCode(String itineraryNo, String documentaryCode) {
        try {
            return persistence.findByitineraryNo_documentaryCode(itineraryNo, documentaryCode);
        } catch (SystemException e) {
            return null;
        }
    }

}

