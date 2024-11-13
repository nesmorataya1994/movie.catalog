package com.movie.catalog.service;

import com.movie.catalog.model.entity.Rating;

import java.util.List;

public interface IRatingService {
    Rating registerRating (Long userId, Long movieId, Integer stars, String review);

    List<Rating> findRatingsByUser(Long userId);

    void deleteRating(Long userId, Long movieId);
}
