package vn.gt.portlet.phuongtien;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fds.nsw.nghiepvu.model.UserPort;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmDataitem;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import com.fds.nsw.nghiepvu.model.DmPortRegion;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import com.fds.nsw.nghiepvu.model.DmShipAgency;
import com.fds.nsw.nghiepvu.model.DmShipType;
import com.fds.nsw.nghiepvu.model.DmState;
import com.fds.nsw.nghiepvu.model.DmVmaShipOwner;
import com.fds.nsw.nghiepvu.model.DmVmaShipType;
import com.fds.nsw.nghiepvu.model.DmVmaTugboat;
import vn.gt.dao.danhmuc.service.DmDataItemLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipOwnerLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaTugboatLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmGtReportCategory;
import vn.gt.dao.danhmucgt.service.DmGtReportCategoryLocalServiceUtil;
import com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipException;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.VmaItinerary;
import com.fds.nsw.nghiepvu.model.VmaItineraryRemark;
import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;
import com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage;
import com.fds.nsw.nghiepvu.model.VmaScheduleMerging;
import com.fds.nsw.nghiepvu.model.VmaScheduleShifting;
import com.fds.nsw.nghiepvu.model.VmaScheduleTesting;
import com.fds.nsw.nghiepvu.model.VmaScheduleTransfer;
import com.fds.nsw.nghiepvu.model.VmaScheduleTugboat;
import com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList;
import com.fds.nsw.nghiepvu.model.VmaShip;
import com.fds.nsw.nghiepvu.model.VmaTransactionSlip;


import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryRemarksLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryScheduleLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleAnchorageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleMergingLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTransferLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaShipLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionSlipLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.TempDebitnote;


import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import vn.gt.portlet.kehoach.utils.ChuyenDichTrangThaiUtils;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import org.json.JSONException;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.utility.string.StringUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.Role;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import com.fds.nsw.liferay.core.ThemeDisplay;


import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VMAUtils
 {

	public static JSONObject createResponseMessage(String message,
			String errorCode, String desciption) {
		JSONObject object = JSONFactoryUtil.createJSONObject();
		object.put("message", message);
		object.put("errorCode", errorCode);
		object.put("desciption", desciption);
		return object;
	}

	public static JSONObject object2Json(Object object, Class<?> clazz,
			String... injections) throws JSONException {

		String key = (clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz
				.getSimpleName().substring(1)).replace("ModelImpl", "") + "Id";

		/*
		 * if(clazz.getDeclaredClasses() != null){ for(int i = 0; i <
		 * clazz.getDeclaredClasses().length; i++){ System.out.println(
		 * "=========================|||||||||||||||||||===================ClassName "
		 * + clazz.getDeclaredClasses()[i].getName()); } }
		 */

		/*
		 * if(clazz.getDeclaredFields() != null){ for(int i = 0; i <
		 * clazz.getDeclaredFields().length; i++){ System.out.println(
		 * "=========================|||||||||||||||||||===================Field "
		 * + clazz.getDeclaredFields()[i].getName() + "|" +
		 * clazz.getDeclaredFields()[i].getType()); } }
		 */

		JSONObject result = JSONFactoryUtil.createJSONObject(JSONFactoryUtil
				.looseSerializeDeep(object));

		if (clazz.getDeclaredFields() != null) {
			for (int i = 0; i < clazz.getDeclaredFields().length; i++) {
				String fieldName = clazz.getDeclaredFields()[i].getName();
				String dataType = clazz.getDeclaredFields()[i].getType()
						.getName();
				// System.out.println(dataType + "|" + fieldName);
				if (dataType.equals("java.util.Date")) {

					fieldName = fieldName.replaceFirst("_", "");

					/*
					 * System.out
					 * .println("=====================>>>>>>>> fieldName2 " +
					 * fieldName);
					 */
					if (result.has(fieldName)) {
						String strDate = StringPool.BLANK;
						long time = (Long) result.getLong(fieldName);

						if (time > 0) {
							Calendar c = Calendar.getInstance();
							c.setTimeInMillis(time);
							Date date = c.getTime();
							strDate = FormatData.formatDateShort3.format(date);
						}
						/*
						 * System.out
						 * .println("=====================>>>>>>>> fieldName " +
						 * fieldName + "|" + time + "|" + strDate);
						 */
						// log.info("====" + "key====" + fieldName + "value" +
						// result.getString(fieldName) + "======time>>>"+ time +
						// "strDate====" + strDate);
						result.put(fieldName, strDate);
					}
				}

			}
		}

		result.put(key, result.getLong("id"));

		result.remove("id");

		if (injections != null && injections.length > 0) {
			for (String injection : injections) {
				if (injection.equals("shipAgencyCode")) {

					String shipAgencyCode = result.getString("shipAgencyCode");

					String shipAgencyName = StringPool.BLANK;

					if (Validator.isNotNull(shipAgencyCode)) {
						DmShipAgency dmShipAgency = DmShipAgencyLocalServiceUtil
								.getByShipAgencyCode(result
										.getString("shipAgencyCode"));

						shipAgencyName = dmShipAgency != null ? dmShipAgency
								.getShipAgencyNameVN() : StringPool.BLANK;

					}

					result.put("shipAgencyName", shipAgencyName);

				} else if (injection.equals("flagStateOfShip")) {
					String flagStateOfShip = result
							.getString("flagStateOfShip");
					String stateName = StringPool.BLANK;

					if (Validator.isNotNull(flagStateOfShip)) {
						DmState dmState = DmStateLocalServiceUtil
								.getByStateCode(flagStateOfShip);
						stateName = dmState != null ? dmState.getStateName()
								: StringPool.BLANK;

					}

					result.put("stateName", stateName);

				} else if (injection.equals("shipTypeCode")) {
					String shipTypeCode = result.getString("shipTypeCode");
					String shipTypeName = StringPool.BLANK;

					if (Validator.isNotNull(shipTypeCode)) {
						DmShipType shipType = DmShipTypeLocalServiceUtil
								.getByShipTypeCode(shipTypeCode);

						shipTypeName = shipType != null ? shipType
								.getShipTypeNameVN() : StringPool.BLANK;

					}

					result.put("shipTypeName", shipTypeName);

				} else if (injection.equals("shipTypeMT")) {
					// ShipTypeMT = ShipTypeCode in DmVmaShipType
					String shipTypeMT = result.getString("shipTypeMT");
					String shipTypeMTName = StringPool.BLANK;

					if (Validator.isNotNull(shipTypeMT)) {
						DmVmaShipType dmVmaShipType = DmVmaShipTypeLocalServiceUtil
								.fetchByShipTypeCode(shipTypeMT);
						shipTypeMTName = dmVmaShipType != null ? dmVmaShipType
								.getShipTypeName() : StringPool.BLANK;
					}

					result.put("shipTypeMTName", shipTypeMTName);

				} else if (injection.equals("shipOwnerCode")) {
					String shipOwnerCode = result.getString("shipOwnerCode");
					String shipOwnerName = StringPool.BLANK;
					String shipOwnerAddress = StringPool.BLANK;
					if (Validator.isNotNull(shipOwnerCode)) {
						DmVmaShipOwner vmaShipOwner = DmVmaShipOwnerLocalServiceUtil
								.fetchByShipOwnerCode(shipOwnerCode);
						shipOwnerName = vmaShipOwner != null ? vmaShipOwner
								.getCompanyName() : StringPool.BLANK;
						shipOwnerAddress = vmaShipOwner != null ? vmaShipOwner
								.getCompanyAddress() : StringPool.BLANK;
					}

					result.put("shipOwnerName", shipOwnerName);
					result.put("shipOwnerAddress", shipOwnerAddress);

				} else if (injection.equals("shipOperatorCode")) {
					String shipOperatorCode = result
							.getString("shipOperatorCode");
					String shipOperatorName = StringPool.BLANK;
					String shipOperatorAddress = StringPool.BLANK;
					if (Validator.isNotNull(shipOperatorCode)) {
						DmVmaShipOwner vmaShipOwner = DmVmaShipOwnerLocalServiceUtil
								.fetchByShipOwnerCode(shipOperatorCode);
						shipOperatorAddress = vmaShipOwner != null ? vmaShipOwner
								.getCompanyAddress() : StringPool.BLANK;
						shipOperatorName = vmaShipOwner != null ? vmaShipOwner
								.getCompanyName() : StringPool.BLANK;
					}

					result.put("shipOperatorName", shipOperatorName);
					result.put("shipOperatorAddress", shipOperatorAddress);

				} else if (injection.equals("timeOfArrival")
						|| injection.equals("timeOfDeparture")) {
					String itineraryNo = result.getString("itineraryNo");
					String timeOfArrival = StringPool.BLANK;
					String timeOfDeparture = StringPool.BLANK;
					if (Validator.isNotNull(itineraryNo)) {
						try {
							VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
									.fetchByitineraryNo(itineraryNo);
							if (vmaItinerary != null
									&& vmaItinerary.getTimeOfArrival() != null) {
								timeOfArrival = FormatData.formatDateShort3
										.format(vmaItinerary.getTimeOfArrival());
							}

							if (vmaItinerary != null
									&& vmaItinerary.getTimeOfDeparture() != null) {
								timeOfDeparture = FormatData.formatDateShort3
										.format(vmaItinerary
												.getTimeOfDeparture());
							}

						} catch (SystemException e) {
							log.error(e.getMessage());
						}
					}

					result.put("timeOfArrival", timeOfArrival);
					result.put("timeOfDeparture", timeOfDeparture);
				}

				else if (injection.equals("shipCaptain")) {
					String itineraryNo = result.getString("itineraryNo");
					String shipCaptain = StringPool.BLANK;

					if (Validator.isNotNull(itineraryNo)) {
						try {
							VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
									.fetchByitineraryNo(itineraryNo);
							if (vmaItinerary != null) {
								shipCaptain = vmaItinerary.getShipCaptain();
							}

						} catch (SystemException e) {
							log.error(e.getMessage());
						}
					}

					result.put("shipCaptain", shipCaptain);

				}

				else if (injection.equals("anchoringPortHarbourCode")) {
					String anchoringPortHarbourCode = result
							.getString("anchoringPortHarbourCode");
					String anchoringPortHarbourName = StringPool.BLANK;
					if (Validator.isNotNull(anchoringPortHarbourCode)) {
						DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil
								.getByPortHarbourCode(anchoringPortHarbourCode);
						anchoringPortHarbourName = dmPortHarbour != null ? dmPortHarbour
								.getPortHarbourNameVN() : StringPool.BLANK;
					}

					result.put("anchoringPortHarbourName",
							anchoringPortHarbourName);
				} else if (injection.equals("shiftingPortHarbourCode")) {
					String shiftingPortHarbourCode = result
							.getString("shiftingPortHarbourCode");
					String shiftingPortHarbourName = StringPool.BLANK;
					if (Validator.isNotNull(shiftingPortHarbourCode)) {
						DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil
								.getByPortHarbourCode(shiftingPortHarbourCode);
						shiftingPortHarbourName = dmPortHarbour != null ? dmPortHarbour
								.getPortHarbourNameVN() : StringPool.BLANK;
					}

					result.put("shiftingPortHarbourName",
							shiftingPortHarbourName);
				}

				else if (injection.equals("anchoringPortWharfCode")) {
					String anchoringPortWharfCode = result
							.getString("anchoringPortWharfCode");
					String anchoringPortWharfName = StringPool.BLANK;
					if (Validator.isNotNull(anchoringPortWharfCode)) {
						DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
								.getByPortWharfCode(anchoringPortWharfCode);

						anchoringPortWharfName = dmPortWharf != null ? dmPortWharf
								.getPortWharfNameVN() : StringPool.BLANK;
					}

					result.put("anchoringPortWharfName", anchoringPortWharfName);

				} else if (injection.equals("shiftingPortWharfCode")) {
					String shiftingPortWharfCode = result
							.getString("shiftingPortWharfCode");
					String shiftingPortWharfName = StringPool.BLANK;
					if (Validator.isNotNull(shiftingPortWharfCode)) {
						DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
								.getByPortWharfCode(shiftingPortWharfCode);

						shiftingPortWharfName = dmPortWharf != null ? dmPortWharf
								.getPortWharfNameVN() : StringPool.BLANK;
					}

					result.put("shiftingPortWharfName", shiftingPortWharfName);

				}

				else if (injection.equals("portWharfCode")) {
					String portWharfCode = result.getString("portWharfCode");
					String portWharfName = StringPool.BLANK;
					if (Validator.isNotNull(portWharfCode)) {
						DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
								.getByPortWharfCode(portWharfCode);

						portWharfName = dmPortWharf != null ? dmPortWharf
								.getPortWharfNameVN() : StringPool.BLANK;
					}

					result.put("portWharfName", portWharfName);

				}

				else if (injection.equals("portHarbourCode")) {
					String portHarbourCode = result
							.getString("portHarbourCode");
					String portHarbourName = StringPool.BLANK;
					if (Validator.isNotNull(portHarbourCode)) {
						DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil
								.getByPortHarbourCode(portHarbourCode);
						portHarbourName = dmPortHarbour != null ? dmPortHarbour
								.getPortHarbourNameVN() : StringPool.BLANK;
					}

					result.put("portHarbourName", portHarbourName);

				}

				else if (injection.equals("portRegionCode")) {
					String portRegionCode = result.getString("portRegionCode");
					String portRegionName = StringPool.BLANK;
					if (Validator.isNotNull(portRegionName)) {
						DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil
								.getByPortRegionCode(portRegionCode);
						portRegionName = dmPortRegion != null ? dmPortRegion
								.getPortRegionNameVN() : StringPool.BLANK;
					}

					result.put("portRegionName", portRegionName);

				}

				else if (injection.equals("cargoCategory")) {
					String cargoCategory = result.getString("cargoCategory");

					String cargoCategoryName = StringPool.BLANK;
					if (Validator.isNotNull(cargoCategory)) {
						DmDataitem dataItem = DmDataItemLocalServiceUtil
								.findByDataGroupIdAndCode0(119, cargoCategory);
						cargoCategoryName = dataItem != null ? dataItem
								.getName() : StringPool.BLANK;
					}

					result.put("cargoCategoryName", cargoCategoryName);

				}

				else if (injection.equals("cargoType")) {
					String cargoType = result.getString("cargoType");
					String cargoTypeName = StringPool.BLANK;
					if (Validator.isNotNull(cargoType)) {
						DmDataitem dataItem = DmDataItemLocalServiceUtil
								.findByDataGroupIdAndCode0(115, cargoType);
						cargoTypeName = dataItem != null ? dataItem.getName()
								: StringPool.BLANK;
					}

					result.put("cargoTypeName", cargoTypeName);

				}

				else if (injection.equals("cargoCode")) {
					String cargoCode = result.getString("cargoCode");
					String cargoCodeName = StringPool.BLANK;
					if (Validator.isNotNull(cargoCode)) {
						DmDataitem dataItem = DmDataItemLocalServiceUtil
								.findByDataGroupIdAndCode0(124, cargoCode);
						cargoCodeName = dataItem != null ? dataItem.getName()
								: StringPool.BLANK;
					}

					result.put("cargoCodeName", cargoCodeName);

				}

				else if (injection.equals("unit")) {
					String unit = result.getString("unit");
					String unitName = StringPool.BLANK;
					if (Validator.isNotNull(unit)) {
						DmDataitem dataItem = DmDataItemLocalServiceUtil
								.findByDataGroupIdAndCode0(111, unit);
						unitName = dataItem != null ? dataItem.getName()
								: StringPool.BLANK;
					}

					result.put("unitName", unitName);

				}

				else if (injection.equals("portRegionCode_VN")) {
					String portRegionCode_VN = result
							.getString("portRegionCode");
					String portRegionNameVN = StringPool.BLANK;
					if (Validator.isNotNull(portRegionCode_VN)) {
						DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil
								.getByPortRegionCode(portRegionCode_VN);
						portRegionNameVN = dmPortRegion != null ? dmPortRegion
								.getPortRegionNameVN() : StringPool.BLANK;
					}

					result.put("portRegionNameVN", portRegionNameVN);

				}

				else if (injection.equals("portHarbourCode_VN")) {
					String portHarbourCode_VN = result
							.getString("portHarbourCode");
					String portHarbourNameVN = StringPool.BLANK;
					if (Validator.isNotNull(portHarbourCode_VN)) {
						DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil
								.getByPortHarbourCode(portHarbourCode_VN);
						portHarbourNameVN = dmPortHarbour != null ? dmPortHarbour
								.getPortHarbourNameVN() : StringPool.BLANK;
					}

					result.put("portHarbourNameVN", portHarbourNameVN);

				}

				else if (injection.equals("portWharfCode_VN")) {
					String portWharfCode_VN = result.getString("portWharfCode");
					String portWharfNameVN = StringPool.BLANK;
					if (Validator.isNotNull(portWharfCode_VN)) {
						DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
								.getByPortWharfCode(portWharfCode_VN);
						portWharfNameVN = dmPortWharf != null ? dmPortWharf
								.getPortWharfNameVN() : StringPool.BLANK;
					}

					result.put("portWharfNameVN", portWharfNameVN);

				}

				else if (injection.equals("shipOperatorName")) {
					String shipOperatorCode = result
							.getString("shipOperatorCode");
					String shipOperatorName = StringPool.BLANK;
					if (Validator.isNotNull(shipOperatorCode)) {
						DmVmaShipOwner dmVmaShipOwner = DmVmaShipOwnerLocalServiceUtil
								.fetchByShipOwnerCode(shipOperatorCode);
						shipOperatorName = dmVmaShipOwner != null ? dmVmaShipOwner
								.getCompanyName() : StringPool.BLANK;
					}

					result.put("shipOperatorName", shipOperatorName);
				}

				else if (injection.equals("payment2Merging")) {

					String itineraryNo = result.getString("itineraryNo");
					if (Validator.isNotNull(itineraryNo)) {
						try {
							VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
									.fetchByitineraryNo(itineraryNo);
							if (Validator.isNotNull(vmaItinerary)) {
								int countMerging = VmaScheduleMergingLocalServiceUtil
										.countByItineraryNo(itineraryNo);
								if (countMerging > 0) {
									result.put("payment2Merging", 1);
								} else {
									result.put("payment2Merging", 0);
								}
								
								int TINHPHI_TAUBIEN = 1;
								int TINHPHI_TAUBIEN_VAO = 0;
								int TINHPHI_TAUBIEN_ROI = 0;
								int TINHPHI_PTTND = 0;
								int TINHPHI_PTTND_VAO = 0;
								int TINHPHI_PTTND_ROI = 0;
								int TINHPHI_DOANLAI = 0;	
								
								if (countMerging > 0) {
									TINHPHI_TAUBIEN = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN",
											vmaItinerary, null );
									TINHPHI_TAUBIEN_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN_VAO",
											vmaItinerary, null );
									TINHPHI_TAUBIEN_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN_ROI",
											vmaItinerary, null );
									TINHPHI_PTTND = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND",
											vmaItinerary, null );
									TINHPHI_PTTND_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND_VAO",
											vmaItinerary, null );
									TINHPHI_PTTND_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND_ROI",
											vmaItinerary, null );
									TINHPHI_DOANLAI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_DOANLAI",
											vmaItinerary, null );
									
								} else {
									
									TINHPHI_TAUBIEN = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN",
											vmaItinerary, null, null );
									TINHPHI_TAUBIEN_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN_VAO",
											vmaItinerary, null, null );
									TINHPHI_TAUBIEN_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN_ROI",
											vmaItinerary, null, null );
									TINHPHI_PTTND = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND",
											vmaItinerary, null, null );
									TINHPHI_PTTND_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND_VAO",
											vmaItinerary, null, null );
									TINHPHI_PTTND_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND_ROI",
											vmaItinerary, null, null );
									
								}
								result.put("TINHPHI_PTTND", TINHPHI_PTTND);
								result.put("TINHPHI_PTTND_VAO", TINHPHI_PTTND_VAO);
								result.put("TINHPHI_PTTND_ROI", TINHPHI_PTTND_ROI);
								result.put("TINHPHI_TAUBIEN", TINHPHI_TAUBIEN);
								result.put("TINHPHI_TAUBIEN_VAO", TINHPHI_TAUBIEN_VAO);
								result.put("TINHPHI_TAUBIEN_ROI", TINHPHI_TAUBIEN_ROI);
								result.put("TINHPHI_DOANLAI", TINHPHI_DOANLAI);
							}

						} catch (SystemException e) {
							log.error(e.getMessage());
						}
					}

				}
			}
		}

		return result;
	}

	public static int requestTransactionByItineraryNo_NoticeShipType(String itineraryNo, String flagTransaction,
			VmaItinerary vmaItinerary, VmaItinerarySchedule vmaItinerarySchedule) throws SystemException {
		int result = 0;
		int TINHPHI_TAUBIEN = 1;
		int TINHPHI_TAUBIEN_VAO = 0;
		int TINHPHI_TAUBIEN_ROI = 0;
		int TINHPHI_PTTND = 0;
		int TINHPHI_PTTND_VAO = 0;
		int TINHPHI_PTTND_ROI = 0;
		int TINHPHI_DOANLAI = 0;
		
		int countMerging = VmaScheduleMergingLocalServiceUtil
				.countByItineraryNo(vmaItinerary.getItineraryNo());		
		int countMerging_In = VmaScheduleMergingLocalServiceUtil
				.countByItineraryNo_NoticeShipType(vmaItinerary.getItineraryNo(), 1);
		int countMerging_Out = VmaScheduleMergingLocalServiceUtil
				.countByItineraryNo_NoticeShipType(vmaItinerary.getItineraryNo(), 2);
		
		// Điều hướng màn hình tính phí. Mặc định tính phí tàu biển, dù có đoàn lai hay không.
				
		if (vmaItinerary.getVrCode().equalsIgnoreCase("VR-SI")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VR-SII")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VR-SI-SII")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("---")) {
			if (countMerging > 0) {
				log.info("Có đoàn lai, nhóm PTTNĐ thông thường");
				if (countMerging_In > 0 && countMerging_Out > 0) {
					TINHPHI_PTTND = 0;
					TINHPHI_PTTND_VAO = 0;
					TINHPHI_PTTND_ROI = 0;
					TINHPHI_TAUBIEN = 0;
					TINHPHI_TAUBIEN_VAO = 0;
					TINHPHI_TAUBIEN_ROI = 0;
					TINHPHI_DOANLAI = 1;	
				} else if (countMerging_In > 0 ) {
					TINHPHI_PTTND = 0;
					TINHPHI_PTTND_VAO = 0;
					TINHPHI_PTTND_ROI = 1;
					TINHPHI_TAUBIEN = 0;
					TINHPHI_TAUBIEN_VAO = 0;
					TINHPHI_TAUBIEN_ROI = 0;
					TINHPHI_DOANLAI = 1;	
				} else if (countMerging_Out > 0 ){
					TINHPHI_PTTND = 0;
					TINHPHI_PTTND_VAO = 1;
					TINHPHI_PTTND_ROI = 0;
					TINHPHI_TAUBIEN = 0;
					TINHPHI_TAUBIEN_VAO = 0;
					TINHPHI_TAUBIEN_ROI = 0;
					TINHPHI_DOANLAI = 1;
				}
			} else {
				TINHPHI_PTTND = 1;
				TINHPHI_PTTND_VAO = 0;
				TINHPHI_PTTND_ROI = 0;
				TINHPHI_TAUBIEN = 0;
				TINHPHI_TAUBIEN_VAO = 0;
				TINHPHI_TAUBIEN_ROI = 0;
				TINHPHI_DOANLAI = 0;					
			}
		} else if (vmaItinerary.getVrCode().equalsIgnoreCase("VR")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VRH")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VRH1")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VRH2")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VRH3")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VR-NA")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VR200")) {
			// Mặc định tính phí tàu biển, dù có đoàn lai hay không.				
			if (countMerging > 0) {
				log.info("Có đoàn lai, nhóm Tàu biển ");
				try {
					List<VmaScheduleMerging> lstVmaScheduleMerging = VmaScheduleMergingLocalServiceUtil.findByItineraryNo(itineraryNo);
					
					for (VmaScheduleMerging objVmaScheduleMerging : lstVmaScheduleMerging){
						if (objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-SI")
								|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-SII")
								|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-SI-SII")
								|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("---")) {
							TINHPHI_DOANLAI = 1;
						}
						if (objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-SB")
								|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-SB-SI")
								|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-SB-SII")
								|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-SB-SI-SII")) {
							// do nothing
						}
					}
				} catch (Exception e) {
					
				}
								
								
			} else {
				// do nothing
			}
			VmaScheduleTransfer vmaScheduleTransfer = new VmaScheduleTransfer();
			try {
				List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil.findByItineraryNo_NoticeShipType(itineraryNo, 2);
				
				if (vmaScheduleTransfers != null && vmaScheduleTransfers.size() > 0){
					vmaScheduleTransfer = vmaScheduleTransfers.get(0);
				}
			} catch (Exception e) {
				
			}
			if(Validator.isNotNull(vmaScheduleTransfer) && vmaScheduleTransfer.getGt().doubleValue() < 500){
				if (vmaScheduleTransfer.getVrCode().equalsIgnoreCase("VR-SB")
						|| vmaScheduleTransfer.getVrCode().equalsIgnoreCase("VR-SB-SI")
						|| vmaScheduleTransfer.getVrCode().equalsIgnoreCase("VR-SB-SII")
						|| vmaScheduleTransfer.getVrCode().equalsIgnoreCase("VR-SB-SI-SII")) {
					// Tinh phi PTTNĐ lượt rời
					TINHPHI_PTTND = 0;
					TINHPHI_PTTND_VAO = 0;
					TINHPHI_PTTND_ROI = 1;
					TINHPHI_TAUBIEN = 0;
					TINHPHI_TAUBIEN_VAO = 1;
					TINHPHI_TAUBIEN_ROI = 0;					
				} else {
					// Mặc định tính phí tàu biển	
				}
			}
		} else if (vmaItinerary.getVrCode().equalsIgnoreCase("VR-SB")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VR-SB-SI")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VR-SB-SII")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VR-SB-SI-SII")) {
			
			if (countMerging > 0) {
				log.info("Có đoàn lai, nhóm PTTNĐ VR-SB ");
				VmaItinerarySchedule vmaItineraryScheduleIn = new VmaItinerarySchedule();
				VmaItinerarySchedule vmaItineraryScheduleOut = new VmaItinerarySchedule();
				vmaItineraryScheduleIn = VmaItineraryScheduleLocalServiceUtil
						.findByItineraryNo_NoticeShipType(itineraryNo,
								1);
				vmaItineraryScheduleOut = VmaItineraryScheduleLocalServiceUtil
						.findByItineraryNo_NoticeShipType(itineraryNo,
								2);
				if (Validator.isNull(vmaItineraryScheduleIn)) {
					vmaItineraryScheduleIn = new VmaItinerarySchedule(); 
				}
				if (Validator.isNull(vmaItineraryScheduleOut)) {
					vmaItineraryScheduleOut = new VmaItinerarySchedule();
				}
				double totalGRT_In = 0;
				double totalGRT_Out = 0;
				if (Validator.isNotNull(vmaItineraryScheduleIn) && vmaItineraryScheduleIn.getGt().doubleValue() >=0) {
					totalGRT_In += vmaItineraryScheduleIn.getGt().doubleValue();
				}
				if (Validator.isNotNull(vmaItineraryScheduleOut) && vmaItineraryScheduleOut.getGt().doubleValue() >=0) {
					totalGRT_Out += vmaItineraryScheduleOut.getGt().doubleValue();
				}
				try {
					List<VmaScheduleMerging> lstVmaScheduleMerging = VmaScheduleMergingLocalServiceUtil.findByItineraryNo(itineraryNo);
					
					for (VmaScheduleMerging objVmaScheduleMerging : lstVmaScheduleMerging){
						if (objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-SI")
								|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-SII")
								|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-SI-SII")
								|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("---")) {
							TINHPHI_DOANLAI = 1;
						}
						if (objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-SB")
								|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-SB-SI")
								|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-SB-SII")
								|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-SB-SI-SII")
										|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR")
										|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VRH")
										|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VRH1")
										|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VRH2")
										|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VRH3")
										|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR-NA")
										|| objVmaScheduleMerging.getShipLashVRCode().equalsIgnoreCase("VR200")) {
							if (objVmaScheduleMerging.getNoticeShipType() == 1 && objVmaScheduleMerging.getGt().doubleValue() >=0){
								totalGRT_In += objVmaScheduleMerging.getGt().doubleValue();
							}
							if (objVmaScheduleMerging.getNoticeShipType() == 2 && objVmaScheduleMerging.getGt().doubleValue() >=0){
								totalGRT_Out += objVmaScheduleMerging.getGt().doubleValue();
							}
						}
					}
				} catch (Exception e) {
					
				}
				if ((Validator.isNotNull(vmaItineraryScheduleIn) && totalGRT_In >= 500)
						&& (Validator.isNotNull(vmaItineraryScheduleOut) && totalGRT_Out >= 500)) {
					TINHPHI_PTTND = 0;
					TINHPHI_PTTND_VAO = 0;
					TINHPHI_PTTND_ROI = 0;
					TINHPHI_TAUBIEN = 1;
					TINHPHI_TAUBIEN_VAO = 0;
					TINHPHI_TAUBIEN_ROI = 0;					
				} else {
					if ((Validator.isNotNull(vmaItineraryScheduleIn) && totalGRT_In >= 500)
							&& vmaItineraryScheduleIn.getRouteLevelCode().equalsIgnoreCase("1")) {
						TINHPHI_TAUBIEN = 0;
						TINHPHI_TAUBIEN_VAO = 1;	
						TINHPHI_PTTND = 0;
					} else if ((Validator.isNotNull(vmaItineraryScheduleIn) && totalGRT_In < 500) 
							|| vmaItineraryScheduleIn.getRouteLevelCode().equalsIgnoreCase("2")){
						TINHPHI_PTTND = 0;
						TINHPHI_PTTND_VAO = 0;					
						TINHPHI_TAUBIEN = 0;
						TINHPHI_TAUBIEN_VAO = 0;
						TINHPHI_DOANLAI = 1;
					}
					
					if ((Validator.isNotNull(vmaItineraryScheduleOut) && totalGRT_Out >= 500)
							&& vmaItineraryScheduleOut.getRouteLevelCode().equalsIgnoreCase("1")) {
						TINHPHI_TAUBIEN = 0;
						TINHPHI_TAUBIEN_ROI = 1;
						TINHPHI_PTTND = 0;
						
					}  else if ((Validator.isNotNull(vmaItineraryScheduleOut) && totalGRT_Out < 500) 
							|| vmaItineraryScheduleOut.getRouteLevelCode().equalsIgnoreCase("2")){
						TINHPHI_PTTND = 0;					
						TINHPHI_PTTND_ROI = 0;
						TINHPHI_TAUBIEN = 0;						
						TINHPHI_TAUBIEN_ROI = 0;
						TINHPHI_DOANLAI = 1;
					}
					
					if ((Validator.isNotNull(vmaItineraryScheduleIn) && totalGRT_In < 500) 
							&& (Validator.isNotNull(vmaItineraryScheduleOut) && totalGRT_Out < 500)) {
						TINHPHI_PTTND = 0;
						TINHPHI_PTTND_VAO = 0;
						TINHPHI_PTTND_ROI = 0;
						TINHPHI_TAUBIEN = 0;
						TINHPHI_TAUBIEN_VAO = 0;
						TINHPHI_TAUBIEN_ROI = 0;
						TINHPHI_DOANLAI = 1;
					}
				}
			
			} else {
				result = requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, flagTransaction,
						vmaItinerary, vmaItinerarySchedule, new VmaScheduleTransfer() ); 
				return result;
			}
		}
		if (flagTransaction.equalsIgnoreCase("TINHPHI_TAUBIEN")) {
			result = TINHPHI_TAUBIEN;
		} else if (flagTransaction.equalsIgnoreCase("TINHPHI_TAUBIEN_VAO")) {
			result = TINHPHI_TAUBIEN_VAO;
		} else if (flagTransaction.equalsIgnoreCase("TINHPHI_TAUBIEN_ROI")) {
			result = TINHPHI_TAUBIEN_ROI;
		} else if (flagTransaction.equalsIgnoreCase("TINHPHI_PTTND")) {
			result = TINHPHI_PTTND;
		} else if (flagTransaction.equalsIgnoreCase("TINHPHI_PTTND_VAO")) {
			result = TINHPHI_PTTND_VAO;
		} else if (flagTransaction.equalsIgnoreCase("TINHPHI_PTTND_ROI")) {
			result = TINHPHI_PTTND_ROI;
		} else if (flagTransaction.equalsIgnoreCase("TINHPHI_DOANLAI")) {
			result = TINHPHI_DOANLAI;
		} 
		return result;
	}
	
	public static int requestTransactionByItineraryNo_NoticeShipType_NoMerging(String itineraryNo, String flagTransaction,
			VmaItinerary vmaItinerary, VmaItinerarySchedule vmaItinerarySchedule, VmaScheduleTransfer vmaScheduleTransfer ) throws SystemException {
		int result = 0;
		// Mặc định tính phí tàu biển, dù có đoàn lai hay không.	
		int TINHPHI_TAUBIEN = 1;
		int TINHPHI_TAUBIEN_VAO = 0;
		int TINHPHI_TAUBIEN_ROI = 0;
		int TINHPHI_PTTND = 0;
		int TINHPHI_PTTND_VAO = 0;
		int TINHPHI_PTTND_ROI = 0;
		int TINHPHI_DOANLAI = 0;
		
		
		
		if (vmaItinerary.getVrCode().equalsIgnoreCase("VR-SI")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VR-SII")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VR-SI-SII")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("---")) {
			TINHPHI_PTTND = 1;
			TINHPHI_PTTND_VAO = 1;
			TINHPHI_PTTND_ROI = 1;
			TINHPHI_TAUBIEN = 0;
			TINHPHI_TAUBIEN_VAO = 0;
			TINHPHI_TAUBIEN_ROI = 0;
			TINHPHI_DOANLAI = 0;
			
			
		} else if (vmaItinerary.getVrCode().equalsIgnoreCase("VR")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VRH")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VRH1")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VRH2")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VRH3")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VR-NA")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VR200")) {
			// Mặc định tính phí tàu biển, dù có đoàn lai hay không.	
			try {
				List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil.findByItineraryNo_NoticeShipType(itineraryNo, 2);
				
				if (vmaScheduleTransfers != null && vmaScheduleTransfers.size() > 0){
					vmaScheduleTransfer = vmaScheduleTransfers.get(0);
				}
			} catch (Exception e) {
				
			}
			if(Validator.isNotNull(vmaScheduleTransfer) && vmaScheduleTransfer.getGt().doubleValue() < 500){
				if (vmaScheduleTransfer.getVrCode().equalsIgnoreCase("VR-SB")
						|| vmaScheduleTransfer.getVrCode().equalsIgnoreCase("VR-SB-SI")
						|| vmaScheduleTransfer.getVrCode().equalsIgnoreCase("VR-SB-SII")
						|| vmaScheduleTransfer.getVrCode().equalsIgnoreCase("VR-SB-SI-SII")) {
					// Tinh phi PTTNĐ lượt rời
					TINHPHI_PTTND = 0;
					TINHPHI_PTTND_VAO = 0;
					TINHPHI_PTTND_ROI = 1;
					TINHPHI_TAUBIEN = 0;
					TINHPHI_TAUBIEN_VAO = 1;
					TINHPHI_TAUBIEN_ROI = 0;
					TINHPHI_DOANLAI = 0;
				} else {
					// Mặc định tính phí tàu biển	
				}
			}			
		} else if (vmaItinerary.getVrCode().equalsIgnoreCase("VR-SB")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VR-SB-SI")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VR-SB-SII")
				|| vmaItinerary.getVrCode().equalsIgnoreCase("VR-SB-SI-SII")) {
			VmaItinerarySchedule vmaItineraryScheduleIn = new VmaItinerarySchedule();
			VmaItinerarySchedule vmaItineraryScheduleOut = new VmaItinerarySchedule();
			vmaItineraryScheduleIn = VmaItineraryScheduleLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo,
							1);
			vmaItineraryScheduleOut = VmaItineraryScheduleLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo,
							2);
			if (Validator.isNull(vmaItineraryScheduleIn)) {
				vmaItineraryScheduleIn = new VmaItinerarySchedule(); 
			}
			if (Validator.isNull(vmaItineraryScheduleOut)) {
				vmaItineraryScheduleOut = new VmaItinerarySchedule();
			}
			if ((Validator.isNotNull(vmaItineraryScheduleIn) && vmaItineraryScheduleIn.getGt().doubleValue() >= 500)
					&& (Validator.isNotNull(vmaItineraryScheduleOut) && vmaItineraryScheduleOut.getGt().doubleValue() >= 500)) {
				TINHPHI_PTTND = 0;
				TINHPHI_PTTND_VAO = 0;
				TINHPHI_PTTND_ROI = 0;
				TINHPHI_TAUBIEN = 1;
				TINHPHI_TAUBIEN_VAO = 0;
				TINHPHI_TAUBIEN_ROI = 0;
				TINHPHI_DOANLAI = 0;
			} else {
				if ((Validator.isNotNull(vmaItineraryScheduleIn) && vmaItineraryScheduleIn.getGt().doubleValue() >= 500)
						&& vmaItineraryScheduleIn.getRouteLevelCode().equalsIgnoreCase("1")) {
					TINHPHI_TAUBIEN = 0;
					TINHPHI_TAUBIEN_VAO = 1;	
					TINHPHI_PTTND = 0;
				} else if ((Validator.isNotNull(vmaItineraryScheduleIn) && vmaItineraryScheduleIn.getGt().doubleValue() < 500) 
						|| vmaItineraryScheduleIn.getRouteLevelCode().equalsIgnoreCase("2")){
					TINHPHI_PTTND = 0;
					TINHPHI_PTTND_VAO = 1;					
					TINHPHI_TAUBIEN = 0;
					TINHPHI_TAUBIEN_VAO = 0;
					TINHPHI_TAUBIEN_ROI = 0;
					TINHPHI_DOANLAI = 0;
				}
				
				if ((Validator.isNotNull(vmaItineraryScheduleOut) && vmaItineraryScheduleOut.getGt().doubleValue() >= 500)
						&& vmaItineraryScheduleOut.getRouteLevelCode().equalsIgnoreCase("1")) {
					TINHPHI_TAUBIEN = 0;
					TINHPHI_TAUBIEN_ROI = 1;
					TINHPHI_PTTND = 0;
					
				}  else if ((Validator.isNotNull(vmaItineraryScheduleOut) && vmaItineraryScheduleOut.getGt().doubleValue() < 500) 
						|| vmaItineraryScheduleOut.getRouteLevelCode().equalsIgnoreCase("2")){
					TINHPHI_PTTND = 0;					
					TINHPHI_PTTND_ROI = 1;
					TINHPHI_TAUBIEN = 0;
					TINHPHI_TAUBIEN_VAO = 0;
					TINHPHI_TAUBIEN_ROI = 0;
					TINHPHI_DOANLAI = 0;
				}
				
				if ((Validator.isNotNull(vmaItineraryScheduleIn) && vmaItineraryScheduleIn.getGt().doubleValue() < 500) 
						&& (Validator.isNotNull(vmaItineraryScheduleOut) && vmaItineraryScheduleOut.getGt().doubleValue() < 500)) {
					TINHPHI_PTTND = 1;
					TINHPHI_PTTND_VAO = 1;
					TINHPHI_PTTND_ROI = 1;
					TINHPHI_TAUBIEN = 0;
					TINHPHI_TAUBIEN_VAO = 0;
					TINHPHI_TAUBIEN_ROI = 0;
					TINHPHI_DOANLAI = 0;
				}
			}
		}
		
		Boolean flagTuyenVanTaiThuyLuotVao = false;
		Boolean flagTuyenVanTaiThuyLuotRoi = false;
		try {
			List<DmDataitem> lsTuyenLuong = DmDataItemLocalServiceUtil.findByDataGroupIdAndLevel(130, 2);
			// Trường hợp Tuyến vận tải thủy từ bờ ra đảo: Tính phí như tàu biển (theo TT 261)
			VmaItinerarySchedule vmaItineraryScheduleFirstIn = new VmaItinerarySchedule();
			VmaItinerarySchedule vmaItineraryScheduleFirstOut = new VmaItinerarySchedule();
			vmaItineraryScheduleFirstIn = VmaItineraryScheduleLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo,
							1);
			vmaItineraryScheduleFirstOut = VmaItineraryScheduleLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo,
							2);
			if (Validator.isNull(vmaItineraryScheduleFirstIn)) {
				vmaItineraryScheduleFirstIn = new VmaItinerarySchedule(); 
			}
			if (Validator.isNull(vmaItineraryScheduleFirstOut)) {
				vmaItineraryScheduleFirstOut = new VmaItinerarySchedule();
			}
			if ((Validator.isNotNull(vmaItineraryScheduleFirstIn) && vmaItineraryScheduleFirstIn.getChanelCodeList().length() > 0 )
			 || (Validator.isNotNull(vmaItineraryScheduleFirstOut) && vmaItineraryScheduleFirstOut.getChanelCodeList().length() > 0 )){
				for (DmDataitem objTuyenLuong : lsTuyenLuong) {
					if (Validator.isNotNull(vmaItineraryScheduleFirstIn) && vmaItineraryScheduleFirstIn.getChanelCodeList().length() > 0 ){
						if (objTuyenLuong.getNode1().equalsIgnoreCase(vmaItinerary.getMaritimeCode()) &&
								(vmaItineraryScheduleFirstIn.getChanelCodeList().contains(objTuyenLuong.getCode()) ||  
								vmaItineraryScheduleFirstIn.getChanelCodeList().equalsIgnoreCase(objTuyenLuong.getCode()))) {
							flagTuyenVanTaiThuyLuotVao = true;
						}
					}
					if (Validator.isNotNull(vmaItineraryScheduleFirstIn) && vmaItineraryScheduleFirstIn.getChanelCodeList().length() > 0 ){
						if (objTuyenLuong.getNode1().equalsIgnoreCase(vmaItinerary.getMaritimeCode()) && 
						(vmaItineraryScheduleFirstOut.getChanelCodeList().contains(objTuyenLuong.getCode()) ||  
								vmaItineraryScheduleFirstOut.getChanelCodeList().equalsIgnoreCase(objTuyenLuong.getCode()))) {
							flagTuyenVanTaiThuyLuotRoi = true;
						}
					}
				}
			}
			
			if ((flagTuyenVanTaiThuyLuotVao == true) && (flagTuyenVanTaiThuyLuotRoi == true)) {
				TINHPHI_PTTND = 0;
				TINHPHI_PTTND_VAO = 0;
				TINHPHI_PTTND_ROI = 0;
				TINHPHI_TAUBIEN = 1;
				TINHPHI_TAUBIEN_VAO = 0;
				TINHPHI_TAUBIEN_ROI = 0;
				TINHPHI_DOANLAI = 0;
			} else { 
				if ((flagTuyenVanTaiThuyLuotVao == true) ) {
					TINHPHI_PTTND = 0;		
					TINHPHI_PTTND_VAO = 0;					
					TINHPHI_TAUBIEN_VAO = 1;				
					TINHPHI_DOANLAI = 0;
				} 
				if ((flagTuyenVanTaiThuyLuotRoi == true) ) {
					TINHPHI_PTTND = 0;
					TINHPHI_PTTND_ROI = 0;
					TINHPHI_TAUBIEN_ROI = 1;				
					TINHPHI_DOANLAI = 0;
				}
			}	
		} catch (Exception e) {
			
		}
		
		if (flagTransaction.equalsIgnoreCase("TINHPHI_TAUBIEN")) {
			result = TINHPHI_TAUBIEN;
		} else if (flagTransaction.equalsIgnoreCase("TINHPHI_TAUBIEN_VAO")) {
			result = TINHPHI_TAUBIEN_VAO;
		} else if (flagTransaction.equalsIgnoreCase("TINHPHI_TAUBIEN_ROI")) {
			result = TINHPHI_TAUBIEN_ROI;
		} else if (flagTransaction.equalsIgnoreCase("TINHPHI_PTTND")) {
			result = TINHPHI_PTTND;
		} else if (flagTransaction.equalsIgnoreCase("TINHPHI_PTTND_VAO")) {
			result = TINHPHI_PTTND_VAO;
		} else if (flagTransaction.equalsIgnoreCase("TINHPHI_PTTND_ROI")) {
			result = TINHPHI_PTTND_ROI;
		} else if (flagTransaction.equalsIgnoreCase("TINHPHI_DOANLAI")) {
			result = TINHPHI_DOANLAI;
		} 
		return result;
	}
	
	
	public static JSONObject mergeJSON(JSONObject object1, JSONObject object2,
			Class<?> clazz) {

		HashMap<String, String> fieldMap = new HashMap<String, String>();

		String object2IdMappingName = (clazz.getSimpleName().substring(0, 1)
				.toLowerCase() + clazz.getSimpleName().substring(1)).replace(
				"ModelImpl", "") + "Id";

		Field[] fields = clazz.getDeclaredFields();

		if (fields != null) {
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String type = fields[i].getType().getName();
				if (String.valueOf(fieldName.charAt(0)).equals("_")) {
					fieldName = fieldName.substring(1);
				}

				fieldMap.put(fieldName.toLowerCase(), type);
			}
		}

		if (object2.has(object2IdMappingName)
				&& !object1.has(object2IdMappingName)) {
			object1.put(object2IdMappingName,
					object2.getLong(object2IdMappingName));
		}

		Iterator<String> keys = object2.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			if (key.equals("id")) {

				long id = object2.getLong("id");

				object1.put(object2IdMappingName, id);
			}
			if (!object1.has(key) && fieldMap.containsKey(key.toLowerCase())) {
				Object value = null;
				String type = fieldMap.get(key.toLowerCase());
				if (type.equals("java.lang.String")) {
					value = object2.getString(key);
					object1.put(key, value.toString());
				} else if (type.equals("int")) {
					value = object2.getInt(key);
					object1.put(key, (Integer) value);
				} else if (type.equals("long")) {
					value = object2.getLong(key);
					object1.put(key, (Long) value);
				} else if (type.equals("double")) {
					value = object2.getDouble(key);
					object1.put(key, (Double) value);
				} else if (type.equals("boolean")) {
					value = object2.getBoolean(key);
					object1.put(key, (Boolean) value);
				} else if (type.equals("java.util.Date")) {
					value = object2.getLong(key);
					long time = (Long) value;
					String strDate = StringPool.BLANK;
					if (time > 0) {
						Calendar c = Calendar.getInstance();
						c.setTimeInMillis(time);
						Date date = c.getTime();
						strDate = FormatData.formatDateShort3.format(date);
					} else {
						strDate = object2.getString(key);
					}
					// log.info("====" + "key====" + key + "value" +
					// object2.getString(key) + "======time>>>"+ time +
					// "strDate====" + strDate);
					object1.put(key, strDate);
				}

			}
		}

		return object1;
	}

	public static String getMarkedAsPendingLabel(int markedAsPending) {
		String label = StringPool.BLANK;

		switch (markedAsPending) {
		case 0:
			label = "Kh\u00F4ng y\u00EAu c\u1EA7u";
			break;
		case 1:
			label = "T\u1EA1m d\u1EEBng";
			break;
		default:
			break;
		}

		return label;
	}

	public static String getNoticeShipTypeName(int noticeShipType) {
		String name = StringPool.BLANK;
		switch (noticeShipType) {
		case 1:
			name = "L\u01B0\u1EE3t vào";
			break;
		case 2:
			name = "L\u01B0\u1EE3t r\u1EDDi";
			break;

		case 3:
			name = "L\u01B0\u1EE3t quá c\u1EA3nh";
			break;

		case 4:
			name = "L\u01B0\u1EE3t di chuy\u1EC3n";
			break;

		case 5:
			name = "L\u01B0\u1EE3t th\u1EED tàu";
			break;
		default:
			break;
		}

		return name;
	}

	public static String doExportExcelFile(HttpServletRequest request,
			ResourceResponse resourceResponse, String sheetName,
			String fileName, String[] headers, String[][] data)
			throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName);
		int rownum = 0;
		Cell cell;
		Row row;

		HSSFCellStyle style = createHeaderStyle(workbook);

		row = sheet.createRow(rownum);

		if (headers != null && headers.length > 0) {
			for (int i = 0; i < headers.length; i++) {
				cell = row.createCell(i, Cell.CELL_TYPE_STRING);
				cell.setCellValue(new String(headers[i].getBytes("UTF-8")));
				cell.setCellStyle(style);
			}
			rownum++;
		}

		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				row = sheet.createRow(rownum);
				String[] values = data[i];
				for (int c = 0; c < values.length; c++) {
					cell = row.createCell(c, Cell.CELL_TYPE_STRING);
					cell.setCellValue(values[c]);
					// cell.setCellStyle(style);
				}
				rownum++;
			}
		}

		SystemPath systemPath = new SystemPath();

		String filePath = systemPath.getExportPathFile() + fileName + ".xls";

		// System.out.print("===============>>>>>>>filePath " + filePath);

		OutputStream os = new FileOutputStream(new File(filePath));

		// OutputStream outputStream =
		// resourceResponse.getPortletOutputStream();

		workbook.write(os);

		os.close();

		return filePath;
	}

	private static HSSFCellStyle createHeaderStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setBoldweight((short) 900);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

	public static byte[] readFileToByteArray(File file) throws IOException {
		FileInputStream fis = null;
		// Creating a byte array using the length of the file
		// file.length returns long which is cast to int
		byte[] bArray = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(bArray);
			fis.close();

		} catch (IOException ioExp) {
			ioExp.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		return bArray;
	}

	static class SystemPath {

		public SystemPath() {
			this.setExportPathFile(realPath
					.substring(realPath.lastIndexOf(":"),
							realPath.lastIndexOf("/WEB-INF")).replaceFirst(":",
							"")
					+ "/export/");
		}

		public String realPath = this.getClass().getProtectionDomain()
				.getCodeSource().getLocation().toString();

		public String exportPathFile;

		public String getExportPathFile() {
			return exportPathFile;
		}

		public void setExportPathFile(String exportPathFile) {
			this.exportPathFile = exportPathFile;
		}
	}

	public static String buildSQLCondition(String columnName, Object value,
			String condition, String searchOperator, String... alias) {
		String queryCondition = StringPool.BLANK;
		queryCondition += StringPool.SPACE
				+ condition
				+ StringPool.SPACE
				+ (alias != null && alias.length > 0 ? (alias[0] + StringPool.PERIOD)
						: StringPool.BLANK) + columnName + StringPool.SPACE
				+ searchOperator + StringPool.SPACE + value;

		return queryCondition;
	}

	public static VmaScheduleAnchorage updateVmaScheduleAnchorage(
			VmaScheduleAnchorage vmaScheduleAnchorage,
			VmaItinerary vmaItinerary, VmaItinerarySchedule vmaItinerarySchedule) {

		if (vmaScheduleAnchorage == null) {
			vmaScheduleAnchorage = new VmaScheduleAnchorage();
		}

		// vmaScheduleAnchorage.setAnchoringDateFrom(anchoringDateFrom);
		vmaScheduleAnchorage.setAnchoringDateFrom(vmaItinerarySchedule
				.getTimeOfPORTArrival());
		// vmaScheduleAnchorage.setAnchoringDuration(anchoringDuration);
		vmaScheduleAnchorage.setAnchoringPortHarbourCode(vmaItinerarySchedule
				.getPortHarbourCode());
		vmaScheduleAnchorage.setAnchoringPortRegionCode(vmaItinerarySchedule
				.getPortRegionCode());
		vmaScheduleAnchorage.setAnchoringPortWharfCode(vmaItinerarySchedule
				.getPortWharfCode());
		vmaScheduleAnchorage.setItineraryNo(vmaItinerary.getItineraryNo());
		vmaScheduleAnchorage.setMakePayment(0);
		vmaScheduleAnchorage
				.setNameOfShip(vmaItinerarySchedule.getNameOfShip());

		vmaScheduleAnchorage.setNoticeShipType(vmaItinerarySchedule
				.getNoticeShipType()); // edit 18/10/2019 - Dungnv

		vmaScheduleAnchorage.setPortofAuthority(vmaItinerary.getMaritimeCode());
		// vmaScheduleAnchorage.setPortHarbourCode(portHarbourCode);
		// vmaScheduleAnchorage.setPortRegionCode(portRegionCode);
		// vmaScheduleAnchorage.setPortWharfCode(portWharfCode);
		// vmaScheduleAnchorage.setPurposeCode(purposeCode);
		// vmaScheduleAnchorage.setPurposeSpecified(purposeSpecified);
		vmaScheduleAnchorage.setShipAgencyCode(vmaItinerarySchedule
				.getShipAgencyCode());
		vmaScheduleAnchorage.setShipOperatorCode(vmaItinerarySchedule
				.getShipOperatorCode());
		vmaScheduleAnchorage.setShipOwnerCode(vmaItinerarySchedule
				.getShipOwnerCode());
		vmaScheduleAnchorage.setItineraryScheduleId(vmaItinerarySchedule
				.getId());
		return vmaScheduleAnchorage;
	}

	public static VmaScheduleAnchorage updateVmaScheduleAnchorage(
			VmaScheduleAnchorage vmaScheduleAnchorage,
			VmaItinerary vmaItinerary,
			VmaItinerarySchedule vmaItinerarySchedule,
			VmaScheduleShifting vmaScheduleShifting) {

		if (vmaScheduleAnchorage == null) {
			vmaScheduleAnchorage = new VmaScheduleAnchorage();
		}

		// vmaScheduleAnchorage.setAnchoringDateFrom(anchoringDateFrom);
		vmaScheduleAnchorage.setAnchoringDateTo(vmaItinerarySchedule
				.getTimeOfDeparture());
		// vmaScheduleAnchorage.setAnchoringDuration(anchoringDuration);
		vmaScheduleAnchorage
				.setAnchoringPortHarbourCode(vmaScheduleShifting != null ? vmaScheduleShifting
						.getShiftingPortHarbourCode() : vmaItinerarySchedule
						.getPortHarbourCode());
		vmaScheduleAnchorage.setAnchoringPortRegionCode(vmaItinerarySchedule
				.getPortRegionCode());
		vmaScheduleAnchorage
				.setAnchoringPortWharfCode(vmaScheduleShifting != null ? vmaScheduleShifting
						.getShiftingPortWharfCode() : vmaItinerarySchedule
						.getPortWharfCode());
		vmaScheduleAnchorage.setItineraryNo(vmaItinerary.getItineraryNo());
		vmaScheduleAnchorage.setMakePayment(0);
		vmaScheduleAnchorage
				.setNameOfShip(vmaItinerarySchedule.getNameOfShip());

		vmaScheduleAnchorage.setNoticeShipType(vmaItinerarySchedule
				.getNoticeShipType()); // edit 18/10/2019 - Dungnv

		vmaScheduleAnchorage.setPortofAuthority(vmaItinerary.getMaritimeCode());
		vmaScheduleAnchorage
				.setPortHarbourCode(vmaScheduleShifting != null ? vmaScheduleShifting
						.getAnchoringPortHarbourCode() : null);
		vmaScheduleAnchorage.setPortRegionCode(vmaItinerarySchedule
				.getPortRegionCode());
		vmaScheduleAnchorage
				.setPortWharfCode(vmaScheduleShifting != null ? vmaScheduleShifting
						.getAnchoringPortWharfCode() : null);
		// vmaScheduleAnchorage.setPurposeCode(purposeCode);
		// vmaScheduleAnchorage.setPurposeSpecified(purposeSpecified);
		vmaScheduleAnchorage.setShipAgencyCode(vmaItinerarySchedule
				.getShipAgencyCode());
		vmaScheduleAnchorage.setShipOperatorCode(vmaItinerarySchedule
				.getShipOperatorCode());
		vmaScheduleAnchorage.setShipOwnerCode(vmaItinerarySchedule
				.getShipOwnerCode());
		vmaScheduleAnchorage
				.setItineraryScheduleId(vmaScheduleShifting != null ? vmaScheduleShifting
						.getItineraryScheduleId() : vmaItinerarySchedule
						.getId());
		return vmaScheduleAnchorage;
	}

	public static VmaItinerarySchedule updateVmaItinerarySchedule(
			VmaItinerarySchedule vmaItinerarySchedule,
			VmaScheduleShifting vmaScheduleShifting, VmaItinerary vmaItinerary) {

		if (vmaItinerarySchedule == null) {
			vmaItinerarySchedule = new VmaItinerarySchedule();
		}

		VmaShip vmaShip = null;

		try {
			String imoNumber = vmaItinerary.getImoNumber();
			String callSign = vmaItinerary.getCallSign();
			if (imoNumber.trim().length() >= 7) {

				vmaShip = VmaShipLocalServiceUtil.fetchByIMONumber_CallSign(
						imoNumber, callSign);
			} else {

				vmaShip = VmaShipLocalServiceUtil.fetchByCallSign(callSign);
				// Tim lai theo registryNumber
				if (!vmaShip.getShipName().contains(
						vmaItinerary.getNameOfShip())) {
					vmaShip = VmaShipLocalServiceUtil
							.fetchByRegistryNumber(vmaItinerary
									.getRegistryNumber());
				}
			}
		} catch (Exception e) {
		}
		if (vmaShip != null) {
			vmaItinerarySchedule.setBreadth(vmaShip.getBreadth());
			vmaItinerarySchedule.setClearanceHeight(vmaShip
					.getClearanceHeight());
			vmaItinerarySchedule.setPower(vmaShip.getPower());
			vmaItinerarySchedule.setMaxDraft(vmaShip.getMaxDraft());
			vmaItinerarySchedule.setShipBoat(vmaShip.getShipBoat());
		}
		DmShipAgency dmShipAgency = null;
		try {
			dmShipAgency = DmShipAgencyLocalServiceUtil
					.getByShipAgencyCode(vmaScheduleShifting
							.getTaxCodeOfShipownersAgents());
		} catch (Exception e) {
		}
		if (dmShipAgency != null) {
			vmaItinerarySchedule.setShipAgencyName(dmShipAgency
					.getShipAgencyName());
			vmaItinerarySchedule
					.setShipAgencyAddress(dmShipAgency.getAddress());
			vmaItinerarySchedule.setShipAgencyContactEmail(dmShipAgency
					.getEmail());
			vmaItinerarySchedule.setShipAgencyPhone(dmShipAgency.getPhone());
			vmaItinerarySchedule.setShipAgencyFax(dmShipAgency.getFax());
		}
		vmaItinerarySchedule.setShipAgencyCode(vmaScheduleShifting
				.getTaxCodeOfShipownersAgents());
		vmaItinerarySchedule.setGt(vmaScheduleShifting.getGt());
		vmaItinerarySchedule.setNt(vmaScheduleShifting.getNt());
		vmaItinerarySchedule.setDwt(vmaScheduleShifting.getDwt());
		vmaItinerarySchedule.setLoa(vmaScheduleShifting.getLoa());
		vmaItinerarySchedule.setShownDraftxF(vmaScheduleShifting
				.getShownDraftxF());
		vmaItinerarySchedule.setShownDraftxA(vmaScheduleShifting
				.getShownDraftxA());
		// vmaItinerarySchedule.setId(id);
		vmaItinerarySchedule.setItineraryNo(vmaScheduleShifting
				.getItineraryNo());
		// vmaItinerarySchedule.setDocumentName(documentName);
		// vmaItinerarySchedule.setDocumentYear(documentYear);
		vmaItinerarySchedule.setNoticeShipType(4);
		vmaItinerarySchedule.setNameOfShip(vmaScheduleShifting.getNameOfShip());
		vmaItinerarySchedule.setShipOwnerCode(vmaItinerary.getShipOwnerCode());
		vmaItinerarySchedule.setShipOwnersName(vmaItinerary.getShipOwnerName());
		vmaItinerarySchedule.setShipOperatorCode(vmaItinerary
				.getShipOperatorCode());
		vmaItinerarySchedule.setShipOperatorName(vmaItinerary
				.getShipOperatorName());
		// vmaItinerarySchedule.setSecurityLevelCode(securityLevelCode);
		vmaItinerarySchedule.setArrivalPortCode(vmaItinerary
				.getArrivalPortCode());
		// Edit by Dungnv
		vmaItinerarySchedule.setPortRegionCode(vmaScheduleShifting
				.getShiftingPortRegionCode());
		vmaItinerarySchedule.setCertificateNo(vmaScheduleShifting
				.getCertificateNo());
		vmaItinerarySchedule.setPortHarbourCode(vmaScheduleShifting
				.getShiftingPortHarbourCode());
		vmaItinerarySchedule.setPortWharfCode(vmaScheduleShifting
				.getShiftingPortWharfCode());
		vmaItinerarySchedule.setUnitLOA(vmaScheduleShifting.getLoaunit());
		// vmaItinerarySchedule.setUnitBreadth(unitBreadth);
		// vmaItinerarySchedule.setUnitClearanceHeight(unitClearanceHeight);
		vmaItinerarySchedule.setUnitShownDraftxF(vmaScheduleShifting
				.getUnitShownDraftxF());
		vmaItinerarySchedule.setUnitShownDraftxA(vmaScheduleShifting
				.getUnitShownDraftxF());
		// vmaItinerarySchedule.setUnitGRT(unitGrt);
		// vmaItinerarySchedule.setUnitNT(unitNt);
		// vmaItinerarySchedule.setUnitDWT(unitDwt);
		// vmaItinerarySchedule.setUnitPower(unitPower);
		// vmaItinerarySchedule.setUnitMaxDraft(unitMaxDraft);
		vmaItinerarySchedule.setPurposeCode(vmaScheduleShifting
				.getPurposeCode());
		vmaItinerarySchedule.setPurposeSpecified(vmaScheduleShifting
				.getReasonToShift());
		// vmaItinerarySchedule.setCrewNumber(crewNumber);
		// vmaItinerarySchedule.setPassengerNumber(passengerNumber);
		// vmaItinerarySchedule.setAsPerManifest(asPerManifest);
		// vmaItinerarySchedule.setContainerNumber(containerNumber);
		// vmaItinerarySchedule.setNumberTEU(numberTEU);
		// vmaItinerarySchedule.setNumberTNE(numberTNE);
		vmaItinerarySchedule.setTimeOfDeparture(vmaScheduleShifting
				.getShiftingDate());
		// vmaItinerarySchedule.setTimeOfArrival(timeOfArrival);
		// vmaItinerarySchedule.setTimeOfPORTArrival(timeOfPORTArrival);
		// vmaItinerarySchedule.setTimeOfPilotOnBoard(timeOfPilotOnBoard);
		// vmaItinerarySchedule.setTimeOfApproval(timeOfApproval);
		// vmaItinerarySchedule.setTugBoat(tugBoat);
		// vmaItinerarySchedule.setDo_(do_);
		// vmaItinerarySchedule.setFo_(fo_);
		// vmaItinerarySchedule.setFw_(fw_);
		// vmaItinerarySchedule.setOilWater(oilWater);
		// vmaItinerarySchedule.setQuantityOfCargo(quantityOfCargo);
		// vmaItinerarySchedule.setRemainingCargo(remainingCargo);
		// vmaItinerarySchedule.setShipRequirementsInTermsOfWaste(shipRequirementsInTermsOfWaste);
		// vmaItinerarySchedule.setPreviousPortsOfCall(previousPortsOfCall);
		// vmaItinerarySchedule.setSubsequentPortsOfCall(subsequentPortsOfCall);
		// vmaItinerarySchedule.setDischargedPorts(dischargedPorts);
		// vmaItinerarySchedule.setPortGoingToStateName(portGoingToStateName);
		// vmaItinerarySchedule.setPortGoingToCode(portGoingToCode);
		// vmaItinerarySchedule.setLastPortOfCallCode(lastPortOfCallCode);
		// vmaItinerarySchedule.setLastProvinceCode(lastProvinceCode);
		// vmaItinerarySchedule.setLastMaritimePortCode(lastMaritimePortCode);
		// vmaItinerarySchedule.setLastPortRegionCode(lastPortRegionCode);
		// vmaItinerarySchedule.setLastPortHarbourCode(lastPortHarbourCode);
		// vmaItinerarySchedule.setLastPortWharfCode(lastPortWharfCode);
		// vmaItinerarySchedule.setNextProvinceCode(nextProvinceCode);
		// vmaItinerarySchedule.setNextMaritimePortCode(nextMaritimePortCode);
		// vmaItinerarySchedule.setNextPortRegionCode(nextPortRegionCode);
		// vmaItinerarySchedule.setNextPortHarbourCode(nextPortHarbourCode);
		// vmaItinerarySchedule.setNextPortWharfCode(nextPortWharfCode);
		vmaItinerarySchedule.setChanelCodeList(vmaScheduleShifting
				.getChanelCodeList());
		vmaItinerarySchedule.setChanelName(vmaScheduleShifting.getChanelName());
		vmaItinerarySchedule.setRemarks(vmaScheduleShifting.getRemarks());
		// vmaItinerarySchedule.setRequestCodeNoticeShipMessage(requestCodeNoticeShipMessage);
		// vmaItinerarySchedule.setRequestCodeGeneralDeclaration(requestCodeGeneralDeclaration);
		// vmaItinerarySchedule.setRequestCodeShipSecurityMessage(requestCodeShipSecurityMessage);
		vmaItinerarySchedule.setModifiedDate(new Date());
		vmaItinerarySchedule.setInitFrom(vmaScheduleShifting.getFrom());
		return vmaItinerarySchedule;
	}

	public static VmaItinerarySchedule updateVmaItinerarySchedule(
			VmaItinerarySchedule vmaItinerarySchedule,
			VmaScheduleShifting vmaScheduleShifting, VmaItinerary vmaItinerary,
			double crewNumber, double passengerNumber) {

		if (vmaItinerarySchedule == null) {
			vmaItinerarySchedule = new VmaItinerarySchedule();
		}

		VmaShip vmaShip = null;

		try {
			String imoNumber = vmaItinerary.getImoNumber();
			String callSign = vmaItinerary.getCallSign();
			if (imoNumber.trim().length() >= 7) {

				vmaShip = VmaShipLocalServiceUtil.fetchByIMONumber_CallSign(
						imoNumber, callSign);
			} else {

				vmaShip = VmaShipLocalServiceUtil.fetchByCallSign(callSign);
				// Tim lai theo registryNumber
				if (!vmaShip.getShipName().contains(
						vmaItinerary.getNameOfShip())) {
					vmaShip = VmaShipLocalServiceUtil
							.fetchByRegistryNumber(vmaItinerary
									.getRegistryNumber());
				}
			}
		} catch (Exception e) {
		}
		if (vmaShip != null) {
			vmaItinerarySchedule.setBreadth(vmaShip.getBreadth());
			vmaItinerarySchedule.setClearanceHeight(vmaShip
					.getClearanceHeight());
			vmaItinerarySchedule.setPower(vmaShip.getPower());
			vmaItinerarySchedule.setMaxDraft(vmaShip.getMaxDraft());
			vmaItinerarySchedule.setShipBoat(vmaShip.getShipBoat());
		}
		DmShipAgency dmShipAgency = null;
		try {
			dmShipAgency = DmShipAgencyLocalServiceUtil
					.getByShipAgencyCode(vmaScheduleShifting
							.getTaxCodeOfShipownersAgents());
		} catch (Exception e) {
		}
		if (dmShipAgency != null) {
			vmaItinerarySchedule.setShipAgencyName(dmShipAgency
					.getShipAgencyName());
			vmaItinerarySchedule
					.setShipAgencyAddress(dmShipAgency.getAddress());
			vmaItinerarySchedule.setShipAgencyContactEmail(dmShipAgency
					.getEmail());
			vmaItinerarySchedule.setShipAgencyPhone(dmShipAgency.getPhone());
			vmaItinerarySchedule.setShipAgencyFax(dmShipAgency.getFax());
		}
		vmaItinerarySchedule.setShipAgencyCode(vmaScheduleShifting
				.getTaxCodeOfShipownersAgents());
		vmaItinerarySchedule.setGt(vmaScheduleShifting.getGt());
		vmaItinerarySchedule.setNt(vmaScheduleShifting.getNt());
		vmaItinerarySchedule.setDwt(vmaScheduleShifting.getDwt());
		vmaItinerarySchedule.setLoa(vmaScheduleShifting.getLoa());
		vmaItinerarySchedule.setShownDraftxF(vmaScheduleShifting
				.getShownDraftxF());
		vmaItinerarySchedule.setShownDraftxA(vmaScheduleShifting
				.getShownDraftxA());
		vmaItinerarySchedule.setItineraryNo(vmaScheduleShifting
				.getItineraryNo());
		vmaItinerarySchedule.setNoticeShipType(4);
		vmaItinerarySchedule.setNameOfShip(vmaScheduleShifting.getNameOfShip());
		vmaItinerarySchedule.setShipOwnerCode(vmaItinerary.getShipOwnerCode());
		vmaItinerarySchedule.setShipOwnersName(vmaItinerary.getShipOwnerName());
		vmaItinerarySchedule.setShipOperatorCode(vmaItinerary
				.getShipOperatorCode());
		vmaItinerarySchedule.setShipOperatorName(vmaItinerary
				.getShipOperatorName());
		vmaItinerarySchedule.setArrivalPortCode(vmaItinerary
				.getArrivalPortCode());
		vmaItinerarySchedule.setPortRegionCode(vmaScheduleShifting
				.getShiftingPortRegionCode());
		vmaItinerarySchedule.setCertificateNo(vmaScheduleShifting
				.getCertificateNo());
		vmaItinerarySchedule.setPortHarbourCode(vmaScheduleShifting
				.getShiftingPortHarbourCode());
		vmaItinerarySchedule.setPortWharfCode(vmaScheduleShifting
				.getShiftingPortWharfCode());
		vmaItinerarySchedule.setUnitLOA(vmaScheduleShifting.getLoaunit());
		vmaItinerarySchedule.setUnitShownDraftxF(vmaScheduleShifting
				.getUnitShownDraftxF());
		vmaItinerarySchedule.setUnitShownDraftxA(vmaScheduleShifting
				.getUnitShownDraftxF());
		vmaItinerarySchedule.setPurposeCode(vmaScheduleShifting
				.getPurposeCode());
		vmaItinerarySchedule.setPurposeSpecified(vmaScheduleShifting
				.getReasonToShift());
		vmaItinerarySchedule.setCrewNumber(BigDecimal.valueOf(crewNumber));
		vmaItinerarySchedule.setPassengerNumber(BigDecimal.valueOf(passengerNumber));
		vmaItinerarySchedule.setTimeOfDeparture(vmaScheduleShifting
				.getShiftingDate());
		vmaItinerarySchedule.setChanelCodeList(vmaScheduleShifting
				.getChanelCodeList());
		vmaItinerarySchedule.setChanelName(vmaScheduleShifting.getChanelName());
		vmaItinerarySchedule.setRemarks(vmaScheduleShifting.getRemarks());
		vmaItinerarySchedule.setModifiedDate(new Date());
		vmaItinerarySchedule.setInitFrom(vmaScheduleShifting.getFrom());
		return vmaItinerarySchedule;
	}

	public static VmaItinerarySchedule updateVmaItinerarySchedule(
			VmaItinerarySchedule vmaItinerarySchedule,
			VmaScheduleTesting vmaScheduleTesting, VmaItinerary vmaItinerary)
			throws SystemException {

		if (vmaItinerary == null) {
			throw new SystemException();
		}
		if (vmaScheduleTesting == null) {
			throw new SystemException();
		}

		if (vmaItinerarySchedule == null) {
			vmaItinerarySchedule = new VmaItinerarySchedule();
		}

		VmaShip vmaShip = null;
		try {
			String imoNumber = vmaItinerary.getImoNumber();
			String callSign = vmaItinerary.getCallSign();
			if (imoNumber.trim().length() >= 7) {

				vmaShip = VmaShipLocalServiceUtil.fetchByIMONumber_CallSign(
						imoNumber, callSign);
			} else {

				vmaShip = VmaShipLocalServiceUtil.fetchByCallSign(callSign);
				// Tim lai theo registryNumber
				if (!vmaShip.getShipName().contains(
						vmaItinerary.getNameOfShip())) {
					vmaShip = VmaShipLocalServiceUtil
							.fetchByRegistryNumber(vmaItinerary
									.getRegistryNumber());
				}
			}
		} catch (Exception e) {
		}
		try {

			vmaItinerarySchedule.setShipBoat(vmaShip.getShipBoat());
			vmaItinerarySchedule.setShipAgencyCode(vmaShip.getShipAgencyCode());

			DmShipAgency dmShipAgency = DmShipAgencyLocalServiceUtil
					.getByShipAgencyCode(vmaShip.getShipAgencyCode());
			vmaItinerarySchedule
					.setShipAgencyName(dmShipAgency != null ? dmShipAgency
							.getShipAgencyName() : StringPool.BLANK);
			vmaItinerarySchedule
					.setShipAgencyAddress(dmShipAgency != null ? dmShipAgency
							.getAddress() : StringPool.BLANK);
			vmaItinerarySchedule
					.setShipAgencyContactEmail(dmShipAgency != null ? dmShipAgency
							.getEmail() : StringPool.BLANK);
			vmaItinerarySchedule
					.setShipAgencyPhone(dmShipAgency != null ? dmShipAgency
							.getPhone() : StringPool.BLANK);
			vmaItinerarySchedule
					.setShipAgencyFax(dmShipAgency != null ? dmShipAgency
							.getFax() : StringPool.BLANK);

			vmaItinerarySchedule.setGt(vmaShip.getGt());
			vmaItinerarySchedule.setNt(vmaShip.getNt());
			vmaItinerarySchedule.setDwt(vmaScheduleTesting.getDwt());
			vmaItinerarySchedule.setLoa(vmaScheduleTesting.getLoa());
			vmaItinerarySchedule.setBreadth(vmaShip.getBreadth());
			vmaItinerarySchedule.setClearanceHeight(vmaShip
					.getClearanceHeight());
			vmaItinerarySchedule.setPower(vmaShip.getPower());
			vmaItinerarySchedule.setMaxDraft(vmaShip.getMaxDraft());
			vmaItinerarySchedule.setShownDraftxF(vmaScheduleTesting
					.getShownDraftxF());
			vmaItinerarySchedule.setShownDraftxA(vmaScheduleTesting
					.getShownDraftxA());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		// vmaItinerarySchedule.setId(id);
		vmaItinerarySchedule
				.setItineraryNo(vmaScheduleTesting.getItineraryNo());
		// vmaItinerarySchedule.setDocumentName(documentName);
		// vmaItinerarySchedule.setDocumentYear(documentYear);
		vmaItinerarySchedule.setNoticeShipType(5);

		vmaItinerarySchedule.setNameOfShip(vmaScheduleTesting.getNameOfShip());
		vmaItinerarySchedule.setShipOwnerCode(vmaItinerary.getShipOwnerCode());
		vmaItinerarySchedule.setShipOwnersName(vmaItinerary.getShipOwnerName());
		vmaItinerarySchedule.setShipOperatorCode(vmaItinerary
				.getShipOperatorCode());
		vmaItinerarySchedule.setShipOperatorName(vmaItinerary
				.getShipOperatorName());

		// vmaItinerarySchedule.setSecurityLevelCode(securityLevelCode);
		vmaItinerarySchedule.setArrivalPortCode(vmaItinerary
				.getArrivalPortCode());
		// vmaItinerarySchedule.setPortRegionCode(portRegionCode);
		vmaItinerarySchedule.setCertificateNo(vmaScheduleTesting
				.getCertificateNo());
		vmaItinerarySchedule.setPortHarbourCode(vmaScheduleTesting
				.getShiftingPortHarbourCode());
		vmaItinerarySchedule.setPortWharfCode(vmaScheduleTesting
				.getShiftingPortWharfCode());

		vmaItinerarySchedule.setUnitLOA(vmaScheduleTesting.getLoaUnit());
		// vmaItinerarySchedule.setUnitBreadth(unitBreadth);
		// vmaItinerarySchedule.setUnitClearanceHeight(unitClearanceHeight);
		vmaItinerarySchedule.setUnitShownDraftxF(vmaScheduleTesting
				.getUnitShownDraftxF());
		vmaItinerarySchedule.setUnitShownDraftxA(vmaScheduleTesting
				.getUnitShownDraftxF());
		// vmaItinerarySchedule.setUnitGRT(unitGrt);
		// vmaItinerarySchedule.setUnitNT(unitNt);
		// vmaItinerarySchedule.setUnitDWT(unitDwt);
		// vmaItinerarySchedule.setUnitPower(unitPower);
		// vmaItinerarySchedule.setUnitMaxDraft(unitMaxDraft);
		vmaItinerarySchedule.setPurposeCode("C24");
		vmaItinerarySchedule.setPurposeSpecified("Testing");
		// vmaItinerarySchedule.setCrewNumber(crewNumber);
		// vmaItinerarySchedule.setPassengerNumber(passengerNumber);
		// vmaItinerarySchedule.setAsPerManifest(asPerManifest);
		// vmaItinerarySchedule.setContainerNumber(containerNumber);
		// vmaItinerarySchedule.setNumberTEU(numberTEU);
		// vmaItinerarySchedule.setNumberTNE(numberTNE);
		vmaItinerarySchedule.setTimeOfDeparture(vmaScheduleTesting
				.getTestingFrom());
		// vmaItinerarySchedule.setTimeOfArrival(timeOfArrival);
		// vmaItinerarySchedule.setTimeOfPORTArrival(timeOfPORTArrival);
		// vmaItinerarySchedule.setTimeOfPilotOnBoard(timeOfPilotOnBoard);
		// vmaItinerarySchedule.setTimeOfApproval(timeOfApproval);
		// vmaItinerarySchedule.setTugBoat(tugBoat);
		// vmaItinerarySchedule.setDo_(do_);
		// vmaItinerarySchedule.setFo_(fo_);
		// vmaItinerarySchedule.setFw_(fw_);
		// vmaItinerarySchedule.setOilWater(oilWater);
		// vmaItinerarySchedule.setQuantityOfCargo(quantityOfCargo);
		// vmaItinerarySchedule.setRemainingCargo(remainingCargo);
		// vmaItinerarySchedule.setShipRequirementsInTermsOfWaste(shipRequirementsInTermsOfWaste);
		// vmaItinerarySchedule.setPreviousPortsOfCall(previousPortsOfCall);
		// vmaItinerarySchedule.setSubsequentPortsOfCall(subsequentPortsOfCall);
		// vmaItinerarySchedule.setDischargedPorts(dischargedPorts);
		// vmaItinerarySchedule.setPortGoingToStateName(portGoingToStateName);
		// vmaItinerarySchedule.setPortGoingToCode(portGoingToCode);
		// vmaItinerarySchedule.setLastPortOfCallCode(lastPortOfCallCode);
		// vmaItinerarySchedule.setLastProvinceCode(lastProvinceCode);
		// vmaItinerarySchedule.setLastMaritimePortCode(lastMaritimePortCode);
		// vmaItinerarySchedule.setLastPortRegionCode(lastPortRegionCode);
		// vmaItinerarySchedule.setLastPortHarbourCode(lastPortHarbourCode);
		// vmaItinerarySchedule.setLastPortWharfCode(lastPortWharfCode);
		// vmaItinerarySchedule.setNextProvinceCode(nextProvinceCode);
		// vmaItinerarySchedule.setNextMaritimePortCode(nextMaritimePortCode);
		// vmaItinerarySchedule.setNextPortRegionCode(nextPortRegionCode);
		// vmaItinerarySchedule.setNextPortHarbourCode(nextPortHarbourCode);
		// vmaItinerarySchedule.setNextPortWharfCode(nextPortWharfCode);
		vmaItinerarySchedule.setChanelCodeList(vmaScheduleTesting
				.getChanelCodeList());
		vmaItinerarySchedule.setChanelName(vmaScheduleTesting.getChanelName());
		vmaItinerarySchedule.setRemarks(vmaScheduleTesting.getRemarks());
		// vmaItinerarySchedule.setRequestCodeNoticeShipMessage(requestCodeNoticeShipMessage);
		// vmaItinerarySchedule.setRequestCodeGeneralDeclaration(requestCodeGeneralDeclaration);
		// vmaItinerarySchedule.setRequestCodeShipSecurityMessage(requestCodeShipSecurityMessage);
		vmaItinerarySchedule.setModifiedDate(new Date());
		vmaItinerarySchedule.setInitFrom(vmaScheduleTesting.getFrom());

		return vmaItinerarySchedule;
	}

	public static double getArchorageDurationTime(String itineraryNo,
			int noticeShipType, int makePayment) {
		VmaItinerarySchedule vmaItinerarySchedule = null;

		double duration = 0;

		try {
			vmaItinerarySchedule = VmaItineraryScheduleLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo,
							noticeShipType);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (noticeShipType == 1) {
			List<VmaScheduleAnchorage> vmaScheduleAnchorages = VmaScheduleAnchorageLocalServiceUtil
					.findByitineraryNo_noticeShipType_makePayment(itineraryNo,
							noticeShipType, makePayment);
			if (vmaScheduleAnchorages != null
					&& !vmaScheduleAnchorages.isEmpty()) {
				for (VmaScheduleAnchorage vmaScheduleAnchorage : vmaScheduleAnchorages) {
					duration += vmaScheduleAnchorage.getAnchoringDuration();
				}

				if (vmaItinerarySchedule != null) {
					duration += -vmaItinerarySchedule
							.getAnchorageFreeDuration();

					if (duration < 0) {
						duration = 0;
					}
				}
			}

		} else if (noticeShipType == 2) {
			List<VmaScheduleAnchorage> vmaScheduleAnchorages = VmaScheduleAnchorageLocalServiceUtil
					.findByitineraryNo_noticeShipType_makePayment(itineraryNo,
							noticeShipType, makePayment);
			if (vmaScheduleAnchorages != null
					&& !vmaScheduleAnchorages.isEmpty()) {
				for (VmaScheduleAnchorage vmaScheduleAnchorage : vmaScheduleAnchorages) {
					duration += vmaScheduleAnchorage.getAnchoringDuration();
				}
			}

		} else if (noticeShipType == 3) {
			duration = getArchorageDurationTime(itineraryNo, 1, makePayment)
					+ getArchorageDurationTime(itineraryNo, 2, makePayment);
		}

		return duration;

	}

	public static Object formatUnicode(Object object) {

		// Field[] fields = object.getClass().getDeclaredFields();
		Method[] methods = object.getClass().getMethods();
		/*
		 * Method[] methods2 = object.getClass().getMethods();
		 * System.out.println
		 * ("====================>>>>>>>>>>>>>>>>>>>>methods2 " +
		 * methods2.length); if (methods2 != null) { for(int i = 0; i <
		 * methods2.length; i++){ String methodName = methods2[i].getName();
		 * System.out.println(methodName); } }
		 * 
		 * Field[] fields = object.getClass().getFields();
		 * System.out.println("====================>>>>>>>>>>>>>>>>>>>>fields "
		 * + fields.length);
		 * 
		 * if (fields != null) { for(int i = 0; i < fields.length; i++){ String
		 * name = fields[i].getName(); System.out.println(name); } }
		 */
		if (methods != null) {
			for (int i = 0; i < methods.length; i++) {
				String methodName = methods[i].getName();
				String type = methods[i].getReturnType().getName();
				if (type.equals("java.lang.String")
						&& methodName.startsWith("get")
						&& !methodName.startsWith("getOriginal")
						&& !methodName.equals("getModelClassName")) {

					try {
						String value = StringPool.BLANK;
						Method getMethod = object.getClass().getMethod(
								methodName);
						if (getMethod != null) {
							value = (String) getMethod.invoke(object);
						}

						if (Validator.isNotNull(value)) {
							System.out.println("Value1 -> " + value);
							value = URLEncoder.encode(value, "ISO-8859-1");
							System.out.println("Value2 -> " + value);
							value = URLDecoder.decode(value, "UTF-8");
							System.out.println("Value3 -> " + value);
							String setMethodName = methodName.replace("get",
									"set");
							Method setMethod = object.getClass().getMethod(
									setMethodName, String.class);

							if (setMethod != null) {
								setMethod.invoke(object, value);
								System.out.println(">>> " + methodName + "|"
										+ setMethodName + "|" + value);
							}

						}

					} catch (Exception e) {
						log.warn(e.getMessage());
						continue;
					}
				}
			}
		}
		return object;
	}

	public static String getString(HttpServletRequest request, String key,
			String defaultValue) {
		String value = ParamUtil.getString(request, key, defaultValue);
		if (Validator.isNotNull(value)) {
			try {
				System.out.println("Value1 -> " + value);
				value = URLEncoder.encode(value, "ISO-8859-1");
				System.out.println("Value2 -> " + value);
				value = URLDecoder.decode(value, "UTF-8");
				System.out.println("Value3 -> " + value);
			} catch (Exception e) {
				// nothing to do
			}
		}
		return value;
	}

	public static String getString(ActionRequest actionRequest, String key,
			String defaultValue) {
		String value = ParamUtil.getString(actionRequest, key, defaultValue);
		if (Validator.isNotNull(value)) {
			try {
				System.out.println("Value1 -> " + value);
				value = URLEncoder.encode(value, "ISO-8859-1");
				System.out.println("Value2 -> " + value);
				value = URLDecoder.decode(value, "UTF-8");
				System.out.println("Value3 -> " + value);
			} catch (Exception e) {
				// nothing to do
			}
		}
		return value;
	}

	public static String getString(ResourceRequest resourceRequest, String key,
			String defaultValue) {
		String value = ParamUtil.getString(resourceRequest, key, defaultValue);
		if (Validator.isNotNull(value)) {
			try {
				System.out.println("Value1 -> " + value);
				value = URLEncoder.encode(value, "ISO-8859-1");
				System.out.println("Value2 -> " + value);
				value = URLDecoder.decode(value, "UTF-8");
				System.out.println("Value3 -> " + value);
			} catch (Exception e) {
				// nothing to do
			}
		}
		return value;
	}

	public static String getChanelName(String chanelCodeList) {
		String[] chanelCodes = StringUtil.split(chanelCodeList);
		List<String> chanelNames = new ArrayList<String>();
		if (chanelCodes != null && chanelCodes.length > 0) {
			for (int i = 0; i < chanelCodes.length; i++) {
				String chanelCode = chanelCodes[i];
				if (Validator.isNotNull(chanelCode)) {
					DmDataitem dmDataItem = DmDataItemLocalServiceUtil
							.findByDataGroupIdAndCode0(130, chanelCode);
					if (dmDataItem != null) {
						chanelNames.add(dmDataItem.getName());
					}
				}
			}
		}

		return StringUtil.merge(chanelNames, "\n");
	}

	public static String getFrom_To(String portHarborCode, String portWharfCode) {
		String cangBien = "";
		String portHarbourName = "";
		String portRegion = "";
		try {
			DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil
					.getByPortHarbourCode(portHarborCode);
			portHarbourName = dmPortHarbour.getPortHarbourNameVN();

			DmMaritime maritime = DmMaritimeLocalServiceUtil
					.getByMaritimeCode(dmPortHarbour.getPortRegionCode());

			if (maritime != null) {
				cangBien = maritime.getCityCode();
			}
			DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil
					.getByPortRegionCode(dmPortHarbour.getPortRegion());

			if (dmPortRegion != null) {
				portRegion = dmPortRegion.getPortRegionNameVN();
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		String portPortWarfName = "";

		try {
			DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
					.getByPortWharfCode(portWharfCode);
			portPortWarfName = dmPortWharf.getPortWharfNameVN();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		/*
		 * return cangBien + ", " + portRegion + ", " + portHarbourName + ", " +
		 * portPortWarfName;
		 */

		return portHarbourName + ", " + portPortWarfName;
	}

	public static double calculateAnchoringDuration(Date date1, Date date2) {
		try {

			
			long diff = date2.getTime() - date1.getTime();
			
			if (Validator.isNull(date1) || Validator.isNull(date2) ) {
				return 0;
			} else if (diff < 0 ) {
				return 0;
			}
			

			double diffHours = Double.valueOf(diff
					/ Double.valueOf(60 * 60 * 1000));
			/*double hoursRound = new BigDecimal(diffHours).setScale(0,
					RoundingMode.HALF_UP).doubleValue();
			if (diffHours == hoursRound) {
				System.out.println(1);
				return hoursRound;
			} else if (diffHours > hoursRound) {
				System.out.println(2);
				return Double.valueOf((int) diffHours + 0.5);
			} else {
				return hoursRound;
			}*/
			
			double hoursRound = diffHours - (int)diffHours;
			 
		    if ((hoursRound > 0) && (hoursRound < 0.5)) {
		    	return (int)diffHours + 0.5;
		    } else if ((hoursRound > 0.5) && (hoursRound < 1 )) {
		    	return (int)diffHours + 1;
		    } else if (hoursRound == 1 ) {
		    	return (int)diffHours;
		    } else if (hoursRound == 0.5 ) {
		    	return (int)diffHours + 0.5;
		    } else {
		    	return (int)diffHours;
		    }
		    
		    
		} catch (Exception e) {
			return 0;
		}
	}

	public static long countGetPaymentData(ThemeDisplay themeDisplay,
			ResourceRequest resourceRequest) {

		HttpServletRequest request = resourceRequest;
		HttpServletRequest requestOrg = request;

		String markasdeleted = ParamUtil.getString(requestOrg, "markasdeleted");
		String mariTimeCode = ParamUtil.getString(requestOrg, "mariTimeCode",
				StringPool.BLANK);
		int start = ParamUtil.getInteger(requestOrg, "start", 0);
		int end = ParamUtil.getInteger(requestOrg, "end", 15);
		String keywordsSearch = ParamUtil.getString(requestOrg,
				"keywordsSearch");
		UserPort portDefault = UserPortLocalServiceUtil
				.findByUserId(themeDisplay.getUserId());
		int maritimeCode = 0;

		if (Validator.isNotNull(portDefault) && Validator.isNull(mariTimeCode)) {
			maritimeCode = Integer.valueOf(portDefault.getPortCode());
		} else {
			maritimeCode = Integer.valueOf(mariTimeCode);
		}

		try {

			User user = themeDisplay.getUser();

			boolean flagDT = false;

			for (Role role : user.getRoles()) {

				if (role.getName().equals("DTND_ROLE")) {
					flagDT = true;
					break;
				}

			}

			String[] documentTypeCode = null;

			if (flagDT) {
				documentTypeCode = new String[] {
						ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6,
						ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7 };
			} else {
				// Edit by Dungnv
				documentTypeCode = new String[] { "NC", "XC", "QC", "4", "5",
						"8", "9", "10", "11", "12", "13", "---" };
			}

			String positionCode = ParamUtil.getString(requestOrg,
					"positionCode");
			String imo = ParamUtil.getString(requestOrg, "imo");
			String stateCode = ParamUtil.getString(requestOrg, "stateCode");
			String portRegionCode = ParamUtil.getString(requestOrg,
					"portRegionCode");
			String maritimeCodeNext = ParamUtil.getString(requestOrg,
					"maritimeCodeNext");
			String shipName = VMAUtils.getString(requestOrg, "shipName",
					StringPool.BLANK);
			String callSign = VMAUtils.getString(requestOrg, "callSign",
					StringPool.BLANK);

			int flag = ParamUtil.getInteger(request, "flag", -1);

			return TempDocumentLocalServiceUtil
					.countTempDebitNoteTauBien_PTTND(
							String.valueOf(maritimeCode), positionCode,
							portRegionCode, keywordsSearch, stateCode, imo,
							markasdeleted, maritimeCodeNext, shipName,
							callSign, flag);
		} catch (Exception e) {
			return 0;
		}
	}

	// Clone getPaymentData from KeToanAction
	public static JSONObject getPaymentData(ThemeDisplay themeDisplay,
			ResourceRequest resourceRequest) {

		HttpServletRequest request = resourceRequest;
		HttpServletRequest requestOrg = request;

		JSONObject result = JSONFactoryUtil.createJSONObject();
		String markasdeleted = ParamUtil.getString(requestOrg, "markasdeleted");
		String mariTimeCode = ParamUtil.getString(requestOrg, "mariTimeCode",
				StringPool.BLANK);
		int start = ParamUtil.getInteger(requestOrg, "start", 0);
		int end = ParamUtil.getInteger(requestOrg, "end", 15);
		String keywordsSearch = ParamUtil.getString(requestOrg,
				"keywordsSearch");

		UserPort portDefault = UserPortLocalServiceUtil
				.findByUserId(themeDisplay.getUserId());
		int maritimeCode = 0;

		if (Validator.isNotNull(portDefault) && Validator.isNull(mariTimeCode)) {
			maritimeCode = Integer.valueOf(portDefault.getPortCode());
		} else {
			maritimeCode = Integer.valueOf(mariTimeCode);
		}

		List<TempDebitnote> list = new ArrayList<TempDebitnote>();
		long total = 0;

		try {

			User user = themeDisplay.getUser();

			boolean flagDT = false;

			for (Role role : user.getRoles()) {

				if (role.getName().equals("DTND_ROLE")) {
					flagDT = true;
					break;
				}

			}

			String[] documentTypeCode = null;

			if (flagDT) {
				documentTypeCode = new String[] {
						ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6,
						ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7 };
			} else {
				// Edit by Dungnv
				documentTypeCode = new String[] { "NC", "XC", "QC", "4", "5",
						"8", "9", "10", "11", "12", "13", "---" };
			}

			long totalDatal = 0;

			JSONArray dataArrayDatal = JSONFactoryUtil.createJSONArray();

			String positionCode = ParamUtil.getString(requestOrg,
					"positionCode");
			String imo = ParamUtil.getString(requestOrg, "imo");
			String stateCode = ParamUtil.getString(requestOrg, "stateCode");
			String portRegionCode = ParamUtil.getString(requestOrg,
					"portRegionCode");
			String maritimeCodeNext = ParamUtil.getString(requestOrg,
					"maritimeCodeNext");
			String shipName = VMAUtils.getString(requestOrg, "shipName",
					StringPool.BLANK);
			String callSign = VMAUtils.getString(requestOrg, "callSign",
					StringPool.BLANK);

			list = TempDocumentLocalServiceUtil.findTempDebitNote(
					String.valueOf(maritimeCode), positionCode, portRegionCode,
					keywordsSearch, stateCode, imo, markasdeleted,
					maritimeCodeNext, start, end, shipName, callSign);
			total = TempDocumentLocalServiceUtil.countTempDebitNote(
					String.valueOf(maritimeCode), positionCode, portRegionCode,
					keywordsSearch, stateCode, imo, markasdeleted,
					maritimeCodeNext, start, end, shipName, callSign);

			JSONArray dataArray = JSONFactoryUtil
					.createJSONArray(JSONFactoryUtil.looseSerialize(list));

			for (int i = 0; i < dataArray.length(); i++) {
				JSONObject currentObject = dataArray.getJSONObject(i);
				String itineraryNo = currentObject.getString("itineraryNo");
				boolean isActive = VmaItineraryUtils
						.checkActiveVma(themeDisplay);
				VmaItinerary vmaItinerary = null;
				if (Validator.isNotNull(itineraryNo)
						&& !itineraryNo.equals("---") && isActive) {
					try {
						vmaItinerary = VmaItineraryLocalServiceUtil
								.fetchByitineraryNo(itineraryNo);
					} catch (Exception e) {
						log.error(e.getMessage());
					}
				}

				// Edit by Dungnv ( Dao nguoc if else )
				TempDocument tempDocument = TempDocumentLocalServiceUtil
						.findTemDocumentByDocumentNameDocumentYear(
								currentObject.getLong("documentName"),
								currentObject.getInt("documentYear"));
				long documentNameFromTempDocument = currentObject.getLong("documentName");
				int documentYearFromTempDocument = currentObject.getInt("documentYear");
				if (tempDocument != null) {
					if(vmaItinerary == null){
						currentObject.put("itineraryNo", itineraryNo);
						currentObject.put("tenTau", tempDocument.getShipName());
						currentObject.put("hoHieu", tempDocument.getCallSign());
						currentObject.put("imo", tempDocument.getImo());
						currentObject.put("quocTich", tempDocument.getStateCode());
						currentObject.put("daiLy", tempDocument.getNameOfShipownersAgents());
						// add by TrungNT
						currentObject.put("documentTypeCode", tempDocument.getDocumentTypeCode());
					}else{
						String imoNumber = tempDocument.getImo();
						String _callSign = tempDocument.getCallSign();
						String registryNumber = vmaItinerary.getRegistryNumber();
						String nameOfShip = tempDocument.getShipName();
						String flagStateOfShip = tempDocument.getStateCode();
						JSONObject jsonItinerary = VMAUtils.object2Json(
								vmaItinerary, VmaItinerary.class);
						//Xu ly neu luot vao, luot roi khac tau
						long documentNameFromTempVmaItinerary = vmaItinerary.getDocumentNameOUT();
						int documentYearFromTempVmaItinerary = vmaItinerary.getDocumentYearOUT();
						if(documentNameFromTempDocument == documentNameFromTempVmaItinerary && documentYearFromTempDocument == documentYearFromTempVmaItinerary){
							List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil.findByItineraryNo_NoticeShipType(itineraryNo, 2);
							VmaScheduleTransfer vmaScheduleTransfer = null;
							if(vmaScheduleTransfers != null && !vmaScheduleTransfers.isEmpty()){
								vmaScheduleTransfer = vmaScheduleTransfers.get(0);
							}
							if(vmaScheduleTransfer != null){
								imoNumber = vmaScheduleTransfer.getImoNumber();
								_callSign = vmaScheduleTransfer.getCallSign();
								registryNumber = vmaScheduleTransfer.getRegistryNumber();
								nameOfShip = vmaScheduleTransfer.getShipName();
								flagStateOfShip = vmaScheduleTransfer.getFlagStateOfShip();
								
								jsonItinerary.put("nameOfShip", nameOfShip);
								jsonItinerary.put("flagStateOfShip", flagStateOfShip);
								jsonItinerary.put("callSign", _callSign);
								jsonItinerary.put("imoNumber", imoNumber);
								jsonItinerary.put("registryNumber", registryNumber);
								jsonItinerary.put("shipOperatorCode", vmaScheduleTransfer.getShipOperatorCode());
								try {
									jsonItinerary.put("shipOperatorName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOperatorCode()).getCompanyName());
								} catch(Exception e){
								}
								jsonItinerary.put("shipOwnerCode", vmaScheduleTransfer.getShipOwnerCode());
								try {
									jsonItinerary.put("shipOwnerName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOwnerCode()).getCompanyName());
								} catch(Exception e){
								}
								jsonItinerary.put("shipCaptain", vmaScheduleTransfer.getNameOfMaster());
								jsonItinerary.put("vmaShipTypeCode", vmaScheduleTransfer.getShipTypeMT());
								jsonItinerary.put("shipTypeCode", vmaScheduleTransfer.getShipTypeCode());
							}
						}
						currentObject.put("itinerary", jsonItinerary);
						currentObject.put("itineraryNo", itineraryNo);
						currentObject.put("tenTau", nameOfShip);
						currentObject.put("hoHieu", _callSign);
						currentObject.put("imo", imoNumber);
						currentObject.put("quocTich", flagStateOfShip);
						currentObject.put("daiLy", tempDocument.getNameOfShipownersAgents());
						currentObject.put("documentTypeCode", tempDocument.getDocumentTypeCode());
					}

					dataArrayDatal.put(currentObject);
				}
				else if (vmaItinerary != null) {
					String imoNumber = vmaItinerary.getImoNumber();
					String _callSign = vmaItinerary.getCallSign();
					String registryNumber = vmaItinerary.getRegistryNumber();
					String nameOfShip = vmaItinerary.getNameOfShip();
					String flagStateOfShip = vmaItinerary.getFlagStateOfShip();

					JSONObject jsonItinerary = VMAUtils.object2Json(
							vmaItinerary, VmaItinerary.class);
					//Xu ly neu luot vao, luot roi khac tau
					long documentNameFromTempVmaItinerary = vmaItinerary.getDocumentNameOUT();
					int documentYearFromTempVmaItinerary = vmaItinerary.getDocumentYearOUT();
					if(documentNameFromTempDocument == documentNameFromTempVmaItinerary && documentYearFromTempDocument == documentYearFromTempVmaItinerary){
						List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil.findByItineraryNo_NoticeShipType(itineraryNo, 2);
						VmaScheduleTransfer vmaScheduleTransfer = null;
						if(vmaScheduleTransfers != null && !vmaScheduleTransfers.isEmpty()){
							vmaScheduleTransfer = vmaScheduleTransfers.get(0);
						}
						if(vmaScheduleTransfer != null){
							imoNumber = vmaScheduleTransfer.getImoNumber();
							_callSign = vmaScheduleTransfer.getCallSign();
							registryNumber = vmaScheduleTransfer.getRegistryNumber();
							nameOfShip = vmaScheduleTransfer.getShipName();
							flagStateOfShip = vmaScheduleTransfer.getFlagStateOfShip();
							
							jsonItinerary.put("nameOfShip", nameOfShip);
							jsonItinerary.put("flagStateOfShip", flagStateOfShip);
							jsonItinerary.put("callSign", _callSign);
							jsonItinerary.put("imoNumber", imoNumber);
							jsonItinerary.put("registryNumber", registryNumber);
							jsonItinerary.put("shipOperatorCode", vmaScheduleTransfer.getShipOperatorCode());
							try {
								jsonItinerary.put("shipOperatorName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOperatorCode()).getCompanyName());
							} catch(Exception e){
							}
							jsonItinerary.put("shipOwnerCode", vmaScheduleTransfer.getShipOwnerCode());
							try {
								jsonItinerary.put("shipOwnerName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOwnerCode()).getCompanyName());
							} catch(Exception e){
							}
							jsonItinerary.put("shipCaptain", vmaScheduleTransfer.getNameOfMaster());
							jsonItinerary.put("vmaShipTypeCode", vmaScheduleTransfer.getShipTypeMT());
							jsonItinerary.put("shipTypeCode", vmaScheduleTransfer.getShipTypeCode());
						}
					}
					currentObject.put("itinerary", jsonItinerary);
					currentObject.put("itineraryNo", itineraryNo);
					currentObject.put("tenTau", nameOfShip);
					currentObject.put("hoHieu", _callSign);
					currentObject.put("imo", imoNumber);
					currentObject.put("quocTich", flagStateOfShip);
					// Edit by Dungnv
					try {
						currentObject
								.put("daiLy",
										DmShipAgencyLocalServiceUtil
												.fetchByShipAgencyCode(
														vmaItinerary
																.getArrivalShipAgencyCode())
												.getShipAgencyNameVN());
					} catch (Exception e) {
						// nothing to do
					}
					// add by TrungNT
					currentObject.put("documentTypeCode", "---");

					dataArrayDatal.put(currentObject);
				}
			}

			result.put("data", dataArrayDatal);
			result.put("total", total);
		} catch (Exception e) {
			result.put("data", JSONFactoryUtil.createJSONArray());
			result.put("total", 0);
		}
		return result;
	}

	public static JSONObject getPaymentDataTauBien_PTTND(
			ThemeDisplay themeDisplay, ResourceRequest resourceRequest) {

		HttpServletRequest request = resourceRequest;
		HttpServletRequest requestOrg = request;

		JSONObject result = JSONFactoryUtil.createJSONObject();
		String markasdeleted = ParamUtil.getString(requestOrg, "markasdeleted");
		String mariTimeCode = ParamUtil.getString(requestOrg, "mariTimeCode",
				StringPool.BLANK);
		int start = ParamUtil.getInteger(requestOrg, "start", 0);
		int end = ParamUtil.getInteger(requestOrg, "end", 15);
		String keywordsSearch = VMAUtils.getString(requestOrg,
				"keywordsSearch", StringPool.BLANK);

		UserPort portDefault = UserPortLocalServiceUtil
				.findByUserId(themeDisplay.getUserId());
		int maritimeCode = 0;

		if (Validator.isNotNull(portDefault) && Validator.isNull(mariTimeCode)) {
			maritimeCode = Integer.valueOf(portDefault.getPortCode());
		} else {
			maritimeCode = Integer.valueOf(mariTimeCode);
		}

		List<TempDebitnote> list = new ArrayList<TempDebitnote>();
		long total = 0;

		try {

			User user = themeDisplay.getUser();

			boolean flagDT = false;

			for (Role role : user.getRoles()) {

				if (role.getName().equals("DTND_ROLE")) {
					flagDT = true;
					break;
				}

			}

			String[] documentTypeCode = null;

			if (flagDT) {
				documentTypeCode = new String[] {
						ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6,
						ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7 };
			} else {
				// Edit by Dungnv
				documentTypeCode = new String[] { "NC", "XC", "QC", "4", "5",
						"8", "9", "10", "11", "12", "13", "---" };
			}

			long totalDatal = 0;

			JSONArray dataArrayDatal = JSONFactoryUtil.createJSONArray();

			String positionCode = ParamUtil.getString(requestOrg,
					"positionCode");
			String imo = ParamUtil.getString(requestOrg, "imo");
			String stateCode = ParamUtil.getString(requestOrg, "stateCode");
			String portRegionCode = ParamUtil.getString(requestOrg,
					"portRegionCode");
			String maritimeCodeNext = ParamUtil.getString(requestOrg,
					"maritimeCodeNext");
			String shipName = VMAUtils.getString(requestOrg, "shipName",
					StringPool.BLANK);
			String callSign = VMAUtils.getString(requestOrg, "callSign",
					StringPool.BLANK);
			int flag = ParamUtil.getInteger(request, "flag", -1);

			if (flag >= 0) {
				list = TempDocumentLocalServiceUtil
						.findTempDebitNoteTauBien_PTTND(
								String.valueOf(maritimeCode), positionCode,
								portRegionCode, keywordsSearch, stateCode, imo,
								markasdeleted, maritimeCodeNext, start, end,
								shipName, callSign, flag);
				total = TempDocumentLocalServiceUtil
						.countTempDebitNoteTauBien_PTTND(
								String.valueOf(maritimeCode), positionCode,
								portRegionCode, keywordsSearch, stateCode, imo,
								markasdeleted, maritimeCodeNext, shipName,
								callSign, flag);
				
				JSONArray dataArray = JSONFactoryUtil
						.createJSONArray(JSONFactoryUtil.looseSerialize(list));
				
				for (int i = 0; i < dataArray.length(); i++) {
					JSONObject currentObject = dataArray.getJSONObject(i);
					String itineraryNo = currentObject.getString("itineraryNo");
					boolean isActive = VmaItineraryUtils
							.checkActiveVma(themeDisplay);
					VmaItinerary vmaItinerary = null;
					if (Validator.isNotNull(itineraryNo)
							&& !itineraryNo.equals("---") && isActive) {
						try {
							vmaItinerary = VmaItineraryLocalServiceUtil
									.fetchByitineraryNo(itineraryNo);
						} catch (Exception e) {
							log.error(e.getMessage());
						}
					}
					String reportBy = currentObject.getString("reportby");
					if (Validator.isNotNull(reportBy)) {
						if (reportBy.contains("kyso") || reportBy.contains("vanthu")) {
							reportBy = StringPool.BLANK;
						} else {
							reportBy = " ("+ currentObject.getString("reportby")	+ " xác nhận.)";						
						}
					} else {
						reportBy = StringPool.BLANK;
					}

					// Edit by Dungnv ( Dao nguoc if else )
					TempDocument tempDocument = TempDocumentLocalServiceUtil
							.findTemDocumentByDocumentNameDocumentYear(
									currentObject.getLong("documentName"),
									currentObject.getInt("documentYear"));
					long documentNameFromTempDocument = currentObject.getLong("documentName");
					int documentYearFromTempDocument = currentObject.getInt("documentYear");
					if (tempDocument != null
							&& currentObject.getLong("documentName") > 0) {
						if(vmaItinerary == null){
							currentObject.put("itineraryNo", itineraryNo);
							currentObject.put("tenTau", tempDocument.getShipName());
							currentObject.put("hoHieu", tempDocument.getCallSign());
							currentObject.put("imo", tempDocument.getImo());
							currentObject.put("quocTich", tempDocument.getStateCode());
							currentObject.put("daiLy", tempDocument.getNameOfShipownersAgents());
							// add by TrungNT
							currentObject.put("documentTypeCode", tempDocument.getDocumentTypeCode());
							currentObject.put("reportby", reportBy);
						}else{
							String imoNumber = tempDocument.getImo();
							String _callSign = tempDocument.getCallSign();
							String registryNumber = vmaItinerary.getRegistryNumber();
							String nameOfShip = tempDocument.getShipName();
							String flagStateOfShip = tempDocument.getStateCode();
							JSONObject jsonItinerary = VMAUtils.object2Json(
									vmaItinerary, VmaItinerary.class);
							//Xu ly neu luot vao, luot roi khac tau
							long documentNameFromTempVmaItinerary = vmaItinerary.getDocumentNameOUT();
							int documentYearFromTempVmaItinerary = vmaItinerary.getDocumentYearOUT();
							if(documentNameFromTempDocument == documentNameFromTempVmaItinerary && documentYearFromTempDocument == documentYearFromTempVmaItinerary){
								List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil.findByItineraryNo_NoticeShipType(itineraryNo, 2);
								VmaScheduleTransfer vmaScheduleTransfer = null;
								if(vmaScheduleTransfers != null && vmaScheduleTransfers.size()>0){
									vmaScheduleTransfer = vmaScheduleTransfers.get(0);
								}
								if(vmaScheduleTransfer != null){
									imoNumber = vmaScheduleTransfer.getImoNumber();
									_callSign = vmaScheduleTransfer.getCallSign();
									registryNumber = vmaScheduleTransfer.getRegistryNumber();
									nameOfShip = vmaScheduleTransfer.getShipName();
									flagStateOfShip = vmaScheduleTransfer.getFlagStateOfShip();
									
									jsonItinerary.put("nameOfShip", nameOfShip);
									jsonItinerary.put("flagStateOfShip", flagStateOfShip);
									jsonItinerary.put("callSign", _callSign);
									jsonItinerary.put("imoNumber", imoNumber);
									jsonItinerary.put("registryNumber", registryNumber);
									jsonItinerary.put("shipOperatorCode", vmaScheduleTransfer.getShipOperatorCode());
									try {
										jsonItinerary.put("shipOperatorName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOperatorCode()).getCompanyName());
									} catch(Exception e){
									}
									jsonItinerary.put("shipOwnerCode", vmaScheduleTransfer.getShipOwnerCode());
									try {
										jsonItinerary.put("shipOwnerName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOwnerCode()).getCompanyName());
									} catch(Exception e){
									}
									jsonItinerary.put("shipCaptain", vmaScheduleTransfer.getNameOfMaster());
									jsonItinerary.put("vmaShipTypeCode", vmaScheduleTransfer.getShipTypeMT());
									jsonItinerary.put("shipTypeCode", vmaScheduleTransfer.getShipTypeCode());
									jsonItinerary.put("vrCode", vmaScheduleTransfer.getVrCode());
									jsonItinerary.remove("payment2ArrivalStatus");
									jsonItinerary.put("payment2ArrivalStatus", 0); // Bo qua Trang thai Tinh phi luot vao khi Ban giao tau
									
								}
							}
							long documentNameIn = vmaItinerary.getDocumentNameIN();
							int documentYearIn = vmaItinerary.getDocumentYearIN();
							if(documentNameFromTempDocument == documentNameIn && documentYearFromTempDocument == documentYearIn){
								jsonItinerary.remove("payment2DepartureStatus");
								jsonItinerary.put("payment2DepartureStatus", 0); // Bo qua Trang thai Tinh phi luot roi								
							}
							
							int countMerging = VmaScheduleMergingLocalServiceUtil
									.countByItineraryNo(vmaItinerary.getItineraryNo());
							if (countMerging > 0) {
								jsonItinerary.put("payment2Merging", 1);
							} else {
								jsonItinerary.put("payment2Merging", 0);
							}
							
							int TINHPHI_TAUBIEN = 1;
							int TINHPHI_TAUBIEN_VAO = 0;
							int TINHPHI_TAUBIEN_ROI = 0;
							int TINHPHI_PTTND = 0;
							int TINHPHI_PTTND_VAO = 0;
							int TINHPHI_PTTND_ROI = 0;
							int TINHPHI_DOANLAI = 0;	
							
							if (countMerging > 0) {
								TINHPHI_TAUBIEN = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN",
										vmaItinerary, null );
								TINHPHI_TAUBIEN_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN_VAO",
										vmaItinerary, null );
								TINHPHI_TAUBIEN_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_TAUBIEN_ROI",
										vmaItinerary, null );
								TINHPHI_PTTND = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND",
										vmaItinerary, null );
								TINHPHI_PTTND_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND_VAO",
										vmaItinerary, null );
								TINHPHI_PTTND_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_PTTND_ROI",
										vmaItinerary, null );
								TINHPHI_DOANLAI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType(itineraryNo, "TINHPHI_DOANLAI",
										vmaItinerary, null );
								
							} else {
								
								TINHPHI_TAUBIEN = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN",
										vmaItinerary, null, null );
								TINHPHI_TAUBIEN_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN_VAO",
										vmaItinerary, null, null );
								TINHPHI_TAUBIEN_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_TAUBIEN_ROI",
										vmaItinerary, null, null );
								TINHPHI_PTTND = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND",
										vmaItinerary, null, null );
								TINHPHI_PTTND_VAO = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND_VAO",
										vmaItinerary, null, null );
								TINHPHI_PTTND_ROI = VMAUtils.requestTransactionByItineraryNo_NoticeShipType_NoMerging(itineraryNo, "TINHPHI_PTTND_ROI",
										vmaItinerary, null, null );
								
							}
							jsonItinerary.put("TINHPHI_PTTND", TINHPHI_PTTND);
							jsonItinerary.put("TINHPHI_PTTND_VAO", TINHPHI_PTTND_VAO);
							jsonItinerary.put("TINHPHI_PTTND_ROI", TINHPHI_PTTND_ROI);
							jsonItinerary.put("TINHPHI_TAUBIEN", TINHPHI_TAUBIEN);
							jsonItinerary.put("TINHPHI_TAUBIEN_VAO", TINHPHI_TAUBIEN_VAO);
							jsonItinerary.put("TINHPHI_TAUBIEN_ROI", TINHPHI_TAUBIEN_ROI);
							jsonItinerary.put("TINHPHI_DOANLAI", TINHPHI_DOANLAI);
							
							
							
							currentObject.put("itinerary", jsonItinerary);
							currentObject.put("itineraryNo", itineraryNo);
							currentObject.put("tenTau", nameOfShip);
							currentObject.put("hoHieu", _callSign);
							currentObject.put("imo", imoNumber);
							currentObject.put("quocTich", flagStateOfShip);
							currentObject.put("daiLy", tempDocument.getNameOfShipownersAgents());
							currentObject.put("documentTypeCode", tempDocument.getDocumentTypeCode());
							currentObject.put("reportby", reportBy);
						}

						dataArrayDatal.put(currentObject);
					}

					else if (vmaItinerary != null) {
						String imoNumber = vmaItinerary.getImoNumber();
						String _callSign = vmaItinerary.getCallSign();
						String registryNumber = vmaItinerary.getRegistryNumber();
						String nameOfShip = vmaItinerary.getNameOfShip();
						String flagStateOfShip = vmaItinerary.getFlagStateOfShip();

						JSONObject jsonItinerary = VMAUtils.object2Json(
								vmaItinerary, VmaItinerary.class);
						//Xu ly neu luot vao, luot roi khac tau
						long documentNameFromTempVmaItinerary = vmaItinerary.getDocumentNameOUT();
						int documentYearFromTempVmaItinerary = vmaItinerary.getDocumentYearOUT();
						if(documentNameFromTempDocument == documentNameFromTempVmaItinerary && documentYearFromTempDocument == documentYearFromTempVmaItinerary){
							List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil.findByItineraryNo_NoticeShipType(itineraryNo, 2);
							VmaScheduleTransfer vmaScheduleTransfer = null;
							if(vmaScheduleTransfers != null && vmaScheduleTransfers.size()>0){
								vmaScheduleTransfer = vmaScheduleTransfers.get(0);
							}
							if(vmaScheduleTransfer != null){
								imoNumber = vmaScheduleTransfer.getImoNumber();
								_callSign = vmaScheduleTransfer.getCallSign();
								registryNumber = vmaScheduleTransfer.getRegistryNumber();
								nameOfShip = vmaScheduleTransfer.getShipName();
								flagStateOfShip = vmaScheduleTransfer.getFlagStateOfShip();
								
								jsonItinerary.put("nameOfShip", nameOfShip);
								jsonItinerary.put("flagStateOfShip", flagStateOfShip);
								jsonItinerary.put("callSign", _callSign);
								jsonItinerary.put("imoNumber", imoNumber);
								jsonItinerary.put("registryNumber", registryNumber);
								jsonItinerary.put("shipOperatorCode", vmaScheduleTransfer.getShipOperatorCode());
								try {
									jsonItinerary.put("shipOperatorName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOperatorCode()).getCompanyName());
								} catch(Exception e){
								}
								jsonItinerary.put("shipOwnerCode", vmaScheduleTransfer.getShipOwnerCode());
								try {
									jsonItinerary.put("shipOwnerName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOwnerCode()).getCompanyName());
								} catch(Exception e){
								}
								jsonItinerary.put("shipCaptain", vmaScheduleTransfer.getNameOfMaster());
								jsonItinerary.put("vmaShipTypeCode", vmaScheduleTransfer.getShipTypeMT());
								jsonItinerary.put("shipTypeCode", vmaScheduleTransfer.getShipTypeCode());
								jsonItinerary.put("vrCode", vmaScheduleTransfer.getVrCode());
							}
						}
						currentObject.put("itinerary", jsonItinerary);
						currentObject.put("itineraryNo", itineraryNo);
						currentObject.put("tenTau", nameOfShip);
						currentObject.put("hoHieu", _callSign);
						currentObject.put("imo", imoNumber);
						currentObject.put("quocTich", flagStateOfShip);
						currentObject.put("soDangKy", registryNumber);
						
						if (currentObject.getLong("documentName") == 0) {
							currentObject.put("documentName", "-");
						}
						// Edit by Dungnv
						try {
							if (vmaItinerary
									.getArrivalShipAgencyCode()
									.equalsIgnoreCase(
											vmaItinerary
													.getDepartureShipAgencyCode())
									&& Validator.isNotNull(vmaItinerary
											.getArrivalShipAgencyCode())) {
								currentObject
										.put("daiLy",
												DmShipAgencyLocalServiceUtil
														.fetchByShipAgencyCode(
																vmaItinerary
																		.getArrivalShipAgencyCode())
														.getShipAgencyNameVN());
							} else if ((!vmaItinerary
									.getArrivalShipAgencyCode()
									.equalsIgnoreCase(
											vmaItinerary
													.getDepartureShipAgencyCode()))
									&& Validator.isNotNull(vmaItinerary
											.getArrivalShipAgencyCode())
									&& Validator.isNotNull(vmaItinerary
											.getDepartureShipAgencyCode())) {
								currentObject
										.put("daiLy",
												"1, "
														+ DmShipAgencyLocalServiceUtil
																.fetchByShipAgencyCode(
																		vmaItinerary
																				.getArrivalShipAgencyCode())
																.getShipAgencyNameVN()
														+ "/n 2, "
														+ DmShipAgencyLocalServiceUtil
																.fetchByShipAgencyCode(
																		vmaItinerary
																				.getDepartureShipAgencyCode())
																.getShipAgencyNameVN());
							} else if (Validator.isNotNull(vmaItinerary
									.getArrivalShipAgencyCode())
									&& Validator.isNull(vmaItinerary
											.getDepartureShipAgencyCode())) {
								currentObject
										.put("daiLy",
												DmShipAgencyLocalServiceUtil
														.fetchByShipAgencyCode(
																vmaItinerary
																		.getArrivalShipAgencyCode())
														.getShipAgencyNameVN());
							} else if (Validator.isNotNull(vmaItinerary
									.getDepartureShipAgencyCode())
									&& Validator.isNull(vmaItinerary
											.getArrivalShipAgencyCode())) {
								currentObject
										.put("daiLy",
												DmShipAgencyLocalServiceUtil
														.fetchByShipAgencyCode(
																vmaItinerary
																		.getDepartureShipAgencyCode())
														.getShipAgencyNameVN());
							} else if (Validator.isNull(vmaItinerary
									.getDepartureShipAgencyCode())
									&& Validator.isNull(vmaItinerary
											.getArrivalShipAgencyCode())) {
								if (Validator.isNotNull(vmaItinerary
										.getShipOwnerCode())
										&& Validator.isNotNull(vmaItinerary
												.getNewShipOwnerCode())) {
									currentObject
											.put("daiLy",
													"Chủ tàu: "
															+ "1, "
															+ DmVmaShipOwnerLocalServiceUtil
																	.fetchByShipOwnerCode(
																			vmaItinerary
																					.getShipOwnerCode())
																	.getCompanyName()
															+ "/n 2, "
															+ DmVmaShipOwnerLocalServiceUtil
																	.fetchByShipOwnerCode(
																			vmaItinerary
																					.getNewShipOwnerCode())
																	.getCompanyName());
								} else if (Validator.isNotNull(vmaItinerary
										.getShipOwnerCode())
										&& Validator.isNull(vmaItinerary
												.getNewShipOwnerCode())) {
									currentObject
											.put("daiLy",
													"Chủ tàu: "
															+ DmVmaShipOwnerLocalServiceUtil
																	.fetchByShipOwnerCode(
																			vmaItinerary
																					.getShipOwnerCode())
																	.getCompanyName());
								} else if (Validator.isNull(vmaItinerary
										.getShipOwnerCode())
										&& Validator.isNull(vmaItinerary
												.getNewShipOwnerCode())) {
									currentObject.put(
											"daiLy",
											"Thuyền trưởng: "
													+ vmaItinerary
															.getShipCaptain());
								}

							} else {
								currentObject.put("daiLy", "Thuyền trưởng: "
										+ vmaItinerary.getShipCaptain());
							}

						} catch (Exception e) {
							// nothing to do
						}
						// add by TrungNT
						currentObject.put("documentTypeCode", "---");
						currentObject.put("reportby", reportBy);
						currentObject.put("itinerary", VMAUtils.object2Json(
								vmaItinerary, VmaItinerary.class,
								new String[] { "payment2Merging" }));

						dataArrayDatal.put(currentObject);
					}
				}

				result.put("data", dataArrayDatal);
				result.put("total", total);
			}
		} catch (Exception e) {
			result.put("data", JSONFactoryUtil.createJSONArray());
			result.put("total", 0);
			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONObject checkHistoryTempDebitnote(
			ResourceRequest resourceRequest) {
		HttpServletRequest request = resourceRequest;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);
		long documentName = ParamUtil.getLong(request, "documentName", 0);
		int documentYear = ParamUtil.getInteger(request, "documentYear", 0);

		if (!itineraryNo.equals(StringPool.BLANK)) {
			VmaItinerary vmaItinerary = null;
			try {
				vmaItinerary = VmaItineraryLocalServiceUtil
						.fetchByitineraryNo(itineraryNo);
			} catch (SystemException e) {
				// nothing to do
			}
			if (vmaItinerary != null) {
				if (vmaItinerary.getPayment2ServiceStatus() == 1) {
					result.put("counter", 1);
					return result;
				}
			}
		}
		TempDebitnote tempDebitNote = null;
		if (!itineraryNo.equals(StringPool.BLANK) && documentName > 0
				&& documentYear > 0) {
			tempDebitNote = TempDebitNoteLocalServiceUtil
					.fetchByItineraryNo_DocumentName_DocumentYear(itineraryNo,
							documentName, documentYear);
		} else if (itineraryNo.equals(StringPool.BLANK) && documentName > 0
				&& documentYear > 0) {
			tempDebitNote = TempDebitNoteLocalServiceUtil
					.findByDocumentNameAnddocumentYear(documentName,
							documentYear);
		}

		if (tempDebitNote != null) {
			result.put("counter", 1);
			return result;
		} else {
			result.put("counter", 0);
			return result;
		}

	}

	public static JSONObject xacBaoTinhPhiTauBien_PTTND(
			ThemeDisplay themeDisplay, ResourceRequest resourceRequest)
			throws PortalException, SystemException {
		HttpServletRequest request = resourceRequest;
		User user = UserLocalServiceUtil.getUserById(themeDisplay.getUserId());

		JSONObject result = JSONFactoryUtil.createJSONObject();

		String itineraryNo = ParamUtil.getString(request, "itineraryNo",
				StringPool.BLANK);
		long documentName = ParamUtil.getLong(request, "documentName", 0);
		int documentYear = ParamUtil.getInteger(request, "documentYear", 0);

		TempDebitnote tempDebitNote = null;
		if ((!itineraryNo.equals(StringPool.BLANK)) && documentName > 0
				&& documentYear > 0) {
			tempDebitNote = TempDebitNoteLocalServiceUtil
					.fetchByItineraryNo_DocumentName_DocumentYear(itineraryNo,
							documentName, documentYear);
			
			if (Validator.isNull(tempDebitNote)) {
				tempDebitNote = TempDebitNoteLocalServiceUtil
						.findByDocumentNameAnddocumentYear(
								Long.valueOf(documentName),
								Integer.valueOf(documentYear));
				if (Validator.isNotNull(tempDebitNote)) {
					tempDebitNote.setItineraryNo(itineraryNo);
				}
			}
		} else if (itineraryNo.equals(StringPool.BLANK) && documentName > 0
				&& documentYear > 0) {
			tempDebitNote = TempDebitNoteLocalServiceUtil
					.findByDocumentNameAnddocumentYear(
							Long.valueOf(documentName),
							Integer.valueOf(documentYear));
		} else {
			tempDebitNote = TempDebitNoteLocalServiceUtil
					.fetchByItineraryNo_DocumentName_DocumentYear(itineraryNo,
							documentName, documentYear);
		}
		boolean isUpdateVmaItinerary = false;

		VmaItinerary vmaItinerary = VmaItineraryLocalServiceUtil
				.fetchByitineraryNo(itineraryNo);

		// Edit by Dungnv
		int noticeShipType = ParamUtil.getInteger(request, "noticeShipType");
		String remarks = DanhMucUtils.getString(request, "remarks");
		
		String imoNumber = vmaItinerary.getImoNumber();
		String callSign = vmaItinerary.getCallSign();
		String registryNumber = vmaItinerary.getRegistryNumber();
		String nameOfShip = vmaItinerary.getNameOfShip();
		String flagStateOfShip = vmaItinerary.getFlagStateOfShip();
		String vrCode = vmaItinerary.getVrCode();
		if(noticeShipType == 2){
			List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil.findByItineraryNo_NoticeShipType(itineraryNo, 2);
			VmaScheduleTransfer vmaScheduleTransfer = null;
			if(vmaScheduleTransfers != null && !vmaScheduleTransfers.isEmpty()){
				vmaScheduleTransfer = vmaScheduleTransfers.get(0);
			}
			if(vmaScheduleTransfer != null){
				imoNumber = vmaScheduleTransfer.getImoNumber();
				callSign = vmaScheduleTransfer.getCallSign();
				registryNumber = vmaScheduleTransfer.getRegistryNumber();
				nameOfShip = vmaScheduleTransfer.getShipName();
				flagStateOfShip = vmaScheduleTransfer.getFlagStateOfShip();
				vrCode = vmaScheduleTransfer.getVrCode();
			}
		}
		//================
		
		VmaItineraryRemark vmaItineraryRemarks = null;
		try {
			vmaItineraryRemarks = VmaItineraryRemarksLocalServiceUtil
					.findByItineraryNo_NoticeShipType(itineraryNo,
							noticeShipType).get(0);
		} catch (Exception e) {
		}

		if (vmaItineraryRemarks == null) {
			vmaItineraryRemarks = new VmaItineraryRemark();
			vmaItineraryRemarks.setRemarks(remarks);
			vmaItineraryRemarks.setNameOfShip(nameOfShip);
			vmaItineraryRemarks.setNoticeShipType(noticeShipType);
			vmaItineraryRemarks.setItineraryNo(itineraryNo);

			vmaItineraryRemarks = VmaItineraryRemarksLocalServiceUtil
					.addVmaItineraryRemarks(vmaItineraryRemarks);
		} else {
			vmaItineraryRemarks.setRemarks(remarks);

			vmaItineraryRemarks = VmaItineraryRemarksLocalServiceUtil
					.updateVmaItineraryRemarks(vmaItineraryRemarks);
		}

		if (vmaItinerary != null) {
			isUpdateVmaItinerary = true;
		}
		if (tempDebitNote == null) {
			Date todate = null;
			String transactionid = StringPool.BLANK;
			long totalpayment = 0;
			String corporationid = vmaItinerary.getMaritimeCode();
			String organization = DmMaritimeLocalServiceUtil.getByMaritimeCode(
					vmaItinerary.getMaritimeCode()).getMaritimeNameVN();
			Date fromdate = null;
			int paymenttype = 0;
			Date reportdate = new Date();
			String reportby = user.getEmailAddress();
			String financialaccountant = StringPool.BLANK;
			int markasdeleted = 0;
			String division = reportby;

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			String debitnotenumber = String.valueOf(timestamp.getTime());

			tempDebitNote = TempDebitNoteLocalServiceUtil.doTempDebitNote(
					documentName, documentYear, corporationid, debitnotenumber,
					division, financialaccountant, fromdate, markasdeleted,
					organization, paymenttype, reportby, reportdate, todate,
					totalpayment, transactionid,
					vmaItinerary.getMaritimeCode(), "VND",
					vmaItinerary.getItineraryNo(), remarks);

			result = VMAUtils.object2Json(tempDebitNote,
					TempDebitnote.class);

			if (isUpdateVmaItinerary) {
				try {
					VmaItinerary itinerary = VmaItineraryLocalServiceUtil
							.fetchByitineraryNo(itineraryNo);
					if (itinerary.getMarkedAsArrival() > 0
							&& itinerary.getMarkedAsDeparture() == 0) {
						if (itinerary.getPayment2ArrivalStatus() != 4) {
							itinerary.setPayment2ArrivalStatus(1);
						}
					} else if (itinerary.getMarkedAsArrival() == 0
							&& itinerary.getMarkedAsDeparture() > 0) {
						if (itinerary.getPayment2DepartureStatus() != 4) {
							itinerary.setPayment2DepartureStatus(1);
						}
					} else if (itinerary.getMarkedAsArrival() > 0
							&& itinerary.getMarkedAsDeparture() > 0) {
						if (itinerary.getArrivalShipAgencyCode().equals(
								itinerary.getDepartureShipAgencyCode())) {
							if (itinerary.getPayment2ArrivalStatus() == 4
									&& itinerary.getPayment2DepartureStatus() != 4) {
								itinerary.setPayment2DepartureStatus(1);
							} else if (itinerary.getPayment2ArrivalStatus() != 4
									&& itinerary.getPayment2DepartureStatus() != 4) {
								itinerary.setPayment2ArrivalStatus(1);
								itinerary.setPayment2DepartureStatus(1);
								itinerary.setPayment2ItineraryStatus(1);
							} else if (itinerary.getPayment2ArrivalStatus() != 4
									&& itinerary.getPayment2DepartureStatus() == 4) {
								itinerary.setPayment2ArrivalStatus(1);
							}
							
							if ((itinerary.getDocumentNameIN()>0) && (itinerary.getDocumentNameIN() == itinerary.getDocumentNameOUT()) && (itinerary.getDocumentYearIN() == itinerary.getDocumentYearOUT())) {
								itinerary.setPayment2ItineraryStatus(0); // Qua canh
								
								if ((itinerary.getPayment2DepartureStatus() == 1 || itinerary.getPayment2DepartureStatus() == 0 || itinerary
										.getPayment2DepartureStatus() == 6)
										&& itinerary.getMarkedAsDeparture() != 6
										&& itinerary.getMarkedAsDeparture() > 1) {
									itinerary.setPayment2DepartureStatus(1);
									if (itinerary.getPayment2ArrivalStatus() == 1 ) {
										itinerary.setPayment2ArrivalStatus(0);
									}
									
								}
							}
									
						} else {
							if (itinerary.getPayment2ArrivalStatus() != 4) {
								itinerary.setPayment2ArrivalStatus(1);
							}
							if (itinerary.getPayment2DepartureStatus() != 4) {
								itinerary.setPayment2DepartureStatus(1);
							}

							itinerary.setPayment2ItineraryStatus(0);
						}
					}

					VmaItineraryLocalServiceUtil.updateVmaItinerary(itinerary);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		} else if (Validator.isNotNull(tempDebitNote) && (tempDebitNote.getId() > 0) && (tempDebitNote.getMarkasdeleted() == 0)){
			try {
				String reportby = user.getEmailAddress();
				Date reportdate = new Date();
				String financialaccountant = StringPool.BLANK;
				int markasdeleted = 0;
				tempDebitNote.setReportby(reportby);
				tempDebitNote.setReportdate(reportdate);
				tempDebitNote.setFinancialaccountant(financialaccountant);
				tempDebitNote.setMarkasdeleted(markasdeleted);
				TempDebitNoteLocalServiceUtil.updateTempDebitNote(tempDebitNote);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

		return result;
	}

	private static final int XBTPTDV = 0;
	private static final int XBK = 1;
	private static final int XEM_BANG_KE_THU_TUC = 0;
	private static final int XEM_BANG_KE_KE_TOAN = 1;

	public static JSONObject xacBaoTinhPhiTauDichVu(ThemeDisplay themeDisplay,
			ResourceRequest resourceRequest) throws SystemException {
		HttpServletRequest request = resourceRequest;
		User user = null;
		try {
			user = UserLocalServiceUtil.getUserById(themeDisplay.getUserId());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		JSONObject result = JSONFactoryUtil.createJSONObject();

		String tugboatCode = ParamUtil.getString(request, "tugboatCode",
				StringPool.BLANK);
		String fromDate = ParamUtil.getString(request, "fromDate",
				StringPool.BLANK);
		String toDate = ParamUtil
				.getString(request, "toDate", StringPool.BLANK);
		String description = ParamUtil.getString(request, "description",
				StringPool.BLANK);
		int xemBK = ParamUtil.getInteger(request, "xemBK", 0);
		int xemBK_Thutuc = ParamUtil.getInteger(request, "xemBK_Thutuc", -1);

		if (!tugboatCode.equals(StringPool.BLANK)
				&& !fromDate.equals(StringPool.BLANK)
				&& !toDate.equals(StringPool.BLANK)) {

		String tugboatCompanyCode = StringPool.BLANK;
			if (Validator.isNotNull(tugboatCode)) {
				
				DmVmaTugboat dmVmaTugboat = DmVmaTugboatLocalServiceUtil
						.fetchByShipCode(tugboatCode);
				tugboatCompanyCode = Validator.isNotNull(dmVmaTugboat) ? dmVmaTugboat.getTugboatCompanyCode() : StringPool.BLANK;
			}
			JSONObject dataBeanJasper = JSONFactoryUtil
					.createJSONObject();
			dataBeanJasper = VmaItineraryScheduleLocalServiceUtil.findThongKeTauLaiDat(
					null, tugboatCompanyCode, tugboatCode,  user.getEmailAddress(),
					"", fromDate, toDate, xemBK, xemBK_Thutuc);

			if (xemBK == XBK) {
				BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
				boolean hasDataBoolean = false;
				try {
					hasDataBoolean = action.export2Report(dataBeanJasper,
							"TugBoatStatement.jrxml",
							"TugBoatStatement.pdf", 2);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				String UrlFile = request.getContextPath() + "/export/"
						+ "TugBoatStatement.pdf";
				String UrlFileDownLoad = UrlFile.replace(".pdfs", ".pdf");

				if (hasDataBoolean) {
					result.put("url", UrlFileDownLoad);
				} else {
					result.put("url", StringPool.BLANK);
				}
			}
		
		}

		return result;
	}

	private static final int HUY_YC_XBTP = 1;
	private static final int HUY_TINH_PHI_LAI = 0;

	// TODO: chua lam xong chuc nang thu 2
	public static JSONObject huyBaoTinhPhi(ThemeDisplay themeDisplay,
			ResourceRequest resourceRequest) throws SystemException {
		HttpServletRequest request = resourceRequest;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		int cancelMode = ParamUtil.getInteger(request, "cancelMode", -1);
		String tugboatCode = ParamUtil.getString(request, "tugboatCode",
				StringPool.BLANK);
		String debitnoteNumber = ParamUtil.getString(request,
				"debitnoteNumber", StringPool.BLANK);
		String fromDate = ParamUtil.getString(request, "fromDate",
				StringPool.BLANK);
		String toDate = ParamUtil
				.getString(request, "toDate", StringPool.BLANK);
		String description = ParamUtil.getString(request, "description",
				StringPool.BLANK);

		if (cancelMode == HUY_YC_XBTP) {
			List<VmaScheduleTugboatList> vmaScheduleTugboatLists = VmaScheduleTugboatListLocalServiceUtil
					.getByShipCode_MakePayment(tugboatCode, 1);

			JSONArray array = JSONFactoryUtil.createJSONArray();
			// Update makePayment and DocumentaryCode for VmaScheduleTugboatList
			for (VmaScheduleTugboatList vmaScheduleTugboatList : vmaScheduleTugboatLists) {
				vmaScheduleTugboatList.setMakePayment(0);
				vmaScheduleTugboatList.setDocumentaryCode(StringPool.BLANK);

				VmaScheduleTugboatList tugboatList = VmaScheduleTugboatListLocalServiceUtil
						.updateVmaScheduleTugboatList(vmaScheduleTugboatList);
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				try {
					obj = VMAUtils.object2Json(tugboatList,
							VmaScheduleTugboatList.class);
				} catch (Exception e) {
					// nothing to do
				}

				array.put(obj);
			}
			result.put("vmaScheduleTugboatList", array);

			TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil
					.getByNumberDebit(debitnoteNumber);

			// Delete VmaTransactionSlip
			VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
					.getByItineraryNo_Debitnoteid("KEYTAUDICHVU", Integer
							.valueOf(String.valueOf(tempDebitNote.getId())));
			if (vmaTransactionSlip != null) {
				VmaTransactionSlipLocalServiceUtil
						.deleteVmaTransactionSlip(vmaTransactionSlip);
				try {
					result.put("vmaTransactionSlip", VMAUtils.object2Json(
							vmaTransactionSlip,
							VmaTransactionSlip.class));

				} catch (JSONException e) {
					log.error(e.getMessage());
				}
			}

			// Delete TempDebitnote
			if (tempDebitNote != null) {
				TempDebitNoteLocalServiceUtil
						.deleteTempDebitNote(tempDebitNote);
				try {
					result.put("tempDebitNote", VMAUtils.object2Json(
							tempDebitNote, TempDebitnote.class));

				} catch (JSONException e) {
					log.error(e.getMessage());
				}
			}
		} else if (cancelMode == HUY_TINH_PHI_LAI) {
			User user = null;
			try {
				user = UserLocalServiceUtil.getUserById(themeDisplay.getUserId());
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			Date frmDate = FormatData.parseDateShort3StringToDate(fromDate);
			Date tDate = FormatData.parseDateShort3StringToDate(toDate);

			long timeStampFromDate = 0, timeStampToDate = 0;
			timeStampFromDate = frmDate.getTime();
			timeStampToDate = tDate.getTime();

			List<VmaScheduleTugboatList> vmaScheduleTugboatLists = VmaScheduleTugboatListLocalServiceUtil
					.getByShipCode_MakePayment(tugboatCode, 1);

			Map<Integer, Integer> arrayDay = new HashMap<Integer, Integer>();
			JSONArray array = JSONFactoryUtil.createJSONArray();
			if (vmaScheduleTugboatLists != null
					&& !vmaScheduleTugboatLists.isEmpty()) {

				// Count real day activity
				int count = 1;
				for (VmaScheduleTugboatList vmaScheduleTugboatList : vmaScheduleTugboatLists) {
					VmaScheduleTugboat vmaScheduleTugboat = VmaScheduleTugboatLocalServiceUtil
							.getByItineraryNo_SequenceNo(
									vmaScheduleTugboatList.getItineraryNo(),
									vmaScheduleTugboatList.getSequenceNo());
					if (vmaScheduleTugboat != null) {
						long tugDateFrom = 0;
						try {
							tugDateFrom = vmaScheduleTugboat.getTugDateFrom()
									.getTime();
						} catch (Exception e) {
							// nothing to do
						}
						if (tugDateFrom > 0) {
							if (tugDateFrom >= timeStampFromDate
									&& tugDateFrom <= timeStampToDate) {
								arrayDay.put(vmaScheduleTugboat
										.getTugDateFrom().getMonth(), count);
							}
						}
					}
					count++;
				}

				double totalPayment = 0;
				for (VmaScheduleTugboatList vmaScheduleTugboatList : vmaScheduleTugboatLists) {
					VmaScheduleTugboat vmaScheduleTugboat = VmaScheduleTugboatLocalServiceUtil
							.getByItineraryNo_SequenceNo(
									vmaScheduleTugboatList.getItineraryNo(),
									vmaScheduleTugboatList.getSequenceNo());
					if (vmaScheduleTugboat != null) {
						long tugDateFrom = 0;
						try {
							tugDateFrom = vmaScheduleTugboat.getTugDateFrom()
									.getTime();
						} catch (Exception e) {
							// nothing to do
						}
						if (tugDateFrom >= timeStampFromDate
								&& tugDateFrom <= timeStampToDate) {
							totalPayment += vmaScheduleTugboatList.getGt().doubleValue()
									* 100 * arrayDay.keySet().size();
						}
					}
				}

				Date date = new Date();
				Calendar cal = Calendar.getInstance(TimeZone.getDefault());
				cal.setTime(date);
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);

				// Update to TempDebitnote
				TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil
						.getByNumberDebit(debitnoteNumber);

				tempDebitNote.setDocumentYear(year);
				tempDebitNote.setFromdate(frmDate);
				tempDebitNote.setTodate(tDate);
				tempDebitNote.setReportby(user != null ? user.getEmailAddress()
						: StringPool.BLANK);
				tempDebitNote.setReportdate(new Date());
				tempDebitNote.setCorporationid(UserPortLocalServiceUtil
						.findByUserId(themeDisplay.getUserId()).getPortCode());
				tempDebitNote.setMariTimeCode(UserPortLocalServiceUtil.findByUserId(
								themeDisplay.getUserId()).getPortCode());
				tempDebitNote.setDescription(description);
				tempDebitNote
						.setOrganization(DmMaritimeLocalServiceUtil
								.getByMaritimeCode(
										UserPortLocalServiceUtil.findByUserId(
												themeDisplay.getUserId())
												.getPortCode())
								.getMaritimeName());
				tempDebitNote.setDocumentTypeCode("---");
				tempDebitNote.setCurrency("VND");
				tempDebitNote.setTotalpayment(BigDecimal.valueOf(totalPayment));

				tempDebitNote = TempDebitNoteLocalServiceUtil
						.updateTempDebitNote(tempDebitNote);
				try {
					result.put("tempDebitNote", VMAUtils.object2Json(
							tempDebitNote, TempDebitnote.class));

				} catch (Exception e) {

				}

				// Update to VmaTransactionSlip
				VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
						.getByItineraryNo_Debitnoteid("KEYTAUDICHVU", Integer
								.valueOf(String.valueOf(tempDebitNote.getId())));
				long sequenceNo = ReportsBusinessUtils
						.taoGiaTriThamSo("KEYTAUDICHVU");
				vmaTransactionSlip.setSequenceNo(Integer.valueOf(String
						.valueOf(sequenceNo)));
				vmaTransactionSlip.setFromdate(frmDate);
				vmaTransactionSlip.setTodate(tDate);
				vmaTransactionSlip
						.setGt(vmaScheduleTugboatLists.get(0).getGt());
				vmaTransactionSlip.setShipOwnerName(vmaScheduleTugboatLists
						.get(0).getTugboatCompanyName());
				vmaTransactionSlip.setShipOperationName(vmaScheduleTugboatLists
						.get(0).getTugboatCompanyName());
				vmaTransactionSlip.setPaymentAmount(totalPayment);
				vmaTransactionSlip.setVndTotalAmount(totalPayment);
				vmaTransactionSlip.setF1311Vnd(totalPayment);
				vmaTransactionSlip.setF1311Remarks(DanhMucUtils
						.encodeUTF8("100 * GT * Số ngày thực tế"));
				vmaTransactionSlip.setPaymentStatus(1);
				vmaTransactionSlip.setPortofAuthority(UserPortLocalServiceUtil
						.findByUserId(themeDisplay.getUserId()).getPortCode());
				vmaTransactionSlip.setAmountInWordsVnd(DanhMucUtils.convert(
						totalPayment, 1));

				vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
						.updateVmaTransactionSlip(vmaTransactionSlip);
				try {
					result.put("vmaTransactionSlip", VMAUtils.object2Json(
							vmaTransactionSlip,
							VmaTransactionSlip.class));

				} catch (Exception e) {

				}

				// Update to VmaScheduleTugboatList
				for (VmaScheduleTugboatList vmaScheduleTugboatList : vmaScheduleTugboatLists) {
					vmaScheduleTugboatList.setMakePayment(1);
					vmaScheduleTugboatList.setDocumentaryCode(debitnoteNumber);

					VmaScheduleTugboatList tugboatList = VmaScheduleTugboatListLocalServiceUtil
							.updateVmaScheduleTugboatList(vmaScheduleTugboatList);

					VmaScheduleTugboat vmaScheduleTugboat = VmaScheduleTugboatLocalServiceUtil
							.getByItineraryNo_SequenceNo(
									vmaScheduleTugboatList.getItineraryNo(),
									vmaScheduleTugboatList.getSequenceNo());

					JSONObject obj = JSONFactoryUtil.createJSONObject();
					try {
						obj = VMAUtils.object2Json(tugboatList,
								VmaScheduleTugboatList.class);
						try {
							if (vmaScheduleTugboat != null) {
								obj.put("tugDateFrom",
										FormatData
												.parseDateToTringType3(vmaScheduleTugboat
														.getTugDateFrom()));
								obj.put("tugDateTo",
										FormatData
												.parseDateToTringType3(vmaScheduleTugboat
														.getTugDateTo()));
								obj.put("tugDate",
										FormatData
												.parseDateToTringDDMMYYY(vmaScheduleTugboat
														.getTugDateFrom()));
							}
						} catch (Exception e) {

						}
						obj.put("documentaryCode",
								tugboatList.getDocumentaryCode());
						obj.put("makePayment", tugboatList.getMakePayment());
					} catch (Exception e) {

					}

					array.put(obj);
				}
				result.put("vmaScheduleTugboatList", array);
			}
		}

		return result;
	}

	public static JSONObject huyBaoTinhPhi_ChuyenTuyen(
			ThemeDisplay themeDisplay, ResourceRequest resourceRequest)
			throws SystemException, JSONException {
		HttpServletRequest request = resourceRequest;
		JSONObject result = JSONFactoryUtil.createJSONObject();

		int cancelMode = ParamUtil.getInteger(request, "cancelMode", -1);
		String documentaryCode = ParamUtil.getString(request,
				"documentaryCode", StringPool.BLANK); /*
													 * documentaryCode tu
													 * VmaTransactionSlip tuong
													 * duong debitnoteNumber
													 * trong Temp_debitnote
													 */
		String imoNumber = ParamUtil.getString(request, "imoNumber");
		String callSign = VMAUtils.getString(request, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(request, "registryNumber",
				StringPool.BLANK);
		String fromDate = ParamUtil.getString(request, "fromDate");
		String toDate = ParamUtil.getString(request, "toDate");
		String description = ParamUtil.getString(request, "description");

		String searchQuery = StringPool.BLANK;
		List<VmaItinerary> vmaItineraries = null;
		if (Validator.isNotNull(imoNumber) && Validator.isNotNull(callSign)
				&& Validator.isNotNull(registryNumber)) {
			searchQuery = generateQuery(imoNumber, callSign, fromDate,
					registryNumber, toDate, "KEYTAUKHACH");
		} else if (Validator.isNotNull(imoNumber)
				&& Validator.isNotNull(callSign)
				&& Validator.isNull(registryNumber)) {
			searchQuery = generateQuery(imoNumber, callSign, fromDate, null,
					toDate, "KEYTAUKHACH");
		} else if (Validator.isNull(imoNumber) && Validator.isNull(callSign)
				&& Validator.isNotNull(registryNumber)) {
			searchQuery = generateQuery(null, null, fromDate, registryNumber,
					toDate, "KEYTAUKHACH");
		}
		try {
			vmaItineraries = VmaItineraryLocalServiceUtil
					.findVmaItinerary_ChuyenTuyen(searchQuery,
							QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (Exception e) {

		}
		if (cancelMode == HUY_YC_XBTP) {
			if (vmaItineraries != null && !vmaItineraries.isEmpty()) {
				JSONArray array = JSONFactoryUtil.createJSONArray();
				// Update to VmaItinerary
				for (VmaItinerary vmaItinerary : vmaItineraries) {
					vmaItinerary.setPayment2ServiceStatus(0);
					vmaItinerary.setPayment2ItineraryStatus(0);

					vmaItinerary = VmaItineraryLocalServiceUtil
							.updateVmaItinerary(vmaItinerary);
					JSONObject obj = JSONFactoryUtil.createJSONObject();
					try {
						obj = object2Json(vmaItinerary,
								VmaItinerary.class);
					} catch (JSONException e) {
						// nothing to do
					}
					array.put(obj);
				}
				result.put("vmaItineraries", array);
			}

			TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil
					.getByNumberDebit(documentaryCode);

			// Delete VmaTransactionSlip
			VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
					.getByItineraryNo_Debitnoteid("KEYTAUKHACH", Integer
							.valueOf(String.valueOf(tempDebitNote.getId())));
			if (vmaTransactionSlip != null && tempDebitNote != null) {
				VmaTransactionSlipLocalServiceUtil
						.deleteVmaTransactionSlip(vmaTransactionSlip);
				try {
					result.put("vmaTransactionSlip", VMAUtils.object2Json(
							vmaTransactionSlip,
							VmaTransactionSlip.class));

				} catch (JSONException e) {
					log.error(e.getMessage());
				}
			}

			// Delete Temp_Debitnote
			if (tempDebitNote != null) {
				TempDebitNoteLocalServiceUtil
						.deleteTempDebitNote(tempDebitNote);
				try {
					result.put("tempDebitNote", VMAUtils.object2Json(
							tempDebitNote, TempDebitnote.class));

				} catch (JSONException e) {
					log.error(e.getMessage());
				}
			}
			result.put("message", "Successfully");
		} else if (cancelMode == HUY_TINH_PHI_LAI) {
			User user = null;
			try {
				user = UserLocalServiceUtil.getUserById(themeDisplay.getUserId());
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			int fromMonth = 0, fromYear = 0;
			int toMonth = 0, toYear = 0;
			Calendar c = Calendar.getInstance();
			c.setTime(FormatData.parseDateShort3StringToDate(fromDate));
			fromMonth = c.get(Calendar.MONTH) + 1;
			fromYear = c.get(Calendar.YEAR);

			c.setTime(FormatData.parseDateShort3StringToDate(toDate));
			toMonth = c.get(Calendar.MONTH) + 1;
			toYear = c.get(Calendar.YEAR);
			if ((fromMonth != 0 && fromYear != 0 && toMonth != 0 && toYear != 0)
					&& (fromMonth == toMonth && fromYear == toYear)) {
				double f1311Vnd = 0, f1351Vnd = 0, f1361Vnd = 0, gtConversion = 1;
				if (vmaItineraries != null && !vmaItineraries.isEmpty()) {
					for (VmaItinerary vmaItinerary : vmaItineraries) {
						VmaShip vmaShip = new VmaShip();
						try {
							if (imoNumber.trim().length() >= 7) {

								vmaShip = VmaShipLocalServiceUtil
										.fetchByIMONumber_CallSign(imoNumber,
												callSign);
							} else {

								vmaShip = VmaShipLocalServiceUtil
										.fetchByCallSign(callSign);
								// Tim lai theo registryNumber
								if (!vmaShip.getShipName().contains(
										vmaItinerary.getNameOfShip())) {
									vmaShip = VmaShipLocalServiceUtil
											.fetchByRegistryNumber(vmaItinerary
													.getRegistryNumber());
								}
							}
						} catch (Exception e) {
						}

						if (vmaShip != null) {
							if (!vmaItinerary.getShipTypeCode().equals("T69")) {
								gtConversion = vmaShip.getGt().doubleValue() / 2;
								f1311Vnd += gtConversion * 500 * 2;
								f1351Vnd += gtConversion * 550 * 2;
								f1361Vnd += lePhiVaoRoi(vmaShip.getGt().doubleValue());
							} else if (vmaItinerary.getShipTypeCode().equals(
									"T69")) {
								long thuTuSoChuyen = 0;
								
								//VmaItinerarySchedule vmaItineraryScheduleArrival = null;
								VmaItinerarySchedule vmaItineraryScheduleDeparture = null;
								String portRegionCode = StringPool.BLANK;
								try {
									
									/*vmaItineraryScheduleArrival = VmaItineraryScheduleLocalServiceUtil
											.findByItineraryNo_NoticeShipType(
													vmaItinerary.getItineraryNo(), 1);*/
									vmaItineraryScheduleDeparture = VmaItineraryScheduleLocalServiceUtil
											.findByItineraryNo_NoticeShipType(
													vmaItinerary.getItineraryNo(), 2);
									if (vmaItineraryScheduleDeparture != null) {
										portRegionCode = vmaItineraryScheduleDeparture.getPortRegionCode();
									}
								} catch (Exception e) {
								}
								
								thuTuSoChuyen = VMAUtils.thuTuSoChuyen(portRegionCode, vmaShip.getImoNumber(),
										vmaShip.getCallSign(),
										vmaShip.getRegistryNumber(), FormatData
												.parseDateToTringType3(vmaItinerary
														.getTimeOfDeparture()), toMonth, toYear);
								
								if (vmaShip.getGt().doubleValue() < 500
										&& thuTuSoChuyen >= 5L) {
									f1311Vnd += (gtConversion * 500 * 2 * 7) / 10;
									f1351Vnd += (gtConversion * 550 * 2 * 7) / 10;
									f1361Vnd += (lePhiVaoRoi(vmaShip.getGt().doubleValue()) * 7) / 10;
								} else if (vmaShip.getGt().doubleValue() >= 500
										&& vmaShip.getGt().doubleValue() < 15000
										&& thuTuSoChuyen >= 4L) {
									f1311Vnd += (gtConversion * 500 * 2 * 6) / 10;
									f1351Vnd += (gtConversion * 550 * 2 * 6) / 10;
									f1361Vnd += (lePhiVaoRoi(vmaShip.getGt().doubleValue()) * 6) / 10;
								} else if (vmaShip.getGt().doubleValue() >= 15000
										&& thuTuSoChuyen >= 3L) {
									f1311Vnd += (gtConversion * 500 * 2 * 5) / 10;
									f1351Vnd += (gtConversion * 550 * 2 * 5) / 10;
									f1361Vnd += (lePhiVaoRoi(vmaShip.getGt().doubleValue()) * 5) / 10;
								}
							}
						}
					}

					Date date = new Date();
					Calendar cal = Calendar.getInstance(TimeZone.getDefault());
					cal.setTime(date);
					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH);

					// Update to DebiteNote
					TempDebitnote tempDebitNote = TempDebitNoteLocalServiceUtil
							.getByNumberDebit(documentaryCode);

					tempDebitNote.setDocumentYear(year);
					tempDebitNote.setFromdate(FormatData
							.parseDateShort3StringToDate(fromDate));
					tempDebitNote.setTodate(FormatData
							.parseDateShort3StringToDate(toDate));
					tempDebitNote.setReportby(user != null ? user
							.getEmailAddress() : StringPool.BLANK);
					tempDebitNote.setReportdate(new Date());
					tempDebitNote.setCorporationid(UserPortLocalServiceUtil
							.findByUserId(themeDisplay.getUserId())
							.getPortCode());
					tempDebitNote.setMariTimeCode(UserPortLocalServiceUtil.findByUserId(
									themeDisplay.getUserId()).getPortCode());
					tempDebitNote.setDescription(description);
					tempDebitNote.setDebitnotenumber(documentaryCode);
					tempDebitNote.setOrganization(DmMaritimeLocalServiceUtil
							.getByMaritimeCode(
									UserPortLocalServiceUtil.findByUserId(
											themeDisplay.getUserId())
											.getPortCode()).getMaritimeName());
					tempDebitNote.setDocumentTypeCode("---");
					tempDebitNote.setCurrency("VND");
					tempDebitNote.setTotalpayment(BigDecimal.valueOf(f1311Vnd + f1351Vnd + f1361Vnd));

					tempDebitNote = TempDebitNoteLocalServiceUtil
							.updateTempDebitNote(tempDebitNote);

					if (tempDebitNote != null) {
						result.put("tempDebitNote", VMAUtils.object2Json(
								tempDebitNote, TempDebitnote.class));

					}

					int debitnoteId = Integer.parseInt(String.valueOf(tempDebitNote.getId()));

					// Update to VmaTransactionSlip
					VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
							.getByItineraryNo_Debitnoteid("KEYTAUDICHVU",
									Integer.valueOf(String
											.valueOf(tempDebitNote.getId())));
					long sequenceNo = ReportsBusinessUtils
							.taoGiaTriThamSo("KEYTAUKHACH");
					vmaTransactionSlip.setSequenceNo(Integer.valueOf(String
							.valueOf(sequenceNo)));
					vmaTransactionSlip.setFromdate(FormatData
							.parseDateShort3StringToDate(fromDate));
					vmaTransactionSlip.setTodate(FormatData
							.parseDateShort3StringToDate(toDate));
					vmaTransactionSlip.setDebitnoteid(debitnoteId);
					vmaTransactionSlip.setDocumentaryCode(documentaryCode);
					vmaTransactionSlip.setPaymentAmount(f1311Vnd + f1351Vnd
							+ f1361Vnd);
					vmaTransactionSlip.setVndTotalAmount(f1311Vnd + f1351Vnd
							+ f1361Vnd);
					vmaTransactionSlip.setF1311Vnd(f1311Vnd);
					vmaTransactionSlip.setPaymentStatus(1);
					vmaTransactionSlip
							.setPortofAuthority(UserPortLocalServiceUtil
									.findByUserId(themeDisplay.getUserId())
									.getPortCode());
					vmaTransactionSlip.setF1351Vnd(f1351Vnd);
					vmaTransactionSlip.setF1361Vnd(f1361Vnd);
					vmaTransactionSlip.setDocumentaryCode(documentaryCode);

					vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
							.updateVmaTransactionSlip(vmaTransactionSlip);
					try {
						result.put("vmaTransactionSlip", VMAUtils.object2Json(
								vmaTransactionSlip,
								VmaTransactionSlip.class));

					} catch (Exception e) {
					}

					JSONArray array = JSONFactoryUtil.createJSONArray();

					// Update to VmaItinerary
					for (VmaItinerary vmaItinerary : vmaItineraries) {
						vmaItinerary.setDocumentaryCode(documentaryCode);
						vmaItinerary.setPayment2ServiceStatus(1);
						vmaItinerary.setPayment2ItineraryStatus(1);
						
						if ((vmaItinerary.getDocumentNameIN()>0) && (vmaItinerary.getDocumentNameIN() == vmaItinerary.getDocumentNameOUT()) && (vmaItinerary.getDocumentYearIN() == vmaItinerary.getDocumentYearOUT())) {
							vmaItinerary.setPayment2ItineraryStatus(0); // Qua canh
							
						}

						vmaItinerary = VmaItineraryLocalServiceUtil
								.updateVmaItinerary(vmaItinerary);
						JSONObject obj = JSONFactoryUtil.createJSONObject();
						obj = object2Json(vmaItinerary,
								VmaItinerary.class);
						array.put(obj);
					}
					result.put("vmaItineraries", array);
				}
			}
		}

		return result;
	}

	public static JSONObject xemBangKeTauChuyenTuyen(ThemeDisplay themeDisplay,
			ResourceRequest resourceRequest) throws SystemException,
			JSONException, NoSuchVmaShipException {
		HttpServletRequest request = resourceRequest;
		User user = null;
		try {
			user = UserLocalServiceUtil.getUserById(themeDisplay.getUserId());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		JSONObject result = JSONFactoryUtil.createJSONObject();

		String imoNumber = ParamUtil.getString(request, "imoNumber");
		String callSign = VMAUtils.getString(request, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(request, "registryNumber",
				StringPool.BLANK);
		String fromDate = ParamUtil.getString(request, "fromDate");
		String toDate = ParamUtil.getString(request, "toDate");

		String searchQuery = StringPool.BLANK;
		List<VmaItinerary> vmaItineraries = null;
		if (Validator.isNotNull(imoNumber) && Validator.isNotNull(callSign)
				&& Validator.isNotNull(registryNumber)) {
			searchQuery = generateQuery(imoNumber, callSign, fromDate,
					registryNumber, toDate, null);
		} else if (Validator.isNotNull(imoNumber)
				&& Validator.isNotNull(callSign)
				&& Validator.isNull(registryNumber)) {
			searchQuery = generateQuery(imoNumber, callSign, fromDate, null,
					toDate, null);
		} else if (Validator.isNull(imoNumber) && Validator.isNull(callSign)
				&& Validator.isNotNull(registryNumber)) {
			searchQuery = generateQuery(null, null, fromDate, registryNumber,
					toDate, null);
		}
		try {
			vmaItineraries = VmaItineraryLocalServiceUtil
					.findVmaItinerary_ChuyenTuyen(searchQuery,
							QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (Exception e) {
		}

		int fromMonth = 0, fromYear = 0;
		int toMonth = 0, toYear = 0;

		Calendar c = Calendar.getInstance();
		c.setTime(FormatData.parseDateShort3StringToDate(fromDate));
		fromMonth = c.get(Calendar.MONTH) + 1;
		fromYear = c.get(Calendar.YEAR);

		c.setTime(FormatData.parseDateShort3StringToDate(toDate));
		toMonth = c.get(Calendar.MONTH) + 1;
		toYear = c.get(Calendar.YEAR);

		JSONObject re = JSONFactoryUtil.createJSONObject();

		if ((fromMonth != 0 && fromYear != 0 && toMonth != 0 && toYear != 0)
				&& (fromMonth == toMonth && fromYear == toYear)) {
			double f1311Vnd = 0, f1351Vnd = 0, f1361Vnd = 0, gtConversion = 1;
			JSONArray array = JSONFactoryUtil.createJSONArray();
			if (vmaItineraries != null && !vmaItineraries.isEmpty()) {
				for (VmaItinerary vmaItinerary : vmaItineraries) {
					JSONObject obj = JSONFactoryUtil.createJSONObject();

					long thuTuSoChuyen = 0;

					VmaShip vmaShip = new VmaShip();

					try {
						if (imoNumber.trim().length() >= 7) {

							vmaShip = VmaShipLocalServiceUtil
									.fetchByIMONumber_CallSign(imoNumber,
											callSign);
						} else {

							vmaShip = VmaShipLocalServiceUtil
									.fetchByCallSign(callSign);
							// Tim lai theo registryNumber
							if (!vmaShip.getShipName().contains(
									vmaItinerary.getNameOfShip())) {
								vmaShip = VmaShipLocalServiceUtil
										.fetchByRegistryNumber(vmaItinerary
												.getRegistryNumber());
							}
						}
					} catch (Exception e) {

					}

					if (vmaShip != null) {
						if (!vmaItinerary.getShipTypeCode().equals("T69")) {
							gtConversion = vmaShip.getGt().doubleValue() / 2;
							f1311Vnd = gtConversion * 500 * 2;
							f1351Vnd = gtConversion * 550 * 2;
							f1361Vnd = lePhiVaoRoi(vmaShip.getGt().doubleValue());
						} else if (vmaItinerary.getShipTypeCode().equals("T69")) {
							//VmaItinerarySchedule vmaItineraryScheduleArrival = null;
							VmaItinerarySchedule vmaItineraryScheduleDeparture = null;
							String portRegionCode = StringPool.BLANK;
							try {
								
								/*vmaItineraryScheduleArrival = VmaItineraryScheduleLocalServiceUtil
										.findByItineraryNo_NoticeShipType(
												vmaItinerary.getItineraryNo(), 1);*/
								vmaItineraryScheduleDeparture = VmaItineraryScheduleLocalServiceUtil
										.findByItineraryNo_NoticeShipType(
												vmaItinerary.getItineraryNo(), 2);
								if (vmaItineraryScheduleDeparture != null) {
									portRegionCode = vmaItineraryScheduleDeparture.getPortRegionCode();
								}
							} catch (Exception e) {
							}
							
							thuTuSoChuyen = VMAUtils.thuTuSoChuyen(portRegionCode, vmaShip.getImoNumber(),
									vmaShip.getCallSign(),
									vmaShip.getRegistryNumber(), FormatData
											.parseDateToTringType3(vmaItinerary
													.getTimeOfDeparture()), toMonth, toYear);
							
							if (vmaShip.getGt().doubleValue() < 500 && thuTuSoChuyen >= 5L) {
								f1311Vnd = (gtConversion * 500 * 2 * 7) / 10;
								f1351Vnd = (gtConversion * 550 * 2 * 7) / 10;
								f1361Vnd = (lePhiVaoRoi(vmaShip.getGt().doubleValue()) * 7) / 10;
							} else if (vmaShip.getGt().doubleValue() >= 500
									&& vmaShip.getGt().doubleValue() < 15000
									&& thuTuSoChuyen >= 4L) {
								f1311Vnd = (gtConversion * 500 * 2 * 6) / 10;
								f1351Vnd = (gtConversion * 550 * 2 * 6) / 10;
								f1361Vnd = (lePhiVaoRoi(vmaShip.getGt().doubleValue()) * 6) / 10;
							} else if (vmaShip.getGt().doubleValue() >= 15000
									&& thuTuSoChuyen >= 3L) {
								f1311Vnd = (gtConversion * 500 * 2 * 5) / 10;
								f1351Vnd = (gtConversion * 550 * 2 * 5) / 10;
								f1361Vnd = (lePhiVaoRoi(vmaShip.getGt().doubleValue()) * 5) / 10;
							}
						}
					}

					obj.put("transactionDate", FormatData
							.parseDateToTringType3(vmaItinerary
									.getTimeOfArrival()));
					obj.put("departureDateTo", FormatData
							.parseDateToTringType3(vmaItinerary
									.getTimeOfDeparture()));
					obj.put("F1361", f1361Vnd);
					obj.put("F1311", f1311Vnd);
					obj.put("voyageNumber", vmaItinerary.getVoyageNumber());

					array.put(obj);
				}
				result.put("vma_schedule_transaction_list", array);
				result.put(
						"signPlace",
						DmMaritimeLocalServiceUtil
								.getByMaritimeCode(
										UserPortLocalServiceUtil.findByUserId(
												themeDisplay.getUserId())
												.getPortCode()).getCityCode());
				result.put(
						"signDate",
						DmMaritimeLocalServiceUtil
								.getByMaritimeCode(
										UserPortLocalServiceUtil.findByUserId(
												themeDisplay.getUserId())
												.getPortCode()).getCityCode());
				result.put("stateCode", vmaItineraries.get(0)
						.getFlagStateOfShip());
				result.put("shipOwnerName", vmaItineraries.get(0)
						.getShipOwnerName());
				result.put("shipName", vmaItineraries.get(0).getNameOfShip());
				result.put("power", StringPool.BLANK); // khong biet lay o dau
				result.put("signName", StringPool.BLANK); // khong biet lay o
															// dau
				result.put(
						"maritimeNameVN",
						DmMaritimeLocalServiceUtil
								.getByMaritimeCode(
										UserPortLocalServiceUtil.findByUserId(
												themeDisplay.getUserId())
												.getPortCode())
								.getMaritimeNameVN());
				result.put("countVoyage", StringPool.BLANK); // khong biet lay o
																// dau
				result.put(
						"maritimeName",
						DmMaritimeLocalServiceUtil
								.getByMaritimeCode(
										UserPortLocalServiceUtil.findByUserId(
												themeDisplay.getUserId())
												.getPortCode())
								.getMaritimeName());
				result.put("callSign", vmaItineraries.get(0).getCallSign());
				result.put("dwt", StringPool.BLANK); // khong biet lay o dau
				result.put("stateName", vmaItineraries.get(0)
						.getFlagStateOfShip());
				result.put("gt", StringPool.BLANK); // khong biet lay o dau
				result.put("imo", vmaItineraries.get(0).getImoNumber());
				result.put("registryNumber", vmaItineraries.get(0)
						.getRegistryNumber());
				result.put("fromDate", fromDate);
				result.put("toDate", toDate);
				result.put("reportMonth", StringPool.BLANK); // khong biet lay o
																// dau
				result.put("F1311", StringPool.BLANK); // khong biet lay o dau
				result.put("unitPower", StringPool.BLANK); // khong biet lay o
															// dau

				log.info("====" + result);
				BaoCaoBussinessUtils action = new BaoCaoBussinessUtils();
				boolean hasDataBoolean = false;
				try {
					hasDataBoolean = action.export2Report(result,
							"PassengerStatement.jrxml",
							"PassengerStatement.pdf", 2);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				String UrlFile = request.getContextPath() + "/export/"
						+ "PassengerStatement.pdf";
				String UrlFileDownLoad = UrlFile.replace(".pdfs", ".pdf");

				if (hasDataBoolean) {
					re.put("url", UrlFileDownLoad);
				} else {
					re.put("url", StringPool.BLANK);
				}
			}
		}
		return re;
	}

	public static JSONObject xacBaoTinhPhiTauChuyenTuyen(
			ThemeDisplay themeDisplay, ResourceRequest resourceRequest)
			throws SystemException, JSONException, NoSuchVmaShipException {
		HttpServletRequest request = resourceRequest;
		User user = null;
		try {
			user = UserLocalServiceUtil.getUserById(themeDisplay.getUserId());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		JSONObject result = JSONFactoryUtil.createJSONObject();

		String imoNumber = ParamUtil.getString(request, "imoNumber");
		String callSign = VMAUtils.getString(request, "callSign",
				StringPool.BLANK);
		String registryNumber = VMAUtils.getString(request, "registryNumber",
				StringPool.BLANK);
		String fromDate = ParamUtil.getString(request, "fromDate");
		String toDate = ParamUtil.getString(request, "toDate");
		String description = ParamUtil.getString(request, "description");

		String searchQuery = StringPool.BLANK;
		List<VmaItinerary> vmaItineraries = null;
		if (Validator.isNotNull(imoNumber) && Validator.isNotNull(callSign)
				&& Validator.isNotNull(registryNumber)) {
			searchQuery = generateQuery(imoNumber, callSign, fromDate,
					registryNumber, toDate, null);
		} else if (Validator.isNotNull(imoNumber)
				&& Validator.isNotNull(callSign)
				&& Validator.isNull(registryNumber)) {
			searchQuery = generateQuery(imoNumber, callSign, fromDate, null,
					toDate, null);
		} else if (Validator.isNull(imoNumber) && Validator.isNull(callSign)
				&& Validator.isNotNull(registryNumber)) {
			searchQuery = generateQuery(null, null, fromDate, registryNumber,
					toDate, null);
		}
		try {
			vmaItineraries = VmaItineraryLocalServiceUtil
					.findVmaItinerary_ChuyenTuyen(searchQuery,
							QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (Exception e) {
		}
		int fromMonth = 0, fromYear = 0;
		int toMonth = 0, toYear = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(FormatData.parseDateShort3StringToDate(fromDate));
		fromMonth = c.get(Calendar.MONTH) + 1;
		fromYear = c.get(Calendar.YEAR);

		c.setTime(FormatData.parseDateShort3StringToDate(toDate));
		toMonth = c.get(Calendar.MONTH) + 1;
		toYear = c.get(Calendar.YEAR);
		if ((fromMonth != 0 && fromYear != 0 && toMonth != 0 && toYear != 0)
				&& (fromMonth == toMonth && fromYear == toYear)) {
			double f1311Vnd = 0, f1351Vnd = 0, f1361Vnd = 0, gtConversion = 1;
			if (vmaItineraries != null && !vmaItineraries.isEmpty()) {
				for (VmaItinerary vmaItinerary : vmaItineraries) {
					VmaShip vmaShip = new VmaShip();
					try {
						if (imoNumber.trim().length() >= 7) {

							vmaShip = VmaShipLocalServiceUtil
									.fetchByIMONumber_CallSign(imoNumber,
											callSign);
						} else {

							vmaShip = VmaShipLocalServiceUtil
									.fetchByCallSign(callSign);
							// Tim lai theo registryNumber
							if (!vmaShip.getShipName().contains(
									vmaItinerary.getNameOfShip())) {
								vmaShip = VmaShipLocalServiceUtil
										.fetchByRegistryNumber(vmaItinerary
												.getRegistryNumber());
							}
						}
					} catch (Exception e) {
					}
					if (vmaShip != null) {
						if (!vmaItinerary.getShipTypeCode().equals("T69")) {
							gtConversion = vmaShip.getGt().doubleValue() / 2;
							f1311Vnd += gtConversion * 500 * 2;
							f1351Vnd += gtConversion * 550 * 2;
							f1361Vnd += lePhiVaoRoi(vmaShip.getGt().doubleValue());
						} else if (vmaItinerary.getShipTypeCode().equals("T69")) {
							long thuTuSoChuyen = 0;
							
							//VmaItinerarySchedule vmaItineraryScheduleArrival = null;
							VmaItinerarySchedule vmaItineraryScheduleDeparture = null;
							String portRegionCode = StringPool.BLANK;
							try {
								
								/*vmaItineraryScheduleArrival = VmaItineraryScheduleLocalServiceUtil
										.findByItineraryNo_NoticeShipType(
												vmaItinerary.getItineraryNo(), 1);*/
								vmaItineraryScheduleDeparture = VmaItineraryScheduleLocalServiceUtil
										.findByItineraryNo_NoticeShipType(
												vmaItinerary.getItineraryNo(), 2);
								if (vmaItineraryScheduleDeparture != null) {
									portRegionCode = vmaItineraryScheduleDeparture.getPortRegionCode();
								}
							} catch (Exception e) {
							}
							
							thuTuSoChuyen = VMAUtils.thuTuSoChuyen(portRegionCode, vmaShip.getImoNumber(),
									vmaShip.getCallSign(),
									vmaShip.getRegistryNumber(), FormatData
											.parseDateToTringType3(vmaItinerary
													.getTimeOfDeparture()), toMonth, toYear);
													
							
							if (vmaShip.getGt().doubleValue() < 500 && thuTuSoChuyen >= 5L) {
								f1311Vnd += (gtConversion * 500 * 2 * 7) / 10;
								f1351Vnd += (gtConversion * 550 * 2 * 7) / 10;
								f1361Vnd += (lePhiVaoRoi(vmaShip.getGt().doubleValue()) * 7) / 10;
							} else if (vmaShip.getGt().doubleValue() >= 500
									&& vmaShip.getGt().doubleValue() < 15000
									&& thuTuSoChuyen >= 4L) {
								f1311Vnd += (gtConversion * 500 * 2 * 6) / 10;
								f1351Vnd += (gtConversion * 550 * 2 * 6) / 10;
								f1361Vnd += (lePhiVaoRoi(vmaShip.getGt().doubleValue()) * 6) / 10;
							} else if (vmaShip.getGt().doubleValue() >= 15000
									&& thuTuSoChuyen >= 3L) {
								f1311Vnd += (gtConversion * 500 * 2 * 5) / 10;
								f1351Vnd += (gtConversion * 550 * 2 * 5) / 10;
								f1361Vnd += (lePhiVaoRoi(vmaShip.getGt().doubleValue()) * 5) / 10;
							}
						}
					}
				}

				Date date = new Date();
				Calendar cal = Calendar.getInstance(TimeZone.getDefault());
				cal.setTime(date);
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);

				// Add to DebiteNote
				TempDebitnote tempDebitNote = new TempDebitnote();

				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				String debitnotenumber = String.valueOf(timestamp.getTime());

				tempDebitNote.setItineraryNo("KEYTAUKHACH");
				tempDebitNote.setDocumentName(0L);
				tempDebitNote.setDocumentYear(year);
				tempDebitNote.setFromdate(FormatData
						.parseDateShort3StringToDate(fromDate));
				tempDebitNote.setTodate(FormatData
						.parseDateShort3StringToDate(toDate));
				String reportby = (user != null) ? user
						.getEmailAddress() : StringPool.BLANK;
				String division = reportby; 
				tempDebitNote.setDivision(division);
				tempDebitNote.setReportby(reportby);
				tempDebitNote.setReportdate(new Date());
				tempDebitNote.setCorporationid(UserPortLocalServiceUtil
						.findByUserId(themeDisplay.getUserId()).getPortCode());
				tempDebitNote.setMariTimeCode(UserPortLocalServiceUtil.findByUserId(
								themeDisplay.getUserId()).getPortCode());
				tempDebitNote.setDocumentTypeCode("---");
				tempDebitNote.setCurrency("VND");
				tempDebitNote.setDescription(description);
				tempDebitNote.setDebitnotenumber(debitnotenumber);
				tempDebitNote.setOrganization(DmMaritimeLocalServiceUtil
						.getByMaritimeCode(
								UserPortLocalServiceUtil.findByUserId(
										themeDisplay.getUserId())
										.getPortCode()).getMaritimeNameVN());
				tempDebitNote.setDocumentTypeCode("---");
				tempDebitNote.setCurrency("VND");
				tempDebitNote.setTotalpayment(BigDecimal.valueOf(f1311Vnd + f1351Vnd + f1361Vnd));

				tempDebitNote = TempDebitNoteLocalServiceUtil
						.addTempDebitNote(tempDebitNote);

				if (tempDebitNote != null) {
					result.put("tempDebitNote", VMAUtils.object2Json(
							tempDebitNote, TempDebitnote.class));

				}

				int debitnoteId = Integer.parseInt(String.valueOf(tempDebitNote.getId()));

				// Add to VmaTransactionSlip
				VmaTransactionSlip vmaTransactionSlip = new VmaTransactionSlip();
				vmaTransactionSlip.setItineraryNo("KEYTAUKHACH");
				long sequenceNo = ReportsBusinessUtils
						.taoGiaTriThamSo("KEYTAUKHACH");
				vmaTransactionSlip.setSequenceNo(Integer.valueOf(String
						.valueOf(sequenceNo)));
				vmaTransactionSlip.setFromdate(FormatData
						.parseDateShort3StringToDate(fromDate));
				vmaTransactionSlip.setTodate(FormatData
						.parseDateShort3StringToDate(toDate));
				vmaTransactionSlip.setDebitnoteid(debitnoteId);
				vmaTransactionSlip.setDocumentaryCode(debitnotenumber);
				vmaTransactionSlip.setNameOfShip(vmaItineraries.get(0)
						.getNameOfShip());
				/*
				 * vmaTransactionSlip.setGt(vmaItineraries.get(0) .getGt());
				 */
				vmaTransactionSlip.setShipOwnerName(vmaItineraries.get(0)
						.getShipOwnerName());
				vmaTransactionSlip.setShipOperationName(vmaItineraries.get(0)
						.getShipOperatorName());
				vmaTransactionSlip.setPaymentAmount(f1311Vnd + f1351Vnd
						+ f1361Vnd);
				vmaTransactionSlip.setVndTotalAmount(f1311Vnd + f1351Vnd
						+ f1361Vnd);
				vmaTransactionSlip.setF1311Vnd(f1311Vnd);
				vmaTransactionSlip.setF1311Remarks(DanhMucUtils
						.encodeUTF8("500 * GT quy đổi * 2 * Phí miễn giảm"));
				vmaTransactionSlip.setPaymentStatus(1);
				vmaTransactionSlip.setPortofAuthority(UserPortLocalServiceUtil
						.findByUserId(themeDisplay.getUserId()).getPortCode());
				vmaTransactionSlip.setF1351Vnd(f1351Vnd);
				vmaTransactionSlip.setF1351Remarks(DanhMucUtils
						.encodeUTF8("550 * GT quy đổi * 2 * Phí miễn giảm"));
				vmaTransactionSlip.setF1361Vnd(f1361Vnd);
				vmaTransactionSlip.setF1361Remarks(DanhMucUtils.encodeUTF8("")); // Sau
																					// ghi
																					// vao
				vmaTransactionSlip.setDocumentaryCode(debitnotenumber);
				vmaTransactionSlip.setImoNumber(imoNumber);
				vmaTransactionSlip.setCallSign(callSign);
				vmaTransactionSlip.setRegistryNumber(registryNumber);

				vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
						.addVmaTransactionSlip(vmaTransactionSlip);
				try {
					result.put("vmaTransactionSlip", VMAUtils.object2Json(
							vmaTransactionSlip,
							VmaTransactionSlip.class));

				} catch (Exception e) {
				}

				JSONArray array = JSONFactoryUtil.createJSONArray();
				// Update to VmaItinerary
				for (VmaItinerary vmaItinerary : vmaItineraries) {
					vmaItinerary.setDocumentaryCode(debitnotenumber);
					vmaItinerary.setPayment2ServiceStatus(1);
					vmaItinerary.setPayment2ItineraryStatus(1);

					if ((vmaItinerary.getDocumentNameIN()>0) && (vmaItinerary.getDocumentNameIN() == vmaItinerary.getDocumentNameOUT()) && (vmaItinerary.getDocumentYearIN() == vmaItinerary.getDocumentYearOUT())) {
						vmaItinerary.setPayment2ItineraryStatus(0); // Qua canh
					}
					
					vmaItinerary = VmaItineraryLocalServiceUtil
							.updateVmaItinerary(vmaItinerary);
					JSONObject obj = JSONFactoryUtil.createJSONObject();
					obj = object2Json(vmaItinerary, VmaItinerary.class);
					array.put(obj);
				}
				result.put("vmaItineraries", array);
			}
		}
		return result;
	}

	public static long thuTuSoChuyen(String portRegionCode, String imoNumber, String callSign,
			String registryNumber, String toDate, int month, int year)
			throws JSONException, SystemException {
		return VmaItineraryLocalServiceUtil
				.countVmaItinerary(generateQuery_SoChuyen(portRegionCode, imoNumber, callSign,
						registryNumber, toDate, month, year));
	}

	private static String generateQuery_SoChuyen(String portRegionCode, String imoNumber,
			String callSign, String registryNumber, String toDate, int month,
			int year) {
		String query = "SELECT count(DISTINCT vma_itinerary.ID) as total from vma_itinerary ";
		query += " JOIN vma_itinerary_schedule ON vma_itinerary_schedule.ItineraryNo = vma_itinerary.ItineraryNo and vma_itinerary_schedule.NoticeShipType = 2 ";
		if (Validator.isNotNull(portRegionCode)) {
			query += " and vma_itinerary_schedule.PortRegionCode = '" + portRegionCode + "'";
		}
		
		query += " WHERE 1 = 1 ";
		if (Validator.isNotNull(imoNumber)) {
			query += " and vma_itinerary.ImoNumber = '" + imoNumber + "'";
		}
		if (Validator.isNotNull(callSign)) {
			query += " and vma_itinerary.CallSign = '" + callSign + "'";
		}
		if (Validator.isNotNull(registryNumber)) {
			query += " and vma_itinerary.RegistryNumber = '" + registryNumber + "'";
		}
		if (Validator.isNotNull(toDate)) {
			try {
				String strToDate = FormatData.parseDateToTring(FormatData
						.parseDateShort3StringToDate(toDate));
				query += " and vma_itinerary.timeOfDeparture BETWEEN '" + year + "-" + month
						+ "-1 00:00:00" + "' AND '" + strToDate + "'";
			} catch (Exception e) {
				log.error(e.getMessage());
				return StringPool.BLANK;
			}
		}

		if (Validator.isNotNull(month)) {
			query += " AND MONTH(vma_itinerary.timeOfArrival) = " + month;
		}

		if (Validator.isNotNull(year)) {
			query += " AND YEAR(vma_itinerary.timeOfArrival) = " + year;
		}

		if (Validator.isNotNull(month)) {
			query += " AND MONTH(vma_itinerary.timeOfDeparture) = " + month;
		}

		if (Validator.isNotNull(year)) {
			query += "  AND YEAR(vma_itinerary.timeOfDeparture) = " + year;
		}

		return query;
	}

	private static double lePhiVaoRoi(double gt) {
		if (gt < 200) {
			return 15000 * 2 * 2;
		} else if (gt >= 200 && gt < 1000) {
			return 25000 * 2 * 2;
		} else if (gt >= 1000 && gt < 5000) {
			return 50000 * 2 * 2;
		} else if (gt > 5000) {
			return 100000 * 2 * 2;
		}
		log.info("============== Loi le phi vao roi");
		return 0;
	}

	private static String generateQuery(String imoNumber, String callSign,
			String fromDate, String registryNumber, String toDate,
			String itineraryNo) {

		String sql = "SELECT a.* FROM vma_itinerary AS a";

		StringBuilder condition = new StringBuilder();

		condition.append(" WHERE 1 = 1 ");

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(VMAUtils.buildSQLCondition("ItineraryNo", "'%"
					+ itineraryNo + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(imoNumber)) {
			condition.append(VMAUtils.buildSQLCondition("IMONumber", "'%"
					+ imoNumber + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(callSign)) {
			condition.append(VMAUtils.buildSQLCondition("CallSign", "'%"
					+ callSign + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(registryNumber)) {
			condition.append(VMAUtils.buildSQLCondition("RegistryNumber", "'%"
					+ registryNumber + "%'", "AND", StringPool.LIKE,
					new String[] { "a" }));
		}

		if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)) {
			String date1 = null;
			String date2 = null;

			try {
				date1 = FormatData.parseDateToTring(FormatData.formatDateShort3
						.parse(fromDate));
				date2 = FormatData.parseDateToTring(FormatData.formatDateShort3
						.parse(toDate));
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date1 != null && date2 != null) {

				condition.append(VMAUtils.buildSQLCondition("TimeOfDeparture",
						"'" + date1 + "'" + " AND '" + date2 + "'", " AND ",
						StringPool.BETWEEN));

			}

			// Edit by Dungnv - MarkedAsArrival, markedAsDeparture ->
			// timeOfArrival,
			// timeOfDeparture
			condition
					.append(" ORDER BY a.MaritimeCode ASC, a.DocumentYearIN DESC, a.TimeOfArrival DESC, a.TimeOfDeparture DESC");
		}
		return sql + condition.toString();
	}

	public static String getTyGia(ResourceRequest resourceRequest)
			throws IOException {
		HttpServletRequest request = resourceRequest;

		String currencyCode = ParamUtil.getString(request, "currencyCode",
				StringPool.BLANK);
		if (currencyCode.equals(StringPool.BLANK)) {
			currencyCode = "USD";
		}

		String transfer = StringPool.BLANK;
		ProcessBuilder pb = new ProcessBuilder("curl", "--insecure",
				"https://portal.vietcombank.com.vn/Usercontrols/TVPortal.TyGia/pXML.aspx?b=68");
		Process p = pb.start();
		InputStream is = p.getInputStream();

		BufferedReader br = new BufferedReader(new InputStreamReader((is)));
		StringBuilder results = new StringBuilder();
		int i = 0;
		String output;
		while ((output = br.readLine()) != null) {
			if (i != 0) {
				results.append(output);
				results.append(System.getProperty("line.separator"));
			}
			i++;
		}

		// Use method to convert XML string content to XML Document object
		Document doc = convertStringToXMLDocument(results.toString());

		NodeList nodeList = doc.getElementsByTagName("Exrate");
		for (int j = 0; j < nodeList.getLength(); j++) {
			if (nodeList.item(j).getAttributes().getNamedItem("CurrencyCode")
					.getNodeValue().contentEquals(currencyCode)) {
				transfer = nodeList.item(j).getAttributes()
						.getNamedItem("Transfer").getNodeValue();
			}
		}
		return transfer.replace(",", StringPool.BLANK);
	}

	private static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(
					xmlString)));
			return doc;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public static JSONObject getDmGtReportCategories(
			ResourceRequest resourceRequest) {
		HttpServletRequest request = resourceRequest;
		int isDelete = ParamUtil.getInteger(request, "isDelete", -1);
		int start = ParamUtil.getInteger(request, "start", -1);
		int end = ParamUtil.getInteger(request, "end", -1);

		List<DmGtReportCategory> dmGtReportCategories = DmGtReportCategoryLocalServiceUtil
				.findByIsDelete(isDelete, start, end);
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		for (DmGtReportCategory dmGtReportCategory : dmGtReportCategories) {
			JSONObject object = JSONFactoryUtil.createJSONObject();
			object.put("dmGtReportCategoryId", dmGtReportCategory.getId());
			object.put("category", dmGtReportCategory.getCategory());
			object.put("categoryName", dmGtReportCategory.getCategoryName());
			object.put("categoryOrder", dmGtReportCategory.getCategoryOrder());
			object.put("markedAsDelete", dmGtReportCategory.getMarkedAsDelete());
			object.put("modifiedDate", dmGtReportCategory.getModifiedDate());
			object.put("syncVersion", dmGtReportCategory.getSyncVersion());
			array.put(object);
		}
		result.put("data", array);

		return result;
	}

	public static JSONObject transferVmaItinerarys(JSONObject vmaItinerary) {
		JSONArray data = JSONFactoryUtil.createJSONArray();
		JSONArray arrayVmaItinerary = vmaItinerary.getJSONArray("data");
		if (arrayVmaItinerary.length() > 0) {
			for (int i = 0; i < arrayVmaItinerary.length(); i++) {
				JSONObject obj = arrayVmaItinerary.getJSONObject(i);
				int noticeShipType = obj.has("NoticeShipType") ? obj.getInt("NoticeShipType") : obj.has("noticeShipType") ? obj.getInt("noticeShipType") : -1;
				if (noticeShipType == 2) {
					List<VmaScheduleTransfer> vmaScheduleTransfers = VmaScheduleTransferLocalServiceUtil
							.findByItineraryNo_NoticeShipType(
									obj.getString("itineraryNo"), 2);
					VmaScheduleTransfer vmaScheduleTransfer = null;
					if (vmaScheduleTransfers != null
							&& !vmaScheduleTransfers.isEmpty()) {
						vmaScheduleTransfer = vmaScheduleTransfers.get(0);
					}
					if(vmaScheduleTransfer != null){
						obj.put("nameOfShip", vmaScheduleTransfer.getShipName());
						obj.put("flagStateOfShip", vmaScheduleTransfer.getFlagStateOfShip());
						obj.put("callSign", vmaScheduleTransfer.getCallSign());
						obj.put("imoNumber", vmaScheduleTransfer.getImoNumber());
						obj.put("registryNumber", vmaScheduleTransfer.getRegistryNumber());
						obj.put("vrCode", vmaScheduleTransfer.getVrCode());
						obj.put("shipOperatorCode", vmaScheduleTransfer.getShipOperatorCode());
						try {
							obj.put("shipOperatorName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOperatorCode()).getCompanyName());
						} catch(Exception e){
						}
						obj.put("shipOwnerCode", vmaScheduleTransfer.getShipOwnerCode());
						try {
							obj.put("shipOwnerName", DmVmaShipOwnerLocalServiceUtil.fetchByShipOwnerCode(vmaScheduleTransfer.getShipOwnerCode()).getCompanyName());
						} catch(Exception e){
						}
						obj.put("shipCaptain", vmaScheduleTransfer.getNameOfMaster());
						obj.put("vmaShipTypeCode", vmaScheduleTransfer.getShipTypeMT());
						obj.put("shipTypeCode", vmaScheduleTransfer.getShipTypeCode());
					}
				}
				data.put(obj);
			}
			vmaItinerary.put("data", data);
		}
		return vmaItinerary;
	}

	public static JSONObject updateChuyenLanhDaoKySo(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		
		try {
			
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}
	
	public static JSONObject updateLanhDaoKySoHSM(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		
		try {
			
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}
	
	public static JSONObject updateLanhDaoTuChoiKySo(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		
		try {
			
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}
}
