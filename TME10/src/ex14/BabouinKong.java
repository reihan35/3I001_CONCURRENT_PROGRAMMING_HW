package ex14;

public class BabouinKong extends Babouin implements Runnable {
	
	public BabouinKong(Corde c){
		super(c, null);
	}
	
	public void run() {
		try{
			super.laCorde.accederKong();
			System.out.println("Kong a pris la corde.");
			super.traverser();
			System.out.println("Kong est arrive.");
			super.laCorde.lacherKong();
		}catch(InterruptedException e){
			System.out.println("Pb babouin !");
		}
	}
}
