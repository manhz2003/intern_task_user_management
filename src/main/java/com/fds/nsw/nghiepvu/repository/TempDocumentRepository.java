package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempDocumentRepository extends JpaRepository<TempDocument, Long> {


}