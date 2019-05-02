package tutorial.tdd.tutorialTdd;


import org.dbunit.Assertion;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import bs.BookBS;
import tutorial.tdd.tutorialTdd.domain.BookDTO;
import tutorial.tdd.tutorialTdd.utils.UtilsDbUnit;

class BookDAOTest {
    private static IDataSet initialDataSet;
    private static IDatabaseConnection databaseConnection;
	
	private BookBS bookBs = new BookBS();
		
	@BeforeAll
	static void getConnection() {
		
		BookDAOTest.databaseConnection = UtilsDbUnit.getConnection();
		
		BookDAOTest.initialDataSet = UtilsDbUnit.getInitialDataSet();
	}
	
	@BeforeEach
	void getSetUpOperation() {
		UtilsDbUnit.importData(BookDAOTest.databaseConnection);
	}

	@AfterEach
	void getTearDownOperation() {
		UtilsDbUnit.deleteData(BookDAOTest.databaseConnection);
	}
	
	
	@Test
	@DisplayName("Test check rowCount initial DataSet")
	public void testDatosCargados() throws Exception
	{
	  assertNotNull(BookDAOTest.initialDataSet);
	  int rowCount = BookDAOTest.initialDataSet.getTable("book").getRowCount();
	  assertEquals(2, rowCount);
	}
	
	@Test
	@DisplayName("Test read all rows from book table")
	public void testReadAllRows() throws Exception
	{
	  QueryDataSet queryDataSet = new QueryDataSet(BookDAOTest.databaseConnection);
	  queryDataSet.addTable ("book", "SELECT * FROM book");

	  
	  Assertion.assertEquals (BookDAOTest.initialDataSet, queryDataSet);
	}
	
	@Test
	@DisplayName("Test delete a book with id 1")
	public void deleteBook() throws Exception {
		IDataSet expectedDataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(UtilsDbUnit.testDeleteBookWithId1)));
		
		bookBs.deleteBook(1);
		
		String[] table = {"book"};
		
		IDataSet realDataSet = BookDAOTest.databaseConnection.createDataSet(table);
		
		Assertion.assertEquals(expectedDataSet, realDataSet);
	}
	
	@Test
	@DisplayName("Test edit book with id 2")
	public void editBookWithId2() throws Exception {
		IDataSet expectedDataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(UtilsDbUnit.testEditBookWithId2)));
		 
		bookBs.editBookEditorial(2, "Anaya"); 
		
		String[] table = {"book"};
		
		IDataSet realDataSet = BookDAOTest.databaseConnection.createDataSet(table);
		
		Assertion.assertEquals(expectedDataSet, realDataSet);
	}
	
	@Test
	@DisplayName("Test insert book with id 3")
	public void testInsertBookWithId3() throws Exception {
		IDataSet expectedDataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(UtilsDbUnit.testInsertBookWithId3)));
		
		BookDTO libro = new BookDTO(3, "La historia interminable", "Michael Ende", "Alfaguara", 496);
		bookBs.newBook(libro);
		
		String[] table = {"book"};
		
		IDataSet realDataSet = BookDAOTest.databaseConnection.createDataSet(table);
		
		Assertion.assertEquals(expectedDataSet, realDataSet);
	}
}
