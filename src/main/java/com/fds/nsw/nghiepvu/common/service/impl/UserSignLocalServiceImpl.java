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

package com.fds.nsw.nghiepvu.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.flex.common.ultility.Validator;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import com.fds.nsw.liferay.file.FileUtil;
import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.common.service.persistence.UserSignPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.model.User;

import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.utils.document.DocumentUtils;

@Slf4j @Service

public class UserSignLocalServiceImpl { @Autowired
UserSignPersistenceImpl persistence;

	public void deleteUserSignById(long userSignId) throws SystemException {
		UserSign userSign = persistence.fetchByPrimaryKey(userSignId);

		if(userSign != null) {
			long fileChungThuSo = userSign.getFilechungthusoid();
			long fileChuKy = userSign.getFilechukyid();
			long fileConDau = userSign.getFilecondauid();

			persistence.remove(userSign);

			deleteFile(fileChungThuSo);
			deleteFile(fileChuKy);
			deleteFile(fileConDau);
		}
	}

	public UserSign updateUserSign(long userId, long userSignId,
			long accountId, String maritimeCode, String title, String representative,
			String chungThuSoName, File chungThuSoFile,
			boolean deleteFileChungThuSo, String chuKySoName, File chuKySoFile,
			boolean deleteFileChuKySo, String conDauName, File conDauFile,
			boolean deleteFileConDau)
		throws PortalException, SystemException {

		UserSign userSign = null;

		if(accountId > 0 && Validator.isNotNull(title)) {
			long chungThuSoId = 0;
			long chuKySoId = 0;
			long conDauId = 0;
			//User user = persistence.findByPrimaryKey(userId);

			//UserPort userPort = persistence.findByUserId(userId);


			User user = UserLocalServiceUtil.fetchUser(accountId);
			UserPort userPort = UserPortLocalServiceUtil.findByUserId(accountId);
			userSign = persistence.fetchByUserId(accountId);

			if(userSign == null) {
				userSign = persistence.create(
						CounterLocalServiceUtil.increment(UserSign.class.getName()));
				userSign.setFilechungthusoid(chungThuSoId);
				userSign.setFilechukyid(chuKySoId);
				userSign.setFilecondauid(conDauId);
			}

			userSign.setUserId(accountId);

			if(Validator.isNotNull(maritimeCode)) {
				userSign.setPortcode(maritimeCode);
			} else if (userPort != null) {
				userSign.setPortcode(userPort.getPortCode());
			}

			userSign.setTitle(title);
			userSign.setRepresentative(representative);



			if(Validator.isNotNull(chungThuSoName)) {
				byte[] chungThuSoBytes = null;

				try {
					chungThuSoBytes = FileUtil.getBytes(chungThuSoFile);
					if (chungThuSoBytes != null) {
						chungThuSoId = DocumentUtils.uploadTaiLieu(chungThuSoBytes, userId, chungThuSoName);
					}
				}
				catch (IOException ioe) {
				}


			} /* SONVH comment 20170618
			else if (deleteFileChungThuSo){
				chungThuSoId = 0;

				deleteFile(userSign.getFilechungthusoid());
			}*/

			if(Validator.isNotNull(chuKySoName)) {
				byte[] chuKySoBytes = null;

				try {
					chuKySoBytes = FileUtil.getBytes(chuKySoFile);
					if (chuKySoBytes != null) {
						chuKySoId = DocumentUtils.uploadTaiLieu(chuKySoBytes, userId, chuKySoName);
					}
				}
				catch (IOException ioe) {
				}


			} /* SONVH comment 20170618
			else if (deleteFileChuKySo){
				chuKySoId = 0;

				deleteFile(userSign.getFilechukyid());
			}*/

			if(Validator.isNotNull(conDauName)) {
				byte[] conDauBytes = null;

				try {
					conDauBytes = FileUtil.getBytes(conDauFile);
					if (conDauBytes != null) {
						conDauId = DocumentUtils.uploadTaiLieu(conDauBytes, userId, conDauName);
					}
				}
				catch (IOException ioe) {
				}


			} /* SONVH comment 20170618
			else if (deleteFileChungThuSo){
				conDauId = 0;

				deleteFile(userSign.getFilecondauid());
			}*/

			if (chungThuSoId > 0){
				userSign.setFilechungthusoid(chungThuSoId);
			}
			if (chuKySoId > 0) {
				userSign.setFilechukyid(chuKySoId);
			}
			if (conDauId > 0) {
				userSign.setFilecondauid(conDauId);
			}


			userSign.setModifydate(new Date());
			userSign.setModifyuser(user.getEmailAddress());

			persistence.updateImpl(userSign, false);
		}

		return userSign;
	}

	public UserSign getByUserId(long userId) {
		UserSign userSign = null;

		try {
			userSign = persistence.fetchByUserId(userId);
		} catch(SystemException e) {
			log.error(e.getMessage());
		}

		return userSign;
	}

	public List<UserSign> getByPortCode(String portCode, int start, int end) {
		List<UserSign> userSigns = new ArrayList<UserSign>();

		try {
			if(Validator.isNotNull(portCode)) {
				userSigns = persistence.findByPortCode(portCode, start, end);
			} else {
				userSigns = persistence.findAll(start, end);
			}
		} catch(SystemException e) {
			log.error(e.getMessage());
		}

		return userSigns;
	}

	public int countByPortCode(String portCode) {
		int cnt = 0;

		try {
			if(Validator.isNotNull(portCode)) {
				cnt = persistence.countByPortCode(portCode);
			} else {
				cnt = persistence.countAll();
			}
		} catch(SystemException e) {
			log.error(e.getMessage());
		}

		return cnt;
	}

	private void deleteFile(long fileEntryId) {

		if(fileEntryId > 0) {
			try {
				//todo add persistent DLFileEntryLocalServiceUtil
//				DLAppLocalServiceUtil.deleteFileEntry(fileEntryId);
			} catch(Exception e) {
				log.error(e.getMessage());
			}
		}
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.common.service.UserSignLocalServiceUtil} to access the user sign local service.
	 */

	/**
	 * Adds the user sign to the database. Also notifies the appropriate model listeners.
	 *
	 * @param userSign the user sign
	 * @return the user sign that was added
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign addUserSign(UserSign userSign) throws SystemException {

		userSign = persistence.updateImpl(userSign, false);





		return userSign;
	}

	/**
	 * Creates a new user sign with the primary key. Does not add the user sign to the database.
	 *
	 * @param id the primary key for the new user sign
	 * @return the new user sign
	 */
	public UserSign createUserSign(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the user sign with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the user sign
	 * @throws PortalException if a user sign with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteUserSign(long id) throws PortalException, SystemException {
		UserSign userSign = persistence.remove(id);




	}

	/**
	 * Deletes the user sign from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userSign the user sign
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteUserSign(UserSign userSign) throws SystemException {
		persistence.remove(userSign);




	}













	public UserSign fetchUserSign(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the user sign with the primary key.
	 *
	 * @param id the primary key of the user sign
	 * @return the user sign
	 * @throws PortalException if a user sign with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign getUserSign(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	/**
	 * Returns a range of all the user signs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of user signs
	 * @param end the upper bound of the range of user signs (not inclusive)
	 * @return the range of user signs
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserSign> getUserSigns(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of user signs.
	 *
	 * @return the number of user signs
	 * @throws SystemException if a system exception occurred
	 */
	public int getUserSignsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the user sign in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param userSign the user sign
	 * @return the user sign that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign updateUserSign(UserSign userSign) throws SystemException {
		return updateUserSign(userSign, true);
	}

	/**
	 * Updates the user sign in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param userSign the user sign
	 * @param merge whether to merge the user sign with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the user sign that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign updateUserSign(UserSign userSign, boolean merge)
			throws SystemException {

		userSign = persistence.updateImpl(userSign, merge);





		return userSign;
	}
}