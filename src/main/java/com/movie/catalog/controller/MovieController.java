package com.movie.catalog.controller;

import com.movie.catalog.repository.MovieRepository;
import com.movie.catalog.model.dto.MovieSearchRequest;
import com.movie.catalog.model.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository movieDao;

    @GetMapping
    public List<Movie> getAllMovie() {
        return movieDao.findAll();
    }



    @GetMapping("/search")
    public Page<Movie> searchMovies(MovieSearchRequest searchRequest) {
        return movieDao.searchMovies(
                searchRequest.getSearchTerm(),
                searchRequest.getCategoryId(),
                searchRequest.getYearOfRelease(),
                PageRequest.of(
                        searchRequest.getPage(),
                        searchRequest.getSize(),
                        Sort.Direction.fromString(searchRequest.getSortDirection()),
                        searchRequest.getSortBy()
                )
        );

    }

}
