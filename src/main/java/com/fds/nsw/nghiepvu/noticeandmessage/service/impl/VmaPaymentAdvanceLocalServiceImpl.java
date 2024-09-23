package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import vn.gt.dao.danhmuc.service.VmaTransactionDepartmentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaPaymentAdvanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionBalanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionSlipLocalServiceUtil;
import vn.gt.tichhop.message.MessageSyncUtil;
import com.fds.nsw.nghiepvu.service.exception.*;
import vn.gt.portlet.phuongtien.VMAUtils;

import java.lang.reflect.Method;
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
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaPaymentAdvancePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaPaymentAdvanceLocalServiceImpl { 
@Autowired VmaPaymentAdvancePersistenceImpl persistence;

	public VmaPaymentAdvance createVmaPaymentAdvance(long id) {
		return persistence.create(id);
	}

	public VmaPaymentAdvance deleteVmaPaymentAdvance(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaPaymentAdvance deleteVmaPaymentAdvance(VmaPaymentAdvance VmaPaymentAdvance)
		throws SystemException {
		return persistence.remove(VmaPaymentAdvance);
	}

	public VmaPaymentAdvance fetchVmaPaymentAdvance(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaPaymentAdvance getVmaPaymentAdvance(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaPaymentAdvance> getVmaPaymentAdvances(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaPaymentAdvancesCount() throws SystemException {
		return persistence.countAll();
	}

	public VmaPaymentAdvance updateVmaPaymentAdvance(VmaPaymentAdvance VmaPaymentAdvance,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaPaymentAdvance, merge);
	}

    public VmaPaymentAdvance addVmaPaymentAdvance(
            VmaPaymentAdvance vmaPaymentAdvance) throws SystemException {
        long id = CounterLocalServiceUtil.increment(VmaPaymentAdvance.class
                .getName());
        vmaPaymentAdvance.setId(id);
        vmaPaymentAdvance.setModifiedDate(new Date());
        return persistence.updateImpl(vmaPaymentAdvance, false);
    }

    public VmaPaymentAdvance addVmaPaymentAdvance_updateVmaTransactionBalance(
            VmaPaymentAdvance vmaPaymentAdvance,
            VmaTransactionBalance vmaTransactionBalance) throws SystemException {
        long id = CounterLocalServiceUtil.increment(VmaPaymentAdvance.class
                .getName());
        vmaPaymentAdvance.setId(id);
        vmaPaymentAdvance.setModifiedDate(new Date());
        double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
        sttlmtAmount = sttlmtAmount + vmaPaymentAdvance.getAmtRate()
                * vmaPaymentAdvance.getAmount();
        vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
        VmaTransactionBalanceLocalServiceUtil
                .updateVmaTransactionBalance(vmaTransactionBalance);
        return persistence.updateImpl(vmaPaymentAdvance, false);
    }

    public VmaPaymentAdvance updateVmaPaymentAdvance(
            VmaPaymentAdvance vmaPaymentAdvance) throws SystemException {
        vmaPaymentAdvance.setModifiedDate(new Date());
        return persistence.updateImpl(vmaPaymentAdvance, false);
    }

    public List<VmaPaymentAdvance> findAll() throws SystemException,
            PortalException {
        return persistence.findAll();
    }

    public List<VmaPaymentAdvance> findAll(int start, int end)
            throws SystemException, PortalException {
        return persistence.findAll(start, end);
    }

    public List<VmaPaymentAdvance> findAll(int start, int end,
                                           OrderByComparator orderByComparator) throws SystemException,
            PortalException {
        return persistence.findAll(start, end,
                orderByComparator);
    }

    public int countByshipAgencyCode(String shipAgencyCode)
            throws SystemException, PortalException {
        return persistence
                .countByshipAgencyCode(shipAgencyCode);
    }

    public int countByshipAgencyCode_paymentType(String shipAgencyCode,
                                                 int paymentType) throws SystemException, PortalException {
        return persistence.countByshipAgencyCode_paymentType(
                shipAgencyCode, paymentType);
    }

    public int countBytransactionTypeCode(String transactionTypeCode)
            throws SystemException, PortalException {
        return persistence
                .countBytransactionTypeCode(transactionTypeCode);
    }

    public int countByshipAgencyCode_transactionTypeCode(String shipAgencyCode,
                                                         String transactionTypeCode) throws SystemException, PortalException {
        return persistence
                .countByshipAgencyCode_transactionTypeCode(shipAgencyCode,
                        transactionTypeCode);
    }

    public int countByportofAuthority(String portofAuthority)
            throws SystemException, PortalException {
        return persistence
                .countByportofAuthority(portofAuthority);
    }

    public boolean rutquy(VmaPaymentAdvance vmaPaymentAdvance,
                          String itineraryNo, String documentaryCode, String portofAuthority,
                          String transactionTypeCode, String shipAgencyCode,
                          String departmentCode) throws SystemException, PortalException {
        List<VmaTransactionBalance> vmaTransactionBalances = VmaTransactionBalanceLocalServiceUtil
                .findByportofAuthority_shipAgencyCode(portofAuthority,
                        shipAgencyCode);

        VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
                .fetchByitineraryNo_documentaryCode(itineraryNo,
                        documentaryCode);

        VmaTransactionDepartment vmaTransactionDepartment = VmaTransactionDepartmentLocalServiceUtil
                .fetchVmaTransactionDepartment(departmentCode);

        if (vmaPaymentAdvance == null || vmaTransactionBalances.isEmpty()
                || vmaTransactionSlip == null
                || vmaTransactionDepartment == null) {
            return false;
        }

        double paymentAmount = vmaTransactionSlip.getPaymentAmount();
        double paidAdvanceAmount = vmaTransactionSlip.getPaidAdvanceAmount();

        String transactionTypeUSD = vmaTransactionDepartment
                .getTransactionTypeUSD();
        String transactionTypeVND = vmaTransactionDepartment
                .getTransactionTypeVND();
        String transactionSettlement = vmaTransactionDepartment
                .getTransactionSettlement();
        for (VmaTransactionBalance vmaTransactionBalance : vmaTransactionBalances) {
            if (vmaTransactionBalance.getTransactionTypeCode().equals("1001")) {
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
                sttlmtAmount = sttlmtAmount + vmaPaymentAdvance.getAmount()
                        * vmaPaymentAdvance.getAmtRate();
                if (paidAdvanceAmount > paymentAmount) {
                    sttlmtAmount = sttlmtAmount
                            + (paymentAmount - paidAdvanceAmount);
                }

                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);
                VmaPaymentAdvanceLocalServiceUtil
                        .addVmaPaymentAdvance(vmaPaymentAdvance);
            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    "1101")) {
                double vndTotalAmount = vmaTransactionSlip.getVndTotalAmount();
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount
                        - vndTotalAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);
            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    "1102")) {
                double usdTotalAmount = vmaTransactionSlip.getUsdTotalAmount();
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount
                        - usdTotalAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);

            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    "1100")) {
                double remainingAmount = paymentAmount - paidAdvanceAmount;
                if (remainingAmount < 0) {
                    remainingAmount = 0;
                }
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount
                        - remainingAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);
            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    transactionTypeUSD)) {
                double usdTotalAmount = vmaTransactionSlip.getUsdTotalAmount();
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount
                        - usdTotalAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);
            }

            else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    transactionTypeVND)) {
                double vndTotalAmount = vmaTransactionSlip.getVndTotalAmount();
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount
                        - vndTotalAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);
            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    transactionSettlement)) {
                double remainingAmount = paymentAmount - paidAdvanceAmount;
                if (remainingAmount < 0) {
                    remainingAmount = 0;
                }
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount
                        - remainingAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);
            }

        }

        vmaTransactionSlip.setPaymentStatus(9);
        vmaTransactionSlip.setPaidAdvanceAmount(paymentAmount);
        VmaTransactionSlipLocalServiceUtil
                .updateVmaTransactionSlip(vmaTransactionSlip);
        return true;
    }

    public boolean xacnhanthanhtoan(VmaPaymentAdvance vmaPaymentAdvance,
                                    String itineraryNo, String documentaryCode, String portofAuthority,
                                    String transactionTypeCode, String shipAgencyCode,
                                    String departmentCode) throws SystemException, PortalException {
        List<VmaTransactionBalance> vmaTransactionBalances = VmaTransactionBalanceLocalServiceUtil
                .findByportofAuthority_shipAgencyCode(portofAuthority,
                        shipAgencyCode);

        VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
                .fetchByitineraryNo_documentaryCode(itineraryNo,
                        documentaryCode);

        VmaTransactionDepartment vmaTransactionDepartment = VmaTransactionDepartmentLocalServiceUtil
                .fetchVmaTransactionDepartment(departmentCode);

        if (vmaPaymentAdvance == null || vmaTransactionBalances.isEmpty()
                || vmaTransactionSlip == null
                || vmaTransactionDepartment == null) {
            return false;
        }

        double paymentAmount = vmaTransactionSlip.getPaymentAmount();
        double paidAdvanceAmount = vmaTransactionSlip.getPaidAdvanceAmount();

        String transactionTypeUSD = vmaTransactionDepartment
                .getTransactionTypeUSD();
        String transactionTypeVND = vmaTransactionDepartment
                .getTransactionTypeVND();
        String transactionSettlement = vmaTransactionDepartment
                .getTransactionSettlement();
        for (VmaTransactionBalance vmaTransactionBalance : vmaTransactionBalances) {
            if (vmaTransactionBalance.getTransactionTypeCode().equals("1001")) {
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();

                if (paidAdvanceAmount > paymentAmount) {
                    sttlmtAmount = sttlmtAmount
                            + (paymentAmount - paidAdvanceAmount);
                    vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                    VmaTransactionBalanceLocalServiceUtil
                            .updateVmaTransactionBalance(vmaTransactionBalance);
                }

                VmaPaymentAdvanceLocalServiceUtil
                        .addVmaPaymentAdvance(vmaPaymentAdvance);
            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    "1101")) {
                double vndTotalAmount = vmaTransactionSlip.getVndTotalAmount();
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount
                        - vndTotalAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);
            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    "1102")) {
                double usdTotalAmount = vmaTransactionSlip.getUsdTotalAmount();
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount
                        - usdTotalAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);

            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    "1100")) {
                double remainingAmount = paymentAmount - paidAdvanceAmount;
                if (remainingAmount < 0) {
                    remainingAmount = 0;
                }
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount
                        - remainingAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);
            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    transactionTypeUSD)) {
                double usdTotalAmount = vmaTransactionSlip.getUsdTotalAmount();
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount
                        - usdTotalAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);
            }

            else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    transactionTypeVND)) {
                double vndTotalAmount = vmaTransactionSlip.getVndTotalAmount();
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount
                        - vndTotalAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);
            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    transactionSettlement)) {
                double remainingAmount = paymentAmount - paidAdvanceAmount;
                if (remainingAmount < 0) {
                    remainingAmount = 0;
                }
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount();
                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount
                        - remainingAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);
            }

        }

        vmaTransactionSlip.setPaymentStatus(9);
        vmaTransactionSlip.setPaidAdvanceAmount(paymentAmount);
        VmaTransactionSlipLocalServiceUtil
                .updateVmaTransactionSlip(vmaTransactionSlip);
        return true;
    }

    public boolean noptien(VmaPaymentAdvance vmaPaymentAdvance,
                           String itineraryNo, String documentaryCode, String portofAuthority,
                           String transactionTypeCode, String shipAgencyCode,
                           String departmentCode) throws SystemException, PortalException {
        List<VmaTransactionBalance> vmaTransactionBalances = VmaTransactionBalanceLocalServiceUtil
                .findByportofAuthority_shipAgencyCode(portofAuthority,
                        shipAgencyCode);

        VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
                .fetchByitineraryNo_documentaryCode(itineraryNo,
                        documentaryCode);

        VmaTransactionDepartment vmaTransactionDepartment = VmaTransactionDepartmentLocalServiceUtil
                .fetchVmaTransactionDepartment(departmentCode);

        if (vmaPaymentAdvance == null || vmaTransactionBalances.isEmpty()
                || vmaTransactionSlip == null
                || vmaTransactionDepartment == null) {
            return false;
        }

        double paymentAmount = vmaTransactionSlip.getPaymentAmount();
        double paidAdvanceAmount = vmaTransactionSlip.getPaidAdvanceAmount();

        String transactionSettlement = vmaTransactionDepartment
                .getTransactionSettlement();

        VmaPaymentAdvanceLocalServiceUtil.addVmaPaymentAdvance(vmaPaymentAdvance);

        for (VmaTransactionBalance vmaTransactionBalance : vmaTransactionBalances) {
            if (vmaTransactionBalance.getTransactionTypeCode().equals("1100")) {

                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount()
                        + (vmaPaymentAdvance.getAmount() * vmaPaymentAdvance
                        .getAmtRate());
                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);
            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    transactionSettlement)) {
                double sttlmtAmount = vmaTransactionBalance.getSttlmtAmount()
                        + (vmaPaymentAdvance.getAmount() * vmaPaymentAdvance
                        .getAmtRate());
                vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                VmaTransactionBalanceLocalServiceUtil
                        .updateVmaTransactionBalance(vmaTransactionBalance);
            }

        }

        // tang do AmtRate = -1
        vmaTransactionSlip.setPaidAdvanceAmount(vmaTransactionSlip
                .getPaidAdvanceAmount()
                - (vmaPaymentAdvance.getAmount() * vmaPaymentAdvance
                .getAmtRate()));
        VmaTransactionSlipLocalServiceUtil
                .updateVmaTransactionSlip(vmaTransactionSlip);
        return true;
    }

    public boolean huytinhphi(String itineraryNo, String documentaryCode,
                              String portofAuthority, String transactionTypeCode,
                              String shipAgencyCode, String departmentCode)
            throws SystemException, PortalException {
        List<VmaTransactionBalance> vmaTransactionBalances = VmaTransactionBalanceLocalServiceUtil
                .findByportofAuthority_shipAgencyCode(portofAuthority,
                        shipAgencyCode);

        VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
                .fetchByitineraryNo_documentaryCode(itineraryNo,
                        documentaryCode);

        VmaTransactionDepartment vmaTransactionDepartment = VmaTransactionDepartmentLocalServiceUtil
                .fetchVmaTransactionDepartment(departmentCode);

        if (vmaTransactionBalances.isEmpty() || vmaTransactionSlip == null
                || vmaTransactionDepartment == null) {
            return false;
        }

        if (vmaTransactionSlip.getPaymentStatus() != 4
                && vmaTransactionSlip.getPaymentStatus() != 9) {
            return false;
        }

        double paymentAmount = vmaTransactionSlip.getPaymentAmount();
        double paidAdvanceAmount = vmaTransactionSlip.getPaidAdvanceAmount();

        String transactionTypeUSD = vmaTransactionDepartment
                .getTransactionTypeUSD();
        String transactionTypeVND = vmaTransactionDepartment
                .getTransactionTypeVND();
        String transactionSettlement = vmaTransactionDepartment
                .getTransactionSettlement();
        for (VmaTransactionBalance vmaTransactionBalance : vmaTransactionBalances) {
            if (vmaTransactionBalance.getTransactionTypeCode().equals("1001")) {

                if (paidAdvanceAmount > 0) {
                    // update quy
                    double sttlmtAmount = vmaTransactionBalance
                            .getSttlmtAmount();
                    sttlmtAmount = sttlmtAmount - paidAdvanceAmount;

                    vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                    VmaTransactionBalanceLocalServiceUtil
                            .updateVmaTransactionBalance(vmaTransactionBalance);

                }

            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    "1101")) {
                double vndTotalAmount = vmaTransactionSlip.getVndTotalAmount();

                if (vndTotalAmount > 0) {
                    double sttlmtAmount = vmaTransactionBalance
                            .getSttlmtAmount();
                    sttlmtAmount = sttlmtAmount - vndTotalAmount;

                    vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                    VmaTransactionBalanceLocalServiceUtil
                            .updateVmaTransactionBalance(vmaTransactionBalance);
                }

            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    "1102")) {
                double usdTotalAmount = vmaTransactionSlip.getUsdTotalAmount();
                if (usdTotalAmount > 0) {
                    double sttlmtAmount = vmaTransactionBalance
                            .getSttlmtAmount();
                    sttlmtAmount = sttlmtAmount - usdTotalAmount;

                    vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                    VmaTransactionBalanceLocalServiceUtil
                            .updateVmaTransactionBalance(vmaTransactionBalance);
                }

            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    "1100")) {

                if (paymentAmount > 0) {
                    double sttlmtAmount = vmaTransactionBalance
                            .getSttlmtAmount();
                    sttlmtAmount = sttlmtAmount - paymentAmount
                            + paidAdvanceAmount;
                    vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                    VmaTransactionBalanceLocalServiceUtil
                            .updateVmaTransactionBalance(vmaTransactionBalance);
                }

            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    transactionTypeUSD)) {
                double usdTotalAmount = vmaTransactionSlip.getUsdTotalAmount();
                if (usdTotalAmount > 0) {
                    double sttlmtAmount = vmaTransactionBalance
                            .getSttlmtAmount();
                    sttlmtAmount = sttlmtAmount - usdTotalAmount;

                    vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                    VmaTransactionBalanceLocalServiceUtil
                            .updateVmaTransactionBalance(vmaTransactionBalance);
                }
            }

            else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    transactionTypeVND)) {
                double vndTotalAmount = vmaTransactionSlip.getVndTotalAmount();

                if (vndTotalAmount > 0) {
                    double sttlmtAmount = vmaTransactionBalance
                            .getSttlmtAmount();
                    sttlmtAmount = sttlmtAmount - vndTotalAmount;

                    vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                    VmaTransactionBalanceLocalServiceUtil
                            .updateVmaTransactionBalance(vmaTransactionBalance);
                }
            } else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                    transactionSettlement)) {
                if (paymentAmount > 0) {
                    double sttlmtAmount = vmaTransactionBalance
                            .getSttlmtAmount();
                    sttlmtAmount = sttlmtAmount - paymentAmount
                            + paidAdvanceAmount;
                    vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                    VmaTransactionBalanceLocalServiceUtil
                            .updateVmaTransactionBalance(vmaTransactionBalance);
                }
            } else if (vmaTransactionBalance.getTransactionLevel() == 3) {
                if (paymentAmount > 0) {
                    String vndMethodName = "getF"
                            + vmaTransactionBalance.getTransactionTypeCode()
                            + "Vnd";
                    String usdMethodName = "getF"
                            + vmaTransactionBalance.getTransactionTypeCode()
                            + "Usd";

                    Method vndMethod = null;

                    try {
                        vndMethod = vmaTransactionSlip.getClass().getMethod(
                                vndMethodName);
                    } catch (Exception e) {
                        // nothing to do
                    }

                    if (vndMethod != null) {
                        try {
                            Object object = vndMethod.invoke(
                                    vmaTransactionSlip, double.class);
                            double vndValue = object != null ? GetterUtil
                                    .getDouble(object) : 0;

                            vndValue = vndValue - paidAdvanceAmount;

                            if (vndValue < 0) {
                                vndValue = 0;
                            }
                            vmaTransactionBalance.setSttlmtAmount(vndValue);

                            VmaTransactionBalanceLocalServiceUtil
                                    .updateVmaTransactionBalance(vmaTransactionBalance);
                        } catch (Exception e) {
                            throw new SystemException();
                        }
                    }

                    Method usdMethod = null;

                    try {
                        usdMethod = vmaTransactionSlip.getClass().getMethod(
                                usdMethodName);
                    } catch (Exception e) {
                        // nothing to do
                    }

                    if (usdMethod != null) {
                        try {
                            Object object = usdMethod.invoke(
                                    vmaTransactionSlip, double.class);
                            double usdValue = object != null ? GetterUtil
                                    .getDouble(object) : 0;
                            usdValue = usdValue - paidAdvanceAmount;

                            if (usdValue < 0) {
                                usdValue = 0;
                            }
                            vmaTransactionBalance.setSttlmtAmount(usdValue);

                            VmaTransactionBalanceLocalServiceUtil
                                    .updateVmaTransactionBalance(vmaTransactionBalance);
                        } catch (Exception e) {
                            throw new SystemException();
                        }
                    }

                }
            }

        }

        vmaTransactionSlip.setPaymentStatus(6);

        VmaTransactionSlipLocalServiceUtil
                .updateVmaTransactionSlip(vmaTransactionSlip);
        return true;
    }

    public boolean xacnhantinhphi(String itineraryNo, String documentaryCode,
                                  String portofAuthority, String transactionTypeCode,
                                  String shipAgencyCode, String departmentCode)
            throws SystemException, PortalException {
        List<VmaTransactionBalance> vmaTransactionBalances = VmaTransactionBalanceLocalServiceUtil
                .findByportofAuthority_shipAgencyCode(portofAuthority,
                        shipAgencyCode);

        VmaTransactionSlip vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
                .fetchByitineraryNo_documentaryCode(itineraryNo,
                        documentaryCode);

        VmaTransactionDepartment vmaTransactionDepartment = VmaTransactionDepartmentLocalServiceUtil
                .fetchVmaTransactionDepartment(departmentCode);

        if (vmaTransactionBalances.isEmpty() || vmaTransactionSlip == null
                || vmaTransactionDepartment == null) {
            return false;
        }

        if (vmaTransactionSlip.getPaymentStatus() != 4
                && vmaTransactionSlip.getPaymentStatus() != 9) {
            double paymentAmount = vmaTransactionSlip.getPaymentAmount();
            double paidAdvanceAmount = vmaTransactionSlip
                    .getPaidAdvanceAmount();
            String transactionTypeUSD = vmaTransactionDepartment
                    .getTransactionTypeUSD();
            String transactionTypeVND = vmaTransactionDepartment
                    .getTransactionTypeVND();
            String transactionSettlement = vmaTransactionDepartment
                    .getTransactionSettlement();
            for (VmaTransactionBalance vmaTransactionBalance : vmaTransactionBalances) {
                if (vmaTransactionBalance.getTransactionTypeCode().equals(
                        "1101")) {
                    double vndTotalAmount = vmaTransactionSlip
                            .getVndTotalAmount();

                    if (vndTotalAmount > 0) {
                        double sttlmtAmount = vmaTransactionBalance
                                .getSttlmtAmount();
                        sttlmtAmount = sttlmtAmount + vndTotalAmount;

                        vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                        VmaTransactionBalanceLocalServiceUtil
                                .updateVmaTransactionBalance(vmaTransactionBalance);
                    }

                } else if (vmaTransactionBalance.getTransactionTypeCode()
                        .equals("1102")) {
                    double usdTotalAmount = vmaTransactionSlip
                            .getUsdTotalAmount();
                    if (usdTotalAmount > 0) {
                        double sttlmtAmount = vmaTransactionBalance
                                .getSttlmtAmount();
                        sttlmtAmount = sttlmtAmount + usdTotalAmount;

                        vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                        VmaTransactionBalanceLocalServiceUtil
                                .updateVmaTransactionBalance(vmaTransactionBalance);
                    }

                } else if (vmaTransactionBalance.getTransactionTypeCode()
                        .equals("1100")) {

                    if (paymentAmount > 0) {
                        double sttlmtAmount = vmaTransactionBalance
                                .getSttlmtAmount();
                        sttlmtAmount = sttlmtAmount + paymentAmount;

                        vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                        VmaTransactionBalanceLocalServiceUtil
                                .updateVmaTransactionBalance(vmaTransactionBalance);
                    }

                } else if (vmaTransactionBalance.getTransactionTypeCode()
                        .equals(transactionTypeUSD)) {
                    double usdTotalAmount = vmaTransactionSlip
                            .getUsdTotalAmount();
                    if (usdTotalAmount > 0) {
                        double sttlmtAmount = vmaTransactionBalance
                                .getSttlmtAmount();
                        sttlmtAmount = sttlmtAmount + usdTotalAmount;

                        vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                        VmaTransactionBalanceLocalServiceUtil
                                .updateVmaTransactionBalance(vmaTransactionBalance);
                    }
                }

                else if (vmaTransactionBalance.getTransactionTypeCode().equals(
                        transactionTypeVND)) {
                    double vndTotalAmount = vmaTransactionSlip
                            .getVndTotalAmount();

                    if (vndTotalAmount > 0) {
                        double sttlmtAmount = vmaTransactionBalance
                                .getSttlmtAmount();
                        sttlmtAmount = sttlmtAmount + vndTotalAmount;

                        vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                        VmaTransactionBalanceLocalServiceUtil
                                .updateVmaTransactionBalance(vmaTransactionBalance);
                    }
                } else if (vmaTransactionBalance.getTransactionTypeCode()
                        .equals(transactionSettlement)) {
                    if (paymentAmount > 0) {
                        double sttlmtAmount = vmaTransactionBalance
                                .getSttlmtAmount();
                        sttlmtAmount = sttlmtAmount + paymentAmount;
                        vmaTransactionBalance.setSttlmtAmount(sttlmtAmount);
                        VmaTransactionBalanceLocalServiceUtil
                                .updateVmaTransactionBalance(vmaTransactionBalance);
                    }
                } else if (vmaTransactionBalance.getTransactionLevel() == 3) {
                    if (paymentAmount > 0) {
                        String vndMethodName = "getF"
                                + vmaTransactionBalance
                                .getTransactionTypeCode() + "Vnd";
                        String usdMethodName = "getF"
                                + vmaTransactionBalance
                                .getTransactionTypeCode() + "Usd";

                        Method vndMethod = null;

                        try {
                            vndMethod = vmaTransactionSlip.getClass()
                                    .getMethod(vndMethodName);
                        } catch (Exception e) {
                            // nothing to do
                        }

                        if (vndMethod != null) {
                            try {
                                Object object = vndMethod.invoke(
                                        vmaTransactionSlip, double.class);
                                double vndValue = object != null ? GetterUtil
                                        .getDouble(object) : 0;

                                vndValue = vndValue + paidAdvanceAmount;

                                if (vndValue < 0) {
                                    vndValue = 0;
                                }
                                vmaTransactionBalance.setSttlmtAmount(vndValue);

                                VmaTransactionBalanceLocalServiceUtil
                                        .updateVmaTransactionBalance(vmaTransactionBalance);
                            } catch (Exception e) {
                                throw new SystemException();
                            }
                        }

                        Method usdMethod = null;

                        try {
                            usdMethod = vmaTransactionSlip.getClass()
                                    .getMethod(usdMethodName);
                        } catch (Exception e) {
                            // nothing to do
                        }

                        if (usdMethod != null) {
                            try {
                                Object object = usdMethod.invoke(
                                        vmaTransactionSlip, double.class);
                                double usdValue = object != null ? GetterUtil
                                        .getDouble(object) : 0;
                                usdValue = usdValue + paidAdvanceAmount;

                                if (usdValue < 0) {
                                    usdValue = 0;
                                }
                                vmaTransactionBalance.setSttlmtAmount(usdValue);

                                VmaTransactionBalanceLocalServiceUtil
                                        .updateVmaTransactionBalance(vmaTransactionBalance);
                            } catch (Exception e) {
                                throw new SystemException();
                            }
                        }

                    }
                }

            }

            vmaTransactionSlip.setPaymentStatus(4);
            vmaTransactionSlip.setPaidAdvanceAmount((double) 0);
            VmaTransactionSlipLocalServiceUtil
                    .updateVmaTransactionSlip(vmaTransactionSlip);

        } else {
            return false;
        }

        return true;
    }

}

