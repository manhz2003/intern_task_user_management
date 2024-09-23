package com.fds.nsw.nghiepvu.danhmuc.service.finder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import com.fds.nsw.nghiepvu.model.DmShipAgency;
@Service
@Slf4j
public class DmShipAgencyFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmShipAgency> queryFactory;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;
	public List<DmShipAgency> findShipAgencys(String shipAgencyNameVN,
			String addressVN, String fax, String taxCode, String isDelete,
			String shipAgencyCodeGroup, int start, int end)
			throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT * FROM dm_ship_agency WHERE 1=1 ";

			if (Validator.isNotNull(shipAgencyNameVN)
					&& shipAgencyNameVN.trim().length() > 0) {
				query.append(" AND shipAgencyNameVN like '%"
						+ shipAgencyNameVN.trim().toUpperCase() + "%' ");
			}
			if (Validator.isNotNull(addressVN) && addressVN.trim().length() > 0) {
				query.append(" AND addressVN like '%"
						+ addressVN.trim().toUpperCase() + "%' ");
			}
			if (Validator.isNotNull(fax) && fax.trim().length() > 0) {
				query.append(" AND fax like '%" + fax.trim().toUpperCase()
						+ "%' ");
			}
			if (Validator.isNotNull(taxCode) && taxCode.trim().length() > 0) {
				query.append(" AND taxCode like '%"
						+ addressVN.trim().toUpperCase() + "%' ");
			}
			if (Validator.isNotNull(isDelete) && isDelete.trim().length() > 0) {
				query.append(" AND isDelete= :isDelete ");
			}
			if (Validator.isNotNull(shipAgencyCodeGroup)
					&& shipAgencyCodeGroup.trim().length() > 0) {
				shipAgencyCodeGroup = shipAgencyCodeGroup.replace(",", "', '");
				query.append(" AND ShipAgencyCode IN ('" + shipAgencyCodeGroup
						+ "')");
			}
			query.append(" ORDER BY modifiedDate DESC ");

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(DmShipAgency.class).build();

			log.info("=========find DANH SACH ===" + sql);
			

			if (Validator.isNotNull(isDelete) && isDelete.trim().length() > 0) {
				int isDeleteTemp = Integer.valueOf(isDelete);
				builder.appendNamedParameterMap("isDeleteTemp",isDeleteTemp);
			}

			return (List<DmShipAgency>)  queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countShipAgencys(String shipAgencyNameVN, String addressVN,
			String fax, String taxCode, String isDelete,
			String shipAgencyCodeGroup) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT count(*) AS total FROM dm_ship_agency WHERE 1=1 ";

			if (Validator.isNotNull(shipAgencyNameVN)
					&& shipAgencyNameVN.trim().length() > 0) {
				query.append(" AND shipAgencyNameVN like '%"
						+ shipAgencyNameVN.trim().toUpperCase() + "%' ");
			}
			if (Validator.isNotNull(addressVN) && addressVN.trim().length() > 0) {
				query.append(" AND addressVN like '%"
						+ addressVN.trim().toUpperCase() + "%' ");
			}
			if (Validator.isNotNull(fax) && fax.trim().length() > 0) {
				query.append(" AND fax like '%" + fax.trim().toUpperCase()
						+ "%' ");
			}
			if (Validator.isNotNull(taxCode) && taxCode.trim().length() > 0) {
				query.append(" AND taxCode like '%"
						+ addressVN.trim().toUpperCase() + "%' ");
			}
			if (Validator.isNotNull(isDelete) && isDelete.trim().length() > 0) {
				query.append(" AND isDelete= :isDelete ");
			}
			if (Validator.isNotNull(shipAgencyCodeGroup)
					&& shipAgencyCodeGroup.trim().length() > 0) {
				shipAgencyCodeGroup = shipAgencyCodeGroup.replace(",", "', '");
				query.append(" AND ShipAgencyCode IN ('" + shipAgencyCodeGroup
						+ "')");
			}
			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();
			

			if (Validator.isNotNull(isDelete) && isDelete.trim().length() > 0) {
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

	public JSONArray getModelMau58T(String query) throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			log.info("=========select bao cao mau_58t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();
Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				int i = 1;
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					JSONObject result = JSONFactoryUtil.createJSONObject();
					String maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String itineraryNo = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					String ten_tau = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					String quoc_tich = String
							.valueOf(objects[3] != null ? objects[3]
									: StringPool.BLANK);
					String ho_hieu = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					String gt = String.valueOf(objects[5] != null ? objects[5]
							: StringPool.BLANK);
					String dwt = String.valueOf(objects[6] != null ? objects[6]
							: StringPool.BLANK);
					String loa = String.valueOf(objects[7] != null ? objects[7]
							: StringPool.BLANK);
					String mon_nuoc = String
							.valueOf(objects[8] != null ? objects[8]
									: StringPool.BLANK);
					String ma_hang = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);
					String k_l = String
							.valueOf(objects[10] != null ? objects[10]
									: StringPool.BLANK);
					String cang_den = String
							.valueOf(objects[11] != null ? objects[11]
									: StringPool.BLANK);
					String ben_cang = String
							.valueOf(objects[12] != null ? objects[12]
									: StringPool.BLANK);
					String cau_cang = String
							.valueOf(objects[13] != null ? objects[13]
									: StringPool.BLANK);
					String thoi_gian_den = String
							.valueOf(objects[14] != null ? objects[14]
									: StringPool.BLANK);
					String tau_lai = String
							.valueOf(objects[15] != null ? objects[15]
									: StringPool.BLANK);
					String dai_ly = String
							.valueOf(objects[16] != null ? objects[16]
									: StringPool.BLANK);
					String hoa_tieu = String
							.valueOf(objects[17] != null ? objects[17]
									: StringPool.BLANK);
					String ghi_chu = String
							.valueOf(objects[18] != null ? objects[18]
									: StringPool.BLANK);
					String ngay = String
							.valueOf(objects[19] != null ? objects[19]
									: StringPool.BLANK);

					result.put("A", i);
					result.put("B", ten_tau);
					result.put("C", quoc_tich);
					result.put("D", ho_hieu);
					result.put("E", gt);
					result.put("F", dwt);
					result.put("G", loa);
					result.put("H", mon_nuoc);
					result.put("I", ma_hang);
					result.put("K", k_l);
					result.put("L", cang_den);
					result.put("M", ben_cang);
					result.put("N", cau_cang);
					result.put("O", thoi_gian_den);
					result.put("P", tau_lai);
					result.put("Q", dai_ly);
					result.put("R", hoa_tieu);
					result.put("S", ghi_chu);

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

	public JSONArray getModelMau58TauDich(String query) throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			log.info("=========select bao cao mau_58t_dich_chuyen>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();
Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				int i = 1;
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					JSONObject result = JSONFactoryUtil.createJSONObject();
					String maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String ten_tau = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					String quoc_gia = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					String ho_hieu = String
							.valueOf(objects[3] != null ? objects[3]
									: StringPool.BLANK);
					String grt = String.valueOf(objects[4] != null ? objects[4]
							: StringPool.BLANK);
					String dwt = String.valueOf(objects[5] != null ? objects[5]
							: StringPool.BLANK);
					String loa = String.valueOf(objects[6] != null ? objects[6]
							: StringPool.BLANK);
					String mon_nuoc = String
							.valueOf(objects[7] != null ? objects[7]
									: StringPool.BLANK);
					String ma_hang = String
							.valueOf(objects[8] != null ? objects[8]
									: StringPool.BLANK);
					String k_l = String.valueOf(objects[9] != null ? objects[9]
							: StringPool.BLANK);
					String tu_ben = String
							.valueOf(objects[10] != null ? objects[10]
									: StringPool.BLANK);
					String tu_cau = String
							.valueOf(objects[11] != null ? objects[11]
									: StringPool.BLANK);
					String den_ben = String
							.valueOf(objects[12] != null ? objects[12]
									: StringPool.BLANK);
					String den_cau = String
							.valueOf(objects[13] != null ? objects[13]
									: StringPool.BLANK);
					String thoi_gian = String
							.valueOf(objects[14] != null ? objects[14]
									: StringPool.BLANK);
					String tau_lai = String
							.valueOf(objects[15] != null ? objects[15]
									: StringPool.BLANK);
					String dai_ly = String
							.valueOf(objects[16] != null ? objects[16]
									: StringPool.BLANK);
					String hoa_tieu = String
							.valueOf(objects[17] != null ? objects[17]
									: StringPool.BLANK);
					String ghi_chu = String
							.valueOf(objects[18] != null ? objects[18]
									: StringPool.BLANK);
					String ngay = String
							.valueOf(objects[19] != null ? objects[19]
									: StringPool.BLANK);

					result.put("A", i);
					result.put("B", ten_tau);
					result.put("C", quoc_gia);
					result.put("D", ho_hieu);
					result.put("E", grt);
					result.put("F", dwt);
					result.put("G", loa);
					result.put("H", mon_nuoc);
					result.put("I", ma_hang);
					result.put("K", k_l);
					result.put("L", tu_ben);
					result.put("M", tu_cau);
					result.put("N", den_ben);
					result.put("O", den_cau);
					result.put("P", thoi_gian);
					result.put("Q", tau_lai);
					result.put("R", dai_ly);
					result.put("S", hoa_tieu);
					result.put("T", ghi_chu);

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

	public JSONArray getModelMau59(String query) throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			log.info("=========select bao cao mau_59t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();



			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				int i = 1;
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					JSONObject result = JSONFactoryUtil.createJSONObject();
					String maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String itineraryNo = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					String ten_tau = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					String loai_tau = String
							.valueOf(objects[3] != null ? objects[3]
									: StringPool.BLANK);
					String ho_hieu = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					double dwt = Double.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					String mon_nuoc = String
							.valueOf(objects[6] != null ? objects[6]
									: StringPool.BLANK);
					String chu_tau = String
							.valueOf(objects[7] != null ? objects[7]
									: StringPool.BLANK);
					String dai_ly = String
							.valueOf(objects[8] != null ? objects[8]
									: StringPool.BLANK);
					String dien_thoai = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);
					String dia_chi = String
							.valueOf(objects[10] != null ? objects[10]
									: StringPool.BLANK);
					String thoi_gian_roi = String
							.valueOf(objects[11] != null ? objects[11]
									: StringPool.BLANK);
					String cang_den = String
							.valueOf(objects[12] != null ? objects[12]
									: StringPool.BLANK);
					String tuyen_luong = String
							.valueOf(objects[13] != null ? objects[13]
									: StringPool.BLANK);
					String ngay = String
							.valueOf(objects[14] != null ? objects[14]
									: StringPool.BLANK);

					result.put("A", i);
					result.put("B", ten_tau);
					result.put("C", ho_hieu);
					result.put("D", loai_tau);
					result.put("E", dwt);
					result.put("F", StringPool.BLANK);
					result.put("G", mon_nuoc);
					result.put("H", chu_tau.equals("0") ? dai_ly
							: StringPool.BLANK);
					result.put("I", dien_thoai);
					result.put("K", dia_chi);
					result.put("L", thoi_gian_roi);
					result.put("M", cang_den);
					result.put("N", tuyen_luong);

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
