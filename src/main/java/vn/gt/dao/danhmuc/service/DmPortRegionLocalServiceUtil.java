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

package vn.gt.dao.danhmuc.service;






/**
 * The utility for the dm port region local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmPortRegionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmPortRegionLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmPortRegionLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmPortRegionLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmPortRegionLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmPortRegionLocalServiceUtil {
public DmPortRegionLocalServiceUtil(DmPortRegionLocalServiceImpl service) {
DmPortRegionLocalServiceUtil._service = service;
}
public static DmPortRegionLocalServiceImpl getService() {
return _service;
}
private static DmPortRegionLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmPortRegionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm port region to the database. Also notifies the appropriate model listeners.
	*
	* @param dmPortRegion the dm port region
	* @return the dm port region that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortRegion addDmPortRegion(
		com.fds.nsw.nghiepvu.model.DmPortRegion dmPortRegion)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmPortRegion(dmPortRegion);
	}

	/**
	* Creates a new dm port region with the primary key. Does not add the dm port region to the database.
	*
	* @param id the primary key for the new dm port region
	* @return the new dm port region
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortRegion createDmPortRegion(
		int id) {
		return getService().createDmPortRegion(id);
	}

	/**
	* Deletes the dm port region with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm port region
	* @throws PortalException if a dm port region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmPortRegion(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmPortRegion(id);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortRegion> findPortRegions(
			java.lang.String maritimeCode, java.lang.String portRegionNameVN,
			java.lang.String portCode, java.lang.String isDelete,
			java.lang.String portRegionCodeGroup, int start, int end) {
		return getService()
				.findPortRegions(maritimeCode, portRegionNameVN, portCode,
						isDelete, portRegionCodeGroup, start, end);
	}

	public static long countPortRegions(java.lang.String maritimeCode,
										java.lang.String portRegionNameVN, java.lang.String portCode,
										java.lang.String isDelete, java.lang.String portRegionCodeGroup) {
		return getService()
				.countPortRegions(maritimeCode, portRegionNameVN, portCode,
						isDelete, portRegionCodeGroup);
	}

	public static long getMaxSequenceNo(java.lang.String maritimeCode,
										java.lang.String portCodeRef) {
		return getService().getMaxSequenceNo(maritimeCode, portCodeRef);
	}

	public static java.util.List<vn.gt.portlet.baocao.bc15t.BC15TModel> getModelMau15T(
			java.lang.String maritimeCode, java.lang.String startDate,
			java.lang.String endDate)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getModelMau15T(maritimeCode, startDate, endDate);
	}

	public static java.util.List<vn.gt.portlet.baocao.bc12bt.BC12BTKhuCangModel> getModelMau12BTKhuCang(
			java.lang.String maritimeCode, java.lang.String startDate,
			java.lang.String endDate)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				.getModelMau12BTKhuCang(maritimeCode, startDate, endDate);
	}


	/**
	* Deletes the dm port region from the database. Also notifies the appropriate model listeners.
	*
	* @param dmPortRegion the dm port region
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmPortRegion(
		com.fds.nsw.nghiepvu.model.DmPortRegion dmPortRegion)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmPortRegion(dmPortRegion);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmPortRegion fetchDmPortRegion(int id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmPortRegion(id);
	}

	/**
	* Returns the dm port region with the primary key.
	*
	* @param id the primary key of the dm port region
	* @return the dm port region
	* @throws PortalException if a dm port region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortRegion getDmPortRegion(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPortRegion(id);
	}

	

	/**
	* Returns a range of all the dm port regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm port regions
	* @param end the upper bound of the range of dm port regions (not inclusive)
	* @return the range of dm port regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortRegion> getDmPortRegions(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPortRegions(start, end);
	}

	/**
	* Returns the number of dm port regions.
	*
	* @return the number of dm port regions
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmPortRegionsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPortRegionsCount();
	}

	/**
	* Updates the dm port region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmPortRegion the dm port region
	* @return the dm port region that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortRegion updateDmPortRegion(
		com.fds.nsw.nghiepvu.model.DmPortRegion dmPortRegion)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmPortRegion(dmPortRegion);
	}

	/**
	* Updates the dm port region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmPortRegion the dm port region
	* @param merge whether to merge the dm port region with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm port region that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortRegion updateDmPortRegion(
		com.fds.nsw.nghiepvu.model.DmPortRegion dmPortRegion, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmPortRegion(dmPortRegion, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmPortRegion getByPortRegionCode(
		java.lang.String portRegionCode) {
		return getService().getByPortRegionCode(portRegionCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortRegion> findPortRegionByPortRegionRef(
		java.lang.String portRegionRef) {
		return getService().findPortRegionByPortRegionRef(portRegionRef);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortRegion> findPortRegionByPortCodeName(
		java.lang.String portCode) {
		return getService().findPortRegionByPortCodeName(portCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortRegion> findDanhSachDmPortRegion(
		java.lang.String maritimeCode, java.lang.String portRegionName,
		java.lang.String portCode, int start, int end) {
		return getService()
				   .findDanhSachDmPortRegion(maritimeCode, portRegionName,
			portCode, start, end);
	}

	public static int countDanhSachDmPortRegion(java.lang.String maritimeCode,
		java.lang.String portRegionName, java.lang.String portCode) {
		return getService()
				   .countDanhSachDmPortRegion(maritimeCode, portRegionName,
			portCode);
	}

	public static com.fds.nsw.nghiepvu.model.DmPortRegion getFirstPortRegion() {
		return getService().getFirstPortRegion();
	}

	public static com.fds.nsw.nghiepvu.model.DmPortRegion getLastPortRegion() {
		return getService().getLastPortRegion();
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}