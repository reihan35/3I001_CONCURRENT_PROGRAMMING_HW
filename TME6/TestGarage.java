public class TestGarage{
	public static void main(String[] args){
		SegTournant sTournant = new SegTournant();
		SegAccueil sAccueil = new SegAccueil();
		PoolHangars pHangars = new PoolHangars(10);

		new Thread(sTournant).start();

		for (int i=0; i<4; i++){
			new Thread(new Loco(sAccueil, sTournant, pHangars)).start();
		}
	}
}
