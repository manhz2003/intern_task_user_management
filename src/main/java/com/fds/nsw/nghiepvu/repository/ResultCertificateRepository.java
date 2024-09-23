package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.ResultCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ResultCertificateRepository extends JpaRepository<ResultCertificate, Long> {
}