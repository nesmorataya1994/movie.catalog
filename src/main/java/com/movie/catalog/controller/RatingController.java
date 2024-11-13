package com.movie.catalog.controller;


import com.movie.catalog.model.dto.RatingDTO;
import com.movie.catalog.model.dto.RatingRequestDTO;
import com.movie.catalog.model.entity.Rating;
import com.movie.catalog.service.impl.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/register")
    public ResponseEntity<RatingDTO> registerRating(@Valid @RequestBody RatingRequestDTO ratingRequest) {
        Rating savedRating = ratingService.registerRating(
                ratingRequest.getUserId(),
                ratingRequest.getMovieId(),
                ratingRequest.getStars(),
                ratingRequest.getReview()
        );
        return new ResponseEntity<>(convertToDTO(savedRating), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingDTO>> getUserRatings(@PathVariable Long userId) {
        List<Rating> ratings = ratingService.findRatingsByUser(userId);
        List<RatingDTO> ratingDTOs = ratings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ratingDTOs);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRating(@RequestParam Long userId, @RequestParam Long movieId) {
        ratingService.deleteRating(userId, movieId);
        return ResponseEntity.noContent().build();
    }

    private RatingDTO convertToDTO(Rating rating) {
        return RatingDTO.builder()
                .movieId(rating.getMovie().getId())
                .movieTitle(rating.getMovie().getName())
                .stars(rating.getStars())
                .review(rating.getReview())
                .build();
    }
}
