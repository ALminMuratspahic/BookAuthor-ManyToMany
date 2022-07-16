package almin.Library.controller;

import almin.Library.model.Author;
import almin.Library.model.Book;
import almin.Library.model.BookDetails;
import almin.Library.service.AuthorService;
import almin.Library.service.BookDetailsService;
import almin.Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookDetailsService bookDetailsService; //***

    @GetMapping("/")
    public String getAllBook(Model model) {
        List<Book> books = bookService.findAllBook();
        List<Author> authors = authorService.findAll();

        int numberOfBook = bookService.countBook(books);
        model.addAttribute("numberOfBook", numberOfBook);

        model.addAttribute("authors", authors);
        model.addAttribute("books", books);
        return "home";
    }

    @PostMapping("/savebook")
    public String saveBook(@ModelAttribute("book") Book book, @ModelAttribute("bookdetails") BookDetails bookDetails) {
        book.setBookDetails(bookDetails);
        bookDetails.setBook(book);

        bookDetailsService.saveDetails(bookDetails); // ***
        // bookService.saveBook(book);

        return "redirect:/";
    }


    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable(name = "id") int id) {
        Book book = bookService.findById(id);
        BookDetails bookDetails = book.getBookDetails();
        bookDetailsService.deleteBookDetails(bookDetails);
        bookService.deleteBook(book);
        return "redirect:/";
    }

    @GetMapping("/createbook")
    public String createBook(Model model) {

        BookDetails bookDetails = new BookDetails();
        Book book = new Book();
        List<Author> authors = authorService.findAll();

        model.addAttribute("authors", authors);
        model.addAttribute("book", book);
        model.addAttribute("bookdetails", bookDetails);

        return "create_book";
    }

    @GetMapping("/editBook/{id}")
    public ModelAndView updateBook(@PathVariable(name = "id") int id, Model model) {
        ModelAndView modelAndView = new ModelAndView("edit_book");

        Book book = bookService.findById(id);
        BookDetails bookDetails = book.getBookDetails();

        model.addAttribute("bookdetails", bookDetails);
        model.addAttribute("authors", authorService.findAll());


        modelAndView.addObject(book);
        modelAndView.addObject(bookDetails);

        return modelAndView;
    }

    @GetMapping("/sortAsc")
    public String sortBookAsc(Model model) {
        List<Book> books = bookService.sortBookAsc();
        int numberOfBook=bookService.findAllBook().size();
        model.addAttribute("numberOfBook",numberOfBook);
        model.addAttribute("books", books);
        return "home";
    }

    @GetMapping("/sortDesc")
    public String sortBookDESC(Model model) {
        List<Book> books = bookService.sortBookDESC();
        int numberOfBook=bookService.findAllBook().size();
        model.addAttribute("numberOfBook",numberOfBook);
        model.addAttribute("books", books);
        return "home";
    }

    @RequestMapping(path = {"/", "/search"})
    public String filterBook(Model model, String keyword) { //

        List<Book> books;
        if (keyword != null) {
            books = bookService.filterBook(keyword);
        } else {
            books = bookService.findAllBook();
        }
        int number = bookService.countBook(books);
        model.addAttribute("numberOfBook", number);
        model.addAttribute("books", books);
        return "home";
    }

}
