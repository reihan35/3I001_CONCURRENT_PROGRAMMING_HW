package Exo11;

public class Test {

	public static void main(String[] args) {
		int NB_CLIENTS = 10;
		Client[] clients = new Clients[NB_CLIENTS]
		Thread tserveur = Thread(new Serveur());
		tserveur.start();
		for (int i=0; i<NB_CLIENTS; i++){
			clients[i] = new Thread(new Client());
			clients[i].start();
		}
		for (int i=0; i<NB_CLIENTS; i++){
			clients[i].join();
		}
		tserveur.join();
	}

}
