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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaShipFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaShipPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaShipLocalServiceImpl { 
@Autowired VmaShipPersistenceImpl persistence;
@Autowired VmaShipFinderImpl finder;

	public VmaShip createVmaShip(long id) {
		return persistence.create(id);
	}

	public VmaShip deleteVmaShip(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaShip deleteVmaShip(VmaShip VmaShip)
		throws SystemException {
		return persistence.remove(VmaShip);
	}

	public VmaShip fetchVmaShip(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaShip getVmaShip(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaShip> getVmaShips(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaShipsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaShip updateVmaShip(VmaShip VmaShip)
		throws SystemException {
		return updateVmaShip(VmaShip, true);
	}

	public VmaShip updateVmaShip(VmaShip VmaShip,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaShip, merge);
	}
public VmaShip addVmaShip(VmaShip vmaShip) throws SystemException {
    long id = CounterLocalServiceUtil.increment(VmaShip.class.getName());
    vmaShip.setId(id);
    vmaShip.setModifiedDate(new Date());
    // VMAUtils.formatUnicode(vmaShip);
    return persistence.updateImpl(vmaShip, false);
}

    public VmaShip updayeVmaShip(VmaShip vmaShip) throws SystemException {
        // VMAUtils.formatUnicode(vmaShip);
        return persistence.updateImpl(vmaShip, false);
    }

    public VmaShip updateVmaShipDeleteStatus(long id, int value)
            throws SystemException, NoSuchVmaShipException {
        VmaShip vmaShip = persistence.findByPrimaryKey(id);
        vmaShip.setIsDelete(value);
        vmaShip.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaShip);
        return persistence.updateImpl(vmaShip, false);
    }

    public JSONObject findVmaShip(String shipName, String shipBoat,
                                  String shipAgencyCode, String flagStateOfShip, String callSign,
                                  String shipOwnerCode, String shipOperatorCode, String shipTypeMT,
                                  String shipTypeCode, double gt, double dwt, double nt, double loa,
                                  double breadth, double shownDraftxA, double clearanceHeight,
                                  double shownDraftxF, double maxDraft, int start, int end)
            throws SystemException, JSONException {

        List<VmaShip> vmaShips = finder.findVmaShip(shipName, shipBoat,
                shipAgencyCode, flagStateOfShip, callSign, shipOwnerCode,
                shipOperatorCode, shipTypeMT, shipTypeCode, gt, dwt, nt, loa,
                breadth, shownDraftxA, clearanceHeight, shownDraftxF, maxDraft,
                start, end);

        long count = finder.countVmaShip(shipName, shipBoat,
                shipAgencyCode, flagStateOfShip, callSign, shipOwnerCode,
                shipOperatorCode, shipTypeMT, shipTypeCode, gt, dwt, nt, loa,
                breadth, shownDraftxA, clearanceHeight, shownDraftxF, maxDraft);

        JSONObject result = JSONFactoryUtil.createJSONObject();

        JSONArray data = JSONFactoryUtil.createJSONArray();

        if (vmaShips != null) {

            for (VmaShip vmaShip : vmaShips) {
                JSONObject object = VMAUtils.object2Json(vmaShip,
                        VmaShip.class, new String[] { "shipTypeCode",
                                "shipTypeMT", "shipOwnerCode",
                                "shipOperatorCode", "flagStateOfShip",
                                "shipAgencyCode" });
                data.put(object);
            }

        }

        result.put("total", count);

        result.put("data", data);

        return result;
    }

    public JSONObject findVmaShip(String searchQuery, String countQuery,
                                  int start, int end) throws JSONException, SystemException {

        List<VmaShip> vmaShips = finder.findVmaShip(VmaShip.class,
                "VmaShip", searchQuery, start, end);

        long count = finder.countVmaShip(countQuery);

        JSONObject result = JSONFactoryUtil.createJSONObject();

        JSONArray data = JSONFactoryUtil.createJSONArray();

        if (vmaShips != null) {

            for (VmaShip vmaShip : vmaShips) {
                JSONObject object = VMAUtils.object2Json(vmaShip,
                        VmaShip.class, new String[] { "shipTypeCode",
                                "shipTypeMT", "shipOwnerCode",
                                "shipOperatorCode", "flagStateOfShip",
                                "shipAgencyCode" });
                data.put(object);
            }

        }

        result.put("total", count);

        result.put("data", data);

        return result;
    }

    public JSONObject findVmaShip(String shipName, String shipBoat,
                                  String shipTypeMT, String shipTypeCode, String tugBoatName,
                                  double gt, double dwt, double loa, String masterCertificateNo,
                                  double breadth, String chiefCertificateNo, String vrCode,
                                  double power, String shipOwnerCode, String nameOfMaster,
                                  String masterCertificateClass, String chiefOfEngineer,
                                  String chiefEngineerCertificateClass, int start, int end)
            throws SystemException, JSONException {

        List<VmaShip> vmaShips = finder.findVmaShip(shipName, shipBoat,
                shipTypeMT, shipTypeCode, tugBoatName, gt, dwt, loa,
                masterCertificateNo, breadth, chiefCertificateNo, vrCode,
                power, shipOwnerCode, nameOfMaster, masterCertificateClass,
                chiefOfEngineer, chiefEngineerCertificateClass, start, end);

        long count = finder.countVmaShip(shipName, shipBoat, shipTypeMT,
                shipTypeCode, tugBoatName, gt, dwt, loa, masterCertificateNo,
                breadth, chiefCertificateNo, vrCode, power, shipOwnerCode,
                nameOfMaster, masterCertificateClass, chiefOfEngineer,
                chiefEngineerCertificateClass);

        JSONObject result = JSONFactoryUtil.createJSONObject();

        JSONArray data = JSONFactoryUtil.createJSONArray();

        if (vmaShips != null) {
            data = JSONFactoryUtil.createJSONArray(JSONFactoryUtil
                    .looseSerialize(vmaShips));
        }

        result.put("total", count);

        result.put("data", data);

        return result;
    }

    public long countVmaShip(String shipName, String shipBoat,
                             String shipAgencyCode, String flagStateOfShip, String callSign,
                             String shipOwnerCode, String shipOperatorCode, String shipTypeMT,
                             String shipTypeCode, double gt, double dwt, double nt, double loa,
                             double breadth, double shownDraftxA, double clearanceHeight,
                             double shownDraftxF, double maxDraft) throws SystemException {
        return finder.countVmaShip(shipName, shipBoat, shipAgencyCode,
                flagStateOfShip, callSign, shipOwnerCode, shipOperatorCode,
                shipTypeMT, shipTypeCode, gt, dwt, nt, loa, breadth,
                shownDraftxA, clearanceHeight, shownDraftxF, maxDraft);
    }

    public long countVmaShip(String shipName, String shipBoat,
                             String shipTypeMT, String shipTypeCode, String tugBoatName,
                             double gt, double dwt, double loa, String masterCertificateNo,
                             double breadth, String chiefCertificateNo, String vrCode,
                             double power, String shipOwnerCode, String nameOfMaster,
                             String masterCertificateClass, String chiefOfEngineer,
                             String chiefEngineerCertificateClass) throws SystemException {
        return finder.countVmaShip(shipName, shipBoat, shipTypeMT,
                shipTypeCode, tugBoatName, gt, dwt, loa, masterCertificateNo,
                breadth, chiefCertificateNo, vrCode, power, shipOwnerCode,
                nameOfMaster, masterCertificateClass, chiefOfEngineer,
                chiefEngineerCertificateClass);
    }

    public long countVmaShip(String sql) throws SystemException {
        return finder.countVmaShip(sql);
    }

    public int countAll() throws SystemException {
        return persistence.countAll();
    }

    public int countByCallSign(String callSign) throws SystemException {
        return persistence.countBycallSign(callSign);
    }

    public int countByIMONumber(String imoNumber) throws SystemException {
        return persistence.countByimoNumber(imoNumber);
    }

    public int countByIMONumber_CallSign(String imoNumber, String callSign)
            throws SystemException {
        return persistence
                .countByimoNumber_callSign(imoNumber, callSign);
    }

    public int countByRegistryNumber(String registryNumber)
            throws SystemException {
        return persistence.countByregistryNumber(registryNumber);
    }

    public int countByVRCode(String vrCode) throws SystemException {
        return persistence.countByvrCode(vrCode);
    }

    public int countByVRCode_RegistryNumber(String imoNumber,
                                            String registryNumber) throws SystemException {
        return persistence.countByvrCode_registryNumber(imoNumber,
                registryNumber);
    }

    public VmaShip findByCallSign(String callSign)
            throws NoSuchVmaShipException, SystemException {
        return persistence.findBycallSign(callSign);
    }

    public VmaShip fetchByCallSign(String callSign) throws SystemException {
        return persistence.fetchBycallSign(callSign);
    }

    public VmaShip findByIMONumber(String imoNumber) throws SystemException,
            NoSuchVmaShipException {
        return persistence.findByimoNumber(imoNumber);
    }

    public VmaShip fetchByIMONumber(String imoNumber) throws SystemException {
        return persistence.fetchByimoNumber(imoNumber);
    }

    public VmaShip findByIMONumber_CallSign(String imoNumber, String callSign)
            throws SystemException, NoSuchVmaShipException {
        return persistence.findByimoNumber_callSign(imoNumber, callSign);
    }

    public VmaShip fetchByIMONumber_CallSign(String imoNumber, String callSign)
            throws SystemException {
        return persistence
                .fetchByimoNumber_callSign(imoNumber, callSign);
    }

    public VmaShip findByRegistryNumber(String registryNumber)
            throws SystemException, NoSuchVmaShipException {
        return persistence.findByregistryNumber(registryNumber);
    }

    public VmaShip fetchByRegistryNumber(String registryNumber)
            throws SystemException, NoSuchVmaShipException {
        return persistence.fetchByregistryNumber(registryNumber);
    }

    public VmaShip findByVRCode(String vrCode) throws SystemException,
            NoSuchVmaShipException {
        return persistence.findByvrCode(vrCode);
    }

    public VmaShip fetchByVRCode(String vrCode) throws SystemException {
        return persistence.fetchByvrCode(vrCode);
    }

    public VmaShip findByVRCode_RegistryNumber(String imoNumber,
                                               String registryNumber) throws SystemException,
            NoSuchVmaShipException {
        return persistence.findByvrCode_registryNumber(imoNumber,
                registryNumber);
    }

    public VmaShip fetchByVRCode_RegistryNumber(String imoNumber,
                                                String registryNumber) throws SystemException {
        return persistence.fetchByvrCode_registryNumber(imoNumber,
                registryNumber);
    }

    public List<VmaShip> findAll(int start, int end) throws SystemException {
        return persistence.findAll(start, end);
    }

    public List<VmaShip> findAll(int start, int end,
                                 OrderByComparator orderByComparator) throws SystemException {
        return persistence.findAll(start, end, orderByComparator);
    }

    public VmaShip fetchByIMONumber_CallSign_RegistryNumber(String imoNumber,
                                                            String callSign, String registryNumber) {
        try {
            return persistence.fetchByimoNumber_callSign_registryNumber(
                    imoNumber, callSign, registryNumber);
        } catch (SystemException e) {
            return null;
        }
    }

}

