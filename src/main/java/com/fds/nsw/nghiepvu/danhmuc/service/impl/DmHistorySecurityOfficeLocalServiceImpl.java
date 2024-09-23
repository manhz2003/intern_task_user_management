package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistorySecurityOfficePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import vn.gt.tichhop.message.MessageSyncUtil;

@Slf4j @Service
public class DmHistorySecurityOfficeLocalServiceImpl { @Autowired
DmHistorySecurityOfficePersistenceImpl persistence;
    public DmHistorySecurityOffice fetchBySecurityOfficeCode_SyncVersion(
            String securityOfficeCode, String syncVersion) {
        try {
            return persistence
                    .fetchByF_securityOfficeCode_syncVersion(
                            securityOfficeCode, syncVersion);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DmHistorySecurityOffice updateHistorySecurityOffice(
            String fromMaritimeCode, String securityOfficeCode,
            String companyName, String companyAddress, String contactEmail,
            String faxNo, String remarks, String maritimeCode, String telNo,
            String syncVersion) throws SystemException,
            NoSuchDmHistorySecurityOfficeException {

        DmHistorySecurityOffice dmHistorySecurityOffice = persistence
                .fetchByF_securityOfficeCode_syncVersion(securityOfficeCode,
                        syncVersion);

        if (dmHistorySecurityOffice == null) {
            long id = CounterLocalServiceUtil
                    .increment(DmHistorySecurityOffice.class.getName());
            dmHistorySecurityOffice = persistence
                    .create(id);
        }

        dmHistorySecurityOffice.setSecurityOfficeCode(securityOfficeCode);
        dmHistorySecurityOffice.setCompanyName(companyName);
        dmHistorySecurityOffice.setCompanyAddress(companyAddress);
        dmHistorySecurityOffice.setContactEmail(contactEmail);
        dmHistorySecurityOffice.setFaxNo(faxNo);
        dmHistorySecurityOffice.setRemarks(remarks);
        dmHistorySecurityOffice.setMaritimeCode(maritimeCode);
        dmHistorySecurityOffice.setTelNo(telNo);

        dmHistorySecurityOffice.setIsDelete(0);
        dmHistorySecurityOffice.setMarkedAsDelete(0);
        dmHistorySecurityOffice.setModifiedDate(new Date());
        dmHistorySecurityOffice.setSyncVersion(syncVersion);

        dmHistorySecurityOffice = persistence.updateImpl(
                dmHistorySecurityOffice, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistorySecurityOffice);

        return dmHistorySecurityOffice;
    }

    public DmHistorySecurityOffice deleteHistorySecurityOffice(
            String fromMaritimeCode, String securityOfficeCode,
            String syncVersion) throws SystemException,
            NoSuchDmHistorySecurityOfficeException {

        DmHistorySecurityOffice dmHistorySecurityOffice = persistence
                .findByF_securityOfficeCode_syncVersion(securityOfficeCode,
                        syncVersion);

        if (dmHistorySecurityOffice == null) {
            long id = CounterLocalServiceUtil
                    .increment(DmHistorySecurityOffice.class.getName());
            dmHistorySecurityOffice = persistence
                    .create(id);
        }

        dmHistorySecurityOffice.setIsDelete(1);
        dmHistorySecurityOffice.setMarkedAsDelete(1);
        dmHistorySecurityOffice.setModifiedDate(new Date());
        dmHistorySecurityOffice.setSyncVersion(syncVersion);

        dmHistorySecurityOffice = persistence.updateImpl(
                dmHistorySecurityOffice, true);

        MessageSyncUtil.dongBoDanhMuc(fromMaritimeCode, dmHistorySecurityOffice);

        return dmHistorySecurityOffice;
    }

}
