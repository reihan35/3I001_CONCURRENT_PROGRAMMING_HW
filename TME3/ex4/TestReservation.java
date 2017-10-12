public class TestReservation{
  public static void main(String[] args){
    Salle s = new Salle(6, 12);
    Thread g1 = new Thread(new Groupe(19, s));
    Thread g2 = new Thread(new Groupe(12, s));
    Thread g3 = new Thread(new Groupe(6, s));
    Thread g4 = new Thread(new Groupe(30, s));
    Thread g5 = new Thread(new Groupe(15, s));
    g1.start();
    g2.start();
    g3.start();
    g4.start();
    g5.start();
  }
}

