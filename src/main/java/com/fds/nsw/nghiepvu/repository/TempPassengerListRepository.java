package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempPassengerList;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempPassengerListRepository extends JpaRepository<TempPassengerList, Long> {
}