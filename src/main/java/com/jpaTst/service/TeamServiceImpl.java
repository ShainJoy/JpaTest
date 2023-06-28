package com.jpaTst.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.jpaTst.entity.Member;
import com.jpaTst.entity.Team;
import com.jpaTst.entity.TeamMember;
import com.jpaTst.repo.TeamRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeamServiceImpl {
	
	private TeamRepo repo;
	private MemberServiceImpl memberServ;
	
	public Team saveOrUpdate(Team team) {
		if(team.getTeamId() > 0) {
			return updateTeam(team);
		}
		return saveNewTeam(team);
	}

	public Team findById(long id) {
		return this.repo
				.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No matching found!"));
	}
	
	private Team getTeamByIdOrNull(long id) {
		try {
			return this.findById(id);
		} catch(Exception err) {
			return null;
		}
	}

	private Team updateTeam(Team team) {
		Team existingTeam = this.getTeamByIdOrNull(team.getTeamId());
		if(existingTeam == null)
			return null;
		existingTeam.setTeamName(team.getTeamName());
//		adding new members
		for(TeamMember teamMember : team.getMembers()) {
			if(!isMemberAvailable(existingTeam.getMembers(), teamMember)) {
				existingTeam.addMember(
						this.memberServ.getMemberByIdOrNull(teamMember.getMemberId()), teamMember.getRole());
			}
		}
//		removing extra members
		Set<TeamMember> removables = new HashSet<>();
		for(TeamMember teamMember : existingTeam.getMembers()) {
			if(!isMemberAvailable(team.getMembers(), teamMember))
				removables.add(teamMember);
		}
		Member temp = null;
		for(TeamMember teamMember : removables) {
			temp = teamMember.getMember();
			temp.removeTeam(teamMember);
			existingTeam.removeMember(teamMember);
		}
		return this.repo.save(existingTeam);
	}
	
	private boolean isMemberAvailable(Set<TeamMember> team, TeamMember member) {
		for(TeamMember teamMember : team) {
			if(teamMember.getMemberId() == member.getMemberId())
				return true;
		}
		return false;
	}

	private Team saveNewTeam(Team team) {
//		with the passed in data of team, creating a new team and saving it.
//		to persist its members, the same is passed on to 'updateTeam' method.
		Team newTeam = new Team();
		newTeam.setTeamName(team.getTeamName());
		newTeam = this.repo.saveAndFlush(newTeam);
		
		team.setTeamId(newTeam.getTeamId());
		return this.updateTeam(team);
	}
}
