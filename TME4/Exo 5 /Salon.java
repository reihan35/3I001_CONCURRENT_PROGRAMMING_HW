public class Salon{
	
	private int capaciteSalon;
	private int nbClients;
	private ArrayList<Client> clients;
	private boolean barbierOccupe;
	
	public Salon(int cap){
			this.capaciteSalon = capaciteSalon;
			this.clients = new ArrayList<Client>();
			this.nbClients=0;
	}
	
	synchronized public void arrivee_client(Client c){
		if (nbClients < capaciteSalon){
			clients.add(c);
			nbClients++;
		
			if (nbClients == 1){
				notify();
			}
		}
	}
	
	synchronized public void coiffer(){
		barbierOccupe = true;
		Client nextClient;
		
		clients.remove(0);
		nbClients--;
		nextClient = clients.get(0);
		nextClient.notifier();
		barbierOccupe = false;
	}
	
	public boolean getDisponibilite(){
		return barbierOccupe;
	}
	
	public boolean pasDeclient(){
		return (nbClients == 0);
	}	
}
