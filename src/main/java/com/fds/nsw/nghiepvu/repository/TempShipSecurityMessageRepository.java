package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempShipSecurityMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempShipSecurityMessageRepository extends JpaRepository<TempShipSecurityMessage, Long> {
}