package Exo11;

public class Client implements Runnable {
	private Serveur s;
	private int id;
	private int type;
	/*private boolean requeteTraitee = false;*/
	
	public Client(Serveur s, int id){
		this.s = s;
		this.id = id;
		if ((id % 3) != 0)
			this.type = 1;
		else
			this.type = 2;
	}
	
	public void requeteServie(ReponseRequete r){
		
	}
	
	public void run() {
		for (int i=0; i<5; i++){
			s.soumettre(this, i, type);
		/*	while (!requeteTraitee)
				attendre();*/
		}
	}

}
