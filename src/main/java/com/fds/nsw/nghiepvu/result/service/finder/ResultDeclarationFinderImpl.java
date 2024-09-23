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

package com.fds.nsw.nghiepvu.result.service.finder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
@Service
@Slf4j
public class ResultDeclarationFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<ResultDeclaration> queryFactory;
	

	public List<ResultDeclaration> findByDocumentNameAndBusinessTypeCodeAndDocumentYear(int businessTypeCode, long documentName, int documentYear) {
		
		
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM result_declaration ");
			query.append(" WHERE DocumentName= :documentName ");
			query.append(" AND DocumentYear= :documentYear ");
			query.append(" AND BusinessTypeCode= :businessTypeCode ");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(ResultDeclaration.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			builder.appendNamedParameterMap("businessTypeCode",businessTypeCode);

			// execute the query and return a list from the db
			
			return (List<ResultDeclaration>)queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			
		}
		return new ArrayList<ResultDeclaration>();
	}
	
	public List<ResultDeclaration> findResultDeclarationByDocumentNameAndDocumentYearOrderByBusiness(
			long documentName, int documentYear) throws SystemException {
		
		
		List<ResultDeclaration> allResultDeclaration = null;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM result_declaration ");
			query.append(" WHERE DocumentName= :documentName ");
			query.append(" AND DocumentYear= :documentYear ");
			query.append(" order by BusinessOrder asc");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(ResultDeclaration.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);

			// execute the query and return a list from the db
			
			allResultDeclaration = (List<ResultDeclaration>)queryFactory.getResultList(builder);
			

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return allResultDeclaration;
	}
	
	public List<ResultDeclaration> findResultDeclarationByDocumentNameAndDocumentYearNcQcReport(
			long documentName, int documentYear) throws SystemException {
		
		
		List<ResultDeclaration> allResultDeclaration = null;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM result_declaration ");
			query.append(" WHERE DocumentName= :documentName ");
			query.append(" AND DocumentYear= :documentYear ");
			query.append(" and businessTypeCode in (40,42) ");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(ResultDeclaration.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);

			// execute the query and return a list from the db
			
			allResultDeclaration = (List<ResultDeclaration>)queryFactory.getResultList(builder);
			

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return allResultDeclaration;
	}
	
	public List<ResultDeclaration> findResultDeclarationByDocumentNameAndDocumentYearXcReport(
			long documentName, int documentYear) throws SystemException {
		
		
		List<ResultDeclaration> allResultDeclaration = null;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM result_declaration ");
			query.append(" WHERE DocumentName= :documentName ");
			query.append(" AND DocumentYear= :documentYear ");
			query.append(" and businessTypeCode =31 ");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(ResultDeclaration.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);

			// execute the query and return a list from the db
			
			allResultDeclaration = (List<ResultDeclaration>)queryFactory.getResultList(builder);
			

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return allResultDeclaration;
	}
	
	public List<ResultDeclaration> findResultDeclarationByDocumentNameAndDocumentYearNXDoiChieu(
			long documentName, int documentYear) throws SystemException {
		
		
		List<ResultDeclaration> allResultDeclaration = null;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM result_declaration ");
			query.append(" WHERE DocumentName= :documentName ");
			query.append(" AND DocumentYear= :documentYear ");
			query.append(" and businessTypeCode in (10,50,51,53) ");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(ResultDeclaration.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);

			// execute the query and return a list from the db
			
			allResultDeclaration = (List<ResultDeclaration>)queryFactory.getResultList(builder);
			

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return allResultDeclaration;
	}
	
	public List<ResultDeclaration> findResultDeclarationByDocumentNameAndDocumentYearQCDoiChieu(
			long documentName, int documentYear) throws SystemException {
		
		
		List<ResultDeclaration> allResultDeclaration = null;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM result_declaration ");
			query.append(" WHERE DocumentName= :documentName ");
			query.append(" AND DocumentYear= :documentYear ");
			query.append(" and businessTypeCode in (10,50,51,52,53) ");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(ResultDeclaration.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);

			// execute the query and return a list from the db
			
			allResultDeclaration = (List<ResultDeclaration>)queryFactory.getResultList(builder);
			

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return allResultDeclaration;
	}
}
