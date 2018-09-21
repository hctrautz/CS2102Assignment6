import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;


class ElectionData {
	private LinkedList<String> ballot = new LinkedList<String>();
	private LinkedList<String> votes = new LinkedList<String>();
	private HashMap<String, LinkedList<Integer>> hash = new HashMap<String, LinkedList<Integer>>();

	ElectionData() {}

	public LinkedList<String> getBallot(){
		return this.ballot;
	}
	
	public LinkedList<String> getVotes(){
		return this.votes;
	}
	
	public int countVotes(String forcand) {
		int numvotes = 0;
		for (String s : votes) {
			if (s.equals(forcand))
				numvotes = numvotes+1;
		}
		return numvotes;
	}

	public void processVote(String first, String second, String third) throws DuplicateVotesException, UnknownCandidateException{
		if(!hash.containsKey(first)) {
			throw new UnknownCandidateException(first);
		}

		if(!hash.containsKey(second)) {
			throw new UnknownCandidateException(second);
		}

		if(!hash.containsKey(third)) {
			throw new UnknownCandidateException(third);
		}
		
		if(first.equals(second) || first.equals(third)){
			throw new DuplicateVotesException(first);
		}

		if(second.equals(first) || second.equals(third)){
			throw new DuplicateVotesException(second);
		}

		if(third.equals(first) || third.equals(second)){
			throw new DuplicateVotesException(third);
		}

		hash.get(first).set(0, hash.get(first).get(0) + 1);
		hash.get(second).set(1, hash.get(second).get(1) + 1);
		hash.get(third).set(2, hash.get(third).get(2) + 1);
	}

	public void addCandidate(String canidate) throws CandidateExistsException{
		LinkedList <Integer> order = new LinkedList<Integer>();

		if(hash.containsKey(canidate)){
			throw new CandidateExistsException(canidate);
		} else {
			order.add(0);
			order.add(0);
			order.add(0);
			
			hash.put(canidate, order);
			ballot.add(canidate);
		}
	} 

	public String findWinnerMostFirstVotes(){
		float mostVotes = 0;

		for(String canidate : ballot) {
			mostVotes = hash.get(canidate).get(0) + mostVotes;
		}

		if(mostVotes > 0) {
			for(String canidate : ballot) {

				float percent = hash.get(canidate).get(0)/mostVotes;

				if((percent * 100) > 50){         
					return canidate;
				}
			}
		}
		return "Runoff required";
	}

	public String findWinnerMostPoints() {
		int mostPoints = 0;
		String winner = null;

		for(String canidate : ballot) {
			int canidatePoints = ((hash.get(canidate).get(0) * 3) + (hash.get(canidate).get(1) * 2) + hash.get(canidate).get(2));

			if(canidatePoints > mostPoints) {
				mostPoints = canidatePoints;
				winner = canidate;
			}

		}
		return winner;
	}
}
