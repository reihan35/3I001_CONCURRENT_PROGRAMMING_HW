public class Gestionnaire{
	private MoteurVitre m1, m2;

	public Gestionnaire(MoteurVitre m1, MoteurVitre m2){
		Thread t1, t2;
		m1 = new MoteurVitre(Enum.Cote.GAUCHE);
		m2 = new MoteurVitre(Enum.Cote.DROITE);

		t1 = new Thread(m1);
		t2 = new Thread(m2);

		t1.start();
		t2.start();

		if (m1.getPosition() == Enum.Position.HAUTE){
			m1.demander(Enum.Operation.DESCENDRE);
		}

		if (m2.getPosition() == Enum.Position.HAUTE){
			m2.demander(Enum.Operation.DESCENDRE);
		}
	}

}
