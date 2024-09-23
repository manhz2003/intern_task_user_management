package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaConversionType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaConversionTypeRepository extends JpaRepository<VmaConversionType, Long> {
}