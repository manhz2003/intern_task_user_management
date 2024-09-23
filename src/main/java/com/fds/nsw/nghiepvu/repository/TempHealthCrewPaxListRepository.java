package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempHealthCrewPaxListRepository extends JpaRepository<TempHealthCrewPaxList, Long> {
}