package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Dlfileentrymetadatum;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DlfileentrymetadatumRepository extends JpaRepository<Dlfileentrymetadatum, Long> {
}