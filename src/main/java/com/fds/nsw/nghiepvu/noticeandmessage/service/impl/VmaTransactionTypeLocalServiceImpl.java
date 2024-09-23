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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaTransactionTypeFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaTransactionTypePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaTransactionTypeLocalServiceImpl { 
@Autowired VmaTransactionTypePersistenceImpl persistence;
@Autowired VmaTransactionTypeFinderImpl finder;


	public VmaTransactionType createVmaTransactionType(long id) {
		return persistence.create(id);
	}

	public VmaTransactionType deleteVmaTransactionType(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaTransactionType deleteVmaTransactionType(VmaTransactionType VmaTransactionType)
		throws SystemException {
		return persistence.remove(VmaTransactionType);
	}

	public VmaTransactionType fetchVmaTransactionType(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaTransactionType getVmaTransactionType(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaTransactionType> getVmaTransactionTypes(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaTransactionTypesCount() throws SystemException {
		return persistence.countAll();
	}


	public VmaTransactionType updateVmaTransactionType(VmaTransactionType VmaTransactionType,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaTransactionType, merge);
	}

    public VmaTransactionType addVmaTransactionType(
            VmaTransactionType vmaTransactionType) throws SystemException {
        long id = CounterLocalServiceUtil.increment(VmaTransactionType.class
                .getName());
        vmaTransactionType.setId(id);
        vmaTransactionType.setModifiedDate(new Date());
        //VMAUtils.formatUnicode(vmaTransactionType);
        return persistence.updateImpl(vmaTransactionType, false);
    }

    public VmaTransactionType updateVmaTransactionType(
            VmaTransactionType vmaTransactionType) throws SystemException {
        vmaTransactionType.setModifiedDate(new Date());
        //VMAUtils.formatUnicode(vmaTransactionType);
        return persistence.updateImpl(vmaTransactionType, false);
    }

    public VmaTransactionType delete(long id) throws SystemException,
            NoSuchVmaTransactionTypeException {
        return persistence.remove(id);
    }

    public List<VmaTransactionType> findByPortofAuthority(String portofAuthority)
            throws SystemException {
        return persistence
                .findByportofAuthority(portofAuthority);
    }

    public List<VmaTransactionType> findByPortofAuthority(
            String portofAuthority, int start, int end) throws SystemException {
        return persistence.findByportofAuthority(
                portofAuthority, start, end);
    }

    public List<VmaTransactionType> findByPortofAuthority(
            String portofAuthority, int start, int end,
            OrderByComparator orderByComparator) throws SystemException {
        return persistence.findByportofAuthority(
                portofAuthority, start, end, orderByComparator);
    }

    public List<VmaTransactionType> findByPortofAuthority_transactionLevel(
            String portofAuthority, int transactionLevel) {
        try {
            return persistence
                    .findByportofAuthority_transactionLevel(portofAuthority,
                            transactionLevel);
        } catch (Exception e) {

            return null;
        }
    }

    public int countByPortofAuthority(String portofAuthority)
            throws SystemException {
        return persistence
                .countByportofAuthority(portofAuthority);
    }

    public List<VmaTransactionType> findVmaTransactionTypeByTransactionTypeCodes(
            String portofAuthority, List<Integer> transactionTypeCodes) {
        return finder
                .findVmaTransactionTypeByTransactionTypeCodes(portofAuthority,
                        transactionTypeCodes);
    }

    public List<Integer> checkExistTransactionType(String portofAuthority, String shipAgencyCode) {
        return finder
                .checkExistTransactionType(portofAuthority, shipAgencyCode);
    }

    public JSONObject findVmaTransactionType(String searchQuery,
                                             String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaTransactionType(countQuery);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        List<VmaTransactionType> VmaTransactionTypes = finder
                .findVmaTransactionType(VmaTransactionType.class,
                        "VmaTransactionType", searchQuery, start, end);

        if (VmaTransactionTypes != null) {
            for (VmaTransactionType VmaTransactionType : VmaTransactionTypes) {

                JSONObject object = VMAUtils.object2Json(VmaTransactionType,
                        VmaTransactionType.class);

                data.put(object);
            }
        }

        result.put("total", total);

        result.put("data", data);

        return result;

    }


    public long countVmaTransactionType(String sql) throws SystemException {

        return finder.countVmaTransactionType(sql);

    }

}

