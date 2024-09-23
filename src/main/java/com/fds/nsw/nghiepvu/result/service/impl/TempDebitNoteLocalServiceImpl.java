

package com.fds.nsw.nghiepvu.result.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.exception.PortalException;
import org.springframework.stereotype.Service;import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempDocumentFinderImpl;
import com.fds.nsw.nghiepvu.result.service.persistence.TempDebitnotePersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;


/**
 * The implementation of the temp debit note local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.result.service.TempDebitNoteLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.result.service.base.TempDebitNoteLocalServiceBaseImpl
 * @see vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil
 */
@Service
@Slf4j
public class TempDebitNoteLocalServiceImpl{
	@Autowired
	TempDebitnotePersistenceImpl persistence;

	@Autowired
	TempDocumentFinderImpl finder;

	public TempDebitnote doTempDebitNote(long documentName, int documentYear, String corporationid,
										 String debitnotenumber, String division, String financialaccountant, Date fromdate, int markasdeleted,
										 String organization, int paymenttype, String reportby, Date reportdate, Date todate, double totalpayment,
										 String transactionid, String mariTimeCode, String currency,
										 String itineraryNo, String description
	) {

		TempDebitnote ett = null;

		try {
			// Edit by Dungnv
			List<TempDebitnote> list = new ArrayList<TempDebitnote>();
			try {
				if (Validator.isNotNull(itineraryNo) && (documentName == 0 || documentYear == 0) ) {
					ett = TempDebitNoteLocalServiceUtil
							.fetchByItineraryNo_DocumentName_DocumentYear(itineraryNo,
									documentName, documentYear);
				} else {
					list = persistence
							.findByDocumentNameAnddocumentYear(documentName,
									documentYear);
				}
			} catch (Exception ex) {
				// nothing to do
			}
			// ======

			if (Validator.isNotNull(list) && !list.isEmpty()) {
				ett = list.get(0);
			} else {
				ett = persistence.create(CounterLocalServiceUtil
						.increment(TempDebitnote.class.getName()));


				ett.setCorporationid(corporationid);
				ett.setDebitnotenumber(debitnotenumber);
				ett.setDivision(division);
				ett.setDocumentName(documentName);
				ett.setDocumentYear(documentYear);
				ett.setFinancialaccountant(financialaccountant);
				ett.setFromdate(fromdate);
				ett.setMarkasdeleted(markasdeleted);
				ett.setOrganization(organization);
				ett.setPaymenttype(paymenttype);
				ett.setReportby(reportby);
				ett.setReportdate(reportdate);
				ett.setTodate(todate);
				ett.setTotalpayment(BigDecimal.valueOf(totalpayment));
				ett.setTransactionid(transactionid);
				ett.setMariTimeCode(mariTimeCode);
				ett.setItineraryNo(itineraryNo);
				ett.setDescription(description);


				if (Validator.isNotNull(currency)) {
					ett.setCurrency(currency);
				} else {
					ett.setCurrency("VND"); // default currency
				}

				// Edit by Dungnv
				try {
					if(documentName > 0 && documentYear > 0){
						TempDocument tempDocument = TempDocumentLocalServiceUtil
								.findTemDocumentByDocumentNameDocumentYear(
										documentName, documentYear);

						ett.setDocumentTypeCode(tempDocument.getDocumentTypeCode());
					}else{
						ett.setDocumentTypeCode("---");
					}
				} catch (Exception e) {
					ett.setDocumentTypeCode("---");
				}

				persistence.updateImpl(ett, Boolean.TRUE);
			}
		} catch (SystemException e) {
			log.error(e.getMessage());
		}

		return ett;

	}
	
	public List<TempDebitnote> findByMarkasdeletedSearch(int mariTimeCode,int markasdeleted, String documentName, String debitnotenumber, String[] documentTypeCode, int start, int end) {
		List<TempDebitnote> results = new ArrayList<TempDebitnote>();
				
		try {
			if (Validator.isNumber(documentName)) {
				results.addAll(persistence.findBydocumentNameSearch(mariTimeCode, markasdeleted, Long.valueOf(documentName), documentTypeCode));
			}
			results.addAll(persistence.findBydebitnotenumberSearch(mariTimeCode, markasdeleted, debitnotenumber, documentTypeCode));
			
			return results;
		} catch (Exception e) {
			return new ArrayList<TempDebitnote>();
		}
	}
	
	public long countByMarkasdeletedSearch(int mariTimeCode,int markasdeleted, String documentName, String debitnotenumber, String[] documentTypeCode) {
		long total = 0;
		try {
			total = persistence.countBydocumentNameSearch(mariTimeCode, markasdeleted, Long.valueOf(documentName), documentTypeCode);
			
			total = total + persistence.countBydebitnotenumberSearch(mariTimeCode, markasdeleted, debitnotenumber, documentTypeCode);
			
			return total;
		} catch (Exception e) {
			return 0;
		}
	}
	public TempDebitnote fetchBymariTimeCode_itineraryNo(int mariTimeCode,
														 String itineraryNo) {
		try {
			return persistence.fetchBymariTimeCode_itineraryNo(
					mariTimeCode, itineraryNo);
		} catch (Exception e) {
			return null;
		}
	}

	public TempDebitnote fetchByItineraryNo_DocumentName_DocumentYear(
			String itineraryNo, long documentName, int documentYear) {
		try {
			return persistence
					.fetchByitineraryNo_documentName_documentYear(itineraryNo,
							documentName, documentYear);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public List<TempDebitnote> findByItineraryNo(String itineraryNo) {
		try {
			return persistence.findByItineraryNo(itineraryNo);
		} catch (SystemException e) {
			return new ArrayList<TempDebitnote>();
		}
	}
	public long countByItineraryNo(String itineraryNo) {
		try {
			return persistence
					.countByItineraryNo(itineraryNo);
		} catch (SystemException e) {
			return 0;
		}
	}


	public List<TempDebitnote> findByMarkasdeleted(int markasdeleted, int mariTimeCode, String[] documentTypeCode, int start, int end) {
		try {
			return persistence.findByMarkasdeleted(markasdeleted, mariTimeCode, documentTypeCode, start, end);
		} catch (Exception e) {
			return new ArrayList<TempDebitnote>();
		}
	}
	public long countByMarkasdeleted(int markasdeleted, int mariTimeCode, String[] documentTypeCode) {
		try {
			return persistence.countByMarkasdeleted(markasdeleted, mariTimeCode, documentTypeCode);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public List<TempDebitnote> findByMarkasdeletedAll(int markasdeleted, int start, int end) {
		try {
			return persistence.findByMarkasdeletedAll(markasdeleted, start, end);
		} catch (Exception e) {
			return new ArrayList<TempDebitnote>();
		}
	}
	public long countByMarkasdeletedAll(int markasdeleted) {
		try {
			return persistence.countByMarkasdeletedAll(markasdeleted);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public TempDebitnote findByDocumentNameAnddocumentYear(long documentName, int documentYear) {
		List<TempDebitnote> list;
		try {
			list = persistence.findByDocumentNameAnddocumentYear(documentName, documentYear);
			if (Validator.isNotNull(list) && list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	public TempDebitnote getByNumberDebit(String debitnotenumber) {
		try {
			return persistence.fetchByF_debitnotenumber(debitnotenumber);
		} catch (Exception e) {
			return new TempDebitnote();
		}
	}
	
	public TempDebitnote getDebitNoteByTransactionId(String transactionId) {
		TempDebitnote debitNote = null;
		
		if(Validator.isNotNull(transactionId)) {
			try {
				debitNote = persistence.fetchByTransactionId(transactionId);
				
				if(debitNote != null) {
					transactionId = debitNote.getTransactionid();
				}
			} catch (Exception e) {
//				log.error(e.getMessage());
			}
		}
		
		return debitNote;
	}


	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil} to access the temp debit note local service.
	 */

	/**
	 * Adds the temp debit note to the database. Also notifies the appropriate model listeners.
	 *
	 * @param tempDebitNote the temp debit note
	 * @return the temp debit note that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote addTempDebitNote(TempDebitnote tempDebitNote)
			throws SystemException {

		tempDebitNote = persistence.updateImpl(tempDebitNote, false);





		return tempDebitNote;
	}

	/**
	 * Creates a new temp debit note with the primary key. Does not add the temp debit note to the database.
	 *
	 * @param id the primary key for the new temp debit note
	 * @return the new temp debit note
	 */
	public TempDebitnote createTempDebitNote(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp debit note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp debit note
	 * @throws PortalException if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempDebitNote(long id)
			throws PortalException, SystemException {
		TempDebitnote tempDebitNote = persistence.remove(id);




	}

	/**
	 * Deletes the temp debit note from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tempDebitNote the temp debit note
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempDebitNote(TempDebitnote tempDebitNote)
			throws SystemException {
		persistence.remove(tempDebitNote);




	}













	public TempDebitnote fetchTempDebitNote(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp debit note with the primary key.
	 *
	 * @param id the primary key of the temp debit note
	 * @return the temp debit note
	 * @throws PortalException if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote getTempDebitNote(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public TempDebitnote getPersistedModel(Serializable primaryKeyObj)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
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
	public List<TempDebitnote> getTempDebitNotes(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp debit notes.
	 *
	 * @return the number of temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempDebitNotesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp debit note in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempDebitNote the temp debit note
	 * @return the temp debit note that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote updateTempDebitNote(TempDebitnote tempDebitNote)
			throws SystemException {
		return updateTempDebitNote(tempDebitNote, true);
	}

	/**
	 * Updates the temp debit note in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempDebitNote the temp debit note
	 * @param merge whether to merge the temp debit note with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the temp debit note that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote updateTempDebitNote(TempDebitnote tempDebitNote,
											 boolean merge) throws SystemException {

		tempDebitNote = persistence.updateImpl(tempDebitNote, merge);





		return tempDebitNote;
	}

	
}