package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmDocType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmDocTypeRepository extends JpaRepository<DmDocType, Integer> {
}