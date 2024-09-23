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
import com.fds.nsw.nghiepvu.result.service.persistence.ResultCertificatePersistenceImpl;
import com.fds.nsw.nghiepvu.result.service.finder.ResultCertificateFinderImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service





/**
 * The implementation of the result certificate local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.result.service.ResultCertificateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.result.service.base.ResultCertificateLocalServiceBaseImpl
 * @see vn.gt.dao.result.service.ResultCertificateLocalServiceUtil
 */
public class ResultCertificateLocalServiceImpl
	{ @Autowired
	ResultCertificatePersistenceImpl persistence;
		@Autowired
		ResultCertificateFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.result.service.ResultCertificateLocalServiceUtil} to access the result certificate local service.
	 */
	public List<ResultCertificate> findResultCertificates(String imoNumber,
														  String callSign, int start, int end) throws SystemException {
		return finder.findResultCertificates(imoNumber,
				callSign, start, end);
	}


	public List<ResultCertificate> findByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return persistence.findByDocumentNameAnddocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<ResultCertificate>();
	}


	public List<ResultCertificate> findByDocumentNameAndDocumentYearAndMaritimeCode(long documentName, int documentYear, String maritimeCode) {
		try {
			return persistence.findByDocumentNameAnddocumentYearAndMaritimeCode(documentName, documentYear, maritimeCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<ResultCertificate>();
	}
	
	public List<ResultCertificate> findByDocumentNameAndDocumentYearAndCertificateCode(long documentName, int documentYear, String certificateCode) {
		try {
			return persistence.findByDocumentNameAnddocumentYearAndCertificateCode(documentName, documentYear, certificateCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<ResultCertificate>();
	}
	
	public ResultCertificate fetchByF_BY4(long documentName, int documentYear, String certificateCode, String certificateNo) throws SystemException {
		return persistence.fetchByF_BY4(documentName, documentYear, certificateCode, certificateNo);
	}
	
	public ResultCertificate findByCrewNameAndCertificateCode(long documentName, int documentYear, String certificateCode, String crewName) throws SystemException {
		return persistence.fetchByCrewNameAndCertificateCode(documentName, documentYear, certificateCode, crewName);
	}


		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.result.service.ResultCertificateLocalServiceUtil} to access the result certificate local service.
		 */

		/**
		 * Adds the result certificate to the database. Also notifies the appropriate model listeners.
		 *
		 * @param resultCertificate the result certificate
		 * @return the result certificate that was added
		 * @throws SystemException if a system exception occurred
		 */
		public ResultCertificate addResultCertificate(
				ResultCertificate resultCertificate) throws SystemException {

			resultCertificate = persistence.updateImpl(resultCertificate,
					false);





			return resultCertificate;
		}

		/**
		 * Creates a new result certificate with the primary key. Does not add the result certificate to the database.
		 *
		 * @param id the primary key for the new result certificate
		 * @return the new result certificate
		 */
		public ResultCertificate createResultCertificate(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the result certificate with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the result certificate
		 * @throws PortalException if a result certificate with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteResultCertificate(long id)
				throws PortalException, SystemException {
			ResultCertificate resultCertificate = persistence.remove(id);




		}

		/**
		 * Deletes the result certificate from the database. Also notifies the appropriate model listeners.
		 *
		 * @param resultCertificate the result certificate
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteResultCertificate(ResultCertificate resultCertificate)
				throws SystemException {
			persistence.remove(resultCertificate);




		}













		public ResultCertificate fetchResultCertificate(long id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the result certificate with the primary key.
		 *
		 * @param id the primary key of the result certificate
		 * @return the result certificate
		 * @throws PortalException if a result certificate with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public ResultCertificate getResultCertificate(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the result certificates.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of result certificates
		 * @param end the upper bound of the range of result certificates (not inclusive)
		 * @return the range of result certificates
		 * @throws SystemException if a system exception occurred
		 */
		public List<ResultCertificate> getResultCertificates(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of result certificates.
		 *
		 * @return the number of result certificates
		 * @throws SystemException if a system exception occurred
		 */
		public int getResultCertificatesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the result certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param resultCertificate the result certificate
		 * @return the result certificate that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public ResultCertificate updateResultCertificate(
				ResultCertificate resultCertificate) throws SystemException {
			return updateResultCertificate(resultCertificate, true);
		}

		/**
		 * Updates the result certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param resultCertificate the result certificate
		 * @param merge whether to merge the result certificate with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
		 * @return the result certificate that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public ResultCertificate updateResultCertificate(
				ResultCertificate resultCertificate, boolean merge)
				throws SystemException {

			resultCertificate = persistence.updateImpl(resultCertificate,
					merge);





			return resultCertificate;
		}
}