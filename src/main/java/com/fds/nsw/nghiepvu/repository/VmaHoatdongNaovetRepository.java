package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaHoatdongNaovetRepository extends JpaRepository<VmaHoatDongNaoVet, Long> {
}