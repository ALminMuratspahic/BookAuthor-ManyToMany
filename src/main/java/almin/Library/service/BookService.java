package almin.Library.service;

import almin.Library.model.Book;
import almin.Library.model.BookDetails;
import almin.Library.repositroy.BookDetailsRepo;
import almin.Library.repositroy.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;
    public Book findById(long id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new NoSuchElementException("Book with id= " + id+" does not exist");
        }
    }
    public List<Book> findAllBook() {
        return bookRepo.findAll();
    }

    //Save Book
    public void saveBook(Book book) {
        bookRepo.save(book);
    }

    //Delete Book
    public void deleteBook(Book book) {
        bookRepo.delete(book);
    }
    public List<Book> sortBookAsc() {
        return bookRepo.sortBookASC();
    }
    public List<Book> sortBookDESC() {
        return bookRepo.sortBookDESC();
    }
    public int countBook(List<Book> books) {
        return books.size();
    }
    public List<Book> filterBook(String title) {
        if (title == null || title.isEmpty()) {
            return bookRepo.findAll();
        } else {
            return bookRepo.searchBook(title);
        }

    }


}
