package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Useridmapper;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UseridmapperRepository extends JpaRepository<Useridmapper, Long> {
}