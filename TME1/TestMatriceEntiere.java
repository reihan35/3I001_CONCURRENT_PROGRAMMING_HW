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

		MatriceEntiere m1 = new MatriceEntiere(3, 4);
		m1.initialise_zero();
		System.out.println(m1.toString());
		System.out.println("Enter a filename:");
		
		String fichier = (System.console()).readLine();
		
		try{
			File f = new File(fichier);
			MatriceEntiere m2 = new MatriceEntiere(f);
			System.out.println(m2.toString());
			m2.transposee();
			System.out.println(m2.toString());

			try{
				m1.somme(m2);
			}catch(TaillesNonConcordantesException e){
				System.err.println(e.getMessage());
			}
		}catch(FileNotFoundException e){
			System.err.println("File not found");
		}
		catch(IOException e){
			System.err.println("IO error");
		}
	}
}
