package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempPassengerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempPassengerDetailRepository extends JpaRepository<TempPassengerDetails, Long> {
}