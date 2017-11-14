import java.util.concurrent.locks.*;

public class SegTournant implements Runnable{
	private int position; /* position actuelle */
	private int destination;  
	private boolean est_appele; /* indique s'il a été appelé */
	private boolean libre; /* indique s'il est libre ou non */
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition stOccupe = lock.newCondition(); /* locomotive attend dessus tant que le segment tournant est occupé */
	private final Condition positionOk = lock.newCondition(); /* locomotive attend dessus lorsque la position n'est pas ok */
	private final Condition aAppel = lock.newCondition(); /* segment tournant attend dessus lorsqu'il n'a pas d'appel  */
	private final Condition entree = lock.newCondition(); /* segment attend que locomotive soit entrée  */ 
	private final Condition vide = lock.newCondition(); /* segment attend que locomotive soit sortie */
	
	public SegTournant(){
		position = 0;
		est_appele = false;
		destination = 0;
		libre = true;
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
				System.out.println("La locomotive attend impatiemment que le segment tournant soit disponible...");
				stOccupe.await();
			}
			System.out.println("Bim! La locomotive a été réveillée!");
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
				System.out.println("La locomotive attend impatiemment que le segment tourne...");
				positionOk.await();
			}
			System.out.println("Le segment tournant est prêt à amener la locomotive");
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
			System.out.println("Bam! La locomotive " + id + " est sur le segment tournant!");
			System.out.println("Paf! La locomotive " + id + " réveille le segment tournant!!");
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
			est_appele = false;
			System.out.println("La locomotive " + id + " sort du segment tournant: elle se sent comme l'alien smiley triste :-(");
			System.out.println("La locomotive " + id + " réveille le segment tournant avant de partir");
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
				System.out.println("Personne n'appelle le segment tournant, il attend impatiemment...");
				aAppel.await();
			}
			/* ici, une locomotive a appelé le segment */
		}finally{
			lock.unlock();
		}
	}
	
	/* le segment tournant attend l'entrée d'une locomotive  */
	public void attendreEntree() throws InterruptedException{
		lock.lock();
		try{
			/* tant qu'aucune locomotive ne l'a appelé, attend sur aAppel */
			while(libre){
				System.out.println("Personne sur le segment tournant, il attend impatiemment...");
				entree.await();
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
				System.out.println("Le segment tournant attend que la locomotive se casse");
				vide.await();
			}
			System.out.println("Le segment tournant n'est plus occupé, il réveille les locomotives!");
			stOccupe.signal();
		}finally{
			lock.unlock();
		}
	}
	
	public void seDeplacer() throws InterruptedException{
		lock.lock();
		try{
			System.out.println("Le segment tournant touuuuuuuuuuuurne...");
			Thread.sleep(500);
			System.out.println("Le segment tournant est arrivé!");
			position = destination;
			positionOk.signal();
		}finally{
			lock.unlock();
		}
	}

	public void run(){
		try{
			while (true) {
				attendreAppel();
				seDeplacer();
				attendreEntree();
				seDeplacer();
				attendreVide();
			}
		}catch(InterruptedException e){
			System.out.println("Terminaison sur interruption du segment tournant");
		}
	}
}

