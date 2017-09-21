/* Fatemeh Hamissi et Laura Nguyen*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;


public class TestMatriceEntiere{
	public static void main(String[] args){
		String fichier = (System.console()).readline();
		
		try{
			File f = new File(fichier);
			MatriceEntiere m = new MatriceEntiere(f);
			f.close();
			System.out.println(m.toString());
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		catch(IOException e){
			System.out.println("IO error");
		}
	}
}
