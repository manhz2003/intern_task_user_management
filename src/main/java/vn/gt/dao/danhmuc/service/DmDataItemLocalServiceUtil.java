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
 * The utility for the dm data item local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmDataitemLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmDataItemLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmDataItemLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmDataitemLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmDataitemLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmDataItemLocalServiceUtil {
public DmDataItemLocalServiceUtil(DmDataitemLocalServiceImpl service) {
DmDataItemLocalServiceUtil._service = service;
}
public static DmDataitemLocalServiceImpl getService() {
return _service;
}
private static DmDataitemLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmDataitemLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm data item to the database. Also notifies the appropriate model listeners.
	*
	* @param dmDataItem the dm data item
	* @return the dm data item that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmDataitem addDmDataitem(
		com.fds.nsw.nghiepvu.model.DmDataitem dmDataItem)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmDataitem(dmDataItem);
	}

	/**
	* Creates a new dm data item with the primary key. Does not add the dm data item to the database.
	*
	* @param Id the primary key for the new dm data item
	* @return the new dm data item
	*/
	public static com.fds.nsw.nghiepvu.model.DmDataitem createDmDataitem(long Id) {
		return getService().createDmDataitem(Id);
	}

	/**
	* Deletes the dm data item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param Id the primary key of the dm data item
	* @throws PortalException if a dm data item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmDataItem(long Id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmDataitem(Id);
	}

	/**
	* Deletes the dm data item from the database. Also notifies the appropriate model listeners.
	*
	* @param dmDataItem the dm data item
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmDataItem(
		com.fds.nsw.nghiepvu.model.DmDataitem dmDataItem)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmDataitem(dmDataItem);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmDataitem fetchDmDataItem(long Id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmDataitem(Id);
	}

	/**
	* Returns the dm data item with the primary key.
	*
	* @param Id the primary key of the dm data item
	* @return the dm data item
	* @throws PortalException if a dm data item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmDataitem getDmDataItem(long Id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmDataitem(Id);
	}

	

	/**
	* Returns a range of all the dm data items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm data items
	* @param end the upper bound of the range of dm data items (not inclusive)
	* @return the range of dm data items
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmDataitem> getDmDataItems(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmDataitems(start, end);
	}

	/**
	* Returns the number of dm data items.
	*
	* @return the number of dm data items
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmDataItemsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmDataitemsCount();
	}

	/**
	* Updates the dm data item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmDataItem the dm data item
	* @return the dm data item that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmDataitem updateDmDataItem(
		com.fds.nsw.nghiepvu.model.DmDataitem dmDataItem)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmDataitem(dmDataItem);
	}

	/**
	* Updates the dm data item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmDataItem the dm data item
	* @param merge whether to merge the dm data item with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm data item that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmDataitem updateDmDataItem(
		com.fds.nsw.nghiepvu.model.DmDataitem dmDataItem, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmDataitem(dmDataItem, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmDataitem findByDataGroupIdAndCode0(
		long DataGroupId, java.lang.String Code0) {
		return getService().findByDataGroupIdAndCode0(DataGroupId, Code0);
	}

	public static com.fds.nsw.nghiepvu.model.DmDataitem findByDataGroupId_Node2(
			long dataGroupId, java.lang.String node2) {
		return getService().findByDataGroupId_Node2(dataGroupId, node2);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmDataitem> findDataItems(
			long dataGroupId, java.lang.String name, java.lang.String maritimeCode,
			java.lang.String codeGroup, java.lang.String status, int start, int end) {
		return getService()
				.findDataItems(dataGroupId, name, maritimeCode, codeGroup,
						status, start, end);
	}

	public static long countDataItems(long dataGroupId, java.lang.String name,
									  java.lang.String maritimeCode, java.lang.String codeGroup,
									  java.lang.String status) {
		return getService()
				.countDataItems(dataGroupId, name, maritimeCode, codeGroup,
						status);
	}


	public static java.util.List<com.fds.nsw.nghiepvu.model.DmDataitem> findByDataGroupId(
		long DataGroupId) {
		return getService().findByDataGroupId(DataGroupId);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmDataitem> findByDataGroupIdAndLevel(
		long DataGroupId, int Level) {
		return getService().findByDataGroupIdAndLevel(DataGroupId, Level);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmDataitem> findByDataGroupIdAndLevelandShortName(
		long DataGroupId, int Level, java.lang.String ShortName) {
		return getService()
				   .findByDataGroupIdAndLevelandShortName(DataGroupId, Level,
			ShortName);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmDataitem> findByDataGroupIdAndNode1(
		long DataGroupId, java.lang.String Node1) {
		return getService().findByDataGroupIdAndNode1(DataGroupId, Node1);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmDataitem> findByDataGroupIdAndShortName(
		long DataGroupId, java.lang.String ShortName) {
		return getService().findByDataGroupIdAndShortName(DataGroupId, ShortName);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmDataitem> getAllByDataGroupID(
		long datagroupid, int start, int end) {
		return getService().getAllByDataGroupID(datagroupid, start, end);
	}

	public static com.fds.nsw.nghiepvu.model.DmDataitem getByNode2AndDataGroupID(
		long datagroupid, java.lang.String node2) {
		return getService().getByNode2AndDataGroupID(datagroupid, node2);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmDataitem> getAllByNode1Node2AndDataGroupID(
		long datagroupid, java.lang.String node1, java.lang.String node2,
		int start, int end) {
		return getService()
				   .getAllByNode1Node2AndDataGroupID(datagroupid, node1, node2,
			start, end);
	}

	public static int countByNode1Node2AndDataGroupID(long datagroupid,
		java.lang.String node1, java.lang.String node2, int start, int end) {
		return getService()
				   .countByNode1Node2AndDataGroupID(datagroupid, node1, node2,
			start, end);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}