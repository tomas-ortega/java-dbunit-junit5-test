package tutorial.tdd.tutorialTdd.dao;


import java.sql.Connection;
import java.sql.SQLException;

import tutorial.tdd.tutorialTdd.domain.BookDTO;

public interface IBookDAO {
	public void newBook(BookDTO book, Connection connectionReference) throws SQLException;
	public void deleteBook(Integer bookId, Connection connectionReference) throws SQLException;
	public void modifyEditorialBook(Integer bookId, String editorial, Connection connectionReference) throws SQLException;
	public BookDTO searchBookById(Integer booId, Connection connectionReference) throws SQLException;
}

