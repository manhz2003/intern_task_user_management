package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.UsersUsergroup;
import com.fds.nsw.liferay.model.UsersUsergroupId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsersUsergroupRepository extends JpaRepository<UsersUsergroup, UsersUsergroupId> {
}