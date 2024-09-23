package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VmaAnninhCangbienRepository extends JpaRepository<VmaAnNinhCangBien, Long> {
}