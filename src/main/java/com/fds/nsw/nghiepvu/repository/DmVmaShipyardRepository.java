package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmVmaShipyard;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmVmaShipyardRepository extends JpaRepository<DmVmaShipyard, Long> {
}