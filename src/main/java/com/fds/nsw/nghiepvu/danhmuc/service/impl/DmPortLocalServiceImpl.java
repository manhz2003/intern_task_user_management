/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.fds.nsw.nghiepvu.danhmuc.service.impl;import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmPortFinderImpl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmPortPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service









/**
 * The implementation of the dm port local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.danhmuc.service.DmPortLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can
 * only be accessed from within the same VM.
 * </p>
 * 
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmPortLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil
 */

public class DmPortLocalServiceImpl{
	@Autowired
	DmPortPersistenceImpl persistence;

	@Autowired
	DmPortFinderImpl finder;

	public List<DmPort> findByPortName(String portName, int start, int end) {
		try {
			return persistence.findByF_portNameByLike(portName, start,
					end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<DmPort> findPorts(String portName, String isDelete,
								  String portCodeGroup, String portType, String stateCode, int start,
								  int end) {
		try {
			return finder.findPorts(portName, isDelete, portCodeGroup,
					portType, stateCode, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public long countPorts(String portName, String isDelete,
						   String portCodeGroup, String portType, String stateCode) {
		try {
			return finder.countPorts(portName, isDelete, portCodeGroup,
					portType, stateCode);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}


	public List<DmPort> findAll() {
		try {
			return persistence.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public DmPort getByPortCode(String portCode) {
		try {
			List<DmPort> findByPortCode = persistence.findByPortCode(portCode);
			
			if(findByPortCode != null && findByPortCode.size() > 0) {
				return findByPortCode.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DmPort> findByLoCode(String loCode) {
		try {
			return persistence.findByLoCode(loCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int countByPortCode(String portCode) {
		try {
			return persistence.countByPortCode(portCode);
		} catch (Exception e) {
		}
		return 0;
	}
	
	public List<DmPort> findAll(int start, int end) {
		try {
			return persistence.findAll(start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DmPort> findByStateCodeAndCityCode(String stateCode, String citycode) {
		try {
			return persistence.findByStateCodeAndCityCode(stateCode, citycode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public DmPort fetchByPortCodeLoCode(String portCode, String loCode) {
		try {
			return persistence.fetchByPortCodeLoCode(portCode, loCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil} to access the dm port local service.
	 */

	/**
	 * Adds the dm port to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmPort the dm port
	 * @return the dm port that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort addDmPort(DmPort dmPort) throws SystemException {

		dmPort = persistence.updateImpl(dmPort, false);





		return dmPort;
	}

	/**
	 * Creates a new dm port with the primary key. Does not add the dm port to the database.
	 *
	 * @param id the primary key for the new dm port
	 * @return the new dm port
	 */
	public DmPort createDmPort(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm port with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm port
	 * @throws PortalException if a dm port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmPort(int id) throws PortalException, SystemException {
		DmPort dmPort = persistence.remove(id);




	}

	/**
	 * Deletes the dm port from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmPort the dm port
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmPort(DmPort dmPort) throws SystemException {
		persistence.remove(dmPort);




	}




	public DmPort fetchDmPort(int id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm port with the primary key.
	 *
	 * @param id the primary key of the dm port
	 * @return the dm port
	 * @throws PortalException if a dm port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort getDmPort(int id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	/**
	 * Returns a range of all the dm ports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm ports
	 * @param end the upper bound of the range of dm ports (not inclusive)
	 * @return the range of dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> getDmPorts(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm ports.
	 *
	 * @return the number of dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmPortsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm port in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmPort the dm port
	 * @return the dm port that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort updateDmPort(DmPort dmPort) throws SystemException {
		return updateDmPort(dmPort, true);
	}

	/**
	 * Updates the dm port in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmPort the dm port
	 * @param merge whether to merge the dm port with the current session. See  for an explanation.
	 * @return the dm port that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort updateDmPort(DmPort dmPort, boolean merge)
			throws SystemException {

		dmPort = persistence.updateImpl(dmPort, merge);





		return dmPort;
	}
}