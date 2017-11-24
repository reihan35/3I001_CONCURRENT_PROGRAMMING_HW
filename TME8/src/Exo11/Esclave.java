package Exo11;

public class Esclave implements Runnable {
	private int id;
	private static int cpt=1;
	private static Object mutex = new Object();
	private Client c;
	private int numReq;
	private int type;
	
	
	public Esclave(Client c, int numReq, int type){
		synchronized(mutex){
			id = cpt++;
		}
		this.c = c;
		this.numReq = numReq;
		this.type = type;
	}
	
	public void run() {
		System.out.println("Esclave " + id + ": je traite la requête " + numReq + " du client " + c.getId() + " de type " + type);
		if (type == 1){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			while (true);
		}
		c.requeteServie(new ReponseRequete(numReq,c));
	}
	
	public int getId(){
		return id;
	}

}