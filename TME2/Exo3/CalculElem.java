public class CalculElem implements Runnable{
	int i;
	int j;
	MatriceEntiere m;
	MatriceEntiere m1;
	MatriceEntiere m2;

	/* i et j >= 1 */
	public CalculElem(int i, int j, MatriceEntiere m, MatriceEntiere m1, MatriceEntiere m2){
		this.i = i;
		this.j = j;
		this.m = m;
		this.m1 = m1;
		this.m2 = m2;
	}

	public void run(){
		try{
		   this.m.setElem(i-1, j-1, MatriceEntiere.produitLigneColonne(m1, i, m2, j));
 		}catch(TaillesNonConcordantesException e){
   		System.out.println(e.getMessage());
 		}
	}
	
}
