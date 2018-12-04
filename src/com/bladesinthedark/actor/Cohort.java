package com.bladesinthedark.actor;

public class Cohort {
	private Rogue rogue;
	private CohortType type;

	public Cohort(Rogue rogue, CohortType type) {
		this.rogue = rogue;
		this.type = type;
	}

	public boolean isExpert() {
		return (type.equals(CohortType.EXPERT));
	}

	public boolean isGang() {
		return (type.equals(CohortType.EXPERT) != true);
	}
}