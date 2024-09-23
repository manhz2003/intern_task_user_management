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

package com.fds.nsw.nghiepvu.noticeandmessage.service;

import org.springframework.stereotype.Component;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.nghiepvu.model.Modify;
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.ModifyLocalServiceImpl;

/**
 * The utility for the modify local service. This utility wraps
 * {@link vn.gt.dao.noticeandmessage.service.impl.ModifyLocalServiceImpl} and is
 * the primary access point for service operations in application layer code
 * running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see ModifyLocalService
 * @see vn.gt.dao.noticeandmessage.service.ModifyLocalServiceImpl.ModifyLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.ModifyLocalServiceImpl
 * @generated
 */
@Component
public class ModifyLocalServiceUtil {
	public ModifyLocalServiceUtil(ModifyLocalServiceImpl service) {
		ModifyLocalServiceUtil._service = service;
	}

	/**
	 * Adds the modify to the database. Also notifies the appropriate model
	 * listeners.
	 *
	 * @param modify the modify
	 * @return the modify that was added
	 * @throws SystemException if a system exception occurred
	 */
	public static Modify addModify(Modify modify) throws SystemException {
		return getService().addModify(modify);
	}

	/**
	 * Creates a new modify with the primary key. Does not add the modify to the
	 * database.
	 *
	 * @param id the primary key for the new modify
	 * @return the new modify
	 */
	public static Modify createModify(long id) {
		return getService().createModify(id);
	}

	/**
	 * Deletes the modify with the primary key from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param id the primary key of the modify
	 * @throws PortalException if a modify with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static void deleteModify(long id) throws PortalException, SystemException {
		getService().deleteModify(id);
	}

	/**
	 * Deletes the modify from the database. Also notifies the appropriate model
	 * listeners.
	 *
	 * @param modify the modify
	 * @throws SystemException if a system exception occurred
	 */
	public static void deleteModify(Modify modify) throws SystemException {
		getService().deleteModify(modify);
	}

	public static Modify fetchModify(long id) throws SystemException {
		return getService().fetchModify(id);
	}

	/**
	 * Returns the modify with the primary key.
	 *
	 * @param id the primary key of the modify
	 * @return the modify
	 * @throws PortalException if a modify with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static Modify getModify(long id) throws PortalException, SystemException {
		return getService().getModify(id);
	}

	/**
	 * Returns a range of all the modifies.
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
	 * @param start the lower bound of the range of modifies
	 * @param end   the upper bound of the range of modifies (not inclusive)
	 * @return the range of modifies
	 * @throws SystemException if a system exception occurred
	 */
	public static java.util.List<Modify> getModifies(int start, int end) throws SystemException {
		return getService().getModifies(start, end);
	}

	/**
	 * Returns the number of modifies.
	 *
	 * @return the number of modifies
	 * @throws SystemException if a system exception occurred
	 */
	public static int getModifiesCount() throws SystemException {
		return getService().getModifiesCount();
	}

	/**
	 * Updates the modify in the database or adds it if it does not yet exist. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param modify the modify
	 * @return the modify that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public static Modify updateModify(Modify modify) throws SystemException {
		return getService().updateModify(modify);
	}

	/**
	 * Updates the modify in the database or adds it if it does not yet exist. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param modify the modify
	 * @param merge  whether to merge the modify with the current session. See
	 *               {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)}
	 *               for an explanation.
	 * @return the modify that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public static Modify updateModify(Modify modify, boolean merge) throws SystemException {
		return getService().updateModify(modify, merge);
	}

	public static ModifyLocalServiceImpl getService() {

		return _service;
	}

	private static ModifyLocalServiceImpl _service;
}