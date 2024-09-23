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

package vn.gt.dao.common.service;






/**
 * The utility for the user sign local service. This utility wraps {@link vn.gt.dao.common.service.impl.UserSignLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see UserSignLocalService
 * @see vn.gt.dao.common.service.base.UserSignLocalServiceBaseImpl
 * @see vn.gt.dao.common.service.impl.UserSignLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.common.service.impl.UserSignLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class UserSignLocalServiceUtil {
public UserSignLocalServiceUtil(UserSignLocalServiceImpl service) {
UserSignLocalServiceUtil._service = service;
}
public static UserSignLocalServiceImpl getService() {
return _service;
}
private static UserSignLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.common.service.impl.UserSignLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the user sign to the database. Also notifies the appropriate model listeners.
	*
	* @param userSign the user sign
	* @return the user sign that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.UserSign addUserSign(
		com.fds.nsw.nghiepvu.model.UserSign userSign)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addUserSign(userSign);
	}

	/**
	* Creates a new user sign with the primary key. Does not add the user sign to the database.
	*
	* @param id the primary key for the new user sign
	* @return the new user sign
	*/
	public static com.fds.nsw.nghiepvu.model.UserSign createUserSign(long id) {
		return getService().createUserSign(id);
	}

	/**
	* Deletes the user sign with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the user sign
	* @throws PortalException if a user sign with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserSign(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteUserSign(id);
	}

	/**
	* Deletes the user sign from the database. Also notifies the appropriate model listeners.
	*
	* @param userSign the user sign
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserSign(com.fds.nsw.nghiepvu.model.UserSign userSign)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteUserSign(userSign);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
												java.lang.String[] parameterTypes, java.lang.Object[] arguments)
			throws java.lang.Throwable {
		return null;
	}









	public static com.fds.nsw.nghiepvu.model.UserSign fetchUserSign(long id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchUserSign(id);
	}

	/**
	* Returns the user sign with the primary key.
	*
	* @param id the primary key of the user sign
	* @return the user sign
	* @throws PortalException if a user sign with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.UserSign getUserSign(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getUserSign(id);
	}

	

	/**
	* Returns a range of all the user signs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user signs
	* @param end the upper bound of the range of user signs (not inclusive)
	* @return the range of user signs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.UserSign> getUserSigns(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getUserSigns(start, end);
	}

	/**
	* Returns the number of user signs.
	*
	* @return the number of user signs
	* @throws SystemException if a system exception occurred
	*/
	public static int getUserSignsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getUserSignsCount();
	}

	/**
	* Updates the user sign in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userSign the user sign
	* @return the user sign that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.UserSign updateUserSign(
		com.fds.nsw.nghiepvu.model.UserSign userSign)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateUserSign(userSign);
	}

	/**
	* Updates the user sign in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userSign the user sign
	* @param merge whether to merge the user sign with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the user sign that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.UserSign updateUserSign(
		com.fds.nsw.nghiepvu.model.UserSign userSign, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateUserSign(userSign, merge);
	}

	

	

	public static void deleteUserSignById(long userSignId)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteUserSignById(userSignId);
	}

	public static com.fds.nsw.nghiepvu.model.UserSign updateUserSign(long userId,
		long userSignId, long accountId, java.lang.String maritimeCode,
		java.lang.String title, java.lang.String representative,
		java.lang.String chungThuSoName, java.io.File chungThuSoFile,
		boolean deleteFileChungThuSo, java.lang.String chuKySoName,
		java.io.File chuKySoFile, boolean deleteFileChuKySo,
		java.lang.String conDauName, java.io.File conDauFile,
		boolean deleteFileConDau)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateUserSign(userId, userSignId, accountId, maritimeCode,
			title, representative, chungThuSoName, chungThuSoFile,
			deleteFileChungThuSo, chuKySoName, chuKySoFile, deleteFileChuKySo,
			conDauName, conDauFile, deleteFileConDau);
	}

	public static com.fds.nsw.nghiepvu.model.UserSign getByUserId(long userId) {
		return getService().getByUserId(userId);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.UserSign> getByPortCode(
		java.lang.String portCode, int start, int end) {
		return getService().getByPortCode(portCode, start, end);
	}

	public static int countByPortCode(java.lang.String portCode) {
		return getService().countByPortCode(portCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	


}