import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import org.junit.*;

public class Examples {

	public ElectionData ElectionOne(){

		ElectionData election = new ElectionData();

		try {
			election.addCandidate("Joe");
			election.addCandidate("Smith");
			election.addCandidate("Gabe");
			election.processVote("Joe", "Smith", "Gabe");
			election.processVote("Joe", "Gabe", "Smith");
			election.processVote("Smith", "Joe", "Gabe");
		}
		catch(Exception e) {
			System.out.println("Error creating election 1: "+e);
		}

		return election;
	}

	public ElectionData ElectionTwo(){
		ElectionData election = new ElectionData();
		try {
			election.addCandidate("Emily");
			election.addCandidate("Ed");
			election.addCandidate("Hunter");		
			election.addCandidate("Louis");
			election.processVote("Emily", "Ed", "Louis");
			election.processVote("Ed", "Hunter", "Louis");
			election.processVote("Louis", "Hunter", "Ed");
			election.processVote("Hunter", "Louis", "Emily");
		}
		catch(Exception e) {
			System.out.println("Election 2 Error: " +e);
		}

		return election;
	}

	public ElectionData ElectionThree(){
		ElectionData election = new ElectionData();
		try {
			election.addCandidate("Root");		
			election.processVote("Root", "Root", "Root");
		} catch (Exception e) {
			System.out.println("Election Three Error: " +e);
		}
		return election;
	}	

	public ElectionData ElectionFour(){
		ElectionData election = new ElectionData();
		try {
			election.addCandidate("Superman");
			election.addCandidate("Batman");
			election.addCandidate("Spiderman");
			election.processVote("Superman", "Batman", "Spiderman");
		} catch (Exception e) {
			System.out.println("Election 4 Error: "+e);
		}
		return election;
	}	

	public ElectionData ElectionFive(){
		ElectionData election = new ElectionData();
		try {
			election.addCandidate("Sam");
			election.addCandidate("Lucas");
			election.addCandidate("Derek");
			election.addCandidate("Lydia");
			election.processVote("Lydia", "Sam", "Lucas");
			election.processVote("Lucas", "Derek", "Sam");
			election.processVote("Lydia", "Sam", "Derek");
		} catch (Exception e) {
			System.out.println("Election 5 Erorr: "+e);
		}
		return election;
	}

	//tests to see if the unknown candidate exception works
	@Test (expected = UnknownCandidateException.class)
	public void testThrowsUnknown () throws DuplicateVotesException, UnknownCandidateException {
		ElectionFive().processVote("Sam", "Lydia", "hello");
	}

	//tests to see that the unknown candidate exception catches first
	@Test (expected = UnknownCandidateException.class)
	public void testUnknownBeforeDuplicate () throws DuplicateVotesException, UnknownCandidateException {
		ElectionFive().processVote("Sam", "RIP HARAMBEE", "Sam");
	}

	//tests the duplicate vote exception
	@Test (expected = DuplicateVotesException.class)
	public void testDuplicateVote() throws DuplicateVotesException, UnknownCandidateException {
		ElectionFive().processVote("Sam", "Lydia", "Lydia");
	}

	//tests the candidate already exists exception
	@Test (expected = CandidateExistsException.class)
	public void testThrowsCandidateExists () throws CandidateExistsException {
		ElectionFive().addCandidate("Lucas");
	}
	//tests the method with a normal election
	@Test
	public void testfindWinnerMostFirstVote(){
		assertEquals("Joe", ElectionOne().findWinnerMostFirstVotes());
	}

	//tests to make sure the method's exception works properly
	@Test
	public void testfindWinnerMostFirstVoteException(){
		assertEquals("Runoff required", ElectionTwo().findWinnerMostFirstVotes());
	}

	//tests the method with a normal election
	@Test
	public void testWinnerMostPoints(){
		assertEquals("Joe", ElectionOne().findWinnerMostPoints());
	}

	//tests the method with a normal election again
	@Test
	public void testWinnerMostPoints2(){
		assertEquals("Hunter", ElectionTwo().findWinnerMostPoints());
	}

	//tests with only one vote cast
	@Test
	public void testfindWinnerMostFirstVote2(){ 
		assertEquals("Superman", ElectionFour().findWinnerMostFirstVotes());
	}

	//tests with only one vote cast
	@Test
	public void testMostPointsOneVote () { 
		assertEquals("Superman", ElectionFour().findWinnerMostPoints());
	}

}