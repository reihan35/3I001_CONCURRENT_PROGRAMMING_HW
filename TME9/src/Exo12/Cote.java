package Exo12;

import java.awt.Point;
import java.util.concurrent.ExecutorService;

import graphique.Fenetre;

public class Cote implements Runnable {
	private final static double LG_MIN = 8.0;
	private final Fenetre f;
	private final Point m, n;
	private ExecutorService pool;
	
	public Cote (Fenetre f, Point m, Point n, ExecutorService pool) {
		this.f = f;
		this.m = m;
		this.n = n;
		this.pool = pool;
	}
	
	public void run () {
		
		final double xa, ya, xb, yb, xc, yc;
		if ( Segment.longueur(m, n) > LG_MIN ) {
			xa = (2 * m.x + n.x) / 3.0;
			xc = (m.x + 2 * n.x) / 3.0;
			ya = (2 * m.y + n.y) / 3.0;
			yc = (m.y + 2 * n.y) / 3.0;
			xb = xa + ( xc - xa - (Math.sqrt(3.0) * (yc - ya)) ) / 2.0;
			yb = ya + ( yc - ya + (Math.sqrt(3.0) * (xc - xa)) ) / 2.0;
			Point a = new Point();
			a.setLocation(xa, ya);
			Point b = new Point();
			b.setLocation(xb, yb);
			Point c = new Point();
			c.setLocation(xc, yc);
			
			pool.execute(new Cote(f,m,a, pool));
			pool.execute(new Cote(f,a,b, pool));
			pool.execute(new Cote(f,b,c, pool));
			pool.execute(new Cote(f,c,n, pool));
			
		//	new Cote(f, m, a).tracer();
		//	new Cote(f, a, b).tracer();
		//	new Cote(f, b, c).tracer();
		//	new Cote(f, c, n).tracer();
		}
		else {
			Segment.tracer(f, m, n);
		}
	}
	
}
