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

	/*	MatriceEntiere m1 = new MatriceEntiere(3, 4);
		m1.initialise_zero();
		System.out.println(m1.toString()); */
		System.out.println("Enter the filenames:");
		
		String fichier1 = (System.console()).readLine();
		String fichier2 = (System.console()).readLine();
		
		try{
			File f1 = new File(fichier1);
			File f2 = new File(fichier2);
			MatriceEntiere m1 = new MatriceEntiere(f1);
			MatriceEntiere m2 = new MatriceEntiere(f2);
			System.out.println(m1.toString());
	/*		m2.transposee(); */
			System.out.println(m2.toString());

/*
			try{
				m1.somme(m2);
			}catch(TaillesNonConcordantesException e){
				System.err.println(e.getMessage());
			} */

			m1.produitScalaire(2);
			m2.produitScalaire(3);

			try{
				m1.produit(m2);
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
