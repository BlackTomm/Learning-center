package guides.spring_db.db;

import guides.spring_db.domain.Book;

public interface BookRepository {
    void addBook(Book book);
    void updateBook(Book book);
    Book findBook(long book_id);
}
