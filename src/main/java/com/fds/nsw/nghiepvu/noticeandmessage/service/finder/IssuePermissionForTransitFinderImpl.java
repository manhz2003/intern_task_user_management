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
import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
@Service
@Slf4j
public class IssuePermissionForTransitFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<IssuePermissionForTransit> queryFactory;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<String> queryFactory2;

	
	
	
	public List<IssuePermissionForTransit> findIssuePermissionForTransitInfo(String requestState, String maritimeCode, String portCode,
			String shipName, String callSign, String shipDateFrom, String shipDateTo, String certificateNumber, int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT iss.* FROM issue_permission_for_transit iss ");
			query.append(" inner join temp_document temp ");
			query.append(" on iss.DocumentName = temp.DocumentName ");
			query.append(" and iss.DocumentYear = temp.DocumentYear ");
			query.append(" INNER JOIN (Select Max(VersionNo) as MaxVerionNo, DocumentName, DocumentYear from issue_permission_for_transit group by DocumentName, DocumentYear) AS MAXVersion ");
			query.append(" ON MAXVersion.DocumentName = iss.DocumentName  ");
			query.append(" AND MAXVersion.DocumentYear = iss.DocumentYear  ");
			query.append(" AND MAXVersion.MaxVerionNo = iss.VersionNo ");
			query.append(" where 1=1  ");
			
			
			// Giay phep ky so
			query.append(" AND iss.StampStatus = 2 AND iss.AttachedFile > 0 ");
						
			// RequestState
			if (Validator.isNotNull(requestState) && requestState.trim().length() > 0) {
				query.append(" AND iss.RequestState =" + FormatData.convertToInt(requestState));
			}
			// So giay phep
			if (Validator.isNotNull(certificateNumber) && certificateNumber.trim().length() > 0) {
//				query.append(" AND iss.CertificateNo like '%" + certificateNumber.trim() + "%' ");
				query.append(" AND iss.CertificateNo like ? ");
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
//				query.append(" AND iss.NameOfShip like '%" + shipName.trim() + "%' ");
				query.append(" AND iss.NameOfShip like ? ");
//				query.append(" AND temp.ShipName like '%" + shipName.trim() + "%' ");
				query.append(" AND temp.ShipName like ? ");
			}
			// Ho hieu
			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
//				query.append(" AND temp.CallSign like '%" + callSign.trim() + "%' ");
				query.append(" AND temp.CallSign like ? ");
			}
			// Tu ngay
			if (Validator.isNotNull(shipDateFrom) && shipDateFrom.trim().length() > 0) {
				Date dateFrom = FormatData.parseDateShort3StringToDate(shipDateFrom.trim());
				shipDateFrom = FormatData.parseDateToTring(dateFrom);
				query.append(" AND (iss.SignDate >= concat(date('" + shipDateFrom + "'), ' 00:00:00')  OR iss.SignDate IS NULL)");
			}
			// Den ngay
			if (Validator.isNotNull(shipDateTo) && shipDateTo.trim().length() > 0) {
				Date dateTo = FormatData.parseDateShort3StringToDate(shipDateTo.trim());
				shipDateTo = FormatData.parseDateToTring(dateTo);
				query.append(" AND (iss.SignDate <= concat(date('" + shipDateTo + "'), ' 23:59:59') OR iss.SignDate IS NULL)");
			}
			query.append(" ORDER BY iss.id DESC");
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(IssuePermissionForTransit.class).build();
			log.debug("=========findIssuePermissionForTransitInfoByCertificateNumber========" + query.toString());
			
			
			if (Validator.isNotNull(certificateNumber) && certificateNumber.trim().length() > 0) {
				builder.appendNamedParameterMap("certificateNumber","%" + certificateNumber.trim() + "%");
			}
			
			if (Validator.isNotNull(maritimeCode) && maritimeCode.trim().length() > 0 && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode);
			}
			if (Validator.isNotNull(portCode) && portCode.trim().length() > 0 && !portCode.trim().equalsIgnoreCase("LUA_CHON1")) {
				builder.appendNamedParameterMap("portCode",portCode);
			}
			
			if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
				builder.appendNamedParameterMap("shipName","%" + shipName.trim() + "%");
			}
			
			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
				builder.appendNamedParameterMap("callSign","%" + callSign.trim() + "%");
			}
			
			return (List<IssuePermissionForTransit>)  queryFactory.getResultList(builder);
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
	
	public int countIssuePermissionForTransitInfo(String requestState, String maritimeCode, String portCode, String shipName, String callSign,
			String shipDateFrom, String shipDateTo, String certificateNumber) throws SystemException { int count = 0; try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT count(*) as total FROM issue_permission_for_transit iss ");
			query.append(" inner join temp_document temp ");
			query.append(" on iss.DocumentName = temp.DocumentName ");
			query.append(" and iss.DocumentYear = temp.DocumentYear ");
			query.append(" INNER JOIN (Select Max(VersionNo) as MaxVerionNo, DocumentName, DocumentYear from issue_permission_for_transit group by DocumentName, DocumentYear) AS MAXVersion ");
			query.append(" ON MAXVersion.DocumentName = iss.DocumentName  ");
			query.append(" AND MAXVersion.DocumentYear = iss.DocumentYear  ");
			query.append(" AND MAXVersion.MaxVerionNo = iss.VersionNo ");
			query.append(" where 1=1  ");
			

			// Giay phep ky so
			query.append(" AND iss.StampStatus = 2 AND iss.AttachedFile > 0 ");
						
			// RequestState
			if (Validator.isNotNull(requestState) && requestState.trim().length() > 0) {
				query.append(" AND iss.RequestState =" + FormatData.convertToInt(requestState));
			}
			// So giay phep
			if (Validator.isNotNull(certificateNumber) && certificateNumber.trim().length() > 0) {
//				query.append(" AND iss.CertificateNo like '%" + certificateNumber.trim() + "%' ");
				query.append(" AND iss.CertificateNo like ? ");
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
//				query.append(" AND iss.NameOfShip like '%" + shipName.trim() + "%' ");
				query.append(" AND iss.NameOfShip like ? ");
//				query.append(" AND temp.ShipName like '%" + shipName.trim() + "%' ");
				query.append(" AND temp.ShipName like ? ");
			}
			// Ho hieu
			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
//				query.append(" AND temp.CallSign like '%" + callSign.trim() + "%' ");
				query.append(" AND temp.CallSign like ? ");
			}
			// Tu ngay
			if (Validator.isNotNull(shipDateFrom) && shipDateFrom.trim().length() > 0) {
				Date dateFrom = FormatData.parseDateShort3StringToDate(shipDateFrom.trim());
				shipDateFrom = FormatData.parseDateToTring(dateFrom);
				query.append(" AND (iss.SignDate >= concat(date('" + shipDateFrom + "'), ' 00:00:00')  OR iss.SignDate IS NULL)");
			}
			
			// Den ngay
			if (Validator.isNotNull(shipDateTo) && shipDateTo.trim().length() > 0) {
				Date dateTo = FormatData.parseDateShort3StringToDate(shipDateTo.trim());
				shipDateTo = FormatData.parseDateToTring(dateTo);
				query.append(" AND (iss.SignDate <= concat(date('" + shipDateTo + "'), ' 23:59:59') OR iss.SignDate IS NULL)");
			}
			
			log.debug("=========countIssuePermissionForTransitInfo========" + query.toString());
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(int.class).build();
			
			
			
			
			if (Validator.isNotNull(certificateNumber) && certificateNumber.trim().length() > 0) {
				builder.appendNamedParameterMap("certificateNumber","%" + certificateNumber.trim() + "%");
			}
			
			if (Validator.isNotNull(maritimeCode) && maritimeCode.trim().length() > 0 && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode);
			}
			if (Validator.isNotNull(portCode) && portCode.trim().length() > 0 && !portCode.trim().equalsIgnoreCase("LUA_CHON1")) {
				builder.appendNamedParameterMap("portCode",portCode);
			}
			
			if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
				builder.appendNamedParameterMap("shipName","%" + shipName.trim() + "%");
				builder.appendNamedParameterMap("shipName","%" + shipName.trim() + "%");
			}
			
			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
				builder.appendNamedParameterMap("callSign","%" + callSign.trim() + "%");
			}
			

			count = (int) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}
	
	
	public List<IssuePermissionForTransit> findIssuePermissionForTransitInfoByCertificateNumber(String requestState, String maritimeCode, String portCode,
			String shipName, String callSign, String shipDateFrom, String shipDateTo, String certificateNumber, int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT iss.* FROM issue_permission_for_transit iss ");
			query.append(" inner join temp_document temp ");
			query.append(" on iss.DocumentName = temp.DocumentName ");
			query.append(" and iss.DocumentYear = temp.DocumentYear where 1=1 ");
			
			// RequestState
			if (Validator.isNotNull(requestState) && requestState.trim().length() > 0) {
				query.append(" AND iss.RequestState =" + FormatData.convertToInt(requestState));
			}
			// So giay phep
			if (Validator.isNotNull(certificateNumber) && certificateNumber.trim().length() > 0) {
//				query.append(" AND iss.CertificateNo like '%" + certificateNumber.trim() + "%' ");
				query.append(" AND iss.CertificateNo like ? ");
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
//				query.append(" AND iss.NameOfShip like '%" + shipName.trim() + "%' ");
				query.append(" AND iss.NameOfShip like ? ");
//				query.append(" AND temp.ShipName like '%" + shipName.trim() + "%' ");
				query.append(" AND temp.ShipName like ? ");
			}
			// Ho hieu
			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
//				query.append(" AND temp.CallSign like '%" + callSign.trim() + "%' ");
				query.append(" AND temp.CallSign like ? ");
			}
			// Tu ngay
			if (Validator.isNotNull(shipDateFrom) && shipDateFrom.trim().length() > 0) {
				Date dateFrom = FormatData.parseDateShort3StringToDate(shipDateFrom.trim());
				shipDateFrom = FormatData.parseDateToTring(dateFrom);
				query.append(" AND (iss.ValidUntil >= concat(date('" + shipDateFrom + "'), ' 00:00:00')  OR iss.ValidUntil IS NULL)");
			}
			// Den ngay
			if (Validator.isNotNull(shipDateTo) && shipDateTo.trim().length() > 0) {
				Date dateTo = FormatData.parseDateShort3StringToDate(shipDateTo.trim());
				shipDateTo = FormatData.parseDateToTring(dateTo);
				query.append(" AND (iss.ValidUntil <= concat(date('" + shipDateTo + "'), ' 23:59:59') OR iss.ValidUntil IS NULL)");
			}
			// query.append(" ORDER BY id DESC");
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(IssuePermissionForTransit.class).build();
			log.debug("=========findIssuePermissionForTransitInfoByCertificateNumber========" + query.toString());
			
			
			if (Validator.isNotNull(certificateNumber) && certificateNumber.trim().length() > 0) {
				builder.appendNamedParameterMap("certificateNumber","%" + certificateNumber.trim() + "%");
			}
			
			if (Validator.isNotNull(maritimeCode) && maritimeCode.trim().length() > 0 && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode);
			}
			if (Validator.isNotNull(portCode) && portCode.trim().length() > 0 && !portCode.trim().equalsIgnoreCase("LUA_CHON1")) {
				builder.appendNamedParameterMap("portCode",portCode);
			}
			
			if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
				builder.appendNamedParameterMap("shipName","%" + shipName.trim() + "%");
				builder.appendNamedParameterMap("shipName","%" + shipName.trim() + "%");
			}
			
			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
				builder.appendNamedParameterMap("callSign","%" + callSign.trim() + "%");
			}
			
			return (List<IssuePermissionForTransit>)  queryFactory.getResultList(builder);
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
	
	public int countIssuePermissionForTransitInfoByCertificateNumber(String requestState, String maritimeCode, String portCode, String shipName, String callSign,
			String shipDateFrom, String shipDateTo, String certificateNumber) throws SystemException { int count = 0; try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT count(*) as total FROM issue_permission_for_transit iss ");
			query.append(" inner join temp_document temp ");
			query.append(" on iss.DocumentName = temp.DocumentName ");
			query.append(" and iss.DocumentYear = temp.DocumentYear where 1=1 ");
			// RequestState
			if (Validator.isNotNull(requestState) && requestState.trim().length() > 0) {
				query.append(" AND iss.RequestState =" + FormatData.convertToInt(requestState));
			}
			// So giay phep
			if (Validator.isNotNull(certificateNumber) && certificateNumber.trim().length() > 0) {
//				query.append(" AND iss.CertificateNo like '%" + certificateNumber.trim() + "%' ");
				query.append(" AND iss.CertificateNo like ? ");
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
//				query.append(" AND iss.NameOfShip like '%" + shipName.trim() + "%' ");
				query.append(" AND iss.NameOfShip like ? ");
//				query.append(" AND temp.ShipName like '%" + shipName.trim() + "%' ");
				query.append(" AND temp.ShipName like ? ");
			}
			// Ho hieu
			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
//				query.append(" AND temp.CallSign like '%" + callSign.trim() + "%' ");
				query.append(" AND temp.CallSign like ? ");
			}
			// Tu ngay
			if (Validator.isNotNull(shipDateFrom) && shipDateFrom.trim().length() > 0) {
				Date dateFrom = FormatData.parseDateShort3StringToDate(shipDateFrom.trim());
				shipDateFrom = FormatData.parseDateToTring(dateFrom);
				query.append(" AND (iss.ValidUntil >= concat(date('" + shipDateFrom + "'), ' 00:00:00')  OR iss.ValidUntil IS NULL)");
			}
			
			// Den ngay
			if (Validator.isNotNull(shipDateTo) && shipDateTo.trim().length() > 0) {
				Date dateTo = FormatData.parseDateShort3StringToDate(shipDateTo.trim());
				shipDateTo = FormatData.parseDateToTring(dateTo);
				query.append(" AND (iss.ValidUntil <= concat(date('" + shipDateTo + "'), ' 23:59:59') OR iss.ValidUntil IS NULL)");
			}
			
			log.debug("=========countIssuePermissionForTransitInfoByCertificateNumber========" + query.toString());
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(int.class).build();
			
			
			
			
			if (Validator.isNotNull(certificateNumber) && certificateNumber.trim().length() > 0) {
				builder.appendNamedParameterMap("certificateNumber","%" + certificateNumber.trim() + "%");
			}
			
			if (Validator.isNotNull(maritimeCode) && maritimeCode.trim().length() > 0 && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode);
			}
			if (Validator.isNotNull(portCode) && portCode.trim().length() > 0 && !portCode.trim().equalsIgnoreCase("LUA_CHON1")) {
				builder.appendNamedParameterMap("portCode",portCode);
			}
			
			if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
				builder.appendNamedParameterMap("shipName","%" + shipName.trim() + "%");
				builder.appendNamedParameterMap("shipName","%" + shipName.trim() + "%");
			}
			
			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
				builder.appendNamedParameterMap("callSign","%" + callSign.trim() + "%");
			}
			

			count = (int) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}
	
	public int countByDocumentNameAndDocumentYear(long documentName, int documentYear) throws SystemException { int count = 0; try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT count(*) as total FROM issue_permission_for_transit iss ");
			query.append("WHERE DocumentName= :documentName AND DocumentYear= :documentYear");
			
			log.debug("=======countByDocumentNameAndDocumentYear====countByDocumentNameAndDocumentYear========" + query.toString());
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(int.class).build();
			
			
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			

			count = (int) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}
	
	public IssuePermissionForTransit findIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState(long documentName, int documentYear,
			int requestState) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM issue_permission_for_transit ");
			query.append(" WHERE DocumentName= :documentName AND DocumentYear= :documentYear AND RequestState= :requestState ");
			
			log.debug("=========findIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(IssuePermissionForTransit.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			builder.appendNamedParameterMap("requestState",requestState);
			
			// execute the query and return a list from the db
			return (IssuePermissionForTransit) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
		return null;
	}
	
	/**
	 * ascOrdesc: ASC is true, DESC is false
	 */
	public List<IssuePermissionForTransit> findByDocumentYearAndDocumentYearOrderByColumn(long documentName, int documentYear, String orderByColumn,
			boolean ascOrdesc) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM issue_permission_for_transit WHERE DocumentName= :documentName AND DocumentYear= :documentYear ORDER BY " + orderByColumn);
			
			if (ascOrdesc) {
				query.append(" asc");
			} else {
				query.append(" desc");
			}
			
			String sql = query.toString();
			log.debug("=========findByDocumentYearAndDocumentYearOrderByColumn========" + sql);
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(IssuePermissionForTransit.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			return (List<IssuePermissionForTransit>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<IssuePermissionForTransit>();
	}
	
	public String capGiayPhepSo(String maritimeReference) throws SystemException {
		
		try {
			if (Validator.isNotNull(maritimeReference) && maritimeReference.length() > 0) {
				
				StringBuilder query = new StringBuilder();
				
				query.append("select SUBSTRING(CertificateNo, 1, (length(CertificateNo) - 4)) as giayPhepSo from issue_shifting_order where CertificateNo like ? ");
				query.append("UNION ");
				query.append("select SUBSTRING(CertificateNo, 1, (length(CertificateNo) - 4)) as giayPhepSo from issue_port_clearance where CertificateNo like ? ");
				query.append("UNION ");
				query.append("select SUBSTRING(CertificateNo, 1, (length(CertificateNo) - 4)) as giayPhepSo from issue_permission_for_transit where CertificateNo like ? ");
				
				log.debug("==capGiayPhepSo==" + query.toString());
				
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(String.class).build();


				
				
				builder.appendNamedParameterMap("maritimeReference","%/" + maritimeReference.trim() + "%");

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
