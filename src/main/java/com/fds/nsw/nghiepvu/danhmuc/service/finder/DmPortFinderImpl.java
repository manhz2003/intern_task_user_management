package com.fds.nsw.nghiepvu.danhmuc.service.finder;

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
import com.fds.nsw.nghiepvu.model.DmPort;
@Service
@Slf4j
public class DmPortFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmPort> queryFactory;

	

	// update by TrungNT -> add param portType
	public List<DmPort> findPorts(String portName, String isDelete,
			String portCodeGroup, String portType, String stateCode, int start,
			int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port WHERE 1 = 1 ");
			if (Validator.isNotNull(portName) && !portName.isEmpty()) {
				query.append(" AND PortName LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}
			if (Validator.isNotNull(portCodeGroup) && !portCodeGroup.isEmpty()) {
				portCodeGroup = portCodeGroup.replace(",", "', '");
				query.append(" AND PortCode IN ('" + portCodeGroup + "')");
			}

			if (Validator.isNotNull(portType)) {
				query.append(" AND PortType= :portType");
			}

			if (Validator.isNotNull(stateCode) && !stateCode.isEmpty()) {
				query.append(" AND StateCode= :stateCode");
			}
			query.append(" ORDER BY PortOrder");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(DmPort.class).build();
			

			if (Validator.isNotNull(portName) && !portName.isEmpty()) {
				builder.appendNamedParameterMap("portName","%" + portName + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			if (Validator.isNotNull(portType)) {
				builder.appendNamedParameterMap("portType",portType);
			}
			if (Validator.isNotNull(stateCode) && !stateCode.isEmpty()) {
				builder.appendNamedParameterMap("stateCode",stateCode);
			}

			log.info("=========>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			return (List<DmPort>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countPorts(String portName, String isDelete,
			String portCodeGroup, String portType, String stateCode) throws SystemException { long count = 0; try {

			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM dm_port WHERE 1 = 1");

			if (Validator.isNotNull(portName) && !portName.isEmpty()) {
				query.append(" AND PortName LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}
			if (Validator.isNotNull(portCodeGroup) && !portCodeGroup.isEmpty()) {
				portCodeGroup = portCodeGroup.replace(",", "', '");
				query.append(" AND PortCode IN ('" + portCodeGroup + "')");
			}

			if (Validator.isNotNull(portType)) {
				query.append(" AND PortType= :portType");
			}
			if (Validator.isNotNull(stateCode) && !stateCode.isEmpty()) {
				query.append(" AND StateCode= :stateCode");
			}
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();
			

			if (Validator.isNotNull(portName) && !portName.isEmpty()) {
				builder.appendNamedParameterMap("portName","%" + portName + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			if (Validator.isNotNull(portType)) {
				builder.appendNamedParameterMap("portType",portType);
			}
			if (Validator.isNotNull(stateCode) && !stateCode.isEmpty()) {
				builder.appendNamedParameterMap("stateCode",stateCode);
			}
			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}
}
