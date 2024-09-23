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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempShipStoresDeclarationFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempShipStoresDeclarationPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service








/**
 * The implementation of the temp ship stores declaration local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempShipStoresDeclarationLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can
 * only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempShipStoresDeclarationLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempShipStoresDeclarationLocalServiceUtil
 */
public class TempShipStoresDeclarationLocalServiceImpl { @Autowired
TempShipStoresDeclarationPersistenceImpl persistence;@Autowired
TempShipStoresDeclarationFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.TempShipStoresDeclarationLocalServiceUtil} to access the temp ship stores declaration local service.
	 */
	
	public int countBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		try {
			return finder.countBydocumentNameAnddocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<TempShipStoresDeclaration> findBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		try {
			return finder.findBydocumentNameAnddocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TempShipStoresDeclaration> findBydocumentNameAnddocumentYearRequestState(long documentName, int documentYear, int requestState) {
		try {
			return persistence.findBydocumentNameAnddocumentYearRequestState(documentName, documentYear, requestState);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TempShipStoresDeclaration> findByRequestCode(String requestCode) {
		try {
			return persistence.findByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public TempShipStoresDeclaration findTempShipStoresDeclarationByRequestCode(String requestCode) {
		try {
			return finder.findTempShipStoresDeclarationByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public TempShipStoresDeclaration getLastByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TempShipStoresDeclaration> findByDocumentNameAndDocumentYearOrderByDescRequestDate(long documentName, int documentYear) {
		return finder.findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName, documentYear);
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempShipStoresDeclarationLocalServiceUtil} to access the temp ship stores declaration local service.
	 */

	/**
	 * Adds the temp ship stores declaration to the database. Also notifies the appropriate model listeners.
	 *
	 * @param tempShipStoresDeclaration the temp ship stores declaration
	 * @return the temp ship stores declaration that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipStoresDeclaration addTempShipStoresDeclaration(
			TempShipStoresDeclaration tempShipStoresDeclaration)
			throws SystemException {

		tempShipStoresDeclaration = persistence.updateImpl(tempShipStoresDeclaration,
				false);





		return tempShipStoresDeclaration;
	}

	/**
	 * Creates a new temp ship stores declaration with the primary key. Does not add the temp ship stores declaration to the database.
	 *
	 * @param id the primary key for the new temp ship stores declaration
	 * @return the new temp ship stores declaration
	 */
	public TempShipStoresDeclaration createTempShipStoresDeclaration(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp ship stores declaration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp ship stores declaration
	 * @throws PortalException if a temp ship stores declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempShipStoresDeclaration(long id)
			throws PortalException, SystemException {
		TempShipStoresDeclaration tempShipStoresDeclaration = persistence.remove(id);




	}

	/**
	 * Deletes the temp ship stores declaration from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tempShipStoresDeclaration the temp ship stores declaration
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempShipStoresDeclaration(
			TempShipStoresDeclaration tempShipStoresDeclaration)
			throws SystemException {
		persistence.remove(tempShipStoresDeclaration);




	}













	public TempShipStoresDeclaration fetchTempShipStoresDeclaration(long id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp ship stores declaration with the primary key.
	 *
	 * @param id the primary key of the temp ship stores declaration
	 * @return the temp ship stores declaration
	 * @throws PortalException if a temp ship stores declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipStoresDeclaration getTempShipStoresDeclaration(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	/**
	 * Returns a range of all the temp ship stores declarations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp ship stores declarations
	 * @param end the upper bound of the range of temp ship stores declarations (not inclusive)
	 * @return the range of temp ship stores declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempShipStoresDeclaration> getTempShipStoresDeclarations(
			int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp ship stores declarations.
	 *
	 * @return the number of temp ship stores declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempShipStoresDeclarationsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp ship stores declaration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempShipStoresDeclaration the temp ship stores declaration
	 * @return the temp ship stores declaration that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipStoresDeclaration updateTempShipStoresDeclaration(
			TempShipStoresDeclaration tempShipStoresDeclaration)
			throws SystemException {
		return updateTempShipStoresDeclaration(tempShipStoresDeclaration, true);
	}

	/**
	 * Updates the temp ship stores declaration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempShipStoresDeclaration the temp ship stores declaration
	 * @param merge whether to merge the temp ship stores declaration with the current session. See  for an explanation.
	 * @return the temp ship stores declaration that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipStoresDeclaration updateTempShipStoresDeclaration(
			TempShipStoresDeclaration tempShipStoresDeclaration, boolean merge)
			throws SystemException {

		tempShipStoresDeclaration = persistence.updateImpl(tempShipStoresDeclaration,
				merge);





		return tempShipStoresDeclaration;
	}
}
