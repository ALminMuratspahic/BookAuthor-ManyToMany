package almin.Library.controller;

import almin.Library.model.Author;
import almin.Library.model.Book;
import almin.Library.service.AuthorService;
import almin.Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @Autowired
    BookService bookService;

    @GetMapping("/authors")
    public String getAllAuthors(Model model) {
        List<Author> authors = authorService.findAll();

        List<Book> books = bookService.findAllBook();
        model.addAttribute("books", books);
        model.addAttribute("authors", authors);
        return "authors";
    }

    @PostMapping("/saveAuthor")
    public String saveAuthor(@ModelAttribute Author author) {
        authorService.saveAuthor(author);
        return "redirect:/createbook";
    }

    @PostMapping("/saveAuthor2")
    public String saveAuthor2(@ModelAttribute Author author) {

        authorService.saveAuthor(author);
        return "redirect:/authors";
    }

    @GetMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable(name = "id") int id) {
        Author author = authorService.findById(id);
        if (author.getBooks().isEmpty() || author.getBooks() == null) {
            authorService.deleteAuthor(author);
        } else {
            return "redirect:/authors";
        }
        return "redirect:/authors";
    }

    @GetMapping("/createauthor")
    public String makeAuthor(Model model) {
        Author author = new Author();
        List<Book> books = bookService.findAllBook();
        model.addAttribute("books", books);
        model.addAttribute("author", author);
        return "create_author";
    }

    @GetMapping("/editAuthor/{id}")
    public ModelAndView editAuthor(@PathVariable(name = "id") int id, Model model) {
        ModelAndView modelAndView = new ModelAndView("edit_author");
        Author author = authorService.findById(id);
        List<Book> books = author.getBooks();
        model.addAttribute("books", bookService.findAllBook());
        modelAndView.addObject(author);
        modelAndView.addObject(books);

        return modelAndView;
    }


    @GetMapping("/sortAuthorAsc")
    public String sortAuthorsAsc(Model model) {
        List<Author> authors = authorService.sortAuthorsAsc();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/sortAuthorDesc")
    public String sortAuthorsDesc(Model model) {
        List<Author> authors = authorService.sortAuthorsDesc();
        model.addAttribute("authors", authors);
        return "authors";
    }


}
