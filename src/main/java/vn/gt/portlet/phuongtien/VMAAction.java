package vn.gt.portlet.phuongtien;

import java.util.List;
import java.util.Map;

import com.fds.nsw.liferay.core.*;

import com.fds.nsw.liferay.core.PortletURL;
import com.fds.nsw.liferay.core.ResourceURL;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.UserPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.portlet.TransportationMVCAction;
import vn.gt.portlet.danhmuc.UserPortUtils;
import vn.gt.portlet.danhmuc.VmaTugboatUtils;
import vn.gt.portlet.kehoach.KeToanAction;

import org.json.JSONObject;
import com.fds.nsw.liferay.core.LiferayPortletMode;
import com.fds.nsw.liferay.core.LiferayWindowState;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.Organization;
import com.fds.nsw.liferay.model.Role;
import com.fds.nsw.liferay.model.User;

import com.fds.nsw.liferay.core.PortletURLFactoryUtil;

import static com.fds.nsw.liferay.core.Constants.WAR_VMA_ACTION;

/**
 * Portlet implementation class KeHoachAction
 */
@Slf4j
@Service
public class VMAAction extends TransportationMVCAction {

	public ResponseEntity<?> serveResource(ResourceRequest resourceRequest,
										   ResourceResponse resourceResponse)  {
		try {
			HttpServletRequest request = resourceRequest;
			HttpServletRequest requestOrg = request;

			String resourceID = ParamUtil.getString(requestOrg, "p_p_resource_id", "");
			System.out
					.println("=================================>>>>> resourceID "
							+ resourceID
							+ " | "
							+ resourceRequest.getParameterMap().size());
			if (resourceRequest.getParameterMap() != null) {
				for (Map.Entry<String, String[]> entry : resourceRequest
						.getParameterMap().entrySet()) {
					System.out.println(entry.getKey() + " | "
							+ entry.getValue()[0]);
				}
			}

			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getSession().getAttribute
(WebKeys.THEME_DISPLAY);


			if (resourceID.equals("getURLInit")) {

				User user = themeDisplay.getUser();
				boolean flagDT = false;
				boolean flagDTCam = false;

				JSONObject object = JSONFactoryUtil.createJSONObject();

				JSONObject userObject = JSONFactoryUtil.createJSONObject();

				userObject.put("userName", user.getFullName());
				userObject.put("userEmail", user.getEmailAddress());
				userObject.put("userId", user.getUserId());
				userObject.put("defaultUser", user.getDefaultUser());
				userObject.put("isKeHoach", false);
				userObject.put("isThuTuc", false);
				userObject.put("isLanhDao", false);
				userObject.put("isVanThu", false);
				userObject.put("isDTND", false);
				userObject.put("isDTNDCam", false);
				userObject.put("isBoos", false);
				userObject.put("isDongBo", false);
				userObject.put("isKeToan", false);

				UserPort portDefault = UserPortLocalServiceUtil
						.findByUserId(user.getUserId());

				if (Validator.isNotNull(portDefault)) {
					DmMaritime dmMaritime = DmMaritimeLocalServiceUtil
							.getByMaritimeCode(portDefault.getPortCode());
					userObject.put("cangVuName", dmMaritime.getCityCode());
					userObject
							.put("maritimeCode", dmMaritime.getMaritimeCode());
				} else {
					userObject.put("cangVuName", "");
				}

				List<Organization> orgs = user.getOrganizations();
				for (Organization organization : orgs) {
					if (organization.getName().equalsIgnoreCase(LanguageUtil.get(themeDisplay.getLocale(),"ke-hoach"))) {
						userObject.put("isKeHoach", true);
					}
					if (organization.getName().equalsIgnoreCase(
							LanguageUtil.get(themeDisplay.getLocale(),
									"thu-tuc"))) {
						userObject.put("isThuTuc", true);
					}
					if (organization.getName().equalsIgnoreCase(
							LanguageUtil.get(themeDisplay.getLocale(),
									"lanh-dao"))) {
						userObject.put("isLanhDao", true);
					}
					if (organization.getName().equalsIgnoreCase(
							LanguageUtil.get(themeDisplay.getLocale(),
									"van-thu"))) {
						userObject.put("isVanThu", true);
					}
					if (organization.getName().equalsIgnoreCase(
							LanguageUtil.get(themeDisplay.getLocale(),
									"dong-bo"))) {
						userObject.put("isDongBo", true);
					}
					if (organization.getName().equalsIgnoreCase(
							LanguageUtil.get(themeDisplay.getLocale(),
									"ke-toan"))) {
						userObject.put("isKeToan", true);
					}
				}

				for (Role role : user.getRoles()) {

					if (role.getName().equals("DTND_ROLE")) {
						flagDT = true;
					}

					if (role.getName().trim().equals("DTND_ROLE_CAMPUCHIA")) {
						flagDTCam = true;
					}
				}

				userObject.put("isDTND", flagDT);
				userObject.put("isDTNDCam", flagDTCam);

				object.put("user", userObject);

				// build url resource
				ResourceURL resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_ship_ship");
				object.put("getVmaShip_Ship_URL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_ship_ship_count");
				object.put("countVmaShip_Ship_URL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_ship_ship_export");
				object.put("exportVmaShip_Ship_URL", resourceURL.toString());

				// ---------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("get_vma_itinerary");
				object.put("getVmaItineraryURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("get_vma_itinerary_by_itineraryno");
				object.put("getVmaItineraryByItineraryNoURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_export");
				object.put("exportVmaItineraryURL", resourceURL.toString());

				// ---------------------------------------------------------
				
				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("find_vma_itinerary_schedule");
				object.put("findVmaItineraryScheduleURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL
						.setResourceID("get_number_of_arrival_departure_in_month");
				object.put("getNumberOfArrivalDepartureInMonthURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("view_vma_itinerary_schedule_pdf");
				object.put("viewVmaItinerarySchedulePdfURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("find_temp_document");
				object.put("findTempDocumentURL", resourceURL.toString());

				// ---------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_ship_boat");
				object.put("getVmaShip_Boat_URL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_ship_boat_count");
				object.put("countVmaShip_Boat_URL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_ship_boat_export");
				object.put("exportVmaShip_Boat_URL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_ship_detail");
				object.put("getVmaShip_Detail_URL", resourceURL.toString());

				// -------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_anchorage_detail");
				object.put("getVmaScheduleAnchorage_Detail_URL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_anchorage_port");
				object.put("getVmaScheduleAnchorage_Port_URL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_anchorage_port_count");
				object.put("countVmaScheduleAnchorage_Port_URL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_anchorage_port_export");
				object.put("exportVmaScheduleAnchorage_Port_URL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_anchorage_ship");
				object.put("getVmaScheduleAnchorage_Ship_URL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_anchorage_ship_count");
				object.put("countVmaScheduleAnchorage_Ship_URL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_anchorage_ship_export");
				object.put("exportVmaScheduleAnchorage_Ship_URL",
						resourceURL.toString());

				// --------------------------------------------------------------
				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_remarks");
				object.put("getVmaItineraryRemarksURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_remarks_count");
				object.put("countVmaItineraryRemarksURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_remarks_export");
				object.put("exportVmaItineraryRemarksURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_remarks_detail");
				object.put("getVmaItineraryRemarks_Detail_URL",
						resourceURL.toString());

				// ----------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_schedule_leave");
				object.put("getVmaItinerarySchedule_Leave_URL",
						resourceURL.toString() + "&itineraryType=departure");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_schedule_leave_count");
				object.put("countVmaItinerarySchedule_Leave_URL",
						resourceURL.toString() + "&itineraryType=departure");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL
						.setResourceID("vma_itinerary_schedule_leave_export");
				object.put("exportVmaItinerarySchedule_Leave_URL",
						resourceURL.toString() + "&itineraryType=departure");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_schedule_come");
				object.put("getVmaItinerarySchedule_Come_URL",
						resourceURL.toString() + "&itineraryType=arrival");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_schedule_come_count");
				object.put("countVmaItinerarySchedule_Come_URL",
						resourceURL.toString() + "&itineraryType=arrival");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_schedule_come_export");
				object.put("exportVmaItinerarySchedule_Come_URL",
						resourceURL.toString() + "&itineraryType=arrival");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_schedule_detail");
				object.put("getVmaItinerarySchedule_Detail_URL",
						resourceURL.toString());

				// -------------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL
						.setResourceID("vma_itinerary_vma_itineraryschedule_vma_scheduleshifting");
				object.put(
						"findVmaItinerary_VmaItinerarySchedule_VmaScheduleShifting_URL",
						resourceURL.toString());

				// --------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_shifting");
				object.put("getVmaScheduleShiftingURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("view_vma_schedule_shifting_pdf");
				object.put("viewVmaScheduleShiftingPdfURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_shifting_count");
				object.put("countVmaScheduleShiftingURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_shifting_export");
				object.put("exportVmaScheduleShiftingURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_shifting_detail");
				object.put("getVmaScheduleShifting_Detail_URL",
						resourceURL.toString());

				// -------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_tugboat");
				object.put("getVmaScheduleTugboatURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_tugboat_count");
				object.put("countVmaScheduleTugboatURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_tugboat_export");
				object.put("exportVmaScheduleTugboatURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_tugboat_detail");
				object.put("getVmaScheduleTugboat_Detail_URL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_tugboat_list");
				object.put("getVmaScheduleTugboatListURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_tugboat_list_count");
				object.put("countVmaScheduleTugboatListURL",
						resourceURL.toString());

				// ------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_pilot");
				object.put("getVmaSchedulePilotURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_pilot_count");
				object.put("countVmaSchedulePilotURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_pilot_export");
				object.put("exportVmaSchedulePilotURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_pilot_detail");
				object.put("getVmaSchedulePilot_Detail_URL",
						resourceURL.toString());

				// -------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_merging");
				object.put("getVmaScheduleMergingURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_merging_count");
				object.put("countVmaScheduleMergingURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_merging_export");
				object.put("exportVmaScheduleMergingURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_merging_detail");
				object.put("getVmaScheduleMerging_Detail_URL",
						resourceURL.toString());

				// ------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_shipyard");
				object.put("getVmaScheduleShipyardURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("view_vma_schedule_shipyard_pdf");
				object.put("viewVmaScheduleShipyardPdfURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_shipyard_count");
				object.put("countVmaScheduleShipyardURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_shipyard_export");
				object.put("exportVmaScheduleShipyardURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_shipyard_detail");
				object.put("getVmaScheduleShipyard_Detail_URL",
						resourceURL.toString());

				// ------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_launching");
				object.put("getVmaScheduleLaunchingURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("view_vma_schedule_launching_pdf");
				object.put("viewVmaScheduleLaunchingPdfURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_launching_count");
				object.put("countVmaScheduleLaunchingURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_launching_export");
				object.put("exportVmaScheduleLaunchingURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_launching_detail");
				object.put("getVmaScheduleLaunching_Detail_URL",
						resourceURL.toString());

				// ---------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_testing");
				object.put("getVmaScheduleTestingURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("view_vma_schedule_testing_pdf");
				object.put("viewVmaScheduleTestingPdfURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_testing_count");
				object.put("countVmaScheduleTestingURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_testing_export");
				object.put("exportVmaScheduleTestingURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_testing_detail");
				object.put("getVmaScheduleTesting_Detail_URL",
						resourceURL.toString());

				// ----------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_security");
				object.put("getVmaScheduleSecurityURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("view_vma_schedule_security_pdf");
				object.put("viewVmaScheduleSecurityPdfURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("view_vma_schedule_evacuate_pdf");
				object.put("viewVmaScheduleEvacuatePdfURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_security_count");
				object.put("countVmaScheduleSecurityURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_security_export");
				object.put("exportVmaScheduleSecurityURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_security_detail");
				object.put("getVmaScheduleSecurity_Detail_URL",
						resourceURL.toString());

				// ----------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_xline");
				object.put("getVmaScheduleXlineURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_xline_count");
				object.put("countVmaScheduleXlineURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_xline_export");
				object.put("exportVmaScheduleXlineURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_xline_detail");
				object.put("getVmaScheduleXline_Detail_URL",
						resourceURL.toString());

				// --------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_cargolist");
				object.put("getVmaScheduleCargolistURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_cargolist_count");
				object.put("countVmaScheduleCargolistURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_cargolist_export");
				object.put("exportVmaScheduleCargolistURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_cargolist_detail");
				object.put("getVmaScheduleCargolist_Detail_URL",
						resourceURL.toString());

				// --------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_protest");
				object.put("getVmaItineraryProtestURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_protest_count");
				object.put("countVmaItineraryProtestURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_protest_export");
				object.put("exportVmaItineraryProtestURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itinerary_protest_detail");
				object.put("getVmaItineraryProtest_Detail_URL",
						resourceURL.toString());

				// --------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_xline_sailing");
				object.put("getVmaScheduleXlineSailingURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_xline_sailing_count");
				object.put("countVmaScheduleXlineSailingURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_xline_sailing_export");
				object.put("exportVmaScheduleXlineSailingURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_xline_sailing_detail");
				object.put("getVmaScheduleXlineSailing_Detail_URL",
						resourceURL.toString());

				// ---------------------------------------------------------------
				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_init_schedule");
				object.put("setVmaInitScheduleURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_notification_itinerary");
				object.put("getVmaNotifyItineraryURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_itineraryno");
				object.put("getVmaItineraryNoURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("check_active_vma");
				object.put("checkActiveVmaURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL
						.setResourceID("get_vma_itinerary_by_itineraryno_noticeshiptype");
				object.put("getVmaItinerary_ItineraryNo_NoticeShipTypeURL",
						resourceURL.toString());

				// --------------------------------------------------------------
				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_transaction_conversion");
				object.put("getVmaTransactionConversionURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_transaction_conversion_count");
				object.put("countVmaTransactionConversionURL",
						resourceURL.toString());

				// --------------------------------------------------------------
				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_transaction_type");
				object.put("getVmaTransactionTypeURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_transaction_type_count");
				object.put("countVmaTransactionTypeURL", resourceURL.toString());

				// --------------------------------------------------------------
				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_transaction_function");
				object.put("getVmaTransactionFunctionURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_transaction_function_count");
				object.put("countVmaTransactionFunctionURL",
						resourceURL.toString());

				// --------------------------------------------------------------
				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_conversion_type");
				object.put("getVmaConversionTypeURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_conversion_type_count");
				object.put("countVmaConversionTypeURL", resourceURL.toString());
				// ----------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL
						.setResourceID("vma_itinerary_schedule_by_itineraryno");
				object.put("findVmaItineraryScheduleByItineraryNoURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL
						.setResourceID("vma_itinerary_schedule_by_documentName_documentYear");
				object.put("getLastVmaItineraryScheduleURL",
						resourceURL.toString());
				// ----------------------------------------------------------------
				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL
						.setResourceID("vma_transaction_slip_by_itineraryno_documenttarycode");
				object.put(
						"findVmaTransactionSlipByItineraryNoDocumenttaryCodeURL",
						resourceURL.toString());

				// ----------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_transaction_slip");
				object.put("getVmaTransactionSlipURL", resourceURL.toString());

				// ----------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_transaction_slip_count");
				object.put("countVmaTransactionSlipURL", resourceURL.toString());
				// ----------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_transaction_slip_detail");
				object.put("getVmaTransactionSlipDetailURL",
						resourceURL.toString());

				// ----------------------------------------------------------------
				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("gen_certificate_no");
				object.put("genCertificateNoURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("gen_documentName_voy");
				object.put("genDocumentNameVoyURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("gen_documentary_code");
				object.put("genDocumentaryCodeURL", resourceURL.toString());

				// ----------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_conversion_data");
				object.put("getVmaConversionDataURL", resourceURL.toString());

				// ------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_schedule_anchorage_duration");
				object.put("getVmaScheduleAnchorageDurationURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL
						.setResourceID("vma_schedule_anchorage_payment_duration");
				object.put("getVmaScheduleAnchoragePaymentDurationURL",
						resourceURL.toString());
				// ------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL
						.setResourceID("vma_ship_certificate_by_imonumber_callsign");
				object.put("getVmaShipCertificateByImoNumberCallSignURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("sotienphainop");
				object.put("sotienphainopURL", resourceURL.toString());

				// ------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("temp_debitnote_canhbaotinhphi");
				object.put("canhbaotinhphiURL", resourceURL.toString());

				// ------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("temp_debitnote_count");
				object.put("countTempDebitNoteURL", resourceURL.toString());

				// ------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("export_vma_itinerary_schedule");
				object.put("vmaItineraryScheduleExportURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("export_vma_schedule_shifting");
				object.put("vmaScheduleShiftingExportURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("lenhDieuDong");
				object.put("lenhDieuDongURL", resourceURL.toString());

				// ------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("phieuBaoThuPhi");
				object.put("phieuBaoThuPhiURL", resourceURL.toString());
				// ------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("getPaymentData");
				object.put("getPaymentDataPublicURL", resourceURL.toString());
				// ------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_certificate");
				object.put("getVmaCertificateURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("vma_certificate_count");
				object.put("countVmaCertificateURL", resourceURL.toString());
				// ------------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("uploadBienLaiThuPhiTemplate");
				object.put("uploadBienLaiThuPhiTemplateURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("retrieveBienLaiThuPhiTemplate");
				object.put("retrieveBienLaiThuPhiTemplateURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("downloadBienLaiThuPhiTemplate");
				object.put("downloadBienLaiThuPhiTemplateURL",
						resourceURL.toString());

				// ---------------------------------------------------------------

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("countDmVmaTugboat");
				object.put("countDmVmaTugboatURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("checkHistoryTempDebitnote");
				object.put("checkHistoryTempDebitnoteURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("xacBaoTinhPhiTauBien_PTTND");
				object.put("xacBaoTinhPhiTauBien_PTTNDURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("xacBaoTinhPhiTauDichVu");
				object.put("xacBaoTinhPhiTauDichVuURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("xemBangKeTauDichVu");
				object.put("xemBangKeTauDichVuURL", resourceURL.toString()
						+ "&xemBK=1");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("xemBangKeTauChuyenTuyen");
				object.put("xemBangKeTauChuyenTuyenURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("xacBaoTinhPhiTauChuyenTuyen");
				object.put("xacBaoTinhPhiTauChuyenTuyenURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("dsChoXacBaoTinhPhi");
				object.put("dsChoXacBaoTinhPhiURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("dsTBPTauDichVu");
				object.put("dsTBPTauDichVuURL", resourceURL.toString()
						+ "&keytaudichvu=1");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("dsTBPTauChuyenTuyen");
				object.put("dsTBPTauChuyenTuyenURL", resourceURL.toString()
						+ "&keytaukhach=1");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("huyBaoTinhPhiDichVu");
				object.put("huyBaoTinhPhiDichVuURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("huyBaoTinhPhiChuyenTuyen");
				object.put("huyBaoTinhPhiChuyenTuyenURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("countDsTBPTauDichVu");
				object.put("countDsTBPTauDichVuURL", resourceURL.toString()
						+ "&keytaudichvu=1&markasdeleted=0,11");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("countDsTBPTauChuyenTuyen");
				object.put("countDsTBPTauChuyenTuyenURL", resourceURL.toString() 
						+ "&keytaukhach=1&markasdeleted=0,11");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("countChoXacBaoTinhPhi");
				object.put("countChoXacBaoTinhPhiURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("countDsTauBien");
				object.put("countDsTauBienURL", resourceURL.toString()
						+ "&flag=1&markasdeleted=0,11");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("countDsPTTND");
				object.put("countDsPTTNDURL", resourceURL.toString()
						+ "&flag=0&markasdeleted=0,11");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("dsTBPTauBien");
				object.put("dsTBPTauBienURL", resourceURL.toString()
						+ "&flag=1");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("dsTBPPTTND");
				object.put("dsTBPPTTNDURL", resourceURL.toString() + "&flag=0");

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("dsQLCongNo");
				object.put("dsQLCongNoURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("countDsQLCongNo");
				object.put("countDsQLCongNoURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("getTyGia");
				object.put("getTyGiaURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("keHoachDieuDongExport");
				object.put("keHoachDieuDongExportURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("yeuCauGiayPhepVaoRoiExport");
				object.put("yeuCauGiayPhepVaoRoiExportURl",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("dsNoiChuyen");
				object.put("dsNoiChuyenURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("exportVmaScheduleAnchoragePort");
				object.put("exportVmaScheduleAnchoragePortURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("xemBangKeTauChuyenTuyen");
				object.put("xemBangKeTauChuyenTuyenURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("findVmaTransactionSlipDetailsChild");
				object.put("findVmaTransactionSlipDetailsChildURL",
						resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("findUserPortByUserId");
				object.put("findUserPortByUserIdURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("getVmaitineraryRemarks");
				object.put("getVmaitineraryRemarks2URL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("getDmGtReportCategories");
				object.put("getDmGtReportCategoriesURL", resourceURL.toString());

				resourceURL = resourceResponse.createResourceURL(WAR_VMA_ACTION);
				resourceURL.setResourceID("getVmaScheduleMergings");
				object.put("getVmaScheduleMergingsURL", resourceURL.toString());
				// ---------------------------------------------------------------

				// build url request

				PortletURL actionUrl = PortletURLFactoryUtil.create(
						request, themeDisplay.getPortletDisplay()
								.getId(), themeDisplay.getPlid(),
						"ACTION_PHASE");
				actionUrl.setWindowState(LiferayWindowState.EXCLUSIVE);
				actionUrl.setPortletMode(LiferayPortletMode.VIEW);
				actionUrl.setParameter("javax.portlet.action", "addVmaShip");
				object.put("addVmaShipURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action", "updateVmaShip");
				object.put("updateVmaShipURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaShipDeleteStatus");
				object.put("updateVmaShipDeleteStatusURL", actionUrl.toString());

				// -----VmaShipCertificate
				actionUrl.setParameter("javax.portlet.action",
						"addVmaShipCertificate");
				object.put("addVmaShipCertificateURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaShipCertificate");
				object.put("updateVmaShipCertificateURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaShipCertificate");
				object.put("deleteVmaShipCertificateURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"addVmaShipCertificateFromResultCertificate");
				object.put("addVmaShipCertificateFromResultCertificateURL",
						actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"addVmaItineraryRemarksFromVmaShipCertificate");
				object.put("addVmaItineraryRemarksFromVmaShipCertificateURL",
						actionUrl.toString());

				// -----VmaItinerary

				actionUrl.setParameter("javax.portlet.action",
						"addVmaItinerary");
				object.put("addVmaItineraryURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaItinerary");
				object.put("updateVmaItineraryURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateShipPosition");
				object.put("updateShipPositionURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaItinerary");
				object.put("deleteVmaItineraryURL", actionUrl.toString());

				// -----VmaItinerarySchedule
				actionUrl.setParameter("javax.portlet.action",
						"addVmaItinerarySchedule");
				object.put("addVmaItineraryScheduleURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaItinerarySchedule");
				object.put("updateVmaItineraryScheduleURL",
						actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaItinerarySchedule");
				object.put("deleteVmaItineraryScheduleURL",
						actionUrl.toString());

				// -----VmaScheduleShifting
				actionUrl.setParameter("javax.portlet.action",
						"addVmaScheduleShifting");
				object.put("addVmaScheduleShiftingURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaScheduleShifting");
				object.put("updateVmaScheduleShiftingURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaScheduleShifting");
				object.put("deleteVmaScheduleShiftingURL", actionUrl.toString());

				// -----VmaScheduleTugboat
				actionUrl.setParameter("javax.portlet.action",
						"addVmaScheduleTugboat");
				object.put("addVmaScheduleTugboatURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaScheduleTugboat");
				object.put("updateVmaScheduleTugboatURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaScheduleTugboat");
				object.put("deleteVmaScheduleTugboatURL", actionUrl.toString());

				// -----VmaScheduleTugboatLis
				/*
				 * actionUrl.setParameter("javax.portlet.action",
				 * "addVmaScheduleTugboatList");
				 * object.put("addVmaScheduleTugboatListURL",
				 * actionUrl.toString());
				 * 
				 * actionUrl.setParameter("javax.portlet.action",
				 * "updateVmaScheduleTugboatList");
				 * object.put("updateVmaScheduleTugboatListURL",
				 * actionUrl.toString());
				 */

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaScheduleTugboatList");
				object.put("deleteVmaScheduleTugboatListURL",
						actionUrl.toString());

				// -----VmaSchedulePilot
				actionUrl.setParameter("javax.portlet.action",
						"addVmaSchedulePilot");
				object.put("addVmaSchedulePilotURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaSchedulePilot");
				object.put("updateVmaSchedulePilotURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaSchedulePilot");
				object.put("deleteVmaSchedulePilotURL", actionUrl.toString());

				// -----VmaSchedulePilotList
				/*
				 * actionUrl.setParameter("javax.portlet.action",
				 * "addVmaSchedulePilotList");
				 * object.put("addVmaSchedulePilotListURL",
				 * actionUrl.toString());
				 * 
				 * actionUrl.setParameter("javax.portlet.action",
				 * "updateVmaSchedulePilotList");
				 * object.put("updateVmaSchedulePilotListURL",
				 * actionUrl.toString());
				 */

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaSchedulePilotList");
				object.put("deleteVmaSchedulePilotListURL",
						actionUrl.toString());

				// -----VmaScheduleAnchorage
				actionUrl.setParameter("javax.portlet.action",
						"addVmaScheduleAnchorage");
				object.put("addVmaScheduleAnchorageURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaScheduleAnchorage");
				object.put("updateVmaScheduleAnchorageURL",
						actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaScheduleAnchorage");
				object.put("deleteVmaScheduleAnchorageURL",
						actionUrl.toString());

				// -----VmaScheduleSecurity
				actionUrl.setParameter("javax.portlet.action",
						"addVmaScheduleSecurity");
				object.put("addVmaScheduleSecurityURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaScheduleSecurity");
				object.put("updateVmaScheduleSecurityURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaScheduleSecurity");
				object.put("deleteVmaScheduleSecurityURL", actionUrl.toString());

				// -----VmaScheduleShipyard
				actionUrl.setParameter("javax.portlet.action",
						"addVmaScheduleShipyard");
				object.put("addVmaScheduleShipyardURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaScheduleShipyard");
				object.put("updateVmaScheduleShipyardURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaScheduleShipyard");
				object.put("deleteVmaScheduleShipyardURL", actionUrl.toString());

				// -----VmaScheduleLaunching
				actionUrl.setParameter("javax.portlet.action",
						"addVmaScheduleLaunching");
				object.put("addVmaScheduleLaunchingURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaScheduleLaunching");
				object.put("updateVmaScheduleLaunchingURL",
						actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaScheduleLaunching");
				object.put("deleteVmaScheduleLaunchingURL",
						actionUrl.toString());

				// -----VmaScheduleTesting
				actionUrl.setParameter("javax.portlet.action",
						"addVmaScheduleTesting");
				object.put("addVmaScheduleTestingURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaScheduleTesting");
				object.put("updateVmaScheduleTestingURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaScheduleTesting");
				object.put("deleteVmaScheduleTestingURL", actionUrl.toString());

				// -----VmaScheduleCargolist
				actionUrl.setParameter("javax.portlet.action",
						"addVmaScheduleCargolist");
				object.put("addVmaScheduleCargolistURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaScheduleCargolist");
				object.put("updateVmaScheduleCargolistURL",
						actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaScheduleCargolist");
				object.put("deleteVmaScheduleCargolistURL",
						actionUrl.toString());

				actionUrl
						.setParameter("javax.portlet.action",
								"updateVmaScheduleCargolist_MakePayment_DocumentaryCode");
				object.put(
						"updateVmaScheduleCargolist_MakePayment_DocumentaryCodeURL",
						actionUrl.toString());

				actionUrl
						.setParameter("javax.portlet.action",
								"updateVmaScheduleCargolistByDocumentaryCode_ItineraryNo");
				object.put(
						"updateVmaScheduleCargolistByDocumentaryCode_ItineraryNoURL",
						actionUrl.toString());

				// VmaScheduleMerging
				actionUrl
				.setParameter("javax.portlet.action",
						"updateVmaScheduleMerging_MakePayment_DocumentaryCode");
				object.put(
						"updateVmaScheduleMerging_MakePayment_DocumentaryCodeURL",
				actionUrl.toString());

				// Chuyen lanh dao Ky so
				
				actionUrl.setParameter("javax.portlet.action","chuyen_lanh_dao_ky_so");
				object.put("chuyen_lanh_dao_ky_so_URL", actionUrl.toString());
				
				
				actionUrl.setParameter("javax.portlet.action","lanh_dao_ky_so_HSM");
				object.put("lanh_dao_ky_so_HSM_URL", actionUrl.toString());
				
			
				actionUrl.setParameter("javax.portlet.action","lanh_dao_tu_choi_ky_so");
				object.put("lanh_dao_tu_choi_ky_so_URL", actionUrl.toString());
				
				// ---------------------------------------------------------
				
				// -----VmaItineraryRemarks
				actionUrl.setParameter("javax.portlet.action",
						"addVmaItineraryRemarks");
				object.put("addVmaItineraryRemarksURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaItineraryRemarks");
				object.put("updateVmaItineraryRemarksURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaItineraryRemarks");
				object.put("deleteVmaItineraryRemarksURL", actionUrl.toString());

				// -----VmaItineraryProtest
				actionUrl.setParameter("javax.portlet.action",
						"addVmaItineraryProtest");
				object.put("addVmaItineraryProtestURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaItineraryProtest");
				object.put("updateVmaItineraryProtestURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaItineraryProtest");
				object.put("deleteVmaItineraryProtestURL", actionUrl.toString());

				actionUrl
						.setParameter("javax.portlet.action",
								"updateVmaItineraryProtest_MakePayment_DocumentaryCode");
				object.put(
						"updateVmaItineraryProtest_MakePayment_DocumentaryCodeURL",
						actionUrl.toString());

				actionUrl
						.setParameter("javax.portlet.action",
								"updateVmaItineraryProtestByDocumentaryCode_ItineraryNo");
				object.put(
						"updateVmaItineraryProtestByDocumentaryCode_ItineraryNoURL",
						actionUrl.toString());

				// -----VmaScheduleXline
				actionUrl.setParameter("javax.portlet.action",
						"addVmaScheduleXline");
				object.put("addVmaScheduleXlineURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaScheduleXline");
				object.put("updateVmaScheduleXlineURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaScheduleXline");
				object.put("deleteVmaScheduleXlineURL", actionUrl.toString());

				// -----VmaScheduleXlineSailing
				actionUrl.setParameter("javax.portlet.action",
						"addVmaScheduleXlineSailing");
				object.put("addVmaScheduleXlineSailingURL",
						actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaScheduleXlineSailing");
				object.put("updateVmaScheduleXlineSailingURL",
						actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaScheduleXlineSailing");
				object.put("deleteVmaScheduleXlineSailingURL",
						actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action", "doCharge");
				object.put("doChargeURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"addVmaTransactionSlip_VmaTransactionSlipDetail");
				object.put("addVmaTransactionSlip_VmaTransactionSlipDetailURL",
						actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaTransactionSlip_VmaTransactionSlipDetail");
				object.put(
						"updateVmaTransactionSlip_VmaTransactionSlipDetailURL",
						actionUrl.toString());
				actionUrl.setParameter("javax.portlet.action",
						"updateVmaTransactionSlip_ERecipt");
				object.put(
						"updateVmaTransactionSlip_EReciptURL",
						actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"autoFillCargolist");
				object.put("autoFillCargolistURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"addScheduleMerging");
				object.put("addScheduleMergingURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateScheduleMerging");
				object.put("updateScheduleMergingURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"deleteScheduleMerging");
				object.put("deleteScheduleMergingURL", actionUrl.toString());

				// ==================================================================
				actionUrl.setParameter("javax.portlet.action",
						"napquy_dunodauky");
				object.put("napquy_dunodaukyURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action", "rutquy");
				object.put("rutquyURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action", "noptien");
				object.put("noptienURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action", "huytinhphi");
				object.put("huytinhphiURL", actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"xacnhanthanhtoan");
				object.put("xacnhanthanhtoanURL", actionUrl.toString());

				actionUrl
						.setParameter("javax.portlet.action", "xacnhantinhphi");
				object.put("xacnhantinhphiURL", actionUrl.toString());

				// ========================================
				// vma_schedule_anchorage_port
				actionUrl.setParameter("javax.portlet.action",
						"updateVmaScheduleAnchoragePort");
				object.put("updateVmaScheduleAnchoragePortURL",
						actionUrl.toString());

				actionUrl
						.setParameter("javax.portlet.action",
								"updateVmaScheduleAnchoragePortByDocumentaryCode_ItineraryNo");
				object.put(
						"updateVmaScheduleAnchoragePortByDocumentaryCode_ItineraryNoURL",
						actionUrl.toString());

				// ----tempdebitnote
				/*
				 * actionUrl.setParameter("javax.portlet.action",
				 * "updateTempDebitNote"); object.put("updateTempDebitNoteURL",
				 * actionUrl.toString());
				 */

				// ======================= Vma_Ship ============
				actionUrl.setParameter("javax.portlet.action",
						"deleteVmaShip_Ship");
				object.put("deleteVmaShip_Ship_URL", actionUrl.toString());

				// ======================= VmaCertificate - DmCertificate
				// ============
				actionUrl.setParameter("javax.portlet.action",
						"updateVmaCertificate_DmCertificate");
				object.put("updateVmaCertificate_DmCertificate_URL",
						actionUrl.toString());

				actionUrl.setParameter("javax.portlet.action",
						"updateVmaScheduleMergings");
				object.put("updateVmaScheduleMergingsURL", actionUrl.toString());

				return writeJSON(resourceRequest, resourceResponse, object);

			} else if (resourceID.equals("vma_ship_ship")) {
				JSONObject result = VmaShipUtils.findVmaShip(resourceRequest,
						resourceResponse);
				return writeJSON(resourceRequest, resourceResponse, result);
			} else if (resourceID.equals("vma_ship_ship_count")) {
				long total = VmaShipUtils.countVmaShip(resourceRequest,
						resourceResponse);
				return writeJSON(resourceRequest, resourceResponse, total);
			} else if (resourceID.equals("vma_ship_ship_export")) {
				VmaShipUtils.exportVmaShip(resourceRequest, resourceResponse);
			} else if (resourceID.equals("vma_ship_boat")) {
				JSONObject result = VmaShipUtils.findVmaShip(resourceRequest,
						resourceResponse);
				return writeJSON(resourceRequest, resourceResponse, result);

			} else if (resourceID.equals("vma_ship_boat_count")) {
				long total = VmaShipUtils.countVmaShip(resourceRequest,
						resourceResponse);
				return writeJSON(resourceRequest, resourceResponse, total);
			} else if (resourceID.equals("vma_ship_boat_export")) {
				VmaShipUtils.exportVmaShip(resourceRequest, resourceResponse);
			} else if (resourceID.equals("vma_ship_detail")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaShipUtils.findVmaShipById(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_anchorage_port")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleAnchorageUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_anchorage_port_count")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleAnchorageUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_anchorage_port_export")) {

				VmaScheduleAnchorageUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_itinerary_remarks")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryRemarksUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_itinerary_remarks_count")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryRemarksUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_itinerary_remarks_export")) {

				VmaItineraryRemarksUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_itinerary_remarks_detail")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryRemarksUtils.findById(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_anchorage_detail")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleAnchorageUtils.findById(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_itinerary_schedule_leave")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_itinerary_schedule_leave_count")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_itinerary_schedule_leave_export")) {

				VmaItineraryScheduleUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_itinerary_schedule_come")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils.doFind(resourceRequest,
								resourceResponse));

			} else if (resourceID.equals("vma_itinerary_schedule_come_count")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_itinerary_schedule_come_export")) {

				VmaItineraryScheduleUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_itinerary_schedule_detail")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils.findById(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_shifting")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleShiftingUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("view_vma_schedule_shifting_pdf")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleShiftingUtils.viewPDF(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_shifting_count")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleShiftingUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_shifting_export")) {
				VmaScheduleShiftingUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_schedule_shifting_detail")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleShiftingUtils.findById(resourceRequest,
								resourceResponse));

			} else if (resourceID.equals("vma_schedule_anchorage_ship")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleAnchorageUtils.doFind(resourceRequest,
								resourceResponse));

			} else if (resourceID.equals("vma_schedule_anchorage_ship_count")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleAnchorageUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_anchorage_ship_export")) {
				VmaScheduleAnchorageUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_schedule_tugboat")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleTugboatUtils.doFind(resourceRequest,
								resourceResponse));

			} else if (resourceID.equals("vma_schedule_tugboat_count")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleTugboatUtils.doCount(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("vma_schedule_tugboat_list")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleTugboatListUtils.doFind(resourceRequest,
								resourceResponse));

			} else if (resourceID.equals("vma_schedule_tugboat_list_count")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleTugboatListUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_tugboat_export")) {
				VmaScheduleTugboatUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_schedule_tugboat_detail")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleTugboatUtils.findById(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_pilot")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaSchedulePilotUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_pilot_count")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaSchedulePilotUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_pilot_export")) {

				VmaSchedulePilotUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_schedule_pilot_detail")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaSchedulePilotUtils.findById(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_merging")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleMergingUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_merging_count")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleMergingUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_merging_export")) {

				VmaScheduleMergingUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_schedule_merging_detail")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleMergingUtils.findById(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_shipyard")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleShipyardUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("view_vma_schedule_shipyard_pdf")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleShipyardUtils.viewPDF(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_shipyard_count")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleShipyardUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_shipyard_export")) {

				VmaScheduleShipyardUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_schedule_shipyard_detail")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleShipyardUtils.findById(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_launching")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleLaunchingUtils.doFind(resourceRequest,
								resourceResponse));

			} else if (resourceID.equals("view_vma_schedule_launching_pdf")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleLaunchingUtils.viewPDF(resourceRequest,
								resourceResponse));

			} else if (resourceID.equals("vma_schedule_launching_count")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleLaunchingUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_launching_export")) {

				VmaScheduleLaunchingUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_schedule_launching_detail")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleLaunchingUtils.findById(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_testing")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleTestingUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("view_vma_schedule_testing_pdf")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleTestingUtils.viewPDF(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_testing_count")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleTestingUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_testing_export")) {

				VmaScheduleTestingUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_schedule_testing_detail")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleTestingUtils.findById(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_security")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleSecurityUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("view_vma_schedule_security_pdf")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleSecurityUtils.viewPDF(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("view_vma_schedule_evacuate_pdf")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleSecurityUtils.viewPDF(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_security_count")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleSecurityUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_security_export")) {

				VmaScheduleSecurityUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_schedule_security_detail")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleSecurityUtils.findById(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_xline")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleXlineUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_xline_count")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleXlineUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_schedule_xline_export")) {

				VmaScheduleXlineUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_itinerary_protest")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryProtestUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_itinerary_protest_count")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryProtestUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_itinerary_protest_export")) {

				VmaItineraryProtestUtils.doExport(resourceRequest,
						resourceResponse);
			} else if (resourceID.equals("vma_itinerary_protest_detail")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryProtestUtils.findById(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_init_schedule")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils.initItinerarySchedule(
								resourceRequest, resourceResponse));
			} else if (resourceID.equals("vma_notification_itinerary")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils.getNotifyItinerary(
								resourceRequest, resourceResponse));
			} else if (resourceID.equals("vma_itineraryno")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryUtils.findByDocumentName_DocumentYear(
								resourceRequest, resourceResponse));
			} else if (resourceID.equals("check_active_vma")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryUtils.checkActiveVma(themeDisplay,
								resourceRequest, resourceResponse));
			} else if (resourceID
					.equals("get_vma_itinerary_by_itineraryno_noticeshiptype")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils
								.findByItineraryNo_NoticeShipType(
										resourceRequest, resourceResponse));
			} else if (resourceID.equals("get_vma_itinerary")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("get_vma_itinerary_by_itineraryno")) {

				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryUtils.findVmaItineraryByItineraryNo(
								resourceRequest, resourceResponse));
			} else if (resourceID.equals("vma_itinerary_export")) {

				VmaItineraryUtils.exportVmaItinerary(resourceRequest,
						resourceResponse);
			}

			else if (resourceID.equals("find_vma_itinerary_schedule")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryUtils.doFindVmaItinerary(resourceRequest,
								resourceResponse));
			} else if (resourceID
					.equals("get_number_of_arrival_departure_in_month")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils
								.getNumberOfArrivalDepartureInMonth(
										resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("view_vma_itinerary_schedule_pdf")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils.viewPDF(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("find_temp_document")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryUtils.doFindTempDocument(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("vma_transaction_conversion")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionConversionUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_transaction_conversion_count")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionConversionUtils.doCount(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("vma_transaction_type")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionTypeUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_transaction_type_count")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionTypeUtils.doCount(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("vma_transaction_function")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionFunctionUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_transaction_function_count")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionFunctionUtils.doCount(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("vma_conversion_type")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaConversionTypeUtils.doFind(resourceRequest,
								resourceResponse));
			} else if (resourceID.equals("vma_conversion_type_count")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaConversionTypeUtils.doCount(resourceRequest,
								resourceResponse));
			} else if (resourceID
					.equals("vma_itinerary_schedule_by_itineraryno")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils.doFind2(resourceRequest,
								resourceResponse));
			} else if (resourceID
					.equals("vma_itinerary_schedule_by_documentName_documentYear")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils.getLastVmaItinerarySchedule(
								resourceRequest, resourceResponse));
			} else if (resourceID
					.equals("vma_transaction_slip_by_itineraryno_documenttarycode")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionSlipUtils
								.findByItineraryNo_DocumentaryCode(
										resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("vma_transaction_slip")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionSlipUtils.doFind(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("vma_transaction_slip_count")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionSlipUtils.doCount(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("vma_transaction_slip_detail")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionSlipUtils
								.findByItineraryNo_DocumentaryCode(
										resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("gen_certificate_no")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils.genCertificateNo(
								resourceRequest, resourceResponse));
			} else if (resourceID.equals("gen_documentName_voy")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils.genDocumentNameVoy(
								resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("gen_documentary_code")) {
//				return writeJSON(resourceRequest, resourceResponse,
//						VmaTransactionSlipUtils.genDocumentaryCode(
//								resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("vma_conversion_data")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionConversionUtils.getConversionData(
								resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("vma_schedule_cargolist")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleCargolistUtils.doFind(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("vma_schedule_cargolist_export")) {
				VmaScheduleCargolistUtils.doExport(resourceRequest,
						resourceResponse);
			}

			else if (resourceID.equals("vma_schedule_cargolist_count")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleCargolistUtils.doCount(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("vma_schedule_cargolist_detail")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleCargolistUtils.findById(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("vma_schedule_anchorage_duration")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleAnchorageUtils
								.getVmaScheduleAnchorageDuration(
										resourceRequest, resourceResponse));
			}

			else if (resourceID
					.equals("vma_schedule_anchorage_payment_duration")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleAnchorageUtils.getAnchoragePaymentDuration(
								resourceRequest, resourceResponse));
			}

			else if (resourceID
					.equals("vma_ship_certificate_by_imonumber_callsign")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaShipCertificateUtils
								.getVmaShipCertificateByImoNumber_CallSign(
										resourceRequest, resourceResponse));
			}

			else if (resourceID
					.equals("vma_itinerary_vma_itineraryschedule_vma_scheduleshifting")) {
				return writeJSON(
						resourceRequest,
						resourceResponse,
						VmaItineraryScheduleUtils
								.findVmaItinerary_VmaItinerarySchedule_VmaScheduleShifting(
										resourceRequest, resourceResponse));
			} else if (resourceID.equals("sotienphainop")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaPaymentAdvanceUtils.sotienconphaitra(
								resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("temp_debitnote_canhbaotinhphi")) {
				return writeJSON(resourceRequest, resourceResponse,
						KeToanAction.canhbaotinhphiTempDebitNote(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("temp_debitnote_count")) {
				return writeJSON(resourceRequest, resourceResponse,
						KeToanAction.countTempDebitNote(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("export_vma_itinerary_schedule")) {
				VmaItineraryScheduleUtils vmaItineraryScheduleUtils = new VmaItineraryScheduleUtils();
				return writeJSON(resourceRequest, resourceResponse,
						vmaItineraryScheduleUtils.export2excel(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("export_vma_schedule_shifting")) {
				VmaScheduleShiftingUtils vmaScheduleShiftingUtils = new VmaScheduleShiftingUtils();
				return writeJSON(resourceRequest, resourceResponse,
						vmaScheduleShiftingUtils.export2excel(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("lenhDieuDong")) {
				VmaScheduleShiftingUtils vmaScheduleShiftingUtils = new VmaScheduleShiftingUtils();
				return writeJSON(resourceRequest, resourceResponse,
						vmaScheduleShiftingUtils.lenhDieuDong(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("phieuBaoThuPhi")) {
				VmaItineraryUtils vmaItineraryUtils = new VmaItineraryUtils();
				return writeJSON(resourceRequest, resourceResponse,
						vmaItineraryUtils.phieuBaoThuPhi(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("getPaymentData")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.getPaymentData(themeDisplay, resourceRequest));
			}

			else if (resourceID.equals("vma_schedule_xline_sailing")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleXlineSailingUtils.doFind(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("vma_schedule_xline_sailing_detail")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleXlineSailingUtils.findById(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("vma_schedule_xline_sailing_count")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleXlineSailingUtils.doCount(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("vma_schedule_xline_sailing_export")) {
				VmaScheduleXlineSailingUtils.doExport(resourceRequest,
						resourceResponse);
			}

			else if (resourceID.equals("vma_certificate")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaCertificateUtils.doFind(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("vma_certificate_count")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaCertificateUtils.doCount(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("uploadBienLaiThuPhiTemplate")) {
				VmaTransactionSlipUtils vmaTransactionSlipUtils = new VmaTransactionSlipUtils();
				return writeJSON(resourceRequest, resourceResponse,
						vmaTransactionSlipUtils.uploadNewTemplate(
								resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("downloadBienLaiThuPhiTemplate")) {
				VmaTransactionSlipUtils vmaTransactionSlipUtils = new VmaTransactionSlipUtils();
				return writeJSON(resourceRequest, resourceResponse,
						vmaTransactionSlipUtils.downloadTemplate(
								resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("retrieveBienLaiThuPhiTemplate")) {
				VmaTransactionSlipUtils vmaTransactionSlipUtils = new VmaTransactionSlipUtils();
				return writeJSON(resourceRequest, resourceResponse,
						vmaTransactionSlipUtils
								.retrieveTemplate(resourceRequest));
			}

			else if (resourceID.equals("countDmVmaTugboat")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTugboatUtils.doCount(resourceRequest));
			}

			else if (resourceID.equals("checkHistoryTempDebitnote")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.checkHistoryTempDebitnote(resourceRequest));
			}

			else if (resourceID.equals("xacBaoTinhPhiTauBien_PTTND")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.xacBaoTinhPhiTauBien_PTTND(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("xacBaoTinhPhiTauDichVu")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.xacBaoTinhPhiTauDichVu(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("xemBangKeTauDichVu")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.xacBaoTinhPhiTauDichVu(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("xacBaoTinhPhiTauChuyenTuyen")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.xacBaoTinhPhiTauChuyenTuyen(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("choXacBaoTinhPhi")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryUtils.dsChoXacBaoTinhPhi(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("dsTBPTauDichVu")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionSlipUtils.dsTBPTauDichVu_ChuyenTuyen(
								resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("dsTBPTauChuyenTuyen")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionSlipUtils.dsTBPTauDichVu_ChuyenTuyen(
								resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("huyBaoTinhPhiDichVu")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.huyBaoTinhPhi(themeDisplay, resourceRequest));
			}

			else if (resourceID.equals("huyBaoTinhPhiChuyenTuyen")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.huyBaoTinhPhi_ChuyenTuyen(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("countChoXacBaoTinhPhi")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryUtils.countChoXacBaoTinhPhi(
								resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("countDsTBPTauDichVu")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionSlipUtils
								.countDsTBPTauDichVu_ChuyenTuyen(
										resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("countDsTBPTauChuyenTuyen")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionSlipUtils
								.countDsTBPTauDichVu_ChuyenTuyen(
										resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("countDsTauBien")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.countGetPaymentData(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("countDsPTTND")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.countGetPaymentData(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("dsTBPTauBien")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.getPaymentDataTauBien_PTTND(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("dsTBPPTTND")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.getPaymentDataTauBien_PTTND(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("dsQLCongNo")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionSlipUtils.dsQLCongNo(resourceRequest,
								resourceResponse));
			}

			else if (resourceID.equals("countDsQLCongNo")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionSlipUtils.countDsQLCongNo(
								resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("getTyGia")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.getTyGia(resourceRequest));
			}

			else if (resourceID.equals("keHoachDieuDongExport")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryScheduleUtils.keHoachDieuDongExport(
								themeDisplay, resourceRequest));
			}

			else if (resourceID.equals("yeuCauGiayPhepVaoRoiExport")) {
				VmaItineraryScheduleUtils vmaItineraryScheduleUtils = new VmaItineraryScheduleUtils();
				return writeJSON(resourceRequest, resourceResponse,
						vmaItineraryScheduleUtils.yeuCauGiayPhepVaoRoi(
								themeDisplay, resourceRequest));
			}

			else if (resourceID.equals("dsNoiChuyen")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryUtils.noiChuyen(resourceRequest));
			}

			else if (resourceID.equals("exportVmaScheduleAnchoragePort")) {
				VmaItineraryScheduleUtils vmaItineraryScheduleUtils = new VmaItineraryScheduleUtils();
				return writeJSON(resourceRequest, resourceResponse,
						vmaItineraryScheduleUtils.exportKHDD(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("xemBangKeTauChuyenTuyen")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.xemBangKeTauChuyenTuyen(themeDisplay,
								resourceRequest));
			}

			else if (resourceID.equals("findVmaTransactionSlipDetailsChild")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaTransactionSlipUtils
								.findVmaTransactionSlipDetailsChild(
										resourceRequest, resourceResponse));
			}

			else if (resourceID.equals("findUserPortByUserId")) {
				return writeJSON(resourceRequest, resourceResponse,
						UserPortUtils.findByUserId(themeDisplay));
			}

			else if (resourceID.equals("getVmaitineraryRemarks")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaItineraryRemarksUtils
								.getVmaitineraryRemarks(resourceRequest));
			}

			else if (resourceID.equals("getDmGtReportCategories")) {
				return writeJSON(resourceRequest, resourceResponse,
						VMAUtils.getDmGtReportCategories(resourceRequest));
			}

			else if (resourceID.equals("getVmaScheduleMergings")) {
				return writeJSON(resourceRequest, resourceResponse,
						VmaScheduleMergingUtils.findVmaScheduleMergings(
								resourceRequest, resourceResponse));
			}

			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		} catch (Exception e) {

			log.error(e.getMessage());

		}
		return super.serveResource(resourceRequest, resourceResponse);
	}

	public ResponseEntity<?> processAction(ActionRequest actionRequest,
			ActionResponse actionResponse)  {
		try {

			// actionRequest.setCharacterEncoding("UTF-8");

			String actionName = ParamUtil.getString(actionRequest,
					ActionRequest.ACTION_NAME);

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getSession().getAttribute(WebKeys.THEME_DISPLAY);

			/*
			 * UploadPortletRequest requestUpload = PortalUtil
			 * .getUploadPortletRequest(actionRequest);
			 */

			System.out.println("=================================>>>> params "
					+ actionRequest.getParameterMap().size() + "|"
					+ actionRequest.getCharacterEncoding());
			if (actionRequest.getParameterMap() != null) {
				for (Map.Entry<String, String[]> entry : actionRequest
						.getParameterMap().entrySet()) {
					System.out.println(entry.getKey() + " | "
							+ entry.getValue()[0]);
					/*
					 * String value = entry.getValue()[0]; value =
					 * URLEncoder.encode(value, "ISO-8859-1"); value =
					 * URLDecoder.decode(value, "UTF-8");
					 * System.out.println(entry.getKey() + " | " + value);
					 * actionRequest.getParameterMap().put(entry.getKey(), new
					 * String[]{value});
					 */
				}
			}

			if (actionName.equals("addVmaShip")) {

				return writeJSON(actionRequest, actionResponse,
						VmaShipUtils.addVmaShip(themeDisplay, actionRequest));

			}

			else if (actionName.equals("updateVmaShip")) {

				return writeJSON(actionRequest, actionResponse,
						VmaShipUtils.updateVmaShip(themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaShipDeleteStatusURL")) {
				// TODO
			}

			// -----VmaShipCertificate
			else if (actionName.equals("addVmaShipCertificate")) {
				VmaShipCertificateUtils.addVmaShipCertificate(themeDisplay,
						actionRequest);
			}

			else if (actionName.equals("updateVmaShipCertificate")) {
				VmaShipCertificateUtils.updateVmaShipCertificate(themeDisplay,
						actionRequest);
			}

			else if (actionName.equals("deleteVmaShipCertificate")) {
				return writeJSON(actionRequest, actionResponse,
						VmaShipCertificateUtils.deleteVmaShipCertificate(
								themeDisplay, actionRequest));
			}

			else if (actionName
					.equals("addVmaShipCertificateFromResultCertificate")) {
				return writeJSON(actionRequest, actionResponse,
						VmaShipCertificateUtils
								.addVmaShipCertificateFromResultCertificate(
										themeDisplay, actionRequest));
			}

			else if (actionName
					.equals("addVmaItineraryRemarksFromVmaShipCertificate")) {
				return writeJSON(actionRequest, actionResponse,
						VmaShipCertificateUtils.addVmaItineraryRemarks(
								themeDisplay, actionRequest));
			}
			// -----VmaItinerary

			else if (actionName.equals("addVmaItinerary")) {
				return writeJSON(actionRequest, actionResponse,
						VmaItineraryUtils.addVmaItinerary(themeDisplay,
								actionRequest));
			}

			else if (actionName.equals("updateVmaItinerary")) {
				return writeJSON(actionRequest, actionResponse,
						VmaItineraryUtils.updateVmaItinerary(themeDisplay,
								actionRequest));
			}

			else if (actionName.equals("updateShipPosition")) {
				return writeJSON(actionRequest, actionResponse,
						VmaItineraryUtils.updateShipPosition(themeDisplay,
								actionRequest));
			}

			else if (actionName.equals("deleteVmaItinerary")) {
				return writeJSON(actionRequest, actionResponse,
						VmaItineraryUtils.deleteVmaItinerary(themeDisplay,
								actionRequest));
			}
			// -----VmaItinerarySchedule
			else if (actionName.equals("addVmaItinerarySchedule")) {
				return writeJSON(actionRequest, actionResponse,
						VmaItineraryUtils.addVmaItinerary_VmaItinerarySchedule(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaItinerarySchedule")) {
				return writeJSON(actionRequest, actionResponse,
						VmaItineraryScheduleUtils
								.updateVmaItinerarySchedule_VmaItinerary(
										themeDisplay, actionRequest));
			}

			else if (actionName.equals("deleteVmaItinerarySchedule")) {
				return writeJSON(actionRequest, actionResponse,
						VmaItineraryScheduleUtils.deleteVmaItinerarySchedule(
								themeDisplay, actionRequest));
			}

			// -----VmaScheduleShifting
			else if (actionName.equals("addVmaScheduleShifting")) {

				return writeJSON(actionRequest, actionResponse,
						VmaItineraryUtils.addVmaItinerary_VmaScheduleShifting(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaScheduleShifting")) {

				return writeJSON(actionRequest, actionResponse,
						VmaScheduleShiftingUtils
								.updateVmaScheduleShifting_VmaItinerary(
										themeDisplay, actionRequest));
			}

			else if (actionName.equals("deleteVmaScheduleShifting")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleShiftingUtils.deleteVmaScheduleShifting(
								themeDisplay, actionRequest));
			}
			// -----VmaScheduleTugboat
			else if (actionName.equals("addVmaScheduleTugboat")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleTugboatUtils
								.addVmaScheduleTugboat_VmaScheduleTugboatList(
										themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaScheduleTugboat")) {
				return writeJSON(
						actionRequest,
						actionResponse,
						VmaScheduleTugboatUtils
								.updateVmaScheduleTugboat_VmaScheduleTugboatList(
										themeDisplay, actionRequest));
			}

			else if (actionName.equals("deleteVmaScheduleTugboat")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleTugboatUtils.deleteVmaScheduleTugboat(
								themeDisplay, actionRequest));
			}

			// -----VmaScheduleTugboatLis
			/*
			 * else if (actionName.equals("addVmaScheduleTugboatList")) {
			 * VmaScheduleTugboatListUtils.addVmaScheduleTugboatList(
			 * themeDisplay, actionRequest); }
			 * 
			 * else if (actionName.equals("updateVmaScheduleTugboatList")) {
			 * VmaScheduleTugboatListUtils.updateVmaScheduleTugboatList(
			 * themeDisplay, actionRequest); }
			 */

			else if (actionName.equals("deleteVmaScheduleTugboatList")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleTugboatListUtils
								.deleteVmaScheduleTugboatList(themeDisplay,
										actionRequest));
			}

			// -----VmaSchedulePilot
			else if (actionName.equals("addVmaSchedulePilot")) {
				/*
				 * VmaSchedulePilotUtils.addVmaSchedulePilot(themeDisplay,
				 * actionRequest);
				 */

				return writeJSON(actionRequest, actionResponse,
						VmaSchedulePilotUtils
								.addVmaSchedulePilot_VmaSchedulePilotList(
										themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaSchedulePilot")) {
				/*
				 * VmaSchedulePilotUtils.updateVmaSchedulePilot(themeDisplay,
				 * actionRequest);
				 */
				return writeJSON(actionRequest, actionResponse,
						VmaSchedulePilotUtils
								.updateVmaSchedulePilot_VmaSchedulePilotList(
										themeDisplay, actionRequest));
			}

			else if (actionName.equals("deleteVmaSchedulePilot")) {
				return writeJSON(actionRequest, actionResponse,
						VmaSchedulePilotUtils.deleteVmaSchedulePilot(
								themeDisplay, actionRequest));
			}

			// -----VmaSchedulePilotList
			/*
			 * else if (actionName.equals("addVmaSchedulePilotList")) {
			 * VmaSchedulePilotListUtils.addVmaSchedulePilotList(themeDisplay,
			 * actionRequest); }
			 * 
			 * else if (actionName.equals("updateVmaSchedulePilotList")) {
			 * VmaSchedulePilotListUtils.updateVmaSchedulePilotList(
			 * themeDisplay, actionRequest); }
			 */

			else if (actionName.equals("deleteVmaSchedulePilotList")) {
				return writeJSON(actionRequest, actionResponse,
						VmaSchedulePilotListUtils.deleteVmaSchedulePilotList(
								themeDisplay, actionRequest));
			}

			// -----VmaScheduleAnchorag
			else if (actionName.equals("addVmaScheduleAnchorage")) {

				return writeJSON(actionRequest, actionResponse,
						VmaItineraryUtils.addVmaItinerary_VmaScheduleAnchorage(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaScheduleAnchorage")) {

				return writeJSON(actionRequest, actionResponse,
						VmaScheduleAnchorageUtils
								.updateVmaScheduleAnchorage_VmaItinerary(
										themeDisplay, actionRequest));

			}

			else if (actionName.equals("deleteVmaScheduleAnchorage")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleAnchorageUtils.deleteVmaScheduleAnchorage(
								themeDisplay, actionRequest));
			}

			// -----VmaScheduleSecurity
			else if (actionName.equals("addVmaScheduleSecurity")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleSecurityUtils.addVmaScheduleSecurity(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaScheduleSecurity")) {

				return writeJSON(actionRequest, actionResponse,
						VmaScheduleSecurityUtils.updateVmaScheduleSecurity(
								themeDisplay, actionRequest));

			}

			else if (actionName.equals("deleteVmaScheduleSecurity")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleSecurityUtils.deleteVmaScheduleSecurity(
								themeDisplay, actionRequest));
			}

			// -----VmaScheduleShipyard
			else if (actionName.equals("addVmaScheduleShipyard")) {

				return writeJSON(actionRequest, actionResponse,
						VmaScheduleShipyardUtils.addVmaScheduleShipyard(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaScheduleShipyard")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleShipyardUtils.updateVmaScheduleShipyard(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("deleteVmaScheduleShipyard")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleShipyardUtils.deleteVmaScheduleShipyard(
								themeDisplay, actionRequest));
			}

			// -----VmaScheduleLaunching
			else if (actionName.equals("addVmaScheduleLaunching")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleLaunchingUtils.addVmaScheduleLaunching(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaScheduleLaunching")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleLaunchingUtils.updateVmaScheduleLaunching(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("deleteVmaScheduleLaunching")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleLaunchingUtils.deleteVmaScheduleLaunching(
								themeDisplay, actionRequest));
			}

			// -----VmaScheduleTesting
			else if (actionName.equals("addVmaScheduleTesting")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleTestingUtils.addVmaScheduleTesting(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaScheduleTesting")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleTestingUtils.updateVmaScheduleTesting(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("deleteVmaScheduleTesting")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleTestingUtils.deleteVmaScheduleTesting(
								themeDisplay, actionRequest));
			}

			// -----VmaScheduleCargolist
			else if (actionName.equals("addVmaScheduleCargolist")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleCargolistUtils.addVmaScheduleCargolist(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaScheduleCargolist")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleCargolistUtils.updateVmaScheduleCargolist(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("deleteVmaScheduleCargolist")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleCargolistUtils.deleteVmaScheduleCargolist(
								themeDisplay, actionRequest));
			}

			// -----VmaItineraryRemarks
			else if (actionName.equals("addVmaItineraryRemarks")) {
				return writeJSON(actionRequest, actionResponse,
						VmaItineraryRemarksUtils.addVmaItineraryRemarks(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaItineraryRemarks")) {
				return writeJSON(actionRequest, actionResponse,
						VmaItineraryRemarksUtils.updateVmaItineraryRemarks(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("deleteVmaItineraryRemarks")) {
				return writeJSON(actionRequest, actionResponse,
						VmaItineraryRemarksUtils.deleteVmaItineraryRemarks(
								themeDisplay, actionRequest));
			}

			// -----VmaItineraryProtest
			else if (actionName.equals("addVmaItineraryProtest")) {
				return writeJSON(actionRequest, actionResponse,
						VmaItineraryProtestUtils.addVmaItineraryProtest(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaItineraryProtest")) {
				return writeJSON(actionRequest, actionResponse,
						VmaItineraryProtestUtils.updateVmaItineraryProtest(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("deleteVmaItineraryProtest")) {
				return writeJSON(actionRequest, actionResponse,
						VmaItineraryProtestUtils.deleteVmaItineraryProtest(
								themeDisplay, actionRequest));
			}

			else if (actionName
					.equals("updateVmaItineraryProtest_MakePayment_DocumentaryCode")) {
				return writeJSON(
						actionRequest,
						actionResponse,
						VmaItineraryProtestUtils
								.updateVmaItineraryProtest_MakePayment_DocumentaryCode(
										themeDisplay, actionRequest));
			}

			else if (actionName
					.equals("updateVmaItineraryProtestByDocumentaryCode_ItineraryNo")) {
				return writeJSON(
						actionRequest,
						actionResponse,
						VmaItineraryProtestUtils
								.updateVmaItineraryProtestByDocumentaryCode_ItineraryNo(
										themeDisplay, actionRequest));
			}

			// Lanh dao ky so
			else if (actionName.equals("chuyen_lanh_dao_ky_so")) {
				return writeJSON(actionRequest, actionResponse,
						VMAUtils.updateChuyenLanhDaoKySo(themeDisplay, actionRequest));
			}
			else if (actionName.equals("lanh_dao_ky_so_HSM")) {
				return writeJSON(actionRequest, actionResponse,
						VMAUtils.updateLanhDaoKySoHSM(themeDisplay, actionRequest));
			}
			else if (actionName.equals("lanh_dao_tu_choi_ky_so")) {
				return writeJSON(actionRequest, actionResponse,
						VMAUtils.updateLanhDaoTuChoiKySo(themeDisplay, actionRequest));
			}
			// -----VmaScheduleXline
			else if (actionName.equals("addVmaScheduleXline")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleXlineUtils.addVmaScheduleXline(themeDisplay,
								actionRequest));
			}

			else if (actionName.equals("updateVmaScheduleXline")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleXlineUtils.updateVmaScheduleXline(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("deleteVmaScheduleXline")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleXlineUtils.deleteVmaScheduleXline(
								themeDisplay, actionRequest));
			}

			// -----VmaScheduleXlineSailing
			else if (actionName.equals("addVmaScheduleXlineSailing")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleXlineSailingUtils
								.addVmaScheduleXlineSailing(themeDisplay,
										actionRequest));
			}

			else if (actionName.equals("updateVmaScheduleXlineSailing")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleXlineSailingUtils
								.updateVmaScheduleXlineSailing(themeDisplay,
										actionRequest));
			}

			else if (actionName.equals("deleteVmaScheduleXlineSailing")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleXlineSailingUtils
								.deleteVmaScheduleXlineSailing(themeDisplay,
										actionRequest));
			}

			else if (actionName.equals("doCharge")) {
				return writeJSON(actionRequest, actionResponse,
						VmaTransactionSlipUtils.doCharge(themeDisplay,
								actionRequest, actionResponse));
			}

			else if (actionName
					.equals("addVmaTransactionSlip_VmaTransactionSlipDetail")) {
				return writeJSON(
						actionRequest,
						actionResponse,
						VmaTransactionSlipUtils
								.addVmaTransactionSlip_VmaTransactionSlipDetail(
										themeDisplay, actionRequest,
										actionResponse));
			}

			else if (actionName
					.equals("updateVmaTransactionSlip_VmaTransactionSlipDetail")) {
				return writeJSON(
						actionRequest,
						actionResponse,
						VmaTransactionSlipUtils
								.updateVmaTransactionSlip_VmaTransactionSlipDetail(
										themeDisplay, actionRequest,
										actionResponse));
			}
			
			else if (actionName
					.equals("updateVmaTransactionSlip_ERecipt")) {
				return writeJSON(
						actionRequest,
						actionResponse,
						VmaTransactionSlipUtils
								.updateVmaTransactionSlip_ERecipt(
										themeDisplay, actionRequest,
										actionResponse));
			}

			else if (actionName.equals("autoFillCargolist")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleCargolistUtils.autoFillCargolist(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("addScheduleMerging")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleMergingUtils.addVmaScheduleMerging(
								themeDisplay, actionRequest));
			} else if (actionName.equals("updateScheduleMerging")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleMergingUtils.updateVmaScheduleMerging(
								themeDisplay, actionRequest));
			} else if (actionName.equals("deleteScheduleMerging")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleMergingUtils.deleteVmaScheduleMerging(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("napquy_dunodauky")) {
				return writeJSON(actionRequest, actionResponse,
						VmaPaymentAdvanceUtils.napquy_dunodauky(themeDisplay,
								actionRequest));
			} else if (actionName.equals("rutquy")) {
				return writeJSON(actionRequest, actionResponse,
						VmaPaymentAdvanceUtils.rutquy(themeDisplay,
								actionRequest));
			}

			else if (actionName.equals("huytinhphi")) {
				return writeJSON(actionRequest, actionResponse,
						VmaPaymentAdvanceUtils.huytinhphi(themeDisplay,
								actionRequest));
			}

			else if (actionName.equals("xacnhanthanhtoan")) {
				return writeJSON(actionRequest, actionResponse,
						VmaPaymentAdvanceUtils.xacnhanthanhtoan(themeDisplay,
								actionRequest));
			}

			else if (actionName.equals("xacnhantinhphi")) {
				return writeJSON(actionRequest, actionResponse,
						VmaPaymentAdvanceUtils.xacnhantinhphi(themeDisplay,
								actionRequest));
			}

			else if (actionName.equals("noptien")) {
				return writeJSON(actionRequest, actionResponse,
						VmaPaymentAdvanceUtils.noptien(themeDisplay,
								actionRequest));
			}

			else if (actionName.equals("updateVmaScheduleAnchoragePort")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleAnchorageUtils
								.updateVmaScheduleAnchoragePort(themeDisplay,
										actionRequest));
			}

			else if (actionName
					.equals("updateVmaScheduleAnchoragePortByDocumentaryCode_ItineraryNo")) {
				return writeJSON(
						actionRequest,
						actionResponse,
						VmaScheduleAnchorageUtils
								.updateVmaScheduleAnchoragePortByDocumentaryCode_ItineraryNo(
										themeDisplay, actionRequest));
			}

			else if (actionName
					.equals("updateVmaScheduleCargolist_MakePayment_DocumentaryCode")) {
				return writeJSON(
						actionRequest,
						actionResponse,
						VmaScheduleCargolistUtils
								.updateVmaScheduleCargolist_MakePayment_DocumentaryCode(
										themeDisplay, actionRequest));
			}

			else if (actionName
					.equals("updateVmaScheduleCargolistByDocumentaryCode_ItineraryNo")) {
				return writeJSON(
						actionRequest,
						actionResponse,
						VmaScheduleCargolistUtils
								.updateVmaScheduleCargolistByDocumentaryCode_ItineraryNo(
										themeDisplay, actionRequest));
			}
			
			else if (actionName
					.equals("updateVmaScheduleMerging_MakePayment_DocumentaryCode")) {
				return writeJSON(
						actionRequest,
						actionResponse,
						VmaScheduleMergingUtils
								.updateVmaScheduleMerging_MakePayment_DocumentaryCode(
										themeDisplay, actionRequest));
			}

			else if (actionName.equals("deleteVmaShip_Ship")) {
				return writeJSON(actionRequest, actionResponse,
						VmaShipUtils.deleteVmaShip(themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaCertificate_DmCertificate")) {
				return writeJSON(actionRequest, actionResponse,
						VmaCertificateUtils.updateVmaCertificate_DmCertificate(
								themeDisplay, actionRequest));
			}

			else if (actionName.equals("updateVmaScheduleMergings")) {
				return writeJSON(actionRequest, actionResponse,
						VmaScheduleMergingUtils.updateVmaScheduleMergings(
								themeDisplay, actionRequest));
			}

			else {

				super.processAction(actionRequest, actionResponse);

			}
		} catch (Exception e) {

		}
		return super.processAction(actionRequest, actionResponse);
	}
}
