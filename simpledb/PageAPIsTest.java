package simpledb;
import simpledb.file.Block;
import simpledb.file.Page;
import simpledb.server.SimpleDB;
import java.util.Date;
import java.util.Arrays;

public class PageAPIsTest {
    public static void main(String[] args) {
		//Initialize the managers
    	SimpleDB.init("simpleDB");
    	//Testing starts
    	System.out.println("Result of Set & Get Short Test:"+(testSetAndGetShort()?"Success":"Failure"));
    	System.out.println("Result of Set & Get Boolean Test:"+(testSetAndGetBoolean()?"Success":"Failure"));
    	System.out.println("Result of Set & Get Byte Array Test:"+(testSetAndGetBytes()?"Success":"Failure"));
    	System.out.println("Result of Set & Get Date Test:"+(testSetAndGetDate()?"Success":"Failure"));
	}
    
    public static boolean testSetAndGetShort() {
		Page pageSet = new Page();
		short valueSet = 123;
		pageSet.setShort(20, valueSet);
		Block blk = pageSet.append("testFile");
		Page pageGet = new Page();
		pageGet.read(blk);
		return valueSet == pageGet.getShort(20);
    }
    
    public static boolean testSetAndGetBoolean() {
    	Page pageSet = new Page();
    	boolean valueSet = true;
		pageSet.setBoolean(20, valueSet);
		Block blk = pageSet.append("testFile");
		Page pageGet = new Page();
		pageGet.read(blk);
		return valueSet == pageGet.getBoolean(20);
    }
    
    public static boolean testSetAndGetBytes() {
    	Page pageSet = new Page();
    	// getBytes method returns a byte array, this byte array is processed
    	byte[] valueSet = "TestByteArray".getBytes();
		pageSet.setBytes(20, valueSet);
		Block blk = pageSet.append("testFile");
		Page pageGet = new Page();
		pageGet.read(blk);
    	return Arrays.equals(valueSet,pageGet.getBytes(20));
    }
    
    public static boolean testSetAndGetDate() {
    	Page pageSet = new Page();
    	Date valueSet = new Date();
		pageSet.setDate(20, valueSet);
		Block blk = pageSet.append("testFile");
		Page pageGet = new Page();
		pageGet.read(blk);
    	return valueSet.equals(pageGet.getDate(20));
    }
}
