package bs;

import java.sql.Connection;

import tutorial.tdd.tutorialTdd.dao.FactoryBookDAO;
import tutorial.tdd.tutorialTdd.dao.IBookDAO;
import tutorial.tdd.tutorialTdd.domain.BookDTO;

public class BookBS implements IBookBS {

	private IBookDAO libroDao = FactoryBookDAO.getBookDAO();
	
	@Override
	public void newBook(BookDTO book) throws Exception {
        try {
            Connection connectionReference = DbConnection.getDbConnection();
            libroDao.newBook(book, connectionReference);
        } catch(Exception ex) {
            System.out.println(ex);
            throw ex;
        }
	}

	@Override
	public void deleteBook(Integer bookId) throws Exception {
        try {
            Connection connectionReference = DbConnection.getDbConnection();
            libroDao.deleteBook(bookId, connectionReference);
        } catch(Exception ex) {
            System.out.println(ex);
            throw ex;
        }
	}

	@Override
	public void editBookEditorial(Integer bookId, String editorial) throws Exception {
            try {
                Connection connectionReference = DbConnection.getDbConnection();
                libroDao.modifyEditorialBook(bookId, editorial, connectionReference);
            } catch(Exception ex) {
                System.out.println(ex);
                throw ex;
            }
	}

	@Override
	public BookDTO searchBookById(Integer idLibro) throws Exception {
            Connection connectionReference = null;
                     
            try {
                connectionReference = DbConnection.getDbConnection();
                return libroDao.searchBookById(idLibro, connectionReference);
            } catch(Exception ex) {
                System.out.println(ex);
                throw ex;
            } finally {
                DbConnection.closeConnection(connectionReference);
            }
	}
}