public class TestSalon{
	public static void main(String[] args){
		Salon s = new Salon(10);
		Thread b = new Thread(new Barbier(s));
		Thread[] clients = new Thread[11];

		b.start();

		for (int i=0; i<11; i++){
			clients[i] = new Thread(new Client(s));
			clients[i].start();
		}

		for (int i=0; i<11; i++){
			try{
				clients[i].join();
			}catch(InterruptedException e){};
		}

		b.interrupt();
	}
}
