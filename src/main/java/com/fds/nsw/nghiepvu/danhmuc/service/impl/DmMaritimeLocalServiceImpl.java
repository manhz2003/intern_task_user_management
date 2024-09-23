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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;import java.util.ArrayList; import java.util.List;import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.util.OrderByComparator;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmMaritimeFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmMaritimePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service












/**
 * The implementation of the dm maritime local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.danhmuc.service.DmMaritimeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmMaritimeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil
 */
public class DmMaritimeLocalServiceImpl { @Autowired
DmMaritimePersistenceImpl persistence;
	@Autowired
DmMaritimeFinderImpl finder;

	/**
	 * Returns all the dm maritimes.
	 *
	 * @return the dm maritimes
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public java.util.List<DmMaritime> findAll() {
		try {
			return persistence.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<DmMaritime> findMaritimes(String maritimeCode, String isDelete, int start, int end){
		try{
			return finder.findMaritimes(maritimeCode, isDelete, start, end);
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}

	public int countMaritimes(String maritimeCode, String isDelete) {
		try {
			return finder.countMaritimes(maritimeCode, isDelete);
		} catch (SystemException e) {
			log.error(e.getMessage());
			return 0;
		}
	}


	/**
	 * Returns a range of all the dm maritimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of
	 * <code>end - start</code> instances. <code>start</code> and
	 * <code>end</code> are not primary keys, they are indexes in the result
	 * set. Thus, <code>0</code> refers to the first result in the set. Setting
	 * both <code>start</code> and <code>end</code> to
	 *  will return
	 * the full result set.
	 * </p>
	 *
	 * @param start
	 *            the lower bound of the range of dm maritimes
	 * @param end
	 *            the upper bound of the range of dm maritimes (not inclusive)
	 * @return the range of dm maritimes
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public List<DmMaritime> findAll(int start, int end) {
		try {
			return persistence.findAll(start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public DmMaritime getByMaritimeCode(String maritimeCode) {
		try {
			return persistence.fetchByMaritimeCode(maritimeCode);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return null;
	}
	
	
	public DmMaritime getByMaritimeReference(String maritimeReference) {
		try {
			return persistence.fetchByMaritimeReference(maritimeReference);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return null;
	}
	/**
	 * Returns the number of dm maritimes where maritimeCode = &#63;.
	 *
	 * @param maritimeCode
	 *            the maritime code
	 * @return the number of matching dm maritimes
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public int countByMaritimeCode(java.lang.String maritimeCode) {
		try {
			return persistence.countByMaritimeCode(maritimeCode);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}

	public List<DmMaritime> findRangeOrderBy(int start, int end, OrderByComparator orderByComparator) {
		try {
			return persistence.findAll(start, end, orderByComparator);
		} catch (SystemException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<DmMaritime> getAll() {
		try {
			return persistence.findByDelete(0, 0);
		} catch (SystemException e) {
			log.error(e.getMessage());
		}
		
		return new ArrayList<DmMaritime>();
	}
	
	public DmMaritime getFirstMaritime() {
		try {
			return finder.getFirstMaritime();
		} catch (SystemException e) {
			log.error(e.getMessage());
		}
		
		return null;
	}
	
	public DmMaritime getLastMaritime() {
		try {
			return finder.getLastMaritime();
		} catch (SystemException e) {
			log.error(e.getMessage());
		}
		
		return null;
	}
	
	public List<DmMaritime> findDanhSachDmMaritime(String maritimeCode, int start, int end) {
		try {
			return finder.findDanhSachDmMaritime(maritimeCode, start, end);
		} catch (SystemException e) {
			log.error(e.getMessage());
		}
		
		return new ArrayList<DmMaritime>();
	}
	
	public int countDanhSachDmMaritime(String maritimeCode, int start, int end) {
		try {
			return finder.countDanhSachDmMaritime(maritimeCode, start, end);
		} catch (SystemException e) {
			log.error(e.getMessage());
		}
		
		return 0;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil} to access the dm maritime local service.
	 */

	/**
	 * Adds the dm maritime to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmMaritime the dm maritime
	 * @return the dm maritime that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime addDmMaritime(DmMaritime dmMaritime)
			throws SystemException {

		dmMaritime = persistence.updateImpl(dmMaritime, false);





		return dmMaritime;
	}

	/**
	 * Creates a new dm maritime with the primary key. Does not add the dm maritime to the database.
	 *
	 * @param id the primary key for the new dm maritime
	 * @return the new dm maritime
	 */
	public DmMaritime createDmMaritime(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm maritime with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm maritime
	 * @throws PortalException if a dm maritime with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmMaritime(int id)
			throws PortalException, SystemException {
		DmMaritime dmMaritime = persistence.remove(id);




	}

	/**
	 * Deletes the dm maritime from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmMaritime the dm maritime
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmMaritime(DmMaritime dmMaritime)
			throws SystemException {
		persistence.remove(dmMaritime);




	}













	public DmMaritime fetchDmMaritime(int id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm maritime with the primary key.
	 *
	 * @param id the primary key of the dm maritime
	 * @return the dm maritime
	 * @throws PortalException if a dm maritime with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime getDmMaritime(int id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}



	/**
	 * Returns a range of all the dm maritimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm maritimes
	 * @param end the upper bound of the range of dm maritimes (not inclusive)
	 * @return the range of dm maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmMaritime> getDmMaritimes(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm maritimes.
	 *
	 * @return the number of dm maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmMaritimesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm maritime in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmMaritime the dm maritime
	 * @return the dm maritime that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime updateDmMaritime(DmMaritime dmMaritime)
			throws SystemException {
		return updateDmMaritime(dmMaritime, true);
	}

	/**
	 * Updates the dm maritime in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmMaritime the dm maritime
	 * @param merge whether to merge the dm maritime with the current session. See  for an explanation.
	 * @return the dm maritime that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime updateDmMaritime(DmMaritime dmMaritime, boolean merge)
			throws SystemException {

		dmMaritime = persistence.updateImpl(dmMaritime, merge);





		return dmMaritime;
	}


}