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

package com.fds.nsw.nghiepvu.danhmucgt.service.impl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmucgt.service.persistence.DmMinistryPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service




/**
 * The implementation of the dm ministry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmucgt.service.DmMinistryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmucgt.service.base.DmMinistryLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.DmMinistryLocalServiceUtil
 */
public class DmMinistryLocalServiceImpl { @Autowired
DmMinistryPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmucgt.service.DmMinistryLocalServiceUtil} to access the dm ministry local service.
	 */
	
	public DmMinistry findByMinistryCode(
			java.lang.String ministryCode){
		try{
			
			return persistence.findByMinistryCode(ministryCode);
		}catch (Exception e) {
			log.error("Not found ministry with ministryCode: " + ministryCode);
//			e.printStackTrace();
		}
		return null;
	}

	/**
	* Returns the number of dm ministries where ministryCode = &#63;.
	*
	* @param ministryCode the ministry code
	* @return the number of matching dm ministries
	* @throws SystemException if a system exception occurred
	*/
	public int countByMinistryCode(java.lang.String ministryCode){
		try{
			return persistence.countByMinistryCode(ministryCode);
		}catch (Exception e) {
			log.error("Not found countByMinistryCode with ministryCode: " + ministryCode);
		}
		return 0;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmucgt.service.DmMinistryLocalServiceUtil} to access the dm ministry local service.
	 */

	/**
	 * Adds the dm ministry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmMinistry the dm ministry
	 * @return the dm ministry that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmMinistry addDmMinistry(DmMinistry dmMinistry)
			throws SystemException {

		dmMinistry = persistence.updateImpl(dmMinistry, false);





		return dmMinistry;
	}

	/**
	 * Creates a new dm ministry with the primary key. Does not add the dm ministry to the database.
	 *
	 * @param id the primary key for the new dm ministry
	 * @return the new dm ministry
	 */
	public DmMinistry createDmMinistry(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm ministry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm ministry
	 * @throws PortalException if a dm ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmMinistry(long id)
			throws PortalException, SystemException {
		DmMinistry dmMinistry = persistence.remove(id);




	}

	/**
	 * Deletes the dm ministry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmMinistry the dm ministry
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmMinistry(DmMinistry dmMinistry)
			throws SystemException {
		persistence.remove(dmMinistry);




	}













	public DmMinistry fetchDmMinistry(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm ministry with the primary key.
	 *
	 * @param id the primary key of the dm ministry
	 * @return the dm ministry
	 * @throws PortalException if a dm ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMinistry getDmMinistry(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	/**
	 * Returns a range of all the dm ministries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm ministries
	 * @param end the upper bound of the range of dm ministries (not inclusive)
	 * @return the range of dm ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmMinistry> getDmMinistries(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm ministries.
	 *
	 * @return the number of dm ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmMinistriesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmMinistry the dm ministry
	 * @return the dm ministry that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmMinistry updateDmMinistry(DmMinistry dmMinistry)
			throws SystemException {
		return updateDmMinistry(dmMinistry, true);
	}

	/**
	 * Updates the dm ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmMinistry the dm ministry
	 * @param merge whether to merge the dm ministry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the dm ministry that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmMinistry updateDmMinistry(DmMinistry dmMinistry, boolean merge)
			throws SystemException {

		dmMinistry = persistence.updateImpl(dmMinistry, merge);





		return dmMinistry;
	}
	
}