package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmVmaTugboatCompanyRepository extends JpaRepository<DmVmaTugboatCompany, Long> {
}