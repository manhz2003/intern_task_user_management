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
 * The utility for the dm history rank rating local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryRankRatingLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryRankRatingLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryRankRatingLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryRankRatingLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryRankRatingLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryRankRatingLocalServiceUtil {
public DmHistoryRankRatingLocalServiceUtil(DmHistoryRankRatingLocalServiceImpl service) {
DmHistoryRankRatingLocalServiceUtil._service = service;
}
public static DmHistoryRankRatingLocalServiceImpl getService() {
return _service;
}
private static DmHistoryRankRatingLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryRankRatingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history rank rating to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryRankRating the dm history rank rating
	* @return the dm history rank rating that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryRankRating addDmHistoryRankRating(
		com.fds.nsw.nghiepvu.model.DmHistoryRankRating dmHistoryRankRating)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryRankRating(dmHistoryRankRating);
	}

	/**
	* Creates a new dm history rank rating with the primary key. Does not add the dm history rank rating to the database.
	*
	* @param id the primary key for the new dm history rank rating
	* @return the new dm history rank rating
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryRankRating createDmHistoryRankRating(
		int id) {
		return getService().createDmHistoryRankRating(id);
	}

	/**
	* Deletes the dm history rank rating with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history rank rating
	* @throws PortalException if a dm history rank rating with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryRankRating(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryRankRating(id);
	}

	/**
	* Deletes the dm history rank rating from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryRankRating the dm history rank rating
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryRankRating(
		com.fds.nsw.nghiepvu.model.DmHistoryRankRating dmHistoryRankRating)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryRankRating(dmHistoryRankRating);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryRankRating fetchDmHistoryRankRating(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryRankRating(id);
	}

	/**
	* Returns the dm history rank rating with the primary key.
	*
	* @param id the primary key of the dm history rank rating
	* @return the dm history rank rating
	* @throws PortalException if a dm history rank rating with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryRankRating getDmHistoryRankRating(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryRankRating(id);
	}

	

	/**
	* Returns a range of all the dm history rank ratings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history rank ratings
	* @param end the upper bound of the range of dm history rank ratings (not inclusive)
	* @return the range of dm history rank ratings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryRankRating> getDmHistoryRankRatings(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryRankRatings(start, end);
	}

	/**
	* Returns the number of dm history rank ratings.
	*
	* @return the number of dm history rank ratings
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryRankRatingsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryRankRatingsCount();
	}

	/**
	* Updates the dm history rank rating in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryRankRating the dm history rank rating
	* @return the dm history rank rating that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryRankRating updateDmHistoryRankRating(
		com.fds.nsw.nghiepvu.model.DmHistoryRankRating dmHistoryRankRating)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryRankRating(dmHistoryRankRating);
	}

	/**
	* Updates the dm history rank rating in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryRankRating the dm history rank rating
	* @param merge whether to merge the dm history rank rating with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history rank rating that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryRankRating updateDmHistoryRankRating(
		com.fds.nsw.nghiepvu.model.DmHistoryRankRating dmHistoryRankRating,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryRankRating(dmHistoryRankRating, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryRankRating getByRankCodeAndSyncVersion(
		java.lang.String rankCode, java.lang.String syncVersion) {
		return getService().getByRankCodeAndSyncVersion(rankCode, syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	


}