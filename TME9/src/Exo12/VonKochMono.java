package Exo12;

import graphique.Fenetre;

import java.awt.Point;
import java.util.concurrent.ExecutorService;

public class VonKochMono {
	private final static double LG_MIN = 8.0;
	Fenetre f;
	
	public VonKochMono (Fenetre f, Point a, Point b, Point c, ExecutorService pool) {
		this.f = f;
		
		pool.execute(new Cote(f,b,a, pool));
		pool.execute(new Cote(f,a,c, pool));
		pool.execute(new Cote(f,c,b, pool));
		
	//	new Cote(f, b, a).tracer();
	//	new Cote(f, a, c).tracer();
	//	new Cote(f, c, b).tracer();
	}
}
