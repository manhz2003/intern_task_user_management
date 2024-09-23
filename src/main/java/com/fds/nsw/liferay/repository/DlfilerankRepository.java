package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Dlfilerank;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DlfilerankRepository extends JpaRepository<Dlfilerank, Long> {
}