package com.fds.nsw.liferay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.model.Organization;
import com.fds.nsw.liferay.service.exception.NoSuchOrganizationException;
import com.fds.nsw.liferay.service.persistence.OrganizationPersistenceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrganizationLocalServiceImpl {

	@Autowired
	OrganizationPersistenceImpl persistence;

	public Organization fetchOrganization(long organizationId) throws SystemException {
		return persistence.fetchByPrimaryKey(organizationId);
	}

	public Organization getOrganization(long organizationId) throws NoSuchOrganizationException, SystemException {
		return persistence.findByPrimaryKey(organizationId);
	}

	public List<Organization> getUserOrganizations(long userId) throws PortalException, SystemException {

		return getUserOrganizations(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<Organization> getUserOrganizations(long userId, int start, int end)
			throws PortalException, SystemException {

		return persistence.getOrganizations(userId, start, end);
	}

}
