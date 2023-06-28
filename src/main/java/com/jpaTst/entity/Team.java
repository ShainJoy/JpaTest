package com.jpaTst.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Teams")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long teamId;
	
	private String teamName;
	
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "team-member")
	private Set<TeamMember> members;
	
	public Set<TeamMember> getMembers(){
		if(this.members == null)
			this.members = new HashSet<>();
		return this.members;
	}
	
	public void addMember(Member member, MemberRole role) {
		TeamMember teamMember = new TeamMember();
		teamMember.setMemberId(member.getMemberId());
		teamMember.setMember(member);
		teamMember.setTeam(this);
		teamMember.setTeamId(this.getTeamId());
		teamMember.setRole(role);
		if(this.members == null)
			this.members = new HashSet<>();
		this.members.add(teamMember);
		member.getTeams().add(teamMember);
	}
	
	public void removeMember(TeamMember members) {
		this.members.remove(members);
	}
}
