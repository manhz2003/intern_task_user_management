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

package vn.gt.dao.noticeandmessage.service;






/**
 * The utility for the view hoan thanh thu tuc local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.ViewHoanThanhThuTucLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see ViewHoanThanhThuTucLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.ViewHoanThanhThuTucLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.ViewHoanThanhThuTucLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.ViewHoanThanhThuTucLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class ViewHoanThanhThuTucLocalServiceUtil {
public ViewHoanThanhThuTucLocalServiceUtil(ViewHoanThanhThuTucLocalServiceImpl service) {
ViewHoanThanhThuTucLocalServiceUtil._service = service;
}
public static ViewHoanThanhThuTucLocalServiceImpl getService() {
return _service;
}
private static ViewHoanThanhThuTucLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.ViewHoanThanhThuTucLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the view hoan thanh thu tuc to the database. Also notifies the appropriate model listeners.
	*
	* @param viewHoanThanhThuTuc the view hoan thanh thu tuc
	* @return the view hoan thanh thu tuc that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc addViewHoanThanhThuTuc(
		com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc viewHoanThanhThuTuc)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addViewHoanThanhThuTuc(viewHoanThanhThuTuc);
	}

	/**
	* Creates a new view hoan thanh thu tuc with the primary key. Does not add the view hoan thanh thu tuc to the database.
	*
	* @param CVHH the primary key for the new view hoan thanh thu tuc
	* @return the new view hoan thanh thu tuc
	*/
//	public static com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc createViewHoanThanhThuTuc(
//		java.lang.String CVHH) {
//		return getService().createViewHoanThanhThuTuc(CVHH);
//	}

	/**
	* Deletes the view hoan thanh thu tuc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CVHH the primary key of the view hoan thanh thu tuc
	* @throws PortalException if a view hoan thanh thu tuc with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteViewHoanThanhThuTuc(java.lang.String CVHH)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteViewHoanThanhThuTuc(CVHH);
	}

	/**
	* Deletes the view hoan thanh thu tuc from the database. Also notifies the appropriate model listeners.
	*
	* @param viewHoanThanhThuTuc the view hoan thanh thu tuc
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteViewHoanThanhThuTuc(
		com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc viewHoanThanhThuTuc)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteViewHoanThanhThuTuc(viewHoanThanhThuTuc);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc fetchViewHoanThanhThuTuc(
		java.lang.String CVHH)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchViewHoanThanhThuTuc(CVHH);
	}

	/**
	* Returns the view hoan thanh thu tuc with the primary key.
	*
	* @param CVHH the primary key of the view hoan thanh thu tuc
	* @return the view hoan thanh thu tuc
	* @throws PortalException if a view hoan thanh thu tuc with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc getViewHoanThanhThuTuc(
		java.lang.String CVHH)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getViewHoanThanhThuTuc(CVHH);
	}

	

	/**
	* Returns a range of all the view hoan thanh thu tucs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of view hoan thanh thu tucs
	* @param end the upper bound of the range of view hoan thanh thu tucs (not inclusive)
	* @return the range of view hoan thanh thu tucs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc> getViewHoanThanhThuTucs(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getViewHoanThanhThuTucs(start, end);
	}

	/**
	* Returns the number of view hoan thanh thu tucs.
	*
	* @return the number of view hoan thanh thu tucs
	* @throws SystemException if a system exception occurred
	*/
	public static int getViewHoanThanhThuTucsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getViewHoanThanhThuTucsCount();
	}

	/**
	* Updates the view hoan thanh thu tuc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param viewHoanThanhThuTuc the view hoan thanh thu tuc
	* @return the view hoan thanh thu tuc that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc updateViewHoanThanhThuTuc(
		com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc viewHoanThanhThuTuc)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateViewHoanThanhThuTuc(viewHoanThanhThuTuc);
	}

	/**
	* Updates the view hoan thanh thu tuc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param viewHoanThanhThuTuc the view hoan thanh thu tuc
	* @param merge whether to merge the view hoan thanh thu tuc with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the view hoan thanh thu tuc that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc updateViewHoanThanhThuTuc(
		com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc viewHoanThanhThuTuc,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateViewHoanThanhThuTuc(viewHoanThanhThuTuc, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc> findByKetQuaHoanThanhThuTuc(
		java.lang.String maritimeCode, java.lang.String reportDateFrom,
		java.lang.String reportDateTo) {
		return getService()
				   .findByKetQuaHoanThanhThuTuc(maritimeCode, reportDateFrom,
			reportDateTo);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}