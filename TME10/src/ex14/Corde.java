package ex14;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Corde {
	
	/* Q1
	private Position sensUtilisation = null;
	private int cpt = 0;
	
	public synchronized void acceder(Position position) throws InterruptedException{
		while (sensUtilisation != position && sensUtilisation != null){
			wait();
		}
		if (sensUtilisation == null){
			sensUtilisation = position;
		}
		cpt++;
	}
	
	public synchronized void lacher(Position position){
		cpt--;
		if (cpt == 0){
			sensUtilisation = null;
			notifyAll();
		}
	}*/
	
	/*Q2
	
	private Position sensUtilisation = null;
	private int cpt = 0;
	private ReentrantLock lock = new ReentrantLock();
	private final Condition accesOk = lock.newCondition();
	private final Condition capOk = lock.newCondition();
	
	public void acceder(Position position) throws InterruptedException{
		
		lock.lock();
	
		try{
			while(sensUtilisation == position && cpt>5 ){
				System.out.println("Pas assez de place");
				capOk.await();
			}
			
			while (sensUtilisation != position && sensUtilisation != null){
				accesOk.await();
			}
			
			if (sensUtilisation == null){
				sensUtilisation = position;
			}
			cpt++;
		}
		finally{
			lock.unlock();
		}
	}
	
	public void lacher(Position position){
		lock.lock();
		try{
			cpt--;
			if(cpt==0){
				sensUtilisation = null;
				accesOk.signalAll();
				capOk.signalAll();
			}
		}
		
		finally{
			lock.unlock();
		}
	}
	*/
	
	private Position sensUtilisation = null;
	private int cpt = 0;
	
	private Semaphore[] semAcces = new Semaphore[2];
	private Semaphore mutex = new Semaphore(1);
	private Semaphore semCond = new Semaphore(0);
		
	public Corde(){
		semAcces[0] = new Semaphore(5);
		semAcces[1] = new Semaphore(5);
	}
	
	public void acceder(Position position) throws InterruptedException{
		mutex.acquire(); 
		if (cpt == 0){
			semAcces[(position.index()+1)%2].acquire();
		}
		cpt++;
		mutex.release();
		semAcces[position.index()].acquire();
			
	}
}
