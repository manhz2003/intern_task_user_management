package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaItinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaItineraryRepository extends JpaRepository<VmaItinerary, Long> {
}