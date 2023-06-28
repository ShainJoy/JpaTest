package com.jpaTst.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpaTst.entity.Member;
import com.jpaTst.repo.MemberRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl {

	private MemberRepo repo;

	public Member saveOrUpdate(Member member) {
		return this.repo.save(member);
	}

	public boolean delete(long id) {
		if(this.findById(id) != null) {
			this.repo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public List<Member> allMembers() {
		return this.repo.findAll();
	}

	public Member findById(long id) {
		return this.repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No matching found!"));
	}
	
	public Member getMemberByIdOrNull(long id) {
		try {
			return this.findById(id);
		} catch (Exception err){
			return null;
		}
	}
}
