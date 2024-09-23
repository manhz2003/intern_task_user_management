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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaScheduleTransferFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaScheduleTransferPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaScheduleTransferLocalServiceImpl { 
@Autowired VmaScheduleTransferPersistenceImpl persistence;
@Autowired VmaScheduleTransferFinderImpl finder;

	public VmaScheduleTransfer createVmaScheduleTransfer(long id) {
		return persistence.create(id);
	}

	public VmaScheduleTransfer deleteVmaScheduleTransfer(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaScheduleTransfer deleteVmaScheduleTransfer(VmaScheduleTransfer VmaScheduleTransfer)
		throws SystemException {
		return persistence.remove(VmaScheduleTransfer);
	}

	public VmaScheduleTransfer fetchVmaScheduleTransfer(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaScheduleTransfer getVmaScheduleTransfer(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaScheduleTransfer> getVmaScheduleTransfers(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaScheduleTransfersCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaScheduleTransfer updateVmaScheduleTransfer(VmaScheduleTransfer VmaScheduleTransfer)
		throws SystemException {
		return updateVmaScheduleTransfer(VmaScheduleTransfer, true);
	}

	public VmaScheduleTransfer updateVmaScheduleTransfer(VmaScheduleTransfer VmaScheduleTransfer,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaScheduleTransfer, merge);
	}
public VmaScheduleTransfer addVmaScheduleTransfer(
        VmaScheduleTransfer vmaScheduleTransfer) throws SystemException {
    long id = CounterLocalServiceUtil.increment(VmaScheduleTransfer.class
            .getName());
    vmaScheduleTransfer.setId(id);
    vmaScheduleTransfer.setModifiedDate(new Date());
    return persistence.updateImpl(vmaScheduleTransfer, false);
}

    public VmaScheduleTransfer updayeVmaScheduleTransfer(
            VmaScheduleTransfer vmaScheduleTransfer) throws SystemException {
        // VMAUtils.formatUnicode(VmaScheduleTransfer);
        return persistence.updateImpl(vmaScheduleTransfer, false);
    }

    public int countByCallSign(String callSign) throws SystemException {
        return persistence.countBycallSign(callSign);
    }

    public int countByIMONumber(String imoNumber) throws SystemException {
        return persistence.countByimoNumber(imoNumber);
    }

    public int countByIMONumber_CallSign(String imoNumber, String callSign)
            throws SystemException {
        return persistence.countByimoNumber_callSign(
                imoNumber, callSign);
    }

    public int countByRegistryNumber(String registryNumber)
            throws SystemException {
        return persistence
                .countByregistryNumber(registryNumber);
    }

    public int countByVRCode(String vrCode) throws SystemException {
        return persistence.countByvrCode(vrCode);
    }

    public int countByVRCode_RegistryNumber(String imoNumber,
                                            String registryNumber) throws SystemException {
        return persistence.countByvrCode_registryNumber(
                imoNumber, registryNumber);
    }

    public VmaScheduleTransfer findByCallSign(String callSign)
            throws NoSuchVmaScheduleTransferException, SystemException {
        return persistence.findBycallSign(callSign);
    }

    public VmaScheduleTransfer fetchByCallSign(String callSign)
            throws SystemException {
        return persistence.fetchBycallSign(callSign);
    }

    public VmaScheduleTransfer findByIMONumber(String imoNumber)
            throws SystemException, NoSuchVmaScheduleTransferException {
        return persistence.findByimoNumber(imoNumber);
    }

    public VmaScheduleTransfer fetchByIMONumber(String imoNumber)
            throws SystemException {
        return persistence.fetchByimoNumber(imoNumber);
    }

    public VmaScheduleTransfer findByIMONumber_CallSign(String imoNumber,
                                                        String callSign) throws SystemException,
            NoSuchVmaScheduleTransferException {
        return persistence.findByimoNumber_callSign(
                imoNumber, callSign);
    }

    public VmaScheduleTransfer fetchByIMONumber_CallSign(String imoNumber,
                                                         String callSign) throws SystemException {
        return persistence.fetchByimoNumber_callSign(
                imoNumber, callSign);
    }

    public VmaScheduleTransfer findByRegistryNumber(String registryNumber)
            throws SystemException, NoSuchVmaScheduleTransferException {
        return persistence
                .findByregistryNumber(registryNumber);
    }

    public VmaScheduleTransfer fetchByRegistryNumber(String registryNumber)
            throws SystemException, NoSuchVmaScheduleTransferException {
        return persistence
                .fetchByregistryNumber(registryNumber);
    }

    public VmaScheduleTransfer findByVRCode(String vrCode)
            throws SystemException, NoSuchVmaScheduleTransferException {
        return persistence.findByvrCode(vrCode);
    }

    public VmaScheduleTransfer fetchByVRCode(String vrCode)
            throws SystemException {
        return persistence.fetchByvrCode(vrCode);
    }

    public VmaScheduleTransfer findByVRCode_RegistryNumber(String imoNumber,
                                                           String registryNumber) throws SystemException,
            NoSuchVmaScheduleTransferException {
        return persistence.findByvrCode_registryNumber(
                imoNumber, registryNumber);
    }

    public VmaScheduleTransfer fetchByVRCode_RegistryNumber(String imoNumber,
                                                            String registryNumber) throws SystemException {
        return persistence.fetchByvrCode_registryNumber(
                imoNumber, registryNumber);
    }

    public List<VmaScheduleTransfer> findAll(int start, int end)
            throws SystemException {
        return persistence.findAll(start, end);
    }

    public List<VmaScheduleTransfer> findAll(int start, int end,
                                             OrderByComparator orderByComparator) throws SystemException {
        return persistence.findAll(start, end,
                orderByComparator);
    }

    public VmaScheduleTransfer fetchByIMONumber_CallSign_RegistryNumber(
            String imoNumber, String callSign, String registryNumber) {
        try {
            return persistence
                    .fetchByimoNumber_callSign_registryNumber(imoNumber,
                            callSign, registryNumber);
        } catch (SystemException e) {
            return null;
        }
    }

    public VmaScheduleTransfer findByItineraryScheduleId(
            long itineraryScheduleId) {
        try {
            return persistence
                    .findByF_ItineraryScheduleId(itineraryScheduleId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<VmaScheduleTransfer> findByItineraryNo_NoticeShipType(
            String itineraryNo, int noticeShipType) {
        try {
            return persistence
                    .findByitineraryNo_noticeShipType(itineraryNo,
                            noticeShipType);
        } catch (Exception e) {
            return null;
        }
    }


}

