package exo13;

public class TestServeur {

	public static void main(String[] args) {
		int NB_CLIENTS = 2;
		Serveur s = new Serveur(2, NB_CLIENTS*5);
		new Thread(s).start();
		Thread[] t_clients = new Thread[NB_CLIENTS];

		for (int i=0; i<NB_CLIENTS; i++){
			t_clients[i] = new Thread(new Client(s));
			t_clients[i].start();
		}

		for (int i=0; i<NB_CLIENTS; i++){
			try {
				t_clients[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Main: j'ai terminé!");
	}

}