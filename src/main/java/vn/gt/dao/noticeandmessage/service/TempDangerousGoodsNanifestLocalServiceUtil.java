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
 * The utility for the temp dangerous goods nanifest local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempDangerousGoodsManifestLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempDangerousGoodsNanifestLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempDangerousGoodsNanifestLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempDangerousGoodsManifestLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempDangerousGoodsManifestLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempDangerousGoodsNanifestLocalServiceUtil {
public TempDangerousGoodsNanifestLocalServiceUtil(TempDangerousGoodsManifestLocalServiceImpl service) {
TempDangerousGoodsNanifestLocalServiceUtil._service = service;
}
public static TempDangerousGoodsManifestLocalServiceImpl getService() {
return _service;
}
private static TempDangerousGoodsManifestLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempDangerousGoodsManifestLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp dangerous goods nanifest to the database. Also notifies the appropriate model listeners.
	*
	* @param tempDangerousGoodsNanifest the temp dangerous goods nanifest
	* @return the temp dangerous goods nanifest that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest addTempDangerousGoodsNanifest(
		com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest tempDangerousGoodsNanifest)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .addTempDangerousGoodsManifest(tempDangerousGoodsNanifest);
	}

	/**
	* Creates a new temp dangerous goods nanifest with the primary key. Does not add the temp dangerous goods nanifest to the database.
	*
	* @param id the primary key for the new temp dangerous goods nanifest
	* @return the new temp dangerous goods nanifest
	*/
	public static com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest createTempDangerousGoodsNanifest(
		long id) {
		return getService().createTempDangerousGoodsManifest(id);
	}

	/**
	* Deletes the temp dangerous goods nanifest with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp dangerous goods nanifest
	* @throws PortalException if a temp dangerous goods nanifest with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempDangerousGoodsNanifest(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempDangerousGoodsManifest(id);
	}

	/**
	* Deletes the temp dangerous goods nanifest from the database. Also notifies the appropriate model listeners.
	*
	* @param tempDangerousGoodsNanifest the temp dangerous goods nanifest
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempDangerousGoodsNanifest(
		com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest tempDangerousGoodsNanifest)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempDangerousGoodsManifest(tempDangerousGoodsNanifest);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest fetchTempDangerousGoodsNanifest(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempDangerousGoodsManifest(id);
	}

	/**
	* Returns the temp dangerous goods nanifest with the primary key.
	*
	* @param id the primary key of the temp dangerous goods nanifest
	* @return the temp dangerous goods nanifest
	* @throws PortalException if a temp dangerous goods nanifest with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest getTempDangerousGoodsNanifest(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDangerousGoodsManifest(id);
	}

	

	/**
	* Returns a range of all the temp dangerous goods nanifests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp dangerous goods nanifests
	* @param end the upper bound of the range of temp dangerous goods nanifests (not inclusive)
	* @return the range of temp dangerous goods nanifests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest> getTempDangerousGoodsNanifests(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDangerousGoodsManifests(start, end);
	}

	/**
	* Returns the number of temp dangerous goods nanifests.
	*
	* @return the number of temp dangerous goods nanifests
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempDangerousGoodsNanifestsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDangerousGoodsManifestsCount();
	}

	/**
	* Updates the temp dangerous goods nanifest in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempDangerousGoodsNanifest the temp dangerous goods nanifest
	* @return the temp dangerous goods nanifest that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest updateTempDangerousGoodsNanifest(
		com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest tempDangerousGoodsNanifest)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempDangerousGoodsManifest(tempDangerousGoodsNanifest);
	}

	/**
	* Updates the temp dangerous goods nanifest in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempDangerousGoodsNanifest the temp dangerous goods nanifest
	* @param merge whether to merge the temp dangerous goods nanifest with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp dangerous goods nanifest that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest updateTempDangerousGoodsNanifest(
		com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest tempDangerousGoodsNanifest,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempDangerousGoodsManifest(tempDangerousGoodsNanifest,
			merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest> findBydocumentNameAnddocumentYearRequestState(
		long documentName, int documentYear, int requestState) {
		return getService()
				   .findBydocumentNameAnddocumentYearRequestState(documentName,
			documentYear, requestState);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest> findByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRequestCode(requestCode);
	}

	public static int countBydocumentNameAnddocumentYear(long documentName,
		int documentYear) {
		return getService()
				   .countBydocumentNameAnddocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public static com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest getLastByDocumentNameAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .getLastByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest> findByDocumentNameAndDocumentYearOrderByDescRequestDate(
		long documentName, int documentYear) {
		return getService()
				   .findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName,
			documentYear);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}