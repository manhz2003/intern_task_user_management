package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempCrewList;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempCrewListRepository extends JpaRepository<TempCrewList, Long> {
}