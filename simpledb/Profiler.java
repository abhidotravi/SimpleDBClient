package simpledb;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;

import simpledb.remote.SimpleDriver;

public class Profiler {

	public static void main(String[] args) {
		System.out.println("----------------------------");
		System.out.println("Analysis of query performace");
		System.out.println("----------------------------");
		
		Connection conn = null;
		try {
			// Step 1: connect to database server
			Driver d = new SimpleDriver();
			conn = d.connect("jdbc:simpledb://localhost", null);;
			long avgTime = 0;

			// Step 2: Run the experiments multiple times
			avgTime = experiment(conn, 5);
			
			// Step 3: Average time taken
			System.out.println("\nAverage time to execute 1000 " + 
					" random select statements : " + avgTime + " ms");

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// Step 4: close the connection
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static long experiment(Connection conn, int times) throws SQLException {
		
		Statement stmt = conn.createStatement();
		
		//5 random select queries
		String qry1 = "select SId, SName from STUDENT";
		
		String qry2 = "select SId, SName, Title from STUDENT, COURSE where "
				+ "MajorId = DeptId and GradYear = ";
		
		String qry3 = "select Title from COURSE";
		
		String qry4 = "select Title, DeptId "
				+ "from COURSE, DEPT "
				+ "where DId = DeptId";
		
		String qry5 = "select SId, SName, Title, Grade "
				+ "from STUDENT, COURSE, ENROLL, SECTION "
				+ "where SId = StudentId and SectionId = SectId and CourseId = Cid";
		
		// print the details of the experiment
		System.out.println("# Experiment #");
		System.out.println("Query 1 - " + qry1);
		System.out.println("Query 2 - " + qry2);
		System.out.println("Query 3 - " + qry3);
		System.out.println("Query 4 - " + qry4);
		System.out.println("Query 5 - " + qry5);
		
		// start timer
		long start = System.currentTimeMillis();
					
		// run the experiment multiple times for better accuracy
		for(int i = 0; i < times; i++) {
			// execute the 5 queries, 200 times. A total of 1000 queries
			for(int j = 0; j < 200; j++) {
				stmt.executeQuery(qry1);
				//try to randomize the query by randomly generating the year
				stmt.executeQuery(qry2 + Integer.toString(2001 + ((int)(Math.random() * 100) % 5)));
				stmt.executeQuery(qry3);
				stmt.executeQuery(qry4);
				stmt.executeQuery(qry5);		
			}
		}
		
		// end timer
		long end = System.currentTimeMillis();

		// return the average time for the experiment
		return((end - start) / times);
	}

}
