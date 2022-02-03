package dev.jacaceresf.readinglist.repositories;

import dev.jacaceresf.readinglist.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository extends JpaRepository<Book, Long> {

    List<Book> findBookByReader(String reader);

}
