package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Dlfileshortcut;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DlfileshortcutRepository extends JpaRepository<Dlfileshortcut, Long> {
}