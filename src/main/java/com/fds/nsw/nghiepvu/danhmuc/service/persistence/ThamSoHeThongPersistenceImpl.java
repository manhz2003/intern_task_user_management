/////**
//// * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
//// *
//// * This library is free software; you can redistribute it and/or modify it under
//// * the terms of the GNU Lesser General Public License as published by the Free
//// * Software Foundation; either version 2.1 of the License, or (at your option)
//// * any later version.
//// *
//// * This library is distributed in the hope that it will be useful, but WITHOUT
//// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
//// * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
//// * details.
//// */
////
////package com.fds.nsw.nghiepvu.danhmuc.service.persistence;
////
////import java.io.Serializable;
////import java.sql.Types;
////import java.util.List;
////import java.util.Optional;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.beans.factory.annotation.Qualifier;
////import org.springframework.stereotype.Service;
////
////import com.fds.flex.common.utility.string.StringBundler;
////import com.fds.flex.common.utility.string.StringPool;
////import com.fds.nsw.kernel.dao.orm.BasePersistence;
////import com.fds.nsw.kernel.dao.orm.QueryUtil;
////import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
////import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
////import com.fds.nsw.kernel.exception.SystemException;
////import com.fds.nsw.kernel.orm.exception.NoSuchModelException;
////import com.fds.nsw.kernel.util.OrderByComparator;
////import com.fds.nsw.nghiepvu.model.ThamSoHeThong;
////import com.fds.nsw.nghiepvu.service.exception.*;
////import com.fds.nsw.nghiepvu.repository.ThamSoHeThongRepository;
////import com.fds.nsw.nghiepvu.modelImpl.ThamSoHeThongModelImpl;
////
////
////import lombok.extern.slf4j.Slf4j;
////
////@Slf4j
////@Service
////public class ThamSoHeThongPersistenceImpl extends BasePersistence {
////	@Autowired
////	ThamSoHeThongRepository repository;
////	@Autowired
////	@Qualifier("blQueryFactory")
////	QueryFactory<ThamSoHeThong> queryFactory;
////	/*
////	 * NOTE FOR DEVELOPERS:
////	 *
////	 * Never modify or reference this class directly. Always use {@link ThamSoHeThongUtil} to access the tham so he thong persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
////	 */
////	public ThamSoHeThong create(long id) {
////		ThamSoHeThong thamSoHeThong = new ThamSoHeThong();
////
////
////		//thamSoHeThong.setPrimaryKey(id);
////
////		return thamSoHeThong;
////	}
////
////	/**
////	 * Removes the tham so he thong with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param id the primary key of the tham so he thong
////	 * @return the tham so he thong that was removed
////	 * @throws vn.gt.dao.danhmuc.NoSuchThamSoHeThongException if a tham so he thong with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public ThamSoHeThong remove(long id)
////		throws NoSuchThamSoHeThongException, SystemException {
////		return remove(Long.valueOf(id));
////	}
////
////	/**
////	 * Removes the tham so he thong with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param primaryKey the primary key of the tham so he thong
////	 * @return the tham so he thong that was removed
////	 * @throws vn.gt.dao.danhmuc.NoSuchThamSoHeThongException if a tham so he thong with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public ThamSoHeThong remove(Serializable primaryKey)
////		throws NoSuchThamSoHeThongException, SystemException {
////
////
////		try {
////
////
////			ThamSoHeThong thamSoHeThong = findByPrimaryKey(primaryKey);
////
////			if (thamSoHeThong == null) {
////				if (log.isWarnEnabled()) {
////					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
////				}
////
////				throw new NoSuchThamSoHeThongException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////					primaryKey);
////			}
////
////			repository.delete(thamSoHeThong);
////			return thamSoHeThong;
////		}
////		catch (NoSuchThamSoHeThongException nsee) {
////			throw nsee;
////		}
////		catch (Exception e) {
////			throw processException(e);
////		}
////		finally {
////
////		}
////	}
////
////
////	public ThamSoHeThong remove(ThamSoHeThong ThamSoHeThong) throws SystemException {
//	removeImpl(ThamSoHeThong);	return ThamSoHeThong;
//}
//
//protected ThamSoHeThong removeImpl
//
//(ThamSoHeThong thamSoHeThong)
////		throws SystemException {
////		try {
////			repository.delete(thamSoHeThong);
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return thamSoHeThong;
////	}
////
////
////	public ThamSoHeThong updateImpl(
////		ThamSoHeThong thamSoHeThong, boolean merge)
////		throws SystemException {
////		try {
////
////			repository.saveAndFlush(thamSoHeThong);
////
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return thamSoHeThong;
////	}
////
////
////	public ThamSoHeThong findByPrimaryKey(Serializable primaryKey)
////		throws NoSuchModelException, SystemException {
////		return findByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the tham so he thong with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchThamSoHeThongException} if it could not be found.
////	 *
////	 * @param id the primary key of the tham so he thong
////	 * @return the tham so he thong
////	 * @throws vn.gt.dao.danhmuc.NoSuchThamSoHeThongException if a tham so he thong with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public ThamSoHeThong findByPrimaryKey(long id)
////		throws NoSuchThamSoHeThongException, SystemException {
////		ThamSoHeThong thamSoHeThong = fetchByPrimaryKey(id);
////
////		if (thamSoHeThong == null) {
////			if (log.isWarnEnabled()) {
////				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
////			}
////
////			throw new NoSuchThamSoHeThongException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////				id);
////		}
////
////		return thamSoHeThong;
////	}
////
////	/**
////	 * Returns the tham so he thong with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param primaryKey the primary key of the tham so he thong
////	 * @return the tham so he thong, or <code>null</code> if a tham so he thong with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public ThamSoHeThong fetchByPrimaryKey(Serializable primaryKey)
////		throws SystemException {
////		return fetchByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the tham so he thong with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param id the primary key of the tham so he thong
////	 * @return the tham so he thong, or <code>null</code> if a tham so he thong with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public ThamSoHeThong fetchByPrimaryKey(long id) throws SystemException {
////		ThamSoHeThong thamSoHeThong = null;
////
////
////
////		if (thamSoHeThong == null) {
////
////
////			boolean hasException = false;
////
////			try {
////
////
////				Optional<ThamSoHeThong> optional = repository.findById(id);
////				if (optional.isPresent()) {
////					thamSoHeThong = optional.get();
////				}
////			}
////			catch (Exception e) {
////				hasException = true;
////
////				throw processException(e);
////			}
////			finally {
////
////
////
////			}
////		}
////
////		return thamSoHeThong;
////	}
////
////	/**
////	 * Returns the tham so he thong where keyData = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchThamSoHeThongException} if it could not be found.
////	 *
////	 * @param keyData the key data
////	 * @return the matching tham so he thong
////	 * @throws vn.gt.dao.danhmuc.NoSuchThamSoHeThongException if a matching tham so he thong could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public ThamSoHeThong findByKeyData(String keyData)
////		throws NoSuchThamSoHeThongException, SystemException {
////		ThamSoHeThong thamSoHeThong = fetchByKeyData(keyData);
////
////		if (thamSoHeThong == null) {
////			StringBundler msg = new StringBundler(4);
////
////			msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////			msg.append("keyData=");
////			msg.append(keyData);
////
////			msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////			if (log.isWarnEnabled()) {
////				log.warn(msg.toString());
////			}
////
////			throw new NoSuchThamSoHeThongException(msg.toString());
////		}
////
////		return thamSoHeThong;
////	}
////
////	/**
////	 * Returns the tham so he thong where keyData = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
////	 *
////	 * @param keyData the key data
////	 * @return the matching tham so he thong, or <code>null</code> if a matching tham so he thong could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public ThamSoHeThong fetchByKeyData(String keyData)
////		throws SystemException {
////		return fetchByKeyData(keyData, true);
////	}
////
////	/**
////	 * Returns the tham so he thong where keyData = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
////	 *
////	 * @param keyData the key data
////	 * @param retrieveFromCache whether to use the finder cache
////	 * @return the matching tham so he thong, or <code>null</code> if a matching tham so he thong could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public ThamSoHeThong fetchByKeyData(String keyData,
////		boolean retrieveFromCache) throws SystemException {
////		ThamSoHeThong thamSoHeThong = null;
////		if (thamSoHeThong == null) {
////			StringBundler query = new StringBundler(3);
////
////			query.append(_SQL_SELECT_THAMSOHETHONG_WHERE);
////
////			if (keyData == null) {
////				query.append(_FINDER_COLUMN_KEYDATA_KEYDATA_1);
////			}
////			else {
////				if (keyData.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_KEYDATA_KEYDATA_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_KEYDATA_KEYDATA_2);
////				}
////			}
////
////			query.append(ThamSoHeThongModelImpl.ORDER_BY_JPQL);
////
////			String sql = query.toString();
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////
////
////				if (keyData != null) {
////					builder.appendNamedParameterMap("keyData", keyData);
////				}
////
////				thamSoHeThong = (ThamSoHeThong) queryFactory.getSingleResult(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////
////
////
////			}
////		}
////		return thamSoHeThong;
////	}
////
////	/**
////	 * Returns the tham so he thong where keyData = &#63; and description = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchThamSoHeThongException} if it could not be found.
////	 *
////	 * @param keyData the key data
////	 * @param description the description
////	 * @return the matching tham so he thong
////	 * @throws vn.gt.dao.danhmuc.NoSuchThamSoHeThongException if a matching tham so he thong could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public ThamSoHeThong findByKeyDataAndDescription(String keyData,
////		String description)
////		throws NoSuchThamSoHeThongException, SystemException {
////		ThamSoHeThong thamSoHeThong = fetchByKeyDataAndDescription(keyData,
////				description);
////
////		if (thamSoHeThong == null) {
////			StringBundler msg = new StringBundler(6);
////
////			msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////			msg.append("keyData=");
////			msg.append(keyData);
////
////			msg.append(", description=");
////			msg.append(description);
////
////			msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////			if (log.isWarnEnabled()) {
////				log.warn(msg.toString());
////			}
////
////			throw new NoSuchThamSoHeThongException(msg.toString());
////		}
////
////		return thamSoHeThong;
////	}
////
////	/**
////	 * Returns the tham so he thong where keyData = &#63; and description = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
////	 *
////	 * @param keyData the key data
////	 * @param description the description
////	 * @return the matching tham so he thong, or <code>null</code> if a matching tham so he thong could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public ThamSoHeThong fetchByKeyDataAndDescription(String keyData,
////		String description) throws SystemException {
////		return fetchByKeyDataAndDescription(keyData, description, true);
////	}
////
////	/**
////	 * Returns the tham so he thong where keyData = &#63; and description = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
////	 *
////	 * @param keyData the key data
////	 * @param description the description
////	 * @param retrieveFromCache whether to use the finder cache
////	 * @return the matching tham so he thong, or <code>null</code> if a matching tham so he thong could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public ThamSoHeThong fetchByKeyDataAndDescription(String keyData,
////		String description, boolean retrieveFromCache)
////		throws SystemException {
////		ThamSoHeThong thamSoHeThong = null;
////		if (thamSoHeThong == null) {
////			StringBundler query = new StringBundler(4);
////
////			query.append(_SQL_SELECT_THAMSOHETHONG_WHERE);
////
////			if (keyData == null) {
////				query.append(_FINDER_COLUMN_KEYDATAANDDESCRIPTION_KEYDATA_1);
////			}
////			else {
////				if (keyData.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_KEYDATAANDDESCRIPTION_KEYDATA_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_KEYDATAANDDESCRIPTION_KEYDATA_2);
////				}
////			}
////
////			if (description == null) {
////				query.append(_FINDER_COLUMN_KEYDATAANDDESCRIPTION_DESCRIPTION_1);
////			}
////			else {
////				if (description.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_KEYDATAANDDESCRIPTION_DESCRIPTION_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_KEYDATAANDDESCRIPTION_DESCRIPTION_2);
////				}
////			}
////
////			query.append(ThamSoHeThongModelImpl.ORDER_BY_JPQL);
////
////			String sql = query.toString();
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////
////
////				if (keyData != null) {
////					builder.appendNamedParameterMap("keyData", keyData);
////				}
////
////				if (description != null) {
////					builder.appendNamedParameterMap("description", description);
////				}
////
////				thamSoHeThong = (ThamSoHeThong) queryFactory.getSingleResult(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////
////
////
////			}
////		}
////		return thamSoHeThong;
////	}
////
////	/**
////	 * Returns all the tham so he thongs.
////	 *
////	 * @return the tham so he thongs
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<ThamSoHeThong> findAll() throws SystemException {
////		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the tham so he thongs.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of tham so he thongs
////	 * @param end the upper bound of the range of tham so he thongs (not inclusive)
////	 * @return the range of tham so he thongs
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<ThamSoHeThong> findAll(int start, int end)
////		throws SystemException {
////		return findAll(start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the tham so he thongs.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of tham so he thongs
////	 * @param end the upper bound of the range of tham so he thongs (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of tham so he thongs
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<ThamSoHeThong> findAll(int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<ThamSoHeThong> list = null;
////		if (list == null) {
////			StringBundler query = null;
////			String sql = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(2 +
////						(orderByComparator.getOrderByFields().length * 3));
////
////				query.append(_SQL_SELECT_THAMSOHETHONG);
////
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////
////				sql = query.toString();
////			}
////			else {
////				sql = _SQL_SELECT_THAMSOHETHONG.concat(ThamSoHeThongModelImpl.ORDER_BY_JPQL);
////			}
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////				list = (List<ThamSoHeThong>) queryFactory.getResultList(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////
////
////
////			}
////		}
////
////		return list;
////	}
////
////	/**
////	 * Removes the tham so he thong where keyData = &#63; from the database.
////	 *
////	 * @param keyData the key data
////	 * @return the tham so he thong that was removed
////	 * @throws SystemException if a system exception occurred
////	 */
////	public ThamSoHeThong removeByKeyData(String keyData)
////		throws NoSuchThamSoHeThongException, SystemException {
////		ThamSoHeThong thamSoHeThong = findByKeyData(keyData);
////
////		repository.delete(thamSoHeThong);
////			return thamSoHeThong;
////	}
////
////	/**
////	 * Removes the tham so he thong where keyData = &#63; and description = &#63; from the database.
////	 *
////	 * @param keyData the key data
////	 * @param description the description
////	 * @return the tham so he thong that was removed
////	 * @throws SystemException if a system exception occurred
////	 */
////	public ThamSoHeThong removeByKeyDataAndDescription(String keyData,
////		String description)
////		throws NoSuchThamSoHeThongException, SystemException {
////		ThamSoHeThong thamSoHeThong = findByKeyDataAndDescription(keyData,
////				description);
////
////		repository.delete(thamSoHeThong);
////			return thamSoHeThong;
////	}
////
////	/**
////	 * Removes all the tham so he thongs from the database.
////	 *
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeAll() throws SystemException {
////		for (ThamSoHeThong thamSoHeThong : findAll()) {
////			repository.delete(thamSoHeThong);
////		}
////	}
////
////	/**
////	 * Returns the number of tham so he thongs where keyData = &#63;.
////	 *
////	 * @param keyData the key data
////	 * @return the number of matching tham so he thongs
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countByKeyData(String keyData) throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(2);
////
////			query.append(_SQL_COUNT_THAMSOHETHONG_WHERE);
////
////			if (keyData == null) {
////				query.append(_FINDER_COLUMN_KEYDATA_KEYDATA_1);
////			}
////			else {
////				if (keyData.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_KEYDATA_KEYDATA_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_KEYDATA_KEYDATA_2);
////				}
////			}
////
////			String sql = query.toString();
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////
////
////				if (keyData != null) {
////					builder.appendNamedParameterMap("keyData", keyData);
////				}
////
////				count = (Long)queryFactory.getSingleResult(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////				if (count == null) {
////					count = Long.valueOf(0);
////				}
////
////
////
////
////			}
////		}
////
////		return count.intValue();
////	}
////
////	/**
////	 * Returns the number of tham so he thongs where keyData = &#63; and description = &#63;.
////	 *
////	 * @param keyData the key data
////	 * @param description the description
////	 * @return the number of matching tham so he thongs
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countByKeyDataAndDescription(String keyData, String description)
////		throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(3);
////
////			query.append(_SQL_COUNT_THAMSOHETHONG_WHERE);
////
////			if (keyData == null) {
////				query.append(_FINDER_COLUMN_KEYDATAANDDESCRIPTION_KEYDATA_1);
////			}
////			else {
////				if (keyData.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_KEYDATAANDDESCRIPTION_KEYDATA_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_KEYDATAANDDESCRIPTION_KEYDATA_2);
////				}
////			}
////
////			if (description == null) {
////				query.append(_FINDER_COLUMN_KEYDATAANDDESCRIPTION_DESCRIPTION_1);
////			}
////			else {
////				if (description.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_KEYDATAANDDESCRIPTION_DESCRIPTION_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_KEYDATAANDDESCRIPTION_DESCRIPTION_2);
////				}
////			}
////
////			String sql = query.toString();
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////
////
////				if (keyData != null) {
////					builder.appendNamedParameterMap("keyData", keyData);
////				}
////
////				if (description != null) {
////					builder.appendNamedParameterMap("description", description);
////				}
////
////				count = (Long)queryFactory.getSingleResult(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////				if (count == null) {
////					count = Long.valueOf(0);
////				}
////
////
////
////
////			}
////		}
////
////		return count.intValue();
////	}
////
////	/**
////	 * Returns the number of tham so he thongs.
////	 *
////	 * @return the number of tham so he thongs
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countAll() throws SystemException {
////		Long count = null;
////
////		if (count == null) {
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_THAMSOHETHONG).build();
////
////				count = (Long)queryFactory.getSingleResult(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////				if (count == null) {
////					count = Long.valueOf(0);
////				}
////
////
////
////
////			}
////		}
////
////		return count.intValue();
////	}
////
////	/**
////	 * Initializes the tham so he thong persistence.
////	 */
////	private static final String _SQL_SELECT_THAMSOHETHONG = "SELECT thamSoHeThong FROM ThamSoHeThong thamSoHeThong";
////	private static final String _SQL_SELECT_THAMSOHETHONG_WHERE = "SELECT thamSoHeThong FROM ThamSoHeThong thamSoHeThong WHERE ";
////	private static final String _SQL_COUNT_THAMSOHETHONG = "SELECT COUNT(thamSoHeThong) FROM ThamSoHeThong thamSoHeThong";
////	private static final String _SQL_COUNT_THAMSOHETHONG_WHERE = "SELECT COUNT(thamSoHeThong) FROM ThamSoHeThong thamSoHeThong WHERE ";
////	private static final String _FINDER_COLUMN_KEYDATA_KEYDATA_1 = "thamSoHeThong.keyData IS NULL";
////	private static final String _FINDER_COLUMN_KEYDATA_KEYDATA_2 = "thamSoHeThong.keyData =:keyData";
////	private static final String _FINDER_COLUMN_KEYDATA_KEYDATA_3 = "(thamSoHeThong.keyData IS NULL OR thamSoHeThong.keyData =:keyData)";
////	private static final String _FINDER_COLUMN_KEYDATAANDDESCRIPTION_KEYDATA_1 = "thamSoHeThong.keyData IS NULL AND ";
////	private static final String _FINDER_COLUMN_KEYDATAANDDESCRIPTION_KEYDATA_2 = "thamSoHeThong.keyData =:keyData AND ";
////	private static final String _FINDER_COLUMN_KEYDATAANDDESCRIPTION_KEYDATA_3 = "(thamSoHeThong.keyData IS NULL OR thamSoHeThong.keyData =:keyData) AND ";
////	private static final String _FINDER_COLUMN_KEYDATAANDDESCRIPTION_DESCRIPTION_1 =
////		"thamSoHeThong.description IS NULL";
////	private static final String _FINDER_COLUMN_KEYDATAANDDESCRIPTION_DESCRIPTION_2 =
////		"thamSoHeThong.description =:description";
////	private static final String _FINDER_COLUMN_KEYDATAANDDESCRIPTION_DESCRIPTION_3 =
////		"(thamSoHeThong.description IS NULL OR thamSoHeThong.description =:description)";
////	private static final String _ORDER_BY_ENTITY_ALIAS = "thamSoHeThong.";
////	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ThamSoHeThong exists with the primary key ";
////	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ThamSoHeThong exists with the key {";
////
////
////
////}
