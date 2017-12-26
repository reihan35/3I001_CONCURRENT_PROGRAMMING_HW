package ex15;

import java.util.Random;

public class TestRestaurant {

	public static void main(String[] args) {
		Restaurant r = new Restaurant(10);
		int nb_groupes = 2;
		Random gen = new Random();
		
		for (int i =0; i<nb_groupes; i++){
			new GroupeClients(r, i, 1+gen.nextInt(10));
		}
	}
}
