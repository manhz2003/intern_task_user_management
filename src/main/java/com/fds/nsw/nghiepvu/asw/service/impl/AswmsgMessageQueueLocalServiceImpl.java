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
package com.fds.nsw.nghiepvu.asw.service.impl;

import java.io.Serializable;
import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.asw.service.finder.AswmsgMessageQueueFinderImpl;
import com.fds.nsw.nghiepvu.asw.service.persistence.AswmsgMessagequeuePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class AswmsgMessageQueueLocalServiceImpl {
	@Autowired
	AswmsgMessagequeuePersistenceImpl persistence;@Autowired
	AswmsgMessageQueueFinderImpl aswmsgMessageQueueFinder;
	public List<AswmsgMessagequeue> getListMessage2Queue(int count){
		return aswmsgMessageQueueFinder.getListMessage2Queue(count);
	}
	
	public List<AswmsgMessagequeue> getListMessageQueuePending(String validationCode){
		return aswmsgMessageQueueFinder.getListMessageQueuePending(validationCode);
	}
	
	public boolean updatePriorityAswmsgMessageQueue(int priority, long id) {
		try{
			AswmsgMessagequeue aswmsgMessageQueue = persistence.fetchByPrimaryKey(id);
			
			if(aswmsgMessageQueue != null) {
				aswmsgMessageQueue.setPriority(priority);
				
				persistence.updateImpl(aswmsgMessageQueue, false);
			}
			
			return true;
		}catch (SystemException e) {
		
		}
		
		return false;
	}
	
	public boolean updateListMessageQueuePending(String validationCode) {
		try{
			List<AswmsgMessagequeue> listMessageQueuePending = aswmsgMessageQueueFinder.getListMessageQueuePending(validationCode);
			
			for(AswmsgMessagequeue aswmsgMessageQueue : listMessageQueuePending) {
				aswmsgMessageQueue.setSolangui(0);
				aswmsgMessageQueue.setValidated(-1);
				
				persistence.updateImpl(aswmsgMessageQueue, false);
			
			}
			
			return true;
		}catch (SystemException e) {
		
		}
		
		return false;
	}

	public AswmsgMessagequeue addAswmsgMessageQueue(
			AswmsgMessagequeue aswmsgMessageQueue) throws SystemException {
		aswmsgMessageQueue = persistence.updateImpl(aswmsgMessageQueue,
				false);


		return aswmsgMessageQueue;
	}

	/**
	 * Creates a new aswmsg message queue with the primary key. Does not add the aswmsg message queue to the database.
	 *
	 * @param id the primary key for the new aswmsg message queue
	 * @return the new aswmsg message queue
	 */
	public AswmsgMessagequeue createAswmsgMessageQueue(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the aswmsg message queue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the aswmsg message queue
	 * @throws PortalException if a aswmsg message queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteAswmsgMessageQueue(long id)
			throws PortalException, SystemException {
		AswmsgMessagequeue aswmsgMessageQueue = persistence.remove(id);

	}

	/**
	 * Deletes the aswmsg message queue from the database. Also notifies the appropriate model listeners.
	 *
	 * @param aswmsgMessageQueue the aswmsg message queue
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteAswmsgMessageQueue(AswmsgMessagequeue aswmsgMessageQueue)
			throws SystemException {
		persistence.remove(aswmsgMessageQueue);

	}



	public AswmsgMessagequeue fetchAswmsgMessageQueue(long id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the aswmsg message queue with the primary key.
	 *
	 * @param id the primary key of the aswmsg message queue
	 * @return the aswmsg message queue
	 * @throws PortalException if a aswmsg message queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AswmsgMessagequeue getAswmsgMessageQueue(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the aswmsg message queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of aswmsg message queues
	 * @param end the upper bound of the range of aswmsg message queues (not inclusive)
	 * @return the range of aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> getAswmsgMessageQueues(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of aswmsg message queues.
	 *
	 * @return the number of aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public int getAswmsgMessageQueuesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the aswmsg message queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param aswmsgMessageQueue the aswmsg message queue
	 * @return the aswmsg message queue that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public AswmsgMessagequeue updateAswmsgMessageQueue(
			AswmsgMessagequeue aswmsgMessageQueue) throws SystemException {
		return updateAswmsgMessageQueue(aswmsgMessageQueue, true);
	}

	/**
	 * Updates the aswmsg message queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param aswmsgMessageQueue the aswmsg message queue
	 * @param merge whether to merge the aswmsg message queue with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the aswmsg message queue that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public AswmsgMessagequeue updateAswmsgMessageQueue(
			AswmsgMessagequeue aswmsgMessageQueue, boolean merge)
			throws SystemException {

		aswmsgMessageQueue = persistence.updateImpl(aswmsgMessageQueue,
				merge);



		return aswmsgMessageQueue;
	}
}