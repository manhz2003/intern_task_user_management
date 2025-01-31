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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmSecurityLevelPersistenceImpl;
import com.fds.nsw.nghiepvu.model.DmSecurityLevel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the dm security level local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.danhmuc.service.DmSecurityLevelLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmSecurityLevelLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmSecurityLevelLocalServiceUtil
 */
public class DmSecurityLevelLocalServiceImpl {
	@Autowired
	DmSecurityLevelPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * vn.gt.dao.danhmuc.service.DmSecurityLevelLocalServiceUtil} to access the dm
	 * security level local service.
	 */

	public List<DmSecurityLevel> findBySecurityLevel(String securityLevel, int start, int end){
		try{
			return persistence.findByF_securityLevel(securityLevel, start, end);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<DmSecurityLevel> findBySecurityLevelName(String securityLevelName, int start, int end){
		try{
			return persistence.findByF_securityLevelName(securityLevelName, start, end);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


	public DmSecurityLevel getBySecurityLevelCode(String securityLevelCode) {
		try {
			List<DmSecurityLevel> dmSecurityLeveles = persistence.findBySecurityLevelCode(securityLevelCode);
			if (dmSecurityLeveles != null && dmSecurityLeveles.size() > 0) {
				return dmSecurityLeveles.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.danhmuc.service.DmSecurityLevelLocalServiceUtil} to access the dm
	 * security level local service.
	 */

	/**
	 * Adds the dm security level to the database. Also notifies the appropriate
	 * model listeners.
	 *
	 * @param dmSecurityLevel the dm security level
	 * @return the dm security level that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel addDmSecurityLevel(DmSecurityLevel dmSecurityLevel) throws SystemException {

		dmSecurityLevel = persistence.updateImpl(dmSecurityLevel, false);

		return dmSecurityLevel;
	}

	/**
	 * Creates a new dm security level with the primary key. Does not add the dm
	 * security level to the database.
	 *
	 * @param id the primary key for the new dm security level
	 * @return the new dm security level
	 */
	public DmSecurityLevel createDmSecurityLevel(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm security level with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm security level
	 * @throws PortalException if a dm security level with the primary key could not
	 *                         be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmSecurityLevel(int id) throws PortalException, SystemException {
		DmSecurityLevel dmSecurityLevel = persistence.remove(id);

	}

	/**
	 * Deletes the dm security level from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param dmSecurityLevel the dm security level
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmSecurityLevel(DmSecurityLevel dmSecurityLevel) throws SystemException {
		persistence.remove(dmSecurityLevel);

	}

	public DmSecurityLevel fetchDmSecurityLevel(int id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm security level with the primary key.
	 *
	 * @param id the primary key of the dm security level
	 * @return the dm security level
	 * @throws PortalException if a dm security level with the primary key could not
	 *                         be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel getDmSecurityLevel(int id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public DmSecurityLevel getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the dm security levels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm security levels
	 * @param end   the upper bound of the range of dm security levels (not
	 *              inclusive)
	 * @return the range of dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSecurityLevel> getDmSecurityLevels(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm security levels.
	 *
	 * @return the number of dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmSecurityLevelsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm security level in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmSecurityLevel the dm security level
	 * @return the dm security level that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel updateDmSecurityLevel(DmSecurityLevel dmSecurityLevel) throws SystemException {
		return updateDmSecurityLevel(dmSecurityLevel, true);
	}

	/**
	 * Updates the dm security level in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmSecurityLevel the dm security level
	 * @param merge           whether to merge the dm security level with the
	 *                        current session. See
	 *                        
	 *                        for an explanation.
	 * @return the dm security level that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel updateDmSecurityLevel(DmSecurityLevel dmSecurityLevel, boolean merge)
			throws SystemException {

		dmSecurityLevel = persistence.updateImpl(dmSecurityLevel, merge);

		return dmSecurityLevel;
	}
}