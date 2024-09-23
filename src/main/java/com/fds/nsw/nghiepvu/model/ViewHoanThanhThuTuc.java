package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "viewhoanthanhthutuc", schema = "dvc_hanghai_nghiepvu_clone")
public class ViewHoanThanhThuTuc {
	
    @Id
    @Column(name = "CVHH", length = 20)
    private String cvhh;

    @Column(name = "MaritimeOrder")
    private Integer MaritimeOrder;

    @Lob
    @Column(name = "NC_KYSO", nullable = false)
    private String NC_KYSO;

    @Lob
    @Column(name = "XC_KYSO", nullable = false)
    private String XC_KYSO;

    @Lob
    @Column(name = "QC_KYSO", nullable = false)
    private String QC_KYSO;

    @Lob
    @Column(name = "VC_KYSO", nullable = false)
    private String VC_KYSO;

    @Lob
    @Column(name = "RC_KYSO", nullable = false)
    private String RC_KYSO;

    @Lob
    @Column(name = "CCV_KYSO", nullable = false)
    private String CCV_KYSO;

    @Lob
    @Column(name = "CCR_KYSO", nullable = false)
    private String CCR_KYSO;

    @Lob
    @Column(name = "VCDK_KYSO", nullable = false)
    private String VCDK_KYSO;

    @Lob
    @Column(name = "RCDK_KYSO", nullable = false)
    private String RCDK_KYSO;

    @Lob
    @Column(name = "NCDK_KYSO", nullable = false)
    private String NCDK_KYSO;

    @Lob
    @Column(name = "XCDK_KYSO", nullable = false)
    private String XCDK_KYSO;

    @Lob
    @Column(name = "NCPTTND_KYSO", nullable = false)
    private String NCPTTND_KYSO;

    @Lob
    @Column(name = "XCPTTND_KYSO", nullable = false)
    private String XCPTTND_KYSO;

    @Lob
    @Column(name = "VCTND_KYSO", nullable = false)
    private String VCTND_KYSO;

    @Lob
    @Column(name = "RCTND_KYSO", nullable = false)
    private String RCTND_KYSO;

    @Lob
    @Column(name = "NC_DUYET", nullable = false)
    private String NC_DUYET;

    @Lob
    @Column(name = "XC_DUYET", nullable = false)
    private String XC_DUYET;

    @Lob
    @Column(name = "QC_DUYET", nullable = false)
    private String QC_DUYET;

    @Lob
    @Column(name = "VC_DUYET", nullable = false)
    private String VC_DUYET;

    @Lob
    @Column(name = "RC_DUYET", nullable = false)
    private String RC_DUYET;

    @Lob
    @Column(name = "CCV_DUYET", nullable = false)
    private String CCV_DUYET;

    @Lob
    @Column(name = "CCR_DUYET", nullable = false)
    private String CCR_DUYET;

    @Lob
    @Column(name = "VCDK_DUYET", nullable = false)
    private String VCDK_DUYET;

    @Lob
    @Column(name = "RCDK_DUYET", nullable = false)
    private String RCDK_DUYET;

    @Lob
    @Column(name = "NCDK_DUYET", nullable = false)
    private String NCDK_DUYET;

    @Lob
    @Column(name = "XCDK_DUYET", nullable = false)
    private String XCDK_DUYET;

    @Lob
    @Column(name = "NCPTTND_DUYET", nullable = false)
    private String NCPTTND_DUYET;

    @Lob
    @Column(name = "XCPTTND_DUYET", nullable = false)
    private String XCPTTND_DUYET;

    @Lob
    @Column(name = "VCTND_DUYET", nullable = false)
    private String VCTND_DUYET;

    @Lob
    @Column(name = "RCTND_DUYET", nullable = false)
    private String RCTND_DUYET;

}