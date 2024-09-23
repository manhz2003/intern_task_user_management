package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.VmaServicePortPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class VmaServicePortLocalServiceImpl { @Autowired
VmaServicePortPersistenceImpl persistence;


	public VmaServicePort createVmaServicePort(long id) {
		return persistence.create(id);
	}

	public VmaServicePort deleteVmaServicePort(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaServicePort deleteVmaServicePort(VmaServicePort VmaServicePort)
		throws SystemException {
		return persistence.remove(VmaServicePort);
	}

	public VmaServicePort fetchVmaServicePort(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaServicePort getVmaServicePort(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaServicePort> getVmaServicePorts(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaServicePortsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaServicePort updateVmaServicePort(VmaServicePort VmaServicePort,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaServicePort, merge);
	}

    public VmaServicePort addVmaServicePort(VmaServicePort vmaServicePort)
            throws SystemException{
        long id = CounterLocalServiceUtil
                .increment(VmaServicePort.class.getName());
        vmaServicePort.setId(id);
        vmaServicePort.setIsDelete(0);
        vmaServicePort.setSyncVersion("1|");
        vmaServicePort.setMarkedAsDelete(0);
        vmaServicePort.setModifiedDate((new Date()));
        vmaServicePort.setRequestedDate(new Date());
        //VMAUtils.formatUnicode(vmaAccidentList);
        return persistence.updateImpl(vmaServicePort, false);
    }

    public VmaServicePort updateVmaServicePort(VmaServicePort vmaServicePort)
            throws SystemException{
        vmaServicePort.setModifiedDate(new Date());
        //VMAUtils.formatUnicode(vmaAccidentList);
        return persistence.updateImpl(vmaServicePort, false);
    }
    public long countVmaServicePort() throws SystemException{
        return persistence.countAll();
    }

}
