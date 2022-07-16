package almin.Library.service;

import almin.Library.model.BookDetails;
import almin.Library.repositroy.BookDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDetailsService {
    @Autowired
    private BookDetailsRepo bookDetailsRepo;
    public void saveDetails(BookDetails bookDetails) {
        bookDetailsRepo.save(bookDetails);
    }
    public void deleteBookDetails(BookDetails bookDetails) {
        bookDetailsRepo.delete(bookDetails);
    }

}
