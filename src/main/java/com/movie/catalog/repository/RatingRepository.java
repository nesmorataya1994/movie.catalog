package com.movie.catalog.repository;

import com.movie.catalog.model.entity.Rating;
import com.movie.catalog.model.entity.RatingId;
import com.movie.catalog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingId> {

    List<Rating> findByUser(User user);

    boolean existsById(RatingId id);


}
