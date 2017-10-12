public class Salle{  
 private boolean[][] placesLibres;
 private int nbRangs;
 private int nbPlacesParRang;

 public Salle(int nbRangs, int nbPlacesParRang){
		this.nbRangs = nbRangs;
		this.nbPlacesParRang = nbPlacesParRang;
		placesLibres = new boolean[nbRangs][nbPlacesParRang];

		for (int i=0; i<nbRangs; i++){
			for (int j=0; j<nbPlacesParRang; j++){
				placesLibres[i][j] = true;
			}
		}
 }

	public boolean capaciteOK(int n){
		int k = 0;

		for (int i=0; i<nbRangs; i++){
			for (int j=0; j<nbPlacesParRang; j++){
				if (placesLibres[i][j]){
					k++;
				}
				if (k == n){
					return true;
				}
			}
		}
		return false;
	}

	public int nContiguesAuRangI(int n, int i){
		int k;
		int j = 0;	

		while ((j < nbPlacesParRang) && (!placesLibres[i][j])){
			j++;
		}

		if (j != nbPlacesParRang){
			k = 0;
			while ((k < nbPlacesParRang) && (placesLibres[i][k])){
				k++;
				if (k == n){
					return j;
				}
			}	
		}

		return -1;
	}

  public synchronized boolean reserverContigues(int n){
    int i = 0;

    while (i < nbRangs){
      int res = nContiguesAuRangI(n, i);
      if (res != -1){
        for (int j = res; j < res + n; j++){
          placesLibres[i][j] = false;
        }
        return true;
      }
      i++;
    }
    return false;
  }

  synchronized public boolean reserver(int n){
    boolean capacite = capaciteOK(n);
    int nbRes = 0;
    boolean res = true;

    if (!capacite){
      res = false;
    }
    else{
      boolean resContigue = reserverContigues(n);

      if (resContigue){
        System.out.println(toString());
      }
      else{
        int i=0;

        while ((i < nbRangs) && (nbRes < n)){
          int j = 0;
          while ((j < nbPlacesParRang) && (nbRes < n)){
            if (placesLibres[i][j]){
              placesLibres[i][j] = false;
              nbRes++;
            }
            j++;
          }
          i++;
        }
      }
     }

		System.out.println(toString());
    return res;
  }

  public String toString(){
		String s = "";
		for (int i=0; i<nbRangs; i++){
			for (int j=0; j<nbPlacesParRang; j++){
				if (placesLibres[i][j]){
					s += "| ";
				}else{
					s += "X ";
				}
			}
			s += "\n";
		}
		return s;
  }
}

