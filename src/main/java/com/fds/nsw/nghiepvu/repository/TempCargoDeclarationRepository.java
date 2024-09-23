package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempCargoDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempCargoDeclarationRepository extends JpaRepository<TempCargoDeclaration, Long> {
}