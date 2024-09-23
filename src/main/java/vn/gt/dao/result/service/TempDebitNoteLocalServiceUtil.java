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

package vn.gt.dao.result.service;






/**
 * The utility for the temp debit note local service. This utility wraps {@link vn.gt.dao.result.service.impl.TempDebitNoteLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempDebitNoteLocalService
 * @see vn.gt.dao.result.service.base.TempDebitNoteLocalServiceBaseImpl
 * @see vn.gt.dao.result.service.impl.TempDebitNoteLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.result.service.impl.TempDebitNoteLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempDebitNoteLocalServiceUtil {
public TempDebitNoteLocalServiceUtil(TempDebitNoteLocalServiceImpl service) {
TempDebitNoteLocalServiceUtil._service = service;
}
public static TempDebitNoteLocalServiceImpl getService() {
return _service;
}
private static TempDebitNoteLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.result.service.impl.TempDebitNoteLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp debit note to the database. Also notifies the appropriate model listeners.
	*
	* @param tempDebitNote the temp debit note
	* @return the temp debit note that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDebitnote fetchBymariTimeCode_itineraryNo(
			int mariTimeCode, java.lang.String itineraryNo) {
		return getService()
				.fetchBymariTimeCode_itineraryNo(mariTimeCode, itineraryNo);
	}

	public static com.fds.nsw.nghiepvu.model.TempDebitnote fetchByItineraryNo_DocumentName_DocumentYear(
			java.lang.String itineraryNo, long documentName, int documentYear) {
		return getService()
				.fetchByItineraryNo_DocumentName_DocumentYear(itineraryNo,
						documentName, documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDebitnote> findByItineraryNo(
			java.lang.String itineraryNo) {
		return getService().findByItineraryNo(itineraryNo);
	}

	public static long countByItineraryNo(java.lang.String itineraryNo) {
		return getService().countByItineraryNo(itineraryNo);
	}



	public static com.fds.nsw.nghiepvu.model.TempDebitnote addTempDebitNote(
		com.fds.nsw.nghiepvu.model.TempDebitnote tempDebitNote)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempDebitNote(tempDebitNote);
	}

	/**
	* Creates a new temp debit note with the primary key. Does not add the temp debit note to the database.
	*
	* @param id the primary key for the new temp debit note
	* @return the new temp debit note
	*/
	public static com.fds.nsw.nghiepvu.model.TempDebitnote createTempDebitNote(
		long id) {
		return getService().createTempDebitNote(id);
	}

	/**
	* Deletes the temp debit note with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp debit note
	* @throws PortalException if a temp debit note with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempDebitNote(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempDebitNote(id);
	}

	/**
	* Deletes the temp debit note from the database. Also notifies the appropriate model listeners.
	*
	* @param tempDebitNote the temp debit note
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempDebitNote(
		com.fds.nsw.nghiepvu.model.TempDebitnote tempDebitNote)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempDebitNote(tempDebitNote);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempDebitnote fetchTempDebitNote(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempDebitNote(id);
	}

	/**
	* Returns the temp debit note with the primary key.
	*
	* @param id the primary key of the temp debit note
	* @return the temp debit note
	* @throws PortalException if a temp debit note with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDebitnote getTempDebitNote(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDebitNote(id);
	}

	

	/**
	* Returns a range of all the temp debit notes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp debit notes
	* @param end the upper bound of the range of temp debit notes (not inclusive)
	* @return the range of temp debit notes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDebitnote> getTempDebitNotes(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDebitNotes(start, end);
	}

	/**
	* Returns the number of temp debit notes.
	*
	* @return the number of temp debit notes
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempDebitNotesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDebitNotesCount();
	}

	/**
	* Updates the temp debit note in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempDebitNote the temp debit note
	* @return the temp debit note that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDebitnote updateTempDebitNote(
		com.fds.nsw.nghiepvu.model.TempDebitnote tempDebitNote)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempDebitNote(tempDebitNote);
	}

	/**
	* Updates the temp debit note in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempDebitNote the temp debit note
	* @param merge whether to merge the temp debit note with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp debit note that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDebitnote updateTempDebitNote(
		com.fds.nsw.nghiepvu.model.TempDebitnote tempDebitNote, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempDebitNote(tempDebitNote, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.TempDebitnote doTempDebitNote(
		long documentName, int documentYear, java.lang.String corporationid,
		java.lang.String debitnotenumber, java.lang.String division,
		java.lang.String financialaccountant, java.util.Date fromdate,
		int markasdeleted, java.lang.String organization, int paymenttype,
		java.lang.String reportby, java.util.Date reportdate,
		java.util.Date todate, double totalpayment,
		java.lang.String transactionid, java.lang.String mariTimeCode,
		java.lang.String currency, java.lang.String itineraryNo,
		java.lang.String description
	) {
		return getService()
				   .doTempDebitNote(documentName, documentYear, corporationid,
			debitnotenumber, division, financialaccountant, fromdate,
			markasdeleted, organization, paymenttype, reportby, reportdate,
			todate, totalpayment, transactionid, mariTimeCode, currency,
						   itineraryNo, description
				   );
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDebitnote> findByMarkasdeletedSearch(
		int mariTimeCode, int markasdeleted, java.lang.String documentName,
		java.lang.String debitnotenumber, java.lang.String[] documentTypeCode,
		int start, int end) {
		return getService()
				   .findByMarkasdeletedSearch(mariTimeCode, markasdeleted,
			documentName, debitnotenumber, documentTypeCode, start, end);
	}

	public static long countByMarkasdeletedSearch(int mariTimeCode,
		int markasdeleted, java.lang.String documentName,
		java.lang.String debitnotenumber, java.lang.String[] documentTypeCode) {
		return getService()
				   .countByMarkasdeletedSearch(mariTimeCode, markasdeleted,
			documentName, debitnotenumber, documentTypeCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDebitnote> findByMarkasdeleted(
		int markasdeleted, int mariTimeCode,
		java.lang.String[] documentTypeCode, int start, int end) {
		return getService()
				   .findByMarkasdeleted(markasdeleted, mariTimeCode,
			documentTypeCode, start, end);
	}

	public static long countByMarkasdeleted(int markasdeleted,
		int mariTimeCode, java.lang.String[] documentTypeCode) {
		return getService()
				   .countByMarkasdeleted(markasdeleted, mariTimeCode,
			documentTypeCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDebitnote> findByMarkasdeletedAll(
		int markasdeleted, int start, int end) {
		return getService().findByMarkasdeletedAll(markasdeleted, start, end);
	}

	public static long countByMarkasdeletedAll(int markasdeleted) {
		return getService().countByMarkasdeletedAll(markasdeleted);
	}

	public static com.fds.nsw.nghiepvu.model.TempDebitnote findByDocumentNameAnddocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findByDocumentNameAnddocumentYear(documentName, documentYear);
	}

	public static com.fds.nsw.nghiepvu.model.TempDebitnote getByNumberDebit(
		java.lang.String debitnotenumber) {
		return getService().getByNumberDebit(debitnotenumber);
	}

	public static com.fds.nsw.nghiepvu.model.TempDebitnote getDebitNoteByTransactionId(
		java.lang.String transactionId) {
		return getService().getDebitNoteByTransactionId(transactionId);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}