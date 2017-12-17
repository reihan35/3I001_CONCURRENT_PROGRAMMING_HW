package ex14;

import java.util.concurrent.Semaphore;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.ReentrantLock;

public class Corde {
	private Position sensUtilisation = null;
	private int cpt = 0;
	
	/* Q1 
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
	private ReentrantLock lock = new ReentrantLock();
	private final Condition accesOk = lock.newCondition();
	private final Condition capOk = lock.newCondition();
//	private final Condition passageKong = lock.newCondition();
//	private final Condition attenteKongPasse = lock.newCondition();
	
//	private boolean kongSurCorde = false;
	
	/* Q4 - lock 
	public void accederKong() throws InterruptedException{
		lock.lock();
		try{
			while (cpt > 0){
				passageKong.await();
			}
			kongSurCorde = true;
		}finally{
			lock.unlock();
		}
	}
	
	public void lacherKong(){
		lock.lock();
		try{
			kongSurCorde = false;
			attenteKongPasse.signalAll();
		}finally{
			lock.unlock();
		}
	}
	
	public void acceder(Position position, int id) throws InterruptedException{
		lock.lock();
		try{
			while (kongSurCorde){
				System.out.println("Babouin " + id + ": j'attends que Kong passe...");
				attenteKongPasse.await();
			}
			
			while(sensUtilisation == position && cpt>=5 ){
				System.out.println("Babouin " + id + ": pas assez de place");
				capOk.await();
			}
			
			while (sensUtilisation != position && sensUtilisation != null){
				System.out.println("Babouin " + id + ": pas mon sens");
				accesOk.await();
			}
			
			if (sensUtilisation == null){
				System.out.println("Babouin " + id + ": je change le sens");
				sensUtilisation = position;
			}
			cpt++;
		}
		finally{
			lock.unlock();
		}
	}
	
	public void lacher(Position position, int id){
		lock.lock();
		try{
			cpt--;
			if(cpt==0){
				System.out.println("Babouin " + id + ": je réveille ceux de l'autre côté + Kong");
				sensUtilisation = null;
				accesOk.signalAll();
				passageKong.signalAll();
			}
			if (cpt == 4){
				System.out.println("Babouin " + id + ": je réveille ceux qui n'avaient pas de place");
				capOk.signalAll();
			}
		}
		
		finally{
			lock.unlock();
		}
	} */
	
	/* Q3 et Q4 */
	private Semaphore[] semAcces = new Semaphore[2];
	private Semaphore mutex = new Semaphore(1);
	private Semaphore semCond = new Semaphore(0);
	
	private int cptBloques = 0;
	private boolean passageKong = false;	
	
	public Corde(){
		semAcces[0] = new Semaphore(5);
		semAcces[1] = new Semaphore(5);
	}
	
	public void accederKong() throws InterruptedException{
		mutex.acquire();
		while (cpt > 0){
			System.out.println("Kong: j'attends qu'il n'y ait plus personne sur la corde...");
			cptBloques++;
			mutex.release();
			semCond.acquire();
			mutex.acquire();
			System.out.println("Kong: je me réveille!");
		}
		passageKong = true;
		mutex.release();
	}
	
	public void lacherKong() throws InterruptedException{
		mutex.acquire();
		passageKong = false;
		System.out.println("Kong: je réveille " + cptBloques + " babouins");
		semCond.release(cptBloques);
		cptBloques = 0;
		mutex.release();
	}
	
	public void acceder(Position position, int id) throws InterruptedException{
		semAcces[(position.index())%2].acquire(); // bloqué tant que pas de place
		mutex.acquire(); 
		
		// tant que le sens n'est pas le bon
		while ((passageKong) || (sensUtilisation != position && sensUtilisation != null)){
			if (passageKong){
				System.out.println("Babouin " + id + ": je laisse Kong passer");
			}else{
				System.out.println("Babouin " + id + ": pas mon sens, bonne nuit");
			}
			cptBloques++;
			mutex.release();
			semCond.acquire(); // blocage sur ce sémaphore 
			mutex.acquire();
			System.out.println("Babouin " + id + ": je me réveille!");
		}
		
		if (sensUtilisation == null){
			System.out.println("Babouin " + id + ": je change le sens!");
			sensUtilisation = position;
		}

		cpt++;
		System.out.println(cpt + " babouins sur la corde");
		mutex.release();
	}
	
	public void lacher(Position position, int id) throws InterruptedException{
		mutex.acquire();
		try{
			cpt--;
			if (cpt == 0){
				System.out.println("Babouin " + id + ": je réveille " + cptBloques + " babouins");
				sensUtilisation = null;
				semCond.release(cptBloques); // on réveille tous les babouins bloqués
				cptBloques = 0;
			}
			semAcces[(position.index())%2].release();
		}finally{
			mutex.release();
		}
	} 
}
