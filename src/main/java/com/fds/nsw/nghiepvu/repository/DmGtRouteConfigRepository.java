package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmGtRouteConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmGtRouteConfigRepository extends JpaRepository<DmGtRouteConfig, Long> {
}