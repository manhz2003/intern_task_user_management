package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.fds.nsw.kernel.exception.PortalException;
import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaCertificateFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaCertificatePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaCertificateLocalServiceImpl { 
@Autowired VmaCertificatePersistenceImpl persistence;
@Autowired VmaCertificateFinderImpl finder;
public VmaCertificate addVmaCertificate(VmaCertificate VmaCertificate)
		throws SystemException {
		return persistence.updateImpl(VmaCertificate, false);
	}

	public VmaCertificate createVmaCertificate(int id) {
		return persistence.create(id);
	}

	public VmaCertificate deleteVmaCertificate(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaCertificate deleteVmaCertificate(VmaCertificate VmaCertificate)
		throws SystemException {
		return persistence.remove(VmaCertificate);
	}

	public VmaCertificate fetchVmaCertificate(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaCertificate getVmaCertificate(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaCertificate> getVmaCertificates(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaCertificatesCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaCertificate updateVmaCertificate(VmaCertificate VmaCertificate,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaCertificate, merge);
	}


    public List<VmaCertificate> findVmaCertificates(String nameOfShip,
                                                    String certificateExpiredDate, String certificateIssueDate,
                                                    String approvalName, String isExamined, String examinationDate,
                                                    String imoNumber, String callSign, String registryNumber,
                                                    int start, int end) throws SystemException {
        return finder.findVmaCertificates(nameOfShip,
                certificateExpiredDate, certificateIssueDate, approvalName,
                isExamined, examinationDate, imoNumber, callSign,
                registryNumber, start, end);
    }

    public long countVmaCertificates(String nameOfShip,
                                     String certificateExpiredDate, String certificateIssueDate,
                                     String approvalName, String isExamined, String examinationDate,
                                     String imoNumber, String callSign, String registryNumber)
            throws SystemException {
        return finder.countVmaCertificates(nameOfShip,
                certificateExpiredDate, certificateIssueDate, approvalName,
                isExamined, examinationDate, imoNumber, callSign,
                registryNumber);
    }

    public VmaCertificate updateVmaCertificate(VmaCertificate vmaCertificate)
            throws SystemException {

        return persistence.updateImpl(vmaCertificate, false);
    }

    public VmaCertificate updateVmaCertificate(int id, String certificateCode,
                                               String nameOfShip, Date certificateExpiredDate,
                                               Date certificateIssueDate, Date examinationDate,
                                               String approvalName, int isExamined, String imoNumber,
                                               String registryNumber, String callSign) throws SystemException {

        VmaCertificate vmaCertificate = persistence
                .fetchByPrimaryKey(id);

        if (vmaCertificate == null) {
            id = Integer.valueOf(String.valueOf(CounterLocalServiceUtil
                    .increment(VmaCertificate.class.getName())));

            vmaCertificate = persistence.create(id);
        }

        vmaCertificate.setNameOfShip(nameOfShip);
        vmaCertificate.setApprovalName(approvalName);
        vmaCertificate.setCertificateExpiredDate(certificateExpiredDate);
        vmaCertificate.setCertificateCode(certificateCode);
        vmaCertificate.setCertificateIssueDate(certificateIssueDate);
        vmaCertificate.setExaminationDate(examinationDate);
        vmaCertificate.setIsExamined(isExamined);
        vmaCertificate.setImoNumber(imoNumber);
        vmaCertificate.setRegistryNumber(registryNumber);
        vmaCertificate.setCallSign(callSign);

        return persistence.updateImpl(vmaCertificate, false);
    }

}

