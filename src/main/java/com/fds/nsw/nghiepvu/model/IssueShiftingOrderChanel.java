package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "issue_shifting_order_chanel", schema = "dvc_hanghai_nghiepvu_clone")
public class IssueShiftingOrderChanel {
    @EmbeddedId
    private IssueShiftingOrderChanelId id;

    @Column(name = "chanelName", length = 500)
    private String chanelName;

    @Column(name = "Order_")
    private Integer order;

}