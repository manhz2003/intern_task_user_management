/**
 * 
 */
package com.fds.nsw.nghiepvu.noticeandmessage.service.finder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest;
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
import com.fds.nsw.nghiepvu.model.InterfaceRequest;
@Service
@Slf4j
public class InterfaceRequestFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<InterfaceRequest> queryFactory;
	
	
	
	public InterfaceRequest findInterfaceRequestByRequestCode(String requestCode) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM INTERFACE_REQUEST ");
			query.append(" WHERE RequestCode= :requestCode");
			
			log.debug("=========findInterfaceRequestByRequestCode========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(InterfaceRequest.class).build();

			builder.appendNamedParameterMap("requestCode",requestCode);
			
			// execute the query and return a list from the db
			List<InterfaceRequest> lst = (List<InterfaceRequest>) queryFactory.getResultList(builder);
			if (lst != null && lst.size() > 0) {
				return lst.get(0);
			}
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return null;
	}
	
	public Date getRequestedDateByRequestCode(String requestCode) throws SystemException {
		Date date = null;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT RequestedDate as requestedDate FROM INTERFACE_REQUEST ");
			query.append(" WHERE RequestCode= :requestCode");
			
			log.debug("=========getRequestedDateByRequestCode========" + query.toString());
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Date.class).build();

			
			
			builder.appendNamedParameterMap("requestCode",requestCode);

			date = (Date) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return null;
	}
	
	public String getFunctionTypeByRequestCode(String requestCode) throws SystemException {
		String value = null;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT FunctionType as functionType FROM INTERFACE_REQUEST ");
			query.append(" WHERE RequestCode= :requestCode");
			
			log.debug("=========getFunctionTypeByRequestCode========" + query.toString());
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(String.class).build();

			
			
			builder.appendNamedParameterMap("requestCode",requestCode);

			value = (String) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return null;
	}
	
	public String getRemarksByRequestCode(String requestCode) throws SystemException {
		String value = null;
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT Remarks as remarks FROM INTERFACE_REQUEST ");
			query.append(" WHERE RequestCode= :requestCode");
			
			log.info("=========getRemarksByRequestCode========" + query.toString() + "requestCode  " + requestCode);
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(String.class).build();

			
			
			builder.appendNamedParameterMap("requestCode",requestCode);

			value = (String) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(e);
		} finally {
			
		}
		return null;
	}
	
	public int updateInterfaceRequest(String sql) throws SystemException {
		
		try {
			
			
			log.debug("=========updateByRequestCode========" + sql);
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(InterfaceRequest.class).build();

			int executeUpdate = queryFactory.executeUpdate(builder);
			//session.flush();
			return executeUpdate;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(e);
		} finally {
			
		}
	}
}
