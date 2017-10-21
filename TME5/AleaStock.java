import java.util.Random;

public class AleaStock{
	private int taille;
	private AleaObjet[] stock;
	private int nbRestants;
	
	public AleaStock(int taille){
		this.taille=taille;
		this.nbRestants = taille;
		this.stock = new AleaObjet[taille+1];

		for(int i=0;i<this.taille;i++){
			stock[i]= new AleaObjet(10,2);
			System.out.println(stock[i].toString());
		}
	}
	
	/* appelée par le chargeur pour prendre des marchandises */
	public AleaObjet getMarchandise(){
		AleaObjet marchandise = stock[nbRestants-1];
		nbRestants--;
		return marchandise;
	}
	
	public boolean estVide(){
		return (nbRestants == 0);
	}
	
}		

