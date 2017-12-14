package ex1;

import java.util.Random;

public class Perroquet implements Runnable {
	private String prenom;
	private Synchroniseur sync;
	private Random gen = new Random();
	private boolean aMonTour = false;
	
	public Perroquet(String prenom, Synchroniseur sync){
		this.prenom = prenom;
		this.sync = sync;
	}
	
	public synchronized void attendre() throws InterruptedException{
		while (!aMonTour){
			wait();
		}
	}
	
	public synchronized void reveiller(){
		aMonTour = true;
		notify();
	}
	
	public void run() {
		sync.enregistrer(this);
		System.out.println(prenom + " avant barrière");
		sync.deverrouiller();
		try {
			sync.barriere();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(prenom + " après barrière");
		while (true){
			try{
				attendre();
				aMonTour = false;
				System.out.println(prenom);
				Thread.sleep((gen.nextInt(3)+1) * 1000);
				sync.reveillerUnPerroquet();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
