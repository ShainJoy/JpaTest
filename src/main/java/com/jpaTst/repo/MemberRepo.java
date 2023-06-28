package com.jpaTst.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpaTst.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Long> {

}
