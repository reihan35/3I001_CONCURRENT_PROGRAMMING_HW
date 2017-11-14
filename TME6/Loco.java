public class Loco implements Runnable{
 	private int id;
 	private static int cpt=1;
	private static Object mutex = new Object();
	private SegAccueil sAccueil;
	private SegTournant sTournant;
	private PoolHangars pHangars;

 	public Loco(SegAccueil sA, SegTournant sT, PoolHangars pH){
		synchronized(mutex){
			id=cpt++;
		}
		sAccueil = sA;
		sTournant = sT;
		pHangars = pH;
 	}

 	public void run() {
		try {
			sAccueil.reserver();
			sTournant.appeler(0);
			sTournant.attendrePositionOK();
			sTournant.entrer(id);
			sAccueil.liberer();
			sTournant.attendrePositionOK();
			pHangars.getHangar( sTournant.getPosition() ).entrer(id);
			sTournant.sortir(id);
		}
		catch (InterruptedException e) {
			System.out.println("Loco " + id + " interrompue (ne devrait pas arriver)");
		}
	}
}
