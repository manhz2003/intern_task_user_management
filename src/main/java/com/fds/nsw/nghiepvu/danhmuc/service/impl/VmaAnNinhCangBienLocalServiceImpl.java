package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import java.util.ArrayList; import java.util.List;
import java.util.Date;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.VmaAnNinhCangBienPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class VmaAnNinhCangBienLocalServiceImpl { @Autowired
VmaAnNinhCangBienPersistenceImpl persistence;

	public VmaAnNinhCangBien createVmaAnNinhCangBien(long id) {
		return persistence.create(id);
	}

	public VmaAnNinhCangBien deleteVmaAnNinhCangBien(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaAnNinhCangBien deleteVmaAnNinhCangBien(VmaAnNinhCangBien VmaAnNinhCangBien)
		throws SystemException {
		return persistence.remove(VmaAnNinhCangBien);
	}

	public VmaAnNinhCangBien fetchVmaAnNinhCangBien(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaAnNinhCangBien getVmaAnNinhCangBien(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaAnNinhCangBien> getVmaAnNinhCangBiens(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaAnNinhCangBiensCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaAnNinhCangBien updateVmaAnNinhCangBien(VmaAnNinhCangBien VmaAnNinhCangBien,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaAnNinhCangBien, merge);
	}


    public VmaAnNinhCangBien addVmaAnNinhCangBien(VmaAnNinhCangBien vmaAnNinhCangBien) throws SystemException
    {
        long id = CounterLocalServiceUtil
                .increment(VmaAnNinhCangBien.class.getName());
        vmaAnNinhCangBien.setId(id);
        vmaAnNinhCangBien.setMarkedAsDelete(0);
        vmaAnNinhCangBien.setModifiedDate((new Date()));
        //VMAUtils.formatUnicode(vmaAccidentList);
        return persistence.updateImpl(vmaAnNinhCangBien, false);
    }

    public VmaAnNinhCangBien updateVmaAnNinhCangBien(VmaAnNinhCangBien vmaAnNinhCangBien) throws SystemException{
        vmaAnNinhCangBien.setModifiedDate(new Date());
        //VMAUtils.formatUnicode(vmaAccidentList);
        return persistence.updateImpl(vmaAnNinhCangBien, false);
    }
    public long countVmaAnNinhCangBien() throws SystemException{
        return persistence.countAll();
    }

}
