package exo13;

public class Client implements Runnable {
	private int id;
	private static int cpt=1;
	private static Object mutex = new Object();
	private Serveur serveur;

	private boolean requeteServie = false;

	public Client(Serveur s) {
		synchronized(mutex){
			id = cpt++;
		}
		serveur = s;

	}

	synchronized public void attendre() throws InterruptedException{
		while (!requeteServie){
			wait();
		}
	}

	synchronized public void requeteServie(ReponseRequete r){
		requeteServie = true;
		System.out.println(r.toString());
		notifyAll();
	}

	public void run() {
		for (int i=0; i<5; i++){
			try{
				serveur.soumettre(this, i);
				System.out.println("Client " + id + ": j'attends qu'on traite ma requête " + i);
				attendre();
				Thread.yield();
			}catch(InterruptedException e){
				System.out.println("Oula!!");
			}
		}
	}

	public int getId(){
		return id;
	}
}