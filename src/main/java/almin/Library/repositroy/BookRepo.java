package almin.Library.repositroy;

import almin.Library.model.Book;
import almin.Library.model.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    @Query(value = "select b from Book b order by b.title ASC")
    public List<Book> sortBookASC();

    @Query(value = "select b from Book b order by b.title DESC")
    public List<Book> sortBookDESC();

    @Query(value = "select b from Book b where " + "lower(b.title) like lower(concat('%',:keyword,'%')) "
            + "or lower(b.genre) like lower(concat('%',:keyword,'%'))")
    public List<Book> searchBook(@Param("keyword") String keyword);

}
