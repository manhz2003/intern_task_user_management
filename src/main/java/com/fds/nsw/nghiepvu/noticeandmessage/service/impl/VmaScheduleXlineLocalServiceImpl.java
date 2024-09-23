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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaScheduleXlineFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaScheduleXlinePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaScheduleXlineLocalServiceImpl { 
@Autowired VmaScheduleXlinePersistenceImpl persistence;
@Autowired VmaScheduleXlineFinderImpl finder;
	public VmaScheduleXline createVmaScheduleXline(long id) {
		return persistence.create(id);
	}

	public VmaScheduleXline deleteVmaScheduleXline(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaScheduleXline deleteVmaScheduleXline(VmaScheduleXline VmaScheduleXline)
		throws SystemException {
		return persistence.remove(VmaScheduleXline);
	}

	public VmaScheduleXline fetchVmaScheduleXline(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaScheduleXline getVmaScheduleXline(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaScheduleXline> getVmaScheduleXlines(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaScheduleXlinesCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaScheduleXline updateVmaScheduleXline(VmaScheduleXline VmaScheduleXline,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaScheduleXline, merge);
	}
public VmaScheduleXline addVmaScheduleXline(
        VmaScheduleXline vmaScheduleXline) throws SystemException {

    long id = CounterLocalServiceUtil.increment(VmaScheduleXline.class
            .getName());

    vmaScheduleXline.setId(id);
    vmaScheduleXline.setModifiedDate(new Date());
    //VMAUtils.formatUnicode(vmaScheduleXline);
    return persistence.updateImpl(vmaScheduleXline, false);

}

    public VmaScheduleXline delete(long id) throws SystemException,
            NoSuchVmaScheduleXlineException {
        return persistence.remove(id);
    }

    public VmaScheduleXline updateVmaScheduleXline(
            VmaScheduleXline vmaScheduleXline) throws SystemException {
        vmaScheduleXline.setModifiedDate(new Date());
        //VMAUtils.formatUnicode(vmaScheduleXline);
        return persistence.updateImpl(vmaScheduleXline, false);

    }

    public JSONObject findVmaScheduleXline(String portofAuthority,
                                           String shipOperatorCode, String companyName,
                                           String routeDescription, Integer scheduleYear,
                                           Integer scheduleMonth, Integer voyageNumber, int start, int end)
            throws SystemException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder.countVmaScheduleXline(
                portofAuthority, shipOperatorCode, companyName,
                routeDescription, scheduleYear, scheduleMonth, voyageNumber);

        JSONArray data = finder.findVmaScheduleXline(
                portofAuthority, shipOperatorCode, companyName,
                routeDescription, scheduleYear, scheduleMonth, voyageNumber,
                start, end);

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public JSONObject findVmaScheduleXline(String searchQuery, String countQuery, int start, int end) throws SystemException, JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder.countVmaScheduleXline(countQuery);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        List<VmaScheduleXline>	vmaScheduleXlines =	finder.findVmaScheduleXline(VmaScheduleXline.class,
                "VmaScheduleXline", searchQuery, start, end);

        if (vmaScheduleXlines != null) {
            for (VmaScheduleXline vmaScheduleXline : vmaScheduleXlines) {

                JSONObject object = VMAUtils.object2Json(vmaScheduleXline,
                        VmaScheduleXline.class);

                data.put(object);
            }
        }

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaScheduleXline(String portofAuthority,
                                      String shipOperatorCode, String companyName,
                                      String routeDescription, Integer scheduleYear,
                                      Integer scheduleMonth, Integer voyageNumber) throws SystemException {

        return finder.countVmaScheduleXline(portofAuthority,
                shipOperatorCode, companyName, routeDescription, scheduleYear,
                scheduleMonth, voyageNumber);

    }

    public long countVmaScheduleXline(String sql) throws SystemException {

        return finder.countVmaScheduleXline(sql);

    }

}

