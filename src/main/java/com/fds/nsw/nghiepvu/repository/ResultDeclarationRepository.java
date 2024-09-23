package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ResultDeclarationRepository extends JpaRepository<ResultDeclaration, Long> {
}