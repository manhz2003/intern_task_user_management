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
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
@Service
@Slf4j
public class IssuePortClearanceFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<IssuePortClearance> queryFactory;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<String> queryFactory2;
	
	
	public IssuePortClearance findLastestIssuePortClearanceByPortNameAndCallSign(String nameOfShip, String callSign) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM issue_port_clearance where 1 = 1 ");
			
			//query by nameofship
			if(Validator.isNotNull(nameOfShip) && nameOfShip.trim().length() > 0) {
//				query.append(" AND NameOfShip like '%" + nameOfShip.trim() + "%' ");
				query.append(" AND NameOfShip like ? ");
			}
			//query by callSign
			if(Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
//				query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
				query.append(" AND CallSign like ? ");
			}
			
			query.append("ORDER BY ApprovalDate DESC limit 1 ");
			
			String sql = query.toString();
			log.info("=========findByRequestCode========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(IssuePortClearance.class).build();
			
			
			
			if(Validator.isNotNull(nameOfShip) && nameOfShip.trim().length() > 0) {
				builder.appendNamedParameterMap("nameOfShip","%" + nameOfShip.trim() + "%");
			}
			
			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
				builder.appendNamedParameterMap("callSign","%" + callSign.trim() + "%");
			}
			
			return (IssuePortClearance) queryFactory.getSingleResult(builder);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return null;
	}
	
	public IssuePortClearance findLatestCertificateOfIssuePortClearance(String nameOfShip, String callSign, String certificateNo) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM issue_port_clearance where 1 = 1 ");
			
			//query by nameofship
			if(Validator.isNotNull(nameOfShip) && nameOfShip.trim().length() > 0) {
//				query.append(" AND NameOfShip like '%" + nameOfShip.trim() + "%' ");
				query.append(" AND NameOfShip like ? ");
			}
			//query by callSign
			if(Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
//				query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
				query.append(" AND CallSign like ? ");
			}
			
			//query by certificateNo
			if(Validator.isNotNull(certificateNo) && certificateNo.trim().length() > 0) {
				query.append(" AND UPPER(CertificateNo)  = '" + certificateNo.trim().toUpperCase() + "'");
			}
			
			query.append(" ORDER BY ApprovalDate DESC limit 1 ");
			
			String sql = query.toString();
			log.info("=========findLatestCertificateOfIssuePortClearance========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(IssuePortClearance.class).build();
			
			
			
			if(Validator.isNotNull(nameOfShip) && nameOfShip.trim().length() > 0) {
				builder.appendNamedParameterMap("nameOfShip","%" + nameOfShip.trim() + "%");
			}
			
			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
				builder.appendNamedParameterMap("callSign","%" + callSign.trim() + "%");
			}
			
			return (IssuePortClearance) queryFactory.getSingleResult(builder);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return null;
	}
	
	public List<IssuePortClearance> findIssuePortClearanceInfo(String requestState, String maritimeCode, String portCode, String shipName,
			String callSign, String shipDateFrom, String shipDateTo, String certificateNumber, int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT iss.* FROM issue_port_clearance iss ");			
			query.append(" inner join temp_document temp ");
			query.append(" on iss.DocumentName = temp.DocumentName ");
			query.append(" and iss.DocumentYear = temp.DocumentYear ");
			query.append(" INNER JOIN (Select Max(VersionNo) as MaxVerionNo, DocumentName, DocumentYear from issue_port_clearance group by DocumentName, DocumentYear) AS MAXVersion ");
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
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(IssuePortClearance.class).build();
			log.debug("=========findIssuePortClearanceInfo========" + query.toString());
			
			
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
			
			return (List<IssuePortClearance>)  queryFactory.getResultList(builder);
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
	
	public int countIssuePortClearanceInfo(String requestState, String maritimeCode, String portCode, String shipName, String callSign,
			String shipDateFrom, String shipDateTo, String certificateNumber) throws SystemException { int count = 0; try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT count(*) as total FROM issue_port_clearance iss ");
			query.append(" inner join temp_document temp ");
			query.append(" on iss.DocumentName = temp.DocumentName ");
			query.append(" and iss.DocumentYear = temp.DocumentYear ");
			query.append(" INNER JOIN (Select Max(VersionNo) as MaxVerionNo, DocumentName, DocumentYear from issue_port_clearance group by DocumentName, DocumentYear) AS MAXVersion ");
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
			
			log.debug("=========countIssuePortClearanceInfo========" + query.toString());
			
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
	
	
	public List<IssuePortClearance> findIssuePortClearanceInfoByCertificateNumber(String requestState, String maritimeCode, String portCode, String shipName,
			String callSign, String shipDateFrom, String shipDateTo, String certificateNumber, int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT iss.* FROM issue_port_clearance iss ");
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
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(IssuePortClearance.class).build();
			log.debug("=========findIssuePortClearanceInfoByCertificateNumber========" + query.toString());
			
			
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
			
			return (List<IssuePortClearance>)  queryFactory.getResultList(builder);
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
	
	public int countIssuePortClearanceInfoByCertificateNumber(String requestState, String maritimeCode, String portCode, String shipName, String callSign,
			String shipDateFrom, String shipDateTo, String certificateNumber) throws SystemException { int count = 0; try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT count(*) as total FROM issue_port_clearance iss ");
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
			
			log.debug("=========countIssuePortClearanceInfoByCertificateNumber========" + query.toString());
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Integer.class).build();
			
			
			
			
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
			

			count = (Integer) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}
	
	
	public List<IssuePortClearance> findIssuePortClearanceByDocumentYearAndDocumentYear(long documentName, int documentYear) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM ISSUE_PORT_CLEARANCE WHERE DocumentName= :documentName AND DocumentYear= :documentYear ");
			
			String sql = query.toString();
			log.debug("=========findIssuePortClearanceByDocumentYearAndDocumentYear========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			return (List<IssuePortClearance>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<IssuePortClearance>();
	}
	
	public int countByDocumentYearAndDocumentYear(long documentName, int documentYear) {
		int count = 0;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) as total FROM ISSUE_PORT_CLEARANCE WHERE DocumentName= :documentName AND DocumentYear= :documentYear ");
			
			log.debug("=========countByDocumentYearAndDocumentYear========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Integer.class).build();
			
			
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			count = (Integer) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return 0;
	}
	
	public List<IssuePortClearance> findIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(long documentName, int documentYear,
			int requestState) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM ISSUE_PORT_CLEARANCE WHERE DocumentName= :documentName AND DocumentYear= :documentYear AND RequestState= :requestState ");
			
			String sql = query.toString();
			log.debug("=========findIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(IssuePortClearance.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			builder.appendNamedParameterMap("requestState",requestState);
			
			// execute the query and return a list from the db
			return (List<IssuePortClearance>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<IssuePortClearance>();
	}
	
	public IssuePortClearance findByRequestCode(String requestCode) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM ISSUE_PORT_CLEARANCE WHERE RequestCode= :requestCode order by id desc");
			
			String sql = query.toString();
			log.debug("=========findByRequestCode========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(IssuePortClearance.class).build();
			
			builder.appendNamedParameterMap("requestCode",requestCode);
			
			// execute the query and return a list from the db
			List<IssuePortClearance> lstPort = (List<IssuePortClearance>) queryFactory.getResultList(builder);
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
	public List<IssuePortClearance> findByDocumentYearAndDocumentYearOrderByColumn(long documentName, int documentYear, String orderByColumn,
			boolean ascOrdesc) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM issue_port_clearance WHERE DocumentName= :documentName AND DocumentYear= :documentYear ORDER BY " + orderByColumn + " ");
			
			if (ascOrdesc) {
				query.append("asc");
			} else {
				query.append("desc");
			}
			
			String sql = query.toString();
			log.debug("=========findByDocumentYearAndDocumentYearOrderByColumn========" + sql);
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(IssuePortClearance.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			return (List<IssuePortClearance>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<IssuePortClearance>();
	}
	
	public String capGiayPhepSo(String maritimeReference) throws SystemException {
		
		try {
			if (Validator.isNotNull(maritimeReference) && maritimeReference.length() > 0) {
				
				StringBuilder query = new StringBuilder();
				
				query.append("select SUBSTRING(CertificateNo, 1, (length(CertificateNo) - 4)) as giayPhepSo from issue_shifting_order where CertificateNo like '%/" + maritimeReference + "' ");
				query.append("UNION ");
				query.append("select SUBSTRING(CertificateNo, 1, (length(CertificateNo) - 4)) as giayPhepSo from issue_port_clearance where CertificateNo like '%/" + maritimeReference + "' ");
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
