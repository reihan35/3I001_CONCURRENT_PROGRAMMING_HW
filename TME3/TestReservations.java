public class TestReservations{
	public static void main(String[]args){
		
			
		Salle s = new Salle(5,5);
		

		System.out.println(s.toString());
		Thread t1=new Thread(new Groupe(3,s));
		t1.run();
		System.out.println(s.toString());

		System.out.println(s.toString());
		Thread t2=new Thread(new Groupe(10,s));
		t2.run();
		System.out.println(s.toString());

		Thread t3=new Thread(new Groupe(3,s));
		t3.run();
		System.out.println(s.toString());

		Thread t4=new Thread(new Groupe(4,s));
		t4.run();
		System.out.println(s.toString());

		
		
	}
}
