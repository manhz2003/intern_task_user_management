package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempShipStoresItems;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempShipStoresItemRepository extends JpaRepository<TempShipStoresItems, Long> {
}