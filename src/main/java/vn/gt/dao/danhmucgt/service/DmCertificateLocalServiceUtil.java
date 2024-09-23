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

package vn.gt.dao.danhmucgt.service;






/**
 * The utility for the dm certificate local service. This utility wraps {@link vn.gt.dao.danhmucgt.service.impl.DmCertificateLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmCertificateLocalService
 * @see vn.gt.dao.danhmucgt.service.base.DmCertificateLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.impl.DmCertificateLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmucgt.service.impl.DmCertificateLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmCertificateLocalServiceUtil {
public DmCertificateLocalServiceUtil(DmCertificateLocalServiceImpl service) {
DmCertificateLocalServiceUtil._service = service;
}
public static DmCertificateLocalServiceImpl getService() {
return _service;
}
private static DmCertificateLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmucgt.service.impl.DmCertificateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm certificate to the database. Also notifies the appropriate model listeners.
	*
	* @param dmCertificate the dm certificate
	* @return the dm certificate that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmCertificate addDmCertificate(
		com.fds.nsw.nghiepvu.model.DmCertificate dmCertificate)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmCertificate(dmCertificate);
	}

	/**
	* Creates a new dm certificate with the primary key. Does not add the dm certificate to the database.
	*
	* @param id the primary key for the new dm certificate
	* @return the new dm certificate
	*/
	public static com.fds.nsw.nghiepvu.model.DmCertificate createDmCertificate(
		long id) {
		return getService().createDmCertificate(id);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmCertificate> findDmCertificates(
			java.lang.String certificateName, int start, int end)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findDmCertificates(certificateName, start, end);
	}

	public static long countDmCertificates(java.lang.String certificateName)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().countDmCertificates(certificateName);
	}


	/**
	* Deletes the dm certificate with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm certificate
	* @throws PortalException if a dm certificate with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmCertificate(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmCertificate(id);
	}

	/**
	* Deletes the dm certificate from the database. Also notifies the appropriate model listeners.
	*
	* @param dmCertificate the dm certificate
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmCertificate(
		com.fds.nsw.nghiepvu.model.DmCertificate dmCertificate)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmCertificate(dmCertificate);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmCertificate fetchDmCertificate(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmCertificate(id);
	}

	/**
	* Returns the dm certificate with the primary key.
	*
	* @param id the primary key of the dm certificate
	* @return the dm certificate
	* @throws PortalException if a dm certificate with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmCertificate getDmCertificate(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmCertificate(id);
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
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmCertificate> getDmCertificates(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmCertificates(start, end);
	}

	/**
	* Returns the number of dm certificates.
	*
	* @return the number of dm certificates
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmCertificatesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmCertificatesCount();
	}

	/**
	* Updates the dm certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmCertificate the dm certificate
	* @return the dm certificate that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmCertificate updateDmCertificate(
		com.fds.nsw.nghiepvu.model.DmCertificate dmCertificate)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmCertificate(dmCertificate);
	}

	/**
	* Updates the dm certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmCertificate the dm certificate
	* @param merge whether to merge the dm certificate with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm certificate that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmCertificate updateDmCertificate(
		com.fds.nsw.nghiepvu.model.DmCertificate dmCertificate, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmCertificate(dmCertificate, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmCertificate> findByCertificateCode(
		java.lang.String certificateCode) {
		return getService().findByCertificateCode(certificateCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmCertificate> findByCertificateCodeAndcertificateName(
		java.lang.String certificateCode, java.lang.String certificateName) {
		return getService()
				   .findByCertificateCodeAndcertificateName(certificateCode,
			certificateName);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmCertificate> findBylCode(
		java.lang.String lCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findBylCode(lCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	


}