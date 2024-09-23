package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_transaction_slip", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaTransactionSlip {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "ItineraryNo", length = 30)
    private String itineraryNo;

    @Column(name = "SequenceNo")
    private Integer sequenceNo;

    @Column(name = "DocumentaryCode", length = 30)
    private String documentaryCode;

    @Column(name = "ApprovalDate")
    private Date approvalDate;

    @Lob
    @Column(name = "FundTransferNo")
    private String fundTransferNo;

    @Column(name = "FundTransferDate")
    private Date fundTransferDate;

    @Lob
    @Column(name = "FundTransferDetails")
    private String fundTransferDetails;

    @Lob
    @Column(name = "ReceiptNo")
    private String receiptNo;

    @Column(name = "ReceiptDate")
    private Date receiptDate;

    @Lob
    @Column(name = "ReceiptDetails")
    private String receiptDetails;

    @Lob
    @Column(name = "ReceiptRemark")
    private String receiptRemark;

    @Lob
    @Column(name = "ReceiptBookNo")
    private String receiptBookNo;

    @Lob
    @Column(name = "eReceiptNo")
    private String eReceiptNo;

    @Column(name = "eReceiptDate")
    private Date eReceiptDate;

    @Lob
    @Column(name = "eReceiptDetails")
    private String eReceiptDetails;

    @Lob
    @Column(name = "EmailRecipients")
    private String emailRecipients;

    @Lob
    @Column(name = "PortClearanceCertificateNo")
    private String portClearanceCertificateNo;

    @Column(name = "DocumentaryKind", length = 30)
    private String documentaryKind;

    @Column(name = "DocumentaryNo", length = 30)
    private String documentaryNo;

    @Column(name = "DocumentaryIssued")
    private Date documentaryIssued;

    @Lob
    @Column(name = "SupplementDocumentary")
    private String supplementDocumentary;

    @ColumnDefault("0")
    @Column(name = "AttachedFile")
    private Long attachedFile;

    @Column(name = "SignTitle")
    private String signTitle;

    @Column(name = "SignName")
    private String signName;

    @Column(name = "PortofAuthority", length = 12)
    private String portofAuthority;

    @Column(name = "NameOfShip")
    private String nameOfShip;

    @Column(name = "FlagStateOfShip", length = 20)
    private String flagStateOfShip;

    @Column(name = "IMONumber", length = 50)
    private String imoNumber;

    @Column(name = "CallSign", length = 20)
    private String callSign;

    @ColumnDefault("''")
    @Column(name = "RegistryNumber", length = 30)
    private String registryNumber;

    @Column(name = "GT", precision = 18, scale = 2)
    private BigDecimal gt;

    @Column(name = "DWT", precision = 18, scale = 2)
    private BigDecimal dwt;

    @Column(name = "ArrivalPortName")
    private String arrivalPortName;

    @Column(name = "LastPortName")
    private String lastPortName;

    @Column(name = "NextPortName")
    private String nextPortName;

    @Column(name = "DischargedPorts")
    private String dischargedPorts;

    @Lob
    @Column(name = "CargoDescription")
    private String cargoDescription;

    @Column(name = "ArrivalDate")
    private Date arrivalDate;

    @Column(name = "DepartureDate")
    private Date departureDate;

    @Column(name = "NoticeShipType")
    private Integer noticeShipType;

    @ColumnDefault("''")
    @Column(name = "DoCharge", length = 50)
    private String doCharge;

    @Column(name = "ShipAgencyCode", length = 30)
    private String shipAgencyCode;

    @Column(name = "ShipAgencyName")
    private String shipAgencyName;

    @Column(name = "ShipOwnerCode", length = 30)
    private String shipOwnerCode;

    @Column(name = "ShipOwnerName")
    private String shipOwnerName;

    @ColumnDefault("''")
    @Column(name = "ShipOperationName")
    private String shipOperationName;

        @ColumnDefault("0")
    @Column(name = "HideShipOwnerShipAgency") 
    private Integer hideShipOwnerShipAgency=0;

    @ColumnDefault("'1'")
    @Column(name = "PaymentBy", length = 6)
    private String paymentBy;

    @ColumnDefault("''")
    @Column(name = "PaymentName")
    private String paymentName;

        @ColumnDefault("0")
    @Column(name = "PaymentType") 
    private Integer paymentType=0;

        @ColumnDefault("0")
    @Column(name = "MakePayment2Arrival") 
    private Integer makePayment2Arrival=0;

        @ColumnDefault("0")
    @Column(name = "MakePayment2Departure") 
    private Integer makePayment2Departure=0;

        @ColumnDefault("0")
    @Column(name = "PaymentStatus") 
    private Integer paymentStatus=0;

        @ColumnDefault("0")
    @Column(name = "PaymentCategory") 
    private Integer paymentCategory=0;

        @ColumnDefault("0")
    @Column(name = "TrackChangesCargoList") 
    private Integer trackChangesCargoList=0;

        @ColumnDefault("0")
    @Column(name = "TrachChangesAnchorage") 
    private Integer trachChangesAnchorage=0;

        @ColumnDefault("0")
    @Column(name = "TrachChangesProtest") 
    private Integer trachChangesProtest=0;

        @ColumnDefault("0")
    @Column(name = "StampStatus") 
    private Integer stampStatus=0;

    @ColumnDefault("''")
    @Column(name = "StampSerialNo", length = 20)
    private String stampSerialNo;

    @ColumnDefault("''")
    @Column(name = "Reportby")
    private String reportby;

    @Column(name = "Reportdate")
    private Date reportdate;

    @ColumnDefault("0.00")
    @Column(name = "Tax")
    private Double tax;

        @ColumnDefault("0")
    @Column(name = "PrintedConvertVND") 
    private Integer printedConvertVND=0;

        @ColumnDefault("0")
    @Column(name = "HideExchangeRate") 
    private Integer hideExchangeRate=0;

    @ColumnDefault("0.00")
    @Column(name = "ExchangeRate")
    private Double exchangeRate;

    @Column(name = "CurrencyExgDate")
    private Date currencyExgDate;

    @ColumnDefault("0.00")
    @Column(name = "DocumentaryExchangeRate")
    private Double documentaryExchangeRate;

    @Column(name = "DocumentaryCurrencyExgDate")
    private Date documentaryCurrencyExgDate;

    @ColumnDefault("0.00")
    @Column(name = "ExchangeRateDifferences")
    private Double exchangeRateDifferences;

    @Column(name = "DifferencesExgDate")
    private Date differencesExgDate;

        @ColumnDefault("0")
    @Column(name = "PaymentDifferences") 
    private Double paymentDifferences=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "PaymentInFigures") 
    private Double paymentInFigures=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "PaymentRealAmount") 
    private Double paymentRealAmount=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "PaymentAmount") 
    private Double paymentAmount=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "PaidAdvanceAmount") 
    private Double paidAdvanceAmount=Double.valueOf(0);

    @Column(name = "PaymentDate")
    private Date paymentDate;

        @ColumnDefault("0")
    @Column(name = "VndTotalAmount") 
    private Double vndTotalAmount=Double.valueOf(0);

    @ColumnDefault("0.00")
    @Column(name = "UsdTotalAmount")
    private Double usdTotalAmount;

    @Lob
    @Column(name = "AmountInWordsVnd")
    private String amountInWordsVnd;

    @Lob
    @Column(name = "AmountInWordsUsd")
    private String amountInWordsUsd;

    @Lob
    @Column(name = "FinancialAccountant")
    private String financialAccountant;

    @Column(name = "DepartmentCode", length = 30)
    private String departmentCode;

    @Column(name = "DepartmentName")
    private String departmentName;

    @Column(name = "debitnoteid")
    private Integer debitnoteid;

    @Column(name = "fromdate")
    private Date fromdate;

    @Column(name = "todate")
    private Date todate;

    @Lob
    @Column(name = "TotalAnchorageHours")
    private String totalAnchorageHours;

    @Lob
    @Column(name = "NumberItineraryPerMonth")
    private String numberItineraryPerMonth;

    @Lob
    @Column(name = "MainPurpose")
    private String mainPurpose;

        @ColumnDefault("0")
    @Column(name = "F1319Vnd") 
    private Double f1319Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1311Vnd") 
    private Double f1311Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "F1312Usd")
    private Double f1312Usd;

        @ColumnDefault("0")
    @Column(name = "F1329Vnd") 
    private Double f1329Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1321Vnd") 
    private Double f1321Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "F1322Usd")
    private Double f1322Usd;

        @ColumnDefault("0")
    @Column(name = "F1339Vnd") 
    private Double f1339Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1331Vnd") 
    private Double f1331Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "F1332Usd")
    private Double f1332Usd;

        @ColumnDefault("0")
    @Column(name = "F1349Vnd") 
    private Double f1349Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1341Vnd") 
    private Double f1341Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "F1342Usd")
    private Double f1342Usd;

        @ColumnDefault("0")
    @Column(name = "F1359Vnd") 
    private Double f1359Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1351Vnd") 
    private Double f1351Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "F1352Usd")
    private Double f1352Usd;

        @ColumnDefault("0")
    @Column(name = "F1369Vnd") 
    private Double f1369Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1361Vnd") 
    private Double f1361Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "F1362Usd")
    private Double f1362Usd;

        @ColumnDefault("0")
    @Column(name = "F1379Vnd") 
    private Double f1379Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1371Vnd") 
    private Double f1371Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "F1372Usd")
    private Double f1372Usd;

        @ColumnDefault("0")
    @Column(name = "F1389Vnd") 
    private Double f1389Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1381Vnd") 
    private Double f1381Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "F1382Usd")
    private Double f1382Usd;

        @ColumnDefault("0")
    @Column(name = "F1399Vnd") 
    private Double f1399Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1391Vnd") 
    private Double f1391Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "F1392Usd")
    private Double f1392Usd;

        @ColumnDefault("0")
    @Column(name = "F1309Vnd") 
    private Double f1309Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1301Vnd") 
    private Double f1301Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "F1302Usd")
    private Double f1302Usd;

        @ColumnDefault("0")
    @Column(name = "F1313Vnd") 
    private Double f1313Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1363Vnd") 
    private Double f1363Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1373Vnd") 
    private Double f1373Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1411Vnd") 
    private Double f1411Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1412Vnd") 
    private Double f1412Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1413Vnd") 
    private Double f1413Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1421Vnd") 
    private Double f1421Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1422Vnd") 
    private Double f1422Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1423Vnd") 
    private Double f1423Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1431Vnd") 
    private Double f1431Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "F1432Vnd") 
    private Double f1432Vnd=Double.valueOf(0);

    @Lob
    @Column(name = "NameOfPortFacility")
    private String nameOfPortFacility;

    @Lob
    @Column(name = "AddressOfPortFacility")
    private String addressOfPortFacility;

    @Lob
    @Column(name = "StatementNumber")
    private String statementNumber;

    @Column(name = "DateOfStatement")
    private Date dateOfStatement;

    @Lob
    @Column(name = "StatementIssuedAt")
    private String statementIssuedAt;

    @Column(name = "StatementValidUntil")
    private Date statementValidUntil;

    @Lob
    @Column(name = "GtRemarks")
    private String gtRemarks;

    @Lob
    @Column(name = "F1311Remarks")
    private String f1311Remarks;

    @Lob
    @Column(name = "F1312Remarks")
    private String f1312Remarks;

    @Lob
    @Column(name = "F1321Remarks")
    private String f1321Remarks;

    @Lob
    @Column(name = "F1322Remarks")
    private String f1322Remarks;

    @Lob
    @Column(name = "F1331Remarks")
    private String f1331Remarks;

    @Lob
    @Column(name = "F1332Remarks")
    private String f1332Remarks;

    @Lob
    @Column(name = "F1341Remarks")
    private String f1341Remarks;

    @Lob
    @Column(name = "F1342Remarks")
    private String f1342Remarks;

    @Lob
    @Column(name = "F1351Remarks")
    private String f1351Remarks;

    @Lob
    @Column(name = "F1352Remarks")
    private String f1352Remarks;

    @Lob
    @Column(name = "F1361Remarks")
    private String f1361Remarks;

    @Lob
    @Column(name = "F1362Remarks")
    private String f1362Remarks;

    @Lob
    @Column(name = "F1371Remarks")
    private String f1371Remarks;

    @Lob
    @Column(name = "F1372Remarks")
    private String f1372Remarks;

    @Lob
    @Column(name = "F1381Remarks")
    private String f1381Remarks;

    @Lob
    @Column(name = "F1382Remarks")
    private String f1382Remarks;

    @Lob
    @Column(name = "F1391Remarks")
    private String f1391Remarks;

    @Lob
    @Column(name = "F1392Remarks")
    private String f1392Remarks;

    @Lob
    @Column(name = "F1301Remarks")
    private String f1301Remarks;

    @Lob
    @Column(name = "F1302Remarks")
    private String f1302Remarks;

    @Lob
    @Column(name = "F1313Remarks")
    private String f1313Remarks;

    @Lob
    @Column(name = "F1363Remarks")
    private String f1363Remarks;

    @Lob
    @Column(name = "F1373Remarks")
    private String f1373Remarks;

    @Lob
    @Column(name = "F1411Remarks")
    private String f1411Remarks;

    @Lob
    @Column(name = "F1412Remarks")
    private String f1412Remarks;

    @Lob
    @Column(name = "F1413Remarks")
    private String f1413Remarks;

    @Lob
    @Column(name = "F1421Remarks")
    private String f1421Remarks;

    @Lob
    @Column(name = "F1422Remarks")
    private String f1422Remarks;

    @Lob
    @Column(name = "F1423Remarks")
    private String f1423Remarks;

    @Lob
    @Column(name = "F1431Remarks")
    private String f1431Remarks;

    @Lob
    @Column(name = "F1432Remarks")
    private String f1432Remarks;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}