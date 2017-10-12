import java.awt.*;

public class DessineLigne2 implements Runnable{
	
	private Point pt1;
	private Point pt2;
	private graphique.Fenetre f;
	
	public DessineLigne2(Point pt1,Point pt2,graphique.Fenetre f){
		this.pt1=pt1;
		this.pt2=pt2;
		this.f=f;
	}
	
	public void run(){
		f.tracerPoint(pt1,"black");
		f.tracerPoint(pt2,"black");
		f.tracerLignePointAPoint(pt1,pt2,"black");
	}
}
