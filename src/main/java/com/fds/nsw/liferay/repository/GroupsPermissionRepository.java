package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.GroupsPermission;
import com.fds.nsw.liferay.model.GroupsPermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface GroupsPermissionRepository extends JpaRepository<GroupsPermission, GroupsPermissionId> {
}