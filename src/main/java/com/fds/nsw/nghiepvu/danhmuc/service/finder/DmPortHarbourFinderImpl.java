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
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
@Service
@Slf4j
public class DmPortHarbourFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmPortHarbour> queryFactory;



	public List<DmPortHarbour> findDanhSachDmPortHarbour(String maritimeCode,
														 String portRegion, String portHarbourName, int start, int end)
			throws SystemException {
		try {
			StringBuilder query = new StringBuilder();

			String sql = "SELECT dm_port_harbour.* FROM dm_port_harbour WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND PortRegionCode= :portRegionCode ");
			}
			if (Validator.isNotNull(portRegion)
					&& portRegion.trim().length() > 0) {
				query.append(" AND PortRegion= :portRegion ");
			}

			if (Validator.isNotNull(portHarbourName)
					&& portHarbourName.trim().length() > 0) {
				query.append(" AND (PortHarbourName is null or UPPER(PortHarbourName) like '%"
						+ portHarbourName.trim().toUpperCase() + "%')");
			}

			query.append(" ORDER BY CONVERT(PortHarbourName USING utf8) COLLATE utf8_polish_ci ");

			sql = sql + query.toString();
			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql)
					.firstResult(start).maxResults(end - start).entityClass(DmPortHarbour.class).build();
			queryFactory.getResultList(builder);
			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(portRegion)
					&& portRegion.trim().length() > 0) {
				builder.appendNamedParameterMap("portRegion",portRegion.trim());
			}

			return (List<DmPortHarbour>) queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}

	public List<DmPortHarbour> getAll() throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port_harbour ");
			query.append("ORDER BY CONVERT(PortHarbourName USING utf8) COLLATE utf8_polish_ci");

			log.debug("===getAll===" + query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(DmPortHarbour.class).build();

			
			// builder.appendNamedParameterMap("type",type);

			return (List<DmPortHarbour>)  queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public List<DmPortHarbour> findPortHarbours(String maritimeCode,
			String portRegion, String portHarbourName, String isDelete,
			String portHarbourCodeGroup, int start, int end)
			throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT dm_port_harbour.* FROM dm_port_harbour WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND PortRegionCode= :portRegionCode ");
			}
			if (Validator.isNotNull(portRegion)
					&& portRegion.trim().length() > 0) {
				query.append(" AND PortRegion= :portRegion ");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete ");
			}
			if (Validator.isNotNull(portHarbourName)
					&& portHarbourName.trim().length() > 0) {
				query.append(" AND PortHarbourName like ? ");
			}
			if (Validator.isNotNull(portHarbourCodeGroup)
					&& !portHarbourCodeGroup.isEmpty()) {
				portHarbourCodeGroup = portHarbourCodeGroup
						.replace(",", "', '");
				query.append(" AND PortHarbourCode IN ('"
						+ portHarbourCodeGroup + "')");
			}

			query.append(" ORDER BY SequenceNo ");

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(DmPortHarbour.class).build();

			log.debug("=========find DANH SACH ===" + sql);
			

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(portRegion)
					&& portRegion.trim().length() > 0) {
				builder.appendNamedParameterMap("portRegion",portRegion.trim());
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}
			if (Validator.isNotNull(portHarbourName)
					&& portHarbourName.trim().length() > 0) {
				builder.appendNamedParameterMap("portHarbourName","%" + portHarbourName.trim() + "%");
			}

			return (List<DmPortHarbour>)  queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countPortHarbours(String maritimeCode, String portRegion,
			String portHarbourName, String isDelete, String portHarbourCodeGroup) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT count(*) AS total FROM dm_port_harbour WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND PortRegionCode= :portRegionCode ");
			}
			if (Validator.isNotNull(portRegion)
					&& portRegion.trim().length() > 0) {
				query.append(" AND PortRegion= :portRegion ");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete ");
			}
			if (Validator.isNotNull(portHarbourName)
					&& portHarbourName.trim().length() > 0) {
				query.append(" AND PortHarbourName like ? ");
			}
			if (Validator.isNotNull(portHarbourCodeGroup)
					&& !portHarbourCodeGroup.isEmpty()) {
				portHarbourCodeGroup = portHarbourCodeGroup
						.replace(",", "', '");
				query.append(" AND PortHarbourCode IN ('"
						+ portHarbourCodeGroup + "')");
			}

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

			

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(portRegion)
					&& portRegion.trim().length() > 0) {
				builder.appendNamedParameterMap("portRegion",portRegion.trim());
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}
			if (Validator.isNotNull(portHarbourName)
					&& portHarbourName.trim().length() > 0) {
				builder.appendNamedParameterMap("portHarbourName","%" + portHarbourName.trim() + "%");
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	public int countDanhSachDmPortHarbour(String maritimeCode,
			String portRegion, String portHarbourName) throws SystemException { int count = 0; try {
			

			StringBuilder query = new StringBuilder();

			String sql = "SELECT Count(id) as total FROM dm_port_harbour WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND PortRegionCode= :portRegionCode ");
			}
			if (Validator.isNotNull(portRegion)
					&& portRegion.trim().length() > 0) {
				query.append(" AND PortRegion= :portRegion ");
			}

			if (Validator.isNotNull(portHarbourName)
					&& portHarbourName.trim().length() > 0) {
				query.append(" AND UPPER(PortHarbourName) like '%"
						+ portHarbourName.trim().toUpperCase() + "%' ");
			}

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();
			

			log.debug("=========find DANH SACH ===" + sql);
			

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(portRegion)
					&& portRegion.trim().length() > 0) {
				builder.appendNamedParameterMap("portRegion",portRegion.trim());
			}

			
			count = (Integer) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	public DmPortHarbour getFirstPortHarbour() throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port_harbour ");
			query.append("ORDER BY ID ASC LIMIT 1");

			log.debug("===getFirstPortHarbour===" + query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(DmPortHarbour.class).build();

			
			// builder.appendNamedParameterMap("type",type);

			return (DmPortHarbour) queryFactory.getResultList(builder)
.get(0);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public DmPortHarbour getLastPortHarbour() throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port_harbour ");
			query.append("ORDER BY ID DESC LIMIT 1");

			log.debug("===getLastPortHarbour===" + query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(DmPortHarbour.class).build();

			
			// builder.appendNamedParameterMap("type",type);

			return (DmPortHarbour) queryFactory.getResultList(builder)
.get(0);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long getMaxSequenceNo(String maritimeCode, String portRegion)
			throws SystemException {
		long count = 0;
		try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT MAX(SequenceNo) AS total FROM dm_port_harbour WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND PortRegionCode= :portRegionCode ");
			}
			if (Validator.isNotNull(portRegion)
					&& portRegion.trim().length() > 0) {
				query.append(" AND PortRegion= :portRegion ");
			}
			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

			

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(portRegion)
					&& portRegion.trim().length() > 0) {
				builder.appendNamedParameterMap("portRegion",portRegion);
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

}
