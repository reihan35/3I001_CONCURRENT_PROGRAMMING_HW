import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestProduitParallele{
	public static void main(String[] args){
		try{
			MatriceEntiere m1 = new MatriceEntiere(new File("donnees_produit1"));
			MatriceEntiere m2 = new MatriceEntiere(new File("donnees_produit2"));
			MatriceEntiere m = new MatriceEntiere(m1.getLignes(), m2.getColonnes());
			System.out.println(m1.toString());
			System.out.println(m2.toString());
			System.out.println(m.toString());
			for (int i=1; i<=m1.getLignes(); i++){
				for (int j=1; j<=m2.getColonnes(); j++){
					Thread t = new Thread(new CalculElem(i, j, m, m1, m2));	
					t.start();
				}
			}
			System.out.println(m.toString());
		}catch(FileNotFoundException e){
			System.out.println("Error : file not found");
		}catch(IOException e){
			System.out.println("Error : IO problem");
		}
	}
}
