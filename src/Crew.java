
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Crew implements Faction {
	public enum District {
		BARROWCLEFT, BRIGHTSTONE, CHARHOLLOW, CHARTERHALL, COALRIDGE, CROWS_FOOT, THE_DOCKS, DUNSLOUGH, NIGHTMARKET, SILKSHORE, SIX_TOWERS, WHITECROWN
	}

	public enum Estate {
		UNDERWORLD, INSTITUTION, LABOR_TRADE, CITIZENRY, THE_FRINGE
	}

	/*
	 * STATIC FIELDS
	 * 
	 */
	private static final int MAX_HEAT = 9;

	//
	public static final District[] ALL_DISTRICTS = { District.BARROWCLEFT, District.BRIGHTSTONE, District.CHARHOLLOW,
			District.CHARTERHALL, District.COALRIDGE, District.CROWS_FOOT, District.THE_DOCKS, District.DUNSLOUGH,
			District.NIGHTMARKET, District.SILKSHORE, District.SIX_TOWERS, District.WHITECROWN };

	//
	private static ArrayList<Crew> factions;

	// initialization
	static {
		factions = new ArrayList<Crew>();
		factions.add(new Crew(NamedFaction.BARROWCLEFT, 2, true));
		factions.add(new Crew(NamedFaction.BILLHOOKS, 2, false));
		factions.add(new Crew(NamedFaction.BLUECOATS, 3, true));
		factions.add(new Crew(NamedFaction.BRIGADE, 2, true));
		factions.add(new Crew(NamedFaction.BRIGHTSTONE, 4, true));
		factions.add(new Crew(NamedFaction.CABBIES, 2, false));
		factions.add(new Crew(NamedFaction.CHARHOLLOW, 1, true));
		factions.add(new Crew(NamedFaction.CHARTERHALL, 4, true));
		factions.add(new Crew(NamedFaction.CHURCH_OF_ECSTASY, 4, true));
		factions.add(new Crew(NamedFaction.CIRCLE_OF_FLAME, 3, true));
		factions.add(new Crew(NamedFaction.CITY_COUNCIL, 5, true));
		factions.add(new Crew(NamedFaction.COALRIDGE, 2, false));
		factions.add(new Crew(NamedFaction.CROWS, 2, false));
		factions.add(new Crew(NamedFaction.CROWS_FOOT, 2, true));
		factions.add(new Crew(NamedFaction.CYPHERS, 2, true));
		factions.add(new Crew(NamedFaction.DAGGER_ISLES_CONSULATE, 1, true));
		factions.add(new Crew(NamedFaction.DEATHLANDS_SCAVENGERS, 2, false));
		factions.add(new Crew(NamedFaction.DIMMER_SISTERS, 2, true));
		factions.add(new Crew(NamedFaction.DOCKERS, 3, true));
		factions.add(new Crew(NamedFaction.DOCKS, 2, true));
		factions.add(new Crew(NamedFaction.DUNSLOUGH, 1, false));
		factions.add(new Crew(NamedFaction.FOG_HOUNDS, 1, false));
		factions.add(new Crew(NamedFaction.FORGOTTEN_GODS, 3, false));
		factions.add(new Crew(NamedFaction.FOUNDATION, 4, true));
		factions.add(new Crew(NamedFaction.GONDOLIERS, 3, true));
		factions.add(new Crew(NamedFaction.GRAY_CLOAKS, 2, true));
		factions.add(new Crew(NamedFaction.GRINDERS, 2, false));
		factions.add(new Crew(NamedFaction.HIVE, 4, true));
		factions.add(new Crew(NamedFaction.HORDE, 3, true));
		factions.add(new Crew(NamedFaction.IMPERIAL_MILITARY, 6, true));
		factions.add(new Crew(NamedFaction.INK_RAKES, 2, false));
		factions.add(new Crew(NamedFaction.INSPECTORS, 3, true));
		factions.add(new Crew(NamedFaction.IRONHOOK_PRISON, 4, true));
		factions.add(new Crew(NamedFaction.IRUVIAN_CONSULATE, 3, true));
		factions.add(new Crew(NamedFaction.LABORERS, 3, false));
		factions.add(new Crew(NamedFaction.LAMPBLACKS, 2, false));
		factions.add(new Crew(NamedFaction.LEVIATHAN_HUNTERS, 5, true));
		factions.add(new Crew(NamedFaction.LORD_SCURLOCK, 3, true));
		factions.add(new Crew(NamedFaction.LOST, 1, false));
		factions.add(new Crew(NamedFaction.MINISTRY_OF_PRESERVATION, 5, true));
		factions.add(new Crew(NamedFaction.NIGHTMARKET, 2, true));
		factions.add(new Crew(NamedFaction.PATH_OF_ECHOES, 3, true));
		factions.add(new Crew(NamedFaction.RAIL_JACKS, 2, false));
		factions.add(new Crew(NamedFaction.RECONCILED, 3, true));
		factions.add(new Crew(NamedFaction.RED_SASHES, 2, false));
		factions.add(new Crew(NamedFaction.SAILORS, 3, false));
		factions.add(new Crew(NamedFaction.SERVANTS, 2, false));
		factions.add(new Crew(NamedFaction.SEVEROSI_CONSULATE, 1, true));
		factions.add(new Crew(NamedFaction.SILKSHORE, 2, true));
		factions.add(new Crew(NamedFaction.SILVER_NAILS, 3, true));
		factions.add(new Crew(NamedFaction.SIX_TOWERS, 3, false));
		factions.add(new Crew(NamedFaction.SKOVLAN_CONSULATE, 3, false));
		factions.add(new Crew(NamedFaction.SKOVLANDER_REFUGEES, 3, false));
		factions.add(new Crew(NamedFaction.SPARKWRIGHTS, 4, true));
		factions.add(new Crew(NamedFaction.SPIRIT_WARDENS, 4, true));
		factions.add(new Crew(NamedFaction.ULF_IRONBORN, 1, true));
		factions.add(new Crew(NamedFaction.UNSEEN, 4, true));
		factions.add(new Crew(NamedFaction.WEEPING_LADY, 2, true));
		factions.add(new Crew(NamedFaction.WHITECROWN, 5, true));
		factions.add(new Crew(NamedFaction.WRAITHS, 2, false));
		factions.add(new Crew(NamedFaction.VULTURES, 1, false));

	}

	// fields
	private boolean active;
	private ArrayList<Rogue> roster;
	private ArrayList<Rogue> retired;

	private String name;
	private Estate estate;
	private Type type;
	private HashSet<Rep> rep;

	private int tier;
	private boolean holdStrong;
	private int coin;
	private int exp;

	//
	private String lair;
	private HashMap<Claim, Crew> claims;
	private EnumSet<Special> specials;
	private HashMap<Upgrade, Crew> upgrades;
	private int turf;

	//
	private int heat;
	private boolean mostWanted;
	private int wantedLevel;

	private boolean atWar;
	private HashMap<Crew, Integer> shipMap;
	private int[] shipArray;

	/*
	 * Hunting grounds provide +1d6 gather information and a free downtime activity
	 * to contribute to an operation of your preferred type; when you claim turf,
	 * you expand the size and/or type of your hunting grounds
	 */
	private String huntingGrounds;
	//
	private HashSet<Crew> huntingGroundsBoss;
	private int huntingGroundSize;
	private String operation;
	// private HashSet<Score.Activity> favoredOps;

	// constructors
	public Crew() {
		// TODO - create additional constructors
		this.active = true;
		this.name = "Default";
		this.type = Faction.randomCrewType();
		this.tier = 0;
		this.holdStrong = true;
		this.coin = 2;
		this.exp = 0;

		//
		this.rep = new HashSet<Rep>();
		rep.add(Faction.randomReputation());

		//
		this.claims = new HashMap<Faction.Claim, Crew>();
		claims.put(Faction.Claim.LAIR, this);
		this.specials = EnumSet.noneOf(Faction.Special.class);
		this.upgrades = new HashMap<Faction.Upgrade, Crew>();
		this.turf = 0;

		//
		this.heat = 0;
		this.mostWanted = false;
		this.wantedLevel = 0;
		this.atWar = false;

		//
		this.huntingGroundSize = 1;
		// this.favoredOps = new HashSet<Score.Activity>();
		// favoredOps.add(Score.randomActivity(type));

		// setup ships
		shipMap = new HashMap<Crew, Integer>();
		shipArray = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < ALL_FACTIONS.length; ++i) {
			shipMap.put(Crew.getCrewByFaction(ALL_FACTIONS[i]), 0);
		}

		this.huntingGroundsBoss = new HashSet<Crew>();
		ArrayList<Crew> shipSetup = new ArrayList<Crew>();
		while (shipSetup.size() < 3) {
			shipSetup.add(Dice.randomFromList(factions));
		}
		Crew c = shipSetup.get(0);

		//
		huntingGroundsBoss.add(c);
		if (type.equals(Type.ASSASSINS)) {
			specials.add(Dice.randomFromArray(ASSASSIN_SPECIALS));
			upgrades.put(Faction.Upgrade.TRAINING_INSIGHT, c);
			upgrades.put(Faction.Upgrade.TRAINING_PROWESS, c);
		} else if (type.equals(Type.BRAVOS)) {
			// TODO - additional cohort details
			specials.add(Dice.randomFromArray(BRAVOS_SPECIALS));
			upgrades.put(Faction.Upgrade.C2_COHORT_1, c);
			upgrades.put(Faction.Upgrade.TRAINING_PROWESS, c);
		} else if (type.equals(Type.CULT)) {
			// TODO - additional cohort details
			specials.add(Dice.randomFromArray(CULT_SPECIALS));
			upgrades.put(Faction.Upgrade.C2_COHORT_1, c);
			upgrades.put(Faction.Upgrade.TRAINING_RESOLVE, c);
		} else if (type.equals(Type.HAWKERS)) {
			specials.add(Dice.randomFromArray(HAWKERS_SPECIALS));
			upgrades.put(Faction.Upgrade.SECURE_LAIR_1, c);
			upgrades.put(Faction.Upgrade.TRAINING_RESOLVE, c);
		} else if (type.equals(Type.SHADOWS)) {
			specials.add(Dice.randomFromArray(SHADOWS_SPECIALS));
			upgrades.put(Faction.Upgrade.HIDDEN_LAIR, c);
			upgrades.put(Faction.Upgrade.TRAINING_PROWESS, c);
		} else if (type.equals(Type.SMUGGLERS)) {
			specials.add(Dice.randomFromArray(SMUGGLERS_SPECIALS));
			upgrades.put(Faction.Upgrade.BOAT_HOUSE_1, c);
			upgrades.put(Faction.Upgrade.TRAINING_PROWESS, c);
		}

		int dice = Dice.roll(3);
		// hunting grounds
		if (dice == 1) {
			coin -= 1;
		} else if (dice == 2) {
			coin -= 2;
			increaseShip(c);
		} else {
			decreaseShip(c);
		}

		// upgrade one
		c = shipSetup.get(1);
		Faction.Upgrade upgrade = Faction.randomUpgradeByCrewType(type);
		while (upgrades.containsKey(upgrade)) {
			upgrade = Faction.randomUpgradeByCrewType(type);
		}

		dice = Dice.roll(2);
		increaseShip(c);
		upgrades.put(upgrade, c);
		if (dice == 1 && coin > 0) {
			coin -= 1;
			increaseShip(c);
		}

		// upgrade two
		c = shipSetup.get(2);
		while (upgrades.containsKey(upgrade)) {
			upgrade = Faction.randomUpgradeByCrewType(type);
		}

		upgrades.put(upgrade, c);
		dice = Dice.roll(2);
		decreaseShip(c);
		if (dice == 1 || coin < 1) {
			decreaseShip(c);
		} else if (coin > 0) {
			coin -= 1;
		}

		this.roster = new ArrayList<Rogue>();
		this.retired = new ArrayList<Rogue>();
		for (int i = Dice.roll(3, 3); i > 0; --i) {
			// starting roster is "3d3" or 3-9 rogues
			roster.add(new Rogue(this));
		}
	}

	public Crew(NamedFaction name, int tier, boolean hold) {
		/*
		 * FIXME - I need to add more to faction initialization, but this works
		 */
		this.active = true;
		this.name = name.toString();
		// this.estate = estate;
		this.tier = tier;
		this.holdStrong = hold;
		//
		this.claims = new HashMap<Faction.Claim, Crew>();
		claims.put(Faction.Claim.LAIR, this);
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public void advance() {
		boolean canAdvance = true;
		int costToAdvance = (tier + 1) * 8;
		updateTurf();

		if (exp < 12 - turf)
			canAdvance = false;

		ArrayList<Rogue> contributors = new ArrayList<Rogue>(getRoguesWithCoin());
		Rogue.CoinDescending coinSort = new Rogue.CoinDescending();
		Collections.sort(contributors, coinSort);
		int availableCoin = coin;
		for (Iterator<Rogue> it = contributors.iterator(); it.hasNext();) {
			availableCoin += it.next().getCoin();
		}

		// TODO - testing
		// System.out.println("Available coin: " + availableCoin);
		if (availableCoin < costToAdvance)
			canAdvance = false;

		if (canAdvance && holdWeak() && atPeace()) {
			// TODO - testing
			System.out.println("\n - - - - - -");
			System.out.println(toStringBasic());
			System.out.println("IMPROVED CREW HOLD.");

			// advanceHelper
			advanceHelper(costToAdvance, contributors);
			holdStrong = true;
			exp -= 12 - turf;
		} else if (canAdvance && holdStrong()) {
			// TODO - testing
			System.out.println("\n - - - - - -");
			System.out.println(toStringBasic());
			System.out.println("ADVANCED CREW TIER.");

			// advanceHelper
			advanceHelper(costToAdvance, contributors);
			holdStrong = false;
			exp -= 12 - turf;
			++tier;
		}
	}

	private void updateTurf() {
		Faction.Claim[] array = new Faction.Claim[] { Faction.Claim.TURF_1, Faction.Claim.TURF_2, Faction.Claim.TURF_3,
				Faction.Claim.TURF_4, Faction.Claim.TURF_5, Faction.Claim.TURF_6 };
		int counter = 0;
		for (Faction.Claim el : array) {
			if (claims.containsKey(el))
				++counter;
		}

		turf = counter;
	}

	private void advanceHelper(int costToAdvance, List<Rogue> contributors) {
		while (costToAdvance > 0) {
			costToAdvance -= everyoneContribute(costToAdvance, contributors);

			if (costToAdvance > 0 && costToAdvance <= coin) {
				int difference = costToAdvance;
				costToAdvance -= difference;
				coin -= difference;

				// TODO - testing
				// System.out.println("Paid " + difference + " out of crew funds.");
			}
		}
	}

	private int everyoneContribute(int cost, List<Rogue> list) {
		int amountPaid = 0, currentCoin;
		Rogue rogue;
		for (Iterator<Rogue> it = list.iterator(); amountPaid < cost && it.hasNext();) {
			rogue = it.next();
			currentCoin = rogue.getCoin();

			if (currentCoin > 0) {
				rogue.setCoin(currentCoin - 1);
				++amountPaid;
				// TODO - testing
				// System.out.println(rogue + " paid toward crew advance.");
			}
		}

		return amountPaid;
	}

	public void findWork() {
		/*
		 * TODO
		 */
		List<Crew>[] array = updateShipArray();
		Crew client = clientFriendOrSelf(array), target;
		Score.Goal goal;

		if (client.sameAs(this)) {
			// working for self
			target = preferredTarget();

			if (atWar()) {
				// TODO - testing
				goal = Score.Goal.SHAKE;
				target = Dice.randomFromList(this.enemiesList());
			} else {
				goal = Score.Goal.CLAIM;

			}

			// if (tier < 6 && Dice.roll(2) == 1) {
			// goal = Score.Goal.CLIMB;
			//
			// } else {
			// goal = Score.Goal.CLAIM;
			//
			// }
		} else {
			/*
			 * TODO - I should figure out how much of the score a patron can dictate;
			 * whether it's the PLAN, just the ACTIVITY, whether they're going to the
			 * specific crew because of their inherent talents or reputation... what I
			 * should probably come up with a "referral system"
			 */
			// Score.Plan plan = Score.randomPlan();
			// Score.Activity activity = Score.randomActivity();
			target = client.npcRandomEnemyGet();

			if (client.tier < 6 && Dice.roll(2) == 1)
				goal = Score.Goal.ASSIST;
			else
				goal = Score.Goal.SHAKE;

		}

		if (array[6].contains(target) || rep.contains(Rep.HONORABLE)) {
			/*
			 * TODO - refuse to pull job on a friendly
			 */
		}

		// TODO - teams are chosen for each score
		int teamSize = Dice.roll(3) + 2;
		if (teamSize > roster.size())
			teamSize = roster.size();

		ArrayList<Rogue> team = new ArrayList<Rogue>();
		Rogue.StressAscending leastStressed = new Rogue.StressAscending();
		Collections.sort(roster, leastStressed);
		for (Iterator<Rogue> it = roster.iterator(); it.hasNext() && team.size() < teamSize;) {
			team.add(it.next());
		}

		Score score = new Score(this, team, new Plan.Quest(this));
		// TODO - testing
		if (client.sameAs(this)) {
			System.out.println("Crew job. (Goal: " + goal + ")");
		} else {
			System.out.println("Job for " + client.toString() + " (Goal: " + goal + ")");
		}
		System.out.println(score.toStringDetailed());
		System.out.println();

		// TODO - determine engagement dice
		int dice = Dice.roll(3);
		score.action(dice);

		Rogue rogue;
		for (Iterator<Rogue> it = team.iterator(); it.hasNext();) {
			rogue = it.next();
			if (rogue.getTrauma().size() > 3) {
				retired.add(rogue);
				roster.remove(rogue);

				// TODO - testing
				System.out.println(rogue + " has retired from the game.");
			}
		}
	}

	public Crew preferredTarget() {
		// ASSASSINS, BRAVOS, CULT, HAWKERS, SHADOWS, SMUGGLERS
		// CITIZENRY, INSTITUTION, LABOR_TRADE, THE_FRINGE, UNDERWORLD
		int[] targets = { 20, 20, 20, 20, 20 };

		/*
		 * ASSASSINS institutions & underworld CULT citizens & fringe HAWKERS citizens &
		 * labor SHADOWS institutions & underworld SMUGGLERS labor & fringe
		 */
		if (type.equals(Type.ASSASSINS)) {
			targets[0] = 10; // citizens
			targets[1] = 35; // institution
			targets[2] = 10; // labor & trade
			targets[3] = 10; // the fringe
			targets[4] = 35; // underworld
		} else if (type.equals(Type.BRAVOS)) {
			targets[0] = 20; // citizens
			targets[1] = 20; // institution
			targets[2] = 20; // labor & trade
			targets[3] = 20; // the fringe
			targets[4] = 20; // underworld
		} else if (type.equals(Type.CULT)) {
			targets[0] = 35; // citizens
			targets[1] = 10; // institution
			targets[2] = 10; // labor & trade
			targets[3] = 35; // the fringe
			targets[4] = 10; // underworld
		} else if (type.equals(Type.HAWKERS)) {
			targets[0] = 35; // citizens
			targets[1] = 10; // institution
			targets[2] = 35; // labor & trade
			targets[3] = 10; // the fringe
			targets[4] = 10; // underworld
		} else if (type.equals(Type.SHADOWS)) {
			targets[0] = 10; // citizens
			targets[1] = 35; // institution
			targets[2] = 10; // labor & trade
			targets[3] = 10; // the fringe
			targets[4] = 35; // underworld
		} else if (type.equals(Type.SMUGGLERS)) {
			targets[0] = 10; // citizens
			targets[1] = 10; // institution
			targets[2] = 35; // labor & trade
			targets[3] = 35; // the fringe
			targets[4] = 10; // underworld
		}

		int dice = Dice.roll(100);
		NamedFaction faction;
		if (dice < 1 + targets[0]) {
			System.out.println(1 + targets[0] + " / " + dice);
			faction = Faction.randomCitizenryEnum();
		} else if (dice < 1 + targets[0] + targets[1]) {
			System.out.println(1 + targets[0] + targets[1] + " / " + dice);
			faction = Faction.randomInstitutionEnum();
		} else if (dice < 1 + targets[0] + targets[1] + targets[2]) {
			System.out.println(1 + targets[0] + targets[1] + targets[2] + " / " + dice);
			faction = Faction.randomLaborTradeEnum();
		} else if (dice < 1 + targets[0] + targets[1] + targets[2] + targets[3]) {
			System.out.println(1 + targets[0] + targets[1] + targets[2] + targets[3] + " / " + dice);
			faction = Faction.randomFringeEnum();
		} else {
			System.out.println(100 + " / " + dice);
			faction = Faction.randomUnderworldEnum();
		}

		return getCrewByFaction(faction);
	}

	public Crew clientFriendOrSelf() {
		List<Crew>[] array = updateShipArray();
		return clientFriendOrSelf(array);
	}

	public Crew clientFriendOrSelf(List<Crew>[] array) {
		int[] obligations = new int[] { 0, 0, 0, 0, 0, 0, 0 };

		if (shipArray[0] > 0)
			obligations[0] = 20;

		if (shipArray[1] > 0)
			obligations[1] = 15;

		if (shipArray[2] > 0)
			obligations[2] = 10;

		if (shipArray[4] > 0)
			obligations[4] = 15;

		if (shipArray[5] > 0)
			obligations[5] = 10;

		if (shipArray[6] > 0)
			obligations[6] = 5;

		int totalObs = obligations[0] + obligations[1] + obligations[2] + obligations[4] + obligations[5]
				+ obligations[6];
		if (totalObs < 31)
			obligations[3] = 60 - totalObs;
		else if (totalObs < 61)
			obligations[3] = 75 - totalObs;

		// TODO - for testing
		// for (int el : obligations) {
		// System.out.println("Ship# " + el);
		// }

		int dice = Dice.roll(100);
		Crew faction;
		if (dice < obligations[0]) {
			faction = Dice.randomFromList(array[6]);
		} else if (dice < obligations[0] + obligations[1]) {
			faction = Dice.randomFromList(array[5]);
		} else if (dice < obligations[0] + obligations[1] + obligations[2]) {
			faction = Dice.randomFromList(array[4]);
		} else if (dice < obligations[0] + obligations[1] + obligations[2] + obligations[3]) {
			faction = Dice.randomFromList(array[3]);
		} else if (dice < obligations[0] + obligations[1] + obligations[2] + obligations[3] + obligations[4]) {
			faction = Dice.randomFromList(array[2]);
		} else if (dice < obligations[0] + obligations[1] + obligations[2] + obligations[3] + obligations[4]
				+ obligations[5]) {
			faction = Dice.randomFromList(array[1]);
		} else if (dice < obligations[0] + obligations[1] + obligations[2] + obligations[3] + obligations[4]
				+ obligations[5] + obligations[6]) {
			faction = Dice.randomFromList(array[0]);
		} else {
			return this;
		}

		// client = Crew.getCrewByFaction(faction);
		return faction;
	}

	public List<Rogue> getRoguesWithCoin() {
		ArrayList<Rogue> list = new ArrayList<Rogue>();
		Rogue rogue;
		for (Iterator<Rogue> it = roster.iterator(); it.hasNext();) {
			rogue = it.next();
			if (rogue.getCoin() > 0)
				list.add(rogue);
		}

		return list;
	}

	public boolean increaseShip(Crew crew) {
		boolean increased = false;
		int v = shipMap.get(crew);

		if (v < 3) {
			shipMap.put(crew, v + 1);
			increased = true;
		}

		checkAtWar();
		return increased;
	}

	public boolean decreaseShip(Crew crew) {
		boolean decreased = false;
		int v = shipMap.get(crew);

		if (v > -3) {
			shipMap.put(crew, v - 1);
			decreased = true;
		}

		checkAtWar();
		return decreased;
	}

	private void checkAtWar() {
		atWar = (shipMap.values().contains(-3)) ? true : false;
	}

	/*
	 * NEW METHODS
	 */
	@Override
	public int factionID() {
		// TODO

		return 0;
	}

	@Override
	public Set<Faction> factions() {
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public Set<Rep> getReputation() {
		return rep;
	}

	public void setReputation(Set<Rep> reputation) {
		this.rep = (HashSet<Rep>) reputation;
	}

	@Override
	public Set<Plan.Quest> getPlans() {
		// TODO
		return null;
	}

	@Override
	public void setPlans(Set<Plan.Quest> plans) {
		// TODO

	}

	@Override
	public int getLevel() {
		// TODO
		return 0;
	}

	@Override
	public void setLevel(int level) {
		// TODO
	}

	@Override
	public int getCoin() {
		return coin;
	}

	@Override
	public void setCoin(int coin) {
		this.coin = coin;
	}

	@Override
	public int getExperience() {
		return exp;
	}

	@Override
	public void setExperience(int experience) {
		this.exp = experience;
	}

	@Override
	public EnumSet<Special> getSpecials() {
		return specials;
	}

	@Override
	public void setSpecials(EnumSet<Special> specials) {
		this.specials = specials;
	}

	@Override
	public Set<Upgrade> upgradeSet() {
		return upgrades.keySet();
	}

	@Override
	public Type crewType() {
		return type;
	}

	@Override
	public void setCrewType(Type type) {
		this.type = type;
	}

	@Override
	public int getHeat() {
		return heat;
	}

	@Override
	public void setHeat(int heat) {
		this.heat = heat;

		// TODO
		if (heat >= MAX_HEAT)
			mostWanted = true;
	}

	@Override
	public int getWantedLevel() {
		return wantedLevel;
	}

	@Override
	public void setWantedLevel(int wantedLevel) {
		this.wantedLevel = wantedLevel;
	}

	@Override
	public boolean active() {
		return active;
	}

	@Override
	public boolean inactive() {
		return (active != true);
	}

	@Override
	public void activate() {
		this.active = true;
	}

	@Override
	public void deactivate() {
		this.active = false;
	}

	@Override
	public boolean atWar() {
		return atWar;
	}

	@Override
	public boolean atPeace() {
		return (atWar != true);
	}

	@Override
	public Set<Ship> getShips() {
		// TODO
		return null;
	}

	@Override
	public void setShips(Set<Ship> ships) {
		// TODO

	}

	@Override
	public Obligations obligations() {
		// TODO
		return null;
	}

	/*
	 * OLD METHODS
	 * 
	 */
	public int getTier() {
		int crewTier = tier;
		if (atWar && holdStrong != true)
			crewTier = tier - 1;

		return crewTier;
	}

	public void addEXP(int gains) {
		exp = (exp + gains > 12) ? 12 : exp + gains;
	}

	public void addCoin(int gains) {
		// TODO - do I need to do something else with this?
		coin += gains;
	}

	public boolean holdStrong() {
		boolean isHoldStrong = holdStrong;

		if (atWar)
			isHoldStrong = false;
		else if (atWar && holdStrong != true)
			isHoldStrong = true;

		return isHoldStrong;
	}

	public boolean holdWeak() {
		boolean isHoldWeak = (holdStrong != true);

		if (holdStrong && atWar)
			isHoldWeak = true;
		else if (atWar && holdStrong != true)
			isHoldWeak = false;

		return isHoldWeak;
	}

	public void strengthenHold() {
		// TODO - testing
		if (holdStrong() && atPeace()) {
			++tier;
			holdStrong = false;
		} else if (atWar && holdStrong) {
			++tier;
			holdStrong = false;
		} else if (atWar && holdStrong != true) {
			holdStrong = true;
		} else if (holdStrong != true) {
			holdStrong = true;
		}
	}

	public void weakenHold() {
		// TODO - testing
		if (holdStrong) {
			holdStrong = false;
		} else if (tier == 0 && holdStrong != true) {
			// TODO - testing
			System.out.println("The faction " + this.name + " has been destroyed.");
			active = false;
		} else if (holdStrong != true) {
			--tier;
		}
	}

	public void resolveHeat() {
		if (heat >= MAX_HEAT) {
			heat -= MAX_HEAT;
			mostWanted = false;

			++wantedLevel;
			// TODO - testing
			System.out.println("Wanted level rose to " + wantedLevel);
		}
	}

	public Set<Crew> getNonZeroShips() {
		HashSet<Crew> nonZeroShips = new HashSet<Crew>();

		Crew f;
		int v;
		for (Iterator<Crew> it = shipMap.keySet().iterator(); it.hasNext();) {
			f = it.next();
			v = shipMap.get(f);
			if (f.active && v != 0)
				nonZeroShips.add(f);
		}

		return nonZeroShips;
	}

	public Set<Crew> npcAllyGet() {
		return ShipOld.crewAllySet(this);
	}

	public Set<Crew> npcEnemyGet() {
		return ShipOld.crewEnemySet(this);
	}

	public Crew npcRandomEnemyGet() {
		Crew choice;
		if (npcEnemyGet().size() > 0)
			choice = Dice.randomFromSet(npcEnemyGet());
		else {
			choice = Dice.randomFromList(getActiveFactions());
			while (choice.sameAs(this)) {
				choice = Dice.randomFromList(getActiveFactions());
			}
		}

		return choice;
	}

	public HashMap<Faction.Claim, Crew> getClaims() {
		return claims;
	}

	public boolean sameAs(Crew other) {
		boolean equals = false;
		if (other.name.compareTo(this.name) == 0)
			equals = true;

		return equals;
	}

	public boolean notSameAs(Crew other) {
		boolean equals = true;
		if (other.name.compareTo(this.name) == 0)
			equals = false;

		return equals;
	}

	@Override
	public String toString() {
		return name;
	}

	public String toStringBasic() {
		String string = String.format("name %s %s %ntier: %2d || rep: %2d (turf: %d) || coin: %3d || Strong hold: %s",
				rep.toString(), type.toString(), tier, exp, turf, coin, holdStrong());

		return string;
	}

	public String toStringDetailed() {
		Set<Crew> set = getNonZeroShips();

		String shipList = "";
		Iterator<Crew> it = set.iterator();
		Crew crew;
		int status;
		String name;
		for (int i = 0; i < set.size(); ++i) {
			crew = it.next();
			name = crew.nameOnly();
			status = shipMap.get(crew);
			name = String.format("%2d %s", status, name);
			shipList += (i + 1 < set.size()) ? name + "\n" : name;
		}

		String upgradeList = upgrades.toString();

		String string = (atWar()) ? String.format(" - AT WAR WITH: %s%n", enemiesList().toString()) : "";
		// string = String.format("name %s %s coin: %2d %n%s", rep.toString(),
		// type.toString(), coin, shipList);
		string += String.format(
				"name %s %s || Crew size: %d %n%s %ntier: %2d || rep: %2d (turf: %d) || coin: %3d || Strong hold: %s %n%s %n%s",
				rep.toString(), type.toString(), roster.size(), roster.toString(), tier, exp, turf, coin, holdStrong(),
				specials.toString(), upgradeList);

		return string;
	}

	public String simplifiedToString() {
		String string = String.format("%s (tier %d)", name, tier);

		return string;
	}

	public String nameOnly() {
		return name;
	}

	public List<Crew>[] allFactionStatus() {
		List<Crew>[] array = (List<Crew>[]) new ArrayList[7];
		for (int i = 0; i < array.length; ++i) {
			array[i] = new ArrayList<Crew>();
		}

		Set<Crew> set = new HashSet<Crew>(shipMap.keySet());
		Crew crew;
		for (Iterator<Crew> it = set.iterator(); it.hasNext();) {
			crew = it.next();
			if (crew.active)
				array[shipMap.get(crew) + 3].add(crew);
		}

		return array;
	}

	public List<Crew>[] nonNeutralStatus() {
		List<Crew>[] array = (List<Crew>[]) new ArrayList[6];
		for (int i = 0; i < array.length; ++i) {
			array[i] = new ArrayList<Crew>();
		}

		Set<Crew> set = new HashSet<Crew>(shipMap.keySet());
		Crew crew;
		int status;
		for (Iterator<Crew> it = set.iterator(); it.hasNext();) {
			crew = it.next();
			status = shipMap.get(crew);

			if (crew.active && status + 3 < 3) {
				array[status + 3].add(crew);
			} else if (crew.active && status + 3 > 3) {
				array[status + 2].add(crew);
			}
		}

		return array;
	}

	public List<Crew>[] updateShipArray() {
		// must update shipArray object in REVERSE order because a faction status equals
		// "status +3" to offset -3 "at war"
		List<Crew>[] array = allFactionStatus();

		for (int i = 0; i < shipArray.length; ++i) {
			shipArray[6 - i] = array[i].size();
		}

		return array;
	}

	public List<Crew> alliesList() {
		List<Crew> list = new ArrayList<Crew>();
		Set<Crew> set = new HashSet<Crew>(shipMap.keySet());
		Crew crew;
		for (Iterator<Crew> it = set.iterator(); it.hasNext();) {
			crew = it.next();
			if (crew.active && shipMap.get(crew) > 2)
				list.add(crew);
		}

		return list;
	}

	public List<Crew> friendliesList() {
		List<Crew> list = new ArrayList<Crew>();
		Set<Crew> set = new HashSet<Crew>(shipMap.keySet());
		Crew crew;
		for (Iterator<Crew> it = set.iterator(); it.hasNext();) {
			crew = it.next();
			if (crew.active && shipMap.get(crew) > 0)
				list.add(crew);
		}

		return list;
	}

	public List<Crew> hostilesList() {
		List<Crew> list = new ArrayList<Crew>();
		Set<Crew> set = new HashSet<Crew>(shipMap.keySet());
		Crew crew;
		for (Iterator<Crew> it = set.iterator(); it.hasNext();) {
			crew = it.next();
			if (crew.active && shipMap.get(crew) < 0)
				list.add(crew);
		}

		return list;
	}

	public List<Crew> enemiesList() {
		List<Crew> list = new ArrayList<Crew>();
		Set<Crew> set = new HashSet<Crew>(shipMap.keySet());
		Crew crew;
		for (Iterator<Crew> it = set.iterator(); it.hasNext();) {
			crew = it.next();
			if (crew.active && shipMap.get(crew) < -2)
				list.add(crew);
		}

		return list;
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	public static Crew randomFaction() {
		Crew choice = Dice.randomFromList(factions);
		return choice;
	}

	public static District randomDistrict() {
		return Dice.randomFromArray(ALL_DISTRICTS);
	}

	public static List<Crew> getFactions() {
		return factions;
	}

	public static List<Crew> getActiveFactions() {
		List<Crew> activeFactions = new ArrayList<Crew>();
		Crew candidate;
		for (Iterator<Crew> it = factions.iterator(); it.hasNext();) {
			candidate = it.next();
			if (candidate.active)
				activeFactions.add(candidate);
		}

		return activeFactions;
	}

	public static Crew getCrewByFaction(NamedFaction faction) {
		int length = factions.size();

		String name;
		Crew crew = null;
		for (int i = 0; i < length; ++i) {
			crew = factions.get(i);
			name = crew.name.toString();
			if (name.endsWith(faction.toString())) {
				break;
			}
		}

		return crew;
	}

	public static NamedFaction getFactionNameByString(String string) {
		NamedFaction[] array = ALL_FACTIONS;
		NamedFaction faction = null;

		for (int i = 0; i < array.length; ++i) {
			// ignore "Faction."
			if (array[i].toString().endsWith(string))
				faction = array[i];
		}

		return faction;
	}

	/*
	 * COMPARATOR - INNER CLASSES
	 */
	class TiersDescending implements Comparator<Crew> {
		@Override
		public int compare(Crew crew1, Crew crew2) {
			return crew1.tier - crew2.tier;
		}
	}
}
