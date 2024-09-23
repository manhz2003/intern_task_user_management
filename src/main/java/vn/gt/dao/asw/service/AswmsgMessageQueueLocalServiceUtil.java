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

package vn.gt.dao.asw.service;






/**
 * The utility for the aswmsg message queue local service. This utility wraps {@link vn.dao.gt.asw.service.impl.AswmsgMessageQueueLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win 64
 * @see AswmsgMessageQueueLocalService
 * @see vn.dao.gt.asw.service.base.AswmsgMessageQueueLocalServiceBaseImpl
 * @see vn.dao.gt.asw.service.impl.AswmsgMessageQueueLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.asw.service.impl.AswmsgMessageQueueLocalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component public class AswmsgMessageQueueLocalServiceUtil {
public AswmsgMessageQueueLocalServiceUtil(AswmsgMessageQueueLocalServiceImpl service) {
AswmsgMessageQueueLocalServiceUtil._service = service;
}
public static AswmsgMessageQueueLocalServiceImpl getService() {
return _service;
}
private static AswmsgMessageQueueLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.dao.gt.asw.service.impl.AswmsgMessageQueueLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the aswmsg message queue to the database. Also notifies the appropriate model listeners.
	*
	* @param aswmsgMessageQueue the aswmsg message queue
	* @return the aswmsg message queue that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.AswmsgMessagequeue addAswmsgMessageQueue(
		com.fds.nsw.nghiepvu.model.AswmsgMessagequeue aswmsgMessageQueue)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addAswmsgMessageQueue(aswmsgMessageQueue);
	}

	/**
	* Creates a new aswmsg message queue with the primary key. Does not add the aswmsg message queue to the database.
	*
	* @param id the primary key for the new aswmsg message queue
	* @return the new aswmsg message queue
	*/
	public static com.fds.nsw.nghiepvu.model.AswmsgMessagequeue createAswmsgMessageQueue(
		long id) {
		return getService().createAswmsgMessageQueue(id);
	}

	/**
	* Deletes the aswmsg message queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the aswmsg message queue
	* @throws PortalException if a aswmsg message queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAswmsgMessageQueue(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteAswmsgMessageQueue(id);
	}

	/**
	* Deletes the aswmsg message queue from the database. Also notifies the appropriate model listeners.
	*
	* @param aswmsgMessageQueue the aswmsg message queue
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAswmsgMessageQueue(
		com.fds.nsw.nghiepvu.model.AswmsgMessagequeue aswmsgMessageQueue)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteAswmsgMessageQueue(aswmsgMessageQueue);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.AswmsgMessagequeue fetchAswmsgMessageQueue(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchAswmsgMessageQueue(id);
	}

	/**
	* Returns the aswmsg message queue with the primary key.
	*
	* @param id the primary key of the aswmsg message queue
	* @return the aswmsg message queue
	* @throws PortalException if a aswmsg message queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.AswmsgMessagequeue getAswmsgMessageQueue(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getAswmsgMessageQueue(id);
	}

	

	/**
	* Returns a range of all the aswmsg message queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of aswmsg message queues
	* @param end the upper bound of the range of aswmsg message queues (not inclusive)
	* @return the range of aswmsg message queues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.AswmsgMessagequeue> getAswmsgMessageQueues(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getAswmsgMessageQueues(start, end);
	}

	/**
	* Returns the number of aswmsg message queues.
	*
	* @return the number of aswmsg message queues
	* @throws SystemException if a system exception occurred
	*/
	public static int getAswmsgMessageQueuesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getAswmsgMessageQueuesCount();
	}

	/**
	* Updates the aswmsg message queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param aswmsgMessageQueue the aswmsg message queue
	* @return the aswmsg message queue that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.AswmsgMessagequeue updateAswmsgMessageQueue(
		com.fds.nsw.nghiepvu.model.AswmsgMessagequeue aswmsgMessageQueue)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateAswmsgMessageQueue(aswmsgMessageQueue);
	}

	/**
	* Updates the aswmsg message queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param aswmsgMessageQueue the aswmsg message queue
	* @param merge whether to merge the aswmsg message queue with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the aswmsg message queue that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.AswmsgMessagequeue updateAswmsgMessageQueue(
		com.fds.nsw.nghiepvu.model.AswmsgMessagequeue aswmsgMessageQueue, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateAswmsgMessageQueue(aswmsgMessageQueue, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.AswmsgMessagequeue> getListMessage2Queue(
		int count) {
		return getService().getListMessage2Queue(count);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.AswmsgMessagequeue> getListMessageQueuePending(
		java.lang.String validationCode) {
		return getService().getListMessageQueuePending(validationCode);
	}

	public static boolean updatePriorityAswmsgMessageQueue(int priority, long id) {
		return getService().updatePriorityAswmsgMessageQueue(priority, id);
	}

	public static boolean updateListMessageQueuePending(
		java.lang.String validationCode) {
		return getService().updateListMessageQueuePending(validationCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	


}