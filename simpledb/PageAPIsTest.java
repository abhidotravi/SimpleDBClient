package simpledb;
import simpledb.file.Block;
import simpledb.file.Page;
import simpledb.server.SimpleDB;
import java.util.Date;
import java.util.Arrays;

public class PageAPIsTest {
	private static final String SUCCESS = "Success";
	private static final String FAILURE = "Failure";
	private static final String TEST_FILE = "testFile";
	
    public static void main(String[] args) {
		// Initialize the managers
    	SimpleDB.init("simpleDB");
    	// ----------------------------------   Testing starts   ---------------------------------- //
    	System.out.println("Result of Set & Get Short Test:"+(testSetAndGetShort()?SUCCESS:FAILURE));
    	System.out.println("Result of Set & Get Boolean Test:"+(testSetAndGetBoolean()?SUCCESS:FAILURE));
    	System.out.println("Result of Set & Get Byte Array Test:"+(testSetAndGetBytes()?SUCCESS:FAILURE));
    	System.out.println("Result of Set & Get Date Test:"+(testSetAndGetDate()?SUCCESS:FAILURE));
    	// ----------------------------------   Testing ends   ---------------------------------- //
	}
    /**
     * Tests whether the setShort() and getShort() methods work as per requirement
     * @return a boolean is returned indicating whether the test succeeded or failed
     */
    private static boolean testSetAndGetShort() {
    	// Create a new page
		Page pageSet = new Page();
		short valueSet = 123;
		// Set a 123 short value at the offset 20
		pageSet.setShort(20, valueSet);
		// Append it to the test file
		Block blk = pageSet.append(TEST_FILE);
		// Create a new page
		Page pageGet = new Page();
		pageGet.read(blk);
		// Read the value at offset 20 and check whether it is the same value written
		// Return true if the value obtained was the same as the value that was set(123)
		// Else return false
		return valueSet == pageGet.getShort(20);
    }

    /**
     * Tests whether the setBoolean() and getBoolean() methods work as per requirement
     * @return a boolean is returned indicating whether the test succeeded or failed
     */
    private static boolean testSetAndGetBoolean() {
    	// Create a new page
    	Page pageSet = new Page();
    	boolean valueSet = true;
    	// Set a true boolean value at the offset 20
		pageSet.setBoolean(20, valueSet);
		// Append it to the test file
		Block blk = pageSet.append(TEST_FILE);
		// Create a new page
		Page pageGet = new Page();
		pageGet.read(blk);
		// Read the value at offset 20 and check whether it is the same value written
		// Return true if the value obtained was the same as the value that was set which is true
		// Else return false
		return valueSet == pageGet.getBoolean(20);
    }
    
    /**
     * Tests whether the setBytes() and getBytes() methods work as per requirement
     * @return a boolean is returned indicating whether the test succeeded or failed
     */
    private static boolean testSetAndGetBytes() {
    	// Create a new page
    	Page pageSet = new Page();
    	// getBytes method returns a byte array, this byte array is set at offset 20
    	byte[] valueSet = "TestByteArray".getBytes();
		pageSet.setBytes(20, valueSet);
		// Append it to the test file
		Block blk = pageSet.append(TEST_FILE);
		// Create a new page
		Page pageGet = new Page();
		pageGet.read(blk);
		// Read the value at offset 20 and check whether it is the same value written
		// Return true if the value obtained was the same as the value that was set 
		// Else return false
    	return Arrays.equals(valueSet,pageGet.getBytes(20));
    }

    /**
     * Tests whether the setDate() and getDate() methods work as per requirement
     * @return a boolean is returned indicating whether the test succeeded or failed
     */
    private static boolean testSetAndGetDate() {
    	// Create a new page
    	Page pageSet = new Page();
    	Date valueSet = new Date();
    	// Set current date at the offset 20
		pageSet.setDate(20, valueSet);
		// Append it to the test file
		Block blk = pageSet.append(TEST_FILE);
		// Create a new page
		Page pageGet = new Page();
		pageGet.read(blk);
		// Read the value at offset 20 and check whether it is the same value written
		// Return true if the value obtained was the same as the current date which was set
		// Else return false
    	return valueSet.equals(pageGet.getDate(20));
    }
}
