import java.util.Random;

public class Effaceur implements Runnable {
	private int id;
	private final Object mutex = new Object();
	private static int cpt = 1;
	private EnsembleDonnees ensemble;
	private Random r = new Random();
	
	public Effaceur(EnsembleDonnees e){
		synchronized(mutex){
			id = cpt++;
		}
		ensemble = e;
	}
	
	public void run(){
		for (int i=0; i<3; i++){
			int val = r.nextInt(10);

			try{
				ensemble.effaceDonnee(new Integer(val), id);
			}catch(ElementPasPresent e){
				System.out.println(e.getMessage());
			}
			try{
				Thread.sleep(5);
			}catch(Exception e){
				System.out.println("Cela ne devrait jamais arriver ;-)");
			}
		}
	}
}
