package vn.gt.portlet.baocao.bctaubien;

import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil;
import vn.gt.utils.FormatData;

import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;


import com.fds.flex.common.utility.string.StringPool;

public class BC58TExport {
	

	public static JSONObject getModel58T(String maritimeCode, String fromDate,
			String toDate, String createDate, String reportPeriod,
			String reportMadeBy) throws SystemException {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		String queryDen = "select v2.MaritimeCode , v1.ItineraryNo , v1.NameOfShip as Ten_tau , v3.StateName as Quoc_tich , v2.CallSign as Ho_hieu , v1.GT , v1.DWT , v1.LOA , CONCAT(v1.ShownDraftxF,'/',v1.ShownDraftxA) as Mon_nuoc , vvvv.Ma_hang , vvvv.K_L , v4.PortName as Cang_den , v6.PortHarbourNameVN as Ben_cang , v7.PortWharfNameVN as Cau_cang , DATE_FORMAT(v1.TimeOfArrival,'%h:%i') as Thoi_gian_den , vv.Tau_lai , v31.ShipAgencyName as Dai_ly , vvv.Hoa_tieu , v1.maritimeRemarks as Ghi_chu , DATE_FORMAT(v1.TimeOfArrival,'%Y-%d-%m') as Ngay from vma_itinerary_schedule as v1 LEFT JOIN vma_itinerary as v2 ON v1.ItineraryNo = v2.ItineraryNo LEFT JOIN dm_ship_agency as v31 on v2.ArrivalShipAgencyCode = v31.ShipAgencyCode LEFT JOIN dm_state as v3 on v2.FlagStateOfShip = v3.StateCode LEFT JOIN dm_port as v4 on v1.LastPortOfCallCode = v4.PortCode LEFT JOIN dm_port_harbour as v6 on v1.PortHarbourCode = v6.PortHarbourCode LEFT JOIN dm_port_wharf as v7 on v1.PortWharfCode = v7.PortWharfCode LEFT JOIN (select v1.ItineraryNo, v1.SequenceNo , GROUP_CONCAT(v2.ShipName) as Tau_lai from vma_schedule_tugboat as v1 INNER JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.SequenceNo = v2.SequenceNo where NoticeShipType = 1 GROUP BY v1.ItineraryNo, v1.SequenceNo) as vv on v1.ItineraryNo = vv.ItineraryNo and vv.Tau_lai is not null LEFT JOIN (select v1.ItineraryNo, v1.SequenceNo , GROUP_CONCAT(v2.PilotName) as Hoa_tieu from vma_schedule_pilot as v1 INNER JOIN vma_schedule_pilot_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.SequenceNo = v2.SequenceNo where NoticeShipType = 1 GROUP BY v1.ItineraryNo, v1.SequenceNo) as vvv on v1.ItineraryNo = vvv.ItineraryNo and vvv.Hoa_tieu is not null LEFT JOIN (select ItineraryNo, GROUP_CONCAT(CargoCode) as Ma_hang, sum(Quantity) as K_L from vma_schedule_cargolist where NoticeShipType = 1 GROUP BY ItineraryNo) as vvvv on v1.ItineraryNo = vvvv.ItineraryNo where v1.NoticeShipType = 1 and v2.MaritimeCode = "
				+ maritimeCode
				+ " and (v1.TimeOfArrival>='"
				+ fromDate
				+ "' and v1.TimeOfArrival<='"
				+ toDate
				+ "') GROUP BY v1.ItineraryNo";

		JSONArray tauDen = DmShipAgencyLocalServiceUtil
				.getModelMau58T(queryDen);
		result.put("Bang1", tauDen);

		String queryRoi = "select v2.MaritimeCode , v1.ItineraryNo , v1.NameOfShip as Ten_tau , v3.StateName as Quoc_tich , v2.CallSign as Ho_hieu , v1.GT , v1.DWT , v1.LOA , CONCAT(v1.ShownDraftxF,'/',v1.ShownDraftxA) as Mon_nuoc , vvvv.Ma_hang , vvvv.K_L , v6.PortHarbourNameVN as Ben_cang , v7.PortWharfNameVN as Cau_cang , v4.PortName as Cang_den , DATE_FORMAT(v1.TimeOfDeparture,'%h:%i') as Thoi_gian_roi , vv.Tau_lai , v31.ShipAgencyName as Dai_ly , vvv.Hoa_tieu , v1.maritimeRemarks as Ghi_chu , DATE_FORMAT(v1.TimeOfDeparture,'%Y-%d-%m') as Ngay from vma_itinerary_schedule as v1 LEFT JOIN vma_itinerary as v2 ON v1.ItineraryNo = v2.ItineraryNo LEFT JOIN dm_ship_agency as v31 on v2.DepartureShipAgencyCode = v31.ShipAgencyCode LEFT JOIN dm_state as v3 on v2.FlagStateOfShip = v3.StateCode LEFT JOIN dm_port as v4 on v1.PortGoingToCode = v4.PortCode LEFT JOIN dm_port_harbour as v6 on v1.PortHarbourCode = v6.PortHarbourCode LEFT JOIN dm_port_wharf as v7 on v1.PortWharfCode = v7.PortWharfCode LEFT JOIN (select v1.ItineraryNo, v1.SequenceNo , GROUP_CONCAT(v2.ShipName) as Tau_lai from vma_schedule_tugboat as v1 INNER JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.SequenceNo = v2.SequenceNo where NoticeShipType = 2 GROUP BY v1.ItineraryNo, v1.SequenceNo) as vv on v1.ItineraryNo = vv.ItineraryNo and vv.Tau_lai is not null LEFT JOIN (select v1.ItineraryNo, v1.SequenceNo , GROUP_CONCAT(v2.PilotName) as Hoa_tieu from vma_schedule_pilot as v1 INNER JOIN vma_schedule_pilot_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.SequenceNo = v2.SequenceNo where NoticeShipType = 2 GROUP BY v1.ItineraryNo, v1.SequenceNo) as vvv on v1.ItineraryNo = vvv.ItineraryNo and vvv.Hoa_tieu is not null LEFT JOIN (select ItineraryNo, GROUP_CONCAT(CargoCode) as Ma_hang, sum(Quantity) as K_L from vma_schedule_cargolist where NoticeShipType = 2 GROUP BY ItineraryNo) as vvvv on v1.ItineraryNo = vvvv.ItineraryNo where v1.NoticeShipType = 2 and v2.MaritimeCode = "
				+ maritimeCode
				+ " and (v1.TimeOfDeparture>='"
				+ fromDate
				+ "' and v1.TimeOfDeparture<='"
				+ toDate
				+ "') GROUP BY v1.ItineraryNo";

		JSONArray tauRoi = DmShipAgencyLocalServiceUtil
				.getModelMau58T(queryRoi);
		result.put("Bang2", tauRoi);

		String queryDich = "select v2.MaritimeCode as Ma_cang_vu , v1.NameOfShip as Ten_tau , v6.StateName as Quoc_gia , v3.CallSign as Ho_hieu , v3.GT as GRT , v3.DWT as DWT , v3.LOA as LOA , CONCAT(v1.ShownDraftxF,'/',v1.ShownDraftxA) as Mon_nuoc , vvvv.Ma_hang , vvvv.K_L , v7.PortHarbourName as Tu_ben , v8.PortWharfName as Tu_cau , v9.PortHarbourName as Den_ben , v10.PortWharfName as Den_cau , DATE_FORMAT(v1.ShiftingDate,'%h-%i') as Thoi_gian , v4.Tau_lai as Tau_lai , v1.NameOfShipownersAgents as Dai_ly , v5.Hoa_tieu as Hoa_tieu , v1.Remarks as Ghi_chu , DATE_FORMAT(v1.ShiftingDate,'%Y-%d-%m') as Ngay from vma_schedule_shifting as v1 LEFT JOIN vma_itinerary as v2 on v1.ItineraryNo = v2.ItineraryNo LEFT JOIN vma_ship as v3 on (v2.CallSign = v3.CallSign and v2.CallSign is not null ) or (v2.IMONumber = v3.IMONumber and v2.IMONumber is not null) LEFT JOIN (SELECT ItineraryNo, GROUP_CONCAT(ShipName) as Tau_lai, SequenceNo from vma_schedule_tugboat_list GROUP BY ItineraryNo) as v4 on v1.ItineraryNo = v4.ItineraryNo and v1.CertificateNo = v4.SequenceNo LEFT JOIN (SELECT ItineraryNo, GROUP_CONCAT(PilotName) as Hoa_tieu, SequenceNo from vma_schedule_pilot_list GROUP BY ItineraryNo) as v5 on v1.ItineraryNo = v5.ItineraryNo and v1.CertificateNo = v5.SequenceNo LEFT JOIN dm_state as v6 on v3.FlagStateOfShip = v6.StateCode LEFT JOIN (select ItineraryNo, GROUP_CONCAT(CargoCode) as Ma_hang, sum(Quantity) as K_L from vma_schedule_cargolist where NoticeShipType = 4 GROUP BY ItineraryNo) as vvvv on v1.ItineraryNo = vvvv.ItineraryNo LEFT JOIN dm_port_harbour as v7 on v1.AnchoringPortHarbourCode = v7.PortHarbourCode LEFT JOIN dm_port_wharf as v8 on v1.AnchoringPortWharfCode = v8.PortWharfCode LEFT JOIN dm_port_harbour as v9 on v1.ShiftingPortHarbourCode = v9.PortHarbourCode LEFT JOIN dm_port_wharf as v10 on v1.ShiftingPortWharfCode = v10.PortWharfCode where v2.MaritimeCode = "
				+ maritimeCode
				+ " and (v1.ShiftingDate>='"
				+ fromDate
				+ "' and v1.ShiftingDate<='" + toDate + "')";

		JSONArray tauDich = DmShipAgencyLocalServiceUtil
				.getModelMau58TauDich(queryDich);

		result.put("Bang3", tauDich);
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
