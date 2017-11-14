public class PoolHangars{
	private Hangar[] hangars;
	private int nbHangars;
	
	public PoolHangars(int nbHangars){
		this.nbHangars = nbHangars;
		hangars = new Hangar[nbHangars+1];
		
		for (int i=1; i<=nbHangars; i++){
			hangars[i] = new Hangar(); 
		}
	}
	
	/* pour récupérer un hangar */
	public Hangar getHangar(int i){
		int k=i;
		if (k == 0)
			k = 1;
		while ((!(hangars[k].est_libre())) && (k<=nbHangars))
			k++;
		System.out.println("Hangar libre : hangar numéro " + k);
		return hangars[k];
	}
	
	/* récupérer le premier hangar disponible 
	 public int getNumHangar(){
		int i=0;
		while ((i<nbHangars) && (!(hangars[i].est_libre()))){
			i++;
		}
		if (i<nbHangars)
			return i;
	}*/
}


