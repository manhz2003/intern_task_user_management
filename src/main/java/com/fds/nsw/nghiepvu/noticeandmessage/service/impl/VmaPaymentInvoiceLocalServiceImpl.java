package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
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
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaPaymentInvoicePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaPaymentInvoiceLocalServiceImpl { 
@Autowired VmaPaymentInvoicePersistenceImpl persistence;

	public VmaPaymentInvoice createVmaPaymentInvoice(long id) {
		return persistence.create(id);
	}

	public VmaPaymentInvoice deleteVmaPaymentInvoice(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaPaymentInvoice deleteVmaPaymentInvoice(VmaPaymentInvoice VmaPaymentInvoice)
		throws SystemException {
		return persistence.remove(VmaPaymentInvoice);
	}

	public VmaPaymentInvoice fetchVmaPaymentInvoice(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaPaymentInvoice getVmaPaymentInvoice(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaPaymentInvoice> getVmaPaymentInvoices(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaPaymentInvoicesCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaPaymentInvoice updateVmaPaymentInvoice(VmaPaymentInvoice VmaPaymentInvoice,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaPaymentInvoice, merge);
	}

    public VmaPaymentInvoice addVmaPaymentInvoice(
            VmaPaymentInvoice vmaPaymentInvoice) throws SystemException {
        long id = CounterLocalServiceUtil.increment(VmaPaymentInvoice.class
                .getName());
        vmaPaymentInvoice.setId(id);
        vmaPaymentInvoice.setModifiedDate(new Date());
        //VMAUtils.formatUnicode(vmaPaymentInvoice);
        return persistence.updateImpl(vmaPaymentInvoice, false);
    }

    public VmaPaymentInvoice updateVmaPaymentInvoice(
            VmaPaymentInvoice vmaPaymentInvoice) throws SystemException {
        vmaPaymentInvoice.setModifiedDate(new Date());
        //VMAUtils.formatUnicode(vmaPaymentInvoice);
        return persistence.updateImpl(vmaPaymentInvoice, false);
    }

    public VmaPaymentInvoice delete(long id) throws SystemException,
            NoSuchVmaPaymentInvoiceException {
        return persistence.remove(id);
    }

    public List<VmaPaymentInvoice> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

}

