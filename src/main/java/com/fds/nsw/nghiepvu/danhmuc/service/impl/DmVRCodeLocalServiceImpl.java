package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import java.util.ArrayList; import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmVRCodePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service
public class DmVRCodeLocalServiceImpl { @Autowired
DmVRCodePersistenceImpl persistence;

public DmVRCode addDmVRCode(DmVRCode DmVRCode)
		throws SystemException {
		return persistence.updateImpl(DmVRCode, false);
	}

	public DmVRCode createDmVRCode(int id) {
		return persistence.create(id);
	}

	public DmVRCode deleteDmVRCode(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public DmVRCode deleteDmVRCode(DmVRCode DmVRCode)
		throws SystemException {
		return persistence.remove(DmVRCode);
	}

	public DmVRCode fetchDmVRCode(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmVRCode getDmVRCode(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<DmVRCode> getDmVRCodes(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getDmVRCodesCount() throws SystemException {
		return persistence.countAll();
	}

	public DmVRCode updateDmVRCode(DmVRCode DmVRCode)
		throws SystemException {
		return updateDmVRCode(DmVRCode, true);
	}

	public DmVRCode updateDmVRCode(DmVRCode DmVRCode,
		boolean merge) throws SystemException {
		return persistence.updateImpl(DmVRCode, merge);
	}


    public List<DmVRCode> findByShipBoat(String shipBoat, int start, int end){
        try{
            return persistence.findByF_shipBoat(shipBoat, start, end);
        }catch(Exception e){
            log.info(e.getMessage());
            return null;
        }
    }

    public long countByShipBoat(String shipBoat){
        try{
            return persistence.countByF_shipBoat(shipBoat);
        }catch(Exception e){
            log.info(e.getMessage());
            return 0;
        }
    }

}
