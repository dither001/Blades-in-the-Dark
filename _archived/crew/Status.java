package com.bladesinthedark.crew;

public class Status {
	private Faction owner;
	private int memberID;

	//
	private boolean active;
	private boolean busy;

	//
	private int rank;
	private int cooldown;
	private int fame;
	private int infamy;

	// constructors
	public Status(Faction owner) {
		this(true, owner);
	}

	public Status(boolean active, Faction owner) {
		this.memberID = 0;
		this.owner = owner;

		//
		this.active = active;

		//
		this.rank = 1;
		this.cooldown = 0;
		this.fame = 0;
		this.infamy = 0;
	}

	// methods
	@Override
	public String toString() {
		String string;
		//
		String name = owner.toString();
		String active = (this.active) ? "(active)" : "(inactive)";

		string = String.format("%s %s || Age: %d", name, active, memberID);
		return string;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public boolean active() {
		return active;
	}

	public boolean inactive() {
		return active != true;
	}

	public void activate() {
		this.active = true;
	}

	public void deactivate() {
		this.active = false;
	}

	public boolean available() {
		return busy != true;
	}

	public void makeBusy() {
		this.busy = true;
	}

	public void release() {
		this.busy = false;
	}

	public int getCooldown() {
		// turnsSinceLastAction / ((lowestRank - rank > 0) ? lowestRank - rank : 1)
		return cooldown / rank;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
}