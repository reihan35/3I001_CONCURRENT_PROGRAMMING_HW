package ex14;

public class TestBabouin {

	public static void main(String[] args) {
		Corde laCorde = new Corde();
		Babouin[] babouins = new Babouin[10];
		
		for (int i=0; i<babouins.length; i++){
			Position p;
			if (i%2 == 0){
				p = Position.NORD;
			}
			else{
				p = Position.SUD;
			}
			babouins[i] = new Babouin(laCorde, p);
			new Thread(babouins[i]).start();
		}
		new Thread(new BabouinKong(laCorde)).start();

	}

}
