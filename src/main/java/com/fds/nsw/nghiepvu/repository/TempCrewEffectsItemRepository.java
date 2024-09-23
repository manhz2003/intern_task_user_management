package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.TempCrewEffectsItems;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TempCrewEffectsItemRepository extends JpaRepository<TempCrewEffectsItems, Long> {
}