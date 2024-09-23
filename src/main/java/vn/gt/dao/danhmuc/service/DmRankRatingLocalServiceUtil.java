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
 * The utility for the dm rank rating local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmRankRatingLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmRankRatingLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmRankRatingLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmRankRatingLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmRankRatingLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmRankRatingLocalServiceUtil {
public DmRankRatingLocalServiceUtil(DmRankRatingLocalServiceImpl service) {
DmRankRatingLocalServiceUtil._service = service;
}
public static DmRankRatingLocalServiceImpl getService() {
return _service;
}
private static DmRankRatingLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmRankRatingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm rank rating to the database. Also notifies the appropriate model listeners.
	*
	* @param dmRankRating the dm rank rating
	* @return the dm rank rating that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmRankRating addDmRankRating(
		com.fds.nsw.nghiepvu.model.DmRankRating dmRankRating)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmRankRating(dmRankRating);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmRankRating> findByRankNameVN(
			java.lang.String rankNameVN, int start, int end) {
		return getService().findByRankNameVN(rankNameVN, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmRankRating> findRankRatings(
			java.lang.String rankNameVN, java.lang.String rankName,
			java.lang.String isDelete, java.lang.String rankCodeGroup, int start,
			int end) {
		return getService()
				.findRankRatings(rankNameVN, rankName, isDelete,
						rankCodeGroup, start, end);
	}

	public static long countRankRatings(java.lang.String rankNameVN,
										java.lang.String rankName, java.lang.String isDelete,
										java.lang.String rankCodeGroup) {
		return getService()
				.countRankRatings(rankNameVN, rankName, isDelete,
						rankCodeGroup);
	}


	/**
	* Creates a new dm rank rating with the primary key. Does not add the dm rank rating to the database.
	*
	* @param id the primary key for the new dm rank rating
	* @return the new dm rank rating
	*/
	public static com.fds.nsw.nghiepvu.model.DmRankRating createDmRankRating(
		int id) {
		return getService().createDmRankRating(id);
	}

	/**
	* Deletes the dm rank rating with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm rank rating
	* @throws PortalException if a dm rank rating with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmRankRating(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmRankRating(id);
	}

	/**
	* Deletes the dm rank rating from the database. Also notifies the appropriate model listeners.
	*
	* @param dmRankRating the dm rank rating
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmRankRating(
		com.fds.nsw.nghiepvu.model.DmRankRating dmRankRating)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmRankRating(dmRankRating);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmRankRating fetchDmRankRating(int id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmRankRating(id);
	}

	/**
	* Returns the dm rank rating with the primary key.
	*
	* @param id the primary key of the dm rank rating
	* @return the dm rank rating
	* @throws PortalException if a dm rank rating with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmRankRating getDmRankRating(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmRankRating(id);
	}

	

	/**
	* Returns a range of all the dm rank ratings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm rank ratings
	* @param end the upper bound of the range of dm rank ratings (not inclusive)
	* @return the range of dm rank ratings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmRankRating> getDmRankRatings(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmRankRatings(start, end);
	}

	/**
	* Returns the number of dm rank ratings.
	*
	* @return the number of dm rank ratings
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmRankRatingsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmRankRatingsCount();
	}

	/**
	* Updates the dm rank rating in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmRankRating the dm rank rating
	* @return the dm rank rating that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmRankRating updateDmRankRating(
		com.fds.nsw.nghiepvu.model.DmRankRating dmRankRating)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmRankRating(dmRankRating);
	}

	/**
	* Updates the dm rank rating in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmRankRating the dm rank rating
	* @param merge whether to merge the dm rank rating with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm rank rating that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmRankRating updateDmRankRating(
		com.fds.nsw.nghiepvu.model.DmRankRating dmRankRating, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmRankRating(dmRankRating, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmRankRating getByRankCode(
		java.lang.String rankCode) {
		return getService().getByRankCode(rankCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}