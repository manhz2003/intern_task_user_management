package vn.gt.portlet.baocao.bctaubien;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.flex.common.utility.string.StringPool;

public class BC59TExport {

	public static JSONObject getModel(String maritimeCode, String fromDate,
			String toDate, String createDate, String reportPeriod,
			String reportMadeBy) throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		String queryRoi = "select v2.MaritimeCode , v1.ItineraryNo , v1.NameOfShip as Ten_tau , ship.ShipTypeName as Loai_tau , v2.CallSign as Ho_hieu , v1.DWT , CONCAT(v1.ShownDraftxF,'/',v1.ShownDraftxA) as Mon_nuoc , v1.ShipOwnersName as Chu_tau , v3.ShipAgencyName as Dai_ly , v3.ContactTell as Dien_thoai , v3.Address as Dia_chi , DATE_FORMAT(v1.TimeOfDeparture,'%h:%i') as Thoi_gian_roi , v4.PortName as Cang_den , v1.ChanelName as Tuyen_luong , DATE_FORMAT(v1.TimeOfDeparture,'%Y-%d-%m') as Ngay from vma_itinerary_schedule as v1 LEFT JOIN vma_itinerary as v2 ON v1.ItineraryNo = v2.ItineraryNo LEFT JOIN dm_ship_type as ship on v2.ShipTypeCode = ship.ShipTypeCode LEFT JOIN dm_ship_agency as v3 on v2.DepartureShipAgencyCode = v3.ShipAgencyCode LEFT JOIN dm_port as v4 on v1.PortGoingToCode = v4.PortCode where v1.NoticeShipType = 2 and v2.MaritimeCode = "
				+ maritimeCode
				+ " and (v1.TimeOfDeparture>='"
				+ fromDate
				+ "' and v1.TimeOfDeparture<='"
				+ toDate
				+ "') GROUP BY v1.ItineraryNo;";
		String queryDen = "select v2.MaritimeCode , v1.ItineraryNo , v1.NameOfShip as Ten_tau , ship.ShipTypeName as Loai_tau , v2.CallSign as Ho_hieu , v1.DWT , CONCAT(v1.ShownDraftxF,'/',v1.ShownDraftxA) as Mon_nuoc , v1.ShipOwnersName as Chu_tau , v3.ShipAgencyName as Dai_ly , v3.ContactTell as Dien_thoai , v3.Address as Dia_chi , DATE_FORMAT(v1.TimeOfArrival,'%h:%i') as Thoi_gian_den , v4.PortName as Cang_roi , v1.ChanelName as Tuyen_luong , DATE_FORMAT(v1.TimeOfArrival,'%Y-%d-%m') as Ngay from vma_itinerary_schedule as v1 LEFT JOIN vma_itinerary as v2 ON v1.ItineraryNo = v2.ItineraryNo LEFT JOIN dm_ship_type as ship on v2.ShipTypeCode = ship.ShipTypeCode LEFT JOIN dm_ship_agency as v3 on v2.ArrivalShipAgencyCode = v3.ShipAgencyCode LEFT JOIN dm_port as v4 on v1.LastPortOfCallCode = v4.PortCode where v1.NoticeShipType = 1 and v2.MaritimeCode = "
				+ maritimeCode
				+ " and (v1.TimeOfArrival>='"
				+ fromDate
				+ "' and v1.TimeOfArrival<='"
				+ toDate
				+ "') GROUP BY v1.ItineraryNo;";

		JSONArray arrayRoi = DmShipAgencyLocalServiceUtil
				.getModelMau59(queryRoi);
		JSONArray arrayDen = DmShipAgencyLocalServiceUtil
				.getModelMau59(queryDen);

		result.put("Bang1", arrayRoi);
		result.put("Bang2", arrayDen);
		result.put("reportMadeBy", reportMadeBy);
		result.put("reportPeriod", reportPeriod);
		try {
			result.put("fromDate", FormatData
					.parseDateToTringDDMMYYY(FormatData.formatDateShort
							.parse(fromDate)));
			result.put("toDate", FormatData
					.parseDateToTringDDMMYYY(FormatData.formatDateShort
							.parse(toDate)));
		} catch (Exception e) {
			// nothing to do
		}
		result.put("reportMonth", fromDate.split(" ")[0].split("-")[1]);
		result.put("reportYear", fromDate.split(" ")[0].split("-")[0]);
		result.put("maritimeNameVN", DmMaritimeLocalServiceUtil
				.getByMaritimeCode(maritimeCode).getMaritimeNameVN());
		result.put("signPlace",
				DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode)
						.getCityCode());
		result.put("signDate", createDate.replace(" 00:00", StringPool.BLANK));

		return result;
	}
}
