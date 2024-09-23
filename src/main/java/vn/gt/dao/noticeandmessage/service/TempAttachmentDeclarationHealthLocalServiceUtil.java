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

package vn.gt.dao.noticeandmessage.service;






/**
 * The utility for the temp attachment declaration health local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempAttDeclarationHealthLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempAttachmentDeclarationHealthLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempAttachmentDeclarationHealthLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempAttDeclarationHealthLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempAttDeclarationHealthLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempAttachmentDeclarationHealthLocalServiceUtil {
public TempAttachmentDeclarationHealthLocalServiceUtil(TempAttDeclarationHealthLocalServiceImpl service) {
TempAttachmentDeclarationHealthLocalServiceUtil._service = service;
}
public static TempAttDeclarationHealthLocalServiceImpl getService() {
return _service;
}
private static TempAttDeclarationHealthLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempAttDeclarationHealthLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp attachment declaration health to the database. Also notifies the appropriate model listeners.
	*
	* @param tempAttachmentDeclarationHealth the temp attachment declaration health
	* @return the temp attachment declaration health that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempAttDeclarationHealth addTempAttachmentDeclarationHealth(
		com.fds.nsw.nghiepvu.model.TempAttDeclarationHealth tempAttachmentDeclarationHealth)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .addTempAttDeclarationHealth(tempAttachmentDeclarationHealth);
	}

	/**
	* Creates a new temp attachment declaration health with the primary key. Does not add the temp attachment declaration health to the database.
	*
	* @param id the primary key for the new temp attachment declaration health
	* @return the new temp attachment declaration health
	*/
	public static com.fds.nsw.nghiepvu.model.TempAttDeclarationHealth createTempAttachmentDeclarationHealth(
		long id) {
		return getService().createTempAttDeclarationHealth(id);
	}

	/**
	* Deletes the temp attachment declaration health with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp attachment declaration health
	* @throws PortalException if a temp attachment declaration health with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempAttachmentDeclarationHealth(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempAttDeclarationHealth(id);
	}

	/**
	* Deletes the temp attachment declaration health from the database. Also notifies the appropriate model listeners.
	*
	* @param tempAttachmentDeclarationHealth the temp attachment declaration health
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempAttachmentDeclarationHealth(
		com.fds.nsw.nghiepvu.model.TempAttDeclarationHealth tempAttachmentDeclarationHealth)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService()
			.deleteTempAttDeclarationHealth(tempAttachmentDeclarationHealth);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempAttDeclarationHealth fetchTempAttachmentDeclarationHealth(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempAttDeclarationHealth(id);
	}

	/**
	* Returns the temp attachment declaration health with the primary key.
	*
	* @param id the primary key of the temp attachment declaration health
	* @return the temp attachment declaration health
	* @throws PortalException if a temp attachment declaration health with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempAttDeclarationHealth getTempAttachmentDeclarationHealth(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempAttDeclarationHealth(id);
	}

	

	/**
	* Returns a range of all the temp attachment declaration healths.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp attachment declaration healths
	* @param end the upper bound of the range of temp attachment declaration healths (not inclusive)
	* @return the range of temp attachment declaration healths
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempAttDeclarationHealth> getTempAttachmentDeclarationHealths(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempAttDeclarationHealths(start, end);
	}

	/**
	* Returns the number of temp attachment declaration healths.
	*
	* @return the number of temp attachment declaration healths
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempAttachmentDeclarationHealthsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempAttDeclarationHealthsCount();
	}

	/**
	* Updates the temp attachment declaration health in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempAttachmentDeclarationHealth the temp attachment declaration health
	* @return the temp attachment declaration health that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempAttDeclarationHealth updateTempAttachmentDeclarationHealth(
		com.fds.nsw.nghiepvu.model.TempAttDeclarationHealth tempAttachmentDeclarationHealth)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempAttDeclarationHealth(tempAttachmentDeclarationHealth);
	}

	/**
	* Updates the temp attachment declaration health in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempAttachmentDeclarationHealth the temp attachment declaration health
	* @param merge whether to merge the temp attachment declaration health with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp attachment declaration health that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempAttDeclarationHealth updateTempAttachmentDeclarationHealth(
		com.fds.nsw.nghiepvu.model.TempAttDeclarationHealth tempAttachmentDeclarationHealth,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempAttDeclarationHealth(tempAttachmentDeclarationHealth,
			merge);
	}

	

	

	public static void clearService() {
		_service = null;
	}

	

	

	
}