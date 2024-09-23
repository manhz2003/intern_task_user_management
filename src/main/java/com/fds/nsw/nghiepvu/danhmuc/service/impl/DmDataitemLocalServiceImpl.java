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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;

import java.util.ArrayList;
import java.util.List;import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmDataItemFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmDataItemPersistenceImpl;
import com.fds.nsw.nghiepvu.model.DmDataitem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmDataitemLocalServiceImpl {
	@Autowired
	DmDataItemPersistenceImpl persistence;
	@Autowired
	DmDataItemFinderImpl finder;

	public DmDataitem findByDataGroupIdAndCode0(long DataGroupId, String Code0) {
		try {
			return persistence.findByDataGroupIdAndCode0(DataGroupId, Code0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<DmDataitem> findByDataGroupId(long DataGroupId) {
		try {
			return persistence.findByDataGroupId(DataGroupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DmDataitem>();
	}

	public List<DmDataitem> findByDataGroupIdAndLevel(long DataGroupId, int Level) {
		try {
			return persistence.findByDataGroupIdAndLevel(DataGroupId, Level);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DmDataitem>();
	}

	public List<DmDataitem> findByDataGroupIdAndLevelandShortName(long DataGroupId, int Level, String ShortName) {
		try {
			return persistence.findByDataGroupIdAndLevelandShortName(DataGroupId, Level, ShortName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DmDataitem>();
	}

	public List<DmDataitem> findByDataGroupIdAndNode1(long DataGroupId, String Node1) {
		try {
			return persistence.findByDataGroupIdAndNode1(DataGroupId, Node1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DmDataitem>();
	}

	public List<DmDataitem> findByDataGroupIdAndShortName(long DataGroupId, String ShortName) {
		try {
			return persistence.findByDataGroupIdAndShortName(DataGroupId, ShortName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DmDataitem>();
	}

	public List<DmDataitem> getAllByDataGroupID(long datagroupid, int start, int end) {
		try {
			return finder.getAllByDataGroupID(datagroupid, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ArrayList<DmDataitem>();
	}

	public DmDataitem getByNode2AndDataGroupID(long datagroupid, String node2) {
		try {
			return finder.getByNode2AndDataGroupID(datagroupid, node2);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<DmDataitem> getAllByNode1Node2AndDataGroupID(long datagroupid, String node1, String node2, int start,
			int end) {
		try {
			return finder.getAllByNode1Node2AndDataGroupID(datagroupid, node1, node2, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ArrayList<DmDataitem>();
	}

	public int countByNode1Node2AndDataGroupID(long datagroupid, String node1, String node2, int start, int end) {
		try {
			return finder.countByNode1Node2AndDataGroupID(datagroupid, node1, node2, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}

	public DmDataitem findByDataGroupId_Node2(long dataGroupId, String node2) {
		try {
			return persistence.findByF_dataGroupId_node2(dataGroupId,
					node2);
		} catch (Exception e) {
			return null;
		}
	}

	public List<DmDataitem> findDataItems(long dataGroupId, String name,
										  String maritimeCode, String codeGroup, String status, int start,
										  int end) {
		try {
			return finder.findDataItems(dataGroupId, name,
					maritimeCode, codeGroup, status, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public long countDataItems(long dataGroupId, String name,
							   String maritimeCode, String codeGroup, String status) {
		try {
			return finder.countDataItems(dataGroupId, name,
					maritimeCode, codeGroup, status);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}


	public DmDataitem addDmDataitem(DmDataitem dmDataItem) throws SystemException {

		dmDataItem = persistence.updateImpl(dmDataItem, false);

		return dmDataItem;
	}

	public DmDataitem createDmDataitem(long Id) {
		return persistence.create(Id);
	}

	public void deleteDmDataitem(long Id) throws PortalException, SystemException {
		DmDataitem dmDataItem = persistence.remove(Id);

	}

	public void deleteDmDataitem(DmDataitem dmDataItem) throws SystemException {
		persistence.remove(dmDataItem);

	}

	public DmDataitem fetchDmDataitem(long Id) throws SystemException {
		return persistence.fetchByPrimaryKey(Id);
	}

	public DmDataitem getDmDataitem(long Id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(Id);
	}

	/*
	 * public PersistedModel getPersistedModel(Serializable primaryKeyObj) throws
	 * PortalException, SystemException { return
	 * persistence.findByPrimaryKey(primaryKeyObj); }
	 */

	public List<DmDataitem> getDmDataitems(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm data items.
	 *
	 * @return the number of dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmDataitemsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm data item in the database or adds it if it does not yet exist.
	 * Also notifies the appropriate model listeners.
	 *
	 * @param dmDataItem the dm data item
	 * @return the dm data item that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem updateDmDataitem(DmDataitem dmDataItem) throws SystemException {
		return updateDmDataitem(dmDataItem, true);
	}

	/**
	 * Updates the dm data item in the database or adds it if it does not yet exist.
	 * Also notifies the appropriate model listeners.
	 *
	 * @param dmDataItem the dm data item
	 * @param merge      whether to merge the dm data item with the current session.
	 *                   See
	 *                   
	 *                   for an explanation.
	 * @return the dm data item that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem updateDmDataitem(DmDataitem dmDataItem, boolean merge) throws SystemException {

		dmDataItem = persistence.updateImpl(dmDataItem, merge);

		return dmDataItem;
	}

}