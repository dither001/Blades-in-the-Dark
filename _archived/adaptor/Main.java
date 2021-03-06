package adaptor;
import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import com.bladesinthedark.actor.Rogue;
import com.bladesinthedark.crew.Crew;
import com.bladesinthedark.crew.Faction;
import com.bladesinthedark.crew.Gang;

import model.Locale;
import model.Setting;

public class Main {
	private static int PCS_TO_ROLL = 5;
	private static int SPELLBOOK_LEVEL = 17;

	//
	private static final Scanner INPUT = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO

		Locale cluster = new Locale();
		cluster.localePrint();

		// factionGameSimulation();

		// workLoop();
		// rollRogues();
		// setupActorLadder();
		// characterAdvance(Class.BARBARIAN);
		// rollCharacter();

	}

	public static void factionGameSimulation() {
		int proceed = 1, turn = 1;
		Setting setting = new Setting();

		String line;
		while (proceed == 1) {
			System.out.println(" - - - - - - - - score: " + turn++);
			setting.update();

			line = INPUT.nextLine();
			try {
				proceed = Integer.parseInt(line);
			} catch (NumberFormatException e) {
				proceed = 1;
			}

			// if (proceed != 0) {
			// if (proceed == 2) {
			// }
			//
			// proceed = 1;
			// }
		}
	}

	public static void factionJobTest(int turns) {
		Setting setting = new Setting();

		for (int i = 0; i < turns; ++i) {
			setting.update();
			System.out.println();

		}
	}

	public static void gangDebugPrint() {
		Setting setting = new Setting();
		Gang gang;
		for (Iterator<Faction> it = setting.orderedFactionList().iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			System.out.println(gang.toStringDetailed());
			System.out.println();
		}

	}

	public static void printFactionStuff() {
		List<Crew> list = Crew.getFactions();
		for (Iterator<Crew> it = list.iterator(); it.hasNext();) {
			System.out.println(it.next().npcAllyGet().toString());
		}
	}

	public static void rollRogues() {
		for (int i = 0; i < PCS_TO_ROLL; ++i) {
			rollOneRogue();
			System.out.println();
		}
	}

	public static void rollOneRogue() {
		Crew crew = new Crew();
		Rogue rogue = new Rogue(crew);
		System.out.println(rogue.toStringDetailed());
	}

	public static void rollCrew() {
		Crew crew = new Crew();
		System.out.println(crew.toString());
	}

	public static void workLoop() {
		int proceed = 1, score = 0;
		Crew crew = new Crew();

		String line;
		while (proceed == 1) {
			System.out.println(" - - - - - - - - score: " + ++score);
			System.out.println(crew.toStringDetailed());
			System.out.println();

			// Crew target = Crew.getCrewByFaction(crew.preferredTarget());
			// Score score = new Score(crew, target);
			// System.out.println(score.toString());
			//
			// System.out.println();
			// score.action();

			crew.findWork();
			crew.advance();

			line = INPUT.nextLine();
			try {
				proceed = Integer.parseInt(line);
			} catch (NumberFormatException e) {
				proceed = 1;
			}

			if (proceed != 0) {
				if (proceed == 2) {
					List<Crew>[] array = crew.nonNeutralStatus();
					System.out.println();
					System.out.println("Allies: " + array[5].toString());
					System.out.println("Friendlies: " + array[4].toString());
					System.out.println("Helpful: " + array[3].toString());
					System.out.println("Indifferent: " + array[2].toString());
					System.out.println("Hostiles: " + array[1].toString());
					System.out.println("Enemies: " + array[0].toString());
					System.out.println();
				}

				proceed = 1;
			}
		}
	}

}
