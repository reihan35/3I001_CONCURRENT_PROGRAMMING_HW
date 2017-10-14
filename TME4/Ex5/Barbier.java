public class Barbier implements Runnable{
	private Salon s;
	
	public Barbier(Salon s){
		this.s = s;
	}

	public void run(){
		/* le barbier se synchronise sur le moniteur du salon */
		synchronized(s){
			try{
				while(true){
					/* le barbier attend les clients */
					while (s.pasDeClient()){
						System.out.println("Le barbier attend impatiemment ...");
						s.wait();
					}
					s.coiffer(); /* Il faut que ce soit le barbier qui appelle coiffer car c'est lui qui détient le moniteur quand il est réveillé (le client ne peut donc pas appeler la méthode) */
				}
			}catch(InterruptedException e){
				System.out.println("Ciao ciao le barbier o/");
			}
		}
	}

}
