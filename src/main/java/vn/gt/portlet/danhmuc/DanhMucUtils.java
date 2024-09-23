package vn.gt.portlet.danhmuc;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.ActionRequest;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import com.fds.nsw.nghiepvu.model.DmShipAgency;
import com.fds.nsw.nghiepvu.model.DmVmaPilot;
import com.fds.nsw.nghiepvu.model.DmVmaPilotCategory;
import com.fds.nsw.nghiepvu.model.DmVmaShipOwner;
import com.fds.nsw.nghiepvu.model.DmVmaShipyard;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaPilotCategoryLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaPilotLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipOwnerLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmVmaShipyardLocalServiceUtil;
import com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipException;
import com.fds.nsw.nghiepvu.model.VmaSchedulePilotList;
import com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList;
import com.fds.nsw.nghiepvu.model.VmaShip;
import com.fds.nsw.nghiepvu.model.VmaTugboatCondition;
import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaShipLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTugboatConditionLocalServiceUtil;
import vn.gt.tichhop.message.xml.StringUtils;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.utility.string.StringUtil;
import com.fds.flex.common.ultility.Validator;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DanhMucUtils {


	

	private static final String SHIPOWNER = "1", SHIPYARD = "2",
			SHIPAGENCY = "3";
	private static final int VND = 1, USD = 2;
	private static final String[] tensNameUSDs = { "", " ten", " twenty",
			" thirty", " forty", " fifty", " sixty", " seventy", " eighty",
			" ninety" };
	private static final String[] tensNameVNDs = { "", " mười", " hai mươi",
			" ba mươi", " bốn mươi", " năm mươi", " sáu mươi", " bảy mươi",
			" tám mươi", " chín mươi" };
	private static final String[] numNameUSDs = { "", " one", " two", " three",
			" four", " five", " six", " seven", " eight", " nine", " ten",
			" eleven", " twelve", " thirteen", " fourteen", " fifteen",
			" sixteen", " seventeen", " eighteen", " nineteen" };
	private static final String[] numNameVNDs = { "", " một", " hai", " ba",
			" bốn", " năm", " sáu", " bảy", " tám", " chín", " mười",
			" mười một", " mười hai", " mười ba", " mười bốn", " mười lăm",
			" mười sáu", " mười bảy", " mười tám", " mười chín" };

	private static String convertLessThanOneThousand(int number, int language) {
		String soFar = "";

		if (number % 100 < 20) {
			if (language == USD) {
				soFar = numNameUSDs[number % 100];
			} else if (language == VND) {
				soFar = numNameVNDs[number % 100];
			}
			number /= 100;
		} else {
			if (language == USD) {
				soFar = numNameUSDs[number % 10];
			} else if (language == VND) {
				soFar = numNameVNDs[number % 10];
			}
			number /= 10;
			if (language == USD) {
				soFar = tensNameUSDs[number % 10] + soFar;
			} else if (language == VND) {
				soFar = tensNameVNDs[number % 10] + soFar;
			}
			number /= 10;
		}
		if (number == 0)
			return soFar;

		String result = "";
		if (language == USD) {
			result = numNameUSDs[number] + " hundred" + soFar;
		} else if (language == VND) {
			result = numNameVNDs[number] + " trăm" + soFar;
		}
		return result;
	}

	public static String convert(double totalMoney, int language) {
		long number = (long)totalMoney;
		if (number == 0) {
			if (language == USD) {
				return "";
			} else if (language == VND) {
				return "";
			}
		}
		String snumber = Long.toString(number);

		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);

		int billions = Integer.parseInt(snumber.substring(0, 3));
		int millions = Integer.parseInt(snumber.substring(3, 6));
		int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
		int thousands = Integer.parseInt(snumber.substring(9, 12));

		String tradBillions = "";
		switch (billions) {
		case 0:
			tradBillions = "";
			break;
		case 1:
			if (language == USD) {
				tradBillions = convertLessThanOneThousand(billions, language)
						+ " billion ";
			} else if (language == VND) {
				tradBillions = convertLessThanOneThousand(billions, language)
						+ " tỷ ";
			}
			break;
		default:
			if (language == USD) {
				tradBillions = convertLessThanOneThousand(billions, language)
						+ " billion ";
			} else if (language == VND) {
				tradBillions = convertLessThanOneThousand(billions, language)
						+ " tỷ ";
			}
		}
		String result = tradBillions;

		String tradMillions = "";
		switch (millions) {
		case 0:
			tradMillions = "";
			break;
		case 1:
			if (language == USD) {
				tradMillions = convertLessThanOneThousand(millions, language)
						+ " million ";
			} else if (language == VND) {
				tradMillions = convertLessThanOneThousand(millions, language)
						+ " triệu ";
			}
			break;
		default:
			if (language == USD) {
				tradMillions = convertLessThanOneThousand(millions, language)
						+ " million ";
			} else if (language == VND) {
				tradMillions = convertLessThanOneThousand(millions, language)
						+ " triệu ";
			}
		}
		result = result + tradMillions;

		String tradHundredThousands = "";
		switch (hundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			if (language == USD) {
				tradHundredThousands = "one thousand ";
			} else if (language == VND) {
				tradHundredThousands = "một nghìn ";
			}
			break;
		default:
			if (language == USD) {
				tradHundredThousands = convertLessThanOneThousand(
						hundredThousands, USD) + " thousand ";
			} else if (language == VND) {
				tradHundredThousands = convertLessThanOneThousand(
						hundredThousands, VND) + " nghìn ";
			}
		}
		result = result + tradHundredThousands;

		String tradThousand;
		tradThousand = convertLessThanOneThousand(thousands, language);
		result = result + tradThousand;

		NumberFormat formatter = new DecimalFormat("#0.00");
		String[] arrayDecimal = (formatter.format(totalMoney)).split("\\.");
		if (arrayDecimal.length > 1) {
			int decimal = Integer.valueOf(arrayDecimal[1]);
			if (decimal > 0) {
				StringBuilder strDecimal = new StringBuilder();
				if (language == USD) {
					strDecimal.append(" dollars and");
				} else if (language == VND) {
					strDecimal.append(" phẩy");
				}
				strDecimal.append(convertLessThanOneThousand(decimal, language)
						+ StringPool.SPACE);
				if (language == USD) {
					strDecimal.append(" cents");
				}
				result += strDecimal.toString();
			}
		}
		if (language == VND) {
			//result = encodeUTF8(result + " đồng");
			result = result + " đồng";
		} else if (language == USD) {
			if (arrayDecimal.length > 1 & Integer.valueOf(arrayDecimal[1]) > 0) {
				//result = encodeUTF8(result);
				result = result;
			} else {
				//result = encodeUTF8(result + " dollars");
				result = result + " dollars";
			}
		}
		return StringUtil.upperCaseFirstLetter(result.replaceAll("^\\s+", "")
				.replaceAll("\\b\\s{2,}\\b", " "));
	}

	public static HSSFWorkbook reportExcel(JSONObject jsonObject,
			String[] headers, String[] keys, String nameExcel) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			if (workbook != null) {
				HSSFSheet sheet = workbook.createSheet(DanhMucUtils
						.encodeUTF8(nameExcel));
				if (sheet != null) {
					long length = jsonObject.getLong("total");
					JSONArray jsonArray = jsonObject.getJSONArray("data");
					for (int i = 0; i < headers.length; i++) {
						int width = ((int) (headers[i].length() * 1.14388)) * 256;
						sheet.setColumnWidth(i, width);
					}
					HSSFRow header = sheet.createRow(0);
					for (int i = 0; i < headers.length; i++) {
						HSSFCell cell = header.createCell(i);
						cell.setCellValue(encodeUTF8(headers[i]));
					}
					if (length > 0) {
						for (int r = 0; r < length; r++) {
							JSONObject object = jsonArray.getJSONObject(r);
							HSSFRow row = sheet.createRow(r + 1);
							HSSFCell cell = null;
							for (int cd = 0; cd < keys.length; cd++) {
								if (!keys[cd].equals("id")
										&& !keys[cd].equals("dataItemId")
										&& !keys[cd].equals("role")) {
									cell = row.createCell(cd);
									if (keys[cd].equals("isDelete")) {
										String status = "";
										if (object.getInt("isDelete") == 1) {
											status = "Đã đánh dấu xóa";
										} else {
											status = "Đang sử dụng";
										}
										cell.setCellValue(encodeUTF8(status));
									} else if (keys[cd].equals("status")) {
										String status = "";
										if (object.getInt("status") == 0) {
											status = "Đã đánh dấu xóa";
										} else {
											status = "Đang sử dụng";
										}
										cell.setCellValue(encodeUTF8(status));
									} else {
										cell.setCellValue(object
												.getString(keys[cd]));
									}
								}
							}
						}
					}
					return workbook;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public static JSONObject checkTaxCode(String type, String taxCode)
			throws SystemException {
		boolean status = true;
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (type.equals(SHIPOWNER)) {
			DmVmaShipOwner dmVmaShipOwner = null;
			dmVmaShipOwner = DmVmaShipOwnerLocalServiceUtil
					.fetchByShipOwnerCode(taxCode);
			if (dmVmaShipOwner != null) {
				status = false;
			} else {
				dmVmaShipOwner = DmVmaShipOwnerLocalServiceUtil
						.fetchByF_taxCode(taxCode);
				if (dmVmaShipOwner != null) {
					status = false;
				}
			}
		} else if (type.equals(SHIPYARD)) {
			DmVmaShipyard dmVmaShipyard = null;
			dmVmaShipyard = DmVmaShipyardLocalServiceUtil
					.fetchByShipYardCode(taxCode);
			if (dmVmaShipyard != null) {
				status = false;
			} else {
				dmVmaShipyard = DmVmaShipyardLocalServiceUtil
						.fetchByF_taxCode(taxCode);
				if (dmVmaShipyard != null) {
					status = false;
				}
			}
		} else if (type.equals(SHIPAGENCY)) {
			DmShipAgency dmShipAgency = null;
			dmShipAgency = DmShipAgencyLocalServiceUtil
					.fetchByShipAgencyCode(taxCode);
			if (dmShipAgency != null) {
				status = false;
			}
		}
		if (status == true) {
			result.put("status", "200");
			result.put("message", encodeUTF8("Mã số thuế có thể sử dụng"));
		} else if (status == false) {
			result.put("status", "400");
			result.put("message", encodeUTF8("Mã số thuế đã tồn tại"));
		}
		return result;
	}

	public static JSONObject warningPortWharf(String portWharfCode, int dwt,
			double loa, double shownDraftxA) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		int status = 0;
		String message = "";
		try {
			DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
					.getByPortWharfCode(portWharfCode);
			if (dwt > 0) {
				if (dmPortWharf.getDwt().doubleValue() <= 0) {
					status = 0;
				} else if (dwt > dmPortWharf.getDwt().doubleValue()) {
					status = 1;
					message = "Vượt quá trọng tải ";
				} else if (dwt <= dmPortWharf.getDwt().doubleValue()) {
					status = 200;
					message = "Trọng tải cho phép";
				}
			} else if (loa > 0) {
				if (dmPortWharf.getLoa() <= 0) {
					status = 0;
				} else if (loa > dmPortWharf.getLoa()) {
					status = 2;
					message = "Vượt quá chiều dài lớn nhất của cầu cảng ";
				} else if (loa > 0 & loa <= dmPortWharf.getLoa()) {
					status = 200;
					message = "Chiều dài cho phép";
				}
			} else if (shownDraftxA > 0) {
				if (dmPortWharf.getMaxDraft() <= 0) {
					status = 0;
				} else if (shownDraftxA > dmPortWharf.getMaxDraft()) {
					status = 3;
					message = "Vượt quá mớn nước cho phép";
				} else if (shownDraftxA <= dmPortWharf.getMaxDraft()) {
					status = 200;
					message = "Mớn nước cho phép";
				}
			}
		} catch (Exception e) {
			log.info("========= DmPortWharf not found -> accept");
		}
		result.put("status", status);
		result.put("message", encodeUTF8(message.toString()));
		return result;
	}

	public static JSONObject warningTugboat(String maritimeCode,
			String imoNumber, String callSign, String itineraryNo)
			throws SystemException, NoSuchVmaShipException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaShip vmaShip = null;
		try {
			vmaShip = VmaShipLocalServiceUtil.findByIMONumber_CallSign(
					imoNumber, callSign);
		} catch (Exception e) {
			log.error(e.getMessage());
			return result;
		}
		double loa = vmaShip.getLoa();
		int status = 0;
		List<VmaTugboatCondition> vmaTugboatConditions = VmaTugboatConditionLocalServiceUtil
				.findByMaritimeCode(maritimeCode);
		List<VmaScheduleTugboatList> vmaScheduleTugboatLists = VmaScheduleTugboatListLocalServiceUtil
				.findByItineraryNo(itineraryNo);
		List<Double> powerTugboatList = new ArrayList<Double>();
		for (int i = vmaScheduleTugboatLists.size() - 1; i >= 0; i--) {
			powerTugboatList.add(vmaScheduleTugboatLists.get(i).getPower());
		}
		Collections.sort(powerTugboatList);
		String message = "";
		if (vmaTugboatConditions != null && !vmaTugboatConditions.isEmpty()) {
			for (int i = vmaTugboatConditions.size() - 1; i >= 0; i--) {
				if (loa >= vmaTugboatConditions.get(i).getMinLOA()
						&& loa <= vmaTugboatConditions.get(i).getMaxLOA()) {
					int quantity = vmaTugboatConditions.get(i).getQuantity();
					List<Double> powerCondition = new ArrayList<Double>();
					try {
						powerCondition.add(vmaTugboatConditions.get(i)
								.getPower1());
					} catch (Exception e) {
						// nothing to do
					}
					try {
						powerCondition.add(vmaTugboatConditions.get(i)
								.getPower2());
					} catch (Exception e) {
						// nothing to do
					}
					try {
						powerCondition.add(vmaTugboatConditions.get(i)
								.getPower3());
					} catch (Exception e) {
						// nothing to do
					}
					try {
						powerCondition.add(vmaTugboatConditions.get(i)
								.getPower4());
					} catch (Exception e) {
						// nothing to do
					}
					Collections.sort(powerCondition);
					if (vmaScheduleTugboatLists.size() < quantity) {
						status = 400;
						message += "Không đủ số tàu hỗ trợ theo quy định.";
					} else {
						if (!warningTugboat(powerCondition, powerTugboatList,
								quantity)) {
							status = 400;
							message += "Thiếu tàu lai có công suất tối thiểu.";
						}
					}
					break;
				}
			}
		} else {
			log.info("========== DmTugBoat not found -> Accept");
			status = 0;
		}
		result.put("status", status);
		result.put("message", encodeUTF8(message));
		return result;
	}

	private static boolean warningTugboat(List<Double> lst1, List<Double> lst2,
			int quantity) {
		int k = 0, count = 0;
		for (int i = 0; i < lst1.size(); i++) {
			for (int j = k; j < lst2.size(); j++) {
				if (lst2.get(j) >= lst1.get(i)) {
					count++;
					k = j;
					break;
				}
			}
		}
		if (count < quantity) {
			return false;
		}
		return true;
	}

	public static JSONObject warningPilot(String imoNumber, String callSign,
			String itineraryNo) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		int status = 0;
		StringBuilder message = new StringBuilder();
		VmaShip vmaShip = null;
		try {
			vmaShip = VmaShipLocalServiceUtil.findByIMONumber_CallSign(
					imoNumber, callSign);
		} catch (Exception e) {
			result.put("status", status);
			return result;
		}
		double loa = vmaShip.getLoa();
		double gt = vmaShip.getGt().doubleValue();
		List<VmaSchedulePilotList> vmaSchedulePilotLists = new ArrayList<VmaSchedulePilotList>();
		try {
			vmaSchedulePilotLists = VmaSchedulePilotListLocalServiceUtil
					.findByItineraryNo(itineraryNo);
		} catch (Exception e) {
			result.put("status", status);
			return result;
		}
		for (VmaSchedulePilotList vmaSchedulePilotList : vmaSchedulePilotLists) {
			String pilotCode = vmaSchedulePilotList.getPilotCode();
			DmVmaPilot dmVmaPilot = DmVmaPilotLocalServiceUtil
					.fetchbyPilotCode(pilotCode);
			if (dmVmaPilot != null) {
				String pilotCategoryCode = dmVmaPilot.getPilotCategoryCode();
				DmVmaPilotCategory dmVmaPilotCategory = DmVmaPilotCategoryLocalServiceUtil
						.fetchByPilotCategoryCode(pilotCategoryCode);
				if (dmVmaPilotCategory != null) {
					if (Double.valueOf(dmVmaPilotCategory.getMaxLength()) <= loa) {
						status = 400;
						message.append("Hoa tiêu ");
						message.append(dmVmaPilot.getPilotName());
						message.append(" không đủ chiều dài dẫn tàu. ");
					}
					if (Double.valueOf(dmVmaPilotCategory.getGrossTonage()) <= gt) {
						status = 400;
						message.append("Hoa tiêu ");
						message.append(dmVmaPilot.getPilotName());
						message.append(" không đủ dung tích dẫn tàu. ");
					}
				}
			}
		}
		result.put("status", status);
		result.put("message", encodeUTF8(message.toString()));		
		return result;
	}

	public static String timeStamp2Date(long value) {
		long time = value;
		String strDate = StringPool.BLANK;
		if (time > 0) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(time);
			Date date = c.getTime();
			strDate = FormatData.formatDateShort3.format(date);
		}
		return strDate;
	}

	public static String encodeUTF8(String str) {
		try {
			str = URLEncoder.encode(str, "ISO-8859-1");
			str = URLDecoder.decode(str, "UTF-8");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return str;
	}

	public static String getLastSyncVersion(String syncVersion) {
		String[] array = syncVersion.split("\\|");
		String newSyncVersion = array[array.length - 1];
		return String.valueOf(newSyncVersion);
	}

	public static String createNewSyncVersion(String syncVersion) {
		String[] array = syncVersion.split("\\|");
		String newSyncVersion = syncVersion
				+ String.valueOf(Integer.valueOf(array[array.length - 1]) + 1)
				+ "|";
		return newSyncVersion;
	}

	public static String getString(ActionRequest actionRequest, String key) {
		String value = ParamUtil.getString(actionRequest, key);
		if (Validator.isNotNull(value)) {
			try {
				value = URLEncoder.encode(value, "ISO-8859-1");
				value = URLDecoder.decode(value, "UTF-8");
			} catch (Exception e) {
				// nothing to do
			}
		}
		return value;
	}

	public static String getString(HttpServletRequest actionRequest, String key) {
		String value = ParamUtil.getString(actionRequest, key);
		if (Validator.isNotNull(value)) {
			try {
				value = URLEncoder.encode(value, "ISO-8859-1");
				value = URLDecoder.decode(value, "UTF-8");
			} catch (Exception e) {
				// nothing to do
			}
		}
		return value;
	}

	public static String getString(HttpServletRequest actionRequest,
			String key, String defaultValue) {
		String value = ParamUtil.getString(actionRequest, key, defaultValue);
		if (Validator.isNotNull(value)) {
			try {
				value = URLEncoder.encode(value, "ISO-8859-1");
				value = URLDecoder.decode(value, "UTF-8");
			} catch (Exception e) {
				// nothing to do
			}
		}
		return value;
	}

	public static JSONObject getMaritimeCurrent(long userId) {
		String maritimeCode = UserPortLocalServiceUtil.findByUserId(userId)
				.getPortCode();
		DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
				.getByMaritimeCode(maritimeCode);
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("cityCode", dmMaritime.getCityCode());
		jsonObject.put("maritimeCode", dmMaritime.getMaritimeCode());
		jsonObject.put("maritimeNameVN", dmMaritime.getMaritimeNameVN());
		return jsonObject;
	}

	public static JSONObject getPortHarbourByPortRegionCode(
			String portRegionCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();
		List<DmPortHarbour> dmPortHarbours = DmPortHarbourLocalServiceUtil
				.findByPortRegion(portRegionCode);
		for (DmPortHarbour dmPortHarbour : dmPortHarbours) {
			JSONObject json = JSONFactoryUtil.createJSONObject();
			json.put("portRegionNameVN", dmPortHarbour.getPortHarbourNameVN());
			json.put("portHarbourCode", dmPortHarbour.getPortHarbourCode());
			array.put(json);
		}
		result.put("data", array);

		return result;
	}
}
