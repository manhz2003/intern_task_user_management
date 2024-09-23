package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Usergrouprole;
import com.fds.nsw.liferay.model.UsergrouproleId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsergrouproleRepository extends JpaRepository<Usergrouprole, UsergrouproleId> {
}