public class SegTournant implements Runnable{
	private int position; /* position actuelle */
	private boolean est_appele;
	private int destination;  
	private boolean libre;
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition stOccupe = lock.newCondition(); /* locomotive attend dessus tant que le segment tournant est occupé */
	private final Condition positionOk = lock.newCondition(); /* locomotive attend dessus lorsque la position n'est pas ok */
	private final Condition aAppel = lock.newCondition(); /* segment tournant attend dessus lorsqu'il n'a pas d'appel  */
	private final Condition entree = lock.newCondition(); /* segment attend que locomotive soit entrée  */ 
	private final Condition vide = lock.newCondition(); /* segment attend que locomotive soit sortie */
	
	public SegTournant(){
		position=0;
		est_appele=false;
		destination=0;
		libre=true;
	}
	
	/* retourne la position du segment tournant */
	public int getPosition(){
		return position;
	}
	
	/* appelée par la locomotive pour avoir le segment tournant 
	   la locomotive se bloque si le segment tournant est occupé */
	public void appeler(int dest) throws InterruptedException{
		lock.lock();
		try{
			while(!libre){
				stOccupe.await();
			}
			est_appele=true;
			destination = dest; /* le segment tournant doit aller en position dest */
			aAppel.signal(); /* réveille le segment tournant */
		}
		finally{
			lock.unlock();
		}
	}
		
	/* appelée par la locomotive pour attendre que le segment tournant aille en position destination */
	public void attendrePositionOK() throws InterruptedException{
		lock.lock();
		try{
			/* tant que le segment tournant n'est pas en position destination, on attend */
			while(position != destination){
				positionOk.await();
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	/* appelée par la locomotive lorsqu'elle rentre sur le segment */
	public void entrer(int id){
		lock.lock();
		try{
			libre = false;
			entree.signal(); /* réveille le segment tournant qui attend une entrée */
		}finally{
			lock.unlock();
		}
	}
	
	/* appelée par la locomotive lorsqu'elle sort du segment */
	public void sortir(int id){
		lock.lock();
		try{
			libre = true;
			vide.signal(); /* réveille le segment tournant qui attend une entrée */
		}finally{
			lock.unlock();
		}
	}
	
	/* le segment tournant attend qu'on l'appelle */
	public void attendreAppel() throws InterruptedException{
		lock.lock();
		try{
			/* tant qu'aucune locomotive ne l'a appelé, attend sur aAppel */
			while(!est_appele){
				aAppel.await();
			}
		}finally{
			lock.unlock();
		
		}
	}
	
	/* Le segment tournant attend que la locomotive soit sortie */
	public void attendreVide() throws InterruptedException{
		lock.lock();
		try{
			while(!libre){
				vide.await();
			}
			stOccupe.signal();
		}finally{
			lock.unlock()
		}
	}
	
	public void seDeplacer(){
		Thread.sleep(500);
		position = destination;
	}
}

