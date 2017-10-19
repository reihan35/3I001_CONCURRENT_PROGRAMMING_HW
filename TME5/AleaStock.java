public class AleaStock{
	private int taille;
	private AleaObjet[] stock;
	private int nbRestants;
	
	public AleaStock(int taille){
		this.taille=taille;
		this.nbRestants = taille;
		for(int x;x<this.taille;i++){
			stock[i]=AleaObjet(10,2);
		}
	}
	
	public AleaObjet getMarchandise(){
		AleaObjet marchandise = stock[nbRestants-1];
		nbRestants--;
		return marchandise;
	}
	
	public boolean estVide(){
		return (nbRestants == 0);
	}
	
}		

