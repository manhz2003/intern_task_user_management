package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmPortRegion;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmPortRegionRepository extends JpaRepository<DmPortRegion, Integer> {
}