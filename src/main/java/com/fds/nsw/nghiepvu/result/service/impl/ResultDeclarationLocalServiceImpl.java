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

package com.fds.nsw.nghiepvu.result.service.impl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.result.service.finder.ResultDeclarationFinderImpl;
import com.fds.nsw.nghiepvu.result.service.persistence.ResultDeclarationPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service





/**
 * The implementation of the result declaration local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.result.service.ResultDeclarationLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can
 * only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.result.service.base.ResultDeclarationLocalServiceBaseImpl
 * @see vn.gt.dao.result.service.resultDeclarationLocalService
 */
public class ResultDeclarationLocalServiceImpl { @Autowired
ResultDeclarationPersistenceImpl persistence;@Autowired
ResultDeclarationFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link vn.gt.dao.result.service.resultDeclarationLocalService} to
	 * access the result declaration local service.
	 */
	
	public List<ResultDeclaration> findResultDeclarationByBusinessTypeCode(int businessTypeCode) {
		try {
			// persistence.c
			return persistence.findByBusinessTypeCode(businessTypeCode);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return new ArrayList<ResultDeclaration>();
	}
	
	public List<ResultDeclaration> findByDocumentNameAndBusinessTypeCodeAndDocumentYear(int businessTypeCode, long documentName, int documentYear) {
		try {
			return finder.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(businessTypeCode, documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<ResultDeclaration>();
	}
	
	public List<ResultDeclaration> DocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(int businessTypeCode, long documentName, int documentYear, String requestCode) {
		try {
			return persistence.findByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(businessTypeCode, documentName, documentYear, requestCode);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return new ArrayList<ResultDeclaration>();
	}
	
	public ResultDeclaration findbyDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(int businessTypeCode, long documentName, int documentYear, String requestCode) {
		try {
			return persistence.findByfndDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(businessTypeCode, documentName, documentYear, requestCode);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	public List<ResultDeclaration> FindByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(int businessTypeCode, long documentName, int documentYear, String requestCode) {
		try {
			return persistence.findByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(businessTypeCode, documentName, documentYear, requestCode);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return new ArrayList<ResultDeclaration>();
	}
	
	public List<ResultDeclaration> findByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return persistence.findByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ResultDeclaration> findByDocumentNameAndDocumentYearOrderByBusinessOrder(long documentName, int documentYear) {
		try {
			return finder.findResultDeclarationByDocumentNameAndDocumentYearOrderByBusiness(documentName, documentYear);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ResultDeclaration> findResultDeclarationByDocumentNameAndDocumentYearNcQcReport(long documentName, int documentYear) {
		try {
			return finder.findResultDeclarationByDocumentNameAndDocumentYearNcQcReport(documentName, documentYear);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ResultDeclaration> findResultDeclarationByDocumentNameAndDocumentYearXcReport(long documentName, int documentYear) {
		try {
			return finder.findResultDeclarationByDocumentNameAndDocumentYearXcReport(documentName, documentYear);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ResultDeclaration> findResultDeclarationByDocumentNameAndDocumentYearNXDoiChieu(long documentName, int documentYear) {
		try {
			return finder.findResultDeclarationByDocumentNameAndDocumentYearNXDoiChieu(documentName, documentYear);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ResultDeclaration> findResultDeclarationByDocumentNameAndDocumentYearQCDoiChieu(long documentName, int documentYear) {
		try {
			return finder.findResultDeclarationByDocumentNameAndDocumentYearQCDoiChieu(documentName, documentYear);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int countByRequestCode(String requestCode) {
		try {
			return persistence.countByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public ResultDeclaration findByRequestCode(String requestCode) {
		try {
			return persistence.findByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int countByDocumentNameAndBusinessTypeCodeAndDocumentYear(int businessTypeCode, long documentName, int documentYear) {
		try {
			return persistence.countByDocumentNameAndBusinessTypeCodeAndDocumentYear(businessTypeCode, documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int countByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return persistence.countByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int countByRequestCodeAndBusinessTypeCode(String requestCode, int businessTypeCode) {
		try {
			return persistence.countByRequestCodeAndBusinessTypeCode(requestCode, businessTypeCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public List<ResultDeclaration> findByRequestCodeAndBusinessTypeCode(String requestCode, int businessTypeCode) {
		try {
			return persistence.findByRequestCodeAndBusinessTypeCode(requestCode, businessTypeCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.result.service.resultDeclarationLocalService} to access the result declaration local service.
	 */

	/**
	 * Adds the result declaration to the database. Also notifies the appropriate model listeners.
	 *
	 * @param resultDeclaration the result declaration
	 * @return the result declaration that was added
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration addResultDeclaration(
			ResultDeclaration resultDeclaration) throws SystemException {

		resultDeclaration = persistence.updateImpl(resultDeclaration,
				false);





		return resultDeclaration;
	}

	/**
	 * Creates a new result declaration with the primary key. Does not add the result declaration to the database.
	 *
	 * @param id the primary key for the new result declaration
	 * @return the new result declaration
	 */
	public ResultDeclaration createResultDeclaration(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the result declaration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the result declaration
	 * @throws PortalException if a result declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteResultDeclaration(long id)
			throws PortalException, SystemException {
		ResultDeclaration resultDeclaration = persistence.remove(id);




	}

	/**
	 * Deletes the result declaration from the database. Also notifies the appropriate model listeners.
	 *
	 * @param resultDeclaration the result declaration
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteResultDeclaration(ResultDeclaration resultDeclaration)
			throws SystemException {
		persistence.remove(resultDeclaration);




	}













	public ResultDeclaration fetchResultDeclaration(long id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the result declaration with the primary key.
	 *
	 * @param id the primary key of the result declaration
	 * @return the result declaration
	 * @throws PortalException if a result declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration getResultDeclaration(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the result declarations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result declarations
	 * @param end the upper bound of the range of result declarations (not inclusive)
	 * @return the range of result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> getResultDeclarations(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of result declarations.
	 *
	 * @return the number of result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int getResultDeclarationsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the result declaration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param resultDeclaration the result declaration
	 * @return the result declaration that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration updateResultDeclaration(
			ResultDeclaration resultDeclaration) throws SystemException {
		return updateResultDeclaration(resultDeclaration, true);
	}

	/**
	 * Updates the result declaration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param resultDeclaration the result declaration
	 * @param merge whether to merge the result declaration with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the result declaration that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration updateResultDeclaration(
			ResultDeclaration resultDeclaration, boolean merge)
			throws SystemException {

		resultDeclaration = persistence.updateImpl(resultDeclaration,
				merge);





		return resultDeclaration;
	}
}