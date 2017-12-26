package ex15;

public class Restaurant {
	private int nbTablesDispo;
	
	public Restaurant(int nbTables){
		nbTablesDispo = nbTables;
	}
	
	public synchronized Integer reserver(int nbPersonnes){
		if (nbPersonnes > nbTablesDispo * 2){
			return null;
		}else{
			int numTable = nbTablesDispo;
			nbTablesDispo = nbTablesDispo - ((nbPersonnes/2) + (nbPersonnes%2));
			System.out.println("Il reste " + nbTablesDispo + " tables disponibles");
			return (Integer)numTable;
		}
	}
	
}