package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempDangerousGoodsManifestRepository extends JpaRepository<TempDangerousGoodsManifest, Long> {
}