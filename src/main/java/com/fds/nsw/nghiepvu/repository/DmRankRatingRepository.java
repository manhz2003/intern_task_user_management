package com.fds.nsw.nghiepvu.repository;

import com.fds.nsw.nghiepvu.model.DmRankRating;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DmRankRatingRepository extends JpaRepository<DmRankRating, Integer> {
}