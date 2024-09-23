package vn.gt.portlet.phuongtien;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails;


import vn.gt.dao.noticeandmessage.service.VmaTransactionSlipDetailsLocalServiceUtil;

import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.utility.string.StringUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.ThemeDisplay;


import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VmaTransactionSlipDetailUtils
 {
	

	public static JSONObject findById(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		HttpServletRequest request = resourceRequest;
		long id = ParamUtil.getLong(request, "vmaTransactionSlipDetailsId");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaTransactionSlipDetails vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
					.getVmaTransactionSlipDetails(id);
			result = VMAUtils.object2Json(vmaTransactionSlipDetails,
					VmaTransactionSlipDetails.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static JSONObject findByItineraryNo_DocumentaryCode(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		String itineraryNo = ParamUtil.getString(resourceRequest,
				"itineraryNo", StringPool.BLANK);

		String documentaryCode = ParamUtil.getString(resourceRequest,
				"documentaryCode", StringPool.BLANK);

		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			VmaTransactionSlipDetails vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
					.fetchByItineraryNo_DocumentaryCode(itineraryNo,
							documentaryCode);
			result = VMAUtils.object2Json(vmaTransactionSlipDetails,
					VmaTransactionSlipDetails.class);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static VmaTransactionSlipDetails getObjectFromRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		HttpServletRequest request = actionRequest;

		VmaTransactionSlipDetails vmaTransactionSlipDetails = null;

		long id = GetterUtil.getLong(
				request.getParameter("vmaTransactionSlipDetailsId"), -1);

		String itineraryNo = ParamUtil.getString(actionRequest, "itineraryNo",
				StringPool.BLANK);

		String documentaryCode = ParamUtil.getString(actionRequest,
				"documentaryCode", StringPool.BLANK);
		if (id > 0) {
			try {
				vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
						.getVmaTransactionSlipDetails(id);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}

			/*try {
				vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
						.fetchByItineraryNo_DocumentaryCode(itineraryNo,
								documentaryCode);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}*/
		} else {
			vmaTransactionSlipDetails = new VmaTransactionSlipDetails();
		}

		if (Validator.isNotNull(itineraryNo)) {
			vmaTransactionSlipDetails.setItineraryNo(itineraryNo);
		}

		if (Validator.isNotNull(documentaryCode)) {
			vmaTransactionSlipDetails.setDocumentaryCode(documentaryCode);
		}
		String shipBoat = ParamUtil.getString(actionRequest, "shipBoat",
				StringPool.BLANK);
		if (Validator.isNotNull(shipBoat)) {
			vmaTransactionSlipDetails.setShipBoat(shipBoat);
		}
		double gt = GetterUtil.getDouble(request.getParameter("gt"), -1);
		if (gt >= 0) {
			vmaTransactionSlipDetails.setGt(gt);
		}
		double dwt = GetterUtil.getDouble(request.getParameter("dwt"), -1);
		if (dwt >= 0) {
			vmaTransactionSlipDetails.setDwt(dwt);
		}
		double gtPercentage = GetterUtil.getDouble(
				request.getParameter("gtPercentage"), -1);
		if (gtPercentage >= 0) {
			vmaTransactionSlipDetails.setGtPercentage(gtPercentage);
		}
		double gtConversion = GetterUtil.getDouble(
				request.getParameter("gtConversion"), -1);
		if (gtConversion >= 0) {
			vmaTransactionSlipDetails.setGtConversion(gtConversion);
		}
		double inRateF1311Vnd = GetterUtil.getDouble(
				request.getParameter("inRateF1311Vnd"), -1);
		if (inRateF1311Vnd >= 0) {
			vmaTransactionSlipDetails.setInRateF1311Vnd(inRateF1311Vnd);
		}
		double inRateF1312Usd = GetterUtil.getDouble(
				request.getParameter("inRateF1312Usd"), -1);
		if (inRateF1312Usd >= 0) {
			vmaTransactionSlipDetails.setInRateF1312Usd(inRateF1312Usd);
		}
		double inRateF1321Vnd = GetterUtil.getDouble(
				request.getParameter("inRateF1321Vnd"), -1);
		if (inRateF1321Vnd >= 0) {
			vmaTransactionSlipDetails.setInRateF1321Vnd(inRateF1321Vnd);
		}
		double inRateF1322Usd = GetterUtil.getDouble(
				request.getParameter("inRateF1322Usd"), -1);
		if (inRateF1322Usd >= 0) {
			vmaTransactionSlipDetails.setInRateF1322Usd(inRateF1322Usd);
		}
		double inRateF1331Vnd = GetterUtil.getDouble(
				request.getParameter("inRateF1331Vnd"), -1);
		if (inRateF1331Vnd >= 0) {
			vmaTransactionSlipDetails.setInRateF1331Vnd(inRateF1331Vnd);
		}
		double inRateF1332Usd = GetterUtil.getDouble(
				request.getParameter("inRateF1332Usd"), -1);
		if (inRateF1332Usd >= 0) {
			vmaTransactionSlipDetails.setInRateF1332Usd(inRateF1332Usd);
		}
		double inRateF1341Vnd = GetterUtil.getDouble(
				request.getParameter("inRateF1341Vnd"), -1);
		if (inRateF1341Vnd >= 0) {
			vmaTransactionSlipDetails.setInRateF1341Vnd(inRateF1341Vnd);
		}
		double inRateF1342Usd = GetterUtil.getDouble(
				request.getParameter("inRateF1342Usd"), -1);
		if (inRateF1342Usd >= 0) {
			vmaTransactionSlipDetails.setInRateF1342Usd(inRateF1342Usd);
		}
		double inRateF1351Vnd = GetterUtil.getDouble(
				request.getParameter("inRateF1351Vnd"), -1);
		if (inRateF1351Vnd >= 0) {
			vmaTransactionSlipDetails.setInRateF1351Vnd(inRateF1351Vnd);
		}
		double inRateF1352Usd = GetterUtil.getDouble(
				request.getParameter("inRateF1352Usd"), -1);
		if (inRateF1352Usd >= 0) {
			vmaTransactionSlipDetails.setInRateF1352Usd(inRateF1352Usd);
		}
		double inRateF1361Vnd = GetterUtil.getDouble(
				request.getParameter("inRateF1361Vnd"), -1);
		if (inRateF1361Vnd >= 0) {
			vmaTransactionSlipDetails.setInRateF1361Vnd(inRateF1361Vnd);
		}
		double inRateF1362Usd = GetterUtil.getDouble(
				request.getParameter("inRateF1362Usd"), -1);
		if (inRateF1362Usd >= 0) {
			vmaTransactionSlipDetails.setInRateF1362Usd(inRateF1362Usd);
		}
		double inRateF1371Vnd = GetterUtil.getDouble(
				request.getParameter("inRateF1371Vnd"), -1);
		if (inRateF1371Vnd >= 0) {
			vmaTransactionSlipDetails.setInRateF1371Vnd(inRateF1371Vnd);
		}
		double inRateF1372Usd = GetterUtil.getDouble(
				request.getParameter("inRateF1372Usd"), -1);
		if (inRateF1372Usd >= 0) {
			vmaTransactionSlipDetails.setInRateF1372Usd(inRateF1372Usd);
		}
		double inRateF1381Vnd = GetterUtil.getDouble(
				request.getParameter("inRateF1381Vnd"), -1);
		if (inRateF1381Vnd >= 0) {
			vmaTransactionSlipDetails.setInRateF1381Vnd(inRateF1381Vnd);
		}
		double inRateF1382Usd = GetterUtil.getDouble(
				request.getParameter("inRateF1382Usd"), -1);
		if (inRateF1382Usd >= 0) {
			vmaTransactionSlipDetails.setInRateF1382Usd(inRateF1382Usd);
		}
		double inRateF1391Vnd = GetterUtil.getDouble(
				request.getParameter("inRateF1391Vnd"), -1);
		if (inRateF1391Vnd >= 0) {
			vmaTransactionSlipDetails.setInRateF1391Vnd(inRateF1391Vnd);
		}
		double inRateF1392Usd = GetterUtil.getDouble(
				request.getParameter("inRateF1392Usd"), -1);
		if (inRateF1392Usd >= 0) {
			vmaTransactionSlipDetails.setInRateF1392Usd(inRateF1392Usd);
		}
		double inRateF1301Vnd = GetterUtil.getDouble(
				request.getParameter("inRateF1301Vnd"), -1);
		if (inRateF1301Vnd >= 0) {
			vmaTransactionSlipDetails.setInRateF1301Vnd(inRateF1301Vnd);
		}
		double inRateF1302Usd = GetterUtil.getDouble(
				request.getParameter("inRateF1302Usd"), -1);
		if (inRateF1302Usd >= 0) {
			vmaTransactionSlipDetails.setInRateF1302Usd(inRateF1302Usd);
		}
		double inRateF1313Vnd = GetterUtil.getDouble(
				request.getParameter("inRateF1313Vnd"), -1);
		if (inRateF1313Vnd >= 0) {
			vmaTransactionSlipDetails.setInRateF1313Vnd(inRateF1313Vnd);
		}
		double inRateF1363Vnd = GetterUtil.getDouble(
				request.getParameter("inRateF1363Vnd"), -1);
		if (inRateF1363Vnd >= 0) {
			vmaTransactionSlipDetails.setInRateF1363Vnd(inRateF1363Vnd);
		}
		double inRateF1373Vnd = GetterUtil.getDouble(
				request.getParameter("inRateF1373Vnd"), -1);
		if (inRateF1373Vnd >= 0) {
			vmaTransactionSlipDetails.setInRateF1373Vnd(inRateF1373Vnd);
		}
		double inUnitF1311Vnd = GetterUtil.getDouble(
				request.getParameter("inUnitF1311Vnd"), -1);
		if (inUnitF1311Vnd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1311Vnd(inUnitF1311Vnd);
		}
		double inUnitF1312Usd = GetterUtil.getDouble(
				request.getParameter("inUnitF1312Usd"), -1);
		if (inUnitF1312Usd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1312Usd(inUnitF1312Usd);
		}
		double inUnitF1321Vnd = GetterUtil.getDouble(
				request.getParameter("inUnitF1321Vnd"), -1);
		if (inUnitF1321Vnd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1321Vnd(inUnitF1321Vnd);
		}
		double inUnitF1322Usd = GetterUtil.getDouble(
				request.getParameter("inUnitF1322Usd"), -1);
		if (inUnitF1322Usd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1322Usd(inUnitF1322Usd);
		}
		double inUnitF1331Vnd = GetterUtil.getDouble(
				request.getParameter("inUnitF1331Vnd"), -1);
		if (inUnitF1331Vnd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1331Vnd(inUnitF1331Vnd);
		}
		double inUnitF1332Usd = GetterUtil.getDouble(
				request.getParameter("inUnitF1332Usd"), -1);
		if (inUnitF1332Usd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1332Usd(inUnitF1332Usd);
		}
		double inUnitF1341Vnd = GetterUtil.getDouble(
				request.getParameter("inUnitF1341Vnd"), -1);
		if (inUnitF1341Vnd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1341Vnd(inUnitF1341Vnd);
		}
		double inUnitF1342Usd = GetterUtil.getDouble(
				request.getParameter("inUnitF1342Usd"), -1);
		if (inUnitF1342Usd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1342Usd(inUnitF1342Usd);
		}
		double inUnitF1351Vnd = GetterUtil.getDouble(
				request.getParameter("inUnitF1351Vnd"), -1);
		if (inUnitF1351Vnd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1351Vnd(inUnitF1351Vnd);
		}
		double inUnitF1352Usd = GetterUtil.getDouble(
				request.getParameter("inUnitF1352Usd"), -1);
		if (inUnitF1352Usd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1352Usd(inUnitF1352Usd);
		}
		double inUnitF1361Vnd = GetterUtil.getDouble(
				request.getParameter("inUnitF1361Vnd"), -1);
		if (inUnitF1361Vnd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1361Vnd(inUnitF1361Vnd);
		}
		double inUnitF1362Usd = GetterUtil.getDouble(
				request.getParameter("inUnitF1362Usd"), -1);
		if (inUnitF1362Usd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1362Usd(inUnitF1362Usd);
		}
		double inUnitF1371Vnd = GetterUtil.getDouble(
				request.getParameter("inUnitF1371Vnd"), -1);
		if (inUnitF1371Vnd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1371Vnd(inUnitF1371Vnd);
		}
		double inUnitF1372Usd = GetterUtil.getDouble(
				request.getParameter("inUnitF1372Usd"), -1);
		if (inUnitF1372Usd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1372Usd(inUnitF1372Usd);
		}
		double inUnitF1381Vnd = GetterUtil.getDouble(
				request.getParameter("inUnitF1381Vnd"), -1);
		if (inUnitF1381Vnd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1381Vnd(inUnitF1381Vnd);
		}
		double inUnitF1382Usd = GetterUtil.getDouble(
				request.getParameter("inUnitF1382Usd"), -1);
		if (inUnitF1382Usd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1382Usd(inUnitF1382Usd);
		}
		double inUnitF1391Vnd = GetterUtil.getDouble(
				request.getParameter("inUnitF1391Vnd"), -1);
		if (inUnitF1391Vnd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1391Vnd(inUnitF1391Vnd);
		}
		double inUnitF1392Usd = GetterUtil.getDouble(
				request.getParameter("inUnitF1392Usd"), -1);
		if (inUnitF1392Usd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1392Usd(inUnitF1392Usd);
		}
		double inUnitF1301Vnd = GetterUtil.getDouble(
				request.getParameter("inUnitF1301Vnd"), -1);
		if (inUnitF1301Vnd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1301Vnd(inUnitF1301Vnd);
		}
		double inUnitF1302Usd = GetterUtil.getDouble(
				request.getParameter("inUnitF1302Usd"), -1);
		if (inUnitF1302Usd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1302Usd(inUnitF1302Usd);
		}
		double inUnitF1313Vnd = GetterUtil.getDouble(
				request.getParameter("inUnitF1313Vnd"), -1);
		if (inUnitF1313Vnd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1313Vnd(inUnitF1313Vnd);
		}
		double inUnitF1363Vnd = GetterUtil.getDouble(
				request.getParameter("inUnitF1363Vnd"), -1);
		if (inUnitF1363Vnd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1363Vnd(inUnitF1363Vnd);
		}
		double inUnitF1373Vnd = GetterUtil.getDouble(
				request.getParameter("inUnitF1373Vnd"), -1);
		if (inUnitF1373Vnd >= 0) {
			vmaTransactionSlipDetails.setInUnitF1373Vnd(inUnitF1373Vnd);
		}
		double inF1311Vnd = GetterUtil.getDouble(
				request.getParameter("inF1311Vnd"), -1);
		if (inF1311Vnd >= 0) {
			vmaTransactionSlipDetails.setInF1311Vnd(inF1311Vnd);
		}
		double inF1312Usd = GetterUtil.getDouble(
				request.getParameter("inF1312Usd"), -1);
		if (inF1312Usd >= 0) {
			vmaTransactionSlipDetails.setInF1312Usd(inF1312Usd);
		}
		double inF1321Vnd = GetterUtil.getDouble(
				request.getParameter("inF1321Vnd"), -1);
		if (inF1321Vnd >= 0) {
			vmaTransactionSlipDetails.setInF1321Vnd(inF1321Vnd);
		}
		double inF1322Usd = GetterUtil.getDouble(
				request.getParameter("inF1322Usd"), -1);
		if (inF1322Usd >= 0) {
			vmaTransactionSlipDetails.setInF1322Usd(inF1322Usd);
		}
		double inF1331Vnd = GetterUtil.getDouble(
				request.getParameter("inF1331Vnd"), -1);
		if (inF1331Vnd >= 0) {
			vmaTransactionSlipDetails.setInF1331Vnd(inF1331Vnd);
		}
		double inF1332Usd = GetterUtil.getDouble(
				request.getParameter("inF1332Usd"), -1);
		if (inF1332Usd >= 0) {
			vmaTransactionSlipDetails.setInF1332Usd(inF1332Usd);
		}
		double inF1341Vnd = GetterUtil.getDouble(
				request.getParameter("inF1341Vnd"), -1);
		if (inF1341Vnd >= 0) {
			vmaTransactionSlipDetails.setInF1341Vnd(inF1341Vnd);
		}
		double inF1342Usd = GetterUtil.getDouble(
				request.getParameter("inF1342Usd"), -1);
		if (inF1342Usd >= 0) {
			vmaTransactionSlipDetails.setInF1342Usd(inF1342Usd);
		}
		double inF1351Vnd = GetterUtil.getDouble(
				request.getParameter("inF1351Vnd"), -1);
		if (inF1351Vnd >= 0) {
			vmaTransactionSlipDetails.setInF1351Vnd(inF1351Vnd);
		}
		double inF1352Usd = GetterUtil.getDouble(
				request.getParameter("inF1352Usd"), -1);
		if (inF1352Usd >= 0) {
			vmaTransactionSlipDetails.setInF1352Usd(inF1352Usd);
		}
		double inF1361Vnd = GetterUtil.getDouble(
				request.getParameter("inF1361Vnd"), -1);
		if (inF1361Vnd >= 0) {
			vmaTransactionSlipDetails.setInF1361Vnd(inF1361Vnd);
		}
		double inF1362Usd = GetterUtil.getDouble(
				request.getParameter("inF1362Usd"), -1);
		if (inF1362Usd >= 0) {
			vmaTransactionSlipDetails.setInF1362Usd(inF1362Usd);
		}
		double inF1371Vnd = GetterUtil.getDouble(
				request.getParameter("inF1371Vnd"), -1);
		if (inF1371Vnd >= 0) {
			vmaTransactionSlipDetails.setInF1371Vnd(inF1371Vnd);
		}
		double inF1372Usd = GetterUtil.getDouble(
				request.getParameter("inF1372Usd"), -1);
		if (inF1372Usd >= 0) {
			vmaTransactionSlipDetails.setInF1372Usd(inF1372Usd);
		}
		double inF1381Vnd = GetterUtil.getDouble(
				request.getParameter("inF1381Vnd"), -1);
		if (inF1381Vnd >= 0) {
			vmaTransactionSlipDetails.setInF1381Vnd(inF1381Vnd);
		}
		double inF1382Usd = GetterUtil.getDouble(
				request.getParameter("inF1382Usd"), -1);
		if (inF1382Usd >= 0) {
			vmaTransactionSlipDetails.setInF1382Usd(inF1382Usd);
		}
		double inF1391Vnd = GetterUtil.getDouble(
				request.getParameter("inF1391Vnd"), -1);
		if (inF1391Vnd >= 0) {
			vmaTransactionSlipDetails.setInF1391Vnd(inF1391Vnd);
		}
		double inF1392Usd = GetterUtil.getDouble(
				request.getParameter("inF1392Usd"), -1);
		if (inF1392Usd >= 0) {
			vmaTransactionSlipDetails.setInF1392Usd(inF1392Usd);
		}
		double inF1301Vnd = GetterUtil.getDouble(
				request.getParameter("inF1301Vnd"), -1);
		if (inF1301Vnd >= 0) {
			vmaTransactionSlipDetails.setInF1301Vnd(inF1301Vnd);
		}
		double inF1302Usd = GetterUtil.getDouble(
				request.getParameter("inF1302Usd"), -1);
		if (inF1302Usd >= 0) {
			vmaTransactionSlipDetails.setInF1302Usd(inF1302Usd);
		}
		double inF1313Vnd = GetterUtil.getDouble(
				request.getParameter("inF1313Vnd"), -1);
		if (inF1313Vnd >= 0) {
			vmaTransactionSlipDetails.setInF1313Vnd(inF1313Vnd);
		}
		double inF1363Vnd = GetterUtil.getDouble(
				request.getParameter("inF1363Vnd"), -1);
		if (inF1363Vnd >= 0) {
			vmaTransactionSlipDetails.setInF1363Vnd(inF1363Vnd);
		}
		double inF1373Vnd = GetterUtil.getDouble(
				request.getParameter("inF1373Vnd"), -1);
		if (inF1373Vnd >= 0) {
			vmaTransactionSlipDetails.setInF1373Vnd(inF1373Vnd);
		}
		double outRateF1311Vnd = GetterUtil.getDouble(
				request.getParameter("outRateF1311Vnd"), -1);
		if (outRateF1311Vnd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1311Vnd(outRateF1311Vnd);
		}
		double outRateF1312Usd = GetterUtil.getDouble(
				request.getParameter("outRateF1312Usd"), -1);
		if (outRateF1312Usd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1312Usd(outRateF1312Usd);
		}
		double outRateF1321Vnd = GetterUtil.getDouble(
				request.getParameter("outRateF1321Vnd"), -1);
		if (outRateF1321Vnd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1321Vnd(outRateF1321Vnd);
		}
		double outRateF1322Usd = GetterUtil.getDouble(
				request.getParameter("outRateF1322Usd"), -1);
		if (outRateF1322Usd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1322Usd(outRateF1322Usd);
		}
		double outRateF1331Vnd = GetterUtil.getDouble(
				request.getParameter("outRateF1331Vnd"), -1);
		if (outRateF1331Vnd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1331Vnd(outRateF1331Vnd);
		}
		double outRateF1332Usd = GetterUtil.getDouble(
				request.getParameter("outRateF1332Usd"), -1);
		if (outRateF1332Usd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1332Usd(outRateF1332Usd);
		}
		double outRateF1341Vnd = GetterUtil.getDouble(
				request.getParameter("outRateF1341Vnd"), -1);
		if (outRateF1341Vnd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1341Vnd(outRateF1341Vnd);
		}
		double outRateF1342Usd = GetterUtil.getDouble(
				request.getParameter("outRateF1342Usd"), -1);
		if (outRateF1342Usd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1342Usd(outRateF1342Usd);
		}
		double outRateF1351Vnd = GetterUtil.getDouble(
				request.getParameter("outRateF1351Vnd"), -1);
		if (outRateF1351Vnd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1351Vnd(outRateF1351Vnd);
		}
		double outRateF1352Usd = GetterUtil.getDouble(
				request.getParameter("outRateF1352Usd"), -1);
		if (outRateF1352Usd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1352Usd(outRateF1352Usd);
		}
		double outRateF1361Vnd = GetterUtil.getDouble(
				request.getParameter("outRateF1361Vnd"), -1);
		if (outRateF1361Vnd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1361Vnd(outRateF1361Vnd);
		}
		double outRateF1362Usd = GetterUtil.getDouble(
				request.getParameter("outRateF1362Usd"), -1);
		if (outRateF1362Usd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1362Usd(outRateF1362Usd);
		}
		double outRateF1371Vnd = GetterUtil.getDouble(
				request.getParameter("outRateF1371Vnd"), -1);
		if (outRateF1371Vnd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1371Vnd(outRateF1371Vnd);
		}
		double outRateF1372Usd = GetterUtil.getDouble(
				request.getParameter("outRateF1372Usd"), -1);
		if (outRateF1372Usd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1372Usd(outRateF1372Usd);
		}
		double outRateF1381Vnd = GetterUtil.getDouble(
				request.getParameter("outRateF1381Vnd"), -1);
		if (outRateF1381Vnd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1381Vnd(outRateF1381Vnd);
		}
		double outRateF1382Usd = GetterUtil.getDouble(
				request.getParameter("outRateF1382Usd"), -1);
		if (outRateF1382Usd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1382Usd(outRateF1382Usd);
		}
		double outRateF1391Vnd = GetterUtil.getDouble(
				request.getParameter("outRateF1391Vnd"), -1);
		if (outRateF1391Vnd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1391Vnd(outRateF1391Vnd);
		}
		double outRateF1392Usd = GetterUtil.getDouble(
				request.getParameter("outRateF1392Usd"), -1);
		if (outRateF1392Usd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1392Usd(outRateF1392Usd);
		}
		double outRateF1301Vnd = GetterUtil.getDouble(
				request.getParameter("outRateF1301Vnd"), -1);
		if (outRateF1301Vnd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1301Vnd(outRateF1301Vnd);
		}
		double outRateF1302Usd = GetterUtil.getDouble(
				request.getParameter("outRateF1302Usd"), -1);
		if (outRateF1302Usd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1302Usd(outRateF1302Usd);
		}
		double outRateF1313Vnd = GetterUtil.getDouble(
				request.getParameter("outRateF1313Vnd"), -1);
		if (outRateF1313Vnd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1313Vnd(outRateF1313Vnd);
		}
		double outRateF1363Vnd = GetterUtil.getDouble(
				request.getParameter("outRateF1363Vnd"), -1);
		if (outRateF1363Vnd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1363Vnd(outRateF1363Vnd);
		}
		double outRateF1373Vnd = GetterUtil.getDouble(
				request.getParameter("outRateF1373Vnd"), -1);
		if (outRateF1373Vnd >= 0) {
			vmaTransactionSlipDetails.setOutRateF1373Vnd(outRateF1373Vnd);
		}
		double outUnitF1311Vnd = GetterUtil.getDouble(
				request.getParameter("outUnitF1311Vnd"), -1);
		if (outUnitF1311Vnd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1311Vnd(outUnitF1311Vnd);
		}
		double outUnitF1312Usd = GetterUtil.getDouble(
				request.getParameter("outUnitF1312Usd"), -1);
		if (outUnitF1312Usd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1312Usd(outUnitF1312Usd);
		}
		double outUnitF1321Vnd = GetterUtil.getDouble(
				request.getParameter("outUnitF1321Vnd"), -1);
		if (outUnitF1321Vnd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1321Vnd(outUnitF1321Vnd);
		}
		double outUnitF1322Usd = GetterUtil.getDouble(
				request.getParameter("outUnitF1322Usd"), -1);
		if (outUnitF1322Usd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1322Usd(outUnitF1322Usd);
		}
		double outUnitF1331Vnd = GetterUtil.getDouble(
				request.getParameter("outUnitF1331Vnd"), -1);
		if (outUnitF1331Vnd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1331Vnd(outUnitF1331Vnd);
		}
		double outUnitF1332Usd = GetterUtil.getDouble(
				request.getParameter("outUnitF1332Usd"), -1);
		if (outUnitF1332Usd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1332Usd(outUnitF1332Usd);
		}
		double outUnitF1341Vnd = GetterUtil.getDouble(
				request.getParameter("outUnitF1341Vnd"), -1);
		if (outUnitF1341Vnd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1341Vnd(outUnitF1341Vnd);
		}
		double outUnitF1342Usd = GetterUtil.getDouble(
				request.getParameter("outUnitF1342Usd"), -1);
		if (outUnitF1342Usd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1342Usd(outUnitF1342Usd);
		}
		double outUnitF1351Vnd = GetterUtil.getDouble(
				request.getParameter("outUnitF1351Vnd"), -1);
		if (outUnitF1351Vnd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1351Vnd(outUnitF1351Vnd);
		}
		double outUnitF1352Usd = GetterUtil.getDouble(
				request.getParameter("outUnitF1352Usd"), -1);
		if (outUnitF1352Usd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1352Usd(outUnitF1352Usd);
		}
		double outUnitF1361Vnd = GetterUtil.getDouble(
				request.getParameter("outUnitF1361Vnd"), -1);
		if (outUnitF1361Vnd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1361Vnd(outUnitF1361Vnd);
		}
		double outUnitF1362Usd = GetterUtil.getDouble(
				request.getParameter("outUnitF1362Usd"), -1);
		if (outUnitF1362Usd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1362Usd(outUnitF1362Usd);
		}
		double outUnitF1371Vnd = GetterUtil.getDouble(
				request.getParameter("outUnitF1371Vnd"), -1);
		if (outUnitF1371Vnd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1371Vnd(outUnitF1371Vnd);
		}
		double outUnitF1372Usd = GetterUtil.getDouble(
				request.getParameter("outUnitF1372Usd"), -1);
		if (outUnitF1372Usd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1372Usd(outUnitF1372Usd);
		}
		double outUnitF1381Vnd = GetterUtil.getDouble(
				request.getParameter("outUnitF1381Vnd"), -1);
		if (outUnitF1381Vnd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1381Vnd(outUnitF1381Vnd);
		}
		double outUnitF1382Usd = GetterUtil.getDouble(
				request.getParameter("outUnitF1382Usd"), -1);
		if (outUnitF1382Usd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1382Usd(outUnitF1382Usd);
		}
		double outUnitF1391Vnd = GetterUtil.getDouble(
				request.getParameter("outUnitF1391Vnd"), -1);
		if (outUnitF1391Vnd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1391Vnd(outUnitF1391Vnd);
		}
		double outUnitF1392Usd = GetterUtil.getDouble(
				request.getParameter("outUnitF1392Usd"), -1);
		if (outUnitF1392Usd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1392Usd(outUnitF1392Usd);
		}
		double outUnitF1301Vnd = GetterUtil.getDouble(
				request.getParameter("outUnitF1301Vnd"), -1);
		if (outUnitF1301Vnd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1301Vnd(outUnitF1301Vnd);
		}
		double outUnitF1302Usd = GetterUtil.getDouble(
				request.getParameter("outUnitF1302Usd"), -1);
		if (outUnitF1302Usd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1302Usd(outUnitF1302Usd);
		}
		double outUnitF1313Vnd = GetterUtil.getDouble(
				request.getParameter("outUnitF1313Vnd"), -1);
		if (outUnitF1313Vnd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1313Vnd(outUnitF1313Vnd);
		}
		double outUnitF1363Vnd = GetterUtil.getDouble(
				request.getParameter("outUnitF1363Vnd"), -1);
		if (outUnitF1363Vnd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1363Vnd(outUnitF1363Vnd);
		}
		double outUnitF1373Vnd = GetterUtil.getDouble(
				request.getParameter("outUnitF1373Vnd"), -1);
		if (outUnitF1373Vnd >= 0) {
			vmaTransactionSlipDetails.setOutUnitF1373Vnd(outUnitF1373Vnd);
		}
		double outF1311Vnd = GetterUtil.getDouble(
				request.getParameter("outF1311Vnd"), -1);
		if (outF1311Vnd >= 0) {
			vmaTransactionSlipDetails.setOutF1311Vnd(outF1311Vnd);
		}
		double outF1312Usd = GetterUtil.getDouble(
				request.getParameter("outF1312Usd"), -1);
		if (outF1312Usd >= 0) {
			vmaTransactionSlipDetails.setOutF1312Usd(outF1312Usd);
		}
		double outF1321Vnd = GetterUtil.getDouble(
				request.getParameter("outF1321Vnd"), -1);
		if (outF1321Vnd >= 0) {
			vmaTransactionSlipDetails.setOutF1321Vnd(outF1321Vnd);
		}
		double outF1322Usd = GetterUtil.getDouble(
				request.getParameter("outF1322Usd"), -1);
		if (outF1322Usd >= 0) {
			vmaTransactionSlipDetails.setOutF1322Usd(outF1322Usd);
		}
		double outF1331Vnd = GetterUtil.getDouble(
				request.getParameter("outF1331Vnd"), -1);
		if (outF1331Vnd >= 0) {
			vmaTransactionSlipDetails.setOutF1331Vnd(outF1331Vnd);
		}
		double outF1332Usd = GetterUtil.getDouble(
				request.getParameter("outF1332Usd"), -1);
		if (outF1332Usd >= 0) {
			vmaTransactionSlipDetails.setOutF1332Usd(outF1332Usd);
		}
		double outF1341Vnd = GetterUtil.getDouble(
				request.getParameter("outF1341Vnd"), -1);
		if (outF1341Vnd >= 0) {
			vmaTransactionSlipDetails.setOutF1341Vnd(outF1341Vnd);
		}
		double outF1342Usd = GetterUtil.getDouble(
				request.getParameter("outF1342Usd"), -1);
		if (outF1342Usd >= 0) {
			vmaTransactionSlipDetails.setOutF1342Usd(outF1342Usd);
		}
		double outF1351Vnd = GetterUtil.getDouble(
				request.getParameter("outF1351Vnd"), -1);
		if (outF1351Vnd >= 0) {
			vmaTransactionSlipDetails.setOutF1351Vnd(outF1351Vnd);
		}
		double outF1352Usd = GetterUtil.getDouble(
				request.getParameter("outF1352Usd"), -1);
		if (outF1352Usd >= 0) {
			vmaTransactionSlipDetails.setOutF1352Usd(outF1352Usd);
		}
		double outF1361Vnd = GetterUtil.getDouble(
				request.getParameter("outF1361Vnd"), -1);
		if (outF1361Vnd >= 0) {
			vmaTransactionSlipDetails.setOutF1361Vnd(outF1361Vnd);
		}
		double outF1362Usd = GetterUtil.getDouble(
				request.getParameter("outF1362Usd"), -1);
		if (outF1362Usd >= 0) {
			vmaTransactionSlipDetails.setOutF1362Usd(outF1362Usd);
		}
		double outF1371Vnd = GetterUtil.getDouble(
				request.getParameter("outF1371Vnd"), -1);
		if (outF1371Vnd >= 0) {
			vmaTransactionSlipDetails.setOutF1371Vnd(outF1371Vnd);
		}
		double outF1372Usd = GetterUtil.getDouble(
				request.getParameter("outF1372Usd"), -1);
		if (outF1372Usd >= 0) {
			vmaTransactionSlipDetails.setOutF1372Usd(outF1372Usd);
		}
		double outF1381Vnd = GetterUtil.getDouble(
				request.getParameter("outF1381Vnd"), -1);
		if (outF1381Vnd >= 0) {
			vmaTransactionSlipDetails.setOutF1381Vnd(outF1381Vnd);
		}
		double outF1382Usd = GetterUtil.getDouble(
				request.getParameter("outF1382Usd"), -1);
		if (outF1382Usd >= 0) {
			vmaTransactionSlipDetails.setOutF1382Usd(outF1382Usd);
		}
		double outF1391Vnd = GetterUtil.getDouble(
				request.getParameter("outF1391Vnd"), -1);
		if (outF1391Vnd >= 0) {
			vmaTransactionSlipDetails.setOutF1391Vnd(outF1391Vnd);
		}
		double outF1392Usd = GetterUtil.getDouble(
				request.getParameter("outF1392Usd"), -1);
		if (outF1392Usd >= 0) {
			vmaTransactionSlipDetails.setOutF1392Usd(outF1392Usd);
		}
		double outF1301Vnd = GetterUtil.getDouble(
				request.getParameter("outF1301Vnd"), -1);
		if (outF1301Vnd >= 0) {
			vmaTransactionSlipDetails.setOutF1301Vnd(outF1301Vnd);
		}
		double outF1302Usd = GetterUtil.getDouble(
				request.getParameter("outF1302Usd"), -1);
		if (outF1302Usd >= 0) {
			vmaTransactionSlipDetails.setOutF1302Usd(outF1302Usd);
		}
		double outF1313Vnd = GetterUtil.getDouble(
				request.getParameter("outF1313Vnd"), -1);
		if (outF1313Vnd >= 0) {
			vmaTransactionSlipDetails.setOutF1313Vnd(outF1313Vnd);
		}
		double outF1363Vnd = GetterUtil.getDouble(
				request.getParameter("outF1363Vnd"), -1);
		if (outF1363Vnd >= 0) {
			vmaTransactionSlipDetails.setOutF1363Vnd(outF1363Vnd);
		}
		double outF1373Vnd = GetterUtil.getDouble(
				request.getParameter("outF1373Vnd"), -1);
		if (outF1373Vnd >= 0) {
			vmaTransactionSlipDetails.setOutF1373Vnd(outF1373Vnd);
		}
		
		boolean flagChangeSymbolReceipt = false;
		if (vmaTransactionSlipDetails.getItineraryNo().contains("HPG")) {
			flagChangeSymbolReceipt = true;
		}
		
		String gtRemarks = VMAUtils.getString(actionRequest, "gtRemarks",
				StringPool.BLANK);
		if (Validator.isNotNull(gtRemarks)) {
			if (flagChangeSymbolReceipt == true) {
				gtRemarks = StringUtil.replace(gtRemarks, "*", "x");
			}
			vmaTransactionSlipDetails.setGtRemarks(gtRemarks);
		}
		String f1301Remarks = VMAUtils.getString(actionRequest,
				"f1301Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1301Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1301Remarks = StringUtil.replace(f1301Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1301Remarks(f1301Remarks);
		}
		String f1302Remarks = VMAUtils.getString(actionRequest,
				"f1302Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1302Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1302Remarks = StringUtil.replace(f1302Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1302Remarks(f1302Remarks);
		}
		String f1311Remarks = VMAUtils.getString(actionRequest,
				"f1311Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1311Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1311Remarks = StringUtil.replace(f1311Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1311Remarks(f1311Remarks);
		}
		String f1312Remarks = VMAUtils.getString(actionRequest,
				"f1312Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1312Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1312Remarks = StringUtil.replace(f1312Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1312Remarks(f1312Remarks);
		}
		String f1321Remarks = VMAUtils.getString(actionRequest,
				"f1321Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1321Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1321Remarks = StringUtil.replace(f1321Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1321Remarks(f1321Remarks);
		}
		String f1322Remarks = VMAUtils.getString(actionRequest,
				"f1322Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1322Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1322Remarks = StringUtil.replace(f1322Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1322Remarks(f1322Remarks);
		}
		String f1331Remarks = VMAUtils.getString(actionRequest,
				"f1331Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1331Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1331Remarks = StringUtil.replace(f1331Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1331Remarks(f1331Remarks);
		}
		String f1332Remarks = VMAUtils.getString(actionRequest,
				"f1332Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1332Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1332Remarks = StringUtil.replace(f1332Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1332Remarks(f1332Remarks);
		}
		String f1341Remarks = VMAUtils.getString(actionRequest,
				"f1341Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1341Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1341Remarks = StringUtil.replace(f1341Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1341Remarks(f1341Remarks);
		}
		String f1342Remarks = VMAUtils.getString(actionRequest,
				"f1342Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1342Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1342Remarks = StringUtil.replace(f1342Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1342Remarks(f1342Remarks);
		}
		String f1351Remarks = VMAUtils.getString(actionRequest,
				"f1351Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1351Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1351Remarks = StringUtil.replace(f1351Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1351Remarks(f1351Remarks);
		}
		String f1352Remarks = VMAUtils.getString(actionRequest,
				"f1352Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1352Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1352Remarks = StringUtil.replace(f1352Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1352Remarks(f1352Remarks);
		}
		String f1361Remarks = VMAUtils.getString(actionRequest,
				"f1361Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1361Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1361Remarks = StringUtil.replace(f1361Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1361Remarks(f1361Remarks);
		}
		String f1362Remarks = VMAUtils.getString(actionRequest,
				"f1362Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1362Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1362Remarks = StringUtil.replace(f1362Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1362Remarks(f1362Remarks);
		}
		String f1371Remarks = VMAUtils.getString(actionRequest,
				"f1371Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1371Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1371Remarks = StringUtil.replace(f1371Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1371Remarks(f1371Remarks);
		}
		String f1372Remarks = VMAUtils.getString(actionRequest,
				"f1372Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1372Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1372Remarks = StringUtil.replace(f1372Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1372Remarks(f1372Remarks);
		}
		String f1381Remarks = VMAUtils.getString(actionRequest,
				"f1381Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1381Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1381Remarks = StringUtil.replace(f1381Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1381Remarks(f1381Remarks);
		}
		String f1382Remarks = VMAUtils.getString(actionRequest,
				"f1382Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1382Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1382Remarks = StringUtil.replace(f1382Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1382Remarks(f1382Remarks);
		}
		String f1391Remarks = VMAUtils.getString(actionRequest,
				"f1391Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1391Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1391Remarks = StringUtil.replace(f1391Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1391Remarks(f1391Remarks);
		}
		String f1392Remarks = VMAUtils.getString(actionRequest,
				"f1392Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1392Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1392Remarks = StringUtil.replace(f1392Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1392Remarks(f1392Remarks);
		}
		
		String f1313Remarks = VMAUtils.getString(actionRequest,
				"f1313Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1313Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1313Remarks = StringUtil.replace(f1313Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1313Remarks(f1313Remarks);
		}
		String f1363Remarks = VMAUtils.getString(actionRequest,
				"f1363Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1363Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1363Remarks = StringUtil.replace(f1363Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1363Remarks(f1363Remarks);
		}
		String f1373Remarks = VMAUtils.getString(actionRequest,
				"f1373Remarks", StringPool.BLANK);
		if (Validator.isNotNull(f1373Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				f1373Remarks = StringUtil.replace(f1373Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setF1373Remarks(f1373Remarks);
		}
		String inCurrencyCode = VMAUtils.getString(actionRequest,
				"inCurrencyCode", StringPool.BLANK);
		if (Validator.isNotNull(inCurrencyCode)) {
			vmaTransactionSlipDetails.setInCurrencyCode(inCurrencyCode);
		}
		String outCurrencyCode = VMAUtils.getString(actionRequest,
				"outCurrencyCode", StringPool.BLANK);
		if (Validator.isNotNull(outCurrencyCode)) {
			vmaTransactionSlipDetails.setOutCurrencyCode(outCurrencyCode);
		}

		// 14 trường chưa truyền giá trị đầu vào 
		String inGtRemarks = VMAUtils.getString(actionRequest,
				"inGtRemarks", StringPool.BLANK);
		if (Validator.isNotNull(inGtRemarks)) {
			if (flagChangeSymbolReceipt == true) {
				inGtRemarks = StringUtil.replace(inGtRemarks, "*", "x");
			}
			vmaTransactionSlipDetails.setInGtRemarks(inGtRemarks);
		}
		String outGtRemarks = VMAUtils.getString(actionRequest,
				"outGtRemarks", StringPool.BLANK);
		if (Validator.isNotNull(outGtRemarks)) {
			if (flagChangeSymbolReceipt == true) {
				outGtRemarks = StringUtil.replace(outGtRemarks, "*", "x");
			}
			vmaTransactionSlipDetails.setOutGtRemarks(outGtRemarks);
		}
		String inDwtRemarks = VMAUtils.getString(actionRequest,
				"inDwtRemarks", StringPool.BLANK);
		if (Validator.isNotNull(inDwtRemarks)) {
			if (flagChangeSymbolReceipt == true) {
				inDwtRemarks = StringUtil.replace(inDwtRemarks, "*", "x");
			}
			vmaTransactionSlipDetails.setInDwtRemarks(inDwtRemarks);
		}
		String outDwtRemarks = VMAUtils.getString(actionRequest,
				"outDwtRemarks", StringPool.BLANK);
		if (Validator.isNotNull(outDwtRemarks)) {
			if (flagChangeSymbolReceipt == true) {
				outDwtRemarks = StringUtil.replace(outDwtRemarks, "*", "x");
			}
			vmaTransactionSlipDetails.setOutDwtRemarks(outDwtRemarks);
		}
		String inF1313Remarks = VMAUtils.getString(actionRequest,
				"inF1313Remarks", StringPool.BLANK);
		if (Validator.isNotNull(inF1313Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				inF1313Remarks = StringUtil.replace(inF1313Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setInF1313Remarks(inF1313Remarks);
		}
		String outF1313Remarks = VMAUtils.getString(actionRequest,
				"outF1313Remarks", StringPool.BLANK);
		if (Validator.isNotNull(outF1313Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				outF1313Remarks = StringUtil.replace(outF1313Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setOutF1313Remarks(outF1313Remarks);
		}
		String inF1311Remarks = VMAUtils.getString(actionRequest,
				"inF1311Remarks", StringPool.BLANK);
		if (Validator.isNotNull(inF1311Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				inF1311Remarks = StringUtil.replace(inF1311Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setInF1311Remarks(inF1311Remarks);
		}
		String inF1312Remarks = VMAUtils.getString(actionRequest,
				"inF1312Remarks", StringPool.BLANK);
		if (Validator.isNotNull(inF1312Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				inF1312Remarks = StringUtil.replace(inF1312Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setInF1312Remarks(inF1312Remarks);
		}
		String outF1311Remarks = VMAUtils.getString(actionRequest,
				"outF1311Remarks", StringPool.BLANK);
		if (Validator.isNotNull(outF1311Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				outF1311Remarks = StringUtil.replace(outF1311Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setOutF1311Remarks(outF1311Remarks);
		}
		String outF1312Remarks = VMAUtils.getString(actionRequest,
				"outF1312Remarks", StringPool.BLANK);
		if (Validator.isNotNull(outF1312Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				outF1312Remarks = StringUtil.replace(outF1312Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setOutF1312Remarks(outF1312Remarks);
		}
		String inF1351Remarks = VMAUtils.getString(actionRequest,
				"inF1351Remarks", StringPool.BLANK);
		if (Validator.isNotNull(inF1351Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				inF1351Remarks = StringUtil.replace(inF1351Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setInF1351Remarks(inF1351Remarks);
		}
		String inF1352Remarks = VMAUtils.getString(actionRequest,
				"inF1352Remarks", StringPool.BLANK);
		if (Validator.isNotNull(inF1352Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				inF1352Remarks = StringUtil.replace(inF1352Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setInF1352Remarks(inF1352Remarks);
		}
		String outF1351Remarks = VMAUtils.getString(actionRequest,
				"outF1351Remarks", StringPool.BLANK);
		if (Validator.isNotNull(outF1351Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				outF1351Remarks = StringUtil.replace(outF1351Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setOutF1351Remarks(outF1351Remarks);
		}
		String outF1352Remarks = VMAUtils.getString(actionRequest,
				"outF1352Remarks", StringPool.BLANK);
		if (Validator.isNotNull(outF1352Remarks)) {
			if (flagChangeSymbolReceipt == true) {
				outF1352Remarks = StringUtil.replace(outF1352Remarks, "*", "x");
			}
			vmaTransactionSlipDetails.setOutF1352Remarks(outF1352Remarks);
		}
		
		return vmaTransactionSlipDetails;
	}

	public static JSONObject addVmaTransactionSlipDetails(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		VmaTransactionSlipDetails vmaTransactionSlipDetails = getObjectFromRequest(
				themeDisplay, actionRequest);

		if (vmaTransactionSlipDetails == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			/*VmaTransactionSlip vmaTransactionSlip2 = VmaTransactionSlipLocalServiceUtil
					.fetchByitineraryNo_documentaryCode(
							vmaTransactionSlipDetails.getItineraryNo(),
							vmaTransactionSlipDetails.getDocumentaryCode());*/
			VmaTransactionSlipDetails vmaTransactionSlipDetails2 = VmaTransactionSlipDetailsLocalServiceUtil
					.fetchByItineraryNo_DocumentaryCode(
							vmaTransactionSlipDetails.getItineraryNo(),
							vmaTransactionSlipDetails.getDocumentaryCode());

			if (Validator.isNull(vmaTransactionSlipDetails2) ) {		
				vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
						.addVmaTransactionSlipDetails(vmaTransactionSlipDetails);
			} else {				
				vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
						.updateVmaTransactionSlipDetails(vmaTransactionSlipDetails);
			}
			
			result = VMAUtils.object2Json(vmaTransactionSlipDetails,
					VmaTransactionSlipDetails.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject updateVmaTransactionSlipDetails(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		VmaTransactionSlipDetails vmaTransactionSlipDetails = getObjectFromRequest(
				themeDisplay, actionRequest);
		if (vmaTransactionSlipDetails == null) {
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "not_found"),
					"not_found", StringPool.BLANK);
		}
		try {
			vmaTransactionSlipDetails = VmaTransactionSlipDetailsLocalServiceUtil
					.updateVmaTransactionSlipDetails(vmaTransactionSlipDetails);
			result = VMAUtils.object2Json(vmaTransactionSlipDetails,
					VmaTransactionSlipDetails.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return VMAUtils.createResponseMessage(
					LanguageUtil.get(themeDisplay.getLocale(), "system_error"),
					"system_error", StringPool.BLANK);
		}
		return result;
	}

	public static JSONObject deleteVmaTransactionSlipDetails(
			ThemeDisplay themeDisplay, ActionRequest actionRequest) {
		long id = ParamUtil.getLong(actionRequest,
				"vmaTransactionSlipDetailsId");
		if (id > 0) {
			try {
				VmaTransactionSlipDetailsLocalServiceUtil
						.deleteVmaTransactionSlipDetails(id);
				return VMAUtils.createResponseMessage(
						LanguageUtil.get(themeDisplay.getLocale(), "success"),
						"", StringPool.BLANK);
			} catch (Exception e) {
				return VMAUtils.createResponseMessage(LanguageUtil.get(
						themeDisplay.getLocale(), "system_error"),
						"system_error", StringPool.BLANK);
			}
		} else {
			return VMAUtils.createResponseMessage(LanguageUtil.get(
					themeDisplay.getLocale(), "incorrect_identifier"),
					"incorrect_identifier", StringPool.BLANK);
		}
	}

}
