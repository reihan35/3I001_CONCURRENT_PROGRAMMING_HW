public class Hangar{
	private static int cpt=0;
	private int id;
	private static Object mutex;
	private boolean libre;
	
	public Hangar(){
		synchronized(mutex){
			id=cpt++;
		}
		libre=true;
	}
	
	/* retourne vrai si le hangar n'est pas occupé, faux sinon */
	public boolean est_libre(){
		return (libre==true);
	}

	/* appelée par la locomotive lorsqu'elle rentre dans le hangar */
	public void entrer(int idLoco){
		libre=false;
		System.out.println("La locomotive " + idLoco + " est rentrée dans le hangar " + id + ". Notre alien smiley est content :-)");
	}
}

