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

package vn.gt.dao.danhmucgt.service;






/**
 * The utility for the dm gt report template local service. This utility wraps {@link vn.gt.dao.danhmucgt.service.impl.DmGtReportTemplateLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmGtReportTemplateLocalService
 * @see vn.gt.dao.danhmucgt.service.base.DmGtReportTemplateLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.impl.DmGtReportTemplateLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmucgt.service.impl.DmGtReportTemplateLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmGtReportTemplateLocalServiceUtil {
public DmGtReportTemplateLocalServiceUtil(DmGtReportTemplateLocalServiceImpl service) {
DmGtReportTemplateLocalServiceUtil._service = service;
}
public static DmGtReportTemplateLocalServiceImpl getService() {
return _service;
}
private static DmGtReportTemplateLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmucgt.service.impl.DmGtReportTemplateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm gt report template to the database. Also notifies the appropriate model listeners.
	*
	* @param dmGtReportTemplate the dm gt report template
	* @return the dm gt report template that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtReportTemplate addDmGtReportTemplate(
		com.fds.nsw.nghiepvu.model.DmGtReportTemplate dmGtReportTemplate)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmGtReportTemplate(dmGtReportTemplate);
	}

	/**
	* Creates a new dm gt report template with the primary key. Does not add the dm gt report template to the database.
	*
	* @param id the primary key for the new dm gt report template
	* @return the new dm gt report template
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtReportTemplate createDmGtReportTemplate(
		long id) {
		return getService().createDmGtReportTemplate(id);
	}

	/**
	* Deletes the dm gt report template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm gt report template
	* @throws PortalException if a dm gt report template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGtReportTemplate(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGtReportTemplate(id);
	}

	/**
	* Deletes the dm gt report template from the database. Also notifies the appropriate model listeners.
	*
	* @param dmGtReportTemplate the dm gt report template
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGtReportTemplate(
		com.fds.nsw.nghiepvu.model.DmGtReportTemplate dmGtReportTemplate)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGtReportTemplate(dmGtReportTemplate);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmGtReportTemplate fetchDmGtReportTemplate(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmGtReportTemplate(id);
	}

	/**
	* Returns the dm gt report template with the primary key.
	*
	* @param id the primary key of the dm gt report template
	* @return the dm gt report template
	* @throws PortalException if a dm gt report template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtReportTemplate getDmGtReportTemplate(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtReportTemplate(id);
	}

	

	/**
	* Returns a range of all the dm gt report templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm gt report templates
	* @param end the upper bound of the range of dm gt report templates (not inclusive)
	* @return the range of dm gt report templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtReportTemplate> getDmGtReportTemplates(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtReportTemplates(start, end);
	}

	/**
	* Returns the number of dm gt report templates.
	*
	* @return the number of dm gt report templates
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmGtReportTemplatesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtReportTemplatesCount();
	}

	/**
	* Updates the dm gt report template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGtReportTemplate the dm gt report template
	* @return the dm gt report template that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtReportTemplate updateDmGtReportTemplate(
		com.fds.nsw.nghiepvu.model.DmGtReportTemplate dmGtReportTemplate)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGtReportTemplate(dmGtReportTemplate);
	}

	/**
	* Updates the dm gt report template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGtReportTemplate the dm gt report template
	* @param merge whether to merge the dm gt report template with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm gt report template that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtReportTemplate updateDmGtReportTemplate(
		com.fds.nsw.nghiepvu.model.DmGtReportTemplate dmGtReportTemplate,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGtReportTemplate(dmGtReportTemplate, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtReportTemplate> findByreportType(
		int reportType) {
		return getService().findByreportType(reportType);
	}

	public static com.fds.nsw.nghiepvu.model.DmGtReportTemplate findByReportCode(
		int reportCode) {
		return getService().findByReportCode(reportCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}