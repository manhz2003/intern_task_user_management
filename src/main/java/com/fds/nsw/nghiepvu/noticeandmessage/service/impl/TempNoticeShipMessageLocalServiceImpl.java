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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempNoTiceShipMessageFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempNoticeShipMessagePersistenceImpl;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service








/**
 * The implementation of the temp no tice ship message local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempNoticeShipMessageLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can
 * only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempNoticeShipMessageLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.tempNoticeShipMessageLocalService
 */
public class TempNoticeShipMessageLocalServiceImpl { @Autowired
TempNoticeShipMessagePersistenceImpl persistence;@Autowired
TempNoTiceShipMessageFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.tempNoticeShipMessageLocalService} to access the temp no tice ship message local service.
	 */
	
	public int countBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		try {
			return persistence.countBydocumentNameAnddocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<TempNoticeShipMessage> findByRequestCode(String requestCode) {
		try {
			return persistence.findByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TempNoticeShipMessage> findBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		try {
			return persistence.findBydocumentNameAnddocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public TempNoticeShipMessage findTempNoticeShipMessageByRequestCode(String requestCode) {
		try {
			return finder.findTempNoticeShipMessageByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public TempNoticeShipMessage findTempNoticeShipMessageXbByRequestCode(String requestCode) {
		try {
			return finder.findTempNoticeShipMessageXbByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public TempNoticeShipMessage findTempNoticeShipMessageTbByRequestCode(String requestCode) {
		try {
			return finder.findTempNoticeShipMessageTbByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TempNoticeShipMessage> findBydocumentNameAndDocumentYearAndNoticeShipType(long documentName, int documentYear, String noticeShipType)
			throws SystemException {
		return finder.findBydocumentNameAndDocumentYearAndNoticeShipType(documentName, documentYear, noticeShipType);
	}
	
	public List<TempNoticeShipMessage> findByDocumentNameAndDocumentYearAndNoticeShipTypeOrderByColumn(long documentName, int documentYear,
			String noticeShipType, String orderByColumn, boolean ascOrdesc) throws SystemException {
		return finder.findByDocumentNameAndDocumentYearAndNoticeShipTypeOrderByColumn(documentName, documentYear,
				noticeShipType, orderByColumn, ascOrdesc);
	}
	
	public TempNoticeShipMessage getXacBaoLastByDocumentNameAndDocumentYear(long documentName, long documentYear) {
		try {
			return finder.getXacBaoLastByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public TempNoticeShipMessage getThongBaoLastByDocumentNameAndDocumentYear(long documentName, long documentYear) {
		try {
			return finder.getThongBaoLastByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.noticeandmessage.service.tempNoticeShipMessageLocalService} to access the temp no tice ship message local service.
	 */

	/**
	 * Adds the temp no tice ship message to the database. Also notifies the appropriate model listeners.
	 *
	 * @param TempNoticeShipMessage the temp no tice ship message
	 * @return the temp no tice ship message that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage addTempNoticeShipMessage(
			TempNoticeShipMessage TempNoticeShipMessage) throws SystemException {

		TempNoticeShipMessage = persistence.updateImpl(TempNoticeShipMessage,
				false);





		return TempNoticeShipMessage;
	}

	/**
	 * Creates a new temp no tice ship message with the primary key. Does not add the temp no tice ship message to the database.
	 *
	 * @param id the primary key for the new temp no tice ship message
	 * @return the new temp no tice ship message
	 */
	public TempNoticeShipMessage createTempNoticeShipMessage(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp no tice ship message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp no tice ship message
	 * @throws PortalException if a temp no tice ship message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempNoticeShipMessage(long id)
			throws PortalException, SystemException {
		TempNoticeShipMessage TempNoticeShipMessage = persistence.remove(id);




	}

	/**
	 * Deletes the temp no tice ship message from the database. Also notifies the appropriate model listeners.
	 *
	 * @param TempNoticeShipMessage the temp no tice ship message
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempNoticeShipMessage(
			TempNoticeShipMessage TempNoticeShipMessage) throws SystemException {
		persistence.remove(TempNoticeShipMessage);




	}













	public TempNoticeShipMessage fetchTempNoticeShipMessage(long id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp no tice ship message with the primary key.
	 *
	 * @param id the primary key of the temp no tice ship message
	 * @return the temp no tice ship message
	 * @throws PortalException if a temp no tice ship message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage getTempNoticeShipMessage(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the temp no tice ship messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp no tice ship messages
	 * @param end the upper bound of the range of temp no tice ship messages (not inclusive)
	 * @return the range of temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempNoticeShipMessage> getTempNoticeShipMessages(int start,
																 int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp no tice ship messages.
	 *
	 * @return the number of temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempNoticeShipMessagesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp no tice ship message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param TempNoticeShipMessage the temp no tice ship message
	 * @return the temp no tice ship message that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage updateTempNoticeShipMessage(
			TempNoticeShipMessage TempNoticeShipMessage) throws SystemException {
		return updateTempNoticeShipMessage(TempNoticeShipMessage, true);
	}

	/**
	 * Updates the temp no tice ship message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param TempNoticeShipMessage the temp no tice ship message
	 * @param merge whether to merge the temp no tice ship message with the current session. See  for an explanation.
	 * @return the temp no tice ship message that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage updateTempNoticeShipMessage(
			TempNoticeShipMessage TempNoticeShipMessage, boolean merge)
			throws SystemException {

		TempNoticeShipMessage = persistence.updateImpl(TempNoticeShipMessage,
				merge);





		return TempNoticeShipMessage;
	}
	
}