public class Chariot{
	private ArrayList<AleaObjet> marchandises;
	private float poidsMax;
	private int nbMax;
	private float poids;
	private int nbMarchandises;
	private final Lock lock = new ReentrantLock();
	private final Condition charger = l.newCondition();
	private final Condition decharger = l.newCondition();
	
	
	public Chariot(int nbMax,int poidsMax){
		this.marchandises=new ArrayList<AleaObjet>();
		this.nbMax=nbMax;
		this.poidsMax=poidsMax;
		this.poids = 0;
		this.nbMarchandises = 0;
	}
	
	/* appelée par le chargeur pour empiler ses marchandises */
	public void empiler(AleaObjet marchandise){
		lock.lock();
		try{
			/* tant que le poids ou le nombre de marchandises a atteint sa capacité maximale */
			while ((poids == poidsMax) || (nbMarchandises == nbMax)){
				charger.await(); /* le chargeur attend */
			}
			
			poids += marchandise.getPoids();
			nbMax++; 
			marchandises.add(marchandise);
			
		}finally{
			lock.unlock();
		}
	}
	
	public void decharger(){
		lock.lock();
		
		try{
			/* tant que le poids ou le nombre de marchandises n'a pas atteint sa capacité maximale*/
			while ((poids != poidsMax) && (nbMarchandises != nbMax)){
				decharger.await(); /* le déchargeur attend */
			}
			poids = 0;
			nbMarchandises = 0;
			marchandises.clear();
			charger.signalAll();
		}finally{
			lock.unlock();
		}
	}
}
