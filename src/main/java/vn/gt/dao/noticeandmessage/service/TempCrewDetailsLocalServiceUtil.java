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
 * The utility for the temp crew details local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempCrewDetailsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempCrewDetailsLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempCrewDetailsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempCrewDetailsLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempCrewDetailsLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempCrewDetailsLocalServiceUtil {
public TempCrewDetailsLocalServiceUtil(TempCrewDetailsLocalServiceImpl service) {
TempCrewDetailsLocalServiceUtil._service = service;
}
public static TempCrewDetailsLocalServiceImpl getService() {
return _service;
}
private static TempCrewDetailsLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempCrewDetailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp crew details to the database. Also notifies the appropriate model listeners.
	*
	* @param tempCrewDetails the temp crew details
	* @return the temp crew details that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewDetails addTempCrewDetails(
		com.fds.nsw.nghiepvu.model.TempCrewDetails tempCrewDetails)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempCrewDetails(tempCrewDetails);
	}

	/**
	* Creates a new temp crew details with the primary key. Does not add the temp crew details to the database.
	*
	* @param id the primary key for the new temp crew details
	* @return the new temp crew details
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewDetails createTempCrewDetails(
		long id) {
		return getService().createTempCrewDetails(id);
	}

	/**
	* Deletes the temp crew details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp crew details
	* @throws PortalException if a temp crew details with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempCrewDetails(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempCrewDetails(id);
	}

	/**
	* Deletes the temp crew details from the database. Also notifies the appropriate model listeners.
	*
	* @param tempCrewDetails the temp crew details
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempCrewDetails(
		com.fds.nsw.nghiepvu.model.TempCrewDetails tempCrewDetails)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempCrewDetails(tempCrewDetails);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempCrewDetails fetchTempCrewDetails(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempCrewDetails(id);
	}

	/**
	* Returns the temp crew details with the primary key.
	*
	* @param id the primary key of the temp crew details
	* @return the temp crew details
	* @throws PortalException if a temp crew details with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewDetails getTempCrewDetails(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCrewDetails(id);
	}

	

	/**
	* Returns a range of all the temp crew detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp crew detailses
	* @param end the upper bound of the range of temp crew detailses (not inclusive)
	* @return the range of temp crew detailses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewDetails> getTempCrewDetailses(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCrewDetailses(start, end);
	}

	/**
	* Returns the number of temp crew detailses.
	*
	* @return the number of temp crew detailses
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempCrewDetailsesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCrewDetailsesCount();
	}

	/**
	* Updates the temp crew details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempCrewDetails the temp crew details
	* @return the temp crew details that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewDetails updateTempCrewDetails(
		com.fds.nsw.nghiepvu.model.TempCrewDetails tempCrewDetails)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempCrewDetails(tempCrewDetails);
	}

	/**
	* Updates the temp crew details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempCrewDetails the temp crew details
	* @param merge whether to merge the temp crew details with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp crew details that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewDetails updateTempCrewDetails(
		com.fds.nsw.nghiepvu.model.TempCrewDetails tempCrewDetails,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempCrewDetails(tempCrewDetails, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewDetails> findByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewDetails> findByGivenNameAndPassportNumber(
		java.lang.String givenName, java.lang.String passportNumber)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findByGivenNameAndPassportNumber(givenName, passportNumber);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewDetails> findByCrewCode(
		java.lang.String crewCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByCrewCode(crewCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}