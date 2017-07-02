package baba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator; 

public class FileUtil {
	
	public static String readAsString(String fileLoc){
		File file = new File(fileLoc);
		StringBuilder sb = new StringBuilder();
		if(file.exists()){
			try(BufferedReader br = new BufferedReader(new FileReader(file))){
				while(true){
					String line = br.readLine();
					if(line != null){
						sb.append(line).append("\n");
					}
					if(line == null){
						break;
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public static class FileIterable implements Iterable<String>{
		
		private Iterator<String> iterator;
		
		public FileIterable(Iterator<String> iterator){
			this.iterator = iterator;
		}

		@Override
		public Iterator<String> iterator() {
			return iterator;
		}
		
	}
	
	public static class FileIterator implements Iterator<String>{
		private BufferedReader br;
		private String token;
		public FileIterator(BufferedReader br) {
			this.br = br;
		}
		@Override
		public boolean hasNext() {
			try {
				token = br.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally{
				if(token==null){
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
	
					}
				}
			}
			return token!=null;
		}

		@Override
		public String next() {
			return token;
		}
		
	}
	
	public static Iterable<String> read(String fileLoc){
		File file = new File(fileLoc);
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return new FileIterable(new FileIterator(br));
	}
	
	public static void move(String srcFile, String distFile){
		
		InputStream inStream = null;
		OutputStream outStream = null;

	    	try{

	    	    File afile =new File(srcFile);
	    	    File bfile =new File(distFile);

	    	    inStream = new FileInputStream(afile);
	    	    outStream = new FileOutputStream(bfile);

	    	    byte[] buffer = new byte[1024];

	    	    int length;
	    	    //copy the file content in bytes
	    	    while ((length = inStream.read(buffer)) > 0){

	    	    	outStream.write(buffer, 0, length);

	    	    }

	    	    inStream.close();
	    	    outStream.close();

	    	    //delete the original file
	    	    afile.delete();

	    	    System.out.println("File is moved successful!");

	    	}catch(IOException e){
	    	    e.printStackTrace();
	    	}
	}
	
	public static void copy(String srcFile, String distFile){
		InputStream inStream = null;
		OutputStream outStream = null;

	    	try{

	    	    File afile =new File(srcFile);
	    	    File bfile =new File(distFile);

	    	    inStream = new FileInputStream(afile);
	    	    outStream = new FileOutputStream(bfile);

	    	    byte[] buffer = new byte[1024];

	    	    int length;
	    	    //copy the file content in bytes
	    	    while ((length = inStream.read(buffer)) > 0){

	    	    	outStream.write(buffer, 0, length);

	    	    }

	    	    inStream.close();
	    	    outStream.close();

	    	    System.out.println("File is copied successful!");

	    	}catch(IOException e){
	    	    e.printStackTrace();
	    	}		
	}
	
	public static void moveDir(String srcFile, String distFile){
		File srcFile2 = new File(srcFile);
		File distFile2 = new File(appendPath(distFile,srcFile.substring(srcFile.lastIndexOf('/')+1,srcFile.length())));
		if(srcFile2.exists()){
			if(srcFile2.isDirectory()){
				if(!distFile2.exists()){
					distFile2.mkdirs();
				}			
				for(String subPath: srcFile2.list()){
					moveDir(appendPath(srcFile,subPath), distFile2.getAbsolutePath());
				}
			}else{
				move(srcFile, distFile2.getAbsolutePath());
			}
			srcFile2.delete();
		}		
	}
	
	public static void copyDir(String srcFile, String distFile){
		File srcFile2 = new File(srcFile);
		File distFile2 = new File(appendPath(distFile,srcFile.substring(srcFile.lastIndexOf('/')+1,srcFile.length())));
		if(srcFile2.exists()){
			if(srcFile2.isDirectory()){
				if(!distFile2.exists()){
					distFile2.mkdirs();
				}			
				for(String subPath: srcFile2.list()){
					copyDir(appendPath(srcFile,subPath), distFile2.getAbsolutePath());
				}
			}else{
				copy(srcFile, distFile2.getAbsolutePath());
			}
		}
		
	}
	
	public static String appendPath(String...paths){
		StringBuilder fullPath = new StringBuilder();
		for(String path : paths ){
			fullPath.append(path).append("/");
		}
		return fullPath.substring(0, fullPath.length()-1);
		
	}
	
	public static File file(String loc){
		return new File(loc);
	}
	
	public static void main(String args[]){
		for(String line : read("D:/LMMSplugins/readme.txt")){
			System.out.println(line);
		}
		
	}
	

}
