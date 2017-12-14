package ex14;

import java.util.Random;

public class Babouin implements Runnable {
	private int id;
	private static int cpt = 1;
	private static Object mutex = new Object();
	private Corde laCorde;
	private Random r = new Random();
	private Position position;
	
	public Babouin(Corde c, Position pos){
		synchronized(mutex){
			id = cpt++;
		}
		laCorde = c;
		position = pos;
	}
	
	private void traverser() throws InterruptedException{
		Thread.sleep(r.nextInt(10));
	}

	public void run() {
		try{
			laCorde.acceder(position);
			System.out.println(this.toString() + " a pris la corde.");
			traverser();
			System.out.println(this.toString() + " est arrive.");
			laCorde.lacher(position);
		}catch(InterruptedException e){
			System.out.println("Pb babouin !");
		}
	}

	public String toString(){
		return "Babouin " + id;
	}
}