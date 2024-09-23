package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmGtFunctionType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmGtFunctionTypeRepository extends JpaRepository<DmGtFunctionType, Long> {
}