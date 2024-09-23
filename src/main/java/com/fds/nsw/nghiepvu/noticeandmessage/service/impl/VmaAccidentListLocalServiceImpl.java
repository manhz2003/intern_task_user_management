package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaAccidentListFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaAccidentListPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaAccidentListLocalServiceImpl { 
@Autowired VmaAccidentListPersistenceImpl persistence;
@Autowired VmaAccidentListFinderImpl finder;

	public VmaAccidentList createVmaAccidentList(long id) {
		return persistence.create(id);
	}

	public VmaAccidentList deleteVmaAccidentList(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaAccidentList deleteVmaAccidentList(VmaAccidentList VmaAccidentList)
		throws SystemException {
		return persistence.remove(VmaAccidentList);
	}

	public VmaAccidentList fetchVmaAccidentList(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaAccidentList getVmaAccidentList(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaAccidentList> getVmaAccidentLists(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaAccidentListsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaAccidentList updateVmaAccidentList(VmaAccidentList VmaAccidentList,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaAccidentList, merge);
	}
public VmaAccidentList addVmaAccidentList(VmaAccidentList vmaAccidentList)
        throws SystemException {
    long id = CounterLocalServiceUtil
            .increment(VmaAccidentList.class.getName());
    vmaAccidentList.setId(id);
    vmaAccidentList.setModifiedDate(new Date());
    //VMAUtils.formatUnicode(vmaAccidentList);
    return persistence.updateImpl(vmaAccidentList, false);
}

    public VmaAccidentList updateVmaAccidentList(VmaAccidentList vmaAccidentList)
            throws SystemException {
        vmaAccidentList.setModifiedDate(new Date());
        //VMAUtils.formatUnicode(vmaAccidentList);
        return persistence.updateImpl(vmaAccidentList, false);
    }

    public VmaAccidentList delete(long id) throws SystemException,
            NoSuchVmaAccidentListException {
        return persistence.remove(id);
    }

    public List<VmaAccidentList> findByPortofAuthority(String portofAuthority)
            throws SystemException {
        return persistence
                .findByportofAuthority(portofAuthority);
    }

    public int countByPortofAuthority(String portofAuthority)
            throws SystemException {
        return persistence
                .countByportofAuthority(portofAuthority);
    }

    public List<VmaAccidentList> getVmaAccidentLists(String nameOfShip,
                                                     String flagStateOfShip, String callSign, String imoNumber,
                                                     String registryNumber, Date accidentTime, String accidentType,
                                                     String accidentCriticalType, String numberOfDead,
                                                     String numberOfMissed, String numberOfInjured, String pilotOnBoad,
                                                     String makeInvestigation, Date investigationDate,
                                                     String portofAuthority, Date accidentOfficialDate, int start,
                                                     int end) throws SystemException {
        return finder.getVmaAccidentLists(nameOfShip,
                flagStateOfShip, callSign, imoNumber, registryNumber,
                accidentTime, accidentType, accidentCriticalType, numberOfDead,
                numberOfMissed, numberOfInjured, pilotOnBoad,
                makeInvestigation, investigationDate, portofAuthority,
                accidentOfficialDate, start, end);
    }

    public long countVmaAccidentLists(String nameOfShip,
                                      String flagStateOfShip, String callSign, String imoNumber,
                                      String registryNumber, Date accidentTime, String accidentType,
                                      String accidentCriticalType, String numberOfDead,
                                      String numberOfMissed, String numberOfInjured, String pilotOnBoad,
                                      String makeInvestigation, Date investigationDate,
                                      String portofAuthority, Date accidentOfficialDate)
            throws SystemException {
        return finder.countVmaAccidentLists(nameOfShip,
                flagStateOfShip, callSign, imoNumber, registryNumber,
                accidentTime, accidentType, accidentCriticalType, numberOfDead,
                numberOfMissed, numberOfInjured, pilotOnBoad,
                makeInvestigation, investigationDate, portofAuthority,
                accidentOfficialDate);
    }


}

