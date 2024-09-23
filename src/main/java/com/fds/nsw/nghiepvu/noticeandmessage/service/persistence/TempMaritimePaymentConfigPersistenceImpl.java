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
import com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.TempMaritimePaymentConfigRepository;
import com.fds.nsw.nghiepvu.modelImpl.TempMaritimePaymentConfigModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TempMaritimePaymentConfigPersistenceImpl extends BasePersistence {
	@Autowired
	TempMaritimePaymentConfigRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempMaritimePaymentConfig> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TempMaritimePaymentConfigUtil} to access the temp maritime payment config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public TempMaritimePaymentConfig create(long id) {
		TempMaritimePaymentConfig tempMaritimePaymentConfig = new TempMaritimePaymentConfig();

		
		//tempMaritimePaymentConfig.setPrimaryKey(id);

		return tempMaritimePaymentConfig;
	}

	/**
	 * Removes the temp maritime payment config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp maritime payment config
	 * @return the temp maritime payment config that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempMaritimePaymentConfigException if a temp maritime payment config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempMaritimePaymentConfig remove(long id)
		throws NoSuchTempMaritimePaymentConfigException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the temp maritime payment config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the temp maritime payment config
	 * @return the temp maritime payment config that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempMaritimePaymentConfigException if a temp maritime payment config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempMaritimePaymentConfig remove(Serializable primaryKey)
		throws NoSuchTempMaritimePaymentConfigException, SystemException {
		

		try {
			

			TempMaritimePaymentConfig tempMaritimePaymentConfig = findByPrimaryKey(primaryKey);

			if (tempMaritimePaymentConfig == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTempMaritimePaymentConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(tempMaritimePaymentConfig);
			return tempMaritimePaymentConfig;
		}
		catch (NoSuchTempMaritimePaymentConfigException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public TempMaritimePaymentConfig remove(TempMaritimePaymentConfig TempMaritimePaymentConfig) throws SystemException {
	removeImpl(TempMaritimePaymentConfig);
	return TempMaritimePaymentConfig;
}

protected TempMaritimePaymentConfig removeImpl(
		TempMaritimePaymentConfig tempMaritimePaymentConfig)
		throws SystemException {
		try {
			repository.delete(tempMaritimePaymentConfig);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempMaritimePaymentConfig;
	}

	
	public TempMaritimePaymentConfig updateImpl(
		com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig tempMaritimePaymentConfig,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(tempMaritimePaymentConfig);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempMaritimePaymentConfig;
	}

	
	public TempMaritimePaymentConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp maritime payment config with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempMaritimePaymentConfigException} if it could not be found.
	 *
	 * @param id the primary key of the temp maritime payment config
	 * @return the temp maritime payment config
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempMaritimePaymentConfigException if a temp maritime payment config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempMaritimePaymentConfig findByPrimaryKey(long id)
		throws NoSuchTempMaritimePaymentConfigException, SystemException {
		TempMaritimePaymentConfig tempMaritimePaymentConfig = fetchByPrimaryKey(id);

		if (tempMaritimePaymentConfig == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchTempMaritimePaymentConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return tempMaritimePaymentConfig;
	}

	/**
	 * Returns the temp maritime payment config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the temp maritime payment config
	 * @return the temp maritime payment config, or <code>null</code> if a temp maritime payment config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempMaritimePaymentConfig fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp maritime payment config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the temp maritime payment config
	 * @return the temp maritime payment config, or <code>null</code> if a temp maritime payment config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempMaritimePaymentConfig fetchByPrimaryKey(long id)
		throws SystemException {
		TempMaritimePaymentConfig tempMaritimePaymentConfig = null;

		

		if (tempMaritimePaymentConfig == null) {
			

			boolean hasException = false;

			try {
				

				Optional<TempMaritimePaymentConfig> optional = repository.findById(id);
				if (optional.isPresent()) {
					tempMaritimePaymentConfig = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return tempMaritimePaymentConfig;
	}

	/**
	 * Returns the temp maritime payment config where maritimeCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempMaritimePaymentConfigException} if it could not be found.
	 *
	 * @param maritimeCode the maritime code
	 * @return the matching temp maritime payment config
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempMaritimePaymentConfigException if a matching temp maritime payment config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempMaritimePaymentConfig findByMaritimeCode(String maritimeCode)
		throws NoSuchTempMaritimePaymentConfigException, SystemException {
		TempMaritimePaymentConfig tempMaritimePaymentConfig = fetchByMaritimeCode(maritimeCode);

		if (tempMaritimePaymentConfig == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("maritimeCode=");
			msg.append(maritimeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchTempMaritimePaymentConfigException(msg.toString());
		}

		return tempMaritimePaymentConfig;
	}

	/**
	 * Returns the temp maritime payment config where maritimeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param maritimeCode the maritime code
	 * @return the matching temp maritime payment config, or <code>null</code> if a matching temp maritime payment config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempMaritimePaymentConfig fetchByMaritimeCode(String maritimeCode)
		throws SystemException {
		return fetchByMaritimeCode(maritimeCode, true);
	}

	/**
	 * Returns the temp maritime payment config where maritimeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param maritimeCode the maritime code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching temp maritime payment config, or <code>null</code> if a matching temp maritime payment config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempMaritimePaymentConfig fetchByMaritimeCode(String maritimeCode,
		boolean retrieveFromCache) throws SystemException {
		TempMaritimePaymentConfig tempMaritimePaymentConfig = null;
		if (tempMaritimePaymentConfig == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_TEMPMARITIMEPAYMENTCONFIG_WHERE);

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

			query.append(TempMaritimePaymentConfigModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(TempMaritimePaymentConfig.class).build();
				

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
				}

				tempMaritimePaymentConfig = (TempMaritimePaymentConfig) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return tempMaritimePaymentConfig;
	}

	/**
	 * Returns all the temp maritime payment configs.
	 *
	 * @return the temp maritime payment configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempMaritimePaymentConfig> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp maritime payment configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp maritime payment configs
	 * @param end the upper bound of the range of temp maritime payment configs (not inclusive)
	 * @return the range of temp maritime payment configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempMaritimePaymentConfig> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp maritime payment configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp maritime payment configs
	 * @param end the upper bound of the range of temp maritime payment configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of temp maritime payment configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempMaritimePaymentConfig> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempMaritimePaymentConfig> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TEMPMARITIMEPAYMENTCONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TEMPMARITIMEPAYMENTCONFIG.concat(TempMaritimePaymentConfigModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<TempMaritimePaymentConfig>) queryFactory.getResultList(builder);
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
	 * Removes the temp maritime payment config where maritimeCode = &#63; from the database.
	 *
	 * @param maritimeCode the maritime code
	 * @return the temp maritime payment config that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TempMaritimePaymentConfig removeByMaritimeCode(String maritimeCode)
		throws NoSuchTempMaritimePaymentConfigException, SystemException {
		TempMaritimePaymentConfig tempMaritimePaymentConfig = findByMaritimeCode(maritimeCode);

		repository.delete(tempMaritimePaymentConfig);
			return tempMaritimePaymentConfig;
	}

	/**
	 * Removes all the temp maritime payment configs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TempMaritimePaymentConfig tempMaritimePaymentConfig : findAll()) {
			repository.delete(tempMaritimePaymentConfig);
		}
	}

	/**
	 * Returns the number of temp maritime payment configs where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @return the number of matching temp maritime payment configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMaritimeCode(String maritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPMARITIMEPAYMENTCONFIG_WHERE);

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
	 * Returns the number of temp maritime payment configs.
	 *
	 * @return the number of temp maritime payment configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPMARITIMEPAYMENTCONFIG).build();

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
	 * Initializes the temp maritime payment config persistence.
	 */
	private static final String _SQL_SELECT_TEMPMARITIMEPAYMENTCONFIG = "SELECT tempMaritimePaymentConfig FROM TempMaritimePaymentConfig tempMaritimePaymentConfig";
	private static final String _SQL_SELECT_TEMPMARITIMEPAYMENTCONFIG_WHERE = "SELECT tempMaritimePaymentConfig FROM TempMaritimePaymentConfig tempMaritimePaymentConfig WHERE ";
	private static final String _SQL_COUNT_TEMPMARITIMEPAYMENTCONFIG = "SELECT COUNT(tempMaritimePaymentConfig) FROM TempMaritimePaymentConfig tempMaritimePaymentConfig";
	private static final String _SQL_COUNT_TEMPMARITIMEPAYMENTCONFIG_WHERE = "SELECT COUNT(tempMaritimePaymentConfig) FROM TempMaritimePaymentConfig tempMaritimePaymentConfig WHERE ";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1 = "tempMaritimePaymentConfig.maritimeCode IS NULL";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2 = "tempMaritimePaymentConfig.maritimeCode =:maritimeCode";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3 = "(tempMaritimePaymentConfig.maritimeCode IS NULL OR tempMaritimePaymentConfig.maritimeCode =:maritimeCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "tempMaritimePaymentConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempMaritimePaymentConfig exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempMaritimePaymentConfig exists with the key {";
	

	
}
