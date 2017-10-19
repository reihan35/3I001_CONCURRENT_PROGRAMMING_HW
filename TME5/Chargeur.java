public class Chargeur implements Runnable{
	private Chariot chariot;
	private AleaStock stock;
	
	public Chargeur(Chariot chariot, AleaStock stock){
		this.chariot = chariot;
		this.stock = stock;
	}
	
	public void run(){
		/* tant qu'il reste des marchandises */
		while (!stock.estVide()){
			AleaObjet marchandise;
			marchandise = stock.getMarchandise();
			try{
				chariot.empiler(marchandise);
			}catch(InterruptedException e){
				System.out.println("Oh! Surprise!");
			}
		}
	}
	
}
