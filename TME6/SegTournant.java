public class SegTournant implements Runnable{
	private int position;
	private boolean libre;
	
	public SegTournant(){
		position=0;
	}
	
	
	public int getPosition(){
		return position;
	
	}
	
	
	public void appeler(int pos){
		position=pos;

	}
		
	public void attendrePositionOK(){
		sleep(5);
	}
	
	public void entrer(int id){
		

	}
}

