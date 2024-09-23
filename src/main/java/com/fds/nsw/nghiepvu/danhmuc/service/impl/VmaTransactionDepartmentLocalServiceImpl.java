package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.VmaTransactionDepartmentFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.VmaTransactionDepartmentPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class VmaTransactionDepartmentLocalServiceImpl { @Autowired
VmaTransactionDepartmentPersistenceImpl persistence;@Autowired
VmaTransactionDepartmentFinderImpl finder;
public VmaTransactionDepartment addVmaTransactionDepartment(VmaTransactionDepartment VmaTransactionDepartment)
		throws SystemException {
		return persistence.updateImpl(VmaTransactionDepartment, false);
	}

	public VmaTransactionDepartment createVmaTransactionDepartment(long id) {
		return persistence.create(id);
	}



	public VmaTransactionDepartment deleteVmaTransactionDepartment(VmaTransactionDepartment VmaTransactionDepartment)
		throws SystemException {
		return persistence.remove(VmaTransactionDepartment);
	}

	public VmaTransactionDepartment fetchVmaTransactionDepartment(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaTransactionDepartment getVmaTransactionDepartment(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaTransactionDepartment> getVmaTransactionDepartments(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaTransactionDepartmentsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaTransactionDepartment updateVmaTransactionDepartment(VmaTransactionDepartment VmaTransactionDepartment)
		throws SystemException {
		return updateVmaTransactionDepartment(VmaTransactionDepartment, true);
	}

	public VmaTransactionDepartment updateVmaTransactionDepartment(VmaTransactionDepartment VmaTransactionDepartment,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaTransactionDepartment, merge);
	}

    public List<VmaTransactionDepartment> findByF_portOfAuthority(
            String portOfAuthority) throws SystemException {
        try {
            return persistence
                    .findByF_portOfAuthority(portOfAuthority);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<VmaTransactionDepartment> findVmaTransactionDepartmentByDepartmentCodes(
            String portofAuthority, List<String> departmentCodes) {
        return finder
                .findVmaTransactionDepartmentByDepartmentCodes(portofAuthority,
                        departmentCodes);
    }

    public List<String> checkExistDepartmentCode(String portofAuthority) {
        return finder
                .checkExistDepartmentCode(portofAuthority);
    }

    public List<String[]> getDepartmentInfo(String portofAuthority) {
        return finder
                .getDepartmentInfo(portofAuthority);
    }

    public List<VmaTransactionDepartment> findVmaTransactionDepartments(
            String portOfAuthority, String departmentName, int start, int end)
            throws SystemException {
        try {
            return finder
                    .findVmaTransactionDepartments(portOfAuthority,
                            departmentName, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public long coutVmaTransactionDepartments(String portOfAuthority,
                                              String departmentName) throws SystemException {
        try {
            return finder
                    .countVmaTransactionDepartments(portOfAuthority,
                            departmentName);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    public VmaTransactionDepartment fetchVmaTransactionDepartment(
            String departmentCode) {
        try {
            return persistence
                    .fetchByF_departmentCode(departmentCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public VmaTransactionDepartment updateVmaTransactionDepartment(
            String portOfAuthority, String departmentCode,
            String departmentName, int sequenceNo, String transactionTypeVND,
            String transactionTypeUSD) throws SystemException {

        VmaTransactionDepartment vmaTransactionDepartment = persistence
                .fetchByF_departmentCode(departmentCode);

        if (vmaTransactionDepartment == null) {
            long id = CounterLocalServiceUtil
                    .increment(VmaTransactionDepartment.class.getName());

            vmaTransactionDepartment = persistence
                    .create(id);

            vmaTransactionDepartment.setSequenceNo(sequenceNo);
        }
        Date now = new Date();

        vmaTransactionDepartment.setDepartmentCode(departmentCode);
        vmaTransactionDepartment.setPortofAuthority(portOfAuthority);
        vmaTransactionDepartment.setDepartmentName(departmentName);
        vmaTransactionDepartment.setTransactionTypeVND(transactionTypeVND);
        vmaTransactionDepartment.setTransactionTypeUSD(transactionTypeUSD);
        vmaTransactionDepartment.setModifiedDate(now);

        vmaTransactionDepartment = persistence.updateImpl(
                vmaTransactionDepartment, true);

        return vmaTransactionDepartment;
    }

    public VmaTransactionDepartment deleteVmaTransactionDepartment(long id)
            throws NoSuchVmaTransactionDepartmentException, SystemException {

        persistence.remove(id);
        return null;
    }

}
