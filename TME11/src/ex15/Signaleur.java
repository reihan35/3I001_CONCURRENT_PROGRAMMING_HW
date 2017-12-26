package ex15;

public class Signaleur implements Runnable {
	private int id;
	
	public Signaleur(int id){
		this.id = id;
	}
	public void run() {
		System.out.println("Le groupe " + id + " est au complet et prêt à passer la commande!");
	}

}
