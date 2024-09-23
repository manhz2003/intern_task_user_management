package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaItineraryProtest;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaItineraryProtestRepository extends JpaRepository<VmaItineraryProtest, Long> {
}