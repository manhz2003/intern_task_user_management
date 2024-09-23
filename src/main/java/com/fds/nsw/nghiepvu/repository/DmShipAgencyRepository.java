package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmShipAgency;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmShipAgencyRepository extends JpaRepository<DmShipAgency, Integer> {
}