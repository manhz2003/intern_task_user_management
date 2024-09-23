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
import com.fds.nsw.nghiepvu.danhmucgt.service.finder.DmCertificateFinderImpl;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmucgt.service.persistence.DmCertificatePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.util.ChuyenDichTrangThaiUtils;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service








/**
 * The implementation of the dm certificate local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmucgt.service.DmCertificateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmucgt.service.base.DmCertificateLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.DmCertificateLocalServiceUtil
 */
public class DmCertificateLocalServiceImpl
	{ @Autowired
	DmCertificatePersistenceImpl persistence;

		@Autowired
		DmCertificateFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmucgt.service.DmCertificateLocalServiceUtil} to access the dm certificate local service.
	 */
	
	public List<DmCertificate> findByCertificateCode(String certificateCode) {
		try {
			return persistence.findByCertificateCode(certificateCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DmCertificate>();
	}
	
	public List<DmCertificate> findByCertificateCodeAndcertificateName(String certificateCode, String certificateName) {
		try {
			return persistence.findByCertificateCodeAndCertificateName(certificateCode, certificateName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DmCertificate>();
	}
	
	//get dmcertificate by thutuchanhchinh
	public List<DmCertificate> findBylCode(String lCode) throws SystemException {
		List<DmCertificate> results = new ArrayList<DmCertificate>();
		if(lCode.equalsIgnoreCase("NC") || lCode.equalsIgnoreCase("QC") || lCode.equalsIgnoreCase("XC")){
			results = persistence.findByF_LCODE_LT("37");
		}else if(lCode.equalsIgnoreCase("4") || lCode.equalsIgnoreCase("5") || lCode.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6) || lCode.equalsIgnoreCase(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7)){
			results = persistence.findByF_LCODE_GT("36");
		}
		return results;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmucgt.service.DmCertificateLocalServiceUtil} to access the dm certificate local service.
		 */

		/**
		 * Adds the dm certificate to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmCertificate the dm certificate
		 * @return the dm certificate that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmCertificate addDmCertificate(DmCertificate dmCertificate)
				throws SystemException {

			dmCertificate = persistence.updateImpl(dmCertificate, false);





			return dmCertificate;
		}

		/**
		 * Creates a new dm certificate with the primary key. Does not add the dm certificate to the database.
		 *
		 * @param id the primary key for the new dm certificate
		 * @return the new dm certificate
		 */
		public DmCertificate createDmCertificate(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm certificate with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm certificate
		 * @throws PortalException if a dm certificate with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmCertificate(long id)
				throws PortalException, SystemException {
			DmCertificate dmCertificate = persistence.remove(id);




		}

		/**
		 * Deletes the dm certificate from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmCertificate the dm certificate
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmCertificate(DmCertificate dmCertificate)
				throws SystemException {
			persistence.remove(dmCertificate);




		}













		public DmCertificate fetchDmCertificate(long id) throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm certificate with the primary key.
		 *
		 * @param id the primary key of the dm certificate
		 * @return the dm certificate
		 * @throws PortalException if a dm certificate with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmCertificate getDmCertificate(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the dm certificates.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm certificates
		 * @param end the upper bound of the range of dm certificates (not inclusive)
		 * @return the range of dm certificates
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmCertificate> getDmCertificates(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm certificates.
		 *
		 * @return the number of dm certificates
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmCertificatesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmCertificate the dm certificate
		 * @return the dm certificate that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmCertificate updateDmCertificate(DmCertificate dmCertificate)
				throws SystemException {
			return updateDmCertificate(dmCertificate, true);
		}

		/**
		 * Updates the dm certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmCertificate the dm certificate
		 * @param merge whether to merge the dm certificate with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
		 * @return the dm certificate that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmCertificate updateDmCertificate(DmCertificate dmCertificate,
												 boolean merge) throws SystemException {

			dmCertificate = persistence.updateImpl(dmCertificate, merge);





			return dmCertificate;
		}

		public List<DmCertificate> findDmCertificates(String certificateName,
													  int start, int end) throws SystemException {
			return finder.findDmCertificates(certificateName, start,
					end);
		}

		public long countDmCertificates(String certificateName)
				throws SystemException {
			return finder.countVmaCertificates(certificateName);
		}

	}