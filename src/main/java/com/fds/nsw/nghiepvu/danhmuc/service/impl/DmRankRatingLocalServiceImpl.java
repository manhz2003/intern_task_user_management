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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmRankRatingFinderImpl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmRankRatingPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service

/**
 * The implementation of the dm rank rating local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmRankRatingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmRankRatingLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmRankRatingLocalServiceUtil
 */
public class DmRankRatingLocalServiceImpl
	{ @Autowired
	DmRankRatingPersistenceImpl persistence;

		@Autowired
		DmRankRatingFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmRankRatingLocalServiceUtil} to access the dm rank rating local service.
	 */


		public List<DmRankRating> findByRankNameVN(String rankNameVN, int start,
												   int end) {
			try {
				return persistence.findByF_rankNameVNbyLike(rankNameVN,
						start, end);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		public List<DmRankRating> findRankRatings(String rankNameVN,
												  String rankName, String isDelete, String rankCodeGroup, int start,
												  int end) {
			try {
				return finder.findRankRatings(rankNameVN, rankName,
						isDelete, rankCodeGroup, start, end);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		}

		public long countRankRatings(String rankNameVN, String rankName,
									 String isDelete, String rankCodeGroup) {
			try {
				return finder.countRankRatings(rankNameVN, rankName,
						isDelete, rankCodeGroup);
			} catch (Exception e) {
				log.error(e.getMessage());
				return 0;
			}
		}

		public DmRankRating getByRankCode(String rankCode) {
		try {
			List<DmRankRating> dmRankRatinges = persistence.findByRankCode(rankCode);
			if (dmRankRatinges != null && dmRankRatinges.size() > 0) { return dmRankRatinges.get(0); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmRankRatingLocalServiceUtil} to access the dm rank rating local service.
		 */

		/**
		 * Adds the dm rank rating to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmRankRating the dm rank rating
		 * @return the dm rank rating that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmRankRating addDmRankRating(DmRankRating dmRankRating)
				throws SystemException {

			dmRankRating = persistence.updateImpl(dmRankRating, false);





			return dmRankRating;
		}

		/**
		 * Creates a new dm rank rating with the primary key. Does not add the dm rank rating to the database.
		 *
		 * @param id the primary key for the new dm rank rating
		 * @return the new dm rank rating
		 */
		public DmRankRating createDmRankRating(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm rank rating with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm rank rating
		 * @throws PortalException if a dm rank rating with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmRankRating(int id)
				throws PortalException, SystemException {
			DmRankRating dmRankRating = persistence.remove(id);




		}

		/**
		 * Deletes the dm rank rating from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmRankRating the dm rank rating
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmRankRating(DmRankRating dmRankRating)
				throws SystemException {
			persistence.remove(dmRankRating);




		}













		public DmRankRating fetchDmRankRating(int id) throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm rank rating with the primary key.
		 *
		 * @param id the primary key of the dm rank rating
		 * @return the dm rank rating
		 * @throws PortalException if a dm rank rating with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmRankRating getDmRankRating(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
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
		public List<DmRankRating> getDmRankRatings(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm rank ratings.
		 *
		 * @return the number of dm rank ratings
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmRankRatingsCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm rank rating in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmRankRating the dm rank rating
		 * @return the dm rank rating that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmRankRating updateDmRankRating(DmRankRating dmRankRating)
				throws SystemException {
			return updateDmRankRating(dmRankRating, true);
		}

		/**
		 * Updates the dm rank rating in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmRankRating the dm rank rating
		 * @param merge whether to merge the dm rank rating with the current session. See  for an explanation.
		 * @return the dm rank rating that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmRankRating updateDmRankRating(DmRankRating dmRankRating,
											   boolean merge) throws SystemException {

			dmRankRating = persistence.updateImpl(dmRankRating, merge);





			return dmRankRating;
		}
	
}