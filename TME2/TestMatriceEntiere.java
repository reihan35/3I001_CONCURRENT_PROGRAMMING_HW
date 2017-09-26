/* Fatemeh Hamissi et Laura Nguyen*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.io.Console;


public class TestMatriceEntiere{
	public static void main(String[] args){

		System.out.println("Enter a filename:");

		String fichier1 = (System.console()).readLine();
		String fichier2 = (System.console()).readLine();
		String fichier3 = (System.console()).readLine();
		String fichier4 = (System.console()).readLine();
	
		try{
			File f1 = new File(fichier1);
			File f2 = new File(fichier2);
			File f3 = new File(fichier3);
			File f4 = new File(fichier4);
			MatriceEntiere m1 = new MatriceEntiere(f1);
			MatriceEntiere m2 = new MatriceEntiere(f2);
			MatriceEntiere m3 = new MatriceEntiere(f3);
			MatriceEntiere m4 = new MatriceEntiere(f4);
			System.out.println(m1.toString());
			System.out.println("\n");
			System.out.println(m2.toString());
			System.out.println("\n");
			System.out.println(m3.toString());
			System.out.println("\n");
			System.out.println(m4.toString());
			System.out.println("\n");

			try{
				m1.somme(m2);				
				System.out.println("\n");
			}catch(TaillesNonConcordantesException e){
				System.out.println(e.getMessage());
			}
			try{
				int produit = MatriceEntiere.produitLigneColonne(m3, 2, m4, 3);
				System.out.println("Produit : " + produit);
				System.out.println("\n");
			}catch(TaillesNonConcordantesException e){
				System.out.println(e.getMessage());
			}
		}catch(FileNotFoundException e){
			System.err.println("File not found");
		}
		catch(IOException e){
			System.err.println("IO error");
		}
	}
}
