package ex1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Synchroniseur {
	private Perroquet[] perroquets = new Perroquet[3];
	private int nbEnregistres = 0;
	private int idAffichage = 0;
	private boolean reveil = false;
	private ReentrantLock lock = new ReentrantLock();
	private Condition enregistrement = lock.newCondition();
	private Condition depart = lock.newCondition();
	
	public void verrouiller(){
		lock.lock();
	}
	
	public void deverrouiller(){
		lock.unlock();
	}
	
	public void enregistrer(Perroquet p){
		verrouiller();
		perroquets[nbEnregistres] = p;
		nbEnregistres++;
		if (nbEnregistres == 3){
			enregistrement.signal();
		}

	}
	
	public void barriere() throws InterruptedException {
		verrouiller();
		while (!reveil){
			depart.await();
		}
		
	}
	
	public void reveil() throws InterruptedException{
		verrouiller();
		try{
			while (nbEnregistres < 3){
				enregistrement.await();
			}
			reveil = true;
			depart.signalAll();
		}finally{
			deverrouiller();
		}
	}
	
	public void reveillerUnPerroquet(){
		perroquets[idAffichage].reveiller();
		idAffichage = (idAffichage+1)%3;
	}

}
