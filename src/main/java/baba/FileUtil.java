package baba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException; 

public class FileUtil {
	
	public static String read(String fileLoc){
		File file = new File(fileLoc);
		StringBuilder sb = new StringBuilder();
		if(file.exists()){
			try(BufferedReader br = new BufferedReader(new FileReader(file))){
				String line = br.readLine();
				if(line != null){
					sb.append(line).append("\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public static void move(String srcFile, String distFile){
		
	}
	
	public static void copy(String srcFile, String distFile){
		
	}
	
	public static void moveDir(String srcFile, String distFile){
		
	}
	
	public static void copyDir(String srcFile, String distFile){
		
	}
	
	

}
