package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.UsersGroup;
import com.fds.nsw.liferay.model.UsersGroupId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsersGroupRepository extends JpaRepository<UsersGroup, UsersGroupId> {
}