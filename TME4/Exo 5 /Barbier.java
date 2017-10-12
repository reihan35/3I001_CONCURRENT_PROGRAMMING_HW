public class Brabier implements Runnable{
	
	private Salon s;
	
	public Barbier(Salon s){
		this.s=s;
	}
	
	public void run(){
		synchronized(s){
			try{
				while(true){
					while (pasDeClient()){
						wait();
					}
				}
			}catch(InterruptedException e){
				System.out.println("Le barbier se casse o/");
			}
		}
	}

}
