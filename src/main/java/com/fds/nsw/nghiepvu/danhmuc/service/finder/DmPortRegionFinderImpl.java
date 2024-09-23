package com.fds.nsw.nghiepvu.danhmuc.service.finder;

import java.util.*;

import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.nghiepvu.model.DmGtStatus;
import vn.gt.portlet.baocao.bc15t.BC15TModel;
import vn.gt.portlet.baocao.bc12bt.BC12BTKhuCangModel;
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
import com.fds.nsw.nghiepvu.model.DmPortRegion;
@Service
@Slf4j
public class DmPortRegionFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmPortRegion> queryFactory;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;

	public List<DmPortRegion> findDanhSachDmPortRegion(String maritimeCode,
													   String portRegionName, String portCode, int start, int end)
			throws SystemException {
		try {
			StringBuilder query = new StringBuilder();

			String sql = "SELECT * FROM dm_port_region WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND portRegionRef= :portRegionRef ");
			}
			if (Validator.isNotNull(portRegionName)
					&& portRegionName.trim().length() > 0) {
				query.append(" AND UPPER(portRegionNameVN) like '%"
						+ portRegionName.trim().toUpperCase() + "%' ");
			}

			if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
				query.append(" AND UPPER(portCode) like '%"
						+ portCode.trim().toUpperCase() + "%' ");
			}

			query.append(" ORDER BY CONVERT(PortCode USING utf8) COLLATE utf8_polish_ci ");

			sql = sql + query.toString();

			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql)
					.firstResult(start).maxResults(end - start).entityClass(DmPortRegion.class).build();
			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}

			return (List<DmPortRegion>) queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}



	public List<DmPortRegion> getAll() throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port_region ");
			query.append("ORDER BY CONVERT(PortCode USING utf8) COLLATE utf8_polish_ci");

			log.debug("===getAll===" + query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(DmPortRegion.class).build();

			
			// builder.appendNamedParameterMap("type",type);

			return (List<DmPortRegion>)  queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public int countDanhSachDmPortRegion(String maritimeCode,
			String portRegionName, String portCode) throws SystemException { int count = 0; try {
			

			StringBuilder query = new StringBuilder();

			String sql = "SELECT Count(id) as total FROM dm_port_region WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND portRegionRef= :portRegionRef ");
			}
			if (Validator.isNotNull(portRegionName)
					&& portRegionName.trim().length() > 0) {
				query.append(" AND UPPER(portRegionNameVN) like '%"
						+ portRegionName.trim().toUpperCase() + "%' ");
			}

			if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
				query.append(" AND UPPER(portCode) like '%"
						+ portCode.trim().toUpperCase() + "%' ");
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

	public List<DmPortRegion> findDmPortRegions(String maritimeCode,
			String portRegionName, String portCode, String isDelete,
			String portRegionCodeGroup, int start, int end)
			throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT * FROM dm_port_region WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND portRegionRef= :portRegionRef ");
			}
			if (Validator.isNotNull(portRegionName)
					&& portRegionName.trim().length() > 0) {
				query.append(" AND portRegionNameVN like ? ");
			}
			if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
				query.append(" AND portCode like ? ");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete ");
			}
			if (Validator.isNotNull(portRegionCodeGroup)
					&& !portRegionCodeGroup.isEmpty()) {
				portRegionCodeGroup = portRegionCodeGroup.replace(",", "', '");
				query.append(" AND PortRegionCode IN ('" + portRegionCodeGroup
						+ "')");
			}
			query.append(" ORDER BY SequenceNo");

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(DmPortRegion.class).build();

			log.info("=========find DANH SACH ===" + sql);
			

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(portRegionName)
					&& portRegionName.trim().length() > 0) {
				builder.appendNamedParameterMap("portRegionName","%" + portRegionName + "%");
			}
			if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
				builder.appendNamedParameterMap("portCode","%" + portCode + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}
			return (List<DmPortRegion>)  queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countDmPortRegions(String maritimeCode, String portRegionName,
			String portCode, String isDelete, String portRegionCodeGroup) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT count(*) AS total FROM dm_port_region WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND portRegionRef= :portRegionRef ");
			}
			if (Validator.isNotNull(portRegionName)
					&& portRegionName.trim().length() > 0) {
				query.append(" AND portRegionNameVN like ? ");
			}
			if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
				query.append(" AND portCode like ? ");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete ");
			}
			if (Validator.isNotNull(portRegionCodeGroup)
					&& !portRegionCodeGroup.isEmpty()) {
				portRegionCodeGroup = portRegionCodeGroup.replace(",", "', '");
				query.append(" AND PortRegionCode IN ('" + portRegionCodeGroup
						+ "')");
			}

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

			

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(portRegionName)
					&& portRegionName.trim().length() > 0) {
				builder.appendNamedParameterMap("portRegionName","%" + portRegionName + "%");
			}
			if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
				builder.appendNamedParameterMap("portCode","%" + portCode + "%");
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

	public long getMaxSequenceNo(String maritimeCode, String portCodeRef)
			throws SystemException {
		long count = 0;
		try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT MAX(SequenceNo) AS total FROM dm_port_region WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND portRegionRef= :portRegionRef ");
			}
			if (Validator.isNotNull(portCodeRef)
					&& portCodeRef.trim().length() > 0) {
				query.append(" AND portCodeRef= :portCodeRef ");
			}
			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

			

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(portCodeRef)
					&& portCodeRef.trim().length() > 0) {
				builder.appendNamedParameterMap("portCodeRef",portCodeRef);
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	public DmPortRegion getFirstPortRegion() throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port_region ");
			query.append("ORDER BY ID ASC LIMIT 1");

			log.debug("===getFirstPortRegion===" + query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(DmPortRegion.class).build();

			
			// builder.appendNamedParameterMap("type",type);

			return (DmPortRegion) queryFactory.getResultList(builder)
.get(0);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public DmPortRegion getLastPortRegion() throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_port_region ");
			query.append("ORDER BY ID DESC LIMIT 1");

			log.debug("===getLastPortRegion===" + query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(DmPortRegion.class).build();

			
			// builder.appendNamedParameterMap("type",type);

			return (DmPortRegion) queryFactory.getResultList(builder).get(0);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public List<BC15TModel> getModelMau15T(String maritimeCode,
			String startDate, String endDate) throws SystemException {

		
		try {
			

			String query = "Select temp.*, temp2.* from ( (Select PortRegionRef as MaritimeCode, PortRegionCode, PortRegionName, '' as PortHarbourCode, PortRegionName as PortHarbourName, 1 as 'level' from dm_port_region where PortRegionRef = "
					+ maritimeCode
					+ " and IsDelete = 0) UNION (SELECT tb1.PortRegionRef as MaritimeCode,tb1.PortRegionCode, tb1.PortRegionName, tb2.PortHarbourCode, tb2.PortHarbourName, 2 as 'level' FROM dm_port_region as tb1 inner join dm_port_harbour as tb2 on tb2.PortRegion = tb1.PortRegionCode where tb2.PortRegionCode = "
					+ maritimeCode
					+ " and tb1.IsDelete=0 and tb2.IsDelete=0 order by tb1.SequenceNo, tb2.PortRegion, tb2.SequenceNo) ) as temp left join ( select sum(case when tb4.CargoType =9 then Quantity else 0 end) as hang_container, sum(case when tb4.CargoType =9 then TeusQuantity else 0 end) as hang_container_teus, sum(case when tb4.CargoType =12 then Quantity else 0 end) as hang_kho, sum(case when tb4.CargoType =3 then Quantity else 0 end) as hang_long, sum(case tb4.CargoCategory when 'C4' then Quantity when 'C4' then Quantity else 0 end) as qua_canh_xep_do, sum(case tb4.CargoCategory when 'C5' then Quantity else 0 end) as qua_canh_khong_xep_do, tb4.PortRegionCode as PortRegionCode_, tb4.PortHarbourCode as PortHarbourCode_ from vma_schedule_cargolist as tb4 inner join vma_itinerary as tb3 on tb4.ItineraryNo = tb3.ItineraryNo where tb4.PortRegionCode is not null and ( (tb4.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4') and tb3.MarkedAsArrival not in (0,1,6)) or (tb4.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4') and tb3.MarkedAsDeparture not in (0,1,6)) ) and tb3.MaritimeCode = "
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
					+ " 23:59:59' else null end) group by PortRegionCode_,PortHarbourCode_) as temp2 on (case temp.level when 1 then temp.PortRegionCode = temp2.PortRegionCode_ else temp.PortHarbourCode = temp2.PortHarbourCode_ end) ORDER BY CONVERT(temp.PortRegionCode USING utf8) COLLATE utf8_polish_ci asc, level asc";
			
			log.info("=========select bao cao mau_22_15t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();



			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			List<BC15TModel> bc15tModels = new ArrayList<BC15TModel>();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					BC15TModel bc15tModel = new BC15TModel();

					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String portRegionCode = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					String portRegionName = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					String portHarbourCode = String
							.valueOf(objects[3] != null ? objects[3]
									: StringPool.BLANK);
					String portHarbourName = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					int level = Integer.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					double hang_container = Double.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));
					double hang_container_teus = Double.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : -1));
					double hang_kho = Double.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : -1));
					double hang_long = Double.valueOf(String
							.valueOf(objects[9] != null ? objects[9] : -1));
					double qua_canh_xep_do = Double.valueOf(String
							.valueOf(objects[10] != null ? objects[10] : -1));
					double qua_canh_khong_xep_do = Double.valueOf(String
							.valueOf(objects[11] != null ? objects[11] : -1));
					String portRegionCode_ = String
							.valueOf(objects[12] != null ? objects[12]
									: StringPool.BLANK);
					String portHarbourCode_ = String
							.valueOf(objects[13] != null ? objects[13]
									: StringPool.BLANK);

					bc15tModel.setMaritimeCode(maritimeCode);
					bc15tModel.setPortRegionCode(portRegionCode);
					bc15tModel.setPortRegionName(portRegionName);
					bc15tModel.setPortHarbourCode(portHarbourCode);
					bc15tModel.setPortHarbourName(portHarbourName);
					bc15tModel.setLevel(level);
					bc15tModel.setHang_container(hang_container);
					bc15tModel.setHang_container_teus(hang_container_teus);
					bc15tModel.setHang_kho(hang_kho);
					bc15tModel.setHang_long(hang_long);
					bc15tModel.setQua_canh_xep_do(qua_canh_xep_do);
					bc15tModel.setQua_canh_khong_xep_do(qua_canh_khong_xep_do);
					bc15tModel.setPortRegionCode_(portRegionCode_);
					bc15tModel.setPortHarbourCode_(portHarbourCode_);

					bc15tModels.add(bc15tModel);
				}
			}

			return bc15tModels;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public List<BC12BTKhuCangModel> getModelMau12BTKhuCang(String maritimeCode,
			String startDate, String endDate) throws SystemException {

		
		try {
			

			String query = "Select temp.*, temp2.khoi_luong_hang, temp3.luot_tau, temp3.luot_tau_bien, temp3.luot_pttnd from (select PortRegionRef as MaritimeCode, PortRegionCode, PortRegionName from dm_port_region where PortRegionRef = "
					+ maritimeCode
					+ " and IsDelete = 0 order by SequenceNo) as temp left join ( select sum(Quantity) as khoi_luong_hang, tb4.PortRegionCode as PortRegionCode_ from vma_schedule_cargolist as tb4 inner join vma_itinerary as tb3 on tb4.ItineraryNo = tb3.ItineraryNo where tb4.PortRegionCode is not null and ( (tb4.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4') and tb3.MarkedAsArrival not in (0,1,6)) or (tb4.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4') and tb3.MarkedAsDeparture not in (0,1,6)) ) and tb4.PortWharfCode in (select PortWharfCode from dm_port_wharf where IsDelete = 0 and PortWharfType = 1) and tb3.MaritimeCode = "
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
					+ " 23:59:59' else null end) group by PortRegionCode_) as temp2 on temp.PortRegionCode = temp2.PortRegionCode_ left join (select sum(case when vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau, sum(case when VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien, sum(case when VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_pttnd, vma_itinerary_schedule.PortRegionCode as PortRegionCode_temp3 from vma_itinerary_schedule inner join vma_itinerary on vma_itinerary_schedule.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.MarkedAsArrival not in (0,1,6)) or (vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.MarkedAsDeparture not in (0,1,6)) ) and MaritimeCode = "
					+ maritimeCode
					+ " AND (case when vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59' else null end) ) as temp3 on temp.PortRegionCode = temp3.PortRegionCode_temp3";

			log.info("=========select bao cao mau_23_12bt_khu_cang>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			List<BC12BTKhuCangModel> bc12btKhuCangModels = new ArrayList<BC12BTKhuCangModel>();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					BC12BTKhuCangModel bc12btKhuCangModel = new BC12BTKhuCangModel();

					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String portRegionCode = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					String portRegionName = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					double khoi_luong_hang = Double.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					int luot_tau = Integer.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : -1));
					int luot_tau_bien = Integer.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					int luot_pttnd = Integer.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));

					bc12btKhuCangModel.setMaritimeCode(maritimeCode);
					bc12btKhuCangModel.setPortRegionCode(portRegionCode);
					bc12btKhuCangModel.setPortRegionName(portRegionName);
					bc12btKhuCangModel.setKhoi_luong_hang(khoi_luong_hang);
					bc12btKhuCangModel.setLuot_tau(luot_tau);
					bc12btKhuCangModel.setLuot_tau_bien(luot_tau_bien);
					bc12btKhuCangModel.setLuot_pttnd(luot_pttnd);

					bc12btKhuCangModels.add(bc12btKhuCangModel);
				}
			}

			return bc12btKhuCangModels;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
}
