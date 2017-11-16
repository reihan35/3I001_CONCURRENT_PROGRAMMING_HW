import java.util.ArrayList;
import java.lang.Integer;
import java.util.concurrent.locks.*;

public class EnsembleDonnees{
	private ArrayList<Integer> ensemble = new ArrayList<Integer>();
	private final ReentrantReadWriteLock lock= new ReentrantReadWriteLock(true);
 
	public void ajoutDonnee(Integer donnee, int id){
		lock.writeLock().lock();
		try{
			ensemble.add(donnee);
			System.out.println("Oh surprise! Le producteur " + id + " a ajouté l'élément " + donnee);
		}
		finally{
			lock.writeLock().unlock();
		}
	}

	public void effaceDonnee(Integer donnee, int id) throws ElementPasPresent{
		lock.writeLock().lock();
		try{ 
			int i = ensemble.lastIndexOf(donnee);

			if (i==-1){
				throw new ElementPasPresent("L'effaceur " + id + " a fait son zozo! L'élement " + donnee + " n'est pas présent!");
			}
			else{
				ensemble.remove(i);
				System.out.println("L'effaceur " + id + " a effacé l'élement " + donnee + " ;-)");
			}
		}
		finally{
			lock.writeLock().unlock();
		}
	}
	
	public void afficheDonnee(int id){
		lock.readLock().lock();
		try{
			System.out.println("Le lecteur " + id + " a lu. Son alien smiley est content ;-)");
			ensemble.forEach((donnee)->System.out.print(donnee + " "));
			System.out.println();
		}
		finally{
			lock.readLock().unlock();
		}
	}
}
