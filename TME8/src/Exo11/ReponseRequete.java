package Exo11;

import java.util.Random;

public class ReponseRequete {
	private int id;
	private Client c;
	private Random r = new Random();
	private int val;
	
	public ReponseRequete(int id, Client c){
		this.id=id;
		this.c=c;
		val = r.nextInt(10);
	}
	
	public String toString(){
		return "client " + c.getId() + " - requête: " + id + " - réponse: " + val;
	}
}