/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.fds.nsw.liferay.service.persistence;

import com.fds.flex.common.utility.string.StringBundler;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.kernel.dao.orm.BasePersistence;
import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.kernel.orm.exception.NoSuchModelException;
import com.fds.nsw.kernel.util.OrderByComparator;
import com.fds.nsw.liferay.model.Organization;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.repository.OrganizationRepository;
import com.fds.nsw.liferay.repository.UserRepository;
import com.fds.nsw.liferay.service.exception.NoSuchOrganizationException;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * The persistence implementation for the organization service.
 *
 * <p>
 * Caching information and settings can be found in
 * <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrganizationPersistence
 * @see OrganizationUtil
 * @generated
 */

@Service
@Slf4j
public class OrganizationPersistenceImpl extends BasePersistence {
	@Autowired
	@Qualifier("lrQueryFactory")
	QueryFactory<Organization> queryFactory;

	@Autowired
	OrganizationRepository repository;

	public Organization findByPrimaryKey(long organizationId) throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByPrimaryKey(organizationId);

		if (organization == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + organizationId);
			}

			throw new NoSuchOrganizationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + organizationId);
		}

		return organization;
	}

	public Organization fetchByPrimaryKey(long organizationId) throws SystemException {
		Organization organization = null;

		if (organization == null) {

			boolean hasException = false;

			try {
				Optional<Organization> optional = repository.findById(organizationId);
				if (optional.isPresent()) {
					organization = optional.get();
				}

			} catch (Exception e) {
				hasException = true;

				throw processException(e);
			} finally {

			}
		}

		return organization;
	}

	public List<Organization> getOrganizations(long pk, int start, int end) throws SystemException {
		return getOrganizations(pk, start, end, null);
	}

	public List<Organization> getOrganizations(long pk, int start, int end, OrderByComparator orderByComparator)
			throws SystemException {

		List<Organization> list = null;

		if (list == null) {

			try {

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETORGANIZATIONS.concat(ORDER_BY_CLAUSE).concat(orderByComparator.getOrderBy());
				} else {
					sql = _SQL_GETORGANIZATIONS.concat(ORDER_BY_SQL);
				}

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(Organization.class).build();
				builder.appendNamedParameterMap("userId", pk);

				list = queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	private static final String _SQL_SELECT_USER = "SELECT user FROM User user";
	private static final String _SQL_SELECT_USER_WHERE = "SELECT user FROM User user WHERE ";
	private static final String _SQL_COUNT_USER = "SELECT COUNT(user) FROM User user";
	private static final String _SQL_COUNT_USER_WHERE = "SELECT COUNT(user) FROM User user WHERE ";
	private static final String _SQL_GETGROUPS = "SELECT Group_.* FROM Group_ INNER JOIN Users_Groups ON (Users_Groups.groupId = Group_.groupId) WHERE (Users_Groups.userId =:userId)";
	private static final String _SQL_GETGROUPSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Groups WHERE userId =:userId";
	private static final String _SQL_CONTAINSGROUP = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Groups WHERE userId =:userId AND groupId =:groupId";
	private static final String _SQL_GETORGANIZATIONS = "SELECT o FROM Organization o INNER JOIN UsersOrg uo ON (uo.organization = o) WHERE (uo.user.userId =:userId)";
	private static final String _SQL_GETORGANIZATIONSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Orgs WHERE userId =:userId";
	private static final String _SQL_CONTAINSORGANIZATION = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Orgs WHERE userId =:userId AND organizationId =:organizationId";
	private static final String _SQL_GETPERMISSIONS = "SELECT Permission_.* FROM Permission_ INNER JOIN Users_Permissions ON (Users_Permissions.permissionId = Permission_.permissionId) WHERE (Users_Permissions.userId =:userId)";
	private static final String _SQL_GETPERMISSIONSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Permissions WHERE userId =:userId";
	private static final String _SQL_CONTAINSPERMISSION = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Permissions WHERE userId =:userId AND permissionId =:permissionId";
	private static final String _SQL_GETROLES = "SELECT Role_.* FROM Role_ INNER JOIN Users_Roles ON (Users_Roles.roleId = Role_.roleId) WHERE (Users_Roles.userId =:userId)";
	private static final String _SQL_GETROLESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Roles WHERE userId =:userId";
	private static final String _SQL_CONTAINSROLE = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Roles WHERE userId =:userId AND roleId =:roleId";
	private static final String _SQL_GETTEAMS = "SELECT Team.* FROM Team INNER JOIN Users_Teams ON (Users_Teams.teamId = Team.teamId) WHERE (Users_Teams.userId =:userId)";
	private static final String _SQL_GETTEAMSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Teams WHERE userId =:userId";
	private static final String _SQL_CONTAINSTEAM = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Teams WHERE userId =:userId AND teamId =:teamId";
	private static final String _SQL_GETUSERGROUPS = "SELECT UserGroup.* FROM UserGroup INNER JOIN Users_UserGroups ON (Users_UserGroups.userGroupId = UserGroup.userGroupId) WHERE (Users_UserGroups.userId =:userId)";
	private static final String _SQL_GETUSERGROUPSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_UserGroups WHERE userId =:userId";
	private static final String _SQL_CONTAINSUSERGROUP = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_UserGroups WHERE userId =:userId AND userGroupId =:userGroupId";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "user.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "user.uuid =:uuid";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(user.uuid IS NULL OR user.uuid =:uuid)";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "user.companyId =:companyId";
	private static final String _FINDER_COLUMN_CONTACTID_CONTACTID_2 = "user.contactId =:contactId";
	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1 = "user.emailAddress IS NULL";
	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2 = "user.emailAddress =:emailAddress";
	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3 = "(user.emailAddress IS NULL OR user.emailAddress =:emailAddress)";
	private static final String _FINDER_COLUMN_PORTRAITID_PORTRAITID_2 = "user.portraitId =:portraitId";
	private static final String _FINDER_COLUMN_C_U_COMPANYID_2 = "user.companyId =:companyId AND ";
	private static final String _FINDER_COLUMN_C_U_USERID_2 = "user.userId =:userId";
	private static final String _FINDER_COLUMN_C_DU_COMPANYID_2 = "user.companyId =:companyId AND ";
	private static final String _FINDER_COLUMN_C_DU_DEFAULTUSER_2 = "user.defaultUser =:defaultUser";
	private static final String _FINDER_COLUMN_C_SN_COMPANYID_2 = "user.companyId =:companyId AND ";
	private static final String _FINDER_COLUMN_C_SN_SCREENNAME_1 = "user.screenName IS NULL";
	private static final String _FINDER_COLUMN_C_SN_SCREENNAME_2 = "user.screenName =:screenName";
	private static final String _FINDER_COLUMN_C_SN_SCREENNAME_3 = "(user.screenName IS NULL OR user.screenName =:screenName)";
	private static final String _FINDER_COLUMN_C_EA_COMPANYID_2 = "user.companyId =:companyId AND ";
	private static final String _FINDER_COLUMN_C_EA_EMAILADDRESS_1 = "user.emailAddress IS NULL";
	private static final String _FINDER_COLUMN_C_EA_EMAILADDRESS_2 = "user.emailAddress =:emailAddress";
	private static final String _FINDER_COLUMN_C_EA_EMAILADDRESS_3 = "(user.emailAddress IS NULL OR user.emailAddress =:emailAddress)";
	private static final String _FINDER_COLUMN_C_FID_COMPANYID_2 = "user.companyId =:companyId AND ";
	private static final String _FINDER_COLUMN_C_FID_FACEBOOKID_2 = "user.facebookId =:facebookId";
	private static final String _FINDER_COLUMN_C_O_COMPANYID_2 = "user.companyId =:companyId AND ";
	private static final String _FINDER_COLUMN_C_O_OPENID_1 = "user.openId IS NULL";
	private static final String _FINDER_COLUMN_C_O_OPENID_2 = "user.openId =:openId";
	private static final String _FINDER_COLUMN_C_O_OPENID_3 = "(user.openId IS NULL OR user.openId =:openId)";
	private static final String _FINDER_COLUMN_C_S_COMPANYID_2 = "user.companyId =:companyId AND ";
	private static final String _FINDER_COLUMN_C_S_STATUS_2 = "user.status =:status";
	private static final String _ORDER_BY_ENTITY_ALIAS = "user.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No User exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No User exists with the key {";

	public static final String TABLE_SQL_CREATE = "create table Organization_ (organizationId LONG not null primary key,companyId LONG,parentOrganizationId LONG,treePath STRING null,name VARCHAR(100) null,type_ VARCHAR(75) null,recursable BOOLEAN,regionId LONG,countryId LONG,statusId INTEGER,comments STRING null)";
	public static final String TABLE_SQL_DROP = "drop table Organization_";
	public static final String ORDER_BY_JPQL = " ORDER BY organization.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY o.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
}