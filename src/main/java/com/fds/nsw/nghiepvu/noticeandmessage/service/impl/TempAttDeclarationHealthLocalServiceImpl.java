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

package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempAttachmentDeclarationHealthPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the temp attachment declaration health local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempAttDeclarationHealthLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempAttDeclarationHealthLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempAttDeclarationHealthLocalServiceUtil
 */
public class TempAttDeclarationHealthLocalServiceImpl {
	@Autowired
	TempAttachmentDeclarationHealthPersistenceImpl persistence;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.
	 * TempAttDeclarationHealthLocalServiceUtil} to access the temp
	 * attachment declaration health local service.
	 */

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.
	 * TempAttDeclarationHealthLocalServiceUtil} to access the temp
	 * attachment declaration health local service.
	 */

	/**
	 * Adds the temp attachment declaration health to the database. Also notifies
	 * the appropriate model listeners.
	 *
	 * @param tempAttachmentDeclarationHealth the temp attachment declaration health
	 * @return the temp attachment declaration health that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempAttDeclarationHealth addTempAttDeclarationHealth(
			TempAttDeclarationHealth tempAttachmentDeclarationHealth) throws SystemException {
		

		tempAttachmentDeclarationHealth = persistence
				.updateImpl(tempAttachmentDeclarationHealth, false);

		return tempAttachmentDeclarationHealth;
	}

	/**
	 * Creates a new temp attachment declaration health with the primary key. Does
	 * not add the temp attachment declaration health to the database.
	 *
	 * @param id the primary key for the new temp attachment declaration health
	 * @return the new temp attachment declaration health
	 */
	public TempAttDeclarationHealth createTempAttDeclarationHealth(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp attachment declaration health with the primary key from the
	 * database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp attachment declaration health
	 * @throws PortalException if a temp attachment declaration health with the
	 *                         primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempAttDeclarationHealth(long id) throws PortalException, SystemException {
		TempAttDeclarationHealth tempAttachmentDeclarationHealth = persistence
				.remove(id);

	}

	/**
	 * Deletes the temp attachment declaration health from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param tempAttachmentDeclarationHealth the temp attachment declaration health
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempAttDeclarationHealth(TempAttDeclarationHealth tempAttachmentDeclarationHealth)
			throws SystemException {
		persistence.remove(tempAttachmentDeclarationHealth);

	}

	public TempAttDeclarationHealth fetchTempAttDeclarationHealth(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp attachment declaration health with the primary key.
	 *
	 * @param id the primary key of the temp attachment declaration health
	 * @return the temp attachment declaration health
	 * @throws PortalException if a temp attachment declaration health with the
	 *                         primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempAttDeclarationHealth getTempAttDeclarationHealth(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public TempAttDeclarationHealth getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the temp attachment declaration healths.
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
	 * @param start the lower bound of the range of temp attachment declaration
	 *              healths
	 * @param end   the upper bound of the range of temp attachment declaration
	 *              healths (not inclusive)
	 * @return the range of temp attachment declaration healths
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempAttDeclarationHealth> getTempAttDeclarationHealths(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp attachment declaration healths.
	 *
	 * @return the number of temp attachment declaration healths
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempAttDeclarationHealthsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp attachment declaration health in the database or adds it if
	 * it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempAttachmentDeclarationHealth the temp attachment declaration health
	 * @return the temp attachment declaration health that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempAttDeclarationHealth updateTempAttDeclarationHealth(
			TempAttDeclarationHealth tempAttachmentDeclarationHealth) throws SystemException {
		return updateTempAttDeclarationHealth(tempAttachmentDeclarationHealth, true);
	}

	/**
	 * Updates the temp attachment declaration health in the database or adds it if
	 * it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempAttachmentDeclarationHealth the temp attachment declaration health
	 * @param merge                           whether to merge the temp attachment
	 *                                        declaration health with the current
	 *                                        session. See
	 *                                        
	 *                                        for an explanation.
	 * @return the temp attachment declaration health that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempAttDeclarationHealth updateTempAttDeclarationHealth(
			TempAttDeclarationHealth tempAttachmentDeclarationHealth, boolean merge) throws SystemException {
		

		tempAttachmentDeclarationHealth = persistence
				.updateImpl(tempAttachmentDeclarationHealth, merge);

		return tempAttachmentDeclarationHealth;
	}
}