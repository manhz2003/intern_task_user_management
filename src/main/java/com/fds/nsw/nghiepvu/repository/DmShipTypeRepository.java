package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmShipType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmShipTypeRepository extends JpaRepository<DmShipType, Integer> {
}