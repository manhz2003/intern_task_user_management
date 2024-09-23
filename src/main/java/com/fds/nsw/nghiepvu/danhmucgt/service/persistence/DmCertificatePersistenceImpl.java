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

package com.fds.nsw.nghiepvu.danhmucgt.service.persistence;

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
import com.fds.nsw.nghiepvu.model.DmCertificate;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmCertificateRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmCertificateModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmCertificatePersistenceImpl extends BasePersistence {
	@Autowired
	DmCertificateRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmCertificate> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmCertificateUtil} to access the dm certificate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmCertificate create(long id) {
		DmCertificate dmCertificate = new DmCertificate();

		
		//dmCertificate.setPrimaryKey(id);

		return dmCertificate;
	}

	/**
	 * Removes the dm certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm certificate
	 * @return the dm certificate that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a dm certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate remove(long id)
		throws NoSuchDmCertificateException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm certificate
	 * @return the dm certificate that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a dm certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmCertificate remove(Serializable primaryKey)
		throws NoSuchDmCertificateException, SystemException {
		

		try {
			

			DmCertificate dmCertificate = findByPrimaryKey(primaryKey);

			if (dmCertificate == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmCertificateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmCertificate);
			return dmCertificate;
		}
		catch (NoSuchDmCertificateException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmCertificate remove(DmCertificate DmCertificate) throws SystemException {
	removeImpl(DmCertificate);
	return DmCertificate;
}

protected DmCertificate removeImpl(DmCertificate dmCertificate)
		throws SystemException {
		try {
			repository.delete(dmCertificate);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmCertificate;
	}

	
	public DmCertificate updateImpl(
		DmCertificate dmCertificate, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmCertificate);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmCertificate;
	}

	
	public DmCertificate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm certificate with the primary key or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmCertificateException} if it could not be found.
	 *
	 * @param id the primary key of the dm certificate
	 * @return the dm certificate
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a dm certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate findByPrimaryKey(long id)
		throws NoSuchDmCertificateException, SystemException {
		DmCertificate dmCertificate = fetchByPrimaryKey(id);

		if (dmCertificate == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmCertificateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmCertificate;
	}

	/**
	 * Returns the dm certificate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm certificate
	 * @return the dm certificate, or <code>null</code> if a dm certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmCertificate fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm certificate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm certificate
	 * @return the dm certificate, or <code>null</code> if a dm certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate fetchByPrimaryKey(long id) throws SystemException {
		DmCertificate dmCertificate = null;

		

		if (dmCertificate == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmCertificate> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmCertificate = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmCertificate;
	}

	/**
	 * Returns all the dm certificates where certificateCode = &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @return the matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findByCertificateCode(String certificateCode)
		throws SystemException {
		return findByCertificateCode(certificateCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm certificates where certificateCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param certificateCode the certificate code
	 * @param start the lower bound of the range of dm certificates
	 * @param end the upper bound of the range of dm certificates (not inclusive)
	 * @return the range of matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findByCertificateCode(String certificateCode,
		int start, int end) throws SystemException {
		return findByCertificateCode(certificateCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm certificates where certificateCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param certificateCode the certificate code
	 * @param start the lower bound of the range of dm certificates
	 * @param end the upper bound of the range of dm certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findByCertificateCode(String certificateCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmCertificate> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMCERTIFICATE_WHERE);

			if (certificateCode == null) {
				query.append(_FINDER_COLUMN_CERTIFICATECODE_CERTIFICATECODE_1);
			}
			else {
				if (certificateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CERTIFICATECODE_CERTIFICATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_CERTIFICATECODE_CERTIFICATECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmCertificateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (certificateCode != null) {
					builder.appendNamedParameterMap("certificateCode", certificateCode);
				}

				list = (List<DmCertificate>)queryFactory.getResultList(builder);
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
	 * Returns the first dm certificate in the ordered set where certificateCode = &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm certificate
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate findByCertificateCode_First(String certificateCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmCertificateException, SystemException {
		DmCertificate dmCertificate = fetchByCertificateCode_First(certificateCode,
				orderByComparator);

		if (dmCertificate != null) {
			return dmCertificate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("certificateCode=");
		msg.append(certificateCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmCertificateException(msg.toString());
	}

	/**
	 * Returns the first dm certificate in the ordered set where certificateCode = &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm certificate, or <code>null</code> if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate fetchByCertificateCode_First(String certificateCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmCertificate> list = findByCertificateCode(certificateCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm certificate in the ordered set where certificateCode = &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm certificate
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate findByCertificateCode_Last(String certificateCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmCertificateException, SystemException {
		DmCertificate dmCertificate = fetchByCertificateCode_Last(certificateCode,
				orderByComparator);

		if (dmCertificate != null) {
			return dmCertificate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("certificateCode=");
		msg.append(certificateCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmCertificateException(msg.toString());
	}

	/**
	 * Returns the last dm certificate in the ordered set where certificateCode = &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm certificate, or <code>null</code> if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate fetchByCertificateCode_Last(String certificateCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCertificateCode(certificateCode);

		List<DmCertificate> list = findByCertificateCode(certificateCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm certificates before and after the current dm certificate in the ordered set where certificateCode = &#63;.
	 *
	 * @param id the primary key of the current dm certificate
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm certificate
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a dm certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate[] findByCertificateCode_PrevAndNext(long id,
		String certificateCode, OrderByComparator orderByComparator)
		throws NoSuchDmCertificateException, SystemException {
		DmCertificate dmCertificate = findByPrimaryKey(id);

		

		try {
			

			DmCertificate[] array = new DmCertificate[3];

			array[0] = getByCertificateCode_PrevAndNext(dmCertificate,
					certificateCode, orderByComparator, true);

			array[1] = dmCertificate;

			array[2] = getByCertificateCode_PrevAndNext(dmCertificate,
					certificateCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmCertificate getByCertificateCode_PrevAndNext(
		DmCertificate dmCertificate, String certificateCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMCERTIFICATE_WHERE);

		if (certificateCode == null) {
			query.append(_FINDER_COLUMN_CERTIFICATECODE_CERTIFICATECODE_1);
		}
		else {
			if (certificateCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CERTIFICATECODE_CERTIFICATECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_CERTIFICATECODE_CERTIFICATECODE_2);
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
			query.append(DmCertificateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (certificateCode != null) {
			builder.appendNamedParameterMap("certificateCode", certificateCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmCertificate);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmCertificate> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm certificates where certificateCode = &#63; and certificateName = &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param certificateName the certificate name
	 * @return the matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findByCertificateCodeAndCertificateName(
		String certificateCode, String certificateName)
		throws SystemException {
		return findByCertificateCodeAndCertificateName(certificateCode,
			certificateName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm certificates where certificateCode = &#63; and certificateName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param certificateCode the certificate code
	 * @param certificateName the certificate name
	 * @param start the lower bound of the range of dm certificates
	 * @param end the upper bound of the range of dm certificates (not inclusive)
	 * @return the range of matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findByCertificateCodeAndCertificateName(
		String certificateCode, String certificateName, int start, int end)
		throws SystemException {
		return findByCertificateCodeAndCertificateName(certificateCode,
			certificateName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm certificates where certificateCode = &#63; and certificateName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param certificateCode the certificate code
	 * @param certificateName the certificate name
	 * @param start the lower bound of the range of dm certificates
	 * @param end the upper bound of the range of dm certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findByCertificateCodeAndCertificateName(
		String certificateCode, String certificateName, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmCertificate> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DMCERTIFICATE_WHERE);

			if (certificateCode == null) {
				query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATECODE_1);
			}
			else {
				if (certificateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATECODE_2);
				}
			}

			if (certificateName == null) {
				query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATENAME_1);
			}
			else {
				if (certificateName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATENAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmCertificateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (certificateCode != null) {
					builder.appendNamedParameterMap("certificateCode", certificateCode);
				}

				if (certificateName != null) {
					builder.appendNamedParameterMap("certificateName", certificateName);
				}

				list = (List<DmCertificate>)queryFactory.getResultList(builder);
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
	 * Returns the first dm certificate in the ordered set where certificateCode = &#63; and certificateName = &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param certificateName the certificate name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm certificate
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate findByCertificateCodeAndCertificateName_First(
		String certificateCode, String certificateName,
		OrderByComparator orderByComparator)
		throws NoSuchDmCertificateException, SystemException {
		DmCertificate dmCertificate = fetchByCertificateCodeAndCertificateName_First(certificateCode,
				certificateName, orderByComparator);

		if (dmCertificate != null) {
			return dmCertificate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("certificateCode=");
		msg.append(certificateCode);

		msg.append(", certificateName=");
		msg.append(certificateName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmCertificateException(msg.toString());
	}

	/**
	 * Returns the first dm certificate in the ordered set where certificateCode = &#63; and certificateName = &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param certificateName the certificate name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm certificate, or <code>null</code> if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate fetchByCertificateCodeAndCertificateName_First(
		String certificateCode, String certificateName,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmCertificate> list = findByCertificateCodeAndCertificateName(certificateCode,
				certificateName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm certificate in the ordered set where certificateCode = &#63; and certificateName = &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param certificateName the certificate name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm certificate
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate findByCertificateCodeAndCertificateName_Last(
		String certificateCode, String certificateName,
		OrderByComparator orderByComparator)
		throws NoSuchDmCertificateException, SystemException {
		DmCertificate dmCertificate = fetchByCertificateCodeAndCertificateName_Last(certificateCode,
				certificateName, orderByComparator);

		if (dmCertificate != null) {
			return dmCertificate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("certificateCode=");
		msg.append(certificateCode);

		msg.append(", certificateName=");
		msg.append(certificateName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmCertificateException(msg.toString());
	}

	/**
	 * Returns the last dm certificate in the ordered set where certificateCode = &#63; and certificateName = &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param certificateName the certificate name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm certificate, or <code>null</code> if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate fetchByCertificateCodeAndCertificateName_Last(
		String certificateCode, String certificateName,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCertificateCodeAndCertificateName(certificateCode,
				certificateName);

		List<DmCertificate> list = findByCertificateCodeAndCertificateName(certificateCode,
				certificateName, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm certificates before and after the current dm certificate in the ordered set where certificateCode = &#63; and certificateName = &#63;.
	 *
	 * @param id the primary key of the current dm certificate
	 * @param certificateCode the certificate code
	 * @param certificateName the certificate name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm certificate
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a dm certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate[] findByCertificateCodeAndCertificateName_PrevAndNext(
		long id, String certificateCode, String certificateName,
		OrderByComparator orderByComparator)
		throws NoSuchDmCertificateException, SystemException {
		DmCertificate dmCertificate = findByPrimaryKey(id);

		

		try {
			

			DmCertificate[] array = new DmCertificate[3];

			array[0] = getByCertificateCodeAndCertificateName_PrevAndNext(
					dmCertificate, certificateCode, certificateName,
					orderByComparator, true);

			array[1] = dmCertificate;

			array[2] = getByCertificateCodeAndCertificateName_PrevAndNext(
					dmCertificate, certificateCode, certificateName,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmCertificate getByCertificateCodeAndCertificateName_PrevAndNext(
		 DmCertificate dmCertificate, String certificateCode,
		String certificateName, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMCERTIFICATE_WHERE);

		if (certificateCode == null) {
			query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATECODE_1);
		}
		else {
			if (certificateCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATECODE_2);
			}
		}

		if (certificateName == null) {
			query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATENAME_1);
		}
		else {
			if (certificateName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATENAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATENAME_2);
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
			query.append(DmCertificateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (certificateCode != null) {
			builder.appendNamedParameterMap("certificateCode", certificateCode);
		}

		if (certificateName != null) {
			builder.appendNamedParameterMap("certificateName", certificateName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmCertificate);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmCertificate> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm certificates where certificateCode &gt; &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @return the matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findByF_LCODE_GT(String certificateCode)
		throws SystemException {
		return findByF_LCODE_GT(certificateCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm certificates where certificateCode &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param certificateCode the certificate code
	 * @param start the lower bound of the range of dm certificates
	 * @param end the upper bound of the range of dm certificates (not inclusive)
	 * @return the range of matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findByF_LCODE_GT(String certificateCode,
		int start, int end) throws SystemException {
		return findByF_LCODE_GT(certificateCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm certificates where certificateCode &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param certificateCode the certificate code
	 * @param start the lower bound of the range of dm certificates
	 * @param end the upper bound of the range of dm certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findByF_LCODE_GT(String certificateCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmCertificate> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMCERTIFICATE_WHERE);

			if (certificateCode == null) {
				query.append(_FINDER_COLUMN_F_LCODE_GT_CERTIFICATECODE_1);
			}
			else {
				if (certificateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_LCODE_GT_CERTIFICATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_LCODE_GT_CERTIFICATECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmCertificateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (certificateCode != null) {
					builder.appendNamedParameterMap("certificateCode", certificateCode);
				}

				list = (List<DmCertificate>)queryFactory.getResultList(builder);
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
	 * Returns the first dm certificate in the ordered set where certificateCode &gt; &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm certificate
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate findByF_LCODE_GT_First(String certificateCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmCertificateException, SystemException {
		DmCertificate dmCertificate = fetchByF_LCODE_GT_First(certificateCode,
				orderByComparator);

		if (dmCertificate != null) {
			return dmCertificate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("certificateCode=");
		msg.append(certificateCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmCertificateException(msg.toString());
	}

	/**
	 * Returns the first dm certificate in the ordered set where certificateCode &gt; &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm certificate, or <code>null</code> if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate fetchByF_LCODE_GT_First(String certificateCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmCertificate> list = findByF_LCODE_GT(certificateCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm certificate in the ordered set where certificateCode &gt; &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm certificate
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate findByF_LCODE_GT_Last(String certificateCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmCertificateException, SystemException {
		DmCertificate dmCertificate = fetchByF_LCODE_GT_Last(certificateCode,
				orderByComparator);

		if (dmCertificate != null) {
			return dmCertificate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("certificateCode=");
		msg.append(certificateCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmCertificateException(msg.toString());
	}

	/**
	 * Returns the last dm certificate in the ordered set where certificateCode &gt; &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm certificate, or <code>null</code> if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate fetchByF_LCODE_GT_Last(String certificateCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_LCODE_GT(certificateCode);

		List<DmCertificate> list = findByF_LCODE_GT(certificateCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm certificates before and after the current dm certificate in the ordered set where certificateCode &gt; &#63;.
	 *
	 * @param id the primary key of the current dm certificate
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm certificate
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a dm certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate[] findByF_LCODE_GT_PrevAndNext(long id,
		String certificateCode, OrderByComparator orderByComparator)
		throws NoSuchDmCertificateException, SystemException {
		DmCertificate dmCertificate = findByPrimaryKey(id);

		

		try {
			

			DmCertificate[] array = new DmCertificate[3];

			array[0] = getByF_LCODE_GT_PrevAndNext(dmCertificate,
					certificateCode, orderByComparator, true);

			array[1] = dmCertificate;

			array[2] = getByF_LCODE_GT_PrevAndNext(dmCertificate,
					certificateCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmCertificate getByF_LCODE_GT_PrevAndNext(
		DmCertificate dmCertificate, String certificateCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMCERTIFICATE_WHERE);

		if (certificateCode == null) {
			query.append(_FINDER_COLUMN_F_LCODE_GT_CERTIFICATECODE_1);
		}
		else {
			if (certificateCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_LCODE_GT_CERTIFICATECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_LCODE_GT_CERTIFICATECODE_2);
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
			query.append(DmCertificateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (certificateCode != null) {
			builder.appendNamedParameterMap("certificateCode", certificateCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmCertificate);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmCertificate> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm certificates where certificateCode &lt; &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @return the matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findByF_LCODE_LT(String certificateCode)
		throws SystemException {
		return findByF_LCODE_LT(certificateCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm certificates where certificateCode &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param certificateCode the certificate code
	 * @param start the lower bound of the range of dm certificates
	 * @param end the upper bound of the range of dm certificates (not inclusive)
	 * @return the range of matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findByF_LCODE_LT(String certificateCode,
		int start, int end) throws SystemException {
		return findByF_LCODE_LT(certificateCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm certificates where certificateCode &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param certificateCode the certificate code
	 * @param start the lower bound of the range of dm certificates
	 * @param end the upper bound of the range of dm certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findByF_LCODE_LT(String certificateCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmCertificate> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMCERTIFICATE_WHERE);

			if (certificateCode == null) {
				query.append(_FINDER_COLUMN_F_LCODE_LT_CERTIFICATECODE_1);
			}
			else {
				if (certificateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_LCODE_LT_CERTIFICATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_LCODE_LT_CERTIFICATECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmCertificateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (certificateCode != null) {
					builder.appendNamedParameterMap("certificateCode", certificateCode);
				}

				list = (List<DmCertificate>)queryFactory.getResultList(builder);
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
	 * Returns the first dm certificate in the ordered set where certificateCode &lt; &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm certificate
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate findByF_LCODE_LT_First(String certificateCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmCertificateException, SystemException {
		DmCertificate dmCertificate = fetchByF_LCODE_LT_First(certificateCode,
				orderByComparator);

		if (dmCertificate != null) {
			return dmCertificate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("certificateCode=");
		msg.append(certificateCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmCertificateException(msg.toString());
	}

	/**
	 * Returns the first dm certificate in the ordered set where certificateCode &lt; &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm certificate, or <code>null</code> if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate fetchByF_LCODE_LT_First(String certificateCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmCertificate> list = findByF_LCODE_LT(certificateCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm certificate in the ordered set where certificateCode &lt; &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm certificate
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate findByF_LCODE_LT_Last(String certificateCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmCertificateException, SystemException {
		DmCertificate dmCertificate = fetchByF_LCODE_LT_Last(certificateCode,
				orderByComparator);

		if (dmCertificate != null) {
			return dmCertificate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("certificateCode=");
		msg.append(certificateCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmCertificateException(msg.toString());
	}

	/**
	 * Returns the last dm certificate in the ordered set where certificateCode &lt; &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm certificate, or <code>null</code> if a matching dm certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate fetchByF_LCODE_LT_Last(String certificateCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_LCODE_LT(certificateCode);

		List<DmCertificate> list = findByF_LCODE_LT(certificateCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm certificates before and after the current dm certificate in the ordered set where certificateCode &lt; &#63;.
	 *
	 * @param id the primary key of the current dm certificate
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm certificate
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmCertificateException if a dm certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCertificate[] findByF_LCODE_LT_PrevAndNext(long id,
		String certificateCode, OrderByComparator orderByComparator)
		throws NoSuchDmCertificateException, SystemException {
		DmCertificate dmCertificate = findByPrimaryKey(id);

		

		try {
			

			DmCertificate[] array = new DmCertificate[3];

			array[0] = getByF_LCODE_LT_PrevAndNext(dmCertificate,
					certificateCode, orderByComparator, true);

			array[1] = dmCertificate;

			array[2] = getByF_LCODE_LT_PrevAndNext(dmCertificate,
					certificateCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmCertificate getByF_LCODE_LT_PrevAndNext(
		DmCertificate dmCertificate, String certificateCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMCERTIFICATE_WHERE);

		if (certificateCode == null) {
			query.append(_FINDER_COLUMN_F_LCODE_LT_CERTIFICATECODE_1);
		}
		else {
			if (certificateCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_LCODE_LT_CERTIFICATECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_LCODE_LT_CERTIFICATECODE_2);
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
			query.append(DmCertificateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (certificateCode != null) {
			builder.appendNamedParameterMap("certificateCode", certificateCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmCertificate);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmCertificate> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm certificates.
	 *
	 * @return the dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm certificates
	 * @param end the upper bound of the range of dm certificates (not inclusive)
	 * @return the range of dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm certificates
	 * @param end the upper bound of the range of dm certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCertificate> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmCertificate> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMCERTIFICATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMCERTIFICATE.concat(DmCertificateModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmCertificate>) queryFactory.getResultList(builder);
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
	 * Removes all the dm certificates where certificateCode = &#63; from the database.
	 *
	 * @param certificateCode the certificate code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCertificateCode(String certificateCode)
		throws SystemException {
		for (DmCertificate dmCertificate : findByCertificateCode(
				certificateCode)) {
			repository.delete(dmCertificate);
		}
	}

	/**
	 * Removes all the dm certificates where certificateCode = &#63; and certificateName = &#63; from the database.
	 *
	 * @param certificateCode the certificate code
	 * @param certificateName the certificate name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCertificateCodeAndCertificateName(
		String certificateCode, String certificateName)
		throws SystemException {
		for (DmCertificate dmCertificate : findByCertificateCodeAndCertificateName(
				certificateCode, certificateName)) {
			repository.delete(dmCertificate);
		}
	}

	/**
	 * Removes all the dm certificates where certificateCode &gt; &#63; from the database.
	 *
	 * @param certificateCode the certificate code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_LCODE_GT(String certificateCode)
		throws SystemException {
		for (DmCertificate dmCertificate : findByF_LCODE_GT(certificateCode)) {
			repository.delete(dmCertificate);
		}
	}

	/**
	 * Removes all the dm certificates where certificateCode &lt; &#63; from the database.
	 *
	 * @param certificateCode the certificate code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_LCODE_LT(String certificateCode)
		throws SystemException {
		for (DmCertificate dmCertificate : findByF_LCODE_LT(certificateCode)) {
			repository.delete(dmCertificate);
		}
	}

	/**
	 * Removes all the dm certificates from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmCertificate dmCertificate : findAll()) {
			repository.delete(dmCertificate);
		}
	}

	/**
	 * Returns the number of dm certificates where certificateCode = &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @return the number of matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCertificateCode(String certificateCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMCERTIFICATE_WHERE);

			if (certificateCode == null) {
				query.append(_FINDER_COLUMN_CERTIFICATECODE_CERTIFICATECODE_1);
			}
			else {
				if (certificateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CERTIFICATECODE_CERTIFICATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_CERTIFICATECODE_CERTIFICATECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (certificateCode != null) {
					builder.appendNamedParameterMap("certificateCode", certificateCode);
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
	 * Returns the number of dm certificates where certificateCode = &#63; and certificateName = &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @param certificateName the certificate name
	 * @return the number of matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCertificateCodeAndCertificateName(
		String certificateCode, String certificateName)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMCERTIFICATE_WHERE);

			if (certificateCode == null) {
				query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATECODE_1);
			}
			else {
				if (certificateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATECODE_2);
				}
			}

			if (certificateName == null) {
				query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATENAME_1);
			}
			else {
				if (certificateName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATENAME_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (certificateCode != null) {
					builder.appendNamedParameterMap("certificateCode", certificateCode);
				}

				if (certificateName != null) {
					builder.appendNamedParameterMap("certificateName", certificateName);
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
	 * Returns the number of dm certificates where certificateCode &gt; &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @return the number of matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_LCODE_GT(String certificateCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMCERTIFICATE_WHERE);

			if (certificateCode == null) {
				query.append(_FINDER_COLUMN_F_LCODE_GT_CERTIFICATECODE_1);
			}
			else {
				if (certificateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_LCODE_GT_CERTIFICATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_LCODE_GT_CERTIFICATECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (certificateCode != null) {
					builder.appendNamedParameterMap("certificateCode", certificateCode);
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
	 * Returns the number of dm certificates where certificateCode &lt; &#63;.
	 *
	 * @param certificateCode the certificate code
	 * @return the number of matching dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_LCODE_LT(String certificateCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMCERTIFICATE_WHERE);

			if (certificateCode == null) {
				query.append(_FINDER_COLUMN_F_LCODE_LT_CERTIFICATECODE_1);
			}
			else {
				if (certificateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_LCODE_LT_CERTIFICATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_LCODE_LT_CERTIFICATECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (certificateCode != null) {
					builder.appendNamedParameterMap("certificateCode", certificateCode);
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
	 * Returns the number of dm certificates.
	 *
	 * @return the number of dm certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMCERTIFICATE).build();

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
	 * Initializes the dm certificate persistence.
	 */
	private static final String _SQL_SELECT_DMCERTIFICATE = "SELECT dmCertificate FROM DmCertificate dmCertificate";
	private static final String _SQL_SELECT_DMCERTIFICATE_WHERE = "SELECT dmCertificate FROM DmCertificate dmCertificate WHERE ";
	private static final String _SQL_COUNT_DMCERTIFICATE = "SELECT COUNT(dmCertificate) FROM DmCertificate dmCertificate";
	private static final String _SQL_COUNT_DMCERTIFICATE_WHERE = "SELECT COUNT(dmCertificate) FROM DmCertificate dmCertificate WHERE ";
	private static final String _FINDER_COLUMN_CERTIFICATECODE_CERTIFICATECODE_1 =
		"dmCertificate.certificateCode IS NULL";
	private static final String _FINDER_COLUMN_CERTIFICATECODE_CERTIFICATECODE_2 =
		"dmCertificate.certificateCode =:certificateCode";
	private static final String _FINDER_COLUMN_CERTIFICATECODE_CERTIFICATECODE_3 =
		"(dmCertificate.certificateCode IS NULL OR dmCertificate.certificateCode =:certificateCode)";
	private static final String _FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATECODE_1 =
		"dmCertificate.certificateCode IS NULL AND ";
	private static final String _FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATECODE_2 =
		"dmCertificate.certificateCode =:certificateCode AND ";
	private static final String _FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATECODE_3 =
		"(dmCertificate.certificateCode IS NULL OR dmCertificate.certificateCode =:certificateCode) AND ";
	private static final String _FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATENAME_1 =
		"dmCertificate.certificateName IS NULL";
	private static final String _FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATENAME_2 =
		"dmCertificate.certificateName =:certificateName";
	private static final String _FINDER_COLUMN_CERTIFICATECODEANDCERTIFICATENAME_CERTIFICATENAME_3 =
		"(dmCertificate.certificateName IS NULL OR dmCertificate.certificateName =:certificateName)";
	private static final String _FINDER_COLUMN_F_LCODE_GT_CERTIFICATECODE_1 = "dmCertificate.certificateCode > NULL AND  ( dmCertificate.certificateCode != 39) ";
	private static final String _FINDER_COLUMN_F_LCODE_GT_CERTIFICATECODE_2 = "dmCertificate.certificateCode > ? AND  ( dmCertificate.certificateCode != 39) ";
	private static final String _FINDER_COLUMN_F_LCODE_GT_CERTIFICATECODE_3 = "(dmCertificate.certificateCode IS NULL OR dmCertificate.certificateCode > ?) AND  ( dmCertificate.certificateCode != 39) ";
	private static final String _FINDER_COLUMN_F_LCODE_LT_CERTIFICATECODE_1 = "dmCertificate.certificateCode < NULL AND  ( dmCertificate.certificateCode != 39) ";
	private static final String _FINDER_COLUMN_F_LCODE_LT_CERTIFICATECODE_2 = "dmCertificate.certificateCode < ? AND  ( dmCertificate.certificateCode != 39) ";
	private static final String _FINDER_COLUMN_F_LCODE_LT_CERTIFICATECODE_3 = "(dmCertificate.certificateCode IS NULL OR dmCertificate.certificateCode < ?) AND  ( dmCertificate.certificateCode != 39) ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmCertificate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmCertificate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmCertificate exists with the key {";
	

	
}
