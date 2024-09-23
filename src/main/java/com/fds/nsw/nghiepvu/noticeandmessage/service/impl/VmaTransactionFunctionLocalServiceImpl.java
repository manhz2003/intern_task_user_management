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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaTransactionFunctionFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaTransactionFunctionPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaTransactionFunctionLocalServiceImpl { 
@Autowired VmaTransactionFunctionPersistenceImpl persistence;
@Autowired VmaTransactionFunctionFinderImpl finder;


	public VmaTransactionFunction createVmaTransactionFunction(long id) {
		return persistence.create(id);
	}

	public VmaTransactionFunction deleteVmaTransactionFunction(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaTransactionFunction deleteVmaTransactionFunction(VmaTransactionFunction VmaTransactionFunction)
		throws SystemException {
		return persistence.remove(VmaTransactionFunction);
	}

	public VmaTransactionFunction fetchVmaTransactionFunction(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaTransactionFunction getVmaTransactionFunction(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaTransactionFunction> getVmaTransactionFunctions(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaTransactionFunctionsCount() throws SystemException {
		return persistence.countAll();
	}



	public VmaTransactionFunction updateVmaTransactionFunction(VmaTransactionFunction VmaTransactionFunction,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaTransactionFunction, merge);
	}
public VmaTransactionFunction addVmaTransactionFunction(
        VmaTransactionFunction vmaTransactionFunction)
        throws SystemException {
    long id = CounterLocalServiceUtil.increment(VmaTransactionFunction.class
            .getName());
    vmaTransactionFunction.setId(id);
    vmaTransactionFunction.setModifiedDate(new Date());
    //VMAUtils.formatUnicode(vmaTransactionFunction);
    return persistence.updateImpl(vmaTransactionFunction,
            false);
}

    public VmaTransactionFunction updateVmaTransactionFunction(
            VmaTransactionFunction vmaTransactionFunction)
            throws SystemException {
        vmaTransactionFunction.setModifiedDate(new Date());
        //VMAUtils.formatUnicode(vmaTransactionFunction);
        return persistence.updateImpl(vmaTransactionFunction,
                false);
    }

    public VmaTransactionFunction delete(long id) throws SystemException,
            NoSuchVmaTransactionFunctionException {
        return persistence.remove(id);
    }

    public List<VmaTransactionFunction> findByPortofAuthority(
            String portofAuthority) throws SystemException {
        return persistence
                .findByportofAuthority(portofAuthority);
    }

    public List<VmaTransactionFunction> findByPortofAuthority(
            String portofAuthority, int start, int end) throws SystemException {
        return persistence.findByportofAuthority(
                portofAuthority, start, end);
    }

    public List<VmaTransactionFunction> findByPortofAuthority(
            String portofAuthority, int start, int end,
            OrderByComparator orderByComparator) throws SystemException {
        return persistence.findByportofAuthority(
                portofAuthority, start, end, orderByComparator);
    }

    public int countByPortofAuthority(String portofAuthority)
            throws SystemException {
        return persistence
                .countByportofAuthority(portofAuthority);
    }

    public List<VmaTransactionFunction> findVmaTransactionFunctionByCustomSQL(
            String sql, int start, int end) {
        try {
            return finder.findVmaTransactionFunction(
                    VmaTransactionFunction.class, "VmaTransactionFunction",
                    sql, start, end);
        } catch (SystemException e) {
            return null;
        }
    }

    public JSONObject findVmaTransactionFunction(String searchQuery,
                                                 String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaTransactionFunction(countQuery);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        List<VmaTransactionFunction> VmaTransactionFunctions = finder
                .findVmaTransactionFunction(VmaTransactionFunction.class,
                        "VmaTransactionFunction", searchQuery, start, end);

        if (VmaTransactionFunctions != null) {
            for (VmaTransactionFunction VmaTransactionFunction : VmaTransactionFunctions) {

                JSONObject object = VMAUtils.object2Json(
                        VmaTransactionFunction,
                        VmaTransactionFunction.class);

                data.put(object);
            }
        }

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaTransactionFunction(String sql) throws SystemException {

        return finder.countVmaTransactionFunction(sql);

    }

    public VmaTransactionFunction findByPortofAuthority_transactionTypeCode(
            String portofAuthority, String transactionTypeCode)
            throws SystemException, PortalException {
        return persistence
                .findByportofAuthority_transactionTypeCode(portofAuthority,
                        transactionTypeCode);
    }

}

