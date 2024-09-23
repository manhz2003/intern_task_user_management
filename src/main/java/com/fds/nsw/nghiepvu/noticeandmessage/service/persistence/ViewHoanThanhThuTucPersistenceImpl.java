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

package com.fds.nsw.nghiepvu.noticeandmessage.service.persistence;

import java.io.Serializable;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fds.flex.common.utility.string.StringBundler;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.kernel.dao.orm.BasePersistence;
import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.kernel.orm.exception.NoSuchModelException;
import com.fds.nsw.kernel.util.OrderByComparator;
import com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.ViewHoanThanhThuTucRepository;
import com.fds.nsw.nghiepvu.modelImpl.ViewHoanThanhThuTucModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ViewHoanThanhThuTucPersistenceImpl extends BasePersistence {
	@Autowired
	ViewHoanThanhThuTucRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<ViewHoanThanhThuTuc> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ViewHoanThanhThuTucUtil} to access the view hoan thanh thu tuc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public ViewHoanThanhThuTuc create(String CVHH) {
		ViewHoanThanhThuTuc viewHoanThanhThuTuc = new ViewHoanThanhThuTuc();

		
		//viewHoanThanhThuTuc.setPrimaryKey(id);

		return viewHoanThanhThuTuc;
	}

	/**
	 * Removes the view hoan thanh thu tuc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CVHH the primary key of the view hoan thanh thu tuc
	 * @return the view hoan thanh thu tuc that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchViewHoanThanhThuTucException if a view hoan thanh thu tuc with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ViewHoanThanhThuTuc remove(String CVHH)
		throws NoSuchViewHoanThanhThuTucException, SystemException {
		return remove((Serializable)CVHH);
	}

	/**
	 * Removes the view hoan thanh thu tuc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the view hoan thanh thu tuc
	 * @return the view hoan thanh thu tuc that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchViewHoanThanhThuTucException if a view hoan thanh thu tuc with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public ViewHoanThanhThuTuc remove(Serializable primaryKey)
		throws NoSuchViewHoanThanhThuTucException, SystemException {
		

		try {
			

			ViewHoanThanhThuTuc viewHoanThanhThuTuc = findByPrimaryKey(primaryKey);

			if (viewHoanThanhThuTuc == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchViewHoanThanhThuTucException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(viewHoanThanhThuTuc);
			return viewHoanThanhThuTuc;
		}
		catch (NoSuchViewHoanThanhThuTucException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public ViewHoanThanhThuTuc remove(ViewHoanThanhThuTuc ViewHoanThanhThuTuc) throws SystemException {
	removeImpl(ViewHoanThanhThuTuc);
	return ViewHoanThanhThuTuc;
}

protected ViewHoanThanhThuTuc removeImpl(
		ViewHoanThanhThuTuc viewHoanThanhThuTuc) throws SystemException {
		try {
			repository.delete(viewHoanThanhThuTuc);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return viewHoanThanhThuTuc;
	}

	
	public ViewHoanThanhThuTuc updateImpl(
		com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc viewHoanThanhThuTuc,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(viewHoanThanhThuTuc);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return viewHoanThanhThuTuc;
	}

	
	public ViewHoanThanhThuTuc findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey((String)primaryKey);
	}

	/**
	 * Returns the view hoan thanh thu tuc with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchViewHoanThanhThuTucException} if it could not be found.
	 *
	 * @param CVHH the primary key of the view hoan thanh thu tuc
	 * @return the view hoan thanh thu tuc
	 * @throws vn.gt.dao.noticeandmessage.NoSuchViewHoanThanhThuTucException if a view hoan thanh thu tuc with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ViewHoanThanhThuTuc findByPrimaryKey(String CVHH)
		throws NoSuchViewHoanThanhThuTucException, SystemException {
		ViewHoanThanhThuTuc viewHoanThanhThuTuc = fetchByPrimaryKey(CVHH);

		if (viewHoanThanhThuTuc == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + CVHH);
			}

			throw new NoSuchViewHoanThanhThuTucException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				CVHH);
		}

		return viewHoanThanhThuTuc;
	}

	/**
	 * Returns the view hoan thanh thu tuc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the view hoan thanh thu tuc
	 * @return the view hoan thanh thu tuc, or <code>null</code> if a view hoan thanh thu tuc with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public ViewHoanThanhThuTuc fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey((String)primaryKey);
	}

	/**
	 * Returns the view hoan thanh thu tuc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CVHH the primary key of the view hoan thanh thu tuc
	 * @return the view hoan thanh thu tuc, or <code>null</code> if a view hoan thanh thu tuc with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ViewHoanThanhThuTuc fetchByPrimaryKey(String CVHH)
		throws SystemException {
		ViewHoanThanhThuTuc viewHoanThanhThuTuc = null;

		

		if (viewHoanThanhThuTuc == null) {
			

			boolean hasException = false;

			try {
				

				Optional<ViewHoanThanhThuTuc> optional = repository.findByCvhh(CVHH);
				viewHoanThanhThuTuc = optional.get();
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return viewHoanThanhThuTuc;
	}

	/**
	 * Returns all the view hoan thanh thu tucs.
	 *
	 * @return the view hoan thanh thu tucs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ViewHoanThanhThuTuc> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the view hoan thanh thu tucs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of view hoan thanh thu tucs
	 * @param end the upper bound of the range of view hoan thanh thu tucs (not inclusive)
	 * @return the range of view hoan thanh thu tucs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ViewHoanThanhThuTuc> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the view hoan thanh thu tucs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of view hoan thanh thu tucs
	 * @param end the upper bound of the range of view hoan thanh thu tucs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of view hoan thanh thu tucs
	 * @throws SystemException if a system exception occurred
	 */
	public List<ViewHoanThanhThuTuc> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ViewHoanThanhThuTuc> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VIEWHOANTHANHTHUTUC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VIEWHOANTHANHTHUTUC.concat(ViewHoanThanhThuTucModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<ViewHoanThanhThuTuc>) queryFactory.getResultList(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}

		return list;
	}

	/**
	 * Removes all the view hoan thanh thu tucs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ViewHoanThanhThuTuc viewHoanThanhThuTuc : findAll()) {
			repository.delete(viewHoanThanhThuTuc);
		}
	}

	/**
	 * Returns the number of view hoan thanh thu tucs.
	 *
	 * @return the number of view hoan thanh thu tucs
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VIEWHOANTHANHTHUTUC).build();

				count = (Long)queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				

				
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the view hoan thanh thu tuc persistence.
	 */
	private static final String _SQL_SELECT_VIEWHOANTHANHTHUTUC = "SELECT viewHoanThanhThuTuc FROM ViewHoanThanhThuTuc viewHoanThanhThuTuc";
	private static final String _SQL_COUNT_VIEWHOANTHANHTHUTUC = "SELECT COUNT(viewHoanThanhThuTuc) FROM ViewHoanThanhThuTuc viewHoanThanhThuTuc";
	private static final String _ORDER_BY_ENTITY_ALIAS = "viewHoanThanhThuTuc.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ViewHoanThanhThuTuc exists with the primary key ";
	

	
}
