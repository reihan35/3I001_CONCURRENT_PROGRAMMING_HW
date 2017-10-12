public class Client implements Runnable {
	private static int cpt=0;
	private static final Object mutex = new Object();
	private int id;
	private Salon s;
	
	public Client (Salon s){
		synchronized(mutex){
			id=cpt++;
		}
		this.s=s;
	}
		
	synchronized public void notifier(){
		notify();
	}
	
	synchronized public void attendre(){
		wait();
	}
		
	public void run(){
		s.arrivee_client(this);
			
		while(!s.getDisponibilite()){
			this.attendre();
		}
		s.coiffer();
	}
		

}
