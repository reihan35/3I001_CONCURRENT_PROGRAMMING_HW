 public class Groupe implements Runnable{
 	
 	private	 static int id=0; //je vais corriger ca apres
 	private int nb;
 	private Salle s;
 	
 	public Groupe(int nb,Salle s){
 		this.nb=nb;
 		this.s=s;
 		id++;
 	}
 	
 	public void run(){
 		
		if(s.reserverContigues(nb)){
			System.out.println("Group "+id+" a reservé "+nb+" place Contigues\n");
		}
		
 		else{
 			if (s.reserverContigues(nb)){
 				System.out.println("Group "+id+" a reservé "+nb+" places Contigues\n");
 			}
 			else{
 		
 			if(s.reserver(nb)==false){
 				System.out.println("Plus de places ! desolee ");
 			}
 			}
 		}
 	
 		}
 	
 	
 }
