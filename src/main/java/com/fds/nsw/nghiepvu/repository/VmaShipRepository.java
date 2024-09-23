package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaShip;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaShipRepository extends JpaRepository<VmaShip, Long> {
}