package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r JOIN UsersRole ur ON r.id = ur.id.roleId WHERE ur.id.userId = :userId")
    List<Role> findRolesByUserId(@Param("userId") Long userId);
}