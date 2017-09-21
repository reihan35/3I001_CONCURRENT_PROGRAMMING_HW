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
		String fichier = (System.console()).readLine();
		
		try{
			File f = new File(fichier);
			MatriceEntiere m = new MatriceEntiere(f);
			f.closeFile();
			System.out.println(m.toString());
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		catch(IOException e){
			System.out.println("IO error");
		}
	}
}
