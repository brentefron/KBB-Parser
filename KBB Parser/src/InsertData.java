import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.*;

/**
 * Purpose: InsertData Class for inserting the data parced by Parser class into a MYSQL Database
 * 
 * @author Brent Efron
 * @author Christopher Wong
 * @version 1.0 7/24/13
 */
public class InsertData {

	/**
	 * The main method for the InsertData Class.
	 * Reads the ArrayList<ArrayList<String> carData and runs a string of SQL code inputing strings of data into a table cardata in database cars
	 *
	 * @param args Not used
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		// JDBC driver name and database URL
		String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //used to access a MYSQL database
		String DB_URL = "jdbc:mysql://localhost:8080/cars"; //most common port is 8080

		//  Database credentials (USER = username , PASS = password)
		String USER = "root"; //original username is root
		String PASS = "root"; //original username is root

		Connection conn = null;
		Statement stmt = null;

		Parser parser = new Parser();
		ArrayList<ArrayList<String>> data = (parser).carData();

		try{
			//STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			//STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");

			//STEP 4: Execute a query
			System.out.println("Inputing data..." + data);

			for (Integer n = 0; n<= data.size(); n++) {
				String classification =  data.get(n).get(0);
				String engine = data.get(n).get(1);
				Integer fuel;
				Integer bodyStyle;
				Integer transmission;
				Integer driveTrain;
				Integer seating;
				Integer mpgCity;
				Integer mpgHighway;
				Integer msrp;

				//Check for engine type
				if (engine.contains("Diesel")) {
					fuel = 2;
				} else if (engine.contains("Hybrid")) {
					fuel = 3;
				} else if (engine.contains("Electric")) {
					fuel = 4;
				} else {
					fuel = 1;
				}

				//Check bodystyle 
				if (data.get(n).get(2).contains("Sedan")) {
					bodyStyle = 1;
				} else if (data.get(n).get(2).contains("Coupe")) {
					bodyStyle = 2;
				} else if (data.get(n).get(2).contains("Wagon")) {
					bodyStyle = 3;
				} else if (data.get(n).get(2).contains("SUV")) {
					bodyStyle = 4;
				} else if (data.get(n).get(2).contains("Hatchback")) {
					bodyStyle = 5;
				} else if (data.get(n).get(2).contains("Convertible")) {
					bodyStyle = 6;
				} else if (data.get(n).get(2).contains("Minivan")) {
					bodyStyle = 7;
				} else if (data.get(n).get(2).contains("Pickup")) {
					bodyStyle = 8;
				} else if (data.get(n).get(2).contains("Roadster")) {
					bodyStyle = 9;
				} else if (data.get(n).get(2).contains("Sport Utility")) {
					bodyStyle = 10;
				} else if (data.get(n).get(2).contains("Van")) {
					bodyStyle = 11;
				} else {
					bodyStyle = 0;
				}

				//Check transmission type
				if (data.get(n).get(3).contains("Automatic")) {
					transmission = 1;
				} else if (data.get(n).get(3).contains("Manual")) {
					transmission = 2;
				} else {
					transmission = 3;
				}

				//Check drivetrain
				if (data.get(n).get(4).contains("FWD")) {
					driveTrain = 1;
				} else if (data.get(n).get(4).contains("RWD")) {
					driveTrain = 2;
				} else if (data.get(n).get(4).contains("2WD")) {
					driveTrain = 3;
				} else if (data.get(n).get(4).contains("4WD")) {
					driveTrain = 4;
				} else if (data.get(n).get(4).contains("AWD")) {
					driveTrain = 5;
				} else {
					driveTrain = 0;
				}

				//Check # of Seats
				seating = Integer.parseInt(data.get(n).get(5));

				//Check MPG - City
				if (data.get(n).get(6).contains("-")) {
					mpgCity = 0;
				} else {
					mpgCity = Integer.parseInt(regexChecker("{1,}[0-9]\\d", data.get(n).get(6)));
				}

				//Check MPG - Highway
				if (data.get(n).get(6).contains("-")) {
					mpgHighway = 0;
				} else {
					mpgHighway = Integer.parseInt(regexChecker("{1,}[0-9]\\d", data.get(n).get(6).substring(8)));;
				}

				//Check MSRP
				msrp = Integer.parseInt(data.get(n).get(7).substring(1).replace(",",""));


				stmt = conn.createStatement();
				String sql = ("insert into cardata " +
						"values('" + (n+1) + "', '" + classification + "','" + engine + "','" + fuel + "','" + bodyStyle + "','"+ transmission +"','"+ driveTrain + "','"+ seating + "','"+ mpgCity + "','" + mpgHighway + "','" + msrp + "')"); 
				stmt.executeUpdate(sql);
			}
			System.out.println("Done :)");
		} catch(SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		} catch(Exception e) {
			//Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			//finally block used to close resources
			try {
				if(stmt!=null)
					conn.close();
			} catch(SQLException se) {
			} try {
				if(conn!=null)
					conn.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}

	/**
	 * Finds and selects a specifc pattern in str2check based on the given parameters in String theRegex
	 * 
	 * @param theRegex Parameters for regexChecker to follow
	 * @param str2Check String to check parameters on
	 * @return matched substring of str2check
	 */
	public static String regexChecker(String theRegex, String str2Check)
	{
		Pattern checkRegex = Pattern.compile(theRegex);
		Matcher regexMatcher = checkRegex.matcher(str2Check);
		while(regexMatcher.find())
		{
			if(regexMatcher.group().length() != 0)
				return(regexMatcher.group().trim());
		}
		return regexMatcher.group().trim();
	}	
}





