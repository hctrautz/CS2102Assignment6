
class UnknownCandidateException extends Exception  {
String DNEName;

UnknownCandidateException(String DNEName){
	this.DNEName = DNEName;
}
}
