package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Assettag;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AssettagRepository extends JpaRepository<Assettag, Long> {
}