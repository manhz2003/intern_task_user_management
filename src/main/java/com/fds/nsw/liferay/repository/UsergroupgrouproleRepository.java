package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Usergroupgrouprole;
import com.fds.nsw.liferay.model.UsergroupgrouproleId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsergroupgrouproleRepository extends JpaRepository<Usergroupgrouprole, UsergroupgrouproleId> {
}