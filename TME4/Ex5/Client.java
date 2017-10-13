public class Client implements Runnable {
	private static int cpt=0;
	private static final Object mutex = new Object();
	private int id;
	private Salon s;
	
	public Client (Salon s){
		synchronized(mutex){
			id = cpt++;
		}
		this.s = s;
	}
		
	/* appelé dans Salon pour réveiller le client
	   lorsque c'est le prochain à se faire coiffer */
	synchronized public void notifier(){
		notify();
		System.out.println("Le barbier a réveillé le client " + id + ".");
	}
	
	synchronized public void attendre(){
		try{
			System.out.println("Le client numéro " + id + " attend impatiemment.");
			wait();
		}catch(InterruptedException e){
			System.out.println(e.getMessage());
		}
	}
		
	public void run(){
		if (!s.salonPlein()){
			s.arrivee_client(this);
			
			try{
				Thread.sleep(5);
			}catch(InterruptedException e){};
			/* tant que le barbier est occupé */
			while(s.barbierOccupe()){
				this.attendre(); /* chaque client qui attend a son propre moniteur */
			}
			s.coiffer();
		}else{
			System.out.println("Plus de place dans le salon pour le client " + id + ".");
		}
	}

	public int getId(){
		return this.id;
	}
}
