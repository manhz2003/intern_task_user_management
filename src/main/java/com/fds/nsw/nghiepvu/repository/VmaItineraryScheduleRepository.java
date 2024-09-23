package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaItineraryScheduleRepository extends JpaRepository<VmaItinerarySchedule, Long> {
}