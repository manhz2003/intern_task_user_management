package com.fds.nsw.controller;

import com.fds.nsw.liferay.core.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempDocumentFinderImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.gt.portlet.baocao.BaoCaoPMNVAction;
import vn.gt.portlet.baocao.BaoCaoUpgradeAction;
import vn.gt.portlet.baocaotichhop.BaoCaoTichHopAction;
import vn.gt.portlet.danhmuc.DanhMucAction;
import vn.gt.portlet.danhmuc.DanhMucRiengAction;
import vn.gt.portlet.kehoach.KeHoachAction;
import vn.gt.portlet.kehoach.KeToanAction;
import vn.gt.portlet.monitor.MonitorPortlet;
import vn.gt.portlet.nguoidung.QuanLyNguoiDungAction;
import vn.gt.portlet.phuongtien.VMAAction;
import vn.gt.portlet.thongtintau.ThongTinTauAction;
import vn.gt.portlet.thutuc.LanhDaoAction;
import vn.gt.portlet.thutuc.PayGateAction;
import vn.gt.portlet.thutuc.VanThuAction;
import vn.gt.portlet.tracuu.TraCuuAction;
import vn.gt.portlet.user.ConfigUserAction;

import static com.fds.nsw.liferay.core.Constants.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Slf4j
public class AllApiController {

	@Autowired
	KeToanAction keToanAction;

	@Autowired
	KeHoachAction keHoachAction;

	@Autowired
	PayGateAction payGateAction;

	@Autowired
	VanThuAction vanThuAction;

	@Autowired
	LanhDaoAction lanhDaoAction;

	@Autowired
	ConfigUserAction configUserAction;

	@Autowired
	BaoCaoUpgradeAction baoCaoUpgradeAction;

	@Autowired
	BaoCaoPMNVAction baoCaoPMNVAction;

	@Autowired
	ThongTinTauAction thongTinTauAction;

	@Autowired
	DanhMucAction danhMucAction;

	@Autowired
	DanhMucRiengAction danhMucRiengAction;

	@Autowired
	TraCuuAction traCuuAction;

	@Autowired
	BaoCaoTichHopAction baoCaoTichHopAction;

	@Autowired
	QuanLyNguoiDungAction quanLyNguoiDungAction;

	@Autowired
	MonitorPortlet monitorAction;

	@Autowired
	VMAAction vmaAction;

	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<?> getBak(HttpServletRequest request) {
		ResourceRequest resourceRequest = new ResourceRequest(request);
		ResourceResponse resourceResponse = new ResourceResponse(request);

		String p_p_id = ParamUtil.getString(request, "p_p_id", "");
		String p_p_resource_id = ParamUtil.getString(request, "p_p_resource_id", "");

		if (p_p_id.contains(WAR_KETOAN_ACTION)) {
			return keToanAction.serveResource(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_KEHOACH_ACTION)) {
			return keHoachAction.serveResource(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_PAYGATE_ACTION)) {
			return payGateAction.serveResource(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_VANTHU_ACTION)) {
			return vanThuAction.serveResource(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_LANHDAO_ACTION)) {
			return lanhDaoAction.serveResource(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_CONFIGUSER_ACTION)) {
			return configUserAction.serveResource(resourceRequest, resourceResponse );
		} else if (p_p_id.contains(WAR_BAOCAO_ACTION)) {
			return baoCaoUpgradeAction.serveResource(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_BAOCAOPMNV_ACTION)) {
			return baoCaoPMNVAction.serveResource(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_THONGTINTAU_ACTION)) {
			return thongTinTauAction.serveResource(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_DANHMUC_ACTION)) {
			return danhMucAction.serveResource(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_DANHMUCRIENG_ACTION)) {
			return danhMucRiengAction.serveResource(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_TRACUU_ACTION)) {
			return traCuuAction.serveResource(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_BAOCAOTICHHOP_ACTION)) {
			return baoCaoTichHopAction.serveResource(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_QUANLYNGUOIDUNG_ACTION)) {
//			return quanLyNguoiDungAction.serveResource(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_MONITOR_ACTION)) {
//			return monitorAction.serveResource(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_VMA_ACTION)) {
			return vmaAction.serveResource(resourceRequest, resourceResponse);
		}

		log.warn("P_P_ID: " + p_p_id + " not matching");
		return ResponseEntity.status(200).body("P_P_ID: " + p_p_id + " not matching");
	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> postBak(HttpServletRequest request) {
		ActionRequest resourceRequest = new ActionRequest(request);
		ActionResponse resourceResponse = new ActionResponse(request);

		String p_p_id = ParamUtil.getString(request, "p_p_id", "");

		if (p_p_id.contains(WAR_KETOAN_ACTION)) {
			return keToanAction.processAction(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_KEHOACH_ACTION)) {
			return keHoachAction.processAction(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_PAYGATE_ACTION)) {
			return payGateAction.processAction(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_VANTHU_ACTION)) {
			return vanThuAction.processAction(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_LANHDAO_ACTION)) {
			return lanhDaoAction.processAction(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_CONFIGUSER_ACTION)) {
			return configUserAction.processAction(resourceRequest, resourceResponse );
		} else if (p_p_id.contains(WAR_BAOCAO_ACTION)) {
			return baoCaoUpgradeAction.processAction(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_BAOCAOPMNV_ACTION)) {
			return baoCaoPMNVAction.processAction(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_THONGTINTAU_ACTION)) {
			return thongTinTauAction.processAction(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_DANHMUC_ACTION)) {
			return danhMucAction.processAction(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_DANHMUCRIENG_ACTION)) {
			return danhMucRiengAction.processAction(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_TRACUU_ACTION)) {
			return traCuuAction.processAction(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_BAOCAOTICHHOP_ACTION)) {
			return baoCaoTichHopAction.processAction(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_QUANLYNGUOIDUNG_ACTION)) {
			return quanLyNguoiDungAction.processAction(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_MONITOR_ACTION)) {
			return monitorAction.processAction(resourceRequest, resourceResponse);
		} else if (p_p_id.contains(WAR_VMA_ACTION)) {
			return vmaAction.processAction(resourceRequest, resourceResponse);
		}

		log.warn("P_P_ID: " + p_p_id + " not matching");
		return ResponseEntity.status(200).body("P_P_ID: " + p_p_id + " not matching");
	}
}
