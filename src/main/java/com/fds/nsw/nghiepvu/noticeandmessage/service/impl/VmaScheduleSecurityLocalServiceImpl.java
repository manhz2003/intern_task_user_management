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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaScheduleSecurityFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaScheduleSecurityPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaScheduleSecurityLocalServiceImpl { 
@Autowired VmaScheduleSecurityPersistenceImpl persistence;
@Autowired VmaScheduleSecurityFinderImpl finder;


	public VmaScheduleSecurity createVmaScheduleSecurity(long id) {
		return persistence.create(id);
	}

	public VmaScheduleSecurity deleteVmaScheduleSecurity(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaScheduleSecurity deleteVmaScheduleSecurity(VmaScheduleSecurity VmaScheduleSecurity)
		throws SystemException {
		return persistence.remove(VmaScheduleSecurity);
	}

	public VmaScheduleSecurity fetchVmaScheduleSecurity(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaScheduleSecurity getVmaScheduleSecurity(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaScheduleSecurity> getVmaScheduleSecurities(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaScheduleSecuritiesCount() throws SystemException {
		return persistence.countAll();
	}


	public VmaScheduleSecurity updateVmaScheduleSecurity(VmaScheduleSecurity VmaScheduleSecurity,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaScheduleSecurity, merge);
	}
public VmaScheduleSecurity addVmaScheduleSecurity(
        VmaScheduleSecurity vmaScheduleSecurity) throws SystemException {

    long id = CounterLocalServiceUtil.increment(VmaScheduleSecurity.class
            .getName());

    vmaScheduleSecurity.setId(id);
    vmaScheduleSecurity.setModifiedDate(new Date());
    //VMAUtils.formatUnicode(vmaScheduleSecurity);
    return persistence
            .updateImpl(vmaScheduleSecurity, false);

}

    public VmaScheduleSecurity delete(long id) throws SystemException,
            NoSuchVmaScheduleSecurityException {
        return persistence.remove(id);
    }

    public VmaScheduleSecurity updateVmaScheduleSecurity(
            VmaScheduleSecurity vmaScheduleSecurity) throws SystemException {
        vmaScheduleSecurity.setModifiedDate(new Date());
        //VMAUtils.formatUnicode(vmaScheduleSecurity);
        return persistence
                .updateImpl(vmaScheduleSecurity, false);

    }

    public List<VmaScheduleSecurity> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public List<VmaScheduleSecurity> findByItineraryNo_NoticeShipType(
            String itineraryNo, int noticeShipType) throws SystemException {
        return persistence.findByitineraryNo_noticeShipType(
                itineraryNo, noticeShipType);
    }

    public int countByItineraryNo_NoticeShipType(String itineraryNo,
                                                 int noticeShipType) throws SystemException {
        return persistence
                .countByitineraryNo_noticeShipType(itineraryNo, noticeShipType);
    }

    public JSONObject findVmaScheduleSecurity(String itineraryNo,
                                              String portofAuthority, String nameOfShip, Long documentName,
                                              Integer documentYear, Integer noticeShipType,
                                              String securityOfficeCode, String securityCompanyName,
                                              String securityOfficialNo, String securityDate,
                                              String securityPlace, String securityReason, Integer evacuated,
                                              String evacuateOfficialCode, String evacuateCompanyName,
                                              String evacuateOfficialNo, String evacuateDate,
                                              String evacuateReason, int start, int end) throws SystemException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder.countVmaScheduleSecurity(
                itineraryNo, portofAuthority, nameOfShip, documentName,
                documentYear, noticeShipType, securityOfficeCode,
                securityCompanyName, securityOfficialNo, securityDate,
                securityPlace, securityReason, evacuated, evacuateOfficialCode,
                evacuateCompanyName, evacuateOfficialNo, evacuateDate,
                evacuateReason);

        JSONArray data = finder.findVmaScheduleSecurity(
                itineraryNo, portofAuthority, nameOfShip, documentName,
                documentYear, noticeShipType, securityOfficeCode,
                securityCompanyName, securityOfficialNo, securityDate,
                securityPlace, securityReason, evacuated, evacuateOfficialCode,
                evacuateCompanyName, evacuateOfficialNo, evacuateDate,
                evacuateReason, start, end);

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public JSONObject findVmaScheduleSecurity(String searchQuery,
                                              String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaScheduleSecurity(countQuery);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        List<VmaScheduleSecurity> vmaScheduleSecurities = finder
                .findVmaScheduleSecurity(VmaScheduleSecurity.class,
                        "VmaScheduleSecurity", searchQuery, start, end);

        if (vmaScheduleSecurities != null) {
            for (VmaScheduleSecurity vmaScheduleSecurity : vmaScheduleSecurities) {

                JSONObject object = VMAUtils.object2Json(vmaScheduleSecurity,
                        VmaScheduleSecurity.class, new String[] {
                                "timeOfArrival", "timeOfDeparture" });

                data.put(object);
            }
        }

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaScheduleSecurity(String itineraryNo,
                                         String portofAuthority, String nameOfShip, Long documentName,
                                         Integer documentYear, Integer noticeShipType,
                                         String securityOfficeCode, String securityCompanyName,
                                         String securityOfficialNo, String securityDate,
                                         String securityPlace, String securityReason, Integer evacuated,
                                         String evacuateOfficialCode, String evacuateCompanyName,
                                         String evacuateOfficialNo, String evacuateDate,
                                         String evacuateReason) throws SystemException {

        return finder.countVmaScheduleSecurity(itineraryNo,
                portofAuthority, nameOfShip, documentName, documentYear,
                noticeShipType, securityOfficeCode, securityCompanyName,
                securityOfficialNo, securityDate, securityPlace,
                securityReason, evacuated, evacuateOfficialCode,
                evacuateCompanyName, evacuateOfficialNo, evacuateDate,
                evacuateReason);

    }

    public long countVmaScheduleSecurity(String sql) throws SystemException {

        return finder.countVmaScheduleSecurity(sql);

    }

}

