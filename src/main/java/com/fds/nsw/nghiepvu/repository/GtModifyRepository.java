package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.Modify;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface GtModifyRepository extends JpaRepository<Modify, Long> {
}