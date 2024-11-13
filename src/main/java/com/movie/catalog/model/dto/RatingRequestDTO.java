package com.movie.catalog.model.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequestDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long movieId;

    @Min(1)
    @Max(5)
    private int stars;

    private String review;
}
