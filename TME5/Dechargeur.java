public class Dechargeur implements Runnable{
	private Chariot chariot;
	
	public Dechargeur(Chariot chariot){
		this.chariot = chariot;
	}
	
	public void run(){
		/* tant qu'il reste des marchandises */
		while (!chariot.plusDeStock()){
			try{
				chariot.decharger();
			}catch(InterruptedException e){
				System.out.println("Alors évidemment on a fini avec un air un petit peu dédaigneux :-)");
			}
		}
		System.out.println("Le déchargeur fait ciao ciao!");
	}
	
}