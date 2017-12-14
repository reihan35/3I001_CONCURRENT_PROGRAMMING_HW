package Exo12;

import java.awt.Point;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import graphique.Fenetre;

public class TestVonKoch {
	public static void main (String[] args) {
		final int XMAX = 400;
		final int YMAX = 400;
		ExecutorService pool = Executors.newFixedThreadPool(5);
		
		final Fenetre f = new Fenetre(XMAX, YMAX, "von Koch");
		Point v = new Point();
		v.setLocation(XMAX/2, YMAX/8);
		
		Point u = new Point();
		u.setLocation(v.x - (3.0 * XMAX/10.0), v.y + (0.5196 * XMAX));
		Point w = new Point();
		w.setLocation(v.x + (3.0 * XMAX/10.0), u.y);
		VonKochMono vk = new VonKochMono(f, u, v, w, pool);
		
		while (!pool.isTerminated()){
			try{
				Thread.sleep(10);
			} catch(InterruptedException e){
				System.out.println("Oula!!!");
			}
		}
	}
}
