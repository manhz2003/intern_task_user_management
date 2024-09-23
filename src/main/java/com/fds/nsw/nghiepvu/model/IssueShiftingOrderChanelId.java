package com.fds.nsw.nghiepvu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
public class IssueShiftingOrderChanelId implements Serializable {
    private static final long serialVersionUID = -4671649997913732001L;
    @Column(name = "ShiftingOrderId", nullable = false)
    private Long shiftingOrderId;

    @Column(name = "ChanelCode", nullable = false)
    private String chanelCode;

    public IssueShiftingOrderChanelId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IssueShiftingOrderChanelId entity = (IssueShiftingOrderChanelId) o;
        return Objects.equals(this.chanelCode, entity.chanelCode) &&
                Objects.equals(this.shiftingOrderId, entity.shiftingOrderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chanelCode, shiftingOrderId);
    }

}