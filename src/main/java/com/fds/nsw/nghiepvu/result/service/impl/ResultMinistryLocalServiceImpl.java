
package com.fds.nsw.nghiepvu.result.service.impl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.result.service.finder.ResultMinistryFinderImpl;
import com.fds.nsw.nghiepvu.result.service.persistence.ResultMinistryPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service


/**
 * The implementation of the result ministry local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.result.service.ResultMinistryLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can
 * only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.result.service.base.ResultMinistryLocalServiceBaseImpl
 * @see vn.gt.dao.result.service.ResultMinistryLocalServiceUtil
 */
public class ResultMinistryLocalServiceImpl { @Autowired
ResultMinistryPersistenceImpl persistence;@Autowired
ResultMinistryFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link vn.gt.dao.result.service.ResultMinistryLocalServiceUtil} to
	 * access the result ministry local service.
	 */
	
	/**
	 * Returns the result ministry where documentName = &#63; and documentYear = &#63; and ministryCode = &#63; and businessTypeCode = &#63; or throws
	 * a {@link vn.gt.dao.result.NoSuchResultMinistryException} if it could not be found.
	 *
	 * @param documentName
	 *            the document name
	 * @param documentYear
	 *            the document year
	 * @param ministryCode
	 *            the ministry code
	 * @param businessTypeCode
	 *            the business type code
	 * @return the matching result ministry
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException
	 *             if a matching result ministry could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public List<ResultMinistry> findByDocumentNameAnddocumentYearMinistryCodeBusinessTypeCode(int documentName, int documentYear,
			String ministryCode, int businessTypeCode) {
		try {
			return finder.findByDocumentNameAnddocumentYearMinistryCodeBusinessTypeCode(documentName, documentYear, ministryCode,
					businessTypeCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ResultMinistry> findDistinctMinistryCode(long documentName, int documentYear) {
		try {
			return finder.findDistinctMinistryCode(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ResultMinistry> findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCode(long documentName, int documentYear,
			String ministryCode, String businessTypeCode) {
		try {
			return finder.findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCode(documentName, documentYear, ministryCode,
					businessTypeCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ResultMinistry> findDocNameAndDocYearAndMinistryCode(int documentName, int documentYear, String ministryCode) {
		try {
			return persistence.findByDocNameAndDocYearAndMinistryCode(documentName, documentYear, ministryCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultMinistry findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCodePhanHoi(long documentName, int documentYear,
			String ministryCode, String lstBusinessTypeCode) {
		try {
			List<ResultMinistry> resultMinistrys = finder.findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCode(documentName,
					documentYear, ministryCode, lstBusinessTypeCode);
			if (resultMinistrys != null && resultMinistrys.size() > 0) {
				return resultMinistrys.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.result.service.ResultMinistryLocalServiceUtil} to access the result ministry local service.
	 */

	/**
	 * Adds the result ministry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param resultMinistry the result ministry
	 * @return the result ministry that was added
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry addResultMinistry(ResultMinistry resultMinistry)
			throws SystemException {

		resultMinistry = persistence.updateImpl(resultMinistry, false);





		return resultMinistry;
	}

	/**
	 * Creates a new result ministry with the primary key. Does not add the result ministry to the database.
	 *
	 * @param id the primary key for the new result ministry
	 * @return the new result ministry
	 */
	public ResultMinistry createResultMinistry(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the result ministry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the result ministry
	 * @throws PortalException if a result ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteResultMinistry(long id)
			throws PortalException, SystemException {
		ResultMinistry resultMinistry = persistence.remove(id);




	}

	/**
	 * Deletes the result ministry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param resultMinistry the result ministry
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteResultMinistry(ResultMinistry resultMinistry)
			throws SystemException {
		persistence.remove(resultMinistry);




	}













	public ResultMinistry fetchResultMinistry(long id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the result ministry with the primary key.
	 *
	 * @param id the primary key of the result ministry
	 * @return the result ministry
	 * @throws PortalException if a result ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry getResultMinistry(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the result ministries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result ministries
	 * @param end the upper bound of the range of result ministries (not inclusive)
	 * @return the range of result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultMinistry> getResultMinistries(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of result ministries.
	 *
	 * @return the number of result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int getResultMinistriesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the result ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param resultMinistry the result ministry
	 * @return the result ministry that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry updateResultMinistry(ResultMinistry resultMinistry)
			throws SystemException {
		return updateResultMinistry(resultMinistry, true);
	}

	/**
	 * Updates the result ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param resultMinistry the result ministry
	 * @param merge whether to merge the result ministry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the result ministry that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry updateResultMinistry(ResultMinistry resultMinistry,
											   boolean merge) throws SystemException {

		resultMinistry = persistence.updateImpl(resultMinistry, merge);





		return resultMinistry;
	}
}