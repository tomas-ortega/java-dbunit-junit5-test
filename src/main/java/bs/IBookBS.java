package bs;

import tutorial.tdd.tutorialTdd.domain.BookDTO;

public interface IBookBS {
	public void newBook(BookDTO book) throws Exception;
	public void deleteBook(Integer bookId) throws Exception;
	public void editBookEditorial(Integer bookId, String editorial) throws Exception;
	public BookDTO searchBookById(Integer bookId) throws Exception;
}

