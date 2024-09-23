package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Dlsync;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DlsyncRepository extends JpaRepository<Dlsync, Long> {
}