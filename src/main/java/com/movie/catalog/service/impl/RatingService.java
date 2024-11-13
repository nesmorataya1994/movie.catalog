package com.movie.catalog.service.impl;

import com.movie.catalog.repository.MovieRepository;
import com.movie.catalog.repository.RatingRepository;
import com.movie.catalog.repository.UserRepository;
import com.movie.catalog.model.entity.Movie;
import com.movie.catalog.model.entity.Rating;
import com.movie.catalog.model.entity.RatingId;
import com.movie.catalog.model.entity.User;
import com.movie.catalog.service.IRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService implements IRatingService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;

    @Override
    public Rating registerRating(Long userId, Long movieId, Integer stars, String review) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (ratingRepository.existsById(new RatingId(userId, movieId))) {
            throw new RuntimeException("User has already rated this movie");
        }

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setMovie(movie);
        rating.setStars(stars);
        rating.setReview(review);

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> findRatingsByUser(Long userId) {
        return ratingRepository.findByUser(userRepository.findById(userId).orElse(null));
    }

    @Override
    public void deleteRating(Long userId, Long movieId) {

        RatingId ratingId = new RatingId(userId, movieId);

        if (!ratingRepository.existsById(ratingId)) {
            throw new RuntimeException("Rating not found for this user and movie");
        }

        ratingRepository.deleteById(ratingId);
    }
}
