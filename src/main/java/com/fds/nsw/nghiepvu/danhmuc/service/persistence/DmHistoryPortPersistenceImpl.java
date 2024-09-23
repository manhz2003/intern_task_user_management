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
import com.fds.nsw.nghiepvu.model.DmHistoryPort;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryPortRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryPortModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryPortPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryPortRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryPort> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryPortUtil} to access the dm history port persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryPort create(int id) {
		DmHistoryPort dmHistoryPort = new DmHistoryPort();

		
		//dmHistoryPort.setPrimaryKey(id);

		return dmHistoryPort;
	}

	/**
	 * Removes the dm history port with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history port
	 * @return the dm history port that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortException if a dm history port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPort remove(int id)
		throws NoSuchDmHistoryPortException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history port with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history port
	 * @return the dm history port that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortException if a dm history port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryPort remove(Serializable primaryKey)
		throws NoSuchDmHistoryPortException, SystemException {
		

		try {
			

			DmHistoryPort dmHistoryPort = findByPrimaryKey(primaryKey);

			if (dmHistoryPort == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryPortException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryPort);
			return dmHistoryPort;
		}
		catch (NoSuchDmHistoryPortException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryPort remove(DmHistoryPort DmHistoryPort) throws SystemException {
	removeImpl(DmHistoryPort);	return DmHistoryPort;
}

protected DmHistoryPort removeImpl

(DmHistoryPort dmHistoryPort)
		throws SystemException {
		try {
			repository.delete(dmHistoryPort);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryPort;
	}

	
	public DmHistoryPort updateImpl(
		DmHistoryPort dmHistoryPort, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryPort);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryPort;
	}

	
	public DmHistoryPort findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history port with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPortException} if it could not be found.
	 *
	 * @param id the primary key of the dm history port
	 * @return the dm history port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortException if a dm history port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPort findByPrimaryKey(int id)
		throws NoSuchDmHistoryPortException, SystemException {
		DmHistoryPort dmHistoryPort = fetchByPrimaryKey(id);

		if (dmHistoryPort == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryPortException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryPort;
	}

	/**
	 * Returns the dm history port with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history port
	 * @return the dm history port, or <code>null</code> if a dm history port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryPort fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history port with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history port
	 * @return the dm history port, or <code>null</code> if a dm history port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPort fetchByPrimaryKey(int id) throws SystemException {
		DmHistoryPort dmHistoryPort = null;

		

		if (dmHistoryPort == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryPort> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryPort = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryPort;
	}

	/**
	 * Returns all the dm history ports where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @return the matching dm history ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPort> findByPortCode(String portCode)
		throws SystemException {
		return findByPortCode(portCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dm history ports where portCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portCode the port code
	 * @param start the lower bound of the range of dm history ports
	 * @param end the upper bound of the range of dm history ports (not inclusive)
	 * @return the range of matching dm history ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPort> findByPortCode(String portCode, int start,
		int end) throws SystemException {
		return findByPortCode(portCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history ports where portCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portCode the port code
	 * @param start the lower bound of the range of dm history ports
	 * @param end the upper bound of the range of dm history ports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPort> findByPortCode(String portCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryPort> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYPORT_WHERE);

			if (portCode == null) {
				query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_1);
			}
			else {
				if (portCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryPortModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portCode != null) {
					builder.appendNamedParameterMap("portCode", portCode);
				}

				list = (List<DmHistoryPort>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history port in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortException if a matching dm history port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPort findByPortCode_First(String portCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortException, SystemException {
		DmHistoryPort dmHistoryPort = fetchByPortCode_First(portCode,
				orderByComparator);

		if (dmHistoryPort != null) {
			return dmHistoryPort;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portCode=");
		msg.append(portCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryPortException(msg.toString());
	}

	/**
	 * Returns the first dm history port in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history port, or <code>null</code> if a matching dm history port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPort fetchByPortCode_First(String portCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryPort> list = findByPortCode(portCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history port in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortException if a matching dm history port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPort findByPortCode_Last(String portCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortException, SystemException {
		DmHistoryPort dmHistoryPort = fetchByPortCode_Last(portCode,
				orderByComparator);

		if (dmHistoryPort != null) {
			return dmHistoryPort;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portCode=");
		msg.append(portCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryPortException(msg.toString());
	}

	/**
	 * Returns the last dm history port in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history port, or <code>null</code> if a matching dm history port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPort fetchByPortCode_Last(String portCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortCode(portCode);

		List<DmHistoryPort> list = findByPortCode(portCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history ports before and after the current dm history port in the ordered set where portCode = &#63;.
	 *
	 * @param id the primary key of the current dm history port
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortException if a dm history port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPort[] findByPortCode_PrevAndNext(int id, String portCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortException, SystemException {
		DmHistoryPort dmHistoryPort = findByPrimaryKey(id);

		

		try {
			

			DmHistoryPort[] array = new DmHistoryPort[3];

			array[0] = getByPortCode_PrevAndNext(dmHistoryPort,
					portCode, orderByComparator, true);

			array[1] = dmHistoryPort;

			array[2] = getByPortCode_PrevAndNext(dmHistoryPort,
					portCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryPort getByPortCode_PrevAndNext(
		DmHistoryPort dmHistoryPort, String portCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYPORT_WHERE);

		if (portCode == null) {
			query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_1);
		}
		else {
			if (portCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_2);
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
			query.append(DmHistoryPortModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portCode != null) {
			builder.appendNamedParameterMap("portCode", portCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryPort);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryPort> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history port where portCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPortException} if it could not be found.
	 *
	 * @param portCode the port code
	 * @param syncVersion the sync version
	 * @return the matching dm history port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortException if a matching dm history port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPort findByPortCodeAndSyncVersion(String portCode,
		String syncVersion)
		throws NoSuchDmHistoryPortException, SystemException {
		DmHistoryPort dmHistoryPort = fetchByPortCodeAndSyncVersion(portCode,
				syncVersion);

		if (dmHistoryPort == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("portCode=");
			msg.append(portCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryPortException(msg.toString());
		}

		return dmHistoryPort;
	}

	/**
	 * Returns the dm history port where portCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param portCode the port code
	 * @param syncVersion the sync version
	 * @return the matching dm history port, or <code>null</code> if a matching dm history port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPort fetchByPortCodeAndSyncVersion(String portCode,
		String syncVersion) throws SystemException {
		return fetchByPortCodeAndSyncVersion(portCode, syncVersion, true);
	}

	/**
	 * Returns the dm history port where portCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param portCode the port code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history port, or <code>null</code> if a matching dm history port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPort fetchByPortCodeAndSyncVersion(String portCode,
		String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryPort dmHistoryPort = null;
		if (dmHistoryPort == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYPORT_WHERE);

			if (portCode == null) {
				query.append(_FINDER_COLUMN_PORTCODEANDSYNCVERSION_PORTCODE_1);
			}
			else {
				if (portCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODEANDSYNCVERSION_PORTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTCODEANDSYNCVERSION_PORTCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_PORTCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryPortModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryPort.class).build();

				

				if (portCode != null) {
					builder.appendNamedParameterMap("portCode", portCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryPort = (DmHistoryPort) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryPort;
	}

	/**
	 * Returns all the dm history ports.
	 *
	 * @return the dm history ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPort> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history ports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history ports
	 * @param end the upper bound of the range of dm history ports (not inclusive)
	 * @return the range of dm history ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPort> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history ports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history ports
	 * @param end the upper bound of the range of dm history ports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPort> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryPort> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYPORT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYPORT.concat(DmHistoryPortModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryPort>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history ports where portCode = &#63; from the database.
	 *
	 * @param portCode the port code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortCode(String portCode) throws SystemException {
		for (DmHistoryPort dmHistoryPort : findByPortCode(portCode)) {
			repository.delete(dmHistoryPort);
		}
	}

	/**
	 * Removes the dm history port where portCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param portCode the port code
	 * @param syncVersion the sync version
	 * @return the dm history port that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPort removeByPortCodeAndSyncVersion(String portCode,
		String syncVersion)
		throws NoSuchDmHistoryPortException, SystemException {
		DmHistoryPort dmHistoryPort = findByPortCodeAndSyncVersion(portCode,
				syncVersion);

		repository.delete(dmHistoryPort);
			return dmHistoryPort;
	}

	/**
	 * Removes all the dm history ports from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryPort dmHistoryPort : findAll()) {
			repository.delete(dmHistoryPort);
		}
	}

	/**
	 * Returns the number of dm history ports where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @return the number of matching dm history ports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortCode(String portCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYPORT_WHERE);

			if (portCode == null) {
				query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_1);
			}
			else {
				if (portCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portCode != null) {
					builder.appendNamedParameterMap("portCode", portCode);
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
	 * Returns the number of dm history ports where portCode = &#63; and syncVersion = &#63;.
	 *
	 * @param portCode the port code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history ports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortCodeAndSyncVersion(String portCode, String syncVersion)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYPORT_WHERE);

			if (portCode == null) {
				query.append(_FINDER_COLUMN_PORTCODEANDSYNCVERSION_PORTCODE_1);
			}
			else {
				if (portCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODEANDSYNCVERSION_PORTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTCODEANDSYNCVERSION_PORTCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_PORTCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portCode != null) {
					builder.appendNamedParameterMap("portCode", portCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
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
	 * Returns the number of dm history ports.
	 *
	 * @return the number of dm history ports
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYPORT).build();

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
	 * Initializes the dm history port persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYPORT = "SELECT dmHistoryPort FROM DmHistoryPort dmHistoryPort";
	private static final String _SQL_SELECT_DMHISTORYPORT_WHERE = "SELECT dmHistoryPort FROM DmHistoryPort dmHistoryPort WHERE ";
	private static final String _SQL_COUNT_DMHISTORYPORT = "SELECT COUNT(dmHistoryPort) FROM DmHistoryPort dmHistoryPort";
	private static final String _SQL_COUNT_DMHISTORYPORT_WHERE = "SELECT COUNT(dmHistoryPort) FROM DmHistoryPort dmHistoryPort WHERE ";
	private static final String _FINDER_COLUMN_PORTCODE_PORTCODE_1 = "dmHistoryPort.portCode IS NULL";
	private static final String _FINDER_COLUMN_PORTCODE_PORTCODE_2 = "dmHistoryPort.portCode =:portCode";
	private static final String _FINDER_COLUMN_PORTCODE_PORTCODE_3 = "(dmHistoryPort.portCode IS NULL OR dmHistoryPort.portCode =:portCode)";
	private static final String _FINDER_COLUMN_PORTCODEANDSYNCVERSION_PORTCODE_1 =
		"dmHistoryPort.portCode IS NULL AND ";
	private static final String _FINDER_COLUMN_PORTCODEANDSYNCVERSION_PORTCODE_2 =
		"dmHistoryPort.portCode =:portCode AND ";
	private static final String _FINDER_COLUMN_PORTCODEANDSYNCVERSION_PORTCODE_3 =
		"(dmHistoryPort.portCode IS NULL OR dmHistoryPort.portCode =:portCode) AND ";
	private static final String _FINDER_COLUMN_PORTCODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryPort.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_PORTCODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryPort.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_PORTCODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryPort.syncVersion IS NULL OR dmHistoryPort.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryPort.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryPort exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryPort exists with the key {";
	

	
}
