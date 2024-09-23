package com.fds.nsw.nghiepvu.danhmuc.service.finder;

import java.util.*;

import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vn.gt.portlet.baocao.bc12bt.BC12BTVinalinesModel;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.dao.orm.BasePersistence;
import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.util.ConvertUtil;
import com.fds.nsw.nghiepvu.util.FormatData;

import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
@Service
@Slf4j
public class DmPortWharfFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmPortWharf> queryFactory;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;

	public DmPortWharf getFirstPortWharf() throws SystemException {
		try {

			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port_wharf WHERE 1= 1   ");
			query.append("ORDER BY ID ASC LIMIT 1");

			String sql = query.toString();

			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(DmPortWharf.class).build();
			// execute the query and return a list from the db
			return (DmPortWharf) queryFactory.getResultList(builder).get(0);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}

	public DmPortWharf getLastPortWharf() throws SystemException {
		try {

			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port_wharf WHERE 1= 1   ");
			query.append("ORDER BY ID DESC LIMIT 1");

			String sql = query.toString();
			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(DmPortWharf.class).build();

			// execute the query and return a list from the db
			return (DmPortWharf) queryFactory.getResultList(builder).get(0);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}


	public List<DmPortWharf> findPortWharfs(String maritimeCode,
											String portRegionCode, String portHarbourCode,
											String portWharfNameVN, String isDelete, String portWharfCodeGroup,
											int start, int end) throws SystemException {
		try {

			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port_wharf WHERE 1= 1   ");

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" and PortRegionCode in (Select distinct PortRegionCode from dm_port_region where PortRegionRef =  ? )");
			}
			if (Validator.isNotNull(portRegionCode)
					&& portRegionCode.trim().length() > 0) {
				query.append(" AND PortRegionCode= :portRegionCode ");
			}
			if (Validator.isNotNull(portHarbourCode)
					&& portHarbourCode.trim().length() > 0) {
				query.append(" AND PortHarbourCode= :portHarbourCode ");
			}
			if (Validator.isNotNull(portWharfNameVN)
					&& portWharfNameVN.trim().length() > 0) {
				query.append(" and PortWharfNameVN like ? ");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete ");
			}
			if (Validator.isNotNull(portWharfCodeGroup)
					&& !portWharfCodeGroup.isEmpty()) {
				portWharfCodeGroup = portWharfCodeGroup.replace(",", "', '");
				query.append(" AND PortWharfCode IN ('" + portWharfCodeGroup
						+ "')");
			}
			query.append(" ORDER BY SequenceNo");

			String sql = query.toString();

			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql)
					.firstResult(start).maxResults(end - start).entityClass(DmPortWharf.class).build();
			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(portRegionCode)
					&& portRegionCode.trim().length() > 0) {
				builder.appendNamedParameterMap("portRegionCode",portRegionCode.trim());
			}
			if (Validator.isNotNull(portHarbourCode)
					&& portHarbourCode.trim().length() > 0) {
				builder.appendNamedParameterMap("portHarbourCode",portHarbourCode.trim());
			}
			if (Validator.isNotNull(portWharfNameVN)
					&& portWharfNameVN.trim().length() > 0) {
				builder.appendNamedParameterMap("portWharfNameVN","%" + portWharfNameVN.trim() + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}
			// execute the query and return a list from the db
			return (List<DmPortWharf>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}



	public List<DmPortWharf> findDanhSachDmPortWharf(String portRegionCode,
													 String portHarbourCode, String PortWharfNameVN,
													 Integer portWharfPayment, boolean ascOrdesc, int start, int end)
			throws SystemException {
		try {

			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port_wharf WHERE 1= 1   ");

			if (Validator.isNotNull(portRegionCode)
					&& portRegionCode.trim().length() > 0) {
				query.append(" and PortRegionCode = '" + portRegionCode + "'");
			}

			if (Validator.isNotNull(portHarbourCode)
					&& portHarbourCode.trim().length() > 0) {
				query.append(" and (PortHarbourCode is null or PortHarbourCode = '"
						+ portHarbourCode + "')");
			}

			if (Validator.isNotNull(PortWharfNameVN)
					&& PortWharfNameVN.trim().length() > 0) {
				query.append(" and (PortWharfNameVN is null or PortWharfNameVN like '%"
						+ PortWharfNameVN + "%')");
			}

			if (portWharfPayment != null) {
				query.append(" and PortWharfPayment = " + portWharfPayment);
			}

			if (ascOrdesc == true) {
				query.append(" order by PortCode asc");
			} else {
				query.append(" order by PortCode desc");
			}

			String sql = query.toString();

			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql)
					.firstResult(start).maxResults(end - start).entityClass(DmPortWharf.class).build();

			// execute the query and return a list from the db
			return (List<DmPortWharf>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}



	public List<DmPortWharf> findByPortRegionCodeOrderPortCode(
			String portRegionCode, boolean ascOrdesc) throws SystemException {
		try {
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port_wharf WHERE 1 = 1 ");

			if (Validator.isNotNull(portRegionCode)
					&& portRegionCode.trim().length() > 0) {
				query.append("and PortRegionCode = '" + portRegionCode + "'");
			}

			if (ascOrdesc == true) {
				query.append(" order by PortCode asc");
			} else {
				query.append(" order by PortCode desc");
			}

			String sql = query.toString();
			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(DmPortWharf.class).build();


			return (List<DmPortWharf>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}


	public List<DmPortWharf> findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(
			String portRegionCode, String portHarbourCode, boolean ascOrdesc)
			throws SystemException {
		
		try {

			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port_wharf WHERE 1= 1   ");

			if (Validator.isNotNull(portRegionCode)
					&& portRegionCode.trim().length() > 0) {
				query.append(" and PortRegionCode = '" + portRegionCode + "'");
			}

			if (Validator.isNotNull(portHarbourCode)
					&& portHarbourCode.trim().length() > 0) {
				query.append(" and (PortHarbourCode is null or PortHarbourCode = '"
						+ portHarbourCode + "')");
			}

			if (ascOrdesc == true) {
				query.append(" order by PortCode asc");
			} else {
				query.append(" order by PortCode desc");
			}

			String sql = query.toString();

			log.debug("========findByPortHarbourCodeOrNullOrderPortCode======"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(DmPortWharf.class).build();

			// execute the query and return a list from the db
			return (List<DmPortWharf>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public int countDanhSachDmPortWharf(String portRegionCode,
			String portHarbourCode, String PortWharfNameVN,
			Integer portWharfPayment) throws SystemException { int count = 0; try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT Count(id) as total  FROM dm_port_wharf WHERE 1= 1   ");

			if (Validator.isNotNull(portRegionCode)
					&& portRegionCode.trim().length() > 0) {
				query.append(" and PortRegionCode = '" + portRegionCode + "'");
			}

			if (Validator.isNotNull(portHarbourCode)
					&& portHarbourCode.trim().length() > 0) {
				query.append(" and (PortHarbourCode is null or PortHarbourCode = '"
						+ portHarbourCode + "')");
			}

			if (Validator.isNotNull(PortWharfNameVN)
					&& PortWharfNameVN.trim().length() > 0) {
				query.append(" and (PortWharfNameVN is null or PortWharfNameVN like '%"
						+ PortWharfNameVN + "%')");
			}

			if (portWharfPayment != null) {
				query.append(" and portWharfPayment = " + portHarbourCode);
			}

			String sql = query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();
		
			log.debug("========findDanhSachDmPortWharf======"
					+ query.toString());
			

			
			count = (Integer) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	public List<DmPortWharf> findDongBoDmPortWharf(String MaritimeCode,
			String portRegionCode, String portHarbourCode,
			String PortWharfNameVN, boolean ascOrdesc, int start, int end)
			throws SystemException {
		
		try {

			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port_wharf WHERE 1= 1   ");

			if (Validator.isNotNull(MaritimeCode)
					&& MaritimeCode.trim().length() > 0
					&& (!MaritimeCode.equalsIgnoreCase("---"))) {
				query.append(" and PortRegionCode in (Select distinct PortRegionCode from dm_port_region where PortRegionRef =  '"
						+ MaritimeCode + "')");
			}

			if (Validator.isNotNull(portRegionCode)
					&& portRegionCode.trim().length() > 0) {
				query.append(" and PortRegionCode = '" + portRegionCode + "'");
			}

			if (Validator.isNotNull(portHarbourCode)
					&& portHarbourCode.trim().length() > 0) {
				query.append(" and (PortHarbourCode is null or PortHarbourCode = '"
						+ portHarbourCode + "')");
			}

			if (Validator.isNotNull(PortWharfNameVN)
					&& PortWharfNameVN.trim().length() > 0) {
				query.append(" and (PortWharfNameVN is null or UPPER(PortWharfNameVN) like '%"
						+ PortWharfNameVN.trim().toUpperCase() + "%')");
			}

			if (ascOrdesc == true) {
				query.append(" order by id asc ");
			} else {
				query.append(" order by id desc");
			}

			String sql = query.toString();

			log.info("========findDanhSachDmPortWharf======"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(DmPortWharf.class).build();

			// execute the query and return a list from the db
			return (List<DmPortWharf>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public int countDongBoDmPortWharf(String MaritimeCode,
			String portRegionCode, String portHarbourCode,
			String PortWharfNameVN) throws SystemException {

		Long count = 0L;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT Count(id) as total FROM dm_port_wharf WHERE 1= 1   ");

			if (Validator.isNotNull(MaritimeCode)
					&& MaritimeCode.trim().length() > 0
					&& (!MaritimeCode.equalsIgnoreCase("---"))) {
				query.append(" and PortRegionCode in (Select distinct PortRegionCode from dm_port_region where PortRegionRef =  '"
						+ MaritimeCode + "')");
			}

			if (Validator.isNotNull(portRegionCode)
					&& portRegionCode.trim().length() > 0) {
				query.append(" and PortRegionCode = '" + portRegionCode + "'");
			}

			if (Validator.isNotNull(portHarbourCode)
					&& portHarbourCode.trim().length() > 0) {
				query.append(" and (PortHarbourCode is null or PortHarbourCode = '"
						+ portHarbourCode + "')");
			}

			if (Validator.isNotNull(PortWharfNameVN)
					&& PortWharfNameVN.trim().length() > 0) {
				query.append(" and (PortWharfNameVN is null or UPPER(PortWharfNameVN) like '%"
						+ PortWharfNameVN.trim().toUpperCase() + "%')");
			}
			String sql = query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();
			

			log.debug("========findDanhSachDmPortWharf======"
					+ query.toString());
			

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count.intValue();
	}

	public List<DmPortWharf> findByPortHarbourCodeOrNull(
			String portHarbourCode, boolean ascOrdesc) throws SystemException {
		
		try {

			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port_wharf WHERE 1 = 1 ");

			if (Validator.isNotNull(portHarbourCode)
					&& portHarbourCode.trim().length() > 0) {
				query.append(" and (PortHarbourCode is null or PortHarbourCode = '"
						+ portHarbourCode + "')");
			}

			if (ascOrdesc == true) {
				query.append(" order by PortCode asc");
			} else {
				query.append(" order by PortCode desc");
			}

			String sql = query.toString();

			log.debug("========findByPortHarbourCodeOrNullOrderPortCode======"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(DmPortWharf.class).build();

			// execute the query and return a list from the db
			return (List<DmPortWharf>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countPortWharfs(String maritimeCode, String portRegionCode,
			String portHarbourCode, String portWharfNameVN, String isDelete,
			String portWharfCodeGroup) throws SystemException { long count = 0; try {

			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM dm_port_wharf WHERE 1= 1   ");

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" and PortRegionCode in (Select distinct PortRegionCode from dm_port_region where PortRegionRef =  ? )");
			}
			if (Validator.isNotNull(portRegionCode)
					&& portRegionCode.trim().length() > 0) {
				query.append(" AND PortRegionCode= :portRegionCode ");
			}
			if (Validator.isNotNull(portHarbourCode)
					&& portHarbourCode.trim().length() > 0) {
				query.append(" AND PortHarbourCode= :portHarbourCode ");
			}
			if (Validator.isNotNull(portWharfNameVN)
					&& portWharfNameVN.trim().length() > 0) {
				query.append(" and PortWharfNameVN like ? ");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete ");
			}
			if (Validator.isNotNull(portWharfCodeGroup)
					&& !portWharfCodeGroup.isEmpty()) {
				portWharfCodeGroup = portWharfCodeGroup.replace(",", "', '");
				query.append(" AND PortWharfCode IN ('" + portWharfCodeGroup
						+ "')");
			}

			String sql = query.toString();

			log.debug("========findDanhSachDmPortWharf======"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();
			
			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(portRegionCode)
					&& portRegionCode.trim().length() > 0) {
				builder.appendNamedParameterMap("portRegionCode",portRegionCode.trim());
			}
			if (Validator.isNotNull(portHarbourCode)
					&& portHarbourCode.trim().length() > 0) {
				builder.appendNamedParameterMap("portHarbourCode",portHarbourCode.trim());
			}
			if (Validator.isNotNull(portWharfNameVN)
					&& portWharfNameVN.trim().length() > 0) {
				builder.appendNamedParameterMap("portWharfNameVN","%" + portWharfNameVN.trim() + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	public long getMaxSequenceNo(String portRegionCode, String portHarbourCode)
			throws SystemException {
		long count = 0;
		try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT MAX(SequenceNo) AS total FROM dm_port_wharf WHERE 1=1 ";

			if (Validator.isNotNull(portRegionCode)
					&& portRegionCode.trim().length() > 0) {
				query.append(" AND portRegionCode= :portRegionCode ");
			}
			if (Validator.isNotNull(portHarbourCode)
					&& portHarbourCode.trim().length() > 0) {
				query.append(" AND PortHarbourCode= :portHarbourCode ");
			}
			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

			

			if (Validator.isNotNull(portRegionCode)
					&& portRegionCode.trim().length() > 0) {
				builder.appendNamedParameterMap("portRegionCode",portRegionCode.trim());
			}
			if (Validator.isNotNull(portHarbourCode)
					&& portHarbourCode.trim().length() > 0) {
				builder.appendNamedParameterMap("portHarbourCode",portHarbourCode);
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	public List<BC12BTVinalinesModel> getModelMau12BTVinalines(
			String maritimeCode, String startDate, String endDate)
			throws SystemException {

		
		try {
			

			String query = "Select temp.*, temp2.khoi_luong_hang, temp2.hang_tau_bien, temp2.hang_pttnd, temp1.luot_tau, temp1.luot_tau_bien, temp1.luot_pttnd from (Select wharf.PortWharfCode as PortWharfCode, concat(region.PortRegionName,' ', harbour.PortHarbourName, ' ', wharf.PortWharfName) as ten_cang from dm_port_wharf as wharf INNER JOIN dm_port_region as region ON region.PortRegionRef = "
					+ maritimeCode
					+ " and region.IsDelete = 0 and region.PortRegionCode = wharf.PortRegionCode inner join dm_port_harbour as harbour ON harbour.PortRegion = wharf.PortRegionCode and harbour.PortHarbourCode = wharf.PortHarbourCode and region.PortRegionRef = "
					+ maritimeCode
					+ " and harbour.IsDelete=0 and region.IsDelete=0 where wharf.IsDelete = 0 and wharf.ManagedVinalines = 1 order by wharf.SequenceNo ) as temp left join ( select sum(Quantity) as khoi_luong_hang, sum(case when VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then Quantity when VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then Quantity else 0 end) as hang_tau_bien, sum(case when VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then Quantity when VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then Quantity else 0 end) as hang_pttnd, tb4.PortWharfCode as PortWharfCode_ from vma_schedule_cargolist as tb4 inner join vma_itinerary as tb3 on tb4.ItineraryNo = tb3.ItineraryNo where tb4.PortWharfCode is not null and ( (tb4.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4') and tb3.MarkedAsArrival not in (0,1,6)) or (tb4.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4') and tb3.MarkedAsDeparture not in (0,1,6)) ) and tb4.PortWharfCode in (select PortWharfCode from dm_port_wharf where IsDelete = 0 and PortWharfType = 1) and tb3.MaritimeCode = "
					+ maritimeCode
					+ " and (case tb4.CargoCategory when 'C2_DO' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C3_DO' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C4' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C5' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C1_XEP' then tb3.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59' when 'C3_XEP' then tb3.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59' when 'C4' then tb3.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59' else null end) group by PortWharfCode_) as temp2 on temp.PortWharfCode = temp2.PortWharfCode_ left join (select vma_itinerary_schedule.PortWharfCode as PortWharfCode_temp1, sum(case when vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau, sum(case when VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien, sum(case when VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_pttnd from vma_itinerary_schedule inner join vma_itinerary on vma_itinerary_schedule.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.MarkedAsArrival not in (0,1,6)) or (vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.MarkedAsDeparture not in (0,1,6)) ) and MaritimeCode = "
					+ maritimeCode
					+ " AND (case when vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59' else null end) ) as temp1 on temp.PortWharfCode = temp1.PortWharfCode_temp1";

			log.info("=========select bao cao mau_23_12bt_vinalines>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();
Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			List<BC12BTVinalinesModel> bc12btVinalinesModels = new ArrayList<BC12BTVinalinesModel>();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					BC12BTVinalinesModel bc12btVinalinesModel = new BC12BTVinalinesModel();

					String portWharfCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String ten_cang = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					double khoi_luong_hang = Double.valueOf(String
							.valueOf(objects[2] != null ? objects[2] : -1));
					double hang_tau_bien = Double.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					double hang_pttnd = Double.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : -1));
					int luot_tau = Integer.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					int luot_tau_bien = Integer.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));
					int luot_pttnd = Integer.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : -1));

					bc12btVinalinesModel.setPortWharfCode(portWharfCode);
					bc12btVinalinesModel.setTen_cang(ten_cang);
					bc12btVinalinesModel.setKhoi_luong_hang(khoi_luong_hang);
					bc12btVinalinesModel.setHang_tau_bien(hang_tau_bien);
					bc12btVinalinesModel.setHang_pttnd(hang_pttnd);
					bc12btVinalinesModel.setLuot_tau(luot_tau);
					bc12btVinalinesModel.setLuot_tau_bien(luot_tau_bien);
					bc12btVinalinesModel.setLuot_pttnd(luot_pttnd);

					bc12btVinalinesModels.add(bc12btVinalinesModel);
				}
			}

			return bc12btVinalinesModels;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONArray getModelMau60(String maritimeCode, String startDate,
			String endDate) throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "select v1.MaritimeCode , v2.AnchoringPortRegionCode , v1.ItineraryNo , v1.NameOfShip as Ten_tau , v3.StateName as Quoc_tich , v1.ArrivalShipAgency as Dai_ly , v4.PortHarbourName as Ben_cang , v5.PortWharfName as Cau_phao , DATE_FORMAT(v1.TimeOfArrival,'%Y-%m-%d') as Ngay_den , v2.AnchoringDateFrom as Ngay_gio_vao_cau from vma_itinerary as v1 LEFT JOIN vma_schedule_anchorage as v2 on v1.ItineraryNo = v2.ItineraryNo LEFT JOIN dm_state as v3 on v1.FlagStateOfShip = v3.StateCode LEFT JOIN dm_port_harbour as v4 on v2.AnchoringPortHarbourCode = v4.PortHarbourCode LEFT JOIN dm_port_wharf as v5 on v2.AnchoringPortWharfCode = v5.PortWharfCode where (v2.AnchoringDateFrom>='"
					+ startDate
					+ "' and v2.AnchoringDateFrom<='"
					+ endDate
					+ "') and v2.AnchoringDateTo>'"
					+ endDate
					+ "' and v1.MaritimeCode = "
					+ maritimeCode
					+ " GROUP BY v2.ItineraryNo;";

			log.info("=========select bao cao mau_60t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();
Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				int i = 1;
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					JSONObject result = JSONFactoryUtil.createJSONObject();
					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String anchoringPortRegionCode = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					String itineraryNo = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					String ten_tau = String
							.valueOf(objects[3] != null ? objects[3]
									: StringPool.BLANK);
					String quoc_tich = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					String dai_ly = String
							.valueOf(objects[5] != null ? objects[5]
									: StringPool.BLANK);
					String ben_cang = String
							.valueOf(objects[6] != null ? objects[6]
									: StringPool.BLANK);
					String cau_phao = String
							.valueOf(objects[7] != null ? objects[7]
									: StringPool.BLANK);
					String ngay_den = String
							.valueOf(objects[8] != null ? objects[8]
									: StringPool.BLANK);
					String ngay_gio_vao_cau = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);

					result.put("A", i);
					result.put("B", ten_tau);
					result.put("C", quoc_tich);
					result.put("D", dai_ly);
					result.put("E", ben_cang);
					result.put("F", cau_phao);
					result.put("G", ngay_den);
					result.put("H", ngay_gio_vao_cau);

					results.put(result);
					i++;
				}
			}

			return results;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONArray getModelMau58S(String maritimeCode, String startDate,
			String endDate) throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "select * from( (select v2.MaritimeCode , v10.PortRegionCode , v10.PortRegionName , v1.ItineraryNo , v1.NameOfShip as Ten_tau , v1.DWT , v1.LOA , CONCAT(v1.ShownDraftxF,'/',v1.ShownDraftxA) as Mon_nuoc , '' as Tu , v7.PortWharfNameVN as Den , vv.Tau_lai , v31.ShipAgencyName as Dai_ly , vvv.Hoa_tieu , v1.maritimeRemarks as Ghi_chu , v1.TimeOfArrival as Thoi_gian from vma_itinerary_schedule as v1 LEFT JOIN vma_itinerary as v2 ON v1.ItineraryNo = v2.ItineraryNo LEFT JOIN dm_port_region as v10 on v1.PortRegionCode = v10.PortRegionCode LEFT JOIN dm_ship_agency as v31 on v2.ArrivalShipAgencyCode = v31.ShipAgencyCode LEFT JOIN dm_port_wharf as v7 on v1.PortWharfCode = v7.PortWharfCode LEFT JOIN (select v1.ItineraryNo, v1.SequenceNo , GROUP_CONCAT(v2.ShipName) as Tau_lai from vma_schedule_tugboat as v1 INNER JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.SequenceNo = v2.SequenceNo where NoticeShipType = 1 GROUP BY v1.ItineraryNo, v1.SequenceNo) as vv on v1.ItineraryNo = vv.ItineraryNo and vv.Tau_lai is not null LEFT JOIN (select v1.ItineraryNo, v1.SequenceNo , GROUP_CONCAT(v2.PilotName) as Hoa_tieu from vma_schedule_pilot as v1 INNER JOIN vma_schedule_pilot_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.SequenceNo = v2.SequenceNo where NoticeShipType = 1 GROUP BY v1.ItineraryNo, v1.SequenceNo) as vvv on v1.ItineraryNo = vvv.ItineraryNo and vvv.Hoa_tieu is not null where v1.NoticeShipType = 1 and v2.MaritimeCode = "
					+ maritimeCode
					+ " and (v1.TimeOfArrival>='"
					+ startDate
					+ "' and v1.TimeOfArrival<='"
					+ endDate
					+ "') GROUP BY v1.ItineraryNo) UNION (select v2.MaritimeCode , v10.PortRegionCode , v10.PortRegionName , v1.ItineraryNo , v1.NameOfShip as Ten_tau , v1.DWT , v1.LOA , CONCAT(v1.ShownDraftxF,'/',v1.ShownDraftxA) as Mon_nuoc , v7.PortWharfNameVN as Tu , '' as Den , vv.Tau_lai , v31.ShipAgencyName as Dai_ly , vvv.Hoa_tieu , v1.maritimeRemarks as Ghi_chu , v1.TimeOfDeparture as Thoi_gian from vma_itinerary_schedule as v1 LEFT JOIN vma_itinerary as v2 ON v1.ItineraryNo = v2.ItineraryNo LEFT JOIN dm_port_region as v10 on v1.PortRegionCode = v10.PortRegionCode LEFT JOIN dm_ship_agency as v31 on v2.DepartureShipAgencyCode = v31.ShipAgencyCode LEFT JOIN dm_port_wharf as v7 on v1.PortWharfCode = v7.PortWharfCode LEFT JOIN (select v1.ItineraryNo, v1.SequenceNo , GROUP_CONCAT(v2.ShipName) as Tau_lai from vma_schedule_tugboat as v1 INNER JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.SequenceNo = v2.SequenceNo where NoticeShipType = 2 GROUP BY v1.ItineraryNo, v1.SequenceNo) as vv on v1.ItineraryNo = vv.ItineraryNo and vv.Tau_lai is not null LEFT JOIN (select v1.ItineraryNo, v1.SequenceNo , GROUP_CONCAT(v2.PilotName) as Hoa_tieu from vma_schedule_pilot as v1 INNER JOIN vma_schedule_pilot_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.SequenceNo = v2.SequenceNo where NoticeShipType = 2 GROUP BY v1.ItineraryNo, v1.SequenceNo) as vvv on v1.ItineraryNo = vvv.ItineraryNo and vvv.Hoa_tieu is not null where v1.NoticeShipType = 2 and v2.MaritimeCode = "
					+ maritimeCode
					+ " and (v1.TimeOfDeparture>='"
					+ startDate
					+ "' and v1.TimeOfDeparture<='"
					+ endDate
					+ "') GROUP BY v1.ItineraryNo) UNION (select v2.MaritimeCode , v11.PortRegionCode , v11.PortRegionName , v1.ItineraryNo , v1.NameOfShip as Ten_tau , v3.DWT as DWT , v3.LOA as LOA , CONCAT(v1.ShownDraftxF,'/',v1.ShownDraftxA) as Mon_nuoc , v8.PortWharfName as Tu , v10.PortWharfName as Den , v4.Tau_lai as Tau_lai , v1.NameOfShipownersAgents as Dai_ly , v5.Hoa_tieu as Hoa_tieu , v1.Remarks as Ghi_chu , v1.ShiftingDate as Thoi_gian from vma_schedule_shifting as v1 LEFT JOIN vma_itinerary as v2 on v1.ItineraryNo = v2.ItineraryNo LEFT JOIN vma_ship as v3 on (v2.CallSign = v3.CallSign and v2.CallSign is not null ) or (v2.IMONumber = v3.IMONumber and v2.IMONumber is not null) LEFT JOIN (SELECT ItineraryNo, GROUP_CONCAT(ShipName) as Tau_lai, SequenceNo from vma_schedule_tugboat_list GROUP BY ItineraryNo) as v4 on v1.ItineraryNo = v4.ItineraryNo and v1.CertificateNo = v4.SequenceNo LEFT JOIN (SELECT ItineraryNo, GROUP_CONCAT(PilotName) as Hoa_tieu, SequenceNo from vma_schedule_pilot_list GROUP BY ItineraryNo) as v5 on v1.ItineraryNo = v5.ItineraryNo and v1.CertificateNo = v5.SequenceNo LEFT JOIN dm_port_wharf as v8 on v1.AnchoringPortWharfCode = v8.PortWharfCode LEFT JOIN dm_port_region as v11 on v8.PortRegionCode = v11.PortRegionCode LEFT JOIN dm_port_wharf as v10 on v1.ShiftingPortWharfCode = v10.PortWharfCode where v2.MaritimeCode = "
					+ maritimeCode
					+ " and (v1.ShiftingDate>='"
					+ startDate
					+ "' and v1.ShiftingDate<='" + endDate + "')) ) as bang";

			log.info("=========select bao cao mau_58s>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();
Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					JSONObject result = JSONFactoryUtil.createJSONObject();
					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String portRegionCode = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					String portRegionName = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					String itineraryNo = String
							.valueOf(objects[3] != null ? objects[3]
									: StringPool.BLANK);
					String ten_tau = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					double dwt = Double.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					double loa = Double.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					String mon_nuoc = String
							.valueOf(objects[6] != null ? objects[6]
									: StringPool.BLANK);
					String tu = String.valueOf(objects[7] != null ? objects[7]
							: StringPool.BLANK);
					String den = String.valueOf(objects[8] != null ? objects[8]
							: StringPool.BLANK);
					String tau_lai = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);
					String dai_ly = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);
					String hoa_tieu = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);
					String ghi_chu = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);
					String thoi_gian = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);

					result.put("portRegionCode", portRegionCode);
					result.put("A", portRegionName);
					result.put("B", thoi_gian);
					result.put("C", ten_tau);
					result.put("D", mon_nuoc);
					result.put("E", loa >= 0 ? loa : null);
					result.put("F", dwt >= 0 ? dwt : null);
					result.put("G", tau_lai);
					result.put("H", tu);
					result.put("I", den);
					result.put("K", hoa_tieu);
					result.put("L", dai_ly);

					results.put(result);
				}
			}

			return results;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
}
