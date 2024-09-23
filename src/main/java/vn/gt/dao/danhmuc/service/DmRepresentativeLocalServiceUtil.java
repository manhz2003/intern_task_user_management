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

package vn.gt.dao.danhmuc.service;






/**
 * The utility for the dm representative local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmRepresentativeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmRepresentativeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmRepresentativeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmRepresentativeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmRepresentativeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmRepresentativeLocalServiceUtil {
public DmRepresentativeLocalServiceUtil(DmRepresentativeLocalServiceImpl service) {
DmRepresentativeLocalServiceUtil._service = service;
}
public static DmRepresentativeLocalServiceImpl getService() {
return _service;
}
private static DmRepresentativeLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmRepresentativeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm representative to the database. Also notifies the appropriate model listeners.
	*
	* @param dmRepresentative the dm representative
	* @return the dm representative that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmRepresentative addDmRepresentative(
		com.fds.nsw.nghiepvu.model.DmRepresentative dmRepresentative)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmRepresentative(dmRepresentative);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmRepresentative> findDmRepresentatives(
			java.lang.String maritimeCode, java.lang.String repNameVN,
			int repLevel, java.lang.String isDelete, java.lang.String repCodeGroup,
			int start, int end) {
		return getService()
				.findDmRepresentatives(maritimeCode, repNameVN, repLevel,
						isDelete, repCodeGroup, start, end);
	}

	public static long countDmRepresentatives(java.lang.String maritimeCode,
											  java.lang.String repNameVN, int repLevel, java.lang.String isDelete,
											  java.lang.String repCodeGroup) {
		return getService()
				.countDmRepresentatives(maritimeCode, repNameVN, repLevel,
						isDelete, repCodeGroup);
	}


	/**
	* Creates a new dm representative with the primary key. Does not add the dm representative to the database.
	*
	* @param id the primary key for the new dm representative
	* @return the new dm representative
	*/
	public static com.fds.nsw.nghiepvu.model.DmRepresentative createDmRepresentative(
		int id) {
		return getService().createDmRepresentative(id);
	}

	/**
	* Deletes the dm representative with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm representative
	* @throws PortalException if a dm representative with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmRepresentative(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmRepresentative(id);
	}

	/**
	* Deletes the dm representative from the database. Also notifies the appropriate model listeners.
	*
	* @param dmRepresentative the dm representative
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmRepresentative(
		com.fds.nsw.nghiepvu.model.DmRepresentative dmRepresentative)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmRepresentative(dmRepresentative);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmRepresentative fetchDmRepresentative(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmRepresentative(id);
	}

	/**
	* Returns the dm representative with the primary key.
	*
	* @param id the primary key of the dm representative
	* @return the dm representative
	* @throws PortalException if a dm representative with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmRepresentative getDmRepresentative(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmRepresentative(id);
	}

	

	/**
	* Returns a range of all the dm representatives.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm representatives
	* @param end the upper bound of the range of dm representatives (not inclusive)
	* @return the range of dm representatives
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmRepresentative> getDmRepresentatives(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmRepresentatives(start, end);
	}

	/**
	* Returns the number of dm representatives.
	*
	* @return the number of dm representatives
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmRepresentativesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmRepresentativesCount();
	}

	/**
	* Updates the dm representative in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmRepresentative the dm representative
	* @return the dm representative that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmRepresentative updateDmRepresentative(
		com.fds.nsw.nghiepvu.model.DmRepresentative dmRepresentative)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmRepresentative(dmRepresentative);
	}

	/**
	* Updates the dm representative in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmRepresentative the dm representative
	* @param merge whether to merge the dm representative with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm representative that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmRepresentative updateDmRepresentative(
		com.fds.nsw.nghiepvu.model.DmRepresentative dmRepresentative, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmRepresentative(dmRepresentative, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmRepresentative getByRepCode(
		java.lang.String repCode) {
		return getService().getByRepCode(repCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmRepresentative> findByMaritimeCode(
		java.lang.String maritimeCode) {
		return getService().findByMaritimeCode(maritimeCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmRepresentative> findDanhSachDmRepresentative(
		java.lang.String maritimeCode, int start, int end) {
		return getService()
				   .findDanhSachDmRepresentative(maritimeCode, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmRepresentative> findDmRepresentativeByMaritimeCode(
		java.lang.String maritimeCode) {
		return getService().findDmRepresentativeByMaritimeCode(maritimeCode);
	}

	public static int countDanhSachDmRepresentative(
		java.lang.String maritimeCode) {
		return getService().countDanhSachDmRepresentative(maritimeCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}