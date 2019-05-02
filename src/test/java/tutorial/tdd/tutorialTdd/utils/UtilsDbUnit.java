package tutorial.tdd.tutorialTdd.utils;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.dataset.xml.FlatXmlWriter;
import org.dbunit.operation.DatabaseOperation;
import org.xml.sax.InputSource;

public class UtilsDbUnit {
	private static String databaseUser = "root";
	private static String databasePassword = "RjUvuH&9w(>A";
	private static String databaseName = "test_tdd";
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String databaseURL = "jdbc:mysql://localhost/" + databaseName;
	private static String inputXml = "db/inputData.xml";
	
	public static String testDeleteBookWithId1 = "db/testDeleteBookWithId1.xml";
	public static String testEditBookWithId2 = "db/testEditBookWithId2.xml";
	public static String testInsertBookWithId3 = "db/testInsertBookWithId3.xml";
	
 
    
    public static IDatabaseConnection getConnection() {
    	Connection jdbcConnection = null;
    	IDatabaseConnection databaseConnection = null;
    	
		try {
			Class.forName(driverName);
			jdbcConnection = 
		    DriverManager.getConnection(databaseURL, databaseUser, databasePassword);
			
			databaseConnection = new DatabaseConnection(jdbcConnection);
		
			return databaseConnection;

		} catch(Exception error) {
			System.out.println(error);
			
			return databaseConnection;
		}
	}
	
	public static FlatXmlDataSet getInitialDataSet() {
		FlatXmlDataSet xmlDataSet = null;
		
		try {
			xmlDataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(inputXml)));
			
			return xmlDataSet;
		} catch(Exception error) {
			System.out.println(error);
			
			return xmlDataSet;
		}
	}
	
	public static void deleteData(IDatabaseConnection databaseConnection) {
		try {
			
			FlatXmlDataSetBuilder datasetBuilder = new FlatXmlDataSetBuilder();
			FlatXmlDataSet xmlDataSet = datasetBuilder.build(new FileInputStream(inputXml));
			
			FlatXmlDataSetBuilder datasetBuilderTestInsertBookWithId3 = new FlatXmlDataSetBuilder();
			FlatXmlDataSet xmlDataSetTestInsertBookWithId3 = datasetBuilderTestInsertBookWithId3.build(new FileInputStream(testInsertBookWithId3));

			DatabaseOperation.DELETE.execute(databaseConnection, xmlDataSet);
			DatabaseOperation.DELETE.execute(databaseConnection, xmlDataSetTestInsertBookWithId3);
			
		} catch (Exception error) {
			System.out.println(error);
		}
	}
	
	public static void importData(IDatabaseConnection databaseConnection) {
		try {
			
			FlatXmlDataSetBuilder datasetBuilder = new FlatXmlDataSetBuilder();
			FlatXmlDataSet xmlDataSet = datasetBuilder.build(new FileInputStream(inputXml));

			DatabaseOperation.INSERT.execute(databaseConnection, xmlDataSet);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
