
public class CandidateExistsException extends Exception {
	String Name;
	
	CandidateExistsException(String Name){
		this.Name = Name;
	}

}
