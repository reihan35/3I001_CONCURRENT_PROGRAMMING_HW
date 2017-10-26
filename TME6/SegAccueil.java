public class SegAccueil{
	private boolean libre; /* indique si le segment d'accueil est libre ou non */
	
	public SegAccueil(){
		libre = true;
	}
	
	/* appelée par la locomotive pour réserver le segment d'accueil */
	synchronized public void reserver(){
		 /* tant que le segment d'accueil est occupé */
		 while (!libre){
		 	System.out.println("Oh zut! Pas de place sur le segment d'accueil!");
		 	wait();
		 }
		 libre = false;
		 System.out.println("Boum! Le le le le la la la la locomotive rentre sur le segment d'accueil!");
	}
	
	/* appelée par la locomotive lorsqu'elle quitte le segment d'accueil */
	synchronized public void liberer(){
		libre = true;
		System.out.println("Diable! La locomotive sort du segment d'accueil et réveille les autres!"); 
		notifyAll();
	}
}


