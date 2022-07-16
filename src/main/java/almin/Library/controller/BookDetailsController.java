package almin.Library.controller;

import almin.Library.model.Book;
import almin.Library.model.BookDetails;
import almin.Library.service.BookDetailsService;
import almin.Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookDetailsController {

    @Autowired
    private BookDetailsService bookDetailsService;
    @Autowired
    private BookService bookService;

    @PostMapping("/saveBookDetails")
    public void saveBookDetails(BookDetails bookDetails) {
        bookDetailsService.saveDetails(bookDetails);
    }

    @GetMapping("/bookdetails/{id}")
    public ModelAndView detailForOneBook(@PathVariable(name = "id") long id, Model model) {
        ModelAndView mv = new ModelAndView("book_details");
        Book book = bookService.findById(id);
        BookDetails bookDetails = book.getBookDetails();
        model.addAttribute("bookdetails", bookDetails);
        return mv;
    }

}
