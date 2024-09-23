package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmRouteRepository extends JpaRepository<DmRoute, Integer> {
}