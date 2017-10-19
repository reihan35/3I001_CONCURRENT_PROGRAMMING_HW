import java.util.Random;

public class AleaObjet{
	private int min,max;
	private int poids;
	private Random r;
	
	public AleaObjet(int max,int min){
		this.min=min;
		this.max=max;
		this.r = new Random();
		this.poids = min + r.nextInt(max-min);
	}
	
	public int getPoids(){
		return this.poids;
	}
	
	public String toString(){
		return "Marchandise de poids "+this.poids;
	}
	
}
