package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleAnchorageLocalServiceUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import java.util.Date;
import java.util.ArrayList; 
import java.util.List;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import org.json.*;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.kernel.util.OrderByComparator;
import org.springframework.beans.factory.annotation.Autowired;
import com.fds.nsw.kernel.exception.PortalException;
import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaScheduleCargolistFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaScheduleCargolistPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import vn.gt.portlet.phuongtien.VMAUtils;

@Slf4j @Service
public class VmaScheduleCargolistLocalServiceImpl { 
@Autowired VmaScheduleCargolistPersistenceImpl persistence;
@Autowired VmaScheduleCargolistFinderImpl finder;


	public VmaScheduleCargolist createVmaScheduleCargolist(long id) {
		return persistence.create(id);
	}

	public VmaScheduleCargolist deleteVmaScheduleCargolist(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaScheduleCargolist deleteVmaScheduleCargolist(VmaScheduleCargolist VmaScheduleCargolist)
		throws SystemException {
		return persistence.remove(VmaScheduleCargolist);
	}

	public VmaScheduleCargolist fetchVmaScheduleCargolist(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaScheduleCargolist getVmaScheduleCargolist(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaScheduleCargolist> getVmaScheduleCargolists(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaScheduleCargolistsCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaScheduleCargolist updateVmaScheduleCargolist(VmaScheduleCargolist VmaScheduleCargolist,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaScheduleCargolist, merge);
	}
public VmaScheduleCargolist addVmaScheduleCargolist(
        VmaScheduleCargolist vmaScheduleCargolist) throws SystemException {

    long id = CounterLocalServiceUtil.increment(VmaScheduleCargolist.class
            .getName());

    vmaScheduleCargolist.setId(id);

    long sequenceNo = CounterLocalServiceUtil.increment(
            "VMA_SCHEDULE_CARGOLIST_SEQUENCE_NO", 1);

    vmaScheduleCargolist.setSequenceNo((int) sequenceNo);

    vmaScheduleCargolist.setModifiedDate(new Date());

    // VMAUtils.formatUnicode(vmaScheduleCargolist);

    return persistence.updateImpl(vmaScheduleCargolist,
            false);

}

    public VmaScheduleCargolist delete(long id) throws SystemException,
            NoSuchVmaScheduleCargolistException {
        return persistence.remove(id);
    }

    public VmaScheduleCargolist updateVmaScheduleCargolist(
            VmaScheduleCargolist vmaScheduleCargolist) throws SystemException {
        vmaScheduleCargolist.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaScheduleCargolist);
        return persistence.updateImpl(vmaScheduleCargolist,
                false);

    }

    public List<VmaScheduleCargolist> findByItineraryNo(String itineraryNo)
            throws SystemException {
        return persistence.findByitineraryNo(itineraryNo);
    }

    public int countByItineraryNo(String itineraryNo) throws SystemException {
        return persistence.countByitineraryNo(itineraryNo);
    }

    public VmaScheduleCargolist findByItineraryNo_NoticeShipType_SequenceNo(
            String itineraryNo, int noticeShipType, int sequenceNo)
            throws SystemException, NoSuchVmaScheduleCargolistException {
        return persistence
                .findByitineraryNo_noticeShipType_sequenceNo(itineraryNo,
                        noticeShipType, sequenceNo);
    }

    public int countByItineraryNo_NoticeShipType_SequenceNo(String itineraryNo,
                                                            int noticeShipType, int sequenceNo) throws SystemException {
        return persistence
                .countByitineraryNo_noticeShipType_sequenceNo(itineraryNo,
                        noticeShipType, sequenceNo);
    }

    public List<VmaScheduleCargolist> findByItineraryNo_NoticeShipType(
            String itineraryNo, int noticeShipType) throws SystemException {
        return persistence
                .findByitineraryNo_noticeShipType(itineraryNo, noticeShipType);
    }

    public int countByItineraryNo_NoticeShipType(String itineraryNo,
                                                 int noticeShipType) throws SystemException {
        return persistence
                .countByitineraryNo_noticeShipType(itineraryNo, noticeShipType);
    }

    public JSONObject findVmaScheduleCargolist(String searchQuery,
                                               String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaScheduleCargolist(countQuery);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        List<VmaScheduleCargolist> vmaScheduleCargolists = finder
                .findVmaScheduleCargolist(VmaScheduleCargolist.class,
                        "VmaScheduleCargolist", searchQuery, start, end);

        if (vmaScheduleCargolists != null) {
            for (VmaScheduleCargolist vmaScheduleCargolist : vmaScheduleCargolists) {

                JSONObject object = VMAUtils.object2Json(vmaScheduleCargolist,
                        VmaScheduleCargolist.class, new String[] {
                                "timeOfArrival", "timeOfDeparture",
                                "portHarbourCode", "portWharfCode",
                                "cargoType", "portRegionCode", "cargoCategory",
                                "cargoCode" });

                long itineraryScheduleId = vmaScheduleCargolist
                        .getItineraryScheduleId();
                if (itineraryScheduleId > 0) {
                    VmaScheduleAnchorage vmaScheduleAnchorage = VmaScheduleAnchorageLocalServiceUtil
                            .findByitineraryScheduleId(itineraryScheduleId);
                    if (vmaScheduleAnchorage != null) {
                        object.put("anchorage", VMAUtils.object2Json(
                                vmaScheduleCargolist,
                                VmaScheduleAnchorage.class));
                        String anchoringPortWharfCode = vmaScheduleAnchorage.getAnchoringPortWharfCode();
                        Integer makePayment = vmaScheduleCargolist.getMakePayment();

                        try {
                            // Canh bao: Tinh phi hay khong
                            if (makePayment == 1) {
                                object.put("Verify_MAKEPAYMENT", "Đã ghi phiếu thu");
                            } else {
                                object.put("Verify_MAKEPAYMENT", "Chưa ghi phiếu thu");
                            }


                            DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil.getByPortWharfCode(anchoringPortWharfCode);
                            if (dmPortWharf !=null && dmPortWharf.getPortWharfPayment()==1){
                                if (Validator.isNull(vmaScheduleCargolist.getCargoCategory())
                                        || (vmaScheduleCargolist.getCargoCategory().equalsIgnoreCase("C6_VC"))
                                        || (vmaScheduleCargolist.getCargoCategory().equalsIgnoreCase("C3_VC"))
                                        || (vmaScheduleCargolist.getCargoCategory().equalsIgnoreCase("C2_VC"))
                                        || (vmaScheduleCargolist.getCargoCategory().equalsIgnoreCase("C1_VC"))
                                        || (vmaScheduleCargolist.getCargoCategory().equalsIgnoreCase("VC"))
                                        || (vmaScheduleCargolist.getCargoCategory().equalsIgnoreCase("C5"))){

                                    object.put("Verify_PAYMENT", "Không tính phí");
                                    object.put("portWharfPayment", 0);
                                } else {
                                    object.put("Verify_PAYMENT", "Cảng vụ tính phí");
                                    object.put("portWharfPayment", dmPortWharf.getPortWharfPayment());
                                }

                            } else {
                                object.put("Verify_PAYMENT", "Không tính phí");
                                object.put("portWharfPayment", dmPortWharf.getPortWharfPayment());
                            }

                        } catch (Exception e) {
                            // nothing to do
                            object.put("Verify_PAYMENT", "Không tính phí");
                            object.put("portWharfPayment", 0);
                        }
                    } else {
                        object.put("anchorage",
                                JSONFactoryUtil.createJSONObject());
                        object.put("Verify_PAYMENT", "Không tính phí");
                        object.put("portWharfPayment", 0);
                    }
                }

                data.put(object);
            }
        }

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaScheduleCargolist(String sql) throws SystemException {

        return finder.countVmaScheduleCargolist(sql);

    }

    public List<VmaScheduleCargolist> findByitineraryScheduleId(
            long itineraryScheduleId) {
        try {
            return persistence
                    .findByitineraryScheduleId(itineraryScheduleId);
        } catch (Exception e) {
            return new ArrayList<VmaScheduleCargolist>();
        }
    }

    public List<VmaScheduleCargolist> findByItineraryNo_documentaryCode(
            String itineraryNo, String documentaryCode) {
        try {
            return persistence
                    .findByitineraryNo_documentaryCode(itineraryNo,
                            documentaryCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public VmaScheduleCargolist findByScheduleAnchorageId(long scheduleAnchorageId) {
        try {
            return persistence.findByF_scheduleAnchorageId(scheduleAnchorageId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}

