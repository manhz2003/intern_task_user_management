/**
 *
 */
package com.fds.nsw.nghiepvu.noticeandmessage.service.finder;

import java.util.*;

import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.nghiepvu.baocaothongke.model.FlowChartDataByDate;
import com.fds.nsw.nghiepvu.model.TempDebitnote;
import com.fds.nsw.nghiepvu.util.ChuyenDichTrangThaiUtils;
import com.fds.nsw.nghiepvu.util.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.dao.orm.BasePersistence;
import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.util.ConvertUtil;
import com.fds.nsw.nghiepvu.util.FormatData;

import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.nghiepvu.model.TempDocument;
@Service
@Slf4j
public class TempDocumentFinderImpl extends
        BasePersistence {
    @Autowired
    @Qualifier("blQueryFactory")
    QueryFactory<TempDocument> queryFactory;
    
    @Autowired
    @Qualifier("blQueryFactory")
    QueryFactory<TempDebitnote> queryFactory2;

    @Autowired
    @Qualifier("blQueryFactory")
    QueryFactory<Long> queryFactory3;
    
    public String getCallSignByDocumentNameAndDocumentYear(long documentName,
                                                           int documentYear) throws SystemException {
        String value = null;
        try {

            StringBuilder query = new StringBuilder();
            query.append("SELECT CallSign as callSign FROM temp_document ");
            query.append(" WHERE DocumentName= :documentName AND DocumentYear= :documentYear ");

            log.debug("=========getCallSignByDocNameAndDocYear========"
                    + query.toString());

            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(String.class).build();



            builder.appendNamedParameterMap("documentName",documentName);
            builder.appendNamedParameterMap("documentYear",documentYear);

            value = (String) queryFactory.getSingleResult(builder);
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {

        }
        return null;
    }

    public TempDocument getByDocumentNameAndDocumentYear(long documentName,
                                                         int documentYear) throws SystemException {

        try {

            StringBuilder query = new StringBuilder();

            query.append("SELECT * FROM temp_document WHERE DocumentName= :documentName AND DocumentYear= :documentYear");

            String sql = query.toString();
            log.debug("=========getByDocumentNameDocumentYear========" + sql);
            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();

            builder.appendNamedParameterMap("documentName",documentName);
            builder.appendNamedParameterMap("documentYear",documentYear);

            // execute the query and return a list from the db
            List<TempDocument> lstDocument = (List<TempDocument>) queryFactory.getResultList(builder);
            if (Validator.isNotNull(lstDocument) && lstDocument.size() > 0) {
                return lstDocument.get(0);
            }

        } catch (Exception e) {
            throw new SystemException(e);
        } finally {

        }
        return null;
    }

    public long countTempDocumentJoinIssuePermissionTransitForStatisticsReport(
            String maritimeCode, String dateFrom, String dateTo,
            String documentTypeCode, String requestState,
            String issRequestState, String documentStatusCode)
            throws SystemException {
        long count = 0L;

        Date date = null;
        try {

            StringBuilder query = new StringBuilder();

            query.append("select count(*) as total from temp_document temp ");
            query.append(" inner join issue_permission_for_transit iss ");
            query.append(" on iss.DocumentName = temp.DocumentName ");
            query.append(" and   iss.DocumentYear  = temp.DocumentYear ");

            if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {
                date = FormatData.parseDateShort3StringToDate(dateFrom.trim());
                dateFrom = FormatData.parseDateToTring(date);
                query.append(" and iss.ApprovalDate >= concat(date('"
                        + dateFrom + "'), ' 00:00:00') ");
            }
            if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {
                date = FormatData.parseDateShort3StringToDate(dateTo.trim());
                dateTo = FormatData.parseDateToTring(date);
                query.append(" and iss.ApprovalDate <= concat(date('" + dateTo
                        + "'), ' 23:59:59') ");
            }
            if (maritimeCode != null) {
                query.append(" and temp.MaritimeCode = " + maritimeCode);
            }

            if (documentTypeCode != null) {
                query.append(" and temp.DocumentTypeCode in ("
                        + documentTypeCode + ")");
            }

            if (requestState != null) {
                query.append(" and temp.requestState in (" + requestState
                        + ") ");
            }
            if (issRequestState != null) {
                query.append(" and iss.requestState in (" + issRequestState
                        + ") ");
            }
            if (documentStatusCode != null) {
                query.append(" and temp.documentStatusCode in ("
                        + documentStatusCode + ")");
            }

            query.append(" and iss.VersionNo = (SELECT MAX(iss2.VersionNo) "
                    + " FROM issue_permission_for_transit iss2 "
                    + " WHERE iss.DocumentName = iss2.DocumentName) "); // lttai
            // 13/7/2015
            // :
            // lay
            // version
            // moi
            // nhat
            // cua
            // giay
            // phep

            log.debug("---query countTempDocumentJoinIssuePermissionTransitForStatisticsReport----");
            log.debug(query.toString());

            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();



            count = (Long) queryFactory.getSingleResult(builder);
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {

        }
        return count;

    }

    public long countTempDocumentJoinIssueAcceptanceForStatisticsReport(
            String maritimeCode, String dateFrom, String dateTo,
            String documentTypeCode, String requestState,
            String issRequestState, String documentStatusCode)
            throws SystemException {
        long count = 0L;

        Date date = null;
        try {

            StringBuilder query = new StringBuilder();

            query.append("select count(*) as total from temp_document temp ");
            query.append(" inner join issue_acceptance iss ");
            query.append(" on iss.DocumentName = temp.DocumentName ");
            query.append(" and   iss.DocumentYear  = temp.DocumentYear ");

            if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {
                date = FormatData.parseDateShort3StringToDate(dateFrom.trim());
                dateFrom = FormatData.parseDateToTring(date);
                query.append(" and iss.ApprovalDate >= concat(date('"
                        + dateFrom + "'), ' 00:00:00') ");
            }
            if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {
                date = FormatData.parseDateShort3StringToDate(dateTo.trim());
                dateTo = FormatData.parseDateToTring(date);
                query.append(" and iss.ApprovalDate <= concat(date('" + dateTo
                        + "'), ' 23:59:59') ");
            }
            if (maritimeCode != null) {
                query.append(" and temp.MaritimeCode = " + maritimeCode);
            }

            if (documentTypeCode != null) {
                query.append(" and temp.DocumentTypeCode in ("
                        + documentTypeCode + ")");
            }

            if (requestState != null) {
                query.append(" and temp.requestState in (" + requestState
                        + ") ");
            }
            if (issRequestState != null) {
                query.append(" and iss.requestState in (" + issRequestState
                        + ") ");
            }
            if (documentStatusCode != null) {
                query.append(" and temp.documentStatusCode in ("
                        + documentStatusCode + ")");
            }

            query.append(" and iss.VersionNo = (SELECT MAX(iss2.VersionNo) "
                    + " FROM issue_acceptance iss2 "
                    + " WHERE iss.DocumentName = iss2.DocumentName) "); // lttai
            // 13/7/2015
            // : lay
            // version
            // moi
            // nhat
            // cua
            // giay
            // phep

            log.debug("----query countTempDocumentJoinIssueAcceptanceForStatisticsReport----");
            log.debug(query.toString());

            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();


            
            count = (Long) queryFactory.getSingleResult(builder);
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {

        }
        return count;

    }

    public long countTempDocumentJoinIssuePortClearanceForStatisticsReport(
            String maritimeCode, String dateFrom, String dateTo,
            String documentTypeCode, String requestState,
            String issRequestState, String documentStatusCode)
            throws SystemException {
        long count = 0L;

        Date date = null;
        try {

            StringBuilder query = new StringBuilder();

            query.append("select count(*) as total from temp_document temp ");
            query.append(" inner join issue_port_clearance iss ");
            query.append(" on iss.DocumentName = temp.DocumentName ");
            query.append(" and   iss.DocumentYear  = temp.DocumentYear ");

            if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {
                date = FormatData.parseDateShort3StringToDate(dateFrom.trim());
                dateFrom = FormatData.parseDateToTring(date);
                query.append(" and iss.ApprovalDate >= concat(date('"
                        + dateFrom + "'), ' 00:00:00') ");
            }
            if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {
                date = FormatData.parseDateShort3StringToDate(dateTo.trim());
                dateTo = FormatData.parseDateToTring(date);
                query.append(" and iss.ApprovalDate <= concat(date('" + dateTo
                        + "'), ' 23:59:59') ");
            }
            if (maritimeCode != null) {
                query.append(" and temp.MaritimeCode = " + maritimeCode);
            }

            if (documentTypeCode != null) {
                query.append(" and temp.DocumentTypeCode in ("
                        + documentTypeCode + ")");
            }

            if (requestState != null) {
                query.append(" and temp.requestState in (" + requestState
                        + ") ");
            }
            if (issRequestState != null) {
                query.append(" and iss.requestState in (" + issRequestState
                        + ") ");
            }
            if (documentStatusCode != null) {
                query.append(" and temp.documentStatusCode in ("
                        + documentStatusCode + ")");
            }

            query.append(" and iss.VersionNo = (SELECT MAX(iss2.VersionNo) "
                    + " FROM issue_port_clearance iss2 "
                    + " WHERE iss.DocumentName = iss2.DocumentName) "); // lttai
            // 13/7/2015
            // : lay
            // version
            // moi
            // nhat
            // cua
            // giay
            // phep

            log.debug("----query countTempDocumentJoinIssuePortClearanceForStatisticsReport----");
            log.debug(query.toString());

            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();


            
            count = (Long) queryFactory.getSingleResult(builder);
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {

        }
        return count;

    }

    public long countTempDocumentJoinIssueShiftingOrderForStatisticsReport(
            String maritimeCode, String dateFrom, String dateTo,
            String documentTypeCode, String requestState,
            String issRequestState, String documentStatusCode)
            throws SystemException {
        long count = 0L;

        Date date = null;
        try {

            StringBuilder query = new StringBuilder();

            query.append("select count(*) as total from temp_document temp ");
            query.append(" inner join issue_shifting_order  iss ");
            query.append(" on iss.DocumentName = temp.DocumentName ");
            query.append(" and   iss.DocumentYear  = temp.DocumentYear ");

            if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {
                date = FormatData.parseDateShort3StringToDate(dateFrom.trim());
                dateFrom = FormatData.parseDateToTring(date);
                query.append(" and iss.ApprovalDate >= concat(date('"
                        + dateFrom + "'), ' 00:00:00') ");
            }
            if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {
                date = FormatData.parseDateShort3StringToDate(dateTo.trim());
                dateTo = FormatData.parseDateToTring(date);
                query.append(" and iss.ApprovalDate <= concat(date('" + dateTo
                        + "'), ' 23:59:59') ");
            }
            if (maritimeCode != null) {
                query.append(" and temp.MaritimeCode = " + maritimeCode);
            }

            if (documentTypeCode != null) {
                query.append(" and temp.DocumentTypeCode in ("
                        + documentTypeCode + ")");
            }

            if (requestState != null) {
                query.append(" and temp.requestState in (" + requestState
                        + ") ");
            }
            if (issRequestState != null) {
                query.append(" and iss.requestState in (" + issRequestState
                        + ") ");
            }
            if (documentStatusCode != null) {
                query.append(" and temp.documentStatusCode in ("
                        + documentStatusCode + ") ");
            }

            query.append(" and iss.VersionNo = (SELECT MAX(iss2.VersionNo) "
                    + " FROM issue_shifting_order iss2 "
                    + " WHERE iss.DocumentName = iss2.DocumentName) "); // lttai
            // 13/7/2015
            // : lay
            // version
            // moi
            // nhat
            // cua
            // giay
            // phep

            // query.append(" GROUP BY temp.DocumentName "); // lttai
            log.debug("-----------------query countTempDocumentJoinIssueShiftingOrderForStatisticsReport------------------- ");
            log.debug(query.toString());

            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();


            
            count = (Long) queryFactory.getSingleResult(builder);
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {

        }
        return count;

    }

    public long countTempDocumentForStatisticsReport(String maritimeCode,
                                                     String dateFrom, String dateTo, String documentTypeCode,
                                                     String requestState, String documentStatusCode)
            throws SystemException {
        long count = 0L;

        Date date = null;
        try {

            StringBuilder query = new StringBuilder();

            query.append("SELECT count(*) as total FROM temp_document ");
            query.append(" WHERE 1 = 1 ");
            if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {
                date = FormatData.parseDateShort3StringToDate(dateFrom.trim());
                dateFrom = FormatData.parseDateToTring(date);
                query.append(" and CreatedDate >= concat(date('" + dateFrom
                        + "'), ' 00:00:00') ");
            }
            if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {
                date = FormatData.parseDateShort3StringToDate(dateTo.trim());
                dateTo = FormatData.parseDateToTring(date);
                query.append(" and CreatedDate <= concat(date('" + dateTo
                        + "'), ' 23:59:59') ");
            }
            if (maritimeCode != null) {
                query.append(" and MaritimeCode = " + maritimeCode);
            }

            if (documentTypeCode != null) {
                query.append(" and DocumentTypeCode in (" + documentTypeCode
                        + ")");
            }

            if (requestState != null) {
                query.append(" and requestState in (" + requestState + ")");
            }
            if (documentStatusCode != null) {
                query.append(" and documentStatusCode in ("
                        + documentStatusCode + ")");
            }

            log.debug("-----------------query countTempDocumentForStatisticsReport------------------- ");
            log.debug(query.toString());

            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();


            
            count = (Long) queryFactory.getSingleResult(builder);
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {

        }
        return count;

    }

    public long countTempDocumentCompletionForStatisticsReport(
            String maritimeCode, String dateFrom, String dateTo,
            String documentTypeCode, String requestState,
            String documentStatusCode) throws SystemException {
        long count = 0L;

        Date date = null;
        try {

            StringBuilder query = new StringBuilder();

            query.append("SELECT count(*) as total FROM temp_document temp ");
            query.append(" inner join result_completion cmpl ");
            query.append(" on cmpl.DocumentName = temp.DocumentName ");
            query.append(" and   cmpl.DocumentYear  = temp.DocumentYear ");
            query.append(" and   cmpl.RequestState in (19, 12) and length(cmpl.ResponseTimeCVHH) > 0 ");

            if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {
                date = FormatData.parseDateShort3StringToDate(dateFrom.trim());
                dateFrom = FormatData.parseDateToTring(date);
                query.append(" and cmpl.ResponseTimeCVHH >= concat(date('"
                        + dateFrom + "'), ' 00:00:00') ");
            }
            if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {
                date = FormatData.parseDateShort3StringToDate(dateTo.trim());
                dateTo = FormatData.parseDateToTring(date);
                query.append(" and cmpl.ResponseTimeCVHH <= concat(date('"
                        + dateTo + "'), ' 23:59:59') ");
            }

            query.append(" WHERE 1 = 1 ");

            if (maritimeCode != null) {
                query.append(" and temp.MaritimeCode = " + maritimeCode);
            }

            if (documentTypeCode != null) {
                query.append(" and temp.DocumentTypeCode in ("
                        + documentTypeCode + ")");
            }

            log.debug("-----------------query countTempDocumentCompletionForStatisticsReport------------------- "
                    + query);
            log.debug(query.toString());

            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();


            
            count = (Long) queryFactory.getSingleResult(builder);
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {

        }
        return count;

    }

    public List<FlowChartDataByDate> countTempDocumentForStatisticsByDate(
            String maritimeCode, String dateFrom, String dateTo,
            String documentTypeCode, String requestState,
            String documentStatusCode) throws SystemException {


        Date date = null;
        List<FlowChartDataByDate> result = null;
        FlowChartDataByDate flowChartDataByDate = null;
        try {

            StringBuilder query = new StringBuilder();

            query.append("SELECT count(*) as documentNumber, Date(CreatedDate) as chartDate FROM temp_document ");
            query.append(" WHERE 1 = 1 ");
            if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {
                date = FormatData.parseDateShort3StringToDate(dateFrom.trim());
                dateFrom = FormatData.parseDateToTring(date);
                query.append(" and CreatedDate >= concat(date('" + dateFrom
                        + "'), ' 00:00:00') ");

            }
            if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {
                date = FormatData.parseDateShort3StringToDate(dateTo.trim());
                dateTo = FormatData.parseDateToTring(date);
                query.append(" and CreatedDate <= concat(date('" + dateTo
                        + "'), ' 23:59:59') ");

            }
            if (maritimeCode != null) {
                query.append(" and MaritimeCode = " + maritimeCode);
            }

            if (documentTypeCode != null) {
                query.append(" and DocumentTypeCode in (" + documentTypeCode
                        + ")");
            }

            if (requestState != null) {
                query.append(" and requestState in (" + requestState + ")");
            }
            if (documentStatusCode != null) {
                query.append(" and documentStatusCode in ("
                        + documentStatusCode + ")");
            }
            query.append(" group by Date(CreatedDate)");
            query.append(" order by Date(CreatedDate) asc");

            // log.debug("-----------------query
            // countTempDocumentForStatisticsReport------------------- ");
            // log.debug(query.toString());
            // System.out.println("---------------------------------------------------------------");
            // System.out.println(query.toString());

            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Integer.class).build();


            List listDateAndCount =  queryFactory.getResultList(builder);
            for (Iterator iter = listDateAndCount.iterator(); iter.hasNext();) {
                Object object[] = (Object[]) iter.next();
                flowChartDataByDate = new FlowChartDataByDate();
                flowChartDataByDate.setDocumentNumber(((Long) object[0]).longValue());
                flowChartDataByDate.setChartDate(((String) object[1]).substring(8, 10));
                if (result == null) {
                    result = new ArrayList<FlowChartDataByDate>();
                }
                result.add(flowChartDataByDate);
                // System.out.println("Ngay: "+
                // flowChartDataByDate.getChartDate() +
                // " so tau: "+flowChartDataByDate.getDocumentNumber());
            }
            return result;

    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
}

    public List<TempDocument> findTempDocumentByKeHoach(String requestState,
                                                        String documentTypeCode, String maritimeCode, String shipName,
                                                        String positionCode, String portRegionCode, String mabankhai,
                                                        String stateCode, String shipDateFromStart, String shipDateFromEnd,
                                                        String shipDateToStart, String shipDateToEnd, String callSign,
                                                        String imo, String ngayLamThuTucFrom, String ngayLamThuTucTo,
                                                        int start, int end) throws SystemException {
        
        try {

            StringBuilder query = new StringBuilder();

            String sql = "SELECT * FROM temp_document WHERE ";

            if (Validator.isNotNull(requestState)
                    && requestState.trim().length() > 0) {
                // if (requestState.trim().equalsIgnoreCase("114")) {
                // // requestState = "14"; // tuong duong trang thai Cho cap
                // lenh
                // dieu dong
                // query.append(" RequestState in (" + requestState + ") ");
                // if (
                // documentTypeCode.equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                // || documentTypeCode.equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                // || documentTypeCode.equals(MessageType.LOAT_THU_TUC_VAO_CANG)
                // )
                // {
                // query.append(" AND DocumentName in (Select distinct
                // DocumentName from issue_shifting_order where StampStatus = 0
                // and IsCancel = 0 and LENGTH(CancelNote) > 0) ");
                // }
                // } else {
                // query.append(" RequestState in (" + requestState + ") ");
                // }
                query.append(" RequestState in (" + requestState + ") ");
            }

            if (Validator.isNotNull(documentTypeCode)
                    && documentTypeCode.trim().length() > 0) {
                query.append(" AND DocumentTypeCode= :documentTypeCode ");
            }

            query.append(" AND DocumentTypeCode NOT LIKE 'HH_%' ");

            // cang vu hang hai
            if (Validator.isNotNull(maritimeCode)
                    && maritimeCode.trim().length() > 0
                    && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
                query.append(" AND MaritimeCode= :maritimeCode");
            }

            // ten tau
            if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
                shipName = shipName.trim();
                query.append(" AND ShipName like '%" + shipName.trim() + "%' ");
            }

            // vi tri tau di chuyen
            if (Validator.isNotNull(positionCode)
                    && positionCode.trim().length() > 0) {
                query.append(" AND ShipPosition= :positionCode");
            }

            // khu vuc cang
            if (Validator.isNotNull(portRegionCode)
                    && portRegionCode.trim().length() > 0) {
                query.append(" AND PortRegionCode= :portRegionCode");
            }

            // Ma ban khai documentName
            if (Validator.isNotNull(mabankhai) && mabankhai.trim().length() > 0) {
                query.append(" AND DocumentName= :mabankhai");
            }

            // Quoc tich
            if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
                query.append(" AND StateCode= :stateCode");
            }

            // thoi gian den FROM
            if (Validator.isNotNull(shipDateFromStart)
                    && shipDateFromStart.trim().length() > 0) {

                Date dateFromStart = FormatData
                        .parseDateShort3StringToDate(shipDateFromStart.trim());
                shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
                query.append(" AND (ShipDateFrom >= concat(date('"
                        + shipDateFromStart
                        + "'), ' 00:00:00')  OR ShipDateFrom IS NULL)");
            }

            // thoi gian den TO
            if (Validator.isNotNull(shipDateFromEnd)
                    && shipDateFromEnd.trim().length() > 0) {

                Date dateFromEnd = FormatData
                        .parseDateShort3StringToDate(shipDateFromEnd.trim());
                shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
                query.append(" AND (ShipDateFrom <= concat(date('"
                        + shipDateFromEnd
                        + "'), ' 23:59:59')  OR ShipDateFrom IS NULL)");
            }

            // Thoi gian roi FROM
            if (Validator.isNotNull(shipDateToStart)
                    && shipDateToStart.trim().length() > 0) {

                Date dateToStart = FormatData
                        .parseDateShort3StringToDate(shipDateToStart.trim());
                shipDateToStart = FormatData.parseDateToTring(dateToStart);
                query.append(" AND (ShipDateTo >= concat(date('"
                        + shipDateToStart
                        + "'), ' 00:00:00')  OR ShipDateTo IS NULL)");
            }

            // Thoi gian roi TO
            if (Validator.isNotNull(shipDateToEnd)
                    && shipDateToEnd.trim().length() > 0) {

                Date dateToEnd = FormatData
                        .parseDateShort3StringToDate(shipDateToEnd.trim());
                shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
                query.append(" AND (ShipDateTo <= concat(date('"
                        + shipDateToEnd
                        + "'), ' 23:59:59')  OR ShipDateTo IS NULL)");
            }

            // ngay gio lam thu tuc
            // ngayLamThuTucFrom
            // ngayLamThuTucTo
            if (Validator.isNotNull(ngayLamThuTucFrom)
                    && ngayLamThuTucFrom.trim().length() > 0) {
                Date dateLamThuTucFrom = FormatData
                        .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
                ngayLamThuTucFrom = FormatData
                        .parseDateToTring(dateLamThuTucFrom);
                query.append(" AND (CreatedDate >= concat(date('"
                        + ngayLamThuTucFrom
                        + "'), ' 00:00:00')  OR CreatedDate IS NULL)");
            }

            if (Validator.isNotNull(ngayLamThuTucTo)
                    && ngayLamThuTucTo.trim().length() > 0) {
                Date dateLamThuTucTo = FormatData
                        .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
                ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
                query.append(" AND (CreatedDate <= concat(date('"
                        + ngayLamThuTucTo
                        + "'), ' 23:59:59')  OR CreatedDate IS NULL)");
            }

            // Ho hieu
            if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
                query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
            }
            if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
                query.append(" AND imo like '%" + imo.trim() + "%' ");
            }

            /**
             * I. KÃ¡ÂºÂ¾ HOÃ¡ÂºÂ CH 1. CHÃ¡Â»Å“ TIÃ¡ÂºÂ¾P NHÃ¡ÂºÂ¬N - 11 2.
             * YÃƒÅ U CÃ¡ÂºÂ¦U SÃ¡Â»Â¬A Ã„ï¿½Ã¡Â»Å½I BÃ¡Â»â€� SUNG - 27 3.
             * CHÃ¡Â»Å“ CÃ¡ÂºÂ¤P LÃ¡Â»â€ NH Ã„ï¿½IÃ¡Â»â‚¬U Ã„ï¿½Ã¡Â»ËœNG - 14 4.
             * TÃ¡Â»Âª CHÃ¡Â»ï¿½I TIÃ¡ÂºÂ¾P NHÃ¡ÂºÂ¬N - 13 5. HÃ¡Â»Â¦Y
             * LÃ¡Â»â€ NH Ã„ï¿½IÃ¡Â»â‚¬U Ã„ï¿½Ã¡Â»ËœNG - 16 6. Ã„ï¿½ÃƒÆ’
             * CÃ¡ÂºÂ¤P LÃ¡Â»â€ NH Ã„ï¿½IÃ¡Â»â‚¬U Ã„ï¿½Ã¡Â»ËœNG - 15 7.
             * Ã„ï¿½ÃƒÆ’ CÃ¡ÂºÂ¤P LÃ¡Â»â€ NH Ã„ï¿½IÃ¡Â»â‚¬U Ã„ï¿½Ã¡Â»ËœNG - 12
             */

            query.append(" ORDER BY ");
            // query.append("DocumentName DESC, ");
            query.append("case when RequestState = '11' then 1 ");
            query.append("when RequestState = '27' then 2 ");
            query.append("when RequestState = '14' then 3 ");
            query.append("when RequestState = '13' then 4 ");
            query.append("when RequestState = '16' then 5 ");
            query.append("when RequestState = '15' then 6 ");
            query.append("when RequestState = '12' then 7 ");
            query.append("end ASC ");

            // TODO KE HOACH sap xep thu tu ho so
            if (Validator.isNotNull(documentTypeCode)
                    && documentTypeCode.trim().length() > 0
                    && (documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_VAO_CANG) || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND))) {

                query.append(", ShipDateFrom DESC, CreatedDate DESC, id DESC");

            } else if (Validator.isNotNull(documentTypeCode)
                    && documentTypeCode.trim().length() > 0
                    && (documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_ROI_CANG) || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
                query.append(", ShipDateTo DESC, CreatedDate DESC, id DESC");
            } else {
                query.append(", ShipDateFrom DESC, ShipDateTo DESC, CreatedDate DESC, id DESC");
            }

            sql = sql + query.toString();
            if (start >= 0) {
                int count = end - start;
                sql = sql + " limit " + start + "," + count;
            }
            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();


            log.debug("===documentTypeCode===" + documentTypeCode + "===");
            if (Validator.isNotNull(documentTypeCode)
                    && documentTypeCode.trim().length() > 0) {
                builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
            }

            if (Validator.isNotNull(maritimeCode)
                    && maritimeCode.trim().length() > 0
                    && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
                builder.appendNamedParameterMap("maritimeCode", maritimeCode.trim());
            }
            if (Validator.isNotNull(positionCode)
                    && positionCode.trim().length() > 0) {
                builder.appendNamedParameterMap("positionCode", positionCode.trim());
                log.debug("===positionCode==" + positionCode);
            }
            if (Validator.isNotNull(portRegionCode)
                    && portRegionCode.trim().length() > 0) {
                builder.appendNamedParameterMap("portRegionCode", portRegionCode.trim());
                log.debug("===portRegionCode==" + portRegionCode);
            }
            if (Validator.isNotNull(mabankhai) && mabankhai.trim().length() > 0) {
                builder.appendNamedParameterMap("documentName", mabankhai.trim());
                log.debug("===mabankhai==" + mabankhai);
            }
            if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
                builder.appendNamedParameterMap("stateCode", stateCode.trim().toUpperCase());
                log.debug("===stateCode==" + stateCode);
            }

            log.debug("===shipDateFromStart==" + shipDateFromStart);
            log.debug("===shipDateFromEnd==" + shipDateFromEnd);
            log.debug("===shipDateToStart==" + shipDateToStart);
            log.debug("===shipDateToEnd==" + shipDateToEnd);

            return (List<TempDocument>)  queryFactory.getResultList(builder);


        }catch (Exception e) {
            throw new SystemException(e);
        } finally {
        }
    }



    public List<TempDocument> findTempDocumentByKeHoach(String requestState,
                                                    String documentTypeCode, String maritimeCode, String shipName,
                                                    String positionCode, String portRegionCode, String mabankhai,
                                                    String stateCode, String shipDateFromStart, String shipDateFromEnd,
                                                    String shipDateToStart, String shipDateToEnd, String callSign,
                                                    String imo, String ngayLamThuTucFrom, String ngayLamThuTucTo,
                                                    String nameOfShipownersAgents, String portRegionCodeNext,
                                                    int start, int end) throws SystemException {

    try {

        StringBuilder query = new StringBuilder();

        String sql = "SELECT * FROM temp_document WHERE ";

        if (Validator.isNotNull(requestState)
                && requestState.trim().length() > 0) {
            query.append(" RequestState in (" + requestState + ") ");
        }

        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0) {
            query.append(" AND DocumentTypeCode= :documentTypeCode ");
        }

        query.append(" AND DocumentTypeCode NOT LIKE 'HH_%' ");

        // cang vu hang hai
        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            query.append(" AND MaritimeCode= :maritimeCode");
        }

        // ten tau
        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            shipName = shipName.trim();
            query.append(" AND ShipName like '%" + shipName.trim() + "%' ");
        }

        // vi tri tau di chuyen
        if (Validator.isNotNull(positionCode)
                && positionCode.trim().length() > 0) {
            query.append(" AND ShipPosition= :positionCode");
        }

        // khu vuc cang
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            query.append(" AND PortRegionCode= :portRegionCode");
        }

        // Ma ban khai documentName
        if (Validator.isNotNull(mabankhai) && mabankhai.trim().length() > 0) {
            query.append(" AND DocumentName= :mabankhai");
        }

        // Quoc tich
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            query.append(" AND StateCode= :stateCode");
        }

        // thoi gian den FROM
        if (Validator.isNotNull(shipDateFromStart)
                && shipDateFromStart.trim().length() > 0) {

            Date dateFromStart = FormatData
                    .parseDateShort3StringToDate(shipDateFromStart.trim());
            shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
            query.append(" AND (ShipDateFrom >= concat(date('"
                    + shipDateFromStart
                    + "'), ' 00:00:00')  OR ShipDateFrom IS NULL)");
        }

        // thoi gian den TO
        if (Validator.isNotNull(shipDateFromEnd)
                && shipDateFromEnd.trim().length() > 0) {

            Date dateFromEnd = FormatData
                    .parseDateShort3StringToDate(shipDateFromEnd.trim());
            shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
            query.append(" AND (ShipDateFrom <= concat(date('"
                    + shipDateFromEnd
                    + "'), ' 23:59:59')  OR ShipDateFrom IS NULL)");
        }

        // Thoi gian roi FROM
        if (Validator.isNotNull(shipDateToStart)
                && shipDateToStart.trim().length() > 0) {

            Date dateToStart = FormatData
                    .parseDateShort3StringToDate(shipDateToStart.trim());
            shipDateToStart = FormatData.parseDateToTring(dateToStart);
            query.append(" AND (ShipDateTo >= concat(date('"
                    + shipDateToStart
                    + "'), ' 00:00:00')  OR ShipDateTo IS NULL)");
        }

        // Thoi gian roi TO
        if (Validator.isNotNull(shipDateToEnd)
                && shipDateToEnd.trim().length() > 0) {

            Date dateToEnd = FormatData
                    .parseDateShort3StringToDate(shipDateToEnd.trim());
            shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
            query.append(" AND (ShipDateTo <= concat(date('"
                    + shipDateToEnd
                    + "'), ' 23:59:59')  OR ShipDateTo IS NULL)");
        }

        // ngay gio lam thu tuc
        // ngayLamThuTucFrom
        // ngayLamThuTucTo
        if (Validator.isNotNull(ngayLamThuTucFrom)
                && ngayLamThuTucFrom.trim().length() > 0) {
            Date dateLamThuTucFrom = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
            ngayLamThuTucFrom = FormatData
                    .parseDateToTring(dateLamThuTucFrom);
            query.append(" AND (CreatedDate >= concat(date('"
                    + ngayLamThuTucFrom
                    + "'), ' 00:00:00')  OR CreatedDate IS NULL)");
        }

        if (Validator.isNotNull(ngayLamThuTucTo)
                && ngayLamThuTucTo.trim().length() > 0) {
            Date dateLamThuTucTo = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
            ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
            query.append(" AND (CreatedDate <= concat(date('"
                    + ngayLamThuTucTo
                    + "'), ' 23:59:59')  OR CreatedDate IS NULL)");
        }

        // Ho hieu
        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
        }
        if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
            query.append(" AND imo like '%" + imo.trim() + "%' ");
        }

        if (Validator.isNotNull(nameOfShipownersAgents)
                && nameOfShipownersAgents.trim().length() > 0) {
            query.append(" AND NameOfShipownersAgents like '%"
                    + nameOfShipownersAgents.trim() + "%' ");
        }

        query.append(" ORDER BY ");
        // query.append("DocumentName DESC, ");
        query.append("case when RequestState = '11' then 1 ");
        query.append("when RequestState = '27' then 2 ");
        query.append("when RequestState = '14' then 3 ");
        query.append("when RequestState = '13' then 4 ");
        query.append("when RequestState = '16' then 5 ");
        query.append("when RequestState = '15' then 6 ");
        query.append("when RequestState = '12' then 7 ");
        query.append("end ASC ");

        // TODO KE HOACH sap xep thu tu ho so
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND))) {

            query.append(", ShipDateFrom DESC, CreatedDate DESC, id DESC");

        } else if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
            query.append(", ShipDateTo DESC, CreatedDate DESC, id DESC");
        } else {
            query.append(", ShipDateFrom DESC, ShipDateTo DESC, CreatedDate DESC, id DESC");
        }

        sql = sql + query.toString();
        if (start >= 0) {
            int count = end - start;
            sql = sql + " limit " + start + "," + count;
        }
        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();

        log.debug("===findTempDocumentByKeHoach==" + sql.toString());
        // log.info("===findTempDocumentByKeHoach==" );

        log.debug("===documentTypeCode===" + documentTypeCode + "===");
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0) {
            builder.appendNamedParameterMap("documentTypeCode",documentTypeCode);

        }

        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
            log.debug("===maritimeCode==" + maritimeCode);
        }

        if (Validator.isNotNull(positionCode)
                && positionCode.trim().length() > 0) {
            builder.appendNamedParameterMap("positionCode",positionCode.trim());
            log.debug("===positionCode==" + positionCode);
        }
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            builder.appendNamedParameterMap("portRegionCode",portRegionCode.trim());
            log.debug("===portRegionCode==" + portRegionCode);
        }
        if (Validator.isNotNull(mabankhai) && mabankhai.trim().length() > 0) {
            builder.appendNamedParameterMap("mabankhai",mabankhai.trim());
            log.debug("===mabankhai==" + mabankhai);
        }
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            builder.appendNamedParameterMap("stateCode",stateCode.trim().toUpperCase());
            log.debug("===stateCode==" + stateCode);
        }

        log.debug("===shipDateFromStart==" + shipDateFromStart);
        log.debug("===shipDateFromEnd==" + shipDateFromEnd);
        log.debug("===shipDateToStart==" + shipDateToStart);
        log.debug("===shipDateToEnd==" + shipDateToEnd);

        return (List<TempDocument>)  queryFactory.getResultList(builder);

    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
}

public List<TempDocument> findTempDocumentByDocumentStatusCode(
        String documentStatusCode, String documentTypeCode, int start,
        int end) throws SystemException {

    try {

        if (Validator.isNotNull(documentStatusCode)
                && documentStatusCode.trim().length() > 0
                && Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0) {
            StringBuilder query = new StringBuilder();

            query.append("SELECT * FROM temp_document ");
            query.append(" WHERE DocumentStatusCode in ("
                    + documentStatusCode
                    + ") AND DocumentTypeCode= :documentTypeCode AND DocumentTypeCode NOT LIKE 'HH_%' ");

            String sql = query.toString();
            if (start >= 0) {
                int count = end - start;
                sql = sql + " limit " + start + "," + count;
            }
            log.debug("=========findTempDocumentByDocumentStatusCode========"
                    + sql);
            log.debug("=========findTempDocumentByDocumentStatusCode========"
                    + sql);
            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();

            builder.appendNamedParameterMap("documentTypeCode",documentTypeCode);

            // execute the query and return a list from the db
            if (start >= 0) {
                return (List<TempDocument>)  queryFactory.getResultList(builder);
            }
        }
    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }

    return new ArrayList<TempDocument>();
}

public List<TempDocument> findTempDocumentByRequestState(
        String requestState, String documentTypeCode, int start, int end)
        throws SystemException {

    try {

        if (Validator.isNotNull(requestState)
                && requestState.trim().length() > 0
                && Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0) {
            StringBuilder query = new StringBuilder();

            query.append("SELECT * FROM temp_document ");
            query.append(" WHERE RequestState in ("
                    + requestState
                    + ") AND DocumentTypeCode= :documentTypeCode AND DocumentTypeCode NOT LIKE 'HH_%' ");

            String sql = query.toString();
            if (start >= 0) {
                int count = end - start;
                sql = sql + " limit " + start + "," + count;
            }
            log.debug("=========findTempDocumentByRequestState========"
                    + sql);

            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();


            builder.appendNamedParameterMap("documentTypeCode",documentTypeCode);

            // execute the query and return a list from the db
            if (start >= 0) {
                return (List<TempDocument>)  queryFactory.getResultList(builder);
            }
        }
    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }

    return new ArrayList<TempDocument>();
}

public int countTempDocumentByRequestState(String requestState,
                                           String documentTypeCode) throws SystemException { int count = 0; try {


    if (Validator.isNotNull(requestState)
            && requestState.trim().length() > 0
            && Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT count(*) as total FROM temp_document ");
        query.append(" WHERE RequestState in ("
                + requestState
                + ") AND DocumentTypeCode= :documentTypeCode AND DocumentTypeCode NOT LIKE 'HH_%' ");

        String sql = query.toString();
        log.debug("=========countTempDocumentByRequestState========"
                + sql);
        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();

        builder.appendNamedParameterMap("documentTypeCode",documentTypeCode);

        
        count = (Integer) queryFactory.getSingleResult(builder);
    }

} catch (Exception e) {
    throw new SystemException(e);
} finally {

}
    return count;
}

public int countTempDocumentByKeHoach(String requestState,
                                      String documentTypeCode, String maritimeCode, String shipName,
                                      String positionCode, String portRegionCode, String mabankhai,
                                      String stateCode, String shipDateFromStart, String shipDateFromEnd,
                                      String shipDateToStart, String shipDateToEnd, String callSign,
                                      String imo, String ngayLamThuTucFrom, String ngayLamThuTucTo) throws SystemException { int count = 0; try {


    StringBuilder query = new StringBuilder();

    String sql = "SELECT count(*) as total FROM temp_document WHERE ";

    if (Validator.isNotNull(requestState)
            && requestState.trim().length() > 0) {
        // if (requestState.trim().equalsIgnoreCase("114")) {
        // // requestState = "14"; // tuong duong trang thai Cho cap
        // lenh
        // dieu dong
        // query.append(" RequestState in (" + requestState + ") ");
        // if (
        // documentTypeCode.equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
        // || documentTypeCode.equals(MessageType.LOAT_THU_TUC_QUA_CANH)
        // || documentTypeCode.equals(MessageType.LOAT_THU_TUC_VAO_CANG)
        // )
        // {
        // query.append(" AND DocumentName in (Select distinct
        // DocumentName from issue_shifting_order where StampStatus = 0
        // and IsCancel = 0 and LENGTH(CancelNote) > 0) ");
        // }
        // } else {
        // query.append(" RequestState in (" + requestState + ") ");
        // }
        query.append(" RequestState in (" + requestState + ") ");
    }

    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0) {
        query.append(" AND DocumentTypeCode= :documentTypeCode ");
    }

    query.append(" AND DocumentTypeCode NOT LIKE 'HH_%' ");

    // cang vu hang hai
    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        query.append(" AND MaritimeCode= :maritimeCode");
    }

    // ten tau
    if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
        shipName = shipName.trim();
        query.append(" AND ShipName like '%" + shipName.trim() + "%' ");
    }

    // vi tri tau di chuyen
    if (Validator.isNotNull(positionCode)
            && positionCode.trim().length() > 0) {
        query.append(" AND ShipPosition= :positionCode");
    }

    // khu vuc cang
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        query.append(" AND PortRegionCode= :portRegionCode");
    }

    // Ma ban khai documentName
    if (Validator.isNotNull(mabankhai) && mabankhai.trim().length() > 0) {
        query.append(" AND DocumentName= :mabankhai");
    }

    // Quoc tich
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        query.append(" AND StateCode= :stateCode");
    }

    // thoi gian den FROM
    if (Validator.isNotNull(shipDateFromStart)
            && shipDateFromStart.trim().length() > 0) {

        Date dateFromStart = FormatData
                .parseDateShort3StringToDate(shipDateFromStart.trim());
        shipDateFromStart = FormatData.parseDateToTring(dateFromStart);

        query.append(" AND (ShipDateFrom >= concat(date('"
                + shipDateFromStart
                + "'), ' 00:00:00')  OR ShipDateFrom IS NULL)");
    }

    // thoi gian den TO
    if (Validator.isNotNull(shipDateFromEnd)
            && shipDateFromEnd.trim().length() > 0) {
        Date dateFromEnd = FormatData
                .parseDateShort3StringToDate(shipDateFromEnd.trim());
        shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
        query.append(" AND (ShipDateFrom <= concat(date('"
                + shipDateFromEnd
                + "'), ' 23:59:59')  OR ShipDateFrom IS NULL)");
    }

    // Thoi gian roi FROM
    if (Validator.isNotNull(shipDateToStart)
            && shipDateToStart.trim().length() > 0) {
        Date dateToStart = FormatData
                .parseDateShort3StringToDate(shipDateToStart.trim());
        shipDateToStart = FormatData.parseDateToTring(dateToStart);
        query.append(" AND (ShipDateTo >= concat(date('"
                + shipDateToStart
                + "'), ' 00:00:00')  OR ShipDateTo IS NULL)");
    }

    // Thoi gian roi TO
    if (Validator.isNotNull(shipDateToEnd)
            && shipDateToEnd.trim().length() > 0) {
        Date dateToEnd = FormatData
                .parseDateShort3StringToDate(shipDateToEnd.trim());
        shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
        query.append(" AND (ShipDateTo <= concat(date('"
                + shipDateToEnd
                + "'), ' 23:59:59')  OR ShipDateTo IS NULL)");
    }

    // ngay gio lam thu tuc
    // ngayLamThuTucFrom
    // ngayLamThuTucTo
    if (Validator.isNotNull(ngayLamThuTucFrom)
            && ngayLamThuTucFrom.trim().length() > 0) {
        Date dateLamThuTucFrom = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
        ngayLamThuTucFrom = FormatData
                .parseDateToTring(dateLamThuTucFrom);
        query.append(" AND (CreatedDate >= concat(date('"
                + ngayLamThuTucFrom
                + "'), ' 00:00:00')  OR CreatedDate IS NULL)");
    }

    if (Validator.isNotNull(ngayLamThuTucTo)
            && ngayLamThuTucTo.trim().length() > 0) {
        Date dateLamThuTucTo = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
        ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
        query.append(" AND (CreatedDate <= concat(date('"
                + ngayLamThuTucTo
                + "'), ' 23:59:59')  OR CreatedDate IS NULL)");
    }

    // Ho hieu
    if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
        query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
    }
    if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
        query.append(" AND imo like '%" + imo.trim() + "%' ");
    }
    sql = sql + query.toString();

    log.debug("==countTempDocumentByKeHoach==" + sql);

    QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();




    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0) {
        log.debug("==documentTypeCode==" + documentTypeCode);
        builder.appendNamedParameterMap("documentTypeCode",documentTypeCode.trim());
    }

    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
    }

    if (Validator.isNotNull(positionCode)
            && positionCode.trim().length() > 0) {
        builder.appendNamedParameterMap("positionCode",positionCode.trim());
    }
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        builder.appendNamedParameterMap("portRegionCode",portRegionCode.trim());
    }
    if (Validator.isNotNull(mabankhai) && mabankhai.trim().length() > 0) {
        builder.appendNamedParameterMap("mabankhai",mabankhai.trim());
    }
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        builder.appendNamedParameterMap("stateCode",stateCode.trim().toUpperCase());
    }
    System.out
            .println("TempDocumentFinderImpl.countTempDocumentByKeHoach(mabankhai)"
                    + mabankhai);

    count = (Integer) queryFactory.getSingleResult(builder);
} catch (Exception e) {
    throw new SystemException(e);
} finally {

}
    return count;
}

public int countTempDocumentByKeHoach(String requestState,
                                      String documentTypeCode, String maritimeCode, String shipName,
                                      String positionCode, String portRegionCode, String mabankhai,
                                      String stateCode, String shipDateFromStart, String shipDateFromEnd,
                                      String shipDateToStart, String shipDateToEnd, String callSign,
                                      String imo, String ngayLamThuTucFrom, String ngayLamThuTucTo,
                                      String nameOfShipownersAgents, String portRegionCodeNext) throws SystemException { int count = 0; try {


    StringBuilder query = new StringBuilder();

    String sql = "SELECT count(*) as total FROM temp_document WHERE ";

    if (Validator.isNotNull(requestState)
            && requestState.trim().length() > 0) {
        query.append(" RequestState in (" + requestState + ") ");
    }

    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0) {
        query.append(" AND DocumentTypeCode= :documentTypeCode ");
    }

    query.append(" AND DocumentTypeCode NOT LIKE 'HH_%' ");

    // cang vu hang hai
    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        query.append(" AND MaritimeCode= :maritimeCode");
    }

    // ten tau
    if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
        shipName = shipName.trim();
        query.append(" AND ShipName like '%" + shipName.trim() + "%' ");
    }

    // vi tri tau di chuyen
    if (Validator.isNotNull(positionCode)
            && positionCode.trim().length() > 0) {
        query.append(" AND ShipPosition= :positionCode");
    }

    // khu vuc cang
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        query.append(" AND PortRegionCode= :portRegionCode");
    }

    // Ma ban khai documentName
    if (Validator.isNotNull(mabankhai) && mabankhai.trim().length() > 0) {
        query.append(" AND DocumentName= :mabankhai");
    }

    // Quoc tich
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        query.append(" AND StateCode= :stateCode");
    }

    // thoi gian den FROM
    if (Validator.isNotNull(shipDateFromStart)
            && shipDateFromStart.trim().length() > 0) {

        Date dateFromStart = FormatData
                .parseDateShort3StringToDate(shipDateFromStart.trim());
        shipDateFromStart = FormatData.parseDateToTring(dateFromStart);

        query.append(" AND (ShipDateFrom >= concat(date('"
                + shipDateFromStart
                + "'), ' 00:00:00')  OR ShipDateFrom IS NULL)");
    }

    // thoi gian den TO
    if (Validator.isNotNull(shipDateFromEnd)
            && shipDateFromEnd.trim().length() > 0) {
        Date dateFromEnd = FormatData
                .parseDateShort3StringToDate(shipDateFromEnd.trim());
        shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
        query.append(" AND (ShipDateFrom <= concat(date('"
                + shipDateFromEnd
                + "'), ' 23:59:59')  OR ShipDateFrom IS NULL)");
    }

    // Thoi gian roi FROM
    if (Validator.isNotNull(shipDateToStart)
            && shipDateToStart.trim().length() > 0) {
        Date dateToStart = FormatData
                .parseDateShort3StringToDate(shipDateToStart.trim());
        shipDateToStart = FormatData.parseDateToTring(dateToStart);
        query.append(" AND (ShipDateTo >= concat(date('"
                + shipDateToStart
                + "'), ' 00:00:00')  OR ShipDateTo IS NULL)");
    }

    // Thoi gian roi TO
    if (Validator.isNotNull(shipDateToEnd)
            && shipDateToEnd.trim().length() > 0) {
        Date dateToEnd = FormatData
                .parseDateShort3StringToDate(shipDateToEnd.trim());
        shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
        query.append(" AND (ShipDateTo <= concat(date('"
                + shipDateToEnd
                + "'), ' 23:59:59')  OR ShipDateTo IS NULL)");
    }

    // ngay gio lam thu tuc
    // ngayLamThuTucFrom
    // ngayLamThuTucTo
    if (Validator.isNotNull(ngayLamThuTucFrom)
            && ngayLamThuTucFrom.trim().length() > 0) {
        Date dateLamThuTucFrom = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
        ngayLamThuTucFrom = FormatData
                .parseDateToTring(dateLamThuTucFrom);
        query.append(" AND (CreatedDate >= concat(date('"
                + ngayLamThuTucFrom
                + "'), ' 00:00:00')  OR CreatedDate IS NULL)");
    }

    if (Validator.isNotNull(ngayLamThuTucTo)
            && ngayLamThuTucTo.trim().length() > 0) {
        Date dateLamThuTucTo = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
        ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
        query.append(" AND (CreatedDate <= concat(date('"
                + ngayLamThuTucTo
                + "'), ' 23:59:59')  OR CreatedDate IS NULL)");
    }

    // Ho hieu
    if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
        query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
    }
    if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
        query.append(" AND imo like '%" + imo.trim() + "%' ");
    }
    if (Validator.isNotNull(nameOfShipownersAgents)
            && nameOfShipownersAgents.trim().length() > 0) {
        query.append(" AND NameOfShipownersAgents like '%"
                + nameOfShipownersAgents.trim() + "%' ");
    }
    // if (Validator.isNotNull(portRegionCodeNext) &&
    // portRegionCodeNext.trim().length() > 0) {
    // query.append(" AND NameOfShipownersAgents like '%" +
    // portRegionCodeNext.trim() + "%' ");
    // }

    sql = sql + query.toString();

    log.debug("==countTempDocumentByKeHoach==" + sql);

    QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();




    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0) {
        log.debug("==documentTypeCode==" + documentTypeCode);
        builder.appendNamedParameterMap("documentTypeCode",documentTypeCode.trim());
    }

    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
    }

    if (Validator.isNotNull(positionCode)
            && positionCode.trim().length() > 0) {
        builder.appendNamedParameterMap("positionCode",positionCode.trim());
    }
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        builder.appendNamedParameterMap("portRegionCode",portRegionCode.trim());
    }
    if (Validator.isNotNull(mabankhai) && mabankhai.trim().length() > 0) {
        builder.appendNamedParameterMap("mabankhai",mabankhai.trim());
    }
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        builder.appendNamedParameterMap("stateCode",stateCode.trim().toUpperCase());
    }
    System.out
            .println("TempDocumentFinderImpl.countTempDocumentByKeHoach(mabankhai)"
                    + mabankhai);

    count = (Integer) queryFactory.getSingleResult(builder);
} catch (Exception e) {
    throw new SystemException(e);
} finally {

}
    return count;
}

public List<TempDocument> findTempDocumentByThuTuc(String documentTypeCode,
                                                   String documentName, String documentYear, String maLoaiHoSo,
                                                   String shipName, String shipTypeCode, String stateCode,
                                                   String shipCaptain, String callSign, String userCreated, int start,
                                                   int end) throws SystemException {

    log.debug("=========THU TUC SEARCH --shipName========" + shipName);


    try {

        StringBuilder query = new StringBuilder();

        String sql = "SELECT * FROM temp_document WHERE 1=1 ";

        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0) {
            query.append(" AND DocumentTypeCode= :documentTypeCode ");
        }

        query.append(" AND DocumentTypeCode NOT LIKE 'HH_%' ");

        if (Validator.isNotNull(documentName)
                && documentName.trim().length() > 0
                && FormatData.convertToInt(documentName) > 0) {
            query.append(" AND  DocumentName=" + documentName.trim());
        }

        if (Validator.isNotNull(documentYear)
                && documentYear.trim().length() > 0
                && FormatData.convertToInt(documentYear) > 0) {
            query.append(" AND  DocumentYear=" + documentYear.trim());
        }

        // Ten tau
        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            query.append(" AND ShipName like '%" + shipName.trim() + "%' ");
        }

        // Loai TAU
        if (Validator.isNotNull(shipTypeCode)
                && shipTypeCode.trim().length() > 0) {
            query.append(" AND ShipTypeCode= :shipTypeCode");
        }

        // Quoc Tich
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            query.append(" AND StateCode= :stateCode");
        }

        // Ten thuyen truong
        if (Validator.isNotNull(shipCaptain)
                && shipCaptain.trim().length() > 0) {
            query.append(" AND ShipCaptain like '%" + shipCaptain.trim()
                    + "%' ");
        }

        // Ho hieu
        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
        }

        // Ma SO Thue
        if (Validator.isNotNull(userCreated)
                && userCreated.trim().length() > 0) {
            query.append(" AND UserCreated like '%" + userCreated.trim()
                    + "%' ");
        }

        query.append(" ORDER ShipDateFrom DESC, DocumentStatusCode ASC ,id DESC");

        sql = sql + query.toString();
        if (start >= 0) {
            int count = end - start;
            sql = sql + " limit " + start + "," + count;
        }
        log.debug("=========THU TUC SEARCH --findTempDocumentByThuTuc========");

        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();



        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0) {
            builder.appendNamedParameterMap("documentTypeCode",documentTypeCode);
        }

        // if (Validator.isNotNull(shipName) && shipName.trim().length() >
        // 0) {
        // builder.appendNamedParameterMap("ppppp"," '%"+shipName.trim()+"%' ");
        // }
        if (Validator.isNotNull(shipTypeCode)
                && shipTypeCode.trim().length() > 0) {
            builder.appendNamedParameterMap("shipTypeCode",shipTypeCode.trim());
        }
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            builder.appendNamedParameterMap("stateCode",stateCode.trim());
        }
        // if (Validator.isNotNull(shipCaptain) &&
        // shipCaptain.trim().length() > 0) {
        // builder.appendNamedParameterMap("ppppp"," %"+shipCaptain.trim()+"% ");
        // }
        // if (Validator.isNotNull(callSign) && callSign.trim().length() >
        // 0) {
        // builder.appendNamedParameterMap("ppppp"," %"+callSign.trim()+"% ");
        // }
        // if (Validator.isNotNull(userCreated) &&
        // userCreated.trim().length() > 0) {
        // builder.appendNamedParameterMap("ppppp"," %"+userCreated.trim()+"% ");
        // }

     return (List<TempDocument>)  queryFactory.getResultList(builder);

    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
}

public int countTempDocumentByThuTuc(String documentTypeCode,
                                     String documentName, String documentYear, String maLoaiHoSo,
                                     String shipName, String shipTypeCode, String stateCode,
                                     String shipCaptain, String callSign, String userCreated) throws SystemException { int count = 0; try {


    StringBuilder query = new StringBuilder();

    String sql = "SELECT count(*) as total FROM temp_document WHERE 1=1";

    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0) {
        query.append(" AND DocumentTypeCode= :documentTypeCode ");
    }

    query.append(" AND DocumentTypeCode NOT LIKE 'HH_%' ");

    if (Validator.isNotNull(documentName)
            && documentName.trim().length() > 0
            && FormatData.convertToInt(documentName) > 0) {
        query.append(" AND  DocumentName=" + documentName.trim());
    }

    if (Validator.isNotNull(documentYear)
            && documentYear.trim().length() > 0
            && FormatData.convertToInt(documentYear) > 0) {
        query.append(" AND  DocumentYear=" + documentYear.trim());
    }

    // Ten tau
    if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
        query.append(" AND ShipName like '%" + shipName.trim() + "%' ");
    }

    // Loai TAU
    if (Validator.isNotNull(shipTypeCode)
            && shipTypeCode.trim().length() > 0) {
        query.append(" AND ShipTypeCode= :shipTypeCode");
    }

    // Quoc Tich
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        query.append(" AND StateCode= :stateCode");
    }

    // Ten thuyen truong
    if (Validator.isNotNull(shipCaptain)
            && shipCaptain.trim().length() > 0) {
        query.append(" AND ShipCaptain like '%" + shipCaptain.trim()
                + "%' ");
    }

    // Ho hieu
    if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
        query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
    }

    // Ma SO Thue
    if (Validator.isNotNull(userCreated)
            && userCreated.trim().length() > 0) {
        query.append(" AND UserCreated like '%" + userCreated.trim()
                + "%' ");
    }

    sql = sql + query.toString();
    log.debug("=========THU TUC SEARCH --count TempDocument ========"
            + sql);

    QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();




    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0) {
        builder.appendNamedParameterMap("documentTypeCode",documentTypeCode);
    }

    // if (Validator.isNotNull(shipName) && shipName.trim().length() >
    // 0) {
    // builder.appendNamedParameterMap("ppppp"," '%"+shipName.trim()+"%' ");
    // }
    if (Validator.isNotNull(shipTypeCode)
            && shipTypeCode.trim().length() > 0) {
        builder.appendNamedParameterMap("shipTypeCode",shipTypeCode.trim());
    }
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        builder.appendNamedParameterMap("stateCode",stateCode.trim());
    }
    // if (Validator.isNotNull(shipCaptain) &&
    // shipCaptain.trim().length() > 0) {
    // builder.appendNamedParameterMap("ppppp"," %"+shipCaptain.trim()+"% ");
    // }
    // if (Validator.isNotNull(callSign) && callSign.trim().length() >
    // 0) {
    // builder.appendNamedParameterMap("ppppp"," %"+callSign.trim()+"% ");
    // }
    // if (Validator.isNotNull(userCreated) &&
    // userCreated.trim().length() > 0) {
    // builder.appendNamedParameterMap("ppppp"," %"+userCreated.trim()+"% ");
    // }


    count = (Integer) queryFactory.getSingleResult(builder);
} catch (Exception e) {
    throw new SystemException(e);
} finally {

}
    return count;
}

public List<TempDocument> findDanhSachHoSoRoleThuTuc(
        String documentTypeCode, String maritimeCode, String portCode,
        String lastPortCode, String shipName, String stateCode,
        String callSign, String imo, String shipPosition,
        String shipDateFromStart, String shipDateFromEnd,
        String shipDateToStart, String shipDateToEnd,
        String documentStatusCode, String arrivalShipAgency,
        String nameArrivalShipAgency, String departureShipAgency,
        String nameDepartureShipAgency, String portRegionCode,
        String ngayLamThuTucFrom, String ngayLamThuTucTo, String maBanKhai,
        int start, int end, boolean isDTND, boolean isDTNDCam)
        throws SystemException {


    try {

        StringBuilder query = new StringBuilder();

        String sql = "SELECT * FROM temp_document WHERE 1=1 ";

        // Pb NC,XC,QC
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0) {
            query.append(" AND DocumentTypeCode= :documentTypeCode ");
        }

        query.append(" AND DocumentTypeCode NOT LIKE 'HH_%' ");

        // CANG VU
        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            query.append(" AND MaritimeCode= :maritimeCode");
        }

        // CANG DEN
        if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
            query.append(" AND PortCode= :portCode");
        }

        // CANG ROI CUOI CUNG
        if (Validator.isNotNull(lastPortCode)
                && lastPortCode.trim().length() > 0) {
            query.append(" AND LastPortCode= :lastPortCode");
        }

        // TEN TAU
        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            query.append(" AND ShipName like '%" + shipName.trim() + "%' ");
        }

        // QUOC TICH
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            query.append(" AND StateCode= :stateCode");
        }

        // HO HIEU
        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
        }
        if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
            query.append(" AND imo like '%" + imo.trim() + "%' ");
        }
        // TAU DEN ROI
        if (Validator.isNotNull(shipPosition)
                && shipPosition.trim().length() > 0
                && FormatData.convertToInt(shipPosition) > 0) {
            query.append(" AND ShipPosition ="
                    + FormatData.convertToInt(shipPosition));
        }

        // thoi gian den FROM
        if (Validator.isNotNull(shipDateFromStart)
                && shipDateFromStart.trim().length() > 0) {
            Date dateFromStart = FormatData
                    .parseDateShort3StringToDate(shipDateFromStart.trim());
            shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
            query.append(" AND (ShipDateFrom >= concat(date('"
                    + shipDateFromStart
                    + "'), ' 00:00:00')  OR ShipDateFrom IS NULL)");
        }

        // thoi gian den To
        if (Validator.isNotNull(shipDateFromEnd)
                && shipDateFromEnd.trim().length() > 0) {
            Date dateFromEnd = FormatData
                    .parseDateShort3StringToDate(shipDateFromEnd.trim());
            shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
            query.append(" AND (ShipDateFrom <= concat(date('"
                    + shipDateFromEnd
                    + "'), ' 23:59:59')  OR ShipDateFrom IS NULL)");
        }

        // thoi gian roi FROM
        if (Validator.isNotNull(shipDateToStart)
                && shipDateToStart.trim().length() > 0) {
            Date dateToStart = FormatData
                    .parseDateShort3StringToDate(shipDateToStart.trim());
            shipDateToStart = FormatData.parseDateToTring(dateToStart);
            query.append(" AND (ShipDateTo >= concat(date('"
                    + shipDateToStart
                    + "'), ' 00:00:00')  OR ShipDateTo IS NULL)");
        }

        // thoi gian roi To
        if (Validator.isNotNull(shipDateToEnd)
                && shipDateToEnd.trim().length() > 0) {
            Date dateToEnd = FormatData
                    .parseDateShort3StringToDate(shipDateToEnd.trim());
            shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
            query.append(" AND (ShipDateTo <= concat(date('"
                    + shipDateToEnd
                    + "'), ' 23:59:59')  OR ShipDateTo IS NULL)");
        }

        // ngay gio lam thu tuc
        // ngayLamThuTucFrom
        // ngayLamThuTucTo
        if (Validator.isNotNull(ngayLamThuTucFrom)
                && ngayLamThuTucFrom.trim().length() > 0) {
            Date dateLamThuTucFrom = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
            ngayLamThuTucFrom = FormatData
                    .parseDateToTring(dateLamThuTucFrom);
            query.append(" AND (CreatedDate >= concat(date('"
                    + ngayLamThuTucFrom
                    + "'), ' 00:00:00')  OR CreatedDate IS NULL)");
        }

        if (Validator.isNotNull(ngayLamThuTucTo)
                && ngayLamThuTucTo.trim().length() > 0) {
            Date dateLamThuTucTo = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
            ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
            query.append(" AND (CreatedDate <= concat(date('"
                    + ngayLamThuTucTo
                    + "'), ' 23:59:59')  OR CreatedDate IS NULL)");
        }

        // ma~ ban khai
        if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
            query.append(" AND (DocumentName = '" + maBanKhai + "')");
        }

        // TRANG THAI
        if (Validator.isNotNull(documentStatusCode)
                && documentStatusCode.trim().length() > 0) {
            // if (documentStatusCode.equalsIgnoreCase("120")) {
            // // documentStatusCode = "20"; // tuong duong de nghi cap giay
            // phep
            //
            // query.append(" AND documentStatusCode IN (" +
            // documentStatusCode.trim() + ")");
            //
            // if
            // (documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_QUA_CANH))
            // {
            // query.append(" AND (DocumentName IN (Select distinct
            // DocumentName from issue_permission_for_transit where
            // StampStatus = 0 and IsCancel = 0 and LENGTH(CancelNote) > 0)
            // ) ");
            //
            // }
            // if
            // (documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
            // ||
            // documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_ROI_CANG)
            // ||
            // documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)){
            // query.append(" AND (DocumentName IN (Select distinct
            // DocumentName from issue_port_clearance where StampStatus = 0
            // and IsCancel = 0 and LENGTH(CancelNote) > 0) ) ");
            // }
            // if
            // (documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND))
            // {
            //
            // query.append(" AND (DocumentName IN (Select distinct
            // DocumentName from issue_acceptance where StampStatus = 0 and
            // IsCancel = 0 and LENGTH(CancelNote) > 0) ) ");
            // }
            // } else {
            // query.append(" AND documentStatusCode IN (" +
            // documentStatusCode.trim() + ")");
            // }
            query.append(" AND documentStatusCode IN ("
                    + documentStatusCode.trim() + ")");
        }

        // DAI LY DEN
        if (Validator.isNotNull(arrivalShipAgency)
                && arrivalShipAgency.trim().length() > 0
                && Validator.isNotNull(nameArrivalShipAgency)
                && nameArrivalShipAgency.trim().length() > 0) {

            // StringBuilder daiLyDen = new StringBuilder();
            // daiLyDen.append("SELECT ShipAgencyCode FROM dm_ship_agency
            // ");
            // daiLyDen.append("where ShipAgencyNameVN like '%" +
            // arrivalShipAgency.trim() + "%' ");

            query.append(" AND ArrivalShipAgency= :arrivalShipAgency");
            // query.append(" AND ArrivalShipAgency in ( " +
            // daiLyDen.toString() + " )");
        }

        // DAI LY DI
        if (Validator.isNotNull(departureShipAgency)
                && departureShipAgency.trim().length() > 0
                && Validator.isNotNull(nameDepartureShipAgency)
                && nameDepartureShipAgency.trim().length() > 0) {

            // StringBuilder daiLyDi = new StringBuilder();
            // daiLyDi.append("SELECT ShipAgencyCode FROM dm_ship_agency ");
            // daiLyDi.append("where ShipAgencyNameVN like '%" +
            // departureShipAgency.trim() + "%' ");

            query.append(" AND DepartureShipAgency= :departureShipAgency");
            // query.append(" AND DepartureShipAgency in ( " +
            // daiLyDi.toString() + " )");
        }

        // KHU VUC CANG
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            query.append(" AND PortRegionCode= :portRegionCode");
        }

        /**
         * II- THÃ¡Â»Â¦ TÃ¡Â»Â¤C 1. Ã„ï¿½ÃƒÆ’ TIÃ¡ÂºÂ¾P NHÃ¡ÂºÂ¬N - 18 2.
         * CHÃ¡Â»Å“ PHÃƒÅ  DUYÃ¡Â»â€ T HOÃƒâ‚¬N THÃƒâ‚¬NH THÃ¡Â»Â¦ TÃ¡Â»Â¤C
         * - 12 3. Ã„ï¿½Ã¡Â»â‚¬ NGHÃ¡Â»Å  CÃ¡ÂºÂ¤P GIÃ¡ÂºÂ¤Y PHÃƒâ€°P - 20
         * 4. YÃƒÅ U CÃ¡ÂºÂ¦Y SÃ¡Â»Â¬A Ã„ï¿½Ã¡Â»â€�I BÃ¡Â»â€� SUNG - 13 5.
         * PHÃƒÅ  DUYÃ¡Â»â€ T VÃƒâ‚¬ HOÃƒâ‚¬N THÃƒâ‚¬NH THÃ¡Â»Â¦ TÃ¡Â»Â¤C -
         * 19 6. HÃ¡Â»Â¦Y THÃ¡Â»Â¦ TÃ¡Â»Â¤C - 10
         */

        if (!isDTND && !isDTNDCam) {
            query.append(" AND DocumentTypeCode in ( 'NC', 'XC', 'QC', '4', '5', '8', '9', '10', '11', '12', '13' ) ");
        } else if (isDTND && isDTNDCam) {
            query.append(" AND DocumentTypeCode in ( '14', '15', '16', '17' ) ");
        } else {

            if (Validator.isNotNull(documentTypeCode)
                    && (documentTypeCode
                    .trim()
                    .equals(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6) || documentTypeCode
                    .trim()
                    .equals(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7))
                    || isDTND) {
                query.append(" AND DocumentTypeCode in ( '16', '17' ) ");
            }
            if (Validator.isNotNull(documentTypeCode)
                    && (documentTypeCode.trim().equals(
                    ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH) || documentTypeCode
                    .trim()
                    .equals(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH))
                    || isDTNDCam) {
                query.append(" AND DocumentTypeCode in ( '14', '15' ) ");
            }

        }

        query.append(" ORDER BY ");
        // query.append(" DocumentName DESC, ");
        query.append("case when DocumentStatusCode = '18' then 1 ");
        query.append("when DocumentStatusCode = '12' then 2 ");
        query.append("when DocumentStatusCode = '20' then 3 ");
        query.append("when DocumentStatusCode = '13' then 4 ");
        query.append("when DocumentStatusCode = '19' then 5 ");
        query.append("when DocumentStatusCode = '10' then 6 ");
        query.append("end asc ");

        // TODO THU TUC sap xep thu tu ho so
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND))) {
            query.append(", ShipDateFrom DESC, CreatedDate DESC, id DESC");

        } else if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
            query.append(", ShipDateTo DESC, CreatedDate DESC, id DESC");

        } else {
            query.append(", ShipDateFrom DESC, ShipDateTo DESC, CreatedDate DESC, id DESC");
        }

        sql = sql + query.toString();
        if (start >= 0) {
            int count = end - start;
            sql = sql + " limit " + start + "," + count;
        }

        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();

        log.debug("=========findDanhSachHoSoRoleThuTuc By THU TUC ==="
                + sql);


        // Pb NC,XC,QC,VC,RC
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0) {

            builder.appendNamedParameterMap("documentTypeCode",documentTypeCode.trim());
        }

        // CANG VU
        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
        }

        // CANG DEN
        if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
            builder.appendNamedParameterMap("portCode",portCode.trim());
        }

        // CANG ROI CUOI CUNG
        if (Validator.isNotNull(lastPortCode)
                && lastPortCode.trim().length() > 0) {
            builder.appendNamedParameterMap("lastPortCode",lastPortCode.trim());
        }

        // QUOC TICH
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            builder.appendNamedParameterMap("stateCode",stateCode.trim());
        }

        // DAI LY DEN
        if (Validator.isNotNull(arrivalShipAgency)
                && arrivalShipAgency.trim().length() > 0
                && Validator.isNotNull(nameArrivalShipAgency)
                && nameArrivalShipAgency.trim().length() > 0) {
            builder.appendNamedParameterMap("arrivalShipAgency",arrivalShipAgency.trim());
        }

        // DAI LY DI
        if (Validator.isNotNull(departureShipAgency)
                && departureShipAgency.trim().length() > 0
                && Validator.isNotNull(nameDepartureShipAgency)
                && nameDepartureShipAgency.trim().length() > 0) {
            builder.appendNamedParameterMap("departureShipAgency",departureShipAgency.trim());
        }

        // KHU VUC CANG
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            builder.appendNamedParameterMap("portRegionCode",portRegionCode.trim());
        }

            return (List<TempDocument>)  queryFactory.getResultList(builder);

    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
}

public List<TempDocument> findDanhSachHoSoRoleThuTuc(
        String documentTypeCode, String maritimeCode, String portCode,
        String lastPortCode, String shipName, String stateCode,
        String callSign, String imo, String shipPosition,
        String shipDateFromStart, String shipDateFromEnd,
        String shipDateToStart, String shipDateToEnd,
        String documentStatusCode, String arrivalShipAgency,
        String nameArrivalShipAgency, String departureShipAgency,
        String nameDepartureShipAgency, String portRegionCode,
        String ngayLamThuTucFrom, String ngayLamThuTucTo, String maBanKhai,
        String nameOfShipownersAgents, String maritimeCodeNext, int start,
        int end, boolean isDTND, boolean isDTNDCam) throws SystemException {


    try {

        StringBuilder query = new StringBuilder();

        String sql = "SELECT * FROM temp_document WHERE 1=1 ";

        // Pb NC,XC,QC
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0) {
            query.append(" AND DocumentTypeCode= :documentTypeCode ");
        }

        query.append(" AND DocumentTypeCode NOT LIKE 'HH_%' ");

        // CANG VU
        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            query.append(" AND MaritimeCode= :maritimeCode");
        }

        // CANG DEN
        if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
            query.append(" AND PortCode= :portCode");
        }

        // CANG ROI CUOI CUNG
        if (Validator.isNotNull(lastPortCode)
                && lastPortCode.trim().length() > 0) {
            query.append(" AND LastPortCode= :lastPortCode");
        }

        // TEN TAU
        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            query.append(" AND ShipName like '%" + shipName.trim() + "%' ");
        }

        // QUOC TICH
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            query.append(" AND StateCode= :stateCode");
        }

        // HO HIEU
        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
        }
        if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
            query.append(" AND imo like '%" + imo.trim() + "%' ");
        }
        // TAU DEN ROI
        if (Validator.isNotNull(shipPosition)
                && shipPosition.trim().length() > 0
                && FormatData.convertToInt(shipPosition) > 0) {
            query.append(" AND ShipPosition ="
                    + FormatData.convertToInt(shipPosition));
        }

        // thoi gian den FROM
        if (Validator.isNotNull(shipDateFromStart)
                && shipDateFromStart.trim().length() > 0) {
            Date dateFromStart = FormatData
                    .parseDateShort3StringToDate(shipDateFromStart.trim());
            shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
            query.append(" AND (ShipDateFrom >= concat(date('"
                    + shipDateFromStart
                    + "'), ' 00:00:00')  OR ShipDateFrom IS NULL)");
        }

        // thoi gian den To
        if (Validator.isNotNull(shipDateFromEnd)
                && shipDateFromEnd.trim().length() > 0) {
            Date dateFromEnd = FormatData
                    .parseDateShort3StringToDate(shipDateFromEnd.trim());
            shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
            query.append(" AND (ShipDateFrom <= concat(date('"
                    + shipDateFromEnd
                    + "'), ' 23:59:59')  OR ShipDateFrom IS NULL)");
        }

        // thoi gian roi FROM
        if (Validator.isNotNull(shipDateToStart)
                && shipDateToStart.trim().length() > 0) {
            Date dateToStart = FormatData
                    .parseDateShort3StringToDate(shipDateToStart.trim());
            shipDateToStart = FormatData.parseDateToTring(dateToStart);
            query.append(" AND (ShipDateTo >= concat(date('"
                    + shipDateToStart
                    + "'), ' 00:00:00')  OR ShipDateTo IS NULL)");
        }

        // thoi gian roi To
        if (Validator.isNotNull(shipDateToEnd)
                && shipDateToEnd.trim().length() > 0) {
            Date dateToEnd = FormatData
                    .parseDateShort3StringToDate(shipDateToEnd.trim());
            shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
            query.append(" AND (ShipDateTo <= concat(date('"
                    + shipDateToEnd
                    + "'), ' 23:59:59')  OR ShipDateTo IS NULL)");
        }

        // ngay gio lam thu tuc
        // ngayLamThuTucFrom
        // ngayLamThuTucTo
        if (Validator.isNotNull(ngayLamThuTucFrom)
                && ngayLamThuTucFrom.trim().length() > 0) {
            Date dateLamThuTucFrom = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
            ngayLamThuTucFrom = FormatData
                    .parseDateToTring(dateLamThuTucFrom);
            query.append(" AND (CreatedDate >= concat(date('"
                    + ngayLamThuTucFrom
                    + "'), ' 00:00:00')  OR CreatedDate IS NULL)");
        }

        if (Validator.isNotNull(ngayLamThuTucTo)
                && ngayLamThuTucTo.trim().length() > 0) {
            Date dateLamThuTucTo = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
            ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
            query.append(" AND (CreatedDate <= concat(date('"
                    + ngayLamThuTucTo
                    + "'), ' 23:59:59')  OR CreatedDate IS NULL)");
        }

        // ma~ ban khai
        if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
            query.append(" AND (DocumentName = '" + maBanKhai + "')");
        }

        // TRANG THAI
        if (Validator.isNotNull(documentStatusCode)
                && documentStatusCode.trim().length() > 0) {
            query.append(" AND documentStatusCode IN ("
                    + documentStatusCode.trim() + ")");
        }

        // DAI LY DEN
        if (Validator.isNotNull(arrivalShipAgency)
                && arrivalShipAgency.trim().length() > 0
                && Validator.isNotNull(nameArrivalShipAgency)
                && nameArrivalShipAgency.trim().length() > 0) {

            query.append(" AND ArrivalShipAgency= :arrivalShipAgency");
        }

        // DAI LY DI
        if (Validator.isNotNull(departureShipAgency)
                && departureShipAgency.trim().length() > 0
                && Validator.isNotNull(nameDepartureShipAgency)
                && nameDepartureShipAgency.trim().length() > 0) {

            query.append(" AND DepartureShipAgency= :departureShipAgency");
        }

        // KHU VUC CANG
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            query.append(" AND PortRegionCode= :portRegionCode");
        }

        if (!isDTND && !isDTNDCam) {
            query.append(" AND DocumentTypeCode in ( 'NC', 'XC', 'QC', '4', '5', '8', '9', '10', '11', '12', '13' ) ");
        } else if (isDTND && isDTNDCam) {
            query.append(" AND DocumentTypeCode in ( '14', '15', '16', '17' ) ");
        } else {

            if (Validator.isNotNull(documentTypeCode)
                    && (documentTypeCode
                    .trim()
                    .equals(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6) || documentTypeCode
                    .trim()
                    .equals(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7))
                    || isDTND) {
                query.append(" AND DocumentTypeCode in ( '16', '17' ) ");
            }
            if (Validator.isNotNull(documentTypeCode)
                    && (documentTypeCode.trim().equals(
                    ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH) || documentTypeCode
                    .trim()
                    .equals(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH))
                    || isDTNDCam) {
                query.append(" AND DocumentTypeCode in ( '14', '15' ) ");
            }

        }

        if (Validator.isNotNull(nameOfShipownersAgents)
                && nameOfShipownersAgents.trim().length() > 0) {
            query.append(" AND NameOfShipownersAgents like '%"
                    + nameOfShipownersAgents.trim() + "%' ");
        }

        if (Validator.isNotNull(maritimeCodeNext)
                && maritimeCodeNext.trim().length() > 0) {
            query.append(" AND LastPortCode like '%"
                    + maritimeCodeNext.trim() + "%' ");
        }

        query.append(" ORDER BY ");
        // query.append(" DocumentName DESC, ");
        query.append("case when DocumentStatusCode = '18' then 1 ");
        query.append("when DocumentStatusCode = '12' then 2 ");
        query.append("when DocumentStatusCode = '20' then 3 ");
        query.append("when DocumentStatusCode = '13' then 4 ");
        query.append("when DocumentStatusCode = '19' then 5 ");
        query.append("when DocumentStatusCode = '10' then 6 ");
        query.append("when DocumentStatusCode = '25' then 7 ");
        query.append("end asc ");

        // TODO THU TUC sap xep thu tu ho so
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND))) {
            query.append(", ShipDateFrom DESC, CreatedDate DESC, id DESC");

        } else if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
            query.append(", ShipDateTo DESC, CreatedDate DESC, id DESC");

        } else {
            query.append(", ShipDateFrom DESC, ShipDateTo DESC, CreatedDate DESC, id DESC");
        }

        sql = sql + query.toString();
        if (start >= 0) {
            int count = end - start;
            sql = sql + " limit " + start + "," + count;
        }

        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();

        log.debug("=========findDanhSachHoSoRoleThuTuc By THU TUC ==="
                + sql);


        // Pb NC,XC,QC,VC,RC
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0) {

            builder.appendNamedParameterMap("documentTypeCode",documentTypeCode.trim());
        }

        // CANG VU
        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
        }

        // CANG DEN
        if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
            builder.appendNamedParameterMap("portCode",portCode.trim());
        }

        // CANG ROI CUOI CUNG
        if (Validator.isNotNull(lastPortCode)
                && lastPortCode.trim().length() > 0) {
            builder.appendNamedParameterMap("lastPortCode",lastPortCode.trim());
        }

        // QUOC TICH
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            builder.appendNamedParameterMap("stateCode",stateCode.trim());
        }

        // DAI LY DEN
        if (Validator.isNotNull(arrivalShipAgency)
                && arrivalShipAgency.trim().length() > 0
                && Validator.isNotNull(nameArrivalShipAgency)
                && nameArrivalShipAgency.trim().length() > 0) {
            builder.appendNamedParameterMap("arrivalShipAgency",arrivalShipAgency.trim());
        }

        // DAI LY DI
        if (Validator.isNotNull(departureShipAgency)
                && departureShipAgency.trim().length() > 0
                && Validator.isNotNull(nameDepartureShipAgency)
                && nameDepartureShipAgency.trim().length() > 0) {
            builder.appendNamedParameterMap("departureShipAgency",departureShipAgency.trim());
        }

        // KHU VUC CANG
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            builder.appendNamedParameterMap("portRegionCode",portRegionCode.trim());
        }

            return (List<TempDocument>)  queryFactory.getResultList(builder);

    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
}

public List<TempDocument> findDanhSachHoSoRoleThuTucLikeShipAgency(
        String documentTypeCode, String maritimeCode, String portCode,
        String lastPortCode, String shipName, String stateCode,
        String callSign, String imo, String shipPosition,
        String shipDateFromStart, String shipDateFromEnd,
        String shipDateToStart, String shipDateToEnd,
        String documentStatusCode, String arrivalShipAgency,
        String nameArrivalShipAgency, String departureShipAgency,
        String nameDepartureShipAgency, String portRegionCode,
        String ngayLamThuTucFrom, String ngayLamThuTucTo, String maBanKhai,
        int start, int end) throws SystemException {

    try {

        StringBuilder query = new StringBuilder();

        query.append("SELECT temp.ID, temp.RequestCode, temp.RequestState, temp.DocumentName, temp.DocumentYear, temp.DocumentStatusCode, temp.DocumentTypeCode, ");
        query.append("temp.UserCreated, temp.ShipAgencyCode, temp.ShipName, temp.ShipTypeCode, temp.StateCode, temp.ShipCaptain, temp.IMO, temp.GRT, temp.NT, temp.DWT, ");
        query.append("temp.UnitGRT, temp.UnitNT , temp.UnitDWT, temp.CallSign, temp.PreDocumentName, temp.CreatedDate, temp.LastModifiedDate, temp.FlowFlag, ");
        query.append("temp.DocumentStatusCode, temp.LocationCode, temp.MaritimeCode, temp.PortRegionCode, temp.PortCode, temp.LastPortCode, temp.ShipPosition, ");
        query.append("temp.ShipOwnerCode, temp.ArrivalShipAgency, temp.DepartureShipAgency, temp.ShipDateFrom, temp.ShipDateTo ");
        query.append("FROM temp_document temp ");

        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0) {
            // DAI LY DEN
            if (documentTypeCode.equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
                if (arrivalShipAgency.trim().length() == 0
                        && nameArrivalShipAgency.trim().length() > 0) {
                    query.append("inner join dm_ship_agency dmArri ");
                    query.append("on temp.ArrivalShipAgency = dmArri.ShipAgencyCode ");
                    query.append("where dmArri.ShipAgencyNameVN like '%"
                            + nameArrivalShipAgency.trim() + "%' ");
                }

                // DAI LY DI
            } else if (documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
                if (departureShipAgency.trim().length() == 0
                        && nameDepartureShipAgency.trim().length() > 0) {
                    query.append("inner join dm_ship_agency dmDep ");
                    query.append("on temp.DepartureShipAgency = dmDep.ShipAgencyCode ");
                    query.append("where dmDep.ShipAgencyNameVN like '%"
                            + nameDepartureShipAgency.trim() + "%' ");
                }

            } else if (documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_DANG_DI_CHUYEN)) {
                if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                        .trim().length() > 0)
                        && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                        .trim().length() > 0)) {

                    query.append("inner join dm_ship_agency dmDep ");
                    query.append("on temp.DepartureShipAgency = dmDep.ShipAgencyCode ");
                    query.append("AND (dmDep.ShipAgencyNameVN like '%"
                            + nameArrivalShipAgency.trim() + "%' "
                            + " OR dmDep.ShipAgencyNameVN like '%"
                            + nameDepartureShipAgency.trim() + "%' ");
                    query.append(" WHERE 1=1  ");
                }

                if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                        .trim().length() == 0)
                        && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                        .trim().length() > 0)) {
                    query.append("inner join dm_ship_agency dmDep ");
                    query.append("on temp.DepartureShipAgency = dmDep.ShipAgencyCode ");
                    query.append("where dmDep.ShipAgencyNameVN like '%"
                            + nameDepartureShipAgency.trim() + "%' ");
                }

                if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                        .trim().length() > 0)
                        && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                        .trim().length() == 0)) {
                    query.append("inner join dm_ship_agency dmArri ");
                    query.append("on temp.ArrivalShipAgency = dmArri.ShipAgencyCode ");
                    query.append("where dmArri.ShipAgencyNameVN like '%"
                            + nameArrivalShipAgency.trim() + "%' ");
                }
            }
        }

        // Pb NC,XC,QC
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
            query.append(" AND temp.DocumentTypeCode = ?");
        }

        // CANG VU
        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            query.append(" AND temp.MaritimeCode = ?");
        }

        // CANG DEN
        if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
            query.append(" AND temp.PortCode = ?");
        }

        // CANG ROI CUOI CUNG
        if (Validator.isNotNull(lastPortCode)
                && lastPortCode.trim().length() > 0) {
            query.append(" AND temp.LastPortCode = ?");
        }

        // TEN TAU
        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            query.append(" AND temp.ShipName like '%" + shipName.trim()
                    + "%' ");
        }

        // QUOC TICH
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            query.append(" AND temp.StateCode = ?");
        }

        // HO HIEU
        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            query.append(" AND temp.CallSign like '%" + callSign.trim()
                    + "%' ");
        }
        if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
            query.append(" AND temp.imo like '%" + imo.trim() + "%' ");
        }
        // TAU DEN ROI
        if (Validator.isNotNull(shipPosition)
                && shipPosition.trim().length() > 0
                && FormatData.convertToInt(shipPosition) > 0) {
            query.append(" AND temp.ShipPosition ="
                    + FormatData.convertToInt(shipPosition));
        }

        // thoi gian den FROM
        if (Validator.isNotNull(shipDateFromStart)
                && shipDateFromStart.trim().length() > 0) {
            Date dateFromStart = FormatData
                    .parseDateShort3StringToDate(shipDateFromStart.trim());
            shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
            query.append(" AND (temp.ShipDateFrom >= concat(date('"
                    + shipDateFromStart
                    + "'), ' 00:00:00')  OR temp.ShipDateFrom IS NULL)");
        }

        // thoi gian den To
        if (Validator.isNotNull(shipDateFromEnd)
                && shipDateFromEnd.trim().length() > 0) {
            Date dateFromEnd = FormatData
                    .parseDateShort3StringToDate(shipDateFromEnd.trim());
            shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
            query.append(" AND (temp.ShipDateFrom <= concat(date('"
                    + shipDateFromEnd
                    + "'), ' 23:59:59')  OR temp.ShipDateFrom IS NULL)");
        }

        // thoi gian roi FROM
        if (Validator.isNotNull(shipDateToStart)
                && shipDateToStart.trim().length() > 0) {
            Date dateToStart = FormatData
                    .parseDateShort3StringToDate(shipDateToStart.trim());
            shipDateToStart = FormatData.parseDateToTring(dateToStart);
            query.append(" AND (temp.ShipDateTo >= concat(date('"
                    + shipDateToStart
                    + "'), ' 00:00:00')  OR temp.ShipDateTo IS NULL)");
        }

        // thoi gian roi To
        if (Validator.isNotNull(shipDateToEnd)
                && shipDateToEnd.trim().length() > 0) {
            Date dateToEnd = FormatData
                    .parseDateShort3StringToDate(shipDateToEnd.trim());
            shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
            query.append(" AND (temp.ShipDateTo <= concat(date('"
                    + shipDateToEnd
                    + "'), ' 23:59:59')  OR temp.ShipDateTo IS NULL)");
        }

        // ngay gio lam thu tuc
        // ngayLamThuTucFrom
        // ngayLamThuTucTo
        if (Validator.isNotNull(ngayLamThuTucFrom)
                && ngayLamThuTucFrom.trim().length() > 0) {
            Date dateLamThuTucFrom = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
            ngayLamThuTucFrom = FormatData
                    .parseDateToTring(dateLamThuTucFrom);
            query.append(" AND (temp.CreatedDate >= concat(date('"
                    + ngayLamThuTucFrom
                    + "'), ' 00:00:00')  OR temp.CreatedDate IS NULL)");
        }

        if (Validator.isNotNull(ngayLamThuTucTo)
                && ngayLamThuTucTo.trim().length() > 0) {
            Date dateLamThuTucTo = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
            ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
            query.append(" AND (temp.CreatedDate <= concat(date('"
                    + ngayLamThuTucTo
                    + "'), ' 23:59:59')  OR temp.CreatedDate IS NULL)");
        }

        // ma~ ban khai
        if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
            query.append(" AND (temp.DocumentName = '" + maBanKhai + "')");
        }

        // TRANG THAI
        if (Validator.isNotNull(documentStatusCode)
                && documentStatusCode.trim().length() > 0) {
            query.append(" AND temp.documentStatusCode IN ("
                    + documentStatusCode.trim() + ")");
        }

        // KHU VUC CANG
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            query.append(" AND temp.PortRegionCode = ?");
        }

        /**
         * II- THÃ¡Â»Â¦ TÃ¡Â»Â¤C 1. Ã„ï¿½ÃƒÆ’ TIÃ¡ÂºÂ¾P NHÃ¡ÂºÂ¬N - 18 2.
         * CHÃ¡Â»Å“ PHÃƒÅ  DUYÃ¡Â»â€ T HOÃƒâ‚¬N THÃƒâ‚¬NH THÃ¡Â»Â¦ TÃ¡Â»Â¤C
         * - 12 3. Ã„ï¿½Ã¡Â»â‚¬ NGHÃ¡Â»Å  CÃ¡ÂºÂ¤P GIÃ¡ÂºÂ¤Y PHÃƒâ€°P - 20
         * 4. YÃƒÅ U CÃ¡ÂºÂ¦Y SÃ¡Â»Â¬A Ã„ï¿½Ã¡Â»â€�I BÃ¡Â»â€� SUNG - 13 5.
         * PHÃƒÅ  DUYÃ¡Â»â€ T VÃƒâ‚¬ HOÃƒâ‚¬N THÃƒâ‚¬NH THÃ¡Â»Â¦ TÃ¡Â»Â¤C -
         * 19 6. HÃ¡Â»Â¦Y THÃ¡Â»Â¦ TÃ¡Â»Â¤C - 10
         */

        query.append(" ORDER BY ");
        query.append("case when temp.DocumentStatusCode = '18' then 1 ");
        query.append("when temp.DocumentStatusCode = '12' then 2 ");
        query.append("when temp.DocumentStatusCode = '20' then 3 ");
        query.append("when temp.DocumentStatusCode = '13' then 4 ");
        query.append("when temp.DocumentStatusCode = '19' then 5 ");
        query.append("when temp.DocumentStatusCode = '10' then 6 ");
        query.append("end asc ");

        // TODO THU TUC sap xep thu tu ho so
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND))) {
            query.append(", temp.ShipDateFrom DESC, temp.CreatedDate DESC, id DESC");

        } else if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
            query.append(", temp.ShipDateTo DESC, temp.CreatedDate DESC, id DESC");

        } else {
            query.append(", temp.ShipDateFrom DESC, temp.ShipDateTo DESC, temp.CreatedDate DESC, id DESC");
        }

        if (start >= 0) {
            int count = end - start;
            query.append(" limit " + start + "," + count);
        }

        // log.info("---" + query.toString());
        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempDocument.class).build();

        log.debug("====findDanhSachHoSoRoleThuTucLikeShipAgency By THU TUC ===");


        // Pb NC,XC,QC
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG))) {
            builder.appendNamedParameterMap("documentTypeCode",documentTypeCode.trim());
        }

        // CANG VU
        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
        }

        // CANG DEN
        if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
            builder.appendNamedParameterMap("portCode",portCode.trim());
        }

        // CANG ROI CUOI CUNG
        if (Validator.isNotNull(lastPortCode)
                && lastPortCode.trim().length() > 0) {
            builder.appendNamedParameterMap("lastPortCode",lastPortCode.trim());
        }

        // QUOC TICH
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            builder.appendNamedParameterMap("stateCode",stateCode.trim());
        }

        // KHU VUC CANG
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            builder.appendNamedParameterMap("portRegionCode",portRegionCode.trim());
        }

        
           return (List<TempDocument>)  queryFactory.getResultList(builder);

    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
}

public int countDanhSachHoSoRoleThuTuc(String documentTypeCode,
                                       String maritimeCode, String portCode, String lastPortCode,
                                       String shipName, String stateCode, String callSign, String imo,
                                       String shipPosition, String shipDateFromStart,
                                       String shipDateFromEnd, String shipDateToStart,
                                       String shipDateToEnd, String documentStatusCode,
                                       String arrivalShipAgency, String nameArrivalShipAgency,
                                       String departureShipAgency, String nameDepartureShipAgency,
                                       String portRegionCode, String ngayLamThuTucFrom,
                                       String ngayLamThuTucTo, String maBanKhai, boolean isDTND,
                                       boolean isDTNDCam) throws SystemException { int count = 0; try {


    StringBuilder query = new StringBuilder();

    String sql = "SELECT count(*) as total FROM temp_document WHERE 1=1";

    // Pb NC,XC,QC, VC, RC
    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0) {
        query.append(" AND DocumentTypeCode= :documentTypeCode ");
    }

    query.append(" AND DocumentTypeCode NOT LIKE 'HH_%' ");

    // CANG VU
    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        query.append(" AND MaritimeCode= :maritimeCode");
    }

    // CANG DEN
    if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
        query.append(" AND PortCode= :portCode");
    }

    // CANG ROI CUOI CUNG
    if (Validator.isNotNull(lastPortCode)
            && lastPortCode.trim().length() > 0) {
        query.append(" AND LastPortCode= :lastPortCode");
    }

    // TEN TAU
    if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
        query.append(" AND ShipName like '%" + shipName.trim() + "%' ");
    }

    // QUOC TICH
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        query.append(" AND StateCode= :stateCode");
    }

    // HO HIEU
    if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
        query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
    }
    if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
        query.append(" AND imo like '%" + imo.trim() + "%' ");
    }
    // TAU DEN ROI
    if (Validator.isNotNull(shipPosition)
            && shipPosition.trim().length() > 0
            && FormatData.convertToInt(shipPosition) > 0) {
        query.append(" AND ShipPosition ="
                + FormatData.convertToInt(shipPosition));
    }

    // thoi gian den FROM
    if (Validator.isNotNull(shipDateFromStart)
            && shipDateFromStart.trim().length() > 0) {
        Date dateFromStart = FormatData
                .parseDateShort3StringToDate(shipDateFromStart.trim());
        shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
        query.append(" AND (ShipDateFrom >= concat(date('"
                + shipDateFromStart
                + "'), ' 00:00:00')  OR ShipDateFrom IS NULL)");
    }

    // thoi gian den To
    if (Validator.isNotNull(shipDateFromEnd)
            && shipDateFromEnd.trim().length() > 0) {
        Date dateFromEnd = FormatData
                .parseDateShort3StringToDate(shipDateFromEnd.trim());
        shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
        query.append(" AND (ShipDateFrom <= concat(date('"
                + shipDateFromEnd
                + "'), ' 23:59:59')  OR ShipDateFrom IS NULL)");
    }

    // thoi gian roi FROM
    if (Validator.isNotNull(shipDateToStart)
            && shipDateToStart.trim().length() > 0) {
        Date dateToStart = FormatData
                .parseDateShort3StringToDate(shipDateToStart.trim());
        shipDateToStart = FormatData.parseDateToTring(dateToStart);
        query.append(" AND (ShipDateTo >= concat(date('"
                + shipDateToStart
                + "'), ' 00:00:00')  OR ShipDateTo IS NULL)");
    }

    // thoi gian roi To
    if (Validator.isNotNull(shipDateToEnd)
            && shipDateToEnd.trim().length() > 0) {
        Date dateToEnd = FormatData
                .parseDateShort3StringToDate(shipDateToEnd.trim());
        shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
        query.append(" AND (ShipDateTo <= concat(date('"
                + shipDateToEnd
                + "'), ' 23:59:59')  OR ShipDateTo IS NULL)");
    }

    // ngay gio lam thu tuc
    // ngayLamThuTucFrom
    // ngayLamThuTucTo
    if (Validator.isNotNull(ngayLamThuTucFrom)
            && ngayLamThuTucFrom.trim().length() > 0) {
        Date dateLamThuTucFrom = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
        ngayLamThuTucFrom = FormatData
                .parseDateToTring(dateLamThuTucFrom);
        query.append(" AND (CreatedDate >= concat(date('"
                + ngayLamThuTucFrom
                + "'), ' 00:00:00')  OR CreatedDate IS NULL)");
    }

    if (Validator.isNotNull(ngayLamThuTucTo)
            && ngayLamThuTucTo.trim().length() > 0) {
        Date dateLamThuTucTo = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
        ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
        query.append(" AND (CreatedDate <= concat(date('"
                + ngayLamThuTucTo
                + "'), ' 23:59:59')  OR CreatedDate IS NULL)");
    }

    // ma~ ban khai
    if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
        query.append(" AND (DocumentName = '" + maBanKhai + "')");
    }

    // TRANG THAI
    if (Validator.isNotNull(documentStatusCode)
            && documentStatusCode.trim().length() > 0) {
        // if (documentStatusCode.equalsIgnoreCase("120")) {
        // documentStatusCode = "20"; // tuong duong de nghi cap giay
        // phep
        //
        // query.append(" AND documentStatusCode IN (" +
        // documentStatusCode.trim() + ")");
        //
        // if
        // (documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_QUA_CANH))
        // {
        // query.append(" AND (DocumentName IN (Select distinct
        // DocumentName from issue_permission_for_transit where
        // StampStatus = 0 and IsCancel = 0 and LENGTH(CancelNote) > 0)
        // ) ");
        //
        // }
        // if
        // (documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
        // ||
        // documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_ROI_CANG)
        // ||
        // documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)){
        // query.append(" AND (DocumentName IN (Select distinct
        // DocumentName from issue_port_clearance where StampStatus = 0
        // and IsCancel = 0 and LENGTH(CancelNote) > 0) ) ");
        // }
        // if
        // (documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND))
        // {
        //
        // query.append(" AND (DocumentName IN (Select distinct
        // DocumentName from issue_acceptance where StampStatus = 0 and
        // IsCancel = 0 and LENGTH(CancelNote) > 0) ) ");
        // }
        // } else {
        // query.append(" AND documentStatusCode IN (" +
        // documentStatusCode.trim() + ")");
        // }
        query.append(" AND documentStatusCode IN ("
                + documentStatusCode.trim() + ")");
    }

    // DAI LY DEN
    if (Validator.isNotNull(arrivalShipAgency)
            && arrivalShipAgency.trim().length() > 0
            && Validator.isNotNull(nameArrivalShipAgency)
            && nameArrivalShipAgency.trim().length() > 0) {

        // StringBuilder daiLyDen = new StringBuilder();
        // daiLyDen.append("SELECT ShipAgencyCode FROM dm_ship_agency
        // ");
        // daiLyDen.append("where ShipAgencyNameVN like '%" +
        // arrivalShipAgency.trim() + "%' ");

        query.append(" AND ArrivalShipAgency= :arrivalShipAgency");
        // query.append(" AND ArrivalShipAgency in ( " +
        // daiLyDen.toString() + " )");
    }

    // DAI LY DI
    if (Validator.isNotNull(departureShipAgency)
            && departureShipAgency.trim().length() > 0
            && Validator.isNotNull(nameDepartureShipAgency)
            && nameDepartureShipAgency.trim().length() > 0) {

        // StringBuilder daiLyDi = new StringBuilder();
        // daiLyDi.append("SELECT ShipAgencyCode FROM dm_ship_agency ");
        // daiLyDi.append("where ShipAgencyNameVN like '%" +
        // departureShipAgency.trim() + "%' ");

        query.append(" AND DepartureShipAgency= :departureShipAgency");
        // query.append(" AND DepartureShipAgency in (" +
        // daiLyDi.toString() + ")");
    }

    // KHU VUC CANG
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        query.append(" AND PortRegionCode= :portRegionCode");
    }

    if (!isDTND && !isDTNDCam) {
        query.append(" AND DocumentTypeCode in ( 'NC', 'XC', 'QC', '4', '5', '8', '9', '10', '11', '12', '13' ) ");
    } else if (isDTND && isDTNDCam) {
        query.append(" AND DocumentTypeCode in ( '14', '15', '16', '17' ) ");
    } else {

        if (Validator.isNotNull(documentTypeCode)
                && (documentTypeCode
                .trim()
                .equals(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6) || documentTypeCode
                .trim()
                .equals(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7))
                || isDTND) {
            query.append(" AND DocumentTypeCode in ( '16', '17' ) ");
        }
        if (Validator.isNotNull(documentTypeCode)
                && (documentTypeCode.trim().equals(
                ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH) || documentTypeCode
                .trim()
                .equals(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH))
                || isDTNDCam) {
            query.append(" AND DocumentTypeCode in ( '14', '15' ) ");
        }

    }

    sql = sql + query.toString();
    log.debug("=========THU TUC SEARCH --count TempDocument ========"
            + sql);

    QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();




    // Pb NC,XC,QC, VC, RC
    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0) {
        builder.appendNamedParameterMap("documentTypeCode",documentTypeCode.trim());
    }

    // CANG VU
    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
    }

    // CANG DEN
    if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
        builder.appendNamedParameterMap("portCode",portCode.trim());
    }

    // CANG ROI CUOI CUNG
    if (Validator.isNotNull(lastPortCode)
            && lastPortCode.trim().length() > 0) {
        builder.appendNamedParameterMap("lastPortCode",lastPortCode.trim());
    }

    // QUOC TICH
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        builder.appendNamedParameterMap("stateCode",stateCode.trim());
    }

    // DAI LY DEN
    if (Validator.isNotNull(arrivalShipAgency)
            && arrivalShipAgency.trim().length() > 0
            && Validator.isNotNull(nameArrivalShipAgency)
            && nameArrivalShipAgency.trim().length() > 0) {
        builder.appendNamedParameterMap("arrivalShipAgency",arrivalShipAgency.trim());
    }

    // DAI LY DI
    if (Validator.isNotNull(departureShipAgency)
            && departureShipAgency.trim().length() > 0
            && Validator.isNotNull(nameDepartureShipAgency)
            && nameDepartureShipAgency.trim().length() > 0) {
        builder.appendNamedParameterMap("departureShipAgency",departureShipAgency.trim());
    }

    // KHU VUC CANG
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        builder.appendNamedParameterMap("portRegionCode",portRegionCode.trim());
    }


    count = (Integer) queryFactory.getSingleResult(builder);
    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
    return count;
}

    public List<TempDocument> findTemDocumentByDocumentNameAndDocumentYear(
            long documentName, int documentYear) throws SystemException {
        
        try {
           
            StringBuilder query = new StringBuilder();

            query.append("SELECT * FROM temp_document WHERE DocumentName= :documentName AND DocumentYear= :documentYear");
            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempDocument.class).build();



            builder.appendNamedParameterMap("documentName",documentName);
            builder.appendNamedParameterMap("documentYear",documentYear);

            return (List<TempDocument>) queryFactory.getResultList(builder);
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            
        }
    }
    public List<TempDocument> findTempDocumentArivalByMaritimeCodeAndDateFromAndDateTo(
            String maritimeCode, String dateFrom, String dateTo,
            String documentTypeCode, String requestState,
            String documentStatusCode) throws SystemException {
        

        Date date = null;
        List<TempDocument> allTempDocument = null;
        try {
           
            StringBuilder query = new StringBuilder();

            query.append("SELECT * FROM temp_document ");
            query.append(" WHERE 1 = 1 ");

            if (maritimeCode != null) {
                query.append(" and MaritimeCode = " + maritimeCode);
            }

            if (documentTypeCode != null) {
                query.append(" and DocumentTypeCode in (" + documentTypeCode
                        + ")");
            }

            if (requestState != null) {
                query.append(" and requestState !=" + requestState);
            }
            if (documentStatusCode != null) {
                query.append(" and documentStatusCode != " + documentStatusCode);
            }

            if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {

                date = FormatData.parseDateShort3StringToDate(dateFrom.trim());
                dateFrom = FormatData.parseDateToTring(date);
                query.append(" and ShipDateFrom >= concat(date('" + dateFrom
                        + "'), ' 00:00:00') ");
            }

            // Thoi gian roi
            if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {

                date = FormatData.parseDateShort3StringToDate(dateTo.trim());
                dateTo = FormatData.parseDateToTring(date);
                query.append(" and ShipDateFrom <= concat(date('" + dateTo
                        + "'), ' 23:59:59') ");
            }

            // execute the query and return a list from the db
            String sql = query.toString();
            sql = sql + " limit 0,1000 ";

            System.out
                    .println("TempDocumentFinderImpl.findTempDocumentArivalByMaritimeCodeAndDateFromAndDateTo(sql): "
                            + sql);

            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();
            return (List<TempDocument>) queryFactory.getResultList(builder);
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            
        }
    }

    public List<TempDocument> findTempDocumentLeaveByMaritimeCodeAndDateFromAndDateTo(
            String maritimeCode, String dateFrom, String dateTo,
            String documentTypeCode, String requestState,
            String documentStatusCode) throws SystemException {
        

        Date date = null;
        List<TempDocument> allTempDocument = null;
        try {
           
            StringBuilder query = new StringBuilder();

            query.append("SELECT * FROM temp_document ");
            query.append(" WHERE 1 = 1 ");

            if (maritimeCode != null) {
                query.append(" and MaritimeCode = " + maritimeCode);
            }

            if (documentTypeCode != null) {
                query.append(" and DocumentTypeCode in (" + documentTypeCode
                        + ")");
            }

            if (requestState != null) {
                query.append(" and requestState !=" + requestState);
            }
            if (documentStatusCode != null) {
                query.append(" and documentStatusCode != " + documentStatusCode);
            }

            query.append(" and ShipDateTo is not null");

            if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {

                date = FormatData.parseDateShort3StringToDate(dateFrom.trim());
                dateFrom = FormatData.parseDateToTring(date);
                query.append(" and ShipDateTo >= concat(date('" + dateFrom
                        + "'), ' 00:00:00') ");
            }

            // Thoi gian roi
            if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {

                date = FormatData.parseDateShort3StringToDate(dateTo.trim());
                dateTo = FormatData.parseDateToTring(date);
                query.append(" and ShipDateTo <= concat(date('" + dateTo
                        + "'), ' 23:59:59') ");
            }
            String sql = query.toString();
            sql = sql + " limit 0,1000 ";
            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();
            return (List<TempDocument>) queryFactory.getResultList(builder);

        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            
        }
    }

    public int countTempDocumentByDocumentStatusCode(String documentStatusCode,
                                                     String documentTypeCode) throws SystemException {
        
        try {
           

            if (Validator.isNotNull(documentStatusCode)
                    && documentStatusCode.trim().length() > 0
                    && Validator.isNotNull(documentTypeCode)
                    && documentTypeCode.trim().length() > 0) {
                StringBuilder query = new StringBuilder();
                query.append("SELECT count(*) as total FROM temp_document ");
                query.append(" WHERE DocumentStatusCode in ("
                        + documentStatusCode
                        + ") AND DocumentTypeCode= :documentTypeCode AND DocumentTypeCode NOT LIKE 'HH_%' ");

                String sql = query.toString();
                log.debug("=========countTempDocumentByDocumentStatusCode========"
                        + sql);
                log.debug("=========countTempDocumentByDocumentStatusCode========"
                        + sql + "=====documentTypeCode==" + documentTypeCode);
                QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

                builder.appendNamedParameterMap("documentTypeCode",documentTypeCode);
                Iterator<Long> itr = queryFactory3.getResultList(builder).iterator();

                if (itr.hasNext()) {
                    Long count = itr.next();
                    if (count != null) {
                        return count.intValue();
                    }
                }
            }

            return 0;
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            
        }
    }



    public TempDocument findTemDocumentByDocumentNameDocumentYear(
            long documentName, int documentYear) throws SystemException {
        
        try {
           
            StringBuilder query = new StringBuilder();

            query.append("SELECT * FROM temp_document WHERE DocumentName= :documentName AND DocumentYear= :documentYear");

            String sql = query.toString();
            log.debug("=========findTemDocumentByDocumentNameDocumentYear========"
                    + sql);
            QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();

            builder.appendNamedParameterMap("documentName",documentName);
            builder.appendNamedParameterMap("documentYear",documentYear);

            // execute the query and return a list from the db
            return (TempDocument) queryFactory.getSingleResult(builder);
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            
        }
    }





    public int countDanhSachHoSoRoleThuTuc(String documentTypeCode,
                                       String maritimeCode, String portCode, String lastPortCode,
                                       String shipName, String stateCode, String callSign, String imo,
                                       String shipPosition, String shipDateFromStart,
                                       String shipDateFromEnd, String shipDateToStart,
                                       String shipDateToEnd, String documentStatusCode,
                                       String arrivalShipAgency, String nameArrivalShipAgency,
                                       String departureShipAgency, String nameDepartureShipAgency,
                                       String portRegionCode, String ngayLamThuTucFrom,
                                       String ngayLamThuTucTo, String maBanKhai,
                                       String nameOfShipownersAgents, String maritimeCodeNext,
                                       boolean isDTND, boolean isDTNDCam) throws SystemException { int count = 0; try {


    StringBuilder query = new StringBuilder();

    String sql = "SELECT count(*) as total FROM temp_document WHERE 1=1";

    // Pb NC,XC,QC, VC, RC
    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0) {
        query.append(" AND DocumentTypeCode= :documentTypeCode ");
    }

    query.append(" AND DocumentTypeCode NOT LIKE 'HH_%' ");

    // CANG VU
    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        query.append(" AND MaritimeCode= :maritimeCode");
    }

    // CANG DEN
    if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
        query.append(" AND PortCode= :portCode");
    }

    // CANG ROI CUOI CUNG
    if (Validator.isNotNull(lastPortCode)
            && lastPortCode.trim().length() > 0) {
        query.append(" AND LastPortCode= :lastPortCode");
    }

    // TEN TAU
    if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
        query.append(" AND ShipName like '%" + shipName.trim() + "%' ");
    }

    // QUOC TICH
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        query.append(" AND StateCode= :stateCode");
    }

    // HO HIEU
    if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
        query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
    }
    if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
        query.append(" AND imo like '%" + imo.trim() + "%' ");
    }
    // TAU DEN ROI
    if (Validator.isNotNull(shipPosition)
            && shipPosition.trim().length() > 0
            && FormatData.convertToInt(shipPosition) > 0) {
        query.append(" AND ShipPosition ="
                + FormatData.convertToInt(shipPosition));
    }

    // thoi gian den FROM
    if (Validator.isNotNull(shipDateFromStart)
            && shipDateFromStart.trim().length() > 0) {
        Date dateFromStart = FormatData
                .parseDateShort3StringToDate(shipDateFromStart.trim());
        shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
        query.append(" AND (ShipDateFrom >= concat(date('"
                + shipDateFromStart
                + "'), ' 00:00:00')  OR ShipDateFrom IS NULL)");
    }

    // thoi gian den To
    if (Validator.isNotNull(shipDateFromEnd)
            && shipDateFromEnd.trim().length() > 0) {
        Date dateFromEnd = FormatData
                .parseDateShort3StringToDate(shipDateFromEnd.trim());
        shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
        query.append(" AND (ShipDateFrom <= concat(date('"
                + shipDateFromEnd
                + "'), ' 23:59:59')  OR ShipDateFrom IS NULL)");
    }

    // thoi gian roi FROM
    if (Validator.isNotNull(shipDateToStart)
            && shipDateToStart.trim().length() > 0) {
        Date dateToStart = FormatData
                .parseDateShort3StringToDate(shipDateToStart.trim());
        shipDateToStart = FormatData.parseDateToTring(dateToStart);
        query.append(" AND (ShipDateTo >= concat(date('"
                + shipDateToStart
                + "'), ' 00:00:00')  OR ShipDateTo IS NULL)");
    }

    // thoi gian roi To
    if (Validator.isNotNull(shipDateToEnd)
            && shipDateToEnd.trim().length() > 0) {
        Date dateToEnd = FormatData
                .parseDateShort3StringToDate(shipDateToEnd.trim());
        shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
        query.append(" AND (ShipDateTo <= concat(date('"
                + shipDateToEnd
                + "'), ' 23:59:59')  OR ShipDateTo IS NULL)");
    }

    // ngay gio lam thu tuc
    // ngayLamThuTucFrom
    // ngayLamThuTucTo
    if (Validator.isNotNull(ngayLamThuTucFrom)
            && ngayLamThuTucFrom.trim().length() > 0) {
        Date dateLamThuTucFrom = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
        ngayLamThuTucFrom = FormatData
                .parseDateToTring(dateLamThuTucFrom);
        query.append(" AND (CreatedDate >= concat(date('"
                + ngayLamThuTucFrom
                + "'), ' 00:00:00')  OR CreatedDate IS NULL)");
    }

    if (Validator.isNotNull(ngayLamThuTucTo)
            && ngayLamThuTucTo.trim().length() > 0) {
        Date dateLamThuTucTo = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
        ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
        query.append(" AND (CreatedDate <= concat(date('"
                + ngayLamThuTucTo
                + "'), ' 23:59:59')  OR CreatedDate IS NULL)");
    }

    // ma~ ban khai
    if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
        query.append(" AND (DocumentName = '" + maBanKhai + "')");
    }

    // TRANG THAI
    if (Validator.isNotNull(documentStatusCode)
            && documentStatusCode.trim().length() > 0) {
        // if (documentStatusCode.equalsIgnoreCase("120")) {
        // documentStatusCode = "20"; // tuong duong de nghi cap giay
        // phep
        //
        // query.append(" AND documentStatusCode IN (" +
        // documentStatusCode.trim() + ")");
        //
        // if
        // (documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_QUA_CANH))
        // {
        // query.append(" AND (DocumentName IN (Select distinct
        // DocumentName from issue_permission_for_transit where
        // StampStatus = 0 and IsCancel = 0 and LENGTH(CancelNote) > 0)
        // ) ");
        //
        // }
        // if
        // (documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
        // ||
        // documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_ROI_CANG)
        // ||
        // documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)){
        // query.append(" AND (DocumentName IN (Select distinct
        // DocumentName from issue_port_clearance where StampStatus = 0
        // and IsCancel = 0 and LENGTH(CancelNote) > 0) ) ");
        // }
        // if
        // (documentTypeCode.trim().equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND))
        // {
        //
        // query.append(" AND (DocumentName IN (Select distinct
        // DocumentName from issue_acceptance where StampStatus = 0 and
        // IsCancel = 0 and LENGTH(CancelNote) > 0) ) ");
        // }
        // } else {
        // query.append(" AND documentStatusCode IN (" +
        // documentStatusCode.trim() + ")");
        // }
        query.append(" AND documentStatusCode IN ("
                + documentStatusCode.trim() + ")");
    }

    // DAI LY DEN
    if (Validator.isNotNull(arrivalShipAgency)
            && arrivalShipAgency.trim().length() > 0
            && Validator.isNotNull(nameArrivalShipAgency)
            && nameArrivalShipAgency.trim().length() > 0) {

        // StringBuilder daiLyDen = new StringBuilder();
        // daiLyDen.append("SELECT ShipAgencyCode FROM dm_ship_agency
        // ");
        // daiLyDen.append("where ShipAgencyNameVN like '%" +
        // arrivalShipAgency.trim() + "%' ");

        query.append(" AND ArrivalShipAgency= :arrivalShipAgency");
        // query.append(" AND ArrivalShipAgency in ( " +
        // daiLyDen.toString() + " )");
    }

    // DAI LY DI
    if (Validator.isNotNull(departureShipAgency)
            && departureShipAgency.trim().length() > 0
            && Validator.isNotNull(nameDepartureShipAgency)
            && nameDepartureShipAgency.trim().length() > 0) {

        // StringBuilder daiLyDi = new StringBuilder();
        // daiLyDi.append("SELECT ShipAgencyCode FROM dm_ship_agency ");
        // daiLyDi.append("where ShipAgencyNameVN like '%" +
        // departureShipAgency.trim() + "%' ");

        query.append(" AND DepartureShipAgency= :departureShipAgency");
        // query.append(" AND DepartureShipAgency in (" +
        // daiLyDi.toString() + ")");
    }

    // KHU VUC CANG
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        query.append(" AND PortRegionCode= :portRegionCode");
    }

    if (!isDTND && !isDTNDCam) {
        query.append(" AND DocumentTypeCode in ( 'NC', 'XC', 'QC', '4', '5', '8', '9', '10', '11', '12', '13' ) ");
    } else if (isDTND && isDTNDCam) {
        query.append(" AND DocumentTypeCode in ( '14', '15', '16', '17' ) ");
    } else {

        if (Validator.isNotNull(documentTypeCode)
                && (documentTypeCode
                .trim()
                .equals(ChuyenDichTrangThaiUtils.TAU_THUYEN_NHAP_CANH_6) || documentTypeCode
                .trim()
                .equals(ChuyenDichTrangThaiUtils.TAU_THUYEN_XUAT_CANH_7))
                || isDTND) {
            query.append(" AND DocumentTypeCode in ( '16', '17' ) ");
        }
        if (Validator.isNotNull(documentTypeCode)
                && (documentTypeCode.trim().equals(
                ChuyenDichTrangThaiUtils.VIET_CAM_NHAP_CANH) || documentTypeCode
                .trim()
                .equals(ChuyenDichTrangThaiUtils.VIET_CAM_XUAT_CANH))
                || isDTNDCam) {
            query.append(" AND DocumentTypeCode in ( '14', '15' ) ");
        }

    }

    if (Validator.isNotNull(nameOfShipownersAgents)
            && nameOfShipownersAgents.trim().length() > 0) {
        query.append(" AND NameOfShipownersAgents like '%"
                + nameOfShipownersAgents.trim() + "%' ");
    }
    if (Validator.isNotNull(maritimeCodeNext)
            && maritimeCodeNext.trim().length() > 0) {
        query.append(" AND LastPortCode like '%"
                + maritimeCodeNext.trim() + "%' ");
    }
    sql = sql + query.toString();
    log.debug("=========THU TUC SEARCH --count TempDocument ========"
            + sql);

    QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();




    // Pb NC,XC,QC, VC, RC
    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0) {
        builder.appendNamedParameterMap("documentTypeCode",documentTypeCode.trim());
    }

    // CANG VU
    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
    }

    // CANG DEN
    if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
        builder.appendNamedParameterMap("portCode",portCode.trim());
    }

    // CANG ROI CUOI CUNG
    if (Validator.isNotNull(lastPortCode)
            && lastPortCode.trim().length() > 0) {
        builder.appendNamedParameterMap("lastPortCode",lastPortCode.trim());
    }

    // QUOC TICH
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        builder.appendNamedParameterMap("stateCode",stateCode.trim());
    }

    // DAI LY DEN
    if (Validator.isNotNull(arrivalShipAgency)
            && arrivalShipAgency.trim().length() > 0
            && Validator.isNotNull(nameArrivalShipAgency)
            && nameArrivalShipAgency.trim().length() > 0) {
        builder.appendNamedParameterMap("arrivalShipAgency",arrivalShipAgency.trim());
    }

    // DAI LY DI
    if (Validator.isNotNull(departureShipAgency)
            && departureShipAgency.trim().length() > 0
            && Validator.isNotNull(nameDepartureShipAgency)
            && nameDepartureShipAgency.trim().length() > 0) {
        builder.appendNamedParameterMap("departureShipAgency",departureShipAgency.trim());
    }

    // KHU VUC CANG
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        builder.appendNamedParameterMap("portRegionCode",portRegionCode.trim());
    }


    count = (Integer) queryFactory.getSingleResult(builder);
} catch (Exception e) {
    throw new SystemException(e);
} finally {

}
    return count;
}

public int countDanhSachHoSoRoleThuTucLikeShipAgency(
        String documentTypeCode, String maritimeCode, String portCode,
        String lastPortCode, String shipName, String stateCode,
        String callSign, String imo, String shipPosition,
        String shipDateFromStart, String shipDateFromEnd,
        String shipDateToStart, String shipDateToEnd,
        String documentStatusCode, String arrivalShipAgency,
        String nameArrivalShipAgency, String departureShipAgency,
        String nameDepartureShipAgency, String portRegionCode,
        String ngayLamThuTucFrom, String ngayLamThuTucTo, String maBanKhai) throws SystemException { int count = 0; try {


    StringBuilder query = new StringBuilder();

    query.append("SELECT count(*) as total FROM temp_document temp ");

    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0) {
        // DAI LY DEN
        if (documentTypeCode.equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND)) {
            if (arrivalShipAgency.trim().length() == 0
                    && nameArrivalShipAgency.trim().length() > 0) {
                query.append("inner join dm_ship_agency dmArri ");
                query.append("on temp.ArrivalShipAgency = dmArri.ShipAgencyCode ");
                query.append("where dmArri.ShipAgencyNameVN like '%"
                        + nameArrivalShipAgency.trim() + "%' ");
            }

            // DAI LY DI
        } else if (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND)) {
            if (departureShipAgency.trim().length() == 0
                    && nameDepartureShipAgency.trim().length() > 0) {
                query.append("inner join dm_ship_agency dmDep ");
                query.append("on temp.DepartureShipAgency = dmDep.ShipAgencyCode ");
                query.append("where dmDep.ShipAgencyNameVN like '%"
                        + nameDepartureShipAgency.trim() + "%' ");
            }

        } else if (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_DANG_DI_CHUYEN)) {
            if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                    .trim().length() > 0)
                    && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                    .trim().length() > 0)) {

                query.append("inner join dm_ship_agency dmDep ");
                query.append("on temp.DepartureShipAgency = dmDep.ShipAgencyCode ");
                query.append("AND (dmDep.ShipAgencyNameVN like '%"
                        + nameArrivalShipAgency.trim() + "%' "
                        + " OR dmDep.ShipAgencyNameVN like '%"
                        + nameDepartureShipAgency.trim() + "%' ");
                query.append(" WHERE 1=1  ");
            }

            if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                    .trim().length() == 0)
                    && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                    .trim().length() > 0)) {
                query.append("inner join dm_ship_agency dmDep ");
                query.append("on temp.DepartureShipAgency = dmDep.ShipAgencyCode ");
                query.append("where dmDep.ShipAgencyNameVN like '%"
                        + nameDepartureShipAgency.trim() + "%' ");
            }

            if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                    .trim().length() > 0)
                    && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                    .trim().length() == 0)) {
                query.append("inner join dm_ship_agency dmArri ");
                query.append("on temp.ArrivalShipAgency = dmArri.ShipAgencyCode ");
                query.append("where dmArri.ShipAgencyNameVN like '%"
                        + nameArrivalShipAgency.trim() + "%' ");
            }
        }
    }

    // Pb NC,XC,QC, VC, RC
    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0
            && (documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
        query.append(" AND temp.DocumentTypeCode = ?");
    }

    // CANG VU
    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        query.append(" AND temp.MaritimeCode = ?");
    }

    // CANG DEN
    if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
        query.append(" AND temp.PortCode = ?");
    }

    // CANG ROI CUOI CUNG
    if (Validator.isNotNull(lastPortCode)
            && lastPortCode.trim().length() > 0) {
        query.append(" AND temp.LastPortCode = ?");
    }

    // TEN TAU
    if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
        query.append(" AND temp.ShipName like '%" + shipName.trim()
                + "%' ");
    }

    // QUOC TICH
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        query.append(" AND temp.StateCode = ?");
    }

    // HO HIEU
    if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
        query.append(" AND temp.CallSign like '%" + callSign.trim()
                + "%' ");
    }
    if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
        query.append(" AND temp.imo like '%" + imo.trim() + "%' ");
    }
    // TAU DEN ROI
    if (Validator.isNotNull(shipPosition)
            && shipPosition.trim().length() > 0
            && FormatData.convertToInt(shipPosition) > 0) {
        query.append(" AND temp.ShipPosition ="
                + FormatData.convertToInt(shipPosition));
    }

    // thoi gian den FROM
    if (Validator.isNotNull(shipDateFromStart)
            && shipDateFromStart.trim().length() > 0) {
        Date dateFromStart = FormatData
                .parseDateShort3StringToDate(shipDateFromStart.trim());
        shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
        query.append(" AND (temp.ShipDateFrom >= concat(date('"
                + shipDateFromStart
                + "'), ' 00:00:00')  OR temp.ShipDateFrom IS NULL)");
    }

    // thoi gian den To
    if (Validator.isNotNull(shipDateFromEnd)
            && shipDateFromEnd.trim().length() > 0) {
        Date dateFromEnd = FormatData
                .parseDateShort3StringToDate(shipDateFromEnd.trim());
        shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
        query.append(" AND (temp.ShipDateFrom <= concat(date('"
                + shipDateFromEnd
                + "'), ' 23:59:59')  OR temp.ShipDateFrom IS NULL)");
    }

    // thoi gian roi FROM
    if (Validator.isNotNull(shipDateToStart)
            && shipDateToStart.trim().length() > 0) {
        Date dateToStart = FormatData
                .parseDateShort3StringToDate(shipDateToStart.trim());
        shipDateToStart = FormatData.parseDateToTring(dateToStart);
        query.append(" AND (temp.ShipDateTo >= concat(date('"
                + shipDateToStart
                + "'), ' 00:00:00')  OR temp.ShipDateTo IS NULL)");
    }

    // thoi gian roi To
    if (Validator.isNotNull(shipDateToEnd)
            && shipDateToEnd.trim().length() > 0) {
        Date dateToEnd = FormatData
                .parseDateShort3StringToDate(shipDateToEnd.trim());
        shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
        query.append(" AND (temp.ShipDateTo <= concat(date('"
                + shipDateToEnd
                + "'), ' 23:59:59')  OR temp.ShipDateTo IS NULL)");
    }

    // ngay gio lam thu tuc
    // ngayLamThuTucFrom
    // ngayLamThuTucTo
    if (Validator.isNotNull(ngayLamThuTucFrom)
            && ngayLamThuTucFrom.trim().length() > 0) {
        Date dateLamThuTucFrom = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
        ngayLamThuTucFrom = FormatData
                .parseDateToTring(dateLamThuTucFrom);
        query.append(" AND (temp.CreatedDate >= concat(date('"
                + ngayLamThuTucFrom
                + "'), ' 00:00:00')  OR temp.CreatedDate IS NULL)");
    }

    if (Validator.isNotNull(ngayLamThuTucTo)
            && ngayLamThuTucTo.trim().length() > 0) {
        Date dateLamThuTucTo = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
        ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
        query.append(" AND (temp.CreatedDate <= concat(date('"
                + ngayLamThuTucTo
                + "'), ' 23:59:59')  OR temp.CreatedDate IS NULL)");
    }

    // ma~ ban khai
    if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
        query.append(" AND (temp.DocumentName = '" + maBanKhai + "')");
    }

    // TRANG THAI
    if (Validator.isNotNull(documentStatusCode)
            && documentStatusCode.trim().length() > 0) {
        query.append(" AND temp.documentStatusCode IN ("
                + documentStatusCode.trim() + ")");
    }

    // KHU VUC CANG
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        query.append(" AND temp.PortRegionCode = ?");
    }

    log.debug("=========THU TUC SEARCH --count TempDocument ========"
            + query.toString());

    QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Integer.class).build();




    // Pb NC,XC,QC
    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0
            && (documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
        builder.appendNamedParameterMap("documentTypeCode",documentTypeCode.trim());
    }

    // CANG VU
    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
    }

    // CANG DEN
    if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
        builder.appendNamedParameterMap("portCode",portCode.trim());
    }

    // CANG ROI CUOI CUNG
    if (Validator.isNotNull(lastPortCode)
            && lastPortCode.trim().length() > 0) {
        builder.appendNamedParameterMap("lastPortCode",lastPortCode.trim());
    }

    // QUOC TICH
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        builder.appendNamedParameterMap("stateCode",stateCode.trim());
    }

    // KHU VUC CANG
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        builder.appendNamedParameterMap("portRegionCode",portRegionCode.trim());
    }


    count = (Integer) queryFactory.getSingleResult(builder);
} catch (Exception e) {
    throw new SystemException(e);
} finally {

}
    return count;
}

public List<TempDocument> findTempDocumentInfo(String documentStatusCode,
                                               String maritimeCode, String portCode, String shipName,
                                               String callSign, String shipDateFrom, String shipDateTo, int start,
                                               int end) throws SystemException {

    try {

        StringBuilder query = new StringBuilder();

        String sql = "SELECT * FROM temp_document WHERE 1=1 ";

        // documentStatusCode
        if (Validator.isNotNull(documentStatusCode)
                && documentStatusCode.trim().length() > 0) {
            query.append(" AND DocumentStatusCode IN ("
                    + documentStatusCode + ")");
        }

        // cang vu hang hai
        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            query.append(" AND MaritimeCode= :maritimeCode");
        }
        log.debug("===query.append===maritimeCode===" + maritimeCode);
        // cang bien
        if (Validator.isNotNull(portCode) && portCode.trim().length() > 0
                && !portCode.trim().equalsIgnoreCase("LUA_CHON1")) {
            query.append(" AND portCode= :portCode");
        }
        log.debug("===query.append===portCode===" + portCode);
        // ten tau
        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            // query.append(" AND ShipName like '%" + shipName.trim() +
            // "%' ");
            query.append(" AND ShipName like ? ");
        }
        // Ho hieu
        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            // query.append(" AND CallSign like '%" + callSign.trim() +
            // "%' ");
            query.append(" AND CallSign like ? ");
        }
        // Tu ngay
        if (Validator.isNotNull(shipDateFrom)
                && shipDateFrom.trim().length() > 0) {
            Date dateFrom = FormatData
                    .parseDateShort3StringToDate(shipDateFrom.trim());
            shipDateFrom = FormatData.parseDateToTring(dateFrom);
            query.append(" AND (ShipDateFrom >= concat(date('"
                    + shipDateFrom
                    + "'), ' 00:00:00')  OR ShipDateFrom IS NULL)");
        }

        // Den ngay
        if (Validator.isNotNull(shipDateTo)
                && shipDateTo.trim().length() > 0) {
            Date dateTo = FormatData.parseDateShort3StringToDate(shipDateTo
                    .trim());
            shipDateTo = FormatData.parseDateToTring(dateTo);
            query.append(" AND (ShipDateTo <= concat(date('" + shipDateTo
                    + "'), ' 23:59:59')  OR ShipDateTo IS NULL)");
        }
        // query.append(" ORDER BY id DESC");

        sql = sql + query.toString();
        if (start >= 0) {
            int count = end - start;
            sql = sql + " limit " + start + "," + count;
        }
        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();
        log.debug("=========TAU SEARCH --findTempDocumentInfo========"
                + sql);


        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            builder.appendNamedParameterMap("maritimeCode",maritimeCode);
            log.debug("===qPos.add===maritimeCode===" + maritimeCode);
        }
        if (Validator.isNotNull(portCode) && portCode.trim().length() > 0
                && !portCode.trim().equalsIgnoreCase("LUA_CHON1")) {
            builder.appendNamedParameterMap("portCode",portCode);
            log.debug("===qPos.add===portCode===" + portCode);
        }

        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            builder.appendNamedParameterMap("shipName","%" + shipName.trim() + "%");
        }
        // Ho hieu
        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            builder.appendNamedParameterMap("callSign","%" + callSign.trim() + "%");
        }

        
           return (List<TempDocument>)  queryFactory.getResultList(builder);
    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
}

public int countTempDocumentInfo(String documentStatusCode,
                                 String maritimeCode, String portCode, String shipName,
                                 String callSign, String shipDateFrom, String shipDateTo) throws SystemException { int count = 0; try {

    StringBuilder query = new StringBuilder();

    String sql = "SELECT count(*) as total FROM temp_document WHERE 1=1";
    // documentStatusCode
    if (Validator.isNotNull(documentStatusCode)
            && documentStatusCode.trim().length() > 0) {
        query.append(" AND DocumentStatusCode IN ("
                + documentStatusCode + ")");
    }
    // cang vu hang hai
    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        query.append(" AND MaritimeCode= :maritimeCode");
    }

    // cang bien
    if (Validator.isNotNull(portCode) && portCode.trim().length() > 0
            && !portCode.trim().equalsIgnoreCase("LUA_CHON1")) {
        query.append(" AND portCode= :portCode");
    }
    // ten tau
    if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
        // query.append(" AND ShipName like '%" + shipName.trim() +
        // "%' ");
        query.append(" AND ShipName like ? ");
    }
    // Ho hieu
    if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
        // query.append(" AND CallSign like '%" + callSign.trim() +
        // "%' ");
        query.append(" AND CallSign like ? ");
    }

    // Tu ngay
    if (Validator.isNotNull(shipDateFrom)
            && shipDateFrom.trim().length() > 0) {
        Date dateFrom = FormatData
                .parseDateShort3StringToDate(shipDateFrom.trim());
        shipDateFrom = FormatData.parseDateToTring(dateFrom);
        query.append(" AND (ShipDateFrom >= concat(date('"
                + shipDateFrom
                + "'), ' 00:00:00')  OR ShipDateFrom IS NULL)");
    }

    // Den ngay
    if (Validator.isNotNull(shipDateTo)
            && shipDateTo.trim().length() > 0) {
        Date dateTo = FormatData.parseDateShort3StringToDate(shipDateTo
                .trim());
        shipDateTo = FormatData.parseDateToTring(dateTo);
        query.append(" AND (ShipDateTo <= concat(date('" + shipDateTo
                + "'), ' 23:59:59')  OR ShipDateTo IS NULL)");
    }

    sql = sql + query.toString();
    log.debug("=========TAU SEARCH --countTempDocumentInfo========"
            + sql);

    QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();




    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
    }
    if (Validator.isNotNull(portCode) && portCode.trim().length() > 0
            && !portCode.trim().equalsIgnoreCase("LUA_CHON1")) {
        builder.appendNamedParameterMap("portCode",portCode.trim());
    }

    if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
        builder.appendNamedParameterMap("shipName","%" + shipName.trim() + "%");
    }
    // Ho hieu
    if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
        builder.appendNamedParameterMap("callSign","%" + callSign.trim() + "%");
    }


    count = (Integer) queryFactory.getSingleResult(builder);
} catch (Exception e) {
    throw new SystemException(e);
} finally {

}
    return count;
}

public List<TempDocument> findKeHoachYCBS() throws SystemException {

    try {

        StringBuilder query = new StringBuilder();
        // 'YÃƒÂªu cÃ¡ÂºÂ§u sÃ¡Â»Â­a Ã„â€˜Ã¡Â»â€¢i bÃ¡Â»â€¢ sung' = 27

        query.append("SELECT * FROM temp_document WHERE RequestState = '27' ");
        query.append("and ( ");
        query.append("(ShipDateFrom < CURDATE() and ShipDateTo is null) ");
        query.append("or ");
        query.append("(ShipDateFrom is not null and ShipDateTo < CURDATE()) ");
        query.append(")");

        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Integer.class).build();

        log.debug("=========findKeHoachYCBS========" + query.toString());


        return (List<TempDocument>)  queryFactory.getResultList(builder);
    }
catch (Exception e) {
        throw new SystemException(e);
		} finally {

                }
                }
    public List<TempDocument> findDanhSachHoSoVanThuChoDongDau(
            String documentTypeCode, String maritimeCode, String portCode,
            String lastPortCode, String shipName, String stateCode,
            String callSign, String imo, String shipPosition,
            String shipDateFromStart, String shipDateFromEnd,
            String shipDateToStart, String shipDateToEnd,
            String documentStatusCode, String arrivalShipAgency,
            String nameArrivalShipAgency, String departureShipAgency,
            String nameDepartureShipAgency, String portRegionCode,
            String ngayLamThuTucFrom, String ngayLamThuTucTo, String maBanKhai,
            int start, int end) throws SystemException {

        
        try {
           
            StringBuilder query = new StringBuilder();
            StringBuilder queryShipAgency = new StringBuilder();

            String sql = "";
            String sql1 = "Select temp_document.* from temp_document "
                    + " INNER JOIN issue_shifting_order ON issue_shifting_order.DocumentName = temp_document.DocumentName "
                    + " AND issue_shifting_order.DocumentYear = temp_document.DocumentYear "
                    + " AND temp_document.RequestState in (14, 15) and issue_shifting_order.StampStatus = 1 ";
            String sql2 = "Select temp_document.* from temp_document "
                    + " INNER JOIN issue_acceptance ON issue_acceptance.DocumentName = temp_document.DocumentName "
                    + " AND issue_acceptance.DocumentYear = temp_document.DocumentYear "
                    + " AND temp_document.DocumentStatusCode in (19, 20) and issue_acceptance.StampStatus = 1 ";
            String sql3 = "Select temp_document.* from temp_document "
                    + " INNER JOIN issue_port_clearance ON issue_port_clearance.DocumentName = temp_document.DocumentName "
                    + " AND issue_port_clearance.DocumentYear = temp_document.DocumentYear "
                    + " AND temp_document.DocumentStatusCode in (19, 20) and issue_port_clearance.StampStatus = 1 ";
            String sql4 = "Select temp_document.* from temp_document "
                    + " INNER JOIN issue_permission_for_transit ON issue_permission_for_transit.DocumentName = temp_document.DocumentName "
                    + " AND issue_permission_for_transit.DocumentYear = temp_document.DocumentYear "
                    + " AND temp_document.DocumentStatusCode in (19, 20) and issue_permission_for_transit.StampStatus = 1 ";

            if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                    .trim().length() > 0)
                    && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                    .trim().length() > 0)) {

                queryShipAgency.append("inner join dm_ship_agency dmDep ");
                queryShipAgency
                        .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
                queryShipAgency.append("AND (dmDep.ShipAgencyNameVN like '%"
                        + nameArrivalShipAgency.trim() + "%' "
                        + " OR dmDep.ShipAgencyNameVN like '%"
                        + nameDepartureShipAgency.trim() + "%' ");

            }

            if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                    .trim().length() == 0)
                    && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                    .trim().length() > 0)) {
                queryShipAgency.append("inner join dm_ship_agency dmDep ");
                queryShipAgency
                        .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
                queryShipAgency.append("AND dmDep.ShipAgencyNameVN like '%"
                        + nameDepartureShipAgency.trim() + "%' ");

            }

            if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                    .trim().length() > 0)
                    && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                    .trim().length() == 0)) {
                queryShipAgency.append("inner join dm_ship_agency dmArri ");
                queryShipAgency
                        .append("on temp_document.ArrivalShipAgency = dmArri.ShipAgencyCode ");
                queryShipAgency.append("AND dmArri.ShipAgencyNameVN like '%"
                        + nameArrivalShipAgency.trim() + "%' ");

            }

            query.append(" WHERE 1=1 ");

            // Pb NC,XC,QC
            if (Validator.isNotNull(documentTypeCode)
                    && documentTypeCode.trim().length() > 0
                    && (documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
                query.append(" AND temp_document.DocumentTypeCode = '"
                        + documentTypeCode.trim() + "' ");
            }

            // CANG VU
            if (Validator.isNotNull(maritimeCode)
                    && maritimeCode.trim().length() > 0
                    && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
                query.append(" AND temp_document.MaritimeCode = '"
                        + maritimeCode.trim() + "' ");
            }

            // CANG DEN
            if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
                query.append(" AND temp_document.PortCode = '"
                        + portCode.trim() + "' ");
            }

            // CANG ROI CUOI CUNG
            if (Validator.isNotNull(lastPortCode)
                    && lastPortCode.trim().length() > 0) {
                query.append(" AND temp_document.LastPortCode = '"
                        + lastPortCode.trim() + "' ");
            }

            // TEN TAU
            if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
                query.append(" AND temp_document.ShipName like '%"
                        + shipName.trim() + "%' ");
            }

            // QUOC TICH
            if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
                query.append(" AND temp_document.StateCode = '"
                        + stateCode.trim() + "' ");
            }

            // HO HIEU
            if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
                query.append(" AND temp_document.CallSign like '%"
                        + callSign.trim() + "%' ");
            }
            if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
                query.append(" AND temp_document.imo like '%" + imo.trim()
                        + "%' ");
            }
            // TAU DEN ROI
            if (Validator.isNotNull(shipPosition)
                    && shipPosition.trim().length() > 0
                    && FormatData.convertToInt(shipPosition) > 0) {
                query.append(" AND temp_document.ShipPosition ="
                        + FormatData.convertToInt(shipPosition));
            }

            // thoi gian den FROM
            if (Validator.isNotNull(shipDateFromStart)
                    && shipDateFromStart.trim().length() > 0) {
                Date dateFromStart = FormatData
                        .parseDateShort3StringToDate(shipDateFromStart.trim());
                shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
                query.append(" AND (temp_document.ShipDateFrom >= concat(date('"
                        + shipDateFromStart
                        + "'), ' 00:00:00')  OR temp_document.ShipDateFrom IS NULL)");
            }

            // thoi gian den To
            if (Validator.isNotNull(shipDateFromEnd)
                    && shipDateFromEnd.trim().length() > 0) {
                Date dateFromEnd = FormatData
                        .parseDateShort3StringToDate(shipDateFromEnd.trim());
                shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
                query.append(" AND (temp_document.ShipDateFrom <= concat(date('"
                        + shipDateFromEnd
                        + "'), ' 23:59:59')  OR temp_document.ShipDateFrom IS NULL)");
            }

            // thoi gian roi FROM
            if (Validator.isNotNull(shipDateToStart)
                    && shipDateToStart.trim().length() > 0) {
                Date dateToStart = FormatData
                        .parseDateShort3StringToDate(shipDateToStart.trim());
                shipDateToStart = FormatData.parseDateToTring(dateToStart);
                query.append(" AND (temp_document.ShipDateTo >= concat(date('"
                        + shipDateToStart
                        + "'), ' 00:00:00')  OR temp_document.ShipDateTo IS NULL)");
            }

            // thoi gian roi To
            if (Validator.isNotNull(shipDateToEnd)
                    && shipDateToEnd.trim().length() > 0) {
                Date dateToEnd = FormatData
                        .parseDateShort3StringToDate(shipDateToEnd.trim());
                shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
                query.append(" AND (temp_document.ShipDateTo <= concat(date('"
                        + shipDateToEnd
                        + "'), ' 23:59:59')  OR temp_document.ShipDateTo IS NULL)");
            }

            // ngay gio lam thu tuc
            // ngayLamThuTucFrom
            // ngayLamThuTucTo
            if (Validator.isNotNull(ngayLamThuTucFrom)
                    && ngayLamThuTucFrom.trim().length() > 0) {
                Date dateLamThuTucFrom = FormatData
                        .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
                ngayLamThuTucFrom = FormatData
                        .parseDateToTring(dateLamThuTucFrom);
                query.append(" AND (temp_document.CreatedDate >= concat(date('"
                        + ngayLamThuTucFrom
                        + "'), ' 00:00:00')  OR temp_document.CreatedDate IS NULL)");
            }

            if (Validator.isNotNull(ngayLamThuTucTo)
                    && ngayLamThuTucTo.trim().length() > 0) {
                Date dateLamThuTucTo = FormatData
                        .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
                ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
                query.append(" AND (temp_document.CreatedDate <= concat(date('"
                        + ngayLamThuTucTo
                        + "'), ' 23:59:59')  OR temp_document.CreatedDate IS NULL)");
            }

            // ma~ ban khai
            if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
                query.append(" AND (temp_document.DocumentName = '"
                        + maBanKhai.trim() + "')");
            }

            // TRANG THAI
            if (Validator.isNotNull(documentStatusCode)
                    && documentStatusCode.trim().length() > 0) {
                query.append(" AND temp_document.documentStatusCode IN ("
                        + documentStatusCode.trim() + ")");
            }

            // KHU VUC CANG
            if (Validator.isNotNull(portRegionCode)
                    && portRegionCode.trim().length() > 0) {
                query.append(" AND temp_document.PortRegionCode = '"
                        + portRegionCode.trim() + "' ");
            }

            StringBuilder queryOrder = new StringBuilder();
            queryOrder.append(" ORDER BY ");
            queryOrder.append("case when DocumentStatusCode = '0' then 0 ");
            queryOrder.append("when DocumentStatusCode = '18' then 1 ");
            queryOrder.append("when DocumentStatusCode = '12' then 2 ");
            queryOrder.append("when DocumentStatusCode = '20' then 3 ");
            queryOrder.append("when DocumentStatusCode = '13' then 4 ");
            queryOrder.append("when DocumentStatusCode = '19' then 5 ");
            queryOrder.append("when DocumentStatusCode = '10' then 6 ");
            queryOrder.append("end asc ");

            // TODO THU TUC sap xep thu tu ho so
            if (Validator.isNotNull(documentTypeCode)
                    && documentTypeCode.trim().length() > 0
                    && (documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_VAO_CANG) || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND))) {
                queryOrder
                        .append(", ShipDateFrom ASC, CreatedDate ASC, id ASC");

            } else if (Validator.isNotNull(documentTypeCode)
                    && documentTypeCode.trim().length() > 0
                    && (documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                    || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_ROI_CANG) || documentTypeCode
                    .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
                queryOrder.append(", ShipDateTo ASC, CreatedDate ASC, id ASC");

            } else {
                queryOrder
                        .append(", ShipDateFrom ASC, ShipDateTo ASC, CreatedDate ASC, id ASC");
            }

            sql = sql + "( " + sql1 + queryShipAgency.toString()
                    + query.toString() + " ) UNION ( " + sql2
                    + queryShipAgency.toString() + query.toString()
                    + " ) UNION ( " + sql3 + queryShipAgency.toString()
                    + query.toString() + " ) UNION ( " + sql4
                    + queryShipAgency.toString() + query.toString() + " )"
                    + queryOrder.toString();
            if (start >= 0) {
                int count = end - start;
                sql = sql + " limit " + start + "," + count;
            }
            log.debug("=========findDanhSachHoSoVanThuChoDongDau ===");
            log.debug("=========findDanhSachHoSoVanThuChoDongDau ===" + sql);
            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();

            return (List<TempDocument>)  queryFactory.getResultList(builder);
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            
        }
    }

public List<TempDocument> findDanhSachHoSoVanThuChoDongDau(
        String documentTypeCode, String maritimeCode, String portCode,
        String lastPortCode, String shipName, String stateCode,
        String callSign, String imo, String shipPosition,
        String shipDateFromStart, String shipDateFromEnd,
        String shipDateToStart, String shipDateToEnd,
        String documentStatusCode, String arrivalShipAgency,
        String nameArrivalShipAgency, String departureShipAgency,
        String nameDepartureShipAgency, String portRegionCode,
        String ngayLamThuTucFrom, String ngayLamThuTucTo, String maBanKhai,
        String nameOfShipownersAgents, String maritimeCodeNext, int start,
        int end) throws SystemException {


    try {

        StringBuilder query = new StringBuilder();
        StringBuilder queryShipAgency = new StringBuilder();

        String sql = "";
        String sql1 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_shifting_order ON issue_shifting_order.DocumentName = temp_document.DocumentName "
                + " AND issue_shifting_order.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.RequestState in (14, 15) and issue_shifting_order.StampStatus = 1 ";
        String sql2 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_acceptance ON issue_acceptance.DocumentName = temp_document.DocumentName "
                + " AND issue_acceptance.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.DocumentStatusCode in (19, 20) and issue_acceptance.StampStatus = 1 ";
        String sql3 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_port_clearance ON issue_port_clearance.DocumentName = temp_document.DocumentName "
                + " AND issue_port_clearance.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.DocumentStatusCode in (19, 20) and issue_port_clearance.StampStatus = 1 ";
        String sql4 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_permission_for_transit ON issue_permission_for_transit.DocumentName = temp_document.DocumentName "
                + " AND issue_permission_for_transit.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.DocumentStatusCode in (19, 20) and issue_permission_for_transit.StampStatus = 1 ";

        if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                .trim().length() > 0)
                && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                .trim().length() > 0)) {

            queryShipAgency.append("inner join dm_ship_agency dmDep ");
            queryShipAgency
                    .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
            queryShipAgency.append("AND (dmDep.ShipAgencyNameVN like '%"
                    + nameArrivalShipAgency.trim() + "%' "
                    + " OR dmDep.ShipAgencyNameVN like '%"
                    + nameDepartureShipAgency.trim() + "%' ");

        }

        if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                .trim().length() == 0)
                && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                .trim().length() > 0)) {
            queryShipAgency.append("inner join dm_ship_agency dmDep ");
            queryShipAgency
                    .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
            queryShipAgency.append("AND dmDep.ShipAgencyNameVN like '%"
                    + nameDepartureShipAgency.trim() + "%' ");

        }

        if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                .trim().length() > 0)
                && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                .trim().length() == 0)) {
            queryShipAgency.append("inner join dm_ship_agency dmArri ");
            queryShipAgency
                    .append("on temp_document.ArrivalShipAgency = dmArri.ShipAgencyCode ");
            queryShipAgency.append("AND dmArri.ShipAgencyNameVN like '%"
                    + nameArrivalShipAgency.trim() + "%' ");

        }

        query.append(" WHERE 1=1 ");

        // Pb NC,XC,QC
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
            query.append(" AND temp_document.DocumentTypeCode = '"
                    + documentTypeCode.trim() + "' ");
        }

        // CANG VU
        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            query.append(" AND temp_document.MaritimeCode = '"
                    + maritimeCode.trim() + "' ");
        }

        // CANG DEN
        if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
            query.append(" AND temp_document.PortCode = '"
                    + portCode.trim() + "' ");
        }

        // CANG ROI CUOI CUNG
        if (Validator.isNotNull(lastPortCode)
                && lastPortCode.trim().length() > 0) {
            query.append(" AND temp_document.LastPortCode = '"
                    + lastPortCode.trim() + "' ");
        }

        // TEN TAU
        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            query.append(" AND temp_document.ShipName like '%"
                    + shipName.trim() + "%' ");
        }

        // QUOC TICH
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            query.append(" AND temp_document.StateCode = '"
                    + stateCode.trim() + "' ");
        }

        // HO HIEU
        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            query.append(" AND temp_document.CallSign like '%"
                    + callSign.trim() + "%' ");
        }
        if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
            query.append(" AND temp_document.imo like '%" + imo.trim()
                    + "%' ");
        }
        // TAU DEN ROI
        if (Validator.isNotNull(shipPosition)
                && shipPosition.trim().length() > 0
                && FormatData.convertToInt(shipPosition) > 0) {
            query.append(" AND temp_document.ShipPosition ="
                    + FormatData.convertToInt(shipPosition));
        }

        // thoi gian den FROM
        if (Validator.isNotNull(shipDateFromStart)
                && shipDateFromStart.trim().length() > 0) {
            Date dateFromStart = FormatData
                    .parseDateShort3StringToDate(shipDateFromStart.trim());
            shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
            query.append(" AND (temp_document.ShipDateFrom >= concat(date('"
                    + shipDateFromStart
                    + "'), ' 00:00:00')  OR temp_document.ShipDateFrom IS NULL)");
        }

        // thoi gian den To
        if (Validator.isNotNull(shipDateFromEnd)
                && shipDateFromEnd.trim().length() > 0) {
            Date dateFromEnd = FormatData
                    .parseDateShort3StringToDate(shipDateFromEnd.trim());
            shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
            query.append(" AND (temp_document.ShipDateFrom <= concat(date('"
                    + shipDateFromEnd
                    + "'), ' 23:59:59')  OR temp_document.ShipDateFrom IS NULL)");
        }

        // thoi gian roi FROM
        if (Validator.isNotNull(shipDateToStart)
                && shipDateToStart.trim().length() > 0) {
            Date dateToStart = FormatData
                    .parseDateShort3StringToDate(shipDateToStart.trim());
            shipDateToStart = FormatData.parseDateToTring(dateToStart);
            query.append(" AND (temp_document.ShipDateTo >= concat(date('"
                    + shipDateToStart
                    + "'), ' 00:00:00')  OR temp_document.ShipDateTo IS NULL)");
        }

        // thoi gian roi To
        if (Validator.isNotNull(shipDateToEnd)
                && shipDateToEnd.trim().length() > 0) {
            Date dateToEnd = FormatData
                    .parseDateShort3StringToDate(shipDateToEnd.trim());
            shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
            query.append(" AND (temp_document.ShipDateTo <= concat(date('"
                    + shipDateToEnd
                    + "'), ' 23:59:59')  OR temp_document.ShipDateTo IS NULL)");
        }

        // ngay gio lam thu tuc
        // ngayLamThuTucFrom
        // ngayLamThuTucTo
        if (Validator.isNotNull(ngayLamThuTucFrom)
                && ngayLamThuTucFrom.trim().length() > 0) {
            Date dateLamThuTucFrom = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
            ngayLamThuTucFrom = FormatData
                    .parseDateToTring(dateLamThuTucFrom);
            query.append(" AND (temp_document.CreatedDate >= concat(date('"
                    + ngayLamThuTucFrom
                    + "'), ' 00:00:00')  OR temp_document.CreatedDate IS NULL)");
        }

        if (Validator.isNotNull(ngayLamThuTucTo)
                && ngayLamThuTucTo.trim().length() > 0) {
            Date dateLamThuTucTo = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
            ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
            query.append(" AND (temp_document.CreatedDate <= concat(date('"
                    + ngayLamThuTucTo
                    + "'), ' 23:59:59')  OR temp_document.CreatedDate IS NULL)");
        }

        // ma~ ban khai
        if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
            query.append(" AND (temp_document.DocumentName = '"
                    + maBanKhai.trim() + "')");
        }

        // TRANG THAI
        if (Validator.isNotNull(documentStatusCode)
                && documentStatusCode.trim().length() > 0) {
            query.append(" AND temp_document.documentStatusCode IN ("
                    + documentStatusCode.trim() + ")");
        }

        // KHU VUC CANG
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            query.append(" AND temp_document.PortRegionCode = '"
                    + portRegionCode.trim() + "' ");
        }

        if (Validator.isNotNull(nameOfShipownersAgents)
                && nameOfShipownersAgents.trim().length() > 0) {
            query.append(" AND temp_document.NameOfShipownersAgents like '%"
                    + nameOfShipownersAgents.trim() + "%' ");
        }
        if (Validator.isNotNull(maritimeCodeNext)
                && maritimeCodeNext.trim().length() > 0) {
            query.append(" AND temp_document.LastPortCode like '%"
                    + maritimeCodeNext.trim() + "%' ");
        }
        StringBuilder queryOrder = new StringBuilder();
        queryOrder.append(" ORDER BY ");
        queryOrder.append("case when DocumentStatusCode = '0' then 0 ");
        queryOrder.append("when DocumentStatusCode = '18' then 1 ");
        queryOrder.append("when DocumentStatusCode = '12' then 2 ");
        queryOrder.append("when DocumentStatusCode = '20' then 3 ");
        queryOrder.append("when DocumentStatusCode = '13' then 4 ");
        queryOrder.append("when DocumentStatusCode = '19' then 5 ");
        queryOrder.append("when DocumentStatusCode = '10' then 6 ");
        queryOrder.append("end asc ");

        // TODO THU TUC sap xep thu tu ho so
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND))) {
            queryOrder
                    .append(", ShipDateFrom ASC, CreatedDate ASC, id ASC");

        } else if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
            queryOrder.append(", ShipDateTo ASC, CreatedDate ASC, id ASC");

        } else {
            queryOrder
                    .append(", ShipDateFrom ASC, ShipDateTo ASC, CreatedDate ASC, id ASC");
        }

        sql = sql + "( " + sql1 + queryShipAgency.toString()
                + query.toString() + " ) UNION ( " + sql2
                + queryShipAgency.toString() + query.toString()
                + " ) UNION ( " + sql3 + queryShipAgency.toString()
                + query.toString() + " ) UNION ( " + sql4
                + queryShipAgency.toString() + query.toString() + " )"
                + queryOrder.toString();
        if (start >= 0) {
            int count = end - start;
            sql = sql + " limit " + start + "," + count;
        }
        log.debug("=========findDanhSachHoSoVanThuChoDongDau ===");
        log.info("=========findDanhSachHoSoVanThuChoDongDau ===" + sql);
        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();



        
           return (List<TempDocument>)  queryFactory.getResultList(builder);

    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
}

public int countDanhSachHoSoVanThuChoDongDau(String documentTypeCode,
                                             String maritimeCode, String portCode, String lastPortCode,
                                             String shipName, String stateCode, String callSign, String imo,
                                             String shipPosition, String shipDateFromStart,
                                             String shipDateFromEnd, String shipDateToStart,
                                             String shipDateToEnd, String documentStatusCode,
                                             String arrivalShipAgency, String nameArrivalShipAgency,
                                             String departureShipAgency, String nameDepartureShipAgency,
                                             String portRegionCode, String ngayLamThuTucFrom,
                                             String ngayLamThuTucTo, String maBanKhai) throws SystemException { int count = 0; try {


    StringBuilder query = new StringBuilder();
    StringBuilder queryShipAgency = new StringBuilder();

    String sql = "SELECT count(*) as total FROM ";

    String sql1 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_shifting_order ON issue_shifting_order.DocumentName = temp_document.DocumentName "
            + " AND issue_shifting_order.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.RequestState in (14, 15) and issue_shifting_order.StampStatus = 1 ";
    String sql2 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_acceptance ON issue_acceptance.DocumentName = temp_document.DocumentName "
            + " AND issue_acceptance.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_acceptance.StampStatus = 1 ";
    String sql3 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_port_clearance ON issue_port_clearance.DocumentName = temp_document.DocumentName "
            + " AND issue_port_clearance.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_port_clearance.StampStatus = 1 ";
    String sql4 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_permission_for_transit ON issue_permission_for_transit.DocumentName = temp_document.DocumentName "
            + " AND issue_permission_for_transit.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_permission_for_transit.StampStatus = 1 ";

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() > 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() > 0)) {

        queryShipAgency.append("inner join dm_ship_agency dmDep ");
        queryShipAgency
                .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
        queryShipAgency.append("AND (dmDep.ShipAgencyNameVN like '%"
                + nameArrivalShipAgency.trim() + "%' "
                + " OR dmDep.ShipAgencyNameVN like '%"
                + nameDepartureShipAgency.trim() + "%' ");

    }

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() == 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() > 0)) {
        queryShipAgency.append("inner join dm_ship_agency dmDep ");
        queryShipAgency
                .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
        queryShipAgency.append("AND dmDep.ShipAgencyNameVN like '%"
                + nameDepartureShipAgency.trim() + "%' ");

    }

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() > 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() == 0)) {
        queryShipAgency.append("inner join dm_ship_agency dmArri ");
        queryShipAgency
                .append("on temp_document.ArrivalShipAgency = dmArri.ShipAgencyCode ");
        queryShipAgency.append("AND dmArri.ShipAgencyNameVN like '%"
                + nameArrivalShipAgency.trim() + "%' ");

    }

    query.append(" WHERE 1=1 ");

    // Pb NC,XC,QC
    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0
            && (documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
        query.append(" AND temp_document.DocumentTypeCode = '"
                + documentTypeCode.trim() + "' ");
    }

    // CANG VU
    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        query.append(" AND temp_document.MaritimeCode = '"
                + maritimeCode.trim() + "' ");
    }

    // CANG DEN
    if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
        query.append(" AND temp_document.PortCode = '"
                + portCode.trim() + "' ");
    }

    // CANG ROI CUOI CUNG
    if (Validator.isNotNull(lastPortCode)
            && lastPortCode.trim().length() > 0) {
        query.append(" AND temp_document.LastPortCode = '"
                + lastPortCode.trim() + "' ");
    }

    // TEN TAU
    if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
        query.append(" AND temp_document.ShipName like '%"
                + shipName.trim() + "%' ");
    }

    // QUOC TICH
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        query.append(" AND temp_document.StateCode = '"
                + stateCode.trim() + "' ");
    }

    // HO HIEU
    if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
        query.append(" AND temp_document.CallSign like '%"
                + callSign.trim() + "%' ");
    }
    if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
        query.append(" AND temp_document.imo like '%" + imo.trim()
                + "%' ");
    }
    // TAU DEN ROI
    if (Validator.isNotNull(shipPosition)
            && shipPosition.trim().length() > 0
            && FormatData.convertToInt(shipPosition) > 0) {
        query.append(" AND temp_document.ShipPosition ="
                + FormatData.convertToInt(shipPosition));
    }

    // thoi gian den FROM
    if (Validator.isNotNull(shipDateFromStart)
            && shipDateFromStart.trim().length() > 0) {
        Date dateFromStart = FormatData
                .parseDateShort3StringToDate(shipDateFromStart.trim());
        shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
        query.append(" AND (temp_document.ShipDateFrom >= concat(date('"
                + shipDateFromStart
                + "'), ' 00:00:00')  OR temp_document.ShipDateFrom IS NULL)");
    }

    // thoi gian den To
    if (Validator.isNotNull(shipDateFromEnd)
            && shipDateFromEnd.trim().length() > 0) {
        Date dateFromEnd = FormatData
                .parseDateShort3StringToDate(shipDateFromEnd.trim());
        shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
        query.append(" AND (temp_document.ShipDateFrom <= concat(date('"
                + shipDateFromEnd
                + "'), ' 23:59:59')  OR temp_document.ShipDateFrom IS NULL)");
    }

    // thoi gian roi FROM
    if (Validator.isNotNull(shipDateToStart)
            && shipDateToStart.trim().length() > 0) {
        Date dateToStart = FormatData
                .parseDateShort3StringToDate(shipDateToStart.trim());
        shipDateToStart = FormatData.parseDateToTring(dateToStart);
        query.append(" AND (temp_document.ShipDateTo >= concat(date('"
                + shipDateToStart
                + "'), ' 00:00:00')  OR temp_document.ShipDateTo IS NULL)");
    }

    // thoi gian roi To
    if (Validator.isNotNull(shipDateToEnd)
            && shipDateToEnd.trim().length() > 0) {
        Date dateToEnd = FormatData
                .parseDateShort3StringToDate(shipDateToEnd.trim());
        shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
        query.append(" AND (temp_document.ShipDateTo <= concat(date('"
                + shipDateToEnd
                + "'), ' 23:59:59')  OR temp_document.ShipDateTo IS NULL)");
    }

    // ngay gio lam thu tuc
    // ngayLamThuTucFrom
    // ngayLamThuTucTo
    if (Validator.isNotNull(ngayLamThuTucFrom)
            && ngayLamThuTucFrom.trim().length() > 0) {
        Date dateLamThuTucFrom = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
        ngayLamThuTucFrom = FormatData
                .parseDateToTring(dateLamThuTucFrom);
        query.append(" AND (temp_document.CreatedDate >= concat(date('"
                + ngayLamThuTucFrom
                + "'), ' 00:00:00')  OR temp_document.CreatedDate IS NULL)");
    }

    if (Validator.isNotNull(ngayLamThuTucTo)
            && ngayLamThuTucTo.trim().length() > 0) {
        Date dateLamThuTucTo = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
        ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
        query.append(" AND (temp_document.CreatedDate <= concat(date('"
                + ngayLamThuTucTo
                + "'), ' 23:59:59')  OR temp_document.CreatedDate IS NULL)");
    }

    // ma~ ban khai
    if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
        query.append(" AND (temp_document.DocumentName = '"
                + maBanKhai.trim() + "')");
    }

    // TRANG THAI
    if (Validator.isNotNull(documentStatusCode)
            && documentStatusCode.trim().length() > 0) {
        query.append(" AND temp_document.documentStatusCode IN ("
                + documentStatusCode.trim() + ")");
    }

    // KHU VUC CANG
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        query.append(" AND temp_document.PortRegionCode = '"
                + portRegionCode.trim() + "' ");
    }

    sql = sql + "( " + "( " + sql1 + queryShipAgency.toString()
            + query.toString() + " ) UNION ( " + sql2
            + queryShipAgency.toString() + query.toString()
            + " ) UNION ( " + sql3 + queryShipAgency.toString()
            + query.toString() + " ) UNION ( " + sql4
            + queryShipAgency.toString() + query.toString() + " )"
            + " ) temp_document ";

    log.debug("=========THU TUC SEARCH --count TempDocument ========");

    QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();





    count = (Integer) queryFactory.getSingleResult(builder);
} catch (Exception e) {
    throw new SystemException(e);
} finally {

}
    return count;
}

public int countDanhSachHoSoVanThuChoDongDau(String documentTypeCode,
                                             String maritimeCode, String portCode, String lastPortCode,
                                             String shipName, String stateCode, String callSign, String imo,
                                             String shipPosition, String shipDateFromStart,
                                             String shipDateFromEnd, String shipDateToStart,
                                             String shipDateToEnd, String documentStatusCode,
                                             String arrivalShipAgency, String nameArrivalShipAgency,
                                             String departureShipAgency, String nameDepartureShipAgency,
                                             String portRegionCode, String ngayLamThuTucFrom,
                                             String ngayLamThuTucTo, String maBanKhai,
                                             String nameOfShipownersAgents, String maritimeCodeNext) throws SystemException { int count = 0; try {


    StringBuilder query = new StringBuilder();
    StringBuilder queryShipAgency = new StringBuilder();

    String sql = "SELECT count(*) as total FROM ";

    String sql1 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_shifting_order ON issue_shifting_order.DocumentName = temp_document.DocumentName "
            + " AND issue_shifting_order.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.RequestState in (14, 15) and issue_shifting_order.StampStatus = 1 ";
    String sql2 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_acceptance ON issue_acceptance.DocumentName = temp_document.DocumentName "
            + " AND issue_acceptance.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_acceptance.StampStatus = 1 ";
    String sql3 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_port_clearance ON issue_port_clearance.DocumentName = temp_document.DocumentName "
            + " AND issue_port_clearance.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_port_clearance.StampStatus = 1 ";
    String sql4 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_permission_for_transit ON issue_permission_for_transit.DocumentName = temp_document.DocumentName "
            + " AND issue_permission_for_transit.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_permission_for_transit.StampStatus = 1 ";

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() > 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() > 0)) {

        queryShipAgency.append("inner join dm_ship_agency dmDep ");
        queryShipAgency
                .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
        queryShipAgency.append("AND (dmDep.ShipAgencyNameVN like '%"
                + nameArrivalShipAgency.trim() + "%' "
                + " OR dmDep.ShipAgencyNameVN like '%"
                + nameDepartureShipAgency.trim() + "%' ");

    }

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() == 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() > 0)) {
        queryShipAgency.append("inner join dm_ship_agency dmDep ");
        queryShipAgency
                .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
        queryShipAgency.append("AND dmDep.ShipAgencyNameVN like '%"
                + nameDepartureShipAgency.trim() + "%' ");

    }

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() > 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() == 0)) {
        queryShipAgency.append("inner join dm_ship_agency dmArri ");
        queryShipAgency
                .append("on temp_document.ArrivalShipAgency = dmArri.ShipAgencyCode ");
        queryShipAgency.append("AND dmArri.ShipAgencyNameVN like '%"
                + nameArrivalShipAgency.trim() + "%' ");

    }

    query.append(" WHERE 1=1 ");

    // Pb NC,XC,QC
    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0
            && (documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
        query.append(" AND temp_document.DocumentTypeCode = '"
                + documentTypeCode.trim() + "' ");
    }

    // CANG VU
    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        query.append(" AND temp_document.MaritimeCode = '"
                + maritimeCode.trim() + "' ");
    }

    // CANG DEN
    if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
        query.append(" AND temp_document.PortCode = '"
                + portCode.trim() + "' ");
    }

    // CANG ROI CUOI CUNG
    if (Validator.isNotNull(lastPortCode)
            && lastPortCode.trim().length() > 0) {
        query.append(" AND temp_document.LastPortCode = '"
                + lastPortCode.trim() + "' ");
    }

    // TEN TAU
    if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
        query.append(" AND temp_document.ShipName like '%"
                + shipName.trim() + "%' ");
    }

    // QUOC TICH
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        query.append(" AND temp_document.StateCode = '"
                + stateCode.trim() + "' ");
    }

    // HO HIEU
    if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
        query.append(" AND temp_document.CallSign like '%"
                + callSign.trim() + "%' ");
    }
    if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
        query.append(" AND temp_document.imo like '%" + imo.trim()
                + "%' ");
    }
    // TAU DEN ROI
    if (Validator.isNotNull(shipPosition)
            && shipPosition.trim().length() > 0
            && FormatData.convertToInt(shipPosition) > 0) {
        query.append(" AND temp_document.ShipPosition ="
                + FormatData.convertToInt(shipPosition));
    }

    // thoi gian den FROM
    if (Validator.isNotNull(shipDateFromStart)
            && shipDateFromStart.trim().length() > 0) {
        Date dateFromStart = FormatData
                .parseDateShort3StringToDate(shipDateFromStart.trim());
        shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
        query.append(" AND (temp_document.ShipDateFrom >= concat(date('"
                + shipDateFromStart
                + "'), ' 00:00:00')  OR temp_document.ShipDateFrom IS NULL)");
    }

    // thoi gian den To
    if (Validator.isNotNull(shipDateFromEnd)
            && shipDateFromEnd.trim().length() > 0) {
        Date dateFromEnd = FormatData
                .parseDateShort3StringToDate(shipDateFromEnd.trim());
        shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
        query.append(" AND (temp_document.ShipDateFrom <= concat(date('"
                + shipDateFromEnd
                + "'), ' 23:59:59')  OR temp_document.ShipDateFrom IS NULL)");
    }

    // thoi gian roi FROM
    if (Validator.isNotNull(shipDateToStart)
            && shipDateToStart.trim().length() > 0) {
        Date dateToStart = FormatData
                .parseDateShort3StringToDate(shipDateToStart.trim());
        shipDateToStart = FormatData.parseDateToTring(dateToStart);
        query.append(" AND (temp_document.ShipDateTo >= concat(date('"
                + shipDateToStart
                + "'), ' 00:00:00')  OR temp_document.ShipDateTo IS NULL)");
    }

    // thoi gian roi To
    if (Validator.isNotNull(shipDateToEnd)
            && shipDateToEnd.trim().length() > 0) {
        Date dateToEnd = FormatData
                .parseDateShort3StringToDate(shipDateToEnd.trim());
        shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
        query.append(" AND (temp_document.ShipDateTo <= concat(date('"
                + shipDateToEnd
                + "'), ' 23:59:59')  OR temp_document.ShipDateTo IS NULL)");
    }

    // ngay gio lam thu tuc
    // ngayLamThuTucFrom
    // ngayLamThuTucTo
    if (Validator.isNotNull(ngayLamThuTucFrom)
            && ngayLamThuTucFrom.trim().length() > 0) {
        Date dateLamThuTucFrom = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
        ngayLamThuTucFrom = FormatData
                .parseDateToTring(dateLamThuTucFrom);
        query.append(" AND (temp_document.CreatedDate >= concat(date('"
                + ngayLamThuTucFrom
                + "'), ' 00:00:00')  OR temp_document.CreatedDate IS NULL)");
    }

    if (Validator.isNotNull(ngayLamThuTucTo)
            && ngayLamThuTucTo.trim().length() > 0) {
        Date dateLamThuTucTo = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
        ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
        query.append(" AND (temp_document.CreatedDate <= concat(date('"
                + ngayLamThuTucTo
                + "'), ' 23:59:59')  OR temp_document.CreatedDate IS NULL)");
    }

    // ma~ ban khai
    if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
        query.append(" AND (temp_document.DocumentName = '"
                + maBanKhai.trim() + "')");
    }

    // TRANG THAI
    if (Validator.isNotNull(documentStatusCode)
            && documentStatusCode.trim().length() > 0) {
        query.append(" AND temp_document.documentStatusCode IN ("
                + documentStatusCode.trim() + ")");
    }

    // KHU VUC CANG
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        query.append(" AND temp_document.PortRegionCode = '"
                + portRegionCode.trim() + "' ");
    }

    if (Validator.isNotNull(nameOfShipownersAgents)
            && nameOfShipownersAgents.trim().length() > 0) {
        query.append(" AND temp_document.NameOfShipownersAgents like '%"
                + nameOfShipownersAgents.trim() + "%' ");
    }
    if (Validator.isNotNull(maritimeCodeNext)
            && maritimeCodeNext.trim().length() > 0) {
        query.append(" AND temp_document.LastPortCode like '%"
                + maritimeCodeNext.trim() + "%' ");
    }
    sql = sql + "( " + "( " + sql1 + queryShipAgency.toString()
            + query.toString() + " ) UNION ( " + sql2
            + queryShipAgency.toString() + query.toString()
            + " ) UNION ( " + sql3 + queryShipAgency.toString()
            + query.toString() + " ) UNION ( " + sql4
            + queryShipAgency.toString() + query.toString() + " )"
            + " ) temp_document ";

    log.debug("=========THU TUC SEARCH --count TempDocument ========");

    QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();





    count = (Integer) queryFactory.getSingleResult(builder);
} catch (Exception e) {
    throw new SystemException(e);
} finally {

}
    return count;
}

public List<TempDocument> findDanhSachHoSoLanhDaoChoKySo(
        String documentTypeCode, String maritimeCode, String portCode,
        String lastPortCode, String shipName, String stateCode,
        String callSign, String imo, String shipPosition,
        String shipDateFromStart, String shipDateFromEnd,
        String shipDateToStart, String shipDateToEnd,
        String documentStatusCode, String arrivalShipAgency,
        String nameArrivalShipAgency, String departureShipAgency,
        String nameDepartureShipAgency, String portRegionCode,
        String ngayLamThuTucFrom, String ngayLamThuTucTo, String maBanKhai,
        String representative, int start, int end) throws SystemException {


    try {

        StringBuilder query = new StringBuilder();
        StringBuilder queryShipAgency = new StringBuilder();

        String sql = "";
        String sql1 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_shifting_order ON issue_shifting_order.DocumentName = temp_document.DocumentName "
                + " AND issue_shifting_order.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.RequestState in (14, 15) and issue_shifting_order.StampStatus = 11 and issue_shifting_order.IsApproval = 0 "
                + " AND issue_shifting_order.Representative = '"
                + representative.trim() + "' ";
        String sql1GroupBy = " GROUP BY issue_shifting_order.DocumentName, issue_shifting_order.DocumentYear, issue_shifting_order.NumberShiftingOrder, issue_shifting_order.VersionNo ";
        String sql2 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_acceptance ON issue_acceptance.DocumentName = temp_document.DocumentName "
                + " AND issue_acceptance.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.DocumentStatusCode in (19, 20) and issue_acceptance.StampStatus = 11 and issue_acceptance.IsApproval = 0 "
                + " AND issue_acceptance.Representative = '"
                + representative.trim() + "' ";
        String sql2GroupBy = " GROUP BY issue_acceptance.DocumentName, issue_acceptance.DocumentYear, issue_acceptance.NumberPortClearance, issue_acceptance.VersionNo ";
        String sql3 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_port_clearance ON issue_port_clearance.DocumentName = temp_document.DocumentName "
                + " AND issue_port_clearance.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.DocumentStatusCode in (19, 20) and issue_port_clearance.StampStatus = 11 and issue_port_clearance.IsApproval = 0 "
                + " AND issue_port_clearance.Representative = '"
                + representative.trim() + "' ";
        String sql3GroupBy = " GROUP BY  issue_port_clearance.DocumentName, issue_port_clearance.DocumentYear, issue_port_clearance.NumberPortClearance, issue_port_clearance.VersionNo ";
        String sql4 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_permission_for_transit  ON issue_permission_for_transit.DocumentName = temp_document.DocumentName "
                + " AND issue_permission_for_transit.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.DocumentStatusCode in (19, 20) and issue_permission_for_transit.StampStatus = 11 and issue_permission_for_transit.IsApproval = 0 "
                + " AND issue_permission_for_transit.Representative = '"
                + representative.trim() + "' ";
        String sql4GroupBy = " GROUP BY  issue_permission_for_transit.DocumentName, issue_permission_for_transit.DocumentYear, issue_permission_for_transit.NumberPermissionForTransit, issue_permission_for_transit.VersionNo ";

        if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                .trim().length() > 0)
                && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                .trim().length() > 0)) {

            queryShipAgency.append("inner join dm_ship_agency dmDep ");
            queryShipAgency
                    .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
            queryShipAgency.append("AND (dmDep.ShipAgencyNameVN like '%"
                    + nameArrivalShipAgency.trim() + "%' "
                    + " OR dmDep.ShipAgencyNameVN like '%"
                    + nameDepartureShipAgency.trim() + "%' ");

        }

        if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                .trim().length() == 0)
                && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                .trim().length() > 0)) {
            queryShipAgency.append("inner join dm_ship_agency dmDep ");
            queryShipAgency
                    .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
            queryShipAgency.append("AND dmDep.ShipAgencyNameVN like '%"
                    + nameDepartureShipAgency.trim() + "%' ");

        }

        if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                .trim().length() > 0)
                && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                .trim().length() == 0)) {
            queryShipAgency.append("inner join dm_ship_agency dmArri ");
            queryShipAgency
                    .append("on temp_document.ArrivalShipAgency = dmArri.ShipAgencyCode ");
            queryShipAgency.append("AND dmArri.ShipAgencyNameVN like '%"
                    + nameArrivalShipAgency.trim() + "%' ");

        }

        query.append(" WHERE 1=1 ");

        // Pb NC,XC,QC
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
            query.append(" AND temp_document.DocumentTypeCode = '"
                    + documentTypeCode.trim() + "' ");
        }

        // CANG VU
        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            query.append(" AND temp_document.MaritimeCode = '"
                    + maritimeCode.trim() + "' ");
        }

        // CANG DEN
        if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
            query.append(" AND temp_document.PortCode = '"
                    + portCode.trim() + "' ");
        }

        // CANG ROI CUOI CUNG
        if (Validator.isNotNull(lastPortCode)
                && lastPortCode.trim().length() > 0) {
            query.append(" AND temp_document.LastPortCode = '"
                    + lastPortCode.trim() + "' ");
        }

        // TEN TAU
        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            query.append(" AND temp_document.ShipName like '%"
                    + shipName.trim() + "%' ");
        }

        // QUOC TICH
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            query.append(" AND temp_document.StateCode = '"
                    + stateCode.trim() + "' ");
        }

        // HO HIEU
        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            query.append(" AND temp_document.CallSign like '%"
                    + callSign.trim() + "%' ");
        }
        if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
            query.append(" AND temp_document.imo like '%" + imo.trim()
                    + "%' ");
        }
        // TAU DEN ROI
        if (Validator.isNotNull(shipPosition)
                && shipPosition.trim().length() > 0
                && FormatData.convertToInt(shipPosition) > 0) {
            query.append(" AND temp_document.ShipPosition ="
                    + FormatData.convertToInt(shipPosition));
        }

        // thoi gian den FROM
        if (Validator.isNotNull(shipDateFromStart)
                && shipDateFromStart.trim().length() > 0) {
            Date dateFromStart = FormatData
                    .parseDateShort3StringToDate(shipDateFromStart.trim());
            shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
            query.append(" AND (temp_document.ShipDateFrom >= concat(date('"
                    + shipDateFromStart
                    + "'), ' 00:00:00')  OR temp_document.ShipDateFrom IS NULL)");
        }

        // thoi gian den To
        if (Validator.isNotNull(shipDateFromEnd)
                && shipDateFromEnd.trim().length() > 0) {
            Date dateFromEnd = FormatData
                    .parseDateShort3StringToDate(shipDateFromEnd.trim());
            shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
            query.append(" AND (temp_document.ShipDateFrom <= concat(date('"
                    + shipDateFromEnd
                    + "'), ' 23:59:59')  OR temp_document.ShipDateFrom IS NULL)");
        }

        // thoi gian roi FROM
        if (Validator.isNotNull(shipDateToStart)
                && shipDateToStart.trim().length() > 0) {
            Date dateToStart = FormatData
                    .parseDateShort3StringToDate(shipDateToStart.trim());
            shipDateToStart = FormatData.parseDateToTring(dateToStart);
            query.append(" AND (temp_document.ShipDateTo >= concat(date('"
                    + shipDateToStart
                    + "'), ' 00:00:00')  OR temp_document.ShipDateTo IS NULL)");
        }

        // thoi gian roi To
        if (Validator.isNotNull(shipDateToEnd)
                && shipDateToEnd.trim().length() > 0) {
            Date dateToEnd = FormatData
                    .parseDateShort3StringToDate(shipDateToEnd.trim());
            shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
            query.append(" AND (temp_document.ShipDateTo <= concat(date('"
                    + shipDateToEnd
                    + "'), ' 23:59:59')  OR temp_document.ShipDateTo IS NULL)");
        }

        // ngay gio lam thu tuc
        // ngayLamThuTucFrom
        // ngayLamThuTucTo
        if (Validator.isNotNull(ngayLamThuTucFrom)
                && ngayLamThuTucFrom.trim().length() > 0) {
            Date dateLamThuTucFrom = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
            ngayLamThuTucFrom = FormatData
                    .parseDateToTring(dateLamThuTucFrom);
            query.append(" AND (temp_document.CreatedDate >= concat(date('"
                    + ngayLamThuTucFrom
                    + "'), ' 00:00:00')  OR temp_document.CreatedDate IS NULL)");
        }

        if (Validator.isNotNull(ngayLamThuTucTo)
                && ngayLamThuTucTo.trim().length() > 0) {
            Date dateLamThuTucTo = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
            ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
            query.append(" AND (temp_document.CreatedDate <= concat(date('"
                    + ngayLamThuTucTo
                    + "'), ' 23:59:59')  OR temp_document.CreatedDate IS NULL)");
        }

        // ma~ ban khai
        if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
            query.append(" AND (temp_document.DocumentName = '"
                    + maBanKhai.trim() + "')");
        }

        // TRANG THAI
        if (Validator.isNotNull(documentStatusCode)
                && documentStatusCode.trim().length() > 0) {
            query.append(" AND temp_document.documentStatusCode IN ("
                    + documentStatusCode.trim() + ")");
        }

        // KHU VUC CANG
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            query.append(" AND temp_document.PortRegionCode = '"
                    + portRegionCode.trim() + "' ");
        }

        StringBuilder queryOrder = new StringBuilder();
        queryOrder.append(" ORDER BY ");
        queryOrder.append("case when DocumentStatusCode = '0' then 0 ");
        queryOrder.append("when DocumentStatusCode = '18' then 1 ");
        queryOrder.append("when DocumentStatusCode = '12' then 2 ");
        queryOrder.append("when DocumentStatusCode = '20' then 3 ");
        queryOrder.append("when DocumentStatusCode = '13' then 4 ");
        queryOrder.append("when DocumentStatusCode = '19' then 5 ");
        queryOrder.append("when DocumentStatusCode = '10' then 6 ");
        queryOrder.append("end asc ");

        // TODO THU TUC sap xep thu tu ho so
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND))) {
            queryOrder
                    .append(", ShipDateFrom ASC, CreatedDate ASC, id ASC");

        } else if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
            queryOrder.append(", ShipDateTo ASC, CreatedDate ASC, id ASC");

        } else {
            queryOrder
                    .append(", ShipDateFrom ASC, ShipDateTo ASC, CreatedDate ASC, id ASC");
        }

        sql = sql + "( " + sql1 + queryShipAgency.toString()
                + query.toString() + sql1GroupBy + " ) UNION ( " + sql2
                + queryShipAgency.toString() + query.toString()
                + sql2GroupBy + " ) UNION ( " + sql3
                + queryShipAgency.toString() + query.toString()
                + sql3GroupBy + " ) UNION ( " + sql4
                + queryShipAgency.toString() + query.toString()
                + sql4GroupBy + " )" + queryOrder.toString();
        if (start >= 0) {
            int count = end - start;
            sql = sql + " limit " + start + "," + count;
        }
        log.debug("=========findDanhSachHoSoLanhDaoChoKySo ===");
        log.debug("=========findDanhSachHoSoLanhDaoChoKySo ===" + sql);
        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();



        
           return (List<TempDocument>)  queryFactory.getResultList(builder);

    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
}

public List<TempDocument> findDanhSachHoSoLanhDaoChoKySo(
        String documentTypeCode, String maritimeCode, String portCode,
        String lastPortCode, String shipName, String stateCode,
        String callSign, String imo, String shipPosition,
        String shipDateFromStart, String shipDateFromEnd,
        String shipDateToStart, String shipDateToEnd,
        String documentStatusCode, String arrivalShipAgency,
        String nameArrivalShipAgency, String departureShipAgency,
        String nameDepartureShipAgency, String portRegionCode,
        String ngayLamThuTucFrom, String ngayLamThuTucTo, String maBanKhai,
        String representative, String nameOfShipownersAgents,
        String maritimeCodeNext, int start, int end) throws SystemException {


    try {

        StringBuilder query = new StringBuilder();
        StringBuilder queryShipAgency = new StringBuilder();

        String sql = "";
        String sql1 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_shifting_order ON issue_shifting_order.DocumentName = temp_document.DocumentName "
                + " AND issue_shifting_order.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.RequestState in (14, 15) and issue_shifting_order.StampStatus = 11 and issue_shifting_order.IsApproval = 0 "
                + " AND issue_shifting_order.Representative = '"
                + representative.trim() + "' ";
        String sql1GroupBy = " GROUP BY issue_shifting_order.DocumentName, issue_shifting_order.DocumentYear, issue_shifting_order.NumberShiftingOrder, issue_shifting_order.VersionNo ";
        String sql2 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_acceptance ON issue_acceptance.DocumentName = temp_document.DocumentName "
                + " AND issue_acceptance.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.DocumentStatusCode in (19, 20) and issue_acceptance.StampStatus = 11 and issue_acceptance.IsApproval = 0 "
                + " AND issue_acceptance.Representative = '"
                + representative.trim() + "' ";
        String sql2GroupBy = " GROUP BY issue_acceptance.DocumentName, issue_acceptance.DocumentYear, issue_acceptance.NumberPortClearance, issue_acceptance.VersionNo ";
        String sql3 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_port_clearance ON issue_port_clearance.DocumentName = temp_document.DocumentName "
                + " AND issue_port_clearance.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.DocumentStatusCode in (19, 20) and issue_port_clearance.StampStatus = 11 and issue_port_clearance.IsApproval = 0 "
                + " AND issue_port_clearance.Representative = '"
                + representative.trim() + "' ";
        String sql3GroupBy = " GROUP BY  issue_port_clearance.DocumentName, issue_port_clearance.DocumentYear, issue_port_clearance.NumberPortClearance, issue_port_clearance.VersionNo ";
        String sql4 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_permission_for_transit  ON issue_permission_for_transit.DocumentName = temp_document.DocumentName "
                + " AND issue_permission_for_transit.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.DocumentStatusCode in (19, 20) and issue_permission_for_transit.StampStatus = 11 and issue_permission_for_transit.IsApproval = 0 "
                + " AND issue_permission_for_transit.Representative = '"
                + representative.trim() + "' ";
        String sql4GroupBy = " GROUP BY  issue_permission_for_transit.DocumentName, issue_permission_for_transit.DocumentYear, issue_permission_for_transit.NumberPermissionForTransit, issue_permission_for_transit.VersionNo ";

        if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                .trim().length() > 0)
                && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                .trim().length() > 0)) {

            queryShipAgency.append("inner join dm_ship_agency dmDep ");
            queryShipAgency
                    .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
            queryShipAgency.append("AND (dmDep.ShipAgencyNameVN like '%"
                    + nameArrivalShipAgency.trim() + "%' "
                    + " OR dmDep.ShipAgencyNameVN like '%"
                    + nameDepartureShipAgency.trim() + "%' ");

        }

        if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                .trim().length() == 0)
                && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                .trim().length() > 0)) {
            queryShipAgency.append("inner join dm_ship_agency dmDep ");
            queryShipAgency
                    .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
            queryShipAgency.append("AND dmDep.ShipAgencyNameVN like '%"
                    + nameDepartureShipAgency.trim() + "%' ");

        }

        if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                .trim().length() > 0)
                && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                .trim().length() == 0)) {
            queryShipAgency.append("inner join dm_ship_agency dmArri ");
            queryShipAgency
                    .append("on temp_document.ArrivalShipAgency = dmArri.ShipAgencyCode ");
            queryShipAgency.append("AND dmArri.ShipAgencyNameVN like '%"
                    + nameArrivalShipAgency.trim() + "%' ");

        }

        query.append(" WHERE 1=1 ");

        // Pb NC,XC,QC
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
            query.append(" AND temp_document.DocumentTypeCode = '"
                    + documentTypeCode.trim() + "' ");
        }

        // CANG VU
        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            query.append(" AND temp_document.MaritimeCode = '"
                    + maritimeCode.trim() + "' ");
        }

        // CANG DEN
        if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
            query.append(" AND temp_document.PortCode = '"
                    + portCode.trim() + "' ");
        }

        // CANG ROI CUOI CUNG
        if (Validator.isNotNull(lastPortCode)
                && lastPortCode.trim().length() > 0) {
            query.append(" AND temp_document.LastPortCode = '"
                    + lastPortCode.trim() + "' ");
        }

        // TEN TAU
        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            query.append(" AND temp_document.ShipName like '%"
                    + shipName.trim() + "%' ");
        }

        // QUOC TICH
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            query.append(" AND temp_document.StateCode = '"
                    + stateCode.trim() + "' ");
        }

        // HO HIEU
        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            query.append(" AND temp_document.CallSign like '%"
                    + callSign.trim() + "%' ");
        }
        if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
            query.append(" AND temp_document.imo like '%" + imo.trim()
                    + "%' ");
        }
        // TAU DEN ROI
        if (Validator.isNotNull(shipPosition)
                && shipPosition.trim().length() > 0
                && FormatData.convertToInt(shipPosition) > 0) {
            query.append(" AND temp_document.ShipPosition ="
                    + FormatData.convertToInt(shipPosition));
        }
        if (Validator.isNotNull(nameOfShipownersAgents)
                && nameOfShipownersAgents.trim().length() > 0) {
            query.append(" AND temp_document.NameOfShipownersAgents like '%"
                    + nameOfShipownersAgents.trim() + "%' ");
        }
        if (Validator.isNotNull(maritimeCodeNext)
                && maritimeCodeNext.trim().length() > 0) {
            query.append(" AND temp_document.LastPortCode like '%"
                    + maritimeCodeNext.trim() + "%' ");
        }
        // thoi gian den FROM
        if (Validator.isNotNull(shipDateFromStart)
                && shipDateFromStart.trim().length() > 0) {
            Date dateFromStart = FormatData
                    .parseDateShort3StringToDate(shipDateFromStart.trim());
            shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
            query.append(" AND (temp_document.ShipDateFrom >= concat(date('"
                    + shipDateFromStart
                    + "'), ' 00:00:00')  OR temp_document.ShipDateFrom IS NULL)");
        }

        // thoi gian den To
        if (Validator.isNotNull(shipDateFromEnd)
                && shipDateFromEnd.trim().length() > 0) {
            Date dateFromEnd = FormatData
                    .parseDateShort3StringToDate(shipDateFromEnd.trim());
            shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
            query.append(" AND (temp_document.ShipDateFrom <= concat(date('"
                    + shipDateFromEnd
                    + "'), ' 23:59:59')  OR temp_document.ShipDateFrom IS NULL)");
        }

        // thoi gian roi FROM
        if (Validator.isNotNull(shipDateToStart)
                && shipDateToStart.trim().length() > 0) {
            Date dateToStart = FormatData
                    .parseDateShort3StringToDate(shipDateToStart.trim());
            shipDateToStart = FormatData.parseDateToTring(dateToStart);
            query.append(" AND (temp_document.ShipDateTo >= concat(date('"
                    + shipDateToStart
                    + "'), ' 00:00:00')  OR temp_document.ShipDateTo IS NULL)");
        }

        // thoi gian roi To
        if (Validator.isNotNull(shipDateToEnd)
                && shipDateToEnd.trim().length() > 0) {
            Date dateToEnd = FormatData
                    .parseDateShort3StringToDate(shipDateToEnd.trim());
            shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
            query.append(" AND (temp_document.ShipDateTo <= concat(date('"
                    + shipDateToEnd
                    + "'), ' 23:59:59')  OR temp_document.ShipDateTo IS NULL)");
        }

        // ngay gio lam thu tuc
        // ngayLamThuTucFrom
        // ngayLamThuTucTo
        if (Validator.isNotNull(ngayLamThuTucFrom)
                && ngayLamThuTucFrom.trim().length() > 0) {
            Date dateLamThuTucFrom = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
            ngayLamThuTucFrom = FormatData
                    .parseDateToTring(dateLamThuTucFrom);
            query.append(" AND (temp_document.CreatedDate >= concat(date('"
                    + ngayLamThuTucFrom
                    + "'), ' 00:00:00')  OR temp_document.CreatedDate IS NULL)");
        }

        if (Validator.isNotNull(ngayLamThuTucTo)
                && ngayLamThuTucTo.trim().length() > 0) {
            Date dateLamThuTucTo = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
            ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
            query.append(" AND (temp_document.CreatedDate <= concat(date('"
                    + ngayLamThuTucTo
                    + "'), ' 23:59:59')  OR temp_document.CreatedDate IS NULL)");
        }

        // ma~ ban khai
        if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
            query.append(" AND (temp_document.DocumentName = '"
                    + maBanKhai.trim() + "')");
        }

        // TRANG THAI
        if (Validator.isNotNull(documentStatusCode)
                && documentStatusCode.trim().length() > 0) {
            query.append(" AND temp_document.documentStatusCode IN ("
                    + documentStatusCode.trim() + ")");
        }

        // KHU VUC CANG
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            query.append(" AND temp_document.PortRegionCode = '"
                    + portRegionCode.trim() + "' ");
        }

        StringBuilder queryOrder = new StringBuilder();
        queryOrder.append(" ORDER BY ");
        queryOrder.append("case when DocumentStatusCode = '0' then 0 ");
        queryOrder.append("when DocumentStatusCode = '18' then 1 ");
        queryOrder.append("when DocumentStatusCode = '12' then 2 ");
        queryOrder.append("when DocumentStatusCode = '20' then 3 ");
        queryOrder.append("when DocumentStatusCode = '13' then 4 ");
        queryOrder.append("when DocumentStatusCode = '19' then 5 ");
        queryOrder.append("when DocumentStatusCode = '10' then 6 ");
        queryOrder.append("end asc ");

        // TODO THU TUC sap xep thu tu ho so
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND))) {
            queryOrder
                    .append(", ShipDateFrom ASC, CreatedDate ASC, id ASC");

        } else if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
            queryOrder.append(", ShipDateTo ASC, CreatedDate ASC, id ASC");

        } else {
            queryOrder
                    .append(", ShipDateFrom ASC, ShipDateTo ASC, CreatedDate ASC, id ASC");
        }

        sql = sql + "( " + sql1 + queryShipAgency.toString()
                + query.toString() + sql1GroupBy + " ) UNION ( " + sql2
                + queryShipAgency.toString() + query.toString()
                + sql2GroupBy + " ) UNION ( " + sql3
                + queryShipAgency.toString() + query.toString()
                + sql3GroupBy + " ) UNION ( " + sql4
                + queryShipAgency.toString() + query.toString()
                + sql4GroupBy + " )" + queryOrder.toString();
        if (start >= 0) {
            int count = end - start;
            sql = sql + " limit " + start + "," + count;
        }
        log.debug("=========findDanhSachHoSoLanhDaoChoKySo ===");
        log.info("=========findDanhSachHoSoLanhDaoChoKySo ===" + sql);
        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();



        
           return (List<TempDocument>)  queryFactory.getResultList(builder);

    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
}

public List<TempDocument> findDanhSachHoSoLanhDaoDaKySo(
        String documentTypeCode, String maritimeCode, String portCode,
        String lastPortCode, String shipName, String stateCode,
        String callSign, String imo, String shipPosition,
        String shipDateFromStart, String shipDateFromEnd,
        String shipDateToStart, String shipDateToEnd,
        String documentStatusCode, String arrivalShipAgency,
        String nameArrivalShipAgency, String departureShipAgency,
        String nameDepartureShipAgency, String portRegionCode,
        String ngayLamThuTucFrom, String ngayLamThuTucTo, String maBanKhai,
        String representative, String nameOfShipownersAgents,
        String maritimeCodeNext, int start, int end) throws SystemException {


    try {

        StringBuilder query = new StringBuilder();
        StringBuilder queryShipAgency = new StringBuilder();

        String sql = "";
        String sql1 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_shifting_order ON issue_shifting_order.DocumentName = temp_document.DocumentName "
                + " AND issue_shifting_order.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.RequestState in (14, 15) and issue_shifting_order.StampStatus IN (1,2) and issue_shifting_order.IsApproval IN (0,1) "
                + " AND issue_shifting_order.Representative = '"
                + representative.trim() + "' ";
        String sql1GroupBy = " GROUP BY issue_shifting_order.DocumentName, issue_shifting_order.DocumentYear, issue_shifting_order.NumberShiftingOrder, issue_shifting_order.VersionNo ";
        String sql2 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_acceptance ON issue_acceptance.DocumentName = temp_document.DocumentName "
                + " AND issue_acceptance.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.DocumentStatusCode in (19, 20) and issue_acceptance.StampStatus IN (1,2) and issue_acceptance.IsApproval IN (0,1) "
                + " AND issue_acceptance.Representative = '"
                + representative.trim() + "' ";
        String sql2GroupBy = " GROUP BY issue_acceptance.DocumentName, issue_acceptance.DocumentYear, issue_acceptance.NumberPortClearance, issue_acceptance.VersionNo ";
        String sql3 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_port_clearance ON issue_port_clearance.DocumentName = temp_document.DocumentName "
                + " AND issue_port_clearance.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.DocumentStatusCode in (19, 20) and issue_port_clearance.StampStatus IN (1,2) and issue_port_clearance.IsApproval IN (0,1) "
                + " AND issue_port_clearance.Representative = '"
                + representative.trim() + "' ";
        String sql3GroupBy = " GROUP BY  issue_port_clearance.DocumentName, issue_port_clearance.DocumentYear, issue_port_clearance.NumberPortClearance, issue_port_clearance.VersionNo ";
        String sql4 = "Select temp_document.* from temp_document "
                + " INNER JOIN issue_permission_for_transit  ON issue_permission_for_transit.DocumentName = temp_document.DocumentName "
                + " AND issue_permission_for_transit.DocumentYear = temp_document.DocumentYear "
                + " AND temp_document.DocumentStatusCode in (19, 20) and issue_permission_for_transit.StampStatus IN (1,2) and issue_permission_for_transit.IsApproval IN (0,1) "
                + " AND issue_permission_for_transit.Representative = '"
                + representative.trim() + "' ";
        String sql4GroupBy = " GROUP BY  issue_permission_for_transit.DocumentName, issue_permission_for_transit.DocumentYear, issue_permission_for_transit.NumberPermissionForTransit, issue_permission_for_transit.VersionNo ";

        if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                .trim().length() > 0)
                && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                .trim().length() > 0)) {

            queryShipAgency.append("inner join dm_ship_agency dmDep ");
            queryShipAgency
                    .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
            queryShipAgency.append("AND (dmDep.ShipAgencyNameVN like '%"
                    + nameArrivalShipAgency.trim() + "%' "
                    + " OR dmDep.ShipAgencyNameVN like '%"
                    + nameDepartureShipAgency.trim() + "%' ");

        }

        if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                .trim().length() == 0)
                && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                .trim().length() > 0)) {
            queryShipAgency.append("inner join dm_ship_agency dmDep ");
            queryShipAgency
                    .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
            queryShipAgency.append("AND dmDep.ShipAgencyNameVN like '%"
                    + nameDepartureShipAgency.trim() + "%' ");

        }

        if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
                .trim().length() > 0)
                && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
                .trim().length() == 0)) {
            queryShipAgency.append("inner join dm_ship_agency dmArri ");
            queryShipAgency
                    .append("on temp_document.ArrivalShipAgency = dmArri.ShipAgencyCode ");
            queryShipAgency.append("AND dmArri.ShipAgencyNameVN like '%"
                    + nameArrivalShipAgency.trim() + "%' ");

        }

        query.append(" WHERE 1=1 ");

        // Pb NC,XC,QC
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
            query.append(" AND temp_document.DocumentTypeCode = '"
                    + documentTypeCode.trim() + "' ");
        }

        // CANG VU
        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            query.append(" AND temp_document.MaritimeCode = '"
                    + maritimeCode.trim() + "' ");
        }

        // CANG DEN
        if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
            query.append(" AND temp_document.PortCode = '"
                    + portCode.trim() + "' ");
        }

        // CANG ROI CUOI CUNG
        if (Validator.isNotNull(lastPortCode)
                && lastPortCode.trim().length() > 0) {
            query.append(" AND temp_document.LastPortCode = '"
                    + lastPortCode.trim() + "' ");
        }

        // TEN TAU
        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            query.append(" AND temp_document.ShipName like '%"
                    + shipName.trim() + "%' ");
        }

        // QUOC TICH
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            query.append(" AND temp_document.StateCode = '"
                    + stateCode.trim() + "' ");
        }

        // HO HIEU
        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            query.append(" AND temp_document.CallSign like '%"
                    + callSign.trim() + "%' ");
        }
        if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
            query.append(" AND temp_document.imo like '%" + imo.trim()
                    + "%' ");
        }
        // TAU DEN ROI
        if (Validator.isNotNull(shipPosition)
                && shipPosition.trim().length() > 0
                && FormatData.convertToInt(shipPosition) > 0) {
            query.append(" AND temp_document.ShipPosition ="
                    + FormatData.convertToInt(shipPosition));
        }
        if (Validator.isNotNull(nameOfShipownersAgents)
                && nameOfShipownersAgents.trim().length() > 0) {
            query.append(" AND temp_document.NameOfShipownersAgents like '%"
                    + nameOfShipownersAgents.trim() + "%' ");
        }
        if (Validator.isNotNull(maritimeCodeNext)
                && maritimeCodeNext.trim().length() > 0) {
            query.append(" AND temp_document.LastPortCode like '%"
                    + maritimeCodeNext.trim() + "%' ");
        }
        // thoi gian den FROM
        if (Validator.isNotNull(shipDateFromStart)
                && shipDateFromStart.trim().length() > 0) {
            Date dateFromStart = FormatData
                    .parseDateShort3StringToDate(shipDateFromStart.trim());
            shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
            query.append(" AND (temp_document.ShipDateFrom >= concat(date('"
                    + shipDateFromStart
                    + "'), ' 00:00:00')  OR temp_document.ShipDateFrom IS NULL)");
        }

        // thoi gian den To
        if (Validator.isNotNull(shipDateFromEnd)
                && shipDateFromEnd.trim().length() > 0) {
            Date dateFromEnd = FormatData
                    .parseDateShort3StringToDate(shipDateFromEnd.trim());
            shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
            query.append(" AND (temp_document.ShipDateFrom <= concat(date('"
                    + shipDateFromEnd
                    + "'), ' 23:59:59')  OR temp_document.ShipDateFrom IS NULL)");
        }

        // thoi gian roi FROM
        if (Validator.isNotNull(shipDateToStart)
                && shipDateToStart.trim().length() > 0) {
            Date dateToStart = FormatData
                    .parseDateShort3StringToDate(shipDateToStart.trim());
            shipDateToStart = FormatData.parseDateToTring(dateToStart);
            query.append(" AND (temp_document.ShipDateTo >= concat(date('"
                    + shipDateToStart
                    + "'), ' 00:00:00')  OR temp_document.ShipDateTo IS NULL)");
        }

        // thoi gian roi To
        if (Validator.isNotNull(shipDateToEnd)
                && shipDateToEnd.trim().length() > 0) {
            Date dateToEnd = FormatData
                    .parseDateShort3StringToDate(shipDateToEnd.trim());
            shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
            query.append(" AND (temp_document.ShipDateTo <= concat(date('"
                    + shipDateToEnd
                    + "'), ' 23:59:59')  OR temp_document.ShipDateTo IS NULL)");
        }

        // ngay gio lam thu tuc
        // ngayLamThuTucFrom
        // ngayLamThuTucTo
        if (Validator.isNotNull(ngayLamThuTucFrom)
                && ngayLamThuTucFrom.trim().length() > 0) {
            Date dateLamThuTucFrom = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
            ngayLamThuTucFrom = FormatData
                    .parseDateToTring(dateLamThuTucFrom);
            query.append(" AND (temp_document.CreatedDate >= concat(date('"
                    + ngayLamThuTucFrom
                    + "'), ' 00:00:00')  OR temp_document.CreatedDate IS NULL)");
        }

        if (Validator.isNotNull(ngayLamThuTucTo)
                && ngayLamThuTucTo.trim().length() > 0) {
            Date dateLamThuTucTo = FormatData
                    .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
            ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
            query.append(" AND (temp_document.CreatedDate <= concat(date('"
                    + ngayLamThuTucTo
                    + "'), ' 23:59:59')  OR temp_document.CreatedDate IS NULL)");
        }

        // ma~ ban khai
        if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
            query.append(" AND (temp_document.DocumentName = '"
                    + maBanKhai.trim() + "')");
        }

        // TRANG THAI
        if (Validator.isNotNull(documentStatusCode)
                && documentStatusCode.trim().length() > 0) {
            query.append(" AND temp_document.documentStatusCode IN ("
                    + documentStatusCode.trim() + ")");
        }

        // KHU VUC CANG
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            query.append(" AND temp_document.PortRegionCode = '"
                    + portRegionCode.trim() + "' ");
        }

        StringBuilder queryOrder = new StringBuilder();
        queryOrder.append(" ORDER BY ");
        queryOrder.append("case when DocumentStatusCode = '0' then 0 ");
        queryOrder.append("when DocumentStatusCode = '18' then 1 ");
        queryOrder.append("when DocumentStatusCode = '12' then 2 ");
        queryOrder.append("when DocumentStatusCode = '20' then 3 ");
        queryOrder.append("when DocumentStatusCode = '13' then 4 ");
        queryOrder.append("when DocumentStatusCode = '19' then 5 ");
        queryOrder.append("when DocumentStatusCode = '10' then 6 ");
        queryOrder.append("end asc ");

        // TODO THU TUC sap xep thu tu ho so
        if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND))) {
            queryOrder
                    .append(", ShipDateFrom ASC, CreatedDate ASC, id ASC");

        } else if (Validator.isNotNull(documentTypeCode)
                && documentTypeCode.trim().length() > 0
                && (documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
                || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG) || documentTypeCode
                .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
            queryOrder.append(", ShipDateTo ASC, CreatedDate ASC, id ASC");

        } else {
            queryOrder
                    .append(", ShipDateFrom ASC, ShipDateTo ASC, CreatedDate ASC, id ASC");
        }

        sql = sql + "( " + sql1 + queryShipAgency.toString()
                + query.toString() + sql1GroupBy + " ) UNION ( " + sql2
                + queryShipAgency.toString() + query.toString()
                + sql2GroupBy + " ) UNION ( " + sql3
                + queryShipAgency.toString() + query.toString()
                + sql3GroupBy + " ) UNION ( " + sql4
                + queryShipAgency.toString() + query.toString()
                + sql4GroupBy + " )" + queryOrder.toString();
        if (start >= 0) {
            int count = end - start;
            sql = sql + " limit " + start + "," + count;
        }
        log.debug("=========findDanhSachHoSoLanhDaoDaKySo ===");
        log.info("=========findDanhSachHoSoLanhDaoDaKySo ===" + sql);
        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();



        
           return (List<TempDocument>)  queryFactory.getResultList(builder);

    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
}

public int countDanhSachHoSoLanhDaoChoKySo(String documentTypeCode,
                                           String maritimeCode, String portCode, String lastPortCode,
                                           String shipName, String stateCode, String callSign, String imo,
                                           String shipPosition, String shipDateFromStart,
                                           String shipDateFromEnd, String shipDateToStart,
                                           String shipDateToEnd, String documentStatusCode,
                                           String arrivalShipAgency, String nameArrivalShipAgency,
                                           String departureShipAgency, String nameDepartureShipAgency,
                                           String portRegionCode, String ngayLamThuTucFrom,
                                           String ngayLamThuTucTo, String maBanKhai, String representative) throws SystemException { int count = 0; try {


    StringBuilder query = new StringBuilder();
    StringBuilder queryShipAgency = new StringBuilder();

    String sql = "SELECT count(*) as total FROM ";

    String sql1 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_shifting_order ON issue_shifting_order.DocumentName = temp_document.DocumentName "
            + " AND issue_shifting_order.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.RequestState in (14, 15) and issue_shifting_order.StampStatus = 11 and issue_shifting_order.IsApproval = 0 "
            + " AND issue_shifting_order.Representative = '"
            + representative.trim() + "' ";
    String sql1GroupBy = " GROUP BY issue_shifting_order.DocumentName, issue_shifting_order.DocumentYear, issue_shifting_order.NumberShiftingOrder, issue_shifting_order.VersionNo ";
    String sql2 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_acceptance ON issue_acceptance.DocumentName = temp_document.DocumentName "
            + " AND issue_acceptance.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_acceptance.StampStatus = 11 and issue_acceptance.IsApproval = 0 "
            + " AND issue_acceptance.Representative = '"
            + representative.trim() + "' ";
    String sql2GroupBy = " GROUP BY issue_acceptance.DocumentName, issue_acceptance.DocumentYear, issue_acceptance.NumberPortClearance, issue_acceptance.VersionNo ";
    String sql3 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_port_clearance ON issue_port_clearance.DocumentName = temp_document.DocumentName "
            + " AND issue_port_clearance.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_port_clearance.StampStatus = 11 and issue_port_clearance.IsApproval = 0 "
            + " AND issue_port_clearance.Representative = '"
            + representative.trim() + "' ";
    String sql3GroupBy = " GROUP BY  issue_port_clearance.DocumentName, issue_port_clearance.DocumentYear, issue_port_clearance.NumberPortClearance, issue_port_clearance.VersionNo ";
    String sql4 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_permission_for_transit  ON issue_permission_for_transit.DocumentName = temp_document.DocumentName "
            + " AND issue_permission_for_transit.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_permission_for_transit.StampStatus = 11 and issue_permission_for_transit.IsApproval = 0 "
            + " AND issue_permission_for_transit.Representative = '"
            + representative.trim() + "' ";
    String sql4GroupBy = " GROUP BY  issue_permission_for_transit.DocumentName, issue_permission_for_transit.DocumentYear, issue_permission_for_transit.NumberPermissionForTransit, issue_permission_for_transit.VersionNo ";

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() > 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() > 0)) {

        queryShipAgency.append("inner join dm_ship_agency dmDep ");
        queryShipAgency
                .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
        queryShipAgency.append("AND (dmDep.ShipAgencyNameVN like '%"
                + nameArrivalShipAgency.trim() + "%' "
                + " OR dmDep.ShipAgencyNameVN like '%"
                + nameDepartureShipAgency.trim() + "%' ");

    }

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() == 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() > 0)) {
        queryShipAgency.append("inner join dm_ship_agency dmDep ");
        queryShipAgency
                .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
        queryShipAgency.append("AND dmDep.ShipAgencyNameVN like '%"
                + nameDepartureShipAgency.trim() + "%' ");

    }

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() > 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() == 0)) {
        queryShipAgency.append("inner join dm_ship_agency dmArri ");
        queryShipAgency
                .append("on temp_document.ArrivalShipAgency = dmArri.ShipAgencyCode ");
        queryShipAgency.append("AND dmArri.ShipAgencyNameVN like '%"
                + nameArrivalShipAgency.trim() + "%' ");

    }

    query.append(" WHERE 1=1 ");

    // Pb NC,XC,QC
    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0
            && (documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
        query.append(" AND temp_document.DocumentTypeCode = '"
                + documentTypeCode.trim() + "' ");
    }

    // CANG VU
    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        query.append(" AND temp_document.MaritimeCode = '"
                + maritimeCode.trim() + "' ");
    }

    // CANG DEN
    if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
        query.append(" AND temp_document.PortCode = '"
                + portCode.trim() + "' ");
    }

    // CANG ROI CUOI CUNG
    if (Validator.isNotNull(lastPortCode)
            && lastPortCode.trim().length() > 0) {
        query.append(" AND temp_document.LastPortCode = '"
                + lastPortCode.trim() + "' ");
    }

    // TEN TAU
    if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
        query.append(" AND temp_document.ShipName like '%"
                + shipName.trim() + "%' ");
    }

    // QUOC TICH
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        query.append(" AND temp_document.StateCode = '"
                + stateCode.trim() + "' ");
    }

    // HO HIEU
    if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
        query.append(" AND temp_document.CallSign like '%"
                + callSign.trim() + "%' ");
    }
    if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
        query.append(" AND temp_document.imo like '%" + imo.trim()
                + "%' ");
    }
    // TAU DEN ROI
    if (Validator.isNotNull(shipPosition)
            && shipPosition.trim().length() > 0
            && FormatData.convertToInt(shipPosition) > 0) {
        query.append(" AND temp_document.ShipPosition ="
                + FormatData.convertToInt(shipPosition));
    }

    // thoi gian den FROM
    if (Validator.isNotNull(shipDateFromStart)
            && shipDateFromStart.trim().length() > 0) {
        Date dateFromStart = FormatData
                .parseDateShort3StringToDate(shipDateFromStart.trim());
        shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
        query.append(" AND (temp_document.ShipDateFrom >= concat(date('"
                + shipDateFromStart
                + "'), ' 00:00:00')  OR temp_document.ShipDateFrom IS NULL)");
    }

    // thoi gian den To
    if (Validator.isNotNull(shipDateFromEnd)
            && shipDateFromEnd.trim().length() > 0) {
        Date dateFromEnd = FormatData
                .parseDateShort3StringToDate(shipDateFromEnd.trim());
        shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
        query.append(" AND (temp_document.ShipDateFrom <= concat(date('"
                + shipDateFromEnd
                + "'), ' 23:59:59')  OR temp_document.ShipDateFrom IS NULL)");
    }

    // thoi gian roi FROM
    if (Validator.isNotNull(shipDateToStart)
            && shipDateToStart.trim().length() > 0) {
        Date dateToStart = FormatData
                .parseDateShort3StringToDate(shipDateToStart.trim());
        shipDateToStart = FormatData.parseDateToTring(dateToStart);
        query.append(" AND (temp_document.ShipDateTo >= concat(date('"
                + shipDateToStart
                + "'), ' 00:00:00')  OR temp_document.ShipDateTo IS NULL)");
    }

    // thoi gian roi To
    if (Validator.isNotNull(shipDateToEnd)
            && shipDateToEnd.trim().length() > 0) {
        Date dateToEnd = FormatData
                .parseDateShort3StringToDate(shipDateToEnd.trim());
        shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
        query.append(" AND (temp_document.ShipDateTo <= concat(date('"
                + shipDateToEnd
                + "'), ' 23:59:59')  OR temp_document.ShipDateTo IS NULL)");
    }

    // ngay gio lam thu tuc
    // ngayLamThuTucFrom
    // ngayLamThuTucTo
    if (Validator.isNotNull(ngayLamThuTucFrom)
            && ngayLamThuTucFrom.trim().length() > 0) {
        Date dateLamThuTucFrom = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
        ngayLamThuTucFrom = FormatData
                .parseDateToTring(dateLamThuTucFrom);
        query.append(" AND (temp_document.CreatedDate >= concat(date('"
                + ngayLamThuTucFrom
                + "'), ' 00:00:00')  OR temp_document.CreatedDate IS NULL)");
    }

    if (Validator.isNotNull(ngayLamThuTucTo)
            && ngayLamThuTucTo.trim().length() > 0) {
        Date dateLamThuTucTo = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
        ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
        query.append(" AND (temp_document.CreatedDate <= concat(date('"
                + ngayLamThuTucTo
                + "'), ' 23:59:59')  OR temp_document.CreatedDate IS NULL)");
    }

    // ma~ ban khai
    if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
        query.append(" AND (temp_document.DocumentName = '"
                + maBanKhai.trim() + "')");
    }

    // TRANG THAI
    if (Validator.isNotNull(documentStatusCode)
            && documentStatusCode.trim().length() > 0) {
        query.append(" AND temp_document.documentStatusCode IN ("
                + documentStatusCode.trim() + ")");
    }

    // KHU VUC CANG
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        query.append(" AND temp_document.PortRegionCode = '"
                + portRegionCode.trim() + "' ");
    }

    sql = sql + "( " + "( " + sql1 + queryShipAgency.toString()
            + query.toString() + sql1GroupBy + " ) UNION ( " + sql2
            + queryShipAgency.toString() + query.toString()
            + sql2GroupBy + " ) UNION ( " + sql3
            + queryShipAgency.toString() + query.toString()
            + sql3GroupBy + " ) UNION ( " + sql4
            + queryShipAgency.toString() + query.toString()
            + sql4GroupBy + " )" + " ) temp_document ";

    log.debug("=========THU TUC SEARCH --countDanhSachHoSoLanhDaoChoKySo ========");

    QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();





    count = (Integer) queryFactory.getSingleResult(builder);
} catch (Exception e) {
    throw new SystemException(e);
} finally {

}
    return count;
}

public int countDanhSachHoSoLanhDaoDaKySo(String documentTypeCode,
                                          String maritimeCode, String portCode, String lastPortCode,
                                          String shipName, String stateCode, String callSign, String imo,
                                          String shipPosition, String shipDateFromStart,
                                          String shipDateFromEnd, String shipDateToStart,
                                          String shipDateToEnd, String documentStatusCode,
                                          String arrivalShipAgency, String nameArrivalShipAgency,
                                          String departureShipAgency, String nameDepartureShipAgency,
                                          String portRegionCode, String ngayLamThuTucFrom,
                                          String ngayLamThuTucTo, String maBanKhai, String representative,
                                          String nameOfShipownersAgents, String maritimeCodeNext) throws SystemException { int count = 0; try {


    StringBuilder query = new StringBuilder();
    StringBuilder queryShipAgency = new StringBuilder();

    String sql = "SELECT count(*) as total FROM ";

    String sql1 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_shifting_order ON issue_shifting_order.DocumentName = temp_document.DocumentName "
            + " AND issue_shifting_order.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.RequestState in (14, 15) and issue_shifting_order.StampStatus IN (1,2) and issue_shifting_order.IsApproval IN (0,1) "
            + " AND issue_shifting_order.Representative = '"
            + representative.trim() + "' ";
    String sql1GroupBy = " GROUP BY issue_shifting_order.DocumentName, issue_shifting_order.DocumentYear, issue_shifting_order.NumberShiftingOrder, issue_shifting_order.VersionNo ";
    String sql2 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_acceptance ON issue_acceptance.DocumentName = temp_document.DocumentName "
            + " AND issue_acceptance.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_acceptance.StampStatus IN (1,2) and issue_acceptance.IsApproval IN (0,1) "
            + " AND issue_acceptance.Representative = '"
            + representative.trim() + "' ";
    String sql2GroupBy = " GROUP BY issue_acceptance.DocumentName, issue_acceptance.DocumentYear, issue_acceptance.NumberPortClearance, issue_acceptance.VersionNo ";
    String sql3 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_port_clearance ON issue_port_clearance.DocumentName = temp_document.DocumentName "
            + " AND issue_port_clearance.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_port_clearance.StampStatus IN (1,2) and issue_port_clearance.IsApproval IN (0,1) "
            + " AND issue_port_clearance.Representative = '"
            + representative.trim() + "' ";
    String sql3GroupBy = " GROUP BY  issue_port_clearance.DocumentName, issue_port_clearance.DocumentYear, issue_port_clearance.NumberPortClearance, issue_port_clearance.VersionNo ";
    String sql4 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_permission_for_transit  ON issue_permission_for_transit.DocumentName = temp_document.DocumentName "
            + " AND issue_permission_for_transit.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_permission_for_transit.StampStatus IN (1,2) and issue_permission_for_transit.IsApproval IN (0,1) "
            + " AND issue_permission_for_transit.Representative = '"
            + representative.trim() + "' ";
    String sql4GroupBy = " GROUP BY  issue_permission_for_transit.DocumentName, issue_permission_for_transit.DocumentYear, issue_permission_for_transit.NumberPermissionForTransit, issue_permission_for_transit.VersionNo ";

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() > 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() > 0)) {

        queryShipAgency.append("inner join dm_ship_agency dmDep ");
        queryShipAgency
                .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
        queryShipAgency.append("AND (dmDep.ShipAgencyNameVN like '%"
                + nameArrivalShipAgency.trim() + "%' "
                + " OR dmDep.ShipAgencyNameVN like '%"
                + nameDepartureShipAgency.trim() + "%' ");

    }

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() == 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() > 0)) {
        queryShipAgency.append("inner join dm_ship_agency dmDep ");
        queryShipAgency
                .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
        queryShipAgency.append("AND dmDep.ShipAgencyNameVN like '%"
                + nameDepartureShipAgency.trim() + "%' ");

    }

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() > 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() == 0)) {
        queryShipAgency.append("inner join dm_ship_agency dmArri ");
        queryShipAgency
                .append("on temp_document.ArrivalShipAgency = dmArri.ShipAgencyCode ");
        queryShipAgency.append("AND dmArri.ShipAgencyNameVN like '%"
                + nameArrivalShipAgency.trim() + "%' ");

    }

    query.append(" WHERE 1=1 ");

    // Pb NC,XC,QC
    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0
            && (documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
        query.append(" AND temp_document.DocumentTypeCode = '"
                + documentTypeCode.trim() + "' ");
    }

    // CANG VU
    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        query.append(" AND temp_document.MaritimeCode = '"
                + maritimeCode.trim() + "' ");
    }

    // CANG DEN
    if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
        query.append(" AND temp_document.PortCode = '"
                + portCode.trim() + "' ");
    }

    // CANG ROI CUOI CUNG
    if (Validator.isNotNull(lastPortCode)
            && lastPortCode.trim().length() > 0) {
        query.append(" AND temp_document.LastPortCode = '"
                + lastPortCode.trim() + "' ");
    }

    // TEN TAU
    if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
        query.append(" AND temp_document.ShipName like '%"
                + shipName.trim() + "%' ");
    }

    // QUOC TICH
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        query.append(" AND temp_document.StateCode = '"
                + stateCode.trim() + "' ");
    }

    // HO HIEU
    if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
        query.append(" AND temp_document.CallSign like '%"
                + callSign.trim() + "%' ");
    }
    if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
        query.append(" AND temp_document.imo like '%" + imo.trim()
                + "%' ");
    }
    // TAU DEN ROI
    if (Validator.isNotNull(shipPosition)
            && shipPosition.trim().length() > 0
            && FormatData.convertToInt(shipPosition) > 0) {
        query.append(" AND temp_document.ShipPosition ="
                + FormatData.convertToInt(shipPosition));
    }

    // thoi gian den FROM
    if (Validator.isNotNull(shipDateFromStart)
            && shipDateFromStart.trim().length() > 0) {
        Date dateFromStart = FormatData
                .parseDateShort3StringToDate(shipDateFromStart.trim());
        shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
        query.append(" AND (temp_document.ShipDateFrom >= concat(date('"
                + shipDateFromStart
                + "'), ' 00:00:00')  OR temp_document.ShipDateFrom IS NULL)");
    }

    // thoi gian den To
    if (Validator.isNotNull(shipDateFromEnd)
            && shipDateFromEnd.trim().length() > 0) {
        Date dateFromEnd = FormatData
                .parseDateShort3StringToDate(shipDateFromEnd.trim());
        shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
        query.append(" AND (temp_document.ShipDateFrom <= concat(date('"
                + shipDateFromEnd
                + "'), ' 23:59:59')  OR temp_document.ShipDateFrom IS NULL)");
    }

    // thoi gian roi FROM
    if (Validator.isNotNull(shipDateToStart)
            && shipDateToStart.trim().length() > 0) {
        Date dateToStart = FormatData
                .parseDateShort3StringToDate(shipDateToStart.trim());
        shipDateToStart = FormatData.parseDateToTring(dateToStart);
        query.append(" AND (temp_document.ShipDateTo >= concat(date('"
                + shipDateToStart
                + "'), ' 00:00:00')  OR temp_document.ShipDateTo IS NULL)");
    }

    // thoi gian roi To
    if (Validator.isNotNull(shipDateToEnd)
            && shipDateToEnd.trim().length() > 0) {
        Date dateToEnd = FormatData
                .parseDateShort3StringToDate(shipDateToEnd.trim());
        shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
        query.append(" AND (temp_document.ShipDateTo <= concat(date('"
                + shipDateToEnd
                + "'), ' 23:59:59')  OR temp_document.ShipDateTo IS NULL)");
    }

    // ngay gio lam thu tuc
    // ngayLamThuTucFrom
    // ngayLamThuTucTo
    if (Validator.isNotNull(ngayLamThuTucFrom)
            && ngayLamThuTucFrom.trim().length() > 0) {
        Date dateLamThuTucFrom = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
        ngayLamThuTucFrom = FormatData
                .parseDateToTring(dateLamThuTucFrom);
        query.append(" AND (temp_document.CreatedDate >= concat(date('"
                + ngayLamThuTucFrom
                + "'), ' 00:00:00')  OR temp_document.CreatedDate IS NULL)");
    }

    if (Validator.isNotNull(ngayLamThuTucTo)
            && ngayLamThuTucTo.trim().length() > 0) {
        Date dateLamThuTucTo = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
        ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
        query.append(" AND (temp_document.CreatedDate <= concat(date('"
                + ngayLamThuTucTo
                + "'), ' 23:59:59')  OR temp_document.CreatedDate IS NULL)");
    }

    // ma~ ban khai
    if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
        query.append(" AND (temp_document.DocumentName = '"
                + maBanKhai.trim() + "')");
    }

    // TRANG THAI
    if (Validator.isNotNull(documentStatusCode)
            && documentStatusCode.trim().length() > 0) {
        query.append(" AND temp_document.documentStatusCode IN ("
                + documentStatusCode.trim() + ")");
    }

    // KHU VUC CANG
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        query.append(" AND temp_document.PortRegionCode = '"
                + portRegionCode.trim() + "' ");
    }

    if (Validator.isNotNull(nameOfShipownersAgents)
            && nameOfShipownersAgents.trim().length() > 0) {
        query.append(" AND temp_document.NameOfShipownersAgents like '%"
                + nameOfShipownersAgents.trim() + "%' ");
    }
    if (Validator.isNotNull(maritimeCodeNext)
            && maritimeCodeNext.trim().length() > 0) {
        query.append(" AND temp_document.LastPortCode like '%"
                + maritimeCodeNext.trim() + "%' ");
    }
    sql = sql + "( " + "( " + sql1 + queryShipAgency.toString()
            + query.toString() + sql1GroupBy + " ) UNION ( " + sql2
            + queryShipAgency.toString() + query.toString()
            + sql2GroupBy + " ) UNION ( " + sql3
            + queryShipAgency.toString() + query.toString()
            + sql3GroupBy + " ) UNION ( " + sql4
            + queryShipAgency.toString() + query.toString()
            + sql4GroupBy + " )" + " ) temp_document ";

    log.debug("=========THU TUC SEARCH --countDanhSachHoSoLanhDaoChoKySo ========");

    QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();





    count = (Integer) queryFactory.getSingleResult(builder);
} catch (Exception e) {
    throw new SystemException(e);
} finally {

}
    return count;
}

public int countDanhSachHoSoLanhDaoChoKySo(String documentTypeCode,
                                           String maritimeCode, String portCode, String lastPortCode,
                                           String shipName, String stateCode, String callSign, String imo,
                                           String shipPosition, String shipDateFromStart,
                                           String shipDateFromEnd, String shipDateToStart,
                                           String shipDateToEnd, String documentStatusCode,
                                           String arrivalShipAgency, String nameArrivalShipAgency,
                                           String departureShipAgency, String nameDepartureShipAgency,
                                           String portRegionCode, String ngayLamThuTucFrom,
                                           String ngayLamThuTucTo, String maBanKhai, String representative,
                                           String nameOfShipownersAgents, String maritimeCodeNext) throws SystemException { int count = 0; try {


    StringBuilder query = new StringBuilder();
    StringBuilder queryShipAgency = new StringBuilder();

    String sql = "SELECT count(*) as total FROM ";

    String sql1 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_shifting_order ON issue_shifting_order.DocumentName = temp_document.DocumentName "
            + " AND issue_shifting_order.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.RequestState in (14, 15) and issue_shifting_order.StampStatus = 11 and issue_shifting_order.IsApproval = 0 "
            + " AND issue_shifting_order.Representative = '"
            + representative.trim() + "' ";
    String sql1GroupBy = " GROUP BY issue_shifting_order.DocumentName, issue_shifting_order.DocumentYear, issue_shifting_order.NumberShiftingOrder, issue_shifting_order.VersionNo ";
    String sql2 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_acceptance ON issue_acceptance.DocumentName = temp_document.DocumentName "
            + " AND issue_acceptance.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_acceptance.StampStatus = 11 and issue_acceptance.IsApproval = 0 "
            + " AND issue_acceptance.Representative = '"
            + representative.trim() + "' ";
    String sql2GroupBy = " GROUP BY issue_acceptance.DocumentName, issue_acceptance.DocumentYear, issue_acceptance.NumberPortClearance, issue_acceptance.VersionNo ";
    String sql3 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_port_clearance ON issue_port_clearance.DocumentName = temp_document.DocumentName "
            + " AND issue_port_clearance.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_port_clearance.StampStatus = 11 and issue_port_clearance.IsApproval = 0 "
            + " AND issue_port_clearance.Representative = '"
            + representative.trim() + "' ";
    String sql3GroupBy = " GROUP BY  issue_port_clearance.DocumentName, issue_port_clearance.DocumentYear, issue_port_clearance.NumberPortClearance, issue_port_clearance.VersionNo ";
    String sql4 = "Select temp_document.* from temp_document "
            + " INNER JOIN issue_permission_for_transit  ON issue_permission_for_transit.DocumentName = temp_document.DocumentName "
            + " AND issue_permission_for_transit.DocumentYear = temp_document.DocumentYear "
            + " AND temp_document.DocumentStatusCode in (19, 20) and issue_permission_for_transit.StampStatus = 11 and issue_permission_for_transit.IsApproval = 0 "
            + " AND issue_permission_for_transit.Representative = '"
            + representative.trim() + "' ";
    String sql4GroupBy = " GROUP BY  issue_permission_for_transit.DocumentName, issue_permission_for_transit.DocumentYear, issue_permission_for_transit.NumberPermissionForTransit, issue_permission_for_transit.VersionNo ";

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() > 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() > 0)) {

        queryShipAgency.append("inner join dm_ship_agency dmDep ");
        queryShipAgency
                .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
        queryShipAgency.append("AND (dmDep.ShipAgencyNameVN like '%"
                + nameArrivalShipAgency.trim() + "%' "
                + " OR dmDep.ShipAgencyNameVN like '%"
                + nameDepartureShipAgency.trim() + "%' ");

    }

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() == 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() > 0)) {
        queryShipAgency.append("inner join dm_ship_agency dmDep ");
        queryShipAgency
                .append("on temp_document.DepartureShipAgency = dmDep.ShipAgencyCode ");
        queryShipAgency.append("AND dmDep.ShipAgencyNameVN like '%"
                + nameDepartureShipAgency.trim() + "%' ");

    }

    if ((arrivalShipAgency.trim().length() == 0 && nameArrivalShipAgency
            .trim().length() > 0)
            && (departureShipAgency.trim().length() == 0 && nameDepartureShipAgency
            .trim().length() == 0)) {
        queryShipAgency.append("inner join dm_ship_agency dmArri ");
        queryShipAgency
                .append("on temp_document.ArrivalShipAgency = dmArri.ShipAgencyCode ");
        queryShipAgency.append("AND dmArri.ShipAgencyNameVN like '%"
                + nameArrivalShipAgency.trim() + "%' ");

    }

    query.append(" WHERE 1=1 ");

    // Pb NC,XC,QC
    if (Validator.isNotNull(documentTypeCode)
            && documentTypeCode.trim().length() > 0
            && (documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_NHAP_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_QUA_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_XUAT_CANH)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_VAO_CANG)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_ROI_CANG)
            || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_VAO_CANG_PTTND) || documentTypeCode
            .equals(MessageType.LOAT_THU_TUC_ROI_CANG_PTTND))) {
        query.append(" AND temp_document.DocumentTypeCode = '"
                + documentTypeCode.trim() + "' ");
    }

    // CANG VU
    if (Validator.isNotNull(maritimeCode)
            && maritimeCode.trim().length() > 0
            && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
        query.append(" AND temp_document.MaritimeCode = '"
                + maritimeCode.trim() + "' ");
    }

    // CANG DEN
    if (Validator.isNotNull(portCode) && portCode.trim().length() > 0) {
        query.append(" AND temp_document.PortCode = '"
                + portCode.trim() + "' ");
    }

    // CANG ROI CUOI CUNG
    if (Validator.isNotNull(lastPortCode)
            && lastPortCode.trim().length() > 0) {
        query.append(" AND temp_document.LastPortCode = '"
                + lastPortCode.trim() + "' ");
    }

    // TEN TAU
    if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
        query.append(" AND temp_document.ShipName like '%"
                + shipName.trim() + "%' ");
    }

    // QUOC TICH
    if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
        query.append(" AND temp_document.StateCode = '"
                + stateCode.trim() + "' ");
    }

    // HO HIEU
    if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
        query.append(" AND temp_document.CallSign like '%"
                + callSign.trim() + "%' ");
    }
    if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
        query.append(" AND temp_document.imo like '%" + imo.trim()
                + "%' ");
    }
    // TAU DEN ROI
    if (Validator.isNotNull(shipPosition)
            && shipPosition.trim().length() > 0
            && FormatData.convertToInt(shipPosition) > 0) {
        query.append(" AND temp_document.ShipPosition ="
                + FormatData.convertToInt(shipPosition));
    }

    // thoi gian den FROM
    if (Validator.isNotNull(shipDateFromStart)
            && shipDateFromStart.trim().length() > 0) {
        Date dateFromStart = FormatData
                .parseDateShort3StringToDate(shipDateFromStart.trim());
        shipDateFromStart = FormatData.parseDateToTring(dateFromStart);
        query.append(" AND (temp_document.ShipDateFrom >= concat(date('"
                + shipDateFromStart
                + "'), ' 00:00:00')  OR temp_document.ShipDateFrom IS NULL)");
    }

    // thoi gian den To
    if (Validator.isNotNull(shipDateFromEnd)
            && shipDateFromEnd.trim().length() > 0) {
        Date dateFromEnd = FormatData
                .parseDateShort3StringToDate(shipDateFromEnd.trim());
        shipDateFromEnd = FormatData.parseDateToTring(dateFromEnd);
        query.append(" AND (temp_document.ShipDateFrom <= concat(date('"
                + shipDateFromEnd
                + "'), ' 23:59:59')  OR temp_document.ShipDateFrom IS NULL)");
    }

    // thoi gian roi FROM
    if (Validator.isNotNull(shipDateToStart)
            && shipDateToStart.trim().length() > 0) {
        Date dateToStart = FormatData
                .parseDateShort3StringToDate(shipDateToStart.trim());
        shipDateToStart = FormatData.parseDateToTring(dateToStart);
        query.append(" AND (temp_document.ShipDateTo >= concat(date('"
                + shipDateToStart
                + "'), ' 00:00:00')  OR temp_document.ShipDateTo IS NULL)");
    }

    // thoi gian roi To
    if (Validator.isNotNull(shipDateToEnd)
            && shipDateToEnd.trim().length() > 0) {
        Date dateToEnd = FormatData
                .parseDateShort3StringToDate(shipDateToEnd.trim());
        shipDateToEnd = FormatData.parseDateToTring(dateToEnd);
        query.append(" AND (temp_document.ShipDateTo <= concat(date('"
                + shipDateToEnd
                + "'), ' 23:59:59')  OR temp_document.ShipDateTo IS NULL)");
    }

    // ngay gio lam thu tuc
    // ngayLamThuTucFrom
    // ngayLamThuTucTo
    if (Validator.isNotNull(ngayLamThuTucFrom)
            && ngayLamThuTucFrom.trim().length() > 0) {
        Date dateLamThuTucFrom = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucFrom.trim());
        ngayLamThuTucFrom = FormatData
                .parseDateToTring(dateLamThuTucFrom);
        query.append(" AND (temp_document.CreatedDate >= concat(date('"
                + ngayLamThuTucFrom
                + "'), ' 00:00:00')  OR temp_document.CreatedDate IS NULL)");
    }

    if (Validator.isNotNull(ngayLamThuTucTo)
            && ngayLamThuTucTo.trim().length() > 0) {
        Date dateLamThuTucTo = FormatData
                .parseDateShort3StringToDate(ngayLamThuTucTo.trim());
        ngayLamThuTucTo = FormatData.parseDateToTring(dateLamThuTucTo);
        query.append(" AND (temp_document.CreatedDate <= concat(date('"
                + ngayLamThuTucTo
                + "'), ' 23:59:59')  OR temp_document.CreatedDate IS NULL)");
    }

    // ma~ ban khai
    if (Validator.isNotNull(maBanKhai) && maBanKhai.trim().length() > 0) {
        query.append(" AND (temp_document.DocumentName = '"
                + maBanKhai.trim() + "')");
    }

    // TRANG THAI
    if (Validator.isNotNull(documentStatusCode)
            && documentStatusCode.trim().length() > 0) {
        query.append(" AND temp_document.documentStatusCode IN ("
                + documentStatusCode.trim() + ")");
    }

    // KHU VUC CANG
    if (Validator.isNotNull(portRegionCode)
            && portRegionCode.trim().length() > 0) {
        query.append(" AND temp_document.PortRegionCode = '"
                + portRegionCode.trim() + "' ");
    }

    if (Validator.isNotNull(nameOfShipownersAgents)
            && nameOfShipownersAgents.trim().length() > 0) {
        query.append(" AND temp_document.NameOfShipownersAgents like '%"
                + nameOfShipownersAgents.trim() + "%' ");
    }
    if (Validator.isNotNull(maritimeCodeNext)
            && maritimeCodeNext.trim().length() > 0) {
        query.append(" AND temp_document.LastPortCode like '%"
                + maritimeCodeNext.trim() + "%' ");
    }
    sql = sql + "( " + "( " + sql1 + queryShipAgency.toString()
            + query.toString() + sql1GroupBy + " ) UNION ( " + sql2
            + queryShipAgency.toString() + query.toString()
            + sql2GroupBy + " ) UNION ( " + sql3
            + queryShipAgency.toString() + query.toString()
            + sql3GroupBy + " ) UNION ( " + sql4
            + queryShipAgency.toString() + query.toString()
            + sql4GroupBy + " )" + " ) temp_document ";

    log.debug("=========THU TUC SEARCH --countDanhSachHoSoLanhDaoChoKySo ========");

    QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Integer.class).build();





    count = (Integer) queryFactory.getSingleResult(builder);
} catch (Exception e) {
    throw new SystemException(e);
} finally {

}
    return count;
}

public long countTempDocumentSignedIssuePortClearanceForStatisticsReport(
        String maritimeCode, String dateFrom, String dateTo,
        String documentTypeCode, String requestState,
        String issRequestState, String documentStatusCode)
        throws SystemException {

    long count = 0L;
    Date date = null;
    try {

        StringBuilder query = new StringBuilder();

        query.append("select count(*) as total from temp_document temp ");
        query.append(" inner join issue_port_clearance iss ");
        query.append(" on iss.DocumentName = temp.DocumentName ");
        query.append(" and   iss.DocumentYear  = temp.DocumentYear ");
        query.append(" and   iss.StampStatus = 2 and iss.AttachedFile > 0 ");

        if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {
            date = FormatData.parseDateShort3StringToDate(dateFrom.trim());
            dateFrom = FormatData.parseDateToTring(date);
            query.append(" and iss.SignDate >= concat(date('" + dateFrom
                    + "'), ' 00:00:00') ");
        }
        if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {
            date = FormatData.parseDateShort3StringToDate(dateTo.trim());
            dateTo = FormatData.parseDateToTring(date);
            query.append(" and iss.SignDate <= concat(date('" + dateTo
                    + "'), ' 23:59:59') ");
        }
        if (maritimeCode != null) {
            query.append(" and temp.MaritimeCode = " + maritimeCode);
        }

        if (documentTypeCode != null) {
            query.append(" and temp.DocumentTypeCode in ("
                    + documentTypeCode + ")");
        }

        if (requestState != null) {
            query.append(" and temp.requestState in (" + requestState
                    + ") ");
        }
        if (issRequestState != null) {
            query.append(" and iss.requestState in (" + issRequestState
                    + ") ");
        }
        if (documentStatusCode != null) {
            query.append(" and temp.documentStatusCode in ("
                    + documentStatusCode + ")");
        }

        query.append(" and iss.VersionNo = (SELECT MAX(iss2.VersionNo) "
                + " FROM issue_port_clearance iss2 "
                + " WHERE iss.DocumentName = iss2.DocumentName) ");

        log.debug("----query countTempDocumentSignedIssuePortClearanceForStatisticsReport----");
        log.debug(query.toString());

        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();


        
        count = (Long) queryFactory.getSingleResult(builder);
    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
    return count;

}

public long countTempDocumentSignedIssuePermissionTransitForStatisticsReport(
        String maritimeCode, String dateFrom, String dateTo,
        String documentTypeCode, String requestState,
        String issRequestState, String documentStatusCode)
        throws SystemException {

    long count = 0L;
    Date date = null;
    try {

        StringBuilder query = new StringBuilder();

        query.append("select count(*) as total from temp_document temp ");
        query.append(" inner join issue_permission_for_transit iss ");
        query.append(" on iss.DocumentName = temp.DocumentName ");
        query.append(" and   iss.DocumentYear  = temp.DocumentYear ");
        query.append(" and   iss.StampStatus = 2 and iss.AttachedFile > 0 ");

        if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {
            date = FormatData.parseDateShort3StringToDate(dateFrom.trim());
            dateFrom = FormatData.parseDateToTring(date);
            query.append(" and iss.SignDate >= concat(date('" + dateFrom
                    + "'), ' 00:00:00') ");
        }
        if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {
            date = FormatData.parseDateShort3StringToDate(dateTo.trim());
            dateTo = FormatData.parseDateToTring(date);
            query.append(" and iss.SignDate <= concat(date('" + dateTo
                    + "'), ' 23:59:59') ");
        }
        if (maritimeCode != null) {
            query.append(" and temp.MaritimeCode = " + maritimeCode);
        }

        if (documentTypeCode != null) {
            query.append(" and temp.DocumentTypeCode in ("
                    + documentTypeCode + ")");
        }

        if (requestState != null) {
            query.append(" and temp.requestState in (" + requestState
                    + ") ");
        }
        if (issRequestState != null) {
            query.append(" and iss.requestState in (" + issRequestState
                    + ") ");
        }
        if (documentStatusCode != null) {
            query.append(" and temp.documentStatusCode in ("
                    + documentStatusCode + ")");
        }

        query.append(" and iss.VersionNo = (SELECT MAX(iss2.VersionNo) "
                + " FROM issue_permission_for_transit iss2 "
                + " WHERE iss.DocumentName = iss2.DocumentName) "); // lttai
        // 13/7/2015
        // :
        // lay
        // version
        // moi
        // nhat
        // cua
        // giay
        // phep

        log.debug("---query countTempDocumentSignedIssuePermissionTransitForStatisticsReport----");
        log.debug(query.toString());

        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();


        
        count = (Long) queryFactory.getSingleResult(builder);
    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
    return count;

}

public long countTempDocumentSignedIssueAcceptanceForStatisticsReport(
        String maritimeCode, String dateFrom, String dateTo,
        String documentTypeCode, String requestState,
        String issRequestState, String documentStatusCode)
        throws SystemException {
        long count = 0;
    Date date = null;
    try {

        StringBuilder query = new StringBuilder();

        query.append("select count(*) as total from temp_document temp ");
        query.append(" inner join issue_acceptance iss ");
        query.append(" on iss.DocumentName = temp.DocumentName ");
        query.append(" and   iss.DocumentYear  = temp.DocumentYear ");
        query.append(" and   iss.StampStatus = 2 and iss.AttachedFile > 0 ");

        if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {
            date = FormatData.parseDateShort3StringToDate(dateFrom.trim());
            dateFrom = FormatData.parseDateToTring(date);
            query.append(" and iss.SignDate >= concat(date('" + dateFrom
                    + "'), ' 00:00:00') ");
        }
        if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {
            date = FormatData.parseDateShort3StringToDate(dateTo.trim());
            dateTo = FormatData.parseDateToTring(date);
            query.append(" and iss.SignDate <= concat(date('" + dateTo
                    + "'), ' 23:59:59') ");
        }
        if (maritimeCode != null) {
            query.append(" and temp.MaritimeCode = " + maritimeCode);
        }

        if (documentTypeCode != null) {
            query.append(" and temp.DocumentTypeCode in ("
                    + documentTypeCode + ")");
        }

        if (requestState != null) {
            query.append(" and temp.requestState in (" + requestState
                    + ") ");
        }
        if (issRequestState != null) {
            query.append(" and iss.requestState in (" + issRequestState
                    + ") ");
        }
        if (documentStatusCode != null) {
            query.append(" and temp.documentStatusCode in ("
                    + documentStatusCode + ")");
        }

        query.append(" and iss.VersionNo = (SELECT MAX(iss2.VersionNo) "
                + " FROM issue_acceptance iss2 "
                + " WHERE iss.DocumentName = iss2.DocumentName) "); // lttai
        // 13/7/2015
        // : lay
        // version
        // moi
        // nhat
        // cua
        // giay
        // phep

        log.debug("----query countTempDocumentSignedIssueAcceptanceForStatisticsReport----");
        log.debug(query.toString());

        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();


        
        count = (Long) queryFactory.getSingleResult(builder);
    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
    return count;

}

public long countTempDocumentSignedIssueShiftingOrderForStatisticsReport(
        String maritimeCode, String dateFrom, String dateTo,
        String documentTypeCode, String requestState,
        String issRequestState, String documentStatusCode)
        throws SystemException {

    long count = 0l;
    Date date = null;
    try {

        StringBuilder query = new StringBuilder();

        query.append("select count(*) as total from temp_document temp ");
        query.append(" inner join issue_shifting_order  iss ");
        query.append(" on iss.DocumentName = temp.DocumentName ");
        query.append(" and   iss.DocumentYear  = temp.DocumentYear ");
        query.append(" and   iss.StampStatus = 2 and iss.AttachedFile > 0 ");

        if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {
            date = FormatData.parseDateShort3StringToDate(dateFrom.trim());
            dateFrom = FormatData.parseDateToTring(date);
            query.append(" and iss.SignDate >= concat(date('" + dateFrom
                    + "'), ' 00:00:00') ");
        }
        if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {
            date = FormatData.parseDateShort3StringToDate(dateTo.trim());
            dateTo = FormatData.parseDateToTring(date);
            query.append(" and iss.SignDate <= concat(date('" + dateTo
                    + "'), ' 23:59:59') ");
        }
        if (maritimeCode != null) {
            query.append(" and temp.MaritimeCode = " + maritimeCode);
        }

        if (documentTypeCode != null) {
            query.append(" and temp.DocumentTypeCode in ("
                    + documentTypeCode + ")");
        }

        if (requestState != null) {
            query.append(" and temp.requestState in (" + requestState
                    + ") ");
        }
        if (issRequestState != null) {
            query.append(" and iss.requestState in (" + issRequestState
                    + ") ");
        }
        if (documentStatusCode != null) {
            query.append(" and temp.documentStatusCode in ("
                    + documentStatusCode + ") ");
        }

        query.append(" and iss.VersionNo = (SELECT MAX(iss2.VersionNo) "
                + " FROM issue_shifting_order iss2 "
                + " WHERE iss.DocumentName = iss2.DocumentName) "); // lttai
        // 13/7/2015
        // : lay
        // version
        // moi
        // nhat
        // cua
        // giay
        // phep

        // query.append(" GROUP BY temp.DocumentName "); // lttai
        log.debug("-----------------query countTempDocumentSignedIssueShiftingOrderForStatisticsReport------------------- ");
        log.debug(query.toString());

        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();


        
        count = (Long) queryFactory.getSingleResult(builder);
    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
    return count;

}

public long counterHoSoAll(String documentTypeCode, String maritimeCode,
                           String shipName, String documentName, String callSign)
        throws SystemException {
    long count = 0L;

    Date date = null;
    try {

        StringBuilder query = new StringBuilder();

        query.append("select count(*) as total from temp_document ");

        query.append(" WHERE DocumentTypeCode = '" + documentTypeCode
                + "' ");

        if (Validator.isNotNull(maritimeCode)) {
            query.append(" AND MaritimeCode = " + maritimeCode + " ");
        }
        if (Validator.isNotNull(shipName)) {
            query.append(" AND ShipName like '%" + shipName.trim() + "%' ");
        }
        if (Validator.isNotNull(documentName)) {
            query.append(" AND DocumentName = " + documentName + " ");
        }
        if (Validator.isNotNull(callSign)) {
            query.append(" AND CallSign = '" + callSign + "' ");
        }
        log.debug(query.toString());

        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();


        
        count = (Long) queryFactory.getSingleResult(builder);
    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
    return count;

}

public List<TempDebitnote> findTempDebitNoteTauBien_PTTND(
        String maritimeCode, String positionCode, String portRegionCode,
        String mabankhai, String stateCode, String imo,
        String markasdelted, String lastPortCode, int start, int end,
        String shipName, String callSign, int flag) throws SystemException {

    try {

        StringBuilder query = new StringBuilder();

        String sql = "select temp_debitnote.* from temp_debitnote inner join temp_document on temp_debitnote.DocumentName = temp_document.DocumentName WHERE 1 = 1 ";

        // cang vu hang hai
        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            query.append(" AND temp_document.MaritimeCode = "
                    + maritimeCode.trim());
        }

        // vi tri tau di chuyen
        if (Validator.isNotNull(positionCode)
                && positionCode.trim().length() > 0) {
            query.append(" AND temp_document.ShipPosition = "
                    + positionCode.trim());
        }

        // khu vuc cang
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            query.append(" AND temp_document.PortRegionCode = '"
                    + portRegionCode.trim()+ "' ");
        }

        // Ma ban khai documentName
        if (Validator.isNotNull(mabankhai) && mabankhai.trim().length() > 0) {
            query.append(" AND (temp_document.DocumentName = " + mabankhai.trim()
                    + " OR temp_debitnote.debitnotenumber = " + mabankhai.trim() + " )");
        }

        // Quoc tich
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            query.append(" AND temp_document.StateCode = '"
                    + stateCode.trim()+ "' ");
        }
        if (Validator.isNotNull(lastPortCode)
                && lastPortCode.trim().length() > 0) {
            query.append(" AND temp_document.LastPortCode = '"
                    + lastPortCode.trim()+ "' ");
        }

        if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
            query.append(" AND temp_document.imo like '%" + imo.trim()
                    + "%' ");
        }

        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            query.append(" AND temp_document.ShipName like '%"
                    + shipName.trim() + "%' ");
        }

        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            query.append(" AND temp_document.CallSign like '%"
                    + callSign.trim() + "%' ");
        }

        if (Validator.isNotNull(markasdelted)
                && markasdelted.trim().length() > 0) {
            query.append(" AND temp_debitnote.markasdeleted in ("
                    + markasdelted.trim() + ") ");
        }
        query.append(" AND temp_debitnote.DocumentTypeCode <> '---' ");
        sql = sql + query.toString();

        // Edit by Dungnv - 20/12/2019
        String sql2 = " UNION (SELECT temp_debitnote.* FROM temp_debitnote INNER JOIN (SELECT vma_itinerary.* FROM vma_itinerary WHERE 1 = 1";

        if (flag == 1) {
            sql2 += " AND VRCode IN ('VR', 'VR-NA', 'VRH', 'VRH1', 'VRH2', 'VRH3', 'VR200')";
        } else if (flag == 0) {
            // Sonvh sua: Khong bao gom ho so tren cong MCQG
            sql = StringPool.BLANK;
            sql2 = " (SELECT temp_debitnote.* FROM temp_debitnote INNER JOIN (SELECT vma_itinerary.* FROM vma_itinerary WHERE 1 = 1";
            sql2 += " AND VRCode IN ('VR-SB', 'VR-SI', 'VR-SII', 'VR-SB-SI', 'VR-SB-SII', 'VR-SB-SI-SII', 'VR-SI-SII', '---')";
        }
        // Them dieu kien loc PTTND

        // Quoc tich, Imo, Ho hieu
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            sql2 += " AND vma_itinerary.FlagStateOfShip = '"
                    + stateCode.trim()+ "' ";
        }

        if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
            sql2 += " AND vma_itinerary.IMONumber like '%" + imo.trim()
                    + "%' ";
        }

        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            sql2 += " AND vma_itinerary.NameOfShip like '%"
                    + shipName.trim() + "%' ";
        }

        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            sql2 += " AND vma_itinerary.CallSign like '%"
                    + callSign.trim() + "%' ";
        }

        // vi tri tau di chuyen
        if (Validator.isNotNull(positionCode)
                && positionCode.trim().length() > 0) {
            sql2 += " AND vma_itinerary.ShipPosition = "
                    + positionCode.trim();
        }
        // khu vuc cang
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            sql2 += " AND ItineraryNo IN (SELECT ItineraryNo FROM vma_itinerary_schedule WHERE 1 = 1 AND PortRegionCode = '"
                    + portRegionCode.trim()+ "') ";
        }
        // Cang roi cuoi cung, Cang dich
        if (Validator.isNotNull(lastPortCode)
                && lastPortCode.trim().length() > 0) {
            sql2 += " AND (vma_itinerary.LastPortCode = '"
                    + lastPortCode.trim() +"'"+ " OR vma_itinerary.NextPortCode = '"
                    + lastPortCode.trim() +"')";
        }


        sql2 += " AND ItineraryNo IN (SELECT ItineraryNo FROM temp_debitnote WHERE 1 = 1 AND MaritimeCode = "
                + maritimeCode
                + " AND temp_debitnote.DocumentTypeCode = '---')) AS itinerary ON temp_debitnote.ItineraryNo = itinerary.ItineraryNo WHERE 1 = 1 AND temp_debitnote.DocumentTypeCode = '---' AND temp_debitnote.MaritimeCode = "
                + maritimeCode ;
        // Tinh trang thanh toan
        if (Validator.isNotNull(markasdelted)
                && markasdelted.trim().length() > 0) {
            sql2 += " AND temp_debitnote.markasdeleted in ("
                    + markasdelted.trim() + ") ";
        }

        // Ma ban khai documentName
        if (Validator.isNotNull(mabankhai) && mabankhai.trim().length() > 0) {
            sql2 += " AND temp_debitnote.debitnotenumber = " + mabankhai.trim() + " ";
        }
        sql += sql2 + ") ";

        sql += " ORDER BY reportdate DESC ";

        if (start >= 0) {
            int count = end - start;
            sql = sql + " limit " + start + "," + count;
        }
        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();

        log.info("===findTempDebitNoteTauBien_PTTND==" + sql.toString());



            return (List<TempDebitnote>)  queryFactory2.getResultList(builder);

    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
}


public List<TempDebitnote> findTempDebitNote(String maritimeCode,
                                             String positionCode, String portRegionCode, String mabankhai,
                                             String stateCode, String imo, String markasdelted,
                                             String lastPortCode, int start, int end, String shipName,
                                             String callSign) throws SystemException {

    try {

        StringBuilder query = new StringBuilder();
        // comment by trungnt
        // String sql =
        // "select temp_debitnote.* from temp_debitnote left join temp_document on temp_debitnote.DocumentName = temp_document.DocumentName where 1 = 1 ";
        String sql = "select temp_debitnote.* from temp_debitnote inner join temp_document on temp_debitnote.DocumentName = temp_document.DocumentName  ";
        // cang vu hang hai
        if (Validator.isNotNull(maritimeCode)
                && maritimeCode.trim().length() > 0
                && !maritimeCode.trim().equalsIgnoreCase("LUA_CHON")) {
            query.append(" AND temp_document.MaritimeCode = "
                    + maritimeCode.trim());
        }

        // vi tri tau di chuyen
        if (Validator.isNotNull(positionCode)
                && positionCode.trim().length() > 0) {
            query.append(" AND temp_document.ShipPosition = "
                    + positionCode.trim());
        }

        // khu vuc cang
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            query.append(" AND temp_document.PortRegionCode = '"
                    + portRegionCode.trim()+ "' ");
        }

        // Ma ban khai documentName
        if (Validator.isNotNull(mabankhai) && mabankhai.trim().length() > 0) {
            query.append(" AND temp_document.DocumentName = "
                    + mabankhai.trim());
        }

        // Quoc tich
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            query.append(" AND temp_document.StateCode = '"
                    + stateCode.trim()+ "' ");
        }
        if (Validator.isNotNull(lastPortCode)
                && lastPortCode.trim().length() > 0) {
            query.append(" AND temp_document.LastPortCode = '"
                    + lastPortCode.trim()+ "' ");
        }

        if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
            query.append(" AND temp_document.imo like '%" + imo.trim()
                    + "%' ");
        }

        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            query.append(" AND temp_document.ShipName like '%"
                    + shipName.trim() + "%' ");
        }

        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            query.append(" AND temp_document.CallSign like '%"
                    + callSign.trim() + "%' ");
        }

        if (Validator.isNotNull(markasdelted)
                && markasdelted.trim().length() > 0) {
            query.append(" AND temp_debitnote.markasdeleted in ("
                    + markasdelted.trim() + ") ");
        }

        sql = sql + query.toString();

        // Edit by Dungnv - 20/12/2019
        String sql2 = " UNION (SELECT temp_debitnote.* FROM temp_debitnote INNER JOIN (SELECT vma_itinerary.* FROM vma_itinerary WHERE 1 = 1";
        // Them dieu kien loc PTTND

        // Quoc tich, Imo, Ho hieu
        if (Validator.isNotNull(stateCode) && stateCode.trim().length() > 0) {
            sql2 += " AND vma_itinerary.FlagStateOfShip = '"
                    + stateCode.trim()+ "' ";
        }

        if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
            sql2 += " AND vma_itinerary.IMONumber like '%" + imo.trim()
                    + "%' ";
        }

        if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
            sql2 += " AND vma_itinerary.NameOfShip like '%"
                    + shipName.trim() + "%' ";
        }

        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            sql2 += " AND vma_itinerary.CallSign like '%"
                    + callSign.trim() + "%' ";
        }

        // vi tri tau di chuyen
        if (Validator.isNotNull(positionCode)
                && positionCode.trim().length() > 0) {
            sql2 += " AND vma_itinerary.ShipPosition = "
                    + positionCode.trim();
        }
        // khu vuc cang
        if (Validator.isNotNull(portRegionCode)
                && portRegionCode.trim().length() > 0) {
            sql2 += " AND ItineraryNo IN (SELECT ItineraryNo FROM vma_itinerary_schedule WHERE 1 = 1 AND PortRegionCode = '"
                    + portRegionCode.trim()+ "') ";
        }
        // Cang roi cuoi cung, Cang dich
        if (Validator.isNotNull(lastPortCode)
                && lastPortCode.trim().length() > 0) {
            sql2 += " AND (vma_itinerary.LastPortCode = '"
                    + lastPortCode.trim() +"'"+ " OR vma_itinerary.NextPortCode = '"
                    + lastPortCode.trim() +"')";
        }


        sql2 += " AND ItineraryNo IN (SELECT ItineraryNo FROM temp_debitnote WHERE 1 = 1 AND MaritimeCode = "
                + maritimeCode
                + " AND temp_debitnote.DocumentTypeCode = '---')) AS itinerary ON temp_debitnote.ItineraryNo = itinerary.ItineraryNo WHERE 1 = 1 AND temp_debitnote.DocumentTypeCode = '---' AND temp_debitnote.MaritimeCode = "
                + maritimeCode ;
        // Tinh trang thanh toan
        if (Validator.isNotNull(markasdelted)
                && markasdelted.trim().length() > 0) {
            sql2 += " AND temp_debitnote.markasdeleted in ("
                    + markasdelted.trim() + ") ";
        }

        sql += sql2 + ") ";



        // add by TrungNT

        sql += " ORDER BY reportdate DESC ";

        if (start >= 0) {
            int count = end - start;
            sql = sql + " limit " + start + "," + count;
        }
        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDocument.class).build();

        log.debug("===findTempDebitNote==" + sql.toString());
        // log.info("===findTempDocumentByKeHoach==" );


            return (List<TempDebitnote>)  queryFactory2.getResultList(builder);

    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
}

    public List<TempDocument> findTempDocument(Class<?> clazz,
                                               String className, String sql, int start, int end)
            throws SystemException {

        

        try {
           

            log.info("=========select Temp_Document>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
                    + sql);

            /* QueryPos qPos = QueryPos.getInstance(q); */
            QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(TempDocument.class).build();

            return (List<TempDocument>)  queryFactory.getResultList(builder);
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            
        }
    }


public boolean isValid_ShipAgencyCode_IMO_MaritimeCode(long documentName,
                                                       int documentYear, String imo, String maritimeCode,
                                                       String ShipAgencyCode) throws SystemException {

    Date date = null;
    try {

        StringBuilder query = new StringBuilder();
        StringBuilder query1 = new StringBuilder();

        query1.append("select count(*) as total from temp_document temp ");
        query1.append(" where temp.DocumentName = " + documentName + " ");
        query1.append(" and temp.DocumentYear = " + documentYear + " ");

        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query1.toString()).entityClass(Long.class)
                .build();
        Long count = null;

        count = (Long) queryFactory.getSingleResult(builder);
        // B1. Kiem tra xem ho so co ton tai; Neu ton tai thi kiem tra tiep
        // B2; Neu Count=0 thi tra ve OK

        if ((count != null) && count.longValue() > 0) {
            // return count.longValue();
            // B2. Kiem tra xem neu ho so ton tai va Cang vu da tiep
            // nhan. Neu ton tai bo key duoi day thi tra ve OK.
            // -- Neu Count=0 tra ve ERR, phai canh bao, khong cap nhat
            // va tu choi tiep nhan message. Quy tac nhu sau
            // -- 1. Khong ton tai cung luc 1 ho so lien quan den 2 dai
            // ly khac nhau;
            // -- 2. Hoac lien quan den 2 tau khac nhau;
            // -- 3. Hoac lien quan den 2 cang vu cung thu ly.

            // B3. Kiem tra xem neu ho so ton tai nhung Cang vu chua
            // tiep nhan. Neu ton tai thi tra ve OK.
            // -- Neu Count=0 tra ve ERR, phai canh bao, khong cap nhat
            // va tu choi tiep nhan message. Quy tac nhu sau
            // -- 1. Khong ton tai cung luc 1 ho so lien quan den 2 dai
            // ly khac nhau;
            // -- 2. Hoac lien quan den 2 cang vu cung thu ly.
            // -- Nhung, van cho sua ten tau, quoc tich, ho hieu, IMO
            query.append("select count(*) as total from temp_document temp ");
            query.append(" where  ");
            query.append(" temp.DocumentName = " + documentName + " ");
            query.append(" and temp.DocumentYear = " + documentYear + " ");
            query.append(
                    " and (temp.ShipAgencyCode = " + ShipAgencyCode + " OR LENGTH(temp.ShipAgencyCode) = 0 ) ");
            query.append(" and (MaritimeCode = " + maritimeCode + " OR LENGTH(MaritimeCode) = 0 ) ");
            query.append(" and   ");
            query.append(" ((temp.RequestState in (14,114, 15,16,12,13) and (temp.IMO = '" + imo.trim() + "'"
                    + " OR LENGTH(temp.IMO) = 0 ) " + " ) ");
            query.append(" OR  ");
            query.append(" RequestState in (11,27) ) ");
            log.info("-----------------query isValid_ShipAgencyCode_IMO_MaritimeCode------------------- ");
            log.info(query.toString());

            QueryBuilder builder2 = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class)
                    .build();


            Long countNEXT = null;
            countNEXT = (Long) queryFactory.getSingleResult(builder2);
            if (countNEXT != null && countNEXT.longValue() > 0) {
                return true;
            } else {
                return false;
            }

            // return 0; // invalid
            // return 1; // valid
        } else {
            // Nguoc lai, return 0; // valid
            return true;
        }

    } catch (Exception e) {
        throw new SystemException(e);
    } finally {
        // TODO?
        // TODO?
    }

}

public TempDocument findTempDocumentLeftByIMOandCallSign(
        String maritimeCode, Date DateOfArrival, String callSign,
        String imo, String documentTypeCode, String requestState,
        String documentStatusCode) throws SystemException {


    List<TempDocument> allTempDocument = null;
    try {

        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM temp_document ");
        query.append(" WHERE 1 = 1 ");

        if (maritimeCode != null) {
            query.append(" and MaritimeCode = " + maritimeCode);
        }

        if (Validator.isNotNull(DateOfArrival)) {
            String dateTo = FormatData.parseDateToTring(DateOfArrival);
            query.append(" and ShipDateFrom <= concat(date('" + dateTo
                    + "'), ' 23:59:59') ");
        }

        // Ho hieu
        if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
            query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
        }

        // IMO
        if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
            query.append(" AND imo like '%" + imo.trim() + "%' ");
        }

        if (documentTypeCode != null) {
            query.append(" and DocumentTypeCode in (" + documentTypeCode
                    + ")");
        }

        if (requestState != null) {
            query.append(" and requestState !=" + requestState);
        }
        if (documentStatusCode != null) {
            query.append(" and documentStatusCode != " + documentStatusCode);
        }

        String sql = query.toString();
        sql = sql + " order by ShipDateFrom desc limit 1 ";
        QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();
        // execute the query and return a list from the db

        allTempDocument = (List<TempDocument>)  queryFactory.getResultList(builder);
        return allTempDocument.get(0);
    } catch (Exception e) {
        throw new SystemException(e);
    } finally {

    }
}

public long countTempDocument(String sql) throws SystemException { long count = 0; try {


    log.info("=========count Temp_Document>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
            + sql);

    QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

    /*  */


    count = (Long) queryFactory.getSingleResult(builder);
} catch (Exception e) {
    throw new SystemException(e);
} finally {

}
    return count;
}

}
