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
 * The utility for the temp passenger details local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempPassengerDetailsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempPassengerDetailsLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempPassengerDetailsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempPassengerDetailsLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempPassengerDetailsLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempPassengerDetailsLocalServiceUtil {
public TempPassengerDetailsLocalServiceUtil(TempPassengerDetailsLocalServiceImpl service) {
TempPassengerDetailsLocalServiceUtil._service = service;
}
public static TempPassengerDetailsLocalServiceImpl getService() {
return _service;
}
private static TempPassengerDetailsLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempPassengerDetailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp passenger details to the database. Also notifies the appropriate model listeners.
	*
	* @param tempPassengerDetails the temp passenger details
	* @return the temp passenger details that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempPassengerDetails addTempPassengerDetails(
		com.fds.nsw.nghiepvu.model.TempPassengerDetails tempPassengerDetails)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempPassengerDetails(tempPassengerDetails);
	}

	/**
	* Creates a new temp passenger details with the primary key. Does not add the temp passenger details to the database.
	*
	* @param id the primary key for the new temp passenger details
	* @return the new temp passenger details
	*/
	public static com.fds.nsw.nghiepvu.model.TempPassengerDetails createTempPassengerDetails(
		long id) {
		return getService().createTempPassengerDetails(id);
	}

	/**
	* Deletes the temp passenger details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp passenger details
	* @throws PortalException if a temp passenger details with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempPassengerDetails(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempPassengerDetails(id);
	}

	/**
	* Deletes the temp passenger details from the database. Also notifies the appropriate model listeners.
	*
	* @param tempPassengerDetails the temp passenger details
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempPassengerDetails(
		com.fds.nsw.nghiepvu.model.TempPassengerDetails tempPassengerDetails)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempPassengerDetails(tempPassengerDetails);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempPassengerDetails fetchTempPassengerDetails(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempPassengerDetails(id);
	}

	/**
	* Returns the temp passenger details with the primary key.
	*
	* @param id the primary key of the temp passenger details
	* @return the temp passenger details
	* @throws PortalException if a temp passenger details with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempPassengerDetails getTempPassengerDetails(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempPassengerDetails(id);
	}

	

	/**
	* Returns a range of all the temp passenger detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp passenger detailses
	* @param end the upper bound of the range of temp passenger detailses (not inclusive)
	* @return the range of temp passenger detailses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPassengerDetails> getTempPassengerDetailses(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempPassengerDetailses(start, end);
	}

	/**
	* Returns the number of temp passenger detailses.
	*
	* @return the number of temp passenger detailses
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempPassengerDetailsesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempPassengerDetailsesCount();
	}

	/**
	* Updates the temp passenger details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempPassengerDetails the temp passenger details
	* @return the temp passenger details that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempPassengerDetails updateTempPassengerDetails(
		com.fds.nsw.nghiepvu.model.TempPassengerDetails tempPassengerDetails)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempPassengerDetails(tempPassengerDetails);
	}

	/**
	* Updates the temp passenger details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempPassengerDetails the temp passenger details
	* @param merge whether to merge the temp passenger details with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp passenger details that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempPassengerDetails updateTempPassengerDetails(
		com.fds.nsw.nghiepvu.model.TempPassengerDetails tempPassengerDetails,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempPassengerDetails(tempPassengerDetails, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPassengerDetails> findByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPassengerDetails> findByPassengerCode(
		java.lang.String passengerCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByPassengerCode(passengerCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}