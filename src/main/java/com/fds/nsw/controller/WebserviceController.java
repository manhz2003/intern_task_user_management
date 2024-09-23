package com.fds.nsw.controller;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.gt.dao.noticeandmessage.service.VmaItineraryLocalServiceUtil;
import vn.gt.tichhop.message.BusinessUtils;

@RestController
@RequestMapping("/TichHopGiaoThong-portlet/services")
@CrossOrigin(origins = "*")
@Slf4j
public class WebserviceController {

    @GetMapping(value = "/test")
    public String test() {
        try {
            VmaItineraryLocalServiceUtil.getModelMau11BT(
                    "19", "2021-08-02", "2021-08-04");
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "oke";
    }

    @PostMapping(value = "/MessageAndResponseImpl", produces = MediaType.TEXT_XML_VALUE, consumes = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<?> nswCallBgt(@RequestBody String requestMessage) {
        BusinessUtils businessUtils = new BusinessUtils();
        requestMessage = businessUtils.decodeUrlXml(requestMessage);
        log.info("=====Thong=tin=nhan=tu=HQMC===" + requestMessage);
        String xmlReturn = businessUtils.receiveMessage(requestMessage);
        log.info("============Tra=du=lieu=hai=quan================" + xmlReturn);

        return ResponseEntity.status(200).body(businessUtils.endoceUrlXml(xmlReturn));
    }
}
