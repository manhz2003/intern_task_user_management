package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IssueShiftingOrderRepository extends JpaRepository<IssueShiftingOrder, Long> {
}