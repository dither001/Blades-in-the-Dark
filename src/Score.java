import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Score implements Plan {
	public enum Act {
		INCITING, RISING, TURNING, FALLING, RELEASE
	}

	public enum Entanglement {
		ARREST, COOPERATION, DEMONIC_NOTICE, FLIPPED, GANG_TROUBLE, INTERROGATION, QUESTIONING, REPRISALS, RIVALS, SHOW_OF_FORCE, UNQUIET_DEAD, USUAL_SUSPECTS
	}

	public enum Diversion {
		ACQUIRE_ASSET, LONG_TERM_PROJECT, RECOVER, REDUCE_HEAT, INDULGE_VICE
	}

	// static fields
	private static final int ACTIONS_PER_SCORE = 20;
	public static final Act[] ACTS = { Act.INCITING, Act.RISING, Act.TURNING, Act.FALLING, Act.RELEASE };

	// fields
	private Faction crew;
	private ArrayList<Rogue> team;

	private Faction client;
	private Faction target;
	private Goal goal;
	private Approach plan;
	private Activity activity;

	//
	Faction.Claim claim;

	//
	private Clock window;
	private Clock openingMove;
	private Clock primaryObjective;
	private Clock getawayMove;

	//
	private Act act;
	private int scene;
	private int tension;
	private Actor.Rating[][] beats;

	private ArrayList<Action> actions;

	// constructors
	public Score(Faction crew, List<Rogue> team, Quest quest) {
		/*
		 * TODO - I need to figure out (at some point) how to differentiate between a
		 * job given by another crew and a job given by a single patron
		 */
		this.crew = crew;
		this.team = new ArrayList<Rogue>(team);

		this.client = quest.getClient();
		this.target = quest.getTarget();
		this.goal = quest.getGoal();
		this.plan = Plan.randomPlan();
		this.activity = Plan.randomActivity(crew.crewType());

		// if (goal.equals(Goal.CLAIM)) {
		// Faction.Claim candidate;
		//
		// if (Dice.roll(3) == 1) {
		// candidate = Faction.turfClaim(crew);
		// } else {
		// candidate = Faction.randomClaimByCrew(crew.crewType());
		// }
		//
		// while (crew.getClaims().containsKey(candidate)) {
		// candidate = Faction.randomClaimByCrew(crew.crewType());
		// }
		// this.claim = candidate;
		// }

		//
		this.window = new Clock(Clock.Length.FOUR);
		this.openingMove = new Clock(Clock.Length.FOUR);
		this.primaryObjective = new Clock();
		this.getawayMove = new Clock();

		//
		this.act = Act.INCITING;
		this.scene = 1;
		this.tension = 0;
		this.beats = Rogue.randomBeats();

		//
		this.actions = new ArrayList<Action>();
	}

	// methods
	public void action(int dice) {
		while (unresolved() && teamRemaining() > 0) {
			if (scene == 1) {
				// every mission begins with engagement roll
				Action.Position start = engagementRoll(dice);
				if (openingMove.expired()) {
					actions.add(new Action(this, primaryObjective, start));

				} else {
					actions.add(new Action(this, openingMove, start));

				}
			} else {
				// TODO - DEBUG
				// objectiveCheck();
				if (getawayMove.expired()) {
					/*
					 * FIXME - currently here for debug only; replace with "end score"
					 */
					// actions.add(new Action(getawayMove, randomPosition()));
					System.out.println("Clean getaway.");
					break;

				} else if (primaryObjective.expired()) {
					actions.add(new Action(this, getawayMove, Action.randomPosition()));

				} else if (openingMove.expired()) {
					actions.add(new Action(this, primaryObjective, Action.randomPosition()));

				} else {
					actions.add(new Action(this, openingMove, Action.randomPosition()));

				}
			}

			// while mission not resolved, advance()
			moraleCheck();
			if (window.expired() != true) {
				int[] results = Dice.fortune(++scene - ACTIONS_PER_SCORE + tension);
				if (results[3] > 0 || results[4] > 0 || results[5] > 0) {
					window.countDown();
					// while I still use the act structure...
					act = nextAct();
				}
			}
		}

		if (window.expired()) {
			System.out.println(" " + " " + " Window closed.");
			if (primaryObjective.expired() != true)
				System.out.println(" " + " " + " Primary objective failed.");
		}

		// TODO
		System.out.println();
		new Downtime(this);
	}

	public boolean advance() {
		boolean advance = false;

		if (window.expired() != true) {
			int[] results = Dice.fortune(++scene - ACTIONS_PER_SCORE + tension);
			if (results[3] > 0 || results[4] > 0 || results[5] > 0) {
				window.countDown();
				advance = true;
				// while I still use the act structure...
				act = nextAct();
			}
		}

		// if (act.equals(Act.RELEASE) != true) {
		// int[] results = Dice.fortune(++scene - ACTIONS_PER_SCORE + tension);
		// // System.out.println(String.format("Scene: %2d", scene));
		//
		// if (results[3] > 0 || results[4] > 0 || results[5] > 0) {
		// act = nextAct();
		// advance = true;
		// System.out.println(act.toString());
		// }
		// }

		return advance;
	}

	public int getTension() {
		return tension;
	}

	public Faction getCrew() {
		return crew;
	}

	public Approach getPlan() {
		return plan;
	}

	public Actor.Rating[][] getBeats() {
		return beats;
	}

	public List<Rogue> getTeam() {
		return team;
	}

	public Action.Position engagementRoll(int dice) {
		Action.Position position;
		int[] results = Dice.fortune(Dice.roll(dice));

		// TODO - testing
		System.out.println("Engagement roll.");
		if (results[5] > 1) {
			// critical result clears the opening
			position = Action.Position.CONTROLLED;
			openingMove.clear();

		} else if (results[5] > 0) {
			// success results in controlled situation
			position = Action.Position.CONTROLLED;

		} else if (results[3] > 0 || results[4] > 0) {
			// partial results in risky position
			position = Action.Position.RISKY;

		} else {
			// failure results in desperate situation
			position = Action.Position.DESPERATE;

		}

		// System.out.println(position);
		return position;
	}

	public Act getAct() {
		return act;
	}

	public int getScene() {
		return scene;
	}

	public Act nextAct() {
		Act nextAct = Act.INCITING;

		if (act.equals(Act.INCITING))
			nextAct = Act.RISING;
		else if (act.equals(Act.RISING))
			nextAct = Act.TURNING;
		else if (act.equals(Act.TURNING))
			nextAct = Act.FALLING;
		else if (act.equals(Act.FALLING))
			nextAct = Act.RELEASE;

		return nextAct;
	}

	public boolean patronage() {
		return (crew.equals(client) != true);
	}

	public boolean unresolved() {
		// return (act.equals(Act.RELEASE) != true);
		return (window.expired() != true);
	}

	public void objectiveCheck() {
		if (getawayMove.expired()) {
			System.out.println(" " + " " + " We're clear.");

		} else if (primaryObjective.expired()) {
			System.out.println(" " + " " + " Primary objective complete.");

		} else if (openingMove.expired()) {
			System.out.println(" " + " " + " We're in.");

		} else {
			System.out.println(" " + " " + " Approaching objective.");

		}
	}

	public boolean moraleCheck() {
		boolean morale = false;
		int moraleCheck = 0, penalty = 0;
		int tpk = 0;
		Rogue rogue;
		for (Iterator<Rogue> it = team.iterator(); it.hasNext();) {
			rogue = it.next();
			if (rogue.stressedOut()) {
				penalty += 4;
				++tpk;
			}
		}

		// base chance to abort mission is 5%
		moraleCheck = Dice.roll(20) + penalty;
		System.out.println("Morale check: " + moraleCheck + " (" + penalty + ")");
		boolean abort = (tpk == team.size()) ? true : (moraleCheck > 20) ? true : false;
		if (primaryObjective.expired() != true && abort)
			window.clear();

		return morale;
	}

	public int teamRemaining() {
		int remaining = 0;
		for (Iterator<Rogue> it = team.iterator(); it.hasNext();) {
			remaining += (it.next().goodToGo()) ? 1 : 0;
		}

		return remaining;
	}

	@Override
	public String toString() {
		String string = String.format("name %s [%s] %s", plan, Plan.detail(plan), activity);
		string += "\nTarget: " + target.toString();

		return string;
	}

	public String toStringDetailed() {
		String string = String.format("name %s [%s] %s || Team size: %d %n%s %nTarget: %s", plan, Plan.detail(plan),
				activity, team.size(), team.toString(), target.toString());

		return string;
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	public static Act randomAct() {
		return Dice.randomFromArray(ACTS);
	}

	public static Rogue randomTeamMember(List<Rogue> list) {
		return Dice.randomFromList(list);
	}

	public static void entanglement(int heat, int wantedLevel) {
		int[] entangle = Dice.fortune(wantedLevel);
		Entanglement encounter;
		if (heat < 4 && entangle[5] > 0) {
			// heat 0-3 && roll 6
			encounter = Entanglement.COOPERATION;

		} else if (heat < 4 && (entangle[3] > 0 || entangle[4] > 0)) {
			// heat 0-3 && roll 4-5
			encounter = (Dice.roll(2) == 1) ? Entanglement.RIVALS : Entanglement.UNQUIET_DEAD;

		} else if (heat < 4) {
			// heat 0-3 && roll 1-3
			encounter = (Dice.roll(2) == 1) ? Entanglement.GANG_TROUBLE : Entanglement.USUAL_SUSPECTS;

		} else if (heat < 6 && entangle[5] > 0) {
			// heat 4-5 && roll 6
			encounter = Entanglement.SHOW_OF_FORCE;

		} else if (heat < 6 && (entangle[3] > 0 || entangle[4] > 0)) {
			// heat 4-5 && roll 4-5
			encounter = (Dice.roll(2) == 1) ? Entanglement.REPRISALS : Entanglement.UNQUIET_DEAD;

		} else if (heat < 6) {
			// heat 4-5 && roll 1-3
			encounter = (Dice.roll(2) == 1) ? Entanglement.GANG_TROUBLE : Entanglement.QUESTIONING;

		} else if (entangle[5] > 0) {
			// heat 6+ && roll 6
			encounter = Entanglement.ARREST;

		} else if (entangle[3] > 0 || entangle[4] > 0) {
			// heat 6+ && roll 4-5
			encounter = (Dice.roll(2) == 1) ? Entanglement.DEMONIC_NOTICE : Entanglement.SHOW_OF_FORCE;

		} else {
			// heat 6+ && roll 1-3
			encounter = (Dice.roll(2) == 1) ? Entanglement.FLIPPED : Entanglement.INTERROGATION;

		}

		// TODO - testing
		System.out.println();
		System.out.println("Current heat: " + heat + " || Wanted Level: " + wantedLevel);
		System.out.println(encounter + " entanglement");

		switch (encounter) {
		case ARREST:
			System.out.println("Bluecoats arrive to arrest you.");
			break;
		case COOPERATION:
			System.out.println("A close ally asks you for a favor.");
			break;
		case DEMONIC_NOTICE:
			System.out.println("A demon approaches you with a dark offer.");
			break;
		case FLIPPED:
			System.out.println("A rival has flipped one of your assets.");
			break;
		case GANG_TROUBLE:
			System.out.println("One of your gangs has caused trouble.");
			break;
		case INTERROGATION:
			System.out.println("The Bluecoats question one of the PCs.");
			break;
		case QUESTIONING:
			System.out.println("The Bluecoats round up one of your cohorts.");
			break;
		case REPRISALS:
			System.out.println("An enemy moves against your crew.");
			break;
		case RIVALS:
			System.out.println("A rival throws their weight around.");
			break;
		case SHOW_OF_FORCE:
			System.out.println("An enemy faction makes a play against you.");
			break;
		case UNQUIET_DEAD:
			System.out.println("A rogue spirit haunts your crew.");
			break;
		case USUAL_SUSPECTS:
			System.out.println("The Bluecoats grab a friend of a PC");
			break;
		}

	}

	/*
	 * DOWNTIME - INNER CLASS
	 */
	private class Downtime {
		int heat;

		Faction crew;

		public Downtime(Score score) {
			this.crew = score.crew;
			ArrayList<Rogue> team = score.team;

			//
			Faction client = score.client;
			Faction target = score.target;
			Goal goal = score.goal;

			// starting heat
			this.heat = Dice.roll(4) + Dice.roll(target.getLevel() / 4) - 2;

			// if (primaryObjective.expired() && goal.equals(Goal.ASSIST)) {
			// String hold = (client.holdStrong()) ? "strong" : "weak";
			// String report = String.format("%s (tier %d, hold %s) is stronger.", client,
			// client.getTier(), hold);
			// System.out.println(report);
			// client.strengthenHold();
			//
			// hold = (client.holdStrong()) ? "strong" : "weak";
			// System.out.println("New tier/hold: " + client.getTier() + " / " + hold);
			// System.out.println();
			// }

			// if (primaryObjective.expired() && goal.equals(Goal.CLAIM)) {
			// String report = String.format("Seized %s from %s", claim, target);
			// System.out.println(report);
			// crew.getClaims().put(claim, target);
			//
			// changes.add(target);
			// crew.decreaseShip(target);
			// System.out.println(target + " status decreased");
			// System.out.println();
			// } else if (goal.equals(Goal.CLAIM)) {
			// target.getClaims().put(claim, target);
			// }

			if (primaryObjective.expired() && goal.equals(Goal.SHAKE)) {
				int level = target.getLevel();
				String report = String.format("%s (level %d) has been weakened.", target, target.getLevel());
				System.out.println(report);

				target.setLevel(level - 1);
				System.out.println("New level: " + target.getLevel());
				System.out.println();
			}

			//
			Ship ship = Ship.get(crew, client);
			int trust;

			// resolve client status
			if (patronage() && primaryObjective.expired()) {
				if (ship != null) {
					trust = ship.getScore();
					ship.setScore(trust + 1);

				} else {
					trust = 1;
					ship = new Ship(crew, client, trust);

				}

				// TODO - testing
				System.out.println("Client (" + client.toString() + ") satisfied");

			} else if (patronage() && window.expired()) {
				if (ship != null) {
					trust = ship.getScore();
					ship.setScore(trust - 1);

				} else {
					trust = -1;
					ship = new Ship(crew, client, trust);

				}

				// TODO - testing
				System.out.println("Client (" + client.toString() + ") disappointed");

			}

			if (patronage() && window.expired()) {
				ship = Ship.get(client, target);
				if (ship != null) {
					trust = ship.getScore();
					ship.setScore(trust - 1);

				} else {
					trust = -1;
					ship = new Ship(client, target, trust);

				}

				// TODO - testing (appears to work)
				System.out.println("Relations worsened between " + target + " and " + client);
			}

			// additional reputation changes
			Set<Faction> enemies = target.enemies();
			Faction current;
			for (Iterator<Faction> it = enemies.iterator(); it.hasNext();) {
				current = it.next();

				if (current.equals(crew) != true && Dice.roll(3) == 1) {
					// enemies of the target like you
					ship = Ship.get(crew, current);

					if (ship != null) {
						trust = ship.getScore();
						ship.setScore(trust + 1);

					} else {
						trust = 1;
						ship = new Ship(crew, client, trust);

					}

					System.out.println(current + " status increased");
				}
			}

			Set<Faction> allies = target.allies();
			for (Iterator<Faction> it = allies.iterator(); it.hasNext();) {
				current = it.next();

				if (current.equals(crew) != true && Dice.roll(3) == 1) {
					// allies of the target don't like you
					ship = Ship.get(crew, current);

					if (ship != null) {
						trust = ship.getScore();
						ship.setScore(trust - 1);

					} else {
						trust = -1;
						ship = new Ship(crew, client, trust);

					}

					System.out.println(current + " status decreased");
				}
			}

			// rep boost
			int crewTier = crew.getLevel() / 2;
			int targetTier = target.getLevel() / 2;
			int exp = (targetTier - crewTier + 2 < 1) ? 0 : targetTier - crewTier + 2;
			if (exp > 0) {
				ship = Ship.get(crew, target);

				if (ship != null) {
					trust = ship.getScore();
					ship.setScore(trust - 1);

				} else {
					trust = -1;
					ship = new Ship(crew, client, trust);

				}

				System.out.println(target + " status decreased");

				//
				int crewEXP = crew.getExperience();
				crew.setExperience(crewEXP + exp);
				System.out.println("Rep gained: " + exp);
			}

			// payoff
			if (score.primaryObjective.expired()) {
				int personalCoin, personalStash, bonus;
				int crewCoin = crew.getCoin(), teamSize = team.size();
				int payoff = (patronage()) ? Dice.roll(2, 4) + 2 : Dice.roll(2, 6);

				// TODO - testing
				System.out.println();
				System.out.println("Coin gained: " + payoff);

				// determine individual pay (or none)
				if (payoff >= teamSize * 3)
					bonus = 3;
				else if (payoff >= teamSize * 2)
					bonus = 2;
				else if (payoff >= teamSize)
					bonus = 1;
				else
					bonus = 0;

				// distribute pay among team members
				if (bonus > 0) {
					Rogue rogue;
					for (Iterator<Rogue> it = team.iterator(); it.hasNext();) {
						rogue = it.next();

						personalCoin = rogue.getCoin();
						personalStash = rogue.getStash();
						if (personalCoin + bonus <= 4) {
							rogue.setCoin(personalCoin + bonus);
							payoff -= bonus;

						} else {
							rogue.setStash(personalStash + bonus);
							payoff -= bonus;

						}

						System.out.println(rogue + " received " + bonus);
					}

					// any remainder goes to the crew
					crew.setCoin(crewCoin + payoff);
					System.out.println(payoff + " went to the crew.");

				} else if (crewCoin + payoff >= teamSize) {
					int difference = teamSize - payoff;
					crew.setCoin(crewCoin - difference);
					bonus = 1;

					Rogue rogue;
					for (Iterator<Rogue> it = team.iterator(); it.hasNext();) {
						rogue = it.next();

						personalCoin = rogue.getCoin();
						personalStash = rogue.getStash();
						if (personalCoin + bonus <= 4) {
							rogue.setCoin(personalCoin + bonus);
							payoff -= bonus;

						} else {
							rogue.setStash(personalStash + bonus);
							payoff -= bonus;

						}

						// System.out.println(rogue + " received " + bonus);
					}
				} else {
					// not enough coin to distribute; all of it goes to the crew
					crew.setCoin(crewCoin + payoff);

				}
			}

			// post-payoff score resolution
			if (team.size() > 0) {
				Rogue rogue;
				for (Iterator<Rogue> it = team.iterator(); it.hasNext();) {
					rogue = it.next();
					rogue.resolveStress();
				}
			}

			// assign heat
			// System.out.println("Received " + heat + " heat from score.");
			// crew.setHeat(crew.getHeat() + heat);
			// crew.resolveHeat();

			// entanglements
			// int heat = crew.getHeat();
			// int wantedLevel = crew.getWantedLevel();
			// entanglement(heat, wantedLevel);

		}

	}
}
