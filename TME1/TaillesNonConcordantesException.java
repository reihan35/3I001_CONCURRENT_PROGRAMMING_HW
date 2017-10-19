public class TaillesNonConcordantesException extends Exception {
	public TaillesNonConcordantesException(String message){
		super("Probleme de tailles : " + message);
	}
}
