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
import com.fds.nsw.nghiepvu.model.DmMaritime;
@Service
@Slf4j
public class DmMaritimeFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmMaritime> queryFactory;
	

	public List<DmMaritime> getAll() throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_maritime ");
			query.append("ORDER BY CONVERT(CityCode USING utf8) COLLATE utf8_polish_ci");

			log.debug("===getAll===" + query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(DmMaritime.class).build();

			
			// builder.appendNamedParameterMap("type",type);

			return (List<DmMaritime>)  queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}


	public DmMaritime getFirstMaritime() throws SystemException {
		try {
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_maritime ");
			query.append("ORDER BY ID ASC LIMIT 1");

			log.debug("===getFirstMaritime===" + query.toString());
			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(query.toString())
					.build();

			return (DmMaritime)queryFactory.getResultList(builder).get(0);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}

	public DmMaritime getLastMaritime() throws SystemException {
		try {
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_maritime ");
			query.append("ORDER BY ID DESC LIMIT 1");

			log.debug("===getLastMaritime===" + query.toString());
			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(query.toString())
					.build();

			return (DmMaritime)queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}

	public List<DmMaritime> findDanhSachDmMaritime(String maritimeCode,
												   int start, int end) throws SystemException {
		try {
			StringBuilder query = new StringBuilder();

			String sql = "SELECT * FROM dm_maritime WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}

			query.append(" ORDER BY CONVERT(maritimeReference USING utf8) COLLATE utf8_polish_ci ");

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(DmMaritime.class).build();


			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());;
			}

			return (List<DmMaritime>) queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}


	public int countDanhSachDmMaritime(String maritimeCode, int start, int end)
			throws SystemException {
		int count = 0;
		try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT Count(id) as total  FROM dm_maritime WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(DmMaritime.class).build();
			

			

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

	public List<DmMaritime> findMaritimes(String maritimeCode, String isDelete,
			int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT * FROM dm_maritime WHERE 1=1 ";

			if (Validator.isNotNull(isDelete) && isDelete.trim().length() > 0) {
				query.append(" AND isDelete= :isDelete ");
			}
			if (Validator.isNotNull(maritimeCode) && !maritimeCode.isEmpty()
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				maritimeCode = maritimeCode.replace(",", "', '");
				query.append(" AND MaritimeCode IN ('" + maritimeCode + "')");
			}

			query.append(" ORDER BY CONVERT(maritimeReference USING utf8) COLLATE utf8_polish_ci ");

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(DmMaritime.class).build();

			log.info("=========find DANH SACH ===" + sql);
			
			
			if (Validator.isNotNull(isDelete) && isDelete.trim().length() > 0) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			return (List<DmMaritime>)  queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public int countMaritimes(String maritimeCode, String isDelete)
			throws SystemException {
		int count = 0;
		try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT Count(id) as total  FROM dm_maritime WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode) && !maritimeCode.isEmpty()
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				maritimeCode = maritimeCode.replace(",", "', '");
				query.append(" AND MaritimeCode IN ('" + maritimeCode + "')");
			}
			if (Validator.isNotNull(isDelete) && isDelete.trim().length() > 0) {
				query.append(" AND isDelete= :isDelete ");
			}

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();
			

			

			if (Validator.isNotNull(isDelete) && isDelete.trim().length() > 0) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}
			
			count = (Integer) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}
}
