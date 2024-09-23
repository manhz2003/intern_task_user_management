package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmGtBusinessType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmGtBusinessTypeRepository extends JpaRepository<DmGtBusinessType, Long> {
}