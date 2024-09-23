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

import java.util.*;

import com.fds.flex.common.ultility.GetterUtil;
import com.fds.flex.common.utility.string.StringUtil;
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
import com.fds.nsw.nghiepvu.model.VmaTransactionType;
@Service
@Slf4j
public class VmaTransactionTypeFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaTransactionType> queryFactory;

	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<String> queryFactory2;

	public List<Integer> checkExistTransactionType(String portofAuthority,
			String shipAgencyCode) {
		String sql = "SELECT vma_transaction_type.TransactionTypeCode FROM vma_transaction_type WHERE vma_transaction_type.TransactionTypeCode NOT IN (SELECT vma_transaction_balance.TransactionTypeCode FROM vma_transaction_balance WHERE vma_transaction_balance.ShipAgencyCode = '"
				+ shipAgencyCode + "')";

		if (Validator.isNotNull(portofAuthority)) {
			sql += " AND portofAuthority = '" + portofAuthority + "'";
		}

		
		List<Integer> transactionTypes = new ArrayList<Integer>();

		try {
			

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

			Iterator<String> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					String transactionType = itr.next();
					transactionTypes
							.add(GetterUtil.getInteger(transactionType));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
		}

		return transactionTypes;
	}

	public List<VmaTransactionType> findVmaTransactionTypeByTransactionTypeCodes(
			String portofAuthority, List<Integer> transactionTypeCodes) {

		if (transactionTypeCodes == null || transactionTypeCodes.isEmpty()) {
			return null;
		}

		String sql = "SELECT * from vma_transaction_type WHERE  TransactionTypeCode IN("
				+ StringUtil.merge(transactionTypeCodes) + ")";
		if (Validator.isNotNull(portofAuthority)) {
			sql += " AND portofAuthority = '" + portofAuthority + "'";
		}


		try {
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true)
					.queryString(sql).entityClass(VmaTransactionType.class).build();
			return (List<VmaTransactionType>)queryFactory.getResultList(builder);

		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} finally {
		}
	}

	public List<VmaTransactionType> findVmaTransactionType(Class<?> clazz,
			String className, String sql, int start, int end)
			throws SystemException {


		try {
			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql)
					.firstResult(start).maxResults(end - start).
					entityClass(Long.class).build();

			return (List<VmaTransactionType>) queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaTransactionType(String sql) throws SystemException { long count = 0; try {
			

			log.info("=========count vma_transaction_conversion>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

			/*  */

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}
}
