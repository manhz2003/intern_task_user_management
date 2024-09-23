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
 * The utility for the dm maritime local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmMaritimeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmMaritimeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmMaritimeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmMaritimeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.kernel.util.OrderByComparator;
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmMaritimeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmMaritimeLocalServiceUtil {
public DmMaritimeLocalServiceUtil(DmMaritimeLocalServiceImpl service) {
DmMaritimeLocalServiceUtil._service = service;
}
public static DmMaritimeLocalServiceImpl getService() {
return _service;
}
private static DmMaritimeLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmMaritimeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm maritime to the database. Also notifies the appropriate model listeners.
	*
	* @param dmMaritime the dm maritime
	* @return the dm maritime that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmMaritime addDmMaritime(
		com.fds.nsw.nghiepvu.model.DmMaritime dmMaritime)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmMaritime(dmMaritime);
	}

	/**
	* Creates a new dm maritime with the primary key. Does not add the dm maritime to the database.
	*
	* @param id the primary key for the new dm maritime
	* @return the new dm maritime
	*/
	public static com.fds.nsw.nghiepvu.model.DmMaritime createDmMaritime(int id) {
		return getService().createDmMaritime(id);
	}

	/**
	* Deletes the dm maritime with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm maritime
	* @throws PortalException if a dm maritime with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmMaritime(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmMaritime(id);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmMaritime> findMaritimes(
			java.lang.String maritimeCode, java.lang.String isDelete, int start,
			int end) {
		return getService().findMaritimes(maritimeCode, isDelete, start, end);
	}

	public static int countMaritimes(java.lang.String maritimeCode,
									 java.lang.String isDelete) {
		return getService().countMaritimes(maritimeCode, isDelete);
	}


	/**
	* Deletes the dm maritime from the database. Also notifies the appropriate model listeners.
	*
	* @param dmMaritime the dm maritime
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmMaritime(
		com.fds.nsw.nghiepvu.model.DmMaritime dmMaritime)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmMaritime(dmMaritime);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmMaritime fetchDmMaritime(int id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmMaritime(id);
	}

	/**
	* Returns the dm maritime with the primary key.
	*
	* @param id the primary key of the dm maritime
	* @return the dm maritime
	* @throws PortalException if a dm maritime with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmMaritime getDmMaritime(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmMaritime(id);
	}

	

	/**
	* Returns a range of all the dm maritimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm maritimes
	* @param end the upper bound of the range of dm maritimes (not inclusive)
	* @return the range of dm maritimes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmMaritime> getDmMaritimes(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmMaritimes(start, end);
	}

	/**
	* Returns the number of dm maritimes.
	*
	* @return the number of dm maritimes
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmMaritimesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmMaritimesCount();
	}

	/**
	* Updates the dm maritime in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmMaritime the dm maritime
	* @return the dm maritime that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmMaritime updateDmMaritime(
		com.fds.nsw.nghiepvu.model.DmMaritime dmMaritime)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmMaritime(dmMaritime);
	}

	/**
	* Updates the dm maritime in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmMaritime the dm maritime
	* @param merge whether to merge the dm maritime with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm maritime that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmMaritime updateDmMaritime(
		com.fds.nsw.nghiepvu.model.DmMaritime dmMaritime, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmMaritime(dmMaritime, merge);
	}

	

	

	/**
	* Returns all the dm maritimes.
	*
	* @return the dm maritimes
	* @throws SystemException
	if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmMaritime> findAll() {
		return getService().findAll();
	}

	/**
	* Returns a range of all the dm maritimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of
	* <code>end - start</code> instances. <code>start</code> and
	* <code>end</code> are not primary keys, they are indexes in the result
	* set. Thus, <code>0</code> refers to the first result in the set. Setting
	* both <code>start</code> and <code>end</code> to
	*  will return
	* the full result set.
	* </p>
	*
	* @param start
	the lower bound of the range of dm maritimes
	* @param end
	the upper bound of the range of dm maritimes (not inclusive)
	* @return the range of dm maritimes
	* @throws SystemException
	if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmMaritime> findAll(
		int start, int end) {
		return getService().findAll(start, end);
	}

	public static com.fds.nsw.nghiepvu.model.DmMaritime getByMaritimeCode(
		java.lang.String maritimeCode) {
		return getService().getByMaritimeCode(maritimeCode);
	}

	public static com.fds.nsw.nghiepvu.model.DmMaritime getByMaritimeReference(
		java.lang.String maritimeReference) {
		return getService().getByMaritimeReference(maritimeReference);
	}

	/**
	* Returns the number of dm maritimes where maritimeCode = &#63;.
	*
	* @param maritimeCode
	the maritime code
	* @return the number of matching dm maritimes
	* @throws SystemException
	if a system exception occurred
	*/
	public static int countByMaritimeCode(java.lang.String maritimeCode) {
		return getService().countByMaritimeCode(maritimeCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmMaritime> findRangeOrderBy(
		int start, int end,
		OrderByComparator orderByComparator) {
		return getService().findRangeOrderBy(start, end, orderByComparator);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmMaritime> getAll() {
		return getService().getAll();
	}

	public static com.fds.nsw.nghiepvu.model.DmMaritime getFirstMaritime() {
		return getService().getFirstMaritime();
	}

	public static com.fds.nsw.nghiepvu.model.DmMaritime getLastMaritime() {
		return getService().getLastMaritime();
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmMaritime> findDanhSachDmMaritime(
		java.lang.String maritimeCode, int start, int end) {
		return getService().findDanhSachDmMaritime(maritimeCode, start, end);
	}

	public static int countDanhSachDmMaritime(java.lang.String maritimeCode,
		int start, int end) {
		return getService().countDanhSachDmMaritime(maritimeCode, start, end);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}