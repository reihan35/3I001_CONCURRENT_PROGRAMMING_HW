public class TestChargementDechargement{
	public static void main(String[] args){
		AleaStock stock = new AleaStock(10);
		Chariot chariot = new Chariot(5, 100);
		Chargeur chargeur = new Chargeur(chariot, stock);
		Dechargeur dechargeur = new Dechargeur(chariot);
		Thread tChargeur = new Thread(chargeur);
		Thread tDechargeur = new Thread(dechargeur);
		tChargeur.start();
		tDechargeur.start();
	}
}
