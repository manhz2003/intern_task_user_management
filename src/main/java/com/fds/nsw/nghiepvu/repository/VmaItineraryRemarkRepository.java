package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaItineraryRemark;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaItineraryRemarkRepository extends JpaRepository<VmaItineraryRemark, Long> {
}