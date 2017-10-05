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
		
		int nbr=0;
		int c=0;
		int j;
		
		for(j=0;j<nbPlacesParRang;j++){
			/*if(tableau[i][j]){
				c=j;*/
				while(tableau[i][j]&&nbr<n){
					c=j;
					nbr++;
				}
			/*}*/
		}
		
		if(nbr==n){
			return j-nbr;
		}
		
		else{
		
			return -1;		
		
		}

	}
	
	public synchronized boolean reserverContigues(int n){
		for(int i=0;i<nbRangs;i++){
			if(nContiguesAuRangI (n,i)!=-1){
				int c=nContiguesAuRangI (n,i);
				for(int j=c;j<n;j++){
					tableau[i][j]=false;
				}
				
				return true;
			}
			
		}
		return false;
	}
		
	
	public synchronized boolean  reserver(int n){
		boolean cap = capaciteOK(n);
		
		if(cap==true){
			
				for(int i=0;i<nbRangs;i++){
					for(int j=0;j<nbPlacesParRang;j++){
						if(tableau[i][j]==true){
							while(n>0){
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
	
}
