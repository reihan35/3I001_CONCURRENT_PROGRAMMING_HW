package Exo11;

public class Test {

	public static void main(String[] args) {
		int NB_CLIENTS = 3;
		Serveur s = new Serveur();
		Thread[] clients = new Thread[NB_CLIENTS];
		Thread tserveur = new Thread(s);
		tserveur.start();
		for (int i=0; i<NB_CLIENTS; i++){
			clients[i] = new Thread(new Client(s, i));
			clients[i].start();
		}
		try{
			for (int i=0; i<NB_CLIENTS; i++){
				clients[i].join();
			}
			tserveur.join();
		}catch(InterruptedException e){};
	}

}
