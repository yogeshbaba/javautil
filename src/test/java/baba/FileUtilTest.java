package baba;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import util.file.FileUtil;



public class FileUtilTest {
	
	static String singleLineFile = "";
	static String singelineContent = "this is a sample test data";
	
	static String multiLineFileName = "";
	static String multiLineContent = "this is a sample test data\nthis is a for testing";
	
	
	@BeforeClass
	public static void createData() {
		
		Date date = new Date();
		String tmpDir = "tmp"+date.getTime();
		File f = new File(tmpDir);
		System.out.println(f.mkdir());
		
		singleLineFile = tmpDir+"/test1.txt";
		try(FileWriter writer = new FileWriter(singleLineFile)) {
			writer.write(singelineContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		multiLineFileName = tmpDir+"/test2.txt";
		try(FileWriter writer = new FileWriter(multiLineFileName)) {
			writer.write(multiLineContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadAsString() {
		Assert.assertEquals(singelineContent,FileUtil.readAsString(singleLineFile));
	}
	
	@Test
	public void testReadAsString_MultiLine() {
		Assert.assertEquals(multiLineContent,FileUtil.readAsString(multiLineFileName));
	}
	
	@Test
	public void testReadMultiLine() {
		String[] content = multiLineContent.split("\n");
		int i = 0;
		for(String line : FileUtil.read(multiLineFileName)) {
			Assert.assertEquals(content[i],line);
			i++;
		}
	}
}
