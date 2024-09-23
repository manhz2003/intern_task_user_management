
package com.fds.nsw.nghiepvu.result.service.impl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.result.service.finder.ResultNotificationFinderImpl;
import com.fds.nsw.nghiepvu.result.service.persistence.ResultNotificationPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service





/**
 * The implementation of the result notification local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.result.service.ResultNotificationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.result.service.base.ResultNotificationLocalServiceBaseImpl
 * @see vn.gt.dao.result.service.ResultNotificationLocalServiceUtil
 */
public class ResultNotificationLocalServiceImpl
	{ @Autowired
	ResultNotificationPersistenceImpl persistence;@Autowired
	ResultNotificationFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.result.service.ResultNotificationLocalServiceUtil} to access the result notification local service.
	 */
	
	/**
	* Returns all the result notifications where documentName = &#63; and documentYear = &#63;.
	*
	* @param documentName the document name
	* @param documentYear the document year
	* @return the matching result notifications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<ResultNotification> findByDocumentNameAnddocumentYear(
		long documentName, int documentYear){
		try{
			
			return persistence.findByDocumentNameAnddocumentYear(documentName, documentYear);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultNotification findByBusinessTypeCodeOrderbyLastestDate(String businessTypeCode, long documentName, int documentYear) {
		try {
			List<ResultNotification> results = finder.findByBusinessTypeCodeOrderbyLastestDate(businessTypeCode,
					documentName, documentYear);
			if (results != null && results.size() > 0) {
				return results.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultNotification findByBusinessTypeCode(int businessTypeCode, long documentName, int documentYear){
		try {
			List<ResultNotification> resultNotifications = persistence.findByBusinessTypeCode(businessTypeCode, documentName, documentYear);
			if(resultNotifications != null && resultNotifications.size()>0){
				return resultNotifications.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<ResultNotification> findByMaritimeCodeOrderbyLastestDate(String maritimeCode) {
		return finder.findByMaritimeCodeOrderbyLastestDate(maritimeCode);
	}


		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.result.service.ResultNotificationLocalServiceUtil} to access the result notification local service.
		 */

		/**
		 * Adds the result notification to the database. Also notifies the appropriate model listeners.
		 *
		 * @param resultNotification the result notification
		 * @return the result notification that was added
		 * @throws SystemException if a system exception occurred
		 */
		public ResultNotification addResultNotification(
				ResultNotification resultNotification) throws SystemException {

			resultNotification = persistence.updateImpl(resultNotification,
					false);





			return resultNotification;
		}

		/**
		 * Creates a new result notification with the primary key. Does not add the result notification to the database.
		 *
		 * @param id the primary key for the new result notification
		 * @return the new result notification
		 */
		public ResultNotification createResultNotification(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the result notification with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the result notification
		 * @throws PortalException if a result notification with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteResultNotification(long id)
				throws PortalException, SystemException {
			ResultNotification resultNotification = persistence.remove(id);




		}

		/**
		 * Deletes the result notification from the database. Also notifies the appropriate model listeners.
		 *
		 * @param resultNotification the result notification
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteResultNotification(ResultNotification resultNotification)
				throws SystemException {
			persistence.remove(resultNotification);




		}













		public ResultNotification fetchResultNotification(long id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the result notification with the primary key.
		 *
		 * @param id the primary key of the result notification
		 * @return the result notification
		 * @throws PortalException if a result notification with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public ResultNotification getResultNotification(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the result notifications.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of result notifications
		 * @param end the upper bound of the range of result notifications (not inclusive)
		 * @return the range of result notifications
		 * @throws SystemException if a system exception occurred
		 */
		public List<ResultNotification> getResultNotifications(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of result notifications.
		 *
		 * @return the number of result notifications
		 * @throws SystemException if a system exception occurred
		 */
		public int getResultNotificationsCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the result notification in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param resultNotification the result notification
		 * @return the result notification that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public ResultNotification updateResultNotification(
				ResultNotification resultNotification) throws SystemException {
			return updateResultNotification(resultNotification, true);
		}

		/**
		 * Updates the result notification in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param resultNotification the result notification
		 * @param merge whether to merge the result notification with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
		 * @return the result notification that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public ResultNotification updateResultNotification(
				ResultNotification resultNotification, boolean merge)
				throws SystemException {

			resultNotification = persistence.updateImpl(resultNotification,
					merge);





			return resultNotification;
		}
			
}