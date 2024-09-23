package com.fds.nsw.nghiepvu.noticeandmessage.service.finder;

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
import com.fds.nsw.nghiepvu.model.IssueAcceptance;
@Service
@Slf4j
public class IssueAcceptanceFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<IssueAcceptance> queryFactory;

	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<String> queryFactory2;
	
	public IssueAcceptance findLastestIssueAcceptanceByPortNameAndCallSign(String nameOfShip, String callSign) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM issue_acceptance where 1 = 1 ");
			
			//query by nameofship
			if(Validator.isNotNull(nameOfShip) && nameOfShip.trim().length() > 0) {
				query.append(" AND NameOfShip like '%" + nameOfShip.trim() + "%' ");
			}
			//query by callSign
			if(Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
				query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
			}
			
			query.append("ORDER BY ApprovalDate DESC limit 1 ");
			
			String sql = query.toString();
			log.info("=========findByRequestCode========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(IssueAcceptance.class).build();
			return (IssueAcceptance) queryFactory.getSingleResult(builder);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return null;
	}
	
	public List<IssueAcceptance> findIssueAcceptanceInfo(String requestState, String maritimeCode, String portCode, String shipName,
			String callSign, String shipDateFrom, String shipDateTo, int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT iss.* FROM issue_acceptance iss ");
			query.append(" left join temp_document temp ");
			query.append(" on iss.DocumentName = temp.DocumentName ");
			query.append(" and iss.DocumentYear = temp.DocumentYear where 1=1 ");
			
			// RequestState
			if (Validator.isNotNull(requestState) && requestState.trim().length() > 0) {
				query.append(" AND iss.RequestState =" + FormatData.convertToInt(requestState));
			}
			
			// cang vu hang hai
			if (Validator.isNotNull(maritimeCode) && maritimeCode.trim().length() > 0 && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
				query.append(" AND iss.PortofAuthority = ?");
			}
			log.debug("===query.append===maritimeCode===" + maritimeCode);
			// cang bien
			if (Validator.isNotNull(portCode) && portCode.trim().length() > 0 && !portCode.trim().equalsIgnoreCase("LUA_CHON1")) {
				query.append(" AND temp.portCode = ?");
			}
			log.debug("===query.append===temp.portCode===" + portCode);
			// ten tau
			if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
				query.append(" AND iss.NameOfShip like '%" + shipName.trim() + "%' ");
			}
			// Ho hieu
			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
				query.append(" AND temp.CallSign like '%" + callSign.trim() + "%' ");
			}
			// Tu ngay
			if (Validator.isNotNull(shipDateFrom) && shipDateFrom.trim().length() > 0) {
				Date dateFrom = FormatData.parseDateShort3StringToDate(shipDateFrom.trim());
				shipDateFrom = FormatData.parseDateToTring(dateFrom);
				query.append(" AND (iss.IssueDate >= concat(date('" + shipDateFrom + "'), ' 00:00:00') OR iss.IssueDate IS NULL)");
			}
			
			// Den ngay
			if (Validator.isNotNull(shipDateTo) && shipDateTo.trim().length() > 0) {
				Date dateTo = FormatData.parseDateShort3StringToDate(shipDateTo.trim());
				shipDateTo = FormatData.parseDateToTring(dateTo);
				query.append(" AND (iss.IssueDate <= concat(date('" + shipDateTo + "'), ' 23:59:59') OR iss.IssueDate IS NULL)");
			}
			// query.append(" ORDER BY id DESC");
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(IssueAcceptance.class).build();
			log.debug("=========TAU SEARCH --findIssueAcceptanceInfo========" + query.toString());
			
			if (Validator.isNotNull(maritimeCode) && maritimeCode.trim().length() > 0 && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode);
				log.debug("===qPos.add===maritimeCode===" + maritimeCode);
			}
			if (Validator.isNotNull(portCode) && portCode.trim().length() > 0 && !portCode.trim().equalsIgnoreCase("LUA_CHON1")) {
				builder.appendNamedParameterMap("portCode",portCode);
				log.debug("===qPos.add===portCode===" + maritimeCode);
			}
			return (List<IssueAcceptance>)  queryFactory.getResultList(builder);
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
	
	public int countIssueAcceptanceInfo(String requestState, String maritimeCode, String portCode, String shipName, String callSign,
			String shipDateFrom, String shipDateTo) throws SystemException { int count = 0; try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT count(*) as total FROM issue_acceptance iss ");
			query.append(" left join temp_document temp ");
			query.append(" on iss.DocumentName = temp.DocumentName ");
			query.append(" and iss.DocumentYear = temp.DocumentYear where 1=1 ");
			
			// RequestState
			if (Validator.isNotNull(requestState) && requestState.trim().length() > 0) {
				query.append(" AND iss.RequestState =" + FormatData.convertToInt(requestState));
			}
			// cang vu hang hai
			if (Validator.isNotNull(maritimeCode) && maritimeCode.trim().length() > 0 && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
				query.append(" AND iss.PortofAuthority = ?");
			}
			
			// cang bien
			if (Validator.isNotNull(portCode) && portCode.trim().length() > 0 && !portCode.trim().equalsIgnoreCase("LUA_CHON1")) {
				query.append(" AND temp.portCode = ?");
			}
			// ten tau
			if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
				query.append(" AND iss.NameOfShip like '%" + shipName.trim() + "%' ");
			}
			// Ho hieu
			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
				query.append(" AND temp.CallSign like '%" + callSign.trim() + "%' ");
			}
			// Tu ngay
			if (Validator.isNotNull(shipDateFrom) && shipDateFrom.trim().length() > 0) {
				Date dateFrom = FormatData.parseDateShort3StringToDate(shipDateFrom.trim());
				shipDateFrom = FormatData.parseDateToTring(dateFrom);
				query.append(" AND (iss.IssueDate >= concat(date('" + shipDateFrom + "'), ' 00:00:00') OR iss.IssueDate IS NULL)");
			}
			
			// Den ngay
			if (Validator.isNotNull(shipDateTo) && shipDateTo.trim().length() > 0) {
				Date dateTo = FormatData.parseDateShort3StringToDate(shipDateTo.trim());
				shipDateTo = FormatData.parseDateToTring(dateTo);
				query.append(" AND (iss.IssueDate <= concat(date('" + shipDateTo + "'), ' 23:59:59') OR iss.IssueDate IS NULL)");
			}
			
			log.debug("=========TAU SEARCH --countIssueAcceptanceInfo========" + query.toString());
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(int.class).build();
			
			
			
			
			if (Validator.isNotNull(maritimeCode) && maritimeCode.trim().length() > 0 && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(portCode) && portCode.trim().length() > 0 && !portCode.trim().equalsIgnoreCase("LUA_CHON1")) {
				builder.appendNamedParameterMap("portCode",portCode.trim());
			}

			count = (int) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}
	
	public List<IssueAcceptance> findIssueAcceptanceByDocumentYearAndDocumentYear(long documentName, int documentYear) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM issue_acceptance WHERE DocumentName= :documentName AND DocumentYear= :documentYear ");
			
			String sql = query.toString();
			log.debug("=========findIssueAcceptanceByDocumentYearAndDocumentYear========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			return (List<IssueAcceptance>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<IssueAcceptance>();
	}
	
	public int countByDocumentYearAndDocumentYear(long documentName, int documentYear) {
		Long count = 0L;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) as total FROM issue_acceptance WHERE DocumentName= :documentName AND DocumentYear= :documentYear ");
			
			log.debug("=========countByDocumentYearAndDocumentYear========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();


			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);

			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return 0;
	}
	
	public List<IssueAcceptance> findIssueAcceptanceByDocumentYearAndDocumentYearAndRequestState(long documentName, int documentYear,
			int requestState) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM issue_acceptance WHERE DocumentName= :documentName AND DocumentYear= :documentYear AND RequestState= :requestState ");
			
			String sql = query.toString();
			log.debug("=========findIssueAcceptanceByDocumentYearAndDocumentYearAndRequestState========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(IssueAcceptance.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			builder.appendNamedParameterMap("requestState",requestState);
			
			// execute the query and return a list from the db
			return (List<IssueAcceptance>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<IssueAcceptance>();
	}
	
	public IssueAcceptance findByRequestCode(String requestCode) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM issue_acceptance WHERE RequestCode= :requestCode order by id desc");
			
			String sql = query.toString();
			log.debug("=========findByRequestCode========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(IssueAcceptance.class).build();
			
			builder.appendNamedParameterMap("requestCode",requestCode);
			
			// execute the query and return a list from the db
			List<IssueAcceptance> lstPort = (List<IssueAcceptance>) queryFactory.getResultList(builder);
			if (lstPort != null && lstPort.size() > 0) {
				return lstPort.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return null;
	}
	
	/**
	 * ascOrdesc: ASC is true, DESC is false
	 */
	public List<IssueAcceptance> findByDocumentYearAndDocumentYearOrderByColumn(long documentName, int documentYear, String orderByColumn,
			boolean ascOrdesc) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM issue_acceptance WHERE DocumentName= :documentName AND DocumentYear= :documentYear ORDER BY " + orderByColumn + " ");
			
			if (ascOrdesc) {
				query.append("asc");
			} else {
				query.append("desc");
			}
			
			String sql = query.toString();
			log.debug("=========findByDocumentYearAndDocumentYearOrderByColumn========" + sql);
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(IssueAcceptance.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			return (List<IssueAcceptance>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<IssueAcceptance>();
	}
	
	public String capGiayPhepSo(String maritimeReference) throws SystemException {
		
		try {
			if (Validator.isNotNull(maritimeReference) && maritimeReference.length() > 0) {
				
				StringBuilder query = new StringBuilder();
				
				query.append("select SUBSTRING(CertificateNo, 1, (length(CertificateNo) - 4)) as giayPhepSo from issue_shifting_order where CertificateNo like '%/" + maritimeReference + "' ");
				query.append("UNION ");
				query.append("select SUBSTRING(CertificateNo, 1, (length(CertificateNo) - 4)) as giayPhepSo from issue_port_clearance where CertificateNo like '%/" + maritimeReference + "' ");				
				query.append("UNION ");
				query.append("select SUBSTRING(CertificateNo, 1, (length(CertificateNo) - 4)) as giayPhepSo from issue_acceptance where CertificateNo like '%/" + maritimeReference + "' ");
				query.append("UNION ");
				query.append("select SUBSTRING(CertificateNo, 1, (length(CertificateNo) - 4)) as giayPhepSo from issue_permission_for_transit where CertificateNo like '%/" + maritimeReference + "' ");
				
				log.debug("==capGiayPhepSo==" + query.toString());
				
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(String.class).build();

				List<String> lstGiayPhepSo = (List<String>) queryFactory2.getResultList(builder);
				
				List<String> lstMax = new ArrayList<String>();
				for (String giayPhepSo : lstGiayPhepSo) {
					//log.info("==giayPhepSo==" + giayPhepSo);
					if (Validator.isNotNull(giayPhepSo)) {
						if (giayPhepSo.length() == 10) {
							lstMax.add(giayPhepSo);
						}
					}
				}
				
				
				String max = Collections.max(lstMax);
				
				log.info("==capGiayPhepSo==MAX==" + max);
				String sMax = "" + max;
				if(sMax.length() < 10) {
					return "0000000001";
				} else {
					Integer iGiayPhepSoMoi = (FormatData.convertToInt(sMax)) + 1;
					return ConvertUtil.capSoGiayPhep(iGiayPhepSoMoi + "");
				}
			}
			return "";
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
}
