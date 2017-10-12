import java.awt.*;

public class TestDessine{
	public static void main(String[] args){
		
		graphique.Fenetre f = new graphique.Fenetre(500,500,"Triangle");
		
		
		Point pt1 = new Point(20,20);
		Point pt2 = new Point(20,50);
		Point pt3 = new Point(50,50);
		
		/*
		DessineLigne t1 = new DessineLigne(pt1,pt2,f);
		DessineLigne t2 = new DessineLigne(pt1,pt3,f);
		DessineLigne t3 = new DessineLigne(pt3,pt2,f);
		
		t1.run();
		t2.run();
		t3.run();
	
		*/
		Thread t1 = new Thread(new DessineLigne(pt1,pt2,f));
		Thread t2 = new Thread (new DessineLigne(pt1,pt3,f));
		Thread t3 = new Thread(new DessineLigne(pt3,pt2,f));
		
		t1.run();
		t2.run();
		t3.run();
	}
}
