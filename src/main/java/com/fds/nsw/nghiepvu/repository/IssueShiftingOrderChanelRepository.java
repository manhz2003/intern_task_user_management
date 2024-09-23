package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanelId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IssueShiftingOrderChanelRepository extends JpaRepository<IssueShiftingOrderChanel, IssueShiftingOrderChanelId> {
}