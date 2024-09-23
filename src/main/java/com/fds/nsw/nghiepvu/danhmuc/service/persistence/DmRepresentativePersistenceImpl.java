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

package com.fds.nsw.nghiepvu.danhmuc.service.persistence;

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
import com.fds.nsw.nghiepvu.model.DmRepresentative;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmRepresentativeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmRepresentativeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmRepresentativePersistenceImpl extends BasePersistence {
	@Autowired
	DmRepresentativeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmRepresentative> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmRepresentativeUtil} to access the dm representative persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmRepresentative create(int id) {
		DmRepresentative dmRepresentative = new DmRepresentative();

		
		//dmRepresentative.setPrimaryKey(id);

		return dmRepresentative;
	}

	/**
	 * Removes the dm representative with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm representative
	 * @return the dm representative that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRepresentativeException if a dm representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative remove(int id)
		throws NoSuchDmRepresentativeException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm representative with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm representative
	 * @return the dm representative that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRepresentativeException if a dm representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmRepresentative remove(Serializable primaryKey)
		throws NoSuchDmRepresentativeException, SystemException {
		

		try {
			

			DmRepresentative dmRepresentative = findByPrimaryKey(primaryKey);

			if (dmRepresentative == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmRepresentativeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmRepresentative);
			return dmRepresentative;
		}
		catch (NoSuchDmRepresentativeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmRepresentative remove(DmRepresentative DmRepresentative) throws SystemException {
	removeImpl(DmRepresentative);	return DmRepresentative;
}

protected DmRepresentative removeImpl

(DmRepresentative dmRepresentative)
		throws SystemException {
		try {
			repository.delete(dmRepresentative);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmRepresentative;
	}

	
	public DmRepresentative updateImpl(
		DmRepresentative dmRepresentative, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmRepresentative);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmRepresentative;
	}

	
	public DmRepresentative findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm representative with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmRepresentativeException} if it could not be found.
	 *
	 * @param id the primary key of the dm representative
	 * @return the dm representative
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRepresentativeException if a dm representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative findByPrimaryKey(int id)
		throws NoSuchDmRepresentativeException, SystemException {
		DmRepresentative dmRepresentative = fetchByPrimaryKey(id);

		if (dmRepresentative == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmRepresentativeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmRepresentative;
	}

	/**
	 * Returns the dm representative with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm representative
	 * @return the dm representative, or <code>null</code> if a dm representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmRepresentative fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm representative with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm representative
	 * @return the dm representative, or <code>null</code> if a dm representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative fetchByPrimaryKey(int id) throws SystemException {
		DmRepresentative dmRepresentative = null;

		

		if (dmRepresentative == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmRepresentative> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmRepresentative = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmRepresentative;
	}

	/**
	 * Returns the dm representative where repCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmRepresentativeException} if it could not be found.
	 *
	 * @param repCode the rep code
	 * @return the matching dm representative
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRepresentativeException if a matching dm representative could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative findByRepCode(String repCode)
		throws NoSuchDmRepresentativeException, SystemException {
		DmRepresentative dmRepresentative = fetchByRepCode(repCode);

		if (dmRepresentative == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("repCode=");
			msg.append(repCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmRepresentativeException(msg.toString());
		}

		return dmRepresentative;
	}

	/**
	 * Returns the dm representative where repCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param repCode the rep code
	 * @return the matching dm representative, or <code>null</code> if a matching dm representative could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative fetchByRepCode(String repCode)
		throws SystemException {
		return fetchByRepCode(repCode, true);
	}

	/**
	 * Returns the dm representative where repCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param repCode the rep code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm representative, or <code>null</code> if a matching dm representative could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative fetchByRepCode(String repCode,
		boolean retrieveFromCache) throws SystemException {
		DmRepresentative dmRepresentative = null;
		if (dmRepresentative == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMREPRESENTATIVE_WHERE);

			if (repCode == null) {
				query.append(_FINDER_COLUMN_REPCODE_REPCODE_1);
			}
			else {
				if (repCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REPCODE_REPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REPCODE_REPCODE_2);
				}
			}

			query.append(DmRepresentativeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmRepresentative.class).build();

				

				if (repCode != null) {
					builder.appendNamedParameterMap("repCode", repCode);
				}

				dmRepresentative = (DmRepresentative) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmRepresentative;
	}

	/**
	 * Returns all the dm representatives where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @return the matching dm representatives
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRepresentative> findByMaritimeCode(String maritimeCode)
		throws SystemException {
		return findByMaritimeCode(maritimeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm representatives where maritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param maritimeCode the maritime code
	 * @param start the lower bound of the range of dm representatives
	 * @param end the upper bound of the range of dm representatives (not inclusive)
	 * @return the range of matching dm representatives
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRepresentative> findByMaritimeCode(String maritimeCode,
		int start, int end) throws SystemException {
		return findByMaritimeCode(maritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm representatives where maritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param maritimeCode the maritime code
	 * @param start the lower bound of the range of dm representatives
	 * @param end the upper bound of the range of dm representatives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm representatives
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRepresentative> findByMaritimeCode(String maritimeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmRepresentative> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMREPRESENTATIVE_WHERE);

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmRepresentativeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
				}

				list = (List<DmRepresentative>)queryFactory.getResultList(builder);
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
	 * Returns the first dm representative in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm representative
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRepresentativeException if a matching dm representative could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative findByMaritimeCode_First(String maritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmRepresentativeException, SystemException {
		DmRepresentative dmRepresentative = fetchByMaritimeCode_First(maritimeCode,
				orderByComparator);

		if (dmRepresentative != null) {
			return dmRepresentative;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("maritimeCode=");
		msg.append(maritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmRepresentativeException(msg.toString());
	}

	/**
	 * Returns the first dm representative in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm representative, or <code>null</code> if a matching dm representative could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative fetchByMaritimeCode_First(String maritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmRepresentative> list = findByMaritimeCode(maritimeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm representative in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm representative
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRepresentativeException if a matching dm representative could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative findByMaritimeCode_Last(String maritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmRepresentativeException, SystemException {
		DmRepresentative dmRepresentative = fetchByMaritimeCode_Last(maritimeCode,
				orderByComparator);

		if (dmRepresentative != null) {
			return dmRepresentative;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("maritimeCode=");
		msg.append(maritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmRepresentativeException(msg.toString());
	}

	/**
	 * Returns the last dm representative in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm representative, or <code>null</code> if a matching dm representative could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative fetchByMaritimeCode_Last(String maritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByMaritimeCode(maritimeCode);

		List<DmRepresentative> list = findByMaritimeCode(maritimeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm representatives before and after the current dm representative in the ordered set where maritimeCode = &#63;.
	 *
	 * @param id the primary key of the current dm representative
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm representative
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRepresentativeException if a dm representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative[] findByMaritimeCode_PrevAndNext(int id,
		String maritimeCode, OrderByComparator orderByComparator)
		throws NoSuchDmRepresentativeException, SystemException {
		DmRepresentative dmRepresentative = findByPrimaryKey(id);

		

		try {
			

			DmRepresentative[] array = new DmRepresentative[3];

			array[0] = getByMaritimeCode_PrevAndNext(dmRepresentative,
					maritimeCode, orderByComparator, true);

			array[1] = dmRepresentative;

			array[2] = getByMaritimeCode_PrevAndNext(dmRepresentative,
					maritimeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmRepresentative getByMaritimeCode_PrevAndNext(
		DmRepresentative dmRepresentative, String maritimeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMREPRESENTATIVE_WHERE);

		if (maritimeCode == null) {
			query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1);
		}
		else {
			if (maritimeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(DmRepresentativeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (maritimeCode != null) {
			builder.appendNamedParameterMap("maritimeCode", maritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmRepresentative);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmRepresentative> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm representatives.
	 *
	 * @return the dm representatives
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRepresentative> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm representatives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm representatives
	 * @param end the upper bound of the range of dm representatives (not inclusive)
	 * @return the range of dm representatives
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRepresentative> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm representatives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm representatives
	 * @param end the upper bound of the range of dm representatives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm representatives
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRepresentative> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmRepresentative> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMREPRESENTATIVE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMREPRESENTATIVE.concat(DmRepresentativeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmRepresentative>) queryFactory.getResultList(builder);
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
	 * Removes the dm representative where repCode = &#63; from the database.
	 *
	 * @param repCode the rep code
	 * @return the dm representative that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative removeByRepCode(String repCode)
		throws NoSuchDmRepresentativeException, SystemException {
		DmRepresentative dmRepresentative = findByRepCode(repCode);

		repository.delete(dmRepresentative);
			return dmRepresentative;
	}

	/**
	 * Removes all the dm representatives where maritimeCode = &#63; from the database.
	 *
	 * @param maritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMaritimeCode(String maritimeCode)
		throws SystemException {
		for (DmRepresentative dmRepresentative : findByMaritimeCode(
				maritimeCode)) {
			repository.delete(dmRepresentative);
		}
	}

	/**
	 * Removes all the dm representatives from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmRepresentative dmRepresentative : findAll()) {
			repository.delete(dmRepresentative);
		}
	}

	/**
	 * Returns the number of dm representatives where repCode = &#63;.
	 *
	 * @param repCode the rep code
	 * @return the number of matching dm representatives
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRepCode(String repCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMREPRESENTATIVE_WHERE);

			if (repCode == null) {
				query.append(_FINDER_COLUMN_REPCODE_REPCODE_1);
			}
			else {
				if (repCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REPCODE_REPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REPCODE_REPCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (repCode != null) {
					builder.appendNamedParameterMap("repCode", repCode);
				}

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
	 * Returns the number of dm representatives where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @return the number of matching dm representatives
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMaritimeCode(String maritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMREPRESENTATIVE_WHERE);

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
				}

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
	 * Returns the number of dm representatives.
	 *
	 * @return the number of dm representatives
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMREPRESENTATIVE).build();

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
	 * Initializes the dm representative persistence.
	 */
	private static final String _SQL_SELECT_DMREPRESENTATIVE = "SELECT dmRepresentative FROM DmRepresentative dmRepresentative";
	private static final String _SQL_SELECT_DMREPRESENTATIVE_WHERE = "SELECT dmRepresentative FROM DmRepresentative dmRepresentative WHERE ";
	private static final String _SQL_COUNT_DMREPRESENTATIVE = "SELECT COUNT(dmRepresentative) FROM DmRepresentative dmRepresentative";
	private static final String _SQL_COUNT_DMREPRESENTATIVE_WHERE = "SELECT COUNT(dmRepresentative) FROM DmRepresentative dmRepresentative WHERE ";
	private static final String _FINDER_COLUMN_REPCODE_REPCODE_1 = "dmRepresentative.repCode IS NULL";
	private static final String _FINDER_COLUMN_REPCODE_REPCODE_2 = "dmRepresentative.repCode =:repCode";
	private static final String _FINDER_COLUMN_REPCODE_REPCODE_3 = "(dmRepresentative.repCode IS NULL OR dmRepresentative.repCode =:repCode)";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1 = "dmRepresentative.maritimeCode IS NULL";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2 = "dmRepresentative.maritimeCode =:maritimeCode";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3 = "(dmRepresentative.maritimeCode IS NULL OR dmRepresentative.maritimeCode =:maritimeCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmRepresentative.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmRepresentative exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmRepresentative exists with the key {";
	

	
}
