package com.blades.main;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandLinePlayer {
	public static final Scanner SCANNER = new Scanner(System.in);

	private static boolean exit(String s) {
		return Stream.of("exit", "q", "quit", "stop").anyMatch(e -> e.matches(s.toLowerCase()));
	}

	private static List<Aspect> getAspectList(String aspect) {
		return Aspect.get(Aspect.filter(aspect)).collect(Collectors.toUnmodifiableList());
	}

	private static Aspect select(List<Aspect> list) {
		for (int i = 0; i < list.size(); ++i) { System.out.println(String.format("%2d. %s", i + 1, list.get(i).getName())); }

		int input = -1;
		while (Util.unless(input)) {
			System.out.println(String.format("Please choose a number 1-%s:", list.size()));
			input = SCANNER.nextInt() -1;
			input = (input >= 0 && input < list.size()) ? input : -1;
		}
		
		return list.get(input);
	}

	public static void main(String... strings) {
		GameLoop.getInstance();
		String input = "";

		Aspect playbook = null; Aspect downtime = null;
		while (Util.unless(exit(input))) {

//			playbook = Util.unless(playbook)? select(getAspectList("playbook")) : playbook;
			select(getAspectList(Aspect.PLAYBOOK));
//			select(getAspectList(Aspect.APPROACH));
			select(getAspectList(Aspect.TRAUMA));

//			input = SCANNER.nextLine();
//			System.out.println("Echo: " + input);
		}
	}

}
