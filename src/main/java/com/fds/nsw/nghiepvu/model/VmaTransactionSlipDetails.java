package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "vma_transaction_slip_details", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaTransactionSlipDetails {
    @Id
@Column(name = "ID", nullable = false)
private Long id=0L;


    @Column(name = "ItineraryNo", length = 30)
    private String itineraryNo;

    @Column(name = "DocumentaryCode")
    private String documentaryCode;

    @Column(name = "ShipBoat", length = 10)
    private String shipBoat;

    @ColumnDefault("0.00")
    @Column(name = "GT")
    private Double gt;

    @ColumnDefault("0.00")
    @Column(name = "DWT")
    private Double dwt;

    @ColumnDefault("0.00")
    @Column(name = "GTPercentage")
    private Double gtPercentage;

    @ColumnDefault("0.00")
    @Column(name = "GTConversion")
    private Double gtConversion;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1311Vnd")
    private Double inRateF1311Vnd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1312Usd")
    private Double inRateF1312Usd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1321Vnd")
    private Double inRateF1321Vnd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1322Usd")
    private Double inRateF1322Usd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1331Vnd")
    private Double inRateF1331Vnd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1332Usd")
    private Double inRateF1332Usd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1341Vnd")
    private Double inRateF1341Vnd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1342Usd")
    private Double inRateF1342Usd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1351Vnd")
    private Double inRateF1351Vnd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1352Usd")
    private Double inRateF1352Usd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1361Vnd")
    private Double inRateF1361Vnd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1362Usd")
    private Double inRateF1362Usd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1371Vnd")
    private Double inRateF1371Vnd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1372Usd")
    private Double inRateF1372Usd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1381Vnd")
    private Double inRateF1381Vnd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1382Usd")
    private Double inRateF1382Usd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1391Vnd")
    private Double inRateF1391Vnd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1392Usd")
    private Double inRateF1392Usd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1301Vnd")
    private Double inRateF1301Vnd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1302Usd")
    private Double inRateF1302Usd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1313Vnd")
    private Double inRateF1313Vnd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1363Vnd")
    private Double inRateF1363Vnd;

    @ColumnDefault("0.00")
    @Column(name = "InRateF1373Vnd")
    private Double inRateF1373Vnd;

        @ColumnDefault("0")
    @Column(name = "InUnitF1311Vnd") 
    private Double inUnitF1311Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InUnitF1312Usd")
    private Double inUnitF1312Usd;

        @ColumnDefault("0")
    @Column(name = "InUnitF1321Vnd") 
    private Double inUnitF1321Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InUnitF1322Usd")
    private Double inUnitF1322Usd;

        @ColumnDefault("0")
    @Column(name = "InUnitF1331Vnd") 
    private Double inUnitF1331Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InUnitF1332Usd")
    private Double inUnitF1332Usd;

        @ColumnDefault("0")
    @Column(name = "InUnitF1341Vnd") 
    private Double inUnitF1341Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InUnitF1342Usd")
    private Double inUnitF1342Usd;

        @ColumnDefault("0")
    @Column(name = "InUnitF1351Vnd") 
    private Double inUnitF1351Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InUnitF1352Usd")
    private Double inUnitF1352Usd;

        @ColumnDefault("0")
    @Column(name = "InUnitF1361Vnd") 
    private Double inUnitF1361Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InUnitF1362Usd")
    private Double inUnitF1362Usd;

        @ColumnDefault("0")
    @Column(name = "InUnitF1371Vnd") 
    private Double inUnitF1371Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InUnitF1372Usd")
    private Double inUnitF1372Usd;

        @ColumnDefault("0")
    @Column(name = "InUnitF1381Vnd") 
    private Double inUnitF1381Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InUnitF1382Usd")
    private Double inUnitF1382Usd;

        @ColumnDefault("0")
    @Column(name = "InUnitF1391Vnd") 
    private Double inUnitF1391Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InUnitF1392Usd")
    private Double inUnitF1392Usd;

        @ColumnDefault("0")
    @Column(name = "InUnitF1301Vnd") 
    private Double inUnitF1301Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InUnitF1302Usd")
    private Double inUnitF1302Usd;

        @ColumnDefault("0")
    @Column(name = "InUnitF1313Vnd") 
    private Double inUnitF1313Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "InUnitF1363Vnd") 
    private Double inUnitF1363Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "InUnitF1373Vnd") 
    private Double inUnitF1373Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "InF1311Vnd") 
    private Double inF1311Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InF1312Usd")
    private Double inF1312Usd;

        @ColumnDefault("0")
    @Column(name = "InF1321Vnd") 
    private Double inF1321Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InF1322Usd")
    private Double inF1322Usd;

        @ColumnDefault("0")
    @Column(name = "InF1331Vnd") 
    private Double inF1331Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InF1332Usd")
    private Double inF1332Usd;

        @ColumnDefault("0")
    @Column(name = "InF1341Vnd") 
    private Double inF1341Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InF1342Usd")
    private Double inF1342Usd;

        @ColumnDefault("0")
    @Column(name = "InF1351Vnd") 
    private Double inF1351Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InF1352Usd")
    private Double inF1352Usd;

        @ColumnDefault("0")
    @Column(name = "InF1361Vnd") 
    private Double inF1361Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InF1362Usd")
    private Double inF1362Usd;

        @ColumnDefault("0")
    @Column(name = "InF1371Vnd") 
    private Double inF1371Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InF1372Usd")
    private Double inF1372Usd;

        @ColumnDefault("0")
    @Column(name = "InF1381Vnd") 
    private Double inF1381Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InF1382Usd")
    private Double inF1382Usd;

        @ColumnDefault("0")
    @Column(name = "InF1391Vnd") 
    private Double inF1391Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InF1392Usd")
    private Double inF1392Usd;

        @ColumnDefault("0")
    @Column(name = "InF1301Vnd") 
    private Double inF1301Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "InF1302Usd")
    private Double inF1302Usd;

        @ColumnDefault("0")
    @Column(name = "InF1313Vnd") 
    private Double inF1313Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "InF1363Vnd") 
    private Double inF1363Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "InF1373Vnd") 
    private Double inF1373Vnd=Double.valueOf(0);

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1311Vnd")
    private Double outRateF1311Vnd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1312Usd")
    private Double outRateF1312Usd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1321Vnd")
    private Double outRateF1321Vnd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1322Usd")
    private Double outRateF1322Usd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1331Vnd")
    private Double outRateF1331Vnd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1332Usd")
    private Double outRateF1332Usd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1341Vnd")
    private Double outRateF1341Vnd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1342Usd")
    private Double outRateF1342Usd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1351Vnd")
    private Double outRateF1351Vnd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1352Usd")
    private Double outRateF1352Usd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1361Vnd")
    private Double outRateF1361Vnd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1362Usd")
    private Double outRateF1362Usd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1371Vnd")
    private Double outRateF1371Vnd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1372Usd")
    private Double outRateF1372Usd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1381Vnd")
    private Double outRateF1381Vnd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1382Usd")
    private Double outRateF1382Usd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1391Vnd")
    private Double outRateF1391Vnd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1392Usd")
    private Double outRateF1392Usd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1301Vnd")
    private Double outRateF1301Vnd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1302Usd")
    private Double outRateF1302Usd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1313Vnd")
    private Double outRateF1313Vnd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1363Vnd")
    private Double outRateF1363Vnd;

    @ColumnDefault("0.00")
    @Column(name = "OutRateF1373Vnd")
    private Double outRateF1373Vnd;

        @ColumnDefault("0")
    @Column(name = "OutUnitF1311Vnd") 
    private Double outUnitF1311Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutUnitF1312Usd")
    private Double outUnitF1312Usd;

        @ColumnDefault("0")
    @Column(name = "OutUnitF1321Vnd") 
    private Double outUnitF1321Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutUnitF1322Usd")
    private Double outUnitF1322Usd;

        @ColumnDefault("0")
    @Column(name = "OutUnitF1331Vnd") 
    private Double outUnitF1331Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutUnitF1332Usd")
    private Double outUnitF1332Usd;

        @ColumnDefault("0")
    @Column(name = "OutUnitF1341Vnd") 
    private Double outUnitF1341Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutUnitF1342Usd")
    private Double outUnitF1342Usd;

        @ColumnDefault("0")
    @Column(name = "OutUnitF1351Vnd") 
    private Double outUnitF1351Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutUnitF1352Usd")
    private Double outUnitF1352Usd;

        @ColumnDefault("0")
    @Column(name = "OutUnitF1361Vnd") 
    private Double outUnitF1361Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutUnitF1362Usd")
    private Double outUnitF1362Usd;

        @ColumnDefault("0")
    @Column(name = "OutUnitF1371Vnd") 
    private Double outUnitF1371Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutUnitF1372Usd")
    private Double outUnitF1372Usd;

        @ColumnDefault("0")
    @Column(name = "OutUnitF1381Vnd") 
    private Double outUnitF1381Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutUnitF1382Usd")
    private Double outUnitF1382Usd;

        @ColumnDefault("0")
    @Column(name = "OutUnitF1391Vnd") 
    private Double outUnitF1391Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutUnitF1392Usd")
    private Double outUnitF1392Usd;

        @ColumnDefault("0")
    @Column(name = "OutUnitF1301Vnd") 
    private Double outUnitF1301Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutUnitF1302Usd")
    private Double outUnitF1302Usd;

        @ColumnDefault("0")
    @Column(name = "OutUnitF1313Vnd") 
    private Double outUnitF1313Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "OutUnitF1363Vnd") 
    private Double outUnitF1363Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "OutUnitF1373Vnd") 
    private Double outUnitF1373Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "OutF1311Vnd") 
    private Double outF1311Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutF1312Usd")
    private Double outF1312Usd;

        @ColumnDefault("0")
    @Column(name = "OutF1321Vnd") 
    private Double outF1321Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutF1322Usd")
    private Double outF1322Usd;

        @ColumnDefault("0")
    @Column(name = "OutF1331Vnd") 
    private Double outF1331Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutF1332Usd")
    private Double outF1332Usd;

        @ColumnDefault("0")
    @Column(name = "OutF1341Vnd") 
    private Double outF1341Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutF1342Usd")
    private Double outF1342Usd;

        @ColumnDefault("0")
    @Column(name = "OutF1351Vnd") 
    private Double outF1351Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutF1352Usd")
    private Double outF1352Usd;

        @ColumnDefault("0")
    @Column(name = "OutF1361Vnd") 
    private Double outF1361Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutF1362Usd")
    private Double outF1362Usd;

        @ColumnDefault("0")
    @Column(name = "OutF1371Vnd") 
    private Double outF1371Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutF1372Usd")
    private Double outF1372Usd;

        @ColumnDefault("0")
    @Column(name = "OutF1381Vnd") 
    private Double outF1381Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutF1382Usd")
    private Double outF1382Usd;

        @ColumnDefault("0")
    @Column(name = "OutF1391Vnd") 
    private Double outF1391Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutF1392Usd")
    private Double outF1392Usd;

        @ColumnDefault("0")
    @Column(name = "OutF1301Vnd") 
    private Double outF1301Vnd=Double.valueOf(0);

    @ColumnDefault("0.00000")
    @Column(name = "OutF1302Usd")
    private Double outF1302Usd;

        @ColumnDefault("0")
    @Column(name = "OutF1313Vnd") 
    private Double outF1313Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "OutF1363Vnd") 
    private Double outF1363Vnd=Double.valueOf(0);

        @ColumnDefault("0")
    @Column(name = "OutF1373Vnd") 
    private Double outF1373Vnd=Double.valueOf(0);

    @Column(name = "InCurrencyCode", length = 10)
    private String inCurrencyCode;

    @Column(name = "OutCurrencyCode", length = 10)
    private String outCurrencyCode;

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
    @Column(name = "InGtRemarks")
    private String inGtRemarks;

    @Lob
    @Column(name = "OutGtRemarks")
    private String outGtRemarks;

    @Lob
    @Column(name = "InDwtRemarks")
    private String inDwtRemarks;

    @Lob
    @Column(name = "OutDwtRemarks")
    private String outDwtRemarks;

    @Lob
    @Column(name = "InF1313Remarks")
    private String inF1313Remarks;

    @Lob
    @Column(name = "OutF1313Remarks")
    private String outF1313Remarks;

    @Lob
    @Column(name = "InF1311Remarks")
    private String inF1311Remarks;

    @Lob
    @Column(name = "InF1312Remarks")
    private String inF1312Remarks;

    @Lob
    @Column(name = "OutF1311Remarks")
    private String outF1311Remarks;

    @Lob
    @Column(name = "OutF1312Remarks")
    private String outF1312Remarks;

    @Lob
    @Column(name = "InF1351Remarks")
    private String inF1351Remarks;

    @Lob
    @Column(name = "InF1352Remarks")
    private String inF1352Remarks;

    @Lob
    @Column(name = "OutF1351Remarks")
    private String outF1351Remarks;

    @Lob
    @Column(name = "OutF1352Remarks")
    private String outF1352Remarks;

}