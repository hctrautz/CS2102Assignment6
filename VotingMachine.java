import java.util.Scanner;

public class VotingMachine {
	ElectionData data = new ElectionData ();
	Scanner keyboard = new Scanner(System.in);
	
	public void printBallot() {
		System.out.println("The candidates are ");
		for (String s : data.getBallot()) {
			System.out.println(s);
		}
	}

	public void screen() {
		this.printBallot();
		System.out.println("Who do you want to vote for?");
		String candidate = keyboard.next();
		data.getVotes().add(candidate);
		System.out.println("You voted for " + candidate);
	}
}
