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

import java.util.List;import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmArrivalPurposePersistenceImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmArrivalPurposeFinderImpl;
import com.fds.nsw.nghiepvu.model.DmArrivalPurpose;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the dm arrival purpose local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.danhmuc.service.DmArrivalPurposeLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmArrivalPurposeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmArrivalPurposeLocalServiceUtil
 */
public class DmArrivalPurposeLocalServiceImpl {
	@Autowired
	DmArrivalPurposePersistenceImpl persistence;

	@Autowired
	DmArrivalPurposeFinderImpl finder;


	public DmArrivalPurpose addDmArrivalPurpose(DmArrivalPurpose dmArrivalPurpose) throws SystemException {

		dmArrivalPurpose = persistence.updateImpl(dmArrivalPurpose, false);

		return dmArrivalPurpose;
	}

	/**
	 * Creates a new dm arrival purpose with the primary key. Does not add the dm
	 * arrival purpose to the database.
	 *
	 * @param id the primary key for the new dm arrival purpose
	 * @return the new dm arrival purpose
	 */
	public DmArrivalPurpose createDmArrivalPurpose(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm arrival purpose from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param dmArrivalPurpose the dm arrival purpose
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmArrivalPurpose(DmArrivalPurpose dmArrivalPurpose) throws SystemException {
		persistence.remove(dmArrivalPurpose);

	}

	public List<DmArrivalPurpose> findByPurposeName(String purposeName,
													int start, int end) {
		try {
			return persistence.findByF_purposeName(purposeName,
					start, end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<DmArrivalPurpose> findArrivalPurposes(String purposeName,
													  String isDelete, String purposeCodeGroup, int start, int end) {
		try {
			return finder.findArrivalPurposes(purposeName,
					isDelete, purposeCodeGroup, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public long countArrivalPurposes(String purposeName, String isDelete,
									 String purposeCodeGroup) {
		try {
			return finder.countArrivalPurposes(purposeName,
					isDelete, purposeCodeGroup);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}


	public void deleteDmArrivalPurpose(int id) throws PortalException, SystemException {
		DmArrivalPurpose dmArrivalPurpose = persistence.remove(id);

	}

	public DmArrivalPurpose fetchDmArrivalPurpose(int id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public DmArrivalPurpose getByPortCode(String purposeCode) {
		try {
			List<DmArrivalPurpose> lstArrivalPurposes = persistence.findByPurposeCode(purposeCode);
			if (lstArrivalPurposes != null && lstArrivalPurposes.size() > 0) {
				return lstArrivalPurposes.get(0);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns the dm arrival purpose with the primary key.
	 *
	 * @param id the primary key of the dm arrival purpose
	 * @return the dm arrival purpose
	 * @throws PortalException if a dm arrival purpose with the primary key could
	 *                         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose getDmArrivalPurpose(int id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	/**
	 * Returns a range of all the dm arrival purposes.
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
	 * @param start the lower bound of the range of dm arrival purposes
	 * @param end   the upper bound of the range of dm arrival purposes (not
	 *              inclusive)
	 * @return the range of dm arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmArrivalPurpose> getDmArrivalPurposes(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm arrival purposes.
	 *
	 * @return the number of dm arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmArrivalPurposesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm arrival purpose in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmArrivalPurpose the dm arrival purpose
	 * @return the dm arrival purpose that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose updateDmArrivalPurpose(DmArrivalPurpose dmArrivalPurpose) throws SystemException {
		return updateDmArrivalPurpose(dmArrivalPurpose, true);
	}

	/**
	 * Updates the dm arrival purpose in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmArrivalPurpose the dm arrival purpose
	 * @param merge            whether to merge the dm arrival purpose with the
	 *                         current session. See
	 *                         
	 *                         for an explanation.
	 * @return the dm arrival purpose that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose updateDmArrivalPurpose(DmArrivalPurpose dmArrivalPurpose, boolean merge)
			throws SystemException {

		dmArrivalPurpose = persistence.updateImpl(dmArrivalPurpose, merge);

		return dmArrivalPurpose;
	}
}