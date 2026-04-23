package com.example.demo.repositories;

import com.example.demo.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findByGenre(String genre);

    List<Book> findByYear(int year);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthors_Id(Long authorId); 
    
    @Query("SELECT DISTINCT b FROM Book b JOIN b.authors a " +
    	       "WHERE (:title IS NULL OR LOWER(b.title) = LOWER(:title)) " +
    	       "AND (:authorName IS NULL OR LOWER(a.name) = LOWER(:authorName))")
    	List<Book> findByTitleOrAuthorName(
    	    @Param("title") String title,
    	    @Param("authorName") String authorName
    	);
}