package com.jpaTst.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = {"member", "team"})
@EqualsAndHashCode(exclude = {"member", "team"})
@Table(name="team_member")
@IdClass(TeamMemberId.class)
public class TeamMember {

	@Id
	private long memberId;
	
	@Id
	private long teamId;
	
	@Column(name="member_role")
	private MemberRole role;
	
	@ManyToOne
	@JoinColumn(
			name="memberId",
			updatable = false, insertable = false
			)
	@JsonBackReference(value = "member-team")
	private Member member;
	
	@ManyToOne
	@JoinColumn(
			name="teamId",
			updatable = false, insertable = false
			)
	@JsonBackReference(value = "team-member")
	private Team team;
}
