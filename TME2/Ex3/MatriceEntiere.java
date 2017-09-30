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

	/* constructeur qui cree une matrice sans l'initialiser */
	public MatriceEntiere(int lignes, int colonnes){
		this.lignes = lignes;
		this.colonnes = colonnes;
		this.tableau = new int[lignes][colonnes];
	}

	/* construire qui cree une matrice 
		 et l'initialise avec les valeurs lues dans le fichier */
	public MatriceEntiere(File fichier) throws FileNotFoundException, IOException{
		BufferedReader in = null;
		
		in = new BufferedReader(new FileReader(fichier));
		Scanner sc = new Scanner(fichier);
			
		if (sc.hasNext()){
			this.lignes = Integer.parseInt(sc.nextLine());
			this.colonnes = Integer.parseInt(sc.nextLine());
			
			int i = 0;
			String currentline;
			this.tableau = new int[this.lignes][this.colonnes];
		
			/* lecture des lignes du fichier */
			while (sc.hasNext() && i<this.lignes){
				currentline = sc.nextLine(); /* String comportant la ligne lue */
				String[] tmp = currentline.split(" "); /* transformation en tableau */
				
				/* affectation des valeurs a la ligne i du tableau */
				for (int j=0; j<this.colonnes; j++){
					this.tableau[i][j] = Integer.parseInt(tmp[j]);
				}
				i++;
			}
		}
			
		in.close();
		
	}
	
	public void initialise_zero(){
		for (int i=0; i<this.lignes; i++){
			for (int j=0; j<this.colonnes; j++){
				this.tableau[i][j] = 0;	
			}
		}
	}

	public void transposee(){
		int[][] new_tab = new int[this.colonnes][this.lignes];
		String s = "";

		for (int i=0; i<this.lignes; i++){
			for (int j=0; j<this.colonnes; j++){
				new_tab[j][i] = this.tableau[i][j];	
			}
		}
	
		for (int i=0; i < this.colonnes; i++){
			for (int j=0; j<this.lignes; j++){
				s = s + new_tab[i][j] + " ";
			}
			s = s + "\n";
		}
		System.out.println(s);
	}

	public void somme(MatriceEntiere B) throws TaillesNonConcordantesException {
		if (this.lignes != B.getLignes() || this.colonnes != B.getColonnes()){
			throw new TaillesNonConcordantesException(this.lignes + " * " + this.colonnes + " != " + B.getLignes() + " * " + B.getColonnes());
		}
		else{
			int i;
			int j;
			String s = "";

			for (i=0; i<this.lignes; i++){
				for (j=0; j<this.colonnes; j++){
					int val = this.tableau[i][j] + B.getElem(i, j);
					s = s + val + " ";
				}
				s = s + "\n";
			}
			System.out.println(s);
		}
	}	

	public int getElem(int i, int j){
		return this.tableau[i][j];
	}	
	
	public void setElem(int i, int j, int val){
		this.tableau[i][j] = val;
	}

	public int getLignes(){
		return this.lignes;
	}

	public int getColonnes(){
		return this.colonnes;
	}

	public static int produitLigneColonne(MatriceEntiere m1, int i, MatriceEntiere m2, int j) throws TaillesNonConcordantesException {
		int	res = 0;
		if (m1.getColonnes() != m2.getLignes()){
			throw new TaillesNonConcordantesException(m1.getColonnes() + " != " + m2.getLignes());
		}
		else{
			for (int k=0; k<m1.getColonnes(); k++){
				res += m1.getElem(i-1, k) * m2.getElem(k, j-1);
			}
		}
		return res;
	}

	public String toString(){
		String s = "";
		for(int i=0; i<this.lignes; i++){
			for(int j=0; j<this.colonnes; j++){
				s = s + Integer.toString(tableau[i][j]) + " "; 
			}
			if (i != this.lignes - 1)
				s = s + "\n";
		}
		return s;
	}
			
}	
