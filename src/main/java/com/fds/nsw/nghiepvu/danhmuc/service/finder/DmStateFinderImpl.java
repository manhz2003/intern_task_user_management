package com.fds.nsw.nghiepvu.danhmuc.service.finder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.*;

import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONArray;
import org.json.JSONObject;
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
import com.fds.nsw.nghiepvu.model.DmState;
@Service
@Slf4j
public class DmStateFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmState> queryFactory;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;


	public List<DmState> findStates(String stateName, String isDelete,
									String stateCodeGroup, int start, int end) throws SystemException {
		try {
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_state WHERE 1 = 1 ");

			if (Validator.isNotNull(stateName) && !stateName.isEmpty()) {
				query.append(" AND StateName LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}
			if (Validator.isNotNull(stateCodeGroup)
					&& !stateCodeGroup.isEmpty()) {
				stateCodeGroup = stateCodeGroup.replace(",", "', '");
				query.append(" AND StateCode IN ('" + stateCodeGroup + "')");
			}

			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(query.toString())
					.firstResult(start).maxResults(end - start).entityClass(DmState.class).build();

			if (Validator.isNotNull(stateName) && !stateName.isEmpty()) {
				builder.appendNamedParameterMap("stateName","%" + stateName + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			return (List<DmState>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}


	public List<DmState> getAllOrderByName() throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_state ");
			query.append("ORDER BY CONVERT(StateName USING utf8) COLLATE utf8_polish_ci");

			log.debug("===getAllOrderByName===" + query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(DmState.class).build();

			

			return (List<DmState>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countStates(String stateName, String isDelete,
			String stateCodeGroup) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM dm_state WHERE 1 = 1 ");

			if (Validator.isNotNull(stateName) && !stateName.isEmpty()) {
				query.append(" AND StateName LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}
			if (Validator.isNotNull(stateCodeGroup)
					&& !stateCodeGroup.isEmpty()) {
				stateCodeGroup = stateCodeGroup.replace(",", "', '");
				query.append(" AND StateCode IN ('" + stateCodeGroup + "')");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();
			
			

			if (Validator.isNotNull(stateName) && !stateName.isEmpty()) {
				builder.appendNamedParameterMap("stateName","%" + stateName + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	public JSONArray getModelMau53_54T(String maritimeCode, String nameOfShip,
			String imoNumber, String registryNumber, String vrCode,
			String flagStateOfShip, String from_gt, String to_gt,
			String from_dwt, String to_dwt, String from_loa, String to_loa,
			String lastPortCode, String nextPortCode, String arrivalShipAgency,
			String departureShipAgency, String cargoType, String cargoCategory,
			String callSign, String startDate, String endDate)
			throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "select temp3.*, GROUP_CONCAT(DISTINCT temp4.ten_hang_den SEPARATOR ', ') as ten_hang_den, GROUP_CONCAT(DISTINCT temp4.ten_hang_roi SEPARATOR ', ') as ten_hang_roi from (select temp1.ItineraryNo, temp1.NameOfShip, temp1.RegistryNumber, temp1.VRCode, temp1.ShipTypeName, temp1.StateName, temp1.LOA, temp1.DWT, temp1.GT, (case when temp1.LastPortCode is not null and temp1.LastPortCode <> '' then (select PortName from dm_port where PortCode = temp1.LastPortCode) else '' end) as cang_roi, (case when temp1.ArrivalPortCode is not null and temp1.ArrivalPortCode <> '' then (select PortName from dm_port where PortCode = temp1.ArrivalPortCode) else '' end) as cang_den, (case when temp1.NextPortCode is not null and temp1.NextPortCode <> '' then (select PortName from dm_port where PortCode = temp1.NextPortCode) else '' end) as cang_dich, temp1.dai_ly_den, temp1.dai_ly_roi, temp1.ngay_den, temp2.ngay_roi from (select vma_itinerary.ItineraryNo as ItineraryNo, vma_itinerary.NameOfShip as NameOfShip, vma_itinerary.RegistryNumber as RegistryNumber, vma_itinerary.VRCode as VRCode, dm_ship_type.ShipTypeName as ShipTypeName, dm_state.StateName as StateName, vma_itinerary_schedule.LOA as LOA , vma_itinerary_schedule.DWT as DWT , vma_itinerary_schedule.GT as GT, vma_itinerary.LastPortCode as LastPortCode, vma_itinerary.ArrivalPortCode as ArrivalPortCode, vma_itinerary.NextPortCode as NextPortCode, vma_itinerary.TimeOfArrival as ngay_den, vma_itinerary.ArrivalShipAgency as dai_ly_den, vma_itinerary.DepartureShipAgency as dai_ly_roi from vma_itinerary inner join dm_ship_type on vma_itinerary.ShipTypeCode = dm_ship_type.ShipTypeCode inner join vma_itinerary_schedule on vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo inner join dm_state on dm_state.StateCode = vma_itinerary.FlagStateOfShip where vma_itinerary.VRCode = 'VR-SB' and vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.MaritimeCode = "
					+ maritimeCode
					+ " AND vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' AND (case when '"
					+ nameOfShip
					+ "' <> '' then vma_itinerary.NameOfShip like concat('%','"
					+ nameOfShip
					+ "','%') else 1 end) AND (case when '"
					+ imoNumber
					+ "' <> '' then vma_itinerary.IMONumber like concat('%','"
					+ imoNumber
					+ "','%') else 1 end) AND (case when '"
					+ callSign
					+ "' <> '' then vma_itinerary.CallSign like concat('%','"
					+ callSign
					+ "','%') else 1 end) AND (case when '"
					+ registryNumber
					+ "' <> '' then vma_itinerary.RegistryNumber like concat('%','"
					+ registryNumber
					+ "','%') else 1 end) AND (case when '"
					+ vrCode
					+ "' <> '' then vma_itinerary.VRCode = '"
					+ vrCode
					+ "' else 1 end) AND (case when '"
					+ flagStateOfShip
					+ "' <> '' then vma_itinerary.FlagStateOfShip = '"
					+ flagStateOfShip
					+ "' else 1 end) AND (case when '"
					+ from_gt
					+ "' <> '' then vma_itinerary_schedule.GT >= '"
					+ from_gt
					+ "' else 1 end) AND (case when '"
					+ to_gt
					+ "' <> '' then vma_itinerary_schedule.GT <= '"
					+ to_gt
					+ "' else 1 end) AND (case when '"
					+ from_dwt
					+ "' <> '' then vma_itinerary_schedule.DWT >= '"
					+ from_dwt
					+ "' else 1 end) AND (case when '"
					+ to_dwt
					+ "' <> '' then vma_itinerary_schedule.DWT <= '"
					+ to_dwt
					+ "' else 1 end) AND (case when '"
					+ from_loa
					+ "' <> '' then vma_itinerary_schedule.LOA >= '"
					+ from_loa
					+ "' else 1 end) AND (case when '"
					+ to_loa
					+ "' <> '' then vma_itinerary_schedule.LOA <= '"
					+ to_loa
					+ "' else 1 end) AND (case when '"
					+ lastPortCode
					+ "' <> '' then vma_itinerary.LastPortCode = '"
					+ lastPortCode
					+ "' else 1 end) AND (case when '"
					+ nextPortCode
					+ "' <> '' then vma_itinerary.NextPortCode = '"
					+ nextPortCode
					+ "' else 1 end) AND (case when '"
					+ arrivalShipAgency
					+ "' <> '' then vma_itinerary.ArrivalShipAgency = '"
					+ arrivalShipAgency
					+ "' else 1 end) AND (case when '"
					+ departureShipAgency
					+ "' <> '' then vma_itinerary.DepartureShipAgency = '"
					+ departureShipAgency
					+ "' else 1 end) group by vma_itinerary.ItineraryNo ) as temp1 inner join (select vma_itinerary.ItineraryNo as ItineraryNo , vma_itinerary_schedule.TimeOfDeparture as ngay_roi from vma_itinerary inner join vma_itinerary_schedule on vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo where vma_itinerary.VRCode = 'VR-SB' and vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.MaritimeCode = "
					+ maritimeCode
					+ " AND vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' group by vma_itinerary.ItineraryNo ) as temp2 on temp1.ItineraryNo = temp2.ItineraryNo ) as temp3 inner join ( select vma_itinerary.ItineraryNo as ItineraryNo, (case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4_DO') and vma_itinerary.MarkedAsArrival not in (0,1,6) then dm_dataitem.name else null end) as ten_hang_den, (case when vma_schedule_cargolist.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4_XEP') and vma_itinerary.MarkedAsDeparture not in (0,1,6) then dm_dataitem.name else null end) as ten_hang_roi from vma_itinerary left join (vma_schedule_cargolist inner join dm_dataitem on dm_dataitem.code = vma_schedule_cargolist.CargoCode and dm_dataitem.datagroupid = '124') on vma_schedule_cargolist.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4_DO') and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.LastPortCode is not null) or (vma_schedule_cargolist.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4_XEP') and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.NextPortCode is not null) ) and MaritimeCode = "
					+ maritimeCode
					+ " and (case vma_schedule_cargolist.CargoCategory when 'C2_DO' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C3_DO' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C4_DO' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C5' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C1_XEP' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' when 'C3_XEP' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' when 'C4_XEP' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' else null end) AND (case when '"
					+ cargoType
					+ "' <> '' then vma_schedule_cargolist.CargoType = '"
					+ cargoType
					+ "' else 1 end) AND (case when '"
					+ cargoCategory
					+ "' <> '' then vma_schedule_cargolist.CargoCategory = '"
					+ cargoCategory
					+ "' else 1 end) ) as temp4 on temp3.ItineraryNo = temp4.ItineraryNo group by temp3.ItineraryNo";

			log.info("=========select bao cao mau53_54t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();
Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				int i = 1;
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					JSONObject result = JSONFactoryUtil.createJSONObject();

					String itineraryNo = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					nameOfShip = String.valueOf(objects[1] != null ? objects[1]
							: StringPool.BLANK);
					registryNumber = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					vrCode = String.valueOf(objects[3] != null ? objects[3]
							: StringPool.BLANK);
					String shipTypeName = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					String stateName = String
							.valueOf(objects[5] != null ? objects[5]
									: StringPool.BLANK);
					double loa = Double.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : null));
					double dwt = Double.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : null));
					double gt = Double.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : null));
					String cang_roi = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);
					String cang_den = String
							.valueOf(objects[10] != null ? objects[10]
									: StringPool.BLANK);
					String cang_dich = String
							.valueOf(objects[11] != null ? objects[11]
									: StringPool.BLANK);
					String dai_ly_den = String
							.valueOf(objects[12] != null ? objects[12]
									: StringPool.BLANK);
					String dai_ly_roi = String
							.valueOf(objects[13] != null ? objects[13]
									: StringPool.BLANK);
					String ngay_den = String
							.valueOf(objects[14] != null ? objects[14]
									: StringPool.BLANK);
					String ngay_roi = String
							.valueOf(objects[15] != null ? objects[15]
									: StringPool.BLANK);
					String ten_hang_den = String
							.valueOf(objects[16] != null ? objects[16]
									: StringPool.BLANK);
					String ten_hang_roi = String
							.valueOf(objects[17] != null ? objects[17]
									: StringPool.BLANK);

					result.put("A", i);
					result.put("B", nameOfShip);
					result.put("C", StringPool.BLANK);
					result.put("D", loa >= 0 ? loa : null);
					result.put("E", StringPool.BLANK);
					result.put("F", stateName);
					result.put("G", shipTypeName);
					result.put("H", ngay_roi);
					result.put("I", dai_ly_roi);
					result.put("K", dai_ly_den);
					result.put("L", cang_dich);
					result.put("M", cang_roi);
					result.put("N", cang_dich);
					result.put("O", dwt >= 0 ? dwt : null);
					result.put("P", gt >= 0 ? gt : null);
					result.put("Q", ngay_den);
					result.put("R", ten_hang_roi);
					result.put("S", ten_hang_den);

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

	public JSONArray getModelMau70_78T(String maritimeCode, String nameOfShip,
			String imoNumber, String registryNumber, String vrCode,
			String flagStateOfShip, String from_gt, String to_gt,
			String from_dwt, String to_dwt, String from_loa, String to_loa,
			String lastPortCode, String nextPortCode, String arrivalShipAgency,
			String departureShipAgency, String cargoType, String cargoCategory,
			String callSign, String anchoringPortHarbourCode,
			String anchoringPortWharfCode, String shiftingPortHarbourCode,
			String shiftingPortWharfCode, String startDate, String endDate)
			throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "select temp5.* from (select temp3.*, GROUP_CONCAT(DISTINCT temp4.ten_hang_den SEPARATOR ', ') as ten_hang_den, GROUP_CONCAT(DISTINCT temp4.ten_hang_roi SEPARATOR ', ') as ten_hang_roi from (select temp1.ItineraryNo, temp1.NameOfShip, temp1.RegistryNumber, temp1.VRCode, temp1.ShipTypeName, temp1.StateName, temp1.LOA, temp1.DWT, temp1.GT, (case when temp1.LastPortCode is not null and temp1.LastPortCode <> '' then (select PortName from dm_port where PortCode = temp1.LastPortCode) else '' end) as cang_roi, (case when temp1.ArrivalPortCode is not null and temp1.ArrivalPortCode <> '' then (select PortName from dm_port where PortCode = temp1.ArrivalPortCode) else '' end) as cang_den, (case when temp1.NextPortCode is not null and temp1.NextPortCode <> '' then (select PortName from dm_port where PortCode = temp1.NextPortCode) else '' end) as cang_dich, temp1.dai_ly_den, temp1.dai_ly_roi, temp1.ngay_den, temp2.ngay_roi from (select vma_itinerary.ItineraryNo as ItineraryNo, vma_itinerary.NameOfShip as NameOfShip, vma_itinerary.RegistryNumber as RegistryNumber, vma_itinerary.VRCode as VRCode, dm_ship_type.ShipTypeName as ShipTypeName, dm_state.StateName as StateName, vma_itinerary_schedule.LOA as LOA , vma_itinerary_schedule.DWT as DWT , vma_itinerary_schedule.GT as GT, vma_itinerary.LastPortCode as LastPortCode, vma_itinerary.ArrivalPortCode as ArrivalPortCode, vma_itinerary.NextPortCode as NextPortCode, vma_itinerary.TimeOfArrival as ngay_den, vma_itinerary.ArrivalShipAgency as dai_ly_den, vma_itinerary.DepartureShipAgency as dai_ly_roi from vma_itinerary inner join dm_ship_type on vma_itinerary.ShipTypeCode = dm_ship_type.ShipTypeCode inner join vma_itinerary_schedule on vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo inner join dm_state on dm_state.StateCode = vma_itinerary.FlagStateOfShip where vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.MaritimeCode = "
					+ maritimeCode
					+ " AND vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' AND (case when '"
					+ nameOfShip
					+ "' <> '' then vma_itinerary.NameOfShip like concat('%','"
					+ nameOfShip
					+ "','%') else 1 end) AND (case when '"
					+ imoNumber
					+ "' <> '' then vma_itinerary.IMONumber like concat('%','"
					+ imoNumber
					+ "','%') else 1 end) AND (case when '"
					+ callSign
					+ "' <> '' then vma_itinerary.CallSign like concat('%','"
					+ callSign
					+ "','%') else 1 end) AND (case when '"
					+ registryNumber
					+ "' <> '' then vma_itinerary.RegistryNumber like concat('%','"
					+ registryNumber
					+ "','%') else 1 end) AND (case when '"
					+ vrCode
					+ "' <> '' then vma_itinerary.VRCode = '"
					+ vrCode
					+ "' else 1 end) AND (case when '"
					+ flagStateOfShip
					+ "' <> '' then vma_itinerary.FlagStateOfShip = '"
					+ flagStateOfShip
					+ "' else 1 end) AND (case when '"
					+ from_gt
					+ "' <> '' then vma_itinerary_schedule.GT >= '"
					+ from_gt
					+ "' else 1 end) AND (case when '"
					+ to_gt
					+ "' <> '' then vma_itinerary_schedule.GT <= '"
					+ to_gt
					+ "' else 1 end) AND (case when '"
					+ from_dwt
					+ "' <> '' then vma_itinerary_schedule.DWT >= '"
					+ from_dwt
					+ "' else 1 end) AND (case when '"
					+ to_dwt
					+ "' <> '' then vma_itinerary_schedule.DWT <= '"
					+ to_dwt
					+ "' else 1 end) AND (case when '"
					+ from_loa
					+ "' <> '' then vma_itinerary_schedule.LOA >= '"
					+ from_loa
					+ "' else 1 end) AND (case when '"
					+ to_loa
					+ "' <> '' then vma_itinerary_schedule.LOA <= '"
					+ to_loa
					+ "' else 1 end) AND (case when '"
					+ lastPortCode
					+ "' <> '' then vma_itinerary.LastPortCode = '"
					+ lastPortCode
					+ "' else 1 end) AND (case when '"
					+ nextPortCode
					+ "' <> '' then vma_itinerary.NextPortCode = '"
					+ nextPortCode
					+ "' else 1 end) AND (case when '"
					+ arrivalShipAgency
					+ "' <> '' then vma_itinerary.ArrivalShipAgency = '"
					+ arrivalShipAgency
					+ "' else 1 end) AND (case when '"
					+ departureShipAgency
					+ "' <> '' then vma_itinerary.DepartureShipAgency = '"
					+ departureShipAgency
					+ "' else 1 end) group by vma_itinerary.ItineraryNo ) as temp1 inner join (select vma_itinerary.ItineraryNo as ItineraryNo , vma_itinerary_schedule.TimeOfDeparture as ngay_roi from vma_itinerary inner join vma_itinerary_schedule on vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo where vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.MaritimeCode = "
					+ maritimeCode
					+ " AND vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' group by vma_itinerary.ItineraryNo ) as temp2 on temp1.ItineraryNo = temp2.ItineraryNo ) as temp3 inner join ( select vma_itinerary.ItineraryNo as ItineraryNo, (case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4_DO') and vma_itinerary.MarkedAsArrival not in (0,1,6) then dm_dataitem.name else null end) as ten_hang_den, (case when vma_schedule_cargolist.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4_XEP') and vma_itinerary.MarkedAsDeparture not in (0,1,6) then dm_dataitem.name else null end) as ten_hang_roi from vma_itinerary left join (vma_schedule_cargolist inner join dm_dataitem on dm_dataitem.code = vma_schedule_cargolist.CargoCode and dm_dataitem.datagroupid = '124') on vma_schedule_cargolist.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4_DO') and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.LastPortCode is not null) or (vma_schedule_cargolist.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4_XEP') and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.NextPortCode is not null) ) and MaritimeCode = "
					+ maritimeCode
					+ " and (case vma_schedule_cargolist.CargoCategory when 'C2_DO' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C3_DO' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C4_DO' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C5' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C1_XEP' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' when 'C3_XEP' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' when 'C4_XEP' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' else null end) ) as temp4 on temp3.ItineraryNo = temp4.ItineraryNo group by temp3.ItineraryNo) as temp5 inner join vma_schedule_anchorage on temp5.ItineraryNo = vma_schedule_anchorage.ItineraryNo where 1 AND (case when '"
					+ anchoringPortHarbourCode
					+ "' <> '' then vma_schedule_anchorage.AnchoringPortHarbourCode = '"
					+ anchoringPortHarbourCode
					+ "' else 1 end) AND (case when '"
					+ anchoringPortWharfCode
					+ "' <> '' then vma_schedule_anchorage.AnchoringPortWharfCode = '"
					+ anchoringPortWharfCode + "' else 1 end)";
			log.info("=========select bao cao mau70-78t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();
Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				int i = 1;
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					JSONObject result = JSONFactoryUtil.createJSONObject();

					String itineraryNo = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					nameOfShip = String.valueOf(objects[1] != null ? objects[1]
							: StringPool.BLANK);
					registryNumber = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					vrCode = String.valueOf(objects[3] != null ? objects[3]
							: StringPool.BLANK);
					String shipTypeName = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					String stateName = String
							.valueOf(objects[5] != null ? objects[5]
									: StringPool.BLANK);
					double loa = Double.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : null));
					double dwt = Double.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : null));
					double gt = Double.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : null));
					String cang_roi = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);
					String cang_den = String
							.valueOf(objects[10] != null ? objects[10]
									: StringPool.BLANK);
					String cang_dich = String
							.valueOf(objects[11] != null ? objects[11]
									: StringPool.BLANK);
					String dai_ly_den = String
							.valueOf(objects[12] != null ? objects[12]
									: StringPool.BLANK);
					String dai_ly_roi = String
							.valueOf(objects[13] != null ? objects[13]
									: StringPool.BLANK);
					String ngay_den = String
							.valueOf(objects[14] != null ? objects[14]
									: StringPool.BLANK);
					String ngay_roi = String
							.valueOf(objects[15] != null ? objects[15]
									: StringPool.BLANK);
					String ten_hang_den = String
							.valueOf(objects[16] != null ? objects[16]
									: StringPool.BLANK);
					String ten_hang_roi = String
							.valueOf(objects[17] != null ? objects[17]
									: StringPool.BLANK);

					result.put("A", i);
					result.put("B", nameOfShip);
					result.put("C", StringPool.BLANK);
					result.put("D", loa >= 0 ? loa : null);
					result.put("E", StringPool.BLANK);
					result.put("F", stateName);
					result.put("G", shipTypeName);
					result.put("H", ngay_roi);
					result.put("I", dai_ly_roi);
					result.put("K", dai_ly_den);
					result.put("L", cang_dich);
					result.put("M", cang_roi);
					result.put("N", cang_dich);
					result.put("O", dwt >= 0 ? dwt : null);
					result.put("P", gt >= 0 ? gt : null);
					result.put("Q", ngay_den);
					result.put("R", ten_hang_roi);
					result.put("S", ten_hang_den);

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

	public JSONArray getModelMau77T(String maritimeCode, String nameOfShip,
			String imoNumber, String registryNumber, String vrCode,
			String flagStateOfShip, String from_gt, String to_gt,
			String from_dwt, String to_dwt, String from_loa, String to_loa,
			String lastPortCode, String nextPortCode, String arrivalShipAgency,
			String departureShipAgency, String cargoType, String cargoCategory,
			String callSign, String anchoringPortHarbourCode,
			String anchoringPortWharfCode, String shiftingPortHarbourCode,
			String shiftingPortWharfCode, String startDate, String endDate)
			throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "select temp5.* from (select temp3.*, GROUP_CONCAT(DISTINCT temp4.ten_hang_den SEPARATOR ', ') as ten_hang_den, GROUP_CONCAT(DISTINCT temp4.ten_hang_roi SEPARATOR ', ') as ten_hang_roi from (select temp1.ItineraryNo, temp1.NameOfShip, temp1.RegistryNumber, temp1.VRCode, temp1.ShipTypeName, temp1.StateName, temp1.LOA, temp1.DWT, temp1.GT, (case when temp1.LastPortCode is not null and temp1.LastPortCode <> '' then (select PortName from dm_port where PortCode = temp1.LastPortCode) else '' end) as cang_roi, (case when temp1.ArrivalPortCode is not null and temp1.ArrivalPortCode <> '' then (select PortName from dm_port where PortCode = temp1.ArrivalPortCode) else '' end) as cang_den, (case when temp1.NextPortCode is not null and temp1.NextPortCode <> '' then (select PortName from dm_port where PortCode = temp1.NextPortCode) else '' end) as cang_dich, temp1.dai_ly_den, temp1.dai_ly_roi, temp1.ngay_den, temp2.ngay_roi from (select vma_itinerary.ItineraryNo as ItineraryNo, vma_itinerary.NameOfShip as NameOfShip, vma_itinerary.RegistryNumber as RegistryNumber, vma_itinerary.VRCode as VRCode, dm_ship_type.ShipTypeName as ShipTypeName, dm_state.StateName as StateName, vma_itinerary_schedule.LOA as LOA , vma_itinerary_schedule.DWT as DWT , vma_itinerary_schedule.GT as GT, vma_itinerary.LastPortCode as LastPortCode, vma_itinerary.ArrivalPortCode as ArrivalPortCode, vma_itinerary.NextPortCode as NextPortCode, vma_itinerary.TimeOfArrival as ngay_den, vma_itinerary.ArrivalShipAgency as dai_ly_den, vma_itinerary.DepartureShipAgency as dai_ly_roi from vma_itinerary inner join dm_ship_type on vma_itinerary.ShipTypeCode = dm_ship_type.ShipTypeCode inner join vma_itinerary_schedule on vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo inner join dm_state on dm_state.StateCode = vma_itinerary.FlagStateOfShip where vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.MaritimeCode = "
					+ maritimeCode
					+ " AND vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' AND (case when '"
					+ nameOfShip
					+ "' <> '' then vma_itinerary.NameOfShip like concat('%','"
					+ nameOfShip
					+ "','%') else 1 end) AND (case when '"
					+ imoNumber
					+ "' <> '' then vma_itinerary.IMONumber like concat('%','"
					+ imoNumber
					+ "','%') else 1 end) AND (case when '"
					+ callSign
					+ "' <> '' then vma_itinerary.CallSign like concat('%','"
					+ callSign
					+ "','%') else 1 end) AND (case when '"
					+ registryNumber
					+ "' <> '' then vma_itinerary.RegistryNumber like concat('%','"
					+ registryNumber
					+ "','%') else 1 end) AND (case when '"
					+ vrCode
					+ "' <> '' then vma_itinerary.VRCode = '"
					+ vrCode
					+ "' else 1 end) AND (case when '"
					+ flagStateOfShip
					+ "' <> '' then vma_itinerary.FlagStateOfShip = '"
					+ flagStateOfShip
					+ "' else 1 end) AND (case when '"
					+ from_gt
					+ "' <> '' then vma_itinerary_schedule.GT >= '"
					+ from_gt
					+ "' else 1 end) AND (case when '"
					+ to_gt
					+ "' <> '' then vma_itinerary_schedule.GT <= '"
					+ to_gt
					+ "' else 1 end) AND (case when '"
					+ from_loa
					+ "' <> '' then vma_itinerary_schedule.LOA >= '"
					+ from_loa
					+ "' else 1 end) AND (case when '"
					+ to_loa
					+ "' <> '' then vma_itinerary_schedule.LOA <= '"
					+ to_loa
					+ "' else 1 end) AND (case when '"
					+ lastPortCode
					+ "' <> '' then vma_itinerary.LastPortCode = '"
					+ lastPortCode
					+ "' else 1 end) AND (case when '"
					+ nextPortCode
					+ "' <> '' then vma_itinerary.NextPortCode = '"
					+ nextPortCode
					+ "' else 1 end) AND (case when '"
					+ arrivalShipAgency
					+ "' <> '' then vma_itinerary.ArrivalShipAgency = '"
					+ arrivalShipAgency
					+ "' else 1 end) AND (case when '"
					+ departureShipAgency
					+ "' <> '' then vma_itinerary.DepartureShipAgency = '"
					+ departureShipAgency
					+ "' else 1 end) group by vma_itinerary.ItineraryNo ) as temp1 inner join (select vma_itinerary.ItineraryNo as ItineraryNo , vma_itinerary_schedule.TimeOfDeparture as ngay_roi from vma_itinerary inner join vma_itinerary_schedule on vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo where vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.MaritimeCode = "
					+ maritimeCode
					+ " AND vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' group by vma_itinerary.ItineraryNo ) as temp2 on temp1.ItineraryNo = temp2.ItineraryNo ) as temp3 inner join ( select vma_itinerary.ItineraryNo as ItineraryNo, (case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4_DO') and vma_itinerary.MarkedAsArrival not in (0,1,6) then dm_dataitem.name else null end) as ten_hang_den, (case when vma_schedule_cargolist.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4_XEP') and vma_itinerary.MarkedAsDeparture not in (0,1,6) then dm_dataitem.name else null end) as ten_hang_roi from vma_itinerary left join (vma_schedule_cargolist inner join dm_dataitem on dm_dataitem.code = vma_schedule_cargolist.CargoCode and dm_dataitem.datagroupid = '124') on vma_schedule_cargolist.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4_DO') and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.LastPortCode is not null) or (vma_schedule_cargolist.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4_XEP') and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.NextPortCode is not null) ) and MaritimeCode = "
					+ maritimeCode
					+ " and (case vma_schedule_cargolist.CargoCategory when 'C2_DO' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C3_DO' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C4_DO' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C5' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C1_XEP' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' when 'C3_XEP' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' when 'C4_XEP' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' else null end) ) as temp4 on temp3.ItineraryNo = temp4.ItineraryNo group by temp3.ItineraryNo) as temp5 inner join vma_schedule_shifting on temp5.ItineraryNo = vma_schedule_shifting.ItineraryNo where 1 AND (case when '"
					+ from_dwt
					+ "' <> '' then vma_schedule_shifting.DWT >= '"
					+ from_dwt
					+ "' else 1 end) AND (case when '"
					+ to_dwt
					+ "' <> '' then vma_schedule_shifting.DWT <= '"
					+ to_dwt
					+ "' else 1 end) AND (case when '"
					+ anchoringPortHarbourCode
					+ "' <> '' then vma_schedule_shifting.AnchoringPortHarbourCode = '"
					+ anchoringPortHarbourCode
					+ "' else 1 end) AND (case when '"
					+ anchoringPortWharfCode
					+ "' <> '' then vma_schedule_shifting.AnchoringPortWharfCode = '"
					+ anchoringPortWharfCode
					+ "' else 1 end) AND (case when '"
					+ shiftingPortHarbourCode
					+ "' <> '' then vma_schedule_shifting.ShiftingPortHarbourCode = '"
					+ shiftingPortHarbourCode
					+ "' else 1 end) AND (case when '"
					+ shiftingPortWharfCode
					+ "' <> '' then vma_schedule_shifting.ShiftingPortWharfCode = '"
					+ shiftingPortWharfCode + "' else 1 end)";
			log.info("=========select bao cao mau77t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();
Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				int i = 1;
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					JSONObject result = JSONFactoryUtil.createJSONObject();

					String itineraryNo = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					nameOfShip = String.valueOf(objects[1] != null ? objects[1]
							: StringPool.BLANK);
					registryNumber = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					vrCode = String.valueOf(objects[3] != null ? objects[3]
							: StringPool.BLANK);
					String shipTypeName = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					String stateName = String
							.valueOf(objects[5] != null ? objects[5]
									: StringPool.BLANK);
					double loa = Double.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : null));
					double dwt = Double.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : null));
					double gt = Double.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : null));
					String cang_roi = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);
					String cang_den = String
							.valueOf(objects[10] != null ? objects[10]
									: StringPool.BLANK);
					String cang_dich = String
							.valueOf(objects[11] != null ? objects[11]
									: StringPool.BLANK);
					String dai_ly_den = String
							.valueOf(objects[12] != null ? objects[12]
									: StringPool.BLANK);
					String dai_ly_roi = String
							.valueOf(objects[13] != null ? objects[13]
									: StringPool.BLANK);
					String ngay_den = String
							.valueOf(objects[14] != null ? objects[14]
									: StringPool.BLANK);
					String ngay_roi = String
							.valueOf(objects[15] != null ? objects[15]
									: StringPool.BLANK);
					String ten_hang_den = String
							.valueOf(objects[16] != null ? objects[16]
									: StringPool.BLANK);
					String ten_hang_roi = String
							.valueOf(objects[17] != null ? objects[17]
									: StringPool.BLANK);

					result.put("A", i);
					result.put("B", nameOfShip);
					result.put("C", StringPool.BLANK);
					result.put("D", loa >= 0 ? loa : null);
					result.put("E", StringPool.BLANK);
					result.put("F", stateName);
					result.put("G", shipTypeName);
					result.put("H", ngay_roi);
					result.put("I", dai_ly_roi);
					result.put("K", dai_ly_den);
					result.put("L", cang_dich);
					result.put("M", cang_roi);
					result.put("N", cang_dich);
					result.put("O", dwt >= 0 ? dwt : null);
					result.put("P", gt >= 0 ? gt : null);
					result.put("Q", ngay_den);
					result.put("R", ten_hang_roi);
					result.put("S", ten_hang_den);

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
}
