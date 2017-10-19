public class Chargeur implements Runnable{
	private Chariot chariot;
	private AleaStock stock;
	
	public Chargeur(Chariot chariot, AleaStock stock){
		this.chariot = chariot;
		this.stock = stock;
	}
	

	public void run(){
		while (!stock.estVide()){
			AleaObjet marchandise;
			marchandise = stock.getMarchandise();
			chariot.empiler();
		}
	}
	
}
