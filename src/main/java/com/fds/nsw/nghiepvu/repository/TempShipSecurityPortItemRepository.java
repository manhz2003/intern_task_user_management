package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempShipSecurityPortItemRepository extends JpaRepository<TempShipSecurityPortItems, Long> {
}