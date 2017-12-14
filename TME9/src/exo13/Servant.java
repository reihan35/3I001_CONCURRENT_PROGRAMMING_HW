package exo13;

import java.util.concurrent.Callable;

public class Servant implements Callable<ReponseRequete> {
	private Client client;
	private int numReq;

	public Servant(Client c, int numR){
		client = c;
		numReq = numR;
	}

	public ReponseRequete call() {
		return new ReponseRequete(client, numReq);
	}

}