package com.movie.catalog.repository;

import com.movie.catalog.model.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT DISTINCT m FROM Movie m " +
            "LEFT JOIN m.categories c " +
            "WHERE (:searchTerm IS NULL OR " +
            "      LOWER(m.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "      LOWER(m.movieSynopsis) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) " +
            "AND (:categoryId IS NULL OR c.id = :categoryId) " +
            "AND (:yearOfRelease IS NULL OR m.yearOfRelease = :yearOfRelease)"
    )
    Page<Movie> searchMovies(
            @Param("searchTerm") String searchTerm,
            @Param("categoryId") Long categoryId,
            @Param("yearOfRelease") Integer yearOfRelease,
            Pageable pageable
    );

}
