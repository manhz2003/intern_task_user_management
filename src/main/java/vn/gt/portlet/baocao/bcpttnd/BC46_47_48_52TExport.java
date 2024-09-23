package vn.gt.portlet.baocao.bcpttnd;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.flex.common.utility.string.StringPool;

public class BC46_47_48_52TExport {

	public static JSONObject getModel(String maritimeCode, String fromDate,
			String toDate, String createDate, String reportPeriod,
			String reportMadeBy) throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		String query = "select temp3.*, GROUP_CONCAT(DISTINCT temp4.ten_hang_den SEPARATOR ', ') as ten_hang_den, GROUP_CONCAT(DISTINCT temp4.ten_hang_roi SEPARATOR ', ') as ten_hang_roi from (select temp1.ItineraryNo, temp1.NameOfShip, temp1.RegistryNumber, temp1.VRCode, temp1.ShipTypeName, temp1.LOA, temp1.DWT, temp1.GT, temp1.luot_khach_den, temp2.luot_khach_roi, (case when temp1.LastPortCode is not null and temp1.LastPortCode <> '' then (select PortName from dm_port where PortCode = temp1.LastPortCode) else '' end) as cang_roi, (case when temp1.ArrivalPortCode is not null and temp1.ArrivalPortCode <> '' then (select PortName from dm_port where PortCode = temp1.ArrivalPortCode) else '' end) as cang_den, (case when temp1.NextPortCode is not null and temp1.NextPortCode <> '' then (select PortName from dm_port where PortCode = temp1.NextPortCode) else '' end) as cang_dich, temp1.dai_ly_tau, temp1.ngay_den, temp2.ngay_roi from (select vma_itinerary.ItineraryNo as ItineraryNo, vma_itinerary.NameOfShip as NameOfShip, vma_itinerary.RegistryNumber as RegistryNumber, vma_itinerary.VRCode as VRCode, dm_ship_type.ShipTypeName as ShipTypeName, vma_itinerary_schedule.LOA as LOA , vma_itinerary_schedule.DWT as DWT , vma_itinerary_schedule.GT as GT , vma_itinerary_schedule.PassengerNumber as luot_khach_den, vma_itinerary.LastPortCode as LastPortCode, vma_itinerary.ArrivalPortCode as ArrivalPortCode, vma_itinerary.NextPortCode as NextPortCode, vma_itinerary.TimeOfArrival as ngay_den, CONCAT(vma_itinerary.ArrivalShipAgency,' ',vma_itinerary.DepartureShipAgency) as dai_ly_tau from vma_itinerary inner join dm_ship_type on vma_itinerary.ShipTypeCode = dm_ship_type.ShipTypeCode inner join vma_itinerary_schedule on vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo inner join dm_state on dm_state.StateCode = vma_itinerary.FlagStateOfShip where vma_itinerary.VRCode not in ('VR', 'VRH') and vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.MaritimeCode = "
				+ maritimeCode
				+ " AND vma_itinerary.TimeOfArrival >='"
				+ fromDate
				+ "' and vma_itinerary.TimeOfArrival <='"
				+ toDate
				+ "' group by vma_itinerary.ItineraryNo ) as temp1 inner join (select vma_itinerary.ItineraryNo as ItineraryNo , vma_itinerary_schedule.PassengerNumber as luot_khach_roi , vma_itinerary_schedule.TimeOfDeparture as ngay_roi from vma_itinerary inner join vma_itinerary_schedule on vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo where vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.MaritimeCode = "
				+ maritimeCode
				+ " AND vma_itinerary.TimeOfDeparture >='"
				+ fromDate
				+ "' and vma_itinerary.TimeOfDeparture <='"
				+ toDate
				+ "' group by vma_itinerary.ItineraryNo ) as temp2 on temp1.ItineraryNo = temp2.ItineraryNo ) as temp3 inner join ( select vma_itinerary.ItineraryNo as ItineraryNo, (case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4_DO') and vma_itinerary.MarkedAsArrival not in (0,1,6) then dm_dataitem.name else null end) as ten_hang_den, (case when vma_schedule_cargolist.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4_XEP') and vma_itinerary.MarkedAsDeparture not in (0,1,6) then dm_dataitem.name else null end) as ten_hang_roi from vma_itinerary left join (vma_schedule_cargolist inner join dm_dataitem on dm_dataitem.code = vma_schedule_cargolist.CargoCode and dm_dataitem.datagroupid = '124') on vma_schedule_cargolist.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4_DO') and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.LastPortCode is not null) or (vma_schedule_cargolist.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4_XEP') and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.NextPortCode is not null) ) and MaritimeCode = "
				+ maritimeCode
				+ " and vma_itinerary.VRCode not in ('VR', 'VRH') and (case vma_schedule_cargolist.CargoCategory when 'C2_DO' then vma_itinerary.TimeOfArrival >='"
				+ fromDate
				+ "' and vma_itinerary.TimeOfArrival <='"
				+ toDate
				+ "' when 'C3_DO' then vma_itinerary.TimeOfArrival >='"
				+ fromDate
				+ "' and vma_itinerary.TimeOfArrival <='"
				+ toDate
				+ "' when 'C4_DO' then vma_itinerary.TimeOfArrival >='"
				+ fromDate
				+ "' and vma_itinerary.TimeOfArrival <='"
				+ toDate
				+ "' when 'C5' then vma_itinerary.TimeOfArrival >='"
				+ fromDate
				+ "' and vma_itinerary.TimeOfArrival <='"
				+ toDate
				+ "' when 'C1_XEP' then vma_itinerary.TimeOfDeparture >='"
				+ fromDate
				+ "' and vma_itinerary.TimeOfDeparture <='"
				+ toDate
				+ "' when 'C3_XEP' then vma_itinerary.TimeOfDeparture >='"
				+ fromDate
				+ "' and vma_itinerary.TimeOfDeparture <='"
				+ toDate
				+ "' when 'C4_XEP' then vma_itinerary.TimeOfDeparture >='"
				+ fromDate
				+ "' and vma_itinerary.TimeOfDeparture <='"
				+ toDate
				+ "' else null end) ) as temp4 on temp3.ItineraryNo = temp4.ItineraryNo group by temp3.ItineraryNo";

		JSONArray array = VmaItineraryLocalServiceUtil
				.getModelMau46_47_48_52_71_72_73_79T(query);
		result.put("Bang1", array);

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
