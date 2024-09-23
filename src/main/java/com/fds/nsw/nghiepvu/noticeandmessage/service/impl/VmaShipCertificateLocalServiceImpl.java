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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaShipCertificateFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaShipCertificatePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaShipCertificateLocalServiceImpl { 
@Autowired VmaShipCertificatePersistenceImpl persistence;
@Autowired VmaShipCertificateFinderImpl finder;

	public VmaShipCertificate createVmaShipCertificate(long id) {
		return persistence.create(id);
	}

	public VmaShipCertificate deleteVmaShipCertificate(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaShipCertificate deleteVmaShipCertificate(VmaShipCertificate VmaShipCertificate)
		throws SystemException {
		return persistence.remove(VmaShipCertificate);
	}

	public VmaShipCertificate fetchVmaShipCertificate(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaShipCertificate getVmaShipCertificate(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaShipCertificate> getVmaShipCertificates(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaShipCertificatesCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaShipCertificate updateVmaShipCertificate(VmaShipCertificate VmaShipCertificate,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaShipCertificate, merge);
	}

    public VmaShipCertificate addVmaShipCertificate(
            VmaShipCertificate vmaShipCertificate) throws SystemException {
        long id = CounterLocalServiceUtil.increment(VmaShipCertificate.class
                .getName());

        vmaShipCertificate.setId(id);
        // vmaShipCertificate.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaShipCertificate);
        return persistence.updateImpl(vmaShipCertificate, false);
    }

    public VmaShipCertificate delete(long id) throws SystemException,
            NoSuchVmaShipCertificateException {
        return persistence.remove(id);
    }

    public VmaShipCertificate updateVmaShipCertificate(
            VmaShipCertificate vmaShipCertificate) throws SystemException {
        // VMAUtils.formatUnicode(vmaShipCertificate);
        return persistence.updateImpl(vmaShipCertificate, false);
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

    public VmaShipCertificate findByCallSign(String callSign)
            throws NoSuchVmaShipCertificateException, SystemException {
        return persistence.findBycallSign(callSign);
    }

    public VmaShipCertificate fetchByCallSign(String callSign)
            throws SystemException {
        return persistence.fetchBycallSign(callSign);
    }

    public VmaShipCertificate findByIMONumber(String imoNumber)
            throws SystemException, NoSuchVmaShipCertificateException {
        return persistence.findByimoNumber(imoNumber);
    }

    public VmaShipCertificate fetchByIMONumber(String imoNumber)
            throws SystemException {
        return persistence.fetchByimoNumber(imoNumber);
    }

    public List<VmaShipCertificate> findByIMONumber_CallSign(String imoNumber,
                                                             String callSign) throws SystemException,
            NoSuchVmaShipCertificateException {
        return persistence.findByimoNumber_callSign(
                imoNumber, callSign);
    }

    public VmaShipCertificate findByRegistryNumber(String registryNumber)
            throws SystemException, NoSuchVmaShipCertificateException {
        return persistence
                .findByregistryNumber(registryNumber);
    }

    public VmaShipCertificate fetchByRegistryNumber(String registryNumber)
            throws SystemException, NoSuchVmaShipCertificateException {
        return persistence
                .fetchByregistryNumber(registryNumber);
    }

    public VmaShipCertificate findByVRCode(String vrCode)
            throws SystemException, NoSuchVmaShipCertificateException {
        return persistence.findByvrCode(vrCode);
    }

    public VmaShipCertificate fetchByVRCode(String vrCode)
            throws SystemException {
        return persistence.fetchByvrCode(vrCode);
    }

    public VmaShipCertificate findByVRCode_RegistryNumber(String imoNumber,
                                                          String registryNumber) throws SystemException,
            NoSuchVmaShipCertificateException {
        return persistence.findByvrCode_registryNumber(
                imoNumber, registryNumber);
    }

    public VmaShipCertificate fetchByVRCode_RegistryNumber(String imoNumber,
                                                           String registryNumber) throws SystemException {
        return persistence.fetchByvrCode_registryNumber(
                imoNumber, registryNumber);
    }

    public List<VmaShipCertificate> findAll(int start, int end)
            throws SystemException {
        return persistence.findAll(start, end);
    }

    public List<VmaShipCertificate> findAll(int start, int end,
                                            OrderByComparator orderByComparator) throws SystemException {
        return persistence.findAll(start, end,
                orderByComparator);
    }

    public List<VmaShipCertificate> findVmaShipCertificate(String imoNumber,
                                                           String callSign, int start, int end) throws SystemException {
        return finder.findVmaShipCertificate(imoNumber,
                callSign, start, end);
    }

}

