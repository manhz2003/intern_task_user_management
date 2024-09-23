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
import com.fds.nsw.nghiepvu.model.DmRepresentative;
@Service
@Slf4j
public class DmRepresentativeFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmRepresentative> queryFactory;

	public List<DmRepresentative> findDanhSachDmRepresentative(
			String maritimeCode, int start, int end) throws SystemException {
		try {
			StringBuilder query = new StringBuilder();

			String sql = "SELECT * FROM dm_representative WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}

			query.append(" ORDER BY cast(MaritimeCode as unsigned) ASC , RepLevel, CONVERT(RepCode USING utf8) COLLATE utf8_polish_ci");

			sql = sql + query.toString();
			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql)
					.firstResult(start).maxResults(end - start).entityClass(DmRepresentative.class).build();
			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}

			return (List<DmRepresentative>) queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}


	public List<DmRepresentative> findDmRepresentativeByMaritimeCode(
			String maritimeCode) throws SystemException {
		try {
			StringBuilder query = new StringBuilder();

			String sql = "SELECT * FROM dm_representative WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}

			query.append(" ORDER BY cast(MaritimeCode as unsigned) ASC , RepLevel, CONVERT(RepCode USING utf8) COLLATE utf8_polish_ci");

			sql = sql + query.toString();

			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(DmRepresentative.class).build();
			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}

			return (List<DmRepresentative>)queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}

	public List<DmRepresentative> getAll() throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_representative ");
			query.append("ORDER BY cast(MaritimeCode as unsigned) ASC , RepLevel, CONVERT(RepCode USING utf8) COLLATE utf8_polish_ci");

			log.debug("===getAll===" + query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(DmRepresentative.class).build();

			
			// builder.appendNamedParameterMap("type",type);

			return (List<DmRepresentative>)  queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public int countDanhSachDmRepresentative(String maritimeCode) throws SystemException { int count = 0; try {
			

			StringBuilder query = new StringBuilder();

			String sql = "SELECT Count(id) as total FROM dm_representative WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();
			

			

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}

			
			count = (Integer) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	public List<DmRepresentative> findDmRepresentatives(String maritimeCode,
			String repNameVN, int repLevel, String isDelete,
			String repCodeGroup, int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT * FROM dm_representative WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}
			if (Validator.isNotNull(repNameVN) && repNameVN.trim().length() > 0) {
				query.append(" AND repNameVN like '%" + repNameVN.trim()
						+ "%' ");
			}
			if (Validator.isNotNull(repLevel) && repLevel != 0) {
				query.append(" AND repLevel= :repLevel");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(repCodeGroup) && repCodeGroup.equals("")) {
				repCodeGroup = repCodeGroup.replace(",", "', '");
				query.append(" AND RepCode IN ('" + repCodeGroup + "')");
			}
			query.append(" ORDER BY cast(MaritimeCode as unsigned) ASC , RepLevel, CONVERT(RepCode USING utf8) COLLATE utf8_polish_ci");

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(DmRepresentative.class).build();

			log.info("=========find DANH SACH ===" + sql);
			

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(repLevel) & repLevel != 0) {
				builder.appendNamedParameterMap("repLevel",repLevel);
			}
			if (Validator.isNotNull(isDelete) & !isDelete.equals("")) {
				int isDeleteTemp = Integer.valueOf(isDelete);
				builder.appendNamedParameterMap("isDeleteTemp",isDeleteTemp);
			}

			return (List<DmRepresentative>)  queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countDmRepresentatives(String maritimeCode, String repNameVN,
			int repLevel, String isDelete, String repCodeGroup) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT count(*) AS total FROM dm_representative WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}
			if (Validator.isNotNull(repNameVN) && repNameVN.trim().length() > 0) {
				query.append(" AND repNameVN like '%" + repNameVN.trim()
						+ "%' ");
			}
			if (Validator.isNotNull(repLevel) && repLevel != 0) {
				query.append(" AND repLevel= :repLevel");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(repCodeGroup) && repCodeGroup.equals("")) {
				repCodeGroup = repCodeGroup.replace(",", "', '");
				query.append(" AND RepCode IN ('" + repCodeGroup + "')");
			}
			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();
			

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(repLevel) & repLevel != 0) {
				builder.appendNamedParameterMap("repLevel",repLevel);
			}
			if (Validator.isNotNull(isDelete) & !isDelete.equals("")) {
				int isDeleteTemp = Integer.valueOf(isDelete);
				builder.appendNamedParameterMap("isDeleteTemp",isDeleteTemp);
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}
}
