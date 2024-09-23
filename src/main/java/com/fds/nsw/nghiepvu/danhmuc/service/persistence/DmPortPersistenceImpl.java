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
import com.fds.nsw.nghiepvu.model.DmPort;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmPortRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmPortModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmPortPersistenceImpl extends BasePersistence {
	@Autowired
	DmPortRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmPort> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmPortUtil} to access the dm port persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmPort create(int id) {
		DmPort dmPort = new DmPort();

		
		//dmPort.setPrimaryKey(id);

		return dmPort;
	}

	/**
	 * Removes the dm port with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm port
	 * @return the dm port that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a dm port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort remove(int id) throws NoSuchDmPortException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm port with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm port
	 * @return the dm port that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a dm port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmPort remove(Serializable primaryKey)
		throws NoSuchDmPortException, SystemException {
		

		try {
			

			DmPort dmPort = findByPrimaryKey(primaryKey);

			if (dmPort == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmPortException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmPort);
			return dmPort;
		}
		catch (NoSuchDmPortException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmPort remove(DmPort DmPort) throws SystemException {
	removeImpl(DmPort);	return DmPort;
}

protected DmPort removeImpl

(DmPort dmPort) throws SystemException {
		try {
			repository.delete(dmPort);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmPort;
	}

	
	public DmPort updateImpl(DmPort dmPort,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmPort);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmPort;
	}

	
	public DmPort findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm port with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmPortException} if it could not be found.
	 *
	 * @param id the primary key of the dm port
	 * @return the dm port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a dm port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort findByPrimaryKey(int id)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = fetchByPrimaryKey(id);

		if (dmPort == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmPortException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmPort;
	}

	/**
	 * Returns the dm port with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm port
	 * @return the dm port, or <code>null</code> if a dm port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmPort fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm port with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm port
	 * @return the dm port, or <code>null</code> if a dm port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort fetchByPrimaryKey(int id) throws SystemException {
		DmPort dmPort = null;

		

		if (dmPort == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmPort> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmPort = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmPort;
	}

	/**
	 * Returns all the dm ports where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @return the matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findByPortCode(String portCode)
		throws SystemException {
		return findByPortCode(portCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dm ports where portCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portCode the port code
	 * @param start the lower bound of the range of dm ports
	 * @param end the upper bound of the range of dm ports (not inclusive)
	 * @return the range of matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findByPortCode(String portCode, int start, int end)
		throws SystemException {
		return findByPortCode(portCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm ports where portCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portCode the port code
	 * @param start the lower bound of the range of dm ports
	 * @param end the upper bound of the range of dm ports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findByPortCode(String portCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPort> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPORT_WHERE);

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
				query.append(DmPortModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portCode != null) {
					builder.appendNamedParameterMap("portCode", portCode);
				}

				list = (List<DmPort>)queryFactory.getResultList(builder);
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
	 * Returns the first dm port in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort findByPortCode_First(String portCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = fetchByPortCode_First(portCode, orderByComparator);

		if (dmPort != null) {
			return dmPort;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portCode=");
		msg.append(portCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortException(msg.toString());
	}

	/**
	 * Returns the first dm port in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port, or <code>null</code> if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort fetchByPortCode_First(String portCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPort> list = findByPortCode(portCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm port in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort findByPortCode_Last(String portCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = fetchByPortCode_Last(portCode, orderByComparator);

		if (dmPort != null) {
			return dmPort;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portCode=");
		msg.append(portCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortException(msg.toString());
	}

	/**
	 * Returns the last dm port in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port, or <code>null</code> if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort fetchByPortCode_Last(String portCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortCode(portCode);

		List<DmPort> list = findByPortCode(portCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm ports before and after the current dm port in the ordered set where portCode = &#63;.
	 *
	 * @param id the primary key of the current dm port
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a dm port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort[] findByPortCode_PrevAndNext(int id, String portCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = findByPrimaryKey(id);

		

		try {
			

			DmPort[] array = new DmPort[3];

			array[0] = getByPortCode_PrevAndNext(dmPort, portCode,
					orderByComparator, true);

			array[1] = dmPort;

			array[2] = getByPortCode_PrevAndNext(dmPort, portCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPort getByPortCode_PrevAndNext( DmPort dmPort,
		String portCode, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPORT_WHERE);

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
			query.append(DmPortModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portCode != null) {
			builder.appendNamedParameterMap("portCode", portCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPort);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPort> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm ports where loCode = &#63;.
	 *
	 * @param loCode the lo code
	 * @return the matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findByLoCode(String loCode) throws SystemException {
		return findByLoCode(loCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm ports where loCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param loCode the lo code
	 * @param start the lower bound of the range of dm ports
	 * @param end the upper bound of the range of dm ports (not inclusive)
	 * @return the range of matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findByLoCode(String loCode, int start, int end)
		throws SystemException {
		return findByLoCode(loCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm ports where loCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param loCode the lo code
	 * @param start the lower bound of the range of dm ports
	 * @param end the upper bound of the range of dm ports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findByLoCode(String loCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPort> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPORT_WHERE);

			if (loCode == null) {
				query.append(_FINDER_COLUMN_LOCODE_LOCODE_1);
			}
			else {
				if (loCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_LOCODE_LOCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_LOCODE_LOCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmPortModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (loCode != null) {
					builder.appendNamedParameterMap("loCode", loCode);
				}

				list = (List<DmPort>)queryFactory.getResultList(builder);
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
	 * Returns the first dm port in the ordered set where loCode = &#63;.
	 *
	 * @param loCode the lo code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort findByLoCode_First(String loCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = fetchByLoCode_First(loCode, orderByComparator);

		if (dmPort != null) {
			return dmPort;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("loCode=");
		msg.append(loCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortException(msg.toString());
	}

	/**
	 * Returns the first dm port in the ordered set where loCode = &#63;.
	 *
	 * @param loCode the lo code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port, or <code>null</code> if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort fetchByLoCode_First(String loCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPort> list = findByLoCode(loCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm port in the ordered set where loCode = &#63;.
	 *
	 * @param loCode the lo code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort findByLoCode_Last(String loCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = fetchByLoCode_Last(loCode, orderByComparator);

		if (dmPort != null) {
			return dmPort;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("loCode=");
		msg.append(loCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortException(msg.toString());
	}

	/**
	 * Returns the last dm port in the ordered set where loCode = &#63;.
	 *
	 * @param loCode the lo code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port, or <code>null</code> if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort fetchByLoCode_Last(String loCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByLoCode(loCode);

		List<DmPort> list = findByLoCode(loCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm ports before and after the current dm port in the ordered set where loCode = &#63;.
	 *
	 * @param id the primary key of the current dm port
	 * @param loCode the lo code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a dm port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort[] findByLoCode_PrevAndNext(int id, String loCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = findByPrimaryKey(id);

		

		try {
			

			DmPort[] array = new DmPort[3];

			array[0] = getByLoCode_PrevAndNext(dmPort, loCode,
					orderByComparator, true);

			array[1] = dmPort;

			array[2] = getByLoCode_PrevAndNext(dmPort, loCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPort getByLoCode_PrevAndNext( DmPort dmPort,
		String loCode, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPORT_WHERE);

		if (loCode == null) {
			query.append(_FINDER_COLUMN_LOCODE_LOCODE_1);
		}
		else {
			if (loCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LOCODE_LOCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_LOCODE_LOCODE_2);
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
			query.append(DmPortModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (loCode != null) {
			builder.appendNamedParameterMap("loCode", loCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPort);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPort> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm ports where stateCode = &#63; and citycode = &#63;.
	 *
	 * @param stateCode the state code
	 * @param citycode the citycode
	 * @return the matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findByStateCodeAndCityCode(String stateCode,
		String citycode) throws SystemException {
		return findByStateCodeAndCityCode(stateCode, citycode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm ports where stateCode = &#63; and citycode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param stateCode the state code
	 * @param citycode the citycode
	 * @param start the lower bound of the range of dm ports
	 * @param end the upper bound of the range of dm ports (not inclusive)
	 * @return the range of matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findByStateCodeAndCityCode(String stateCode,
		String citycode, int start, int end) throws SystemException {
		return findByStateCodeAndCityCode(stateCode, citycode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm ports where stateCode = &#63; and citycode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param stateCode the state code
	 * @param citycode the citycode
	 * @param start the lower bound of the range of dm ports
	 * @param end the upper bound of the range of dm ports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findByStateCodeAndCityCode(String stateCode,
		String citycode, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmPort> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DMPORT_WHERE);

			if (stateCode == null) {
				query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_STATECODE_1);
			}
			else {
				if (stateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_STATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_STATECODE_2);
				}
			}

			if (citycode == null) {
				query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_CITYCODE_1);
			}
			else {
				if (citycode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_CITYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_CITYCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmPortModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (stateCode != null) {
					builder.appendNamedParameterMap("stateCode", stateCode);
				}

				if (citycode != null) {
					builder.appendNamedParameterMap("citycode", citycode);
				}

				list = (List<DmPort>)queryFactory.getResultList(builder);
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
	 * Returns the first dm port in the ordered set where stateCode = &#63; and citycode = &#63;.
	 *
	 * @param stateCode the state code
	 * @param citycode the citycode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort findByStateCodeAndCityCode_First(String stateCode,
		String citycode, OrderByComparator orderByComparator)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = fetchByStateCodeAndCityCode_First(stateCode, citycode,
				orderByComparator);

		if (dmPort != null) {
			return dmPort;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stateCode=");
		msg.append(stateCode);

		msg.append(", citycode=");
		msg.append(citycode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortException(msg.toString());
	}

	/**
	 * Returns the first dm port in the ordered set where stateCode = &#63; and citycode = &#63;.
	 *
	 * @param stateCode the state code
	 * @param citycode the citycode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port, or <code>null</code> if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort fetchByStateCodeAndCityCode_First(String stateCode,
		String citycode, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmPort> list = findByStateCodeAndCityCode(stateCode, citycode, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm port in the ordered set where stateCode = &#63; and citycode = &#63;.
	 *
	 * @param stateCode the state code
	 * @param citycode the citycode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort findByStateCodeAndCityCode_Last(String stateCode,
		String citycode, OrderByComparator orderByComparator)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = fetchByStateCodeAndCityCode_Last(stateCode, citycode,
				orderByComparator);

		if (dmPort != null) {
			return dmPort;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stateCode=");
		msg.append(stateCode);

		msg.append(", citycode=");
		msg.append(citycode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortException(msg.toString());
	}

	/**
	 * Returns the last dm port in the ordered set where stateCode = &#63; and citycode = &#63;.
	 *
	 * @param stateCode the state code
	 * @param citycode the citycode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port, or <code>null</code> if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort fetchByStateCodeAndCityCode_Last(String stateCode,
		String citycode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByStateCodeAndCityCode(stateCode, citycode);

		List<DmPort> list = findByStateCodeAndCityCode(stateCode, citycode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm ports before and after the current dm port in the ordered set where stateCode = &#63; and citycode = &#63;.
	 *
	 * @param id the primary key of the current dm port
	 * @param stateCode the state code
	 * @param citycode the citycode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a dm port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort[] findByStateCodeAndCityCode_PrevAndNext(int id,
		String stateCode, String citycode, OrderByComparator orderByComparator)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = findByPrimaryKey(id);

		

		try {
			

			DmPort[] array = new DmPort[3];

			array[0] = getByStateCodeAndCityCode_PrevAndNext(dmPort,
					stateCode, citycode, orderByComparator, true);

			array[1] = dmPort;

			array[2] = getByStateCodeAndCityCode_PrevAndNext(dmPort,
					stateCode, citycode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPort getByStateCodeAndCityCode_PrevAndNext(
		DmPort dmPort, String stateCode, String citycode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPORT_WHERE);

		if (stateCode == null) {
			query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_STATECODE_1);
		}
		else {
			if (stateCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_STATECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_STATECODE_2);
			}
		}

		if (citycode == null) {
			query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_CITYCODE_1);
		}
		else {
			if (citycode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_CITYCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_CITYCODE_2);
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
			query.append(DmPortModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (stateCode != null) {
			builder.appendNamedParameterMap("stateCode", stateCode);
		}

		if (citycode != null) {
			builder.appendNamedParameterMap("citycode", citycode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPort);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPort> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm port where portCode = &#63; and loCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmPortException} if it could not be found.
	 *
	 * @param portCode the port code
	 * @param loCode the lo code
	 * @return the matching dm port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort findByPortCodeLoCode(String portCode, String loCode)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = fetchByPortCodeLoCode(portCode, loCode);

		if (dmPort == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("portCode=");
			msg.append(portCode);

			msg.append(", loCode=");
			msg.append(loCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmPortException(msg.toString());
		}

		return dmPort;
	}

	/**
	 * Returns the dm port where portCode = &#63; and loCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param portCode the port code
	 * @param loCode the lo code
	 * @return the matching dm port, or <code>null</code> if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort fetchByPortCodeLoCode(String portCode, String loCode)
		throws SystemException {
		return fetchByPortCodeLoCode(portCode, loCode, true);
	}

	/**
	 * Returns the dm port where portCode = &#63; and loCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param portCode the port code
	 * @param loCode the lo code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm port, or <code>null</code> if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort fetchByPortCodeLoCode(String portCode, String loCode,
		boolean retrieveFromCache) throws SystemException {
		DmPort dmPort = null;
		if (dmPort == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMPORT_WHERE);

			if (portCode == null) {
				query.append(_FINDER_COLUMN_PORTCODELOCODE_PORTCODE_1);
			}
			else {
				if (portCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODELOCODE_PORTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTCODELOCODE_PORTCODE_2);
				}
			}

			if (loCode == null) {
				query.append(_FINDER_COLUMN_PORTCODELOCODE_LOCODE_1);
			}
			else {
				if (loCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODELOCODE_LOCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTCODELOCODE_LOCODE_2);
				}
			}

			query.append(DmPortModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmPort.class).build();

				

				if (portCode != null) {
					builder.appendNamedParameterMap("portCode", portCode);
				}

				if (loCode != null) {
					builder.appendNamedParameterMap("loCode", loCode);
				}

				dmPort = (DmPort) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmPort;
	}

	/**
	 * Returns all the dm ports where portName LIKE &#63;.
	 *
	 * @param portName the port name
	 * @return the matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findByF_portNameByLike(String portName)
		throws SystemException {
		return findByF_portNameByLike(portName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm ports where portName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portName the port name
	 * @param start the lower bound of the range of dm ports
	 * @param end the upper bound of the range of dm ports (not inclusive)
	 * @return the range of matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findByF_portNameByLike(String portName, int start,
		int end) throws SystemException {
		return findByF_portNameByLike(portName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm ports where portName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portName the port name
	 * @param start the lower bound of the range of dm ports
	 * @param end the upper bound of the range of dm ports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findByF_portNameByLike(String portName, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmPort> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPORT_WHERE);

			if (portName == null) {
				query.append(_FINDER_COLUMN_F_PORTNAMEBYLIKE_PORTNAME_1);
			}
			else {
				if (portName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PORTNAMEBYLIKE_PORTNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PORTNAMEBYLIKE_PORTNAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmPortModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portName != null) {
					builder.appendNamedParameterMap("portName", portName);
				}

				list = (List<DmPort>)queryFactory.getResultList(builder);
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
	 * Returns the first dm port in the ordered set where portName LIKE &#63;.
	 *
	 * @param portName the port name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort findByF_portNameByLike_First(String portName,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = fetchByF_portNameByLike_First(portName,
				orderByComparator);

		if (dmPort != null) {
			return dmPort;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portName=");
		msg.append(portName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortException(msg.toString());
	}

	/**
	 * Returns the first dm port in the ordered set where portName LIKE &#63;.
	 *
	 * @param portName the port name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port, or <code>null</code> if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort fetchByF_portNameByLike_First(String portName,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPort> list = findByF_portNameByLike(portName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm port in the ordered set where portName LIKE &#63;.
	 *
	 * @param portName the port name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort findByF_portNameByLike_Last(String portName,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = fetchByF_portNameByLike_Last(portName, orderByComparator);

		if (dmPort != null) {
			return dmPort;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portName=");
		msg.append(portName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortException(msg.toString());
	}

	/**
	 * Returns the last dm port in the ordered set where portName LIKE &#63;.
	 *
	 * @param portName the port name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port, or <code>null</code> if a matching dm port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort fetchByF_portNameByLike_Last(String portName,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_portNameByLike(portName);

		List<DmPort> list = findByF_portNameByLike(portName, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm ports before and after the current dm port in the ordered set where portName LIKE &#63;.
	 *
	 * @param id the primary key of the current dm port
	 * @param portName the port name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm port
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortException if a dm port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort[] findByF_portNameByLike_PrevAndNext(int id, String portName,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = findByPrimaryKey(id);

		

		try {
			

			DmPort[] array = new DmPort[3];

			array[0] = getByF_portNameByLike_PrevAndNext(dmPort,
					portName, orderByComparator, true);

			array[1] = dmPort;

			array[2] = getByF_portNameByLike_PrevAndNext(dmPort,
					portName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPort getByF_portNameByLike_PrevAndNext(
		DmPort dmPort, String portName, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPORT_WHERE);

		if (portName == null) {
			query.append(_FINDER_COLUMN_F_PORTNAMEBYLIKE_PORTNAME_1);
		}
		else {
			if (portName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_PORTNAMEBYLIKE_PORTNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_PORTNAMEBYLIKE_PORTNAME_2);
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
			query.append(DmPortModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portName != null) {
			builder.appendNamedParameterMap("portName", portName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPort);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPort> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm ports.
	 *
	 * @return the dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm ports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm ports
	 * @param end the upper bound of the range of dm ports (not inclusive)
	 * @return the range of dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm ports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm ports
	 * @param end the upper bound of the range of dm ports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPort> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPort> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMPORT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMPORT.concat(DmPortModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmPort>) queryFactory.getResultList(builder);
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
	 * Removes all the dm ports where portCode = &#63; from the database.
	 *
	 * @param portCode the port code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortCode(String portCode) throws SystemException {
		for (DmPort dmPort : findByPortCode(portCode)) {
			repository.delete(dmPort);
		}
	}

	/**
	 * Removes all the dm ports where loCode = &#63; from the database.
	 *
	 * @param loCode the lo code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByLoCode(String loCode) throws SystemException {
		for (DmPort dmPort : findByLoCode(loCode)) {
			repository.delete(dmPort);
		}
	}

	/**
	 * Removes all the dm ports where stateCode = &#63; and citycode = &#63; from the database.
	 *
	 * @param stateCode the state code
	 * @param citycode the citycode
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByStateCodeAndCityCode(String stateCode, String citycode)
		throws SystemException {
		for (DmPort dmPort : findByStateCodeAndCityCode(stateCode, citycode)) {
			repository.delete(dmPort);
		}
	}

	/**
	 * Removes the dm port where portCode = &#63; and loCode = &#63; from the database.
	 *
	 * @param portCode the port code
	 * @param loCode the lo code
	 * @return the dm port that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmPort removeByPortCodeLoCode(String portCode, String loCode)
		throws NoSuchDmPortException, SystemException {
		DmPort dmPort = findByPortCodeLoCode(portCode, loCode);

		repository.delete(dmPort);
			return dmPort;
	}

	/**
	 * Removes all the dm ports where portName LIKE &#63; from the database.
	 *
	 * @param portName the port name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_portNameByLike(String portName)
		throws SystemException {
		for (DmPort dmPort : findByF_portNameByLike(portName)) {
			repository.delete(dmPort);
		}
	}

	/**
	 * Removes all the dm ports from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmPort dmPort : findAll()) {
			repository.delete(dmPort);
		}
	}

	/**
	 * Returns the number of dm ports where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @return the number of matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortCode(String portCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPORT_WHERE);

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
	 * Returns the number of dm ports where loCode = &#63;.
	 *
	 * @param loCode the lo code
	 * @return the number of matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByLoCode(String loCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPORT_WHERE);

			if (loCode == null) {
				query.append(_FINDER_COLUMN_LOCODE_LOCODE_1);
			}
			else {
				if (loCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_LOCODE_LOCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_LOCODE_LOCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (loCode != null) {
					builder.appendNamedParameterMap("loCode", loCode);
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
	 * Returns the number of dm ports where stateCode = &#63; and citycode = &#63;.
	 *
	 * @param stateCode the state code
	 * @param citycode the citycode
	 * @return the number of matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByStateCodeAndCityCode(String stateCode, String citycode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMPORT_WHERE);

			if (stateCode == null) {
				query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_STATECODE_1);
			}
			else {
				if (stateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_STATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_STATECODE_2);
				}
			}

			if (citycode == null) {
				query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_CITYCODE_1);
			}
			else {
				if (citycode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_CITYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_STATECODEANDCITYCODE_CITYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (stateCode != null) {
					builder.appendNamedParameterMap("stateCode", stateCode);
				}

				if (citycode != null) {
					builder.appendNamedParameterMap("citycode", citycode);
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
	 * Returns the number of dm ports where portCode = &#63; and loCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param loCode the lo code
	 * @return the number of matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortCodeLoCode(String portCode, String loCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMPORT_WHERE);

			if (portCode == null) {
				query.append(_FINDER_COLUMN_PORTCODELOCODE_PORTCODE_1);
			}
			else {
				if (portCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODELOCODE_PORTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTCODELOCODE_PORTCODE_2);
				}
			}

			if (loCode == null) {
				query.append(_FINDER_COLUMN_PORTCODELOCODE_LOCODE_1);
			}
			else {
				if (loCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODELOCODE_LOCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTCODELOCODE_LOCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portCode != null) {
					builder.appendNamedParameterMap("portCode", portCode);
				}

				if (loCode != null) {
					builder.appendNamedParameterMap("loCode", loCode);
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
	 * Returns the number of dm ports where portName LIKE &#63;.
	 *
	 * @param portName the port name
	 * @return the number of matching dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_portNameByLike(String portName)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPORT_WHERE);

			if (portName == null) {
				query.append(_FINDER_COLUMN_F_PORTNAMEBYLIKE_PORTNAME_1);
			}
			else {
				if (portName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PORTNAMEBYLIKE_PORTNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PORTNAMEBYLIKE_PORTNAME_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portName != null) {
					builder.appendNamedParameterMap("portName", portName);
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
	 * Returns the number of dm ports.
	 *
	 * @return the number of dm ports
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMPORT).build();

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
	 * Initializes the dm port persistence.
	 */
	private static final String _SQL_SELECT_DMPORT = "SELECT dmPort FROM DmPort dmPort";
	private static final String _SQL_SELECT_DMPORT_WHERE = "SELECT dmPort FROM DmPort dmPort WHERE ";
	private static final String _SQL_COUNT_DMPORT = "SELECT COUNT(dmPort) FROM DmPort dmPort";
	private static final String _SQL_COUNT_DMPORT_WHERE = "SELECT COUNT(dmPort) FROM DmPort dmPort WHERE ";
	private static final String _FINDER_COLUMN_PORTCODE_PORTCODE_1 = "dmPort.portCode IS NULL";
	private static final String _FINDER_COLUMN_PORTCODE_PORTCODE_2 = "dmPort.portCode =:portCode";
	private static final String _FINDER_COLUMN_PORTCODE_PORTCODE_3 = "(dmPort.portCode IS NULL OR dmPort.portCode =:portCode)";
	private static final String _FINDER_COLUMN_LOCODE_LOCODE_1 = "dmPort.loCode IS NULL";
	private static final String _FINDER_COLUMN_LOCODE_LOCODE_2 = "dmPort.loCode =:loCode";
	private static final String _FINDER_COLUMN_LOCODE_LOCODE_3 = "(dmPort.loCode IS NULL OR dmPort.loCode =:loCode)";
	private static final String _FINDER_COLUMN_STATECODEANDCITYCODE_STATECODE_1 = "dmPort.stateCode IS NULL AND ";
	private static final String _FINDER_COLUMN_STATECODEANDCITYCODE_STATECODE_2 = "dmPort.stateCode =:stateCode AND ";
	private static final String _FINDER_COLUMN_STATECODEANDCITYCODE_STATECODE_3 = "(dmPort.stateCode IS NULL OR dmPort.stateCode =:stateCode) AND ";
	private static final String _FINDER_COLUMN_STATECODEANDCITYCODE_CITYCODE_1 = "dmPort.citycode IS NULL";
	private static final String _FINDER_COLUMN_STATECODEANDCITYCODE_CITYCODE_2 = "dmPort.citycode =:citycode";
	private static final String _FINDER_COLUMN_STATECODEANDCITYCODE_CITYCODE_3 = "(dmPort.citycode IS NULL OR dmPort.citycode =:citycode)";
	private static final String _FINDER_COLUMN_PORTCODELOCODE_PORTCODE_1 = "dmPort.portCode IS NULL AND ";
	private static final String _FINDER_COLUMN_PORTCODELOCODE_PORTCODE_2 = "dmPort.portCode =:portCode AND ";
	private static final String _FINDER_COLUMN_PORTCODELOCODE_PORTCODE_3 = "(dmPort.portCode IS NULL OR dmPort.portCode =:portCode) AND ";
	private static final String _FINDER_COLUMN_PORTCODELOCODE_LOCODE_1 = "dmPort.loCode IS NULL";
	private static final String _FINDER_COLUMN_PORTCODELOCODE_LOCODE_2 = "dmPort.loCode =:loCode";
	private static final String _FINDER_COLUMN_PORTCODELOCODE_LOCODE_3 = "(dmPort.loCode IS NULL OR dmPort.loCode =:loCode)";
	private static final String _FINDER_COLUMN_F_PORTNAMEBYLIKE_PORTNAME_1 = "dmPort.portName LIKE NULL";
	private static final String _FINDER_COLUMN_F_PORTNAMEBYLIKE_PORTNAME_2 = "dmPort.portName LIKE :portName";
	private static final String _FINDER_COLUMN_F_PORTNAMEBYLIKE_PORTNAME_3 = "(dmPort.portName IS NULL OR dmPort.portName LIKE :portName)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmPort.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmPort exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmPort exists with the key {";
	

	
}
