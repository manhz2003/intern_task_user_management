package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Usergroup;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsergroupRepository extends JpaRepository<Usergroup, Long> {
}