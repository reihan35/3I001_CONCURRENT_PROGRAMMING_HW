package exo13;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Serveur implements Runnable {
	private final ExecutorService exec;
	private CompletionService<ReponseRequete> cs;
	private Client client;
	private int numReq;
	private int nbReqTraitees;
	private int nbReqMax;

	private boolean occupe = false;
	private boolean requeteSoumise = false;

	private ReentrantLock lock = new ReentrantLock();
	private Condition soumission = lock.newCondition();
	private Condition aRequete = lock.newCondition();

	public Serveur(int nb_threads, int nbReqMax){
		exec = Executors.newFixedThreadPool(nb_threads);
		cs = new ExecutorCompletionService<ReponseRequete>(exec);
		nbReqTraitees = 0;
		this.nbReqMax = nbReqMax;
	}
	
	public void soumettre(Client client, int numReq) throws InterruptedException{
		lock.lock();
		try{
			while (occupe){
				System.out.println("Client " + client.getId() + ": le serveur est occupé, j'attends impatiemment...");
				soumission.await();
			}
			this.client = client;
			this.numReq = numReq;

			System.out.println("Client " + client.getId() + ": j'ai soumis ma requête " + numReq);
			this.occupe = true;
			this.requeteSoumise = true;
			aRequete.signalAll();
		}finally{
			lock.unlock();
		}
	}

	public void attendreRequete() throws InterruptedException{
		lock.lock();
		try{
			while (!requeteSoumise){
				System.out.println("Serveur: j'attends une requête, c'est long ...");
				aRequete.await();
			}
			System.out.println("Tayo tayo je vais traiter la requête " + numReq + " du client " + client.getId());
		}finally{
			lock.unlock();
		}
	}

	public void traiterRequete() throws InterruptedException, ExecutionException{
		lock.lock();
		try{
			cs.submit(new Servant(client, numReq));
			System.out.println("Serveur: j'ai relégué la requête " + numReq + " de " + client.getId() + " à un servant");
			
			ReponseRequete r = cs.take().get();
			Client c = r.getClient();
			c.requeteServie(r);
			
			nbReqTraitees++;
			occupe = false;
			requeteSoumise = false;
			soumission.signalAll();
		}finally{
			lock.unlock();
		}
	}

	public void run() {
		try{
			while(nbReqTraitees < nbReqMax){
				attendreRequete();
				traiterRequete();
			}
			System.out.println("Serveur: adios!");
			exec.shutdown();
		}catch(InterruptedException e){
			System.out.println("Serveur interrompu");
		}catch(ExecutionException e){
			System.out.println("Oula!!");
		}
	}
}