package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import java.util.ArrayList; import java.util.List;
import java.util.Date;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.VmaHoatDongNaoVetPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class VmaHoatDongNaoVetLocalServiceImpl { @Autowired
VmaHoatDongNaoVetPersistenceImpl persistence;

	public VmaHoatDongNaoVet createVmaHoatDongNaoVet(long id) {
		return persistence.create(id);
	}

	public VmaHoatDongNaoVet deleteVmaHoatDongNaoVet(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaHoatDongNaoVet deleteVmaHoatDongNaoVet(VmaHoatDongNaoVet VmaHoatDongNaoVet)
		throws SystemException {
		return persistence.remove(VmaHoatDongNaoVet);
	}

	public VmaHoatDongNaoVet fetchVmaHoatDongNaoVet(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaHoatDongNaoVet getVmaHoatDongNaoVet(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaHoatDongNaoVet> getVmaHoatDongNaoVets(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaHoatDongNaoVetsCount() throws SystemException {
		return persistence.countAll();
	}


	public VmaHoatDongNaoVet updateVmaHoatDongNaoVet(VmaHoatDongNaoVet VmaHoatDongNaoVet,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaHoatDongNaoVet, merge);
	}

    public VmaHoatDongNaoVet addVmaHoatDongNaoVet(VmaHoatDongNaoVet vmaHoatDongNaoVet)
            throws SystemException {
        long id = CounterLocalServiceUtil
                .increment(VmaHoatDongNaoVet.class.getName());
        vmaHoatDongNaoVet.setId(id);
        vmaHoatDongNaoVet.setMarkedAsDelete(0);
        vmaHoatDongNaoVet.setModifiedDate((new Date()));
        //VMAUtils.formatUnicode(vmaAccidentList);
        return persistence.updateImpl(vmaHoatDongNaoVet, false);
    }

    public VmaHoatDongNaoVet updateVmaHoatDongNaoVet(VmaHoatDongNaoVet vmaHoatDongNaoVet)
            throws SystemException {
        vmaHoatDongNaoVet.setModifiedDate(new Date());
        //VMAUtils.formatUnicode(vmaAccidentList);
        return persistence.updateImpl(vmaHoatDongNaoVet, false);
    }

    public long countVmaHoatDongNaoVet() throws SystemException{
        return persistence.countAll();
    }

}
