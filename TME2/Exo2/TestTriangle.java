import java.awt.*;


public class TestTriangle{
	public static void main(String[] args){
		
		graphique.Fenetre f = new graphique.Fenetre(500,500,"Triangle");
		Point pt1 = new Point(20,20);
		Point pt2 = new Point(20,50);
		Point pt3 = new Point(50,50);
		
		f.remplir("purple");
		
		f.tracerPoint(pt1,"black");
		f.tracerPoint(pt2,"black");
		f.tracerPoint(pt3,"black");
			
		f.tracerLignePointAPoint(pt1,pt2,"black");
		f.tracerLignePointAPoint(pt1,pt3,"black");
		f.tracerLignePointAPoint(pt2,pt3,"black");
	}
}
