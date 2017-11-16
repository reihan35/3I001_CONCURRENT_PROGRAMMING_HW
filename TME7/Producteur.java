import java.util.Random;

public class Producteur implements Runnable {
	private int id;
	private final Object mutex = new Object();
	private static int cpt = 1;
	private EnsembleDonnees ensemble;
	private Random r = new Random();
	
	public Producteur(EnsembleDonnees e){
		synchronized(mutex){
			id = cpt++;
		}
		ensemble = e;
	}
	
	public void run(){
		for (int i=0; i<3; i++){
			for (int j=0; j<4; j++){
				ensemble.ajoutDonnee(new Integer(r.nextInt(10)), id);
				Thread.yield();
			}
		}	
	}
}
