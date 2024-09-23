package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.UsersPermission;
import com.fds.nsw.liferay.model.UsersPermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsersPermissionRepository extends JpaRepository<UsersPermission, UsersPermissionId> {
}