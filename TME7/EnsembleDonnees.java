import java.util.ArrayList;
import java.lang.Integer;

public class EnsembleDonnees{
	private ArrayList<Integer> ensemble = new ArrayList<Integer>();
 
	public void ajoutDonnee(Integer donnee){
		ensemble.add(donnee);
	}

	public void effaceDonnee(Integer donnee) throws ElementPasPresent{
			
			int i = ensemble.lastIndexOf(donnee);

			if (i==-1){
				throw new ElementPasPresent();

			}
			
			else{
				ensemble.remove(i);

			}


		}
}
