package com.jpaTst.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpaTst.entity.Member;
import com.jpaTst.service.MemberServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class MemberController {

	private MemberServiceImpl service;
	
	@GetMapping("/{id}")
	public Member getEmployeeById(@PathVariable long id) {
		return this.service.findById(id);
	}
	
	@PostMapping
	public Member saveOrUpdate(@RequestBody Member member) {
		return this.service.saveOrUpdate(member);
	}
}
