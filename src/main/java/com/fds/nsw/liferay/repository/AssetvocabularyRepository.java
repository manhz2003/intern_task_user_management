package com.fds.nsw.liferay.repository;

import com.fds.nsw.liferay.model.Assetvocabulary;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AssetvocabularyRepository extends JpaRepository<Assetvocabulary, Long> {
}