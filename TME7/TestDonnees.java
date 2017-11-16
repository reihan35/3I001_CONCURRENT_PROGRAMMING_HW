public class TestDonnees{
	public static void main(String[] args){
		EnsembleDonnees e = new EnsembleDonnees();
		
		for (int i=0; i<2; i++){
			new Thread(new Lecteur(e)).start();
			new Thread(new Producteur(e)).start();
			new Thread(new Effaceur(e)).start();
		}
	}
}
