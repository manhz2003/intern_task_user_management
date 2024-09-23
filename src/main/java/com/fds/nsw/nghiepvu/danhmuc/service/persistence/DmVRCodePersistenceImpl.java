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
import com.fds.nsw.nghiepvu.model.DmVRCode;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmVrcodeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmVRCodeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmVRCodePersistenceImpl extends BasePersistence {
	@Autowired
	DmVrcodeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVRCode> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmVRCodeUtil} to access the dm v r code persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmVRCode create(int id) {
		DmVRCode dmVRCode = new DmVRCode();

		
		//dmVRCode.setPrimaryKey(id);

		return dmVRCode;
	}

	/**
	 * Removes the dm v r code with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm v r code
	 * @return the dm v r code that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVRCodeException if a dm v r code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVRCode remove(int id)
		throws NoSuchDmVRCodeException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm v r code with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm v r code
	 * @return the dm v r code that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVRCodeException if a dm v r code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVRCode remove(Serializable primaryKey)
		throws NoSuchDmVRCodeException, SystemException {
		

		try {
			

			DmVRCode dmVRCode = findByPrimaryKey(primaryKey);

			if (dmVRCode == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmVRCodeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmVRCode);
			return dmVRCode;
		}
		catch (NoSuchDmVRCodeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmVRCode remove(DmVRCode DmVRCode) throws SystemException {
	removeImpl(DmVRCode);	return DmVRCode;
}

protected DmVRCode removeImpl

(DmVRCode dmVRCode) throws SystemException {
		try {
			repository.delete(dmVRCode);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVRCode;
	}

	
	public DmVRCode updateImpl(DmVRCode dmVRCode,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmVRCode);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVRCode;
	}

	
	public DmVRCode findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm v r code with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVRCodeException} if it could not be found.
	 *
	 * @param id the primary key of the dm v r code
	 * @return the dm v r code
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVRCodeException if a dm v r code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVRCode findByPrimaryKey(int id)
		throws NoSuchDmVRCodeException, SystemException {
		DmVRCode dmVRCode = fetchByPrimaryKey(id);

		if (dmVRCode == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmVRCodeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmVRCode;
	}

	/**
	 * Returns the dm v r code with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm v r code
	 * @return the dm v r code, or <code>null</code> if a dm v r code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVRCode fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm v r code with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm v r code
	 * @return the dm v r code, or <code>null</code> if a dm v r code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVRCode fetchByPrimaryKey(int id) throws SystemException {
		DmVRCode dmVRCode = null;

		

		if (dmVRCode == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmVRCode> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmVRCode = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmVRCode;
	}

	/**
	 * Returns all the dm v r codes where shipBoat = &#63;.
	 *
	 * @param shipBoat the ship boat
	 * @return the matching dm v r codes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVRCode> findByF_shipBoat(String shipBoat)
		throws SystemException {
		return findByF_shipBoat(shipBoat, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dm v r codes where shipBoat = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipBoat the ship boat
	 * @param start the lower bound of the range of dm v r codes
	 * @param end the upper bound of the range of dm v r codes (not inclusive)
	 * @return the range of matching dm v r codes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVRCode> findByF_shipBoat(String shipBoat, int start, int end)
		throws SystemException {
		return findByF_shipBoat(shipBoat, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm v r codes where shipBoat = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipBoat the ship boat
	 * @param start the lower bound of the range of dm v r codes
	 * @param end the upper bound of the range of dm v r codes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm v r codes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVRCode> findByF_shipBoat(String shipBoat, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVRCode> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_DMVRCODE_WHERE);

			if (shipBoat == null) {
				query.append(_FINDER_COLUMN_F_SHIPBOAT_SHIPBOAT_1);
			}
			else {
				if (shipBoat.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPBOAT_SHIPBOAT_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPBOAT_SHIPBOAT_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (shipBoat != null) {
					builder.appendNamedParameterMap("shipBoat", shipBoat);
				}

				list = (List<DmVRCode>)queryFactory.getResultList(builder);
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
	 * Returns the first dm v r code in the ordered set where shipBoat = &#63;.
	 *
	 * @param shipBoat the ship boat
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm v r code
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVRCodeException if a matching dm v r code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVRCode findByF_shipBoat_First(String shipBoat,
		OrderByComparator orderByComparator)
		throws NoSuchDmVRCodeException, SystemException {
		DmVRCode dmVRCode = fetchByF_shipBoat_First(shipBoat, orderByComparator);

		if (dmVRCode != null) {
			return dmVRCode;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipBoat=");
		msg.append(shipBoat);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVRCodeException(msg.toString());
	}

	/**
	 * Returns the first dm v r code in the ordered set where shipBoat = &#63;.
	 *
	 * @param shipBoat the ship boat
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm v r code, or <code>null</code> if a matching dm v r code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVRCode fetchByF_shipBoat_First(String shipBoat,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVRCode> list = findByF_shipBoat(shipBoat, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm v r code in the ordered set where shipBoat = &#63;.
	 *
	 * @param shipBoat the ship boat
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm v r code
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVRCodeException if a matching dm v r code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVRCode findByF_shipBoat_Last(String shipBoat,
		OrderByComparator orderByComparator)
		throws NoSuchDmVRCodeException, SystemException {
		DmVRCode dmVRCode = fetchByF_shipBoat_Last(shipBoat, orderByComparator);

		if (dmVRCode != null) {
			return dmVRCode;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipBoat=");
		msg.append(shipBoat);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVRCodeException(msg.toString());
	}

	/**
	 * Returns the last dm v r code in the ordered set where shipBoat = &#63;.
	 *
	 * @param shipBoat the ship boat
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm v r code, or <code>null</code> if a matching dm v r code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVRCode fetchByF_shipBoat_Last(String shipBoat,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_shipBoat(shipBoat);

		List<DmVRCode> list = findByF_shipBoat(shipBoat, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm v r codes before and after the current dm v r code in the ordered set where shipBoat = &#63;.
	 *
	 * @param id the primary key of the current dm v r code
	 * @param shipBoat the ship boat
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm v r code
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVRCodeException if a dm v r code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVRCode[] findByF_shipBoat_PrevAndNext(int id, String shipBoat,
		OrderByComparator orderByComparator)
		throws NoSuchDmVRCodeException, SystemException {
		DmVRCode dmVRCode = findByPrimaryKey(id);

		

		try {
			

			DmVRCode[] array = new DmVRCode[3];

			array[0] = getByF_shipBoat_PrevAndNext(dmVRCode, shipBoat,
					orderByComparator, true);

			array[1] = dmVRCode;

			array[2] = getByF_shipBoat_PrevAndNext(dmVRCode, shipBoat,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmVRCode getByF_shipBoat_PrevAndNext(
		DmVRCode dmVRCode, String shipBoat,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMVRCODE_WHERE);

		if (shipBoat == null) {
			query.append(_FINDER_COLUMN_F_SHIPBOAT_SHIPBOAT_1);
		}
		else {
			if (shipBoat.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_SHIPBOAT_SHIPBOAT_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_SHIPBOAT_SHIPBOAT_2);
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

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (shipBoat != null) {
			builder.appendNamedParameterMap("shipBoat", shipBoat);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmVRCode);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmVRCode> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm v r codes.
	 *
	 * @return the dm v r codes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVRCode> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm v r codes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm v r codes
	 * @param end the upper bound of the range of dm v r codes (not inclusive)
	 * @return the range of dm v r codes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVRCode> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm v r codes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm v r codes
	 * @param end the upper bound of the range of dm v r codes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm v r codes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVRCode> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVRCode> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMVRCODE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMVRCODE;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmVRCode>) queryFactory.getResultList(builder);
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
	 * Removes all the dm v r codes where shipBoat = &#63; from the database.
	 *
	 * @param shipBoat the ship boat
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_shipBoat(String shipBoat) throws SystemException {
		for (DmVRCode dmVRCode : findByF_shipBoat(shipBoat)) {
			repository.delete(dmVRCode);
		}
	}

	/**
	 * Removes all the dm v r codes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmVRCode dmVRCode : findAll()) {
			repository.delete(dmVRCode);
		}
	}

	/**
	 * Returns the number of dm v r codes where shipBoat = &#63;.
	 *
	 * @param shipBoat the ship boat
	 * @return the number of matching dm v r codes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_shipBoat(String shipBoat) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVRCODE_WHERE);

			if (shipBoat == null) {
				query.append(_FINDER_COLUMN_F_SHIPBOAT_SHIPBOAT_1);
			}
			else {
				if (shipBoat.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPBOAT_SHIPBOAT_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPBOAT_SHIPBOAT_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (shipBoat != null) {
					builder.appendNamedParameterMap("shipBoat", shipBoat);
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
	 * Returns the number of dm v r codes.
	 *
	 * @return the number of dm v r codes
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMVRCODE).build();

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
	 * Initializes the dm v r code persistence.
	 */
	private static final String _SQL_SELECT_DMVRCODE = "SELECT dmVRCode FROM DmVRCode dmVRCode";
	private static final String _SQL_SELECT_DMVRCODE_WHERE = "SELECT dmVRCode FROM DmVRCode dmVRCode WHERE ";
	private static final String _SQL_COUNT_DMVRCODE = "SELECT COUNT(dmVRCode) FROM DmVRCode dmVRCode";
	private static final String _SQL_COUNT_DMVRCODE_WHERE = "SELECT COUNT(dmVRCode) FROM DmVRCode dmVRCode WHERE ";
	private static final String _FINDER_COLUMN_F_SHIPBOAT_SHIPBOAT_1 = "dmVRCode.shipBoat IS NULL";
	private static final String _FINDER_COLUMN_F_SHIPBOAT_SHIPBOAT_2 = "dmVRCode.shipBoat =:shipBoat";
	private static final String _FINDER_COLUMN_F_SHIPBOAT_SHIPBOAT_3 = "(dmVRCode.shipBoat IS NULL OR dmVRCode.shipBoat =:shipBoat)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmVRCode.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmVRCode exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmVRCode exists with the key {";
	

	
}
