package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import vn.gt.portlet.phuongtien.VMAUtils;
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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaConversionTypeFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaConversionTypePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaConversionTypeLocalServiceImpl { 
@Autowired VmaConversionTypePersistenceImpl persistence;
@Autowired VmaConversionTypeFinderImpl finder;
public VmaConversionType addVmaConversionType(VmaConversionType VmaConversionType)
		throws SystemException {
		return persistence.updateImpl(VmaConversionType, false);
	}

	public VmaConversionType createVmaConversionType(long id) {
		return persistence.create(id);
	}

	public VmaConversionType deleteVmaConversionType(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaConversionType deleteVmaConversionType(VmaConversionType VmaConversionType)
		throws SystemException {
		return persistence.remove(VmaConversionType);
	}

	public VmaConversionType fetchVmaConversionType(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaConversionType getVmaConversionType(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaConversionType> getVmaConversionTypes(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaConversionTypesCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaConversionType updateVmaConversionType(VmaConversionType VmaConversionType)
		throws SystemException {
		return updateVmaConversionType(VmaConversionType, true);
	}

	public VmaConversionType updateVmaConversionType(VmaConversionType VmaConversionType,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaConversionType, merge);
	}
public int countAll(){
    try {
        return persistence.countAll();
    } catch (SystemException e) {
        return 0;
    }
}

    public List<VmaConversionType> findAll() {
        try {
            return persistence.findAll();
        } catch (SystemException e) {

            return null;
        }
    }

    public List<VmaConversionType> findAll(int start, int end) {
        try {
            return persistence.findAll(start, end);
        } catch (SystemException e) {
            return null;
        }
    }

    public List<VmaConversionType> findAll(int start, int end, OrderByComparator orderByComparator) {
        try {
            return persistence.findAll(start, end, orderByComparator);
        } catch (SystemException e) {
            return null;
        }
    }

    public JSONObject findVmaConversionType(String searchQuery,
                                            String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaConversionType(countQuery);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        List<VmaConversionType> vmaConversionTypes = finder
                .findVmaConversionType(VmaConversionType.class,
                        "VmaConversionType", searchQuery, start, end);

        if (vmaConversionTypes != null) {
            for (VmaConversionType vmaConversionType : vmaConversionTypes) {

                JSONObject object = VMAUtils.object2Json(vmaConversionType,
                        VmaConversionType.class, new String[]{"shipTypeMT", "shipTypeCode"});

                data.put(object);
            }
        }

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaConversionType(String sql) throws SystemException {

        return finder.countVmaConversionType(sql);

    }

}

