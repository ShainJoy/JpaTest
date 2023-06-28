package com.jpaTst.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpaTst.entity.TeamMember;
import com.jpaTst.entity.TeamMemberId;

public interface TeamMemberRepo extends JpaRepository<TeamMember, TeamMemberId> {

}
