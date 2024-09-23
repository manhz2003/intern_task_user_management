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
import com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmVmaSecurityOfficeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmVmaSecurityOfficeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmVmaSecurityOfficePersistenceImpl extends BasePersistence {
	@Autowired
	DmVmaSecurityOfficeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaSecurityOffice> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmVmaSecurityOfficeUtil} to access the dm vma security office persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmVmaSecurityOffice create(long id) {
		DmVmaSecurityOffice dmVmaSecurityOffice = new DmVmaSecurityOffice();

		
		//dmVmaSecurityOffice.setPrimaryKey(id);

		return dmVmaSecurityOffice;
	}

	/**
	 * Removes the dm vma security office with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm vma security office
	 * @return the dm vma security office that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaSecurityOfficeException if a dm vma security office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaSecurityOffice remove(long id)
		throws NoSuchDmVmaSecurityOfficeException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm vma security office with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm vma security office
	 * @return the dm vma security office that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaSecurityOfficeException if a dm vma security office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaSecurityOffice remove(Serializable primaryKey)
		throws NoSuchDmVmaSecurityOfficeException, SystemException {
		

		try {
			

			DmVmaSecurityOffice dmVmaSecurityOffice = findByPrimaryKey(primaryKey);

			if (dmVmaSecurityOffice == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmVmaSecurityOfficeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmVmaSecurityOffice);
			return dmVmaSecurityOffice;
		}
		catch (NoSuchDmVmaSecurityOfficeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmVmaSecurityOffice remove(DmVmaSecurityOffice DmVmaSecurityOffice) throws SystemException {
	removeImpl(DmVmaSecurityOffice);	return DmVmaSecurityOffice;
}

protected DmVmaSecurityOffice removeImpl

(
		DmVmaSecurityOffice dmVmaSecurityOffice) throws SystemException {
		try {
			repository.delete(dmVmaSecurityOffice);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaSecurityOffice;
	}

	
	public DmVmaSecurityOffice updateImpl(
		DmVmaSecurityOffice dmVmaSecurityOffice,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmVmaSecurityOffice);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaSecurityOffice;
	}

	
	public DmVmaSecurityOffice findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma security office with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaSecurityOfficeException} if it could not be found.
	 *
	 * @param id the primary key of the dm vma security office
	 * @return the dm vma security office
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaSecurityOfficeException if a dm vma security office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaSecurityOffice findByPrimaryKey(long id)
		throws NoSuchDmVmaSecurityOfficeException, SystemException {
		DmVmaSecurityOffice dmVmaSecurityOffice = fetchByPrimaryKey(id);

		if (dmVmaSecurityOffice == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmVmaSecurityOfficeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmVmaSecurityOffice;
	}

	/**
	 * Returns the dm vma security office with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm vma security office
	 * @return the dm vma security office, or <code>null</code> if a dm vma security office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaSecurityOffice fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma security office with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm vma security office
	 * @return the dm vma security office, or <code>null</code> if a dm vma security office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaSecurityOffice fetchByPrimaryKey(long id)
		throws SystemException {
		DmVmaSecurityOffice dmVmaSecurityOffice = null;

		

		if (dmVmaSecurityOffice == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmVmaSecurityOffice> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmVmaSecurityOffice = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmVmaSecurityOffice;
	}

	/**
	 * Returns all the dm vma security offices where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the matching dm vma security offices
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaSecurityOffice> findByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		return findByF_maritimeCode(MaritimeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma security offices where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm vma security offices
	 * @param end the upper bound of the range of dm vma security offices (not inclusive)
	 * @return the range of matching dm vma security offices
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaSecurityOffice> findByF_maritimeCode(String MaritimeCode,
		int start, int end) throws SystemException {
		return findByF_maritimeCode(MaritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma security offices where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm vma security offices
	 * @param end the upper bound of the range of dm vma security offices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm vma security offices
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaSecurityOffice> findByF_maritimeCode(String MaritimeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmVmaSecurityOffice> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMVMASECURITYOFFICE_WHERE);

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
				query.append(DmVmaSecurityOfficeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
				}

				list = (List<DmVmaSecurityOffice>)queryFactory.getResultList(builder);
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
	 * Returns the first dm vma security office in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma security office
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaSecurityOfficeException if a matching dm vma security office could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaSecurityOffice findByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaSecurityOfficeException, SystemException {
		DmVmaSecurityOffice dmVmaSecurityOffice = fetchByF_maritimeCode_First(MaritimeCode,
				orderByComparator);

		if (dmVmaSecurityOffice != null) {
			return dmVmaSecurityOffice;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaSecurityOfficeException(msg.toString());
	}

	/**
	 * Returns the first dm vma security office in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma security office, or <code>null</code> if a matching dm vma security office could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaSecurityOffice fetchByF_maritimeCode_First(
		String MaritimeCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmVmaSecurityOffice> list = findByF_maritimeCode(MaritimeCode, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm vma security office in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma security office
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaSecurityOfficeException if a matching dm vma security office could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaSecurityOffice findByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaSecurityOfficeException, SystemException {
		DmVmaSecurityOffice dmVmaSecurityOffice = fetchByF_maritimeCode_Last(MaritimeCode,
				orderByComparator);

		if (dmVmaSecurityOffice != null) {
			return dmVmaSecurityOffice;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaSecurityOfficeException(msg.toString());
	}

	/**
	 * Returns the last dm vma security office in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma security office, or <code>null</code> if a matching dm vma security office could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaSecurityOffice fetchByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_maritimeCode(MaritimeCode);

		List<DmVmaSecurityOffice> list = findByF_maritimeCode(MaritimeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm vma security offices before and after the current dm vma security office in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param id the primary key of the current dm vma security office
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm vma security office
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaSecurityOfficeException if a dm vma security office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaSecurityOffice[] findByF_maritimeCode_PrevAndNext(long id,
		String MaritimeCode, OrderByComparator orderByComparator)
		throws NoSuchDmVmaSecurityOfficeException, SystemException {
		DmVmaSecurityOffice dmVmaSecurityOffice = findByPrimaryKey(id);

		

		try {
			

			DmVmaSecurityOffice[] array = new DmVmaSecurityOffice[3];

			array[0] = getByF_maritimeCode_PrevAndNext(
					dmVmaSecurityOffice, MaritimeCode, orderByComparator, true);

			array[1] = dmVmaSecurityOffice;

			array[2] = getByF_maritimeCode_PrevAndNext(
					dmVmaSecurityOffice, MaritimeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmVmaSecurityOffice getByF_maritimeCode_PrevAndNext(
		 DmVmaSecurityOffice dmVmaSecurityOffice,
		String MaritimeCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMVMASECURITYOFFICE_WHERE);

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
			query.append(DmVmaSecurityOfficeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (MaritimeCode != null) {
			builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmVmaSecurityOffice);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmVmaSecurityOffice> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm vma security office where SecurityOfficeCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaSecurityOfficeException} if it could not be found.
	 *
	 * @param SecurityOfficeCode the security office code
	 * @return the matching dm vma security office
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaSecurityOfficeException if a matching dm vma security office could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaSecurityOffice findByF_securityOfficeCode(
		String SecurityOfficeCode)
		throws NoSuchDmVmaSecurityOfficeException, SystemException {
		DmVmaSecurityOffice dmVmaSecurityOffice = fetchByF_securityOfficeCode(SecurityOfficeCode);

		if (dmVmaSecurityOffice == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("SecurityOfficeCode=");
			msg.append(SecurityOfficeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmVmaSecurityOfficeException(msg.toString());
		}

		return dmVmaSecurityOffice;
	}

	/**
	 * Returns the dm vma security office where SecurityOfficeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param SecurityOfficeCode the security office code
	 * @return the matching dm vma security office, or <code>null</code> if a matching dm vma security office could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaSecurityOffice fetchByF_securityOfficeCode(
		String SecurityOfficeCode) throws SystemException {
		return fetchByF_securityOfficeCode(SecurityOfficeCode, true);
	}

	/**
	 * Returns the dm vma security office where SecurityOfficeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param SecurityOfficeCode the security office code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm vma security office, or <code>null</code> if a matching dm vma security office could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaSecurityOffice fetchByF_securityOfficeCode(
		String SecurityOfficeCode, boolean retrieveFromCache)
		throws SystemException {
		DmVmaSecurityOffice dmVmaSecurityOffice = null;
		if (dmVmaSecurityOffice == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMVMASECURITYOFFICE_WHERE);

			if (SecurityOfficeCode == null) {
				query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SECURITYOFFICECODE_1);
			}
			else {
				if (SecurityOfficeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SECURITYOFFICECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SECURITYOFFICECODE_2);
				}
			}

			query.append(DmVmaSecurityOfficeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmVmaSecurityOffice.class).build();

				

				if (SecurityOfficeCode != null) {
					builder.appendNamedParameterMap("SecurityOfficeCode", SecurityOfficeCode);
				}

				dmVmaSecurityOffice = (DmVmaSecurityOffice) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmVmaSecurityOffice;
	}

	/**
	 * Returns all the dm vma security offices.
	 *
	 * @return the dm vma security offices
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaSecurityOffice> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma security offices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma security offices
	 * @param end the upper bound of the range of dm vma security offices (not inclusive)
	 * @return the range of dm vma security offices
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaSecurityOffice> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma security offices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma security offices
	 * @param end the upper bound of the range of dm vma security offices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm vma security offices
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaSecurityOffice> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaSecurityOffice> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMVMASECURITYOFFICE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMVMASECURITYOFFICE.concat(DmVmaSecurityOfficeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmVmaSecurityOffice>) queryFactory.getResultList(builder);
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
	 * Removes all the dm vma security offices where MaritimeCode = &#63; from the database.
	 *
	 * @param MaritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		for (DmVmaSecurityOffice dmVmaSecurityOffice : findByF_maritimeCode(
				MaritimeCode)) {
			repository.delete(dmVmaSecurityOffice);
		}
	}

	/**
	 * Removes the dm vma security office where SecurityOfficeCode = &#63; from the database.
	 *
	 * @param SecurityOfficeCode the security office code
	 * @return the dm vma security office that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaSecurityOffice removeByF_securityOfficeCode(
		String SecurityOfficeCode)
		throws NoSuchDmVmaSecurityOfficeException, SystemException {
		DmVmaSecurityOffice dmVmaSecurityOffice = findByF_securityOfficeCode(SecurityOfficeCode);

		repository.delete(dmVmaSecurityOffice);
			return dmVmaSecurityOffice;
	}

	/**
	 * Removes all the dm vma security offices from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmVmaSecurityOffice dmVmaSecurityOffice : findAll()) {
			repository.delete(dmVmaSecurityOffice);
		}
	}

	/**
	 * Returns the number of dm vma security offices where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the number of matching dm vma security offices
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMASECURITYOFFICE_WHERE);

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
	 * Returns the number of dm vma security offices where SecurityOfficeCode = &#63;.
	 *
	 * @param SecurityOfficeCode the security office code
	 * @return the number of matching dm vma security offices
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_securityOfficeCode(String SecurityOfficeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMASECURITYOFFICE_WHERE);

			if (SecurityOfficeCode == null) {
				query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SECURITYOFFICECODE_1);
			}
			else {
				if (SecurityOfficeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SECURITYOFFICECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SECURITYOFFICECODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (SecurityOfficeCode != null) {
					builder.appendNamedParameterMap("SecurityOfficeCode", SecurityOfficeCode);
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
	 * Returns the number of dm vma security offices.
	 *
	 * @return the number of dm vma security offices
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMVMASECURITYOFFICE).build();

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
	 * Initializes the dm vma security office persistence.
	 */
	private static final String _SQL_SELECT_DMVMASECURITYOFFICE = "SELECT dmVmaSecurityOffice FROM DmVmaSecurityOffice dmVmaSecurityOffice";
	private static final String _SQL_SELECT_DMVMASECURITYOFFICE_WHERE = "SELECT dmVmaSecurityOffice FROM DmVmaSecurityOffice dmVmaSecurityOffice WHERE ";
	private static final String _SQL_COUNT_DMVMASECURITYOFFICE = "SELECT COUNT(dmVmaSecurityOffice) FROM DmVmaSecurityOffice dmVmaSecurityOffice";
	private static final String _SQL_COUNT_DMVMASECURITYOFFICE_WHERE = "SELECT COUNT(dmVmaSecurityOffice) FROM DmVmaSecurityOffice dmVmaSecurityOffice WHERE ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1 = "dmVmaSecurityOffice.MaritimeCode IS NULL";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2 = "dmVmaSecurityOffice.MaritimeCode =:MaritimeCode";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3 = "(dmVmaSecurityOffice.MaritimeCode IS NULL OR dmVmaSecurityOffice.MaritimeCode =:MaritimeCode)";
	private static final String _FINDER_COLUMN_F_SECURITYOFFICECODE_SECURITYOFFICECODE_1 =
		"dmVmaSecurityOffice.SecurityOfficeCode IS NULL";
	private static final String _FINDER_COLUMN_F_SECURITYOFFICECODE_SECURITYOFFICECODE_2 =
		"dmVmaSecurityOffice.SecurityOfficeCode =:SecurityOfficeCode";
	private static final String _FINDER_COLUMN_F_SECURITYOFFICECODE_SECURITYOFFICECODE_3 =
		"(dmVmaSecurityOffice.SecurityOfficeCode IS NULL OR dmVmaSecurityOffice.SecurityOfficeCode =:SecurityOfficeCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmVmaSecurityOffice.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmVmaSecurityOffice exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmVmaSecurityOffice exists with the key {";
	

	
}