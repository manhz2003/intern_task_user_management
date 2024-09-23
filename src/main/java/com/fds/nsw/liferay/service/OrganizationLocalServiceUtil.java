package com.fds.nsw.liferay.service;

import org.springframework.stereotype.Component;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.model.Organization;
import com.fds.nsw.liferay.service.impl.OrganizationLocalServiceImpl;

@Component
public class OrganizationLocalServiceUtil {
	public OrganizationLocalServiceUtil(OrganizationLocalServiceImpl service) {
		OrganizationLocalServiceUtil._service = service;
	}

	public static Organization fetchOrganization(long organizationId) throws SystemException {
		return getService().fetchOrganization(organizationId);
	}

	/**
	 * Returns the organization with the primary key.
	 *
	 * @param organizationId the primary key of the organization
	 * @return the organization
	 * @throws PortalException if a organization with the primary key could not be
	 *                         found
	 * @throws SystemException if a system exception occurred
	 */
	public static Organization getOrganization(long organizationId) throws PortalException, SystemException {
		return getService().getOrganization(organizationId);
	}

	public static java.util.List<Organization> getUserOrganizations(long userId)
			throws PortalException, SystemException {
		return getService().getUserOrganizations(userId);
	}

	public static OrganizationLocalServiceImpl getService() {

		return _service;
	}

	private static OrganizationLocalServiceImpl _service;
}
