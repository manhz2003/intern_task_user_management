/**
 * 
 */
package vn.gt.utils.validation.inland;

import java.util.Date;
import java.util.Enumeration;

import com.fds.nsw.liferay.core.ActionRequest;

import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import vn.gt.dao.common.service.LogMessageValidationLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmArrivalPurposeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmDocTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmGoodsLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmGoodsTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPackageLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPassportTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmRankRatingLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmSecurityLevelLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmUnitGeneralLocalServiceUtil;
import vn.gt.utils.ActionUtils;
import vn.gt.utils.FormatData;
import vn.gt.utils.config.ConfigurationManager;

import com.fds.nsw.kernel.exception.SystemException;


/**
 * @author win_64
 *
 */
import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.liferay.core.LanguageUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.UploadPortletRequest;
@Slf4j
public class ValidationUtils
 {
	
	//Chua co gia tri. Bat buoc phai nap
	private static String MALOI_01 = ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N001", "");
	
	//Du lieu thuoc dang danh muc 	
	private static String MALOI_02 = ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N002", "");
	
	//Du lieu thuoc dang so
	private static String MALOI_03 = ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N003", "");
	private static String MALOI_04 = ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N004", "");
	
	//Du lieu thuoc dang chu
	private static String MALOI_05 = ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N005", "");
	private static String MALOI_06 = ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N006", "");
	
	//Du lieu dang ngay thang
	private static String MALOI_07 = ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N007", "");
	
	//Du lieu dang email
	private static String MALOI_08 = ConfigurationManager.getStrProp("vn.gt.logMessageValidation.N008", "");
	
	
	public static int DM_UNIT_GENERAL = 1;//Don vi tinh
	public static int DM_SHIP_AGENCY = 2;
	public static int DM_PORT_REGION = 3;
	public static int DM_PORT_WHARF = 4;
	public static int DM_DOC_TYPE = 5;
	public static int DM_PASSPORT_TYPE = 6;
	public static int DM_SECURITY_LEVEL = 7;
	public static int DM_SHIP_TYPE = 8;
	public static int DM_PORT = 9;
	public static int DM_ARRIVAL_PURPOSE = 10;
	public static int DM_STATE = 11;//danh muc quoc gia
	public static int DM_GOODS_TYPE = 12;//danh muc loai hang hoa
	public static int DM_RANK_RATING = 13;
	public static int DM_GOODS = 14;//danh muc hang haa
	public static int DM_PACKAGE = 15;
	public static int DM_MARITIME = 16;//  cang? vu,
	public static int DM_POST_HARBOUR = 17;
	
	public static void logValidation(LogMessageValidation logMess, String id, int maLoi, String nameColumn,
			String idColumn) throws SystemException {
		
		switch (maLoi) {
			case 1:
				logMess.setTagProperty(nameColumn);
				logMess.setDataValidation(id + "1" + idColumn + ": " + MALOI_01);
				addLogMessageValidation(logMess);
				break;
			case 2:
				logMess.setTagProperty(nameColumn);
				logMess.setDataValidation(id + "2" + idColumn + ": " + MALOI_02);
				addLogMessageValidation(logMess);
				break;
			case 3:
				logMess.setTagProperty(nameColumn);
				logMess.setDataValidation(id + "3" + idColumn + ": " + MALOI_03);
				addLogMessageValidation(logMess);
				break;
			case 4:
				logMess.setTagProperty(nameColumn);
				logMess.setDataValidation(id + "4" + idColumn + ": " + MALOI_04);
				addLogMessageValidation(logMess);
				break;
			case 5:
				logMess.setTagProperty(nameColumn);
				logMess.setDataValidation(id + "5" + idColumn + ": " + MALOI_05);
				addLogMessageValidation(logMess);
				break;
			case 6:
				logMess.setTagProperty(nameColumn);
				logMess.setDataValidation(id + "6" + idColumn + ": " + MALOI_06);
				addLogMessageValidation(logMess);
				break;
			case 7:
				logMess.setTagProperty(nameColumn);
				logMess.setDataValidation(id + "7" + idColumn + ": " + MALOI_07);
				addLogMessageValidation(logMess);
				break;
			case 8:
				logMess.setTagProperty(nameColumn);
				logMess.setDataValidation(id + "8" + idColumn + ": " + MALOI_08);
				addLogMessageValidation(logMess);
				break;
			default:
				break;
		}
	}
	
	/**
	 * kiem tra dieu kien dung chung
	 * */
	
	public static boolean checkValidation(String validation, LogMessageValidation logMess, String id, String nameColumn,
			String idColumn, int[] maLois, int lengthData, boolean inputCheck) throws SystemException {
	
		boolean isBreak = false;
		boolean status = true;
		for (int maLoi : maLois) {
			switch (maLoi) {
				case 1:
					//Chua co gia tri. Bat buoc phai nap
					if (validation == null) {
						status = false;
						isBreak = true;
						logValidation(logMess, id, maLoi, nameColumn, idColumn);
					}
					break;
				case 2:
					//Du lieu thuoc dang danh muc:
					break;
				case 3:
					//Dinh dang so khong hop le
					break;
				case 4:
					//Gia tri so khong hop le (vuot qua gioi han min, max, overflow)
					break;
				case 5:
					//Gia tri vuot qua do dai cho phep
					if (!ActionUtils.checkLength(validation, lengthData)) {
						status = false;
						isBreak = true;
						logValidation(logMess, id, maLoi, nameColumn, idColumn);
					}
					break;
				case 6:
					//Gia tri co ky tu dac biet
					if (!ActionUtils.checkIfIsAValidSpecialCharacters(validation)) {
						status = false;
						isBreak = true;
						logValidation(logMess, id, maLoi, nameColumn, idColumn);
					}
					break;
				case 7:
					//Kiem tra ngay thang
					if (!FormatData.isThisDateValid(validation)) {
						status = false;
						isBreak = true;
						logValidation(logMess, id, maLoi, nameColumn, idColumn);
					}
					break;
				case 8:
					//Kiem tra dinh dang email
					if (!ActionUtils.checkIfIsAValidEmail(validation)) {
						status = false;
						isBreak = true;
						logValidation(logMess, id, maLoi, nameColumn, idColumn);
					}
					break;
				default:
					break;
			}
			if (isBreak == true) { break; }
		}
		return status;
	}
	
	/**
	 * DataType = 0 la so Interger,
	 * DataType = 1 la so Double
	 * 
	 * before la dinh so phan truoc dau phay ?
	 * after  la dinh so phan sau   dau phay ? 
	 * */
	public static boolean checkValidationNumber(String validation, LogMessageValidation logMess, String id,
			String nameColumn, String idColumn, int[] maLois, int dataType, int before, int after, boolean inputCheck)
			throws SystemException {
	
		boolean isBreak = false;
		boolean status = inputCheck;
		for (int maLoi : maLois) {
			switch (maLoi) {
				case 1:
					//Chua co gia tri. Bat buoc phai nap
					if (validation == null) {
						logValidation(logMess, id, maLoi, nameColumn, idColumn);
						isBreak = true;
						status = false;
					}
					break;
				case 3:
					//Dinh dang so khong hop le
					if (dataType == 0) {
						if (!ActionUtils.checkIfIsLong(validation)) {
							logValidation(logMess, id, maLoi, nameColumn, idColumn);
							isBreak = true;
							status = false;
						}
					} else if (dataType == 1) {
						if (!ActionUtils.checkIfIsDouble(validation)) {
							logValidation(logMess, id, maLoi, nameColumn, idColumn);
							isBreak = true;
							status = false;
						}
					}
					break;
				case 4:
					//Gia tri so khong hop le (vuot qua gioi han min, max, overflow)
					if (dataType == 0) {
						if (!ActionUtils.checkFormatInterger(validation, before)) {
							logValidation(logMess, id, maLoi, nameColumn, idColumn);
							isBreak = true;
							status = false;
						} else if (!ActionUtils.checkFormatDouble(validation, before, after)) {
							logValidation(logMess, id, maLoi, nameColumn, idColumn);
							isBreak = true;
							status = false;
						}
					}
					break;
				
				default:
					break;
			}
			if (isBreak == true) { break; }
		}
		return status;
	}
	
	/**
	 * Dieu kien kiem tra danh muc trong Database
	 * lengthData : do dai validation kiem tra chuoi truyen vao
	 * typeDanhMuc (dataType - in): kieu danh muc, xem trong file ValidationUtils.java
	 * */
	public static boolean checkValidationDanhMuc(String validation, LogMessageValidation logMess, String id,
			String nameColumn, String idColumn, int[] maLois, int lengthData, int typeDanhMuc, boolean inputCheck) throws SystemException {
	
		boolean isBreak = false;
		boolean status = inputCheck;
		for (int maLoi : maLois) {
			switch (maLoi) {
				case 1:
					//Chua co gia tri. Bat buoc phai nap
					if (validation == null) {
						logValidation(logMess, id, maLoi, nameColumn, idColumn);
						isBreak = true;
						status = false;
					}
					break;
				case 2:
					//Du lieu thuoc dang danh muc
					switch (typeDanhMuc) {
						case 1:
							//don vi tinh, asw.dm_unit_general, DM_UNIT_GENERAL
							if (validation != null && validation.trim().length() >0 && DmUnitGeneralLocalServiceUtil.getByUnitCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 2:
							//hang~ tau, DmShipAgencyLocalServiceUtil
							if (validation != null && validation.trim().length() >0 && DmShipAgencyLocalServiceUtil.getByShipAgencyCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 3:
							//khu vuc cang,  DmPortRegionLocalServiceUtil
							if (validation != null && validation.trim().length() >0 && DmPortRegionLocalServiceUtil.getByPortRegionCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 4:
							//cau cang, dia diem neo dau,  DmPortWharfLocalServiceUtil
							if (validation != null && validation.trim().length() >0 && DmPortWharfLocalServiceUtil.getByPortWharfCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 5:
							//loai ho so, asw.dm_doc_type,  DM_DOC_TYPE 
							if (validation != null && validation.trim().length() >0 && DmDocTypeLocalServiceUtil.getByDocumentTypeCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 6:
							//loai ho chieu, asw.dm_passport_type,  DM_PASSPORT_TYPE
							if (validation != null && validation.trim().length() >0 && DmPassportTypeLocalServiceUtil.getByPassportTypeCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 7:
							//----------------cap do an ninh,  DM_SECURITY_LEVEL
							if (validation != null && validation.trim().length() >0 && DmSecurityLevelLocalServiceUtil.getBySecurityLevelCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 8:
							//loai tau, asw.dm_ship_type,   DmShipTypeLocalServiceUtil
							if (validation != null && validation.trim().length() >0 && DmShipTypeLocalServiceUtil.getByShipTypeCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 9:
							//cang ?   DmPortLocalServiceUtil
							if (validation != null && validation.trim().length() >0 && DmPortLocalServiceUtil.getByPortCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 10:
							//muc dich den cang ?, asw.dm_arrival_purpose,   DmArrivalPurposeLocalServiceUtil
							if (validation != null && validation.trim().length() >0 && DmArrivalPurposeLocalServiceUtil.getByPortCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 11:
							//quoc ki, asw.dm_state,  DmStateLocalServiceUtil
							if (validation != null && validation.trim().length() >0 && DmStateLocalServiceUtil.getByStateCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 12:
							//BAng danh muc loai hang hoa, asw.dm_goods_type,   DM_GOODS_TYPE
							if (validation != null && validation.trim().length() >0 && DmGoodsTypeLocalServiceUtil.getByGoodsTypeCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 13:
							//danh muc chuc danh thuyen vien, RankCode, DM_RANK_RATING
							if (validation != null && validation.trim().length() >0 && DmRankRatingLocalServiceUtil.getByRankCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 14:
							//------------------hang hoa, khong co, Mã UN/GDS,DM_GOODS
							if (validation != null && validation.trim().length() >0 && DmGoodsLocalServiceUtil.getByGoodsItemCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 15:
							//---------------danh muc loai bao kien---DM_PACKAGE
							if (validation != null && validation.trim().length() >0 && DmPackageLocalServiceUtil.getByPackageCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 16:
							//------------------ DM _ Maritime,  cang? vu,
							if (validation != null && validation.trim().length() >0 && DmMaritimeLocalServiceUtil.getByMaritimeCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						case 17:
							//------------------ DM _ Post Harbour,  
							if (validation != null && validation.trim().length() >0 && DmPortHarbourLocalServiceUtil.getByPortHarbourCode(validation) == null) {
								isBreak = true;
								status = false;
								logValidation(logMess, id, maLoi, nameColumn, idColumn);
							}
							break;
						default:
							break;
					}
					break;
				case 5:
					//Gia tri vuot qua do dai cho phep
					if (!ActionUtils.checkLength(validation, lengthData)) {
						logValidation(logMess, id, maLoi, nameColumn, idColumn);
						isBreak = true;
						status = false;
					}
					break;
				case 6:
					//Gia tri co ky tu dac biet
					if (!ActionUtils.checkIfIsAValidSpecialCharacters(validation)) {
						logValidation(logMess, id, maLoi, nameColumn, idColumn);
						isBreak = true;
						status = false;
					}
					break;
				default:
					break;
			}
			
			if (isBreak == true) { break; }
		}
		return status;
	}

	
	public static void addLogMessageValidation(LogMessageValidation logMessageValidation) {
		try {
			logMessageValidation.setRequestDate(new Date());
			LogMessageValidationLocalServiceUtil.addLogMessageValidation(logMessageValidation);
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}

	
	public static void setParams(ActionRequest resourceRequest) {
		Enumeration<String> listName = resourceRequest.getParameterNames();
		String maTaiLieu = "";
		while (listName.hasMoreElements()) {
			maTaiLieu = listName.nextElement();
			resourceRequest.setAttribute(maTaiLieu,
					ParamUtil.getString(resourceRequest, maTaiLieu));
		}
	}

}
