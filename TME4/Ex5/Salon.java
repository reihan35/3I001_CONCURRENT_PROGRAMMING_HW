import java.util.ArrayList;

public class Salon{
	private int capaciteSalon; /* nombre maximum de clients que peut accueillir le salon */
	private int nbClients; /* nombre de clients qui attendent */
	private ArrayList<Client> clients; /* la liste de clients attendant de se faire coiffer */
	private boolean barbierOccupe; /* indique si le barbier est en train de coiffer ou non */
	
	public Salon(int capaciteSalon){
		this.capaciteSalon = capaciteSalon;
		this.clients = new ArrayList<Client>();
		this.nbClients = 0;
		this.barbierOccupe = false;
	}
	
	/* méthode appelée par le client quand il arrive dans le salon  
		synchronisée sur le moniteur du salon */
	synchronized public void arrivee_client(Client c){
		if (nbClients < capaciteSalon){
			clients.add(c);
			nbClients++;
			System.out.println("Le client " + c.getId() + " est rentré dans le salon.");
		
			/* pas de client avant */
			if (nbClients == 1){
				System.out.println("Le client " + c.getId() + " réveille le barbier");
				/* réveille le barbier qui est sur le moniteur du salon */
				notify();
			}
		}
	}
	
	/* appelée par le client lorsque c'est à son tour de se faire couper les cheveux  
		synchronisé sur le moniteur du salon */
	synchronized public void coiffer(){
		Client nextClient; /* le prochain client dans la file d'attente */
		Client currentClient;
		barbierOccupe = true; /* les autres clients doivent attendre */
		
		currentClient = clients.get(0);
		System.out.println("Le barbier coupe les tiff du client " + currentClient.getId() + ".");
		clients.remove(0);
		nbClients--;

		System.out.println("Ciao ciao client " + currentClient.getId() + "!");
		
		barbierOccupe = false;
		if (nbClients != 0){
			nextClient = clients.get(0);
			nextClient.notifier();
		}

	}

	public boolean salonPlein(){
		return (nbClients == capaciteSalon);
	}
	
	public boolean barbierOccupe(){
		return barbierOccupe;
	}

	public boolean pasDeClient(){
		return (nbClients == 0);
	}
}
