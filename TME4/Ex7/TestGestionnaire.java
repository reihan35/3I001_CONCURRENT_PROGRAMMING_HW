public class TestGestionnaire{
	public static void main(String[] args){
		MoteurVitre m1 = new MoteurVitre(Enum.Cote.GAUCHE);
		MoteurVitre m2 = new MoteurVitre(Enum.Cote.DROITE);
		Gestionnaire g = new Gestionnaire(m1, m2);
	}
}
