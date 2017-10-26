public class PoolHangars{
	private int[] hangars;
	private int nbHangars;
	
	public PoolHangars(int nbHangars){
		this.nbHangars = nbHangars;
		hangars = new Hangar[nbHangars];
		
		for (int i=0; i<nbHangars; i++){
			hangars[i] = new Hangar(); 
		}
	}
	
	/* pour récupérer un hangar */
	private Hangar getHangar(int i){
		return hangars[i];
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


