package com.jpaTst.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpaTst.entity.Team;

public interface TeamRepo extends JpaRepository<Team, Long> {
	
}
