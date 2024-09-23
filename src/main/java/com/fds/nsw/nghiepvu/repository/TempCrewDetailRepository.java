package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempCrewDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempCrewDetailRepository extends JpaRepository<TempCrewDetails, Long> {
}