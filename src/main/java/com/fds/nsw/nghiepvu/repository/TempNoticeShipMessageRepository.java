package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempNoticeShipMessageRepository extends JpaRepository<TempNoticeShipMessage, Long> {
}