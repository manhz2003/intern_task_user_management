package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaScheduleLaunching;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaScheduleLaunchingRepository extends JpaRepository<VmaScheduleLaunching, Long> {
}