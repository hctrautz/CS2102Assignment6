
class DuplicateVotesException extends Exception {
	String duplicateName;
	
	DuplicateVotesException(String duplicateName){
		this.duplicateName = duplicateName;
	}
}
