package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import vn.gt.dao.danhmuc.service.VmaTransactionDepartmentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionBalanceLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionTypeLocalServiceUtil;
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
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.VmaTransactionBalancePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class VmaTransactionBalanceLocalServiceImpl {
@Autowired VmaTransactionBalancePersistenceImpl persistence;

	public VmaTransactionBalance createVmaTransactionBalance(long id) {
		return persistence.create(id);
	}

	public VmaTransactionBalance deleteVmaTransactionBalance(long id)
		throws PortalException, SystemException {
		return persistence.remove(id);
	}

	public VmaTransactionBalance deleteVmaTransactionBalance(VmaTransactionBalance VmaTransactionBalance)
		throws SystemException {
		return persistence.remove(VmaTransactionBalance);
	}

	public VmaTransactionBalance fetchVmaTransactionBalance(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	public VmaTransactionBalance getVmaTransactionBalance(long id)
		throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public List<VmaTransactionBalance> getVmaTransactionBalances(int start, int end)
		throws SystemException {
		return persistence.findAll(start, end);
	}

	public int getVmaTransactionBalancesCount() throws SystemException {
		return persistence.countAll();
	}


	public VmaTransactionBalance updateVmaTransactionBalance(VmaTransactionBalance VmaTransactionBalance,
		boolean merge) throws SystemException {
		return persistence.updateImpl(VmaTransactionBalance, merge);
	}


    public void autoInitVmaTransactionBalance(String portofAuthority,
                                              String shipAgencyCode, String shipAgencyName)
            throws SystemException {

        List<Integer> transactionTypeCodes = VmaTransactionTypeLocalServiceUtil
                .checkExistTransactionType(portofAuthority, shipAgencyCode);

        if (transactionTypeCodes != null && !transactionTypeCodes.isEmpty()) {
            List<VmaTransactionType> vmaTransactionTypes = VmaTransactionTypeLocalServiceUtil
                    .findVmaTransactionTypeByTransactionTypeCodes(
                            portofAuthority, transactionTypeCodes);

            if (vmaTransactionTypes != null) {
                for (VmaTransactionType vmaTransactionType : vmaTransactionTypes) {

                    if (vmaTransactionType.getTransactionLevel() == 1) {
                        VmaTransactionBalance transactionBalance = new VmaTransactionBalance();
                        transactionBalance.setPortofAuthority(portofAuthority);
                        transactionBalance.setShipAgencyCode(shipAgencyCode);
                        transactionBalance.setShipAgencyName(shipAgencyName);
                        transactionBalance
                                .setTransactionLevel(vmaTransactionType
                                        .getTransactionLevel());
                        transactionBalance
                                .setTransactionTypeCode(vmaTransactionType
                                        .getTransactionTypeCode());
                        transactionBalance
                                .setTransactionTypeName(vmaTransactionType
                                        .getTransactionTypeName());
                        // TODO get CurrencyCode from vmaTransactionType
                        transactionBalance.setCurrencyCode(vmaTransactionType
                                .getCurrencyCode());
                        VmaTransactionBalanceLocalServiceUtil
                                .addVmaTransactionBalance(transactionBalance);
                    } else if (vmaTransactionType.getTransactionLevel() == 2) {
                        // Not need departmentCode
                        /*
                         * VmaTransactionDepartment vmaTransactionDepartment =
                         * VmaTransactionDepartmentLocalServiceUtil .
                         * fetchByF_portOfAuthority_transactionTypeVND_transactionTypeUSD
                         * ( portofAuthority, String.valueOf(vmaTransactionType
                         * .getTransactionTypeCode()),
                         * String.valueOf(vmaTransactionType
                         * .getTransactionTypeCode()));
                         */
                        // if (vmaTransactionDepartment != null) {

                        VmaTransactionBalance transactionBalance = new VmaTransactionBalance();
                        transactionBalance.setPortofAuthority(portofAuthority);
                        transactionBalance.setShipAgencyCode(shipAgencyCode);
                        transactionBalance.setShipAgencyName(shipAgencyName);
                        transactionBalance
                                .setTransactionLevel(vmaTransactionType
                                        .getTransactionLevel());
                        transactionBalance
                                .setTransactionTypeCode(vmaTransactionType
                                        .getTransactionTypeCode());
                        transactionBalance
                                .setTransactionTypeName(vmaTransactionType
                                        .getTransactionTypeName());
                        // TODO get CurrencyCode from vmaTransactionType
                        transactionBalance.setCurrencyCode(vmaTransactionType
                                .getCurrencyCode());

                        /*
                         * transactionBalance
                         * .setDepartmentCode(vmaTransactionDepartment
                         * .getDepartmentCode()); transactionBalance
                         * .setDepartmentName(vmaTransactionDepartment
                         * .getDepartmentName());
                         */

                        VmaTransactionBalanceLocalServiceUtil
                                .addVmaTransactionBalance(transactionBalance);
                        // }
                    } else if (vmaTransactionType.getTransactionLevel() == 3) {
                        List<String[]> departmentInfos = VmaTransactionDepartmentLocalServiceUtil
                                .getDepartmentInfo(portofAuthority);

                        if (departmentInfos != null) {
                            for (String[] departmentInfo : departmentInfos) {
                                VmaTransactionBalance transactionBalance = new VmaTransactionBalance();
                                transactionBalance
                                        .setPortofAuthority(portofAuthority);
                                transactionBalance
                                        .setShipAgencyCode(shipAgencyCode);
                                transactionBalance
                                        .setShipAgencyName(shipAgencyName);
                                transactionBalance
                                        .setTransactionLevel(vmaTransactionType
                                                .getTransactionLevel());
                                transactionBalance
                                        .setTransactionTypeCode(vmaTransactionType
                                                .getTransactionTypeCode());
                                transactionBalance
                                        .setTransactionTypeName(vmaTransactionType
                                                .getTransactionTypeName());
                                // TODO get CurrencyCode from vmaTransactionType
                                transactionBalance
                                        .setCurrencyCode(vmaTransactionType
                                                .getCurrencyCode());

                                transactionBalance
                                        .setDepartmentCode(departmentInfo[0]);
                                transactionBalance
                                        .setDepartmentName(departmentInfo[1]);

                                VmaTransactionBalanceLocalServiceUtil
                                        .addVmaTransactionBalance(transactionBalance);
                            }
                        }
                    }

                }
            }
        }

        /*
         * List<String> departmentCodes =
         * VmaTransactionDepartmentLocalServiceUtil
         * .checkExistDepartmentCode(portofAuthority);
         */

    }

    public VmaTransactionBalance addVmaTransactionBalance(
            VmaTransactionBalance vmaTransactionBalance) throws SystemException {
        long id = CounterLocalServiceUtil.increment(VmaTransactionBalance.class
                .getName());
        vmaTransactionBalance.setId(id);
        vmaTransactionBalance.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaTransactionBalance);
        return persistence.updateImpl(vmaTransactionBalance,
                false);
    }

    public VmaTransactionBalance updateVmaTransactionBalance(
            VmaTransactionBalance vmaTransactionBalance) throws SystemException {
        vmaTransactionBalance.setModifiedDate(new Date());
        // VMAUtils.formatUnicode(vmaTransactionBalance);
        return persistence.updateImpl(vmaTransactionBalance,
                false);
    }

    public VmaTransactionBalance delete(long id) throws SystemException,
            NoSuchVmaTransactionBalanceException {
        return persistence.remove(id);
    }

    public List<VmaTransactionBalance> findByPortofAuthority(
            String portofAuthority) throws SystemException {
        return persistence
                .findByportofAuthority(portofAuthority);
    }

    public int countByPortofAuthority(String portofAuthority)
            throws SystemException {
        return persistence
                .countByportofAuthority(portofAuthority);
    }

    public VmaTransactionBalance findByportofAuthority_transactionTypeCode(
            String portofAuthority, String transactionTypeCode) {
        try {
            return persistence
                    .findByportofAuthority_transactionTypeCode(portofAuthority,
                            transactionTypeCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public VmaTransactionBalance findByportofAuthority_shipAgencyCode_transactionTypeCode(
            String portofAuthority, String shipAgencyCode,
            String transactionTypeCode) {
        try {
            return persistence
                    .findByportofAuthority_shipAgencyCode_transactionTypeCode(
                            portofAuthority, shipAgencyCode,
                            transactionTypeCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<VmaTransactionBalance> findByportofAuthority_shipAgencyCode(
            String portofAuthority, String shipAgencyCode) {
        try {
            return persistence
                    .findByportofAuthority_shipAgencyCode(portofAuthority,
                            shipAgencyCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ArrayList<VmaTransactionBalance>();
        }
    }

}

