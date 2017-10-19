public class TestChargement{
	public static void main(String[] args){
		AleaStock stock = new AleaStock(10);
		Chariot chariot = new Chariot(5, 100);
		Chargeur chargeur = new Chargeur(chariot, stock);
		Thread t = new Thread(chargeur);
		t.start();
		
	}
}
