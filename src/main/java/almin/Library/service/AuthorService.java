package almin.Library.service;

import almin.Library.model.Author;
import almin.Library.repositroy.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepo authorRepo;
    public Author findById(long id) {
        Optional<Author> author = authorRepo.findById(id);
        if (author.isPresent()) {
            return author.get();
        } else {
            throw new NoSuchElementException("There is no author with id number : " + id);
        }
    }
    public List<Author> findAll() {
        return authorRepo.findAll();
    }
    public void saveAuthor(Author author) {
        authorRepo.save(author);
    }
    public void deleteAuthor(Author author) {
        authorRepo.delete(author);
    }
    public List<Author> sortAuthorsAsc() {
        return authorRepo.sortAuthorAsc();
    }
    public List<Author> sortAuthorsDesc() {
        return authorRepo.sortAuthorDesc();
    }


}
