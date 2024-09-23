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
 * The utility for the issue shifting order chanel local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.IssueShiftingOrderChanelLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see IssueShiftingOrderChanelLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.IssueShiftingOrderChanelLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.IssueShiftingOrderChanelLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.IssueShiftingOrderChanelLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class IssueShiftingOrderChanelLocalServiceUtil {
public IssueShiftingOrderChanelLocalServiceUtil(IssueShiftingOrderChanelLocalServiceImpl service) {
IssueShiftingOrderChanelLocalServiceUtil._service = service;
}
public static IssueShiftingOrderChanelLocalServiceImpl getService() {
return _service;
}
private static IssueShiftingOrderChanelLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.IssueShiftingOrderChanelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the issue shifting order chanel to the database. Also notifies the appropriate model listeners.
	*
	* @param issueShiftingOrderChanel the issue shifting order chanel
	* @return the issue shifting order chanel that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel addIssueShiftingOrderChanel(
		com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel issueShiftingOrderChanel)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addIssueShiftingOrderChanel(issueShiftingOrderChanel);
	}

	/**
	* Creates a new issue shifting order chanel with the primary key. Does not add the issue shifting order chanel to the database.
	*
	* @param issueShiftingOrderChanelPK the primary key for the new issue shifting order chanel
	* @return the new issue shifting order chanel
	*/
	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel createIssueShiftingOrderChanel(
			com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanelId issueShiftingOrderChanelPK) {
		return getService()
				   .createIssueShiftingOrderChanel(issueShiftingOrderChanelPK);
	}

	/**
	* Deletes the issue shifting order chanel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param issueShiftingOrderChanelPK the primary key of the issue shifting order chanel
	* @throws PortalException if a issue shifting order chanel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteIssueShiftingOrderChanel(
			com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanelId issueShiftingOrderChanelPK)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteIssueShiftingOrderChanel(issueShiftingOrderChanelPK);
	}

	/**
	* Deletes the issue shifting order chanel from the database. Also notifies the appropriate model listeners.
	*
	* @param issueShiftingOrderChanel the issue shifting order chanel
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteIssueShiftingOrderChanel(
		com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel issueShiftingOrderChanel)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteIssueShiftingOrderChanel(issueShiftingOrderChanel);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel fetchIssueShiftingOrderChanel(
			com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanelId issueShiftingOrderChanelPK)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .fetchIssueShiftingOrderChanel(issueShiftingOrderChanelPK);
	}

	/**
	* Returns the issue shifting order chanel with the primary key.
	*
	* @param issueShiftingOrderChanelPK the primary key of the issue shifting order chanel
	* @return the issue shifting order chanel
	* @throws PortalException if a issue shifting order chanel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel getIssueShiftingOrderChanel(
			com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanelId issueShiftingOrderChanelPK)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .getIssueShiftingOrderChanel(issueShiftingOrderChanelPK);
	}

	

	/**
	* Returns a range of all the issue shifting order chanels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of issue shifting order chanels
	* @param end the upper bound of the range of issue shifting order chanels (not inclusive)
	* @return the range of issue shifting order chanels
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel> getIssueShiftingOrderChanels(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getIssueShiftingOrderChanels(start, end);
	}

	/**
	* Returns the number of issue shifting order chanels.
	*
	* @return the number of issue shifting order chanels
	* @throws SystemException if a system exception occurred
	*/
	public static int getIssueShiftingOrderChanelsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getIssueShiftingOrderChanelsCount();
	}

	/**
	* Updates the issue shifting order chanel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param issueShiftingOrderChanel the issue shifting order chanel
	* @return the issue shifting order chanel that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel updateIssueShiftingOrderChanel(
		com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel issueShiftingOrderChanel)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateIssueShiftingOrderChanel(issueShiftingOrderChanel);
	}

	/**
	* Updates the issue shifting order chanel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param issueShiftingOrderChanel the issue shifting order chanel
	* @param merge whether to merge the issue shifting order chanel with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the issue shifting order chanel that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel updateIssueShiftingOrderChanel(
		com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel issueShiftingOrderChanel,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateIssueShiftingOrderChanel(issueShiftingOrderChanel,
			merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel> findByShiftingOrderId(
		long shiftingOrderId) {
		return getService().findByShiftingOrderId(shiftingOrderId);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel> getShiftingOrderChanel(
		long shiftingOrderId)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getShiftingOrderChanel(shiftingOrderId);
	}

	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel getShiftingOrderChanel(
		long shiftingOrderId, java.lang.String chanelCode)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getShiftingOrderChanel(shiftingOrderId, chanelCode);
	}

	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel addShiftingOrderChanel(
		long shiftingOrderId, java.lang.String chanelCode,
		java.lang.String chanelName, int order)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .addShiftingOrderChanel(shiftingOrderId, chanelCode,
			chanelName, order);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}