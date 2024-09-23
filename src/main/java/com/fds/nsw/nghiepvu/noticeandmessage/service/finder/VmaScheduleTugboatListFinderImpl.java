/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.fds.nsw.nghiepvu.noticeandmessage.service.finder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleTugboatListModelImpl;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.portlet.phuongtien.VMAUtils;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.flex.common.ultility.GetterUtil;
import com.fds.flex.common.utility.string.StringPool;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.dao.orm.BasePersistence;
import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.nghiepvu.util.ConvertUtil;
import com.fds.nsw.nghiepvu.util.FormatData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VmaScheduleTugboatListFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleTugboatList> queryFactory;

	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;

	public JSONArray findVmaScheduleTugboatList(
			LinkedHashMap<String, Class<?>> colMap, Class<?> clazz,
			String className, String sql, int start, int end)
			throws SystemException {

		
		JSONArray result = JSONFactoryUtil.createJSONArray();
		try {
			
			log.info("=========select vma_schedule_tugboat_list>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(Object.class).build();

			if (Validator.isNotNull(className) && clazz != null) {
				List<VmaScheduleTugboatList> vmaScheduleTugboatLists = (List<VmaScheduleTugboatList>) queryFactory.getResultList(builder);

				if (vmaScheduleTugboatLists != null) {
					for (VmaScheduleTugboatList vmaScheduleTugboatList : vmaScheduleTugboatLists) {
						JSONObject row = VMAUtils.object2Json(
								vmaScheduleTugboatList,
								VmaScheduleTugboatList.class);
						result.put(row);
					}
				}
			} else {
				if (colMap != null) {

					Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

					if (itr.hasNext()) {
						while (itr.hasNext()) {
							Object[] objects = itr.next();
							JSONObject row = JSONFactoryUtil.createJSONObject();
							int count = 0;
							for (Map.Entry<String, Class<?>> entry : colMap
									.entrySet()) {
								String key = entry.getKey();
								Class<?> type = entry.getValue();
								Object object = objects[count];

								if (key.equalsIgnoreCase("anchoringportharbourcode")) {
									String anchoringPortHarbourName = "";
									if (object != null) {
										try {
											DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil
													.getByPortHarbourCode((String) object);
											anchoringPortHarbourName = dmPortHarbour.getPortHarbourName();
										} catch (Exception e) {
											
										}
										
										row.put("anchoringPortHarbourName", anchoringPortHarbourName);
									}
								} else if (key
										.equalsIgnoreCase("anchoringportwharfcode")) {
									String anchoringPortWharfName = "";
									if (object != null) {
										try {
											DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
													.getByPortWharfCode((String) object);
											anchoringPortWharfName = dmPortWharf.getPortWharfName();
										} catch (Exception e) {
											
										}
										
										row.put("anchoringPortWharfName", anchoringPortWharfName);
									}
								} else if (key
										.equalsIgnoreCase("shiftingportharbourcode")) {
									String shiftingPortHarbourName = "";
									
									if (object != null) {
										try {
											DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil
													.getByPortHarbourCode((String) object);
											shiftingPortHarbourName = dmPortHarbour.getPortHarbourName();
										} catch (Exception e) {
											
										}
										
										row.put("shiftingPortHarbourName", shiftingPortHarbourName);
									}
									
									
								} else if (key
										.equalsIgnoreCase("shiftingportregioncode")) {
									String shiftingPortRegionName = "";
									
									if (object != null) {
										try {
											DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil
													.getByPortRegionCode((String) object);
											shiftingPortRegionName = dmPortRegion.getPortRegionName();
										} catch (Exception e) {
											
										}
										
										row.put("shiftingPortRegionName", shiftingPortRegionName);
									}
									
								} else if (key
										.equalsIgnoreCase("shiftingportwharfcode")) {
									String shiftingPortWharfName = "";
									
									if (object != null) {
										try {
											DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
													.getByPortWharfCode((String) object);
											shiftingPortWharfName = dmPortWharf.getPortWharfName();
										} catch (Exception e) {
											
										}
										
										row.put("shiftingPortWharfName", shiftingPortWharfName);
									}
								}

								if (type.getName().equals(Date.class.getName())) {
									Date date = (Date) object;
									String _tmp = "";
									if (date != null) {
										_tmp = FormatData.formatDateShort3
												.format(date);
									}
									row.put(key, _tmp);
								} else if (type.getName().equals(
										int.class.getName())
										|| type.getName().equals(
												Integer.class.getName())) {
									row.put(key, GetterUtil.getInteger(object));
								} else if (type.getName().equals(
										long.class.getName())
										|| type.getName().equals(
												Long.class.getName())) {
									row.put(key, GetterUtil.getLong(object));
								} else if (type.getName().equals(
										double.class.getName())
										|| type.getName().equals(
												Double.class.getName())) {
									row.put(key, GetterUtil.getDouble(object));
								} else if (type.getName().equals(
										float.class.getName())
										|| type.getName().equals(
												Float.class.getName())) {
									row.put(key, GetterUtil.getFloat(object));
								} else if (type.getName().equals(
										boolean.class.getName())
										|| type.getName().equals(
												Boolean.class.getName())) {
									row.put(key, GetterUtil.getBoolean(object));
								} else if (type.getName().equals(
										String.class.getName())) {
									row.put(key, String.valueOf(object));
								}
								count++;
							}

							result.put(row);
						}
					}
				}
			}

			return result;

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaScheduleTugboatList(String sql) throws SystemException { long count = 0; try {
			
			log.info("=========count vma_schedule_tugboat_list>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

			/*  */

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

}
