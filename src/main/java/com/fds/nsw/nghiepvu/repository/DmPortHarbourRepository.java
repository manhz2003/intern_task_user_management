package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmPortHarbourRepository extends JpaRepository<DmPortHarbour, Integer> {
}