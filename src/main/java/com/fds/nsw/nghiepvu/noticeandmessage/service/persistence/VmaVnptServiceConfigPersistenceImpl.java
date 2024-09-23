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
import com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaVnptserviceconfigRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaVnptServiceConfigModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaVnptServiceConfigPersistenceImpl extends BasePersistence {
	@Autowired
	VmaVnptserviceconfigRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaVnptServiceConfig> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaVnptServiceConfigUtil} to access the vma vnpt service config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaVnptServiceConfig create(long id) {
		VmaVnptServiceConfig vmaVnptServiceConfig = new VmaVnptServiceConfig();

		
		//vmaVnptServiceConfig.setPrimaryKey(id);

		return vmaVnptServiceConfig;
	}

	/**
	 * Removes the vma vnpt service config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma vnpt service config
	 * @return the vma vnpt service config that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaVnptServiceConfigException if a vma vnpt service config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig remove(long id)
		throws NoSuchVmaVnptServiceConfigException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma vnpt service config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma vnpt service config
	 * @return the vma vnpt service config that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaVnptServiceConfigException if a vma vnpt service config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaVnptServiceConfig remove(Serializable primaryKey)
		throws NoSuchVmaVnptServiceConfigException, SystemException {
		

		try {
			

			VmaVnptServiceConfig vmaVnptServiceConfig = findByPrimaryKey(primaryKey);

			if (vmaVnptServiceConfig == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaVnptServiceConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaVnptServiceConfig);
			return vmaVnptServiceConfig;
		}
		catch (NoSuchVmaVnptServiceConfigException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaVnptServiceConfig remove(VmaVnptServiceConfig VmaVnptServiceConfig) throws SystemException {
	removeImpl(VmaVnptServiceConfig);
	return VmaVnptServiceConfig;
}

protected VmaVnptServiceConfig removeImpl(
		VmaVnptServiceConfig vmaVnptServiceConfig) throws SystemException {
		try {
			repository.delete(vmaVnptServiceConfig);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaVnptServiceConfig;
	}

	
	public VmaVnptServiceConfig updateImpl(
		com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig vmaVnptServiceConfig,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaVnptServiceConfig);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaVnptServiceConfig;
	}

	
	public VmaVnptServiceConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma vnpt service config with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaVnptServiceConfigException} if it could not be found.
	 *
	 * @param id the primary key of the vma vnpt service config
	 * @return the vma vnpt service config
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaVnptServiceConfigException if a vma vnpt service config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig findByPrimaryKey(long id)
		throws NoSuchVmaVnptServiceConfigException, SystemException {
		VmaVnptServiceConfig vmaVnptServiceConfig = fetchByPrimaryKey(id);

		if (vmaVnptServiceConfig == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaVnptServiceConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaVnptServiceConfig;
	}

	/**
	 * Returns the vma vnpt service config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma vnpt service config
	 * @return the vma vnpt service config, or <code>null</code> if a vma vnpt service config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaVnptServiceConfig fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma vnpt service config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma vnpt service config
	 * @return the vma vnpt service config, or <code>null</code> if a vma vnpt service config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig fetchByPrimaryKey(long id)
		throws SystemException {
		VmaVnptServiceConfig vmaVnptServiceConfig = null;

		

		if (vmaVnptServiceConfig == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaVnptServiceConfig> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaVnptServiceConfig = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaVnptServiceConfig;
	}

	/**
	 * Returns the vma vnpt service config where maritimeCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaVnptServiceConfigException} if it could not be found.
	 *
	 * @param maritimeCode the maritime code
	 * @return the matching vma vnpt service config
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaVnptServiceConfigException if a matching vma vnpt service config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig findByMaritimeCode(String maritimeCode)
		throws NoSuchVmaVnptServiceConfigException, SystemException {
		VmaVnptServiceConfig vmaVnptServiceConfig = fetchByMaritimeCode(maritimeCode);

		if (vmaVnptServiceConfig == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("maritimeCode=");
			msg.append(maritimeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaVnptServiceConfigException(msg.toString());
		}

		return vmaVnptServiceConfig;
	}

	/**
	 * Returns the vma vnpt service config where maritimeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param maritimeCode the maritime code
	 * @return the matching vma vnpt service config, or <code>null</code> if a matching vma vnpt service config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig fetchByMaritimeCode(String maritimeCode)
		throws SystemException {
		return fetchByMaritimeCode(maritimeCode, true);
	}

	/**
	 * Returns the vma vnpt service config where maritimeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param maritimeCode the maritime code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma vnpt service config, or <code>null</code> if a matching vma vnpt service config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig fetchByMaritimeCode(String maritimeCode,
		boolean retrieveFromCache) throws SystemException {
		VmaVnptServiceConfig vmaVnptServiceConfig = null;
		if (vmaVnptServiceConfig == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMAVNPTSERVICECONFIG_WHERE);

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

			query.append(VmaVnptServiceConfigModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaVnptServiceConfig.class).build();

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
				}

				vmaVnptServiceConfig = (VmaVnptServiceConfig) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaVnptServiceConfig;
	}

	/**
	 * Returns the vma vnpt service config where maritimeCode = &#63; and testMode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaVnptServiceConfigException} if it could not be found.
	 *
	 * @param maritimeCode the maritime code
	 * @param testMode the test mode
	 * @return the matching vma vnpt service config
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaVnptServiceConfigException if a matching vma vnpt service config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig findByTestMode_MaritimeCode(
		String maritimeCode, int testMode)
		throws NoSuchVmaVnptServiceConfigException, SystemException {
		VmaVnptServiceConfig vmaVnptServiceConfig = fetchByTestMode_MaritimeCode(maritimeCode,
				testMode);

		if (vmaVnptServiceConfig == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("maritimeCode=");
			msg.append(maritimeCode);

			msg.append(", testMode=");
			msg.append(testMode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaVnptServiceConfigException(msg.toString());
		}

		return vmaVnptServiceConfig;
	}

	/**
	 * Returns the vma vnpt service config where maritimeCode = &#63; and testMode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param maritimeCode the maritime code
	 * @param testMode the test mode
	 * @return the matching vma vnpt service config, or <code>null</code> if a matching vma vnpt service config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig fetchByTestMode_MaritimeCode(
		String maritimeCode, int testMode) throws SystemException {
		return fetchByTestMode_MaritimeCode(maritimeCode, testMode, true);
	}

	/**
	 * Returns the vma vnpt service config where maritimeCode = &#63; and testMode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param maritimeCode the maritime code
	 * @param testMode the test mode
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma vnpt service config, or <code>null</code> if a matching vma vnpt service config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig fetchByTestMode_MaritimeCode(
		String maritimeCode, int testMode, boolean retrieveFromCache)
		throws SystemException {
		VmaVnptServiceConfig vmaVnptServiceConfig = null;
		if (vmaVnptServiceConfig == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMAVNPTSERVICECONFIG_WHERE);

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_TESTMODE_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TESTMODE_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TESTMODE_MARITIMECODE_MARITIMECODE_2);
				}
			}

			query.append(_FINDER_COLUMN_TESTMODE_MARITIMECODE_TESTMODE_2);

			query.append(VmaVnptServiceConfigModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaVnptServiceConfig.class).build();
				

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
				}

				builder.appendNamedParameterMap("testMode", testMode);

				vmaVnptServiceConfig = (VmaVnptServiceConfig) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaVnptServiceConfig;
	}

	/**
	 * Returns the vma vnpt service config where testMode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaVnptServiceConfigException} if it could not be found.
	 *
	 * @param testMode the test mode
	 * @return the matching vma vnpt service config
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaVnptServiceConfigException if a matching vma vnpt service config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig findByTestMode(int testMode)
		throws NoSuchVmaVnptServiceConfigException, SystemException {
		VmaVnptServiceConfig vmaVnptServiceConfig = fetchByTestMode(testMode);

		if (vmaVnptServiceConfig == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("testMode=");
			msg.append(testMode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaVnptServiceConfigException(msg.toString());
		}

		return vmaVnptServiceConfig;
	}

	/**
	 * Returns the vma vnpt service config where testMode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testMode the test mode
	 * @return the matching vma vnpt service config, or <code>null</code> if a matching vma vnpt service config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig fetchByTestMode(int testMode)
		throws SystemException {
		return fetchByTestMode(testMode, true);
	}

	/**
	 * Returns the vma vnpt service config where testMode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testMode the test mode
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma vnpt service config, or <code>null</code> if a matching vma vnpt service config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig fetchByTestMode(int testMode,
		boolean retrieveFromCache) throws SystemException {
		VmaVnptServiceConfig vmaVnptServiceConfig = null;
		if (vmaVnptServiceConfig == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMAVNPTSERVICECONFIG_WHERE);

			query.append(_FINDER_COLUMN_TESTMODE_TESTMODE_2);

			query.append(VmaVnptServiceConfigModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaVnptServiceConfig.class).build();
				

				builder.appendNamedParameterMap("testMode", testMode);

				vmaVnptServiceConfig = (VmaVnptServiceConfig) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaVnptServiceConfig;
	}

	/**
	 * Returns all the vma vnpt service configs.
	 *
	 * @return the vma vnpt service configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaVnptServiceConfig> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma vnpt service configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma vnpt service configs
	 * @param end the upper bound of the range of vma vnpt service configs (not inclusive)
	 * @return the range of vma vnpt service configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaVnptServiceConfig> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma vnpt service configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma vnpt service configs
	 * @param end the upper bound of the range of vma vnpt service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma vnpt service configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaVnptServiceConfig> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaVnptServiceConfig> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMAVNPTSERVICECONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMAVNPTSERVICECONFIG.concat(VmaVnptServiceConfigModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaVnptServiceConfig>) queryFactory.getResultList(builder);
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
	 * Removes the vma vnpt service config where maritimeCode = &#63; from the database.
	 *
	 * @param maritimeCode the maritime code
	 * @return the vma vnpt service config that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig removeByMaritimeCode(String maritimeCode)
		throws NoSuchVmaVnptServiceConfigException, SystemException {
		VmaVnptServiceConfig vmaVnptServiceConfig = findByMaritimeCode(maritimeCode);

		repository.delete(vmaVnptServiceConfig);
			return vmaVnptServiceConfig;
	}

	/**
	 * Removes the vma vnpt service config where maritimeCode = &#63; and testMode = &#63; from the database.
	 *
	 * @param maritimeCode the maritime code
	 * @param testMode the test mode
	 * @return the vma vnpt service config that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig removeByTestMode_MaritimeCode(
		String maritimeCode, int testMode)
		throws NoSuchVmaVnptServiceConfigException, SystemException {
		VmaVnptServiceConfig vmaVnptServiceConfig = findByTestMode_MaritimeCode(maritimeCode,
				testMode);

		repository.delete(vmaVnptServiceConfig);
			return vmaVnptServiceConfig;
	}

	/**
	 * Removes the vma vnpt service config where testMode = &#63; from the database.
	 *
	 * @param testMode the test mode
	 * @return the vma vnpt service config that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaVnptServiceConfig removeByTestMode(int testMode)
		throws NoSuchVmaVnptServiceConfigException, SystemException {
		VmaVnptServiceConfig vmaVnptServiceConfig = findByTestMode(testMode);

		repository.delete(vmaVnptServiceConfig);
			return vmaVnptServiceConfig;
	}

	/**
	 * Removes all the vma vnpt service configs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaVnptServiceConfig vmaVnptServiceConfig : findAll()) {
			repository.delete(vmaVnptServiceConfig);
		}
	}

	/**
	 * Returns the number of vma vnpt service configs where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @return the number of matching vma vnpt service configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMaritimeCode(String maritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAVNPTSERVICECONFIG_WHERE);

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
	 * Returns the number of vma vnpt service configs where maritimeCode = &#63; and testMode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param testMode the test mode
	 * @return the number of matching vma vnpt service configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTestMode_MaritimeCode(String maritimeCode, int testMode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMAVNPTSERVICECONFIG_WHERE);

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_TESTMODE_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TESTMODE_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TESTMODE_MARITIMECODE_MARITIMECODE_2);
				}
			}

			query.append(_FINDER_COLUMN_TESTMODE_MARITIMECODE_TESTMODE_2);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
				}

				builder.appendNamedParameterMap("testMode", testMode);

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
	 * Returns the number of vma vnpt service configs where testMode = &#63;.
	 *
	 * @param testMode the test mode
	 * @return the number of matching vma vnpt service configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTestMode(int testMode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAVNPTSERVICECONFIG_WHERE);

			query.append(_FINDER_COLUMN_TESTMODE_TESTMODE_2);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				builder.appendNamedParameterMap("testMode", testMode);

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
	 * Returns the number of vma vnpt service configs.
	 *
	 * @return the number of vma vnpt service configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMAVNPTSERVICECONFIG).build();

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
	 * Initializes the vma vnpt service config persistence.
	 */
	private static final String _SQL_SELECT_VMAVNPTSERVICECONFIG = "SELECT vmaVnptServiceConfig FROM VmaVnptServiceConfig vmaVnptServiceConfig";
	private static final String _SQL_SELECT_VMAVNPTSERVICECONFIG_WHERE = "SELECT vmaVnptServiceConfig FROM VmaVnptServiceConfig vmaVnptServiceConfig WHERE ";
	private static final String _SQL_COUNT_VMAVNPTSERVICECONFIG = "SELECT COUNT(vmaVnptServiceConfig) FROM VmaVnptServiceConfig vmaVnptServiceConfig";
	private static final String _SQL_COUNT_VMAVNPTSERVICECONFIG_WHERE = "SELECT COUNT(vmaVnptServiceConfig) FROM VmaVnptServiceConfig vmaVnptServiceConfig WHERE ";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1 = "vmaVnptServiceConfig.maritimeCode IS NULL";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2 = "vmaVnptServiceConfig.maritimeCode =:maritimeCode";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3 = "(vmaVnptServiceConfig.maritimeCode IS NULL OR vmaVnptServiceConfig.maritimeCode =:maritimeCode)";
	private static final String _FINDER_COLUMN_TESTMODE_MARITIMECODE_MARITIMECODE_1 =
		"vmaVnptServiceConfig.maritimeCode IS NULL AND ";
	private static final String _FINDER_COLUMN_TESTMODE_MARITIMECODE_MARITIMECODE_2 =
		"vmaVnptServiceConfig.maritimeCode =:maritimeCode AND ";
	private static final String _FINDER_COLUMN_TESTMODE_MARITIMECODE_MARITIMECODE_3 =
		"(vmaVnptServiceConfig.maritimeCode IS NULL OR vmaVnptServiceConfig.maritimeCode =:maritimeCode) AND ";
	private static final String _FINDER_COLUMN_TESTMODE_MARITIMECODE_TESTMODE_2 = "vmaVnptServiceConfig.testMode =:testMode";
	private static final String _FINDER_COLUMN_TESTMODE_TESTMODE_2 = "vmaVnptServiceConfig.testMode =:testMode";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaVnptServiceConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaVnptServiceConfig exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaVnptServiceConfig exists with the key {";
	

	
}
