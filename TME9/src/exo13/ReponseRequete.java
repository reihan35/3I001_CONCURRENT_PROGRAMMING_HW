package exo13;

import java.util.Random;

public class ReponseRequete {
	private Client client;
	private int numReq;
	private int res;
	private Random r = new Random();

	public ReponseRequete(Client client, int numReq){
		this.client = client;
		this.numReq = numReq;
		res = r.nextInt(10);
	}

	public Client getClient(){
		return client;
	}
	
	public String toString(){
		return "Réponse du serveur à la requête " + numReq + " de " + client.getId() + ": " + res;
	}
}