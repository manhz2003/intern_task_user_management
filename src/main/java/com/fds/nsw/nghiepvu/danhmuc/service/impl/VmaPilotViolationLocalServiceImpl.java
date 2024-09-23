package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.VmaPilotViolationPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class VmaPilotViolationLocalServiceImpl { @Autowired
VmaPilotViolationPersistenceImpl persistence;
public VmaPilotViolation addVmaPilotViolation(VmaPilotViolation VmaPilotViolation)
		throws SystemException {
		return persistence.updateImpl(VmaPilotViolation, false);
	}

	public VmaPilotViolation createVmaPilotViolation(long id) {
		return persistence.create(id);
	}

	public VmaPilotViolation deleteVmaPilotViolation(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaPilotViolation deleteVmaPilotViolation(VmaPilotViolation VmaPilotViolation)
		throws SystemException {
		return persistence.remove(VmaPilotViolation);
	}

	public VmaPilotViolation fetchVmaPilotViolation(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaPilotViolation getVmaPilotViolation(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaPilotViolation> getVmaPilotViolations(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaPilotViolationsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaPilotViolation updateVmaPilotViolation(VmaPilotViolation VmaPilotViolation)
		throws SystemException {
		return updateVmaPilotViolation(VmaPilotViolation, true);
	}

	public VmaPilotViolation updateVmaPilotViolation(VmaPilotViolation VmaPilotViolation,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaPilotViolation, merge);
	}
public List<VmaPilotViolation> findByMaritimeCode(String maritimeCode,
                                                  int start, int end) {
    try {
        return persistence.findByF_maritimeCode(
                maritimeCode, start, end);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

    public VmaPilotViolation updateVmaPilotViolation(String fromMaritimeCode,
                                                     long id, String maritimeCode, String pilotCode, Date violationDate,
                                                     String violationDescription, String troubleshooting)
            throws SystemException {

        VmaPilotViolation vmaPilotViolation = persistence
                .fetchByPrimaryKey(id);
        if (vmaPilotViolation == null) {
            id = CounterLocalServiceUtil.increment(VmaPilotViolation.class
                    .getName());

            vmaPilotViolation = persistence.create(id);
        }
        vmaPilotViolation.setMaritimeCode(maritimeCode);
        vmaPilotViolation.setPilotCode(pilotCode);
        vmaPilotViolation.setViolationDate(violationDate);
        vmaPilotViolation.setViolationDescription(violationDescription);
        vmaPilotViolation.setTroubleshooting(troubleshooting);

        return persistence.updateImpl(vmaPilotViolation, true);
    }

    public VmaPilotViolation deleteVmaPilotViolation(String fromMaritimeCode,
                                                     long id) throws NoSuchVmaPilotViolationException, SystemException {
        return persistence.remove(id);
    }

}
