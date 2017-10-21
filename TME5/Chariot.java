import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class Chariot{
	private ArrayList<AleaObjet> marchandises;
	private float poidsMax;
	private int nbMax;
	private float poids;
	private int nbMarchandises;
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition charger = lock.newCondition();
	private final Condition decharger = lock.newCondition();
	private boolean chargementFini; 	
	private boolean stockVide;

	public Chariot(int nbMax,int poidsMax){
		this.marchandises=new ArrayList<AleaObjet>();
		this.nbMax=nbMax;
		this.poidsMax=poidsMax;
		this.poids = 0;
		this.nbMarchandises = 0;
		this.chargementFini = false;
		this.stockVide = false;
	}


	/* appelée par le chargeur pour empiler ses marchandises */
	public void empiler(AleaObjet marchandise) throws InterruptedException{
		lock.lock();
		try{
			if (marchandise.getPoids() == -1){
				System.out.println("Le chargeur est parti. Ciao ciao le chargeur!");
				stockVide = true;
				chargementFini = true;
				System.out.println("Avant de partir, le chargeur réveille le déchargeur.");
				decharger.signalAll();
			}else{
				System.out.println("Oh wtf! Il y a " + nbMarchandises + " marchandises sur le chariot!");
		
				/* tant que le poids ou le nombre de marchandises a atteint sa capacité maximale */
				while (chargementFini){
					System.out.println("Le chargeur attend impatiemment... Diable!");
					charger.await(); /* le chargeur attend */		
				}
	
				poids += marchandise.getPoids();
				nbMarchandises++;
				System.out.println("Le chargeur a empilé la marchandise de poids " + poids);
		
				if ((nbMarchandises == nbMax) || (poids >= poidsMax)){
					chargementFini = true;
					System.out.println("Oh surprise! Le déchargeur est réveillé!");
					decharger.signalAll(); /* on réveille le déchargeur */
				}
			}
		}finally{
			lock.unlock();
		}
	}	

	public void decharger() throws InterruptedException{
		lock.lock();
		
		try{
			/* tant que le poids ou le nombre de marchandises n'a pas atteint sa capacité maximale*/
			while (!chargementFini){
				System.out.println("Le déchargeur attend impatiemment, il préfère en boire :-(");
				decharger.await(); /* le déchargeur attend */
			}

			/* Déchargement du chariot */
		
			poids = 0;
			nbMarchandises = 0;
			marchandises.clear();
			chargementFini = false;
			System.out.println("Le déchargeur a tout déchargé : notre alien smiley est content :-)");
			if (!stockVide){
				System.out.println("Paf! Le chargeur est réveillé! Bam!");
				charger.signalAll(); /* réveille le chargeur */
			}
		}finally{
			lock.unlock();
		}
	}

	public boolean plusDeStock(){
		return stockVide;
	}
//	public void empiler(AleaObjet marchandise) throws InterruptedException{
//		lock.lock();
//		try{
//			System.out.println("Oh wtf! Il y a " + nbMarchandises + " marchandises sur le chariot!");
//			/* tant que le poids ou le nombre de marchandises a atteint sa capacité maximale */
//			while ((poids >= poidsMax) || (nbMarchandises == nbMax)){
//				System.out.println("Oh surprise! Le déchargeur est réveillé!");
//				decharger.signalAll(); /* on réveille le déchargeur */
//				System.out.println("Le chargeur attend impatiemment... Diable!");
//				charger.await(); /* le chargeur attend */		
//			}
//			
//			poids += marchandise.getPoids();
//			nbMarchandises++; 
//			marchandises.add(marchandise);
//			System.out.println("Le chargeur a empilé la marchandise de poids " + poids);
//		}finally{
//			lock.unlock();
//		}
//	}
	
//	public void decharger() throws InterruptedException{
//		lock.lock();
//		
//		try{
//			/* tant que le poids ou le nombre de marchandises n'a pas atteint sa capacité maximale*/
//			while ((poids <= poidsMax) && (nbMarchandises != nbMax)){
//				System.out.println("Le déchargeur attend impatiemment, il préfère en boire :-(");
//				decharger.await(); /* le déchargeur attend */
//			}
//			/* Déchargement du chariot */
//			
//			poids = 0;
//			nbMarchandises = 0;
//			marchandises.clear();
//			System.out.println("Le déchargeur a tout déchargé : notre alien smiley est content :-)");
//			System.out.println("Paf! Le chargeur est réveillé! Bam!");
//			charger.signalAll(); /* réveille le chargeur */
//		}finally{
//			lock.unlock();
//		}
//	}
}
