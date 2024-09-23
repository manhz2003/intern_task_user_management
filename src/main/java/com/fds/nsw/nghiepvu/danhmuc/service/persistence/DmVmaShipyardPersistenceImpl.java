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
import com.fds.nsw.nghiepvu.model.DmVmaShipyard;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmVmaShipyardRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmVmaShipyardModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmVmaShipyardPersistenceImpl extends BasePersistence {
	@Autowired
	DmVmaShipyardRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaShipyard> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmVmaShipyardUtil} to access the dm vma shipyard persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmVmaShipyard create(long id) {
		DmVmaShipyard dmVmaShipyard = new DmVmaShipyard();

		
		//dmVmaShipyard.setPrimaryKey(id);

		return dmVmaShipyard;
	}

	/**
	 * Removes the dm vma shipyard with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm vma shipyard
	 * @return the dm vma shipyard that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipyardException if a dm vma shipyard with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard remove(long id)
		throws NoSuchDmVmaShipyardException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm vma shipyard with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm vma shipyard
	 * @return the dm vma shipyard that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipyardException if a dm vma shipyard with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaShipyard remove(Serializable primaryKey)
		throws NoSuchDmVmaShipyardException, SystemException {
		

		try {
			

			DmVmaShipyard dmVmaShipyard = findByPrimaryKey(primaryKey);

			if (dmVmaShipyard == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmVmaShipyardException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmVmaShipyard);
			return dmVmaShipyard;
		}
		catch (NoSuchDmVmaShipyardException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmVmaShipyard remove(DmVmaShipyard DmVmaShipyard) throws SystemException {
	removeImpl(DmVmaShipyard);	return DmVmaShipyard;
}

protected DmVmaShipyard removeImpl

(DmVmaShipyard dmVmaShipyard)
		throws SystemException {
		try {
			repository.delete(dmVmaShipyard);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaShipyard;
	}

	
	public DmVmaShipyard updateImpl(
		DmVmaShipyard dmVmaShipyard, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmVmaShipyard);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaShipyard;
	}

	
	public DmVmaShipyard findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma shipyard with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaShipyardException} if it could not be found.
	 *
	 * @param id the primary key of the dm vma shipyard
	 * @return the dm vma shipyard
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipyardException if a dm vma shipyard with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard findByPrimaryKey(long id)
		throws NoSuchDmVmaShipyardException, SystemException {
		DmVmaShipyard dmVmaShipyard = fetchByPrimaryKey(id);

		if (dmVmaShipyard == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmVmaShipyardException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmVmaShipyard;
	}

	/**
	 * Returns the dm vma shipyard with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm vma shipyard
	 * @return the dm vma shipyard, or <code>null</code> if a dm vma shipyard with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaShipyard fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma shipyard with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm vma shipyard
	 * @return the dm vma shipyard, or <code>null</code> if a dm vma shipyard with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard fetchByPrimaryKey(long id) throws SystemException {
		DmVmaShipyard dmVmaShipyard = null;

		

		if (dmVmaShipyard == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmVmaShipyard> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmVmaShipyard = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmVmaShipyard;
	}

	/**
	 * Returns all the dm vma shipyards where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the matching dm vma shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipyard> findByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		return findByF_maritimeCode(MaritimeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma shipyards where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm vma shipyards
	 * @param end the upper bound of the range of dm vma shipyards (not inclusive)
	 * @return the range of matching dm vma shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipyard> findByF_maritimeCode(String MaritimeCode,
		int start, int end) throws SystemException {
		return findByF_maritimeCode(MaritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma shipyards where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm vma shipyards
	 * @param end the upper bound of the range of dm vma shipyards (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm vma shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipyard> findByF_maritimeCode(String MaritimeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmVmaShipyard> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMVMASHIPYARD_WHERE);

			if (MaritimeCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (MaritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmVmaShipyardModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
				}

				list = (List<DmVmaShipyard>)queryFactory.getResultList(builder);
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
	 * Returns the first dm vma shipyard in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma shipyard
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipyardException if a matching dm vma shipyard could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard findByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaShipyardException, SystemException {
		DmVmaShipyard dmVmaShipyard = fetchByF_maritimeCode_First(MaritimeCode,
				orderByComparator);

		if (dmVmaShipyard != null) {
			return dmVmaShipyard;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaShipyardException(msg.toString());
	}

	/**
	 * Returns the first dm vma shipyard in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma shipyard, or <code>null</code> if a matching dm vma shipyard could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard fetchByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaShipyard> list = findByF_maritimeCode(MaritimeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm vma shipyard in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma shipyard
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipyardException if a matching dm vma shipyard could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard findByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaShipyardException, SystemException {
		DmVmaShipyard dmVmaShipyard = fetchByF_maritimeCode_Last(MaritimeCode,
				orderByComparator);

		if (dmVmaShipyard != null) {
			return dmVmaShipyard;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaShipyardException(msg.toString());
	}

	/**
	 * Returns the last dm vma shipyard in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma shipyard, or <code>null</code> if a matching dm vma shipyard could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard fetchByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_maritimeCode(MaritimeCode);

		List<DmVmaShipyard> list = findByF_maritimeCode(MaritimeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm vma shipyards before and after the current dm vma shipyard in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param id the primary key of the current dm vma shipyard
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm vma shipyard
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipyardException if a dm vma shipyard with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard[] findByF_maritimeCode_PrevAndNext(long id,
		String MaritimeCode, OrderByComparator orderByComparator)
		throws NoSuchDmVmaShipyardException, SystemException {
		DmVmaShipyard dmVmaShipyard = findByPrimaryKey(id);

		

		try {
			

			DmVmaShipyard[] array = new DmVmaShipyard[3];

			array[0] = getByF_maritimeCode_PrevAndNext(dmVmaShipyard,
					MaritimeCode, orderByComparator, true);

			array[1] = dmVmaShipyard;

			array[2] = getByF_maritimeCode_PrevAndNext(dmVmaShipyard,
					MaritimeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmVmaShipyard getByF_maritimeCode_PrevAndNext(
		DmVmaShipyard dmVmaShipyard, String MaritimeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMVMASHIPYARD_WHERE);

		if (MaritimeCode == null) {
			query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1);
		}
		else {
			if (MaritimeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2);
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
			query.append(DmVmaShipyardModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (MaritimeCode != null) {
			builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmVmaShipyard);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmVmaShipyard> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm vma shipyard where ShipYardCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaShipyardException} if it could not be found.
	 *
	 * @param ShipYardCode the ship yard code
	 * @return the matching dm vma shipyard
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipyardException if a matching dm vma shipyard could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard findByF_shipYardCode(String ShipYardCode)
		throws NoSuchDmVmaShipyardException, SystemException {
		DmVmaShipyard dmVmaShipyard = fetchByF_shipYardCode(ShipYardCode);

		if (dmVmaShipyard == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ShipYardCode=");
			msg.append(ShipYardCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmVmaShipyardException(msg.toString());
		}

		return dmVmaShipyard;
	}

	/**
	 * Returns the dm vma shipyard where ShipYardCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ShipYardCode the ship yard code
	 * @return the matching dm vma shipyard, or <code>null</code> if a matching dm vma shipyard could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard fetchByF_shipYardCode(String ShipYardCode)
		throws SystemException {
		return fetchByF_shipYardCode(ShipYardCode, true);
	}

	/**
	 * Returns the dm vma shipyard where ShipYardCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ShipYardCode the ship yard code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm vma shipyard, or <code>null</code> if a matching dm vma shipyard could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard fetchByF_shipYardCode(String ShipYardCode,
		boolean retrieveFromCache) throws SystemException {
		DmVmaShipyard dmVmaShipyard = null;
		if (dmVmaShipyard == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMVMASHIPYARD_WHERE);

			if (ShipYardCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SHIPYARDCODE_1);
			}
			else {
				if (ShipYardCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SHIPYARDCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SHIPYARDCODE_2);
				}
			}

			query.append(DmVmaShipyardModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmVmaShipyard.class).build();

				

				if (ShipYardCode != null) {
					builder.appendNamedParameterMap("ShipYardCode", ShipYardCode);
				}

				dmVmaShipyard = (DmVmaShipyard) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmVmaShipyard;
	}

	/**
	 * Returns the dm vma shipyard where TaxCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaShipyardException} if it could not be found.
	 *
	 * @param TaxCode the tax code
	 * @return the matching dm vma shipyard
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipyardException if a matching dm vma shipyard could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard findByF_taxCode(String TaxCode)
		throws NoSuchDmVmaShipyardException, SystemException {
		DmVmaShipyard dmVmaShipyard = fetchByF_taxCode(TaxCode);

		if (dmVmaShipyard == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("TaxCode=");
			msg.append(TaxCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmVmaShipyardException(msg.toString());
		}

		return dmVmaShipyard;
	}

	/**
	 * Returns the dm vma shipyard where TaxCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param TaxCode the tax code
	 * @return the matching dm vma shipyard, or <code>null</code> if a matching dm vma shipyard could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard fetchByF_taxCode(String TaxCode)
		throws SystemException {
		return fetchByF_taxCode(TaxCode, true);
	}

	/**
	 * Returns the dm vma shipyard where TaxCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param TaxCode the tax code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm vma shipyard, or <code>null</code> if a matching dm vma shipyard could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard fetchByF_taxCode(String TaxCode,
		boolean retrieveFromCache) throws SystemException {
		DmVmaShipyard dmVmaShipyard = null;
		if (dmVmaShipyard == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMVMASHIPYARD_WHERE);

			if (TaxCode == null) {
				query.append(_FINDER_COLUMN_F_TAXCODE_TAXCODE_1);
			}
			else {
				if (TaxCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TAXCODE_TAXCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TAXCODE_TAXCODE_2);
				}
			}

			query.append(DmVmaShipyardModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmVmaShipyard.class).build();

				

				if (TaxCode != null) {
					builder.appendNamedParameterMap("TaxCode", TaxCode);
				}

				dmVmaShipyard = (DmVmaShipyard) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmVmaShipyard;
	}

	/**
	 * Returns all the dm vma shipyards.
	 *
	 * @return the dm vma shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipyard> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma shipyards.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma shipyards
	 * @param end the upper bound of the range of dm vma shipyards (not inclusive)
	 * @return the range of dm vma shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipyard> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma shipyards.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma shipyards
	 * @param end the upper bound of the range of dm vma shipyards (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm vma shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipyard> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaShipyard> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMVMASHIPYARD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMVMASHIPYARD.concat(DmVmaShipyardModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmVmaShipyard>) queryFactory.getResultList(builder);
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
	 * Removes all the dm vma shipyards where MaritimeCode = &#63; from the database.
	 *
	 * @param MaritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		for (DmVmaShipyard dmVmaShipyard : findByF_maritimeCode(MaritimeCode)) {
			repository.delete(dmVmaShipyard);
		}
	}

	/**
	 * Removes the dm vma shipyard where ShipYardCode = &#63; from the database.
	 *
	 * @param ShipYardCode the ship yard code
	 * @return the dm vma shipyard that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard removeByF_shipYardCode(String ShipYardCode)
		throws NoSuchDmVmaShipyardException, SystemException {
		DmVmaShipyard dmVmaShipyard = findByF_shipYardCode(ShipYardCode);

		repository.delete(dmVmaShipyard);
			return dmVmaShipyard;
	}

	/**
	 * Removes the dm vma shipyard where TaxCode = &#63; from the database.
	 *
	 * @param TaxCode the tax code
	 * @return the dm vma shipyard that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipyard removeByF_taxCode(String TaxCode)
		throws NoSuchDmVmaShipyardException, SystemException {
		DmVmaShipyard dmVmaShipyard = findByF_taxCode(TaxCode);

		repository.delete(dmVmaShipyard);
			return dmVmaShipyard;
	}

	/**
	 * Removes all the dm vma shipyards from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmVmaShipyard dmVmaShipyard : findAll()) {
			repository.delete(dmVmaShipyard);
		}
	}

	/**
	 * Returns the number of dm vma shipyards where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the number of matching dm vma shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMASHIPYARD_WHERE);

			if (MaritimeCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (MaritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
				}

				count = (Long) queryFactory.getSingleResult(builder);
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
	 * Returns the number of dm vma shipyards where ShipYardCode = &#63;.
	 *
	 * @param ShipYardCode the ship yard code
	 * @return the number of matching dm vma shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_shipYardCode(String ShipYardCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMASHIPYARD_WHERE);

			if (ShipYardCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SHIPYARDCODE_1);
			}
			else {
				if (ShipYardCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SHIPYARDCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SHIPYARDCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (ShipYardCode != null) {
					builder.appendNamedParameterMap("ShipYardCode", ShipYardCode);
				}

				count = (Long) queryFactory.getSingleResult(builder);
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
	 * Returns the number of dm vma shipyards where TaxCode = &#63;.
	 *
	 * @param TaxCode the tax code
	 * @return the number of matching dm vma shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_taxCode(String TaxCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMASHIPYARD_WHERE);

			if (TaxCode == null) {
				query.append(_FINDER_COLUMN_F_TAXCODE_TAXCODE_1);
			}
			else {
				if (TaxCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TAXCODE_TAXCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TAXCODE_TAXCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (TaxCode != null) {
					builder.appendNamedParameterMap("TaxCode", TaxCode);
				}

				count = (Long) queryFactory.getSingleResult(builder);
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
	 * Returns the number of dm vma shipyards.
	 *
	 * @return the number of dm vma shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMVMASHIPYARD).build();

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
	 * Initializes the dm vma shipyard persistence.
	 */
	private static final String _SQL_SELECT_DMVMASHIPYARD = "SELECT dmVmaShipyard FROM DmVmaShipyard dmVmaShipyard";
	private static final String _SQL_SELECT_DMVMASHIPYARD_WHERE = "SELECT dmVmaShipyard FROM DmVmaShipyard dmVmaShipyard WHERE ";
	private static final String _SQL_COUNT_DMVMASHIPYARD = "SELECT COUNT(dmVmaShipyard) FROM DmVmaShipyard dmVmaShipyard";
	private static final String _SQL_COUNT_DMVMASHIPYARD_WHERE = "SELECT COUNT(dmVmaShipyard) FROM DmVmaShipyard dmVmaShipyard WHERE ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1 = "dmVmaShipyard.MaritimeCode IS NULL";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2 = "dmVmaShipyard.MaritimeCode =:MaritimeCode";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3 = "(dmVmaShipyard.MaritimeCode IS NULL OR dmVmaShipyard.MaritimeCode =:MaritimeCode)";
	private static final String _FINDER_COLUMN_F_SHIPYARDCODE_SHIPYARDCODE_1 = "dmVmaShipyard.ShipYardCode IS NULL";
	private static final String _FINDER_COLUMN_F_SHIPYARDCODE_SHIPYARDCODE_2 = "dmVmaShipyard.ShipYardCode =:ShipYardCode";
	private static final String _FINDER_COLUMN_F_SHIPYARDCODE_SHIPYARDCODE_3 = "(dmVmaShipyard.ShipYardCode IS NULL OR dmVmaShipyard.ShipYardCode =:ShipYardCode)";
	private static final String _FINDER_COLUMN_F_TAXCODE_TAXCODE_1 = "dmVmaShipyard.TaxCode IS NULL";
	private static final String _FINDER_COLUMN_F_TAXCODE_TAXCODE_2 = "dmVmaShipyard.TaxCode =:TaxCode";
	private static final String _FINDER_COLUMN_F_TAXCODE_TAXCODE_3 = "(dmVmaShipyard.TaxCode IS NULL OR dmVmaShipyard.TaxCode =:TaxCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmVmaShipyard.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmVmaShipyard exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmVmaShipyard exists with the key {";
	

	
}
