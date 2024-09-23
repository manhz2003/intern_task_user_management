package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.nsw.nghiepvu.service.exception.*;
import vn.gt.portlet.phuongtien.VMAUtils;
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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaScheduleLaunchingFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaScheduleLaunchingPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaScheduleLaunchingLocalServiceImpl { 
@Autowired VmaScheduleLaunchingPersistenceImpl persistence;
@Autowired VmaScheduleLaunchingFinderImpl finder;


	public VmaScheduleLaunching createVmaScheduleLaunching(long id) {
		return persistence.create(id);
	}

	public VmaScheduleLaunching deleteVmaScheduleLaunching(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaScheduleLaunching deleteVmaScheduleLaunching(VmaScheduleLaunching VmaScheduleLaunching)
		throws SystemException {
		return persistence.remove(VmaScheduleLaunching);
	}

	public VmaScheduleLaunching fetchVmaScheduleLaunching(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaScheduleLaunching getVmaScheduleLaunching(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaScheduleLaunching> getVmaScheduleLaunchings(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaScheduleLaunchingsCount() throws SystemException {
		return persistence.countAll();
	}


	public VmaScheduleLaunching updateVmaScheduleLaunching(VmaScheduleLaunching VmaScheduleLaunching,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaScheduleLaunching, merge);
	}
public VmaScheduleLaunching addVmaScheduleLaunching(
        VmaScheduleLaunching vmaScheduleLaunching) throws SystemException {

    long id = CounterLocalServiceUtil.increment(VmaScheduleLaunching.class
            .getName());

    vmaScheduleLaunching.setId(id);

    vmaScheduleLaunching.setModifiedDate(new Date());
    //VMAUtils.formatUnicode(vmaScheduleLaunching);
    return persistence.updateImpl(vmaScheduleLaunching,
            false);

}

    public VmaScheduleLaunching delete(long id) throws SystemException,
            NoSuchVmaScheduleLaunchingException {
        return persistence.remove(id);
    }

    public VmaScheduleLaunching updateVmaScheduleLaunching(
            VmaScheduleLaunching vmaScheduleLaunching) throws SystemException {
        vmaScheduleLaunching.setModifiedDate(new Date());
        //VMAUtils.formatUnicode(vmaScheduleLaunching);
        return persistence.updateImpl(vmaScheduleLaunching,
                false);

    }

    public List<VmaScheduleLaunching> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public List<VmaScheduleLaunching> findByItineraryNo_NoticeShipType(
            String itineraryNo, int noticeShipType) throws SystemException {
        return persistence
                .findByitineraryNo_noticeShipType(itineraryNo, noticeShipType);
    }

    public int countByItineraryNo_NoticeShipType(String itineraryNo,
                                                 int noticeShipType) throws SystemException {
        return persistence
                .countByitineraryNo_noticeShipType(itineraryNo, noticeShipType);
    }

    public JSONObject findVmaScheduleLaunching(String itineraryNo,
                                               String portofAuthority, String nameOfShip, Long documentName,
                                               Integer documentYear, Integer noticeShipType, String launchingFrom,
                                               String launchingTo, String launchingPlace, String launchingReason,
                                               int start, int end) throws SystemException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder.countVmaScheduleLaunching(
                itineraryNo, portofAuthority, nameOfShip, documentName,
                documentYear, noticeShipType, launchingFrom, launchingTo,
                launchingPlace, launchingReason);

        JSONArray data = finder.findVmaScheduleLaunching(
                itineraryNo, portofAuthority, nameOfShip, documentName,
                documentYear, noticeShipType, launchingFrom, launchingTo,
                launchingPlace, launchingReason, start, end);

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public JSONObject findVmaScheduleLaunching(String searchQuery,
                                               String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaScheduleLaunching(countQuery);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        List<VmaScheduleLaunching> vmaScheduleLaunchings = finder
                .findVmaScheduleLaunching(VmaScheduleLaunching.class,
                        "VmaScheduleLaunching", searchQuery, start, end);

        if (vmaScheduleLaunchings != null) {
            for (VmaScheduleLaunching vmaScheduleLaunching : vmaScheduleLaunchings) {

                JSONObject object = VMAUtils.object2Json(vmaScheduleLaunching,
                        VmaScheduleLaunching.class, new String[] {
                                "timeOfArrival", "timeOfDeparture" });

                data.put(object);
            }
        }

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaScheduleLaunching(String itineraryNo,
                                          String portofAuthority, String nameOfShip, Long documentName,
                                          Integer documentYear, Integer noticeShipType, String launchingFrom,
                                          String launchingTo, String launchingPlace, String launchingReason)
            throws SystemException {

        return finder.countVmaScheduleLaunching(
                itineraryNo, portofAuthority, nameOfShip, documentName,
                documentYear, noticeShipType, launchingFrom, launchingTo,
                launchingPlace, launchingReason);

    }

    public long countVmaScheduleLaunching(String sql) throws SystemException {

        return finder.countVmaScheduleLaunching(sql);

    }

}

