package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.RolesPermission;
import com.fds.nsw.liferay.model.RolesPermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RolesPermissionRepository extends JpaRepository<RolesPermission, RolesPermissionId> {
}