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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaScheduleShipyardFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaScheduleShipyardPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaScheduleShipyardLocalServiceImpl { 
@Autowired VmaScheduleShipyardPersistenceImpl persistence;
@Autowired VmaScheduleShipyardFinderImpl finder;


	public VmaScheduleShipyard createVmaScheduleShipyard(long id) {
		return persistence.create(id);
	}

	public VmaScheduleShipyard deleteVmaScheduleShipyard(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaScheduleShipyard deleteVmaScheduleShipyard(VmaScheduleShipyard VmaScheduleShipyard)
		throws SystemException {
		return persistence.remove(VmaScheduleShipyard);
	}

	public VmaScheduleShipyard fetchVmaScheduleShipyard(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaScheduleShipyard getVmaScheduleShipyard(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaScheduleShipyard> getVmaScheduleShipyards(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaScheduleShipyardsCount() throws SystemException {
		return persistence.countAll();
	}


	public VmaScheduleShipyard updateVmaScheduleShipyard(VmaScheduleShipyard VmaScheduleShipyard,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaScheduleShipyard, merge);
	}
public VmaScheduleShipyard addVmaScheduleShipyard(
        VmaScheduleShipyard vmaScheduleShipyard) throws SystemException {

    long id = CounterLocalServiceUtil.increment(VmaScheduleShipyard.class
            .getName());

    vmaScheduleShipyard.setId(id);
    vmaScheduleShipyard.setModifiedDate(new Date());
    return persistence.updateImpl(vmaScheduleShipyard, false);

}

    public VmaScheduleShipyard delete(long id) throws SystemException,
            NoSuchVmaScheduleShipyardException {
        return persistence.remove(id);
    }

    public VmaScheduleShipyard updateVmaScheduleShipyard(
            VmaScheduleShipyard vmaScheduleShipyard) throws SystemException {
        vmaScheduleShipyard.setModifiedDate(new Date());
        return persistence.updateImpl(vmaScheduleShipyard, false);

    }

    public List<VmaScheduleShipyard> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public List<VmaScheduleShipyard> findByItineraryNo_NoticeShipType(
            String itineraryNo, int noticeShipType) throws SystemException {
        return persistence.findByitineraryNo_noticeShipType(
                itineraryNo, noticeShipType);
    }

    public int countByItineraryNo_NoticeShipType(String itineraryNo,
                                                 int noticeShipType) throws SystemException {
        return persistence
                .countByitineraryNo_noticeShipType(itineraryNo, noticeShipType);
    }

    public JSONObject findVmaScheduleShipyard(String itineraryNo,
                                              String portofAuthority, String nameOfShip, Long documentName,
                                              Integer documentYear, Integer noticeShipType, String shipYardCode,
                                              String shipYardCompanyName, String shipYardOfficialNo,
                                              String repairingFrom, String repairingTo, String repairingPlace,
                                              String repairingReason, Integer repaired, int start, int end)
            throws SystemException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder.countVmaScheduleShipyard(
                itineraryNo, portofAuthority, nameOfShip, documentName,
                documentYear, noticeShipType, shipYardCode,
                shipYardCompanyName, shipYardOfficialNo, repairingFrom,
                repairingTo, repairingPlace, repairingReason, repaired);

        JSONArray data = finder.findVmaScheduleShipyard(
                itineraryNo, portofAuthority, nameOfShip, documentName,
                documentYear, noticeShipType, shipYardCode,
                shipYardCompanyName, shipYardOfficialNo, repairingFrom,
                repairingTo, repairingPlace, repairingReason, repaired, start,
                end);

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public JSONObject findVmaScheduleShipyard(String searchQuery,
                                              String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaScheduleShipyard(countQuery);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        List<VmaScheduleShipyard> vmaScheduleShipyards = finder
                .findVmaScheduleShipyard(VmaScheduleShipyard.class,
                        "VmaScheduleShipyard", searchQuery, start, end);

        if (vmaScheduleShipyards != null) {
            for (VmaScheduleShipyard vmaScheduleShipyard : vmaScheduleShipyards) {

                JSONObject object = VMAUtils.object2Json(vmaScheduleShipyard,
                        VmaScheduleShipyard.class);

                data.put(object);
            }
        }

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaScheduleShipyard(String itineraryNo,
                                         String portofAuthority, String nameOfShip, Long documentName,
                                         Integer documentYear, Integer noticeShipType, String shipYardCode,
                                         String shipYardCompanyName, String shipYardOfficialNo,
                                         String repairingFrom, String repairingTo, String repairingPlace,
                                         String repairingReason, Integer repaired) throws SystemException {

        return finder.countVmaScheduleShipyard(itineraryNo,
                portofAuthority, nameOfShip, documentName, documentYear,
                noticeShipType, shipYardCode, shipYardCompanyName,
                shipYardOfficialNo, repairingFrom, repairingTo, repairingPlace,
                repairingReason, repaired);

    }

    public long countVmaScheduleShipyard(String sql) throws SystemException {

        return finder.countVmaScheduleShipyard(sql);

    }


}

