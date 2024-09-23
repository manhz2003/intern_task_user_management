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

package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempShipSecurityMessageFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempShipSecurityMessagePersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service








/**
 * The implementation of the temp ship security message local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can
 * only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempShipSecurityMessageLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalServiceUtil
 */
public class TempShipSecurityMessageLocalServiceImpl { @Autowired
TempShipSecurityMessagePersistenceImpl persistence;@Autowired
TempShipSecurityMessageFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalServiceUtil} to access the temp ship security message local service.
	 */
	public List<TempShipSecurityMessage> findBydocumentNameAnddocumentYearRequestState(long documentName, int documentYear, int requestState) {
		try {
			return persistence.findBydocumentNameAnddocumentYearRequestState(documentName, documentYear, requestState);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int countBydocumentNameAnddocumentYear(long documentName, int documentYear) throws SystemException {
		return persistence.countBydocumentNameAnddocumentYear(documentName, documentYear);
	}
	
	public List<TempShipSecurityMessage> findByRequestCode(java.lang.String requestCode) throws SystemException {
		return persistence.findByRequestCode(requestCode);
	}
	
	public TempShipSecurityMessage getByRequestCode(String requestCode) throws SystemException {
		return finder.getByRequestCode(requestCode);
	}
	
	public List<TempShipSecurityMessage> findBydocumentNameAndDocumentYear(long documentName, int documentYear, int start, int end)
			throws SystemException {
		return persistence.findBydocumentNameAnddocumentYear(documentName, documentYear, start, end);
	}
	
	public List<TempShipSecurityMessage> findBydocumentNameAnddocumentYear(long documentName, int documentYear) throws SystemException {
		return finder.findBydocumentNameAnddocumentYear(documentName, documentYear);
	}
	
	public TempShipSecurityMessage getLastByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TempShipSecurityMessage> findByDocumentNameAndDocumentYearOrderByDescRequestDate(long documentName, int documentYear) {
		return finder.findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName, documentYear);
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalServiceUtil} to access the temp ship security message local service.
	 */

	/**
	 * Adds the temp ship security message to the database. Also notifies the appropriate model listeners.
	 *
	 * @param tempShipSecurityMessage the temp ship security message
	 * @return the temp ship security message that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityMessage addTempShipSecurityMessage(
			TempShipSecurityMessage tempShipSecurityMessage)
			throws SystemException {

		tempShipSecurityMessage = persistence.updateImpl(tempShipSecurityMessage,
				false);





		return tempShipSecurityMessage;
	}

	/**
	 * Creates a new temp ship security message with the primary key. Does not add the temp ship security message to the database.
	 *
	 * @param id the primary key for the new temp ship security message
	 * @return the new temp ship security message
	 */
	public TempShipSecurityMessage createTempShipSecurityMessage(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp ship security message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp ship security message
	 * @throws PortalException if a temp ship security message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempShipSecurityMessage(long id)
			throws PortalException, SystemException {
		TempShipSecurityMessage tempShipSecurityMessage = persistence.remove(id);




	}

	/**
	 * Deletes the temp ship security message from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tempShipSecurityMessage the temp ship security message
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempShipSecurityMessage(
			TempShipSecurityMessage tempShipSecurityMessage)
			throws SystemException {
		persistence.remove(tempShipSecurityMessage);


	}













	public TempShipSecurityMessage fetchTempShipSecurityMessage(long id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp ship security message with the primary key.
	 *
	 * @param id the primary key of the temp ship security message
	 * @return the temp ship security message
	 * @throws PortalException if a temp ship security message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityMessage getTempShipSecurityMessage(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	/**
	 * Returns a range of all the temp ship security messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp ship security messages
	 * @param end the upper bound of the range of temp ship security messages (not inclusive)
	 * @return the range of temp ship security messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempShipSecurityMessage> getTempShipSecurityMessages(
			int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp ship security messages.
	 *
	 * @return the number of temp ship security messages
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempShipSecurityMessagesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp ship security message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempShipSecurityMessage the temp ship security message
	 * @return the temp ship security message that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityMessage updateTempShipSecurityMessage(
			TempShipSecurityMessage tempShipSecurityMessage)
			throws SystemException {
		return updateTempShipSecurityMessage(tempShipSecurityMessage, true);
	}

	/**
	 * Updates the temp ship security message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempShipSecurityMessage the temp ship security message
	 * @param merge whether to merge the temp ship security message with the current session. See  for an explanation.
	 * @return the temp ship security message that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityMessage updateTempShipSecurityMessage(
			TempShipSecurityMessage tempShipSecurityMessage, boolean merge)
			throws SystemException {

		tempShipSecurityMessage = persistence.updateImpl(tempShipSecurityMessage,
				merge);





		return tempShipSecurityMessage;
	}
}