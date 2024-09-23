package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Dlcontent;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DlcontentRepository extends JpaRepository<Dlcontent, Long> {
}