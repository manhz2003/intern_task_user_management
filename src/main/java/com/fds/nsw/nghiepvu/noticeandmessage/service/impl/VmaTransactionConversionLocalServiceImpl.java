package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaConversionTypeLocalServiceUtil;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import vn.gt.portlet.phuongtien.VMAUtils;
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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.VmaTransactionConversionFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaTransactionConversionPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaTransactionConversionLocalServiceImpl { 
@Autowired VmaTransactionConversionPersistenceImpl persistence;
@Autowired VmaTransactionConversionFinderImpl finder;


	public VmaTransactionConversion createVmaTransactionConversion(long id) {
		return persistence.create(id);
	}

	public VmaTransactionConversion deleteVmaTransactionConversion(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaTransactionConversion deleteVmaTransactionConversion(VmaTransactionConversion VmaTransactionConversion)
		throws SystemException {
		return persistence.remove(VmaTransactionConversion);
	}

	public VmaTransactionConversion fetchVmaTransactionConversion(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaTransactionConversion getVmaTransactionConversion(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaTransactionConversion> getVmaTransactionConversions(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaTransactionConversionsCount() throws SystemException {
		return persistence.countAll();
	}


	public VmaTransactionConversion updateVmaTransactionConversion(VmaTransactionConversion VmaTransactionConversion,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaTransactionConversion, merge);
	}
public VmaTransactionConversion addVmaTransactionConversion(
        VmaTransactionConversion vmaTransactionConversion)
        throws SystemException {
    long id = CounterLocalServiceUtil.increment(VmaTransactionConversion.class
            .getName());
    vmaTransactionConversion.setId(id);
    vmaTransactionConversion.setModifiedDate(new Date());
    // VMAUtils.formatUnicode(vmaTransactionConversion);
    return persistence.updateImpl(
            vmaTransactionConversion, false);
}

    public VmaTransactionConversion updateVmaTransactionConversion(
            VmaTransactionConversion vmaTransactionConversion)
            throws SystemException {
        vmaTransactionConversion.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaTransactionConversion);
        return persistence.updateImpl(
                vmaTransactionConversion, false);
    }

    public VmaTransactionConversion delete(long id) throws SystemException,
            NoSuchVmaTransactionConversionException {
        return persistence.remove(id);
    }

    public JSONObject findTransactionConversionData(VmaShip vmaShip) {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        String rate = StringPool.BLANK;
        String originValue = StringPool.BLANK;
        String unit = StringPool.BLANK;
        double value = 0;

        List<VmaConversionType> conversionTypes = VmaConversionTypeLocalServiceUtil
                .findAll();

        VmaTransactionConversion vmaTransactionConversion = null;

        if (conversionTypes != null
                && vmaShip != null
                && (Validator.isNotNull(vmaShip.getShipTypeCode()) || Validator
                .isNotNull(vmaShip.getShipTypeMT()))) {
            if (vmaShip.getGt().longValue() > 0) {

                VmaConversionType vmaConversionType = null;
                String functionType = StringPool.BLANK;
                for (VmaConversionType temp : conversionTypes) {
                    functionType = temp.getFunctionType();
                    if (functionType.equals("GT")) {
                        vmaConversionType = temp;
                        break;
                    }
                }

                if (vmaConversionType != null) {

                    if (Validator.isNotNull(vmaShip.getShipTypeCode())) {

                        try {
                            vmaTransactionConversion = persistence
                                    .fetchByshipTypeCode_functionType(
                                            vmaShip.getShipTypeCode(),
                                            functionType);
                            rate = String.valueOf(vmaTransactionConversion
                                    .getConversionRate());
                            originValue = String.valueOf(vmaShip.getGt());
                            unit = vmaConversionType.getConversionUnit();
                            value = vmaShip.getGt().longValue()
                                    * vmaTransactionConversion
                                    .getConversionRate() / 100;
                        } catch (Exception e) {
                            log.error(e.getMessage());
                        }
                    }

                    if (Validator.isNotNull(vmaShip.getShipTypeMT())
                            && vmaTransactionConversion == null) {
                        try {
                            vmaTransactionConversion = persistence
                                    .fetchByshipTypeMT_functionType(
                                            vmaShip.getShipTypeMT(),
                                            functionType);
                            rate = String.valueOf(vmaTransactionConversion
                                    .getConversionRate());
                            originValue = String.valueOf(vmaShip.getGt());
                            unit = vmaConversionType.getConversionUnit();
                            value = vmaShip.getGt().longValue()
                                    * vmaTransactionConversion
                                    .getConversionRate() / 100;
                        } catch (Exception e) {
                            log.error(e.getMessage());
                        }
                    }
                }
            }

            if (value <= 0) {
                double seat_lie = 0;
                String seat_lie_rate = StringPool.BLANK;
                String seat_lie_originValue = StringPool.BLANK;
                String seat_lie_unit = StringPool.BLANK;

                for (VmaConversionType vmaConversionType : conversionTypes) {
                    String functionType = vmaConversionType.getFunctionType();
                    if (functionType.equals("GT")) {
                        continue;
                    } else if (functionType.equals("DWT")) {
                        if (vmaShip.getDwt().longValue() <= 0) {
                            continue;
                        } else {
                            if (Validator.isNotNull(vmaShip.getShipTypeCode())) {
                                try {
                                    vmaTransactionConversion = persistence
                                            .fetchByshipTypeCode_functionType(
                                                    vmaShip.getShipTypeCode(),
                                                    functionType);

                                    rate = String
                                            .valueOf(vmaTransactionConversion
                                                    .getConversionRate());
                                    originValue = String.valueOf(vmaShip
                                            .getDwt());

                                    double temp = vmaShip.getDwt().longValue()
                                            * vmaTransactionConversion
                                            .getConversionRate() / 100;
                                    if (temp > value) {

                                        unit = vmaConversionType
                                                .getConversionUnit();
                                        value = temp;
                                    }
                                    break;

                                } catch (Exception e) {
                                    log.error(e.getMessage());
                                }
                            }

                            if (Validator.isNotNull(vmaShip.getShipTypeMT())
                                    && vmaTransactionConversion == null) {
                                try {
                                    vmaTransactionConversion = persistence
                                            .fetchByshipTypeMT_functionType(
                                                    vmaShip.getShipTypeMT(),
                                                    functionType);

                                    rate = String
                                            .valueOf(vmaTransactionConversion
                                                    .getConversionRate());
                                    originValue = String.valueOf(vmaShip
                                            .getDwt());

                                    double temp = vmaShip.getDwt().longValue()
                                            * vmaTransactionConversion
                                            .getConversionRate() / 100;
                                    if (temp > value) {

                                        unit = vmaConversionType
                                                .getConversionUnit();
                                        value = temp;
                                    }
                                    break;

                                } catch (Exception e) {
                                    log.error(e.getMessage());
                                }
                            }
                        }

                    } else if (functionType.equals("POWER")) {

                        if (vmaShip.getPower() <= 0) {
                            continue;
                        } else {
                            if (Validator.isNotNull(vmaShip.getShipTypeCode())) {
                                try {
                                    vmaTransactionConversion = persistence
                                            .fetchByshipTypeCode_functionType(
                                                    vmaShip.getShipTypeCode(),
                                                    functionType);

                                    rate = String
                                            .valueOf(vmaTransactionConversion
                                                    .getConversionRate());
                                    originValue = String.valueOf(vmaShip
                                            .getPower());

                                    double temp = vmaShip.getPower()
                                            * vmaTransactionConversion
                                            .getConversionRate() / 100;
                                    if (temp > value) {

                                        unit = vmaConversionType
                                                .getConversionUnit();
                                        value = temp;
                                    }
                                    break;

                                } catch (Exception e) {
                                    log.error(e.getMessage());
                                }
                            }

                            if (Validator.isNotNull(vmaShip.getShipTypeMT())
                                    && vmaTransactionConversion == null) {

                                try {
                                    vmaTransactionConversion = persistence
                                            .fetchByshipTypeMT_functionType(
                                                    vmaShip.getShipTypeMT(),
                                                    functionType);

                                    rate = String
                                            .valueOf(vmaTransactionConversion
                                                    .getConversionRate());
                                    originValue = String.valueOf(vmaShip
                                            .getPower());

                                    double temp = vmaShip.getPower()
                                            * vmaTransactionConversion
                                            .getConversionRate() / 100;
                                    if (temp > value) {

                                        unit = vmaConversionType
                                                .getConversionUnit();
                                        value = temp;
                                    }
                                    break;

                                } catch (Exception e) {
                                    log.error(e.getMessage());
                                }
                            }
                        }
                    } else if (functionType.equals("SEAT")) {

                        if (vmaShip.getSeat() <= 0) {
                            continue;
                        } else {
                            if (Validator.isNotNull(vmaShip.getShipTypeCode())) {
                                try {
                                    vmaTransactionConversion = persistence
                                            .fetchByshipTypeCode_functionType(
                                                    vmaShip.getShipTypeCode(),
                                                    functionType);
                                    double temp = vmaShip.getSeat()
                                            * vmaTransactionConversion
                                            .getConversionRate() / 100;
                                    if (temp > 0) {
                                        seat_lie_originValue += originValue
                                                + "-";
                                        seat_lie_rate += rate + "-";
                                        seat_lie_unit += vmaConversionType
                                                .getConversionUnit() + "-";
                                        seat_lie += temp;
                                    }
                                    break;

                                } catch (Exception e) {
                                    log.error(e.getMessage());
                                }
                            }

                            if (Validator.isNotNull(vmaShip.getShipTypeMT())
                                    && vmaTransactionConversion == null) {

                                try {
                                    vmaTransactionConversion = persistence
                                            .fetchByshipTypeMT_functionType(
                                                    vmaShip.getShipTypeMT(),
                                                    functionType);
                                    double temp = vmaShip.getSeat()
                                            * vmaTransactionConversion
                                            .getConversionRate() / 100;
                                    if (temp > 0) {
                                        seat_lie_originValue += originValue
                                                + "-";
                                        seat_lie_rate += rate + "-";
                                        seat_lie_unit += vmaConversionType
                                                .getConversionUnit() + "-";
                                        seat_lie += temp;
                                    }
                                    break;

                                } catch (Exception e) {
                                    log.error(e.getMessage());
                                }
                            }
                        }

                    } else if (functionType.equals("LIE")) {

                        if (Validator.isNotNull(vmaShip.getShipTypeCode())) {
                            try {
                                vmaTransactionConversion = persistence
                                        .fetchByshipTypeCode_functionType(
                                                vmaShip.getShipTypeCode(),
                                                functionType);
                                double temp = vmaShip.getLies()
                                        * vmaTransactionConversion
                                        .getConversionRate() / 100;
                                if (temp > 0) {
                                    seat_lie_originValue += originValue + "-";
                                    seat_lie_rate += rate + "-";
                                    seat_lie_unit += vmaConversionType
                                            .getConversionUnit() + "-";
                                    seat_lie += temp;
                                }
                                break;

                            } catch (Exception e) {
                                log.error(e.getMessage());
                            }
                        }

                        if (Validator.isNotNull(vmaShip.getShipTypeMT())
                                && vmaTransactionConversion == null) {

                            try {
                                vmaTransactionConversion = persistence
                                        .fetchByshipTypeMT_functionType(
                                                vmaShip.getShipTypeMT(),
                                                functionType);
                                double temp = vmaShip.getLies()
                                        * vmaTransactionConversion
                                        .getConversionRate() / 100;
                                if (temp > 0) {
                                    seat_lie_originValue += originValue + "-";
                                    seat_lie_rate += rate + "-";
                                    seat_lie_unit += vmaConversionType
                                            .getConversionUnit() + "-";
                                    seat_lie += temp;
                                }
                                break;

                            } catch (Exception e) {
                                log.error(e.getMessage());
                            }
                        }
                    }
                }

                if (seat_lie > value) {

                    value = seat_lie;
                    seat_lie_originValue = seat_lie_originValue.substring(0,
                            seat_lie_originValue.length() - 1);
                    seat_lie_rate = seat_lie_rate.substring(0,
                            seat_lie_rate.length() - 1);
                    seat_lie_unit = seat_lie_unit.substring(0,
                            seat_lie_unit.length() - 1);

                    unit = seat_lie_unit;
                    rate = seat_lie_rate;
                    originValue = seat_lie_originValue;
                }
            }
        }

        result.put("unit", unit);
        result.put("originValue", originValue);
        result.put("rate", rate);
        result.put("conversionValue", Math.round(value));
        return result;
    }

    public int countAll() {
        try {
            return persistence.countAll();
        } catch (SystemException e) {
            return 0;
        }
    }

    public List<VmaTransactionConversion> findAll() {
        try {
            return persistence.findAll();
        } catch (SystemException e) {
            return null;
        }
    }

    public List<VmaTransactionConversion> findAll(int start, int end) {
        try {
            return persistence.findAll(start, end);
        } catch (SystemException e) {
            return null;
        }
    }

    public List<VmaTransactionConversion> findAll(int start, int end,
                                                  OrderByComparator orderByComparator) {
        try {
            return persistence.findAll(start, end,
                    orderByComparator);
        } catch (SystemException e) {
            return null;
        }
    }

    public JSONObject findVmaTransactionConversion(String searchQuery,
                                                   String countQuery, int start, int end) throws SystemException,
            JSONException {

        JSONObject result = JSONFactoryUtil.createJSONObject();

        long total = finder
                .countVmaTransactionConversion(countQuery);

        JSONArray data = JSONFactoryUtil.createJSONArray();

        List<VmaTransactionConversion> VmaTransactionConversions = finder
                .findVmaTransactionConversion(
                        VmaTransactionConversion.class,
                        "VmaTransactionConversion", searchQuery, start, end);

        if (VmaTransactionConversions != null) {
            for (VmaTransactionConversion VmaTransactionConversion : VmaTransactionConversions) {

                JSONObject object = VMAUtils.object2Json(
                        VmaTransactionConversion,
                        VmaTransactionConversion.class);

                data.put(object);
            }
        }

        result.put("total", total);

        result.put("data", data);

        return result;

    }

    public long countVmaTransactionConversion(String sql)
            throws SystemException {

        return finder
                .countVmaTransactionConversion(sql);

    }


}

