public class Salle{
	
	private boolean[][] tableau;
	private int nbRangs;
	private int nbPlacesParRang;
	
	
	public Salle(int nbRangs,int nbPlacesParRang){
	
	this.nbPlacesParRang=nbPlacesParRang;
	this.nbRangs=nbRangs;
	this.tableau = new boolean[nbPlacesParRang][nbRangs];
		for(int i=0;i<nbRangs;i++){
			for(int j=0;j<nbPlacesParRang;j++){
				tableau[i][j]=true;
			}
		}
	
	
	}
	
	
	public boolean capaciteOK(int n){
		int nbr=0;
		for(int i=0;i<nbRangs;i++){
			for(int j=0;j<nbPlacesParRang;j++){
				if(tableau[i][j]){
				nbr++;
				}
			}
		}
	
		if(nbr>n || nbr==n){
			return true;
		}
		
		else{
			return false;
		}
	}
	
	public int nContiguesAuRangI (int n, int i){
		
		int j=0;
		
		while(tableau[i][j]==false && j<nbPlacesParRang){
			j++;
		} // tant qu'on est pas arrivé au premier bloc true on parcours la ligne
		if(j<nbPlacesParRang){
		
		int c=j; //des qu'on l'a trouvé on le sauvgarde
		
		int k=c;
		
		while (k<nbPlacesParRang && tableau[i][k]){
			k++;
		}//on parcours la ligne tant qu'on est true

		if (k>n || k==n){
			return c; //si le nbr de true est sup ou egal a n on est bon et si non on retourne -1
		}
		
		}
		
		
			return -1;		
		

	}
	
	public synchronized boolean reserverContigues(int n){
		if(n<this.nbPlacesParRang || n==nbPlacesParRang){
			for(int i=0;i<nbRangs;i++){
				if(nContiguesAuRangI (n,i)!=-1){
					int c=nContiguesAuRangI (n,i);
					for(int j=c;j<n;j++){
						tableau[i][j]=false;
					}
					break;
					
				}
				
				else{
						return false;
					}
			
			}return true;
		}
		return false;
	}
		
	
	public synchronized boolean  reserver(int n){
		boolean cap = capaciteOK(n);
		
		if(cap==true){
			while(n>0){
				for(int i=0;i<nbRangs;i++){
					for(int j=0;j<nbPlacesParRang;j++){
						while(tableau[i][j]==true && n>0){
							tableau[i][j]=false;
							n--;						
						}
					}
				}
				
			
			}
			return true;
		}
		return false;	
	}

	public String toString(){
		String s = "";
		for(int i=0; i<this.nbRangs; i++){
			for(int j=0; j<this.nbPlacesParRang; j++){
				s = s + Boolean.toString(tableau[i][j]) + " "; 
			}
				s = s + "\n";
		}
		return s;
	}
	
}
