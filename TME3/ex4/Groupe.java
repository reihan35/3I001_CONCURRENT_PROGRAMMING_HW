public class Groupe implements Runnable{
  private int nb;
  private Salle s;
  private static int cpt=0;
  private int id;

  public Groupe(int nb, Salle s){
    this.nb = nb;
    this.s = s;
    this.id = cpt++;
  }

  public void run(){
    System.out.println("Le groupe " + id + " veut réserver.");
    if (s.reserver(nb)){
      System.out.println("Le groupe " + id + " a reservé " + nb + " places.");
    }
    else{
      System.out.println("Plus de place libre pour accueillir " + nb + " personnes.");
    }
    System.out.println("Le groupe " + id + " a terminé sa réservation.");
  }

}

