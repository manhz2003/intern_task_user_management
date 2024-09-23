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
import com.fds.nsw.nghiepvu.model.DmGtOrganization;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmGtOrganizationRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmGtOrganizationModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmGtOrganizationPersistenceImpl extends BasePersistence {
	@Autowired
	DmGtOrganizationRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmGtOrganization> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmGtOrganizationUtil} to access the dm gt organization persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmGtOrganization create(long id) {
		DmGtOrganization dmGtOrganization = new DmGtOrganization();

		
		//dmGtOrganization.setPrimaryKey(id);

		return dmGtOrganization;
	}

	/**
	 * Removes the dm gt organization with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm gt organization
	 * @return the dm gt organization that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtOrganizationException if a dm gt organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtOrganization remove(long id)
		throws NoSuchDmGtOrganizationException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm gt organization with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm gt organization
	 * @return the dm gt organization that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtOrganizationException if a dm gt organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtOrganization remove(Serializable primaryKey)
		throws NoSuchDmGtOrganizationException, SystemException {
		

		try {
			

			DmGtOrganization dmGtOrganization = findByPrimaryKey(primaryKey);

			if (dmGtOrganization == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmGtOrganizationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmGtOrganization);
			return dmGtOrganization;
		}
		catch (NoSuchDmGtOrganizationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmGtOrganization remove(DmGtOrganization DmGtOrganization) throws SystemException {
	removeImpl(DmGtOrganization);
	return DmGtOrganization;
}

protected DmGtOrganization removeImpl(DmGtOrganization dmGtOrganization)
		throws SystemException {
		try {
			repository.delete(dmGtOrganization);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGtOrganization;
	}

	
	public DmGtOrganization updateImpl(
		DmGtOrganization dmGtOrganization,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmGtOrganization);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGtOrganization;
	}

	
	public DmGtOrganization findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm gt organization with the primary key or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtOrganizationException} if it could not be found.
	 *
	 * @param id the primary key of the dm gt organization
	 * @return the dm gt organization
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtOrganizationException if a dm gt organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtOrganization findByPrimaryKey(long id)
		throws NoSuchDmGtOrganizationException, SystemException {
		DmGtOrganization dmGtOrganization = fetchByPrimaryKey(id);

		if (dmGtOrganization == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmGtOrganizationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmGtOrganization;
	}

	/**
	 * Returns the dm gt organization with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm gt organization
	 * @return the dm gt organization, or <code>null</code> if a dm gt organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtOrganization fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm gt organization with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm gt organization
	 * @return the dm gt organization, or <code>null</code> if a dm gt organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtOrganization fetchByPrimaryKey(long id)
		throws SystemException {
		DmGtOrganization dmGtOrganization = null;

		

		if (dmGtOrganization == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmGtOrganization> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmGtOrganization = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmGtOrganization;
	}

	/**
	 * Returns the dm gt organization where organizationCode = &#63; or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtOrganizationException} if it could not be found.
	 *
	 * @param organizationCode the organization code
	 * @return the matching dm gt organization
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtOrganizationException if a matching dm gt organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtOrganization findByOrganizationCode(String organizationCode)
		throws NoSuchDmGtOrganizationException, SystemException {
		DmGtOrganization dmGtOrganization = fetchByOrganizationCode(organizationCode);

		if (dmGtOrganization == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("organizationCode=");
			msg.append(organizationCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmGtOrganizationException(msg.toString());
		}

		return dmGtOrganization;
	}

	/**
	 * Returns the dm gt organization where organizationCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param organizationCode the organization code
	 * @return the matching dm gt organization, or <code>null</code> if a matching dm gt organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtOrganization fetchByOrganizationCode(String organizationCode)
		throws SystemException {
		return fetchByOrganizationCode(organizationCode, true);
	}

	/**
	 * Returns the dm gt organization where organizationCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param organizationCode the organization code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm gt organization, or <code>null</code> if a matching dm gt organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtOrganization fetchByOrganizationCode(String organizationCode,
		boolean retrieveFromCache) throws SystemException {
		DmGtOrganization dmGtOrganization = null;
		if (dmGtOrganization == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMGTORGANIZATION_WHERE);

			if (organizationCode == null) {
				query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_1);
			}
			else {
				if (organizationCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_2);
				}
			}

			query.append(DmGtOrganizationModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmGtOrganization.class).build();

				

				if (organizationCode != null) {
					builder.appendNamedParameterMap("organizationCode", organizationCode);
				}

				dmGtOrganization = (DmGtOrganization) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmGtOrganization;
	}

	/**
	 * Returns all the dm gt organizations.
	 *
	 * @return the dm gt organizations
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtOrganization> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm gt organizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm gt organizations
	 * @param end the upper bound of the range of dm gt organizations (not inclusive)
	 * @return the range of dm gt organizations
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtOrganization> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm gt organizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm gt organizations
	 * @param end the upper bound of the range of dm gt organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm gt organizations
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtOrganization> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtOrganization> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMGTORGANIZATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMGTORGANIZATION.concat(DmGtOrganizationModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmGtOrganization>) queryFactory.getResultList(builder);
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
	 * Removes the dm gt organization where organizationCode = &#63; from the database.
	 *
	 * @param organizationCode the organization code
	 * @return the dm gt organization that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtOrganization removeByOrganizationCode(String organizationCode)
		throws NoSuchDmGtOrganizationException, SystemException {
		DmGtOrganization dmGtOrganization = findByOrganizationCode(organizationCode);

		repository.delete(dmGtOrganization);
			return dmGtOrganization;
	}

	/**
	 * Removes all the dm gt organizations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmGtOrganization dmGtOrganization : findAll()) {
			repository.delete(dmGtOrganization);
		}
	}

	/**
	 * Returns the number of dm gt organizations where organizationCode = &#63;.
	 *
	 * @param organizationCode the organization code
	 * @return the number of matching dm gt organizations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOrganizationCode(String organizationCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMGTORGANIZATION_WHERE);

			if (organizationCode == null) {
				query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_1);
			}
			else {
				if (organizationCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (organizationCode != null) {
					builder.appendNamedParameterMap("organizationCode", organizationCode);
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
	 * Returns the number of dm gt organizations.
	 *
	 * @return the number of dm gt organizations
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMGTORGANIZATION).build();

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
	 * Initializes the dm gt organization persistence.
	 */
	private static final String _SQL_SELECT_DMGTORGANIZATION = "SELECT dmGtOrganization FROM DmGtOrganization dmGtOrganization";
	private static final String _SQL_SELECT_DMGTORGANIZATION_WHERE = "SELECT dmGtOrganization FROM DmGtOrganization dmGtOrganization WHERE ";
	private static final String _SQL_COUNT_DMGTORGANIZATION = "SELECT COUNT(dmGtOrganization) FROM DmGtOrganization dmGtOrganization";
	private static final String _SQL_COUNT_DMGTORGANIZATION_WHERE = "SELECT COUNT(dmGtOrganization) FROM DmGtOrganization dmGtOrganization WHERE ";
	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_1 =
		"dmGtOrganization.organizationCode IS NULL";
	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_2 =
		"dmGtOrganization.organizationCode =:organizationCode";
	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_3 =
		"(dmGtOrganization.organizationCode IS NULL OR dmGtOrganization.organizationCode =:organizationCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmGtOrganization.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmGtOrganization exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmGtOrganization exists with the key {";
	

	
}
