package ex1;

import java.util.Random;

public class TestPerroquet {

	public static void main(String[] args) {
		Synchroniseur sync = new Synchroniseur();
		Thread[] t_perroquets = new Thread[3];
		String[] prenoms = {"Coco", "Jaco", "Rico"};
		
		for (int i=0; i<3; i++){
			t_perroquets[i] = new Thread(new Perroquet(prenoms[i], sync));
			t_perroquets[i].start();
		}
		
		try {
			sync.reveil();
			sync.reveillerUnPerroquet();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(2000 + new Random().nextInt(1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
