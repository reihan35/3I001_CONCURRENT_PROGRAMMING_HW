public class Gestionnaire implements Runnable{
	private MoteurVitre m1, m2;

	public Gestionnaire(MoteurVitre m1, MoteurVitre m2){
		Thread t1, t2;
		Enum.Position pos1, pos2;

		m1 = new MoteurVitre(Enum.Cote.GAUCHE);
		m2 = new MoteurVitre(Enum.Cote.DROITE);

		t1 = new Thread(m1);
		t2 = new Thread(m2);

		t1.start();
		t2.start();

		pos1 = m1.getPosition();
		pos2 = m2.getPosition();

		m1.demander(Enum.Operation.DESCENDRE);
		m2.demander(Enum.Operation.DESCENDRE);

		while (m1.getPosition() != Enum.Position.BASSE && m2.getPosition() != Enum.Position.BASSE){
			/* il faudrait attendre pour chaque vitre qui appeleraient notify quand elles ont fini l'exécution */
			System.out.println("Le gestionnaire attend impatiemment ...");
		}

		System.out.println("Dépliage de la capote du spider !!!!");
/*
		if (pos1 == Enum.Position.HAUTE){
			m1.demander(Enum.Operation.MONTER);
		}
		 
		if (pos2 == Enum.Position.HAUTE){
			m2.demander(Enum.Operation.DESCENDRE);
		}
*/
		t1.interrupt();
		t2.interrupt();
	}

}
