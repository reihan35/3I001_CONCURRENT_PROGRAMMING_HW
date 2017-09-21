/* Fatemeh Hamissi et Kim-Anh Laura Nguyen */
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MatriceEntiere{
	private int lignes;
	private int colonnes;
	private int[][] tableau;
	
	MatriceEntiere(int lignes, int colonnes){
		this.lignes = lignes;
		this.colonnes = colonnes;
		this.tableau = new int[lignes][colonnes];
	}
	
	MatriceEntiere(File fichier) throws FileNotFoundException, IOException{
		BufferedReader in = null;
		
		
		in = new BufferedReader(new FileReader(fichier));
		Scanner sc = new Scanner(fichier);
		
			
		if (sc.hasNext()){
			this.lignes = Integer.parseInt(sc.nextLine());
			this.colonnes = Integer.parseInt(sc.nextLine());
			
			int i = 0;
			String currentline;
			this.tableau = new int[this.lignes][this.colonnes];
			
			while (sc.hasNext() && i<this.lignes){
				currentline = sc.nextLine();
				String[] tmp = currentline.split(" ");
				
				for (int j=0; j<this.colonnes; j++){
					this.tableau[i][j] = Integer.parseInt(tmp[j]);
				}
				i++;
			}
		}
			
		in.close();
		
	}
	
	public int getElem(int i, int j){
		return this.tableau[i][j];
	}	
	
	public void setElem(int i, int j, int val){
		this.tableau[i][j] = val;
	}
	
	public String toString(){
		String s = "";
		for(int i=0; i<this.lignes; i++){
			for(int j=0; j<this.colonnes; j++){
				s = s + Integer.toString(tableau[i][j]);
			}
		}
		return s;
	}
			
}	
