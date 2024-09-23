package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vma_report_category", schema = "dvc_hanghai_nghiepvu_clone")
public class VmaReportCategory {
    @Id
@Column(name = "ID", nullable = false)
private Integer id=0;


    @Column(name = "RptCode", nullable = false, length = 12)
    private String rptCode;

    @Column(name = "RptName", nullable = false, length = 250)
    private String rptName;

    @Column(name = "RptDescription", length = 250)
    private String rptDescription;

    @Column(name = "RptGroup", length = 12)
    private String rptGroup;

    @Column(name = "RptLevel")
    private Integer rptLevel;

    @Column(name = "SequenceNo")
    private Integer sequenceNo;

    @Column(name = "JasperFileTemplate", length = 50)
    private String jasperFileTemplate;

    @Column(name = "ExcelFileTemplate", length = 50)
    private String excelFileTemplate;

    @Column(name = "MaritimeCodeSelect")
    private Integer maritimeCodeSelect;

    @Column(name = "PortCodeSelect")
    private Integer portCodeSelect;

    @Column(name = "PortRegionCodeSelect")
    private Integer portRegionCodeSelect;

    @Column(name = "PortHarbourCodeSelect")
    private Integer portHarbourCodeSelect;

    @Column(name = "PortWharfCodeSelect")
    private Integer portWharfCodeSelect;

    @Column(name = "ChannelCodeSelect")
    private Integer channelCodeSelect;

    @Column(name = "DepartmentCodeSelect")
    private Integer departmentCodeSelect;

    @Column(name = "ShipTypeCodeSelect")
    private Integer shipTypeCodeSelect;

    @Column(name = "FlagStateCodeSelect")
    private Integer flagStateCodeSelect;

    @Column(name = "GrossTonnageSelect")
    private Integer grossTonnageSelect;

    @Column(name = "DeadWeightSelect")
    private Integer deadWeightSelect;

    @Column(name = "CargoSelect")
    private Integer cargoSelect;

    @Column(name = "CargoCategorySelect")
    private Integer cargoCategorySelect;

    @Column(name = "CargoUnloadingSelect")
    private Integer cargoUnloadingSelect;

    @Column(name = "CargoGroupSelect")
    private Integer cargoGroupSelect;

    @Column(name = "ShipAgencyCodeSelect")
    private Integer shipAgencyCodeSelect;

    @Column(name = "ShipOwnerCodeSelect")
    private Integer shipOwnerCodeSelect;

    @Column(name = "PilotCompanyCodeSelect")
    private Integer pilotCompanyCodeSelect;

    @Column(name = "PilotCodeSelect")
    private Integer pilotCodeSelect;

    @Column(name = "TugboatCompanyCodeSelect")
    private Integer tugboatCompanyCodeSelect;

    @Column(name = "TugboatCodeSelect")
    private Integer tugboatCodeSelect;

    @Column(name = "ShipCodeSelect")
    private Integer shipCodeSelect;

    @Column(name = "ShipYardCodeSelect")
    private Integer shipYardCodeSelect;

    @Column(name = "SecurityOfficeCodeSelect")
    private Integer securityOfficeCodeSelect;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

}