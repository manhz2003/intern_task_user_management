package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmState;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmStateRepository extends JpaRepository<DmState, Integer> {
}