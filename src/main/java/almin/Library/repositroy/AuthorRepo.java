package almin.Library.repositroy;

import almin.Library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {


    @Query(value = "select a from Author a order by a.firstName ASC,a.lastName")
    public List<Author> sortAuthorAsc();

    @Query(value = "select a from Author a order by a.firstName DESC,a.lastName")
    public List<Author> sortAuthorDesc();


}
