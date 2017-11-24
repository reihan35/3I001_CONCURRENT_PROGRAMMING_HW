package Exo11;

import java.util.Random;

public class Client implements Runnable {
	private Serveur s;
	private int id;
	private int type;
	private boolean requeteTraitee = false;
	private Random r = new Random();
	
	public Client(Serveur s, int id){
		this.s = s;
		this.id = id;
		if ((id % 3) != 0)
			this.type = 1;
		else
			this.type = 2;
	}
	
	public synchronized void requeteServie(ReponseRequete r){
		requeteTraitee = true;
		System.out.println(r.toString());
		notify();
	}
	
	public synchronized void attendre() throws InterruptedException{
		while (!requeteTraitee){
			System.out.println("Client " + id + ": j'attend impatiemment qu'on traite ma requête.");
			wait();
		}
	}
	
	public void run() {
		for (int i=0; i<5; i++){
			try{
				s.soumettre(this, i, type);
				attendre();
				System.out.println("Client " + id + ": diable! Le serveur m'a réveillé!");
				Thread.sleep(r.nextInt(10));
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public int getId(){
		return id;
	}
}
