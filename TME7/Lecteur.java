public class Lecteur implements Runnable{
	private int id;
	private final Object mutex = new Object();
	private static int cpt = 1;
	private EnsembleDonnees ensemble;
	
	public Lecteur(EnsembleDonnees e){
		synchronized(mutex){
			id = cpt++;
		}
		ensemble = e;
	}
	
	public void run(){
		for (int i=0; i<3; i++){
			ensemble.afficheDonnee(id);
			try{
				Thread.sleep(5);
			}catch(Exception e){
				System.out.println("Cela ne devrait jamais arriver ;-)");
			}
		}
	}
}
