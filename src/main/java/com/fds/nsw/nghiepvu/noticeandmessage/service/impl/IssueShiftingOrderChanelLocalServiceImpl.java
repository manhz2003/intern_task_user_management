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
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.IssueShiftingOrderChanelPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the issue shifting order chanel local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.IssueShiftingOrderChanelLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.IssueShiftingOrderChanelLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.IssueShiftingOrderChanelLocalServiceUtil
 */
public class IssueShiftingOrderChanelLocalServiceImpl {
	@Autowired
	IssueShiftingOrderChanelPersistenceImpl persistence;
	
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.IssueShiftingOrderChanelLocalServiceUtil}
	 * to access the issue shifting order chanel local service.
	 */

	public List<IssueShiftingOrderChanel> findByShiftingOrderId(long shiftingOrderId) {
		try {
			return persistence.findByShiftingOrderId(shiftingOrderId);
		} catch (SystemException e) {
			return new ArrayList<IssueShiftingOrderChanel>();
		}
	}

	public List<IssueShiftingOrderChanel> getShiftingOrderChanel(long shiftingOrderId) throws SystemException {
		return persistence.findByShiftingOrderId(shiftingOrderId);
	}

	public IssueShiftingOrderChanel getShiftingOrderChanel(long shiftingOrderId, String chanelCode)
			throws SystemException, PortalException {
		
		IssueShiftingOrderChanelId issueShiftingOrderChanelId = new IssueShiftingOrderChanelId(shiftingOrderId, chanelCode);
		
		return persistence.findByPrimaryKey(issueShiftingOrderChanelId);
	}

	public IssueShiftingOrderChanel addShiftingOrderChanel(long shiftingOrderId, String chanelCode, String chanelName,
			int order) throws SystemException, PortalException {
		IssueShiftingOrderChanelId issueShiftingOrderChanelId = new IssueShiftingOrderChanelId(shiftingOrderId, chanelCode);

		IssueShiftingOrderChanel issueShiftingOrderChanel = persistence
				.fetchByPrimaryKey(issueShiftingOrderChanelId);

		if (issueShiftingOrderChanel == null) {
			issueShiftingOrderChanel = persistence.create(issueShiftingOrderChanelId);
			issueShiftingOrderChanel.setChanelName(chanelName);
			issueShiftingOrderChanel.setOrder(order);
		} else {
			issueShiftingOrderChanel.setChanelName(chanelName);
			issueShiftingOrderChanel.setOrder(order);
		}

		return persistence.updateImpl(issueShiftingOrderChanel, false);
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.IssueShiftingOrderChanelLocalServiceUtil}
	 * to access the issue shifting order chanel local service.
	 */

	/**
	 * Adds the issue shifting order chanel to the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param issueShiftingOrderChanel the issue shifting order chanel
	 * @return the issue shifting order chanel that was added
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrderChanel addIssueShiftingOrderChanel(IssueShiftingOrderChanel issueShiftingOrderChanel)
			throws SystemException {
		

		issueShiftingOrderChanel = persistence.updateImpl(issueShiftingOrderChanel, false);

		return issueShiftingOrderChanel;
	}

	/**
	 * Creates a new issue shifting order chanel with the primary key. Does not add
	 * the issue shifting order chanel to the database.
	 *
	 * @param issueShiftingOrderChanelPK the primary key for the new issue shifting
	 *                                   order chanel
	 * @return the new issue shifting order chanel
	 */
	public IssueShiftingOrderChanel createIssueShiftingOrderChanel(
			IssueShiftingOrderChanelId issueShiftingOrderChanelId) {
		return persistence.create(issueShiftingOrderChanelId);
	}

	/**
	 * Deletes the issue shifting order chanel with the primary key from the
	 * database. Also notifies the appropriate model listeners.
	 *
	 * @param issueShiftingOrderChanelPK the primary key of the issue shifting order
	 *                                   chanel
	 * @throws PortalException if a issue shifting order chanel with the primary key
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteIssueShiftingOrderChanel(IssueShiftingOrderChanelId issueShiftingOrderChanelId)
			throws PortalException, SystemException {
		IssueShiftingOrderChanel issueShiftingOrderChanel = persistence
				.remove(issueShiftingOrderChanelId);

	}

	/**
	 * Deletes the issue shifting order chanel from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param issueShiftingOrderChanel the issue shifting order chanel
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteIssueShiftingOrderChanel(IssueShiftingOrderChanel issueShiftingOrderChanel)
			throws SystemException {
		persistence.remove(issueShiftingOrderChanel);

	}

	public IssueShiftingOrderChanel fetchIssueShiftingOrderChanel(IssueShiftingOrderChanelId issueShiftingOrderChanelId)
			throws SystemException {
		return persistence.fetchByPrimaryKey(issueShiftingOrderChanelId);
	}

	/**
	 * Returns the issue shifting order chanel with the primary key.
	 *
	 * @param issueShiftingOrderChanelPK the primary key of the issue shifting order
	 *                                   chanel
	 * @return the issue shifting order chanel
	 * @throws PortalException if a issue shifting order chanel with the primary key
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrderChanel getIssueShiftingOrderChanel(IssueShiftingOrderChanelId issueShiftingOrderChanelId)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(issueShiftingOrderChanelId);
	}

	public IssueShiftingOrderChanel getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the issue shifting order chanels.
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
	 * @param start the lower bound of the range of issue shifting order chanels
	 * @param end   the upper bound of the range of issue shifting order chanels
	 *              (not inclusive)
	 * @return the range of issue shifting order chanels
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrderChanel> getIssueShiftingOrderChanels(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of issue shifting order chanels.
	 *
	 * @return the number of issue shifting order chanels
	 * @throws SystemException if a system exception occurred
	 */
	public int getIssueShiftingOrderChanelsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the issue shifting order chanel in the database or adds it if it does
	 * not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param issueShiftingOrderChanel the issue shifting order chanel
	 * @return the issue shifting order chanel that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrderChanel updateIssueShiftingOrderChanel(IssueShiftingOrderChanel issueShiftingOrderChanel)
			throws SystemException {
		return updateIssueShiftingOrderChanel(issueShiftingOrderChanel, true);
	}

	/**
	 * Updates the issue shifting order chanel in the database or adds it if it does
	 * not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param issueShiftingOrderChanel the issue shifting order chanel
	 * @param merge                    whether to merge the issue shifting order
	 *                                 chanel with the current session. See
	 *                                 
	 *                                 for an explanation.
	 * @return the issue shifting order chanel that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrderChanel updateIssueShiftingOrderChanel(IssueShiftingOrderChanel issueShiftingOrderChanel,
			boolean merge) throws SystemException {
		

		issueShiftingOrderChanel = persistence.updateImpl(issueShiftingOrderChanel, merge);

		return issueShiftingOrderChanel;
	}
}