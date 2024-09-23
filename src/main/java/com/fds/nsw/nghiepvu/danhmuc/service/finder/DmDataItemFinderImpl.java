package com.fds.nsw.nghiepvu.danhmuc.service.finder;

import java.util.*;

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
import com.fds.nsw.nghiepvu.model.DmDataitem;
@Service
@Slf4j
public class DmDataItemFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmDataitem> queryFactory;

	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Long> queryFactory3;
	public List<DmDataitem> getAllByDataGroupID(long datagroupid, int start,
			int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_DataItem WHERE datagroupid = :datagroupid ");
			query.append("ORDER BY CONVERT(name USING utf8) COLLATE utf8_polish_ci");
			String sql = query.toString();
			if (start >= 0) {
				int count = end - start;
				sql = sql + " limit " + start + "," + count;
			}
			log.debug("===getAll===" + query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(DmDataitem.class).build();

			
			builder.appendNamedParameterMap("datagroupid",datagroupid);

			return (List<DmDataitem>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}


	public List<DmDataitem> getAllByNode1Node2AndDataGroupID(long datagroupid,
																					 String node1, String node2, int start, int end)
			throws SystemException {
		try {
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_DataItem WHERE datagroupid = :datagroupid ");
			if (Validator.isNotNull(node1) && node1.trim().length() > 0
					&& (!node1.equalsIgnoreCase("---"))) {
				query.append(" AND node_1 = :node_1 ");
			}
			if (Validator.isNotNull(node2) && node2.trim().length() > 0
					&& (!node2.equalsIgnoreCase("---"))) {
				query.append(" AND node_2 = :node_2 ");
			}
			query.append("ORDER BY CONVERT(name USING utf8) COLLATE utf8_polish_ci");
			String sql = query.toString();
			if (start >= 0) {
				int count = end - start;
				sql = sql + " limit " + start + "," + count;
			}
			log.debug("===getAllByNode1Node2AndDataGroupID==="
					+ query.toString());
			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(DmDataitem.class).build();
			builder.appendNamedParameterMap("datagroupid",datagroupid);
			if (Validator.isNotNull(node1) && node1.trim().length() > 0
					&& (!node1.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("node_1",node1);
			}
			if (Validator.isNotNull(node2) && node2.trim().length() > 0
					&& (!node2.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("node_2",node2);
			}



			return (List<DmDataitem>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}

	public int countByNode1Node2AndDataGroupID(long datagroupid, String node1,
											   String node2, int start, int end) throws SystemException {
		try {
			StringBuilder query = new StringBuilder();

			query.append("SELECT Count(id) as total  FROM dm_DataItem WHERE datagroupid = :datagroupid ");
			if (Validator.isNotNull(node1) && node1.trim().length() > 0
					&& (!node1.equalsIgnoreCase("---"))) {
				query.append(" AND node_1 = :node_1 ");
			}
			if (Validator.isNotNull(node2) && node2.trim().length() > 0
					&& (!node2.equalsIgnoreCase("---"))) {
				query.append(" AND node_2 = :node_2 ");
			}

			String sql = query.toString();
			if (start >= 0) {
				int count = end - start;
				sql = sql + " limit " + start + "," + count;
			}
			log.debug("===countByNode1Node2AndDataGroupID==="
					+ query.toString());
			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();
			builder.appendNamedParameterMap("datagroupid",datagroupid);
			if (Validator.isNotNull(node1) && node1.trim().length() > 0
					&& (!node1.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("node_1",node1);
			}
			if (Validator.isNotNull(node2) && node2.trim().length() > 0
					&& (!node2.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("node_2",node2);
			}

			Iterator<Long> itr = queryFactory3.getResultList(builder).iterator();

			if (itr.hasNext()) {
				Long count = itr.next();
				if (count != null) {
					return count.intValue();
				}
			}
			return 0;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}

	}

	public DmDataitem getByNode2AndDataGroupID(long datagroupid, String node2)
			throws SystemException {
		try {
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_DataItem WHERE datagroupid = :datagroupid and Node_2 = :Node_2 ");
			query.append("ORDER BY CONVERT(name USING utf8) COLLATE utf8_polish_ci");
			String sql = query.toString();

			log.debug("===getByNode2AndDataGroupID===" + query.toString());

			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(DmDataitem.class).build();
			builder.appendNamedParameterMap("datagroupid",datagroupid);
			builder.appendNamedParameterMap("Node_2",node2);

			return (DmDataitem) queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}

	public List<DmDataitem> findDataItems(long dataGroupId, String name,
										  String maritimeCode, String codeGroup, String status, int start,
										  int end) throws SystemException {
		try {
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_dataitem WHERE 1 = 1");
			if (Validator.isNotNull(dataGroupId) && dataGroupId != 0) {
				query.append(" AND datagroupid = :datagroupid");
			}
			if (Validator.isNotNull(name) && !name.isEmpty()) {
				query.append(" AND name LIKE =:name");
			}
			if (Validator.isNotNull(maritimeCode) && !maritimeCode.isEmpty()
					&& !maritimeCode.equals("---")) {
				query.append(" AND node_1 = :node_1");
			}
			if (Validator.isNotNull(codeGroup) && !codeGroup.isEmpty()) {
				codeGroup = codeGroup.replace(",", "', '");
				query.append(" AND code IN ('" + codeGroup + "')");
			}
			if (Validator.isNotNull(status) && !status.isEmpty()) {
				query.append(" AND status = :status");
			}
			query.append(" ORDER BY validatedfrom DESC");
			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(query.toString())
					.firstResult(start).maxResults(end - start).entityClass(DmDataitem.class).build();

			if (Validator.isNotNull(dataGroupId) && dataGroupId != 0) {
				builder.appendNamedParameterMap("datagroupid",Long.valueOf(dataGroupId));
			}
			if (Validator.isNotNull(name) && !name.isEmpty()) {
				builder.appendNamedParameterMap("name", "%" + name + "%");
			}
			if (Validator.isNotNull(maritimeCode) && !maritimeCode.isEmpty()
					&& !maritimeCode.equals("---")) {
				builder.appendNamedParameterMap("node_1", maritimeCode);
			}
			if (Validator.isNotNull(status) && !status.isEmpty()) {
				builder.appendNamedParameterMap("status", Integer.valueOf(status));
			}

			return (List<DmDataitem>)queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}


	public long countDataItems(long dataGroupId, String name,
			String maritimeCode, String codeGroup, String status)
			throws SystemException {
		long count = 0;
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM dm_dataitem WHERE 1 = 1");
			if (Validator.isNotNull(dataGroupId) && dataGroupId != 0) {
				query.append(" AND datagroupid = :datagroupid");
			}
			if (Validator.isNotNull(name) && !name.isEmpty()) {
				query.append(" AND name LIKE :name");
			}
			if (Validator.isNotNull(maritimeCode) && !maritimeCode.isEmpty()
					&& !maritimeCode.equals("---")) {
				query.append(" AND node_1 = :maritimeCode");
			}
			if (Validator.isNotNull(codeGroup) && !codeGroup.isEmpty()) {
				codeGroup = codeGroup.replace(",", "', '");
				query.append(" AND code IN ('" + codeGroup + "')");
			}
			if (Validator.isNotNull(status) && !status.isEmpty()) {
				query.append(" AND status = :status");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();
			

			if (Validator.isNotNull(dataGroupId) && dataGroupId != 0) {
				builder.appendNamedParameterMap("dataGroupId",Long.valueOf(dataGroupId));
			}
			if (Validator.isNotNull(name) && !name.isEmpty()) {
				builder.appendNamedParameterMap("name","%" + name + "%");
			}
			if (Validator.isNotNull(maritimeCode) && !maritimeCode.isEmpty()
					&& !maritimeCode.equals("---")) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode);
			}
			if (Validator.isNotNull(status) && !status.isEmpty()) {
				builder.appendNamedParameterMap("status",Integer.valueOf(status));
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

}
