package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmVmaTugboat;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmVmaTugboatRepository extends JpaRepository<DmVmaTugboat, Long> {
}