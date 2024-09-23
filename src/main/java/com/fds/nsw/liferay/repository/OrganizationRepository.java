package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Query("SELECT r FROM Organization r JOIN UsersOrg ur ON r.id = ur.id.userId WHERE ur.id.userId = :userId")
    List<Organization> findOrganizationsByUserId(@Param("userId") long userId);

}