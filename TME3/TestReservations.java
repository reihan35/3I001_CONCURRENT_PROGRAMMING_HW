public class TestReservations{
	public static void main(String[]args){
		Salle s = new Salle(2,2);
		
		Thread t1=new Thread(new Groupe(3,s));
		t1.run();
		Thread t2=new Thread(new Groupe(2,s));
		t2.run();
		Thread t3=new Thread(new Groupe(1,s));
		t3.run();
		
		/*System.out.println(s.nContiguesAuRangI(3,2));
		System.out.println(s.reserverContigues(2));
		System.out.println(s.reserver(2));*/
		
	}
}
