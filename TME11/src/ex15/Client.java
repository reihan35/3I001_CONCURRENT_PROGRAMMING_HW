package ex15;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Client implements Runnable {
	private int idClient;
	private int idGroupe;
	private GroupeClients gr;
	private CyclicBarrier cb;
	private Random r = new Random();
	
	public Client(int idClient, int idGroupe, GroupeClients gr, CyclicBarrier cb){
		this.idClient = idClient;
		this.idGroupe = idGroupe;
		this.gr = gr;
		this.cb = cb;
	}
	
	public void run() {
		try{
			gr.reserver(idClient);
			Thread.sleep(r.nextInt(10));
			System.out.println("Client " + idClient + "(groupe " + idGroupe + "): je suis arrivé! J'attends...");
			cb.await();
		}catch(InterruptedException e){
			System.out.println("Client " + idClient + "(groupe " + idGroupe + "): plus de place je me casse");
		} catch (BrokenBarrierException e) {
			System.out.println("Client " + idClient + "(groupe " + idGroupe + "): barrière cassée");
		}
	}

}