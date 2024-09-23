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
 * The utility for the dm port wharf local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmPortWharfLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmPortWharfLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmPortWharfLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmPortWharfLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmPortWharfLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmPortWharfLocalServiceUtil {
public DmPortWharfLocalServiceUtil(DmPortWharfLocalServiceImpl service) {
DmPortWharfLocalServiceUtil._service = service;
}
public static DmPortWharfLocalServiceImpl getService() {
return _service;
}
private static DmPortWharfLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmPortWharfLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm port wharf to the database. Also notifies the appropriate model listeners.
	*
	* @param dmPortWharf the dm port wharf
	* @return the dm port wharf that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortWharf addDmPortWharf(
		com.fds.nsw.nghiepvu.model.DmPortWharf dmPortWharf)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmPortWharf(dmPortWharf);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortWharf> findPortWharfs(
			java.lang.String maritimeCode, java.lang.String portRegionCode,
			java.lang.String portHarbourCode, java.lang.String portWharfNameVN,
			java.lang.String isDelete, java.lang.String portWharfCodeGroup,
			int start, int end) {
		return getService()
				.findPortWharfs(maritimeCode, portRegionCode,
						portHarbourCode, portWharfNameVN, isDelete, portWharfCodeGroup,
						start, end);
	}

	public static long countPortWharfs(java.lang.String maritimeCode,
									   java.lang.String portRegionCode, java.lang.String portHarbourCode,
									   java.lang.String portWharfNameVN, java.lang.String isDelete,
									   java.lang.String portWharfCodeGroup) {
		return getService()
				.countPortWharfs(maritimeCode, portRegionCode,
						portHarbourCode, portWharfNameVN, isDelete, portWharfCodeGroup);
	}

	public static long getMaxSequenceNo(java.lang.String portRegionCode,
										java.lang.String portHarbourCode) {
		return getService().getMaxSequenceNo(portRegionCode, portHarbourCode);
	}

	public static java.util.List<vn.gt.portlet.baocao.bc12bt.BC12BTVinalinesModel> getModelMau12BTVinalines(
			java.lang.String maritimeCode, java.lang.String startDate,
			java.lang.String endDate)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				.getModelMau12BTVinalines(maritimeCode, startDate, endDate);
	}

	public static org.json.JSONArray getModelMau60(
			java.lang.String maritimeCode, java.lang.String startDate,
			java.lang.String endDate)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getModelMau60(maritimeCode, startDate, endDate);
	}

	public static org.json.JSONArray getModelMau58S(
			java.lang.String maritimeCode, java.lang.String startDate,
			java.lang.String endDate)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getModelMau58S(maritimeCode, startDate, endDate);
	}


	/**
	* Creates a new dm port wharf with the primary key. Does not add the dm port wharf to the database.
	*
	* @param id the primary key for the new dm port wharf
	* @return the new dm port wharf
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortWharf createDmPortWharf(int id) {
		return getService().createDmPortWharf(id);
	}

	/**
	* Deletes the dm port wharf with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm port wharf
	* @throws PortalException if a dm port wharf with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmPortWharf(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmPortWharf(id);
	}

	/**
	* Deletes the dm port wharf from the database. Also notifies the appropriate model listeners.
	*
	* @param dmPortWharf the dm port wharf
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmPortWharf(
		com.fds.nsw.nghiepvu.model.DmPortWharf dmPortWharf)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmPortWharf(dmPortWharf);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmPortWharf fetchDmPortWharf(int id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmPortWharf(id);
	}

	/**
	* Returns the dm port wharf with the primary key.
	*
	* @param id the primary key of the dm port wharf
	* @return the dm port wharf
	* @throws PortalException if a dm port wharf with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortWharf getDmPortWharf(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPortWharf(id);
	}

	

	/**
	* Returns a range of all the dm port wharfs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm port wharfs
	* @param end the upper bound of the range of dm port wharfs (not inclusive)
	* @return the range of dm port wharfs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortWharf> getDmPortWharfs(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPortWharfs(start, end);
	}

	/**
	* Returns the number of dm port wharfs.
	*
	* @return the number of dm port wharfs
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmPortWharfsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPortWharfsCount();
	}

	/**
	* Updates the dm port wharf in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmPortWharf the dm port wharf
	* @return the dm port wharf that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortWharf updateDmPortWharf(
		com.fds.nsw.nghiepvu.model.DmPortWharf dmPortWharf)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmPortWharf(dmPortWharf);
	}

	/**
	* Updates the dm port wharf in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmPortWharf the dm port wharf
	* @param merge whether to merge the dm port wharf with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm port wharf that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortWharf updateDmPortWharf(
		com.fds.nsw.nghiepvu.model.DmPortWharf dmPortWharf, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmPortWharf(dmPortWharf, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmPortWharf getByPortWharfCode(
		java.lang.String portWharfCode) {
		return getService().getByPortWharfCode(portWharfCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortWharf> findByPortRegionCode(
		java.lang.String portRegionCode) {
		return getService().findByPortRegionCode(portRegionCode);
	}

	/**
	* ascOrdesc:
	* asc = true
	* desc = false
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortWharf> findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(
		java.lang.String portRegionCode, java.lang.String portHarbourCode,
		boolean ascOrdesc) {
		return getService()
				   .findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(portRegionCode,
			portHarbourCode, ascOrdesc);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortWharf> findByPortHarbourCodeOrNull(
		java.lang.String portHarbourCode, boolean ascOrdesc) {
		return getService()
				   .findByPortHarbourCodeOrNull(portHarbourCode, ascOrdesc);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortWharf> findByPortRegionCodeOrderPortCode(
		java.lang.String portRegionCode, boolean ascOrdesc) {
		return getService()
				   .findByPortRegionCodeOrderPortCode(portRegionCode, ascOrdesc);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortWharf> findDanhSachDmPortWharf(
		java.lang.String portRegionCode, java.lang.String portHarbourCode,
		java.lang.String PortWharfNameVN,java.lang.Integer portWharfPayment, boolean ascOrdesc, int start, int end) {
		return getService()
				   .findDanhSachDmPortWharf(portRegionCode, portHarbourCode,
			PortWharfNameVN, portWharfPayment, ascOrdesc, start, end);
	}

	public static int countDanhSachDmPortWharf(
		java.lang.String portRegionCode, java.lang.String portHarbourCode,
		java.lang.String PortWharfNameVN, java.lang.Integer portWharfPayment) {
		return getService()
				   .countDanhSachDmPortWharf(portRegionCode, portHarbourCode,
			PortWharfNameVN, portWharfPayment);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortWharf> findDongBoDmPortWharf(
		java.lang.String MaritimeCode, java.lang.String portRegionCode,
		java.lang.String portHarbourCode, java.lang.String PortWharfNameVN,
		boolean ascOrdesc, int start, int end) {
		return getService()
				   .findDongBoDmPortWharf(MaritimeCode, portRegionCode,
			portHarbourCode, PortWharfNameVN, ascOrdesc, start, end);
	}

	public static int countDongBoDmPortWharf(java.lang.String MaritimeCode,
		java.lang.String portRegionCode, java.lang.String portHarbourCode,
		java.lang.String PortWharfNameVN) {
		return getService()
				   .countDongBoDmPortWharf(MaritimeCode, portRegionCode,
			portHarbourCode, PortWharfNameVN);
	}

	public static com.fds.nsw.nghiepvu.model.DmPortWharf getFirstPortWharf() {
		return getService().getFirstPortWharf();
	}

	public static com.fds.nsw.nghiepvu.model.DmPortWharf getLastPortWharf() {
		return getService().getLastPortWharf();
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}