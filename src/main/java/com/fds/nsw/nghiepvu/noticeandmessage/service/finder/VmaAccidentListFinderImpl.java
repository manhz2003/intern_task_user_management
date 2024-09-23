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
import com.fds.nsw.nghiepvu.model.VmaAccidentList;
@Service
@Slf4j
public class VmaAccidentListFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaAccidentList> queryFactory;

	

	public List<VmaAccidentList> getVmaAccidentLists(String nameOfShip,
			String flagStateOfShip, String callSign, String imoNumber,
			String registryNumber, Date accidentTime, String accidentType,
			String accidentCriticalType, String numberOfDead,
			String numberOfMissed, String numberOfInjured, String pilotOnBoad,
			String makeInvestigation, Date investigationDate,
			String portofAuthority, Date accidentOfficialDate, int start,
			int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM vma_accident_list WHERE 1 = 1 ");
			if (Validator.isNotNull(nameOfShip) && !nameOfShip.isEmpty()) {
				query.append(" AND NameOfShip LIKE ?");
			}
			if (Validator.isNotNull(flagStateOfShip)
					&& !flagStateOfShip.isEmpty()) {
				query.append(" AND FlagStateOfShip LIKE ?");
			}
			if (Validator.isNotNull(callSign) && !callSign.isEmpty()) {
				query.append(" AND CallSign LIKE ?");
			}
			if (Validator.isNotNull(imoNumber) && !imoNumber.isEmpty()) {
				query.append(" AND IMONumber LIKE ?");
			}
			if (Validator.isNotNull(registryNumber)
					&& !registryNumber.isEmpty()) {
				query.append(" AND RegistryNumber LIKE ?");
			}
			if (Validator.isNotNull(accidentTime)) {
				query.append(" AND AccidentTime= :accidentTime");
			}
			if (Validator.isNotNull(accidentType) && !accidentType.isEmpty()) {
				query.append(" AND AccidentType LIKE ?");
			}
			if (Validator.isNotNull(accidentCriticalType)
					&& !accidentCriticalType.isEmpty()) {
				query.append(" AND AccidentCriticalType LIKE ?");
			}
			if (Validator.isNotNull(numberOfDead) && !numberOfDead.isEmpty()) {
				query.append(" AND NumberOfDead LIKE ?");
			}
			if (Validator.isNotNull(numberOfMissed)
					&& !numberOfMissed.isEmpty()) {
				query.append(" AND NumberOfMissed LIKE ?");
			}
			if (Validator.isNotNull(numberOfInjured)
					&& !numberOfInjured.isEmpty()) {
				query.append(" AND NumberOfInjured LIKE ?");
			}
			if (Validator.isNotNull(pilotOnBoad) && !pilotOnBoad.isEmpty()) {
				query.append(" AND PilotOnBoad LIKE ?");
			}
			if (Validator.isNotNull(makeInvestigation)
					&& !makeInvestigation.isEmpty()) {
				query.append(" AND MakeInvestigation LIKE ?");
			}
			if (Validator.isNotNull(investigationDate)) {
				query.append(" AND InvestigationDate= :investigationDate");
			}
			if (Validator.isNotNull(portofAuthority)
					&& !portofAuthority.isEmpty()) {
				query.append(" AND PortofAuthority= :portofAuthority");
			}
			if (Validator.isNotNull(accidentOfficialDate)) {
				query.append(" AND AccidentOfficialDate= :accidentOfficialDate");
			}
			query.append(" ORDER BY AccidentTime DESC");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaAccidentList.class).build();

			log.info("=========find VmaAccidentList ===" + query.toString());
			

			if (Validator.isNotNull(nameOfShip) && !nameOfShip.isEmpty()) {
				builder.appendNamedParameterMap("nameOfShip","%" + nameOfShip + "%");
			}
			if (Validator.isNotNull(flagStateOfShip)
					&& !flagStateOfShip.isEmpty()) {
				builder.appendNamedParameterMap("flagStateOfShip","%" + flagStateOfShip + "%");
			}
			if (Validator.isNotNull(callSign) && !callSign.isEmpty()) {
				builder.appendNamedParameterMap("callSign","%" + callSign + "%");
			}
			if (Validator.isNotNull(imoNumber) && !imoNumber.isEmpty()) {
				builder.appendNamedParameterMap("imoNumber","%" + imoNumber + "%");
			}
			if (Validator.isNotNull(registryNumber)
					&& !registryNumber.isEmpty()) {
				builder.appendNamedParameterMap("registryNumber","%" + registryNumber + "%");
			}
			if (Validator.isNotNull(accidentTime)) {
				builder.appendNamedParameterMap("accidentTime",accidentTime);
			}
			if (Validator.isNotNull(accidentType) && !accidentType.isEmpty()) {
				builder.appendNamedParameterMap("accidentType","%" + accidentType + "%");
			}
			if (Validator.isNotNull(accidentCriticalType)
					&& !accidentCriticalType.isEmpty()) {
				builder.appendNamedParameterMap("accidentCriticalType","%" + accidentCriticalType + "%");
			}
			if (Validator.isNotNull(numberOfDead) && !numberOfDead.isEmpty()) {
				builder.appendNamedParameterMap("numberOfDead","%" + numberOfDead + "%");
			}
			if (Validator.isNotNull(numberOfMissed)
					&& !numberOfMissed.isEmpty()) {
				builder.appendNamedParameterMap("numberOfMissed","%" + numberOfMissed + "%");
			}
			if (Validator.isNotNull(numberOfInjured)
					&& !numberOfInjured.isEmpty()) {
				builder.appendNamedParameterMap("numberOfInjured","%" + numberOfInjured + "%");
			}
			if (Validator.isNotNull(pilotOnBoad) && !pilotOnBoad.isEmpty()) {
				builder.appendNamedParameterMap("pilotOnBoad","%" + pilotOnBoad + "%");
			}
			if (Validator.isNotNull(makeInvestigation)
					&& !makeInvestigation.isEmpty()) {
				builder.appendNamedParameterMap("makeInvestigation","%" + makeInvestigation + "%");
			}
			if (Validator.isNotNull(investigationDate)) {
				builder.appendNamedParameterMap("investigationDate",investigationDate);
			}
			if (Validator.isNotNull(portofAuthority)
					&& !portofAuthority.isEmpty()) {
				builder.appendNamedParameterMap("portofAuthority",portofAuthority);
			}
			if (Validator.isNotNull(accidentOfficialDate)) {
				builder.appendNamedParameterMap("accidentOfficialDate",accidentOfficialDate);
			}

			return (List<VmaAccidentList>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaAccidentLists(String nameOfShip,
			String flagStateOfShip, String callSign, String imoNumber,
			String registryNumber, Date accidentTime, String accidentType,
			String accidentCriticalType, String numberOfDead,
			String numberOfMissed, String numberOfInjured, String pilotOnBoad,
			String makeInvestigation, Date investigationDate,
			String portofAuthority, Date accidentOfficialDate) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM vma_accident_list WHERE 1 = 1 ");
			if (Validator.isNotNull(nameOfShip) && !nameOfShip.isEmpty()) {
				query.append(" AND NameOfShip LIKE ?");
			}
			if (Validator.isNotNull(flagStateOfShip)
					&& !flagStateOfShip.isEmpty()) {
				query.append(" AND FlagStateOfShip LIKE ?");
			}
			if (Validator.isNotNull(callSign) && !callSign.isEmpty()) {
				query.append(" AND CallSign LIKE ?");
			}
			if (Validator.isNotNull(imoNumber) && !imoNumber.isEmpty()) {
				query.append(" AND IMONumber LIKE ?");
			}
			if (Validator.isNotNull(registryNumber)
					&& !registryNumber.isEmpty()) {
				query.append(" AND RegistryNumber LIKE ?");
			}
			if (Validator.isNotNull(accidentTime)) {
				query.append(" AND AccidentTime= :accidentTime");
			}
			if (Validator.isNotNull(accidentType) && !accidentType.isEmpty()) {
				query.append(" AND AccidentType LIKE ?");
			}
			if (Validator.isNotNull(accidentCriticalType)
					&& !accidentCriticalType.isEmpty()) {
				query.append(" AND AccidentCriticalType LIKE ?");
			}
			if (Validator.isNotNull(numberOfDead) && !numberOfDead.isEmpty()) {
				query.append(" AND NumberOfDead LIKE ?");
			}
			if (Validator.isNotNull(numberOfMissed)
					&& !numberOfMissed.isEmpty()) {
				query.append(" AND NumberOfMissed LIKE ?");
			}
			if (Validator.isNotNull(numberOfInjured)
					&& !numberOfInjured.isEmpty()) {
				query.append(" AND NumberOfInjured LIKE ?");
			}
			if (Validator.isNotNull(pilotOnBoad) && !pilotOnBoad.isEmpty()) {
				query.append(" AND PilotOnBoad LIKE ?");
			}
			if (Validator.isNotNull(makeInvestigation)
					&& !makeInvestigation.isEmpty()) {
				query.append(" AND MakeInvestigation LIKE ?");
			}
			if (Validator.isNotNull(investigationDate)) {
				query.append(" AND InvestigationDate= :investigationDate");
			}
			if (Validator.isNotNull(portofAuthority)
					&& !portofAuthority.isEmpty()) {
				query.append(" AND PortofAuthority= :portofAuthority");
			}
			if (Validator.isNotNull(accidentOfficialDate)) {
				query.append(" AND AccidentOfficialDate= :accidentOfficialDate");
			}
			query.append(" ORDER BY AccidentTime DESC");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();

			

			if (Validator.isNotNull(nameOfShip) && !nameOfShip.isEmpty()) {
				builder.appendNamedParameterMap("nameOfShip","%" + nameOfShip + "%");
			}
			if (Validator.isNotNull(flagStateOfShip)
					&& !flagStateOfShip.isEmpty()) {
				builder.appendNamedParameterMap("flagStateOfShip","%" + flagStateOfShip + "%");
			}
			if (Validator.isNotNull(callSign) && !callSign.isEmpty()) {
				builder.appendNamedParameterMap("callSign","%" + callSign + "%");
			}
			if (Validator.isNotNull(imoNumber) && !imoNumber.isEmpty()) {
				builder.appendNamedParameterMap("imoNumber","%" + imoNumber + "%");
			}
			if (Validator.isNotNull(registryNumber)
					&& !registryNumber.isEmpty()) {
				builder.appendNamedParameterMap("registryNumber","%" + registryNumber + "%");
			}
			if (Validator.isNotNull(accidentTime)) {
				builder.appendNamedParameterMap("accidentTime",accidentTime);
			}
			if (Validator.isNotNull(accidentType) && !accidentType.isEmpty()) {
				builder.appendNamedParameterMap("accidentType","%" + accidentType + "%");
			}
			if (Validator.isNotNull(accidentCriticalType)
					&& !accidentCriticalType.isEmpty()) {
				builder.appendNamedParameterMap("accidentCriticalType","%" + accidentCriticalType + "%");
			}
			if (Validator.isNotNull(numberOfDead) && !numberOfDead.isEmpty()) {
				builder.appendNamedParameterMap("numberOfDead","%" + numberOfDead + "%");
			}
			if (Validator.isNotNull(numberOfMissed)
					&& !numberOfMissed.isEmpty()) {
				builder.appendNamedParameterMap("numberOfMissed","%" + numberOfMissed + "%");
			}
			if (Validator.isNotNull(numberOfInjured)
					&& !numberOfInjured.isEmpty()) {
				builder.appendNamedParameterMap("numberOfInjured","%" + numberOfInjured + "%");
			}
			if (Validator.isNotNull(pilotOnBoad) && !pilotOnBoad.isEmpty()) {
				builder.appendNamedParameterMap("pilotOnBoad","%" + pilotOnBoad + "%");
			}
			if (Validator.isNotNull(makeInvestigation)
					&& !makeInvestigation.isEmpty()) {
				builder.appendNamedParameterMap("makeInvestigation","%" + makeInvestigation + "%");
			}
			if (Validator.isNotNull(investigationDate)) {
				builder.appendNamedParameterMap("investigationDate",investigationDate);
			}
			if (Validator.isNotNull(portofAuthority)
					&& !portofAuthority.isEmpty()) {
				builder.appendNamedParameterMap("portofAuthority",portofAuthority);
			}
			if (Validator.isNotNull(accidentOfficialDate)) {
				builder.appendNamedParameterMap("accidentOfficialDate",accidentOfficialDate);
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}
}
