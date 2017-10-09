 public class Groupe implements Runnable{
 	
	private static int cpt=1;
 	private	 static final Object mutex = new Object();
	private int id;
 	private int nb;
 	private Salle s;
 	
 	public Groupe(int nb,Salle s){
		synchronized(mutex){
			id=cpt++;
		} 		
		this.nb=nb;
 		this.s=s;
 	}
 	
 	public void run(){
 		
		if(s.reserverContigues(nb)){
			System.out.println("Group "+id+" a reservé "+nb+" place Contigues\n");
		}
		
 		else{
 			if (s.reserver(nb)){
 				System.out.println("Group "+id+" a reservé "+nb+" places \n");
 			}
 			else{
 		
 			if(s.reserver(nb)==false){
 				System.out.println("Plus de places ! desolee ");
 			}
 			}
 		}
 	
 		}
 	
 	
 }
