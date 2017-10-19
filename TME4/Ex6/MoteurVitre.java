import java.util.Random;

public class MoteurVitre implements Runnable{
	private Enum.Cote c;
	private Enum.Position position;
	private Enum.Operation operation;
	private Random r;
	private String cote;
	private boolean demande;

	public MoteurVitre(Enum.Cote c){
		this.c = c;
		this.position = Enum.Position.HAUTE;
		this.operation = Enum.Operation.NIL;
		this.r = new Random();
		this.cote = (c == Enum.Cote.GAUCHE)?"gauche":"droite";
		this.demande = false;
	}

	synchronized public void attendre(){
		/* tant qu'il n'y a pas eu de demande */
		while (!demande){
			try{
				System.out.println("Le moteur de la vitre " + cote + " attend.");
				wait();
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	synchronized public void executer(){
		if (operation == Enum.Operation.MONTER){
			position = Enum.Position.HAUTE;
			System.out.println("La vitre " + cote + " va être montée");
		}else{
			if (operation == Enum.Operation.DESCENDRE){
				position = Enum.Position.BASSE;
				System.out.println("La vitre " + cote + " va être descendue");
			}
		}
		this.demande = false;
		this.operation = Enum.Operation.NIL;
	}

	public void run(){
		/* indéfiniment actif */
		while(true){
			attendre();
			System.out.println("Le moteur de la vitre " + cote + " vient de se réveiller");
			executer();

			/* temps nécessaire à l'opération*/
			try{
				int temps = 1 + r.nextInt(11);
				Thread.sleep(temps);
				System.out.println("L'opération a pris " + temps + " millisecondes.");
			}catch(InterruptedException e){
				System.out.println(e.getMessage());	
			}
		}
	}

	/* appelée par le gestionnaire pour demander une operation au moteur 
		 on y intègre pas l'exécution de l'opération car le temps nécessaire à l'opération doit être simulé dans l'exécution du moteur */
	synchronized public void demander(Enum.Operation operation){
		String op = (operation == Enum.Operation.MONTER)?"monter":"descendre";
		System.out.println("Le gestionnaire a demandé l'opération " + op + " sur la vitre " + cote);
		this.operation = operation;
		this.demande = true;
		
		notify();
	}

	/* permet de savoir si la vitre est en position haute ou basse */
	public Enum.Position getPosition(){
		return position;
	}
}
