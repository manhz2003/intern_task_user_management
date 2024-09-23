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

package com.fds.nsw.nghiepvu.noticeandmessage.service.finder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.dao.orm.BasePersistence;
import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.util.ConvertUtil;
import com.fds.nsw.nghiepvu.util.FormatData;

import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
@Service
@Slf4j
public class TempNoTiceShipMessageFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempNoticeShipMessage> queryFactory;
	
	
	
	public TempNoticeShipMessage findTempNoticeShipMessageByRequestCode(String requestCode) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT temp_notice_ship_message.* FROM temp_notice_ship_message ");
			query.append(" WHERE RequestCode= :requestCode");
			
			log.debug("=========findTempNoTiceShipMessageByRequestCode========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempNoticeShipMessage.class).build();
			
			builder.appendNamedParameterMap("requestCode",requestCode);
			
			// execute the query and return a list from the db
			return (TempNoticeShipMessage) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
	
	public TempNoticeShipMessage findTempNoticeShipMessageXbByRequestCode(String requestCode) throws SystemException {
		
		
		TempNoticeShipMessage result = null;
		List<ResultDeclaration> allResultDeclaration = null;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT temp_notice_ship_message.* FROM temp_notice_ship_message ");
			query.append(" WHERE RequestCode= :requestCode");
			query.append(" and noticeShipType = 2");
			
			log.debug("=========findTempNoTiceShipMessageXbByRequestCode========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempNoticeShipMessage.class).build();
			
			builder.appendNamedParameterMap("requestCode",requestCode);
			
			// execute the query and return a list from the db
			result = (TempNoticeShipMessage) queryFactory.getSingleResult(builder);
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}
	
	public List<TempNoticeShipMessage> findBydocumentNameAndDocumentYearAndNoticeShipType(long documentName, int documentYear, String noticeShipType) {
		
		
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT temp_notice_ship_message.* FROM temp_notice_ship_message ");
			query.append(" WHERE DocumentName= :documentName");
			query.append(" AND DocumentYear= :documentYear");
			query.append(" AND NoticeShipType= :noticeShipType order by ConfirmTime desc");
			
			log.debug("=========findBydocumentNameAndDocumentYearAndNoticeShipType========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempNoticeShipMessage.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			builder.appendNamedParameterMap("noticeShipType",noticeShipType);
			
			return (List<TempNoticeShipMessage>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<TempNoticeShipMessage>();
	}
	
	public List<TempNoticeShipMessage> findAllTempNoTiceShipMessageXbByRequestCode(String requestCode) throws SystemException {
		
		
		List<TempNoticeShipMessage> result = null;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT temp_notice_ship_message.* FROM temp_notice_ship_message ");
			query.append(" WHERE RequestCode= :requestCode");
			query.append(" and noticeShipType = 2");
			
			log.debug("=========findTempNoTiceShipMessageXbByRequestCode========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempNoticeShipMessage.class).build();
			
			builder.appendNamedParameterMap("requestCode",requestCode);
			
			result = (List<TempNoticeShipMessage>) queryFactory.getResultList(builder);
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}
	
	public List<TempNoticeShipMessage> findAllTempNoTiceShipMessageTbByRequestCode(String requestCode) throws SystemException {
		
		
		List<TempNoticeShipMessage> result = null;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT temp_notice_ship_message.* FROM temp_notice_ship_message ");
			query.append(" WHERE RequestCode= :requestCode");
			query.append(" and noticeShipType = 1");
			
			log.debug("=========findTempNoTiceShipMessageXbByRequestCode========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempNoticeShipMessage.class).build();
			
			builder.appendNamedParameterMap("requestCode",requestCode);
			
			result = (List<TempNoticeShipMessage>) queryFactory.getResultList(builder);
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}
	
	public TempNoticeShipMessage findTempNoticeShipMessageTbByRequestCode(String requestCode) throws SystemException {
		
		
		TempNoticeShipMessage result = null;
		List<ResultDeclaration> allResultDeclaration = null;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT temp_notice_ship_message.* FROM temp_notice_ship_message ");
			query.append(" WHERE RequestCode= :requestCode");
			query.append(" and noticeShipType = 1");
			
			log.debug("=========findTempNoTiceShipMessageXbByRequestCode========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempNoticeShipMessage.class).build();
			
			builder.appendNamedParameterMap("requestCode",requestCode);
			
			// execute the query and return a list from the db
			result = (TempNoticeShipMessage) queryFactory.getSingleResult(builder);
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}
	
	public List<TempNoticeShipMessage> findByDocumentNameAndDocumentYearAndNoticeShipTypeOrderByColumn(long documentName, int documentYear,
			String noticeShipType, String orderByColumn, boolean ascOrdesc) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT temp_notice_ship_message.* FROM temp_notice_ship_message ");
			query.append(" WHERE DocumentName= :documentName");
			query.append(" AND DocumentYear= :documentYear");
			query.append(" AND NoticeShipType= :noticeShipType");
			query.append(" order by ConfirmTime ");
			
			if (ascOrdesc == true) {
				query.append(" asc");
			} else if (ascOrdesc == false) {
				query.append(" desc");
			}
			
			log.debug("=========findBy_DocumentName_And_DocumentYear_And_NoticeShipType_OrderByColumn========" + query.toString());
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempNoticeShipMessage.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			builder.appendNamedParameterMap("noticeShipType",noticeShipType);
			
			return (List<TempNoticeShipMessage>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<TempNoticeShipMessage>();
	}
	
	/**
	 * noticeShipType = 1 ThongBao. noticeShipType = 2 XacBao
	 */
	
	public TempNoticeShipMessage getXacBaoLastByDocumentNameAndDocumentYear(long documentName, long documentYear) throws SystemException {
		
		
		TempNoticeShipMessage result = null;
		try {
			
			StringBuilder maxXacBao = new StringBuilder();
			maxXacBao.append("select max(ConfirmTime) from temp_notice_ship_message where ");
			maxXacBao.append("DocumentName = '" + documentName + "' ");
			maxXacBao.append("and DocumentYear = '" + documentYear + "' ");
			maxXacBao.append("and noticeShipType = 2");
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT temp_notice_ship_message.* FROM temp_notice_ship_message ");
			query.append(" WHERE DocumentName= :documentName");
			query.append(" AND DocumentYear= :documentYear");
			query.append(" and noticeShipType = 2");
			query.append(" and ConfirmTime = (" + maxXacBao.toString() + ")");
			
			log.debug("==getXacBaoLastByDocumentNameAndDocumentYear==" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempNoticeShipMessage.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			List<TempNoticeShipMessage> lstResult = (List<TempNoticeShipMessage>) queryFactory.getResultList(builder);
			if (lstResult != null && lstResult.size() > 0) {
				result = lstResult.get(0);
			}
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}
	
	/**
	 * noticeShipType = 1 ThongBao. noticeShipType = 2 XacBao
	 */
	public TempNoticeShipMessage getThongBaoLastByDocumentNameAndDocumentYear(long documentName, long documentYear) throws SystemException {
		
		
		TempNoticeShipMessage result = null;
		try {
			
			StringBuilder maxXacBao = new StringBuilder();
			maxXacBao.append("select max(ConfirmTime) from temp_notice_ship_message where ");
			maxXacBao.append("DocumentName = '" + documentName + "' ");
			maxXacBao.append("and DocumentYear = '" + documentYear + "' ");
			maxXacBao.append("and noticeShipType = 1");
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT temp_notice_ship_message.* FROM temp_notice_ship_message ");
			query.append(" WHERE DocumentName= :documentName");
			query.append(" AND DocumentYear= :documentYear");
			query.append(" and noticeShipType = 1");
			query.append(" and ConfirmTime = (" + maxXacBao.toString() + ")");
			
			log.debug("=========findThongBaoLastByDocumentNameAndDocumentYear========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempNoticeShipMessage.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			List<TempNoticeShipMessage> lstResult = (List<TempNoticeShipMessage>) queryFactory.getResultList(builder);
			if (lstResult != null && lstResult.size() > 0) {
				result = lstResult.get(0);
			}
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}
	
}
