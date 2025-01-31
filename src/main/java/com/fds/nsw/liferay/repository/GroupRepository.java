package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}