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
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaTransactionSlipDetailsPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaTransactionSlipDetailsLocalServiceImpl { 
@Autowired VmaTransactionSlipDetailsPersistenceImpl persistence;


	public VmaTransactionSlipDetails createVmaTransactionSlipDetails(long id) {
		return persistence.create(id);
	}


	public VmaTransactionSlipDetails deleteVmaTransactionSlipDetails(VmaTransactionSlipDetails VmaTransactionSlipDetails)
		throws SystemException {
		return persistence.remove(VmaTransactionSlipDetails);
	}

	public VmaTransactionSlipDetails fetchVmaTransactionSlipDetails(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaTransactionSlipDetails getVmaTransactionSlipDetails(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaTransactionSlipDetails> getVmaTransactionSlipDetailses(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaTransactionSlipDetailsesCount() throws SystemException {
		return persistence.countAll();
	}



	public VmaTransactionSlipDetails updateVmaTransactionSlipDetails(VmaTransactionSlipDetails VmaTransactionSlipDetails,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaTransactionSlipDetails, merge);
	}

    public VmaTransactionSlipDetails addVmaTransactionSlipDetails(
            VmaTransactionSlipDetails vmaTransactionSlipDetails)
            throws SystemException {
        long id = CounterLocalServiceUtil.increment(VmaTransactionSlipDetails.class
                .getName());
        vmaTransactionSlipDetails.setId(id);
        return persistence.updateImpl(
                vmaTransactionSlipDetails, false);
    }

    public void deleteVmaTransactionSlipDetails(long id)
            throws NoSuchVmaTransactionSlipDetailsException, SystemException {
        persistence.remove(id);
    }

    public VmaTransactionSlipDetails updateVmaTransactionSlipDetails(
            VmaTransactionSlipDetails vmaTransactionSlipDetails)
            throws SystemException {
        return persistence.updateImpl(
                vmaTransactionSlipDetails, false);
    }

    public List<VmaTransactionSlipDetails> findAll() throws SystemException,
            PortalException {
        return persistence.findAll();
    }

    public List<VmaTransactionSlipDetails> findAll(int start, int end)
            throws SystemException, PortalException {
        return persistence.findAll(start, end);
    }

    public List<VmaTransactionSlipDetails> findAll(int start, int end,
                                                   OrderByComparator orderByComparator) throws SystemException,
            PortalException {
        return persistence.findAll(start, end,
                orderByComparator);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException,
            PortalException {
        return persistence
                .countByItineraryNo(itineraryNo);
    }

    public VmaTransactionSlipDetails fetchByItineraryNo_DocumentaryCode(
            String itineraryNo, String documentaryCode) throws SystemException {
        try {
            return persistence

                    .fetchByItineraryNo_DocumentaryCode(itineraryNo,
                            documentaryCode);

        } catch (SystemException e) {
            return null;
        }

    }

    public List<VmaTransactionSlipDetails> findByItineraryNo(
            String itineraryNo) throws SystemException{
        return persistence
                .findByItineraryNo(itineraryNo);

    }

}

