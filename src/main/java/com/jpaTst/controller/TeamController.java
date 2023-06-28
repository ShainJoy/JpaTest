package com.jpaTst.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpaTst.entity.Team;
import com.jpaTst.service.TeamServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/teams")
@AllArgsConstructor
public class TeamController {

	private TeamServiceImpl service;
	
	@GetMapping("/{id}")
	public Team getTeamById(@PathVariable long id) {
		return this.service.findById(id);
	}
	
	@PostMapping
	public Team save(@RequestBody Team team) {
		return this.service.saveOrUpdate(team);
	}
}
