package Exo11;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Serveur implements Runnable {
	private Client c;
	private int numReq;
	private int type;
	private boolean dispo = true;
	private boolean recuReq = false;
	private boolean traitementFini = false;
	private ReentrantLock lock = new ReentrantLock();
	private Condition serveurOccupe = lock.newCondition();
	private Condition aReq = lock.newCondition();
	
	public void soumettre(Client c,int numReq,int type ){
		lock.lock();
		try{
			while (!traitementFini)
				serveurOccupe.await();
			
			this.c = c;
			this.numReq = numReq;
			this.type = type;
			this.dispo = false;
			aReq.signal();
			
		} finally {
			lock.unlock();
		}
	}
	
	public void attendreRequete(){
		lock.lock();
		try{
			while (!recuReq)
				aReq.await();
		
			dispo = false;
		} finally {
			lock.unlock();
		}
	}
	
	public void traiterRequete(){
		lock.lock();
		try{
			c.requeteServie(new ReponseRequete(numReq,c));
		} finally {
			lock.unlock();
		}
	}
	
	public void run(){
		try{
			while(true){
				attendreRequete();
				traiterRequete();	
			}
		}catch(InterruptedException e){
			System.out.println("Serveur interrompu");
		}
		
	}

}
