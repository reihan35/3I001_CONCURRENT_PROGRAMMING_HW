package ex14;

public enum Position {
	NORD(1), SUD(1);
	
	private final int index;
	
	private Position(int index){
		this.index = index;
	}
	
	public int index(){
		return index;
	}
}
