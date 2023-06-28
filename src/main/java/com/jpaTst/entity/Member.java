package com.jpaTst.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="members")
public class Member {
	
	@Id
	private long memberId;
	
	@Column(length = 100)
	private String memberName;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "member-team")
	private Set<TeamMember> teams;
	
	public Set<TeamMember> getTeams() {
		if(this.teams == null)
			this.teams = new HashSet<>();
		return this.teams;
	}
	
	public void removeTeam(TeamMember team) {
		this.teams.remove(team);
	}
}
