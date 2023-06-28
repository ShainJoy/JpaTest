package com.jpaTst.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TeamMemberId implements Serializable {

	private static final long serialVersionUID = 101L;
	
	private long memberId;
	
	private long teamId;
	
	public int hashCode() {
		return (int) (memberId + teamId);
	}

	public boolean equals(Object object) {
		if (object instanceof TeamMemberId) {
			TeamMemberId otherId = (TeamMemberId) object;
			return (otherId.memberId == this.memberId) && (otherId.teamId == this.teamId);
		}
		return false;
	}
}
